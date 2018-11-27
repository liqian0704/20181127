package com.yeepay.g3.core.laike.aop;

import com.yeepay.g3.core.laike.utils.AppKey;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description: 粗略过滤分组权限
 * Author: jiawen.huang
 * Date: 2017/6/24
 * Time: 18:21
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
@Aspect
@Order(3)
public class BizControlIntercepter {

	private static Logger LOGGER = LoggerFactory.getLogger(BizControlIntercepter.class);

	@Around(value = "execution(* com.yeepay.g3.core.laike.biz.*.*(..))")
	public Object facadeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		AppKey appKey = methodSignature.getMethod().getClass().getAnnotation(AppKey.class);
		if (appKey != null) {
			AppSourceEnum appSourceEnum = ((BaseRequest) args[0]).getAppSourceEnum();
			if (null != appSourceEnum) {
				if (appKey.value().equals(appSourceEnum)) {
					throw new LaikeSysException(ErrorCode.USER_PERMISSION_DENY);
				}
			}
		}
		return joinPoint.proceed();
	}
}
