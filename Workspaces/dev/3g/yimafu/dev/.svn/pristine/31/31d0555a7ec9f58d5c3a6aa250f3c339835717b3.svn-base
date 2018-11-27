package com.yeepay.g3.core.ymf.entity.customer;

import com.yeepay.g3.facade.ymf.enumtype.CustomerLevel;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import com.yeepay.g3.facade.ymf.validator.annotations.LengthValidator;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.RegValidator;

import java.io.Serializable;
import java.util.Date;

public class CustomerArgs implements Serializable {
    private Long id;

    @NotEmpty("商户编号")
    @LengthValidator(value="商户编号",length=50)
    private String customerNumber;
    @NotEmpty("商户名称")
    @LengthValidator(value="商户名称",length=100)
    private String customerName;
    private CustomerLevel customerLevel;

    private Date createTime;

    private Date updateTime;
    @NotEmpty("所属行业")
    private String industryType;
    private String customerType;
    @NotEmpty("支付方式")
    private String payTypeInfo;
    @NotEmpty("应用类型")
    private String appType;
    @NotEmpty("状态")
    private Status status;
    @NotEmpty("业务方")
    private Long businessId;
    private String customerLogo;
    @RegValidator(value = "订单有效期", reg="([1-9]\\d*)|([1-9])",msg = "订单有效期为非负整数!")
    private String validityPeriod;

    private String gratuityTmp;
    @NotEmpty("收款方式")
    private String collectTypeInfo;

    private String doucumentNo;
    @NotEmpty("结算产品")
    private BalanceType balanceProduct;

    public BalanceType getBalanceProduct() {
        return balanceProduct;
    }

    public void setBalanceProduct(BalanceType balanceProduct) {
        this.balanceProduct = balanceProduct;
    }

    public String getDoucumentNo() {
        return doucumentNo;
    }

    public void setDoucumentNo(String doucumentNo) {
        this.doucumentNo = doucumentNo;
    }


    public String getCollectTypeInfo() {
        return collectTypeInfo;
    }

    public void setCollectTypeInfo(String collectTypeInfo) {
        this.collectTypeInfo = collectTypeInfo;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(CustomerLevel customerLevel) {
        this.customerLevel = customerLevel;
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

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getPayTypeInfo() {
        return payTypeInfo;
    }

    public void setPayTypeInfo(String payTypeInfo) {
        this.payTypeInfo = payTypeInfo;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getBusinessId() {
        return businessId;
    }



    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
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
        CustomerArgs other = (CustomerArgs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerNumber() == null ? other.getCustomerNumber() == null : this.getCustomerNumber().equals(other.getCustomerNumber()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCustomerLevel() == null ? other.getCustomerLevel() == null : this.getCustomerLevel().equals(other.getCustomerLevel()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIndustryType() == null ? other.getIndustryType() == null : this.getIndustryType().equals(other.getIndustryType()))
            && (this.getCustomerType() == null ? other.getCustomerType() == null : this.getCustomerType().equals(other.getCustomerType()))
            && (this.getPayTypeInfo() == null ? other.getPayTypeInfo() == null : this.getPayTypeInfo().equals(other.getPayTypeInfo()))
            && (this.getAppType() == null ? other.getAppType() == null : this.getAppType().equals(other.getAppType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBusinessId() == null ? other.getBusinessId() == null : this.getBusinessId().equals(other.getBusinessId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerNumber() == null) ? 0 : getCustomerNumber().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCustomerLevel() == null) ? 0 : getCustomerLevel().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIndustryType() == null) ? 0 : getIndustryType().hashCode());
        result = prime * result + ((getCustomerType() == null) ? 0 : getCustomerType().hashCode());
        result = prime * result + ((getPayTypeInfo() == null) ? 0 : getPayTypeInfo().hashCode());
        result = prime * result + ((getAppType() == null) ? 0 : getAppType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CustomerArgs{" +
                "id=" + id +
                ", customerNumber='" + customerNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerLevel=" + customerLevel +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", industryType='" + industryType + '\'' +
                ", customerType='" + customerType + '\'' +
                ", payTypeInfo='" + payTypeInfo + '\'' +
                ", appType='" + appType + '\'' +
                ", status=" + status +
                ", businessId=" + businessId +
                ", customerLogo='" + customerLogo + '\'' +
                ", validityPeriod='" + validityPeriod + '\'' +
                ", gratuityTmp='" + gratuityTmp + '\'' +
                '}';
    }

    public String getCustomerLogo() {
        return customerLogo;
    }

    public void setCustomerLogo(String customerLogo) {
        this.customerLogo = customerLogo;
    }
    public String getGratuityTmp() {
        return gratuityTmp;
    }

    public void setGratuityTmp(String gratuityTmp) {
        this.gratuityTmp = gratuityTmp;
    }
}