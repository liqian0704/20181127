package com.yeepay.g3.core.ymf.biz.trade;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.exception.YmfException;

import java.util.Date;

/**
 * 交易退款补单
 * Created by aoick on 2016/8/28.
 */
public interface SupplyBiz {

    /**
     * 定时交易补单
     *
     * @param from 开始时间
     * @param to 结束时间
     */
    void supplyOrder(Date from, Date to);

    /**
     * 交易补单
     *
     * @param customerNumber 商户编号
     * @param externalId externalId
     * @return 补单结果
     */
    ResponseMessage supplyOrder(String customerNumber, String externalId);

    /**
     * 交易补单
     *
     * @param order 订单
     * @return 补单结果
     */
    ResponseMessage supplyOrder(Order order);

    /**
     * 根据业务订单号进行补单
     * @param externalId
     * @return
     */
    ResponseMessage supplyOrderByExernalId(String externalId);

    /**
     * 单个订单补单
     * @param id
     * @return
     * @throws YmfException
     * @throws ParameterInvalidException
     */
    ResponseMessage supplyOrderById(Long id) throws YmfException, ParameterInvalidException;

    /**
     * 定时退款补状态
     *
     * @param from 开始时间
     * @param to 结束时间
     */
    void supplyRefund(Date from, Date to);

    /**
     * 手工根据商编和易码付订单号发起退款
     * @param orderId 订单编号
     * @return
     */
    ResponseMessage supplyRefund(Long orderId);

    /**
     * 退款补状态
     *
     * @param refundOrder 退款订单
     * @return 补单结果
     */
    ResponseMessage supplyRefund(RefundOrder refundOrder);

    /**
     * 定时清理过期订单
     *
     * @param from 开始时间
     * @param to 结束时间
     */
    void expireOrder(Date from, Date to);

    /**
     * 清理过期订单
     *
     * @param customerNumber 商户编号
     * @param externalId externalId
     * @return 补单结果
     */
    ResponseMessage expireOrder(String customerNumber, String externalId);

    /**
     * 清理过期订单
     *
     * @param order 订单
     * @return 补单结果
     */
    ResponseMessage expireOrder(Order order);
}
