package com.yeepay.g3.ymf.boss.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/13.
 */
public class TransferUtils {

    /**
     * 把实体o转换成clazz对应的实体
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T transform(Object obj, Class<T> clazz) {
        try {
            T entity = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Field[] objFields = obj.getClass().getDeclaredFields();
            for (int i = 0, lenth = fields.length; i < lenth; i ++) {
                Field setField = fields[i];
                Field getField = objFields[i];
                setField.setAccessible(true);
                getField.setAccessible(true);
                // transfer
                Object value = getField.get(obj);
                setField.set(entity, value);
                setField.setAccessible(false);
                getField.setAccessible(false);
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把from实体的属性赋值给to的同名属性, null属性不处理
     * @param from
     * @param to
     * @return
     */
    public static void copyProperties(Object from, Object to) {
        try {
            Field[] fromFields = from.getClass().getDeclaredFields();
            Field[] toFields = to.getClass().getDeclaredFields();
            for (int i = 0, lenth = fromFields.length; i < lenth; i ++) {
                Field setField = toFields[i];
                if (Modifier.isFinal(setField.getModifiers())) {
                    // final 类型的不设置
                    continue;
                }
                Field getField = fromFields[i];
                setField.setAccessible(true);
                getField.setAccessible(true);
                // transfer
                Object value = getField.get(from);
                if (null != value) { // 只传递非空的值
                    setField.set(to, value);
                }
                setField.setAccessible(false);
                getField.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
