package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerParam;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 添加默认网点
     */
    void addDefaultShop(String customerNumber);

    /**
     * 添加商户同时，添加默认网点
     * @param en
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    int saveCustomerAndShop(OperateEntity<Customer> en);

    int saveCustomer(OperateEntity<Customer>  customer);

    int saveCustomer(Customer  customer);

    /**
     * 添加商户同时，添加默认网点
     * @param customer
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    int saveCustomerAndShop(Customer customer);

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

    int changeTradeSystemForId(String customerid);
    
    List<Customer> getCustomersByGroupId(Long groupId);
    
    int unBindGroup(Long id);
    
    /**
     * 绑定对应商户的一级商户
     * @param id 商户ID
     * @param groupId 一级商户ID
     */
    int bindGroup(Long id,Long groupId);
    
    List<Customer> findCustomerByName(String customerName);

    /**
     * 分页查询商户信息
     * @param status 商户状态
     * @param bizCode 业务方编码
     * @param rowBegin 起始页
     * @param rowEnd 结束页
     * @return
     */
    List<Customer> findStatusAndBusiType(Status status, String bizCode,int rowBegin,int rowEnd);

    /**
     * 查询商户汇总
     * @param status 商户状态
     * @param bizCode 业务方编码
     * @return
     */
    int findStatusAndBusiTypeCount(Status status, String bizCode);

    /**
     * 查询所有没有网点的商户
     * @return
     */
    List<Customer> findNOShopCustomers();

    /**
     * 查询所有商户logo的ftp路径
     * @return
     */
    List<Customer> findOldFtpPrefix(String prefix);
}
