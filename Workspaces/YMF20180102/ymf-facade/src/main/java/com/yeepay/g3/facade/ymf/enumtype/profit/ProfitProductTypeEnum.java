/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.ymf.enumtype.profit;

import java.util.HashMap;
import java.util.Map;

/**
 * 毛利产品类型
 * @author：xiaobin.liu
 */
public enum ProfitProductTypeEnum {

	SKB_PROFIT("收款宝"),
	RJT_PROFIT("日结通"),
	E_WALLET_PROFIT("易钱包"),
	LAKER_PROFIT("来客"),
	ALL("全部"),
	;

	private ProfitProductTypeEnum(String displayName) {
		this.displayName = displayName;
	}

	private String displayName;

	// 描述信息
	public String getDisplayName() {
		return this.displayName;
	}

	private static final Map<String, ProfitProductTypeEnum> valuesMap = new HashMap<String, ProfitProductTypeEnum>();

	static {
		for (ProfitProductTypeEnum e: values()) {
			valuesMap.put(e.name(), e);
		}
	}

	/**
	 * 字符串转换为Enum
	 * @param name	枚举字符串
	 */
	public static ProfitProductTypeEnum safeParse(String name) {
		return valuesMap.get(name);
	}

}
