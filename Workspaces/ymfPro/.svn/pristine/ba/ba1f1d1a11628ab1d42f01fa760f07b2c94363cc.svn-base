package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 消息推送——推送状态
 * Author: jiawen.huang
 * Date: 16/1/29
 * Time: 16:39
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum PushStatus {

	NOT_SEND("NOT_SEND", "未发布"),

	SENDED("SENDED", "已发布"),

	DELETED("DELETED", "作废");

	private static final Map<String, PushStatus> VALUE_MAP = new HashMap();

	static {
		for (PushStatus item : PushStatus.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;

	PushStatus(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static PushStatus parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, PushStatus> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
