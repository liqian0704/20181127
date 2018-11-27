package com.yeepay.g3.core.laike.service;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.utils.RemoteFacadeFactory;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.ncmember.dto.GetUsableRespDTO;
import com.yeepay.g3.facade.ncmember.dto.MerchantUserDTO;
import com.yeepay.g3.facade.ncmember.facade.BindCardFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: wei.li
 * Date: 17/4/24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class BindCardServiceTest extends BaseTest {

    private BindCardFacade accountFacade = RemoteFacadeFactory.getService(BindCardFacade.class,
            ExternalSystem.NC_MEMBER);

    @Autowired
    private CardService cardService;

    @Test
    public void test() throws Exception {
        MerchantUserDTO merchantUserDTO = new MerchantUserDTO();
        merchantUserDTO.setMerchantNo("10040041073");
        merchantUserDTO.setIdentityType("USER_ID");
        merchantUserDTO.setIdentityId("212468327991");
        GetUsableRespDTO respDTO = accountFacade.getUsableBindList(merchantUserDTO);
        System.err.println(respDTO.getUsableBindCard().size());
    }

    @Test
    public void test1() throws Exception {
        MerchantUserDTO merchantUserDTO = new MerchantUserDTO();
        merchantUserDTO.setMerchantNo("10040041016");
        merchantUserDTO.setIdentityType("ID_CARD");
        merchantUserDTO.setIdentityId("220183198807041860");
        GetUsableRespDTO respDTO = accountFacade.getUsableBindList(merchantUserDTO);
        System.err.println(new Gson().toJson(respDTO));
    }

    @Test
    public void test2() throws Exception {
        MerchantUserDTO merchantUserDTO = new MerchantUserDTO();
        merchantUserDTO.setMerchantNo("10040041016");
        merchantUserDTO.setIdentityType("PHONE");
        merchantUserDTO.setIdentityId("13610201080");
        GetUsableRespDTO respDTO = accountFacade.getUsableBindList(merchantUserDTO);
        System.err.println(new Gson().toJson(respDTO));
    }

    @Test
    public void test3() {
        cardService.hasBindCard("10040041016", "212468327836");
    }

}
