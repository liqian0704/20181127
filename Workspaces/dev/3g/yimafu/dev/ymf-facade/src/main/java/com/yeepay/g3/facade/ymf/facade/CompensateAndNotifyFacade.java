package com.yeepay.g3.facade.ymf.facade;

import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;

/**
 * @Description: 底层系统补偿订单并补发COD通知接口
 * @Author: xiaobin.liu
 * @Date: 17/5/5
 * @Time: 上午10:27
 */
public interface CompensateAndNotifyFacade {

    /**
     * 补偿订单和补发通知。如订单未成功支付，发起补单并通知COD（补单成功）；如订单已经成功发起COD通知。
     */
    ResponseMessage compensateAndNotify(String externalId);
}
