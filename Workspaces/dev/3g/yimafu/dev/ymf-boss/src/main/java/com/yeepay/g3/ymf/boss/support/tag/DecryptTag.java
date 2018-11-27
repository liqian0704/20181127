package com.yeepay.g3.ymf.boss.support.tag;

import com.yeepay.g3.core.ymf.utils.security.SpaySignUtil;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Title: 枚举标签
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/11.
 */
public class DecryptTag extends TagSupport {

    private static final long serialVersionUID = 5306041918998051642L;
    private static final Logger log = LoggerFactory.getLogger(DecryptTag.class);

    private String data; // 简单类名

    public int doEndTag(){
        try {
            JspWriter out = pageContext.getOut();
            if (StringUtils.isBlank(data)) {
                out.write("");
            } else {
                out.write(SpaySignUtil.decryptAppSecret(data));
            }
        } catch (Exception e) {
            log.error("DecryptTag异常:" + e.getMessage()); // simple message
        }
        return EVAL_PAGE;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
