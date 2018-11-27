package com.yeepay.g3.core.ymf.biz.opr;

import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import org.junit.Test;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/2/23
 * @Time: 下午6:18
 */
public class OprUrlBizImplTest extends SoaContextAwareTest {

    @Test
    public void testStanardUrl() {
        OprUrlBiz bean = getBean(OprUrlBiz.class);
        try {
            String s = bean.standardCashier("10040007800",
                    "C7D747BA0BFDA60165ED78C08FAA0E79C37E4377EDF0128A4C48364422136366", "",
                    "", "");
            System.out.println("支付链接：" + s) ;
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }
}
