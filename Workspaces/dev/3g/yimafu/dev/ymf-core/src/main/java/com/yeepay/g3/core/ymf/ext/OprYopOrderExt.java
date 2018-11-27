package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.facade.opr.dto.QueryRefundResponseDTO;
import com.yeepay.g3.facade.opr.dto.ResponseRefundDTO;
import com.yeepay.g3.facade.opr.dto.yop.order.YopCreateOrderResDTO;
import com.yeepay.g3.facade.opr.dto.yop.order.YopQueryOrderResDTO;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

/**
 * @Description: 对接大算订单中心  目前只做单商户模式,代理商模式不做
 * @Author: xiaobin.liu
 * @Date: 17/1/4
 * @Time: 下午4:33
 */
public interface OprYopOrderExt {
    //大算  成功
    public static final String opr_ok = "OPR00000";

    /**
     * 订单处理器 下单
     * @param order
     * @param customer
     * @param payment
     * @return
     */
    YopCreateOrderResDTO createOrder(Order order, Customer customer, Payment payment) throws YmfTrxException;

    /**
     * 订单处理器 下单
     * @param order
     * @param customer
     * @param payment
     * @param selfFlag     是否同人支付,0:同人，他人：1
     * @return
     */
    YopCreateOrderResDTO createOrder(Order order, Customer customer, Payment payment, String selfFlag) throws YmfTrxException;

    /**
     * 查询订单接口。调用统一订单处理器查询订单信息
     *
     * @param customerNumber  商户编号
     * @param customerOrderId 商户订单号
     * @param uniqueOrderNo   统一订单处理器订单号
     * @return 订单信息
     */
    YopQueryOrderResDTO queryOrder( String customerNumber, String customerOrderId,
                                   String uniqueOrderNo) throws YmfTrxException;

    /**
     * 订单处理器退款接口
     * @param order
     * @return
     */
    ResponseRefundDTO oprRefund(Order order) throws YmfTrxException;

    /**
     * 退款查询接口
     * @param order
     * @return
     */
    QueryRefundResponseDTO oprRefundQuery(Order order) throws YmfTrxException;
}
