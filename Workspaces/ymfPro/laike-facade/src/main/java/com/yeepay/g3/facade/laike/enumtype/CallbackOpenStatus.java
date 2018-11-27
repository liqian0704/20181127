package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 开会回调状态
 * Author: jiawen.huang
 * Date: 16/12/15
 * Time: 17:39
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum CallbackOpenStatus {

	PASS("PASS", "通过"),
	REJECT("REJECT", "拒绝");

	private static final Map<String, CallbackOpenStatus> VALUE_MAP = new HashMap<String, CallbackOpenStatus>();

	private String value;
	private String displayName;

	static {
		for (CallbackOpenStatus item : CallbackOpenStatus.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	CallbackOpenStatus(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static CallbackOpenStatus parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, CallbackOpenStatus> getValueMap() {
		return VALUE_MAP;
	}
}
