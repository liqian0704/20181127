package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.CardService;
import com.yeepay.g3.facade.cwh.enumtype.BankCardType;
import com.yeepay.g3.facade.cwh.enumtype.BindCardStatus;
import com.yeepay.g3.facade.cwh.param.BindCardDTO;
import com.yeepay.g3.facade.laike.dto.BankCardInfo;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.ncmember.dto.GetUsableRespDTO;
import com.yeepay.g3.facade.ncmember.dto.MerchantUserDTO;
import com.yeepay.g3.facade.ncmember.exception.ParameterInvalidException;
import com.yeepay.g3.facade.trade.bankinterface.nocard.enums.CardTypeEnum;
import com.yeepay.g3.facade.trade.bankinterface.nocard.result.CardBinResultDTO;
import com.yeepay.g3.utils.common.ValidateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Description: 卡账户及卡信息相关服务
 * Author: wei.li
 * Date: 17/4/24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class CardServiceImpl extends AbstractService implements CardService {


    private static String IDENTITY_TYPE = "USER_ID";//用户标识类型

    @Override
    public boolean hasBindCard(String merchantNo, String memberNo) {
        try {
            MerchantUserDTO merchantUserDTO = new MerchantUserDTO();
            merchantUserDTO.setMerchantNo(merchantNo);
            merchantUserDTO.setIdentityType(IDENTITY_TYPE);
            merchantUserDTO.setIdentityId(memberNo);
            GetUsableRespDTO respDTO = bindCardFacade.getUsableBindList(merchantUserDTO);
            if (null != respDTO.getUsableBindCard()) {
                for (BindCardDTO cardDTO : respDTO.getUsableBindCard()) {
                    if (BindCardStatus.VALID.equals(cardDTO.getStatus()) && (BankCardType.CREDITCARD.equals(cardDTO.getBankCardType()) || BankCardType.QUASI_CREDIT.equals(cardDTO.getBankCardType()))) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ParameterInvalidException e) {
            throw new LaikeSysException(ErrorCode.NC_MEMBER_PARAM_ERROR, e);
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.NC_MEBER_UNKNOW_EXCEPTION, e);
        }
    }

    @Override
    public BankCardInfo getBankCode(String bankCardNo) {
        try {
            if (ValidateUtils.isBankCard(bankCardNo)) {
                BankCardInfo bankCardInfo = new BankCardInfo();
                CardBinResultDTO cardBinResultDTO = bankInterfaceNoCardFacade.getCardBinInfo(bankCardNo);
                if (null != cardBinResultDTO) {
                    if (CardTypeEnum.DEBIT.equals(cardBinResultDTO.getCardType())) {
                        bankCardInfo.setBankCode(transferBankCode(cardBinResultDTO.getBankId()));
                        bankCardInfo.setBankName(cardBinResultDTO.getBankName());
                        bankCardInfo.setCardName(cardBinResultDTO.getCardName());
                        return bankCardInfo;
                    }
                    throw new LaikeSysException(ErrorCode.CARD_TYPE_IS_ILLEGAL);
                }
                throw new LaikeSysException(ErrorCode.UNKNOWN_CARD_INFO);
            }
            throw new LaikeSysException(ErrorCode.BANK_NO_ILLEGAL);
        } catch (LaikeSysException e) {
            throw e;
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.BANK_TRADE_UNKNOW_EXCEPTION);
        }
    }

    /**
     * 展业--bankCode转换
     *
     * @param bankCode
     * @return
     */
    private String transferBankCode(String bankCode) {
        String retBankCode = ((HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_BANK_CODE_TRANSFER)).get(bankCode);
        if (StringUtils.isBlank(retBankCode)) {
            retBankCode = bankCode;
        }
        return retBankCode;
    }
}
