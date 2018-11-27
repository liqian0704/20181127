package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.facade.laike.dto.RiskQueryRequest;
import com.yeepay.g3.facade.laike.dto.RiskQueryResponse;

/**
 * Created by dongxulu on 17/3/3.
 * 获取来客风控参数接口
 */
public interface LaikeRiskExt {

    /**
     * 获取风控参数
     * @param request
     * @return
     */
    RiskQueryResponse getRiskParam(RiskQueryRequest request);

}
