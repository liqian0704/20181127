package com.yeepay.g3.facade.ymf.dto.order;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.dto.common.PageParam;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.OneOf;

import java.io.Serializable;
import java.util.Date;

/**
 * 单笔订单查询请求参数
 * 注解支持校验
 */
public class OrderQueryArgs extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -8321358348851005113L;
    @NotEmpty("商户编号")
    private String customerNumber; // 商户编号
    @NotEmpty("商户订单号")
    private String customerOrderId; // 商户订单号

    private String yeepayOrderId; // 交易流水号
    @NotEmpty("来客订单号")
    private String externalId; // 易码付订单号 唯一


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}