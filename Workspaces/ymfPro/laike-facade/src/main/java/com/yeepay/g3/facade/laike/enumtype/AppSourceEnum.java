package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: app标签-appKey
 * Author: jiawen.huang
 * Date: 2017/6/15
 * Time: 15:42
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum AppSourceEnum {

	//displayName：yop的appKey
	LIKER("LIKER", "laike"),
	ALLIANCE("ALLIANCE", "alliance-app");

	private static final Map<String, AppSourceEnum> VALUE_MAP = new HashMap<String, AppSourceEnum>();

	private String value;
	private String displayName;

	static {
		for (AppSourceEnum item : AppSourceEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	AppSourceEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static AppSourceEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, AppSourceEnum> getValueMap() {
		return VALUE_MAP;
	}
}
