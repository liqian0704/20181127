package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * Title: 订单状态
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/9/5.
 */
public enum OrderStatus {

    INIT("未支付"),
    PROCESS("支付中"),
    SUCCESS("订单完成"),
    FAIL("订单失败");

    private String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
