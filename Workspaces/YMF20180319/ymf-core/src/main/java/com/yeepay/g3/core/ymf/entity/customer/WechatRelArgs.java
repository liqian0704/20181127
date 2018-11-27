package com.yeepay.g3.core.ymf.entity.customer;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.OneOf;

public class WechatRelArgs extends BaseDTO {

    private Long id;

    @NotEmpty("商户编号")
    private String customerNumber;

    @NotEmpty("AppId")
    private String appId;

    @NotEmpty("App密钥")
    private String appSecret;

    @NotEmpty
    @OneOf(value = "状态", type = Status.class)
    private Status status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}