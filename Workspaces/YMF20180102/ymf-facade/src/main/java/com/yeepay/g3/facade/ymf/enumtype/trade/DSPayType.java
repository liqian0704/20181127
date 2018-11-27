package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * Title: 大算支付产品码
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/2/23.
 */
public enum DSPayType {

    ZF_WY_B2B("网银B2B"),
    ZF_WY_B2C("网银B2C"),
    ZF_YJZF_JJK("借记卡"),
    ZF_YJZF_DJK("贷记卡"),
    ZF_YHSM_WX("微信"),
    ZF_YHSM_ZFB("支付宝"),
    ZF_SJSM_WX("微信"),
    ZF_SJSM_ZFB("支付宝"),
    ZF_GZH_WX("微信"),
    ZF_FQJH_3("分期3期"),
    ZF_FQJH_6("分期6期"),
    ZF_FQJH_9("分期9期"),
    ZF_FQJH_12("分期12期"),
    ZF_FQJH_24("分期24期"),
    ZF_FQJH_BT("白条"),
    ZF_QB_ZFB("支付宝");

    private String displayName;

    DSPayType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
