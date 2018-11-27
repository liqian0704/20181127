package com.yeepay.g3.core.laike.event;

import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.core.laike.service.NotifyService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Description: 展业app审核邮件
 * Author: wei.li
 * Date: 17/6/15
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class SendAuditMailHandler extends BaseEventListener {

    private static Logger LOGGER = LoggerFactory.getLogger(SendAuditMailHandler.class);

    private static String ALLIANCE_APPLY_TARGET = "推客汇审核邮件";

    @Autowired
    private NotifyService notifyService;

    @Override
    public String getListenedEventName() {
        return ConstantUtil.SEND_AUDIT_MAIL_EVENT;
    }

    @Override
    public void doAction(Object... objects) {
        LOGGER.info("[laike_sys] - [入参] - [SendAuditMailHandler] - [" + objects + "]");
        long startTime = System.currentTimeMillis();
        try {
            Map<String, Object> messages = Maps.newHashMap();
            String message = "您好，商户:" + objects[0] + "人证可能不一致，请您审核。\n";
            messages.put("title", ALLIANCE_APPLY_TARGET + new Date());
            messages.put("message", message);
            notifyService.sendOperationEmail(ConfigEnum.ALLIANCE_OPERATION_CONTACT_EMAIL, messages, null, null);
        } catch (Throwable e) {
            LOGGER.error("[laike_sys] - [异步调用异常] - [SendAuditMailHandler] - errorMsg:", e);
        } finally {
            long time = (System.currentTimeMillis() - startTime);
            LOGGER.info("[laike_sys] - [用时] - [SendAuditMailHandler] - 耗时[" + time + "]毫秒");
        }
    }
}
