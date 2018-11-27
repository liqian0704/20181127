package com.yeepay.g3.facade.ymf

import com.caucho.hessian.client.HessianProxyFactory
/**
 * Created by yp-tc-m-2574 on 16/12/1.
 */
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs
import com.yeepay.g3.utils.rmi.RemoteServiceFactory

class Test_queryOrder {
    private static String url = "http://172.21.0.83:8003/nc-api-hessian/hessian/TradeCashierFacade";

    public static void main(String[] args) {
        Test_queryOrder test_queryOrder =  new Test_queryOrder();
        test_queryOrder.orderQuery();

    }

    public void orderQuery(){
        HessianProxyFactory factory1 = new HessianProxyFactory();
        factory1.create(url);
        factory1.setReadTimeout(3000);


        OrderArgs order = new OrderArgs();
        order.setCustomerNumber("10040007800")
        order.setExternalId("123480767539")
        order.setCustomerOrderId("123480767539")
        RemoteServiceFactory factory = new RemoteServiceFactory();
        factory.init();

    }


}
