package com.yeepay.g3.facade.laike.enumtype;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Description: 功能级别
 * Author: jiawen.huang
 * Date: 2017/7/21
 * Time: 16:38
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum FuncLevelEnum {

    PUBLIC(0, "公开访问权限"),
    LOGIN(1, "注册后访问"),
    PAYABLE(2, "开通交易后访问"),
    WITHDRAW(3, "开通结算后访问"),
    S0_SUCCESS(4, "开通秒到后访问");//暂无

    private static final List<FuncLevelEnum> VALUE_LIST = Lists.newArrayList();

    static {
        for (FuncLevelEnum item : FuncLevelEnum.values()) {
            VALUE_LIST.add(item);
        }
    }

    private int value;

    private String displayName;

    FuncLevelEnum(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<FuncLevelEnum> getValueList() {
        return VALUE_LIST;
    }

}
