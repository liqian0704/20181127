package com.yeepay.g3.facade.ymf.dto.order;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Title: 订单详情DTO
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/16.
 */
public class OrderDetailDTO extends BaseDTO implements Serializable  {

    private static final long serialVersionUID = -6517666404124836611L;

    private Long id; // 主键
    private String customerNumber; // 商户编号
    private String customerName; // 商户名称
    private String customerOrderId; // 商户订单号
    private String externalId; // 易码付订单号 唯一
    private String yeepayOrderId; // 交易流水号
    private String outOrderId; // 商品标识
    private BigDecimal amount; // 订单金额
    private BigDecimal fee; // 手续费
    private Date createTime; // 请求时间
    private Date payTime; // 支付时间
    private PaymentStatus payStatus; // 支付状态
    private String payConfirm; // 支付凭证码
    private String paySource; // 支付方式
    private BusinessType businessType; // 业务类型

    private BigDecimal refundAmount; // 已退金额
    private BigDecimal remain; // 可退金额

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public PaymentStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PaymentStatus payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayConfirm() {
        return payConfirm;
    }

    public void setPayConfirm(String payConfirm) {
        this.payConfirm = payConfirm;
    }

    public String getPaySource() {
        return paySource;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getRemain() {
        return remain;
    }

    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }
}
