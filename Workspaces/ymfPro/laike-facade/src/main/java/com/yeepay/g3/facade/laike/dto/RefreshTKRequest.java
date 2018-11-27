package com.yeepay.g3.facade.laike.dto;

/**
 * Description: 刷新oauth 的tk
 * Author: jiawen.huang
 * Date: 17/1/11
 * Time: 11:22
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class RefreshTKRequest extends BaseRequest {

	String grantType;

	String refreshToken;

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
