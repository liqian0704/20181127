package com.yeepay.g3.core.ymf.ext.impl;

import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.ext.MerchantExt;
import com.yeepay.g3.facade.merchant_platform.dto.MerchantRelationRespDTO;
import com.yeepay.g3.facade.merchant_platform.dto.MerchantReqDTO;
import com.yeepay.g3.facade.merchant_platform.facade.MerchantFacade;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: 客户中心商户查询接口
 * @Author: xiaobin.liu
 * @Date: 17/3/15
 * @Time: 上午11:27
 */
@Service("merchantExt")
public class MerchantExtImpl implements MerchantExt {
    private static final Logger logger = LoggerFactory.getLogger(MerchantExtImpl.class);
    protected static final String Charset = "UTF-8";

    /**
     * 1.存量商户，查询不到商户关系，来客默认按照直销商户处理。当返回2009时
     * 2.新入网来客商户，能查询到商户关系，按照返回的实际商户关系处理。
     *
     * @param merchantNo    商户编号
     * @return
     * @throws YmfTrxException
     */
    @Override
    public String queryParentMerchentNo(String merchantNo) throws YmfTrxException {
        MerchantFacade facade = RemoteServiceFactory.getService(MerchantFacade.class);
        MerchantReqDTO merchantReqDTO = new MerchantReqDTO();
        merchantReqDTO.setSystem(CfgConstant.getMerSystem());
        merchantReqDTO.setUid(CfgConstant.getMerUUID());
        merchantReqDTO.setCharSet(Charset);
        merchantReqDTO.setMerchantNo(merchantNo);
        logger.info("开始调用 [客户中心父商编查询] ,请求参数：{}", JSONUtils.toJsonString(merchantReqDTO));
        MerchantRelationRespDTO merchantRelation = facade.getMerchantRelation(merchantReqDTO);
        logger.info("成功调用 [客户中心父商编查询] ,响应参数：{}", JSONUtils.toJsonString(merchantRelation));
        if ("0000".equals(merchantRelation.getRetCode())) {
            if ("ZXSH".equals(merchantRelation.getRole())) {
                //直销商户
                return merchantRelation.getMerchantNo();
            } else {
                return merchantRelation.getParentNo();
            }
        } else if ("2009".equals(merchantRelation.getRetCode())) {
            //2009	查询数据不存在  按照系统商版本处理
            return merchantNo;
        } else {
            //2005	商户不存在
            throw YmfTrxException.MER_CENTER_QUERY_ERROR;
        }
    }
}
