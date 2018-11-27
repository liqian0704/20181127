package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: app平台类型(消息发送时使用，版本管理请用VersionPlatform)
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 17:14
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum PlatformEnum {

	ALL("ALL", "全平台"),
	ANDROID("ANDROID", "安卓"),
	IOS("IOS", "苹果");

	private static final Map<String, PlatformEnum> VALUE_MAP = new HashMap<String, PlatformEnum>();

	private String value;
	private String displayName;

	static {
		for (PlatformEnum item : PlatformEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	PlatformEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static PlatformEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, PlatformEnum> getValueMap() {
		return VALUE_MAP;
	}
}
