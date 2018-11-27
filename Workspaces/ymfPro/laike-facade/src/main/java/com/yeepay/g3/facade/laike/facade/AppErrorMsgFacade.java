package com.yeepay.g3.facade.laike.facade;

import com.yeepay.g3.facade.laike.dto.AppErrorMsgRequset;
import com.yeepay.g3.facade.laike.dto.BaseResponse;

/**
 * Description: App错误信息facade
 * Author: wei.li
 * Date: 17/2/9
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Deprecated
public interface AppErrorMsgFacade {

    /**
     * 保存App错误信息
     *
     * @param request
     * @return
     */
    BaseResponse saveAppErrorMsg(AppErrorMsgRequset request);

}
