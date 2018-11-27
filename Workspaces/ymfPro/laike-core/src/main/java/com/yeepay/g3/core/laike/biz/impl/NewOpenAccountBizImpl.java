package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.NewOpenAccountBiz;
import com.yeepay.g3.core.laike.dtoparser.impl.OpenAccountDTOConvert;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.AttachmentEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.utils.BeanUtil;
import com.yeepay.g3.facade.laike.dto.AttachmentsRequest;
import com.yeepay.g3.facade.laike.dto.BranchBankInfo;
import com.yeepay.g3.facade.laike.dto.OpenAccountRequest;
import com.yeepay.g3.facade.laike.dto.OpenAccountResponse;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.OpenStatusEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.MathUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Description: 新入网业务接口
 * Author: wei.li
 * Date: 17/8/30
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class NewOpenAccountBizImpl extends AbstractBiz implements NewOpenAccountBiz {

    private static String BANK_PROVINCE_CODE = "01";

    private static String BANK_PROVINCE_NAME = "北京";

    private static String BANK_CITY_CODE = "01";

    private static String BANK_CITY_NAME = "北京";

    private static String OCR_DEFAULT_RESULT = "OCR_FAIL";//OCR默认识别结果

    private static String DEFAULT_ID_DATE_END = "2111-11-11";//身份证默认日期

    private static String DEFAULT_ID_DATE_START = "2017-01-01";//身份证默认日期

    @Override
    public OpenAccountResponse gatherNewBaseInfo(OpenAccountRequest request) {
        //检查用户注册情况
        UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
        //检查账户状态
        AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
        if (accountOpenEntity.getOpenStatus().getStep() >= 5 || !CompanyTypeEnum.MICRO.equals(accountOpenEntity.getCompanyType())) {
            throw new LaikeSysException(ErrorCode.COMMIT_INFO_DENY);
        }
        AttachmentEntity attachmentEntity = attachmentService.findByAccount(accountOpenEntity.getId());
        attachmentEntity.setAccountId(accountOpenEntity.getId());
        try {
            new BeanUtil().copyProperties(attachmentEntity, request, true);
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
        attachmentService.createAndUpdate(attachmentEntity);
        saveBaseInfo(request, accountOpenEntity);
        userService.updateAllianceCodeAndMerNo(userEntity,
                null, accountOpenEntity.getInviteCode(), accountOpenEntity.getAgentNo());
        //提交客户中心基本信息并变更我方开户状态
        accountOpenEntity = csMerchantService.gatherBaseInfo(accountOpenEntity);
        //提交客户中心资质信息并变更我方开户状态
        accountOpenEntity = csMerchantService.gatherBizInfo(accountOpenEntity,
                checkSubmitFlag(accountOpenEntity, request.getSubmit()));
        return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, null, null);
    }

    @Override
    public OpenAccountResponse gatherNewSettleInfo(OpenAccountRequest request) {
        String branchBankJson = ((HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_BRANCH_BANK_CODE)).get(request.getSettleBankCode());
        BranchBankInfo branchBankInfo = JSONUtils.jsonToBean(branchBankJson, BranchBankInfo.class);
        //检查用户注册情况
        UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
        //检查账户状态
        AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
        AttachmentEntity attachmentEntity = attachmentService.findByAccount(accountOpenEntity.getId());
        // TODO: 17/9/6 step 硬编码
        if (accountOpenEntity.getOpenStatus().getStep() >= 5 ||
                StringUtils.isEmpty(accountOpenEntity.getMerchantNo())
                || !CompanyTypeEnum.MICRO.equals(accountOpenEntity.getCompanyType())
                || null == attachmentEntity) {//已经提交,或者没有生成商编,或资质信息
            throw new LaikeSysException(ErrorCode.COMMIT_SETTLE_DENY);
        }
        //保存信息
        attachmentEntity.setBankcardImg(request.getBankcardImg());
        attachmentEntity.setBankcardImg2(request.getBankcardImg2());
        attachmentService.createAndUpdate(attachmentEntity);
        accountOpenEntity.setSettleCardNo(request.getSettleCardNo());
        accountOpenEntity.setSettleCardName(request.getSettleCardName());
        accountOpenEntity.setSettleBankCode(request.getSettleBankCode());
        accountOpenEntity.setSettleBankName(request.getSettleBankName());
        if (StringUtils.isEmpty(request.getOcrSettleCardNo())) {
            accountOpenEntity.setOcrSettleCardNo(OCR_DEFAULT_RESULT);
        } else {
            accountOpenEntity.setOcrSettleCardNo(request.getOcrSettleCardNo());
        }
        accountOpenEntity.setBankProvinceCode(BANK_PROVINCE_CODE);
        accountOpenEntity.setBankProvinceName(BANK_PROVINCE_NAME);
        accountOpenEntity.setBankCityCode(BANK_CITY_CODE);
        accountOpenEntity.setBankCityName(BANK_CITY_NAME);
        accountOpenEntity.setBranchBankCode(branchBankInfo.getBranchBankCode());
        accountOpenEntity.setBranchBankName(branchBankInfo.getBranchBankName());
        accountOpenService.createAndUpdate(accountOpenEntity);
        //提交客户中心，更新状态
        accountOpenEntity = csMerchantService.gatherSettleInfo(accountOpenEntity,
                checkSubmitFlag(accountOpenEntity, request.getSubmit()));
        return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, attachmentEntity, null);
    }

    @Override
    public OpenAccountResponse gatherNewAttachments(AttachmentsRequest request) {
        //检查用户注册情况
        UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
        //检查账户状态
        AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
        AttachmentEntity attachmentEntity = attachmentService.findByAccount(accountOpenEntity.getId());
        if (accountOpenEntity.getOpenStatus().getStep() >= 5 ||
                StringUtils.isEmpty(accountOpenEntity.getMerchantNo())
                || null == attachmentEntity) {//已经提交,或者没有生成商编,或资质信息表为空
            throw new LaikeSysException(ErrorCode.COMMIT_IMG_DENY);
        }
        if (accountOpenEntity.getOpenStatus().getStep() < 4 &&
                accountOpenEntity.getOpenStatus().getStep() > 2 &&
                Math.abs(MathUtils.subtract(OpenStatusEnum.IMG_SUBMIT.getStep()
                        , accountOpenEntity.getOpenStatus().getStep())) != 0.1) {
            throw new LaikeSysException(ErrorCode.COMMIT_IMG_ILLEGAL);
        }
        try {
            new BeanUtil().copyProperties(attachmentEntity, request, true);
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
        attachmentService.createAndUpdate(attachmentEntity);
        //提交客户中心，更新状态
        accountOpenEntity = csMerchantService.gatherImageInfo(accountOpenEntity, attachmentEntity, request.getSubmit());
        return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, attachmentEntity, null);
    }

    /**
     * 保存小微商户基本信息
     *
     * @param request
     * @param accountOpenEntity
     */
    private void saveBaseInfo(OpenAccountRequest request, AccountOpenEntity accountOpenEntity) {
        if (StringUtils.isEmpty(request.getLegalIdEnd())) {
            accountOpenEntity.setLegalIdEnd(DEFAULT_ID_DATE_END);
        } else {
            accountOpenEntity.setLegalIdEnd(request.getLegalIdEnd());
        }
        if (StringUtils.isEmpty(request.getLegalIdStart())) {
            accountOpenEntity.setLegalIdStart(DEFAULT_ID_DATE_START);
        } else {
            accountOpenEntity.setLegalIdStart(request.getLegalIdStart());
        }
        accountOpenEntity.setMerProvince(request.getMerProvince());
        accountOpenEntity.setMerProvinceName(request.getMerProvinceName());
        accountOpenEntity.setMerCity(request.getMerCity());
        accountOpenEntity.setMerCityName(request.getMerCityName());
        accountOpenEntity.setMerDistrictName(request.getMerDistrictName());
        accountOpenEntity.setMerDistrict(request.getMerDistrict());
        accountOpenEntity.setMerAddress(request.getMerAddress());
        if (StringUtils.isEmpty(request.getOcrLegalIdCard())) {
            accountOpenEntity.setOcrLegalIdCard(OCR_DEFAULT_RESULT);
        } else {
            accountOpenEntity.setOcrLegalIdCard(request.getOcrLegalIdCard());
        }
        if (StringUtils.isEmpty(accountOpenEntity.getMerchantNo())) {
            accountOpenEntity.setLegalName(request.getLegalName());
            accountOpenEntity.setLegalIdCard(request.getLegalIdCard());
            accountOpenEntity.setMerchantName(request.getLegalName());
            if (request.getAppSourceEnum() == null || request.getAppSourceEnum().equals(AppSourceEnum.LIKER)) {
                accountOpenEntity.setOpenStatus(OpenStatusEnum.INFO_SAVE);
            }
        }
        accountOpenService.createAndUpdate(accountOpenEntity);
    }

    /**
     * 检查是否直接提交
     *
     * @return
     */
    private boolean checkSubmitFlag(AccountOpenEntity entity, boolean submit) {
        if (entity.getOpenStatus().getStep() == 4 && submit) {//退回
            return true;
        }
        return false;
    }
}
