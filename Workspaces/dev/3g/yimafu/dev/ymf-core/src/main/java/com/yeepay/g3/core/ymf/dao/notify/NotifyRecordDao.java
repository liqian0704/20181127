package com.yeepay.g3.core.ymf.dao.notify;

import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dongxulu on 16/12/29.
 */
@Repository
public interface  NotifyRecordDao {

    List<NotifyRecord> getByCustomerAndSattus(@Param("customerNumber") String customerNumber,
                                              @Param("status") Status status);
}
