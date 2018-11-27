package com.yeepay.g3.ymf.boss.support;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.support.bean.DictionaryHolder;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.soa.context.ApplicationContextHelper;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Formatter;
import java.util.List;

/**
 * 数据字典下拉select
 * Created by chen.liu on 16/8/26.
 */
public class DicSelectTag extends TagSupport {

    private static final long serialVersionUID = 6584600670115210289L;
    private String type; // 字典type
    private String code; // 需要选中的
    private String clazz; // 样式
    private String name; // name

    private static final Logger log = LoggerFactory.getLogger(DicSelectTag.class);

    private DictionaryHolder fetchHolder() {
        return ApplicationContextHelper.getApplicationContext().getBean(DictionaryHolder.class);
    }

    @Override
    public int doEndTag(){
        try {
            JspWriter writer = pageContext.getOut();
            if (StringUtils.isBlank(type)) {
                writer.write("");
            } else {
                List<Dictionary> typeList = fetchHolder().getSelection(type);
                Formatter formatter = new Formatter();
                if (null != typeList) {
                    formatter.format("<select id='%s' name='%s' class='%s'>\r\n", id, name, clazz);
                    formatter.format("<option value=''>全部</option>");
                    for (Dictionary dictionary : typeList) {
                        String code = dictionary.getCode();
                        String name = dictionary.getName();
                        if (code.equals(this.code)) { // 被选中的
                            formatter.format("<option value='%s' selected>%s</option>", code, name);
                        } else {
                            formatter.format("<option value='%s'>%s</option>", code, name);
                        }
                    }
                    formatter.format("</select>");
                }
                writer.write(formatter.toString());
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

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
