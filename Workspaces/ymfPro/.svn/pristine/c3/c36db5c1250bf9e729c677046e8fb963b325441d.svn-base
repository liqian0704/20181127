package com.yeepay.g3.core.laike.biz;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.LoginRequest;
import com.yeepay.g3.facade.laike.dto.SendRegisterSmsRequest;
import com.yeepay.g3.facade.laike.dto.VerifyRegisterSmsRequest;
import com.yeepay.g3.facade.laike.facade.PwdFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Description:UserBizTest
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class UserBizTest extends BaseTest {

	@Autowired
	private UserAuthBiz userAuthBiz;

    @Autowired
    private PwdFacade pwdFacade;


    //	@Rollback(false)
	@Test
	public void sendRegisterSms() {
		SendRegisterSmsRequest request = new SendRegisterSmsRequest();
		request.setPhoneNo("18519307782");
		request.setLocation("deuhd");
		System.out.print(new Gson().toJson(userAuthBiz.sendRegisterSms(request)));
	}

	@Rollback(false)
	@Test
	public void verifyRegisterSms() {
		VerifyRegisterSmsRequest request = new VerifyRegisterSmsRequest();
		request.setPhoneNo("18519397782");
		request.setLocation("deuhd");
		request.setSmsCode("018612");
		request.setPwd("123qwe");
		System.out.print(new Gson().toJson(userAuthBiz.verifyRegisterSms(request)));
    }

    @Test
    public void login() {
        LoginRequest request = new LoginRequest();
        request.setPhoneNo("15900000001");
        request.setPwd("/YL2tnnPQoPtVy4gTO21VA==");
        System.err.print(new Gson().toJson(userAuthBiz.login(request)));
    }

    @Test
    public void findPwd() {
        BaseRequest request = new BaseRequest();
        request.setPhoneNo("15910283021");
        request.setVersionId("APA17051767189564");
        System.err.print(new Gson().toJson(pwdFacade.findPwdBySms(request)));
    }
}
