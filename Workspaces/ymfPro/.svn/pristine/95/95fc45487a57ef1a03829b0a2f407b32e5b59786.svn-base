package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 通用操作类型
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 10:33
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum OperateTypeEnum {

	OPEN("OPEN", "开通"),
	CHANGE("CHANGE", "变更"),
	CLOSE("CLOSE", "关闭");

	private static final Map<String, OperateTypeEnum> VALUE_MAP = new HashMap<String, OperateTypeEnum>();

	private String value;
	private String displayName;

	static {
		for (OperateTypeEnum item : OperateTypeEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	OperateTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static OperateTypeEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, OperateTypeEnum> getValueMap() {
		return VALUE_MAP;
	}
}
