package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * Description: 出款方式
 * Author: jiawen.huang
 * Date: 17/4/25
 * Time: 15:49
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum SettleType {

	S0("S0", "秒到出款"),

	T1("T1", "T+1结算");

	private String value;

	private String displayName;

	SettleType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getValue() {
		return value;
	}
}
