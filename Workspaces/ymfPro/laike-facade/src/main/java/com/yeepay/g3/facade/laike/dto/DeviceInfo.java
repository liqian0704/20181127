package com.yeepay.g3.facade.laike.dto;

import java.io.Serializable;

/**
 * Description: 设备详情
 * Author: jiawen.huang
 * Date: 17/3/16
 * Time: 17:34
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class DeviceInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String snSerial;

	private String manufacture;

	private String manufactureDisplayName;

	private String termType;

	private String termTypeDisplayName;

	public String getSnSerial() {
		return snSerial;
	}

	public void setSnSerial(String snSerial) {
		this.snSerial = snSerial;
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
