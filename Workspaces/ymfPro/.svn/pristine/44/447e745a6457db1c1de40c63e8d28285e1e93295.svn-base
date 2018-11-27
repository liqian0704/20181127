package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.facade.app.AllianceAccountFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 展业账户业务
 * Author: wei.li
 * Date: 17/6/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AllianceAccountFacadeImpl extends AbstractFacade implements AllianceAccountFacade {

    @Override
    public OpenAccountResponse uploadImage(UploadImgRequest request) {
        return allianceAccountBiz.uploadImage(request);
    }

    @Override
    public OpenAccountResponse openAllianceAccount(OpenAccountRequest request) {
        return allianceAccountBiz.openAllianceAccount(request);
    }

    @Override
    public ShareResponse autoRegAlliance(BaseRequest request) {
        return allianceAccountBiz.autoRegAlliance(request);
    }
}
