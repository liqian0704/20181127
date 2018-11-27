package com.yeepay.g3.core.ymf.support;

import com.yeepay.g3.core.ymf.utils.common.Amount;

import java.math.BigDecimal;

/**
 * Title: 累计
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/9/12.
 */
public class TradeContext {

    // init
    public TradeContext() {
        this.amount = BigDecimal.ZERO;
        this.fee = BigDecimal.ZERO;
    }

    /**
     * 累加
     * @param context
     */
    public void add(TradeContext context) {
        add(context.getAmount(), context.getFee());
    }

    /**
     * 累加
     * @param amount
     * @param fee
     */
    public void add(BigDecimal amount, BigDecimal fee) {
        if (null != amount) {
            this.amount = Amount.add(this.amount, amount);
        }
        if (null != fee) {
            this.fee = Amount.add(this.fee, fee);
        }
    }

    /**
     * 金额累计
     */
    private BigDecimal amount;

    /**
     * 手续费累计
     */
    private BigDecimal fee;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
