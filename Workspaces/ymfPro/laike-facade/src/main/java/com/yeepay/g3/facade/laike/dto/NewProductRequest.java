package com.yeepay.g3.facade.laike.dto;

import java.io.Serializable;

/**
 * Description: 入网产品请求参数
 * Author: jiawen.huang
 * Date: 17/3/15
 * Time: 17:26
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class NewProductRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 提交入网的请求号
	 */
	private String requestNo;

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("BaseRequest{");
		sb.append("requestNo='").append(requestNo).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
