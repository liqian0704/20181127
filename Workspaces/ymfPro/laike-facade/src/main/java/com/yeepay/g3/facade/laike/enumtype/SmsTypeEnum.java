package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 短信类型
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 13:54
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum SmsTypeEnum {

	REGISTER("REGISTER", "注册"),
	FIND_LOGIN_PWD("FIND_LOGIN_PWD", "找回登陆密码");

	private static final Map<String, SmsTypeEnum> VALUE_MAP = new HashMap<String, SmsTypeEnum>();

	private String value;
	private String displayName;

	static {
		for (SmsTypeEnum item : SmsTypeEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	SmsTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static SmsTypeEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, SmsTypeEnum> getValueMap() {
		return VALUE_MAP;
	}
}
