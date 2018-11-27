package com.yeepay.g3.core.ymf.ext.impl;

import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.ext.LaikeRiskExt;
import com.yeepay.g3.facade.laike.dto.RiskQueryRequest;
import com.yeepay.g3.facade.laike.dto.RiskQueryResponse;
import com.yeepay.g3.facade.laike.facade.LikerRiskParamFacade;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.springframework.stereotype.Service;

/**
 * Created by dongxulu on 17/3/3.
 */
@Service("laikeRiskExt")
public class LaikeRiskExtImpl extends SoaBaseBiz implements LaikeRiskExt {
    private LikerRiskParamFacade likerRiskParamFacade;
    @Override
    public RiskQueryResponse getRiskParam(RiskQueryRequest request) {
        likerRiskParamFacade = getService(RemotingProtocol.HESSIAN,LikerRiskParamFacade.class);
        return likerRiskParamFacade.queryNCPayRiskParam(request);
    }
}
