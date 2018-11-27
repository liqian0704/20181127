package com.yeepay.g3.core.ymf.entity.profit;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProfitSummation implements Serializable {
    private Long id;

    private String month;

    private Long totalCount;

    private Long totalDayCount;

    private Long totalTrxCount;

    private BigDecimal totalTrxAmt;

    private BigDecimal mitangTotalTrxamt;

    private BigDecimal mitangTotalProfitAmt;

    private BigDecimal totalProfitAmt;

    private ProfitProductTypeEnum profitProductType;

    private CustomerTypeEnum customerType;

    private Status calculateStatus;

    private BigDecimal percent;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalDayCount() {
        return totalDayCount;
    }

    public void setTotalDayCount(Long totalDayCount) {
        this.totalDayCount = totalDayCount;
    }

    public Long getTotalTrxCount() {
        return totalTrxCount;
    }

    public void setTotalTrxCount(Long totalTrxCount) {
        this.totalTrxCount = totalTrxCount;
    }

    public BigDecimal getTotalTrxAmt() {
        return totalTrxAmt;
    }

    public void setTotalTrxAmt(BigDecimal totalTrxAmt) {
        this.totalTrxAmt = totalTrxAmt;
    }

    public BigDecimal getMitangTotalTrxamt() {
        return mitangTotalTrxamt;
    }

    public void setMitangTotalTrxamt(BigDecimal mitangTotalTrxamt) {
        this.mitangTotalTrxamt = mitangTotalTrxamt;
    }

    public BigDecimal getMitangTotalProfitAmt() {
        return mitangTotalProfitAmt;
    }

    public void setMitangTotalProfitAmt(BigDecimal mitangTotalProfitAmt) {
        this.mitangTotalProfitAmt = mitangTotalProfitAmt;
    }

    public BigDecimal getTotalProfitAmt() {
        return totalProfitAmt;
    }

    public void setTotalProfitAmt(BigDecimal totalProfitAmt) {
        this.totalProfitAmt = totalProfitAmt;
    }

    public ProfitProductTypeEnum getProfitProductType() {
        return profitProductType;
    }

    public void setProfitProductType(ProfitProductTypeEnum profitProductType) {
        this.profitProductType = profitProductType;
    }

    public CustomerTypeEnum getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeEnum customerType) {
        this.customerType = customerType;
    }

    public Status getCalculateStatus() {
        return calculateStatus;
    }

    public void setCalculateStatus(Status calculateStatus) {
        this.calculateStatus = calculateStatus;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        ProfitSummation other = (ProfitSummation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getTotalCount() == null ? other.getTotalCount() == null : this.getTotalCount().equals(other.getTotalCount()))
            && (this.getTotalDayCount() == null ? other.getTotalDayCount() == null : this.getTotalDayCount().equals(other.getTotalDayCount()))
            && (this.getTotalTrxCount() == null ? other.getTotalTrxCount() == null : this.getTotalTrxCount().equals(other.getTotalTrxCount()))
            && (this.getTotalTrxAmt() == null ? other.getTotalTrxAmt() == null : this.getTotalTrxAmt().equals(other.getTotalTrxAmt()))
            && (this.getMitangTotalTrxamt() == null ? other.getMitangTotalTrxamt() == null : this.getMitangTotalTrxamt().equals(other.getMitangTotalTrxamt()))
            && (this.getMitangTotalProfitAmt() == null ? other.getMitangTotalProfitAmt() == null : this.getMitangTotalProfitAmt().equals(other.getMitangTotalProfitAmt()))
            && (this.getTotalProfitAmt() == null ? other.getTotalProfitAmt() == null : this.getTotalProfitAmt().equals(other.getTotalProfitAmt()))
            && (this.getProfitProductType() == null ? other.getProfitProductType() == null : this.getProfitProductType().equals(other.getProfitProductType()))
            && (this.getCustomerType() == null ? other.getCustomerType() == null : this.getCustomerType().equals(other.getCustomerType()))
            && (this.getCalculateStatus() == null ? other.getCalculateStatus() == null : this.getCalculateStatus().equals(other.getCalculateStatus()))
            && (this.getPercent() == null ? other.getPercent() == null : this.getPercent().equals(other.getPercent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getTotalCount() == null) ? 0 : getTotalCount().hashCode());
        result = prime * result + ((getTotalDayCount() == null) ? 0 : getTotalDayCount().hashCode());
        result = prime * result + ((getTotalTrxCount() == null) ? 0 : getTotalTrxCount().hashCode());
        result = prime * result + ((getTotalTrxAmt() == null) ? 0 : getTotalTrxAmt().hashCode());
        result = prime * result + ((getMitangTotalTrxamt() == null) ? 0 : getMitangTotalTrxamt().hashCode());
        result = prime * result + ((getMitangTotalProfitAmt() == null) ? 0 : getMitangTotalProfitAmt().hashCode());
        result = prime * result + ((getTotalProfitAmt() == null) ? 0 : getTotalProfitAmt().hashCode());
        result = prime * result + ((getProfitProductType() == null) ? 0 : getProfitProductType().hashCode());
        result = prime * result + ((getCustomerType() == null) ? 0 : getCustomerType().hashCode());
        result = prime * result + ((getCalculateStatus() == null) ? 0 : getCalculateStatus().hashCode());
        result = prime * result + ((getPercent() == null) ? 0 : getPercent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", month=").append(month);
        sb.append(", totalCount=").append(totalCount);
        sb.append(", totalDayCount=").append(totalDayCount);
        sb.append(", totalTrxCount=").append(totalTrxCount);
        sb.append(", totalTrxAmt=").append(totalTrxAmt);
        sb.append(", mitangTotalTrxamt=").append(mitangTotalTrxamt);
        sb.append(", mitangTotalProfitAmt=").append(mitangTotalProfitAmt);
        sb.append(", totalProfitAmt=").append(totalProfitAmt);
        sb.append(", profitProductType=").append(profitProductType);
        sb.append(", customerType=").append(customerType);
        sb.append(", calculateStatus=").append(calculateStatus);
        sb.append(", percent=").append(percent);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}