package com.yeepay.g3.facade.laike.dto;

/**
 * Description:  贷款服务请求
 * Author: wei.li
 * Date: 17/3/29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class CreditRequset extends BaseRequest {

    /**
     * ip地址
     */
    private String ip;

    /**
     * 通道ID
     */
    private String channelId;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
