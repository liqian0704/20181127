package com.yeepay.g3.core.ymf.biz.impl;

import com.yeepay.g3.core.ymf.biz.OrderCheckBiz;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Description: 订单校验
 * @Author: xiaobin.liu
 * @Date: 17/10/18
 * @Time: 下午5:13
 */
@Service
public class OrderCheckBizImpl implements OrderCheckBiz {
    @Autowired
    private OrderService orderService;
    /**
     * 商户当日S0订单笔数限制校验
     *
     * @param cutomerNmuber
     * @return true：校验通过;false:校验失败
     */
    @Override
    public boolean checkCustomerS0Limit(String cutomerNmuber) {
        //通过统一配置做一个简单的白名单
        Map<String, String> whiteList = CfgConstant.getCUSTOMER_S0_ORDER_LIMIT();
        if (whiteList.containsKey(cutomerNmuber)) {
            return true;
        }
        Date today = new Date();
        Date beginDate = DateUtil.getFirstOfDay(today);
        Date endDate = DateUtil.getLastOfDay(today);

        int orderCuunt = orderService.findCustomerS0OrderCounts(cutomerNmuber, beginDate, endDate);
        int limitCount = CfgConstant.getS0_ORDER_COUNT_LIMIT();
        if (orderCuunt > limitCount) {
            return false;
        }
        return true;
    }
}
