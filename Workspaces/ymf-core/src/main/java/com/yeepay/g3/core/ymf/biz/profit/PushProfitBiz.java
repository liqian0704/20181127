package com.yeepay.g3.core.ymf.biz.profit;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 18/1/9
 * @time: 下午6:16
 */
public interface PushProfitBiz {
    /**
     * 数据推送到代理商
     * @param month     月份
     */
    void pushToAgent(String month);
}
