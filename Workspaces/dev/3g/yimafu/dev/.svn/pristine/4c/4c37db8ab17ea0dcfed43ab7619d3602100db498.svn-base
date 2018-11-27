package com.yeepay.g3.core.ymf.entity.order;

import com.yeepay.g3.facade.ymf.enumtype.common.CardType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.SettleStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Payment implements Serializable {
    private Long id;

    private Long version;

    private Long orderId;

    private String customerNumber;

    private String customerOrderId;

    private PaySource paySource;

    private Date createTime;

    private Date expireTime;

    private String payUrl;

    private Date payRequestTime;

    private Date payTime;

    private Date channelPayTime;

    private PaymentStatus payStatus;

    private SettleStatus settleStatus;

    private Date settleTime;

    private String backUrl;

    private String bankOrderId;

    private String yeepayOrderId;

    private String outOrderId;

    private BigDecimal trxAmt;

    private BigDecimal realAmt;

    private BigDecimal refundAmt;

    private BigDecimal fee;

    private TrxType trxType;

    private String openId;

    private String bankType;

    private String bankName;

    private String cardNo;

    private CardType cardType;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public PaySource getPaySource() {
        return paySource;
    }

    public void setPaySource(PaySource paySource) {
        this.paySource = paySource;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public Date getPayRequestTime() {
        return payRequestTime;
    }

    public void setPayRequestTime(Date payRequestTime) {
        this.payRequestTime = payRequestTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getChannelPayTime() {
        return channelPayTime;
    }

    public void setChannelPayTime(Date channelPayTime) {
        this.channelPayTime = channelPayTime;
    }

    public PaymentStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PaymentStatus payStatus) {
        this.payStatus = payStatus;
    }

    public SettleStatus getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(SettleStatus settleStatus) {
        this.settleStatus = settleStatus;
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getBankOrderId() {
        return bankOrderId;
    }

    public void setBankOrderId(String bankOrderId) {
        this.bankOrderId = bankOrderId;
    }

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
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

    public TrxType getTrxType() {
        return trxType;
    }

    public void setTrxType(TrxType trxType) {
        this.trxType = trxType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
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
        Payment other = (Payment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getCustomerNumber() == null ? other.getCustomerNumber() == null : this.getCustomerNumber().equals(other.getCustomerNumber()))
            && (this.getCustomerOrderId() == null ? other.getCustomerOrderId() == null : this.getCustomerOrderId().equals(other.getCustomerOrderId()))
            && (this.getPaySource() == null ? other.getPaySource() == null : this.getPaySource().equals(other.getPaySource()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getPayUrl() == null ? other.getPayUrl() == null : this.getPayUrl().equals(other.getPayUrl()))
            && (this.getPayRequestTime() == null ? other.getPayRequestTime() == null : this.getPayRequestTime().equals(other.getPayRequestTime()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getChannelPayTime() == null ? other.getChannelPayTime() == null : this.getChannelPayTime().equals(other.getChannelPayTime()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getSettleTime() == null ? other.getSettleTime() == null : this.getSettleTime().equals(other.getSettleTime()))
            && (this.getBackUrl() == null ? other.getBackUrl() == null : this.getBackUrl().equals(other.getBackUrl()))
            && (this.getBankOrderId() == null ? other.getBankOrderId() == null : this.getBankOrderId().equals(other.getBankOrderId()))
            && (this.getYeepayOrderId() == null ? other.getYeepayOrderId() == null : this.getYeepayOrderId().equals(other.getYeepayOrderId()))
            && (this.getOutOrderId() == null ? other.getOutOrderId() == null : this.getOutOrderId().equals(other.getOutOrderId()))
            && (this.getTrxAmt() == null ? other.getTrxAmt() == null : this.getTrxAmt().equals(other.getTrxAmt()))
            && (this.getRealAmt() == null ? other.getRealAmt() == null : this.getRealAmt().equals(other.getRealAmt()))
            && (this.getRefundAmt() == null ? other.getRefundAmt() == null : this.getRefundAmt().equals(other.getRefundAmt()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getTrxType() == null ? other.getTrxType() == null : this.getTrxType().equals(other.getTrxType()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getBankType() == null ? other.getBankType() == null : this.getBankType().equals(other.getBankType()))
            && (this.getBankName() == null ? other.getBankName() == null : this.getBankName().equals(other.getBankName()))
            && (this.getCardNo() == null ? other.getCardNo() == null : this.getCardNo().equals(other.getCardNo()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getCustomerNumber() == null) ? 0 : getCustomerNumber().hashCode());
        result = prime * result + ((getCustomerOrderId() == null) ? 0 : getCustomerOrderId().hashCode());
        result = prime * result + ((getPaySource() == null) ? 0 : getPaySource().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getPayUrl() == null) ? 0 : getPayUrl().hashCode());
        result = prime * result + ((getPayRequestTime() == null) ? 0 : getPayRequestTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getChannelPayTime() == null) ? 0 : getChannelPayTime().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getSettleTime() == null) ? 0 : getSettleTime().hashCode());
        result = prime * result + ((getBackUrl() == null) ? 0 : getBackUrl().hashCode());
        result = prime * result + ((getBankOrderId() == null) ? 0 : getBankOrderId().hashCode());
        result = prime * result + ((getYeepayOrderId() == null) ? 0 : getYeepayOrderId().hashCode());
        result = prime * result + ((getOutOrderId() == null) ? 0 : getOutOrderId().hashCode());
        result = prime * result + ((getTrxAmt() == null) ? 0 : getTrxAmt().hashCode());
        result = prime * result + ((getRealAmt() == null) ? 0 : getRealAmt().hashCode());
        result = prime * result + ((getRefundAmt() == null) ? 0 : getRefundAmt().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getTrxType() == null) ? 0 : getTrxType().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getBankType() == null) ? 0 : getBankType().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        result = prime * result + ((getCardNo() == null) ? 0 : getCardNo().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
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
        sb.append(", orderId=").append(orderId);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", customerOrderId=").append(customerOrderId);
        sb.append(", paySource=").append(paySource);
        sb.append(", createTime=").append(createTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", payUrl=").append(payUrl);
        sb.append(", payRequestTime=").append(payRequestTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", channelPayTime=").append(channelPayTime);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", settleStatus=").append(settleStatus);
        sb.append(", settleTime=").append(settleTime);
        sb.append(", backUrl=").append(backUrl);
        sb.append(", bankOrderId=").append(bankOrderId);
        sb.append(", yeepayOrderId=").append(yeepayOrderId);
        sb.append(", outOrderId=").append(outOrderId);
        sb.append(", trxAmt=").append(trxAmt);
        sb.append(", realAmt=").append(realAmt);
        sb.append(", refundAmt=").append(refundAmt);
        sb.append(", fee=").append(fee);
        sb.append(", trxType=").append(trxType);
        sb.append(", openId=").append(openId);
        sb.append(", bankType=").append(bankType);
        sb.append(", bankName=").append(bankName);
        sb.append(", cardNo=").append(cardNo);
        sb.append(", cardType=").append(cardType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}