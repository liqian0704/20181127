package com.yeepay.g3.core.laike.service;

/**
 * Description: S0相关服务
 * Author: wei.li
 * Date: 17/4/26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface SettleS0Service {

    /**
     * 开通S0
     *
     * @param merchantNo
     * @return
     */
    void openS0(String merchantNo);

    /**
     * 设置RJT费率
     *
     * @param merchantNo
     * @return
     */
    void modifyRJTfee(String merchantNo);

    /**
     * 通知ymf系统开通s0服务
     *
     * @param merchantNo
     * @return
     */
    boolean notifyYMF(String merchantNo);
}
