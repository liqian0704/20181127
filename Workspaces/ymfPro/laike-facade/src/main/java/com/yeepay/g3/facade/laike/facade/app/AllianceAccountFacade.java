package com.yeepay.g3.facade.laike.facade.app;

import com.yeepay.g3.facade.laike.dto.*;

/**
 * Description: 展业账户业务
 * Author: wei.li
 * Date: 17/6/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AllianceAccountFacade {

    /**
     * 上传资质图片
     *
     * @param request
     * @return
     */
    OpenAccountResponse uploadImage(UploadImgRequest request);

    /**
     * 展业资料提交入网
     *
     * @param request
     * @return
     */
    OpenAccountResponse openAllianceAccount(OpenAccountRequest request);

    /**
     * 自动注册推客
     *
     * @param request
     * @return
     */
    ShareResponse autoRegAlliance(BaseRequest request);
}
