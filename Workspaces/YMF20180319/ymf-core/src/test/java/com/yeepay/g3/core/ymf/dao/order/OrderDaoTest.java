package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.core.ymf.dao.upayterminalno.TerminalNumberDao;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumber;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderDTO;
import com.yeepay.g3.facade.ymf.dto.order.OrderProofDTO;
import com.yeepay.g3.facade.ymf.dto.order.OrderProofQueryArgs;
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
    public void findByCustomerAndRequestIDwithArgs() throws Exception {

    }
    @Test
    public void findProofByQueryArgs() throws Exception{
        OrderProofQueryArgs OrderProofQueryArgs=new OrderProofQueryArgs();
        OrderProofQueryArgs.setCustomerNumber("10040007800");
        OrderProofQueryArgs.setStartdate("2017-03-01 00:00:00.000");
        OrderProofQueryArgs.setEnddate("2017-03-07 00:00:00.000");
        List<OrderProofDTO> list=getBean(OrderDao.class).findProofByQueryArgs(OrderProofQueryArgs);
        System.out.println(list.size());
    }
    @Test
    public void findTerminal(){
       List<TerminalNumber> list=getBean(TerminalNumberDao.class).selectByCustomerNumber("10040041026","ACTIVE");
        if(list!=null && list.size()>0){
            System.out.print(list.size());
        }else{
            System.out.print("8888");
        }

    }
}