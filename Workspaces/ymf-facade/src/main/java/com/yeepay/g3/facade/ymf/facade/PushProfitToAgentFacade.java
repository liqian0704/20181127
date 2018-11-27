package com.yeepay.g3.facade.ymf.facade;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 2018/1/19
 * @time: 下午4:10
 */
public interface PushProfitToAgentFacade {
    /**
     * 推送上月毛利计算结果
     */
    void pushLastMonth();

    /**
     * 推送指定月份毛利计算结果
     * @param month
     */
    void pushWithMoth(String month);
}
