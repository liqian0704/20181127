package com.yeepay.g3.core.ymf.facade.impl.trade;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.PayNotifyRequest;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.facade.PayNotifyFacade;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import org.junit.Test;

import java.util.Date;

/**
 * Created by dongxulu on 16/12/12.
 */
public class LaikePayNotifyFacadeTest extends SoaContextAwareTest {

    @Test
    public void purchasePayNotifyTest(){
        PayNotifyFacade service = getService(PayNotifyFacade.class);
        PayNotifyRequest request = new PayNotifyRequest();
        request.setExternalSystem(ExternalSystem.LIKER);
        request.setMerchantNo("10040007800");
        request.setOrderAmount(new Amount(0.01));
        request.setOrderNo("PY1482736573065");
//        收据
        request.setPayReceipt("622480767535");
        request.setPayTime(new Date());
        request.setPaySource(PaySource.WECHAT);
        request.setTotalAmount(new Amount(0.01));
        request.setRealPayAmount(new Amount(0.01));
        BaseResponse response = service.pushPayMsg2APP(request);
        System.out.println(response.toString());
    }

}
