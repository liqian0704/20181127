package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: app角色枚举类
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 15:33
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum RoleEnum {

	BOSS("BOSS", "老板"),
	WORKER("WORKER", "员工");

	private static final Map<String, RoleEnum> VALUE_MAP = new HashMap<String, RoleEnum>();

	private String value;
	private String displayName;

	static {
		for (RoleEnum item : RoleEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	RoleEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static RoleEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, RoleEnum> getValueMap() {
		return VALUE_MAP;
	}
}
