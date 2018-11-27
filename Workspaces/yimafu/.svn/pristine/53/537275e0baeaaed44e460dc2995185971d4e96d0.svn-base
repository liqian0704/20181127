package com.yeepay.g3.core.ymf.service.order;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceGenerator;
import com.yeepay.g3.facade.ymf.dto.order.OrderQueryArgs;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/18.
 */
public class OrderServiceTest extends ApplicationContextAwareTest {

    @Test
    public void save() throws Exception {
        Order order = new Order();
        order.setCustomerNumber("10040010020");
        order.setCustomerOrderId(SequenceGenerator.getInstance().generate().toString());
        order.setCreateTime(new Date());
        order.setFee(BigDecimal.ONE);
        OrderService service = getBean(OrderService.class);
        int r = service.save(order);
        System.out.println(r);
    }

    @Test
    public void querySingleOrder() throws Exception {
        OrderQueryArgs args = new OrderQueryArgs();
        args.setCustomerNumber("10040007800");
        args.setCustomerOrderId("428580769337");
        args.setYeepayOrderId("411609066935181949");
        OrderService service = getBean(OrderService.class);
        jsonPrint(service.findByQueryArgs(args));
    }


}