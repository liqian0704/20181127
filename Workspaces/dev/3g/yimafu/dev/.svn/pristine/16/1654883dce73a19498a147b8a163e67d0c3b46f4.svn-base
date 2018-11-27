package com.yeepay.g3.facade.ymf.dto.refund;

import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;

import java.io.Serializable;

/**
 * Title: 手动发起退款 请求参数
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public class RefundManualRequestDTO extends RefundRequestDTO implements Serializable {

    private static final long serialVersionUID = -4594593258501459510L;

    @NotEmpty("操作人")
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
