package com.yeepay.g3.facade.ymf.dto.agent;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.enumtype.common.CardType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Title: 订单同步DTO
 * 只同步成功{@link com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus#SUCCESS}的订单
 * 来客订单统一按非封顶订单标识, 分润规则按比例
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/12/22.
 */
public class SyncOrderDTO extends BaseDTO {

    private static final long serialVersionUID = -259653013164502361L;

    /**
     * 交易订单号
     * external_id
     */
    private String trxCode;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 商户编号
     */
    private String customerNumber;

    /**
     * 交易时间
     * 订单完成时间
     */
    private Date trxTime;

    /**
     * 交易卡类型
     */
    private CardType cardType;

    /**
     * 支付方式
     */
    private PaySource paySource;

    /**
     * 商户名称
     */
    private String customerName;

    /**
     * 商户订单号
     */
    private String customerOrderId;

    /**
     * 支付凭证码
     */
    private String payConfirm;


    public String getTrxCode() {
        return trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Date getTrxTime() {
        return trxTime;
    }

    public void setTrxTime(Date trxTime) {
        this.trxTime = trxTime;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public PaySource getPaySource() {
        return paySource;
    }

    public void setPaySource(PaySource paySource) {
        this.paySource = paySource;
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

    public String getPayConfirm() {
        return payConfirm;
    }

    public void setPayConfirm(String payConfirm) {
        this.payConfirm = payConfirm;
    }
}
