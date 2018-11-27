package com.yeepay.g3.facade.laike.dto;

import java.io.Serializable;

/**
 * Description:
 * Author: wei.li
 * Date: 17/4/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class BankInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bankName;

    private String bankCode;

    private String bankImg;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankImg() {
        return bankImg;
    }

    public void setBankImg(String bankImg) {
        this.bankImg = bankImg;
    }
}
