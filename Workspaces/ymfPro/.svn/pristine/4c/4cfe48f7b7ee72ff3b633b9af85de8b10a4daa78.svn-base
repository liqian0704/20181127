package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 二元枚举
 * Author: jiawen.HUANG
 * Date: 17/6/26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum BoolEnum {

	TRUE("TRUE", "1"),
	FALSE("FALSE", "0");


	private static final Map<String, BoolEnum> VALUE_MAP = new HashMap<String, BoolEnum>();

	private String value;
	private String displayName;

	static {
		for (BoolEnum item : BoolEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	BoolEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static BoolEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, BoolEnum> getValueMap() {
		return VALUE_MAP;
	}
}
