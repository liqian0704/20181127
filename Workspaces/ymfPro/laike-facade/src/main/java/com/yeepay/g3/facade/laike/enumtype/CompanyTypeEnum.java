package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 公司类型
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 18:19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum CompanyTypeEnum {

	ENTERPRISE("ENTERPRISE", "企业"),
	ENTER_UNION("ENTER_UNION", "企业三证合一"),
	INDIVIDUAL("INDIVIDUAL", "个体户"),
	MICRO("MICRO", "小微企业");

	private static final Map<String, CompanyTypeEnum> VALUE_MAP = new HashMap<String, CompanyTypeEnum>();

	private String value;
	private String displayName;

	static {
		for (CompanyTypeEnum item : CompanyTypeEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	CompanyTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static CompanyTypeEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, CompanyTypeEnum> getValueMap() {
		return VALUE_MAP;
	}
}
