package com.yeepay.g3.facade.ymf.dto.refund;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.dto.common.PageParam;
import com.yeepay.g3.facade.ymf.validator.annotations.LengthValidator;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Title: 发起退款 请求参数
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public class RefundRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -4594593258501459510L;

    @NotEmpty("商户编号")
    private String customerNumber;

    private String customerOrderId;

    @NotEmpty("易码付订单号")
    private String externalId;

    @NotEmpty("退款金额")
    private BigDecimal refundAmount;

    @NotEmpty("交易流水号")
    private String yeepayOrderId;

    @LengthValidator(value = "退款备注", length = 50)
    private String remark;

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

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
