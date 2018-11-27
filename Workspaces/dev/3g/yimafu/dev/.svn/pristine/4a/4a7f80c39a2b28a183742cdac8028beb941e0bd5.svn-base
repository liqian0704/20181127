package com.yeepay.g3.core.ymf.service.notify;

import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.sp.NotifyType;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by dongxulu on 16/12/30.
 */
public class NotifyRecordServiceTest extends ApplicationContextAwareTest {
    @Test
    public void save(){
        NotifyRecordeService notifyRecordeService = getBean(NotifyRecordeService.class);
        NotifyRecord record = new NotifyRecord();
        record.setCustomerNumber("1004007800");
        record.setExternalId("10023122");
        record.setStatus(Status.FAIL);
        Date date = new Date();
        record.setCreateTime(date);
        record.setLastNotifyTime(date);
        record.setRemark("test");
        record.setNotifyType(NotifyType.AUTO);
        notifyRecordeService.save(record);
    }
    @Test
    public void select(){
        NotifyRecordeService notifyRecordeService = getBean(NotifyRecordeService.class);
        List<NotifyRecord> list = notifyRecordeService.getByCustomerAndSattus("1004007800",Status.FAIL);
        for(NotifyRecord record:list){
            System.out.println(record.toString());
        }


    }
}
