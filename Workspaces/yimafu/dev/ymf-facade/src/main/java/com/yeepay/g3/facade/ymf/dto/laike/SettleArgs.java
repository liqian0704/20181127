package com.yeepay.g3.facade.ymf.dto.laike;

import com.yeepay.g3.facade.ymf.dto.common.PageParam;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * Title: 到账查询RequestDTO
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/11/2.
 */
public class SettleArgs extends BaseArgs implements Serializable {

    private static final long serialVersionUID = 7221132039623956772L;
    // 参数
    @NotEmpty("商户编号")
    private String customerNumber;

    @NotNull("开始日期")
    private Date startDate;

    @NotNull("结束日期")
    private Date endDate;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
