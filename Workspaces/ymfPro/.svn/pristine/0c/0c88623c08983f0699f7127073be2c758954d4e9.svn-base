package com.yeepay.g3.core.laike.event;

import com.yeepay.g3.core.laike.entity.S0RecordEntity;
import com.yeepay.g3.core.laike.service.NotifyService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: 通知ymf人工处理s0业务
 * Author: wei.li
 * Date: 17/4/28
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class SendS0MailHandler extends BaseEventListener {

    private static Logger LOGGER = LoggerFactory.getLogger(SendS0MailHandler.class);

    @Autowired
    private NotifyService notifyService;

    @Override
    public String getListenedEventName() {
        return ConstantUtil.SEND_S0_MAIL_EVENT;
    }

    @Override
    public void doAction(Object... objects) {

        LOGGER.info("[laike_sys] - [入参] - [SendS0MailHandler] - [" + objects + "]");
        long startTime = System.currentTimeMillis();
        try {
            String message = "objects:" + toString((S0RecordEntity) objects[0]) + "\n" + "e:" + objects[1];
            notifyService.sendErrorEmail("开通s0业务通知失败", message);
        } catch (Throwable e) {
            LOGGER.error("[laike_sys] - [异步调用异常] - [SendS0MailHandler] - errorMsg:", e);
        } finally {
            long time = (System.currentTimeMillis() - startTime);
            LOGGER.info("[laike_sys] - [用时] - [SendS0MailHandler] - 耗时[" + time + "]毫秒");
        }
    }

    private String toString(S0RecordEntity s0RecordEntity) {
        StringBuffer sb = new StringBuffer();
        sb.append("id=[" + s0RecordEntity.getId() + "]")
                .append("merchantNo=[" + s0RecordEntity.getMerchantNo() + "]")
                .append("memberNo=[" + s0RecordEntity.getMemberNo() + "]")
                .append("agentNo=[" + s0RecordEntity.getAgentNo() + "]")
                .append("operateType=[" + s0RecordEntity.getOperateType() + "]")
                .append("productStatus=[" + s0RecordEntity.getProductStatus() + "]");
        return sb.toString();
    }
}
