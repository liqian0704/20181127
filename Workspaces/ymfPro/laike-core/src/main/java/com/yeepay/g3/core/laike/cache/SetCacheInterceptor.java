package com.yeepay.g3.core.laike.cache;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Description: Mybatis拦截器，在insert和update执行后拦截，把结果先放缓存在返回上层
 * Author: jiawen.huang
 * Date: 17/7/23
 * Time: 15:10
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Intercepts({
		@Signature(type = Executor.class, method = "update",
				args = {MappedStatement.class, Object.class})
})
@Component
public class SetCacheInterceptor extends BaseInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if ((Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CACHE_SWITCH)) {
			//建立pKey-entity缓存对象
			if (target instanceof DefaultParameterHandler) {
				Object object = ((DefaultParameterHandler) target).getParameterObject();
				CacheUtil.refresh(systemName, object);
			}
		}
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.systemName = (String) properties.get("systemName");
	}
}
