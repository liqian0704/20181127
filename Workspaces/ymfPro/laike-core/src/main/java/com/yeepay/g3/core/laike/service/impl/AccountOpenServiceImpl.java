package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.AccountOpenService;
import com.yeepay.g3.facade.laike.enumtype.*;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.UIDGenerator;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountOpenServiceImpl extends AbstractService implements AccountOpenService {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountOpenServiceImpl.class);

	@Override
	public AccountOpenEntity findById(String id) {
		AccountOpenEntity entity = accountOpenRepository.findById(id);
		return entity;
	}

	@Override
	public AccountOpenEntity findExistById(String id) {
		AccountOpenEntity entity = accountOpenRepository.findById(id);
		if (null == entity) {
			throw new LaikeSysException(ErrorCode.ACCOUNT_NOT_EXIST);
		}
		return entity;
	}

	@Override
	public void createAndUpdate(AccountOpenEntity entity) {
		if (StringUtils.isBlank(entity.getId())) {
			try {
				if (InviteType.SIGNEDPAPER.equals(entity.getInviteType()) ||
						InviteType.SELLER_DIRECT_SALE.equals(entity.getInviteType())) {
					checkExistByQR(entity.getInviteCode());
				}
				String userNo = UIDGenerator.generateBizUID(accountOpenRepository.nextSequence(), BizPrefixEnum.AC.getValue());
				if (entity.getAccountType() == null || AccountType.LK.equals(entity.getAccountType())) {
					entity.setAccountType(AccountType.LK);
                    entity.setOpenStatus(OpenStatusEnum.INIT);
				} else if (AccountType.LOL.equals(entity.getAccountType())) {
					entity.setLolOpenStatus(LOLOpenStatus.INIT);
                }
                entity.setId(userNo);
				accountOpenRepository.save(entity);
			} catch (DuplicateKeyException e) {
				throw new LaikeSysException(ErrorCode.USER_ACCOUNT_OPEN_REPEAT, e);
			}
		} else {
            LOGGER.info("[laike_sys] - [入参] - [AccountOpenService] - [" + toString(entity) + "]");
            Integer num = accountOpenRepository.update(entity);
			if (0 == num) {
				throw new LaikeSysException(ErrorCode.ACCOUNT_NOT_EXIST);
			}
            LOGGER.info("[laike_sys] - [出参] - [AccountOpenService] - [" + toString(entity) + "]");
        }
	}

	@Override
	public AccountOpenEntity findPayableById(String accountId) {
		AccountOpenEntity accountOpenEntity = findExistById(accountId);
		if (AccountType.LOL.equals(accountOpenEntity.getAccountType())) {
			if (!accountOpenEntity.getLolOpenStatus().equals(LOLOpenStatus.SUCCESS)) {
				throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_DENY);
			}
		} else {
			if (accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.MICRO)) {
				if (accountOpenEntity.getOpenStatus().getStep() < 6 ||
						accountOpenEntity.getOpenStatus().equals(OpenStatusEnum.REJECT)) {
					throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_DENY);
				}
			} else {
				if (!OpenStatusEnum.SUCCESS.equals(accountOpenEntity.getOpenStatus())) {
					throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_DENY);
				}
			}
		}
		return accountOpenEntity;
	}

	@Override
	public AccountOpenEntity findByMemberNo(String memberNo) {
		AccountOpenEntity entity = accountOpenRepository.findByMemberNo(memberNo);
		return entity;
	}

	@Override
	public AccountOpenEntity findByMerchantNo(String merchantNo) {
		AccountOpenEntity entity = accountOpenRepository.findByMerchantNo(merchantNo);
		return entity;
	}

	@Override
	public AccountOpenEntity findAccount2Push(String merchantNo) {
		AccountOpenEntity accountOpenEntity = this.findByMerchantNo(merchantNo);
		if (null == accountOpenEntity) {
			throw new LaikeSysException(ErrorCode.NULL_ACCOUNT_TO_PUSH);
		} else {
			if (accountOpenEntity.getCompanyType().equals(CompanyTypeEnum.MICRO)) {
				if (accountOpenEntity.getOpenStatus().getStep() < 6 ||
						accountOpenEntity.getOpenStatus().equals(OpenStatusEnum.REJECT)) {
					throw new LaikeSysException(ErrorCode.ACCOUNT_INVALID_TO_PUSH);
				}
			} else {
				if (!OpenStatusEnum.SUCCESS.equals(accountOpenEntity.getOpenStatus())) {
					throw new LaikeSysException(ErrorCode.ACCOUNT_INVALID_TO_PUSH);
				}
			}
		}
		return accountOpenEntity;
	}

	@Override
	public void createAccount(AccountOpenEntity entity) {
		try {
			String userNo = UIDGenerator.generateBizUID(accountOpenRepository.nextSequence(), BizPrefixEnum.AC.getValue());
			entity.setId(userNo);
			accountOpenRepository.save(entity);
		} catch (DuplicateKeyException e) {
			throw new LaikeSysException(ErrorCode.USER_ACCOUNT_OPEN_REPEAT, e);
		}
	}


    @Override
    public List<AccountOpenEntity> findByLolStatus(LOLOpenStatus lolOpenStatus) {
        List<AccountOpenEntity> entityList = accountOpenRepository.findByLolStatus(lolOpenStatus);
        return entityList;
    }

	private boolean checkExistByQR(String inviteQR) {
		List<AccountOpenEntity> list = accountOpenRepository.findByQRCode(inviteQR);
		if (null != list && list.size() > 0) {
			throw new LaikeSysException(ErrorCode.ACCOUNT_SIGNEDPAPER_REGISTER);
		}
		return false;
	}

    private String toString(AccountOpenEntity accountOpenEntity) {
        StringBuffer sb = new StringBuffer();
        sb.append("id=[" + accountOpenEntity.getId() + "]")
                .append("merchantNo=[" + accountOpenEntity.getMerchantNo() + "]")
                .append("openStatus=[" + accountOpenEntity.getOpenStatus() + "]")
                .append("lolOpenStatus=[" + accountOpenEntity.getLolOpenStatus() + "]");
        return sb.toString();
    }
}
