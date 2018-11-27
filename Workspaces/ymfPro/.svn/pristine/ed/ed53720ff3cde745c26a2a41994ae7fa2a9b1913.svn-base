package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.AppVersionEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.AppVersionService;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.UIDGenerator;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @Description: app版本service
 * @Author: jiawen.huang
 * @Date: 16/10/26
 * @Time: 下午2:21
 */
@Service
public class AppVersionServiceImpl extends AbstractService implements AppVersionService {

	@Override
	public void save(AppVersionEntity appVersionEntity) {
		try {
			String id = UIDGenerator.generateBizUID(appVersionRepository.nextSequence(), BizPrefixEnum.AP.getValue());
			appVersionEntity.setId(StringUtils.isBlank(appVersionEntity.getId()) ? id : appVersionEntity.getId());
			appVersionRepository.save(appVersionEntity);
		} catch (DuplicateKeyException e) {
			throw new LaikeSysException(ErrorCode.APP_VERSION_EXIST, e);
		}
	}

	@Override
	public AppVersionEntity findNewByRoleAndPlatform(VersionPlatform platformEnum, AppRoleEnum roleEnum) {
		return appVersionRepository.findNewByRoleAndPlatform(platformEnum, roleEnum);
	}

	@Override
	public void updateUrl(AppVersionEntity appVersionEntity) {
		Integer num = appVersionRepository.update(appVersionEntity);
		if (0 == num) {
			throw new LaikeSysException(ErrorCode.APP_VERSION_NO_EXIST);
		}
	}

	@Override
	public AppVersionEntity findById(String id) {
		AppVersionEntity appVersionEntity = appVersionRepository.findById(id);
		if (null == appVersionEntity) {
			throw new LaikeSysException(ErrorCode.APP_VERSION_NO_EXIST);
		}
		return appVersionEntity;
	}
}
