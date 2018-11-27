package com.yeepay.g3.core.ymf.service.refund;

import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.refund.RefundDTO;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderArgs;

import java.util.Date;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public interface RefundService {

    /**
     * 根据条件查询退款订单
     * @param  orderId 商户订单主键
     * @return
     */
    RefundOrder queryByOrderId(Long orderId);

    /**
     * 根据退款主键查询明细
     * @param refundOrderId
     * @return
     */
    RefundOrderDetail queryByRefundOrderId(Long refundOrderId);

    /**
     * 根据参数查询订单
     * @param args 参数
     * @return
     */
    List<RefundDTO> queryRefundDTOByArgs(RefundOrderArgs args);

    /**
     * 根据参数汇总
     * @param args 参数
     * @return
     */
    CountResponse countRefundByArgs(RefundOrderArgs args);

    /**
     * 根据时间查询 状态是PROCESS的退款订单
     * @param from 开始时间
     * @param to 结束时间
     * @return
     */
    List<RefundOrder> querySupplyRefund(Date from, Date to);

    /**
     * 根据退款主键 查询退款明细
     * @param refundInfoId 退款主键
     * @return list
     */
    List<RefundOrderDetail> querySupplyDetail(Long refundInfoId);

    /**
     * 根据退款主键、交易流水号 查询退款明细
     * @param refundInfoId 退款主键
     * @param yeepayOrderId 交易流水号
     * @return list
     */
    List<RefundOrderDetail> querySupplyDetail(Long refundInfoId, String yeepayOrderId);

    /**
     * 更新退款明细
     * @param refundOrderDetail
     * @return
     */
    int updateRefundDetail(RefundOrderDetail refundOrderDetail);

    /**
     * 更新退款主表
     * @param refundOrder 退款主记录
     * @return 0 or 1
     */
    int updateRefundOrder(RefundOrder refundOrder);


}
