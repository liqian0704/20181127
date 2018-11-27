package com.yeepay.g3.core.ymf.facade.impl.trade;

import com.yeepay.g3.core.ymf.biz.trade.SupplyBiz;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import com.yeepay.g3.utils.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Autowired
    private SupplyBiz supplyBiz;

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

}
