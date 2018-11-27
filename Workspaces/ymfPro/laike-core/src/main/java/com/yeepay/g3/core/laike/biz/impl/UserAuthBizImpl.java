package com.yeepay.g3.core.laike.biz.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.EnvironmemntUtil;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.UserAuthBiz;
import com.yeepay.g3.core.laike.entity.OperateRecordEntity;
import com.yeepay.g3.core.laike.entity.SmsCodeEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.*;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.member.param.MemberParam;
import com.yeepay.g3.facade.yop.oauth2.dto.OAuth2AccessToken;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 用户鉴权biz
 * Author: jiawen.huang
 * Date: 16/11/14
 * Time: 16:26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class UserAuthBizImpl extends AbstractBiz implements UserAuthBiz {

	@Override
	public BaseResponse sendRegisterSms(SendRegisterSmsRequest request) {
		//未注册检查和初始化用户
		UserEntity userEntity = checkAndCreate(request);
		//检查短信发送频率控制
		securityControlService.checkFreeze(userEntity.getPhoneNo(), ControlTypeEnum.SMS_SEND_CONTROL);
		//生成本次二维码
		SmsCodeEntity smsCodeEntity = smsCodeService.getAvaliable(request.getPhoneNo(), SmsTypeEnum.REGISTER);
		//发送二维码
		notifyService.sendSmsRandom(request.getPhoneNo(), smsCodeEntity.getSmsCode(), smsCodeEntity.getSmsType());
		//增加发送次数控制
		securityControlService.increaseCount(request.getPhoneNo(), ControlTypeEnum.SMS_SEND_CONTROL);
		return new BaseResponse();
	}

	@Override
	public BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest request) {
		//检查没注册
		UserEntity userEntity = getUnRegisterUser(request.getPhoneNo(), request.getAppSourceEnum());
		//验证验证码
		verifySMS(request.getPhoneNo(), request.getSmsCode(), SmsTypeEnum.REGISTER);
		//三代注册
		String memeberNo = memberService.register(request);
		//更新本地
		userService.update2Register(memeberNo, userEntity);
		return new BaseResponse();
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		UserEntity userEntity = userService.findRegisterUser(request.getPhoneNo(), request.getAppSourceEnum());
		LoginResponse response = checkLoginPwd(userEntity, request);
		if (request.getAppSourceEnum().equals(AppSourceEnum.LIKER)) {
			response.setBannerUrl((String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_BANNER_URL));
			response.setDeviceMallUrl((String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_DEVICE_MALL_URL));
			response.setRole(userEntity.getRole());
			response.setInviteCode(userEntity.getInviteCode());
		} else if (request.getAppSourceEnum().equals(AppSourceEnum.ALLIANCE)) {
			response.setUserGuideUrl((String) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_USER_GUIDE_URL));
		}
		response.setHeadUrl(EnvironmemntUtil.getWebHost());
		response.setPhoneNo(userEntity.getPhoneNo());
		response.setUserStatus(userEntity.getUserStatus());
		return response;
	}

	@Override
	public BaseResponse logout(LogoutReuqest request) {
		//获取注册用户
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		checkDeviceChange(userEntity.getMemberNo(), request);
		//移除老设备
		yopOauthService.revokeToken(request.getToken());
		//更新登陆快照
		userEntity.setToken("");
		saveRequestSnapt(userEntity.getMemberNo(), request);
		return new BaseResponse();
	}

	@Override
	public RefreshTKResponse refreshTK(RefreshTKRequest request) {
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		checkDeviceChange(userEntity.getMemberNo(), request);
		OAuth2AccessToken oAuth2AccessToken = yopOauthService.refreshToken(request.getGrantType(), request.getRefreshToken());
		userEntity.setToken(oAuth2AccessToken.getValue());
		saveRequestSnapt(userEntity.getMemberNo(), request);
		RefreshTKResponse response = new RefreshTKResponse();
		response.setAccessToken(oAuth2AccessToken.getValue());
		response.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
		response.setExpiration(DateUtils.getTimeStampStr(oAuth2AccessToken.getExpiration()));
		response.setMemberNo(userEntity.getMemberNo());
		return response;
	}

	@Override
	public BaseResponse changePwd(ChangePwdRequest request) {
		//获取用户
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		securityControlService.checkFreeze(userEntity.getPhoneNo(), ControlTypeEnum.RESET_PWD_CONTROL);
		try {
			//三代改密码
			memberService.changePwd(userEntity.getMemberNo(), request.getPwd(), request.getNewPwd());
		} catch (LaikeSysException e) {
			if (e.getDefineCode().equals(ErrorCode.USER_PWD_ERROR) ||
					e.getDefineCode().equals(ErrorCode.MEMBER_PWD_CHECK_EXCEPTION)) {
				securityControlService.increaseCount(userEntity.getPhoneNo(), ControlTypeEnum.RESET_PWD_CONTROL);
			}
			throw e;
		}
		return new BaseResponse();
	}

	@Override
	public BaseResponse findPwdBySms(BaseRequest request) {
		//获取用户，本地没有三代有的，直接创建
		UserEntity userEntity = getRegisterByPhoneNo(request, true);
		//检查找回密码权限是否被冻结
		securityControlService.checkFreeze(userEntity.getPhoneNo(), ControlTypeEnum.FIND_PWD_CONTROL);
		//生成找回密码短信
		SmsCodeEntity smsCodeEntity = smsCodeService.getAvaliable(request.getPhoneNo(), SmsTypeEnum.FIND_LOGIN_PWD);
		//发送找回密码短信
		notifyService.sendSmsRandom(request.getPhoneNo(), smsCodeEntity.getSmsCode(), smsCodeEntity.getSmsType());
		//增加找回密码次数
		securityControlService.increaseCount(request.getPhoneNo(), ControlTypeEnum.FIND_PWD_CONTROL);
		return new BaseResponse();
	}

	@Override
	public BaseResponse resetPwdBySms(ResetPwdRequest request) {
		//获取用户
		UserEntity userEntity = getRegisterByPhoneNo(request, false);
		//检查sms code
		verifySMS(request.getPhoneNo(), request.getSmsCode(), SmsTypeEnum.FIND_LOGIN_PWD);
		//重置密码
		memberService.setLoginPassword(userEntity.getMemberNo(), request.getNewPwd());
		return new BaseResponse();
	}

    @Override
    public BaseResponse checkRegister(String phoneNo, AppSourceEnum appSourceEnum) {
        UserEntity userEntity = userService.findByPhoneNo(phoneNo, appSourceEnum);
        if (null == userEntity) {
            throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
        }
        return new BaseResponse();
    }

	/**
	 * 获取用户
	 *
	 * @param request
	 * @param doCreate 如果本地没有三代有，是否创建，true：创建 false：不创建
	 * @return
	 */
	private UserEntity getRegisterByPhoneNo(BaseRequest request, boolean doCreate) {
		UserEntity userEntity = userService.findByPhoneNo(request.getPhoneNo(), request.getAppSourceEnum());
		if (null == userEntity) {
			if (doCreate) {
				MemberParam memberParam = memberService.getMemberByPhoneNo(request.getPhoneNo(), request.getAppSourceEnum());
				if (null != memberParam) {
					return userService.createNew(request, true, memberParam.getMemberNo(), null);//其实不可能出现这一步，除非。。内鬼？
				}
			}
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
		} else if (!userEntity.getUserStatus().equals(UserStatus.REGISTER)) {
			throw new LaikeSysException(ErrorCode.USER_UN_REGISTER);
		}
		return userEntity;
	}

	/**
	 * 获取未注册用户
	 *
	 * @param phoneNo
	 * @return
	 */
	private UserEntity getUnRegisterUser(String phoneNo, AppSourceEnum appSourceEnum) {
		UserEntity userEntity = userService.findByPhoneNo(phoneNo, appSourceEnum);
		if (null == userEntity) {
			throw new LaikeSysException(ErrorCode.USER_UNSEND_SMS);
		} else {
			if (UserStatus.REGISTER.equals(userEntity.getUserStatus())) {
				throw new LaikeSysException(ErrorCode.USER_HAS_REGISTER);
			}
			return userEntity;
		}
	}

	/**
	 * 未注册检查和初始化用户
	 *
	 * @param request
	 * @return
	 */
	private UserEntity checkAndCreate(SendRegisterSmsRequest request) {
		UserEntity userEntity = userService.findByPhoneNo(request.getPhoneNo(), request.getAppSourceEnum());
		Map<String, Object> params = Maps.newHashMap();
		String agentNo = "";
		if (StringUtils.isNotBlank(request.getInviteCode())) {
			agentNo = allianceService.validateInviteCode(request.getInviteCode());
			params.put("agentNo", agentNo);
			params.put("inviteCode", request.getInviteCode());
		}
		if (null != userEntity) {
			if (userEntity.getUserStatus().equals(UserStatus.REGISTER)) {
				throw new LaikeSysException(ErrorCode.USER_HAS_REGISTER);
			}
			if (StringUtils.isNotBlank(request.getInviteCode())) {
				return userService.updateInviteCode(request.getPhoneNo(), request.getAppSourceEnum(), request.getInviteCode(), agentNo);
			}
			return userEntity;
		} else {
			MemberParam param = memberService.getMemberByPhoneNo(request.getPhoneNo(), request.getAppSourceEnum());
			if (null != param) {
				userService.createNew(request, true, param.getMemberNo(), params);//会员中心有，直接注册
				throw new LaikeSysException(ErrorCode.USER_LOGIN_UNION_MEMBER);//提示用会员账号登陆
			} else {
				return userService.createNew(request, false, null, params);
			}
		}
	}

	/**
	 * 验证sms
	 *
	 * @param phoneNo
	 * @param smsCode
	 * @param smsTypeEnum
	 */
	private void verifySMS(String phoneNo, String smsCode, SmsTypeEnum smsTypeEnum) {
		try {
			securityControlService.checkFreeze(phoneNo, ControlTypeEnum.VERIFY_SMS_CONTROL);
			smsCodeService.verify(phoneNo, smsTypeEnum, smsCode);
		} catch (Throwable e) {
			if (e instanceof LaikeSysException) {
				LaikeSysException laikeSysException = (LaikeSysException) e;
				if (laikeSysException.getDefineCode().equals(ErrorCode.SMS_VERIFY_ERROR)) {
					securityControlService.increaseCount(phoneNo, ControlTypeEnum.VERIFY_SMS_CONTROL);
				}
				throw laikeSysException;
			}
		}
	}

	/**
	 * 登陆次数、密码校验，通过的申请oauthToken
	 *
	 * @param userEntity
	 * @param request
	 * @return
	 */
	private LoginResponse checkLoginPwd(UserEntity userEntity, LoginRequest request) {
		try {
			//安全控制
			securityControlService.checkFreeze(userEntity.getPhoneNo(), ControlTypeEnum.LOGIN_CONTROL);
			//密码校验
			memberService.login(userEntity.getMemberNo(), request.getPwd());
			//申请tk，此处yop自己注销了该用户的其他tk
			OAuth2AccessToken oAuth2AccessToken = yopOauthService.getGenerateToken(userEntity.getMemberNo(), request.getAppSourceEnum());
			//更新新tk映射
			userEntity.setToken(oAuth2AccessToken.getValue());
			//更新登陆快照
			saveRequestSnapt(userEntity.getMemberNo(), request);
			LoginResponse response = new LoginResponse();
			response.setAccessToken(oAuth2AccessToken.getValue());
			response.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
			response.setExpiration(DateUtils.getTimeStampStr(oAuth2AccessToken.getExpiration()));
			response.setMemberNo(userEntity.getMemberNo());
			return response;
		} catch (LaikeSysException e) {
			if (e.getDefineCode().equals(ErrorCode.USER_LOGIN_PWD_ERROR) ||
					e.getDefineCode().equals(ErrorCode.MEMBER_PWD_CHECK_EXCEPTION)) {
				securityControlService.increaseCount(userEntity.getPhoneNo(), ControlTypeEnum.LOGIN_CONTROL);
			}
			throw e;
		}
	}

	/**
	 * 设备异常切换检查
	 *
	 * @param request
	 */
	private void checkDeviceChange(String memberNo, BaseRequest request) {
		OperateRecordEntity recordEntity = operateRecodeService.findLastRecode(memberNo, BizTypeEnum.OAUTH);
		if (null != recordEntity && !request.getImei().equals(recordEntity.getLinkPhone())) {
			throw new LaikeSysException(ErrorCode.USER_DEVICE_CHANGE_WARM);
		}
	}

	/**
	 * 存快照
	 *
	 * @param request
	 */
	private void saveRequestSnapt(String memberNo, BaseRequest request) {
		OperateRecordEntity operateRecordEntity = new OperateRecordEntity();
		operateRecordEntity.setMemberNo(memberNo);
		operateRecordEntity.setBizType(BizTypeEnum.OAUTH);
		operateRecordEntity.setBizParam(request.getVersionId());
		operateRecordEntity.setLinkPhone(request.getImei());
		operateRecordEntity.setProvinceName(request.getLocation());
		operateRecodeService.save(operateRecordEntity);
	}

}
