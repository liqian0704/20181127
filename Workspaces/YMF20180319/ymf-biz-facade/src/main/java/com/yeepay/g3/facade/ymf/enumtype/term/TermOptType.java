package com.yeepay.g3.facade.ymf.enumtype.term;

/**
 * Title: 终端操作类型
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
public enum TermOptType {

    IN("入库"),

    OUT("出库");

    public String displayName;

    TermOptType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
