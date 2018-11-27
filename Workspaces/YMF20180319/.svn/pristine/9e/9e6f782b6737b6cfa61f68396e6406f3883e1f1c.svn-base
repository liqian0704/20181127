package com.yeepay.g3.facade.ymf.dto.laike;

import com.yeepay.g3.facade.ymf.validator.annotations.Max;
import com.yeepay.g3.facade.ymf.validator.annotations.NotNull;

/**
 * Title: 来客facade请求对象基类
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/11/4.
 */
public class BaseArgs {

    private String requestNo;

    private String versionId;

    private String telNo;

    @NotNull("页码")
    private Integer start;

    @NotNull("页长")
    @Max(name = "页长", value = 1000)
    private Integer page = 20;

    private Integer end;

    private String orderBy;

    // 是否汇总
    private boolean count;
    // 是否下载
    private boolean download;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getEnd() {
        this.end = start + page - 1;
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
