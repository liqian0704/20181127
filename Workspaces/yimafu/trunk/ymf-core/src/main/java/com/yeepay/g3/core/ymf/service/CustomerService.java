package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerParam;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.Status;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/11.
 */
public interface CustomerService {

    Customer findById(Long id);

    Customer findByCustomerNumber(String customerNumber);

    List<Customer> findByParam(CustomerParam param);

    int saveCustomer(OperateEntity<Customer>  customer);

    int saveCustomer(Customer  customer);

    /**
     * 商户信息修改
     *  add by fei.lu
     * @param customer
     * @return
     */
    int updateCustomer(Customer customer);

    /**
     * 保存日志的商户信息修改
     * @param customer
     * @return
     */
    int updateCustomer(OperateEntity<Customer> customer);

    /**
     * 获得某状态的所有商户
     * @param status
     * @return
     */
    List<Customer> getAllCustomerByStatus(Status status);
}
