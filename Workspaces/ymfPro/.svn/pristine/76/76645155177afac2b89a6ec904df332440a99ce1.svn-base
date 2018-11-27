package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 版本平台
 * Author: jiawen.huang
 * Date: 17/1/5
 * Time: 15:10
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum VersionPlatform {

	ANDROID("ANDROID", "安卓"),
	IOS("IOS", "苹果");

	private static final Map<String, VersionPlatform> VALUE_MAP = new HashMap<String, VersionPlatform>();

	private String value;
	private String displayName;

	static {
		for (VersionPlatform item : VersionPlatform.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	VersionPlatform(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static VersionPlatform parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, VersionPlatform> getValueMap() {
		return VALUE_MAP;
	}
}
