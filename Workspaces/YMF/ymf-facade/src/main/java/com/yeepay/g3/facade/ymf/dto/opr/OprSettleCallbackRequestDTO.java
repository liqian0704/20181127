package com.yeepay.g3.facade.ymf.dto.opr;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;

/**
 * Created by dongxulu on 17/1/19.
 */
public class OprSettleCallbackRequestDTO  extends BaseDTO{

    private static final long serialVersionUID = -935135656253702692L;
    /**
     *  父商编
     */
    private String parentMerchantNo;
    /**
     *  子商编
     */
    private String merchantNo;
    /**
     *  商户订单号
     */
    private String orderId;
    /**
     *  银行订单号
     */
    private String bankOrderId;
    /**
     *  易宝唯一订单号
     */
    private String uniqueOrderNo;
    /**
     * 通知状态，SUCCESS
     */
    private String status;
    /**
     * 订单金额
     */
    private String orderAmount;
    /**
     * 支付金额
     */
    private String payAmount;
    /**
     *  请求时间
     */
    private String requestDate;
    /**
     * 支付成功时间
     */
    private String successDate;
    /**
     * 清算完成时间
     */
    private String csSuccessDate;
    /**
     * 订单状态 SUCCESS
     */
    private String orderStatus;
    /**
     * 业务方标识（内部系统调用回传，非内部系统不传）
     */
    private String bizSystemNo;
    /**
     * 支付成功时间
     */
    private String paySuccessDate;
    /**
     * 商户手续费
     */
    private String merchantFee;
    /**
     * 用户手续费
     */
    private String customerFee;
    /**
     * 收款场景（内部系统调用回传，非内部系统不传）
     */
    private String cashierType;
    /**
     * 支付产品（内部系统调用回传，非内部系统不传）
     */
    private String paymentProduct;
    /**
     * 银行通道编码(微信WECHAT,支付宝ALIPAY,分期支付CFL_BT)
     */
    private String bankId;

    public String getCashierType() {
        return cashierType;
    }

    public void setCashierType(String cashierType) {
        this.cashierType = cashierType;
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

    public String getMerchantFee() {
        return merchantFee;
    }

    public void setMerchantFee(String merchantFee) {
        this.merchantFee = merchantFee;
    }

    public String getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(String customerFee) {
        this.customerFee = customerFee;
    }

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaySuccessDate() {
        return paySuccessDate;
    }

    public void setPaySuccessDate(String paySuccessDate) {
        this.paySuccessDate = paySuccessDate;
    }

    public String getBankOrderId() {
        return bankOrderId;
    }

    public void setBankOrderId(String bankOrderId) {
        this.bankOrderId = bankOrderId;
    }

    public String getUniqueOrderNo() {
        return uniqueOrderNo;
    }

    public void setUniqueOrderNo(String uniqueOrderNo) {
        this.uniqueOrderNo = uniqueOrderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getSuccessDate() {
        return successDate;
    }

    public void setSuccessDate(String successDate) {
        this.successDate = successDate;
    }

    public String getCsSuccessDate() {
        return csSuccessDate;
    }

    public void setCsSuccessDate(String csSuccessDate) {
        this.csSuccessDate = csSuccessDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBizSystemNo() {
        return bizSystemNo;
    }

    public void setBizSystemNo(String bizSystemNo) {
        this.bizSystemNo = bizSystemNo;
    }


}
