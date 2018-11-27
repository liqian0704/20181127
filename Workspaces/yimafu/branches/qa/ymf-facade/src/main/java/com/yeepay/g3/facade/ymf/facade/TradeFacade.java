package com.yeepay.g3.facade.ymf.facade;

import java.util.Date;

/**
 * Title: 交易相关接口
 * Description: 下单 退款 补单
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/22.
 */
public interface TradeFacade {

    /**
     * 补单今日定时
     *
     */
    void supplyTodayOrder();

    /**
     * 补单昨日
     */
    void supplyYesterdayOrder();

    /**
     * 补单前天(if needed?)
     */
    void supplyBeforeOrder();

    /**
     * 按指定时间补单
     * @param from 开始时间
     * @param to 结束时间
     */
    void supplyOrder(Date from, Date to);

    /**
     * 按指定条件补单
     * @param customerNumber 商户编号
     * @param externalId 商户订单号
     */
    void supplyOrder(String customerNumber, String externalId);

    /**
     * 补单退款
     */
    void supplyRefund();

    /**
     * 按指定时间补退款单
     * @param from 开始时间
     * @param to 结束时间
     */
    void supplyRefund(Date from, Date to);

    /**
     * 按指定条件补退款单
     * @param orderId 订单主键
     */
    void supplyRefund(Long orderId);

    /**
     * 按指定时间补退款单
     * @param from 开始时间
     */
    void supplyRefund(Date from);

    /**
     * 订单超时
     */
    void expireOrder();

    /**
     * 同步当日清算信息
     */
    void syncSettle();

    /**
     * 同步商户清算信息
     * @param trxDate
     */
    void syncSettle(Date trxDate);
}
