package com.yeepay.g3.core.ymf.junit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.core.ymf.junit.common.PrintTest;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierBaseDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeRefundQueryRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeRefundQueryResponseDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeRefundRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeRefundResponseDTO;
import com.yeepay.g3.facade.nctrade.facade.TradeBaseFacade;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.soa.context.ContextLoaderListener;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletContextEvent;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/2.
 */
public class SoaContextAwareTest extends PrintTest  {

    @Before
    public void setUp() {
        ContextLoaderListener listener = new ContextLoaderListener();
        MockServletContext servletContext = new MockServletContext("");
        servletContext.setInitParameter("soa_app_name", "ymf-boss");
        servletContext.setInitParameter("contextConfigLocation", "classpath*:applicationContext.xml");
        listener.contextInitialized(new ServletContextEvent(servletContext));

        ConfigurationUtils.init();
        RemoteServiceFactory.init();

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

}
