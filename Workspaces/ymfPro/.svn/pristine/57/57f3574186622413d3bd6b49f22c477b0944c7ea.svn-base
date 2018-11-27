package com.yeepay.g3.core.laike.entity;

import com.yeepay.g3.facade.laike.enumtype.SmsTypeEnum;

import java.util.Date;

/**
 * Description: 验证码
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 13:23
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class SmsCodeEntity extends PersistenceEntity {

	/**
	 * 手机号
	 */
	private String phoneNo;

	/**
	 * 验证码
	 */
	private String smsCode;

	/**
	 * sms类型
	 */
	private SmsTypeEnum smsType;

	/**
	 * 是否还能做验证 1是0否
	 */
	private Boolean available;

	/**
	 * 有效期
	 */
	private Date effectTime;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public SmsTypeEnum getSmsType() {
		return smsType;
	}

	public void setSmsType(SmsTypeEnum smsType) {
		this.smsType = smsType;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}
}
