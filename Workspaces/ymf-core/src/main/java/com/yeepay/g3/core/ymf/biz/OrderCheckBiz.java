package com.yeepay.g3.core.ymf.biz;

/**
 * @Description: 订单校验
 * @Author: xiaobin.liu
 * @Date: 17/10/18
 * @Time: 下午5:09
 */
public interface OrderCheckBiz {

    /**
     * 商户当日S0订单笔数限制校验
     * @param cutomerNmuber
     * @return    true：校验通过;false:校验失败
     */
    boolean checkCustomerS0Limit(String cutomerNmuber);
}
