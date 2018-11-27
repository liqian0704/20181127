package com.yeepay.g3.core.ymf.facade.impl.laike;

import com.yeepay.g3.core.ymf.biz.trade.TradeBiz;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.laike.BaseResp;
import com.yeepay.g3.facade.ymf.dto.laike.SettleArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderQueryArgs;
import com.yeepay.g3.facade.ymf.facade.laike.OrderFacade;
import com.yeepay.g3.facade.ymf.validator.LaikeSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: 来客管账接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/11/2.
 */
@Service
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private TradeBiz tradeBiz;

    @Override
    public BaseResp queryOrder(OrderArgs args) {
        return LaikeSupport.transfer(tradeBiz.queryOrder(args));
    }

    @Override
    public BaseResp queryOrderDetail(OrderQueryArgs args) {
        return LaikeSupport.transfer(tradeBiz.querySingleOrder(args));
    }

    @Override
    public BaseResp querySettle(SettleArgs args) {
        return LaikeSupport.transfer(tradeBiz.querySettle(args));
    }
}
