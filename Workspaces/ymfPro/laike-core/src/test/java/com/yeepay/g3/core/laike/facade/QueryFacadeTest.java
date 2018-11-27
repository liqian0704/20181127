package com.yeepay.g3.core.laike.facade;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.biz.QueryBiz;
import com.yeepay.g3.core.laike.biz.QueryPageBiz;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.core.laike.utils.RemoteFacadeFactory;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.dto.alliance.AllianceRequest;
import com.yeepay.g3.facade.laike.enumtype.AccountType;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.facade.app.AllianceQueryFacade;
import com.yeepay.g3.facade.laike.facade.app.AppLikerQueryFacade;
import com.yeepay.g3.facade.mer.dto.response.out.MerAreaConvertCodeRespDto;
import com.yeepay.g3.facade.mer.facade.out.MerOutInvokeFacade;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.encrypt.Base64;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:userAuthorityFacade
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class QueryFacadeTest extends BaseTest {

	@Autowired
	private AppLikerQueryFacade likerQueryFacade;

	@Autowired
	private QueryPageBiz queryPageBiz;

	@Autowired
    private QueryBiz queryBiz;

    @Autowired
    private AllianceQueryFacade allianceQueryFacade;

	public static void main(String[] a) {
		System.out.println(AES.encryptToBase64("123qwe", ConstantUtil.AES_KEY1));
		List<String> phoneNos = new ArrayList<String>() {
			{
				add("sssss1");
				add("sssss2");
				add("sssss3");
				add("sssss4");
			}
		};
		System.out.print(StringUtils.join(phoneNos.toArray(), ","));
	}

	@Test
	public void queryMsg() {
		Gson gson = new Gson();
		QueryPushMsgRequest request = new QueryPushMsgRequest();
		request.setPhoneNo("212468315882");
		request.setPageIndex(0);
		request.setPageSize(10);
		request.setStartDate("2017-01-01");
		request.setEndDate("2017-02-22");
		QueryListResponse response = likerQueryFacade.queryMsg(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
		System.out.print(gson.toJson(response));
	}

	@Test
	public void queryAlliance() {
		Gson gson = new Gson();
		AllianceRequest request = new AllianceRequest();
		request.setMemberNo("312468327836");
		request.setAccountType(AccountType.LOL);
		QueryListResponse response = queryPageBiz.queryAllianceDetail(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
		System.out.print(gson.toJson(response));
	}

	@Test
	public void queryOrder() {
		Gson gson = new Gson();
		QueryOrderRequest request = new QueryOrderRequest();
		request.setMemberNo("212468314438");
		request.setPageIndex(1);
		request.setPageSize(20);
//		request.setOrderStatus(OrderStatus.FAIL);
		request.setOrderType(TrxType.PURCHASE);
		request.setPaySource(PaySource.NCPAY);
		request.setStartDate("2016-09-26");
		request.setEndDate("2016-12-23");
		QueryListResponse response = likerQueryFacade.queryOrder(request);
		System.out.print(gson.toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Test
	public void querySettle() {
		Gson gson = new Gson();
		QueryOrderRequest request = new QueryOrderRequest();
		//request.setPhoneNo("212468315183");
		request.setMemberNo("212468315183");
//		request.setPageIndex(1);
//		request.setPageSize(10);
		request.setStartDate("2016-11-25");
		request.setEndDate("2017-02-25");
		QueryListResponse response = likerQueryFacade.querySettle(request);
		System.out.print(gson.toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Test
	public void querySettleAll() {
		Gson gson = new Gson();
		QueryOrderRequest request = new QueryOrderRequest();
        request.setMemberNo("212468327836");
        request.setStartDate("2017-06-01");
        request.setEndDate("2017-08-25");
        QueryListResponse response = likerQueryFacade.querySettleAll(request);
		System.out.print(gson.toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}


	@Test
	public void queryAccountBalance() {
		Gson gson = new Gson();
		BaseRequest request = new BaseRequest();
		request.setMemberNo("212468314302");
		QueryResponse response = likerQueryFacade.queryAccountBalance(request);
		System.out.print(gson.toJson(response));
	}

	@Test
	public void getQrCodeByCustomer() {
		Gson gson = new Gson();
		BaseRequest request = new BaseRequest();
		request.setMemberNo("212468314302");
		QueryResponse response = likerQueryFacade.queryQRCode(request);
		System.out.print(gson.toJson(response));
	}

	@Test
	public void queryAreaInfo() {
		Gson gson = new Gson();
		QueryAreaInfoRequest request = new QueryAreaInfoRequest();
        request.setCode("320100");
        QueryResponse response = likerQueryFacade.queryAreaInfo(request);
		System.out.print(gson.toJson(response));
	}

	@Test
	public void queryProvineInfo() {
		MerOutInvokeFacade service = RemoteFacadeFactory.getService(MerOutInvokeFacade.class,
				ExternalSystem.CS_MERCHANT);
        MerAreaConvertCodeRespDto merAreaConvertCodeRespDto = service.g2AreaToCusArea("0501");
        System.out.print(merAreaConvertCodeRespDto);
    }

	@Test
	public void query() {
		BaseRequest baseRequest = new BaseRequest();
		baseRequest.setMemberNo("212468315706");
        System.err.println(queryPageBiz.queryStatisticsAll(baseRequest));
    }

	@Test
	public void queryLolTradeDetail() {
		AllianceRequest request = new AllianceRequest();
		request.setMemberNo("212468327049");
		request.setStartDate("2017-07-01");
		request.setEndDate("2017-07-30");
		request.setAccountType(AccountType.LK);
		System.err.println(queryPageBiz.queryLolTradeDetail(request));
	}

	@Test
	public void queryStatistics() {
//		BaseRequest baseRequest = new BaseRequest();
//		baseRequest.setVersionId("APA17051767189565");
//		baseRequest.setMemberNo("");
//		allianceQueryFacade.queryStatistics(baseRequest);
        System.err.println(Base64.encode("13000000000"));
    }

//    @Test
//	public void queryGeologyLocation(){
//        LbsRequest request = new LbsRequest();
//		request.setLat("39.983424");
//		request.setLng("116.322987");
//		System.err.println(queryBiz.queryGeologyLocation(request));
//	}
}
