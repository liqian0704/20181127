package com.yeepay.g3.facade.ymf.dto.order;

import com.yeepay.g3.facade.ymf.dto.common.PageParam;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.OneOf;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单查询请求参数
 * 注解支持校验
 */
public class OrderArgs extends PageParam implements Serializable {

    private static final long serialVersionUID = -4478491064960544493L;
    //tradeName - 商品名称 未增加
    @NotEmpty("商户编号")
    private String customerNumber; // 商户编号
    private String externalId; // 易码付订单号 唯一
    private String customerName; // 商户名称
    private String customerOrderId; // 商户订单号
    private String outOrderId; // 商品标识
    private Date createTimeStart; // 创建时间开始
    private Date createTimeEnd; // 创建时间结束
    private Date payTimeStart; // 支付时间开始
    private Date payTimeEnd; // 支付时间结束

    @OneOf(value = "支付状态", type = PaymentStatus.class)
    private String payStatus; // 支付状态

    private String payConfirm; // 订单确认码

    @OneOf(value = "交易类型", type = BusinessType.class)
    private String businessType; // 业务类型

    private String shopName;//商户网点名称

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

    public Date getPayTimeStart() {
        return payTimeStart;
    }

    public void setPayTimeStart(Date payTimeStart) {
        this.payTimeStart = payTimeStart;
    }

    public Date getPayTimeEnd() {
        return payTimeEnd;
    }

    public void setPayTimeEnd(Date payTimeEnd) {
        this.payTimeEnd = payTimeEnd;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayConfirm() {
        return payConfirm;
    }

    public void setPayConfirm(String payConfirm) {
        this.payConfirm = payConfirm;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }



}