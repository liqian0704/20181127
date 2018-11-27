package com.yeepay.g3.core.ymf.biz;

import java.util.Date;

/**
 * Created by yp-tc-m-2762 on 16/10/27.
 */
public interface CustomerSettleInfo2gBiz {
    /**
     * 定时同步商户结算记录
     * @param trxDate 日期
     */
    void customerSettleInfo(Date trxDate);

    /**
     * 定时同步商户结算记录
     * @param customerNumber 商户编号
     * @param trxDate 日期
     */
    void customerSettleInfo(String customerNumber, Date trxDate);
}
