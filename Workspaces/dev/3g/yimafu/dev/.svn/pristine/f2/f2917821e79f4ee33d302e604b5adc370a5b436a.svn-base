package com.yeepay.g3.core.ymf.service.qrcode;

import com.yeepay.g3.core.ymf.entity.qrcode.QrRelation;

import java.util.List;

/**
 * Created by dongxulu on 16/12/8.
 */
public interface QrRelationService {

    void create(QrRelation qrRelation);

    int update(QrRelation qrRelation);

    List<QrRelation> getByAgentNumberAndRequestId(String agentNumber, String requestID);

    List<QrRelation> getByCustomerNumberAndRequestId(String customerNumber, String requestID);

    List<QrRelation> getBySalesNameAndRequestId(String salesName, String requestID);

    QrRelation getByID (Long id);

    QrRelation getByQrCodeID(String qrCodeID);

}
