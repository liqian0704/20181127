package com.yeepay.g3.core.ymf.entity.common;

import com.yeepay.g3.facade.ymf.dto.common.PageParam;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志args
 */
public class LogArgs extends PageParam implements Serializable {

    private Long id;

    private Date createTime;

    private String entityName;

    private OperateType optType;

    private String customerNumber;

    private String operatorName;

    private String description;

    private Long proceedTime;

    private String bizType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public OperateType getOptType() {
        return optType;
    }

    public void setOptType(OperateType optType) {
        this.optType = optType;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProceedTime() {
        return proceedTime;
    }

    public void setProceedTime(Long proceedTime) {
        this.proceedTime = proceedTime;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", entityName=").append(entityName);
        sb.append(", optType=").append(optType);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", description=").append(description);
        sb.append(", proceedTime=").append(proceedTime);
        sb.append(", bizType=").append(bizType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}