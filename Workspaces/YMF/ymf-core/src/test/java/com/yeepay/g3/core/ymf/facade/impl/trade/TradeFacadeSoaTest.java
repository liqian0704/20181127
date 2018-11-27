package com.yeepay.g3.core.ymf.facade.impl.trade;

import com.yeepay.g3.core.ymf.biz.trade.TradeBiz;
import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.core.ymf.support.ServletSupport;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierBaseDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.facade.TradeBaseFacade;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/22.
 */
public class TradeFacadeSoaTest extends SoaContextAwareTest {

    public void supplyRefund() throws Exception {

        TradeBaseFacade facade = RemoteServiceFactory.getService(
                "http://172.21.0.83:8003/nc-api-hessian/hessian/TradeCashierFacade", RemotingProtocol.HESSIAN,
                TradeBaseFacade.class);

        facade.refundRequest(null);

    }
    @Test
    public void queryOrder() {
        TradeCashierFacade facade = RemoteServiceFactory.getService(
                "http://172.21.0.83:8003/nc-api-hessian/hessian/TradeCashierFacade", RemotingProtocol.HESSIAN,
                TradeCashierFacade.class);
        TradeCashierBaseDTO request = new TradeCashierBaseDTO();
        try {
            request.setCustomerOrderId("123480767539");
            TradeCashierReplyDTO reply = facade.purchaseQuery(request);
        } catch (ParameterInvalidException e) {
            e.printStackTrace();
        }
    }

    public void testQueryOrder() {
        TradeFacade biz = RemoteServiceFactory.getService(TradeFacade.class);
        biz.supplyTodayOrder();
    }

}