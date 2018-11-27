package com.yeepay.g3.core.ymf.support;

import com.yeepay.g3.facade.ymf.enumtype.OperateType;

/**
 * Title: 增删改service层操作的实体
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/16.
 */
public class OperateEntity<T> {

    private String customerNumber;

    private String operator;

    private T entity;

    private OperateType operateType;

    /**
     *
     * @param operator 操作人
     * @param entity 操作实体
     * @param operateType 操作类型
     */
    public OperateEntity(String operator, T entity, OperateType operateType) {
        this.operator = operator;
        this.entity = entity;
        this.operateType = operateType;
    }

    /**
     *
     * @param operator 操作人
     * @param entity 操作实体
     * @param operateType 操作类型
     * @param customerNumber 商户编号
     */
    public OperateEntity(String operator, T entity, OperateType operateType, String customerNumber) {
        this.operator = operator;
        this.entity = entity;
        this.operateType = operateType;
        this.customerNumber = customerNumber;
    }


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }
}
