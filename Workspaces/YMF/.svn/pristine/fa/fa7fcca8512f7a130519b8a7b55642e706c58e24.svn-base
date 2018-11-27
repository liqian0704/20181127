package com.yeepay.g3.facade.ymf.exception;

import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;

/**
 * Descripe: 易码付hession通用异常
 * 异常码
 */
public class YmfException extends Exception {

    private static final long serialVersionUID = 3720522659489663341L;
    private ResponseMessage resp;

    public YmfException(Throwable t) {
        super(t);
    }

    public YmfException(Throwable t, ResponseMessage resp) {
        super(t);
        this.resp = resp;
    }

    public YmfException(ResponseMessage resp) {
        super(resp.getMsg());
        this.resp = resp;

    }

    public ResponseMessage getResp() {
        return resp;
    }

    public void setResp(ResponseMessage resp) {
        this.resp = resp;
    }
}