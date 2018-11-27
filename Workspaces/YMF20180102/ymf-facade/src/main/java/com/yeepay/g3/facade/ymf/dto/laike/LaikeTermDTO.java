package com.yeepay.g3.facade.ymf.dto.laike;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;

import java.io.Serializable;

/**
 * Title: 来客终端DTO
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/15.
 */
public class LaikeTermDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -4367358846324225155L;

    /**
     * 终端SN号
     */
    private String snSerial;

    /**
     * 商户编号
     */
    private String customerNumber;

    /**
     * 终端厂商
     */
    private String manufacture;

    /**
     * 终端厂商显示名称
     */
    private String manufactureDisplayName;

    /**
     * 终端类型
     */
    private String termType;

    /**
     * 终端类型显示名称
     */
    private String termTypeDisplayName;

    public String getSnSerial() {
        return snSerial;
    }

    public void setSnSerial(String snSerial) {
        this.snSerial = snSerial;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getManufactureDisplayName() {
        return manufactureDisplayName;
    }

    public void setManufactureDisplayName(String manufactureDisplayName) {
        this.manufactureDisplayName = manufactureDisplayName;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getTermTypeDisplayName() {
        return termTypeDisplayName;
    }

    public void setTermTypeDisplayName(String termTypeDisplayName) {
        this.termTypeDisplayName = termTypeDisplayName;
    }
}
