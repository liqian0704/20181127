package com.yeepay.g3.core.laike.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.fee.front.dto.CalFeeModelDTO;
import com.yeepay.g3.facade.fee.front.enumtype.CalFeeOwnerSourceTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.yeepay.g3.core.laike.service.AbstractService.calFeeModelManagementFacade;

/**
 * Description:
 * Author: wei.li
 * Date: 17/7/31
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class CalFeeServiceTest extends BaseTest {

    @Autowired
    private CalFeeService calFeeService;

    @Test
    public void test() {
        CalFeeModelDTO calFeeModelDTO = calFeeModelManagementFacade.findCalFeeModelDTO("10040042308", CalFeeOwnerSourceTypeEnum.MERCHANT, "DSXTSB", new Date());
        calFeeModelDTO.getRules().get(0);
        System.err.println(new Gson().toJson(calFeeModelDTO));
    }

    @Test
    public void serviceTest() throws JsonProcessingException {
        System.err.println(new Gson().toJson(calFeeService.findCalFeeModel("10040007800", InviteType.BIG_MERCHANT)));
    }

}
