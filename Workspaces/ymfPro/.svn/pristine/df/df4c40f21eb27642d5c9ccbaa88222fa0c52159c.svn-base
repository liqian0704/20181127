package com.yeepay.g3.facade.laike.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: facade返回结果状态
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 17:12
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum ResponseStatus {

	SUCCESS("SUCCESS", "成功"),
	FAILURE("FAILURE", "失败");

	private static final Map<String, ResponseStatus> VALUE_MAP = new HashMap<String, ResponseStatus>();

	private String value;
	private String displayName;

	static {
		for (ResponseStatus item : ResponseStatus.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	ResponseStatus(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static ResponseStatus parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, ResponseStatus> getValueMap() {
		return VALUE_MAP;
	}
}
