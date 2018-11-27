package com.yeepay.g3.facade.ymf.dto.agent;/**
 * Created by jiwei.lv on 17/6/14.
 */

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.enumtype.common.CardType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jiwei.lv
 * @create 2017-06-17/6/14
 */
public class SyncS0OrderDTO extends BaseDTO {
    private static final long serialVersionUID = -2594983147901816535L;

    /**
     * 交易订单号/商户订单号
     */
    private String trxCode;
    /**
     * 易宝订单号
     */
    private String yeepayOrderId;
    /**
     * 商户编号
     */
    private String customerNumber;
    /**
     * 商户名称
     */
    private String customerName;
    /**
     * 交易时间/打款创建时间
     */
    private Date trxTime;
    /**
     * 打款请求时间
     */
    private Date remitRequestTime;
    /**
     * 回调时间
     */
    private Date callBackTime;
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 打款金额
     */
    private BigDecimal amount;
    /**
     * 商户承担手续费
     */
    private BigDecimal srcFee;
    /**
     * 用户承担手续费
     */
    private BigDecimal targetFee;
    /**
     * 源手续费
     */
    private BigDecimal fee;

    /**
     * 支付方式
     */
    private PaySource paySource;

    /**
     * 交易卡类型
     */
    private CardType cardType;

    public String getTrxCode() {
        return trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
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

    public Date getTrxTime() {
        return trxTime;
    }

    public void setTrxTime(Date trxTime) {
        this.trxTime = trxTime;
    }

    public Date getRemitRequestTime() {
        return remitRequestTime;
    }

    public void setRemitRequestTime(Date remitRequestTime) {
        this.remitRequestTime = remitRequestTime;
    }

    public Date getCallBackTime() {
        return callBackTime;
    }

    public void setCallBackTime(Date callBackTime) {
        this.callBackTime = callBackTime;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSrcFee() {
        return srcFee;
    }

    public void setSrcFee(BigDecimal srcFee) {
        this.srcFee = srcFee;
    }

    public BigDecimal getTargetFee() {
        return targetFee;
    }

    public void setTargetFee(BigDecimal targetFee) {
        this.targetFee = targetFee;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public PaySource getPaySource() {
        return paySource;
    }

    public void setPaySource(PaySource paySource) {
        this.paySource = paySource;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
