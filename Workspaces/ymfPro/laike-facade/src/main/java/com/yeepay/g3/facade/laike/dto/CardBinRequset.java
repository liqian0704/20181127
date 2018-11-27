package com.yeepay.g3.facade.laike.dto;

/**
 * Description: 卡bin查询请求
 * Author: wei.li
 * Date: 17/6/21
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class CardBinRequset extends BaseRequest {

    /**
     * 银行卡号
     */
    private String bankCardNo;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CardBinRequset{");
        sb.append("bankCardNo='").append(bankCardNo);
        sb.append('}');
        return sb.toString();
    }
}
