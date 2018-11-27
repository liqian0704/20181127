package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.core.ymf.biz.opr.OprUrlBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.opr.dto.yop.order.YopCreateOrderResDTO;
import com.yeepay.g3.facade.opr.dto.yop.order.YopQueryOrderResDTO;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayRequestDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.DirectPayTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.PassivePayTypeEnum;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @Description: 打算下单类接口
 * @Author: xiaobin.liu
 * @Date: 17/1/4
 * @Time: 下午5:00
 */
public class OprYopOrderExtTest extends AnnotationContextAwareTest {
    @Autowired
    private OprYopOrderExt oprYopOrderExt;
    @Autowired
    private OprUrlBiz oprUrlBiz;

    @Test
    public void testCreatOrder() {
        Order order = new Order();
        String orderId = "" + System.currentTimeMillis();
        order.setCustomerOrderId(orderId);
        order.setExternalId(orderId);
        order.setTrxAmt(new BigDecimal("0.01"));
        Payment payment = new Payment();
//        payment.setOpenId("2321312");
        Customer customer = new Customer();
        customer.setCustomerNumber("10040040627");//10040040627
        customer.setCustomerNumber(customer.getCustomerNumber());
        try {
            YopCreateOrderResDTO resp = oprYopOrderExt.createOrder(order, customer, payment);
            String s = oprUrlBiz.standardCashier(customer.getCustomerNumber(),
                    resp.getToken(), "",
                    "123456", "USER_ID");
            System.out.println("支付链接：" + s) ;
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQueryOrder() throws YmfTrxException {
        //"uniqueOrderNo":"1001201703030000000000019557","orderId":"635480767537"  10040007800
        YopQueryOrderResDTO resp = oprYopOrderExt.queryOrder("10040040627",
                "1489645285960", "1001201703160000000000019865");
        System.out.println(JSONUtils.toJsonString(resp));

    }

    @Test
    public void genStdLink() {
        Order order = new Order();
        String orderId = "" + System.currentTimeMillis();
        order.setCustomerOrderId(orderId);
        order.setExternalId(orderId);
        order.setTrxAmt(new BigDecimal("0.01"));
        Payment payment = new Payment();
//        payment.setOpenId("2321312");
        Customer customer = new Customer();
        customer.setCustomerNumber("10040007800");
        try {
            YopCreateOrderResDTO resp = oprYopOrderExt.createOrder(order, customer, payment);
            String s = oprUrlBiz.standardCashier("10040007800",
                    resp.getToken(), "",
                    "123456", "USER_ID");
            System.out.println("支付链接：" + s) ;
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void genQrLink() {
        Order order = new Order();
        String orderId = "" + System.currentTimeMillis();
        order.setCustomerOrderId(orderId);
        order.setExternalId(orderId);
        order.setTrxAmt(new BigDecimal("0.01"));
        Payment payment = new Payment();
//        payment.setOpenId("2321312");
        Customer customer = new Customer();
        customer.setCustomerNumber("10040007800");

        try {
            YopCreateOrderResDTO resp = oprYopOrderExt.createOrder(order, customer, payment);
            String s = oprUrlBiz.directPay("10040007800",
                    resp.getToken(), "",
                    "123456", "USER_ID", DirectPayTypeEnum.ALIPAY);
            System.out.println("支付链接：" + s) ;
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void genPqrLink() {
        Order order = new Order();
        String orderId = "" + System.currentTimeMillis();
        order.setCustomerOrderId(orderId);
        order.setExternalId(orderId);
        order.setTrxAmt(new BigDecimal("0.01"));
        Payment payment = new Payment();
//        payment.setOpenId("2321312");
        Customer customer = new Customer();
        customer.setCustomerNumber("10040007800");

        try {
            YopCreateOrderResDTO resp = oprYopOrderExt.createOrder(order, customer, payment);
            PassivePayRequestDTO requestDto = new PassivePayRequestDTO();
            requestDto.setCode("11111");
            requestDto.setDeviceSn("112312312");
            String s = oprUrlBiz.passivePay("10040007800",
                    resp.getToken(),requestDto, "1234567890",
                    PassivePayTypeEnum.WX);
            System.out.println("支付链接：" + s) ;
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }


}
