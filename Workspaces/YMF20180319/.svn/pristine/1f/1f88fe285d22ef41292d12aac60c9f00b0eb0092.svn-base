package com.yeepay.g3.core.ymf.entity.gratuity;

import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GratuityOrder implements Serializable {
    private Long id;

    private Long version;

    private BigDecimal trxAmt;

    private String customerNumber;

    private String gratuityAmount;

    private OrderStatus orderStatus;

    private Date createTime;

    private Date completeTime;

    private String employeeNumber;

    private String orgexternalId;

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

    public BigDecimal getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(BigDecimal trxAmt) {
        this.trxAmt = trxAmt;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getGratuityAmount() {
        return gratuityAmount;
    }

    public void setGratuityAmount(String gratuityAmount) {
        this.gratuityAmount = gratuityAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getOrgexternalId() {
        return orgexternalId;
    }

    public void setOrgexternalId(String orgexternalId) {
        this.orgexternalId = orgexternalId;
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
        GratuityOrder other = (GratuityOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getTrxAmt() == null ? other.getTrxAmt() == null : this.getTrxAmt().equals(other.getTrxAmt()))
            && (this.getCustomerNumber() == null ? other.getCustomerNumber() == null : this.getCustomerNumber().equals(other.getCustomerNumber()))
            && (this.getGratuityAmount() == null ? other.getGratuityAmount() == null : this.getGratuityAmount().equals(other.getGratuityAmount()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCompleteTime() == null ? other.getCompleteTime() == null : this.getCompleteTime().equals(other.getCompleteTime()))
            && (this.getEmployeeNumber() == null ? other.getEmployeeNumber() == null : this.getEmployeeNumber().equals(other.getEmployeeNumber()))
            && (this.getOrgexternalId() == null ? other.getOrgexternalId() == null : this.getOrgexternalId().equals(other.getOrgexternalId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getTrxAmt() == null) ? 0 : getTrxAmt().hashCode());
        result = prime * result + ((getCustomerNumber() == null) ? 0 : getCustomerNumber().hashCode());
        result = prime * result + ((getGratuityAmount() == null) ? 0 : getGratuityAmount().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCompleteTime() == null) ? 0 : getCompleteTime().hashCode());
        result = prime * result + ((getEmployeeNumber() == null) ? 0 : getEmployeeNumber().hashCode());
        result = prime * result + ((getOrgexternalId() == null) ? 0 : getOrgexternalId().hashCode());
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
        sb.append(", trxAmt=").append(trxAmt);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", gratuityAmount=").append(gratuityAmount);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", employeeNumber=").append(employeeNumber);
        sb.append(", orgexternalId=").append(orgexternalId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}