package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.facade.LikerScheduleFacade;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/5/24
 * Time: 14:46
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class LikerScheduleFacadeImpl extends AbstractFacade implements LikerScheduleFacade {

	@Override
    public void gatherDailyDeviceApply() {
        scheduleBiz.gatherDailyDeviceApply();
    }

    @Override
    public void gatherAlliancetoMer() {
        allianceAccountBiz.gatherAlliancetoMer();
    }
}
