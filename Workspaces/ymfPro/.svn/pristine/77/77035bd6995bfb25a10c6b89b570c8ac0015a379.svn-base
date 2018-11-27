package com.yeepay.g3.core.laike.facade;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.yeepay.g3.common.laike.utils.EnvironmemntUtil;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.core.laike.utils.SecurityUtil;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.facade.UserAuthorityFacade;
import com.yeepay.g3.facade.laike.facade.app.AppUserAuthorityFacade;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.soa.context.DubboConfigUtils;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Description:userAuthorityFacade
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class UserFacadeTest extends BaseTest {

	@Autowired
	private UserAuthorityFacade userAuthorityFacade;

	@Autowired
	private AppUserAuthorityFacade appUserAuthorityFacade;

	public static void main(String[] a) throws UnsupportedEncodingException {
		//System.out.println(AES.encryptToBase64("13800000000", ConstantUtil.AES_KEY2));//18519397782
		System.err.println(SecurityUtil.decryptL2Info("9Ijq5eeJqmO6IL727oU5kw=="));
//        System.err.println(SecurityUtil.decryptL2Info("cvlvsmNmPy2EpRqPWlJ3zQ=="));
		System.err.println(SecurityUtil.encryptL1Info("123qwe"));
//        System.err.println(String.format("%.2f", Double.parseDouble("-1")));
//        Map<String, String> microLimitMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_MICRO_LIMIT);
//        System.err.println(String.format("%.2f", Double.parseDouble(microLimitMap.get("OTHER_ORDER"))));
	}

	@Rollback(false)
	@Test
	public void sendRegisterSms() {
		SendRegisterSmsRequest request = new SendRegisterSmsRequest();
		request.setPhoneNo("15922222239");
		request.setLocation("testLocation");
		request.setVersionId("APA17051767189564");
		request.setInviteCode("Y573426");
		BaseResponse response = appUserAuthorityFacade.sendRegisterSms(request);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));

	}

	@Rollback(false)
	@Test
	public void verifyRegisterSms() {
		VerifyRegisterSmsRequest request = new VerifyRegisterSmsRequest();
		request.setPhoneNo("15922222239");
		request.setLocation("deuhd");
		request.setPwd("/YL2tnnPQoPtVy4gTO21VA==");
		request.setSmsCode("916732");
		request.setVersionId("APA17051767189564");
		request.setAppSourceEnum(AppSourceEnum.LIKER);
		BaseResponse response = appUserAuthorityFacade.verifyRegisterSms(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}


	//	@Rollback(false)
	@Test
	public void login() throws MalformedURLException {
		HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
		AppUserAuthorityFacade appUserAuthorityFacade1 = (AppUserAuthorityFacade) hessianProxyFactory.create(AppUserAuthorityFacade.class, "http://10.151.160.130:8087/laike-hessian/hessian/AppUserAuthorityFacade");
		LoginRequest request = new LoginRequest();
		request.setPhoneNo("18614000000");
		request.setLocation("deuhd");
		request.setPwd("/YL2tnnPQoPtVy4gTO21VA==");
		request.setVersionId("APA17090723671941");
		LoginResponse response = appUserAuthorityFacade.login(request);
		System.err.println(new Gson().toJson(response));
//		Assert.assertTrue(response.getStatus().equals(ResponseStatus.FAILURE));
	}

	@Test
	public void test() {
		System.out.println(AES.encryptToBase64("15584841050", ConstantUtil.AES_KEY2));
	}

	@Test
	public void logout() {
//        LogoutReuqest request = new LogoutReuqest();
//        request.setImei("860707030306244");
//        request.setMemberNo("212468327015");
//        request.setVersionId("APA17051767189564");
//        userAuthorityFacade.logout(request);
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		System.out.println(sd.format(new Date(1499672003318l)));
		System.err.println(DubboConfigUtils.loadProperties().getProperty("dubbo.application.environment"));
	}

	@Test
	public void test1() {
		System.err.println(EnvironmemntUtil.getWebHost());
	}
}
