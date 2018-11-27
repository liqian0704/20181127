package com.yeepay.g3.facade.ymf.dto.laike;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;

/**
 * Created by dongxulu on 16/12/2.
 */
public class ScanQrCodeRequestDTO extends BaseScanCodeDTO {
    private static final long serialVersionUID = -7248430003952309541L;
    /**
     * 产品名称
     */
    private String productName;
    /**
     *产品描述
     */
    private String productDesc;
    /**
     * 网点编号
     */
    private String shopName;
    /**
     * 订单金额
     */
    private Amount orderAmount;
    /**
     * 打赏金额
     */
    private Amount gratuityAmount;
    /**
     * 网点编号
     */
    private String shopNumber;
    /**
     * 用户标识  用于一键绑卡
     */
    private String userID;
    /**
     * 0:本人 1:他人
     */
    private String selfFlag;
    /**
     * 支付方式
     */
    private PaySource paySource;
    /**
     * 用户ip
     */
    private String userIp;

    public String getSelfFlag() {
        return selfFlag;
    }

    public void setSelfFlag(String selfFlag) {
        this.selfFlag = selfFlag;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Amount getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Amount orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Amount getGratuityAmount() {
        return gratuityAmount;
    }

    public void setGratuityAmount(Amount gratuityAmount) {
        this.gratuityAmount = gratuityAmount;
    }

    public PaySource getPaySource() {
        return paySource;
    }

    public void setPaySource(PaySource paySource) {
        this.paySource = paySource;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }
}
