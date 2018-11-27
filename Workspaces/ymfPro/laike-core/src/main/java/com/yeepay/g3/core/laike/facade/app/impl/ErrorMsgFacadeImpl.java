package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.AppErrorMsgRequset;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.facade.app.ErrorMsgFacade;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: wei.li
 * Date: 17/2/9
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class ErrorMsgFacadeImpl extends AbstractFacade implements ErrorMsgFacade {

    @Override
    public BaseResponse saveAppErrorMsg(AppErrorMsgRequset request) {
        return appErrorMsgBiz.save(request);
    }
}
