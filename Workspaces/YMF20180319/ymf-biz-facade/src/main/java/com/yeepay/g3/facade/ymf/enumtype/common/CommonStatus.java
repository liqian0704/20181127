package com.yeepay.g3.facade.ymf.enumtype.common;

/**
 * Title: 普通状态类
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
public enum CommonStatus {

    ACTIVE("可用"),

    INACTIVE("不可用");

    public String displayName;

    CommonStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
