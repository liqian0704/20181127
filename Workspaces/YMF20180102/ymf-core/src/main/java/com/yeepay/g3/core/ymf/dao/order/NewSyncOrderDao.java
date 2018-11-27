package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderParam;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewSyncOrderDao {

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
    List<SyncOrderDTO> querySyncOrder(SyncOrderParam param);

    /**
     * 逐笔结算汇总
     * @param param 请求参数
     * @return 成功的逐笔结算订单
     */
    CountResponse countS0Remit(SyncOrderParam param);

    /**
     * 逐笔结算纪录
     * @param param 请求参数
     * @return 成功的逐笔结算
     */
    List<SyncS0OrderDTO> querySyncRemit(SyncOrderParam param);
}