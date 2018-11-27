package com.yeepay.g3.facade.laike.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 产品信息详情
 * Author: wei.li
 * Date: 17/4/10
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String maxLimit;

    private String loanTime;

    private String feeRate;

    private String deadline;

    private String icon;

    private List<String> description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(String maxLimit) {
        this.maxLimit = maxLimit;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
