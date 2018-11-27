package com.yeepay.g3.core.laike.biz.impl;

import com.google.common.collect.Lists;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.QueryBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.OpenStatusEnum;
import com.yeepay.g3.facade.laike.enumtype.PayProductEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.mer.dto.response.out.AreaDto;
import com.yeepay.g3.utils.common.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 查询biz 现
 * Author: jiawen.huang
 * Date: 16/11/18
 * Time: 15:10
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class QueryBizImpl extends AbstractBiz implements QueryBiz {

	@Override
	public QueryResponse queryBankCity(QueryBankRequest request) {
		Map<String, String> param = bankCodeInfoService.queryCity(request.getProvinceCode());
		QueryResponse response = new QueryResponse();
		response.setResultMap(param);
		return response;
	}

	@Override
	public QueryResponse queryAreaInfo(QueryAreaInfoRequest request) {
		List list = bankCodeInfoService.queryAreaInfo(request.getCode());
		QueryResponse response = new QueryResponse();
		response.setResultList(list);
		return response;
	}

	@Override
	public QueryResponse queryBranchBank(QueryBankRequest request) {
		Map<String, String> param = bankCodeInfoService.queryBranchBank(request.getHeadBankCode(),
				request.getProvinceCode(), request.getCityCode());
		QueryResponse response = new QueryResponse();
		response.setResultMap(param);
		return response;
	}

	@Override
	public QueryResponse queryAccountBalance(BaseRequest request) {
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
		if (!OpenStatusEnum.SUCCESS.equals(accountOpenEntity.getOpenStatus())) {
			throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_DENY);
		}
		Map<String, String> param = accountManageService.getBalance(accountOpenEntity.getMerchantNo());
		param.put("merchantName", accountOpenEntity.getMerchantName());
		QueryResponse response = new QueryResponse();
		response.setResultMap(param);
		return response;
	}

	@Override
	public QueryResponse queryQRCode(BaseRequest request) {
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
		Map<String, String> param = qrPayService.getQrCodeByCustomer(accountOpenEntity.getMerchantNo());
		param.put("merchantName", accountOpenEntity.getMerchantName());
		QueryResponse response = new QueryResponse();
		response.setResultMap(param);
		return response;
	}

	@Override
	public QueryResponse querySettleBank(BaseRequest request) {
		List<Object> list = bankCodeInfoService.querySettleBank();
		QueryResponse response = new QueryResponse();
		response.setResultList(list);
		return response;
	}

	@Override
    public CardBinResponse queryCardBinAndSettleList(CardBinRequset requset) {
        CardBinResponse response = new CardBinResponse();
        try {
			if (null != requset.getBankCardNo()) {
				BankCardInfo bankCardInfo = cardService.getBankCode(requset.getBankCardNo());
				if (checkSettleCard(bankCardInfo.getBankCode())) {
					response.setBankCode(bankCardInfo.getBankCode());
					response.setBankName(bankCardInfo.getBankName());
				}
			}
		} catch (LaikeSysException e) {
			if (e.getDefineCode().equals(ErrorCode.CARD_TYPE_IS_ILLEGAL)) {
                throw e;
            }
        }
		List<Object> list = bankCodeInfoService.querySettleBank();
		response.setResultList(list);
		return response;
    }

    @Override
    public CalFeeResponse queryCalFeeAndLimit(BaseRequest request) {
        AccountOpenEntity entity = accountOpenService.findByMemberNo(request.getMemberNo());
        if (null == entity || !OpenStatusEnum.SUCCESS.equals(entity.getOpenStatus())) {
            throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_DENY);
        } else {
			try {
				CalFeeResponse response = new CalFeeResponse();
				List<CalFeeInfo> calFeeInfos = calFeeService.findCalFeeModel(entity.getMerchantNo(), entity.getInviteType());
				CalFeeInfo feLimit = tradeLimitService.limitQuery(entity.getMerchantNo(), "FE", entity.getCompanyType());
				CalFeeInfo ncLimit = tradeLimitService.limitQuery(entity.getMerchantNo(), "NOCARDPAY", entity.getCompanyType());
				for (CalFeeInfo calFeeInfo : calFeeInfos) {
					if (PayProductEnum.ONE_KEY_PAY.equals(calFeeInfo.getPayProduct())) {
						calFeeInfo.setSingleAmount(ncLimit.getSingleAmount());
						calFeeInfo.setDayAmount(ncLimit.getDayAmount());
						calFeeInfo.setMonthAmount(ncLimit.getMonthAmount());
					} else {
						calFeeInfo.setSingleAmount(feLimit.getSingleAmount());
						calFeeInfo.setDayAmount(feLimit.getDayAmount());
						calFeeInfo.setMonthAmount(feLimit.getMonthAmount());
					}
				}
				response.setCalFeeInfoList(calFeeInfos);
				return response;
			} catch (LaikeSysException e) {
				throw new LaikeSysException(ErrorCode.CAL_AND_LIMIT_EXCEPTION);
			}
		}
    }

    @Override
    public QueryResponse queryGeologyLocation(LbsRequest request) {
        QueryResponse response = new QueryResponse();
        HashMap<String, String> lbsMap = lbsService.queryGeologyLocation(request.getLat(), request.getLng());
        HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_PROVINCE_CODE);
        String provinceCode = getLikeByMap(map, lbsMap.get("merProvinceName").substring(0, 2)).get(0);
        String cityCode = null;
        String districtCode = null;
        lbsMap.put("merProvince", provinceCode);
        lbsMap.put("merProvinceName", lbsMap.get("merProvinceName"));
        if (null == provinceCode) {
            throw new LaikeSysException(ErrorCode.LBS_INFO_NOT_EXIST);
        }
        List<AreaDto> cityList = bankCodeInfoService.queryAreaInfo(provinceCode);
        for (AreaDto areaDto : cityList) {
            if (lbsMap.get("merCityName").equals(areaDto.getName())) {
                cityCode = areaDto.getCode();
            }
        }
        if (null == cityCode) {
            throw new LaikeSysException(ErrorCode.LBS_INFO_NOT_EXIST);
        }
        lbsMap.put("merCity", cityCode);
        List<AreaDto> districtList = bankCodeInfoService.queryAreaInfo(cityCode);
        for (AreaDto areaDto : districtList) {
            if (lbsMap.get("merDistrictName").equals(areaDto.getName())) {
                districtCode = areaDto.getCode();
            }
        }
        if (null == districtCode) {
            throw new LaikeSysException(ErrorCode.LBS_INFO_NOT_EXIST);
        }
        lbsMap.put("merDistrict", districtCode);
        response.setResultMap(lbsMap);
        return response;
    }

    /**
     * 检查是否支持此结算卡
     *
     * @param settleBankCode
     * @return
     */
    private boolean checkSettleCard(String settleBankCode) {
        if (!StringUtils.isEmpty(settleBankCode)) {
            String retBankcode = ((HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_BANK_CODE_TRANSFER)).get(settleBankCode);
            String bankCode = retBankcode == null ? settleBankCode : retBankcode;
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
     * 模糊匹配map中的key值
     *
     * @param map
     * @param keyLike
     * @return
     */
    private List<String> getLikeByMap(Map<String, String> map, String keyLike) {
        List<String> list = Lists.newArrayList();
        for (Map.Entry<String, String> entity : map.entrySet()) {
            if (entity.getKey().indexOf(keyLike) > -1) {
                list.add(entity.getValue());
            }
        }
        return list;
    }
}
