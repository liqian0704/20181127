package com.yeepay.g3.facade.laike.dto.boss;


import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 大商户注册Request(后台)
 * Author: wei.li
 * Date: 17/3/1
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class RegisterMerRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    private List<String> phoneNoList;

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * OP单号
     */
    private String opNo;

    /**
     * 企业类型
     */
    private CompanyTypeEnum companyType;

    /**
     * boss手机号
     */
    private String bossUser;


    public List<String> getPhoneNoList() {
        return phoneNoList;
    }

    public void setPhoneNoList(List<String> phoneNo) {
        this.phoneNoList = phoneNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getOpNo() {
        return opNo;
    }

    public void setOpNo(String opNo) {
        this.opNo = opNo;
    }

    public CompanyTypeEnum getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyTypeEnum companyType) {
        this.companyType = companyType;
    }

    public String getBossUser() {
        return bossUser;
    }

    public void setBossUser(String bossUser) {
        this.bossUser = bossUser;
    }
}
