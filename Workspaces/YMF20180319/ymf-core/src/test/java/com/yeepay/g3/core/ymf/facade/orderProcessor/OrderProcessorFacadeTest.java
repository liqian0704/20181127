package com.yeepay.g3.core.ymf.facade.orderProcessor;

import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.facade.opr.dto.yop.order.YopCreateOrderReqDTO;
import com.yeepay.g3.facade.opr.dto.yop.order.YopCreateOrderResDTO;
import com.yeepay.g3.facade.opr.facade.yop.YopOrderFacade;
import org.junit.Test;

/**
 * Created by dongxulu on 16/12/30.
 */
public class OrderProcessorFacadeTest extends SoaContextAwareTest {
    @Test
    public void invokeOrderPorcessor(){
        YopOrderFacade facade = getService(YopOrderFacade.class);
        YopCreateOrderReqDTO reqDto = new YopCreateOrderReqDTO();
        reqDto.setParentMerchantNo("10040007800");
        reqDto.setMerchantNo("10040007800");
        reqDto.setOrderAmount("0.01");
        YopCreateOrderResDTO resDto = facade.createOrder(reqDto);
        System.out.println(resDto.toString());

    }


}
