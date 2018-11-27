package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

/**
 * Description: 注销请求
 * Author: jiawen.huang
 * Date: 16/11/14
 * Time: 19:21
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class LogoutReuqest extends BaseRequest {

	/**
	 * oauth token
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("LogoutReuqest{");
		sb.append("memberNo='").append(memberNo).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("location='").append(HiddenCodeUtil.hiddenMobile(location)).append('\'');
		sb.append("token='").append(token).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
