package com.yeepay.g3.core.ymf.hessian;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 对HessianProxyFactory的扩展，增加了connectTimeOut参数
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
public class HessianProxyFactoryExt extends HessianProxyFactory {
	/**
	 * 连接超时参数
	 */
	private int connectTimeOut = 0;

	public int getConnectTimeOut() {
		return connectTimeOut;
	}

	public void setConnectTimeOut(int connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}

//	public URLConnection openConnection(URL url) throws IOException {
////		URLConnection conn = super.openConnection(url);
////		conn.setDoOutput(true);
//		if (connectTimeOut > 0) {
//			conn.setConnectTimeout(connectTimeOut);
//		}
//		return conn;
//	}
}
