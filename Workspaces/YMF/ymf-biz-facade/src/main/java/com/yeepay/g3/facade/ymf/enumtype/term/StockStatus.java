package com.yeepay.g3.facade.ymf.enumtype.term;

/**
 * Title: 库存状态
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
public enum StockStatus {

    IN("已入库"),

    OUT("已出库");

    public String displayName;

    StockStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
