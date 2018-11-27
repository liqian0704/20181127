package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.OpenAccountBiz;
import com.yeepay.g3.core.laike.dtoparser.impl.OpenAccountDTOConvert;
import com.yeepay.g3.core.laike.entity.*;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.alliance.enums.member.MerType;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.*;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.MathUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.yeepay.g3.facade.laike.enumtype.BizTypeEnum.OPEN_ACCOUNT;

/**
 * Description: 账户业务
 * Author: jiawen.huang
 * Date: 16/11/30
 * Time: 17:01
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class OpenAccountBizImpl extends AbstractBiz implements OpenAccountBiz {

	@Override
	public OpenAccountResponse findOpenAccount(BaseRequest request) {
		//检查用户注册情况
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		//检查开户状态
        AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
        //附件查询
		AttachmentEntity attachmentEntity = attachmentService.findByAccount(userEntity.getAccountId());

        OpenAccountResponse response = new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, attachmentEntity, userEntity);
        //判断入网走新老逻辑
        MerInLogic(response, accountOpenEntity);
        return response;
    }

	@Override
	public OpenAccountResponse checkInviteType(OpenAccountRequest request) {
		//检查用户注册情况
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		//检查开户状态
		AccountOpenEntity accountOpenEntity = accountOpenService.findById(userEntity.getAccountId());
		if (null == accountOpenEntity) {
			//新用户
			accountOpenEntity = new AccountOpenEntity();
			accountOpenEntity.setMemberNo(request.getMemberNo());
			accountOpenEntity.setPhoneNo(userEntity.getPhoneNo());
		} else if (null != accountOpenEntity && accountOpenEntity.getOpenStatus().getStep() >= 2) {
			throw new LaikeSysException(ErrorCode.CHANGE_INVITE_TYPE_DENY);
		}
		accountOpenEntity.setOpenLbs(request.getOpenLbs());
		accountOpenEntity.setInviteCode(request.getInviteCode());
		accountOpenEntity.setInviteType(request.getInviteType());
		accountOpenEntity.setCompanyType(request.getCompanyType());//首次保存
		if (null != userEntity.getInviteCode() && userEntity.getInviteCode().length() > 6 && InviteType.INVITECODE.equals(accountOpenEntity.getInviteType())) {
			allianceService.validateInviteCode(request.getInviteCode());
            accountOpenEntity.setAgentNo(userEntity.getAgentNo());
        } else {
			if (accountOpenEntity.getInviteCode().length() > 6 && InviteType.INVITECODE.equals(accountOpenEntity.getInviteType())) {//老版本联盟邀请码入网
				String agentNo = allianceService.validateInviteCode(request.getInviteCode());
                accountOpenEntity.setAgentNo(agentNo);
            } else {
                accountOpenEntity = agentRelationService.checkInviteBiz(accountOpenEntity);
            }
        }
		accountOpenService.createAndUpdate(accountOpenEntity);
		saveOpenAccountSnapt(accountOpenEntity.getMemberNo(), request);
		userService.updateAccountId(userEntity.getMemberNo(), accountOpenEntity.getId());//关联入网单子
        return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, null, null);
    }

	@Override
	public OpenAccountResponse gatherBaseInfo(OpenAccountRequest request) {
		//检查用户注册情况
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		//检查账户状态
		AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
		if (accountOpenEntity.getOpenStatus().getStep() >= 5) {
			throw new LaikeSysException(ErrorCode.COMMIT_INFO_DENY);
		}
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
	public OpenAccountResponse gatherSettleInfo(OpenAccountRequest request) {
		//检查用户注册情况
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		//检查账户状态
		AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
		if (accountOpenEntity.getOpenStatus().getStep() >= 5 ||
				StringUtils.isEmpty(accountOpenEntity.getMerchantNo())) {//已经提交,或者没有生成商编
			throw new LaikeSysException(ErrorCode.COMMIT_SETTLE_DENY);
		}
		//保存信息
		accountOpenEntity.setSettleCardNo(request.getSettleCardNo());
		accountOpenEntity.setSettleCardName(request.getSettleCardName());
		accountOpenEntity.setBankProvinceCode(request.getBankProvinceCode());
		accountOpenEntity.setBankProvinceName(request.getBankProvinceName());
		accountOpenEntity.setBankCityCode(request.getBankCityCode());
		accountOpenEntity.setBankCityName(request.getBankCityName());
		accountOpenEntity.setSettleBankCode(request.getSettleBankCode());
		accountOpenEntity.setSettleBankName(request.getSettleBankName());
		accountOpenEntity.setBranchBankCode(request.getBranchBankCode());
		accountOpenEntity.setBranchBankName(request.getBranchBankName());
		accountOpenService.createAndUpdate(accountOpenEntity);
		//提交客户中心，更新状态
		accountOpenEntity = csMerchantService.gatherSettleInfo(accountOpenEntity,
				checkSubmitFlag(accountOpenEntity, request.getSubmit()));
        return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, null, null);
    }

	@Override
	public OpenAccountResponse gatherAttachments(AttachmentsRequest request) {
		//检查用户注册情况
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		//检查账户状态
		AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
		if (accountOpenEntity.getOpenStatus().getStep() >= 5 ||
				StringUtils.isEmpty(accountOpenEntity.getMerchantNo())) {//已经提交，或者没有生成商编
			throw new LaikeSysException(ErrorCode.COMMIT_IMG_DENY);
		}
		if (accountOpenEntity.getOpenStatus().getStep() < 4 &&
				accountOpenEntity.getOpenStatus().getStep() > 2 &&
				Math.abs(MathUtils.subtract(OpenStatusEnum.IMG_SUBMIT.getStep()
						, accountOpenEntity.getOpenStatus().getStep())) != 0.1) {
			throw new LaikeSysException(ErrorCode.COMMIT_IMG_ILLEGAL);
		}

		AttachmentEntity attachmentEntity = attachmentService.findByAccount(accountOpenEntity.getId());
		attachmentEntity.setAccountId(accountOpenEntity.getId());
		attachmentEntity.setBizImg(request.getBizImg());
		attachmentEntity.setBankcardImg(request.getBankcardImg());
		attachmentEntity.setIdcardImg1(request.getIdcardImg1());
		attachmentEntity.setIdcardImg2(request.getIdcardImg2());
		attachmentEntity.setAgreementImg(request.getAgreementImg());
		attachmentEntity.setPermitImg(request.getPermitImg());
		attachmentEntity.setTaxImg(request.getTaxImg());
		attachmentEntity.setOrgImg(request.getOrgImg());
		attachmentEntity.setAgreementImg2(request.getAgreementImg2());
		attachmentEntity.setBankcardImg2(request.getBankcardImg2());
		attachmentEntity.setSignImg(request.getSignImg());
		attachmentEntity.setCreditCodeImg(request.getCreditCodeImg());
		attachmentService.createAndUpdate(attachmentEntity);
		//提交客户中心，更新状态
		accountOpenEntity = csMerchantService.gatherImageInfo(accountOpenEntity, attachmentEntity, request.getSubmit());
        return new OpenAccountDTOConvert().convert2DTO(accountOpenEntity, attachmentEntity, null);
    }

	@Transactional
	@Override
	public BaseResponse callbackResult(Map<String, String> request) {
		AccountOpenEntity accountOpenEntity = null;
        if (StringUtils.isNotEmpty(request.get("requestNo"))) {
            accountOpenEntity = accountOpenService.findExistById(request.get("requestNo"));
        } else if (StringUtils.isNotEmpty(request.get("merchantNo"))) {
            accountOpenEntity = accountOpenService.findByMerchantNo(request.get("merchantNo"));
        }
        UserEntity userEntity = userService.findByMemberNo(accountOpenEntity.getMemberNo());
        //是否通知联盟
        boolean isNotifyAlliance = AccountType.LK.equals(accountOpenEntity.getAccountType()) &&
                ((null != userEntity.getInviteCode() && userEntity.getInviteCode().length() > 6 && InviteType.INVITECODE.equals(accountOpenEntity.getInviteType()))
                        || (null != accountOpenEntity.getInviteCode() && accountOpenEntity.getInviteCode().length() > 6 && InviteType.INVITECODE.equals(accountOpenEntity.getInviteType())));
        if (StringUtils.isNotEmpty(request.get("requestNo"))) {
            if (accountOpenEntity.getOpenStatus().getStep() >= 5) {
                accountOpenEntity.setOpenStatus(request.get("merNetInStatus").equals("PROCESS_SUCCESS") ?
						OpenStatusEnum.SUCCESS : request.get("merNetInStatus").equals("PROCESS_REJECT") ?
						OpenStatusEnum.REJECT : OpenStatusEnum.RETURN);
				accountOpenEntity.setRemark(request.get("remark"));//清空或者注入
				accountOpenService.createAndUpdate(accountOpenEntity);
				if (request.get("merNetInStatus").equals("PROCESS_SUCCESS")) {
					userService.update2Boss(accountOpenEntity.getMemberNo(), accountOpenEntity.getMerchantNo());
//					qrPayService.bindQRCodeBoard(accountOpenEntity);
					//通知联盟系统
                    if (isNotifyAlliance) {
                        if (!CompanyTypeEnum.MICRO.equals(accountOpenEntity.getCompanyType())) {
                            allianceService.joinAlliance(userEntity.getInviteCode(), accountOpenEntity.getMerchantNo(), accountOpenEntity.getMerchantName()
                                    , null, new Date(), MerType.CHILD);
                            userService.updateMerchantNo(userEntity.getMemberNo(), accountOpenEntity.getMerchantNo());
						}
					}
					BaseEventUtils.sendEventNotInTransaction(ConstantUtil.BIND_BIZ_MAN_EVENT, accountOpenEntity);
					if ((InviteType.INVITECODE.equals(accountOpenEntity.getInviteType()) || InviteType.SIGNEDPAPER.equals(accountOpenEntity.getInviteType())) &&
							!accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.ENTERPRISE) && !accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.ENTER_UNION)) {
						//官网版默认不开通秒到
						if (!(null != userEntity.getInviteCode() && userEntity.getInviteCode().length() > 6 && InviteType.INVITECODE.equals(accountOpenEntity.getInviteType())
								&& allianceService.isOfficialLord(userEntity.getInviteCode()))) {
							BaseEventUtils.sendEventNotInTransaction(ConstantUtil.OPEN_SETTLE_S0_EVENT, accountOpenEntity.getMemberNo());
						}
					}
				}
				BaseEventUtils.sendEventNotInTransaction(ConstantUtil.SEND_APP_MSG_EVENT, accountOpenEntity.getMemberNo());
				return new BaseResponse();
			}
		}
		if (StringUtils.isNotEmpty(request.get("merchantNo"))) {
			if (null == accountOpenEntity) {
				throw new LaikeSysException(ErrorCode.ACCOUNT_NOT_EXIST);
			}
			if (request.get("merNetInStatus").equals("PAY_SUCCESS")) {
				if (accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.MICRO)) {
					if (accountOpenEntity.getOpenStatus().equals(OpenStatusEnum.AUDITING)) {
						accountOpenEntity.setOpenStatus(OpenStatusEnum.PAY_SUCCESS);
						accountOpenService.createAndUpdate(accountOpenEntity);
						userService.update2Boss(accountOpenEntity.getMemberNo(), accountOpenEntity.getMerchantNo());
						qrPayService.bindQRCodeBoard(accountOpenEntity);
						BaseEventUtils.sendEventNotInTransaction(ConstantUtil.SEND_APP_MSG_EVENT, accountOpenEntity.getMemberNo());
                        //小微商户PAY_SUCCESS通知联盟
                        if (isNotifyAlliance) {
                            allianceService.joinAlliance(userEntity.getInviteCode(), accountOpenEntity.getMerchantNo(), accountOpenEntity.getMerchantName()
                                    , null, new Date(), MerType.CHILD);
                            userService.updateMerchantNo(userEntity.getMemberNo(), accountOpenEntity.getMerchantNo());
                        }
                        return new BaseResponse();
					}
					if (accountOpenEntity.getOpenStatus().equals(OpenStatusEnum.PAY_SUCCESS)) {
						return new BaseResponse();
					}
				}
			}
		}
		throw new LaikeSysException(ErrorCode.GET_CALLBACK_DENY);
	}

	@Override
	public NewProductResponse findProductInfo(NewProductRequest request) {
		Map<String, String> directWorkFeeMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_DIRECT_WORKER_FEE_RATE);
		Map<String, String> workFeeMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_WORKER_FEE_RATE);
		Map<String, String> enterpriseLimitMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_ENTERPRISE_LIMIT);
		Map<String, String> microLimitMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_MICRO_LIMIT);
		Map<String, String> individualLimitMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_INDIVIDUAL_LIMIT);
		List<String> directWorkProductList = (ArrayList<String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_DIRECT_WORKER_PRODUCT);
		List<String> workProductList = (ArrayList<String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_WORKER_PRODUCT);

		NewProductResponse newProductResponse = new NewProductResponse();
		AccountOpenEntity accountOpenEntity = accountOpenService.findById(request.getRequestNo());
		if (null != accountOpenEntity) {
			CompanyTypeEnum companyType = accountOpenEntity.getCompanyType();
			UserEntity userEntity = userService.findByMemberNo(accountOpenEntity.getMemberNo());
			List<String> paylist = new ArrayList<String>();
			paylist.add("SIGNEDPAGER_RECEIVE");
			paylist.add("APP_RECEIVE");
			AppRoleEnum appRoleEnum = checkRole(userEntity);
            if (appRoleEnum.equals(AppRoleEnum.DIRECT_WORKER) || appRoleEnum.equals(AppRoleEnum.DIRECT_WORKER_TEST) ||
                    appRoleEnum.equals(AppRoleEnum.OFFICIAL) || appRoleEnum.equals(AppRoleEnum.OFFICIAL_TEST)) {
                paylist.add("HARDWARE_RECEIVE");
				newProductResponse.setFeeRateMap(directWorkFeeMap);
				newProductResponse.setOpenProductList(directWorkProductList);
			} else if (appRoleEnum.equals(AppRoleEnum.WORKER) || appRoleEnum.equals(AppRoleEnum.WORKER_TEST)) {
				newProductResponse.setFeeRateMap(workFeeMap);
				newProductResponse.setOpenProductList(workProductList);
			} else {
				throw new LaikeSysException(ErrorCode.TYPE_NOT_EXIST);
			}
            newProductResponse.setStandardAgreement(true);
            newProductResponse.setLkReceiveType(paylist);
            if (companyType.equals(CompanyTypeEnum.MICRO)) {
				newProductResponse.setRiskInfoMap(microLimitMap);
			} else if (companyType.equals(CompanyTypeEnum.INDIVIDUAL)) {
				newProductResponse.setRiskInfoMap(individualLimitMap);
			} else if (companyType.equals(CompanyTypeEnum.ENTERPRISE) || companyType.equals(CompanyTypeEnum.ENTER_UNION)) {
				newProductResponse.setRiskInfoMap(enterpriseLimitMap);
			} else {
				throw new LaikeSysException(ErrorCode.TYPE_NOT_EXIST);
			}
		} else {
			throw new LaikeSysException(ErrorCode.OPEN_ID_NOT_EXIST);
		}
		return newProductResponse;
	}

	@Override
	public AccountOpenEntity findByMerchantNo(String merchantNo) {
		AccountOpenEntity accountOpenEntity = accountOpenService.findByMerchantNo(merchantNo);
		return accountOpenEntity;
	}

	private void saveBaseInfo(OpenAccountRequest request, AccountOpenEntity accountOpenEntity) {
		accountOpenEntity.setLegalIdStart(request.getLegalIdStart());
		accountOpenEntity.setLegalIdEnd(request.getLegalIdEnd());
		accountOpenEntity.setMerLevel1No(request.getMerLevel1No());
		accountOpenEntity.setMerLevel2No(request.getMerLevel2No());
		accountOpenEntity.setMerLevel1NoName(request.getMerLevel1NoName());
		accountOpenEntity.setMerLevel2NoName(request.getMerLevel2NoName());
		accountOpenEntity.setMerProvince(request.getMerProvince());
		accountOpenEntity.setMerProvinceName(request.getMerProvinceName());
		accountOpenEntity.setMerCity(request.getMerCity());
		accountOpenEntity.setMerCityName(request.getMerCityName());
		accountOpenEntity.setMerDistrictName(request.getMerDistrictName());
		accountOpenEntity.setMerDistrict(request.getMerDistrict());
		accountOpenEntity.setMerAddress(request.getMerAddress());
		if (StringUtils.isEmpty(accountOpenEntity.getMerchantNo())) {
			//小薇商户，没有上送merchantName，保存为法人姓名
			if (CompanyTypeEnum.MICRO.equals(request.getCompanyType())) {
				accountOpenEntity.setMerchantName(request.getLegalName());
			} else {
				accountOpenEntity.setMerchantName(request.getMerchantName());
			}
			accountOpenEntity.setLegalName(request.getLegalName());
			accountOpenEntity.setLegalIdCard(request.getLegalIdCard());
			accountOpenEntity.setBizNo(request.getBizNo());
			if (request.getAppSourceEnum() == null || request.getAppSourceEnum().equals(AppSourceEnum.LIKER)) {
				accountOpenEntity.setOpenStatus(OpenStatusEnum.INFO_SAVE);
			}
		}
		if (accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.ENTERPRISE) ||
				accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.ENTER_UNION) ||
				accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.INDIVIDUAL)) {
			if (accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.ENTERPRISE) ||
					accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.INDIVIDUAL)) {
				accountOpenEntity.setAccountLicense(request.getAccountLicense());
				accountOpenEntity.setTaxNo(request.getTaxNo());
				accountOpenEntity.setOrgNo(request.getOrgNo());
				accountOpenEntity.setOrgNoStart(request.getOrgNoStart());
				accountOpenEntity.setOrgNoEnd(request.getOrgNoEnd());
			}
			accountOpenEntity.setBizNoStart(request.getBizNoStart());
			accountOpenEntity.setBizNoEnd(request.getBizNoEnd());
			accountOpenEntity.setAccountLicense(request.getAccountLicense());
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

	private void saveOpenAccountSnapt(String memberNo, BaseRequest request) {
		OperateRecordEntity operateRecordEntity = new OperateRecordEntity();
		operateRecordEntity.setMemberNo(memberNo);
        operateRecordEntity.setBizType(OPEN_ACCOUNT);
        operateRecordEntity.setBizParam(request.getVersionId());
        operateRecordEntity.setLinkPhone(request.getImei());
        operateRecordEntity.setProvinceName(request.getLocation());
        operateRecodeService.save(operateRecordEntity);
    }

	private AppRoleEnum checkRole(UserEntity userEntity) {
        OperateRecordEntity operateRecordEntity = operateRecodeService.findLastRecode(userEntity.getMemberNo(), OPEN_ACCOUNT);
        if (null == operateRecordEntity) {
            return appVersionService.findById(userEntity.getLastVersionId()).getRoleType();
        } else {
            return appVersionService.findById(operateRecordEntity.getBizParam()).getRoleType();
        }
	}

    /**
     * 入网逻辑判断(区分小微新老商户入网页面)
     *
     * @param response
     * @param accountOpenEntity
     */
    private void MerInLogic(OpenAccountResponse response, AccountOpenEntity accountOpenEntity) {
        //查询入网时使用的版本
        OperateRecordEntity operateRecordEntity = operateRecodeService.findLastRecode(accountOpenEntity.getMemberNo(), BizTypeEnum.OPEN_ACCOUNT);
        response.setIsNewLogic(BoolEnum.FALSE);
        //判断用户入网走哪个入网逻辑
        if (AccountType.LK.equals(accountOpenEntity.getAccountType())) {
            if (OpenStatusEnum.INIT.equals(accountOpenEntity.getOpenStatus())) {
                response.setIsNewLogic(BoolEnum.TRUE);
            } else {
                if (CompanyTypeEnum.MICRO.equals(accountOpenEntity.getCompanyType())) {
                    if (null == operateRecordEntity) {
                        response.setIsNewLogic(BoolEnum.FALSE);
                    } else {
                        String versionId = operateRecordEntity.getBizParam();
                        AppVersionEntity appVersionEntity = appVersionService.findById(versionId);
                        //入网时版本大于1.1.0走新逻辑
                        if (appVersionEntity.getVersionCode().compareTo("1.1.1") >= 0) {
                            response.setIsNewLogic(BoolEnum.TRUE);
                        } else {
                            response.setIsNewLogic(BoolEnum.FALSE);
                        }
                    }
                }
            }
        }
    }
}
