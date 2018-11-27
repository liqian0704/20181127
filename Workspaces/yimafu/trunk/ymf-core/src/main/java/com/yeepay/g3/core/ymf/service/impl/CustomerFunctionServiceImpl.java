package com.yeepay.g3.core.ymf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yeepay.g3.core.ymf.dao.customer.CustomerFunctionMapper;
import com.yeepay.g3.core.ymf.entity.customer.CustomerFunction;
import com.yeepay.g3.core.ymf.entity.customer.CustomerFunctionParam;
import com.yeepay.g3.core.ymf.service.CustomerFunctionService;
import com.yeepay.g3.facade.ymf.enumtype.Status;

/**
 * Created by yp-tc-m-2762 on 16/8/16.
 */
@Service
public class CustomerFunctionServiceImpl implements CustomerFunctionService{
    @Autowired
    private CustomerFunctionMapper customerFunctionMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveCustomerFunction(CustomerFunction function) {
        function.setStatus(Status.ACTIVE);
        return customerFunctionMapper.insert(function);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateCustomerFunction(CustomerFunction function) {
        return customerFunctionMapper.updateByPrimaryKeySelective(function);
    }

    @Override
    public List<CustomerFunction> getCustomerFunctionByCustomerNumber(String customerNumber) {
        CustomerFunctionParam param = new CustomerFunctionParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber);
        List<CustomerFunction> list = customerFunctionMapper.selectByFilter(param);
        if(list!=null&&list.size()!=0){
            return list;
        }else {
            return null;
        }
    }
    @Override
    public List<CustomerFunction> getCustomerFunctionByCustomerNumberAndStatus(String customerNumber,Status status) {
    	CustomerFunctionParam param = new CustomerFunctionParam();
    	param.createCriteria().andCustomerNumberEqualTo(customerNumber).andStatusEqualTo(status);
    	List<CustomerFunction> list = customerFunctionMapper.selectByFilter(param);
    	if(list!=null&&list.size()!=0){
    		return list;
    	}else {
    		return null;
    	}
    }

    @Override
    public CustomerFunction getFunctionByCustomerNumberAndType(String customerNumber, String typeName) {
        CustomerFunctionParam param = new CustomerFunctionParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber).andTypeNameEqualTo(typeName);
        List<CustomerFunction> list = customerFunctionMapper.selectByFilter(param);
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }else {
            return null;
        }

    }
}
