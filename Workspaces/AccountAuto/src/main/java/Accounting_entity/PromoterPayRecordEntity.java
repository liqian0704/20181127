package Accounting_entity;

import com.miitang.facade.accounting.enumtype.OrderFeeStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 推广人打款
 *
 * @author huahua.jin
 * @date 2017-12-22 10:30:00
 */
public class PromoterPayRecordEntity {
    private String uniquePromoterPayNo;

    private Integer optimistic;

    private String totalBillNo;

    private String promoterNo;

    private String parentMerchantNo;

    private String parentMerchantName;

    private BigDecimal profitAmount;

    private BigDecimal profitFee;

    private OrderFeeStatusEnum feeStatus;

    private Date createDate;

    private Date updateDate;

    public String getUniquePromoterPayNo() {
        return uniquePromoterPayNo;
    }

    public void setUniquePromoterPayNo(String uniquePromoterPayNo) {
        this.uniquePromoterPayNo = uniquePromoterPayNo == null ? null : uniquePromoterPayNo.trim();
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public String getTotalBillNo() {
        return totalBillNo;
    }

    public void setTotalBillNo(String totalBillNo) {
        this.totalBillNo = totalBillNo == null ? null : totalBillNo.trim();
    }

    public String getPromoterNo() {
        return promoterNo;
    }

    public void setPromoterNo(String promoterNo) {
        this.promoterNo = promoterNo == null ? null : promoterNo.trim();
    }

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo == null ? null : parentMerchantNo.trim();
    }

    public String getParentMerchantName() {
        return parentMerchantName;
    }

    public void setParentMerchantName(String parentMerchantName) {
        this.parentMerchantName = parentMerchantName == null ? null : parentMerchantName.trim();
    }

    public BigDecimal getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(BigDecimal profitAmount) {
        this.profitAmount = profitAmount;
    }

    public BigDecimal getProfitFee() {
        return profitFee;
    }

    public void setProfitFee(BigDecimal profitFee) {
        this.profitFee = profitFee;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public OrderFeeStatusEnum getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(OrderFeeStatusEnum feeStatus) {
        this.feeStatus = feeStatus;
    }
}