package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.AppVersionRequset;
import com.yeepay.g3.facade.laike.dto.AppVersionResponse;
import com.yeepay.g3.facade.laike.facade.app.VersionFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 版本管理facade实现
 * Author: jiawen.huang
 * Date: 16/11/28
 * Time: 10:32
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class VersionFacadeImpl extends AbstractFacade implements VersionFacade {

	@Override
    public AppVersionResponse checkNew(AppVersionRequset request) {
        return appVersionBiz.checkNew(request);
    }
}
