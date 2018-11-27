package com.yeepay.g3.facade.ymf.enumtype.refund;

/**
 * 退款发起方式
 * Created by aoick on 2016/8/28.
 */
public enum RefundType {

    REFUND_MANUAL("人工"),
    REFUND_AUTO("自动");

    String displayName;

    RefundType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
