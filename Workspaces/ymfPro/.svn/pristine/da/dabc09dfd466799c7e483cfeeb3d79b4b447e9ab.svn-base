package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 支付产品枚举类
 * Author: wei.li
 * Date: 17/8/2
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum PayProductEnum {

    OFFICIAL_ACCOUNT_PAY("OFFICIAL_ACCOUNT_PAY", "微信支付"),
    WALLET_PAY_ZFB("WALLET_PAY_ZFB", "支付宝"),
    UPOP_SCAN_PAY("UPOP_SCAN_PAY", "银联云闪付"),
    ONE_KEY_PAY("ONE_KEY_PAY", "银行卡快捷支付"),
    MERCHANT_SCAN_PAY("MERCHANT_SCAN_PAY", "商家扫码"),
    S0_SETTLE("S0_SETTLE", "逐笔结算");

    private static final Map<String, PayProductEnum> VALUE_MAP = new HashMap<String, PayProductEnum>();

    private String value;
    private String displayName;

    static {
        for (PayProductEnum item : PayProductEnum.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }

    PayProductEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public static PayProductEnum parse(String value) {
        return VALUE_MAP.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<String, PayProductEnum> getValueMap() {
        return VALUE_MAP;
    }
}
