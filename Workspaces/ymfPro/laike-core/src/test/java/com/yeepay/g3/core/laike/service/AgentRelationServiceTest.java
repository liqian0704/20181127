package com.yeepay.g3.core.laike.service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import com.yeepay.agent.hessian.bean.AgentOperatorInviteCodeBean;
import com.yeepay.agent.hessian.bean.LaikeBindCustomerBean;
import com.yeepay.agent.hessian.service.laike.GetLaikeAgentInfoService;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.aop.RemoteFacadeLogHandler;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.utils.RemoteFacadeFactory;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.member.enumtype.MemberTypeEnum;
import com.yeepay.g3.facade.member.facade.MemberQueryFacade;
import com.yeepay.g3.facade.member.param.MemberParam;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoResponseDTO;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import com.yeepay.g3.facade.ymf.facade.laike.PurchaseQrCodeFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;
import java.net.MalformedURLException;

/**
 * Description: AgentServiceTest
 * Author: jiawen.huang
 * Date: 16/12/5
 * Time: 14:51
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AgentRelationServiceTest extends BaseTest {

	@Autowired
	private AgentRelationService agentRelationService;

	@Autowired
	private AccountOpenService accountOpenService;

	@Test
	public void testHessian() {
		HessianProxyFactory factory = new HessianProxyFactory();
		MemberQueryFacade memberQueryFacade = null;
		try {
			Object target = factory.create(MemberQueryFacade.class, "http://10.151.30.80:18007/member-hessian/hessian/MemberQueryFacade");
			RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, "test");
			memberQueryFacade = (MemberQueryFacade) Proxy.newProxyInstance(logHandler.getClass().getClassLoader(), target.getClass().getInterfaces(), logHandler);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		MemberParam param = memberQueryFacade.queryMember(MemberTypeEnum.YEEPAY_COM, "18519397782", "YEEPAY_COM");
		System.out.println("@@@" + new Gson().toJson(param));
	}

	@Test
	public void checkAgentBiz() {
		QrCodeInfoRequestDTO requestDTO = new QrCodeInfoRequestDTO();
		requestDTO.setQrCodeID("7X39RM7P");
		QrCodeInfoResponseDTO responseDTO = RemoteFacadeFactory.getService(PurchaseQrCodeFacade.class,
				ExternalSystem.YMF).getQrCodeInfo(requestDTO);
		System.out.println("@@@" + new Gson().toJson(responseDTO));
	}

	@Test
	public void checkAgentSeller() {
		GetLaikeAgentInfoService getLaikeAgentInfoService = RemoteFacadeFactory.
				getHttpInvokeService(GetLaikeAgentInfoService.class, ExternalSystem.AGENT_BOSS, false);
		AgentOperatorInviteCodeBean inviteCodeBean = getLaikeAgentInfoService.
				checkAgentInfoByInviteCodeOrQrcodeId("SIGNEDPAPER", null, "7X39RM7P", null);
		System.out.println("@@@" + new Gson().toJson(inviteCodeBean));
	}

	@Test
	public void bind() {
		GetLaikeAgentInfoService getLaikeAgentInfoService = RemoteFacadeFactory.
				getHttpInvokeService(GetLaikeAgentInfoService.class, ExternalSystem.AGENT_BOSS, false);
		AccountOpenEntity accountOpenEntity = accountOpenService.findByMerchantNo("10040040621");
		LaikeBindCustomerBean laikeBindCustomerBean = getLaikeAgentInfoService
				.bindAgentSubCustomer(accountOpenEntity.getInviteType().getValue(),
						accountOpenEntity.getInviteCode(), accountOpenEntity.getMerchantNo(),
						"", accountOpenEntity.getBusinessMan());
		System.out.println("@@@" + new Gson().toJson(laikeBindCustomerBean));
	}

	@Test
	public void syncSettle() {
		HessianProxyFactory factory = new HessianProxyFactory();
		TradeFacade tradeFacade = null;
		try {
			Object target = factory.create(TradeFacade.class, "http://10.151.30.161:8081/ymf-hessian/hessian/TradeFacade");
			RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, "test");
			tradeFacade = (TradeFacade) Proxy.newProxyInstance(logHandler.getClass().getClassLoader(), target.getClass().getInterfaces(), logHandler);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		tradeFacade.syncSettle();
	}

    @Test
    public void checkAgentS0Open() {
        agentRelationService.checkAgentS0Open("10040041016");
    }
}
