package com.yeepay.g3.core.ymf.service; /**
 * 类名称: QrCodeServiceTest <br>
 * 类描述: <br>
 *
 * @author: dongxu.lu
 * @since: 16/10/28 下午6:17
 * @version: 1.0.0
 */

import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.core.ymf.service.impl.sequence.SequenceGeneratorImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongxulu on 16/10/28.
 */
public class QrCodeServiceTest extends ApplicationContextAwareTest {
    private SequenceGeneratorImpl sequenceGeneratorImpl;
    @Test
    public void queryQrcode(){
        QrCodeService service = getBean(QrCodeService.class);
        QRCode code = service.getQrCodeById(32l);
        System.out.println(code.toString());
    }
    @Test
    public void batchCreate(){
        QrCodeService service = getBean(QrCodeService.class);
        List<QRCode> list = new ArrayList<QRCode>();
        sequenceGeneratorImpl=getBean(SequenceGeneratorImpl.class);
        for (int i = 0;i<5;i++){
            QRCode qrCode = new QRCode();
            qrCode.setCustomerNumber("10040011560");
            list.add(qrCode);
        }
        int a = service.batchCreateQrCode(list);
        System.out.println("本次创建二维码数量为:"+a);
    }
    @Test
    public void create(){
        QrCodeService service = getBean(QrCodeService.class);
        QRCode qrCode = new QRCode();
        qrCode.setCustomerNumber("10040011560");
        int a = service.saveQrCode(qrCode);
        System.out.println(a);
    }
}
