package com.yeepay.g3.core.ymf.entity.material;

import com.yeepay.g3.facade.ymf.enumtype.common.CommonStatus;
import java.io.Serializable;
import java.util.Date;

public class TermRelation implements Serializable {
    private Long id;

    private String customerNumber;

    private Long shopId;

    private String snSerial;

    private Long termId;

    private Date bindTime;

    private String bindOperator;

    private Date unbindTime;

    private String unbindOperator;

    private CommonStatus status;

    private String opCode;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSnSerial() {
        return snSerial;
    }

    public void setSnSerial(String snSerial) {
        this.snSerial = snSerial;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getBindOperator() {
        return bindOperator;
    }

    public void setBindOperator(String bindOperator) {
        this.bindOperator = bindOperator;
    }

    public Date getUnbindTime() {
        return unbindTime;
    }

    public void setUnbindTime(Date unbindTime) {
        this.unbindTime = unbindTime;
    }

    public String getUnbindOperator() {
        return unbindOperator;
    }

    public void setUnbindOperator(String unbindOperator) {
        this.unbindOperator = unbindOperator;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
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
        TermRelation other = (TermRelation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerNumber() == null ? other.getCustomerNumber() == null : this.getCustomerNumber().equals(other.getCustomerNumber()))
            && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()))
            && (this.getSnSerial() == null ? other.getSnSerial() == null : this.getSnSerial().equals(other.getSnSerial()))
            && (this.getTermId() == null ? other.getTermId() == null : this.getTermId().equals(other.getTermId()))
            && (this.getBindTime() == null ? other.getBindTime() == null : this.getBindTime().equals(other.getBindTime()))
            && (this.getBindOperator() == null ? other.getBindOperator() == null : this.getBindOperator().equals(other.getBindOperator()))
            && (this.getUnbindTime() == null ? other.getUnbindTime() == null : this.getUnbindTime().equals(other.getUnbindTime()))
            && (this.getUnbindOperator() == null ? other.getUnbindOperator() == null : this.getUnbindOperator().equals(other.getUnbindOperator()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOpCode() == null ? other.getOpCode() == null : this.getOpCode().equals(other.getOpCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerNumber() == null) ? 0 : getCustomerNumber().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        result = prime * result + ((getSnSerial() == null) ? 0 : getSnSerial().hashCode());
        result = prime * result + ((getTermId() == null) ? 0 : getTermId().hashCode());
        result = prime * result + ((getBindTime() == null) ? 0 : getBindTime().hashCode());
        result = prime * result + ((getBindOperator() == null) ? 0 : getBindOperator().hashCode());
        result = prime * result + ((getUnbindTime() == null) ? 0 : getUnbindTime().hashCode());
        result = prime * result + ((getUnbindOperator() == null) ? 0 : getUnbindOperator().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOpCode() == null) ? 0 : getOpCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", shopId=").append(shopId);
        sb.append(", snSerial=").append(snSerial);
        sb.append(", termId=").append(termId);
        sb.append(", bindTime=").append(bindTime);
        sb.append(", bindOperator=").append(bindOperator);
        sb.append(", unbindTime=").append(unbindTime);
        sb.append(", unbindOperator=").append(unbindOperator);
        sb.append(", status=").append(status);
        sb.append(", opCode=").append(opCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}