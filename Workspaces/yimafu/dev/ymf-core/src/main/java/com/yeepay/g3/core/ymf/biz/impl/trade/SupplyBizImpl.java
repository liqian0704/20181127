package com.yeepay.g3.core.ymf.biz.impl.trade;

import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.biz.cod.CodBiz;
import com.yeepay.g3.core.ymf.biz.trade.SupplyBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.service.refund.RefundService;
import com.yeepay.g3.core.ymf.service.trade.TradeService;
import com.yeepay.g3.core.ymf.support.NCApiSupport;
import com.yeepay.g3.core.ymf.support.TradeContext;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierBaseDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeRefundQueryRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeRefundQueryResponseDTO;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.facade.TradeBaseFacade;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 交易退款补单
 * Created by chen.liu on 2016/8/28.
 *
 */
@Service
public class SupplyBizImpl extends SoaBaseBiz implements SupplyBiz {

    private static final Logger log = LoggerFactory.getLogger(SupplyBizImpl.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RefundService refundService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CodBiz codBiz;
    @Autowired
    private TradeYMFbizService tradeYMFbizService;

    private ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private static final String log_pre = "{}, 开始时间:{} 结束时间:{}, 条数:{}";
    private static final String order_log_pre = "{}, 商户编号:{}, 商户订单号:{}";
    private static final String order_start_log_pre = "{}, 商户编号:{}, 商户订单号:{}, 支付记录数:{}";
    private static final String order_end_log_pre = "{}, 商户编号:{}, 商户订单号:{}, 更新支付记录数:{}";
    private static final String payment_log_pre = order_log_pre + ", 交易流水号:{}";
    private static final String payment_api_log_pre = payment_log_pre + ", 接口返回结果:{}";


    @Override
	public ResponseMessage supplyOrderById(Long paymentId) throws YmfException, ParameterInvalidException {
    	Payment payment = paymentService.findById(paymentId) ;
		Customer customer = null;
		if (payment == null) {
			return ResponseMessage.error("订单不存在！");
		}
		Order order = orderService.findById(payment.getOrderId());
		customer = customerService.findByCustomerNumber(order.getCustomerNumber());
		OrderStatus orderStatus = order.getOrderStatus();
		if (OrderStatus.SUCCESS.equals(orderStatus)) {
			// 发起COD通知
            codBiz.orderPayNotify(payment, customer, order);
            log.info("------ Cod Notify Success");
            return ResponseMessage.error("该订单已经交易成功，已补COD通知");
		} else if (OrderStatus.FAIL.equals(orderStatus)) {
			return ResponseMessage.error("该订单已经交易失败，请确认！");
		}
		TradeCashierBaseDTO request = NCApiSupport.buildSupplyRequest(order, payment);
		TradeCashierFacade facade = getService(TradeCashierFacade.class);
		TradeCashierReplyDTO resp = facade.purchaseSupply(request);
		if (null == resp) {
			return ResponseMessage.error("补单接口调用失败，请稍后重试！");
		}
		String orderState = resp.getOrderState();
		PaymentStatus status = NCApiSupport.transferStatus(orderState);
        log.info("[NC_API_RESP]补单接口返回信息:" + resp);
		if (PaymentStatus.SUCCESS.equals(status)) {
			payment = NCApiSupport.preparePayment(payment, resp);
			NCApiSupport.prepareOrder(resp, order);
			log.info("[SUPPLY_ORDER] 补交易单成功, 接口返回状态: " + orderState + ", 补单状态:" + payment.getPayStatus());
			// 行业版需要通知 成功时, 通知cod TODO 拆单需要修改
			tradeYMFbizService.updateOrderAndPayment(order, payment);
			log.error("------ Update Order And Payment Success,Order is Success");
            codBiz.orderPayNotify(payment, customer, order);
            log.info("------ Cod Notify Success");
			return ResponseMessage.error("订单支付成功，已通知COD！");
		} else if (PaymentStatus.FAIL.equals(status)) {
			NCApiSupport.prepareFailPayment(payment, resp);
			order.setOrderStatus(OrderStatus.FAIL);
			order.setCompleteTime(new Date());
			tradeYMFbizService.updateOrderAndPayment(order, payment);
			log.error("------ Update Order And Payment Success ,Order is Fail");
			return ResponseMessage.error("订单超时取消或支付失败！");
		} else {
			return ResponseMessage.error("订单未支付，等待支付中！");
		}
	}
    /**
     * 
     * @param from 开始时间
     * @param to 结束时间
     * @return
     */
    @Override
    public void supplyOrder(Date from, Date to) {
        List<Order> supplyOrders = orderService.findSupplyOrder(from, to); // 查询补单 只查PROCESS状态的
        if (null != supplyOrders) {
            DateFormat df = threadLocal.get();
            log.info(log_pre, "[SUPPLY_ORDER] 定时开始补交易单", df.format(from), df.format(to), supplyOrders.size());
            for (Order order : supplyOrders) {
                supplyOrder(order);
            }
        }
    }

    @Override
    public ResponseMessage supplyOrder(String customerNumber, String externalId) {
        Order order = orderService.findByCustomerAndExternalId(customerNumber, externalId);
        return supplyOrder(order);
    }

    @Override
    public ResponseMessage supplyOrder(Order order) {
        if (null == order) {
            return ResponseMessage.error("订单不能为空");
        }
        if (OrderStatus.PROCESS != order.getOrderStatus()) { // 外部调用 不记录日志
            return ResponseMessage.error("订单状态不是支付中");
        }
        String customerNumber = order.getCustomerNumber();
        String customerOrderId = order.getCustomerOrderId();
        List<Payment> paymentList = paymentService.queryPayments(customerNumber, order.getId(), TrxType.PURCHASE);
        if (null != paymentList) {
            final int final_count = paymentList.size();
            log.info(order_start_log_pre, "[SUPPLY_ORDER] 开始补交易单", customerNumber, customerOrderId, final_count);
            List<Payment> successList = new ArrayList<Payment>();
            List<Payment> failList = new ArrayList<Payment>();
            int successCount = 0; // 支付成功
            int failCount = 0; // 支付失败
            int processCount = 0; // 支付中
            TradeContext context = new TradeContext();
            for (Payment payment : paymentList) {
                String yeepayOrderId = payment.getYeepayOrderId();
                log.info(payment_log_pre + ", payment状态:{}", "[SUPPLY_ORDER_PAYMENT] 开始处理payment", customerNumber,
                        customerOrderId, yeepayOrderId, payment.getPayStatus());
                // 如果payment的状态是success, 则跳过, 并记录金额
                switch (payment.getPayStatus()) {
                    case INIT: // TODO INT是否占用支付金额?
                        processCount ++;
                        break;
                    case FAIL:
                        failCount ++;
                        break;
                    case SUCCESS: // 成功的不需要补状态
                        context.add(payment.getRealAmt(), payment.getFee());
                        successCount ++;
                        break;
                    case PROCESS:
                        PaymentStatus returnStatus = supplyOrder(order, payment, context);
                        switch (returnStatus) { // 根据补单状态 判断如何处理
                            case SUCCESS:
                                successList.add(payment);
                                successCount ++;
                                break;
                            case FAIL:
                                failList.add(payment);
                                failCount ++;
                                break;
                            case PROCESS:
                                // 如果是process则 保留在remainSize里
                                processCount ++;
                                break;
                            default: // nonsense
                        }
                        break;
                    default: // nonsense
                }
            }
            int successSize = successList.size() + successCount; // 总成功数
            int failSize = failList.size() + failCount; // 总失败数
            if (processCount == final_count) { // 都是支付中
                log.debug(order_start_log_pre, "[SUPPLY_ORDER] 订单状态还是支付中",
                        customerNumber, customerOrderId, final_count);
                return ResponseMessage.ok();
            }
            // 判断 1.全部成功 2.部分成功 3.全部失败
            try {
                if (successSize == final_count) { // 全部成功
                    // 补实收金额和手续费
                    order.setRealAmt(context.getAmount());
                    order.setFee(context.getFee());
                    order.setOrderStatus(OrderStatus.SUCCESS);
                    order.setCompleteTime(new Date());
                    tradeService.refreshOrderStatus(order, successList);
                    log.info(order_end_log_pre, "[SUPPLY_ORDER] 支付记录全部成功 ", customerNumber, customerOrderId, successSize);
                    Customer customer = customerService.findByCustomerNumber(order.getCustomerNumber());
                    codNotify(order, successList, customer); // 成功才通知COD
                } else {
                    if (failSize == final_count) { // 全部失败
                        order.setOrderStatus(OrderStatus.FAIL);
                        order.setCompleteTime(new Date());
                        tradeService.refreshOrderStatus(order, failList);
                        log.info(order_end_log_pre, "[SUPPLY_ORDER] 支付记录全部失败 ", customerNumber, customerOrderId, successSize);
                    } else { // 部分成功
                        // 补实收金额和手续费
                        order.setRealAmt(context.getAmount());
                        order.setFee(context.getFee());
                        failList.addAll(successList); // 成功和失败的都需要更新
                        tradeService.refreshOrderStatus(order, failList);
                        Customer customer = customerService.findByCustomerNumber(order.getCustomerNumber());
                        codNotify(order, successList, customer); // 只通知COD成功的
                        log.info(order_end_log_pre, "[SUPPLY_ORDER] 支付记录部分成功 ", customerNumber, customerOrderId, successSize);
                    }
                }
            } catch (Throwable t) {
                log.error("[SUPPLY_ORDER] 补单失败", t);
            }
        } else {
            log.info("[SUPPLY_ORDER] 订单没有支付记录!!! 商户编号:" + customerNumber + ", 商户订单号:" + customerOrderId);
        }
        return ResponseMessage.ok();
    }

    /**
     * 补单成功后通知COD
     * @param order 订单
     * @param successList 成功的支付记录
     */
    private void codNotify(Order order, List<Payment> successList, Customer customer) {
        for (Payment payment : successList) {
            // 行业版需要通知 成功时, 通知cod
            codBiz.orderPayNotify(payment, customer, order);
            log.info("------ Cod Notify Success");
        }
    }

    /**
     * 补支付记录
     * @param order 订单
     * @param payment 支付记录
     * @param context 用于累计金额
     * @return PaymentStatus
     */
    private PaymentStatus supplyOrder(Order order, Payment payment, TradeContext context) {
        String customerNumber = order.getCustomerNumber();
        String customerOrderId = order.getCustomerOrderId();
        String yeepayOrderId = payment.getYeepayOrderId();
        TradeCashierBaseDTO request = NCApiSupport.buildSupplyRequest(order, payment);
        // 请求补单
        TradeCashierFacade facade = getService(TradeCashierFacade.class);
        try {
            TradeCashierReplyDTO resp = facade.purchaseQuery(request);
            log.info(payment_api_log_pre, "[SUPPLY_ORDER_PAYMENT][NC_API] 支付记录补单接口",
                    customerNumber, customerOrderId, yeepayOrderId, resp);
            if (null != resp) {
                String orderState = resp.getOrderState();
                if (null != orderState) {
                    PaymentStatus paymentStatus = NCApiSupport.transferStatus(orderState);
                    payment.setPayStatus(paymentStatus);
                    log.info("[NC_API_RESP] 商户编号:" + customerNumber + ", 商户订单号:" + customerOrderId
                                    + ",接口返回结果: " + resp);
                    // 如果状态是成功, 则要补一些字段
                    if (PaymentStatus.SUCCESS == paymentStatus) {
                        payment = NCApiSupport.preparePayment(payment, resp);
                        // 累计实收金额和手续费
                        context.add(payment.getRealAmt(), payment.getFee());
                        log.info(payment_log_pre + "接口返回状态: " + orderState + ", 补单状态:" + paymentStatus,
                                "[SUPPLY_ORDER_PAYMENT] 补支付记录成功", customerNumber, customerOrderId, yeepayOrderId);
                    } else if (PaymentStatus.FAIL == paymentStatus) {
                        NCApiSupport.prepareFailPayment(payment, resp);
                        log.info(payment_log_pre + "接口返回状态: " + orderState + ", 补单状态:" + paymentStatus,
                                "[SUPPLY_ORDER_PAYMENT] 补支付记录结束", customerNumber, customerOrderId, yeepayOrderId);
                    }
                    return paymentStatus;
                }
            }
        } catch (ParameterInvalidException e) {
            log.error("[SUPPLY_ORDER][CALL_NCAPI] 发起补交易单请求参数校验失败" + e.getMessage()); // 不修改补单状态
        } catch (Exception e) {
            log.error("[SUPPLY_ORDER][CALL_NCAPI] 发起补交易单请求异常", e); // 不修改补单状态
        }
        return PaymentStatus.PROCESS; // 其他状态都是支付中...
    }

    @Override
    public void supplyRefund(Date from, Date to) {
        List<RefundOrder> supplyRefund = refundService.querySupplyRefund(from, to);
        if (null != supplyRefund) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.info("[SUPPLY_REFUND] 开始补退款单, FROM:" + df.format(from) + " TO:" + df.format(to) + " 补单数:" + supplyRefund.size());
            for (RefundOrder refundOrder : supplyRefund) { // 查询Payment和RefundDetail
                supplyRefund(refundOrder);
            }
        }
    }

    @Override
    public ResponseMessage supplyRefund(Long orderId) {
        RefundOrder refundOrder = refundService.queryByOrderId(orderId);
        return supplyRefund(refundOrder);
    }

    @Override
    public ResponseMessage supplyRefund(RefundOrder refundOrder) {
        if (null == refundOrder) {
            return ResponseMessage.error("退款订单不能为空");
        }
        if (RefundStatus.PROCESS != refundOrder.getRefundStatus()) {
            return ResponseMessage.error("退款订单状态不是退款中");
        }
        String customerNumber = refundOrder.getCustomerNumber();
        String customerOrderId = refundOrder.getCustomerOrderId();
        String log_out = " 商户编号:" + customerNumber + ", 商户订单号: " + customerOrderId;
        // 根据商户编号和商户订单号查询 交易Payment
        BigDecimal orderRefundAmount = BigDecimal.ZERO; // 订单总退款金额
        Long orderId = refundOrder.getOrderId();
        List<Payment> paymentList = paymentService.queryPayments(customerNumber, orderId, TrxType.PURCHASE);
        if (null != paymentList) {
            log.info("[SUPPLY_REFUND] 开始补退款单payment " + log_out);
            int total_count = 0;
            int refund_count = 0;
            int already_refund_count = 0;
            for (Payment payment : paymentList) {
                // 退款详情, 退款payment, 交易payment
                String yeepayOrderId = payment.getYeepayOrderId();
                List<Payment> refundPaymentList = paymentService.queryPayments(customerNumber, orderId, yeepayOrderId, TrxType.REFUND);
                List<RefundOrderDetail> refundOrderDetailList = refundService.querySupplyDetail(refundOrder.getId(), yeepayOrderId);
                // paymentList和refundOrderDetailList的个数应该一样
                if (null != refundPaymentList && null != refundOrderDetailList) {
                    String log_f = log_out + ", 交易流水号: " + yeepayOrderId;
                    int count = refundPaymentList.size();
                    int final_count = count;
                    BigDecimal paymentRefundAmount = BigDecimal.ZERO; // payment总退款金额
                    log.info("[SUPPLY_REFUND] 开始补退款单 " + log_f + ", 总退款次数:" + count);
                    for (int i = 0; i < final_count; i++) {
                        total_count ++;
                        Payment refundPayment = refundPaymentList.get(i);
                        RefundOrderDetail refundOrderDetail = refundOrderDetailList.get(i);
                        BigDecimal oneRefundAmount = refundOrderDetail.getRefundAmount();
                        // 判断退款状态
                        if (PaymentStatus.SUCCESS == refundPayment.getPayStatus()) {
                            // 累计退款金额
                            paymentRefundAmount = Amount.add(paymentRefundAmount, oneRefundAmount);
                            orderRefundAmount = Amount.add(orderRefundAmount, oneRefundAmount);
                            already_refund_count ++;
                            continue;
                        }
                        TradeRefundQueryRequestDTO request = NCApiSupport.buildSupplyRequest(refundOrder, refundOrderDetail);
                        // 请求补单
                        TradeBaseFacade facade = getService(TradeBaseFacade.class);
                        try {
                            TradeRefundQueryResponseDTO resp = facade.refundQuery(request);
                            if (null != resp) {
                                String refundStatus = resp.getStatus();
                                if (null != refundStatus) {
                                    RefundStatus status = NCApiSupport.transferRefundStatus(refundStatus);
                                    if (RefundStatus.SUCCESS == status) {
                                        refundOrderDetail.setRefundStatus(status);
                                        refundOrderDetail.setRefundTime(resp.getLastUpdateTime());
                                        refundPayment.setPayStatus(NCApiSupport.transferRefundPaymentStatus(refundStatus));
                                        // 更新
                                        tradeService.refundSupply(refundPayment, refundOrderDetail);
                                        paymentRefundAmount = Amount.add(paymentRefundAmount, oneRefundAmount);
                                        orderRefundAmount = Amount.add(orderRefundAmount, oneRefundAmount);
                                        log.info("[SUPPLY_REFUND] 补退款单成功" + log_f + ", 接口返回状态: " + refundStatus
                                                + ", 补单状态:" + refundOrderDetail.getRefundStatus());
                                        count--;
                                        refund_count++;
                                    } else {
                                        log.info("[SUPPLY_REFUND] 补退款单" + log_f + ", 接口返回状态: " + refundStatus
                                                + ", 原退款状态:" + refundOrderDetail.getRefundStatus());
                                    }
                                }

                            }
                        } catch (ParameterInvalidException e) {
                            log.error("[SUPPLY_REFUND][CALL_NCAPI] 发起补退款单请求参数校验失败" + e.getMessage()); // 不修改补单状态
                        } catch (YmfException e) {
                            log.error("[SUPPLY_REFUND][CALL_NCAPI] 发起补退款单请求异常", e); // 不修改补单状态
                        }
                    }
                    // 判断所有的Payment和RefundDetail是否都完成补单
                    if (final_count == count) {
                        if (log.isDebugEnabled()) {
                            log.debug("[SUPPLY_REFUND] 未进行任何补单操作, 需要补单数:" + final_count + log_f);
                        }
                        continue;
                    }
                    // 有退款 更新下payment
                    payment.setRefundAmt(paymentRefundAmount);
                    paymentService.updateById(payment);
                    log.info("[SUPPLY_REFUND] 更新交易payment的退款金额 " + log_f);
                }
            }
            if (refund_count > 0) { // 实际退款次数大于0, 才更新订单状态
                // 更新订单状态
                Order order = orderService.findById(refundOrder.getOrderId());
                order.setRefundAmt(orderRefundAmount);
                refundOrder.setRefundAmount(orderRefundAmount);
                refundOrder.setRemain(Amount.sub(order.getTrxAmt(), orderRefundAmount));
                refundOrder.setLastRefundTime(new Date());
                if (total_count == refund_count + already_refund_count) { // 如果退款完成数和总的退款请求数相等, 表示退款完成
                    refundOrder.setRefundStatus(RefundStatus.SUCCESS);
                }
                try {
                    tradeService.refundSupply(order, refundOrder);
                    log.info("[SUPPLY_REFUND] 补退款单结束 " + log_out);
                } catch (YmfException e) {
                    log.error("[SUPPLY_REFUND] 补退款单异常" + log_out, e);
                }
            }
        }
        return ResponseMessage.ok();
    }

    @Override
    public void expireOrder(Date from, Date to) {
        List<Order> orderList = orderService.findExpireOrder(from, to);
        if (null != orderList) {
            DateFormat df = threadLocal.get();
            log.info(log_pre, "[EXPIRE_ORDER] 开始执行订单超时定时", df.format(from), df.format(to), orderList.size());
            for (Order order : orderList) {
                expireOrder(order);
            }
        }
    }

    @Override
    public ResponseMessage expireOrder(String customerNumber, String externalId) {
        Order order = orderService.findByCustomerAndExternalId(customerNumber, externalId);
        return expireOrder(order);
    }

    @Override
    public ResponseMessage expireOrder(Order order) {
        if (null == order) {
            return ResponseMessage.error("订单不能为空");
        }
        // 不等于init的订单不判断失效时间
        if (OrderStatus.INIT != order.getOrderStatus()) { // 外部调用 不记录日志
            return ResponseMessage.error("订单状态不是未支付");
        }
        String customerNumber = order.getCustomerNumber();
        String customerOrderId = order.getCustomerOrderId();
        List<Payment> paymentList = paymentService.queryPayments(customerNumber, order.getId(), TrxType.PURCHASE);
        if (null != paymentList) {
            int final_count = paymentList.size();
            List<Payment> expireList = new ArrayList<Payment>();
            log.info(order_start_log_pre, "[EXPIRE_ORDER] 开始判断订单支付记录", customerNumber, customerOrderId, final_count);
            for (int i = final_count - 1; i >= 0; i --) {
                Payment payment = paymentList.get(i);
                String yeepayOrderId = payment.getYeepayOrderId();
                // 所有的payment状态都是init 或者 process
                switch (payment.getPayStatus()) {
                    case SUCCESS:
                    case PROCESS:
                    case FAIL:
                        if (log.isDebugEnabled()) {
                            log.debug(payment_log_pre, "[EXPIRE_ORDER_PAYMENT] 状态不是INIT的支付记录不判断超时",
                                    customerNumber, customerOrderId, yeepayOrderId);
                        }
                        continue;
                    case INIT:
                        Date expireDate = payment.getExpireTime();
                        if (null != expireDate && expireDate.before(new Date())) { // 超时时间不是空 并且早于当前时间
                            payment.setPayStatus(PaymentStatus.FAIL);
                            log.info(payment_log_pre, "[EXPIRE_ORDER_PAYMENT] 判断支付记录已经超时",
                                    customerNumber, customerOrderId, yeepayOrderId);
                            expireList.add(paymentList.remove(i));
                        }
                        break;
                    default: // nonsense
                }
            }
            // 处理结果
            int failSize = paymentList.size(); // 已经是fail的
            int doSize = expireList.size(); // 变更状态的
            try {
                if (doSize > 0) { // 有payment状态变更
                    if (doSize + failSize == final_count) { // 所有支付记录都已超时
                        order.setOrderStatus(OrderStatus.FAIL);
                        tradeService.refreshOrderStatus(order, paymentList);
                    } else {
                        tradeService.refreshPaymentStatus(paymentList);
                    }
                    log.info(order_end_log_pre, "[EXPIRE_ORDER] 更新超时订单状态成功",
                            customerNumber, customerOrderId, doSize);
                } else {
                    if (failSize == final_count) { // 没有payment状态变更, 所有的payment也都fail了
                        order.setOrderStatus(OrderStatus.FAIL);
                        orderService.updateOrderStatusById(order);
                        log.info(order_log_pre, "[EXPIRE_ORDER] 更新超时订单状态成功", customerNumber, customerOrderId);
                    } else {
                        log.info(order_log_pre, "[EXPIRE_ORDER] 没有payment状态变更,但是有剩余payment,订单状态有误,需要排查",
                                customerNumber, customerOrderId);
                    }
                }
            } catch (Throwable t) {
                log.error("[EXPIRE_ORDER] 更新超时订单状态失败", t);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug(order_log_pre, "[EXPIRE_ORDER] 订单没有任何支付记录", customerNumber, customerOrderId);
            }
        }
        return ResponseMessage.ok();
    }

}


