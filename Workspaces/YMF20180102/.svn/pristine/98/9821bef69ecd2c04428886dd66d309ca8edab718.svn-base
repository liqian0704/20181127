package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.facade.merchant_platform.dto.AreaRespDTO;
import com.yeepay.g3.facade.merchant_platform.dto.MerchantRespDTO;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

/**
 * @Description: 对接客户中心
 * @Author: xiaobin.liu
 * @Date: 17/3/15
 * @Time: 上午11:11
 */
public interface MerchantExt {

    /**
     * 查询父商编
     * @param merchantNo    商户编号
     * @return      父商户编号，如果是直销商户则返回他自己
     */
    public String queryParentMerchentNo(String merchantNo) throws YmfTrxException;

    /**
     * 查询商户基础信息接口
     * @param merchantNo    商户编号
     * @return
     * @throws YmfTrxException
     */
    MerchantRespDTO queryMerchant(String merchantNo) throws YmfTrxException;

    /**
     * 查询所有省市区信息
     * @return
     * @throws YmfTrxException
     */
    AreaRespDTO queryAllArea() throws YmfTrxException;
}
