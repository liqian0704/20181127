package com.yeepay.g3.core.laike.service.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.common.Amount;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.QRPayService;
import com.yeepay.g3.facade.laike.enumtype.BoolEnum;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import com.yeepay.g3.facade.laike.enumtype.S0LevelEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ErrorCodeSource;
import com.yeepay.g3.facade.laike.exception.ExternalReturnCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayResponseDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeResponseDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description: 二维码支付ServiceImpl
 * Author: jiawen.huang
 * Date: 16/12/6
 * Time: 19:10
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class QRPayServiceImpl extends AbstractService implements QRPayService {

	private static Logger LOGGER = LoggerFactory.getLogger(QRPayServiceImpl.class);

	private static String GOODS_NAME_SUFFIX = "-收款";//商户订单后缀

	private static String INDIVIDUAL_GOODS_NAME_PREFIX = "个体户-";//商户订单后缀

	private static String CASHIER_ERROR_CODE = "9999";//收银台授权码不能为空

	private static String YMF_BINDED_ERROR_CODE = "1022";//二维码已绑定

	@Override
	public void bindQRCodeBoard(AccountOpenEntity accountOpenEntity) {
		try {
			QrCodeInfoRequestDTO requestDTO = new QrCodeInfoRequestDTO();
			if (accountOpenEntity.getInviteType().equals(InviteType.SIGNEDPAPER) ||
					accountOpenEntity.getInviteType().equals(InviteType.SELLER_DIRECT_SALE)) {
				requestDTO.setQrCodeID(accountOpenEntity.getInviteCode());
				requestDTO.setCustomerNumber(accountOpenEntity.getMerchantNo());
				QrCodeInfoResponseDTO responseDTO = purchaseQrCodeFacade.bindCustomerInfo(requestDTO);
				if (!ExternalReturnCode.YMF_SUCCESS_CODE.equals(responseDTO.getReturnCode())) {
					if (4 != responseDTO.getReturnCode().length()) {
						throw new LaikeSysException(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
					} else {
						if (YMF_BINDED_ERROR_CODE.equals(responseDTO.getReturnCode())
								&& accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.MICRO)) {
							LOGGER.info(responseDTO.getReturnCode() + responseDTO.getReturnMsg());
						} else {
							throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.YMF,
									responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
						}
					}
				}
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.YMF_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
    public ScanQrCodeResponseDTO generatePayQR(Amount orderAmount, Amount gratuityAmount, PaySource paySource, String merchantNo, String merchantName, String ip, CompanyTypeEnum companyTypeEnum, BoolEnum s0Balance) {
        return doGeneratePayCode(null, orderAmount, gratuityAmount, paySource, merchantNo, merchantName, true, ip, companyTypeEnum, s0Balance);
    }

	@Override
    public ScanQrCodeResponseDTO generateUnionPayQR(String memberNo, Amount orderAmount, PaySource paySource, Amount gratuityAmount, String merchantNo, String merchantName, boolean notSelf, String ip, CompanyTypeEnum companyTypeEnum, BoolEnum s0Balance) {
        return doGeneratePayCode(memberNo, orderAmount, gratuityAmount, paySource, merchantNo, merchantName, notSelf, ip, companyTypeEnum, s0Balance);
    }

	@Override
    public PassivePayResponseDTO passivePay(Amount orderAmount, Amount gratuityAmount, String userPayCode, String merchantNo, String merchantName, String ip, String deviceSn, CompanyTypeEnum companyTypeEnum) {
        try {
			PassivePayRequestDTO requestDTO = new PassivePayRequestDTO();
			requestDTO.setRequestID(BizPrefixEnum.PY.getValue() + String.valueOf(System.currentTimeMillis()));
			requestDTO.setOrderAmount(orderAmount);
			requestDTO.setGratuityAmount(gratuityAmount);
			requestDTO.setCustomerNumber(merchantNo);
			requestDTO.setUserIp(ip);
			PaySource paySource = checkPaySource(userPayCode);
            if (CompanyTypeEnum.MICRO.equals(companyTypeEnum)) {
                requestDTO.setProductName(INDIVIDUAL_GOODS_NAME_PREFIX + merchantName + GOODS_NAME_SUFFIX);
                requestDTO.setProductDesc(INDIVIDUAL_GOODS_NAME_PREFIX + merchantName + GOODS_NAME_SUFFIX);
            }
            requestDTO.setProductName(merchantName + GOODS_NAME_SUFFIX);
			requestDTO.setProductDesc(merchantName + GOODS_NAME_SUFFIX);
			requestDTO.setPaySource(paySource);
			requestDTO.setCode(userPayCode);
			requestDTO.setDeviceSn(deviceSn);
			PassivePayResponseDTO responseDTO = ymfTradeBizFacade.passiveDoPay(requestDTO);
			if (!ExternalReturnCode.YMF_SUCCESS_CODE.equals(responseDTO.getReturnCode())) {
				if (responseDTO.getReturnCode().equals(CASHIER_ERROR_CODE)) {
					throw new LaikeSysException(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				} else {
					throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.YMF,
							responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
				}
			} else {
				return responseDTO;
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (YmfTrxException e) {
			throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.YMF,
					e.getCode(), e.getMessage()), e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.YMF_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
	public Map<String, String> getQrCodeByCustomer(String merchantNo) {
		try {
			QrCodeInfoRequestDTO requestDTO = new QrCodeInfoRequestDTO();
			requestDTO.setCustomerNumber(merchantNo);
			QrCodeInfoResponseDTO responseDTO = purchaseQrCodeFacade.getQrCodeByCustomer(requestDTO);
			if (!ExternalReturnCode.YMF_SUCCESS_CODE.equals(responseDTO.getReturnCode())) {
				if (4 != responseDTO.getReturnCode().length()) {
					throw new LaikeSysException(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				} else {
					throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.YMF,
							responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
				}
			} else {
				Map<String, String> resultMap = Maps.newHashMap();
				resultMap.put("qrCodeID", responseDTO.getQrCodeID());
				resultMap.put("qrCodePath", responseDTO.getQrCodePath());
				return resultMap;
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (YmfTrxException e) {
			throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.YMF,
					e.getCode(), e.getMessage()), e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.YMF_UNKNOW_EXCEPTION, e);
		}
	}

	private ScanQrCodeResponseDTO doGeneratePayCode(String memberNo, Amount orderAmount, Amount gratuityAmount,
                                                    PaySource paySource, String merchantNo, String merchantName,
                                                    boolean notSelf, String ip, CompanyTypeEnum companyTypeEnum, BoolEnum s0Balance) {
        try {
            String amountLimit = (String) ConfigUtils.getSysConfigParam(ConfigEnum.YMF_REMIT_AMOUNT_LIMIT);
            HashMap<String, String> timeLimit = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.YMF_REMIT_TIME_LIMIT);
            int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            ScanQrCodeRequestDTO requestDTO = new ScanQrCodeRequestDTO();
			requestDTO.setRequestID(BizPrefixEnum.PY.getValue() + String.valueOf(System.currentTimeMillis()));
			requestDTO.setOrderAmount(orderAmount);
			requestDTO.setGratuityAmount(gratuityAmount);
			requestDTO.setCustomerNumber(merchantNo);
			requestDTO.setUserIp(ip);
            if (CompanyTypeEnum.MICRO.equals(companyTypeEnum)) {
                requestDTO.setProductName(INDIVIDUAL_GOODS_NAME_PREFIX + merchantName + GOODS_NAME_SUFFIX);
                requestDTO.setProductDesc(INDIVIDUAL_GOODS_NAME_PREFIX + merchantName + GOODS_NAME_SUFFIX);
            } else {
                requestDTO.setProductName(merchantName + GOODS_NAME_SUFFIX);
                requestDTO.setProductDesc(merchantName + GOODS_NAME_SUFFIX);
            }
            requestDTO.setUserID(memberNo);
			//requestDTO.setSelfFlag(notSelf ? "1" : "0");//0 本人
			if (PaySource.NCPAY.equals(paySource)) {
				requestDTO.setSelfFlag("0");
			} else {
				requestDTO.setSelfFlag("1");
			}
			if (null == paySource) {
				requestDTO.setPaySource(PaySource.SCAN_CODE);
			} else {
				requestDTO.setPaySource(paySource);
			}
			UserEntity entity = userRepository.findByMemberNo(memberNo);
            //S0结算规则
            if (!S0LevelEnum.S0_STANDARD.equals(entity.getS0Level()) || orderAmount.isLesserThan(new Amount(amountLimit))
                    || !(currentHour >= Integer.parseInt(timeLimit.get("START")) && currentHour < Integer.parseInt(timeLimit.get("END")))) {
                //代理1.0.8之后的版本S0校验，要求报错
                if (BoolEnum.TRUE.equals(s0Balance)) {
                    throw new LaikeSysException(ErrorCode.S0_BALANCE_DENY);
                } else {
                    requestDTO.setBalanceType(BalanceType.T1);
                }
            } else {
                if (null == s0Balance || BoolEnum.TRUE.equals(s0Balance)) {
                    requestDTO.setBalanceType(BalanceType.S0);
                } else {
                    requestDTO.setBalanceType(BalanceType.T1);
                }
            }

            ScanQrCodeResponseDTO responseDTO = ymfTradeBizFacade.scanQrCodeDoPay(requestDTO);
			if (!ExternalReturnCode.YMF_SUCCESS_CODE.equals(responseDTO.getReturnCode())) {
				if (4 != responseDTO.getReturnCode().length()) {
					throw new LaikeSysException(responseDTO.getReturnCode(), responseDTO.getReturnMsg());
				} else {
					throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.YMF,
							responseDTO.getReturnCode(), responseDTO.getReturnMsg()));
				}
			} else {
				return responseDTO;
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (YmfTrxException e) {
			throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.YMF,
					e.getCode(), e.getMessage()), e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.YMF_UNKNOW_EXCEPTION, e);
		}
	}

	/**
	 * 检查收款码来源是否合规
	 *
	 * @param payCode
	 * @return
	 */
	private PaySource checkPaySource(String payCode) {
		Map<String, String> prefixMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_PAY_CODE_PREFIX);
		Set<String> keySet = prefixMap.keySet();
		for (String key : keySet) {
			if (payCode.startsWith(key)) {
				return PaySource.valueOf(prefixMap.get(key));
			}
		}
		throw new LaikeSysException(ErrorCode.PASSIVE_PAY_SOURCE_ILLEGAL);
	}
}
