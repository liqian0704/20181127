package com.yeepay.g3.core.ymf.biz.qrcode;

import com.yeepay.g3.core.ymf.biz.qrCode.QrCodeBizService;
import com.yeepay.g3.core.ymf.entity.qrcode.QrRelation;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import org.junit.Test;

import java.util.Date;

/**
 * Created by dongxulu on 16/12/8.
 */
public class QrCodeBizServiceTest extends ApplicationContextAwareTest {

    @Test
    public void insert(){
        QrCodeBizService service =getBean(QrCodeBizService.class);

        QrRelation re = new QrRelation();
        String request = ""+System.nanoTime();
        System.out.println(request);
        re.setRequestId(request);
        re.setStatus(OrderStatus.INIT);
        re.setQrcount((short)10);
        re.setCreateTime(new Date());
        re.setAgentNumber("1004007800");
        service.createQrCodeWithRelation(re);
    }

}
