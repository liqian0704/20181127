package com.yeepay.g3.facade.laike.facade;

import com.yeepay.g3.facade.laike.dto.AppVersionRequset;
import com.yeepay.g3.facade.laike.dto.AppVersionResponse;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.boss.CreateAppVersionRequest;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAppVersionRequest;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;

/**
 * Description: 版本管理facade
 * Author: jiawen.huang
 * Date: 16/11/28
 * Time: 10:31
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppVersionFacade {

	/**
	 * 根据版本id查询
	 *
	 * @param request
	 * @return
	 */
	AppVersionResponse findOne(BaseRequest request);

	/**
	 * 查询是否有新版
	 *
	 * @param request
	 * @return
	 */
	@Deprecated
	AppVersionResponse checkNew(AppVersionRequset request);

	/**
	 * 后台根据id查询
	 *
	 * @param id
	 * @return
	 */
	AppVersionResponse findOne(String id);

	/**
	 * 创建新版
	 *
	 * @param request
	 */
	AppVersionResponse save(CreateAppVersionRequest request);

	/**
     * 更新url、操作员等上传信息
     *
	 * @param request
	 */
	AppVersionResponse updateUrl(UpdateAppVersionRequest request);

    /**
     * 根据平台和app版本版本查询
     *
     * @return
     */
    AppVersionResponse findNewByRoleAndPlatform(VersionPlatform versionPlatform, AppRoleEnum appRoleEnum);
}
