package com.yeepay.g3.core.ymf.entity.remit;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Remittance implements Serializable {
    private Long id;

    private Integer version;

    private String customerNumber;

    private String customerOrderId;

    private String yeepayOrderId;

    private String batchNo;

    private BigDecimal amount;

    private BigDecimal srcFee;

    private BigDecimal targetFee;

    private BigDecimal fee;

    private RemitStatus remitStatus;

    private Date createTime;

    private Date requestTime;

    private Date callbackTime;

    private Date lastNotifyTime;

    private String description;

    private String remark;

    private Status status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
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

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
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

    public RemitStatus getRemitStatus() {
        return remitStatus;
    }

    public void setRemitStatus(RemitStatus remitStatus) {
        this.remitStatus = remitStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getCallbackTime() {
        return callbackTime;
    }

    public void setCallbackTime(Date callbackTime) {
        this.callbackTime = callbackTime;
    }

    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        Remittance other = (Remittance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getCustomerNumber() == null ? other.getCustomerNumber() == null : this.getCustomerNumber().equals(other.getCustomerNumber()))
            && (this.getCustomerOrderId() == null ? other.getCustomerOrderId() == null : this.getCustomerOrderId().equals(other.getCustomerOrderId()))
            && (this.getYeepayOrderId() == null ? other.getYeepayOrderId() == null : this.getYeepayOrderId().equals(other.getYeepayOrderId()))
            && (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getSrcFee() == null ? other.getSrcFee() == null : this.getSrcFee().equals(other.getSrcFee()))
            && (this.getTargetFee() == null ? other.getTargetFee() == null : this.getTargetFee().equals(other.getTargetFee()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getRemitStatus() == null ? other.getRemitStatus() == null : this.getRemitStatus().equals(other.getRemitStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getRequestTime() == null ? other.getRequestTime() == null : this.getRequestTime().equals(other.getRequestTime()))
            && (this.getCallbackTime() == null ? other.getCallbackTime() == null : this.getCallbackTime().equals(other.getCallbackTime()))
            && (this.getLastNotifyTime() == null ? other.getLastNotifyTime() == null : this.getLastNotifyTime().equals(other.getLastNotifyTime()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getCustomerNumber() == null) ? 0 : getCustomerNumber().hashCode());
        result = prime * result + ((getCustomerOrderId() == null) ? 0 : getCustomerOrderId().hashCode());
        result = prime * result + ((getYeepayOrderId() == null) ? 0 : getYeepayOrderId().hashCode());
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getSrcFee() == null) ? 0 : getSrcFee().hashCode());
        result = prime * result + ((getTargetFee() == null) ? 0 : getTargetFee().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getRemitStatus() == null) ? 0 : getRemitStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getRequestTime() == null) ? 0 : getRequestTime().hashCode());
        result = prime * result + ((getCallbackTime() == null) ? 0 : getCallbackTime().hashCode());
        result = prime * result + ((getLastNotifyTime() == null) ? 0 : getLastNotifyTime().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", yeepayOrderId=").append(yeepayOrderId);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", amount=").append(amount);
        sb.append(", srcFee=").append(srcFee);
        sb.append(", targetFee=").append(targetFee);
        sb.append(", fee=").append(fee);
        sb.append(", remitStatus=").append(remitStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", requestTime=").append(requestTime);
        sb.append(", callbackTime=").append(callbackTime);
        sb.append(", lastNotifyTime=").append(lastNotifyTime);
        sb.append(", description=").append(description);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}