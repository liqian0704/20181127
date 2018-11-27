package com.yeepay.g3.ymf.pay.controller.ymfbill.billDto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 请求报文实体
 */
public class QueryParamDTO {

    /**
     * 商户编号
     */
    @NotBlank(message = "商户号验证失败，商户号不能为空")
    private String customerNumber;

    /**
     * 支付开始时间
     */
    @NotBlank(message = "支付开始时间验证失败，支付开始时间不能为空")
    private String paytimeStart;

    /**
     * 支付结束时间
     */
    @NotBlank(message = "支付结束时间验证失败，支付结束时间不能为空")
    private String paytimeEnd;

    /**
     * md5密文
     */
    @NotBlank(message = "md5密文验证失败，md5密文不能为空")
    private String sign;


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getPaytimeStart() {
        return paytimeStart;
    }

    public void setPaytimeStart(String paytimeStrat) {
        this.paytimeStart = paytimeStrat;
    }

    public String getPaytimeEnd() {
        return paytimeEnd;
    }

    public void setPaytimeEnd(String paytimeEnd) {
        this.paytimeEnd = paytimeEnd;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
