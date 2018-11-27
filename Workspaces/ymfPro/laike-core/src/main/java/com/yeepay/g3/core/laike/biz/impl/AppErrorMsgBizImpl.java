package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.AppErrorMsgBiz;
import com.yeepay.g3.core.laike.entity.AppErrorMsgEntity;
import com.yeepay.g3.facade.laike.dto.AppErrorMsgRequset;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: wei.li
 * Date: 17/2/9
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AppErrorMsgBizImpl extends AbstractBiz implements AppErrorMsgBiz{

    @Override
    public BaseResponse save(AppErrorMsgRequset appErrorMsgRequset) {
        AppErrorMsgEntity appErrorMsgEntity = new AppErrorMsgEntity();
        appErrorMsgEntity.setMemberNo(appErrorMsgRequset.getMemberNo());
        appErrorMsgEntity.setAppVersionId(appErrorMsgRequset.getVersionId());
        appErrorMsgEntity.setPhoneManufacturer(appErrorMsgRequset.getPhoneManufacturer());
        appErrorMsgEntity.setPhoneModel(appErrorMsgRequset.getPhoneModel());
        appErrorMsgEntity.setReqInterface(appErrorMsgRequset.getReqInterface());
        appErrorMsgEntity.setLogContent(appErrorMsgRequset.getLogContent());
        appErrorMsgEntity.setSdk(appErrorMsgRequset.getSdk());
        appErrorMsgEntity.setCurrentActivity(appErrorMsgRequset.getCurrentActivity());
        appErrorMsgEntity.setLogLevel(appErrorMsgRequset.getLogLevel());
        appErrorMsgEntity.setImei(appErrorMsgRequset.getImei());
        appErrorMsgService.save(appErrorMsgEntity);
        return new BaseResponse();
    }
}
