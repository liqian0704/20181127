package com.yeepay.g3.core.laike.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.AttachmentEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.AllianceService;
import com.yeepay.g3.core.laike.service.CSMerchantService;
import com.yeepay.g3.core.laike.utils.AttachmentsAttribute;
import com.yeepay.g3.core.laike.utils.SecurityUtil;
import com.yeepay.g3.facade.laike.enumtype.AccountType;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import com.yeepay.g3.facade.laike.enumtype.OpenStatusEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ErrorCodeSource;
import com.yeepay.g3.facade.laike.exception.ExternalReturnCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.mer.dto.MerRespBaseDto;
import com.yeepay.g3.facade.mer.dto.request.*;
import com.yeepay.g3.facade.mer.dto.response.out.MerAreaConvertCodeRespDto;
import com.yeepay.g3.facade.mer.enumtype.*;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description: 客户中心接口实现
 * Author: jiawen.huang
 * Date: 16/12/5
 * Time: 15:39
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class CSMerchantServiceImpl extends AbstractService implements CSMerchantService {

	private static String REGIST_MEMBER_MAIL_SUFFIX = "@liker.com";//会员注册后缀

	private static String CALL_BACK_MER_URL = "liker";//入网中心回调

	private static String CS_MERCHANT_PARAMS_VALID = "REG30002";//入网中心参数异常，业务性强，不用放全局类维护

	private static String CS_MERCHANT_PARAMS_ILLEGAL = "REG30003";//入网中心参数验证失败，业务性强，不用放全局类维护

	private static String ID_CARD_VALID_DATE = "长期";

	private static String REGIST_LOL_MAIL_SUFFIX = "@lol.com";//展业注册后缀

	private static String DEFAULT_ID_DATE = "2111-11-11";//身份证默认日期

	@Autowired
	protected AllianceService allianceService;

	@Override
	public AccountOpenEntity gatherBaseInfo(AccountOpenEntity entity) {
		try {
			HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
			String microMerL1 = (String) ConfigUtils.getSysConfigParam(ConfigEnum.MICRO_MER_LEVEL1);
			String microMerL2 = (String) ConfigUtils.getSysConfigParam(ConfigEnum.MICRO_MER_LEVEL2);
			MerNetInDto requestDTO = new MerNetInDto();
			requestDTO.setRequestNo(entity.getId());
			requestDTO.setSubmit(false);//该接口客户中心第一步，不能提交
			if (entity.getInviteType().equals(InviteType.DIRECT_SALE)) {
				requestDTO.setMerRule(MerRuleEnum.ZXSH);
				requestDTO.setSellerNo((String) ConfigUtils.getSysConfigParam(ConfigEnum.LAIKE_DIRECT_SELLER));
			} else if (entity.getInviteType().equals(InviteType.SELLER_DIRECT_SALE)) {
				requestDTO.setMerRule(MerRuleEnum.ZXSH);
				requestDTO.setSellerNo(entity.getAgentNo());
			} else {
				if (null != entity.getInviteCode() && entity.getInviteCode().length() > 6 && InviteType.INVITECODE.equals(entity.getInviteType())) {
					requestDTO.setAgentNo(allianceService.getLord(entity.getInviteCode()));
				} else {
					requestDTO.setAgentNo(entity.getAgentNo());
				}
				requestDTO.setMerRule(MerRuleEnum.ZSH);
			}
			requestDTO.setMerSource(MerSourceEnum.LK);
			requestDTO.setRelationType(RelationTypeEnum.LKDZ);
			requestDTO.setMerLoginPassword(SecurityUtil.digest2G(StringUtils.right(entity.getLegalIdCard(), 8), SecurityUtil.DEFAULT_ENCODE));
			requestDTO.setMerSignType(entity.getCompanyType().equals(CompanyTypeEnum.ENTERPRISE) ?
					MerSignTypeEnum.QY : entity.getCompanyType().equals(CompanyTypeEnum.ENTER_UNION) ?
					MerSignTypeEnum.QY : entity.getCompanyType().equals(CompanyTypeEnum.INDIVIDUAL) ?
					MerSignTypeEnum.GT : MerSignTypeEnum.ZRR);
			requestDTO.setMerQuaFileType(entity.getCompanyType().equals(CompanyTypeEnum.ENTERPRISE) ?
					MerQuaFileTypeEnum.CORP_CODE : entity.getCompanyType().equals(CompanyTypeEnum.ENTER_UNION) ?
					MerQuaFileTypeEnum.UNI_CREDIT_CODE : entity.getCompanyType().equals(CompanyTypeEnum.INDIVIDUAL) ?
					MerQuaFileTypeEnum.CORP_CODE : null);
			requestDTO.setMerQuaFileCard(entity.getBizNo());
			requestDTO.setMerFullName(entity.getMerchantName());
			requestDTO.setMerShortName(entity.getMerchantName());
			requestDTO.setLegalLocation(LegalLocationEnum.CHINA);
			requestDTO.setLegalPersonId(entity.getLegalIdCard());
			requestDTO.setLegalName(entity.getLegalName());
			requestDTO.setLegalPhone(entity.getPhoneNo());
			if (requestDTO.getMerSignType().equals(MerSignTypeEnum.ZRR)) {
				//自然人填默认值
				requestDTO.setMerLevel1No(microMerL1);
				requestDTO.setMerLevel2No(microMerL2);
			} else {
				requestDTO.setMerLevel1No(entity.getMerLevel1No());
				requestDTO.setMerLevel2No(entity.getMerLevel2No());
			}
			requestDTO.setMerProvince(entity.getMerProvince());
			requestDTO.setMerCity(entity.getMerCity());
			requestDTO.setMerDistrict(entity.getMerDistrict());
			requestDTO.setMerAddress(entity.getMerAddress());
			requestDTO.setMerContactName(entity.getLegalName());
			if (ID_CARD_VALID_DATE.equals(entity.getLegalIdEnd())) {
				requestDTO.setLegalPersonIdLong(true);
			} else {
				requestDTO.setLegalPersonIdLong(false);
				try {
					requestDTO.setLegalPersonIdExpiry(DateUtils.parseDate(entity.getLegalIdEnd(), DateUtils.DATE_FORMAT_DATEONLY));
				} catch (Exception e) {
					requestDTO.setLegalPersonIdExpiry(DateUtils.parseDate(DEFAULT_ID_DATE, DateUtils.DATE_FORMAT_DATEONLY));
				}
			}
			requestDTO.setMerContactPhone(entity.getPhoneNo());
			requestDTO.setMerContactEmail(entity.getPhoneNo() + REGIST_MEMBER_MAIL_SUFFIX);
			requestDTO.setCallBackMerUrl(CALL_BACK_MER_URL);
			MerRespBaseDto responseDTO = merNetInFacade.merNetInWorkFlow(requestDTO);
			if (responseDTO.getReturnCode().equals(map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE))) {
				if (StringUtils.isEmpty(entity.getOpRegNo())) {
					entity.setOpRegNo(responseDTO.getExternalId());
				}
				if (StringUtils.isEmpty(entity.getMerchantNo())) {
					entity.setMerchantNo(responseDTO.getMerNo());
				}
				if (entity.getOpenStatus().getStep() != 4) {
					entity.setOpenStatus(OpenStatusEnum.INFO_SUBMIT);
					accountOpenRepository.updateStatus(entity);
				}
				return entity;
			} else {
				//检查特殊返回码
				filteSpecialCode(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.CS_MERCHANT,
						responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MERCHANT_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
	public AccountOpenEntity gatherBizInfo(AccountOpenEntity entity, boolean submit) {
		try {
			HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
			MerQuaInfoDto requestDTO = new MerQuaInfoDto();
			requestDTO.setSubmit(submit);
			requestDTO.setRequestNo(entity.getId());
			requestDTO.setExternalId(entity.getOpRegNo());
			requestDTO.setAgentNo(entity.getAgentNo());
			requestDTO.setMerNo(entity.getMerchantNo());
			requestDTO.setAccountLicense(entity.getAccountLicense());
			requestDTO.setTaxRegistCert(CompanyTypeEnum.ENTERPRISE == entity.getCompanyType() ? entity.getTaxNo() : null);
			requestDTO.setOrgCode(CompanyTypeEnum.ENTERPRISE == entity.getCompanyType() ? entity.getOrgNo() : null);
			if (StringUtils.isNotBlank(entity.getOrgNoEnd())) {
				requestDTO.setOrgCodeExpiry(DateUtils.parseDate(entity.getOrgNoEnd(), DateUtils.DATE_FORMAT_DATEONLY));
			}
			requestDTO.setOrgCodeLong(CompanyTypeEnum.ENTERPRISE == entity.getCompanyType() ? false : false);
			MerRespBaseDto responseDTO = merQuaInfoFacade.merQuaInfoWorkFlow(requestDTO);
			if (responseDTO.getReturnCode().equals(map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE))) {
				if (submit) {
					if (entity.getCompanyType().equals(CompanyTypeEnum.MICRO) &&
							entity.getOpenStatus().equals(OpenStatusEnum.RETURN)) {
						entity.setOpenStatus(OpenStatusEnum.PAY_SUCCESS);
					} else {
						entity.setOpenStatus(OpenStatusEnum.AUDITING);
					}
					accountOpenRepository.updateStatus(entity);
				} else {
					if (entity.getOpenStatus().getStep() != 4) {
						entity.setOpenStatus(OpenStatusEnum.BIZ_SUBMIT);
						accountOpenRepository.updateStatus(entity);
					}
				}
				return entity;
			} else {
				//检查特殊返回码
				filteSpecialCode(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.CS_MERCHANT,
						responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
			}
		} catch (ParseException e) {
			throw new LaikeSysException(ErrorCode.PARAM_EXCEPTION, e);
		} catch (IllegalArgumentException e) {
			throw new LaikeSysException(ErrorCode.PARAM_EXCEPTION, e);
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MERCHANT_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
	public AccountOpenEntity gatherSettleInfo(AccountOpenEntity entity, boolean submit) {
		try {
			HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
			MerSettleDto requestDTO = new MerSettleDto();
			requestDTO.setSubmit(submit);
			requestDTO.setRequestNo(entity.getId());
			requestDTO.setMerNo(entity.getMerchantNo());
			requestDTO.setAgentNo(entity.getAgentNo());
			requestDTO.setExternalId(entity.getOpRegNo());
			requestDTO.setBankAcountType(CompanyTypeEnum.ENTERPRISE == entity.getCompanyType() ?
					BankAccountTypeEnum.CORPORATE : BankAccountTypeEnum.PERSONAL);
			requestDTO.setBankcardNo(entity.getSettleCardNo());
			//开户省和市需要转换成6位的
			if (StringUtils.isNotBlank(entity.getBankProvinceCode())) {
				MerAreaConvertCodeRespDto convertCodeRespDto = merOutInvokeFacade.g2AreaToCusArea(
						entity.getBankProvinceCode());
				if (map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE).equals(convertCodeRespDto.getReturnCode())) {
					requestDTO.setBankProvince(convertCodeRespDto.getCode());
				}
			}
			String bankCityCode = entity.getBankCityCode();
			if (StringUtils.isNotBlank(bankCityCode)) {
				if (bankCityCode.length() == 2) {
					//如果市为2位转为4位
					bankCityCode = entity.getBankProvinceCode() + bankCityCode;
				}
				MerAreaConvertCodeRespDto convertCodeRespDto = merOutInvokeFacade.g2AreaToCusArea(bankCityCode);
				if (map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE).equals(convertCodeRespDto.getReturnCode())) {
					requestDTO.setBankCity(convertCodeRespDto.getCode());
				}
			}
			requestDTO.setHeadBankName(entity.getSettleBankCode());
			requestDTO.setBankName((Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_BRANCH_CODE) ?
					entity.getBranchBankCode() : entity.getBranchBankName());
			MerRespBaseDto responseDTO = merSettleFacade.merSettleWorkFlow(requestDTO);
			if (responseDTO.getReturnCode().equals(map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE))) {
				if (submit) {
					if (entity.getCompanyType().equals(CompanyTypeEnum.MICRO) &&
							entity.getOpenStatus().equals(OpenStatusEnum.RETURN)) {
						entity.setOpenStatus(OpenStatusEnum.PAY_SUCCESS);
					} else {
						entity.setOpenStatus(OpenStatusEnum.AUDITING);
					}
					accountOpenRepository.updateStatus(entity);
				} else {
					if (entity.getOpenStatus().getStep() != 4) {
						entity.setOpenStatus(OpenStatusEnum.SETTLE_SUBMIT);
						accountOpenRepository.updateStatus(entity);
					}
				}
				return entity;
			} else {
				if (!submit) {
					entity.setOpenStatus(OpenStatusEnum.BIZ_SUBMIT);
					accountOpenRepository.updateStatus(entity);
				}
				//检查特殊返回码
				filteSpecialCode(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.CS_MERCHANT,
						responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MERCHANT_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
	public AccountOpenEntity gatherImageInfo(AccountOpenEntity entity, AttachmentEntity attachmentEntity, boolean submit) {
		try {
			HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
			Map<String, String> ocrResult = Maps.newHashMap();
			MerQuaFileDto requestDTO = new MerQuaFileDto();
			requestDTO.setRequestNo(entity.getId());
			requestDTO.setSubmit(submit);
			requestDTO.setExternalId(entity.getOpRegNo());
			requestDTO.setMerNo(entity.getMerchantNo());
			requestDTO.setAgentNo(entity.getAgentNo());
			requestDTO.setFileInfo(convert2Json(entity, attachmentEntity));
			if (entity.getLegalIdCard().equalsIgnoreCase(entity.getOcrLegalIdCard())) {
				ocrResult.put("idCardFrontOcrAuthStatus", "IDENTIFIED_SUCCESS");
			} else {
				ocrResult.put("idCardFrontOcrAuthStatus", "IDENTIFIED_FAIL");
			}
			if (entity.getSettleCardNo().equals(entity.getOcrSettleCardNo())) {
				ocrResult.put("bankCardOcrAuthStatus", "IDENTIFIED_SUCCESS");
			} else {
				ocrResult.put("bankCardOcrAuthStatus", "IDENTIFIED_FAIL");
			}
			ocrResult.put("idCardBackOcrAuthStatus", "IDENTIFIED_SUCCESS");
			requestDTO.setLkOcrAuthResult(JSONUtils.toJsonString(ocrResult));
			//告诉新的
			gatherProductInfo(entity, attachmentEntity);
			MerRespBaseDto responseDTO = merQuaFileFacade.merQuaFileWorkFlow(requestDTO);
			if (responseDTO.getReturnCode().equals(map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE))) {
				if (submit) {
					if (entity.getCompanyType().equals(CompanyTypeEnum.MICRO) &&
							entity.getOpenStatus().equals(OpenStatusEnum.RETURN)) {
						entity.setOpenStatus(OpenStatusEnum.PAY_SUCCESS);
					} else {
						entity.setOpenStatus(OpenStatusEnum.AUDITING);
					}
					accountOpenRepository.updateStatus(entity);
				} else {
					if (entity.getOpenStatus().getStep() != 4) {
						entity.setOpenStatus(OpenStatusEnum.IMG_SUBMIT);
						accountOpenRepository.updateStatus(entity);
					}
				}
				return entity;
			} else {
				//检查特殊返回码
				filteSpecialCode(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.CS_MERCHANT,
						responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MERCHANT_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
	public AccountOpenEntity gatherAllianceInfo(AccountOpenEntity entity, AttachmentEntity attachmentEntity) {
		try {
			HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
			MerSignTypeNetInDto requestDTO = new MerSignTypeNetInDto();
			requestDTO.setRequestNo(entity.getId());
			requestDTO.setMerSource(MerSourceEnum.LOL);
			requestDTO.setMerRule(MerRuleEnum.TGY);
			requestDTO.setMerContactEmail(entity.getPhoneNo() + REGIST_LOL_MAIL_SUFFIX);
			requestDTO.setMerFullName(entity.getMerchantName());
			requestDTO.setMerShortName(entity.getMerchantName());
			requestDTO.setLegalName(entity.getLegalName());
			requestDTO.setLegalPersonId(entity.getLegalIdCard());
			if (ID_CARD_VALID_DATE.equals(entity.getLegalIdEnd())) {
				requestDTO.setLegalPersonIdLong(true);
			} else {
				requestDTO.setLegalPersonIdLong(false);
				try {
					requestDTO.setLegalPersonIdExpiry(DateUtils.parseDate(entity.getLegalIdEnd(), DateUtils.DATE_FORMAT_DATEONLY));
				} catch (Exception e) {
					requestDTO.setLegalPersonIdExpiry(DateUtils.parseDate(DEFAULT_ID_DATE, DateUtils.DATE_FORMAT_DATEONLY));
				}
			}
			requestDTO.setMerContactPhone(entity.getPhoneNo());
			requestDTO.setMerLevel1No(entity.getMerLevel1No());
			requestDTO.setMerLevel2No(entity.getMerLevel2No());
			requestDTO.setMerProvince(entity.getMerProvince());
			requestDTO.setMerCity(entity.getMerCity());
			requestDTO.setMerDistrict(entity.getMerDistrict());
			requestDTO.setMerAddress(entity.getMerAddress());
			requestDTO.setBankcardNo(entity.getSettleCardNo());
			requestDTO.setHeadBankName(entity.getSettleBankCode());
			requestDTO.setBankName(entity.getBranchBankCode());
			if (StringUtils.isNotBlank(entity.getBankProvinceCode())) {
				MerAreaConvertCodeRespDto convertCodeRespDto = merOutInvokeFacade.g2AreaToCusArea(
						entity.getBankProvinceCode());
				if (map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE).equals(convertCodeRespDto.getReturnCode())) {
					requestDTO.setBankProvince(convertCodeRespDto.getCode());
				}
			}
			String bankCityCode = entity.getBankCityCode();
			if (StringUtils.isNotBlank(bankCityCode)) {
				if (bankCityCode.length() == 2) {
					//如果市为2位转为4位
					bankCityCode = entity.getBankProvinceCode() + bankCityCode;
				}
				MerAreaConvertCodeRespDto convertCodeRespDto = merOutInvokeFacade.g2AreaToCusArea(bankCityCode);
				if (map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE).equals(convertCodeRespDto.getReturnCode())) {
					requestDTO.setBankCity(convertCodeRespDto.getCode());
				}
			}
			requestDTO.setHeadBankName(entity.getSettleBankCode());
			requestDTO.setBankName((Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_BRANCH_CODE) ?
					entity.getBranchBankCode() : entity.getBranchBankName());
			requestDTO.setFileInfo(convert2Json(entity, attachmentEntity));
			MerRespBaseDto responseDTO = merLaiKeExtensionFacade.personNetInWorkFlow(requestDTO);
			if (responseDTO.getReturnCode().equals(map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE))) {
				if (StringUtils.isEmpty(entity.getMerchantNo())) {
					entity.setMerchantNo(responseDTO.getMerNo());
				}
				return entity;
			} else {
				//检查特殊返回码
				filteSpecialCode(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.CS_MERCHANT,
						responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MERCHANT_UNKNOW_EXCEPTION, e);
		}
	}


	private AccountOpenEntity gatherProductInfo(AccountOpenEntity accountOpenEntity, AttachmentEntity attachmentEntity) {
		try {
			HashMap<String, String> map0 = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
			MerProductInfoDto requestDTO = new MerProductInfoDto();
			Map<String, Object> map = Maps.newHashMap();
			List<Map<String, String>> openProductsList = Lists.newArrayList();
			Map<String, String> payTypeMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_PRODUCT_PAY_TYPE_PARAMS);
			List<String> list = (ArrayList<String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_OPEN_PRODUCTS_PARAMS);
			for (String openProduct : list) {
				Map<String, String> pMap = Maps.newHashMap();
				pMap.put("id", openProduct);
				openProductsList.add(pMap);
			}
			map.put("openProducts", openProductsList);
			map.put("payType", payTypeMap);
			requestDTO.setRequestNo(accountOpenEntity.getId());
			requestDTO.setExternalId(accountOpenEntity.getOpRegNo());
			requestDTO.setOpenProducts(JSONUtils.toJsonString(map));
			if (accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.INDIVIDUAL) ||
					accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.MICRO)) {
				//个体小微,协议地址传签名
				requestDTO.setAgreementFileUrl(attachmentEntity.getSignImg());
			} else {
				if ((Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LAIKE_AGREEMENT_SIGNAL)) {
					requestDTO.setAgreementFileUrl(attachmentEntity.getAgreementImg());
				} else {
					requestDTO.setAgreementFileUrl(attachmentEntity.getAgreementImg() + "," +
							attachmentEntity.getAgreementImg2());
				}
			}
			requestDTO.setAgentNo(accountOpenEntity.getAgentNo());
			requestDTO.setMerNo(accountOpenEntity.getMerchantNo());
			requestDTO.setSubmit(false);
			MerRespBaseDto responseDTO = merProductInfoFacade.confirmAgreementWorkFlow(requestDTO);
			if (responseDTO.getReturnCode().equals(map0.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE))) {
				return accountOpenEntity;
			} else {
				filteSpecialCode(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.CS_MERCHANT,
						responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MERCHANT_UNKNOW_EXCEPTION, e);
		}
	}

	/**
	 * 附件信息转客户中心要求的json格式
	 *
	 * @param attachmentEntity
	 * @return
	 */
	private String convert2Json(AccountOpenEntity accountOpenEntity, AttachmentEntity attachmentEntity) {
		List<Map<String, String>> list = Lists.newArrayList();
		Field[] fields = attachmentEntity.getClass().getDeclaredFields();
		for (Field field : fields) {
			AttachmentsAttribute attribute = field.getAnnotation(AttachmentsAttribute.class);
			if (null != attribute) {
				Map<String, String> map = Maps.newHashMap();
				try {
					field.setAccessible(true);
					String quaUrl = (String) field.get(attachmentEntity);
					if (StringUtils.isBlank(quaUrl)) {
						continue;
					}
					//来客不传DZXY
					if (attribute.fieldName().equals("DZXY") && AccountType.LK.equals(accountOpenEntity.getAccountType())) {
						continue;
					}
					map.put("quaType", attribute.fieldName());
					map.put("quaUrl", quaUrl);
					list.add(map);
				} catch (IllegalAccessException e) {
					throw new LaikeSysException(ErrorCode.ATTACH_ATTRIBUTE_ILLEGAL, e);
				}
			}
		}
		return JSONUtils.toJsonString(list);
	}

	/**
	 * 指定返回码过滤
	 *
	 * @param returnCode
	 */
	private void filteSpecialCode(String returnCode, String returnMsg) {
		HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
		if (returnCode.equals(map.get(CS_MERCHANT_PARAMS_VALID))
				|| returnCode.equals(map.get(CS_MERCHANT_PARAMS_ILLEGAL))) {
			throw new LaikeSysException(returnCode, returnMsg);
		}
	}
}
