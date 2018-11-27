package com.yeepay.g3.ymf.boss.query;

import com.yeepay.g3.utils.query.Query;

/**
 * Title: 下载用
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/14.
 */
public class DownloadQuery extends Query {

    /**
     * 下载模板名称
     */
    private String templateName;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
