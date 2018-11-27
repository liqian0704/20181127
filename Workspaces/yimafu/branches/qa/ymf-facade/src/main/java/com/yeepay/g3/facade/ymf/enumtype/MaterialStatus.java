package com.yeepay.g3.facade.ymf.enumtype;

/**
 * Created by yp-tc-m-2762 on 16/8/31.
 */
public enum MaterialStatus {
    ACTIVE("可用"),

    INACTIVE("不可用"),

    DELETE("已删除");


    public String displayName;

    MaterialStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
