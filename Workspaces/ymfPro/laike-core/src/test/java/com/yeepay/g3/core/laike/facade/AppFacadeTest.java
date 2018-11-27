package com.yeepay.g3.core.laike.facade;

import com.google.common.io.Files;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.AppVersionRequset;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
import com.yeepay.g3.facade.laike.dto.boss.CreateAppVersionRequest;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAppVersionRequest;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import com.yeepay.g3.facade.laike.facade.AppVersionFacade;
import com.yeepay.g3.facade.laike.facade.LikerCacheFacade;
import com.yeepay.g3.facade.laike.facade.app.VersionFacade;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.io.File;
import java.io.IOException;

/**
 * Description:userAuthorityFacade
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AppFacadeTest extends BaseTest {

	@Autowired
	private AppVersionFacade appVersionFacade;

	@Autowired
	private VersionFacade versionFacade;

	@Autowired
	private LikerCacheFacade likerCacheFacade;

	@Test
	public void findOne() {
		BaseRequest request = new BaseRequest();
		request.setVersionId("APA17031467189521");
		BaseResponse response = appVersionFacade.findOne(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Test
	public void clean() {
		likerCacheFacade.cleanAllCache();
	}

	@Test
	public void checkNew() {
        AppVersionRequset request = new AppVersionRequset();
        request.setVersionId("APA17011218952369");
        BaseResponse response = versionFacade.checkNew(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Rollback(false)
	@Test
	public void save() {
		CreateAppVersionRequest request = new CreateAppVersionRequest();
		request.setOperator("zhenzheng.zhang2");
		request.setDescription("测试IOS版本");
        //request.setForceUpdate(false);
        // unique index
        request.setRoleType(AppRoleEnum.WORKER);
		request.setPlatform(VersionPlatform.IOS);
        request.setVersionCode("1.0.4");
        BaseResponse response = appVersionFacade.save(request);
        Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	//	@Rollback(false)
	@Test
	public void updateUrl() throws IOException {
		UpdateAppVersionRequest request = new UpdateAppVersionRequest();
		request.setId("APA17011271895237");
		request.setDescription("单元测试上传包");
		request.setOperator("jiawen.huang");
        File file = new File("/Users/yp-tc-m-7116/Desktop/考试");
        request.setFile(Files.toByteArray(file));
        BaseResponse response = appVersionFacade.updateUrl(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}
}
