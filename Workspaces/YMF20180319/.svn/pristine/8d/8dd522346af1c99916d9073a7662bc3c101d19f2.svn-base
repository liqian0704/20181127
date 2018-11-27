package com.yeepay.g3.ymf.boss.utils;

import com.yeepay.g3.facade.laike.enumtype.FuncLevelEnum;
import com.yeepay.g3.utils.common.StringUtils;

/**
 * Description: app工具类
 * Author: wei.li
 * Date: 17/9/22
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AppUtils{

    private static String funLevel = "________";

    /**
     * 获取认证类型Map
     * @return
     */
    public static String convert2FunLevel(String funLevel){
        StringBuffer sb = new StringBuffer();
        for(FuncLevelEnum levelEnum : FuncLevelEnum.getValueList()){
            if (String.valueOf(funLevel.charAt(levelEnum.getValue())).equals("1")){
                sb.append(levelEnum.getBossDisplayName() + ";");
            }
        }
        return sb.toString();
    }

    public static String convert2FunQuery(String funLevelName){
        if (StringUtils.isNotBlank(funLevelName)){
            FuncLevelEnum levelEnum = FuncLevelEnum.valueOf(funLevelName);
            return funLevel.substring(0, levelEnum.getValue()) + "1" + funLevel.substring(levelEnum.getValue() + 1);
        }
        return null;
    }

}
