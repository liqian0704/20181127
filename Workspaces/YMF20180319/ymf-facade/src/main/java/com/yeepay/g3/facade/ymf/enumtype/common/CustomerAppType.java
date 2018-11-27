package com.yeepay.g3.facade.ymf.enumtype.common;

/**
 * Created by dongxulu on 16/11/8.
 */
public enum CustomerAppType {

    NORMAL("标准版"),

    INDUSTRY("行业版"),

    MANUAL("手输订单版"),

    GRATUITY("打赏版") ;//微信余额支付

    private String displayName;

    CustomerAppType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * 获取卡类型对应的枚举
     * @param CustomerAppType  卡类型值(字符串)
     * @return  返回枚举,未找到返回null
     */
    public static CustomerAppType getCustomerAppType(String CustomerAppType) {
        if ("NORMAL".equals(CustomerAppType)) {
            return NORMAL ;
        } else if ("INDUSTRY".equals(CustomerAppType)) {
            return INDUSTRY ;
        } else if ("GRATUITY".equals(CustomerAppType)) {
            return GRATUITY ;
        } else if ("MANUAL".equals(CustomerAppType)) {
            return MANUAL ;
        }
        return null ;
    }
}
