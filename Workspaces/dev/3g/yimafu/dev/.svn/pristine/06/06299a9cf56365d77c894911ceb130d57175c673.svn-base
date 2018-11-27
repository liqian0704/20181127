package com.yeepay.g3.facade.ymf.dto.common;

import com.yeepay.g3.facade.ymf.validator.annotations.NotNull;
import com.yeepay.g3.facade.ymf.validator.annotations.Max;

import java.io.Serializable;

/**
 * Title: 分页信息
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/16.
 */
public class PageParam extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -3103648346510852032L;

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
}
