package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * 打款状态
 * Created by dongxulu on 17/4/24.
 */
public enum RemitStatus {

    INIT("未打款"),
    PROCESS("打款中"),
    SUCCESS("打款成功"),
    FAIL("打款失败");

    private String displayName;

    RemitStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
