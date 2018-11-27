package com.yeepay.g3.core.laike.biz;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.OpenProductionRequest;
import com.yeepay.g3.facade.laike.enumtype.AppProductEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Description:
 * Author: wei.li
 * Date: 17/4/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class SettleS0BizTest extends BaseTest {

    @Autowired
    private SettleS0Biz settleS0Biz;

    @Test
    public void findAppServiceInfo() {
        BaseRequest request = new BaseRequest();
        request.setMemberNo("212468327836");//212468327887--212468327836
        System.err.println(new Gson().toJson(settleS0Biz.findAppServiceInfo(request)));
    }

    @Test
    @Rollback(false)
    public void open() {
        OpenProductionRequest request = new OpenProductionRequest();
        request.setMemberNo("212468327086");//212468327836
        request.setProductCode(AppProductEnum.S0_SETTLE);
        System.err.println(new Gson().toJson(settleS0Biz.open(request)));
    }
}
