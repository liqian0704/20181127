package com.yeepay.g3.facade.ymf.enumtype.common;

/**
 * @Description: 网点状态
 * @Author: xiaobin.liu
 * @Date: 17/6/20
 * @Time: 上午10:54
 */
public enum ShopStatus {

    ACTIVE("开通"),

    INACTIVE("关闭"),

    DELETED("核销");

    public String displayName;

    ShopStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
