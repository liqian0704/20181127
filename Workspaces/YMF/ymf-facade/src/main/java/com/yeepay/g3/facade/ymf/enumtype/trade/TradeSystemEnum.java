package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * @Description: 下单系统
 * @Author: xiaobin.liu
 * @Date: 17/2/21
 * @Time: 下午3:05
 */
public enum TradeSystemEnum {
    CASHIER("统一收银台"),
    OPR("订单处理器");
    private String displayName;

    TradeSystemEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
