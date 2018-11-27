package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 基础状态枚举值
 * Author: jiawen.huang
 * Date: 17/4/18
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum ProductStatusEnum {

    INIT("INIT", "未开通"),
    OPEN("OPEN", "开通"),
	CLOSED("CLOSED", "关闭"),
	FORBIDDEN("FORBIDDEN", "禁用");

	private static final Map<String, ProductStatusEnum> VALUE_MAP = new HashMap<String, ProductStatusEnum>();

	static {
		for (ProductStatusEnum item : ProductStatusEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;

	ProductStatusEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static ProductStatusEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, ProductStatusEnum> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
