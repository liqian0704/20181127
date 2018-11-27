package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.facade.laike.dto.LogoutReuqest;
import com.yeepay.g3.facade.laike.dto.VerifyRegisterSmsRequest;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.member.param.FastRegMemberInfoParam;
import com.yeepay.g3.facade.member.param.MemberParam;

/**
 * 会员服务接口
 *
 * @author Created by Felix on 9/2/16.
 */

public interface MemberService {
	/**
	 * 通过 三代会员号获取会员信息
	 *
	 * @param memberNo
	 * @return
	 */
	MemberParam getMember(String memberNo);

	/**
	 * 校验支付密码
	 *
	 * @param memberNo 三代会员号
	 * @param pwd      支付密码
	 * @return
	 */
	boolean checkTradePwd(String memberNo, String pwd);

	/**
	 * 检查是否设置了支付密码
	 *
	 * @param memberNo
	 * @return
	 */
	boolean hasSetTradePwd(String memberNo);

	/**
	 * 设置支付密码
	 *
	 * @param memberNo
	 * @param newpwd
	 */
	void setTradePwd(String memberNo, String newpwd);

	/**
	 * 根据登录名获取会员信息
	 *
	 * @param loginName
	 * @param appSourceEnum
	 * @return
	 */
	MemberParam getMemberByPhoneNo(String loginName, AppSourceEnum appSourceEnum);

	/**
	 * 根据登录名获取二代会员信息
	 *
	 * @param loginName
	 * @return
	 */
	MemberParam queryG2MemberByLoginName(String loginName);

	/**
	 * 根据登录名获取二代会员信息
	 *
	 * @return
	 */
	void login(String memberNo, String pwd);

	/**
	 * 检查密码
	 *
	 * @param userId
	 * @param password
	 * @return
	 */
	Boolean checkPwd(String userId, String password);

	/**
	 * 修改密码
	 *
	 * @param memberNo
	 * @param oldPassword
	 * @param newPassword
	 */
	void changePwd(String memberNo, String oldPassword, String newPassword);

	/**
	 * 注册会员
	 *
	 * @param request
	 * @return 会员号
	 */
	String register(VerifyRegisterSmsRequest request);

	/**
	 * 设置注册信息
	 *
	 * @param memberParam
	 */
	void setFastRegMemberInfo(FastRegMemberInfoParam memberParam);

	/**
	 * 更新密码
	 *
	 * @param memberNo
	 * @param password
	 */
	void setLoginPassword(String memberNo, String password);

	/**
	 * 注销
	 *
	 * @param reuqest
	 */
	void logout(LogoutReuqest reuqest);
}
