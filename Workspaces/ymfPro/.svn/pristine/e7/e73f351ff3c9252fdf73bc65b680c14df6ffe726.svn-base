package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 展业开户状态表
 * Author: jiawen.huang
 * Date: 17/06/19
 * Time: 17:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum LOLOpenStatus {

	INIT("INIT", "初始化"),
	SUBMIT("SUBMIT", "基本信息已提交"),
	AUDITING("AUDITING", "入网系统受理成功"),
    OCR_AUDIT("OCR_AUDIT", "人证不一"),//照片审核中
    RETURN("RETURN", "退回"),
	SUCCESS("SUCCESS", "开户成功");

	private static final Map<String, LOLOpenStatus> VALUE_MAP = new HashMap<String, LOLOpenStatus>();

	static {
		for (LOLOpenStatus item : LOLOpenStatus.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;

	LOLOpenStatus(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static LOLOpenStatus parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, LOLOpenStatus> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
