package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.enumtype.OpenStatusEnum;
import com.yeepay.g3.facade.laike.enumtype.RoleEnum;
import com.yeepay.g3.facade.laike.enumtype.UserStatus;

/**
 * Description: 登陆响应
 * Author: jiawen.huang
 * Date: 16/11/14
 * Time: 15:35
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class LoginResponse extends BaseResponse {

	/**
	 * 手机号
	 */
	protected String phoneNo;

	/**
	 * 三代会员号
	 */
	private String memberNo;

	/**
	 * 角色
	 */
	private RoleEnum role;

	/**
	 * 用户状态
	 */
	private UserStatus userStatus;

	/**
	 * 账户开户状态
	 */
	private OpenStatusEnum openStatus;

	/**
	 * 账户号
	 */
	private String accountNo;

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

    /**
     * 首页广告url
     */
	private String bannerUrl;

	/**
	 * 硬件超市url
	 */
	private String deviceMallUrl;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 用户指导url
     */
    private String userGuideUrl;

    /**
     * 链接前缀
     */
    private String headUrl;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public OpenStatusEnum getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(OpenStatusEnum openStatus) {
		this.openStatus = openStatus;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getDeviceMallUrl() {
		return deviceMallUrl;
	}

	public void setDeviceMallUrl(String deviceMallUrl) {
		this.deviceMallUrl = deviceMallUrl;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getUserGuideUrl() {
        return userGuideUrl;
    }

    public void setUserGuideUrl(String userGuideUrl) {
        this.userGuideUrl = userGuideUrl;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
