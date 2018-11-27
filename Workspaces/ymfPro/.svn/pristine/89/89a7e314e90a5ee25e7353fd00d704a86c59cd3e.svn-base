package com.yeepay.g3.core.laike.biz.impl;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.yeepay.g3.common.laike.utils.ReportUtil;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.ScheduleBiz;
import com.yeepay.g3.core.laike.entity.OperateRecordEntity;
import com.yeepay.g3.facade.laike.enumtype.BizTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.FileTypeEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.DateUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/5/24
 * Time: 15:40
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class ScheduleBizImpl extends AbstractBiz implements ScheduleBiz {

	private static String DAILY_DEVICE_APPLY_TEMPLATE = "device_apply";

	private static String DAILY_DEVICE_APPLY_TARGET = "来客硬件申请记录";

	@Override
	public void gatherDailyDeviceApply() {
		Date start = DateUtils.getDayStart(DateUtils.addDay(new Date(), -1));
		Date end = DateUtils.getDayEnd(DateUtils.addDay(new Date(), -1));
		gatherDeviceApply(start, end);
		//return new BaseResponse();
	}

	private void gatherDeviceApply(Date startDate, Date endDate) {
		try {
			String date = DateUtils.getReqDate(startDate);
			String targetName = DAILY_DEVICE_APPLY_TARGET + FileTypeEnum.CSV.getDisplayName();
			List<OperateRecordEntity> recordEntityList = operateRecodeService.findByDate(BizTypeEnum.DEVICE_APPLY, startDate, endDate);
			Gson gson = new Gson();
			String json = gson.toJson(recordEntityList);
			List<Map<String, Object>> list = gson.fromJson(json, List.class);
			Map<String, Object> messages = Maps.newHashMap();
			messages.put("title", DAILY_DEVICE_APPLY_TARGET + date);//邮件标题
			if (list.size() > 0) {
				ByteArrayOutputStream outputStream = ReportUtil.reportCSVStream(DAILY_DEVICE_APPLY_TEMPLATE, targetName, list);
				messages.put("message", "您好，昨日来源为来客app的硬件申请情况见附件：\n");//邮件内容
				notifyService.sendOperationEmail(ConfigEnum.LIKER_OPERATION_CONTACT_EMAIL,
						messages, targetName, outputStream.toByteArray());
			} else {
				messages.put("message", "您好，昨日来源为来客app的硬件申请数量为0。\n");//邮件内容
				notifyService.sendOperationEmail(ConfigEnum.LIKER_OPERATION_CONTACT_EMAIL,
						messages, targetName, null);
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.GENERATE_REPORT_EXCEPTION, e);
		}
	}

}
