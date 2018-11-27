package com.yeepay.g3.core.ymf.facade.laike;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.facade.laike.RemitInfoQueryFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 打款定时测试
 * @Author: xiaobin.liu
 * @Date: 17/6/8
 * @Time: 下午6:06
 */
public class RemitInfoQueryFacadeTest extends AnnotationContextAwareTest {

    @Autowired
    private RemitInfoQueryFacade remitInfoQueryFacade;

    @Test
    public void testDoRemit() {
        remitInfoQueryFacade.doRemit(2);



    }

    /**
     * 测试 定时查询打款结果
     */
    @Test
    public void testQueryRemitInfo() {
        remitInfoQueryFacade.queryRemitInfo(1, 4000);
    }
}
