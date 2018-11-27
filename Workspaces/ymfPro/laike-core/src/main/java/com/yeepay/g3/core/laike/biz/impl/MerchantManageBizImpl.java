package com.yeepay.g3.core.laike.biz.impl;

import com.google.gson.Gson;
import com.yeepay.g3.common.laike.utils.RandomUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.MerchantManageBiz;
import com.yeepay.g3.core.laike.biz.QueryPageBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.core.laike.utils.SecurityUtil;
import com.yeepay.g3.facade.laike.dto.QueryListResponse;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
import com.yeepay.g3.facade.laike.dto.VerifyRegisterSmsRequest;
import com.yeepay.g3.facade.laike.dto.boss.RegisterMerRequest;
import com.yeepay.g3.facade.laike.dto.boss.RegisterMerResponse;
import com.yeepay.g3.facade.laike.enumtype.*;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.laike.exception.SystemErrorCodeTranslator;
import com.yeepay.g3.facade.member.param.MemberParam;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.ValidateUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 商户管理
 * Author: wei.li
 * Date: 17/3/2
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class MerchantManageBizImpl extends AbstractBiz implements MerchantManageBiz {

	private static Logger LOGGER = LoggerFactory.getLogger(MerchantManageBizImpl.class);

	@Autowired
	private QueryPageBiz queryPageBiz;

	@Override
	@Transactional
	public RegisterMerResponse registerMer(RegisterMerRequest requestDTO) {

		RegisterMerResponse responseDTO = new RegisterMerResponse();

		Map<String, String> verifyResult = new HashMap<String, String>();

		Map<String, String> registerInfo = new HashMap<String, String>();

		int failCount = 0;

		boolean hasRegister = false;

		String accountId = "";

		List<String> phoneList = requestDTO.getPhoneNoList();
		QueryListResponse queryListResponse = queryPageBiz.queryYmfCustomer(requestDTO);

		//是否开通laike业务
		if (queryListResponse.getCount() != 0) {
			AccountOpenEntity entity = accountOpenService.findByMerchantNo(requestDTO.getMerchantNo());
			//检查商编是否已注册
			if (null != entity) {
                if (InviteType.BIG_MERCHANT.equals(entity.getInviteType())) {
                    entity.setOpRegNo(entity.getOpRegNo() + "," + requestDTO.getOpNo());
                    accountOpenService.createAndUpdate(entity);
                    accountId = entity.getId();
                    hasRegister = true;
                } else {
                    String errorMsg = SystemErrorCodeTranslator.getInstance().getMessage(ErrorCode.MERCHANT_IS_EXIST);
                    responseDTO.setVerifyResult(convert2Map(phoneList, errorMsg));
                    responseDTO.setFailCount(phoneList.size());
                    responseDTO.setMerchantNo(requestDTO.getMerchantNo());
                    responseDTO.setOpNo(requestDTO.getOpNo());
                    responseDTO.setStatus(ResponseStatus.FAILURE);
                    return responseDTO;
                }
            }
			for (String phone : phoneList) {
				try {
					if (ValidateUtils.isMobile(phone)) {
						UserEntity userEntity = userService.findByPhoneNo(phone, AppSourceEnum.LIKER);
						if (null == userEntity) {
							MemberParam param = memberService.getMemberByPhoneNo(phone, AppSourceEnum.LIKER);
							String memberNo;
							if (null == param) {
								String pwd = "lk" + RandomUtils.randomNumberString(4);
								registerInfo.put(phone, pwd);
                                memberNo = memberService.register(convert2RegisterReq(phone, StringUtils.trim(SecurityUtil.encryptL1Info(pwd))));
                            } else {
                                memberNo = param.getMemberNo();
                            }
                            if (phone.equals(requestDTO.getBossUser()) && !hasRegister) {
								AccountOpenEntity openEntity = new AccountOpenEntity();
								openEntity.setMerchantNo(requestDTO.getMerchantNo());
								openEntity.setMerchantName((String) queryListResponse.getList().get(0).get("customerName"));
								openEntity.setCompanyType(requestDTO.getCompanyType());
								openEntity.setInviteType(InviteType.BIG_MERCHANT);
								openEntity.setInviteCode(InviteType.BIG_MERCHANT.getValue());
								openEntity.setOpRegNo(requestDTO.getOpNo());
								openEntity.setMemberNo(memberNo);
								openEntity.setPhoneNo(requestDTO.getBossUser());
								openEntity.setOpenStatus(OpenStatusEnum.SUCCESS);
                                openEntity.setAccountType(AccountType.LK);
                                accountOpenService.createAccount(openEntity);
								accountId = openEntity.getId();
								UserEntity userEntity1 = userService.createUser(phone, requestDTO.getMerchantNo(), memberNo, accountId);
								userService.update2Boss(userEntity1.getMemberNo(), userEntity1.getMerchantNo());
								verifyResult.put(phone, VerifyResultEnum.PASS.getDisplayName());

							} else {
								userService.createUser(phone, requestDTO.getMerchantNo(), memberNo, accountId);
								verifyResult.put(phone, VerifyResultEnum.PASS.getDisplayName());
							}

						} else {
							throw new LaikeSysException(ErrorCode.USER_HAS_REGISTER);
						}
					} else {
						throw new LaikeSysException(ErrorCode.PHONE_ILLEGAL_ARGUMENT);
					}
				} catch (LaikeSysException e) {
					verifyResult.put(phone, VerifyResultEnum.NOPASS.getDisplayName() + "，" + e.getMessage());
					failCount++;
				}
			}
			responseDTO.setFailCount(failCount);
			responseDTO.setMerchantNo(requestDTO.getMerchantNo());
			responseDTO.setOpNo(requestDTO.getOpNo());
			responseDTO.setStatus(failCount == 0 ? ResponseStatus.SUCCESS : ResponseStatus.FAILURE);
			responseDTO.setVerifyResult(verifyResult);
			if (failCount > 0) {
				Gson gson = new Gson();
				throw new LaikeSysException(ErrorCode.OP_REGISTER_MERCHANT_FAIL, gson.toJson(responseDTO));
			}
			//发送密码
			BaseEventUtils.sendEventNotInTransaction(ConstantUtil.SEND_PWD_EVENT, registerInfo);
		} else {
			String errorMsg = SystemErrorCodeTranslator.getInstance().getMessage(ErrorCode.MERCHANT_NOT_EXIST_IN_YMF);
			responseDTO.setVerifyResult(convert2Map(phoneList, errorMsg));
			responseDTO.setFailCount(phoneList.size());
			responseDTO.setMerchantNo(requestDTO.getMerchantNo());
			responseDTO.setOpNo(requestDTO.getOpNo());
			responseDTO.setStatus(ResponseStatus.FAILURE);
		}
		return responseDTO;
	}

	/**
	 * 将手机号、错误信息转成Map
	 *
	 * @param phoneList
	 * @param errorMsg
	 * @return
	 */
	private Map<String, String> convert2Map(List<String> phoneList, String errorMsg) {
		Map<String, String> resultMap = new HashMap();
		for (String list : phoneList) {
			resultMap.put(list, errorMsg);
		}
		return resultMap;
	}

	/**
	 * 组装三代会员注册参数
	 *
	 * @param phone
	 * @param pwd
	 * @return
	 */
	private VerifyRegisterSmsRequest convert2RegisterReq(String phone, String pwd) {
		VerifyRegisterSmsRequest verifyRegisterSmsRequest = new VerifyRegisterSmsRequest();
		verifyRegisterSmsRequest.setPhoneNo(phone);
		verifyRegisterSmsRequest.setPwd(pwd);
		verifyRegisterSmsRequest.setAppSourceEnum(AppSourceEnum.LIKER);
		return verifyRegisterSmsRequest;
	}

}
