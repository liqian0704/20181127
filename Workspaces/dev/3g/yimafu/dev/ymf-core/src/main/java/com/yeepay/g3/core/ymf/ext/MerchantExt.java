package com.yeepay.g3.core.ymf.ext;

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
}
