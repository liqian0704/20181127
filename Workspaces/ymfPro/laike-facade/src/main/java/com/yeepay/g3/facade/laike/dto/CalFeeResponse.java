package com.yeepay.g3.facade.laike.dto;

import java.util.List;

/**
 * Description: 费率及限额响应DTO
 * Author: wei.li
 * Date: 17/8/21
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class CalFeeResponse extends BaseResponse {

    /**
     * 费率及限额信息列表
     */
    private List<CalFeeInfo> CalFeeInfoList;

    public List<CalFeeInfo> getCalFeeInfoList() {
        return CalFeeInfoList;
    }

    public void setCalFeeInfoList(List<CalFeeInfo> calFeeInfoList) {
        CalFeeInfoList = calFeeInfoList;
    }
}
