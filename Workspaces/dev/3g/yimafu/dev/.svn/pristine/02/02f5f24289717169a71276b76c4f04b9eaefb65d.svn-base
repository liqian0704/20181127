package com.yeepay.g3.ymf.boss.support.method;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.ymf.boss.support.annotations.DictBatchArgs;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 参数实体注入拦截器
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/13.
 */
public class DictBatchArgsResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(DictBatchArgs.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String type = webRequest.getParameter("type");
        List<Dictionary> argsList = new ArrayList<Dictionary>();
        // 表单提交的字段顺序是否保持不变?
        String[] codes = webRequest.getParameterValues("code");
        String[] names = webRequest.getParameterValues("name");
        if (codes.length != names.length) {
            throw new RuntimeException("参数有误");
        }
        int count = codes.length;
        for (int i = 0; i < count; i ++) {
            String code = codes[i];
            String name = names[i];
            Dictionary args = new Dictionary();
            args.setType(type);
            args.setCode(code);
            args.setName(name);
            args.setStatus(Status.ACTIVE);
            argsList.add(args);
        }
        return argsList;
    }
}
