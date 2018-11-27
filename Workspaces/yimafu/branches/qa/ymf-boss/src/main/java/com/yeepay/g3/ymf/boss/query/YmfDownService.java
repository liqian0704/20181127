package com.yeepay.g3.ymf.boss.query;/**
 * Created by jiwei.lv on 16/8/22.
 */

import com.yeepay.g3.utils.query.QueryParam;
import com.yeepay.g3.utils.query.QueryResult;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/22
 */
public interface YmfDownService  {
    QueryResult query(String queryKey, QueryParam queryParam);
}
