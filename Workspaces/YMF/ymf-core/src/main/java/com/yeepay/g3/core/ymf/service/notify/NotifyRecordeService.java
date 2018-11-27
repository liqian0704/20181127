package com.yeepay.g3.core.ymf.service.notify;

import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.facade.ymf.enumtype.Status;

import java.util.List;

/**
 * Created by dongxulu on 16/12/29.
 * 异常记录表
 */
public interface NotifyRecordeService {


    int save(NotifyRecord record);
    int update(NotifyRecord record);

    /**
     *
     * @param id
     * @return
     */
    NotifyRecord getByid(Long id);

    /**
     * 根据商编与状态查询通知记录
     * @param customerNumber
     * @param status
     * @return
     */
    List<NotifyRecord> getByCustomerAndSattus(String customerNumber,Status status);

}
