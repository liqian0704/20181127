package com.yeepay.g3.core.ymf.entity.profit;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Profit implements Serializable {
    private Long id;

    private String uniqueId;

    private Long dayCount;

    private String month;

    private String sale;

    private String customerNumber;

    private String agentNumber;

    private Long totalTrxCount;

    private BigDecimal trxAmt;

    private BigDecimal profitAmt;

    private String salesProductCode;

    private String productCode;

    private ProfitProductTypeEnum profitProductType;

    private CustomerTypeEnum customerType;

    private BigDecimal mitangTrxamt;

    private BigDecimal mitangProfitAmt;

    private BigDecimal percent;

    private Status calculateStatus;

    private Long summationId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Long getDayCount() {
        return dayCount;
    }

    public void setDayCount(Long dayCount) {
        this.dayCount = dayCount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
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

    public Long getTotalTrxCount() {
        return totalTrxCount;
    }

    public void setTotalTrxCount(Long totalTrxCount) {
        this.totalTrxCount = totalTrxCount;
    }

    public BigDecimal getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(BigDecimal trxAmt) {
        this.trxAmt = trxAmt;
    }

    public BigDecimal getProfitAmt() {
        return profitAmt;
    }

    public void setProfitAmt(BigDecimal profitAmt) {
        this.profitAmt = profitAmt;
    }

    public String getSalesProductCode() {
        return salesProductCode;
    }

    public void setSalesProductCode(String salesProductCode) {
        this.salesProductCode = salesProductCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public BigDecimal getMitangTrxamt() {
        return mitangTrxamt;
    }

    public void setMitangTrxamt(BigDecimal mitangTrxamt) {
        this.mitangTrxamt = mitangTrxamt;
    }

    public BigDecimal getMitangProfitAmt() {
        return mitangProfitAmt;
    }

    public void setMitangProfitAmt(BigDecimal mitangProfitAmt) {
        this.mitangProfitAmt = mitangProfitAmt;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Status getCalculateStatus() {
        return calculateStatus;
    }

    public void setCalculateStatus(Status calculateStatus) {
        this.calculateStatus = calculateStatus;
    }

    public Long getSummationId() {
        return summationId;
    }

    public void setSummationId(Long summationId) {
        this.summationId = summationId;
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
        Profit other = (Profit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUniqueId() == null ? other.getUniqueId() == null : this.getUniqueId().equals(other.getUniqueId()))
            && (this.getDayCount() == null ? other.getDayCount() == null : this.getDayCount().equals(other.getDayCount()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getSale() == null ? other.getSale() == null : this.getSale().equals(other.getSale()))
            && (this.getCustomerNumber() == null ? other.getCustomerNumber() == null : this.getCustomerNumber().equals(other.getCustomerNumber()))
            && (this.getAgentNumber() == null ? other.getAgentNumber() == null : this.getAgentNumber().equals(other.getAgentNumber()))
            && (this.getTotalTrxCount() == null ? other.getTotalTrxCount() == null : this.getTotalTrxCount().equals(other.getTotalTrxCount()))
            && (this.getTrxAmt() == null ? other.getTrxAmt() == null : this.getTrxAmt().equals(other.getTrxAmt()))
            && (this.getProfitAmt() == null ? other.getProfitAmt() == null : this.getProfitAmt().equals(other.getProfitAmt()))
            && (this.getSalesProductCode() == null ? other.getSalesProductCode() == null : this.getSalesProductCode().equals(other.getSalesProductCode()))
            && (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode()))
            && (this.getProfitProductType() == null ? other.getProfitProductType() == null : this.getProfitProductType().equals(other.getProfitProductType()))
            && (this.getCustomerType() == null ? other.getCustomerType() == null : this.getCustomerType().equals(other.getCustomerType()))
            && (this.getMitangTrxamt() == null ? other.getMitangTrxamt() == null : this.getMitangTrxamt().equals(other.getMitangTrxamt()))
            && (this.getMitangProfitAmt() == null ? other.getMitangProfitAmt() == null : this.getMitangProfitAmt().equals(other.getMitangProfitAmt()))
            && (this.getPercent() == null ? other.getPercent() == null : this.getPercent().equals(other.getPercent()))
            && (this.getCalculateStatus() == null ? other.getCalculateStatus() == null : this.getCalculateStatus().equals(other.getCalculateStatus()))
            && (this.getSummationId() == null ? other.getSummationId() == null : this.getSummationId().equals(other.getSummationId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUniqueId() == null) ? 0 : getUniqueId().hashCode());
        result = prime * result + ((getDayCount() == null) ? 0 : getDayCount().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getSale() == null) ? 0 : getSale().hashCode());
        result = prime * result + ((getCustomerNumber() == null) ? 0 : getCustomerNumber().hashCode());
        result = prime * result + ((getAgentNumber() == null) ? 0 : getAgentNumber().hashCode());
        result = prime * result + ((getTotalTrxCount() == null) ? 0 : getTotalTrxCount().hashCode());
        result = prime * result + ((getTrxAmt() == null) ? 0 : getTrxAmt().hashCode());
        result = prime * result + ((getProfitAmt() == null) ? 0 : getProfitAmt().hashCode());
        result = prime * result + ((getSalesProductCode() == null) ? 0 : getSalesProductCode().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getProfitProductType() == null) ? 0 : getProfitProductType().hashCode());
        result = prime * result + ((getCustomerType() == null) ? 0 : getCustomerType().hashCode());
        result = prime * result + ((getMitangTrxamt() == null) ? 0 : getMitangTrxamt().hashCode());
        result = prime * result + ((getMitangProfitAmt() == null) ? 0 : getMitangProfitAmt().hashCode());
        result = prime * result + ((getPercent() == null) ? 0 : getPercent().hashCode());
        result = prime * result + ((getCalculateStatus() == null) ? 0 : getCalculateStatus().hashCode());
        result = prime * result + ((getSummationId() == null) ? 0 : getSummationId().hashCode());
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
        sb.append(", uniqueId=").append(uniqueId);
        sb.append(", dayCount=").append(dayCount);
        sb.append(", month=").append(month);
        sb.append(", sale=").append(sale);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", agentNumber=").append(agentNumber);
        sb.append(", totalTrxCount=").append(totalTrxCount);
        sb.append(", trxAmt=").append(trxAmt);
        sb.append(", profitAmt=").append(profitAmt);
        sb.append(", salesProductCode=").append(salesProductCode);
        sb.append(", productCode=").append(productCode);
        sb.append(", profitProductType=").append(profitProductType);
        sb.append(", customerType=").append(customerType);
        sb.append(", mitangTrxamt=").append(mitangTrxamt);
        sb.append(", mitangProfitAmt=").append(mitangProfitAmt);
        sb.append(", percent=").append(percent);
        sb.append(", calculateStatus=").append(calculateStatus);
        sb.append(", summationId=").append(summationId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}