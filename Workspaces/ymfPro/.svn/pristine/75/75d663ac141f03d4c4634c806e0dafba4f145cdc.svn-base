package com.yeepay.g3.facade.laike.dto;

import java.io.Serializable;

/**
 * Description:
 * Author: wei.li
 * Date: 17/8/1
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class FeeRateDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 费率
     */
    private String feeRate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeeRateDetail that = (FeeRateDetail) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return feeRate != null ? feeRate.equals(that.feeRate) : that.feeRate == null;

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (feeRate != null ? feeRate.hashCode() : 0);
        return result;
    }
}
