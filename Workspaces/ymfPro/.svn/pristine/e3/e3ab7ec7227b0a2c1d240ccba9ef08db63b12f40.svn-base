package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.common.laike.utils.HttpUtils;
import com.yeepay.g3.core.laike.BaseTest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;


/**
 * Description:
 * Author: wei.li
 * Date: 17/4/24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class SettleInDayServiceTest extends BaseTest {

    @Autowired
    private SettleS0Service settleS0Service;

    @Test
    public void test() {
        String url = "http://qa.yeepay.com/boss_oltp/openSettleIndayApi.action";

        String result = "";
        String host = "http://qa.yeepay.com/boss_oltp";
        String path = "/openSettleIndayApi.action";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> body = new HashMap<String, String>();

        Map querys = new HashMap<String, String>();
        //Map querys1 = new HashMap<String, String>();
        querys.put("customerNumber", "10040012913");

        //工作日普通
        querys.put("workdayDelay", "false");
        querys.put("workdayDelayFeeRate", "0");

        //工作日加急
        querys.put("workdayUrgency", "true");
        querys.put("workdayUrgencyFeeRate", "0");

        //非工作日普通
        querys.put("weekendDelay", "false");
        querys.put("weekendDelayFeeRate", "0");

        //非工作日加急
        querys.put("weekendUrgency", "false");
        querys.put("weekendUrgencyFeeRate", "0");

        //结算天数状态
        querys.put("waitDaysControl", "false");
        querys.put("waitDays", "0");

        //自动拆分状态
        querys.put("autoSplit", "true");

        //pos对私出款限额
        querys.put("privateLimit", "false");
        querys.put("limitAmount", "0");

        //出款比例
        querys.put("remitRate", "80");

        //最低限额
        querys.put("minLeft", "0");

        //收款人限制
        querys.put("payeeLimitValid", "0");

        //对公、对私限制
        querys.put("payeePrivateOrPublic", "PUBLIC34");

        //固定出资金额
        querys.put("proxyIndayValid", "aaa");
        querys.put("proxyIndayAmount", "15.098");

        //出资配比
        querys.put("proxyIndayRate", "5:5");

        //临时出资金额
        querys.put("tmpProxyValid", "false");
        querys.put("tmpProxyAmount", "100.0");

        //资金管理费比例
        querys.put("managefeeValid", "false");
        querys.put("managefee", "10.0");

        //代理商收款宝开关
        querys.put("agentSKBFlag", "false");

        //预存子商户认证费
        querys.put("authChildCerFee", "0.0");

        //包量认证商户
        querys.put("wrapCerCount", "0");

        //超出流量费率
        querys.put("superHighFee", "0.0");

        //一次性开通费
        querys.put("authHandFee", "0.0");

        //风险保证金
        querys.put("skbRiskFee", "0.0");

        //商户收款宝开关
        querys.put("customerSKBFlag", "false");

        //航旅固定出资金额
        querys.put("hlIndayValid", "false");
        querys.put("hlIndayAmount", "100.0");

        //出资配比
        querys.put("hlIndayRate", "5:5");

        //银联垫资开关
        querys.put("unionProxyFlag", "1");

        //垫资银行
        querys.put("autobankIds", null);

        //开通规则类型
        querys.put("openRuleType", "m");

        HttpResponse response = null;
        try {
            response = HttpUtils.doPost(host, path, headers, querys, body);
            result = response.toString();
            System.err.println(EntityUtils.toString(response.getEntity()));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void openS0() {
        settleS0Service.openS0("100400129131");
    }


}
