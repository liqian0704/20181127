package com.yeepay.g3.core.ymf.facade.impl.trade;

import com.yeepay.g3.core.ymf.biz.CustomerSettleInfo2gBiz;
import com.yeepay.g3.core.ymf.biz.trade.SupplyBiz;
import com.yeepay.g3.core.ymf.entity.common.HolidayInfo;
import com.yeepay.g3.core.ymf.service.common.HolidayInfoService;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.holiday.HolidayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Title: 补单 退款  接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/22.
 */
@Service
public class TradeFacadeImpl implements TradeFacade {
	private static Logger logger = LoggerFactory.getLogger(TradeFacadeImpl.class);
	@Autowired
	private SupplyBiz supplyBiz;
	@Autowired
	private CustomerSettleInfo2gBiz customerSettleInfo2gBiz;
	@Autowired
	private HolidayInfoService holidayInfoService;

	@Override
	public void supplyTodayOrder() {
		Date today = new Date();
		Date from = DateUtils.addMinute(today, ConfigureSetting.getTimerConfigOrderFrom()); // to的时间往前推130分钟
		Date to = DateUtils.addMinute(today, ConfigureSetting.getTimerConfigOrderTo()); // 当前服务器时间往前推10分钟
		supplyBiz.supplyOrder(from, to);
	}

	@Override
	public void supplyYesterdayOrder() {
		Date from = DateUtil.getFirstOfDay(DateUtil.getDate(new Date(), -1)); // 昨天
		Date to = DateUtil.getLastOfDay(from);
		supplyBiz.supplyOrder(from, to);
	}

	@Override
	public void supplyBeforeOrder() {
		Date from = DateUtil.getFirstOfDay(DateUtil.getDate(new Date(), -4));
		Date to = DateUtil.getLastOfDay(DateUtil.getDate(new Date(), -2));
		supplyBiz.supplyOrder(from, to);
	}

	@Override
	public void supplyOrder(Date from, Date to) {
		supplyBiz.supplyOrder(from, to);
	}


	@Override
	public void supplyOrder(String customerNumber, String externalId) {
		supplyBiz.supplyOrder(customerNumber, externalId);
	}

	@Override
	public void supplyRefund() {
		Date from = DateUtils.addDay(new Date(), ConfigureSetting.getTimerConfigRefundFrom()); // ?天前
		Date to = DateUtils.addMinute(new Date(), ConfigureSetting.getTimerConfigRefundTo()); // 当前服务器时间往前推?分钟
		supplyBiz.supplyRefund(from, to);
	}

	@Override
	public void supplyRefund(Date from, Date to) {
		supplyBiz.supplyRefund(from, to);
	}

	@Override
	public void supplyRefund(Long orderId) {
		supplyBiz.supplyRefund(orderId);
	}

	@Override
	public void supplyRefund(Date from) {
		supplyBiz.supplyRefund(from, new Date());
	}

	@Override
	public void expireOrder() {
		Date from = DateUtils.addDay(new Date(), ConfigureSetting.getTimerConfigExpire()); // ?天前
		supplyBiz.expireOrder(from, new Date());
	}

	@Override
	public void syncSettle() {
		Date syncDate = DateUtil.getYesterday();
		Boolean isHoliday = HolidayUtils.isHoliday(syncDate);
		if(isHoliday){
//			TODO 存储节假日信息
			saveHoliday(syncDate);
			return;
		}
		customerSettleInfo2gBiz.customerSettleInfo(syncDate);
	}

	@Override
	public void syncSettle(Date trxDate) {
		Boolean isHoliday = HolidayUtils.isHoliday(trxDate);
		if(isHoliday){
//			TODO 存储节假日信息
			saveHoliday(trxDate);
			return;
		}
		customerSettleInfo2gBiz.customerSettleInfo(trxDate);
	}

	@Override
	public void syncSettle(String customerNumber, Date trxDate) {
		Boolean isHoliday = HolidayUtils.isHoliday(trxDate);
		if(isHoliday){
//			TODO 存储节假日信息
			saveHoliday(trxDate);
			return;
		}
		customerSettleInfo2gBiz.customerSettleInfo(customerNumber, trxDate);
	}

	@Override
	public void synHolidaySettle(String customerNumber) {
		try {
			logger.info("####synHolidaySettle begin ####");
			boolean isHoliday = HolidayUtils.isHoliday(new Date());
			if(isHoliday){
				logger.info("today is holiday !synHolidaySettle stop!");
				return ;
			}
		    List<HolidayInfo> holidayInfos = holidayInfoService.findAllByStatus(Status.INIT);
			if(null == holidayInfos || holidayInfos.size()<1){
				logger.info("no holidayInfo ! ");
				return;
			}
			for(HolidayInfo holidayInfo:holidayInfos){
				Date holiday = holidayInfo.getHolidayDate();
				customerSettleInfo2gBiz.customerHolidaySettleInfo(customerNumber,holiday);

			}
			logger.info("####synHolidaySettle end ####");
		} catch (Exception e) {
			logger.error("findAllByStatus exception",e);
			return;
		}
	}
	/**
	 * 存储节假日信息
	 * @param holiday
	 */
	private void saveHoliday(Date holiday){
		HolidayInfo holidayInfo=null;
		try {
			holidayInfo = holidayInfoService.findByHolidayDate(holiday);
		} catch (Exception e) {
			logger.error("findByHolidayDate Exception ",e);
			return;
		}
		if(null != holidayInfo){
			logger.error("holidayInfo has exsist! ");
			return;
		}
		holidayInfo = new HolidayInfo();
		holidayInfo.setHolidayDate(holiday);
		holidayInfo.setStatus(Status.INIT);
		try {
			holidayInfoService.save(holidayInfo);
		} catch (Exception e) {
			logger.error(" holidayInfoService.save exception",e);
		}
	}
}
