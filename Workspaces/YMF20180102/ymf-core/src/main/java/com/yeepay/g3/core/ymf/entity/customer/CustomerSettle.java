package com.yeepay.g3.core.ymf.entity.customer;

import com.yeepay.g3.facade.ymf.enumtype.trade.SettleType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustomerSettle implements Serializable {
    private Long id;

    private Long version;

    private String customerNumber;

    private Date trxDate;

    private BigDecimal realAmount;

    private Integer settleTimes;

    private Date createTime;

    private Date updateTime;

    private BigDecimal trxFee;

    private BigDecimal settleFee;

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

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Integer getSettleTimes() {
        return settleTimes;
    }

    public void setSettleTimes(Integer settleTimes) {
        this.settleTimes = settleTimes;
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

    public BigDecimal getTrxFee() {
        return trxFee;
    }

    public void setTrxFee(BigDecimal trxFee) {
        this.trxFee = trxFee;
    }

    public BigDecimal getSettleFee() {
        return settleFee;
    }

    public void setSettleFee(BigDecimal settleFee) {
        this.settleFee = settleFee;
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
        sb.append(", trxDate=").append(trxDate);
        sb.append(", realAmount=").append(realAmount);
        sb.append(", trxFee=").append(trxFee);
        sb.append(", settleFee=").append(settleFee);
        sb.append(", settleTimes=").append(settleTimes);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}