package com.yeepay.g3.core.ymf.utils.reflect;

import com.yeepay.g3.utils.common.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Title: 反射
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/25.
 */
public class ReflectionUtils {

    /**
     * 递归向上查找属性
     * @param clazz 类
     * @param fieldName 属性名
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClazz = clazz.getSuperclass();
            if (null == superClazz || Object.class == superClazz) {
                throw new NoSuchFieldException(fieldName);
            }
            return getField(superClazz, fieldName);
        }
    }

    /**
     * 获取所有属性
     * @param clazz
     * @return
     */
    public static Field[] getAllFields(Class clazz) {
        Field[] fields = new Field[0];
        if (null == clazz) {
            return fields;
        }
        return getFields(clazz, fields);
    }

    /**
     * 获取所有属性
     * @param clazz
     * @param out_fields
     */
    private static Field[] getFields(Class clazz, Field[] out_fields) {
        Field[] fields = clazz.getDeclaredFields();
        out_fields = ArrayUtils.addAll(out_fields, fields);
        Class superclass = clazz.getSuperclass();
        if (null != superclass && Object.class != superclass) {
            getFields(superclass, out_fields);
        }
        return out_fields;
    }

    /**
     * @Title: setFieldValue
     * @Description: 赋值bean属性，目前只针对String后期可扩展
     * @param @param map
     * @param @param bean
     * @param @throws Exception
     * @return void
     * @throws
     */
    public static void setFieldValue(Map<String, Object> map, Object bean) throws Exception{
        Class<?> cls = bean.getClass();
        Field fields[] = cls.getDeclaredFields();
        for(Field field:fields){
            String fldSetName = field.getName();
            if (Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                // final与static跳过
                continue;
            }
            Object value = map.get(fldSetName);
            if(null != value){
                field.setAccessible(true);
                // 如果map内的value类型和bean的property不一致,则会异常
                // TODO 处理value类型转换
                field.set(bean,value);
            }

        }
    }
}
