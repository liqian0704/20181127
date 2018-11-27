package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 系统环境
 * Author: jiawen.huang
 * Date: 2017/7/17
 * Time: 11:50
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum EnvironmentType {

	PRO("PRO", "生产"),
	NC("NC", "内测"),
	QA("QA", "QA");

	private static final Map<String, EnvironmentType> VALUE_MAP = new HashMap<String, EnvironmentType>();

	private String value;
	private String displayName;

	static {
		for (EnvironmentType item : EnvironmentType.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	EnvironmentType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static EnvironmentType parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, EnvironmentType> getValueMap() {
		return VALUE_MAP;
	}
}
