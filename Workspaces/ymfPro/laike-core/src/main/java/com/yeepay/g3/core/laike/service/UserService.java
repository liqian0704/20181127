package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户登陆注册service
 * @Author: zhaoyu.cui
 * @Date: 16/10/9
 * @Time: 下午4:51
 */
public interface UserService {

	/**
	 * 创建用户
	 *
	 * @param request
	 * @param flag     true:状态直接register（联合登陆时才用）；false：状态init
	 * @param memberNo
	 * @param params
	 * @return
	 */
	UserEntity createNew(BaseRequest request, boolean flag, String memberNo, Map<String, Object> params);

	/**
	 * 根据用户编号查询
	 *
	 * @param id
	 * @return
	 */
	UserEntity findById(String id);

	/**
	 * 查询用户
	 *
	 * @param phoneNo
	 * @param appSourceEnum
	 * @return
	 */
	UserEntity findByPhoneNo(String phoneNo, AppSourceEnum appSourceEnum);

	/**
	 * 查询
	 *
	 * @param memberNo
	 * @return
	 */
	UserEntity findByMemberNo(String memberNo);

	/**
	 * 查注册的
	 *
	 * @param phoneNo
	 * @param appSourceEnum
	 */
	UserEntity findRegisterUser(String phoneNo, AppSourceEnum appSourceEnum);

	/**
	 * 通过会员号查注册的
	 *
	 * @param memberNo
	 * @return
	 */
	UserEntity findRegisterByMember(String memberNo);

	/**
	 * 根据所属商户号查询
	 *
	 * @param merchantNo
	 * @return
	 */
	List<UserEntity> findByMerchantNo(String merchantNo);

	/**
	 * 根据所属商户号查询老板
	 *
	 * @param merchantNo
	 * @return
	 */
	UserEntity findBoss(String merchantNo);

	/**
	 * 更新会员
	 *
	 * @param memeberNo
	 */
	void update2Register(String memeberNo, UserEntity userEntity);

	/**
	 * 升级为boss
	 *
	 * @param memeberNo
	 * @param merchantNo
	 */
	void update2Boss(String memeberNo, String merchantNo);

	/**
	 * 跟新商编
	 *
	 * @param memeberNo
	 * @param merchantNo
	 */
	void updateMerchantNo(String memeberNo, String merchantNo);

	/**
	 * 更新关联入网信息
	 *
	 * @param memeberNo
	 * @param accountId
	 */
	void updateAccountId(String memeberNo, String accountId);

	/**
	 * 更新请求来源快快照
	 *
	 * @param userEntity
	 * @param request
	 */
	void updateRequestSnapt(UserEntity userEntity, BaseRequest request);

	/**
	 * op入网,初始化用户表
	 *
	 * @param phoneNo
	 * @param merchantNo
	 * @param memberNo
	 * @return
	 */
	UserEntity createUser(String phoneNo, String merchantNo, String memberNo, String accountId);

	/**
	 * 更新S0状态
	 *
	 * @param userEntity
	 */
	void updateS0Level(UserEntity userEntity);

    /**
     * 更新联盟邀请码和商编
     *
     * @param userEntity
     * @param merchantNo 非空时，inviteCode为allianceInviteCode
     * @param inviteCode
     * @param agentNo    merchantNo空这个必填
     */
    void updateAllianceCodeAndMerNo(UserEntity userEntity, String merchantNo, String inviteCode, String agentNo);

    /**
     * 注册时用户修改了邀请码
     *
     * @param phoneNo
     * @param appSourceEnum
     * @param inviteCode
     * @param agentNo
     */
    UserEntity updateInviteCode(String phoneNo, AppSourceEnum appSourceEnum, String inviteCode, String agentNo);

}
