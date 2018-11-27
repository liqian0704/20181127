package com.yeepay.g3.facade.ymf.dto.spi;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.OneOf;

import java.io.Serializable;
import java.util.Date;

/**
 * Title: COD异步接口请求类
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
public class CodNotifyArgs implements Serializable {

    private static final long serialVersionUID = -3086524126764703021L;

    @OneOf(value = "通知状态", type = Status.class)
    @NotEmpty("通知状态")
    private String status; // 通知状态

    @NotEmpty("开始时间")
    private Date startDate; // 开始时间

    @NotEmpty("结束时间")
    private Date endDate; // 结束时间

    @NotEmpty("商户订单号")
    private String externalId; // 商户订单号

    private String trxType; // 交易类型

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }
}
