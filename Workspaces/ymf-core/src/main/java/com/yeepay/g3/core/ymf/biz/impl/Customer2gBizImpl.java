package com.yeepay.g3.core.ymf.biz.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.yeepay.app.hessian.dto.CustomerBasicInfoDTO;
import com.yeepay.app.hessian.facade.G2ServiceInterfaceFacade;
import com.yeepay.g3.core.ymf.biz.Customer2gBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/15.
 */
//@Service
public class Customer2gBizImpl implements Customer2gBiz {

//    @Reference
    G2ServiceInterfaceFacade g2ServiceInterfaceFacade;

    @Override
    public Customer test() {
        CustomerBasicInfoDTO basic = g2ServiceInterfaceFacade.getCustomerBasicInfo("100400");
        Customer c = new Customer();
        c.setCustomerName(basic.getCustomernumber());
        return c;
    }
}
