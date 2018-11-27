package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;

/**
 * Description: app版本回参
 * Author: jiawen.huang
 * Date: 16/11/27
 * Time: 22:00
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AppVersionResponse extends BaseResponse {

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
     * 更新时间
     */
    private String updateTime;

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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
