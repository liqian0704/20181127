package com.yeepay.g3.core.ymf.service.notify;

import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.facade.ymf.enumtype.Status;

/**
 * Created by dongxulu on 17/1/4.
 */
public interface NotifyBizService {
    /**
     * 来客通知方法
     *  NotifyRecord 需要传已经持久化后的实体需包含id信息
     *
     */
    public Status asynNotify(NotifyRecord record);
}
