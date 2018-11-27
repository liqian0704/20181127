package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * 业务类型
 * Created by aoick on 2016/8/28.
 */
public enum BusinessType {

    ORDER_PAY("订单支付"),
    STANDARD("标准支付"),
    MANUAL_ORDER("手工订单支付"),
    GRATUITY_PAY("打赏版支付");


    private String displayName;

    BusinessType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
