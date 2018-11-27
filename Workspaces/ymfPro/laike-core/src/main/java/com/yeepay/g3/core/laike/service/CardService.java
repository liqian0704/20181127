package com.yeepay.g3.core.laike.service;


import com.yeepay.g3.facade.laike.dto.BankCardInfo;

/**
 * Description: 卡账户及卡信息相关服务
 * Author: wei.li
 * Date: 17/4/24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface CardService {

    /**
     * 是否存在绑卡关系
     *
     * @param merchantNo
     * @param memberNo
     * @return
     */
    boolean hasBindCard(String merchantNo, String memberNo);

    /**
     * 根据卡号获取总行code
     *
     * @param bankCardNo
     * @return
     */
    BankCardInfo getBankCode(String bankCardNo);
}
