package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.AppErrorMsgEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.AppErrorMsgService;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author: wei.li
 * Date: 17/2/9
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class AppErrorMsgServiceImpl  extends AbstractService implements AppErrorMsgService {

    @Override
    public void save(AppErrorMsgEntity appErrorMsgEntity) {
        try {
            appErrorMsgRepository.save(appErrorMsgEntity);
        } catch (DuplicateKeyException e) {
            throw new LaikeSysException(ErrorCode.MSG_CREATE_REPEAT);
        }
    }

}
