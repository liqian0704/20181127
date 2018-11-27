package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 验证结果枚举值
 * Author: wei.li
 * Date: 17/3/21
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum VerifyResultEnum {
    PASS("PASS", "通过"),
    NOPASS("NOPASS", "不通过");

    private static final Map<String, VerifyResultEnum> VALUE_MAP = new HashMap<String, VerifyResultEnum>();

    private String value;
    private String displayName;

    static {
        for (VerifyResultEnum item : VerifyResultEnum.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }

    VerifyResultEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public static VerifyResultEnum parse(String value) {
        return VALUE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<String, VerifyResultEnum> getValueMap() {
        return VALUE_MAP;
    }
}
