package com.yeepay.g3.facade.ymf.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Title: 汇总返回
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/24.
 */
public class CountResponse extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -1802038540508719766L;

    /** 总笔数 */
    private Long count;

    /** 总金额 */
    private BigDecimal amount;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
