package com.yeepay.g3.core.laike.biz;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.dto.alliance.AllianceRequest;
import com.yeepay.g3.facade.laike.enumtype.AccountType;
import com.yeepay.g3.facade.laike.facade.app.AppLikerQueryFacade;
import com.yeepay.g3.utils.common.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: wei.li
 * Date: 17/2/22
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class QueryBizTest extends BaseTest {

    @Autowired
    private QueryBiz queryBiz;

    @Autowired
    private AppNotifyBiz appNotifyBiz;

    @Autowired
    private QueryPageBiz queryPageBiz;

//    @Test
//    public void querySettle(){
//        Gson gson = new Gson();
//        QueryOrderRequest request = new QueryOrderRequest();
//
//        request.setMemberNo("212468315183");
//        request.setPageIndex(1);
//        request.setPageSize(10);
//        QueryListResponse response = queryBiz.querySettleAll(request);
//        System.out.print(gson.toJson(response));
//    }
//
//    @Test
//    public void queryYMF(){
//        Gson gson = new Gson();
//        RegisterMerRequest request = new RegisterMerRequest();
//        request.setMerchantNo("10040039783");
//        QueryListResponse response = queryBiz.queryYmfCustomer(request);
//        System.out.print(response);
//    }

    @Test
    public void querySettleBank() {
        BaseRequest request = new BaseRequest();
        System.err.println(queryBiz.querySettleBank(request).getResultList().get(0));

    }

    @Test
    public void queryAllianceMerchantCount() {
        AllianceRequest request = new AllianceRequest();
        request.setMemberNo("212468327083");
        request.setAccountType(AccountType.LOL);
        request.setStartDate("2017-07-01");
        request.setEndDate("2017-07-30");
        System.err.println(queryPageBiz.queryLolTradeDetail(request));
    }

    @Test//今日交易金额
    public void testFacadeV6() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        AppLikerQueryFacade appLikerQueryFacade1 = (AppLikerQueryFacade) hessianProxyFactory.create(AppLikerQueryFacade.class, "http://10.151.32.27:30036/laike-hessian/hessian/AppLikerQueryFacade");
        //AppUserAuthorityFacade appUserAuthorityFacade = (AppUserAuthorityFacade)hessianProxyFactory.create(AppLikerQueryFacade.class, "http://10.151.32.27:30036/laike-hessian/hessian/AppUserAuthorityFacade");
        QueryOrderRequest request = new QueryOrderRequest();
        request.setMemberNo("212468327836");
        System.err.println(appLikerQueryFacade1.queryToday(request));
    }
    @Test//收款列表
    public void testFacadeV7() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        AppLikerQueryFacade appLikerQueryFacade1 = (AppLikerQueryFacade) hessianProxyFactory.create(AppLikerQueryFacade.class, "http://10.151.32.27:30036/laike-hessian/hessian/AppLikerQueryFacade");
        //AppUserAuthorityFacade appUserAuthorityFacade = (AppUserAuthorityFacade)hessianProxyFactory.create(AppLikerQueryFacade.class, "http://10.151.32.27:30036/laike-hessian/hessian/AppUserAuthorityFacade");
        QueryOrderRequest request = new QueryOrderRequest();
        request.setMemberNo("212468327836");
        request.setStartDate("2017-08-11");
        request.setEndDate("2017-08-21");
        QueryListResponse response = appLikerQueryFacade1.queryOrder(request);
        long orderMount=response.getList().size();
        System.out.println("订单数量为"+orderMount);
        long realMount= response.getCount();
        Assert.assertTrue(realMount==response.getList().size());
        //Assert.assertTrue(response.getStatus().equals("SUCCESS"));
        junit.framework.Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
//        Gson gson = new Gson();
//        System.out.print(gson.toJson(response));
//       // System.out.println("订单数量为:"+appLikerQueryFacade1.queryOrder(request).getCount());
    }
    @Test//到账查询
    public void testFacadeV8() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        AppLikerQueryFacade appLikerQueryFacade1 = (AppLikerQueryFacade) hessianProxyFactory.create(AppLikerQueryFacade.class, "http://10.151.32.27:30036/laike-hessian/hessian/AppLikerQueryFacade");
        //AppUserAuthorityFacade appUserAuthorityFacade = (AppUserAuthorityFacade)hessianProxyFactory.create(AppLikerQueryFacade.class, "http://10.151.32.27:30036/laike-hessian/hessian/AppUserAuthorityFacade");
        QueryOrderRequest request = new QueryOrderRequest();
        request.setMemberNo("212468327836");
//        request.setStartDate("2017-07-25");
//        request.setEndDate("2017-07-31");
//        request.setStartDate("2017-05-10");
//        request.setEndDate("2017-05-12");
        request.setStartDate("2017-05-01");
        request.setEndDate("2017-07-21");
        long mount=appLikerQueryFacade1.querySettleAll(request).getCount();
        BigDecimal sum=appLikerQueryFacade1.querySettleAll(request).getSum();
        System.out.println("笔数:="+mount+"到账金额="+sum);
        //到账记录条数
       long size= appLikerQueryFacade1.querySettleAll(request).getList().size();
        //到账记录
       List listItem= appLikerQueryFacade1.querySettleAll(request).getList();
        Gson gson=new Gson();
        System.out.println(gson.toJson(listItem));}



    @Rollback(false)
    @Test
    public void test1() {
        BaseRequest request = new BaseRequest();
        request.setMemberNo("212468327025");
        appNotifyBiz.pushOpenMsg2APP(request);
    }

    @Test
    public void CardBinQuery() {
        CardBinRequset requset = new CardBinRequset();
        requset.setBankCardNo("6222802433201253843");
        requset.setBankCardNo("6225768772024541");
        System.err.println(new Gson().toJson(queryBiz.queryCardBinAndSettleList(requset)));
    }

    @Test
    public void queryAllianceShare() {
        QueryBaseRequest request = new QueryBaseRequest();
        Date shareDate = DateUtils.getFirstDateOfMonth(new Date());
        request.setMemberNo("212468327285");
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -1);
//        calendar.set(Calendar.DAY_OF_MONTH,0);
//        System.err.println(shareDate);
        //calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
//        System.err.println(calendar.getTime());
//        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
//        System.err.println(calendar.getTime());

        System.err.println(new Gson().toJson(queryPageBiz.queryAllianceShareAll(request)));
    }

    @Test
    public void queryAllianceShareDetail() {
        QueryBaseRequest request = new QueryBaseRequest();
        request.setMemberNo("212468327285");
        System.err.println(new Gson().toJson(queryPageBiz.queryAllianceShareDetail(request)));
    }

    @Test
    public void queryOrder() {
        QueryOrderRequest request = new QueryOrderRequest();
        request.setMemberNo("212468327836");
//        request.setPageSize(1);
        request.setStartDate("2017-05-22");
        request.setEndDate("2017-05-23");
        System.err.println(new Gson().toJson(queryPageBiz.queryOrder(request)));
    }

    @Test
    public void queryShopName() {
        QueryOrderRequest request = new QueryOrderRequest();
        //request.setPageIndex(5);
        request.setMemberNo("212468327434");
        System.err.println(new Gson().toJson(queryPageBiz.queryShopName(request)));
    }

    @Test
    public void queryCalFeeAndLimit() {
        QueryOrderRequest request = new QueryOrderRequest();
        //request.setPageIndex(5);
        request.setMemberNo("212468327329");
        System.err.println(new Gson().toJson(queryBiz.queryCalFeeAndLimit(request)));
    }



}
