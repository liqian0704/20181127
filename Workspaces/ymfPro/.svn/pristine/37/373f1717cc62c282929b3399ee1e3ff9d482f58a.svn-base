package com.yeepay.g3.core.laike.aop;

import com.yeepay.g3.common.laike.utils.LoggerUtil;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description: 远程接口调用日志Handler
 * Author: jiawen.huang
 * Date: 16/9/19
 * Time: 15:19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class RemoteFacadeLogHandler implements InvocationHandler {

	private static final Logger logger = LoggerFactory.getLogger(RemoteFacadeLogHandler.class);

	/**
	 * 目标对象
	 */

	private Object target;

	/**
	 * 外部系统
	 */
	private String externalName;

	/**
	 * 构造方法
	 *
	 * @param target 目标对象
	 */
	public RemoteFacadeLogHandler(Object target, String externalName) {
		this.target = target;
		this.externalName = externalName;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		long startTime = System.currentTimeMillis();
		String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
		logger.info("[remote_sys] - [入参] - [" + externalName + "] - [" + methodName + "] - "
				+ LoggerUtil.arrayToString(args));
		try {
			Object result = AopUtils.invokeJoinpointUsingReflection(target, method, args);
			logger.info("[remote_sys] - [返回] - [" + externalName + "] - [" + methodName + "] - "
					+ LoggerUtil.argsToString(result));
			return result;
		} catch (Throwable e) {
			logger.warn("[remote_sys] - [异常] - [" + externalName + "] - [" + methodName + "]", e);
			throw e;
		} finally {
			long time = (System.currentTimeMillis() - startTime);
			logger.info("[remote_sys] - [时间] - [" + externalName + "] - [" + methodName + "] - 耗时[" + time
					+ "]毫秒");
		}
	}
}
