package com.yeepay.g3.core.ymf.biz.qrCode;

import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.qrcode.QrRelation;

/**
 * Created by dongxulu on 16/12/8.
 */
public interface QrCodeBizService {

    /**
     * 根据关系创建二维码
     * @param qrRelation
     */
     void createQrCodeWithRelation(QrRelation qrRelation);

    /**
     * 创建台签牌
     * @param
     * @return
     */
    QRCode  signedPaperQrcode(String customerNumber);
}
