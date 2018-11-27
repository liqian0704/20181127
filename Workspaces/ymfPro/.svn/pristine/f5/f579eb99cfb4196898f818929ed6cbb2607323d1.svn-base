package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 入网账户类型
 * Author: jiawen.huang
 * Date: 2017/6/15
 * Time: 15:42
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum AccountType {

	LK("LK", "来客"),
	LOL("LOL", "联盟");

	private static final Map<String, AccountType> VALUE_MAP = new HashMap<String, AccountType>();

	private String value;
	private String displayName;

	static {
		for (AccountType item : AccountType.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	AccountType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static AccountType parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, AccountType> getValueMap() {
		return VALUE_MAP;
	}
}
