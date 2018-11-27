package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.agent.hessian.bean.AgentOperatorInviteCodeBean;
import com.yeepay.agent.hessian.bean.LaikeBindCustomerBean;
import com.yeepay.agent.hessian.bean.LaikeS0ProductResponseDTO;
import com.yeepay.agent.hessian.exception.AgentHessianRuntimeException;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.AgentRelationService;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ErrorCodeSource;
import com.yeepay.g3.facade.laike.exception.ExternalReturnCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoResponseDTO;
import com.yeepay.g3.utils.common.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 代理关系服务实现
 * Author: jiawen.huang
 * Date: 16/12/1
 * Time: 19:38
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class AgentRelationServiceImple extends AbstractService implements AgentRelationService {

    private static String ACTIVE_STATUS = "ACTIVE";  //代理商系统,表示激活状态

    private static String SCAN_CODE = "ScanCode"; //扫码

	@Override
	public String checkAgentBiz(InviteType inviteType, String inviteCode) {
		List<AgentOperatorInviteCodeBean> list = null;
		try {
			if (InviteType.INVITECODE.equals(inviteType)) {
				list = getLaikeAgentInfoService.getAgentOperatorInviteCodeInfoByCode(inviteType.getValue(), null, inviteCode);
			} else if (InviteType.SIGNEDPAPER.equals(inviteType)) {
				String agentCode = findBizByCode(inviteCode).getAgentNumber();
				list = getLaikeAgentInfoService.getAgentOperatorInviteCodeInfoByCode(inviteType.getValue(), agentCode, null);
			} else {
				return "";//直销没有代理关系
			}
			AgentOperatorInviteCodeBean inviteCodeBean = list.get(0);
            if (!(inviteCodeBean.getStatus().equals(ACTIVE_STATUS) &&
                    inviteCodeBean.getProductCode().equals(SCAN_CODE))) {
                throw new LaikeSysException(ErrorCode.AGENT_INACTIVE_OR_UNPERMISSION);
			}
			return inviteCodeBean.getAgentCode();
		} catch (LaikeSysException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.POS_AGENT,
					e.getMessage(), e.getMessage()), e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.AGENT_BOSS_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
	public AccountOpenEntity checkInviteBiz(AccountOpenEntity accountOpenEntity) {
		try {
			if (accountOpenEntity.getInviteType().equals(InviteType.SIGNEDPAPER)) {
				QrCodeInfoResponseDTO qrCodeInfoResponseDTO = findBizByCode(accountOpenEntity.getInviteCode());
				if (StringUtils.isEmpty(qrCodeInfoResponseDTO.getAgentNumber())) {//直销
					accountOpenEntity.setAgentNo(qrCodeInfoResponseDTO.getSalesNo());//销售直销
					accountOpenEntity.setInviteType(InviteType.SELLER_DIRECT_SALE);
				} else {
					AgentOperatorInviteCodeBean inviteCodeBean = findAgentSellers(accountOpenEntity.getInviteType(), accountOpenEntity.getInviteCode());
					accountOpenEntity.setAgentNo(qrCodeInfoResponseDTO.getAgentNumber());
					accountOpenEntity.setBusinessMan(inviteCodeBean.getSaleId());//代理商业务员
				}
			}
			if (accountOpenEntity.getInviteType().equals(InviteType.INVITECODE)) {
				AgentOperatorInviteCodeBean inviteCodeBean = findAgentSellers(accountOpenEntity.getInviteType(), accountOpenEntity.getInviteCode());
				accountOpenEntity.setAgentNo(inviteCodeBean.getAgentCode());
				accountOpenEntity.setBusinessMan(inviteCodeBean.getSaleId());//代理商业务员
			}
			return accountOpenEntity;
		} catch (LaikeSysException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.POS_AGENT,
					e.getMessage(), e.getMessage()), e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.AGENT_BOSS_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
	public void bindBusinessMan(AccountOpenEntity accountOpenEntity) {
		if (StringUtils.isNotBlank(accountOpenEntity.getBusinessMan())) {
			LaikeBindCustomerBean laikeBindCustomerBean = getLaikeAgentInfoService
					.bindAgentSubCustomer(accountOpenEntity.getInviteType().getValue(),
							accountOpenEntity.getInviteCode(), accountOpenEntity.getMerchantNo(),
							"", accountOpenEntity.getBusinessMan());
			if (!ExternalReturnCode.AGENT_BOSS_SUCCESS_CODE.equals(laikeBindCustomerBean.getCode())) {
				throw new LaikeSysException(ErrorCode.AGENT_BIND_BIZMAN_EXCEPTION);
			}
        }
    }

    @Override
    public boolean checkAgentS0Open(String merchantNo) {
        try {
            LaikeS0ProductResponseDTO response = getLaikeAgentInfoService.checkAgentS0Product(merchantNo);
            if (response.getAgentStauts().equals(ACTIVE_STATUS) && response.getS0Stauts().equals(ACTIVE_STATUS)) {
                return true;
            }
            return false;
        } catch (AgentHessianRuntimeException e) {
            throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.POS_AGENT, e.getErrorMsg(), e.getErrorMsg()), e);
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.AGENT_BOSS_UNKNOW_EXCEPTION, e);
        }
	}

	/**
	 * 根据二维码id查询二维码所属代理商或者直销销售
	 *
	 * @param qrCode
	 * @return
	 */
	private QrCodeInfoResponseDTO findBizByCode(String qrCode) {
		try {
			QrCodeInfoRequestDTO requestDTO = new QrCodeInfoRequestDTO();
			requestDTO.setQrCodeID(qrCode);
			QrCodeInfoResponseDTO responseDTO = purchaseQrCodeFacade.getQrCodeInfo(requestDTO);
			if (!ExternalReturnCode.YMF_SUCCESS_CODE.equals(responseDTO.getReturnCode())) {
				throw new LaikeSysException("YMF" + responseDTO.getReturnCode());
			}
			return responseDTO;
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.YMF_UNKNOW_EXCEPTION, e);
		}
	}

	/**
	 * 查询代理商业务员
	 *
	 * @param inviteType
	 * @param inviteCode
	 * @return
	 * @throws RuntimeException
	 */
	private AgentOperatorInviteCodeBean findAgentSellers(InviteType inviteType, String inviteCode) throws RuntimeException {
		AgentOperatorInviteCodeBean inviteCodeBean = null;
		if (InviteType.INVITECODE.equals(inviteType)) {
			inviteCodeBean = getLaikeAgentInfoService.checkAgentInfoByInviteCodeOrQrcodeId(inviteType.getValue(), inviteCode, null, null);
		} else if (InviteType.SIGNEDPAPER.equals(inviteType)) {
			inviteCodeBean = getLaikeAgentInfoService.checkAgentInfoByInviteCodeOrQrcodeId(inviteType.getValue(), null, inviteCode, null);
		}
        if (!(inviteCodeBean.getStatus().equals(ACTIVE_STATUS) &&
                inviteCodeBean.getProductCode().equals(SCAN_CODE))) {
            throw new LaikeSysException(ErrorCode.AGENT_INACTIVE_OR_UNPERMISSION);
		}
		return inviteCodeBean;
	}
}
