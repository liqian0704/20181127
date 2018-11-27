package com.yeepay.g3.core.ymf.hessian;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * 对HessianProxyFactoryBean的扩展，增加了超时设置参数
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright (c)2009
 * </p>
 * <p>
 * Company: YeePay
 * </p>
 * <p>
 * @author junning.li
 * </p>
 */
public class HessianProxyFactoryBeanExt extends HessianProxyFactoryBean {
	private HessianProxyFactoryExt proxyFactoryExt;
	/**
	 * 读取超时
	 */
	private int readTimeOut;
	/**
	 * 连接超时
	 */
	private int connectTimeOut;

	public int getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	public int getConnectTimeOut() {
		return connectTimeOut;
	}

	public void setConnectTimeOut(int connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}

	public void afterPropertiesSet() {
		try {
			proxyFactoryExt = new HessianProxyFactoryExt();
			logger.info("url:::"+this.getServiceUrl());
			proxyFactoryExt.setReadTimeout(readTimeOut);
			proxyFactoryExt.setConnectTimeOut(connectTimeOut);
			setProxyFactory(proxyFactoryExt);
			super.afterPropertiesSet();
		} catch (Throwable e) {
			logger.warn("initialize HessianProxyFactoryBeanExt fail : " + e.getMessage());
		}
	}
}
