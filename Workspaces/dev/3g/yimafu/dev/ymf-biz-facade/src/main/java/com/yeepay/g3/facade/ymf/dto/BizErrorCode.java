package com.yeepay.g3.facade.ymf.dto;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/13.
 */
public enum BizErrorCode {

    SYS_ERROR("BIZ9999", "系统未知异常"),

    LACK_OF_PARAM("BIZ0001", "参数缺失"),
    ILLEGAL_PARAM("BIZ0002", "参数不合法"),

    // 商户管理异常码
    CUSTOMER_NOT_EXIST("BIZ2001", "商户不存在"),
    CUSTOMER_NOT_ACTIVE("BIZ2002", "商户状态无效"),

    // 终端管理异常码
    TERM_ERROR_IMPORT("BIZ1001", "批量入库失败"),

    TERM_ERROR_EXPORT("BIZ1002", "批量出库失败"),

    TERM_NO_RELATION("BIZ1003", "终端没有绑定关系"),

    TERM_NOT_EXIST("BIZ1004", "终端不存在"),

    TERM_EXIST_RELATION("BIZ1005", "终端已存在绑定关系")

    ;

    private String key;

    private String msg;

    BizErrorCode(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    public java.lang.String getKey() {
        return key;
    }

    public java.lang.String getMsg() {
        return msg;
    }
}
