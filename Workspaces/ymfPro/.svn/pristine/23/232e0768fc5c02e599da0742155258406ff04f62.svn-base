package com.yeepay.g3.core.laike.biz.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.SettleS0Biz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.S0RecordEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.*;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.laike.exception.SystemErrorCodeTranslator;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import com.yeepay.g3.utils.query.QueryParam;
import com.yeepay.g3.utils.query.QueryResult;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 秒到结算业务接口实现
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 14:48
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class SettleS0BizImpl extends AbstractBiz implements SettleS0Biz {

	private static final String YMF_NOTIFY_FAIL = "YMF_NOTIFY_FAIL";

    private static final String S0_SETTLE_FEE = "2元/笔";//s0结算手续费

	@Override
	public S0InfoResponse findS0Info(S0InfoRequest request) {
		AccountOpenEntity accountOpenEntity = accountOpenService.findByMerchantNo(request.getMerchantNo());
		if (null == accountOpenEntity) {
			throw new LaikeSysException(ErrorCode.ACCOUNT_NOT_EXIST);
		}
		S0RecordEntity s0RecordEntity = s0RecordService.findByMemberNo(accountOpenEntity.getMemberNo());

		String retBankcode = ((HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_BANK_CODE_TO_RJT)).get(s0RecordEntity.getSettleBankCode());
		String bankCode = retBankcode == null ? s0RecordEntity.getSettleBankCode() : retBankcode;
		S0InfoResponse response = new S0InfoResponse();
		response.setMemberNo(s0RecordEntity.getMemberNo());
		response.setAgentNo(s0RecordEntity.getAgentNo());
		response.setSettleCardNo(s0RecordEntity.getSettleCardNo());
		response.setSettleBankCode(bankCode);
		response.setSettleBankName(s0RecordEntity.getSettleBankName());
		response.setBranchBankCode(s0RecordEntity.getBranchBankCode());
		response.setBranchBankName(s0RecordEntity.getBranchBankName());
		response.setLegalName(accountOpenEntity.getLegalName());
		return response;
	}

	@Override
	public List<ProductionResponse> findAppServiceInfo(BaseRequest request) {
		Map<String, String> url = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_OPEN_PRODUCTION_URL);
		AccountOpenEntity accountOpenEntity = accountOpenService.findByMemberNo(request.getMemberNo());

		List<ProductionResponse> responses = Lists.newArrayList();
		//检查开户状态、初始化产品列表
		if (null != accountOpenEntity && OpenStatusEnum.SUCCESS.equals(accountOpenEntity.getOpenStatus())) {
			//初始化基本产品
			ProductionResponse scanProduct = new ProductionResponse();
			scanProduct.setProductName(AppProductEnum.SCAN_PAY.getDisplayName());
			scanProduct.setProductCode(AppProductEnum.SCAN_PAY);
			scanProduct.setProductStatusEnum(ProductStatusEnum.OPEN);
			try {
				scanProduct.setFeeRate(calFeeService.findCalFeeModel(accountOpenEntity.getMerchantNo(), accountOpenEntity.getInviteType()));
			} catch (LaikeSysException e) {
				//查询费率信息报错，不处理
			}
			responses.add(scanProduct);
			if (InviteType.INVITECODE.equals(accountOpenEntity.getInviteType()) || InviteType.SIGNEDPAPER.equals(accountOpenEntity.getInviteType())) {
				UserEntity userEntity = userService.findRegisterUser(accountOpenEntity.getPhoneNo(), request.getAppSourceEnum());
				//boss用户、代理商子商户
				if (RoleEnum.BOSS.equals(userEntity.getRole()) && !CheckUtils.isEmpty(accountOpenEntity.getAgentNo())) {
                    //如果用户开通了秒到
					if (!accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.ENTERPRISE) && !accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.ENTER_UNION)) {
						if (S0LevelEnum.S0_STANDARD.equals(userEntity.getS0Level())) {
							ProductionResponse s0Product = new ProductionResponse();
							s0Product.setProductName(AppProductEnum.S0_SETTLE.getDisplayName());
							s0Product.setProductCode(AppProductEnum.S0_SETTLE);
							s0Product.setProductStatusEnum(ProductStatusEnum.OPEN);
							FeeRateDetail feeRateDetail = new FeeRateDetail();
							feeRateDetail.setFeeRate(S0_SETTLE_FEE);
							CalFeeInfo calFeeInfo = new CalFeeInfo();
							calFeeInfo.setPayProductName(PayProductEnum.S0_SETTLE.getDisplayName());
							calFeeInfo.setPayProduct(PayProductEnum.S0_SETTLE);
							calFeeInfo.setFeeInfoDesc(feeRateDetail);
							s0Product.setFeeRate(calFeeInfo);
							responses.add(s0Product);
						} else if (null == userEntity.getS0Level() || userEntity.getS0Level().getStep() < 4) {
//							try {
//								if (checkOpenS0Qualification(userEntity, accountOpenEntity)) {
                            ProductionResponse s0Product = new ProductionResponse();
                            s0Product.setProductName(AppProductEnum.S0_SETTLE.getDisplayName());
									s0Product.setProductCode(AppProductEnum.S0_SETTLE);
									s0Product.setProductStatusEnum(ProductStatusEnum.INIT);
									s0Product.setUrl(url.get(AppProductEnum.S0_SETTLE.getValue()));
									responses.add(s0Product);
//								}
//							} catch (LaikeSysException e) {
                            //返回前端信息，不处理异常
//							}
                        }
					}
				}
			}
		}
		return responses;
	}

	@Override
	public ProductionResponse open(OpenProductionRequest request) {
		AccountOpenEntity accountOpenEntity = accountOpenService.findByMemberNo(request.getMemberNo());
		UserEntity userEntity = userService.findBoss(accountOpenEntity.getMerchantNo());
		ProductionResponse response = new ProductionResponse();
		response.setProductCode(request.getProductCode());
		response.setProductName(request.getProductCode().getDisplayName());
		//productCode是S0_SETTLE
		if (AppProductEnum.S0_SETTLE.equals(request.getProductCode())) {
			if (!InviteType.INVITECODE.equals(accountOpenEntity.getInviteType()) && !InviteType.SIGNEDPAPER.equals(accountOpenEntity.getInviteType())) {
				throw new LaikeSysException(ErrorCode.INVITE_TYPE_ILLEGAL);
			}
			if (CompanyTypeEnum.ENTERPRISE.equals(accountOpenEntity.getCompanyType()) || CompanyTypeEnum.ENTER_UNION.equals(accountOpenEntity.getCompanyType())) {
				throw new LaikeSysException(ErrorCode.COMPANY_TYPE_ILLEGAL);
			}
			if (OpenStatusEnum.SUCCESS.equals(accountOpenEntity.getOpenStatus()) && !S0LevelEnum.S0_STANDARD.equals(userEntity.getS0Level())) {
				S0RecordEntity s0RecordEntity = new S0RecordEntity();
				s0RecordEntity.setMemberNo(request.getMemberNo());
				s0RecordEntity.setMerchantNo(accountOpenEntity.getMerchantNo());
                if (((null != userEntity.getInviteCode() && userEntity.getInviteCode().length() > 6) || accountOpenEntity.getInviteCode().length() > 6)
                        && InviteType.INVITECODE.equals(accountOpenEntity.getInviteType())) {
                    s0RecordEntity.setAgentNo(allianceService.getLord(accountOpenEntity.getInviteCode()));
                } else {
                    s0RecordEntity.setAgentNo(accountOpenEntity.getAgentNo());
                }
                s0RecordEntity.setOperateType(OperateTypeEnum.OPEN);
				s0RecordEntity.setSettleCardNo(accountOpenEntity.getSettleCardNo());
				s0RecordEntity.setSettleBankCode(accountOpenEntity.getSettleBankCode());
				s0RecordEntity.setSettleBankName(accountOpenEntity.getSettleBankName());
				s0RecordEntity.setBranchBankCode(accountOpenEntity.getBranchBankCode());
				s0RecordEntity.setBranchBankName(accountOpenEntity.getBranchBankName());
				s0RecordService.createOne(s0RecordEntity);
				try {
					if (checkOpenS0Qualification(userEntity, accountOpenEntity)) {
						//是否因为YMF通知导致失败
						if (checkYmfNotify(request.getMemberNo(), OperateTypeEnum.OPEN)) {
							//发起秒到服务
							settleS0Service.openS0(userEntity.getMerchantNo());
							//计费模板
							settleS0Service.modifyRJTfee(userEntity.getMerchantNo());
						}
						//通知YMF
						settleS0Service.notifyYMF(accountOpenEntity.getMerchantNo());
						s0RecordEntity.setProductStatus(ProductStatusEnum.OPEN);
						userEntity.setS0Level(S0LevelEnum.S0_STANDARD);
						userService.updateS0Level(userEntity);
					}
				} catch (LaikeSysException e) {
					s0RecordEntity.setRemark(e.getMessage());
					if (e.getDefineCode().equals("L30007") || e.getDefineCode().equals("L30021")) {
						s0RecordEntity.setRemark(ExternalSystem.YMF.getValue() + ":" + e.getDefineCode() + e.getMessage());
						BaseEventUtils.sendEventNotInTransaction(ConstantUtil.SEND_S0_MAIL_EVENT, s0RecordEntity, e.getMessage());
					}
					if (e.getDefineCode().equals(ErrorCode.RJT_OPEN_EXCEPTION)) {
						e.setMessage(SystemErrorCodeTranslator.getInstance().getMessage(ErrorCode.RJT_OPEN_EXCEPTION));
					}
					response.setProductStatusEnum(s0RecordEntity.getProductStatus());
					s0RecordService.update(s0RecordEntity);
					throw e;
				}
				response.setProductStatusEnum(s0RecordEntity.getProductStatus());
				s0RecordService.update(s0RecordEntity);
			} else {
				throw new LaikeSysException(ErrorCode.USER_STATUS_ILLEGAL);
			}
		} else {
			throw new LaikeSysException(ErrorCode.PRODUCT_CODE_EXCEPTION);
		}
		return response;
	}

	/**
	 * 检查是否有开通资格
	 *
	 * @param userEntity
	 * @param accountOpenEntity
	 * @return
	 */
	private boolean checkOpenS0Qualification(UserEntity userEntity, AccountOpenEntity accountOpenEntity) {
		S0LevelEnum s0Level = null == userEntity.getS0Level() ?
				S0LevelEnum.LEVEL0 : userEntity.getS0Level();
		S0LevelEnum tempS0Level = s0Level;
		try {
			switch (s0Level) {
				case LEVEL0:
					//结算卡是否支持秒到
//					if (!checkSettleCard(accountOpenEntity.getSettleBankCode())) {
//                        throw new LaikeSysException(ErrorCode.SETTLE_CARD_INVALID);
//                    }
                    s0Level = S0LevelEnum.LEVEL1;
				case LEVEL1:
					//T1结算大于1000
//					if (!checkT1Settle(userEntity.getMerchantNo())) {
//						throw new LaikeSysException(ErrorCode.S0_SETTLE_AMOUNT_INVALID);
//					}
                    s0Level = S0LevelEnum.LEVEL2;
				case LEVEL2:
					//是否本人绑卡
//					if (!cardService.hasBindCard(userEntity.getMerchantNo(), userEntity.getMemberNo())) {
//						throw new LaikeSysException(ErrorCode.BIND_CARD_NOT_EXSIT);
//					}
                    s0Level = S0LevelEnum.LEVEL3;
				case LEVEL3:
                    if (((null != userEntity.getInviteCode() && userEntity.getInviteCode().length() > 6) || accountOpenEntity.getInviteCode().length() > 6)
                            && InviteType.INVITECODE.equals(accountOpenEntity.getInviteType())) {
                        //联盟不验证代理商秒到开通状态
                    } else {
                        //查询代理商秒到开通状态
                        if (!agentRelationService.checkAgentS0Open(userEntity.getMerchantNo())) {
                            throw new LaikeSysException(ErrorCode.AGENT_S0_STATUS_EXCEPTION);
                        }
                    }
                    return true;
			}
			return false;
		} catch (LaikeSysException e) {
			throw e;
		} finally {
			if (!tempS0Level.equals(s0Level)) {
				userEntity.setS0Level(s0Level);
				userService.updateS0Level(userEntity);
			}
		}
	}

//	/**
//	 * 检查结算卡是否支持秒到
//	 *
//	 * @param settleBankCode
//	 * @return
//	 */
//	private boolean checkSettleCard(String settleBankCode) {
//		if (!StringUtils.isEmpty(settleBankCode)) {
//			String retBankcode = ((HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_BANK_CODE_TRANSFER)).get(settleBankCode);
//			String bankCode = retBankcode == null ? settleBankCode : retBankcode;
//			List<BankInfo> list = bankCodeInfoService.querySettleBank();
//			for (BankInfo b : list) {
//				if (bankCode.equals(b.getBankCode())) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	/**
	 * 检查三月内的T1结算资格
	 *
	 * @param merchantNo
	 * @return
	 */
	private boolean checkT1Settle(String merchantNo) {
		QueryParam queryParam = new QueryParam();
		Map<String, Object> param = Maps.newHashMap();
		param.put("merchantNo", merchantNo);
		param.put("startDate", DateUtils.getReqDate(DateUtils.addDay(new Date(), -90)));
		param.put("endDate", DateUtils.getReqDate(new Date()));
		queryParam.setParams(param);
		QueryResult queryResult = likerQueryService.query("QUERY_SETTLE_T1_SUM", queryParam);
		if ((Integer) ((Map) ((List) queryResult.getData()).get(0)).get(1) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 检查ymf通知结果
	 * <p>
	 * 通知失败：false
	 *
	 * @param memberNo
	 * @param operateType
	 * @return
	 */
	private boolean checkYmfNotify(String memberNo, OperateTypeEnum operateType) {
		S0RecordEntity entity = s0RecordService.findByType(memberNo, operateType);
		if (null != entity && StringUtils.isNotEmpty(entity.getRemark()) && entity.getRemark().contains(YMF_NOTIFY_FAIL)) {
			return false;
		}
		return true;
	}
}
