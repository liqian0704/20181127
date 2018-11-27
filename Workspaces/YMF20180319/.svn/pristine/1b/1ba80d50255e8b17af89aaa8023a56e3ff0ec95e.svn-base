package com.yeepay.g3.core.ymf.facade.impl.agent;

import com.yeepay.g3.core.ymf.service.order.NewSyncOrderService;
import com.yeepay.g3.core.ymf.utils.BeanValidator;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderParam;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.agent.NewSyncOrderFacade;
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
public class NewSyncOrderFacadeImpl implements NewSyncOrderFacade {

    @Autowired
    private NewSyncOrderService newSyncOrderService;

    @Override
    public CountResponse countOrder(SyncOrderParam param) throws YmfTrxException {
        ResponseMessage resp = BeanValidator.validateExclude(param, "start");
        if (!resp.isOk()) {
            throw new YmfTrxException(TrxCode.T1006, resp.getMsg());
        }
        Date from = param.getFrom();
        Date to = param.getTo();
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        return newSyncOrderService.countSyncOrder(param);
    }

    @Override
    public List<SyncOrderDTO> queryOrder(SyncOrderParam param) throws YmfTrxException {
        ResponseMessage resp = BeanValidator.validate(param);
        if (!resp.isOk()) {
            throw new YmfTrxException(TrxCode.T1006, resp.getMsg());
        }
        Date from = param.getFrom();
        Date to = param.getTo();
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        return newSyncOrderService.queryOrder(param);
    }

    @Override
    public CountResponse countS0Remit(SyncOrderParam param) throws YmfTrxException {
        ResponseMessage resp = BeanValidator.validateExclude(param, "start");
        if (!resp.isOk()) {
            throw new YmfTrxException(TrxCode.T1006, resp.getMsg());
        }
        Date from = param.getFrom();
        Date to = param.getTo();
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        return newSyncOrderService.countSyncS0Remit(param);
    }

    @Override
    public List<SyncS0OrderDTO> queryS0Remit(SyncOrderParam param) throws YmfTrxException {
        ResponseMessage resp = BeanValidator.validate(param);
        if (!resp.isOk()) {
            throw new YmfTrxException(TrxCode.T1006, resp.getMsg());
        }
        Date from = param.getFrom();
        Date to = param.getTo();
        double gap = DateUtil.compareDate(to, from);
        if (gap <= 0) {
            throw new YmfTrxException(TrxCode.T1006, "开始时间不能晚于结束时间");
        }
        if (gap > 30) {
            throw new YmfTrxException(TrxCode.T1006, "查询天数不能超过30天");
        }
        return newSyncOrderService.queryS0Remit(param);
    }

}
