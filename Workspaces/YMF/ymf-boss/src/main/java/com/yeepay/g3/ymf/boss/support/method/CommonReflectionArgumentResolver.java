package com.yeepay.g3.ymf.boss.support.method;

import com.yeepay.g3.ymf.boss.support.annotations.CommonArgs;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Title: 参数实体注入拦截器
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/13.
 */
public class CommonReflectionArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CommonArgs.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> clazz = parameter.getParameterType();
        Object o = clazz.newInstance();
        // 反射赋值
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) { // 反射赋值
            if (!Modifier.isFinal(f.getModifiers())) { // none final
                String value = webRequest.getParameter(f.getName());
                if (null != value) {
                    f.setAccessible(true);
                    f.set(o, value);
                }
            }
        }
        return o;
    }
}
