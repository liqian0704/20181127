package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.common.laike.utils.RandomUtils;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.AppVersionBiz;
import com.yeepay.g3.core.laike.entity.AppVersionEntity;
import com.yeepay.g3.facade.laike.dto.AppVersionRequset;
import com.yeepay.g3.facade.laike.dto.AppVersionResponse;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.boss.CreateAppVersionRequest;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAppVersionRequest;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.FileTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Description: 版本管理和控制biz
 * Author: jiawen.huang
 * Date: 16/11/27
 * Time: 22:00
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AppVersionBizImpl extends AbstractBiz implements AppVersionBiz {

	@Override
	public AppVersionResponse findOne(BaseRequest request) {
		AppVersionEntity versionEntity = appVersionService.findById(request.getVersionId());
		return convertDTO(versionEntity);
	}

    @Override
	public AppVersionResponse checkNew(AppVersionRequset request) {
		AppVersionEntity versionEntity = appVersionService.findById(request.getVersionId());
		if (request.getIsManual().equals("1") || checkUpdatePermission(request)) {
			AppVersionEntity newVersionEntity = appVersionService.findNewByRoleAndPlatform(versionEntity.getPlatform(), versionEntity.getRoleType());
            if (null != newVersionEntity && versionEntity.getVersionCode().compareTo(
                    newVersionEntity.getVersionCode()) < 0) {
                return convertDTO(newVersionEntity);
            }
        }
        throw new LaikeSysException(ErrorCode.APP_DONT_NEED_UPDATE);
    }

	@Override
	public AppVersionResponse findOne(String id) {
		AppVersionEntity versionEntity = appVersionService.findById(id);
		return convertDTO(versionEntity);
	}

	@Override
	public AppVersionResponse save(CreateAppVersionRequest request) {
		AppVersionEntity currentVersion = appVersionService.
				findNewByRoleAndPlatform(request.getPlatform(), request.getRoleType());
		if (null != currentVersion && currentVersion.getVersionCode().compareTo(request.getVersionCode()) >= 0) {
			throw new LaikeSysException(ErrorCode.APP_VERSION_CODE_INVALID);
		}
		AppVersionEntity newAppVersion = new AppVersionEntity();
		newAppVersion.setRoleType(request.getRoleType());
		newAppVersion.setOperator(request.getOperator());
		newAppVersion.setDescription(request.getDescription());
		newAppVersion.setForceUpdate(false);
		newAppVersion.setPlatform(request.getPlatform());
		newAppVersion.setVersionCode(request.getVersionCode());
		appVersionService.save(newAppVersion);
		BaseRequest requestDTO = new BaseRequest();
		requestDTO.setVersionId(newAppVersion.getId());
		return findOne(requestDTO);
	}

	@Override
	public AppVersionResponse updateUrl(UpdateAppVersionRequest request) {
		AppVersionEntity versionEntity = appVersionService.findById(request.getId());
		versionEntity.setFileUrl(uploadFile(versionEntity, new ByteArrayInputStream(request.getFile())));
		versionEntity.setDescription(request.getDescription());
		versionEntity.setOperator(request.getOperator());
		versionEntity.setForceUpdate(request.getForceUpdate());
		appVersionService.updateUrl(versionEntity);
		BaseRequest requestDTO = new BaseRequest();
		requestDTO.setVersionId(request.getId());
		return findOne(requestDTO);
    }

    @Override
    public AppVersionResponse findNewByRoleAndPlatform(VersionPlatform versionPlatform, AppRoleEnum appRoleEnum) {
        return convertDTO(appVersionService.findNewByRoleAndPlatform(versionPlatform, appRoleEnum));
    }

	/**
	 * 上传和返回url
	 *
	 * @param versionEntity
	 * @param file
	 * @return
	 */
	private String uploadFile(AppVersionEntity versionEntity, InputStream file) {
		String fileURL = uploadFileService.upload(file, versionEntity.getPlatform().
				equals(VersionPlatform.ANDROID) ? FileTypeEnum.APK : FileTypeEnum.IPA);
		if (versionEntity.getPlatform().equals(VersionPlatform.IOS)) {
			versionEntity.setFileUrl(fileURL);
			InputStream plistInputStream = plistFileGenerateService.generatePlistFile(versionEntity);
			String plistURL = uploadFileService.upload(plistInputStream, FileTypeEnum.PLIST);
			fileURL = "itms-services://?action=download-manifest&url=" + plistURL;
		}
		return fileURL;
	}

	/**
	 * 当前是否能跟新
	 *
	 * @param request
	 * @return true 能；false 不能
	 */
	private boolean checkUpdatePermission(BaseRequest request) {
		if ((Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_FORCE_UPDATE)) {
			return true;
		}
		int suffix = Integer.valueOf(StringUtils.isBlank(request.getMemberNo()) ?
				RandomUtils.randomNumberString(1) : request.getMemberNo().substring(11)) + 1;//取末尾数字
		int token = Calendar.getInstance().get(Calendar.MINUTE) % 10 + 1;
		return token % suffix == 0;
	}

	private AppVersionResponse convertDTO(AppVersionEntity versionEntity) {
		AppVersionResponse response = new AppVersionResponse();
		response.setRoleType(versionEntity.getRoleType());
        response.setDescription(versionEntity.getDescription());
        response.setFileUrl(versionEntity.getFileUrl());
        response.setForceUpdate(versionEntity.getForceUpdate());
        response.setPlatform(versionEntity.getPlatform());
		response.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(versionEntity.getUpdateTime()));
		response.setVersionCode(versionEntity.getVersionCode());
		return response;
	}
}
