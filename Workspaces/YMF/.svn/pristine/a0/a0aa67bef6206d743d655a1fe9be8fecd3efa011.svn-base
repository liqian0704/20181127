package com.yeepay.g3.core.ymf.facade.impl.trade;

import com.yeepay.g3.core.ymf.junit.RmiContextAwareTest;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import org.junit.Test;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/22.
 */
public class TradeFacadeImplTest extends RmiContextAwareTest {

    @Test
    public void supplyTodayOrder() throws Exception {
        TradeFacade facade = getBean(TradeFacade.class);
        facade.supplyTodayOrder();
    }

    @Test
    public void supplyYesterdayOrder() throws Exception {
        TradeFacade facade = getBean(TradeFacade.class);
        facade.supplyYesterdayOrder();
    }

    @Test
    public void supplyBeforeOrder() throws Exception {
        TradeFacade facade = getBean(TradeFacade.class);
        facade.supplyBeforeOrder();
    }

    @Test
    public void supplyRefund() throws Exception {
        TradeFacade facade = getBean(TradeFacade.class);
        facade.supplyRefund();
    }

    @Test
    public void remote() {
        TradeCashierFacade facade = getBean(TradeCashierFacade.class);
        TradeCashierReplyDTO request = new TradeCashierReplyDTO();
        try {
            facade.purchaseQuery(request);
        } catch (ParameterInvalidException e) {
            e.printStackTrace();
        }
    }
}