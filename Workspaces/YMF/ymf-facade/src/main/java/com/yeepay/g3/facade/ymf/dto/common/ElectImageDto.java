package com.yeepay.g3.facade.ymf.dto.common;

/**
 * 电子凭证元素
 *
 * Created by dongxulu on 16/9/27.
 */
public class ElectImageDto extends BaseDTO{
    
    /**
     *参考号
     */
    private String externalId;
    /**
     *商户编号
     */
    private String customerNumber;
    /**
     *商户订单号
     */
    private String customerOrderId;
    /**
     *交易时间
     */
    private String tradeTime;
    /**
     *商户名称
     */
    private String customerName;
    /**
     *交易金额
     */
    private String trxAmount;
    /**
     *产品名称
     */
    private String productName;
    /**
     *交易日期
     */
    private String trxDate;
    /**
     *用户姓名
     */
    private String userName;

    /**
     *交易状态
     */
    private String status;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

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

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(String trxAmount) {
        this.trxAmount = trxAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

}

