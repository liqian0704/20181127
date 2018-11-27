package com.yeepay.g3.core.ymf.service.impl.trade;

import com.yeepay.g3.core.ymf.biz.TradeCashierBizService;
import com.yeepay.g3.core.ymf.service.trade.TradeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongxulu on 16/12/3.
 */
@Service
public class TradeFactoryImpl implements TradeFactory {

    @Autowired
    TradeCashierBizService tradeCashierBizService;
    @Override
    public TradeCashierBizService getTradeCashierService() {

        return tradeCashierBizService;
    }
}
