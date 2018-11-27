package com.yeepay.g3.core.ymf.service.trade;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.facade.ymf.exception.YmfException;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/26.
 */
public interface TradeService {

    /**
     * 发起退款
     * 新建记录:Payment, RefundOrderDetail
     * 如果是第一次退款 需要新建RefundOrder
     * 否则更新RefundOrder
     * 为了保证数据, 在同一个事物内持久化
     *
     * @param refundPayment 退款记录
     * @param refundOrder 退款主表
     * @param refundOrderDetail 退款详情
     *
     * @throws YmfException 内部捕获异常记录流程, 再抛出保证事物回滚
     */
    void refundOrder(Payment refundPayment, RefundOrder refundOrder, RefundOrderDetail refundOrderDetail) throws YmfException;

    /**
     * 发起退款后 更新状态
     * @param refundPayment 退款记录
     * @param refundOrder 退款主表
     * @param refundOrderDetail 退款详情
     * @throws YmfException 内部捕获异常记录流程, 再抛出保证事物回滚
     */
    void refreshAcceptRefund(Payment refundPayment, RefundOrder refundOrder, RefundOrderDetail refundOrderDetail) throws YmfException;


    /**
     * 退款订单补单
     * @param refundPayment 退款Payment
     * @param refundOrderDetail 退款详情
     * @throws YmfException 内部捕获异常记录流程, 再抛出保证事物回滚
     */
    void refundSupply(Payment refundPayment, RefundOrderDetail refundOrderDetail) throws YmfException;

    /**
     * 退款订单补单
     * @param order 订单
     * @param refundOrder 退款订单
     * @throws YmfException 内部捕获异常记录流程, 再抛出保证事物回滚
     */
    void refundSupply(Order order, RefundOrder refundOrder) throws YmfException;

    /**
     * 更新订单状态
     * @param order
     * @param paymentList
     * @throws YmfException
     */
    void refreshOrderStatus(Order order, List<Payment> paymentList) throws YmfException;

    /**
     * 更新payment状态
     * @param paymentList
     * @throws YmfException
     */
    void refreshPaymentStatus(List<Payment> paymentList) throws YmfException;
}
