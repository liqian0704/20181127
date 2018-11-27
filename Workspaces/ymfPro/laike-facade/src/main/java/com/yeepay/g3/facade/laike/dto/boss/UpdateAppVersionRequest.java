package com.yeepay.g3.facade.laike.dto.boss;

import java.io.Serializable;

/**
 * Description: 上传安装包url
 * Author: jiawen.huang
 * Date: 16/11/27
 * Time: 22:00
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class UpdateAppVersionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 强制更新标志
	 */
	private Boolean forceUpdate;

	/**
	 * 安装文件
	 */
	private byte[] file;

	/**
	 * 更新内容
	 */
	private String description;

	/**
	 * 创建者
	 */
	private String operator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Boolean getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
}
