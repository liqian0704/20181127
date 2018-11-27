package com.yeepay.g3.facade.ymf.dto.opr;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;

/**
 * Created by dongxulu on 17/1/19.
 */
public class OprPayCallbackRequestDTO extends BaseDTO {
    private static final long serialVersionUID = 2179795448757966404L;
    /**
     * 父商编
     */
    private String parentMerchantNo;
    /**
     * 易宝唯一订单号
     */
    private String uniqueOrderNo;
    /**
     * 支付成功时间
     */
    private String successDate;
    /**
     *  通知状态，SUCCESS
     */
    private String status;
    /**
     *  商户订单号
     */
    private String orderId;
    /**
     *  请求时间
     */
    private String requestDate;
    /**
     * 支付成功时间
     */
    private String paySuccessDate;
    /**
     * 支付金额
     */
    private String payAmount;
    /**
     * 订单金额
     */
    private String orderAmount;
    /**
     * 分期期数，分期支付才有
     */
    private String instNumber;
    /**
     * 分期公司，分期支付才有
     */
    private String instCompany;
    /**
     * 子商编
     */
    private String merchantNo;
    /**
     * 业务方标识（内部系统调用回传，非内部系统不传）
     */
    private String bizSystemNo;
    /**
     * 银行订单号
     */
    private String bankOrderId;
    /**
     *  银行通道编码(微信WECHAT,支付宝ALIPAY,分期支付CFL_BT)
     */
    private String bankId;
    /**
     * 支付产品
     */
    private String paymentProduct;
    /**
     * 卡类型 借记卡 贷记卡
     */
    private String cardType;
    /**
     *
     */
    private String platformType;
    /**
     * 银行流水号
     */
    private String bankTrxId;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPaymentProduct() {
        return paymentProduct;
    }

    public void setPaymentProduct(String paymentProduct) {
        this.paymentProduct = paymentProduct;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }
    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }
    public String getUniqueOrderNo() {
        return uniqueOrderNo;
    }

    public void setUniqueOrderNo(String uniqueOrderNo) {
        this.uniqueOrderNo = uniqueOrderNo;
    }

    public String getSuccessDate() {
        return successDate;
    }

    public void setSuccessDate(String successDate) {
        this.successDate = successDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPaySuccessDate() {
        return paySuccessDate;
    }

    public void setPaySuccessDate(String paySuccessDate) {
        this.paySuccessDate = paySuccessDate;
    }

    public String getInstNumber() {
        return instNumber;
    }

    public void setInstNumber(String instNumber) {
        this.instNumber = instNumber;
    }

    public String getInstCompany() {
        return instCompany;
    }

    public void setInstCompany(String instCompany) {
        this.instCompany = instCompany;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getBizSystemNo() {
        return bizSystemNo;
    }

    public void setBizSystemNo(String bizSystemNo) {
        this.bizSystemNo = bizSystemNo;
    }

    public String getBankOrderId() {
        return bankOrderId;
    }

    public void setBankOrderId(String bankOrderId) {
        this.bankOrderId = bankOrderId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getBankTrxId() {
        return bankTrxId;
    }

    public void setBankTrxId(String bankTrxId) {
        this.bankTrxId = bankTrxId;
    }
}
