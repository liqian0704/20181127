package com.yeepay.g3.core.laike.facade;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.utils.ValidateParamUtil;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.CreditProductResponse;
import com.yeepay.g3.facade.laike.dto.CreditRequset;
import com.yeepay.g3.facade.laike.facade.app.AppCreditFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: wei.li
 * Date: 17/3/30
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AppCreditFacadeTest extends BaseTest {

    @Autowired
    private AppCreditFacade appCreditFacade;

    @Test
    public void getCreditLink() {
        CreditRequset creditRequset = new CreditRequset();
        creditRequset.setMemberNo("212468315181");
        creditRequset.setIp("0.0.0.600");
        creditRequset.setChannelId("DM");
        creditRequset.setImei("868808027591483");
        System.err.println(new Gson().toJson(appCreditFacade.getCreditLink(creditRequset)));

    }

    @Test
    public void getCreditProduct() {
        BaseRequest request = new BaseRequest();
        request.setMemberNo("212468315181");
        CreditProductResponse creditProductResponse = appCreditFacade.getCreditProductInfo(request);
        System.err.println(creditProductResponse);
        System.err.println(JSONUtils.toJsonString(creditProductResponse));
    }

    @Test
    public void checkIp() {
        String ip = null;
        System.err.println(ValidateParamUtil.isIp(ip));
    }

}
