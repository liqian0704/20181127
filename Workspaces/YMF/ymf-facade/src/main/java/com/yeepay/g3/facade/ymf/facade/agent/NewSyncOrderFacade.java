package com.yeepay.g3.facade.ymf.facade.agent;

import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderDTO;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderParam;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

import java.util.List;

/**
 * Title: 易码付对接代理商同步订单接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/12/22.
 */
public interface NewSyncOrderFacade {

    /**
     * 根据商户编号和订单时间汇总来客订单
     * @param param 请求参数
     * @return 成功的交易订单汇总
     */
    CountResponse countOrder(SyncOrderParam param) throws YmfTrxException;

    /**
     * 根据商户编号和订单时间查询来客订单
     * @param param 请求参数
     * @return 成功的交易订单
     */
    List<SyncOrderDTO> queryOrder(SyncOrderParam param) throws YmfTrxException;

    /**
     * 根据商户编号和订单时间汇总来客秒到订单
     * @param param 请求参数
     * @return
     * @throws YmfTrxException
     */
    CountResponse countS0Remit(SyncOrderParam param) throws YmfTrxException;

    /**
     * 根据商户编号和订单时间查询来客秒到订单
     * @param param 请求参数
     * @return
     * @throws YmfTrxException
     */
    List<SyncS0OrderDTO> queryS0Remit(SyncOrderParam param) throws YmfTrxException;

}