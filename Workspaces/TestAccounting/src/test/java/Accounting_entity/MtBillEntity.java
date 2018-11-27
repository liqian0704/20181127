package Accounting_entity;

import com.miitang.facade.accounting.enumtype.DeductStatusEnum;
import com.miitang.facade.accounting.enumtype.StatusEnum;
import org.jfaster.mango.annotation.Getter;
import org.jfaster.mango.annotation.Setter;
import org.jfaster.mango.invoker.function.enums.EnumToStringFunction;
import org.jfaster.mango.invoker.function.enums.StringToEnumFunction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:
 * Author: xing.zhang-1
 * Date: 2018/1/19
 * Time: 下午6:23
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class MtBillEntity implements Serializable {

    private static final long serialVersionUID = -4367919269317527017L;
    /**
     * 主键
     */
    private String mtBillNo;

    /**
     * 账单号
     */
    private String totalBillNo;

    /**
     * 运营商编号
     */
    private String parentMerchantNo;
    /**
     * 累计交易金额
     */
    private BigDecimal totalTradeAmount;
    /**
     * 交易笔数
     */
    private Integer tradeNum;
    /**
     * 角色
     */
    private String role;
    /**
     * 费用类型
     */
    private String costType;
    /**
     * 税费
     */
    private String feeRate;
    /**
     * 发放金额
     */
    private String paymentAmount;
    /**
     * 发放状态
     */
    private DeductStatusEnum deductStatus;
    /**
     * 状态
     */
    private StatusEnum status;
    /**
     * 明细开始时间
     */
    private Date detailStartDate;
    /**
     * 明细截止时间
     */
    private Date detailEndDate;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 完成时间
     */
    private Date successDate;

    public String getTotalBillNo() {
        return totalBillNo;
    }

    public void setTotalBillNo(String totalBillNo) {
        this.totalBillNo = totalBillNo;
    }

    public String getMtBillNo() {
        return mtBillNo;
    }

    public void setMtBillNo(String mtBillNo) {
        this.mtBillNo = mtBillNo;
    }

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public BigDecimal getTotalTradeAmount() {
        return totalTradeAmount;
    }

    public void setTotalTradeAmount(BigDecimal totalTradeAmount) {
        this.totalTradeAmount = totalTradeAmount;
    }

    public Integer getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Integer tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Getter(EnumToStringFunction.class)
    public DeductStatusEnum getDeductStatus() {
        return deductStatus;
    }

    @Setter(StringToEnumFunction.class)
    public void setDeductStatus(DeductStatusEnum deductStatus) {
        this.deductStatus = deductStatus;
    }

    @Getter(EnumToStringFunction.class)
    public StatusEnum getStatus() {
        return status;
    }

    @Setter(StringToEnumFunction.class)
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getDetailStartDate() {
        return detailStartDate;
    }

    public void setDetailStartDate(Date detailStartDate) {
        this.detailStartDate = detailStartDate;
    }

    public Date getDetailEndDate() {
        return detailEndDate;
    }

    public void setDetailEndDate(Date detailEndDate) {
        this.detailEndDate = detailEndDate;
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

    public Date getSuccessDate() {
        return successDate;
    }

    public void setSuccessDate(Date successDate) {
        this.successDate = successDate;
    }
}
