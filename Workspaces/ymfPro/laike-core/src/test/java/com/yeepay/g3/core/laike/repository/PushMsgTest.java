package com.yeepay.g3.core.laike.repository;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.report.ReceivedsResult;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.biz.AppNotifyBiz;
import com.yeepay.g3.core.laike.entity.PushMsgEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.JPushService;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.enumtype.MsgTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.PushStatus;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.UIDGenerator;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

/**
 * Description: AttachmentTest
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class PushMsgTest extends BaseTest {

    @Autowired
    private AppNotifyBiz appNotifyBiz;

    private static Logger LOGGER = LoggerFactory.getLogger(PushMsgTest.class);

	@Autowired
	private PushMsgRepository pushMsgRepository;

    @Autowired
    private JPushService jPushService;

    @Test
    public void result() {
        JPushClient jpushClient = new JPushClient("11a4916cbd5f705c2cfc645e", "7cd38d599c251844b6de7318");
        try {
            ReceivedsResult result = jpushClient.getReportReceiveds("2251800053476431");
            System.out.println("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            e.printStackTrace();

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            e.printStackTrace();
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }
    }

    @Test
    public void tt() {
        PushMsgEntity pushMsgEntity = pushMsgRepository.findByMessageNo("MSA17080889524774");
        jPushService.push2Customers(pushMsgEntity);
    }

	@Rollback(false)
	@Test
	public void test() {
		PushMsgEntity pushMsgEntity = new PushMsgEntity();
		String no = UIDGenerator.generateBizUID(pushMsgRepository.nextSequence(), BizPrefixEnum.MS.getValue());
		pushMsgEntity.setMessageNo(no);
		pushMsgEntity.setOperator("zhenzhen.zhang");
		pushMsgEntity.setTitle("test2");
		pushMsgEntity.setType(MsgTypeEnum.SYS);
		pushMsgEntity.setPushStatus(PushStatus.SENDED);
		pushMsgEntity.setContent("YopResponse[state=SUCCESS,result={\n" +
				"  \"status\" : \"SUCCESS\",\n" +
				"  \"count\" : 3,\n" +
				"  \"list\" : [ {\n" +
				"    \"pushtime\" : null,\n" +
				"    \"content\" : \"testtesttesttesttesttesttesttesttesttesttesttesttesttest\",\n" +
				"    \"title\" : \"test\",\n" +
				"    \"lifeend\" : null,\n" +
				"    \"lifestart\" : null,\n" +
				"    \"url1\" : null,\n" +
				"    \"url2\" : null,\n" +
				"    \"jpushid\" : null,\n" +
				"    \"msgno\" : \"MSA16111836718959\",\n" +
				"    \"type\" : \"SYS\"\n" +
				"  }, {\n" +
				"    \"pushtime\" : null,\n" +
				"    \"content\" : \"hello world\",\n" +
				"    \"title\" : \"测试信息\",\n" +
				"    \"lifeend\" : null,\n" +
				"    \"lifestart\" : null,\n" +
				"    \"url1\" : null,\n" +
				"    \"url2\" : null,\n" +
				"    \"jpushid\" : null,\n" +
				"    \"msgno\" : \"IM");
		pushMsgEntity.setPushTime(DateUtils.addDay(new Date(),-3));
		pushMsgRepository.save(pushMsgEntity);
//		PushMsgEntity entity = pushMsgRepository.findById(pushMsgEntity.getId());
//		Assert.assertEquals(entity.getTitle(), "test");
//		entity.setTitle("hhhhhh");
//		int num = pushMsgRepository.update(entity);
//		Assert.assertEquals(num, 1);
    }

    @Test
    public void pushOpenMsg2APP() {
        BaseRequest request = new BaseRequest();
        request.setMemberNo("212468327240");
        appNotifyBiz.pushOpenMsg2APP(request);
    }
}
