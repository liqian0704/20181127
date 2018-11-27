package com.yeepay.g3.ymf.boss.utils;

import com.yeepay.g3.facade.ymf.enumtype.CustomerLevel;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.ymf.boss.support.EnumTag;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/12.
 */
public class EnumUtils {

    private static final Map<String, Class<?>> enumMap = new HashMap<String, Class<?>>();

    static {
        enumMap.put("status", Status.class);
        enumMap.put("customerLevel", CustomerLevel.class);
        enumMap.put("operateType", OperateType.class);
    }

    /**
     * 根据枚举值获取枚举的displayName
     * @param params 最多3个参数 [type][name][display]
     * @return displayName
     *
     * @see EnumTag#doEndTag()
     */
    @SuppressWarnings("unchecked")
    public static String displayOfEnum(String...params) {
        if (params.length <= 1) {
            return "";
        }
        String type = null;
        String name = null;
        String display = null;
        if (params.length == 2) {
            type = params[0];
            name = params[1];
        } else {
            type = params[0];
            name = params[1];
            display = params[2];
        }
        try {
            Class<Enum> enumClazz;
            if (StringUtils.isBlank(name)) {
                return name;
            }
            if (StringUtils.isBlank(type)) {
                return name;
            }
            // 优先从map中取, 取不到按照类名来反射
            enumClazz = (Class<Enum>) enumMap.get(type);
            if (null == enumClazz) {
                enumClazz = (Class<Enum>) Class.forName(type);
            }
            if (null != enumClazz) {
                Enum e = Enum.valueOf(enumClazz, name);
                Field field;
                if (StringUtils.isBlank(display)) {
                    field = enumClazz.getDeclaredField("displayName");
                } else {
                    field = enumClazz.getDeclaredField(display);
                }
                if (null != field) {
                    field.setAccessible(true);
                    String value = (String) field.get(e);
                    field.setAccessible(false);
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NULL";
    }
}
