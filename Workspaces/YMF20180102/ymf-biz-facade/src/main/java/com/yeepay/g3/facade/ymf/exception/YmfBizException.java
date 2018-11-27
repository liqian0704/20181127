package com.yeepay.g3.facade.ymf.exception;

import com.yeepay.g3.facade.ymf.dto.BizErrorCode;

/**
 * Title: 来客业务异常
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
public class YmfBizException extends Exception {

    private static final long serialVersionUID = -827298145471980576L;

    private String message;

    private BizErrorCode errorCode;

    public YmfBizException(BizErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "[" + errorCode.getKey() + "]" + errorCode.getMsg() + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BizErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(BizErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
