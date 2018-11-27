package Mitang_TestCase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.OrderInfoDaemonFacade;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


/**
 * Created by yp-tc-2646 on 18/3/5.
 * 计费定时
 */
public class Mitang05_OrderInfoDaemonTask {
    String envFlag=CommPro.env;
    String envuri=CommPro.getEnvURI(envFlag);
@Test
    public void addPayCompanyFee(){
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        OrderInfoDaemonFacade orderInfoDaemonFacade = null;
        System.out.println(envuri);
        try {
            try {
                orderInfoDaemonFacade= (OrderInfoDaemonFacade)hessianProxyFactory.create(OrderInfoDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/OrderInfoDaemonFacade");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } finally {
            }
        } finally {
        }

        boolean respose1=orderInfoDaemonFacade.queryOrderInfo2MTOrderFeeRecord(null,null,null,null,null,"10427813356",null);
       // boolean response2=orderInfoDaemonFacade.queryOrderInfo2PayCompanyOrderFeeRecord(null,null,null,null,null,"10427813356",null);
        boolean response3=orderInfoDaemonFacade.queryOrderInfo2MTOrderCalFee(null,null,null,null,null,"10427813356",null);
       // boolean response4=orderInfoDaemonFacade.queryOrderInfo2PayCompanyOrderCalFee(null,null,null,null,null,"10427813356",null);
        System.out.println(respose1+"---"+response3);
    }}
