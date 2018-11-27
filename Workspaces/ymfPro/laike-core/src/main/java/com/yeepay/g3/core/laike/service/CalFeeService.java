package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.facade.laike.dto.CalFeeInfo;
import com.yeepay.g3.facade.laike.enumtype.InviteType;

import java.util.List;

/**
 * Description: 计费服务层
 * Author: wei.li
 * Date: 17/7/26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface CalFeeService {

    List<CalFeeInfo> findCalFeeModel(String merchantNo, InviteType inviteType);
}
