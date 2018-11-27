package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.AttachmentsRequest;
import com.yeepay.g3.facade.laike.dto.OpenAccountRequest;
import com.yeepay.g3.facade.laike.dto.OpenAccountResponse;

/**
 * Description: 新入网业务接口
 * Author: wei.li
 * Date: 17/8/30
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface NewOpenAccountBiz {

    /**
     * 提交/修改开户基本信息(来客1.1.1小微商户入网使用)
     *
     * @param request
     * @return
     */
    OpenAccountResponse gatherNewBaseInfo(OpenAccountRequest request);

    /**
     * 提交/修改结算信息(来客1.1.1小微商户入网使用)
     *
     * @param request
     * @return
     */
    OpenAccountResponse gatherNewSettleInfo(OpenAccountRequest request);

    /**
     * 提交/修改附件信息(来客1.1.1小微商户入网使用)
     *
     * @param request
     * @return
     */
    OpenAccountResponse gatherNewAttachments(AttachmentsRequest request);
}
