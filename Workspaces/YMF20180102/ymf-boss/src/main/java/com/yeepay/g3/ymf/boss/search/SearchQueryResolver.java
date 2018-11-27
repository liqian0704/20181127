package com.yeepay.g3.ymf.boss.search;

import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.query.QueryParam;
import com.yeepay.g3.ymf.boss.WebConstants;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 2017/6/26.
 */
public class SearchQueryResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SearchQuery.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        QueryParam queryParam = new QueryParam();
        String q = webRequest.getParameter("q");
        String merType = webRequest.getParameter("merType");
        String pageStr = webRequest.getParameter("page");
        Integer page = 1;
        if (StringUtils.isNotEmpty(pageStr)) {
            page = Integer.valueOf(pageStr);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("q", q);
        params.put("merType", merType);
        queryParam.setParams(params);
        queryParam.setStartIndex((page - 1) * WebConstants.SEARCH_PAGE_COUNT + 1);
        queryParam.setMaxSize(WebConstants.SEARCH_PAGE_COUNT);
        queryParam.setCurrentPage(page);
        return queryParam;
    }
}
