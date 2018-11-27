package com.yeepay.g3.ymf.boss.controller.common;


import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.query.QueryParam;
import com.yeepay.g3.utils.query.QueryResult;
import com.yeepay.g3.utils.query.QueryService;
import com.yeepay.g3.ymf.boss.WebConstants;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 2017/6/26.
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("search")
public class SearchController extends ValidateController {
    @Autowired
    protected QueryService ymfDownloadQueryService;
    /**
     * 简单查询用
     * @param id queryId
     * @return
     */
    @RequestMapping("{id}")
    @ResponseBody
    public SearchResult search(@RequestParam("q") String q,@RequestParam(value = "page",required = false) String page,
                               @PathVariable String id) {
        QueryParam param = prepareParams(q,page);
        QueryResult result = ymfDownloadQueryService.query(id, param);
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) result.getData();
        List<Object> items = new LinkedList<Object>();
        if ("customerNumberSearch".equals(id)) {
            //特殊
            for (Map<String, Object> dataMap : dataList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", dataMap.get("id"));
                map.put("name", dataMap.get("name"));
                items.add(map);
            }
        } else {
            for (Map<String, Object> dataMap : dataList) {
                items.addAll(dataMap.values());
            }
        }
        return SearchResult.SearchResultBuilder.builder()
                .totalCount(result.getTotalCount())
                .items(items)
                .build();
    }

    private QueryParam prepareParams(String q,String pageStr) {
        QueryParam queryParam = new QueryParam();
        Integer page = 1;
        if (StringUtils.isNotEmpty(pageStr)) {
            page = Integer.valueOf(pageStr);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("q", q);
        queryParam.setParams(params);
        queryParam.setStartIndex((page - 1) * WebConstants.SEARCH_PAGE_COUNT + 1);
        queryParam.setMaxSize(WebConstants.SEARCH_PAGE_COUNT);
        queryParam.setCurrentPage(page);
        return queryParam;
    }

//    /**
//     * 特殊查询用
//     * @param param 查询参数
//     * @param id queryId
//     * @return
//     */
//    @RequestMapping("complex/{id}")
//    @ResponseBody
//    public SearchResult complexSearch(@SearchQuery QueryParam param, @PathVariable String id) {
//        QueryResult result = ymfDownloadQueryService.query(id, param);
//        List<Map<String, Object>> dataList = (List<Map<String, Object>>) result.getData();
//        List<Object> items = new LinkedList<Object>();
//        if ("complexCustomerNumberSearch".equals(id)) {
//            // unified
//            for (Map<String, Object> dataMap : dataList) {
//                RelationVo vo = new RelationVo();
//                vo.setCustomerNumber(StringUtils.notSafe(dataMap.get("customernumber")));
//                vo.setId(vo.getCustomerNumber());
//                vo.setParentCode(StringUtils.notSafe(dataMap.get("parentcode")));
//                vo.setLordCode(StringUtils.notSafe(dataMap.get("lordcode")));
//                vo.setMerType(MerType.safeOf(StringUtils.notSafe(dataMap.get("mertype"))));
//                items.add(vo);
//            }
//        } else { // for other complex search
//
//        }
//        return SearchResult.SearchResultBuilder.builder()
//                .totalCount(result.getTotalCount())
//                .items(items)
//                .build();
//    }

}
