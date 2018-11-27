package com.yeepay.g3.core.ymf.facade.orderProcessor;

import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import org.junit.Test;

/**
 * Created by dongxulu on 16/12/30.
 */
public class HolidayFacadeTest extends SoaContextAwareTest {
    @Test
    public void invokeOrderPorcessor(){
        TradeFacade facade = getBean(TradeFacade.class);
        facade.synHolidaySettle(null);
    }


}
