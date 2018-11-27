/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.ymf.enumtype.profit;

import java.util.HashMap;
import java.util.Map;

/**
 * 商户类型类型
 * @author：xiaobin.liu
 */
public enum CustomerTypeEnum {

	MT("蜜堂"),
	STOCK("存量"),
	ALL("全部"),
	;

	private CustomerTypeEnum(String displayName) {
		this.displayName = displayName;
	}

	private String displayName;

	// 描述信息
	public String getDisplayName() {
		return this.displayName;
	}

	private static final Map<String, CustomerTypeEnum> valuesMap = new HashMap<String, CustomerTypeEnum>();

	static {
		for (CustomerTypeEnum e: values()) {
			valuesMap.put(e.name(), e);
		}
	}

	/**
	 * 字符串转换为Enum
	 * @param name	枚举字符串
	 */
	public static CustomerTypeEnum safeParse(String name) {
		return valuesMap.get(name);
	}

}
