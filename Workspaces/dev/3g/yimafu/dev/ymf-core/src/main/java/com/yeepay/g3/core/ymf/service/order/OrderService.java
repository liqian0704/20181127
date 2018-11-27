package com.yeepay.g3.core.ymf.service.order;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.order.*;

import java.util.Date;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/16.
 */
public interface OrderService {

    /**
     * 新增
     * @param order
     */
    int save(Order order);

    /**
     *
     * @param order
     * @return
     */
    int update(Order order);

    /**
     * 查询
     * @param params
     * @return
     */
    List<OrderDTO> queryOrders(OrderArgs params);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Order findById(Long id);
    
    /**
     * 查询补单
     * 订单'PayStatus'是'PROCESS'的订单
     * @param from 开始时间 包含
     * @param to 结束时间 不包含
     * @return
     */
    List<Order> findSupplyOrder(Date from, Date to);

    /**
     * 查询补单
     * 订单状态是init和process
     * @param from 开始时间 包含
     * @param to 结束时间 不包含
     * @return
     */
    List<Order> findExpireOrder(Date from, Date to);


    /**
     * 注意:只查订单版的订单
     * @Title:
     * @Description: 根据商编与商户订单号查询订单
     * @param customerNumber 商户编号
     * @param customerOrderId  商户订单号
     * @return Order
     * @throws
     */
    Order findOrderPayOrder(String customerNumber, String customerOrderId);

    /**
     *
     * @Title: findByQueryArgs
     * @Description: 根据商户编号\商户订单号\交易流水号获取订单
     * @param args 参数
     * @return OrderDTO
     * @throws
     */
    OrderDetailDTO findByQueryArgs(OrderQueryArgs args);

    /**
     * 根据参数查询订单
     * @param args 参数
     * @return
     */
    List<OrderDTO> queryOrderDTOByArgs(OrderArgs args);

    /**
     * 根据参数汇总
     * @param args 参数
     * @return
     */
    CountResponse countOrderByArgs(OrderArgs args);

	void updateOrderStatusById(Order order) ;

    /**
     *  产生图片路径
     * @return
     */
    String createProofPic(String orderId,String image);

    /**
     * 根据商户编号产生订单凭证
     * @param customerNumber
     * @return
     */
    List<OrderProofDTO> findByCustomer(String customerNumber);

    /**
     * 生成订单凭证
     * @param orderProofDTO
     */
    String createProofPicTime(List<OrderProofDTO> orderProofDTO) throws Exception;

    /**
     * 根据参考号查询订单信息
     * @param customerNumber    可以空,最好加上
     * @param externalId        交易参考号
     * @return
     */
    Order findByCustomerAndExternalId(String customerNumber,String externalId) ;

}
