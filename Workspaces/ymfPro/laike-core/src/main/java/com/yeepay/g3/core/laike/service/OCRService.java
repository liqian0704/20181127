package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.facade.laike.dto.BankCardInfo;
import com.yeepay.g3.facade.laike.dto.IdCardInfo;

/**
 * Description: OCR服务
 * Author: wei.li
 * Date: 17/6/15
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface OCRService {

    /**
     * OCR身份证识别(不做认证)
     *
     * @return
     */
    IdCardInfo getIdCardInfo(String idCardImageBase64, String memberNo);

    /**
     * OCR银行卡识别(不做认证)
     *
     * @return
     */
    BankCardInfo getBankCardInfo(String bankCardImageBase64, String memberNo);
}
