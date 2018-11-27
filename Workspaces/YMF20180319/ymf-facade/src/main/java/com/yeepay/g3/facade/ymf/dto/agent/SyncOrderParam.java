package com.yeepay.g3.facade.ymf.dto.agent;

import com.yeepay.g3.facade.ymf.dto.common.PageParam;
import com.yeepay.g3.facade.ymf.enumtype.common.AppType;
import com.yeepay.g3.facade.ymf.validator.annotations.NotNull;

import java.util.Date;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 2017/8/21.
 */
public class SyncOrderParam extends PageParam {

    private static final long serialVersionUID = 1072432202035067403L;

    @NotNull("开始时间")
    private Date from;

    @NotNull("结束时间")
    private Date to;

    //@NotNull("同步来源")
    private AppType appType;

    /// 商户编号
    private List<String> customerNumbers;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    public List<String> getCustomerNumbers() {
        return customerNumbers;
    }

    public void setCustomerNumbers(List<String> customerNumbers) {
        this.customerNumbers = customerNumbers;
    }
}
