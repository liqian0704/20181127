package com.yeepay.g3.core.laike.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:参数检验工具类
 * Author: wei.li
 * Date: 17/4/17
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ValidateParamUtil {

    /**
     * 检查是否符合ip规则
     *
     * @param ip
     * @return
     */
    public static boolean isIp(String ip) {
        if (null != ip) {
            String ipReg = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";
            Pattern pattern = Pattern.compile(ipReg);
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        }
        return false;
    }
}
