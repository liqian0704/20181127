package com.yeepay.g3.core.laike.dtoparser.impl;

import com.google.common.collect.Lists;
import com.yeepay.g3.core.laike.dtoparser.DTOConvert2;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.AttachmentEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.dto.OpenAccountResponse;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

/**
 * Description:
 * Author: wei.li
 * Date: 17/9/2
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class OpenAccountDTOConvert implements DTOConvert2<OpenAccountResponse, AccountOpenEntity, AttachmentEntity, UserEntity> {

    @Override
    public List convert2Entity(OpenAccountResponse openAccountResponse) {
        List list = Lists.newArrayList();
        AccountOpenEntity accountOpenEntity = new AccountOpenEntity();
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        UserEntity userEntity = new UserEntity();
        try {
            BeanUtils.copyProperties(accountOpenEntity, openAccountResponse);
            list.add(accountOpenEntity);
            BeanUtils.copyProperties(attachmentEntity, openAccountResponse);
            list.add(attachmentEntity);
            BeanUtils.copyProperties(userEntity, openAccountResponse);
            list.add(userEntity);
            return list;
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
    }

    @Override
    public OpenAccountResponse convert2DTO(AccountOpenEntity accountOpenEntity, AttachmentEntity attachmentEntity, UserEntity userEntity) {
        OpenAccountResponse response = new OpenAccountResponse();
        try {
            if (null != attachmentEntity) {
                BeanUtils.copyProperties(response, attachmentEntity);
            }
            if (null != accountOpenEntity) {
                BeanUtils.copyProperties(response, accountOpenEntity);
            }
            if (null != userEntity) {
                BeanUtils.copyProperties(response, userEntity);
                if (StringUtils.isBlank(userEntity.getInviteCode())) {
                    response.setInviteCode(null);
                }
                if (StringUtils.isBlank(userEntity.getMerchantNo())) {
                    response.setMerchantNo(accountOpenEntity.getMerchantNo());
                }
            }
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.ACCOUNT_CONVERT_DTO_EXCEP, e);
        }
        return response;
    }

}
