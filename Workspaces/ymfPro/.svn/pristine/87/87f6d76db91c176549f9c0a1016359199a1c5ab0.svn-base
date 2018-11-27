package com.yeepay.g3.core.laike.service.impl;


import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.MemberService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.laike.dto.LogoutReuqest;
import com.yeepay.g3.facade.laike.dto.VerifyRegisterSmsRequest;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.member.enumtype.MemberFromWayEnum;
import com.yeepay.g3.facade.member.enumtype.MemberRegTypeEnum;
import com.yeepay.g3.facade.member.enumtype.MemberTypeEnum;
import com.yeepay.g3.facade.member.exception.MemberExistsException;
import com.yeepay.g3.facade.member.exception.MemberNotExistsException;
import com.yeepay.g3.facade.member.param.FastRegMemberInfoParam;
import com.yeepay.g3.facade.member.param.MemberParam;
import com.yeepay.g3.facade.member.param.MemberRegParam;
import com.yeepay.g3.facade.member.param.MemberSecuritySetParam;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.encrypt.HmacSign;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl extends AbstractService implements MemberService {

	@Override
	public MemberParam getMember(String memberNo) {
		if (StringUtils.isBlank(memberNo))
			return null;
		MemberParam param;
		try {
			param = memberAuthenticationFacade.checkMemberByNo(memberNo);
		} catch (MemberNotExistsException e) {
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST, e);
		}
		if (param == null) {
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
		}
		return param;
	}

	@Override
	public boolean checkTradePwd(String memberNo, String pwd) {
		try {
			return memberSecurtiySetFacade.checkDealPassword(memberNo, pwd);
		} catch (Exception e) {
			throw new LaikeSysException(ErrorCode.MEMBER_SYS_EXCEPTION, e);
		}
	}

	/**
	 * 通过登录用户名查找三代用户
	 *
	 * @param loginName 用户名+@liker.com
	 * @return
	 */
	@Override
	public MemberParam getMemberByPhoneNo(String loginName, AppSourceEnum appSourceEnum) {
		MemberParam param = null;
		try {
			String suffix = "@" + appSourceEnum.getValue() + ".com";
			param = memberQueryFacade.queryMemberByLoginName(loginName + suffix);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MEMBER_QUERY_EXCEPTION, e);
		}
		return param;
	}

	@Override
	public MemberParam queryG2MemberByLoginName(String loginName) {
		return memberQueryFacade.queryG2MemberByLoginName(loginName);
	}

	@Override
	public void login(String memberNo, String pwd) {
		if (!checkPwd(memberNo, pwd)) {
			throw new LaikeSysException(ErrorCode.USER_LOGIN_PWD_ERROR);
		}
	}

	@Override
	public Boolean checkPwd(String memberNo, String password) {
		boolean pass = false;
		try {
			MemberParam member = memberQueryFacade.queryMember(memberNo);
			if (member == null) {
				throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
			}
			String pwd = StringUtils.trim(AES.decryptFromBase64(password, ConstantUtil.TRANSFER_KEY));
			pass = memberAuthenticationFacade.checkLogin(member.getLoginName(),
					HmacSign.signToBase64(pwd, ConstantUtil.SIGN_SECRET_KEY), false);
		} catch (MemberNotExistsException e) {
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST, e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MEMBER_PWD_CHECK_EXCEPTION, e);
		}
		return pass;
	}

	@Override
	public void changePwd(String memberNo, String oldPassword, String newPassword) {
		if (checkPwd(memberNo, oldPassword)) {
			setLoginPassword(memberNo, newPassword);
		} else {
			throw new LaikeSysException(ErrorCode.USER_PWD_ERROR);
		}
	}

	@Override
	public String register(VerifyRegisterSmsRequest request) {
		MemberRegParam regParam = new MemberRegParam();
		regParam.setRegType(MemberRegTypeEnum.EMAIL);
		regParam.setFromWay(MemberFromWayEnum.YEEPAY_REG);
		regParam.setMemberType(MemberTypeEnum.LIKER);
		regParam.setFromTag("LIKER");
		regParam.setIsVerify(true);
		String suffix = "@" + request.getAppSourceEnum().getValue() + ".com";
		regParam.setLoginName(StringUtils.trim(request.getPhoneNo() + suffix));
		String pwd = StringUtils.trim(AES.decryptFromBase64(request.getPwd(), ConstantUtil.TRANSFER_KEY));
		regParam.setLoginPassword(HmacSign.signToBase64(pwd, ConstantUtil.SIGN_SECRET_KEY));
		regParam.setExternalUserId(StringUtils.trim(request.getPhoneNo()));
		try {
			return memberManageFacade.fastRegMember(regParam);

		} catch (MemberExistsException e) {
			throw new LaikeSysException(ErrorCode.MEMBER_REGISTER_EXCEPTION, e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MEMBER_SYS_EXCEPTION, e);
		}
	}

	@Override
	public void setFastRegMemberInfo(FastRegMemberInfoParam memberParam) {
		try {
			memberManageFacade.setFastRegMemberInfo(memberParam);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MEMBER_SYS_EXCEPTION, e);
		}
	}

	@Override
	public void setLoginPassword(String memberNo, String password) {
		try {
			String pwd = StringUtils.trim(AES.decryptFromBase64(password, ConstantUtil.TRANSFER_KEY));
			memberSecurtiySetFacade.setLoginPassword(memberNo, HmacSign.signToBase64(pwd, ConstantUtil.SIGN_SECRET_KEY));
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MEMBER_CHANGE_PWD_EXCEPTION, e);
		}
	}

	@Override
	public void logout(LogoutReuqest reuqest) {
		try {
			tokenFacade.revokeToken(reuqest.getToken());
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.REVOKE_TOKEN_EXCEPTION, e);
		}
	}

	@Override
	public boolean hasSetTradePwd(String memberNo) {
		MemberSecuritySetParam setParam = null;
		try {
			setParam = memberQueryFacade.queryMemberSecuritySet(memberNo);
			return setParam.getTradePasswordTag();
		} catch (Exception e) {
			throw new LaikeSysException(ErrorCode.MEMBER_SYS_EXCEPTION, e);
		}
	}

	@Override
	public void setTradePwd(String memberNo, String newPwd) {
		memberSecurtiySetFacade.setDealPassword(memberNo, newPwd);
	}


}
