package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 推送方式
 * Author: jiawen.huang
 * Date: 16/11/30
 * Time: 20:24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum PushType {

	BROADCAST("BROADCAST", "广播"),

	TO_CUSTOMER("TO_CUSTOMER", "定点商遍推送"),

	T0_TAG("T0_TAG", "标签推送");

	private static final Map<String, PushType> VALUE_MAP = new HashMap();

	static {
		for (PushType item : PushType.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;

	PushType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static PushType parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, PushType> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
