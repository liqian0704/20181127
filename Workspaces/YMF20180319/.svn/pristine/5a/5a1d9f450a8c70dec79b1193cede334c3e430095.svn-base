package com.yeepay.g3.core.ymf.service.impl.customer;/**
 * Created by jiwei.lv on 16/12/27.
 */

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerFunction;
import com.yeepay.g3.core.ymf.service.CustomerFunctionService;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.service.customer.CustomerUtil;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiwei.lv
 * @create 2016-12-16/12/27
 */
@Service
public class CustomerUtilImpl implements CustomerUtil{
    @Autowired
    private CustomerFunctionService customerFunctionService;

    /**
     * 批量增加
     * @param functionArrays
     * @param customer
     */
    @Transactional
    public void updateCustomerFunction(String[] functionArrays, Customer customer){
        List<CustomerFunction> list = new ArrayList<CustomerFunction>();
        for (String funciton:functionArrays) {//保存支付配置
            CustomerFunction function = new CustomerFunction();
            function.setCustomerNumber(customer.getCustomerNumber());
            function.setTypeName(funciton);
            function.setStatus(Status.ACTIVE);
            list.add(function);
        }
        customerFunctionService.batchCreate(list);//保存与支付方式的关系商户关系
    }
}
