package com.yeepay.g3.facade.ymf.dto.refund;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Title: 退款订单DTO
 * Description:
 * Copyright: Copyright (c)2016
 * Company: YeePay
 *
 * @author chen.liu on 16/8/25.
 */
public class RefundDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -2395216567703792300L;

    private Long id; // payment主键
    private Long rowNumber; // 序号
    private String customerNumber;
    private String refundOrderId; // 退款请求号
    private String customerOrderId; // 原商户订单号
    private String yeepayOrderId; // 原交易流水号 易宝流水号
    private BigDecimal refundRequestAmount; // 退款请求金额
    private BigDecimal refundAmount; // 已退金额
    private BigDecimal trxAmount; // 原支付金额
    private Date createTime; // 退款请求时间
    private Date refundTime; // 退款成功时间
    private String status; // 退款状态
    private String payType; // 支付方式
    private String cause; // 退款备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(String refundOrderId) {
        this.refundOrderId = refundOrderId;
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

    public BigDecimal getRefundRequestAmount() {
        return refundRequestAmount;
    }

    public void setRefundRequestAmount(BigDecimal refundRequestAmount) {
        this.refundRequestAmount = refundRequestAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(BigDecimal trxAmount) {
        this.trxAmount = trxAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
