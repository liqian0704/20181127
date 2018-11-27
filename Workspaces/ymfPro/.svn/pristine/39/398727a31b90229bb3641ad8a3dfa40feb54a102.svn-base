package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 业务类型枚举
 * Author: wei.li
 * Date: 17/5/23
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum BizTypeEnum {

    OAUTH("OAUTH", "身份鉴权"),
	OPEN_ACCOUNT("OPEN_ACCOUNT", "开户"),
    DEVICE_APPLY("DEVICE_APPLY", "硬件申请"),
    CREDIT_CARD_APPLY("CREDIT_CARD_APPLY", "信用卡申请");


    private static final Map<String, BizTypeEnum> VALUE_MAP = new HashMap<String, BizTypeEnum>();

    private String value;
    private String displayName;

    static {
        for (BizTypeEnum item : BizTypeEnum.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }

    BizTypeEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public static BizTypeEnum parse(String value) {
        return VALUE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<String, BizTypeEnum> getValueMap() {
        return VALUE_MAP;
    }
}
