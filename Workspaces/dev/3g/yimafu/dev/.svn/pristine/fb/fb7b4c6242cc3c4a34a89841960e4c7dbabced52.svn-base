package com.yeepay.g3.core.ymf.service.impl.remit;

import com.yeepay.g3.core.ymf.dao.remit.RemittanceDao;
import com.yeepay.g3.core.ymf.dao.remit.RemittanceMapper;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.service.remit.RemittanceService;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by dongxulu on 17/4/24.
 */
@Service
public class RemittanceServiceImpl implements RemittanceService {
    @Autowired
    private RemittanceDao remittanceDao;
    @Autowired
    private RemittanceMapper remittanceMapper;
    @Override
    public int save(Remittance remittance) {
        remittance.setVersion(0);//版本默认为0
        return remittanceMapper.insert(remittance);
    }

    @Override
    public int update(Remittance remittance) {
        return remittanceMapper.updateByPrimaryKeyAndVersion(remittance);
    }

    @Override
    public Remittance findByID(Long id) {

        return remittanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public Remittance findByCustomerNumberAndCustomerOrderId(String customerNumber, String customerOrderID) {
        return remittanceMapper.findByCustomerNumberAndCustomerOrderId(customerNumber,customerOrderID);
    }

    @Override
    public Remittance findByYeepayOrderId(String yeepayOrderId) {
        return remittanceMapper.findByYeepayOrderId(yeepayOrderId);
    }

    @Override
    public List<Remittance> findByRemitStatus(RemitStatus remitStatus, Date begin, Date end, int startRow, int endRow) {
        return remittanceMapper.findByRemitStatus(remitStatus,begin,end,startRow,endRow);
    }

    @Override
    public int findByRemitStatusCount(RemitStatus remitStatus, Date begin, Date end) {
        return remittanceMapper.findByRemitStatusCount(remitStatus,begin,end);
    }
}
