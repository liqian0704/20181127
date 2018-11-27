package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.AppErrorMsgRequset;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.facade.AppErrorMsgFacade;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: wei.li
 * Date: 17/2/9
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AppErrorMsgFacadeImpl extends AbstractFacade implements AppErrorMsgFacade {

    @Override
    public BaseResponse saveAppErrorMsg(AppErrorMsgRequset request) {
        return appErrorMsgBiz.save(request);
    }
}
