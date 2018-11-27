package com.yeepay.g3.core.ymf.facade.impl;

import com.yeepay.g3.core.ymf.biz.profit.PushProfitBiz;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.core.ymf.utils.email.MonitorNotify;
import com.yeepay.g3.facade.ymf.facade.PushProfitToAgentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: 推送毛利数据到代理商系统
 * @author: xiaobin.liu
 * @date: 18/1/9
 * @time: 下午6:12
 */
@Service
public class PushProfitToAgentFacadeImpl implements PushProfitToAgentFacade {
    @Autowired
    private PushProfitBiz pushProfitBiz;

    @Override
    public void pushLastMonth() {
        pushWithMoth(null);
    }

    @Override
    public void pushWithMoth(String month) {
        if (month == null || "LASTMOTH".equals(month)) {
            //暂时不开放此方法
            Date lastMonthFirstDay = DateUtil.getLastMonthFirstDay(null);
            month = DateUtil.formatDate(lastMonthFirstDay, "yyyyMM");
        } else {
            Date begin = DateUtil.getStrToDate(month, "yyyyMM");
        }
        try {
            //MonitorNotify.notifyEmail("开始推送，" + month + "月份分润到代理商。",null);
            pushProfitBiz.pushToAgent(month);
        } catch (Exception e) {
            MonitorNotify.notifyEmail("推送" + month + "月份，分润失败。",e);
            throw new RuntimeException(e);

        }
    }
}
