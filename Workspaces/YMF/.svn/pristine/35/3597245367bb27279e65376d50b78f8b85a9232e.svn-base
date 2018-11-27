package com.yeepay.g3.core.ymf.support;

import com.yeepay.g3.core.ymf.entity.common.OperateLog;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.facade.ymf.dto.refund.RefundRequestDTO;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundSource;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundStatus;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.utils.common.StringUtils;

import java.util.Date;

/**
 * Title: 实体构建
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/26.
 */
public class EntityBuilder {

    /**
     * 根据订单构造退款订单
     * @param order
     * @return
     */
    public static RefundOrder buildRefundOrder(Order order) {
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setCustomerOrderId(order.getCustomerOrderId());
        refundOrder.setCustomerNumber(order.getCustomerNumber());
        refundOrder.setOrderId(order.getId());
        refundOrder.setOrderTime(order.getCompleteTime());
        refundOrder.setTrxAmount(order.getTrxAmt());
        refundOrder.setRemain(order.getTrxAmt());
        refundOrder.setRefundStatus(RefundStatus.INIT);
        return refundOrder;
    }

    /**
     * 根据参数构建退款订单明细
     * @param orderPayment 支付payment
     * @param refundOrder 退款主记录
     * @param request 退款外部请求
     * @return RefundOrderDetail
     */
    public static RefundOrderDetail buildRefundOrderDetail(Payment orderPayment, RefundOrder refundOrder,
                                                           RefundRequestDTO request) {
        RefundOrderDetail refundOrderDetail = new RefundOrderDetail();
        refundOrderDetail.setRefundinfoId(refundOrder.getId());
        refundOrderDetail.setRefundAmount(request.getRefundAmount());
        refundOrderDetail.setCause(request.getRemark());
        refundOrderDetail.setYeepayOrderId(orderPayment.getYeepayOrderId());
        refundOrderDetail.setRefundSource(RefundSource.MERCHANT_BOSS);
        refundOrderDetail.setRefundType(RefundType.REFUND_MANUAL);
        refundOrderDetail.setTrxAmount(orderPayment.getTrxAmt());
        refundOrderDetail.setCreateTime(new Date());
        refundOrderDetail.setRefundStatus(RefundStatus.INIT);
        refundOrderDetail.setPaySource(orderPayment.getPaySource());
        refundOrderDetail.setPayTime(orderPayment.getPayTime());
        return refundOrderDetail;
    }

    /**
     * 构建退款payment
     * @param orderDetail 退款明细
     * @param orderPayment 支付记录
     * @return Payment
     */
    public static Payment buildRefundPayment(RefundOrderDetail orderDetail, Payment orderPayment) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(orderPayment, payment);
        payment.setId(null);
        payment.setTrxType(TrxType.REFUND);
        payment.setCreateTime(new Date());
        payment.setPayStatus(PaymentStatus.INIT);
        payment.setRefundAmt(orderDetail.getRefundAmount());
        return payment;
    }

    /**
     * 根据实体构建日志
     * @param en 实体
     * @param bizType 业务类型
     * @param eclipse 执行时间
     * @return 日志
     */
    public static OperateLog buildLog(OperateEntity en, String bizType, Long eclipse) {
        OperateLog log = new OperateLog();
        log.setOperatorName(en.getOperator());
        log.setBizType(strictLength(bizType, 30));
        log.setEntityName(strictLength(en.getEntity().getClass().getCanonicalName(), 100));
        log.setOptType(en.getOperateType());
        log.setCustomerNumber(en.getCustomerNumber());
        log.setDescription(strictLength(en.getEntity().toString(), 2000));
        log.setProceedTime(eclipse);
        log.setCreateTime(new Date());
        return log;
    }

    /**
     * biztype长度不能超过30
     * @param name
     * @return
     */
    private static String strictLength(String name, int length) {
        if (StringUtils.isNotBlank(name)) {
            if (name.length() >= length) {
                return name.substring(0, length);
            }
        }
        return name;
    }
}
