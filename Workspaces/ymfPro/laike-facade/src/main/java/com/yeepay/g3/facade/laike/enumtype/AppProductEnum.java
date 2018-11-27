package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: APP服务枚举
 * Author: jiawen.huang
 * Date: 17/4/18
 * Time: 17:18
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum AppProductEnum {

	BOX_PAY("BOX_PAY", "盒子支付"),
    SCAN_PAY("SCAN_PAY", "二维码收款"),
	S0_SETTLE("S0_SETTLE", "逐笔结算");

	private static final Map<String, AppProductEnum> VALUE_MAP = new HashMap<String, AppProductEnum>();

	static {
		for (AppProductEnum item : AppProductEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;

	AppProductEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;

    }

	public static AppProductEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, AppProductEnum> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
