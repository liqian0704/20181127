package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.AppVersionEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import com.yeepay.g3.utils.common.UIDGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Description: VersionTest
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class VersionTest extends BaseTest {

	@Autowired
	private AppVersionRepository appVersionRepository;

	@Rollback(false)
	@Test
	public void test() {
		AppVersionEntity versionEntity = new AppVersionEntity();
		String id = UIDGenerator.generateBizUID(appVersionRepository.nextSequence(), BizPrefixEnum.AP.getValue());
		versionEntity.setId(id);
		versionEntity.setOperator("zhenzheng.zhang");
		versionEntity.setDescription("测试安卓版本2");
		versionEntity.setFileUrl("http://www.baidu.com");
		versionEntity.setForceUpdate(false);
		// unique index
		versionEntity.setRoleType(AppRoleEnum.WORKER);
		versionEntity.setPlatform(VersionPlatform.ANDROID);
		versionEntity.setVersionCode("1.0.1");
		// unique index
		int num = appVersionRepository.save(versionEntity);
		Assert.assertEquals(num, 1);
	}
}
