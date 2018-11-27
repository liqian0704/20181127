package com.yeepay.g3.facade.ymf.enumtype.common;

/**
 * Created by dongxulu on 17/2/20.
 * 企业类型
 */
public enum MerchantType {

    INDIVIDUAL("自然人"),
    INDIVIDUALUSINESS("个体工商户"),
    NTERPRISE("企业");

    private String displayName;

    MerchantType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
