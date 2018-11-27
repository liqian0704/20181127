package com.yeepay.g3.facade.ymf.enumtype.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 类型
 * Created by dongxulu on 16/12/16.
 */
public enum AppType {

    NORMAL("标准版"),
    AGENT("来客代理商版"),
    ALLIANCE("来客联盟版"),
    INDUSTRY("行业版");

    private static final Map<String, AppType> valuesMap = new HashMap<String, AppType>();

    static {
        for (AppType e: values()) {
            valuesMap.put(e.name(), e);
        }
    }

    private String displayName;

    AppType(String displayName) {
        this.displayName = displayName;
    }

    public static AppType safeOf(String name) {
        return valuesMap.get(name);
    }

    public String getDisplayName() {
        return displayName;
    }

}
