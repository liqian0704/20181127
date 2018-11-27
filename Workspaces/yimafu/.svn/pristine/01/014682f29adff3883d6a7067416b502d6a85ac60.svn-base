package com.yeepay.g3.core.ymf.biz.servlet;

import com.yeepay.g3.core.ymf.biz.trade.TradeBiz;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.core.ymf.support.ServletSupport;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundRequestDTO;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/25.
 */
public class G2ServletBizTest extends ApplicationContextAwareTest {
    @Test
    public void queryOrder() throws Exception {
        TradeBiz biz = getBean(TradeBiz.class);
        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("count", true);
        param.put("start", "1");
        param.put("page", "2");
        param.put("customerNumber", "10040007800");
        OrderArgs args = ServletSupport.buildOrderArgs(param);
        ResponseMessage resp = biz.queryOrder(args);
        jsonPrint(resp);
    }

    @Test
    public void queryRefund() throws Exception {
        TradeBiz biz = getBean(TradeBiz.class);
        RefundRequestDTO req = new RefundRequestDTO();
        req.setCustomerNumber("10040007800");
        req.setCustomerOrderId("428580769337");
        req.setYeepayOrderId("411609066935181949");
        req.setRefundAmount(new BigDecimal("0.3"));
        req.setRemark("测试第三次退款");
        jsonPrint(biz.refundOrder(req));
    }

    @Test
    public void countRefund() throws Exception {

    }

    @Test
    public void refundOrder() throws Exception {

    }

}