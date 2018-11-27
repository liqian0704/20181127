package com.yeepay.g3.facade.ymf.enumtype.term;

/**
 * Title: 终端状态
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
public enum TermStatus {

    BIND("绑定"),

    UNBIND("空闲");

    public String displayName;

    TermStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
