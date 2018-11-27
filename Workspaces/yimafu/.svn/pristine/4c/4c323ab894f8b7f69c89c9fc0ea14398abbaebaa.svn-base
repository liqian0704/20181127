package com.yeepay.g3.core.ymf.utils;

import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.validator.annotations.LengthValidator;
import com.yeepay.g3.facade.ymf.validator.annotations.Max;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.NotNull;
import com.yeepay.g3.facade.ymf.validator.annotations.OneOf;
import com.yeepay.g3.facade.ymf.validator.annotations.RegValidator;
import com.yeepay.g3.facade.ymf.validator.annotations.ValidatorRule;
import com.yeepay.g3.utils.common.ArrayUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Title: 参数校验
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/17.
 */
public class BeanValidator {

    private static final Logger log = LoggerFactory.getLogger(BeanValidator.class);

    /** 校验注解缓存 */
    private static final Map<Class<?>, Map<Field, List<Annotation>>> validatorCache = new HashMap<Class<?>, Map<Field, List<Annotation>>>();
    private static final Map<Class<?>, Map<String, List<Annotation>>> validatorNameCache = new HashMap<Class<?>, Map<String, List<Annotation>>>();

    /**
     * 校验 全字段校验
     * @param o 校验实体
     * @return resp
     */
    public static ResponseMessage validate(Object o) {
        if (null == o) {
            return ResponseMessage.error("校验实体不能为空");
        }
        Map<Field, List<Annotation>> classValiators = fetchClassValidators(o.getClass());
        if (null != classValiators) { // 如果实体注册了validator的注解
            try {
                for (Iterator<Map.Entry<Field, List<Annotation>>> it = classValiators.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<Field, List<Annotation>> entry = it.next();
                    Field field = entry.getKey();
                    List<Annotation> annotations = entry.getValue();
                    field.setAccessible(true);
                    Object value = field.get(o);
                    for (Annotation annotation : annotations) { // 从缓存中取出的标有注解的
                        ResponseMessage resp = validate(value, annotation);
                        if (!resp.isOk()) {
                            return resp;
                        }
                    }
                }
                return ResponseMessage.ok();
            } catch (Exception e) {
                log.error("参数校验执行异常", e);
                return ResponseMessage.error("系统异常");
            }
        } else {
            return ResponseMessage.ok();
        }
    }

    /**
     * 只校验提供的参数args
     * args的名称必须和o的属性名一致
     * @param o 参数实体
     * @param args 需要校验的字段
     * @return resp
     */
    public static ResponseMessage validateInclude(Object o, String...args) {
        if (null == o) {
            return ResponseMessage.error("校验实体不能为空");
        }
        Map<String, List<Annotation>> classValiators = fetchNameClassValidators(o.getClass());
        if (null != classValiators) { // 如果实体注册了validator的注解
            try {
                for (String arg : args) {
                    List<Annotation> annotations = classValiators.get(arg); // 从缓存中取出的标有注解的
                    if (null != annotations) {
                        // 反射获取Field
                        try {
                            Field field = o.getClass().getDeclaredField(arg);
                            field.setAccessible(true);
                            Object value = field.get(o);
                            for (Annotation annotation : annotations) {
                                ResponseMessage resp = validate(value, annotation);
                                if (!resp.isOk()) {
                                    return resp;
                                }
                            }
                        } catch (NoSuchFieldException nsfe) {
                            log.error("没有找到参数对应的属性, 参数名:" + arg);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("参数校验执行异常", e);
                return ResponseMessage.error("系统异常");
            }
        }
        return ResponseMessage.ok();
    }

    /**
     * 不校验提供的参数args
     * args的名称必须和o的属性名一致
     * @param o 参数实体
     * @param args 需要校验的字段
     * @return resp
     */
    public static ResponseMessage validateExclude(Object o, String...args) {
        if (null == o) {
            return ResponseMessage.error("校验实体不能为空");
        }
        Map<String, List<Annotation>> classValiators = fetchNameClassValidators(o.getClass());
        if (null != classValiators) { // 如果实体注册了validator的注解
            try {
                for (Iterator<Map.Entry<String, List<Annotation>>> it = classValiators.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, List<Annotation>> entry = it.next();
                    String fieldName  = entry.getKey();
                    if (!ArrayUtils.contains(args, fieldName)) { // 没找到才校验
                        List<Annotation> annotations = entry.getValue(); // 从缓存中取出的标有注解的
                        if (null != annotations) {
                            // 反射获取Field
                            Field field = o.getClass().getDeclaredField(fieldName);
                            field.setAccessible(true);
                            Object value = field.get(o);
                            for (Annotation annotation : annotations) {
                                ResponseMessage resp = validate(value, annotation);
                                if (!resp.isOk()) {
                                    return resp;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.error("参数校验执行异常", e);
                return ResponseMessage.error("系统异常");
            }
        }
        return ResponseMessage.ok();
    }

    /**
     * 校验
     * @param value 属性值
     * @param annotation 注解
     * @return resp
     */
    public static ResponseMessage validate(Object value, Annotation annotation) {
        Class annoClazz = annotation.annotationType();
        if (NotNull.class == annoClazz) { // 非空
            NotNull notNull = (NotNull) annotation;
            if (null == value) {
                return ResponseMessage.error(notNull.value() + notNull.msg());
            }
        } else if (NotEmpty.class == annoClazz) {
            NotEmpty notEmpty = (NotEmpty) annotation;
            if (null == value
                    || (String.class == value.getClass() && ((String) value).trim().equals(""))) {
                return ResponseMessage.error(notEmpty.value() + notEmpty.msg());
            }
        } else if (LengthValidator.class == annoClazz) {
            LengthValidator lengthValidator = (LengthValidator) annotation;
            if (null != value && String.class == value.getClass()) {
                String valueStr = (String) value;
                int length = lengthValidator.length();
                if (valueStr.length() > length) {
                    return ResponseMessage.error(lengthValidator.value() +
                            new Formatter().format(lengthValidator.msg(), length));
                }
            }
        } else if (RegValidator.class == annoClazz) {
            RegValidator regValidator = (RegValidator) annotation;
            if (null != value && StringUtils.isNotBlank(value.toString())) { // blank不校验
                String valueStr = value.toString();
                if (!valueStr.matches(regValidator.reg())) { // 正则校验不过
                    return ResponseMessage.error(regValidator.value() + regValidator.msg());
                }
            }
        } else if (Max.class == annoClazz) {
            Max max = (Max) annotation;
            if (null != value) {
                try {
                    int typeValue = Integer.class.cast(value);
                    if (typeValue > max.value()) {
                        return ResponseMessage.error(new Formatter().format(max.msg(), max.name(), max.value()).toString());
                    }
                } catch (ClassCastException e) {
                    return ResponseMessage.error(max.value() + "值类型不是int");
                }
            }
        } else if (OneOf.class == annoClazz) {
            OneOf oneOf = (OneOf) annotation;
            if (null != value) {
                if (oneOf.type() == Enum.class) { // 如果是Enum, 则读取group的值
                    String[] groups = oneOf.group();
                    if (!ArrayUtils.contains(groups, value)) { // 如果不包含
                        return ResponseMessage.error(new Formatter().format(oneOf.msg(), oneOf.value(),
                                Arrays.toString(groups)).toString());
                    }
                } else {
                    Class<? extends Enum> enumClass = oneOf.type();
                    Enum[] groups = enumClass.getEnumConstants();
                    if (!ArrayUtils.contains(groups, Enum.valueOf(enumClass, value.toString()))) { // 如果不包含
                        return ResponseMessage.error(new Formatter().format(oneOf.msg(), oneOf.value(),
                                Arrays.toString(groups)).toString());
                    }
                }
            }
        } else {
            // TODO 新的自定义校验注解
        }
        return ResponseMessage.ok();
    }

    /**
     *
     * 缓存类的属性注解 以属性为key
     * @param clazz
     * @return
     */
    private static Map<Field, List<Annotation>> fetchClassValidators(Class clazz) {
        Map<Field, List<Annotation>> classValidators = validatorCache.get(clazz);
        if (null == classValidators) {
            return checkInitialized(clazz);
        }
        return classValidators;
    }

    /**
     * 重载初始化方式, 降低锁粒度
     * @param clazz
     * @return
     */
    private synchronized static Map<Field, List<Annotation>> checkInitialized(Class clazz) {
        log.info("初始化校验类缓存, clazz: " + clazz.getCanonicalName());
        final Map<Field, List<Annotation>> classValidators = new HashMap<Field, List<Annotation>>();
        // 递归向上查找
        recursiveSuperClassFields(clazz, classValidators);
        if (0 == classValidators.size()) {
            return null;
        }
        validatorCache.put(clazz, classValidators);
        return classValidators;
    }

    /**
     * 缓存类的属性注解 以属性名为key
     * @param clazz
     * @return
     */
    private static Map<String, List<Annotation>> fetchNameClassValidators(Class clazz) {
        Map<String, List<Annotation>> classValidators = validatorNameCache.get(clazz);
        if (null == classValidators) {
            return checkNameInitialized(clazz);
        }
        validatorNameCache.put(clazz, classValidators);
        return classValidators;
    }

    /**
     * 重载初始化方式, 降低锁粒度
     * @param clazz
     * @return
     */
    private synchronized static Map<String, List<Annotation>> checkNameInitialized(Class clazz) {
        log.info("初始化校验类缓存, clazz: " + clazz.getCanonicalName());
        final Map<String, List<Annotation>> classValidators = new HashMap<String, List<Annotation>>();
        // 递归向上查找
        recursiveSuperClassNameFields(clazz, classValidators);
        if (0 == classValidators.size()) {
            return null;
        }
        validatorNameCache.put(clazz, classValidators);
        return classValidators;
    }

    /**
     * 递归向上查找父类和子类的 写了校验注解的 属性
     * @param clazz 子类
     * @param classValidators 缓存容器
     */
    private static void recursiveSuperClassFields(Class clazz, Map<Field, List<Annotation>> classValidators) {
        if (null != clazz && Object.class != clazz) { // 递归有效
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                if (null != annotations && annotations.length > 0) {
                    // 根据注解的标记注解来过滤
                    for (Annotation anno : annotations) {
                        if (null != anno.annotationType().getAnnotation(ValidatorRule.class)) {
                            if (null == classValidators.get(field)) {
                                classValidators.put(field, new LinkedList<Annotation>());
                            }
                            classValidators.get(field).add(anno);
                        }
                    }
                }
            }
            // up
            recursiveSuperClassFields(clazz.getSuperclass(), classValidators);
        }
        // 递归到顶
    }

    /**
     * 递归向上查找父类和子类的 写了校验注解的 属性
     * @param clazz 子类
     * @param classValidators 缓存容器
     */
    private static void recursiveSuperClassNameFields(Class clazz, Map<String, List<Annotation>> classValidators) {
        if (null != clazz && Object.class != clazz) { // 递归有效
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isFinal(field.getModifiers())) { // final类型的字段不会被校验
                    Annotation[] annotations = field.getDeclaredAnnotations();
                    if (null != annotations && annotations.length > 0) {
                        // 根据注解的标记注解来过滤
                        for (Annotation anno : annotations) {
                            if (null != anno.annotationType().getAnnotation(ValidatorRule.class)) {
                                String fieldName = field.getName();
                                if (null == classValidators.get(fieldName)) {
                                    classValidators.put(fieldName, new LinkedList<Annotation>());
                                }
                                classValidators.get(fieldName).add(anno);
                            }
                        }
                    }
                }
            }
            recursiveSuperClassNameFields(clazz.getSuperclass(), classValidators);
        }
        // 递归到顶
    }
}
