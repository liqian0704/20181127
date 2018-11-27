package com.yeepay.g3.core.laike.event;

import com.yeepay.g3.core.laike.service.NotifyService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Description: 发送op表单注册用户的默认密码
 * Author: wei.li
 * Date: 17/3/5
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class SendPwdHandler extends BaseEventListener {

    private static Logger LOGGER = LoggerFactory.getLogger(SendPwdHandler.class);

    @Autowired
    private NotifyService notifyService;

    @Override
    public String getListenedEventName() {
        return ConstantUtil.SEND_PWD_EVENT;
    }

    @Override
    public void doAction(Object... args) {
        try{
            CheckUtils.notEmpty(args, "异步请求参数");
            Map<String, String> map = (Map<String, String>) args[0];
            LOGGER.info("SEND_PWD_EVENT,map:{" + map + "}");
            Set<String> keySet = map.keySet();
            for(String key : keySet){
                notifyService.sendCustomSMS(key,"您已经成功注册来客，初始密码为：" + map.get(key) + "，请尽快修改。");
            }
        } catch (Exception e) {
            LOGGER.error("error in QuerySettleEvent",e);
        }
    }
}

