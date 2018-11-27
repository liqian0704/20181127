package com.yeepay.g3.ymf.boss.support;

import com.yeepay.g3.core.ymf.support.bean.DictionaryHolder;
import com.yeepay.g3.utils.soa.context.ApplicationContextHelper;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.Formatter;
import java.util.Set;

/**
 * 数据字典下拉select
 * Created by chen.liu on 16/8/26.
 */
public class DicTypeSelectTag extends TagSupport {
    private String type; // 需要选中的字典type
    private String clazz; // 样式
    private String name; // name

    private DictionaryHolder fetchHolder() {
        return ApplicationContextHelper.getApplicationContext().getBean(DictionaryHolder.class);
    }

    public int doEndTag(){
        try {
            Set<String> typeList = fetchHolder().getKeySet();
            Formatter formatter = new Formatter();
            if (null != typeList) {
                formatter.format("<select id='%s' name='%s' class='%s'>\r\n", id, name, clazz);
                formatter.format("<option value=''>全部</option>");
                for (String key : typeList) {
                    if (key.equals(this.type)) { // 被选中的
                        formatter.format("<option value='%s' selected>%s</option>", key, key);
                    } else {
                        formatter.format("<option value='%s'>%s</option>", key, key);
                    }
                }
                formatter.format("</select>");
            }
            pageContext.getOut().write(formatter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
