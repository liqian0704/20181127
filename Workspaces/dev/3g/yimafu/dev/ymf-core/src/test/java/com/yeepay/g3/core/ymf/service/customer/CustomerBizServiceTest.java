package com.yeepay.g3.core.ymf.service.customer;

import com.yeepay.app.httpinvoke.online.facade.G2ServiceInterfaceFacade;
import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.junit.Test;

/**
 * Created by dongxulu on 17/5/1.
 */
public class CustomerBizServiceTest extends SoaContextAwareTest {


    @Test
    public void getCustomerId(){
        CustomerBizService customerBizService = getBean(CustomerBizService.class);
        Long customerID = customerBizService.getG2CustomerID("10040007800");
        System.out.println(customerID);
        System.out.println("RemoteCacheUtils get value:"+customerID);//40007800
    }
    @Test
    public void getCusCardID(){
        G2ServiceInterfaceFacade facade =
                RemoteServiceFactory.getService(RemotingProtocol.HTTPINVOKER, G2ServiceInterfaceFacade.class);
        String result = facade.getCusIDCardNoByCusNo("10040007800");
        System.out.println(result);
    }
}

