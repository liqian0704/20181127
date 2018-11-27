package com.yeepay.g3.facade.laike.dto;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/1/11
 * Time: 11:23
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class RefreshTKResponse extends BaseResponse {

	/**
	 * 三代会员号
	 */
	private String memberNo;

	/**
	 * 鉴权
	 */
	private String accessToken;

	/**
	 * 鉴权
	 */
	private String refreshToken;

	/**
	 * 有效期
	 */
	private String expiration;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
    }
}
