package com.yeepay.g3.core.ymf.biz.impl.qrCode;

import com.yeepay.g3.core.ymf.biz.qrCode.QrCodeBizService;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.qrcode.QrRelation;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.core.ymf.service.qrcode.QrRelationService;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongxulu on 16/12/8.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class, timeout = 30)
public class QrCodeBizServiceImpl implements QrCodeBizService {
    @Autowired
    private QrRelationService qrRelationService;
    @Autowired
    private QrCodeService qrCodeService;
    @Override
    public void createQrCodeWithRelation(QrRelation qrRelation) {
        int  qrCount = qrRelation.getQrcount();
        List<QRCode> qrCodeList = new ArrayList<QRCode>();
        for(int i=0;i<qrCount;i++){
            QRCode qrCode = new QRCode();
            qrCode.setBusinessType(BusinessType.STANDARD);
            qrCode.setRelationId(qrRelation.getRequestId());
            qrCodeList.add(qrCode);
        }
        qrCodeService.batchCreateQrCode(qrCodeList);
        qrRelationService.create(qrRelation);
    }

    @Override
    public QRCode  signedPaperQrcode(String customerNumber) {
        QRCode qrCode = new QRCode();
        qrCode.setCustomerNumber(customerNumber);
        qrCode.setBusinessType(BusinessType.STANDARD);
        qrCodeService.saveSignedPaperQrcode(qrCode);
        return qrCode;

    }
}
