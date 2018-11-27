package com.yeepay.g3.core.ymf.utils.dateutils;

import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;

import java.util.Date;

/**
 * @Description: 超时时间计算工具类
 * @Author: xiaobin.liu
 * @Date: 17/3/1
 * @Time: 下午5:35
 */
public class ExpireTimeUtil {
    /**
     * 行业版订单默认有效期限。
     */
    public static final int DEFUALT_ORDER_PAY_EXPECT_TIME = 24 * 10 ;//单位小时 行业版10天
    /**
     * 标准版订单默认有效期限。
     */
    public static final int DEFUALT_STANDARD_EXPECT_TIME = 24 ;//单位小时

    /**
     * 订单失效时间。具体的日期时间点
     */
    public static Date expTime(Date createDate, BusinessType businessType, Integer validityPeriod) {
        long time = 0;
        if(BusinessType.ORDER_PAY.equals(businessType)){
            time = orderExpDate(BusinessType.ORDER_PAY, validityPeriod) ;
        } else if(BusinessType.REQUIRE_PAY.equals(businessType)){
            time = orderExpDate(BusinessType.REQUIRE_PAY, validityPeriod) ;
        }else{
            time = orderExpDate(BusinessType.STANDARD, validityPeriod) ;
        }
        return DateUtil.addMinuteToDate(createDate, (int) time) ;
    }

    /**
     * 订单失效时间。分钟为单位
     *
     * @param businessType 交易类型
     * @param validityPeriod 单位小时
     */
    public static long orderExpDate(BusinessType businessType,Integer validityPeriod) {
        //Integer validityPeriod = customer.getValidityPeriod();
        if (null != validityPeriod && validityPeriod > 0) {
            return validityPeriod * 60 ;
        }
        if (BusinessType.ORDER_PAY.equals(businessType)
                || BusinessType.REQUIRE_PAY.equals(businessType)) {
            return DEFUALT_ORDER_PAY_EXPECT_TIME * 60 ;
        } else {
            return DEFUALT_STANDARD_EXPECT_TIME * 60 ;
        }
    }
}
