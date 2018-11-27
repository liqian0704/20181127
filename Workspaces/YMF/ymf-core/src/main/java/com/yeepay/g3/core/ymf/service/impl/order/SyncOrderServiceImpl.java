package com.yeepay.g3.core.ymf.service.impl.order;

import com.yeepay.g3.core.ymf.dao.order.SyncOrderDao;
import com.yeepay.g3.core.ymf.service.order.SyncOrderService;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderTypeDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Title: 订单同步service
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/2/23.
 */
@Service
public class SyncOrderServiceImpl implements SyncOrderService {

    @Autowired
    private SyncOrderDao syncOrderDao;

    @Override
    public CountResponse countSyncOrder(List<String> customerNumbers, Date from, Date to) {
        return syncOrderDao.countSyncOrder(from, to, customerNumbers);
    }

    @Override
    public List<SyncOrderDTO> queryOrder(List<String> customerNumbers, Date from, Date to) {
        return syncOrderDao.querySyncOrder(from, to, customerNumbers);
    }

    @Override
    public CountResponse countSyncS0Remit(List<String> customerNumbers, Date from, Date to) {
        return syncOrderDao.countS0Remit(from, to, customerNumbers);
    }

    @Override
    public List<SyncS0OrderDTO> queryS0Remit(List<String> customerNumbers, Date from, Date to) {
        return syncOrderDao.querySyncRemit(from, to, customerNumbers);
    }

    @Override
    public List<SyncOrderTypeDTO> queryOrderForPage(List<String> customerNumbers, Date from, Date to, int startNum, int endNum) {
        return syncOrderDao.querySyncOrderForPage(from, to, customerNumbers,startNum,endNum);
    }
}
