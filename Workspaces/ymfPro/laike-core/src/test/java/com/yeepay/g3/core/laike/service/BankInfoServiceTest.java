package com.yeepay.g3.core.laike.service;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.BankInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description: AgentServiceTest
 * Author: jiawen.huang
 * Date: 16/12/5
 * Time: 14:51
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class BankInfoServiceTest extends BaseTest {

	@Autowired
	private BankCodeInfoService bankCodeInfoService;

	@Test
	public void test() {
//		System.out.print(new Gson().toJson(bankCodeInfoService.queryCity("01")));
//		System.out.print(bankCodeInfoService.queryBranchBank("ICBC", "01", "01").size());
        System.err.print(new Gson().toJson(bankCodeInfoService.queryBranchBank("BOC", "13", "1318")));
        //System.err.print(new Gson().toJson(bankCodeInfoService.queryBranchBank("BOC", "13", "12")));
//        System.err.print(new Gson().toJson(bankCodeInfoService.queryBranchBank("ICBC", "340000", "340400")));
    }

    @Test
    public void test1() {
//		String s = "{\"bankName\":\"SJD\",\"bankCode\":\"手机贷\",\"bankImg\":\"20000\"}";
//		BankInfo bankInfo = JSONUtils.jsonToBean(s, BankInfo.class);
//		System.err.println(bankInfo.getBankCode());
        List<BankInfo> list = bankCodeInfoService.querySettleBank();
        for (BankInfo b : list) {
            System.err.println(b.getBankCode());
        }
    }
}
