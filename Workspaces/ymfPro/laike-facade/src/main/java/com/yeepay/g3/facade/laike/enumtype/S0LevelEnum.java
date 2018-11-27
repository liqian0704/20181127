package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 用户秒到资格等级
 * Author: jiawen.huang
 * Date: 17/4/19
 * Time: 10:51
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum S0LevelEnum {

	LEVEL0("LEVEL0", "初始化", 0),
	LEVEL1("LEVEL1", "结算银行达标", 1),
	LEVEL2("LEVEL2", "T1结算达标", 2),
	LEVEL3("LEVEL3", "已绑卡", 3),
	S0_STANDARD("S0_STANDARD", "已开通", 4),
	S0_CLOSED("S0_CLOSED", "已关闭", 5);

	private static final Map<String, S0LevelEnum> VALUE_MAP = new HashMap<String, S0LevelEnum>();

	static {
		for (S0LevelEnum item : S0LevelEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private Integer step;//开通顺序
	private String displayName;

	S0LevelEnum(String value, String displayName, Integer step) {
		this.value = value;
		this.displayName = displayName;
		this.step = step;
	}

	public static S0LevelEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, S0LevelEnum> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Integer getStep() {
		return step;
	}

}
