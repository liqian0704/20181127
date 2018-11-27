package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.UserService;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.LoginRequest;
import com.yeepay.g3.facade.laike.dto.LogoutReuqest;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.enumtype.RoleEnum;
import com.yeepay.g3.facade.laike.enumtype.UserStatus;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.UIDGenerator;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户类服务层实现
 * @Author: zhaoyu.cui
 * @Date: 16/10/9
 * @Time: 下午5:04
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserEntity createNew(BaseRequest request, boolean flag, String memberNo, Map<String, Object> params) {
		UserEntity userEntity = new UserEntity();
		userEntity.setPhoneNo(request.getPhoneNo());
		userEntity.setImei(request.getImei());
		userEntity.setLastVersionId(request.getVersionId());
		userEntity.setLocation(request.getLocation());
		userEntity.setUserStatus(flag ? UserStatus.REGISTER : UserStatus.INIT);
		userEntity.setMemberNo(flag ? memberNo : null);
		userEntity.setAppSource(request.getAppSourceEnum());
		if (null != params && null != params.get("agentNo")) {
			userEntity.setAgentNo((String) params.get("agentNo"));
		}
		if (null != params && null != params.get("inviteCode")) {
			userEntity.setInviteCode((String) params.get("inviteCode"));
		}
		this.save(userEntity);
		return userEntity;
	}

	private void save(UserEntity entity) {
		try {
			String userNo = UIDGenerator.generateBizUID(userRepository.nextSequence(), BizPrefixEnum.US.getValue());
			entity.setId(StringUtils.isBlank(entity.getId()) ? userNo : entity.getId());
			entity.setPhoneNo(entity.getPhoneNo());
			entity.setRole(RoleEnum.WORKER);
			userRepository.insert(entity);
		} catch (DuplicateKeyException e) {
			throw new LaikeSysException(ErrorCode.USER_PHONE_REPEAT, e);
		}
	}

	@Override
	public UserEntity findById(String id) {
		UserEntity userEntity = userRepository.findById(id);
		if (null == userEntity) {
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
		}
		userEntity.setPhoneNo(userEntity.getPhoneNo());
		return userEntity;
	}

	@Override
	public UserEntity findByPhoneNo(String phoneNo, AppSourceEnum appSourceEnum) {
		UserEntity userEntity = userRepository.findByPhoneAndSource(phoneNo, appSourceEnum);
		return userEntity;
	}

	@Override
	public UserEntity findByMemberNo(String memberNo) {
		UserEntity userEntity = userRepository.findByMemberNo(memberNo);
		return userEntity;
	}

	@Override
	public UserEntity findRegisterUser(String phoneNo, AppSourceEnum appSourceEnum) {
		UserEntity userEntity = this.findByPhoneNo(phoneNo, appSourceEnum);
		if (null == userEntity) {
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
		}
		if (!userEntity.getUserStatus().equals(UserStatus.REGISTER)) {
			throw new LaikeSysException(ErrorCode.USER_UN_REGISTER);
		}
		return userEntity;
	}

	@Override
	public UserEntity findRegisterByMember(String memberNo) {
		UserEntity userEntity = this.findByMemberNo(memberNo);
		if (null == userEntity) {
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
		}
		if (!userEntity.getUserStatus().equals(UserStatus.REGISTER)) {
			throw new LaikeSysException(ErrorCode.USER_UN_REGISTER);
		}
		return userEntity;
	}

	@Override
	public List<UserEntity> findByMerchantNo(String merchantNo) {
		List<UserEntity> userEntityList = new ArrayList<UserEntity>();
		List<UserEntity> list = userRepository.findByMerchantNo(merchantNo);
		for (UserEntity entity : list) {
			userEntityList.add(entity);
		}
		return userEntityList;
	}

	@Override
	public UserEntity findBoss(String merchantNo) {
		UserEntity userEntity = userRepository.findBossByMerchantNo(merchantNo);
		if (null == userEntity) {
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
		}
		return userEntity;
	}

	@Override
	public void update2Register(String memeberNo, UserEntity userEntity) {
		userEntity.setMemberNo(memeberNo);
		userEntity.setUserStatus(UserStatus.REGISTER);
		update(userEntity);
	}

	@Override
	public void update2Boss(String memeberNo, String merchantNo) {
		LOGGER.info("update2Boss,memeberNo:{" + memeberNo + "},merchantNo:{" + merchantNo + "}");
		UserEntity userEntity = this.findRegisterByMember(memeberNo);
		userEntity.setRole(RoleEnum.BOSS);
		userEntity.setMerchantNo(merchantNo);
		update(userEntity);
	}

	@Override
	public void updateMerchantNo(String memeberNo, String merchantNo) {
		UserEntity userEntity = this.findRegisterByMember(memeberNo);
		userEntity.setMerchantNo(merchantNo);
		update(userEntity);
	}

	@Override
	public void updateAccountId(String memeberNo, String accountId) {
		UserEntity userEntity = this.findRegisterByMember(memeberNo);
		userEntity.setAccountId(accountId);
		update(userEntity);

	}

	@Deprecated
	@Override
	public void updateRequestSnapt(UserEntity userEntity, BaseRequest request) {
		userEntity.setLocation(request.getLocation());
		userEntity.setLastVersionId(request.getVersionId());
		userEntity.setImei(request.getImei());
		if (request instanceof LogoutReuqest) {
			userEntity.setLastLogoutTime(new Date());
		}
		if (request instanceof LoginRequest) {
			userEntity.setLastLoginTime(new Date());
		}
		update(userEntity);
	}

	@Override
	public UserEntity createUser(String phoneNo, String merchantNo, String memberNo, String accountId) {
		UserEntity userEntity = new UserEntity();
		userEntity.setPhoneNo(phoneNo);
		userEntity.setMerchantNo(merchantNo);
		userEntity.setUserStatus(UserStatus.REGISTER);
		userEntity.setMemberNo(memberNo);
		userEntity.setAccountId(accountId);
        userEntity.setAppSource(AppSourceEnum.LIKER);
        this.save(userEntity);
		return userEntity;
	}

	@Override
	public void updateS0Level(UserEntity userEntity) {
		UserEntity entity = this.findById(userEntity.getId());
		entity.setS0Level(userEntity.getS0Level());
		update(entity);
	}

    @Override
    public void updateAllianceCodeAndMerNo(UserEntity entity, String merchantNo, String inviteCode, String agentNo) {
        if (StringUtils.isBlank(merchantNo)) {
            entity.setInviteCode(inviteCode);
            entity.setAgentNo(agentNo);
        } else {
            entity.setMerchantNo(merchantNo);
            entity.setAllianceInviteCode(inviteCode);
        }
        update(entity);
    }

    @Override
    public UserEntity updateInviteCode(String phoneNo, AppSourceEnum appSourceEnum, String inviteCode, String agentNo) {
        UserEntity entity = this.findByPhoneNo(phoneNo, appSourceEnum);
        if (null != entity && UserStatus.INIT.equals(entity.getUserStatus())) {
            entity.setInviteCode(inviteCode);
            entity.setAgentNo(agentNo);
        }
        update(entity);
        return entity;
    }

	private void update(UserEntity entity) {
		Integer num = userRepository.update(entity);
		if (0 == num) {
			throw new LaikeSysException(ErrorCode.USER_NOT_EXIST);
		}
	}
}
