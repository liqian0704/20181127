package com.yeepay.g3.core.ymf.service.order;

import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;

import java.util.Date;
import java.util.List;

/**
 * Title: 订单同步专用接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/2/23.
 */
public interface SyncOrderService {

    /**
     * 根据商户编号和订单时间汇总来客订单
     * @param customerNumbers 商户编号集合
     * @param from 开始时间 包含
     * @param to 结束时间 不包含
     * @return 成功的交易订单
     */
    CountResponse countSyncOrder(List<String> customerNumbers, Date from, Date to);

    /**
     * 根据商户编号和订单时间查询来客订单
     * @param customerNumbers 商户编号集合
     * @param from 开始时间 包含
     * @param to 结束时间 不包含
     * @return 成功的交易订单
     */
    List<SyncOrderDTO> queryOrder(List<String> customerNumbers, Date from, Date to);
}
