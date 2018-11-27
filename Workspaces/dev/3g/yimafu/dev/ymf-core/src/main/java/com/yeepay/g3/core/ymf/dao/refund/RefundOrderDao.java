package com.yeepay.g3.core.ymf.dao.refund;

import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.refund.RefundDTO;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderArgs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundOrderDao {

    /**
     * 根据条件查询退款订单
     * @param args
     * @return
     */
    List<RefundDTO> queryRefundDTOByArgs(RefundOrderArgs args);

    /**
     * 根据条件汇总退款订单
     * @param args
     * @return
     */
    CountResponse countRefundByArgs(RefundOrderArgs args);

    /**
     * 根据订单主键查询 不是FAIL的退款主表
     * @param orderId
     * @return
     */
    RefundOrder queryByOrderId(Long orderId);

}