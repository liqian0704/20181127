package com.yeepay.g3.core.ymf.biz.remit;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferParamDTO;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferResultDTO;
import com.yeepay.g3.facade.balance.dto.remitdto.TransferParamDTO;
import com.yeepay.g3.facade.balance.page.PageResult;

/**
 * 调用打款统一服务
 * Created by dongxulu on 17/4/25.
 */
public interface InvokeRemoteRemitService {

    /**
     * 调用远程系统，请求打款
     * @param order     订单
     * @param payment   支付信息
     * @param remittance    打款记录
     */
    void requestRemittance(Order order, Payment payment, Remittance remittance) throws Exception;

    /**
     * 判断是否能打款，保存打款记录
     */
     void doRemittance(Order order,Payment payment) throws Throwable;

    /**
     * 查询打款信息
     */
     PageResult<BatchQueryTransferResultDTO> getRemitInfo(BatchQueryTransferParamDTO queryTransferParamDTO) throws Throwable;

    /**
     * 调用打款接口服务
     * @param param
     * @return
     */
     TransferParamDTO transferConfirm(TransferParamDTO param) throws Throwable;


}
