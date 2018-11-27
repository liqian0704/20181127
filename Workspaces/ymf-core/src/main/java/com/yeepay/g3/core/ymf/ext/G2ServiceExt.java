package com.yeepay.g3.core.ymf.ext;

import com.yeepay.app.httpinvoke.online.dto.CustomerBasicInfoDTO;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/1/17
 * @Time: 下午4:07
 */
public interface G2ServiceExt {
    CustomerBasicInfoDTO queryCustomerBasicInfo(String customerNumber);

    String queryCustomerHmacKey(String customerNumber);
}
