package com.yeepay.g3.facade.laike.dto;

import com.google.common.collect.Lists;
import com.yeepay.g3.facade.laike.enumtype.PayProductEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 计费信息
 * Author: wei.li
 * Date: 17/7/27
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class CalFeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private static String UNLIMITED = "不限";//限额转换

    /**
     * 支付产品
     */
    private PayProductEnum payProduct;

    /**
     * 支付产品中文
     */
    private String payProductName;

    /**
     * 费率信息描述
     */
    private List<FeeRateDetail> feeInfoDesc;

    /**
     * 单笔限额
     */
    private String singleAmount;

    /**
     * 单日限额
     */
    private String dayAmount;

    /**
     * 单月限额
     */
    private String monthAmount;


    public PayProductEnum getPayProduct() {
        return payProduct;
    }

    public void setPayProduct(PayProductEnum payProduct) {
        this.payProduct = payProduct;
    }

    public String getPayProductName() {
        return payProductName;
    }

    public void setPayProductName(String payProductName) {
        this.payProductName = payProductName;
    }

    public List<FeeRateDetail> getFeeInfoDesc() {
        return feeInfoDesc;
    }

    public void setFeeInfoDesc(List<FeeRateDetail> feeInfoDesc) {
        this.feeInfoDesc = feeInfoDesc;
    }

    public void setFeeInfoDesc(FeeRateDetail feeInfoDesc) {
        List<FeeRateDetail> list = Lists.newArrayList();
        list.add(feeInfoDesc);
        this.feeInfoDesc = list;
    }

    public String getSingleAmount() {
        return singleAmount;
    }

    public void setSingleAmount(Object singleAmount) {
        this.singleAmount = limitTransform(singleAmount);
    }

    public String getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(Object dayAmount) {
        this.dayAmount = limitTransform(dayAmount);
    }

    public String getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(Object monthAmount) {
        this.monthAmount = limitTransform(monthAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalFeeInfo that = (CalFeeInfo) o;

        return payProduct != null ? payProduct.equals(that.payProduct) : that.payProduct == null;

    }

    @Override
    public int hashCode() {
        return payProduct != null ? payProduct.hashCode() : 0;
    }

    /**
     * 限额转换
     *
     * @return
     */
    private String limitTransform(Object amount) {
        if (amount instanceof Integer) {
            if (new Integer(-1).equals(amount)) {
                return UNLIMITED;
            } else {
                return String.format("%.2f", ((Integer) amount).doubleValue());
            }
        } else {
            if ("-1".equals(amount) || UNLIMITED.equals(amount)) {
                return UNLIMITED;
            } else {
                return String.format("%.2f", Double.parseDouble((String) amount));
            }
        }

    }
}
