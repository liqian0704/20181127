package com.yeepay.g3.facade.ymf.dto.refund;

import com.yeepay.g3.facade.ymf.dto.common.PageParam;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Title: 退款接口请求参数实体
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public class RefundOrderArgs extends PageParam implements Serializable {

    private static final long serialVersionUID = -5912386300587679283L;
    @NotEmpty("商户编号")
    private String customerNumber;

    private String customerOrderId; // 原商户订单号

    private String refundStatus; // 退款状态

    private String outOrderId; // 商户名称

    private Date createTimeStart; // 退款请求时间开始
    private Date createTimeEnd; // 退款请求时间结束

    private Date refundTimeStart; // 退款成功时间开始
    private Date refundTimeEnd; // 退款成功时间结束

    private BigDecimal refundAmount; // 退款金额

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

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getRefundTimeStart() {
        return refundTimeStart;
    }

    public void setRefundTimeStart(Date refundTimeStart) {
        this.refundTimeStart = refundTimeStart;
    }

    public Date getRefundTimeEnd() {
        return refundTimeEnd;
    }

    public void setRefundTimeEnd(Date refundTimeEnd) {
        this.refundTimeEnd = refundTimeEnd;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

}
