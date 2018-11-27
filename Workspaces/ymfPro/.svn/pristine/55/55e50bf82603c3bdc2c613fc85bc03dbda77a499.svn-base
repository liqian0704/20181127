package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 入网邀请方式
 * Author: jiawen.huang
 * Date: 16/12/2
 * Time: 18:26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum InviteType {

	INVITECODE("INVITECODE", "邀请码"),
	DIRECT_SALE("DIRECT_SALE", "直销"),
	BIG_MERCHANT("BIG_MERCHANT", "大商户"),
	SELLER_DIRECT_SALE("SELLER_DIRECT_SALE", "销售直销"),
	SIGNEDPAPER("SIGNEDPAPER", "台签牌"),
	ALLIANCE("ALLIANCE", "联盟系统");

	private static final Map<String, InviteType> VALUE_MAP = new HashMap<String, InviteType>();

	private String value;
	private String displayName;

	static {
		for (InviteType item : InviteType.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	InviteType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static InviteType parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, InviteType> getValueMap() {
		return VALUE_MAP;
	}
}
