package com.yeepay.g3.facade.ymf.enumtype.common;

/**
 * 二维码所有者
 *
 * Created by dongxulu on 16/10/30.
 */
public enum QROwner {

    SALES("销售"),
    AGENT("代理商"),
    CUSTOMER("商户");

    private String displayName;

    QROwner(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * 获取卡类型对应的枚举
     * @param qrOwner
     * @return  返回枚举,未找到返回null
     */
    public static QROwner getQROwner(String qrOwner) {
        if ("SALES".equals(qrOwner)) {
            return SALES ;
        } else if ("AGENT".equals(qrOwner)) {
            return AGENT ;
        } else if ("CUSTOMER".equals(qrOwner)) {
            return CUSTOMER ;
        }
        return null ;
    }
}
