package com.yeepay.g3.core.ymf.biz.trade;

import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderQueryArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundRequestDTO;

/**
 * Title: 易码付交易类BIZ
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public interface TradeBiz {

    /**
     * 根据参数查询订单
     * @param args 订单参数
     * @return resp
     */
    ResponseMessage queryOrder(OrderArgs args);

    /**
     * 根据参数查询订单
     * @param args 订单参数
     * @return resp
     */
    ResponseMessage querySingleOrder(OrderQueryArgs args);


    /**
     * 根据参数查询退款总数和金额
     * @param args 退款参数
     * @return resp
     */
    ResponseMessage queryRefund(RefundOrderArgs args);

    /**
     * 发起退款请求
     * @param request 退款请求实体
     * @return resp
     */
    ResponseMessage refundOrder(RefundRequestDTO request);

}
