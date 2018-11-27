package com.yeepay.g3.core.laike.enumtype;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Description: 业务编号前缀
 * Author: jiawen.huang,zhaoyu.cui
 * Date: 15/3/22
 * Time: 12:15
 * Version: 1.0
 * Copyright © 2015 YeePay.com All rights reserved.
 */
public enum BizPrefixEnum {

	PY("PY", "订单前缀"),
	US("US", "用户编号前缀"),
	AC("AC", "账户编号前缀"),
	MS("MS", "消息编号前缀"),
	AP("AP", "版本编号前缀"),
	S0("S0", "S0申请前缀"),
    CL("CL", "贷款请求前缀"),
    RO("RO", "角色前缀"),
    OCR("OCR", "OCR请求前缀");

	private static final Map<String, BizPrefixEnum> VALUE_MAP = Maps.newHashMap();

	private String value;
	private String displayName;

	static {
		for (BizPrefixEnum item : BizPrefixEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	BizPrefixEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static BizPrefixEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, BizPrefixEnum> getValueMap() {
		return VALUE_MAP;
	}
}
