package com.yeepay.g3.core.laike.facade;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.yeepay.g3.common.Amount;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.aop.RemoteFacadeLogHandler;
import com.yeepay.g3.core.laike.biz.AppNotifyBiz;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.PayNotifyRequest;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.facade.PayNotifyFacade;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.lang.reflect.Proxy;
import java.net.MalformedURLException;

/**
 * Description: PayNotifyFacadeTest
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class PayNotifyFacadeTest extends BaseTest {

	@Autowired
	private PayNotifyFacade payNotifyFacade;

    @Autowired
    private AppNotifyBiz appNotifyBiz;

	@Test
	public void findOne() {
		PayNotifyRequest request = new PayNotifyRequest();
		request.setMerchantNo("10040007800");
		request.setOrderAmount(new Amount(123.12));
		request.setRealPayAmount(new Amount(223.12));
		request.setTipAmount(new Amount(100));
		request.setOrderNo("2380928391728937192");
		request.setPayReceipt("客观您的消息到了");
		request.setExternalSystem(ExternalSystem.YMF);
		BaseResponse response = payNotifyFacade.pushPayMsg2APP(request);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Test
	public void findOne2() {
		HessianProxyFactory factory = new HessianProxyFactory();
		PayNotifyFacade payNotifyFacade = null;
		try {
//			Object target = factory.create(PayNotifyFacade.class, "http://10.151.30.161:8087/laike-hessian/hessian/PayNotifyFacade");
			Object target = factory.create(PayNotifyFacade.class, "http://10.151.32.27:30035/laike-hessian/hessian/PayNotifyFacade");
			RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, "test");
			payNotifyFacade = (PayNotifyFacade) Proxy.newProxyInstance(logHandler.getClass().getClassLoader(),target.getClass().getInterfaces(), logHandler);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String[] ml={"10040040194"};
		for (String m:ml ){
			PayNotifyRequest request = new PayNotifyRequest();
			request.setMerchantNo(m);
			request.setOrderAmount(new Amount(123.12));
			request.setRealPayAmount(new Amount(223.12));
			request.setTipAmount(new Amount(100));
			request.setOrderNo("2380928391728937192");
			request.setPayReceipt("收到本条消息的请联系嘉雯");
			request.setExternalSystem(ExternalSystem.YMF);
			BaseResponse response = payNotifyFacade.pushPayMsg2APP(request);
			System.out.print(new Gson().toJson(response));
		}
    }

    @Rollback(false)
    @Test
    public void sendAppMsg() {
        BaseRequest request = new BaseRequest();
        request.setMemberNo("212468327057");
        request.setVersionId("APA17051767189564");
        appNotifyBiz.pushOpenMsg2APP(request);
    }

}
