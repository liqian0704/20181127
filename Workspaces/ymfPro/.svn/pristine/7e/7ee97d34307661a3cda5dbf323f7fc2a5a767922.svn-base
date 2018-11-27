package com.yeepay.g3.facade.laike.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Description: facade返回对象基类
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 17:08
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	protected String errCode;

	/**
	 * 错误信息
	 */
	protected String errMsg;

	/**
	 * 返回状态
	 */
	private ResponseStatus status = ResponseStatus.SUCCESS;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
