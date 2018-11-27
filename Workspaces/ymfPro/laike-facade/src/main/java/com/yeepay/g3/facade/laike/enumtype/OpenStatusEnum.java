package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 开户状态表
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 18:19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum OpenStatusEnum {

	INIT("INIT", "初始化", 1),//入网邀请方式检查
	INFO_SAVE("INFO_SAVE", "资质信息已保存", 2),//不能再变更入网邀请方式
	INFO_SUBMIT("INFO_SUBMIT", "基本信息已提交通过", 3.1),//不能在变更资质信息
	BIZ_SUBMIT("BIZ_SUBMIT", "资质信息已提交通过", 3.2),//不能在变更资质信息
	SETTLE_SUBMIT("SETTLE_SUBMIT", "结算信息提通过", 3.3),//不能变更结算信息
	IMG_SUBMIT("IMG_SUBMIT", "附件已提交通过", 3.4),
	AUDITING("AUDITING", "审核中", 5),
	PAY_SUCCESS("PAY_SUCCESS", "结算开通中", 6),
	RETURN("RETURN", "退回", 4),
	REJECT("REJECT", "拒绝", 7),
	SUCCESS("SUCCESS", "开户成功", 7);

	private static final Map<String, OpenStatusEnum> VALUE_MAP = new HashMap<String, OpenStatusEnum>();

	static {
		for (OpenStatusEnum item : OpenStatusEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;
	private double step;

	OpenStatusEnum(String value, String displayName, double step) {
		this.value = value;
		this.displayName = displayName;
		this.step = step;
	}

	public static OpenStatusEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, OpenStatusEnum> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public double getStep() {
		return step;
	}
}
