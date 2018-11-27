package com.yeepay.g3.common.laike.utils;


import com.yeepay.g3.utils.common.encrypt.AES;

/**
 * Description: 联盟工具类
 * Author: wei.li
 * Date: 17/7/26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AllianceUtils {


    /**
     * 加密(联盟用)
     *
     * @param data
     * @return
     */
    public static String encryptInfo(String data) {
        return AES.encryptToBase64(data, Constants.KEY);
    }

    /**
     * 解密(联盟用)
     *
     * @param data
     * @return
     */
    public static String decryptInfo(String data) {
        return AES.decryptFromBase64(data, Constants.KEY);
    }
}
