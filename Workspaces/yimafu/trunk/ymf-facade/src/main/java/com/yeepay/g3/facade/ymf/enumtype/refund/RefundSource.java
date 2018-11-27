package com.yeepay.g3.facade.ymf.enumtype.refund;

/**
 * 退款发起方
 * Created by aoick on 2016/8/28.
 */
public enum RefundSource {

    MERCHANT_BOSS("商户后台"),

    YMF_BOSS("易码付运营");

    private String displayName;

    RefundSource(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
