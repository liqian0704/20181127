package com.yeepay.g3.core.laike.event;

import com.yeepay.g3.core.laike.biz.SettleS0Biz;
import com.yeepay.g3.core.laike.service.NotifyService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.laike.dto.OpenProductionRequest;
import com.yeepay.g3.facade.laike.enumtype.AppProductEnum;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 异步开通s0
 * Author: wei.li
 * Date: 17/7/11
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class OpenSettleS0Handler extends BaseEventListener {

    private static Logger LOGGER = LoggerFactory.getLogger(OpenSettleS0Handler.class);

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private SettleS0Biz settleS0Biz;

    @Override
    public String getListenedEventName() {
        return ConstantUtil.OPEN_SETTLE_S0_EVENT;
    }

    @Override
    public void doAction(Object... objects) {
        long startTime = System.currentTimeMillis();
        String memberNo = (String) objects[0];
        LOGGER.info("[laike_sys] - [入参] - [OpenSettleS0Handler] - [" + memberNo + "]");
        try {
            OpenProductionRequest request = new OpenProductionRequest();
            request.setMemberNo(memberNo);
            request.setProductCode(AppProductEnum.S0_SETTLE);
            settleS0Biz.open(request);
        } catch (Throwable e) {
            String message = "objects:" + memberNo + "\n" + "e:" + e.getMessage();
            notifyService.sendErrorEmail("自动开通S0异常", message);
            LOGGER.error("[laike_sys] - [异步调用异常] - [OpenSettleS0Handler] - errorMsg:", e);
        } finally {
            long time = (System.currentTimeMillis() - startTime);
            LOGGER.info("[laike_sys] - [用时] - [OpenSettleS0Handler] - 耗时[" + time + "]毫秒");
        }
    }

}
