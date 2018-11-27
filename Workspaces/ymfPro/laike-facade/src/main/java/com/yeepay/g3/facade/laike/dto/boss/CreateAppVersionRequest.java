package com.yeepay.g3.facade.laike.dto.boss;

import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 创建新版（后台）
 * Author: jiawen.huang
 * Date: 16/11/27
 * Time: 21:55
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class CreateAppVersionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 版本供应用户
	 */
	@NotEmpty
	private AppRoleEnum roleType;

	/**
	 * 手机平台
	 */
	@NotEmpty
	private VersionPlatform platform;

	/**
	 * 前端展示版本号
	 */
	@NotEmpty
	private String versionCode;

	/**
	 * 强制更新标志
	 */
	private Boolean forceUpdate;

	/**
	 * 更新内容
	 */
	private String description;

	/**
	 * 创建者
	 */
	@NotEmpty
	private String operator;

	/**
	 * 更新时间
	 */
	private Date updateTime;


	public AppRoleEnum getRoleType() {
		return roleType;
	}

	public void setRoleType(AppRoleEnum roleType) {
		this.roleType = roleType;
	}

	public VersionPlatform getPlatform() {
		return platform;
	}

	public void setPlatform(VersionPlatform platform) {
		this.platform = platform;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public Boolean getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
