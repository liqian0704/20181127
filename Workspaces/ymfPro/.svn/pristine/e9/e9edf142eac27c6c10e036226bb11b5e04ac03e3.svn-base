package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 用户状态
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 15:42
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum UserStatus {

	INIT("INIT", "初始化"),
	SEND("SEND", "短信已发送"),
	REGISTER("REGISTER", "已注册");

	private static final Map<String, UserStatus> VALUE_MAP = new HashMap<String, UserStatus>();

	private String value;
	private String displayName;

	static {
		for (UserStatus item : UserStatus.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	UserStatus(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static UserStatus parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, UserStatus> getValueMap() {
		return VALUE_MAP;
	}
}
