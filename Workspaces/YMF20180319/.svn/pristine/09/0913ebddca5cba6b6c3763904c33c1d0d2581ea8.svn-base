package com.yeepay.g3.core.ymf.facade.impl;

import com.yeepay.g3.core.ymf.biz.trade.SupplyBiz;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.facade.CompensateAndNotifyFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 底层系统补偿订单并补发COD通知接口
 * @Author: xiaobin.liu
 * @Date: 17/5/5
 * @Time: 上午10:28
 */
@Service
public class CompensateAndNotifyFacadeImpl implements CompensateAndNotifyFacade {
    private static Logger logger = LoggerFactory.getLogger(TradeCashierCallBackFacadeImpl.class);
    @Autowired
    private SupplyBiz supplyBiz;
    /**
     * 补偿订单和补发通知。如订单未成功支付，发起补单并通知COD（补单成功）；如订单已经成功发起COD通知。
     *
     * @param externalId    易码付业务订单号
     */
    @Override
    public ResponseMessage compensateAndNotify(String externalId) {
        logger.info("[补单接口] 请求参数：{}",externalId);
        if (StringUtils.isBlank(externalId)) {
            return ResponseMessage.error("业务订单号必传！");
        }
        ResponseMessage responseMessage = supplyBiz.supplyOrderByExernalId(externalId);
        logger.info("[补单接口] 响应参数：{}", JSONUtils.toJsonString(responseMessage));
        return responseMessage;
    }
}
