package com.yeepay.g3.facade.ymf.facade;

/**
 * @Description: 订单相关定时
 * @Author: xiaobin.liu
 * @Date: 17/6/23
 * @Time: 上午10:23
 */
public interface OrderTimerFacade {
    /**
     * 超时订单关闭.对从订单处理器下单，已经超时订单发起支付结果查询，根据支付结果进行补单及关闭订单。
     * @param beforeDay         超时时间范围，与今天间隔。如：1  为跑昨日数据，如 0 为跑今日数据
     * @param retryCount        当前数据重跑次数,0:则只跑未跑过数据，1：则相当于第二次跑
     * @param recordCount       单次跑最大数据总数
     */
    void clossOrder(int beforeDay, int retryCount, int recordCount);

    void completeOrder();
}
