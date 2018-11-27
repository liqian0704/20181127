package com.yeepay.g3.core.ymf.service.impl.notify;

import com.yeepay.g3.core.ymf.dao.notify.NotifyRecordDao;
import com.yeepay.g3.core.ymf.dao.notify.NotifyRecordMapper;
import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.core.ymf.service.notify.NotifyRecordeService;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dongxulu on 16/12/29.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class,timeout = 5)
public class NotifyRecordeServiceImpl implements NotifyRecordeService {

    @Autowired
    private NotifyRecordMapper notifyRecordMapper;
    @Autowired
    private NotifyRecordDao notifyRecordDao;
    @Override
    public int save(NotifyRecord record) {
        return notifyRecordMapper.insert(record);
    }

    @Override
    public int update(NotifyRecord record) {
        return notifyRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public NotifyRecord getByid(Long id) {
        return notifyRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<NotifyRecord> getByCustomerAndSattus(String customerNumber, Status status) {
        return notifyRecordDao.getByCustomerAndSattus(customerNumber,status);
    }
}
