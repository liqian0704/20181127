package com.yeepay.g3.facade.laike.enumtype;


import com.yeepay.g3.facade.laike.exception.ErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 安全控制类型枚举
 * Author: jiawen.huang
 * Date: 16/9/21
 * Time: 16:02
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum ControlTypeEnum {

	LOGIN_CONTROL("LOGIN_CONTROL", "登陆错误次数控制", 5, 300l, ErrorCode.LOGIN_LIMIT),
	RESET_PWD_CONTROL("RESET_PWD_CONTROL", "修改密码错误次数控制", 5, 300l, ErrorCode.LOGIN_PWD_LIMIT),
	SMS_SEND_CONTROL("SMS_SEND_CONTROL", "验证码发送次数控制", 5, 300l, ErrorCode.SMS_SEND_LIMIT),
	VERIFY_SMS_CONTROL("VERIFY_SMS_CONTROL", "验证码验证错误次数控制", 3, 300l, ErrorCode.VERIFY_SMS_LIMIT),
	FIND_PWD_CONTROL("FIND_PWD_CONTROL", "找回密码次数控制", 3, 300l, ErrorCode.FIND_PWD_LIMIT);

	private static final Map<String, ControlTypeEnum> VALUE_MAP = new HashMap<String, ControlTypeEnum>();

	static {
		for (ControlTypeEnum item : ControlTypeEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;
	private Integer times;
	private Long interval;
	private String errorCode;

	ControlTypeEnum(String value, String displayName, Integer times, Long interval, String errorCode) {
		this.value = value;
		this.displayName = displayName;
		this.times = times;
		this.interval = interval;
		this.errorCode = errorCode;
	}

	public static ControlTypeEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, ControlTypeEnum> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Integer getTimes() {
		return times;
	}

	public Long getInterval() {
		return interval;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
