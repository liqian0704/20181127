package Accounting_entity;

import com.miitang.facade.accounting.enumtype.OrderFeeStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

public class PayComOtherFeeRecordEntity {
    private String uniqueComOtherNo;

    private Integer optimistic;

    private String requestNo;

    private String parentMerchantNo;

    private String parentMerchantName;

    private String payCompany;

    private String productCode;

    private BigDecimal feeAmount;

    private OrderFeeStatusEnum feeStatus;

    private Date requestDate;

    private Date createDate;

    private Date updateDate;

    private Long payCompanyFeeFormulaId;

    private BigDecimal orderAmount;

    public String getUniqueComOtherNo() {
        return uniqueComOtherNo;
    }

    public void setUniqueComOtherNo(String uniqueComOtherNo) {
        this.uniqueComOtherNo = uniqueComOtherNo == null ? null : uniqueComOtherNo.trim();
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo == null ? null : requestNo.trim();
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

    public String getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(String payCompany) {
        this.payCompany = payCompany == null ? null : payCompany.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public OrderFeeStatusEnum getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(OrderFeeStatusEnum feeStatus) {
        this.feeStatus = feeStatus;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
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

    public Long getPayCompanyFeeFormulaId() {
        return payCompanyFeeFormulaId;
    }

    public void setPayCompanyFeeFormulaId(Long payCompanyFeeFormulaId) {
        this.payCompanyFeeFormulaId = payCompanyFeeFormulaId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
}