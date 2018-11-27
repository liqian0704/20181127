package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.ApplyBizRequest;
import com.yeepay.g3.facade.laike.dto.ShareRequest;
import com.yeepay.g3.facade.laike.enumtype.BizTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.BoolEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Description:
 * Author: wei.li
 * Date: 17/5/24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ApplyBizTest extends BaseTest {

    @Autowired
    private ApplyBiz applyBiz;

    @Test
    @Rollback(false)
    public void applyDevice() {
        ApplyBizRequest applyBizRequest = new ApplyBizRequest();
        applyBizRequest.setMemberNo("212468315242");
        applyBizRequest.setLinkPhone("15900000001");
        applyBizRequest.setLinkName("test");
        applyBizRequest.setBizType(BizTypeEnum.DEVICE_APPLY);
        applyBizRequest.setDeviceInfo("测试入网商户");
        System.err.println(applyBiz.applyDevice(applyBizRequest));
    }

    @Test
    @Rollback(false)
    public void applyDevice1() {
        ApplyBizRequest applyBizRequest = new ApplyBizRequest();
        applyBizRequest.setMemberNo("212468315242");
        applyBizRequest.setLinkPhone("13211112222");
        applyBizRequest.setLinkName("茄汁面");
        applyBizRequest.setLinkProvinceName("1231231111");
        applyBizRequest.setLinkMerCityName("123211111");
        applyBizRequest.setDeviceInfo("台牌");
        applyBizRequest.setBizType(BizTypeEnum.DEVICE_APPLY);
        System.err.println(applyBiz.applyDevice(applyBizRequest));
    }

    @Test
    public void applyTest() {
        ShareRequest request = new ShareRequest();
        request.setHasAlliance(BoolEnum.TRUE);
        request.setMemberNo("212468315242");
        System.err.println(applyBiz.getShareLink(request));
    }
}
