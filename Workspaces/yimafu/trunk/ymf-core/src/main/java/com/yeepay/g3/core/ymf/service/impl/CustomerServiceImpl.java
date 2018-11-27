package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.g3.core.ymf.dao.customer.CustomerMapper;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerParam;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/11.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer findById(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Customer findByCustomerNumber(String customerNumber) {
        CustomerParam param = new CustomerParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber);
        List<Customer> customerList = customerMapper.selectByFilter(param);
        if (null == customerList || customerList.size() == 0) {
            return null;
        }
        return customerList.get(0);
    }

    @Override
    public List<Customer> findByParam(CustomerParam param) {
        return customerMapper.selectByFilter(param);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveCustomer(OperateEntity<Customer> en) {
        en.getEntity().setCreateTime(new Date());
        return customerMapper.insert(en.getEntity());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveCustomer(Customer customer) {
        customer.setOptimisitc(0L);
        customer.setCreateTime(new Date());
        return customerMapper.insert(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateCustomer(Customer customer) {
        customer.setUpdateTime(new Date());
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateCustomer(OperateEntity<Customer> customer) {
        customer.getEntity().setUpdateTime(new Date());
        return customerMapper.updateByPrimaryKeySelective(customer.getEntity());
    }

    @Override
    public List<Customer> getAllCustomerByStatus(Status status) {
        CustomerParam param = new CustomerParam();
        param.createCriteria().andStatusEqualTo(status);
        return customerMapper.selectByFilter(param);
    }
}
