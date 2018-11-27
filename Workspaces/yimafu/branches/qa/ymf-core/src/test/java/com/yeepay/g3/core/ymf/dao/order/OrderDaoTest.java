package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/29.
 */
public class OrderDaoTest extends ApplicationContextAwareTest {

    @Test
    public void queryOrderDTOByArgs() throws Exception {
        OrderArgs orderArgs = new OrderArgs();
        orderArgs.setStart(0);
        orderArgs.setPage(10);
        List<OrderDTO> orderDTOList = getBean(OrderDao.class).queryOrderDTOByArgs(orderArgs);
        System.out.println(orderDTOList.size());
    }

    @Test
    public void countOrderByArgs() throws Exception {
        OrderArgs orderArgs = new OrderArgs();
        orderArgs.setStart(0);
        orderArgs.setPage(10);
        CountResponse countResponse = getBean(OrderDao.class).countOrderByArgs(orderArgs);
        jsonPrint(countResponse);
    }

    @Test
    public void updateExtendIdById() throws Exception {
        String externalId = "327580769330";

    }

    @Test
    public void findByCustomerAndRequestID() throws Exception {
        String customerNumber = "10040010020";
        String customerOrderId = "464261768668680192";
        Map<String, String> map = new HashMap<String, String>();
        map.put("customerNumber", customerNumber);
        map.put("customerOrderId", customerOrderId);
        Order order = getBean(OrderDao.class).findByCustomerAndRequestID(map);
        jsonPrint(order);
    }

    @Test
    public void findByCustomerAndRequestIDwithArgs() throws Exception {

    }

}