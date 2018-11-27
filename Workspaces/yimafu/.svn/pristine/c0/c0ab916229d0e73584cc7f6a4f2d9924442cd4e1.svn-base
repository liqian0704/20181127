package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.customer.Customer;

import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.enumtype.CustomerLevel;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.junit.Test;

import java.util.Date;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/2.
 */
public class CustomerServiceTest extends ApplicationContextAwareTest {
    @Test
    public void findUserById() throws Exception {
        CustomerService customerService = getBean(CustomerService.class);
        Customer customer = customerService.findById(1L);
        System.out.println(customer);
    }

    @Test
    public void save() throws Exception {
        CustomerService customerService = getBean(CustomerService.class);
        Customer customer = new Customer();
        customer.setCustomerNumber("10040022354");
        customer.setCustomerName("hehe");
        customer.setAppType("STANDARD");
        customer.setCreateTime(new Date());
        customer.setCustomerType("444");
        customer.setStatus(Status.ACTIVE);
        customer.setCustomerLevel(CustomerLevel.A);
        customer.setOptimisitc(0L);
        customer.setIndustryType("hangye");
        customer.setPayTypeInfo("wechat");
        customer.setBusinessId(1L);
        customerService.saveCustomer(customer);
        jsonPrint(customer);
    }


}