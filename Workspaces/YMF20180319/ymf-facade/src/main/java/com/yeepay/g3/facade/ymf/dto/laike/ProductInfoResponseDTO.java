/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.ymf.dto.laike;
/**
 * 类名称: ProductInfoResponseDTO <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/8/30 下午4:52
 * @version: 1.0.0
 */

import com.yeepay.g3.facade.ymf.dto.common.BaseResponseDTO;

/**
 * Created by dongxulu on 17/8/30.
 */
public class ProductInfoResponseDTO extends BaseResponseDTO {
    /**
     * 商编号
     */
    private String customerNumber;
    /**
     * 支付产品
     */
    private String payTypeInfos;
    /**
     *业务方
     */
    private String business;

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getPayTypeInfos() {
        return payTypeInfos;
    }

    public void setPayTypeInfos(String payTypeInfos) {
        this.payTypeInfos = payTypeInfos;
    }
}