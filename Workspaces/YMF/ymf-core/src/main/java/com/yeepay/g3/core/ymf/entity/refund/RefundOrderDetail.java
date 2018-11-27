package com.yeepay.g3.core.ymf.entity.refund;

import com.yeepay.g3.facade.ymf.enumtype.refund.RefundSource;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundStatus;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RefundOrderDetail implements Serializable {
    private Long id;

    private Integer version;

    private Long refundinfoId;

    private RefundSource refundSource;

    private String refundOrderId;

    private String refundRequestId;

    private String yeepayOrderId;

    private RefundType refundType;

    private BigDecimal trxAmount;

    private PaySource paySource;

    private BigDecimal refundAmount;

    private BigDecimal refundFee;

    private RefundStatus refundStatus;

    private String reasonCode;

    private Date createTime;

    private Date refundTime;

    private Date manualExecTime;

    private String manualOperator;

    private String refundHandleType;

    private String cause;

    private Date payTime;

    private String reasonMsg;

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

    public Long getRefundinfoId() {
        return refundinfoId;
    }

    public void setRefundinfoId(Long refundinfoId) {
        this.refundinfoId = refundinfoId;
    }

    public RefundSource getRefundSource() {
        return refundSource;
    }

    public void setRefundSource(RefundSource refundSource) {
        this.refundSource = refundSource;
    }

    public String getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(String refundOrderId) {
        this.refundOrderId = refundOrderId;
    }

    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
    }

    public RefundType getRefundType() {
        return refundType;
    }

    public void setRefundType(RefundType refundType) {
        this.refundType = refundType;
    }

    public BigDecimal getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(BigDecimal trxAmount) {
        this.trxAmount = trxAmount;
    }

    public PaySource getPaySource() {
        return paySource;
    }

    public void setPaySource(PaySource paySource) {
        this.paySource = paySource;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getManualExecTime() {
        return manualExecTime;
    }

    public void setManualExecTime(Date manualExecTime) {
        this.manualExecTime = manualExecTime;
    }

    public String getManualOperator() {
        return manualOperator;
    }

    public void setManualOperator(String manualOperator) {
        this.manualOperator = manualOperator;
    }

    public String getRefundHandleType() {
        return refundHandleType;
    }

    public void setRefundHandleType(String refundHandleType) {
        this.refundHandleType = refundHandleType;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getReasonMsg() {
        return reasonMsg;
    }

    public void setReasonMsg(String reasonMsg) {
        this.reasonMsg = reasonMsg;
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
        RefundOrderDetail other = (RefundOrderDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getRefundinfoId() == null ? other.getRefundinfoId() == null : this.getRefundinfoId().equals(other.getRefundinfoId()))
            && (this.getRefundSource() == null ? other.getRefundSource() == null : this.getRefundSource().equals(other.getRefundSource()))
            && (this.getRefundOrderId() == null ? other.getRefundOrderId() == null : this.getRefundOrderId().equals(other.getRefundOrderId()))
            && (this.getRefundRequestId() == null ? other.getRefundRequestId() == null : this.getRefundRequestId().equals(other.getRefundRequestId()))
            && (this.getYeepayOrderId() == null ? other.getYeepayOrderId() == null : this.getYeepayOrderId().equals(other.getYeepayOrderId()))
            && (this.getRefundType() == null ? other.getRefundType() == null : this.getRefundType().equals(other.getRefundType()))
            && (this.getTrxAmount() == null ? other.getTrxAmount() == null : this.getTrxAmount().equals(other.getTrxAmount()))
            && (this.getPaySource() == null ? other.getPaySource() == null : this.getPaySource().equals(other.getPaySource()))
            && (this.getRefundAmount() == null ? other.getRefundAmount() == null : this.getRefundAmount().equals(other.getRefundAmount()))
            && (this.getRefundFee() == null ? other.getRefundFee() == null : this.getRefundFee().equals(other.getRefundFee()))
            && (this.getRefundStatus() == null ? other.getRefundStatus() == null : this.getRefundStatus().equals(other.getRefundStatus()))
            && (this.getReasonCode() == null ? other.getReasonCode() == null : this.getReasonCode().equals(other.getReasonCode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getRefundTime() == null ? other.getRefundTime() == null : this.getRefundTime().equals(other.getRefundTime()))
            && (this.getManualExecTime() == null ? other.getManualExecTime() == null : this.getManualExecTime().equals(other.getManualExecTime()))
            && (this.getManualOperator() == null ? other.getManualOperator() == null : this.getManualOperator().equals(other.getManualOperator()))
            && (this.getRefundHandleType() == null ? other.getRefundHandleType() == null : this.getRefundHandleType().equals(other.getRefundHandleType()))
            && (this.getCause() == null ? other.getCause() == null : this.getCause().equals(other.getCause()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getReasonMsg() == null ? other.getReasonMsg() == null : this.getReasonMsg().equals(other.getReasonMsg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getRefundinfoId() == null) ? 0 : getRefundinfoId().hashCode());
        result = prime * result + ((getRefundSource() == null) ? 0 : getRefundSource().hashCode());
        result = prime * result + ((getRefundOrderId() == null) ? 0 : getRefundOrderId().hashCode());
        result = prime * result + ((getRefundRequestId() == null) ? 0 : getRefundRequestId().hashCode());
        result = prime * result + ((getYeepayOrderId() == null) ? 0 : getYeepayOrderId().hashCode());
        result = prime * result + ((getRefundType() == null) ? 0 : getRefundType().hashCode());
        result = prime * result + ((getTrxAmount() == null) ? 0 : getTrxAmount().hashCode());
        result = prime * result + ((getPaySource() == null) ? 0 : getPaySource().hashCode());
        result = prime * result + ((getRefundAmount() == null) ? 0 : getRefundAmount().hashCode());
        result = prime * result + ((getRefundFee() == null) ? 0 : getRefundFee().hashCode());
        result = prime * result + ((getRefundStatus() == null) ? 0 : getRefundStatus().hashCode());
        result = prime * result + ((getReasonCode() == null) ? 0 : getReasonCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getRefundTime() == null) ? 0 : getRefundTime().hashCode());
        result = prime * result + ((getManualExecTime() == null) ? 0 : getManualExecTime().hashCode());
        result = prime * result + ((getManualOperator() == null) ? 0 : getManualOperator().hashCode());
        result = prime * result + ((getRefundHandleType() == null) ? 0 : getRefundHandleType().hashCode());
        result = prime * result + ((getCause() == null) ? 0 : getCause().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getReasonMsg() == null) ? 0 : getReasonMsg().hashCode());
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
        sb.append(", refundinfoId=").append(refundinfoId);
        sb.append(", refundSource=").append(refundSource);
        sb.append(", refundOrderId=").append(refundOrderId);
        sb.append(", refundRequestId=").append(refundRequestId);
        sb.append(", yeepayOrderId=").append(yeepayOrderId);
        sb.append(", refundType=").append(refundType);
        sb.append(", trxAmount=").append(trxAmount);
        sb.append(", paySource=").append(paySource);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", refundFee=").append(refundFee);
        sb.append(", refundStatus=").append(refundStatus);
        sb.append(", reasonCode=").append(reasonCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", manualExecTime=").append(manualExecTime);
        sb.append(", manualOperator=").append(manualOperator);
        sb.append(", refundHandleType=").append(refundHandleType);
        sb.append(", cause=").append(cause);
        sb.append(", payTime=").append(payTime);
        sb.append(", reasonMsg=").append(reasonMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}