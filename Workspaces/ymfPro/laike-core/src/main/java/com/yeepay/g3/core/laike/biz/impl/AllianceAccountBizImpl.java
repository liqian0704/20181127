package com.yeepay.g3.core.laike.biz.impl;

import com.apay.util.encrypt.Base64;
import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.EnvironmemntUtil;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.AllianceAccountBiz;
import com.yeepay.g3.core.laike.dtoparser.impl.OpenAccountDTOConvert;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.AttachmentEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.core.laike.utils.SecurityUtil;
import com.yeepay.g3.facade.alliance.enums.member.MerType;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAllianceAccRequest;
import com.yeepay.g3.facade.laike.enumtype.*;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.member.param.MemberParam;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 展业账户业务
 * Author: wei.li
 * Date: 17/6/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AllianceAccountBizImpl extends AbstractBiz implements AllianceAccountBiz {

    private static String OCR_DEFAULT_RESULT = "OCR_FAIL";//OCR默认识别结果

    @Transactional
    @Override
    public OpenAccountResponse uploadImage(UploadImgRequest request) {
        try {
            UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
            AccountOpenEntity accountOpenEntity = accountOpenService.findById(userEntity.getAccountId());
            if (null == accountOpenEntity) {
                //新用户
                accountOpenEntity = new AccountOpenEntity();
                accountOpenEntity.setMemberNo(request.getMemberNo());
                accountOpenEntity.setPhoneNo(userEntity.getPhoneNo());
                accountOpenEntity.setLolOpenStatus(LOLOpenStatus.INIT);
                accountOpenEntity.setAgentNo(userEntity.getAgentNo());
                accountOpenEntity.setCompanyType(CompanyTypeEnum.MICRO);
                accountOpenEntity.setAccountType(AccountType.LOL);
                accountOpenEntity.setInviteCode(userEntity.getInviteCode());
                accountOpenEntity.setInviteType(InviteType.ALLIANCE);
            }
            if (LOLOpenStatus.INIT.equals(accountOpenEntity.getLolOpenStatus()) || LOLOpenStatus.RETURN.equals(accountOpenEntity.getLolOpenStatus())) {
                if (accountOpenEntity.getRemark() != null) {
                    if (!accountOpenEntity.getRemark().contains(request.getAttachmentTypeEnum().name())) {
                        throw new LaikeSysException(ErrorCode.COMMIT_ATTACH_ILLEGAL);
                    }
                }
                if (AttachmentTypeEnum.ID_CARD_FRONT.equals(request.getAttachmentTypeEnum())) {
                    IdCardInfo idCardFront = ocrService.getIdCardInfo(request.getImageBase64(), request.getMemberNo());
                    if (StringUtils.isNotEmpty(idCardFront.getIdCardNo())) {
                        accountOpenEntity.setOcrLegalIdCard(idCardFront.getIdCardNo());
                        if (accountOpenEntity.getRemark() == null) {
                            accountOpenEntity.setLegalIdCard(idCardFront.getIdCardNo());
                            accountOpenEntity.setLegalName(idCardFront.getName());
                        }
                    } else {
                        accountOpenEntity.setOcrLegalIdCard(OCR_DEFAULT_RESULT);
                    }
                }
                if (AttachmentTypeEnum.ID_CARD_BACK.equals(request.getAttachmentTypeEnum())) {
                    IdCardInfo idCardBack = ocrService.getIdCardInfo(request.getImageBase64(), request.getMemberNo());
                    if (accountOpenEntity.getRemark() == null) {
                        if (StringUtils.isNotEmpty(idCardBack.getValidDate())) {
                            accountOpenEntity.setLegalIdEnd(idCardBack.getValidDate());
                        } else {
                            accountOpenEntity.setLegalIdEnd(OCR_DEFAULT_RESULT);
                        }
                    }
                }
                if (AttachmentTypeEnum.SETTLE_CARD.equals(request.getAttachmentTypeEnum())) {
                    BankCardInfo bankCardInfo = ocrService.getBankCardInfo(request.getImageBase64(), request.getMemberNo());
                    if (StringUtils.isNotEmpty(bankCardInfo.getCardNo())) {
                        accountOpenEntity.setOcrSettleCardNo(bankCardInfo.getCardNo());
                        if (accountOpenEntity.getRemark() == null) {
                            accountOpenEntity.setSettleCardNo(bankCardInfo.getCardNo());
                        }
                    } else {
                        accountOpenEntity.setOcrSettleCardNo(OCR_DEFAULT_RESULT);
                    }
                    try {
                        if (accountOpenEntity.getRemark() == null) {
                            BankCardInfo cardBin = cardService.getBankCode(bankCardInfo.getCardNo());
                            if (accountOpenEntity.getSettleBankCode() != null && checkSettleCard(cardBin.getBankCode())) {
                                accountOpenEntity.setSettleBankCode(cardBin.getBankCode());
                                accountOpenEntity.setSettleBankName(cardBin.getBankName());
                            }
                        }
                    } catch (LaikeSysException e) {
                        //卡bin异常不处理
                    }
                }
                accountOpenService.createAndUpdate(accountOpenEntity);
                if (userEntity.getAccountId() == null) {
                    userService.updateAccountId(userEntity.getMemberNo(), accountOpenEntity.getId());//关联入网单子
                }
                AttachmentEntity attachmentEntity = attachmentService.findByAccount(accountOpenEntity.getId());
                attachmentEntity.setAccountId(accountOpenEntity.getId());
                if (AttachmentTypeEnum.ID_CARD_FRONT.equals(request.getAttachmentTypeEnum())) {
                    attachmentEntity.setIdcardImg1(uploadFileService.upload(new ByteArrayInputStream(Base64.decode2(request.getImageBase64())), FileTypeEnum.JPEG));
                }
                if (AttachmentTypeEnum.ID_CARD_BACK.equals(request.getAttachmentTypeEnum())) {
                    attachmentEntity.setIdcardImg2(uploadFileService.upload(new ByteArrayInputStream(Base64.decode2(request.getImageBase64())), FileTypeEnum.JPEG));
                }
                if (AttachmentTypeEnum.SETTLE_CARD.equals(request.getAttachmentTypeEnum())) {
                    attachmentEntity.setBankcardImg(uploadFileService.upload(new ByteArrayInputStream(Base64.decode2(request.getImageBase64())), FileTypeEnum.JPEG));
                }
                if (AttachmentTypeEnum.HARD_ID_CARD.equals(request.getAttachmentTypeEnum())) {
                    attachmentEntity.setBankcardImg2(uploadFileService.upload(new ByteArrayInputStream(Base64.decode2(request.getImageBase64())), FileTypeEnum.JPEG));
                }
                if (AttachmentTypeEnum.SIGNATURE.equals(request.getAttachmentTypeEnum())) {
                    attachmentEntity.setSignImg(uploadFileService.upload(new ByteArrayInputStream(Base64.decode2(request.getImageBase64())), FileTypeEnum.JPEG));
                }
                attachmentService.createAndUpdate(attachmentEntity);
                return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, attachmentEntity, userEntity);
            }
            throw new LaikeSysException(ErrorCode.COMMIT_STATUS_ILLEGAL);
        } catch (LaikeSysException e) {
            throw e;
        }
    }


    @Override
    public OpenAccountResponse openAllianceAccount(OpenAccountRequest request) {
        AccountOpenEntity accountOpenEntity = null;
        String microMerL1 = (String) ConfigUtils.getSysConfigParam(ConfigEnum.MICRO_MER_LEVEL1);
        String microMerL2 = (String) ConfigUtils.getSysConfigParam(ConfigEnum.MICRO_MER_LEVEL2);
        try {
            UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
            accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
            AttachmentEntity attachmentEntity = attachmentService.findByAccount(accountOpenEntity.getId());
            //检查资质信息是否完整
            if (!checkAttachment(attachmentEntity)) {
                throw new LaikeSysException(ErrorCode.ALLIANCE_ATTACH_ILLEGAL);
            }
            if (LOLOpenStatus.INIT.equals(accountOpenEntity.getLolOpenStatus()) ||
                    (LOLOpenStatus.RETURN.equals(accountOpenEntity.getLolOpenStatus()) && StringUtils.isEmpty(accountOpenEntity.getRemark()))) {
                accountOpenEntity.setLolOpenStatus(LOLOpenStatus.SUBMIT);
                accountOpenEntity.setMerProvince(request.getMerProvince());
                accountOpenEntity.setMerProvinceName(request.getMerProvinceName());
                accountOpenEntity.setMerCity(request.getMerCity());
                accountOpenEntity.setMerCityName(request.getMerCityName());
                accountOpenEntity.setMerDistrictName(request.getMerDistrictName());
                accountOpenEntity.setMerDistrict(request.getMerDistrict());
                accountOpenEntity.setMerAddress(request.getMerAddress());
                accountOpenEntity.setMerchantName(request.getLegalName());
                accountOpenEntity.setLegalName(request.getLegalName());
                accountOpenEntity.setLegalIdCard(request.getLegalIdCard());
                accountOpenEntity.setMerLevel1No(microMerL1);
                accountOpenEntity.setMerLevel2No(microMerL2);
                accountOpenEntity.setSettleCardNo(request.getSettleCardNo());
                accountOpenEntity.setSettleBankCode(request.getSettleBankCode());
                accountOpenEntity.setSettleBankName(request.getSettleBankName());
                accountOpenEntity.setBranchBankCode(request.getBranchBankCode());
                accountOpenEntity.setBankProvinceCode(request.getBankProvinceCode());
                accountOpenEntity.setBankProvinceName(request.getBankProvinceName());
                accountOpenEntity.setBankCityCode(request.getBankCityCode());
                accountOpenEntity.setBankCityName(request.getBankCityName());
                accountOpenEntity.setBranchBankName(request.getBranchBankName());
                accountOpenEntity.setSettleCardName(request.getLegalName());
                try {
                    csMerchantService.gatherAllianceInfo(accountOpenEntity, attachmentEntity);
                    accountOpenEntity.setLolOpenStatus(LOLOpenStatus.AUDITING);
                    //是否人证合一
                    checkConsistency(accountOpenEntity, userEntity);
                    return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, attachmentEntity, userEntity);
                } catch (LaikeSysException e) {
                    if (!e.getDefineCode().equals(ErrorCode.MERCHANT_UNKNOW_EXCEPTION)) {
                        accountOpenEntity.setLolOpenStatus(LOLOpenStatus.RETURN);
                    }
                    throw e;
                }
            }
            if (LOLOpenStatus.RETURN.equals(accountOpenEntity.getLolOpenStatus()) && StringUtils.isNotEmpty(accountOpenEntity.getRemark())) {
                checkConsistency(accountOpenEntity, userEntity);
                return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, attachmentEntity, userEntity);
            }
            throw new LaikeSysException(ErrorCode.ALLIANCE_OPEN_ACCOUNT_DENY);
        } catch (LaikeSysException e) {
            throw e;
        } finally {
            if (accountOpenEntity != null) {
                accountOpenService.createAndUpdate(accountOpenEntity);
            }
        }
    }

    @Override
    public void gatherAlliancetoMer() {
        //补偿入网异常
        List<AccountOpenEntity> entityList = accountOpenService.findByLolStatus(LOLOpenStatus.SUBMIT);
        //补偿联盟异常
        List<AccountOpenEntity> entityListAud = accountOpenService.findByLolStatus(LOLOpenStatus.AUDITING);

        List<AccountOpenEntity> sumEntity = null;

        if (entityList != null) {
            sumEntity = entityList;
            sumEntity.addAll(entityListAud);
        } else if (entityListAud != null) {
            sumEntity = entityListAud;
            sumEntity.addAll(entityList);
        }
        if (sumEntity != null) {
            for (AccountOpenEntity entity : sumEntity) {
                try {
                    AttachmentEntity attachmentEntity = attachmentService.findByAccount(entity.getId());
                    UserEntity userEntity = userService.findRegisterByMember(entity.getMemberNo());
                    try {
                        if (LOLOpenStatus.SUBMIT.equals(entity.getLolOpenStatus())) {
                            csMerchantService.gatherAllianceInfo(entity, attachmentEntity);
                            entity.setLolOpenStatus(LOLOpenStatus.AUDITING);
                        }
                        //是否人证合一
                        checkConsistency(entity, userEntity);
                    } catch (LaikeSysException e) {
                        if (!e.getDefineCode().equals(ErrorCode.MERCHANT_UNKNOW_EXCEPTION)) {
                            entity.setLolOpenStatus(LOLOpenStatus.RETURN);
                        }
                    }
                } finally {
                    accountOpenService.createAndUpdate(entity);
                }
            }
        }
    }

    @Override
    public BaseResponse updateAllianceAccount(UpdateAllianceAccRequest request) {
        AccountOpenEntity entity = accountOpenService.findById(request.getId());
        UserEntity userEntity = userService.findRegisterByMember(entity.getMemberNo());
        if (null == entity || !entity.getLolOpenStatus().equals(LOLOpenStatus.OCR_AUDIT)) {
            throw new LaikeSysException(ErrorCode.UPDATE_ALLIANCE_ACCOUNT_DENY);
        }
        if (LOLOpenStatus.RETURN.equals(request.getStatus())) {
            if (StringUtils.isNotEmpty(request.getRemark())) {
                entity.setRemark(request.getRemark());
                entity.setLolOpenStatus(LOLOpenStatus.RETURN);
                accountOpenService.createAndUpdate(entity);
            } else {
                throw new LaikeSysException(ErrorCode.REMARK_IS_ILLEGAL);
            }
        }
        if (LOLOpenStatus.SUCCESS.equals(request.getStatus())) {
            notifyAlliance(entity, userEntity);
        }
        BaseEventUtils.sendEventNotInTransaction(ConstantUtil.SEND_APP_MSG_EVENT, entity.getMemberNo());
        return new BaseResponse();
    }


    @Override
    public CustomerSourceResponse queryCustomerSource(String merchantNo) {
        CustomerSourceResponse response = new CustomerSourceResponse();
        AccountOpenEntity entity = accountOpenService.findByMerchantNo(merchantNo);
        if (null == entity) {
            throw new LaikeSysException(ErrorCode.MERCHANT_IS_NOT_EXIST);
        } else {
            if (AccountType.LOL.equals(entity.getAccountType())) {
                response.setAppSourceEnum(AppSourceEnum.ALLIANCE);
            } else {
                if (InviteType.INVITECODE.equals(entity.getInviteType()) && entity.getInviteCode().length() > 6) {
                    response.setAppSourceEnum(AppSourceEnum.ALLIANCE);
                } else {
                    response.setAppSourceEnum(AppSourceEnum.LIKER);
                }
            }
        }
        return response;
    }

    @Override
    public ShareResponse autoRegAlliance(BaseRequest request) {
        ShareResponse response = new ShareResponse();
        String webHost = EnvironmemntUtil.getWebHost();
        AccountOpenEntity allianceAccountEntity = null;
        String downloadSuffix = (String) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_DOWNLOAD_SUFFIX);
        String registerSuffix = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_REGISTER_SUFFIX);
        UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
        AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
        AttachmentEntity attachmentEntity = attachmentService.findByAccount(accountOpenEntity.getId());
        // TODO: 17/9/6 枚举 常用判断
        if (!OpenStatusEnum.SUCCESS.equals(accountOpenEntity.getOpenStatus()) || CompanyTypeEnum.ENTER_UNION.equals(accountOpenEntity.getCompanyType())
                || CompanyTypeEnum.ENTERPRISE.equals(accountOpenEntity.getCompanyType())) {
            throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_NOT_AVAILABLE);
        }
        try {
            if (InviteType.INVITECODE.equals(accountOpenEntity.getInviteType()) && accountOpenEntity.getInviteCode().length() > 6) {
                UserEntity allianceUserEntity = userService.findByPhoneNo(userEntity.getPhoneNo(), AppSourceEnum.ALLIANCE);
                if (null == allianceUserEntity || UserStatus.INIT.equals(allianceUserEntity.getUserStatus())) {
                    //创建alliance用户
                    String memberNo = null;
                    MemberParam param = memberService.getMemberByPhoneNo(userEntity.getPhoneNo(), AppSourceEnum.ALLIANCE);
                    if (null == param) {
                        memberNo = memberService.register(convert2RegisterReq(userEntity.getPhoneNo(),
                                StringUtils.trim(SecurityUtil.encryptL1Info("lk" + StringUtils.right(accountOpenEntity.getLegalIdCard(), 4)))));
                    } else {
                        memberNo = param.getMemberNo();
                    }
                    Map map = Maps.newHashMap();
                    map.put("agentNo", accountOpenEntity.getAgentNo());
                    map.put("inviteCode", accountOpenEntity.getInviteCode());
                    if (null == allianceUserEntity) {
                        request.setAppSourceEnum(AppSourceEnum.ALLIANCE);
                        allianceUserEntity = userService.createNew(request, true, memberNo, map);
                    } else {
                        userService.update2Register(memberNo, allianceUserEntity);
                    }
                }
                allianceAccountEntity = accountOpenService.findById(allianceUserEntity.getAccountId());
                if (null == allianceAccountEntity) {
                    //创建开户表
                    allianceAccountEntity = new AccountOpenEntity();
                    convert2AccountEntity(allianceAccountEntity, accountOpenEntity, allianceUserEntity.getMemberNo());
                    accountOpenService.createAndUpdate(allianceAccountEntity);
                    userService.updateAccountId(allianceUserEntity.getMemberNo(), allianceAccountEntity.getId());
                }
                AttachmentEntity allianceAttachmentEntity = attachmentService.findByAccount(allianceAccountEntity.getId());
                if (StringUtils.isEmpty(allianceAttachmentEntity.getAccountId())) {
                    convert2AttachmentEntity(allianceAttachmentEntity, attachmentEntity, allianceAccountEntity.getId());
                    attachmentService.createAndUpdate(allianceAttachmentEntity);
                }
                if (!LOLOpenStatus.SUCCESS.equals(allianceAccountEntity.getLolOpenStatus()) && StringUtils.isEmpty(allianceAccountEntity.getOcrLegalIdCard())) {
                    allianceAccountEntity.setLolOpenStatus(LOLOpenStatus.SUBMIT);
                    try {
                        csMerchantService.gatherAllianceInfo(allianceAccountEntity, allianceAttachmentEntity);
                    } catch (LaikeSysException e) {
                        if (!("L30044".equals(e.getDefineCode()) && StringUtils.isNotEmpty(allianceAccountEntity.getMerchantNo()))) {
                            throw e;
                        }
                    }
                    allianceAccountEntity.setLolOpenStatus(LOLOpenStatus.AUDITING);
                    notifyAlliance(allianceAccountEntity, allianceUserEntity);
                }
                if (LOLOpenStatus.SUCCESS.equals(allianceAccountEntity.getLolOpenStatus())) {
                    response.setShareUrl(webHost + registerSuffix + com.yeepay.g3.utils.common.encrypt.Base64.encode(allianceUserEntity.getAllianceInviteCode()));
                } else {
                    response.setShareUrl(webHost + downloadSuffix);
                }
                return response;
            }
        } catch (LaikeSysException e) {
            //下载链接
            response.setShareUrl(webHost + downloadSuffix);
            if (allianceAccountEntity != null) {
                accountOpenService.createAndUpdate(allianceAccountEntity);
            }
            return response;
        }
        throw new LaikeSysException(ErrorCode.USER_PERMISSION_DENY);
    }

    /**
     * 结算卡校验
     *
     * @param bankCode
     * @return
     */
    private boolean checkSettleCard(String bankCode) {
        if (!StringUtils.isEmpty(bankCode)) {
            List<BankInfo> list = bankCodeInfoService.querySettleBank();
            for (BankInfo b : list) {
                if (bankCode.equals(b.getBankCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查资质是否齐全
     *
     * @param entity
     * @return
     */
    private boolean checkAttachment(AttachmentEntity entity) {
        if (StringUtils.isNotEmpty(entity.getIdcardImg1()) && StringUtils.isNotEmpty(entity.getIdcardImg2()) &&
                StringUtils.isNotEmpty(entity.getBankcardImg()) && StringUtils.isNotEmpty(entity.getBankcardImg2())
                && StringUtils.isNotEmpty(entity.getSignImg())) {
            return true;
        }
        return false;
    }

    /**
     * 检查人证是否一致
     * 一致：通知分销系统
     * 不一致：邮件通知运营
     *
     * @param accountOpenEntity
     */
    private void checkConsistency(AccountOpenEntity accountOpenEntity, UserEntity userEntity) {
        HashMap map = Maps.newHashMap();
        if (!accountOpenEntity.getOcrLegalIdCard().equalsIgnoreCase(accountOpenEntity.getLegalIdCard())) {
            map.put("ID_CARD_FRONT", AttachmentTypeEnum.ID_CARD_FRONT.getErrorMsg());
        }
        if (!accountOpenEntity.getOcrSettleCardNo().equals(accountOpenEntity.getSettleCardNo())) {
            map.put("SETTLE_CARD", AttachmentTypeEnum.SETTLE_CARD.getErrorMsg());
        }
        if (!map.isEmpty()) {
            accountOpenEntity.setRemark(JSONUtils.toJsonString(map));
            accountOpenEntity.setLolOpenStatus(LOLOpenStatus.OCR_AUDIT);
            //邮件通知运营
            BaseEventUtils.sendEventNotInTransaction(ConstantUtil.SEND_AUDIT_MAIL_EVENT, accountOpenEntity.getMerchantNo());
        } else {
            //通知联盟系统
            notifyAlliance(accountOpenEntity, userEntity);
        }
        BaseEventUtils.sendEventNotInTransaction(ConstantUtil.SEND_APP_MSG_EVENT, accountOpenEntity.getMemberNo());
    }

    /**
     * 通知联盟系统
     *
     * @param accountOpenEntity
     */
    private void notifyAlliance(AccountOpenEntity accountOpenEntity, UserEntity userEntity) {
        String allianceInviteCode = allianceService.joinAlliance(accountOpenEntity.getInviteCode(), accountOpenEntity.getMerchantNo(), accountOpenEntity.getMerchantName()
                , null, accountOpenEntity.getCreateTime(), MerType.ALLY);
        userService.updateAllianceCodeAndMerNo(userEntity, accountOpenEntity.getMerchantNo(), allianceInviteCode, accountOpenEntity.getAgentNo());
        accountOpenEntity.setLolOpenStatus(LOLOpenStatus.SUCCESS);
        accountOpenService.createAndUpdate(accountOpenEntity);
    }

    /**
     * 组装三代会员注册参数(联盟)
     *
     * @param phone
     * @param pwd
     * @return
     */
    private VerifyRegisterSmsRequest convert2RegisterReq(String phone, String pwd) {
        VerifyRegisterSmsRequest verifyRegisterSmsRequest = new VerifyRegisterSmsRequest();
        verifyRegisterSmsRequest.setPhoneNo(phone);
        verifyRegisterSmsRequest.setPwd(pwd);
        verifyRegisterSmsRequest.setAppSourceEnum(AppSourceEnum.ALLIANCE);
        return verifyRegisterSmsRequest;
    }

    /**
     * 初始化联盟开户表
     *
     * @param tagEntity
     * @param srcEntity
     */
    private void convert2AccountEntity(AccountOpenEntity tagEntity, AccountOpenEntity srcEntity, String memberNo) {
        try {
            String microMerL1 = (String) ConfigUtils.getSysConfigParam(ConfigEnum.MICRO_MER_LEVEL1);
            String microMerL2 = (String) ConfigUtils.getSysConfigParam(ConfigEnum.MICRO_MER_LEVEL2);
            BeanUtils.copyProperties(tagEntity, srcEntity);
            tagEntity.setAccountType(AccountType.LOL);
            tagEntity.setInviteType(InviteType.ALLIANCE);
            tagEntity.setCompanyType(CompanyTypeEnum.MICRO);
            tagEntity.setOpenStatus(null);
            tagEntity.setMerLevel1No(microMerL1);
            tagEntity.setMerLevel2No(microMerL2);
            tagEntity.setMerchantNo(null);
            tagEntity.setId(null);
            tagEntity.setOcrLegalIdCard(null);
            tagEntity.setOcrSettleCardNo(null);
            tagEntity.setMemberNo(memberNo);
            tagEntity.setRemark(null);
            tagEntity.setOpRegNo(null);
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
    }

    /**
     * 初始化联盟附件表
     *
     * @param tagEntity
     * @param srcEntity
     * @param id
     */
    private void convert2AttachmentEntity(AttachmentEntity tagEntity, AttachmentEntity srcEntity, String id) {
        try {
            BeanUtils.copyProperties(tagEntity, srcEntity);
            tagEntity.setAccountId(id);
            tagEntity.setId(null);
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
    }
}
