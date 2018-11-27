package com.yeepay.g3.core.laike.utils;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.HttpUtils;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.aop.RemoteFacadeLogHandler;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.apache.commons.lang.StringUtils;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Description: 远程接口初始化类
 * Author: jiawen.huang
 * Date: 16/9/19
 * Time: 15:24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class RemoteFacadeFactory {

	private static final Logger logger = LoggerFactory.getLogger(RemoteFacadeFactory.class);

	/**
	 * 获取和代理facade服务
	 *
	 * @param targetClass  外部系统class
	 * @param externalName 外部系统名称
	 * @param <T>
	 * @return
	 */
	public static <T> T getService(Class<T> targetClass, ExternalSystem externalName) {
		Object target = RemoteServiceFactory.getService(targetClass);
		RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, externalName.getKey());
		return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target
				.getClass().getInterfaces(), logHandler);
	}

	/**
	 * 获取三代发布的httpinvoke服务
	 *
	 * @param targetClass
	 * @param externalName
	 * @param <T>
	 * @return
	 */
	public static <T> T getHttpInvokeService(Class<T> targetClass, ExternalSystem externalName) {
		Object target = RemoteServiceFactory.getService(RemotingProtocol.HTTPINVOKER, targetClass);
		RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, externalName.getKey());
		return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target
				.getClass().getInterfaces(), logHandler);
	}

	/**
	 * 获取和代理非三代发布的hessian服务
	 *
	 * @param targetClass
	 * @param externalName
	 * @param <T>
	 * @return
	 * @throws MalformedURLException
	 */
	public static <T> T getServiceBy2G(Class<T> targetClass, ExternalSystem externalName) {
		HessianProxyFactory factory = new HessianProxyFactory();
		String serviceValue = externalName.getValue();
		String serviceUrl = (String) ConfigUtils.getRemoteService(ConfigEnum.valueOf(serviceValue));
		try {
			Object target = factory.create(targetClass, serviceUrl);
			RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, externalName.getKey());
			return (T) Proxy.newProxyInstance(logHandler.getClass().getClassLoader(), target.getClass().getInterfaces(), logHandler);
		} catch (MalformedURLException e) {
			throw new LaikeSysException(ErrorCode.HESSIAN_CONNECT_EXCEPTION, e);
		}
	}

	/**
	 * 通过http调用的服务
	 *
	 * @param externalName
	 * @param querys
	 * @param headers
	 * @param bodys
	 * @return
	 */
	public static String getHttpPostService(ExternalSystem externalName, Map<String, String> querys,
											Map<String, String> headers, Map<String, String> bodys) {
		long startTime = System.currentTimeMillis();
		try {
			logger.info("[remote_sys] - [入参] - [" + externalName.getKey() + "] - [ getHttpPostService ] - "
					+ querys + headers + bodys);
			if (null == headers) {
				headers = Maps.newHashMap();
			}
			if (null == bodys) {
				bodys = Maps.newHashMap();
			}
			CheckUtils.notEmpty(querys, "querys");//IllegalArgumentException
			String URL = (String) ConfigUtils.getSysConfigParam(ConfigEnum.valueOf(externalName.getValue()));
			String response = HttpUtils.post(URL, "", headers, querys, bodys);
			logger.info("[remote_sys] - [返回] - [" + externalName.getKey() + "] - [ getHttpPostService ] - "
					+ response);
			return response;
		} catch (Throwable e) {
			logger.warn("[remote_sys] - [异常] - [" + externalName.getKey() + "] - [ getHttpPostService ]", e);
			throw new LaikeSysException(ErrorCode.SYSTEM_EXCEPTION, e);
		} finally {
			long time = (System.currentTimeMillis() - startTime);
			logger.info("[remote_sys] - [时间] - [" + externalName.getKey() + "] - [ getHttpPostService ] - 耗时[" + time
					+ "]毫秒");
		}
	}

	/**
	 * 获取httpinvoke服务
	 *
	 * @param clazz
	 * @param externalName
	 * @param <T>
	 * @return
	 */
	public static <T> T getHttpInvokeService(Class<T> clazz, ExternalSystem externalName, boolean flag) {
		String serviceValue = externalName.getValue();
		String serviceUrl = (String) ConfigUtils.getRemoteService(ConfigEnum.valueOf(serviceValue));
		if (flag && !StringUtils.endsWith(serviceUrl, clazz.getSimpleName())) {
			if (!StringUtils.endsWith(serviceUrl, "/")) {
				serviceUrl += "/";
			}
			serviceUrl += clazz.getSimpleName();
		}
		HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
		bean.setServiceUrl(serviceUrl);
		bean.setServiceInterface(clazz);
		bean.afterPropertiesSet();
		Object target = bean.getObject();
		RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, externalName.getKey());
		return (T) Proxy.newProxyInstance(logHandler.getClass().getClassLoader(), target.getClass().getInterfaces(), logHandler);
	}
}
