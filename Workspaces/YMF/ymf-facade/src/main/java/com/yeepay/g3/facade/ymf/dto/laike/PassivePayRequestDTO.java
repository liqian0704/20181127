package com.yeepay.g3.facade.ymf.dto.laike;/**
 * Created by jiwei.lv on 17/3/9.
 */

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;

/**
 * @author jiwei.lv
 * @create 2017-03-17/3/9
 */
public class PassivePayRequestDTO extends BaseScanCodeDTO {
    private static final long serialVersionUID = -1027732158374258708L;
    /**
     * 产品名称
     */
    private String productName;
    /**
     *产品描述
     */
    private String productDesc;
    /**
     * 网点名称
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
     * 支付方式
     */
    private PaySource paySource;
    /**
     * 用户ip
     */
    private String userIp;

    /**
     * 设备号
     */
    private String deviceSn;
    /**
     * 授权码(扫描码结果)
     */
    private String code;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public PaySource getPaySource() {
        return paySource;
    }

    public void setPaySource(PaySource paySource) {
        this.paySource = paySource;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
