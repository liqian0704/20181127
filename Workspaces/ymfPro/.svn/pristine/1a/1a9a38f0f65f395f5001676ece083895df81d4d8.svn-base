package com.yeepay.g3.core.laike.aop;

import com.yeepay.g3.common.laike.utils.LoggerUtil;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
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

import java.lang.reflect.Method;

/**
 * Description: facade请求日志切面
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 11:39
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
@Aspect
@Order(1)
public class InnerFacadeLogAdvice {

	private static Logger LOGGER = LoggerFactory.getLogger(InnerFacadeLogAdvice.class);

	@Around(value = "execution(* com.yeepay.g3.facade.laike.facade..*.*(..))")
	public Object facadeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object[] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		String methodName = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
		Object result = null;
		try {
			LOGGER.info("[laike_sys] - [入参] - [" + methodName + "] - [" + LoggerUtil.argsToString(args) + "]");
			result = joinPoint.proceed();
		} catch (Throwable e) {
			if (result == null) {
				result = method.getReturnType().newInstance();
			}
			handelException(methodName, (BaseResponse) result, e);
		} finally {
			long time = (System.currentTimeMillis() - startTime);
			LOGGER.info("[laike_sys] - [用时] - [" + methodName + "] - 耗时[" + time + "]毫秒");
		}
		LOGGER.info("[laike_sys] - [返回] - [" + methodName + "] - [" + LoggerUtil.argsToString(result) + "]");
		return result;
	}


	private void handelException(String method, BaseResponse result, Throwable e) {
		BaseResponse responseDTO = result;
		String errorCode;
		String errorMsg;
		ExceptionType exceptionType = ExceptionType.BIZ_EXCEPTION;
		if (e instanceof LaikeSysException) {
			LaikeSysException bizException = (LaikeSysException) e;
			errorCode = bizException.getDefineCode();
			errorMsg = bizException.getMessage();
		} else if (e instanceof IllegalArgumentException) {
			errorCode = ErrorCode.PARAM_EXCEPTION;
			errorMsg = e.getMessage();
			exceptionType = ExceptionType.PARAM_EXCEPTION;
		} else {
			errorCode = ErrorCode.SYSTEM_EXCEPTION;
			errorMsg = "系统异常";
			exceptionType = ExceptionType.SYS_EXCEPTION;
		}
		logError(method, e, exceptionType, errorCode, errorMsg);
		setFailResponse(responseDTO, errorCode, errorMsg);
	}

	private void logError(String methodName, Throwable e, ExceptionType exceptionType,
						  String errorCode, String errorMsg) {
		LOGGER.warn("[laike_sys] - [" + exceptionType.description + "] - [" + methodName + "] - "
				+ "errorCode:[" + errorCode + "], " + "errorMsg:[" + errorMsg + "]", e);
	}

	private void setFailResponse(BaseResponse responseDTO, String errorCode, String errorMsg) {
		responseDTO.setStatus(ResponseStatus.FAILURE);
		responseDTO.setErrCode(errorCode);
		responseDTO.setErrMsg(errorMsg);
	}

	private enum ExceptionType {

		PARAM_EXCEPTION("参数异常"),
		BIZ_EXCEPTION("业务异常"),
		SYS_EXCEPTION("系统异常");

		private final String description;

		ExceptionType(String description) {
			this.description = description;
		}
	}

}
