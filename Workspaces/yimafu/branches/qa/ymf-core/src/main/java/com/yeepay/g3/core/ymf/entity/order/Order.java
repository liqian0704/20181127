package com.yeepay.g3.core.ymf.entity.order;

import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    private Long id;

    private Long version;

    private String customerNumber;

    private String customerOrderId;

    private String externalId;

    private String outOrderId;

    private String sanCode;

    private BigDecimal trxAmt;

    private BigDecimal realAmt;

    private BigDecimal refundAmt;

    private BigDecimal fee;

    private String receiverName;

    private String receiverTel;

    private BusinessType businessType;

    private Integer queryCount;

    private String remark;

    private Date createTime;

    private Date completeTime;

    private OrderStatus orderStatus;

    private String payConfirm;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getSanCode() {
        return sanCode;
    }

    public void setSanCode(String sanCode) {
        this.sanCode = sanCode;
    }

    public BigDecimal getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(BigDecimal trxAmt) {
        this.trxAmt = trxAmt;
    }

    public BigDecimal getRealAmt() {
        return realAmt;
    }

    public void setRealAmt(BigDecimal realAmt) {
        this.realAmt = realAmt;
    }

    public BigDecimal getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(BigDecimal refundAmt) {
        this.refundAmt = refundAmt;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public Integer getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(Integer queryCount) {
        this.queryCount = queryCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayConfirm() {
        return payConfirm;
    }

    public void setPayConfirm(String payConfirm) {
        this.payConfirm = payConfirm;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getCustomerNumber() == null ? other.getCustomerNumber() == null : this.getCustomerNumber().equals(other.getCustomerNumber()))
            && (this.getCustomerOrderId() == null ? other.getCustomerOrderId() == null : this.getCustomerOrderId().equals(other.getCustomerOrderId()))
            && (this.getExternalId() == null ? other.getExternalId() == null : this.getExternalId().equals(other.getExternalId()))
            && (this.getOutOrderId() == null ? other.getOutOrderId() == null : this.getOutOrderId().equals(other.getOutOrderId()))
            && (this.getSanCode() == null ? other.getSanCode() == null : this.getSanCode().equals(other.getSanCode()))
            && (this.getTrxAmt() == null ? other.getTrxAmt() == null : this.getTrxAmt().equals(other.getTrxAmt()))
            && (this.getRealAmt() == null ? other.getRealAmt() == null : this.getRealAmt().equals(other.getRealAmt()))
            && (this.getRefundAmt() == null ? other.getRefundAmt() == null : this.getRefundAmt().equals(other.getRefundAmt()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getReceiverName() == null ? other.getReceiverName() == null : this.getReceiverName().equals(other.getReceiverName()))
            && (this.getReceiverTel() == null ? other.getReceiverTel() == null : this.getReceiverTel().equals(other.getReceiverTel()))
            && (this.getBusinessType() == null ? other.getBusinessType() == null : this.getBusinessType().equals(other.getBusinessType()))
            && (this.getQueryCount() == null ? other.getQueryCount() == null : this.getQueryCount().equals(other.getQueryCount()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCompleteTime() == null ? other.getCompleteTime() == null : this.getCompleteTime().equals(other.getCompleteTime()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getPayConfirm() == null ? other.getPayConfirm() == null : this.getPayConfirm().equals(other.getPayConfirm()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getCustomerNumber() == null) ? 0 : getCustomerNumber().hashCode());
        result = prime * result + ((getCustomerOrderId() == null) ? 0 : getCustomerOrderId().hashCode());
        result = prime * result + ((getExternalId() == null) ? 0 : getExternalId().hashCode());
        result = prime * result + ((getOutOrderId() == null) ? 0 : getOutOrderId().hashCode());
        result = prime * result + ((getSanCode() == null) ? 0 : getSanCode().hashCode());
        result = prime * result + ((getTrxAmt() == null) ? 0 : getTrxAmt().hashCode());
        result = prime * result + ((getRealAmt() == null) ? 0 : getRealAmt().hashCode());
        result = prime * result + ((getRefundAmt() == null) ? 0 : getRefundAmt().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getReceiverName() == null) ? 0 : getReceiverName().hashCode());
        result = prime * result + ((getReceiverTel() == null) ? 0 : getReceiverTel().hashCode());
        result = prime * result + ((getBusinessType() == null) ? 0 : getBusinessType().hashCode());
        result = prime * result + ((getQueryCount() == null) ? 0 : getQueryCount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCompleteTime() == null) ? 0 : getCompleteTime().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getPayConfirm() == null) ? 0 : getPayConfirm().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", customerOrderId=").append(customerOrderId);
        sb.append(", externalId=").append(externalId);
        sb.append(", outOrderId=").append(outOrderId);
        sb.append(", sanCode=").append(sanCode);
        sb.append(", trxAmt=").append(trxAmt);
        sb.append(", realAmt=").append(realAmt);
        sb.append(", refundAmt=").append(refundAmt);
        sb.append(", fee=").append(fee);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverTel=").append(receiverTel);
        sb.append(", businessType=").append(businessType);
        sb.append(", queryCount=").append(queryCount);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", payConfirm=").append(payConfirm);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}