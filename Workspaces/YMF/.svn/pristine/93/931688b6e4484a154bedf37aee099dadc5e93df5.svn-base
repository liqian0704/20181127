package com.yeepay.g3.facade.ymf.facade.laike;

import com.yeepay.g3.facade.ymf.dto.laike.BaseResp;
import com.yeepay.g3.facade.ymf.dto.laike.SettleArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderQueryArgs;

/**
 * Title: 来客管账接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/11/2.
 */
public interface OrderFacade {

    /**
     * 收款查询
     * @param args 请求参数
     * @return 统一返回
     */
    BaseResp queryOrder(OrderArgs args);

    /**
     * 查询订单详情
     * @param args 请求参数
     * @return 统一返回
     */
    BaseResp queryOrderDetail(OrderQueryArgs args);

    /**
     * 到账查询
     * @param args 请求参数
     * @return 统一返回
     */
    BaseResp querySettle(SettleArgs args);
}
