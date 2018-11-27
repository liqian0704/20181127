package Accounting_entity;

import com.miitang.facade.accounting.enumtype.BeneficiaryEnum;
import com.miitang.facade.accounting.enumtype.BillStatusEnum;
import com.miitang.facade.accounting.enumtype.BillTypeEnum;
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
 * Time: 下午6:35
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class TotalBillEntity implements Serializable {

    /**
     * 总账单编号
     */
    private String totalBillNo;
    /**
     * 运营商编号
     */
    private String parentMerchantNo;
    /**
     * 运营商名称
     */
    private String parentMerchantName;
    /**
     * 账单号
     */
    private String uniqueBillNo;
    /**
     * 账单类型
     */
    private BillTypeEnum billType;
    /**
     * 收款方
     */
    private BeneficiaryEnum beneficiary;
    /**
     * 账单金额
     */
    private BigDecimal billAmount;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date successDate;
    /**
     * 状态
     */
    private BillStatusEnum billStatus;

    private String errorCode;

    private String errorMessage;

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public String getParentMerchantName() {
        return parentMerchantName;
    }

    public void setParentMerchantName(String parentMerchantName) {
        this.parentMerchantName = parentMerchantName;
    }

    public String getUniqueBillNo() {
        return uniqueBillNo;
    }

    public void setUniqueBillNo(String uniqueBillNo) {
        this.uniqueBillNo = uniqueBillNo;
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
    public BeneficiaryEnum getBeneficiary() {
        return beneficiary;
    }

    @Setter(StringToEnumFunction.class)
    public void setBeneficiary(BeneficiaryEnum beneficiary) {
        this.beneficiary = beneficiary;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSuccessDate() {
        return successDate;
    }

    public void setSuccessDate(Date successDate) {
        this.successDate = successDate;
    }

    public String getTotalBillNo() {
        return totalBillNo;
    }

    public void setTotalBillNo(String totalBillNo) {
        this.totalBillNo = totalBillNo;
    }

    @Getter(EnumToStringFunction.class)
    public BillStatusEnum getBillStatus() {
        return billStatus;
    }

    @Setter(StringToEnumFunction.class)
    public void setBillStatus(BillStatusEnum billStatus) {
        this.billStatus = billStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
