package com.yeepay.g3.facade.ymf.enumtype;

/**
 * 商户标识，标识接入打算后的商户
 */
public enum CustomerFlag {
    /**
     * 非存量商户，迁移订单处理器之后的
     */
    OPR_AFTER("非存量商户");

    public String displayName;

    CustomerFlag(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
