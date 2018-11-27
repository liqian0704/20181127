package Accounting_entity;

import com.miitang.facade.accounting.enumtype.BeneficiaryEnum;
import com.miitang.facade.accounting.enumtype.BillTypeEnum;
import com.miitang.facade.accounting.enumtype.FeePeriodEnum;
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
 * Time: 下午5:36
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class ParentMerchantPeriodRuleEntity implements Serializable {

    private static final long serialVersionUID = -4367919269317527017L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 运营商编号
     */
    private String parentMerchantNo;
    /**
     * 收款方
     */
    private BeneficiaryEnum beneficiary;
    /**
     * 账单类型
     */
    private BillTypeEnum billType;
    /**
     * 收费周期
     */
    private FeePeriodEnum feePeriod;
    /**
     * 周期时间
     */
    private String periodTime;
    /**
     * 生效开始时间
     */
    private Date effectiveStartDate;
    /**
     * 生效截止时间
     */
    private Date effectiveEndDate;
    /**
     * 状态
     */
    private StatusEnum status;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;

    private int automatic;


    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    private BigDecimal feeRate;

    public int getAutomatic() {
        return automatic;
    }

    public void setAutomatic(int automatic) {
        this.automatic = automatic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    @Getter(EnumToStringFunction.class)
    public BeneficiaryEnum getBeneficiary() {
        return beneficiary;
    }

    @Setter(StringToEnumFunction.class)
    public void setBeneficiary(BeneficiaryEnum beneficiary) {
        this.beneficiary = beneficiary;
    }

    @Getter(EnumToStringFunction.class)
    public BillTypeEnum getBillType() {
        return billType;
    }

    @Setter(StringToEnumFunction.class)
    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
    }

    @Getter(EnumToStringFunction.class)
    public FeePeriodEnum getFeePeriod() {
        return feePeriod;
    }

    @Setter(StringToEnumFunction.class)
    public void setFeePeriod(FeePeriodEnum feePeriod) {
        this.feePeriod = feePeriod;
    }

    public String getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(String periodTime) {
        this.periodTime = periodTime;
    }

    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    @Getter(EnumToStringFunction.class)
    public StatusEnum getStatus() {
        return status;
    }

    @Setter(StringToEnumFunction.class)
    public void setStatus(StatusEnum status) {
        this.status = status;
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
}
