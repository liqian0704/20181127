package com.yeepay.g3.facade.ymf.enumtype;

/**
 * Created by yp-tc-m-2762 on 16/8/11.
 * 操作类型
 */
public enum OperateType {

    ADD("增加"),

    DELETE("删除"),
    UPDATE("修改"),

    SELECT("查询");

    public String displayName;

    OperateType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
