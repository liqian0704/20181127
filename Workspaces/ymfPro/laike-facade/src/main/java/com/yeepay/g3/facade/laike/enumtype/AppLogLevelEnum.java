package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: wei.li
 * Date: 17/2/10
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum AppLogLevelEnum {

    INFO("INFO","统计信息"),
    WARN("WARN", "警告信息"),
    ERROR("ERROR", "错误信息");

    private static final Map<String, AppLogLevelEnum> VALUE_MAP = new HashMap<String, AppLogLevelEnum>();

    private String value;
    private String displayName;

    static {
        for (AppLogLevelEnum item : AppLogLevelEnum.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }

    AppLogLevelEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public static AppLogLevelEnum parse(String value) {
        return VALUE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<String, AppLogLevelEnum> getValueMap() {
        return VALUE_MAP;
    }
}
