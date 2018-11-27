package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 消息类型
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 17:04
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum MsgTypeEnum {

	PAY("PAY", "支付消息"),
	SYS("SYS", "系统消息");

	private static final Map<String, MsgTypeEnum> VALUE_MAP = new HashMap<String, MsgTypeEnum>();

	private String value;
	private String displayName;

	static {
		for (MsgTypeEnum item : MsgTypeEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	MsgTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static MsgTypeEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, MsgTypeEnum> getValueMap() {
		return VALUE_MAP;
	}
}
