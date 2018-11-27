package com.yeepay.g3.core.ymf.biz.impl.trade;

import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.biz.trade.TradeBiz;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.service.refund.RefundService;
import com.yeepay.g3.core.ymf.service.trade.TradeService;
import com.yeepay.g3.core.ymf.support.EntityBuilder;
import com.yeepay.g3.core.ymf.support.NCApiSupport;
import com.yeepay.g3.core.ymf.utils.BeanValidator;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.facade.nctrade.dto.TradeRefundRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeRefundResponseDTO;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.facade.TradeBaseFacade;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderDTO;
import com.yeepay.g3.facade.ymf.dto.order.OrderDetailDTO;
import com.yeepay.g3.facade.ymf.dto.order.OrderQueryArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundDTO;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundRequestDTO;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Title: 交易流程
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
@Service
public class TradeBizImpl extends SoaBaseBiz implements TradeBiz {

    private static final Logger log = LoggerFactory.getLogger(TradeBizImpl.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RefundService refundService;
    @Autowired
    private TradeService tradeService;

    @Override
    public ResponseMessage queryOrder(OrderArgs args) {
        ResponseMessage resp = BeanValidator.validate(args);
        if (resp.isOk()) {
            // 是否需要汇总
            if (args.isCount()) {
                CountResponse count = orderService.countOrderByArgs(args);
                resp.setCount(count);
            }
            List<OrderDTO> orderDTOList = orderService.queryOrderDTOByArgs(args);
            return resp.setContent(orderDTOList);
        }
        return resp;
    }

    @Override
    public ResponseMessage querySingleOrder(OrderQueryArgs args) {
        ResponseMessage resp = BeanValidator.validate(args);
        if (resp.isOk()) {
            // 是否需要汇总
            OrderDetailDTO orderDetailDTO = orderService.findByQueryArgs(args);
            return resp.setContent(orderDetailDTO);
        }
        return resp;
    }

    @Override
    public ResponseMessage queryRefund(RefundOrderArgs args) {
        ResponseMessage resp = BeanValidator.validate(args);
        if (resp.isOk()) {
            if (args.isCount()) { // 是否需要汇总
                CountResponse count = refundService.countRefundByArgs(args);
                resp.setCount(count);
            }
            List<RefundDTO> refundDTOList = refundService.queryRefundDTOByArgs(args);
            return resp.setContent(refundDTOList);
        }
        return resp;
    }

    @Override
    public ResponseMessage refundOrder(RefundRequestDTO request) {
        ResponseMessage responseMessage = BeanValidator.validate(request);
        if (responseMessage.isOk()) {
            // 判断能否退款
            String customerNumber = request.getCustomerNumber();
            String externalId = request.getExternalId();
            String yeepayOrderId = request.getYeepayOrderId();
            String log_suffix = "商户编号: " + customerNumber + ", 交易订单号" + externalId
                    + ", 交易流水号: " + yeepayOrderId;
            log.info("开始进入退款流程..." + log_suffix);
            // 业务校验开始
            Order order = orderService.findByCustomerAndExternalId(customerNumber, externalId);
            if (null == order) {
                log.info("未查询到有效订单, " + log_suffix);
                return ResponseMessage.error("根据交易订单号未查询到有效订单");
            }
            Payment orderPayment = paymentService.findByYeepayOrderId(customerNumber, yeepayOrderId);
            if (null == orderPayment) {
                log.info("未查询到有效支付信息, " + log_suffix);
                return ResponseMessage.error("根据交易流水号未查询到有效支付信息");
            }
            if (PaymentStatus.SUCCESS != orderPayment.getPayStatus()) { // 支付状态不是succes的不能发起退款
                log.info("支付状态不是成功, 不能发起退款 " + log_suffix);
                return ResponseMessage.error("未成功的订单不能发起退款");
            }
            // 业务校验结束
            // 判断是否有退款记录
            RefundOrder refundOrder = refundService.queryByOrderId(order.getId());
            BigDecimal refundAmount = request.getRefundAmount();
            if (null == refundOrder) {
                // 新建退款主表
                refundOrder = EntityBuilder.buildRefundOrder(order);
                refundOrder.setRefundTimes(1);
                refundOrder.setRefundAmount(refundAmount);
                refundOrder.setCreateTime(new Date());
            } else {
                // 判断退款金额是否大于可退金额
                BigDecimal remain = refundOrder.getRemain();
                if (Amount.bigger(refundAmount, remain)) {
                    log.info("申请的退款金额大于可退金额, " + log_suffix);
                    return ResponseMessage.error("申请的退款金额" + refundAmount + "大于可退金额" + remain);
                }
                // 累计退款金额 更新退款主表
                refundOrder.setRefundTimes(refundOrder.getRefundTimes() + 1);
                refundOrder.setRefundAmount(Amount.add(refundOrder.getRefundAmount(), refundAmount));
                refundOrder.setRemain(Amount.sub(refundOrder.getTrxAmount(), refundOrder.getRefundAmount()));
                refundOrder.setLastRefundTime(new Date());
            }
            // 新建退款详情
            RefundOrderDetail orderDetail = EntityBuilder.buildRefundOrderDetail(orderPayment, refundOrder, request);
            // 新建退款payment
            Payment refundPayment = EntityBuilder.buildRefundPayment(orderDetail, orderPayment);
            // 持久化
            try {
                tradeService.refundOrder(refundPayment, refundOrder, orderDetail);
                log.info("退款持久化完成..." + log_suffix);
                // 调用发起退款的接口
                TradeRefundRequestDTO apiRequest = NCApiSupport.buildRefundRequest(refundPayment, orderDetail);
                TradeBaseFacade tradeBaseFacade = getService(TradeBaseFacade.class);
                log.info("准备发起退款接口..." + log_suffix);
                TradeRefundResponseDTO resp = tradeBaseFacade.refundRequest(apiRequest);
                if (null != resp) {
                    if (resp.isAccept()) { // 已受理 计算可退金额
                        refundOrder.setRemain(Amount.sub(refundOrder.getRemain(), refundAmount));
                        orderDetail.setRefundRequestId(resp.getBizRefundNo());
                        orderDetail.setRefundStatus(RefundStatus.PROCESS);
                        refundOrder.setRefundStatus(RefundStatus.PROCESS);
                        refundPayment.setPayStatus(PaymentStatus.PROCESS);
                    } else {
                        orderDetail.setReasonCode(resp.getErrorCode());
                        orderDetail.setReasonMsg(resp.getErrorMsg());
                        orderDetail.setRefundStatus(RefundStatus.FAIL);
                        refundOrder.setRefundStatus(RefundStatus.FAIL);
                        refundPayment.setPayStatus(PaymentStatus.FAIL);
                    }
                    // 更新退款状态
                    tradeService.refreshAcceptRefund(refundPayment, refundOrder, orderDetail);
                }
                log.info("退款申请处理完毕..." + log_suffix);
            } catch (ParameterInvalidException e) {
                log.error("调用统一收银台退款接口, 请求参数校验失败", e);
                return ResponseMessage.error("调用统一收银台退款接口, 请求参数校验失败", e);
            }  catch (YmfException ymfE) {
                log.error("发起订单退款失败", ymfE);
                return ResponseMessage.error("发起订单退款失败", ymfE);
            } catch (Exception e) {
                log.error("发起统一收银台退款接口失败", e);
                return ResponseMessage.error("发起统一收银台退款接口失败", e);
            }
            return ResponseMessage.ok();
        }
        return responseMessage;
    }

}
