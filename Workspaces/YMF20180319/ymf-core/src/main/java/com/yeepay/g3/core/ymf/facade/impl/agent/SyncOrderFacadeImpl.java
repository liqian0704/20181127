package com.yeepay.g3.core.ymf.facade.impl.agent;

import com.yeepay.g3.core.ymf.service.order.SyncOrderService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.agent.SyncOrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Title: 代理商订单同步
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/2/23.
 */
@Service
public class SyncOrderFacadeImpl implements SyncOrderFacade {

    @Autowired
    private SyncOrderService syncOrderService;

    @Override
    public CountResponse countOrder(List<String> customerNumbers, Date from, Date to) throws YmfTrxException {
        if (null == from) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能为空");
        }
        if (null == to) {
            throw new YmfTrxException(TrxCode.T1006, "结束时间不能为空");
        }
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        if (null == customerNumbers || 0 == customerNumbers.size()) {
            throw new YmfTrxException(TrxCode.T1006, "商户编号不能为空集合");
        }
        return syncOrderService.countSyncOrder(customerNumbers, from, to);
    }

    @Override
    public List<SyncOrderDTO> queryOrder(List<String> customerNumbers, Date from, Date to) throws YmfTrxException {
        if (null == from) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能为空");
        }
        if (null == to) {
            throw new YmfTrxException(TrxCode.T1006, "结束时间不能为空");
        }
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        if (null == customerNumbers || 0 == customerNumbers.size()) {
            throw new YmfTrxException(TrxCode.T1006, "商户编号不能为空集合");
        }
        return syncOrderService.queryOrder(customerNumbers, from, to);
    }

    @Override
    public CountResponse countS0Remit(List<String> customerNumbers, Date from, Date to) throws YmfTrxException {
        if (null == from) {
            throw new YmfTrxException(TrxCode.T1006, "秒到开始时间不能为空");
        }
        if (null == to) {
            throw new YmfTrxException(TrxCode.T1006, "秒到结束时间不能为空");
        }
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        if (null == customerNumbers || 0 == customerNumbers.size()) {
            throw new YmfTrxException(TrxCode.T1006, "商户编号不能为空集合");
        }
        return syncOrderService.countSyncS0Remit(customerNumbers, from, to);
    }

    @Override
    public List<SyncS0OrderDTO> queryS0Remit(List<String> customerNumbers, Date from, Date to) throws YmfTrxException {
        if (null == from) {
            throw new YmfTrxException(TrxCode.T1006, "秒到开始时间不能为空");
        }
        if (null == to) {
            throw new YmfTrxException(TrxCode.T1006, "秒到结束时间不能为空");
        }
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        if (null == customerNumbers || 0 == customerNumbers.size()) {
            throw new YmfTrxException(TrxCode.T1006, "商户编号不能为空集合");
        }
        return syncOrderService.queryS0Remit(customerNumbers, from, to);
    }

    @Override
    public List<SyncOrderDTO> queryOrderForPage(List<String> customerNumbers, Date from, Date to, int startNum, int endNum) throws YmfTrxException {
        if (null == from) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能为空");
        }
        if (null == to) {
            throw new YmfTrxException(TrxCode.T1006, "结束时间不能为空");
        }
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        if (null == customerNumbers || 0 == customerNumbers.size()) {
            throw new YmfTrxException(TrxCode.T1006, "商户编号不能为空集合");
        }

        return syncOrderService.queryOrderForPage(customerNumbers, from, to,startNum,endNum);
    }
}
