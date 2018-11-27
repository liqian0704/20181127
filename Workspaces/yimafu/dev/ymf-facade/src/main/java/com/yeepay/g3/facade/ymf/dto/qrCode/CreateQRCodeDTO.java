package com.yeepay.g3.facade.ymf.dto.qrCode;

import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;

import java.io.Serializable;

/**
 * Created by dongxulu on 16/11/1.
 */
public class CreateQRCodeDTO implements Serializable {

    public String qrOwner;
    public int qrCount;
    public String shopname;
    public String customerNumber;
    public String agentNumber;
    public String sales;
    public String qrInvoice;
    public BusinessType businessType;

    @Override
    public String toString() {
        return "CreateQRCodeDTO{" +
                "qrOwner='" + qrOwner + '\'' +
                ", qrCount=" + qrCount +
                ", shopname='" + shopname + '\'' +
                ", customerNumber='" + customerNumber + '\'' +
                ", agentNumber='" + agentNumber + '\'' +
                ", sales='" + sales + '\'' +
                ", qrInvoice='" + qrInvoice + '\'' +
                '}';
    }

    public String getQrInvoice() {
        return qrInvoice;
    }

    public void setQrInvoice(String qrInvoice) {
        this.qrInvoice = qrInvoice;
    }

    public String getQrOwner() {
        return qrOwner;
    }

    public void setQrOwner(String qrOwner) {
        this.qrOwner = qrOwner;
    }

    public int getQrCount() {
        return qrCount;
    }

    public void setQrCount(int qrCount) {
        this.qrCount = qrCount;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }
}
