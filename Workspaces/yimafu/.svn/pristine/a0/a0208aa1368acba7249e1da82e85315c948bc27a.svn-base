package com.yeepay.g3.facade.ymf.enumtype.common;

/**
 * 银行卡类型
 * Created by aoick on 2016/8/28.
 */
public enum CardType {

    DEBIT("借记卡"),

    CREDIT("贷记卡"),
    
    CFT("财付通") ;//微信余额支付

    private String displayName;

    CardType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * 获取卡类型对应的枚举
     * @param cardType  卡类型值(字符串)
     * @return  返回枚举,未找到返回null
     */
    public static CardType getCardType(String cardType) {
        if ("DEBIT".equals(cardType)) {
            return DEBIT ;
        } else if ("CREDIT".equals(cardType)) {
            return CREDIT ;
        } else if ("CFT".equals(cardType)) {
            return CFT ;
        }
        return null ;
    }
}
