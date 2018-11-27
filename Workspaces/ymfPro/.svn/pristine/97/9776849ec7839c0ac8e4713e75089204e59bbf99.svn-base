package com.yeepay.g3.core.laike.cache;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Description: 、拦截，先查缓存，没有继续查数据库
 * Author: jiawen.huang
 * Date: 17/7/26
 * Time: 15:05
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */

@Intercepts({
		@Signature(type = Executor.class, method = "query",
				args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
		@Signature(type = Executor.class, method = "query",
				args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
@Component
public class GetCacheInterceptor extends BaseInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		boolean cacheable = true;
		//获取Executor.query()方法参数
		Object[] args = invocation.getArgs();
		//缓存配置预处理
		CachePretreatment cachePretreatment = CacheUtil.getCachePretreatment(systemName, args);
		//开启缓存
		if (cachePretreatment.isCacheable()) {
			String indexKey = cachePretreatment.getIndexKey();
			//用index-key查询persistent-key
			String persistentKey = (String) CacheUtil.find(String.class, indexKey);
			//persistent-key存在下查询entity
			Object object = CacheUtil.find(cachePretreatment.getTargetClz(), persistentKey);
			if (null == object) {
				object = invocation.proceed();
				List list = (List) object;
				if (null != list && list.size() == 1) {
					//缓存index-pKey,pKey-entity
					CacheUtil.refresh(systemName, indexKey, list.get(0));
				}
				return object;
			} else {
				List list = new ArrayList();
				list.add(object);
				return list;
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.systemName = (String) properties.get("systemName");
	}
}















