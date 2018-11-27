package com.yeepay.g3.core.ymf.service.order;

import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderParam;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;

import java.util.List;

/**
 * Title: 订单同步专用接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/2/23.
 */
public interface NewSyncOrderService {

    /**
     * 根据商户编号和订单时间汇总来客订单
     * @param param 请求参数
     * @return 成功的交易订单
     */
    CountResponse countSyncOrder(SyncOrderParam param);

    /**
     * 根据商户编号和订单时间查询来客订单
     * @param param 请求参数
     * @return 成功的交易订单
     */
    List<SyncOrderDTO> queryOrder(SyncOrderParam param);

    /**
     * 逐笔结算的汇总
     * @param param 请求参数
     * @return 成功的逐笔结算
     */
    CountResponse countSyncS0Remit(SyncOrderParam param);

    /**
     * 逐笔结算
     * @param param 请求参数
     * @return 成功的逐笔结算
     */
    List<SyncS0OrderDTO> queryS0Remit(SyncOrderParam param);
}
