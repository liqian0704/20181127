package com.yeepay.g3.core.ymf.facade;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.facade.CompensateAndNotifyFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/5/8
 * @Time: 下午2:29
 */
public class CompensateAndNotifyFacadeTest extends AnnotationContextAwareTest {
    @Autowired
    CompensateAndNotifyFacade compensateAndNotifyFacade;

    @Test
    public void testCompensateAndNotify() {
        ResponseMessage responseMessage = compensateAndNotifyFacade.compensateAndNotify("446480767531");
    }
}
