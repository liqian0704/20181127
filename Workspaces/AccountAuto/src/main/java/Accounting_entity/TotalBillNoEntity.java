package Accounting_entity;

import com.miitang.facade.accounting.enumtype.BeneficiaryEnum;
import com.miitang.facade.accounting.enumtype.BillTypeEnum;

import java.util.Date;

/**
 * 类名称: TotalBillNoEntity <br>
 * 类描述: 账单号<br>
 *
 * @author: hua.jin
 * @since: 2018/3/6 上午11:13
 * @version: 1.0.0
 */
public class TotalBillNoEntity {
    private Long id;

    private Integer optimistic;

    private String parentMerchantNo;

    private String parentMerchantName;

    private String totalBillNo;

    private BillTypeEnum billType;

    private BeneficiaryEnum beneficiary;

    private boolean active;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    private Date updateDate;

    private String payCompany;

    private String ruleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
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

    public String getTotalBillNo() {
        return totalBillNo;
    }

    public void setTotalBillNo(String totalBillNo) {
        this.totalBillNo = totalBillNo == null ? null : totalBillNo.trim();
    }

    public BillTypeEnum getBillType() {
        return billType;
    }

    public void setBillType(BillTypeEnum billType) {
        this.billType = billType;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(String payCompany) {
        this.payCompany = payCompany;
    }

    public BeneficiaryEnum getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryEnum beneficiary) {
        this.beneficiary = beneficiary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}