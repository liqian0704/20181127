package com.yeepay.g3.core.laike.service.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.ResourcesUtils;
import com.yeepay.g3.core.laike.entity.AppVersionEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.PlistFileGenerateService;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/2/17
 * Time: 16:10
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class PlistFileGenerateServiceImpl extends AbstractService implements PlistFileGenerateService {

	@Override
	public InputStream generatePlistFile(AppVersionEntity appVersionEntity) {
		try {
			String pFileName = appVersionEntity.getRoleType().equals(AppRoleEnum.ALLIANCE) ?
					"alliance" : appVersionEntity.getRoleType().equals(AppRoleEnum.ALLIANCE_TEST) ?
					"alliance" : "liker";
			String basePlist = ResourcesUtils.readResource("", pFileName, ".plist");
			Map<String, String> data = Maps.newHashMap();
			data.put("fileUrl", appVersionEntity.getFileUrl());//原来的ipa地址
			data.put("versionCode", appVersionEntity.getVersionCode());
			String plistContent = ResourcesUtils.replaceResource(basePlist, data);
			return new ByteArrayInputStream(plistContent.getBytes("UTF-8"));
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.REPLACE_PLIST_EXCEPTION, e);
		}
	}
}
