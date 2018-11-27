package com.yeepay.g3.core.laike.entity;


import com.yeepay.g3.facade.laike.enumtype.ControlTypeEnum;

import java.util.Date;

/**
 * Description: 安全控制实体
 * Author: jiawen.huang
 * Date: 16/9/21
 * Time: 16:01
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class SecurityControlEntity extends PersistenceEntity {

	/**
	 * 手机号
	 */
	private String phoneNumber;

	/**
	 * 控制类型
	 */
	private ControlTypeEnum controlTypeEnum;

	/**
	 * 累计错误次数
	 */
	private Integer mistakeTimes;

	/**
	 * 开始累计错误日期时间
	 */
	private Date firstMistakeTime;

	/**
	 * 最后错误日期时间
	 */
	private Date lastMistakeTime;

	/**
	 * 冻结标识
	 */
	private Boolean freezed;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ControlTypeEnum getControlTypeEnum() {
		return controlTypeEnum;
	}

	public void setControlTypeEnum(ControlTypeEnum controlTypeEnum) {
		this.controlTypeEnum = controlTypeEnum;
	}

	public Integer getMistakeTimes() {
		return mistakeTimes;
	}

	public void setMistakeTimes(Integer mistakeTimes) {
		this.mistakeTimes = mistakeTimes;
	}

	public Date getFirstMistakeTime() {
		return firstMistakeTime;
	}

	public void setFirstMistakeTime(Date firstMistakeTime) {
		this.firstMistakeTime = firstMistakeTime;
	}

	public Date getLastMistakeTime() {
		return lastMistakeTime;
	}

	public void setLastMistakeTime(Date lastMistakeTime) {
		this.lastMistakeTime = lastMistakeTime;
	}

	public Boolean getFreezed() {
		return freezed;
	}

	public void setFreezed(Boolean freezed) {
		this.freezed = freezed;
	}
}
