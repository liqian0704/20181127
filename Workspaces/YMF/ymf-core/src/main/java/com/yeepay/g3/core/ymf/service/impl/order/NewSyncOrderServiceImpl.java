package com.yeepay.g3.core.ymf.service.impl.order;

import com.yeepay.g3.core.ymf.dao.order.NewSyncOrderDao;
import com.yeepay.g3.core.ymf.service.order.NewSyncOrderService;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderParam;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class NewSyncOrderServiceImpl implements NewSyncOrderService {

    @Autowired
    private NewSyncOrderDao newSyncOrderDao;

    @Override
    public CountResponse countSyncOrder(SyncOrderParam param) {
        return newSyncOrderDao.countSyncOrder(param);
    }

    @Override
    public List<SyncOrderDTO> queryOrder(SyncOrderParam param) {
        return newSyncOrderDao.querySyncOrder(param);
    }

    @Override
    public CountResponse countSyncS0Remit(SyncOrderParam param) {
        return newSyncOrderDao.countS0Remit(param);
    }

    @Override
    public List<SyncS0OrderDTO> queryS0Remit(SyncOrderParam param) {
        return newSyncOrderDao.querySyncRemit(param);
    }
}
