package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.common.Amount;

import java.util.Date;

/**
 * Description: 当日收款回参
 * Author: wei.li
 * Date: 17/5/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class QueryTodayResponse extends BaseResponse {

    private Integer totalCount = 0;

    private Amount totalAmount = new Amount(0);

    private Date currentTime = new Date();

    private String accountBalance = "0.00";

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Amount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Amount totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }
}
