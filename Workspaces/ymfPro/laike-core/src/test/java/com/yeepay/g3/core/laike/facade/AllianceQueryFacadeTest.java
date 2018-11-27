package com.yeepay.g3.core.laike.facade;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.dto.alliance.AllianceRequest;
import com.yeepay.g3.facade.laike.dto.alliance.StatisticsResponse;
import com.yeepay.g3.facade.laike.enumtype.AccountType;
import com.yeepay.g3.facade.laike.enumtype.BoolEnum;
import com.yeepay.g3.facade.laike.facade.app.AllianceQueryFacade;
import com.yeepay.g3.facade.laike.facade.app.AppLikerQueryFacade;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;

/**
 * Description:userAuthorityFacade
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AllianceQueryFacadeTest extends BaseTest {

	@Autowired
	private AllianceQueryFacade allianceQueryFacade;

	@Test
	public void queryAllianceDetail() {
		Gson gson = new Gson();
		AllianceRequest request = new AllianceRequest();
		request.setVersionId("APA17051767189565");
		request.setAccountType(AccountType.LOL);
		request.setMemberNo("312468327836");
		request.setStartDate("2017-03-29");
		request.setEndDate("2017-07-09");
		request.setOpenStatus(BoolEnum.FALSE);
		QueryListResponse response = allianceQueryFacade.queryAllianceDetail(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
		System.out.print(gson.toJson(response));
	}

	@Test
	public void queryLolTradeDetail() {
		Gson gson = new Gson();
		AllianceRequest request = new AllianceRequest();
		request.setAccountType(AccountType.LOL);
		request.setVersionId("APA17051767189565");
        request.setMemberNo("212468327836");
        request.setStartDate("2017-04-08");
		request.setEndDate("2017-07-02");
		QueryListResponse response = allianceQueryFacade.queryLolTradeDetail(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
		System.out.print(gson.toJson(response));
	}

	@Test
	public void queryStatisticsAll() {
		Gson gson = new Gson();
		BaseRequest request = new BaseRequest();
		request.setVersionId("APA17051767189565");
		request.setMemberNo("312468327836");
		StatisticsResponse response = allianceQueryFacade.queryStatistics(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
		System.out.print(gson.toJson(response));
	}

	@Test
	public void queryAllianceMerchantCount() {
		Gson gson = new Gson();
		QueryBaseRequest request = new QueryBaseRequest();
		request.setVersionId("APA17051767189565");
		request.setMemberNo("212468327015");
		request.setStartDate("2017-06-12");
		request.setEndDate("2017-07-02");
		QueryListResponse response = allianceQueryFacade.queryAllianceMerchantCount(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
		System.out.print(gson.toJson(response));
	}
	@Test
	public void testFacadeV6() throws MalformedURLException {
		HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
		AppLikerQueryFacade appLikerQueryFacade1 = (AppLikerQueryFacade)hessianProxyFactory.create(AppLikerQueryFacade.class, "http://10.151.32.27:30036/laike-hessian/hessian/AppLikerQueryFacade");
		QueryOrderRequest request = new QueryOrderRequest();
		request.setMemberNo("212468315283");
		System.err.println(appLikerQueryFacade1.queryToday(request));
	}
}
