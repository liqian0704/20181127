package com.yeepay.g3.facade.ymf.exception;

import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;

/**
 * 交易业务提示码
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 16/10/24
 * @Time: 下午5:24
 */
public class YmfTrxException extends Exception {

    private static final long serialVersionUID = 3720622659489663641L;

    private TrxCode trxCode;

    public YmfTrxException(TrxCode trxCode) {
        super(trxCode.toPromptMsg());
        this.trxCode = trxCode;
    }

    public YmfTrxException(TrxCode trxCode,String message) {
        super(trxCode.toPromptMsg() + "." + message);
        this.trxCode = trxCode ;
    }

    public String getCode() {
        return trxCode.getCode();
    }

    public TrxCode getTrxCode() {
        return trxCode;
    }

    @Override
    public String toString() {
        return super.toString() ;
    }

    public static void main(String[] args) {
        System.out.println(new YmfTrxException(TrxCode.T1001).getMessage()) ;
        System.out.println(new YmfTrxException(TrxCode.T1001,"商编不能为空"));
    }
}
