package com.yeepay.g3.ymf.boss.support;

import com.yeepay.g3.core.ymf.support.bean.DictionaryHolder;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.soa.context.ApplicationContextHelper;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * Created by fei.lu on 16/8/26.
 */
public class DicTag extends TagSupport {

    private static final Logger log = LoggerFactory.getLogger(DicTag.class);

    private static final long serialVersionUID = -1916297198282726496L;
    private String type; // 简单类名
    private String code;

    private DictionaryHolder fetchHolder() {
        return ApplicationContextHelper.getApplicationContext().getBean(DictionaryHolder.class);
    }

    public int doEndTag(){
        try {
            JspWriter writer = pageContext.getOut();
            if (StringUtils.isBlank(type) || StringUtils.isBlank(code)) {
                writer.write("");
            } else {
                String value = fetchHolder().getDictValue(type + code);
                if (StringUtils.isNotBlank(value)) {
                    writer.write(value);
                } else {
                    writer.write(type + code);
                }
            }
        } catch (Exception e) {
            log.info("字典数据写入name失败" + e.getMessage());
        }
        return EVAL_PAGE;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
