package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.AppVersionRequset;
import com.yeepay.g3.facade.laike.dto.AppVersionResponse;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.boss.CreateAppVersionRequest;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAppVersionRequest;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import com.yeepay.g3.facade.laike.facade.AppVersionFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 版本管理facade实现
 * Author: jiawen.huang
 * Date: 16/11/28
 * Time: 10:32
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AppVersionFacadeImpl extends AbstractFacade implements AppVersionFacade {

	@Override
	public AppVersionResponse findOne(BaseRequest request) {
		return appVersionBiz.findOne(request);
	}

	@Override
	public AppVersionResponse checkNew(AppVersionRequset request) {
		return appVersionBiz.checkNew(request);
	}

	@Override
	public AppVersionResponse findOne(String id) {
		return appVersionBiz.findOne(id);
	}

	@Override
	public AppVersionResponse save(CreateAppVersionRequest request) {
		return appVersionBiz.save(request);
	}

	@Override
	public AppVersionResponse updateUrl(UpdateAppVersionRequest request) {
		return appVersionBiz.updateUrl(request);
	}

	@Override
	public AppVersionResponse findNewByRoleAndPlatform(VersionPlatform versionPlatform, AppRoleEnum appRoleEnum) {
		return appVersionBiz.findNewByRoleAndPlatform(versionPlatform, appRoleEnum);
	}
}
