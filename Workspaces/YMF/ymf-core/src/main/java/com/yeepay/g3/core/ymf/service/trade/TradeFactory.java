package com.yeepay.g3.core.ymf.service.trade;

import com.yeepay.g3.core.ymf.biz.TradeCashierBizService;

/**
 * Created by dongxulu on 16/12/3.
 */
public interface TradeFactory {
    /**
     * 获取收银台服务
     * @return
     */
    public TradeCashierBizService getTradeCashierService();
}
