package com.yeepay.g3.core.laike.entity;

import com.yeepay.g3.core.laike.cache.annotations.CacheKey;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;

import java.util.Date;

/**
 * Description: app版本实体
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 15:33
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@CacheKey(value = "APP_VERSION", key = "ID")
public class AppVersionEntity extends GenerateIDEntity {

	/**
	 * 版本供应用户
	 */
	private AppRoleEnum roleType;

	/**
	 * 手机平台
	 */
	private VersionPlatform platform;

	/**
	 * 前端展示版本号
	 */
	private String versionCode;

	/**
	 * 强制更新标志
	 */
	private Boolean forceUpdate;

	/**
	 * 下载路径
	 */
	private String fileUrl;

	/**
	 * 更新内容
	 */
	private String description;

	/**
	 * 创建者
	 */
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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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