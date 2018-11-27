package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * 结算类型 目前只支持T1 S0
 * Created by dongxulu on 17/4/24.
 */
public enum BalanceType {
    T1("T1结算"),
    S0("秒到");


    private String displayName;

    BalanceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }





}
