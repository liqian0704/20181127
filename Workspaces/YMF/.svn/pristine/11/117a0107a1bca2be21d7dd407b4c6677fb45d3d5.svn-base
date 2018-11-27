package com.yeepay.g3.core.ymf.service.impl.qrcode;

import com.yeepay.g3.core.ymf.dao.qrcode.QrRelationDao;
import com.yeepay.g3.core.ymf.dao.qrcode.QrRelationMapper;
import com.yeepay.g3.core.ymf.entity.qrcode.QrRelation;
import com.yeepay.g3.core.ymf.service.qrcode.QrRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dongxulu on 16/12/8.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class,timeout = 30)
public class QrRelationServiceImpl implements QrRelationService {
    @Autowired
    private QrRelationMapper qrRelationMapper;
    @Autowired
    private QrRelationDao qrRelationDao;

    @Override
    public void create(QrRelation qrRelation) {
        qrRelationMapper.insert(qrRelation);
    }

    @Override
    public int update(QrRelation qrRelation) {
        return qrRelationMapper.updateByPrimaryKey(qrRelation);
    }

    @Override
    public List<QrRelation> getByAgentNumberAndRequestId(String agentNumber, String requestID) {

        return qrRelationDao.getByAgentNumberAndRequestId(agentNumber,requestID);
    }

    @Override
    public List<QrRelation> getByCustomerNumberAndRequestId(String customerNumber, String requestID) {
        return qrRelationDao.getByCustomerNumberAndRequestId(customerNumber,requestID);
    }

    @Override
    public List<QrRelation> getBySalesNameAndRequestId(String salesName, String requestID) {
        return qrRelationDao.getBySalesNameAndRequestId(salesName,requestID);
    }

    @Override
    public QrRelation getByID(Long id) {
        return qrRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public QrRelation getByQrCodeID(String qrCodeID) {

        return qrRelationDao.getByQrCodeID(qrCodeID);
    }
}
