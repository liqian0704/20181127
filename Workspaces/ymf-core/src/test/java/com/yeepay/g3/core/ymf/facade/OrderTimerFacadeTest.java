package com.yeepay.g3.core.ymf.facade;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.facade.OrderTimerFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单定时
 * @Author: xiaobin.liu
 * @Date: 17/6/26
 * @Time: 下午4:02
 */
public class OrderTimerFacadeTest extends AnnotationContextAwareTest {
    @Autowired
    private OrderTimerFacade orderTimerFacade;

    @Test
    public void testClossOrder() {
        //跑今天第一次
        orderTimerFacade.clossOrder(1,0,10);
        //跑昨天
        //orderTimerFacade.clossOrder(1,0,10);
    }
}
