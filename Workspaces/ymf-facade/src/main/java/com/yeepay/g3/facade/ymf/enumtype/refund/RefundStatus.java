package com.yeepay.g3.facade.ymf.enumtype.refund;

/**
 * Title: 退款状态
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/26.
 */
public enum RefundStatus {

    SUCCESS("退款成功"),

    CANCEL("已取消"),

    PROCESS("退款中"),

    TIMEOUT("退款超时"),

    INIT("未发起"),

    FAIL("退款失败");


    String displayName;

    RefundStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
