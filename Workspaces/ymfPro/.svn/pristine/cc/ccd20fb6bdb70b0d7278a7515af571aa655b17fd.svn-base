package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.OCRService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.auth2.bundle.exception.Auth2Exception;
import com.yeepay.g3.facade.auth2.ocr.dto.BankCardOCRRequest;
import com.yeepay.g3.facade.auth2.ocr.dto.BankCardOCRResponse;
import com.yeepay.g3.facade.auth2.ocr.dto.IdCardOCRRequest;
import com.yeepay.g3.facade.auth2.ocr.dto.IdCardOCRResponse;
import com.yeepay.g3.facade.laike.dto.BankCardInfo;
import com.yeepay.g3.facade.laike.dto.IdCardInfo;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: OCR服务
 * Author: wei.li
 * Date: 17/6/16
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class OCRServiceImpl extends AbstractService implements OCRService {

    @Override
    public IdCardInfo getIdCardInfo(String idCardImageBase64, String memberNo) {
        try {
            Map<String, String> ocrSwitch = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_OCR_SWITCH);
            IdCardOCRRequest request = new IdCardOCRRequest();
            IdCardInfo idCardInfo = new IdCardInfo();
            request.setIdCardImageBase64(idCardImageBase64);
            request.setRequestSystem(ConstantUtil.LIKER);
            request.setRequestCustomerId(memberNo);
            request.setRequestFlowId(BizPrefixEnum.OCR.getValue() + String.valueOf(System.currentTimeMillis()));
            IdCardOCRResponse response = idCardOCRFacade.recognize(request);
            if (response.getStatus().equals("SUCCESS")) {
                idCardInfo.setIdCardNo(response.getIdCardNumber());
                idCardInfo.setName(response.getName());
                idCardInfo.setValidDate(response.getValidDate());
            } else if (ocrSwitch.get("ID_CARD_SWITCH").equals("1")) {
                throw new LaikeSysException(ErrorCode.OCR_RECOGNIZE_FAIL);
            }
            return idCardInfo;
        } catch (LaikeSysException e) {
            throw e;
        } catch (Auth2Exception e) {
            throw new LaikeSysException(e.getDefineCode(), e.getMessage());
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.OCR_UNKNOW_EXCEPTION, e);
        }
    }

    @Override
    public BankCardInfo getBankCardInfo(String bankCardImageBase64, String memberNo) {
        try {
            Map<String, String> ocrSwitch = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_OCR_SWITCH);
            BankCardOCRRequest request = new BankCardOCRRequest();
            BankCardInfo bankCardInfo = new BankCardInfo();
            request.setBankCardImageBase64(bankCardImageBase64);
            request.setRequestSystem(ConstantUtil.LIKER);
            request.setRequestCustomerId(memberNo);
            request.setRequestFlowId(BizPrefixEnum.OCR.getValue() + String.valueOf(System.currentTimeMillis()));
            BankCardOCRResponse response = bankCardOCRFacade.recognize(request);
            if (response.getStatus().equals("SUCCESS")) {
                bankCardInfo.setCardNo(response.getBankCardNumber());
                bankCardInfo.setBankName(response.getBankName());
                bankCardInfo.setCardName(response.getBankCardName());
                //测试用,QA环境全是FAILURE
            } else if (ocrSwitch.get("BANK_CARD_SWITCH").equals("1")) {
                throw new LaikeSysException(ErrorCode.OCR_RECOGNIZE_FAIL);
            }
            return bankCardInfo;
        } catch (LaikeSysException e) {
            throw e;
        } catch (Auth2Exception e) {
            throw new LaikeSysException(e.getDefineCode(), e.getMessage());
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.OCR_UNKNOW_EXCEPTION, e);
        }
    }
}
