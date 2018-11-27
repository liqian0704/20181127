package com.yeepay.g3.core.ymf.junit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.junit.common.PrintTest;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.web.HttpUtil;
import com.yeepay.g3.facade.nctrade.dto.*;
import com.yeepay.g3.facade.nctrade.facade.TradeBaseFacade;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.laike.BaseResp;
import com.yeepay.g3.facade.ymf.dto.laike.SettleArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import com.yeepay.g3.facade.ymf.facade.agent.SyncOrderFacade;
import com.yeepay.g3.facade.ymf.facade.laike.OrderFacade;
import com.yeepay.g3.utils.cache.remote.RemoteCacheUtils;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.log.utils.StringUtil;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/2.
 */
public class SoaContextAwareTest extends PrintTest  {
    protected ApplicationContext context;

    protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected DateFormat df_m = new SimpleDateFormat("yyyy-MM-dd");


    protected <T> T getService(Class<T> clazz) {
        return RemoteServiceFactory.getService(clazz);
    }

    @Before
    public void setUp() {
//        ContextLoaderListener listener = new ContextLoaderListener();
//        MockServletContext servletContext = new MockServletContext("");
//        servletContext.setInitParameter("soa_app_name", "ymf-boss");
//        servletContext.setInitParameter("contextConfigLocation", "classpath*:applicationContext.xml");
//        listener.contextInitialized(new ServletContextEvent(servletContext));
        context = new ClassPathXmlApplicationContext("ymf-application-test.xml");
        RemoteServiceFactory.init();
        ConfigurationUtils.init();
        RemoteCacheUtils.init();
    }
    public <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public Object getBean(String beanName) {
        return context.getBean(beanName);
    }
    @Test
    public void test() {
        TradeFacade facade = RemoteServiceFactory.getService(TradeFacade.class);
        try {
            facade.supplyTodayOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        TradeCashierFacade facade = RemoteServiceFactory.getService(TradeCashierFacade.class);
        TradeCashierBaseDTO req = new TradeCashierBaseDTO();
        try {
            req.setMerchantAccount("10040007800");
            req.setCustomerOrderId("411608256306427981");
            req.setAccessCode("17");
            // 退款订单号
            TradeCashierReplyDTO resp = facade.purchaseQuery(req);
            ObjectMapper om = new ObjectMapper();
            System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {
         "hmac" : "8d931d0dc05c997ebcb2382454c15c35",
         "tradeSysNo" : "NCTRADE",
         "accessCode" : "17",
         "tradeSysOrderId" : "411608256306427981",
         "merchantAccount" : "10040007800",
         "merchantRefundOrderid" : "refund112233445566",
         "bizRefundNo" : "201608265449606845",
         "subMerchantAccount" : null,
         "amount" : 1,
         "fee" : 0,
         "currency" : "CNY",
         "lastUpdateTime" : 1472195296000,
         "remain" : 0,
         "cause" : null,
         "errorCode" : null,
         "errorMsg" : null,
         "accept" : true
     }
     */
    @Test
    public void testRefund() {
        TradeBaseFacade facade = RemoteServiceFactory.getService(TradeBaseFacade.class);
        TradeRefundRequestDTO req = new TradeRefundRequestDTO();
        try {
            req.setMerchantAccount("10040007800");
            req.setAmount(1L);
            req.setTradeSysOrderId("411608256306427981");
            req.setAccessCode("17");
            // 退款订单号
            req.setCurrency("CNY");
            req.setMerchantRefundOrderid("refund112233445566");
            TradeRefundResponseDTO resp = facade.refundRequest(req);
            ObjectMapper om = new ObjectMapper();
            System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetSales() {
        try {
            String url =  ConfigureSetting.getValue(Constants.SALES_DATA_URL,String.class);
            System.out.println(url);
            if(!StringUtil.isEmpty(url)){
                String result =  HttpUtil.httpGet(url,new HashMap());
                System.out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * {
         "hmac" : "8d931d0dc05c997ebcb2382454c15c35",
         "tradeSysNo" : "NCTRADE",
         "accessCode" : "17",
         "tradeSysOrderId" : "411608256306427981",            易宝交易流水号
         "merchantAccount" : "10040007800",
         "merchantRefundOrderid" : "refund10040007800724126", 商户退款订单号 ymf生成
         "bizRefundNo" : "201608256349500810",                统一收银台退款号
         "amount" : 1,
         "fee" : 0,
         "currency" : "CNY",
         "createTime" : 1472117895000,
         "lastUpdateTime" : 1472117911000,
         "remain" : 1,
         "cause" : "测试商户退款",
         "status" : "5"
     }
     */
    @Test
    public void testRefundQuery() {
        TradeBaseFacade facade = RemoteServiceFactory.getService(TradeBaseFacade.class);
        TradeRefundQueryRequestDTO req = new TradeRefundQueryRequestDTO();
        try {
            req.setAccessCode("17");
            req.setMerchantRefundOrderid("refund112233445566");
            TradeRefundQueryResponseDTO resp = facade.refundQuery(req);
            ObjectMapper om = new ObjectMapper();
            System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrderFacade() {
        OrderFacade facade = RemoteServiceFactory.getService(OrderFacade.class);
        OrderArgs args = new OrderArgs();
        args.setStart(0);
        args.setPage(20);
        args.setCustomerNumber("10040007800");
        args.setPayStatus("SUCCESS");
        args.setCount(true);
        try {
            args.setPayTimeStart(df.parse("2016-10-01 00:00:00"));
            args.setPayTimeEnd(df.parse("2016-10-28 00:00:00"));
            BaseResp resp = facade.queryOrder(args);
            deepPrint(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuerySettle() {
        OrderFacade facade = getService(OrderFacade.class);
        SettleArgs args = new SettleArgs();
        args.setCount(true);
        args.setCustomerNumber("10040007800");
        args.setStart(0);
        args.setPage(20);
        try {
            args.setStartDate(df_m.parse("2016-04-27"));
            args.setEndDate(df_m.parse("2016-04-27"));
            BaseResp resp = facade.querySettle(args);
            deepPrint(resp);
        } catch (Exception e) {

        }
    }

    @Test
    public void testQueryOrder() {
        try {
            SyncOrderFacade facade = RemoteServiceFactory.getService(SyncOrderFacade.class);
            List<String> customerNumbers = new ArrayList<String>();
            customerNumbers.add("10040040183");
            Date from = df.parse("2017-03-01 00:00:00");
            Date to = df.parse("2017-03-30 00:00:00");
            List<SyncOrderDTO> orderDTOList = facade.queryOrder(customerNumbers, from, to);
            System.out.println(orderDTOList.size());
            for (SyncOrderDTO syncOrderDTO : orderDTOList) {
                System.out.println(syncOrderDTO);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }
}
