package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAllianceAccRequest;

/**
 * Description: 展业账户业务
 * Author: wei.li
 * Date: 17/6/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AllianceAccountBiz {

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
     * 展业Submit状态开户单,提交入网
     * 展业Audting状态,提交入网
     */
    void gatherAlliancetoMer();


    /**
     * 展业更新开户单状态(boss用)
     *
     * @param request
     * @return
     */
    BaseResponse updateAllianceAccount(UpdateAllianceAccRequest request);

    /**
     * 查询商户来源(联盟用)
     *
     * @param merchantNo
     * @return
     */
    CustomerSourceResponse queryCustomerSource(String merchantNo);

    /**
     * 自动注册推客
     *
     * @param request
     * @return
     */
    ShareResponse autoRegAlliance(BaseRequest request);
}
