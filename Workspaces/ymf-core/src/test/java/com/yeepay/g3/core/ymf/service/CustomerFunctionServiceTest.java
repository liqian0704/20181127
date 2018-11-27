package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.CustomerFunction;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongxulu on 16/12/16.
 */
public class CustomerFunctionServiceTest extends ApplicationContextAwareTest {

    @Test
    public void getByCustomerAndDicType(){

        CustomerFunctionService service = getBean(CustomerFunctionService.class);
        List<CustomerFunction> list = service.getByCustomerAndDicType("10040007800", Constants.COLLECT_TYPE);
        for(CustomerFunction function:list){
            System.out.println(function.toString());
        }

    }
    @Test
    public void batchCreate(){

        List<CustomerFunction> list = new ArrayList<CustomerFunction>();
        CustomerFunctionService service = getBean(CustomerFunctionService.class);
        for (int i=0;i<5;i++) {//保存支付配置
            CustomerFunction function = new CustomerFunction();
            function.setCustomerNumber("10040007800");
            function.setTypeName("testBatch");
            function.setStatus(Status.ACTIVE);
            list.add(function);
        }
        service.batchCreate(list);

    }

}
