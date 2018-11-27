package com.yeepay.g3.core.ymf.service.impl.trade;

import com.yeepay.g3.core.ymf.dao.order.OrderMapper;
import com.yeepay.g3.core.ymf.dao.order.PaymentMapper;
import com.yeepay.g3.core.ymf.dao.refund.RefundOrderDetailMapper;
import com.yeepay.g3.core.ymf.dao.refund.RefundOrderMapper;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.core.ymf.service.trade.TradeService;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceUtils;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: 交易聚合类, 为了保证事务一致性
 * Description: 请不要在此类中添加非事务方法
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/26.
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = YmfException.class)
@Service
public class TradeServiceImpl implements TradeService {

    private static final Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private RefundOrderMapper refundOrderMapper;
    @Autowired
    private RefundOrderDetailMapper refundOrderDetailMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void refundOrder(Payment refundPayment, RefundOrder refundOrder, RefundOrderDetail refundOrderDetail)
            throws YmfException {
        try {
            if (null == refundOrder.getId()) {
                refundOrderMapper.insert(refundOrder);
            } else {
                refundOrderMapper.updateByPrimaryKey(refundOrder);
            }
            paymentMapper.insert(refundPayment);
            refundOrderDetail.setRefundinfoId(refundOrder.getId());
            refundOrderDetailMapper.insert(refundOrderDetail);
            // 更新退款订单号
            String refundOrderId = SequenceUtils.createRefundSequence(refundOrderDetail.getId());
            refundOrderDetail.setRefundOrderId(refundOrderId);
            refundOrderDetailMapper.updateByPrimaryKeySelective(refundOrderDetail);
            log.info("新建退款记录成功 交易流水号:" + refundPayment.getYeepayOrderId());
        } catch (Exception e) {
            throw new YmfException(e);
        }
    }

    @Override
    public void refreshAcceptRefund(Payment refundPayment, RefundOrder refundOrder,
                                    RefundOrderDetail refundOrderDetail) throws YmfException {
        try {
            paymentMapper.updateByPrimaryKey(refundPayment);
            refundOrderMapper.updateByPrimaryKey(refundOrder);
            refundOrderDetailMapper.updateByPrimaryKey(refundOrderDetail);
            log.info("更新退款记录成功 交易流水号:" + refundPayment.getYeepayOrderId());
        } catch (Exception e) {
            throw new YmfException(e);
        }
    }

    @Override
    public void refundSupply(Payment refundPayment, RefundOrderDetail refundOrderDetail) throws YmfException {
        try {
            paymentMapper.updateByPrimaryKey(refundPayment);
            refundOrderDetailMapper.updateByPrimaryKey(refundOrderDetail);
            log.info("更新退款补单状态成功 交易流水号:" + refundPayment.getYeepayOrderId());
        } catch (Exception e) {
            throw new YmfException(e);
        }
    }

    @Override
    public void refundSupply(Order order, RefundOrder refundOrder) throws YmfException {
        try {
            orderMapper.updateByPrimaryKey(order);
            refundOrderMapper.updateByPrimaryKey(refundOrder);
            log.info("更新退款补单状态成功 商户订单号:" + order.getCustomerOrderId());
        } catch (Exception e) {
            throw new YmfException(e);
        }
    }

    @Override
    public void refreshOrderStatus(Order order, List<Payment> paymentList) throws YmfException {
        try {
            orderMapper.updateByPrimaryKey(order);
            for (Payment payment : paymentList) {
                paymentMapper.updateByPrimaryKey(payment);
            }
            log.info("更新交易订单状态成功 商户编号:" + order.getCustomerNumber() + ", 商户订单号:" + order.getCustomerOrderId());
        } catch (Exception e) {
            throw new YmfException(e);
        }
    }

    @Override
    public void refreshPaymentStatus(List<Payment> paymentList) throws YmfException {
        try {
            for (Payment payment : paymentList) {
                paymentMapper.updateByPrimaryKey(payment);
            }
        } catch (Exception e) {
            throw new YmfException(e);
        }
    }
}
