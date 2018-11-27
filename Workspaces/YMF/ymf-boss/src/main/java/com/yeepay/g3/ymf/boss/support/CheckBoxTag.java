package com.yeepay.g3.ymf.boss.support;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.support.bean.DictionaryHolder;
import com.yeepay.g3.utils.soa.context.ApplicationContextHelper;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.Formatter;
import java.util.List;

/**
 * Created by yp-tc-m-2762 on 16/9/3.
 */
public class CheckBoxTag extends TagSupport {
    private String type; // 字典type
    private String code; // 需要选中的
    private String clazz; // 样式
    private String name; // name

    private DictionaryHolder fetchHolder() {
        return ApplicationContextHelper.getApplicationContext().getBean(DictionaryHolder.class);
    }
    @SuppressWarnings("unchecked")
    public int doEndTag(){
        //
        try {
            List<Dictionary> typeList = fetchHolder().getSelection(type);
            Formatter formatter = new Formatter();
            if (null != typeList) {
                for (Dictionary dictionary : typeList) {
                    String code = dictionary.getCode();
                    String Dicname = dictionary.getName();
                    if (code.equals(this.code)) { // 被选中的
                        formatter.format("<input type='checkbox'  name='%s' value='%s' checked/>&nbsp;&nbsp;%s",name,code,Dicname);
                    } else {
                        formatter.format("<input type='checkbox'  name='%s' value='%s'/>&nbsp;&nbsp;%s",name,code,Dicname);
                    }
                }
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
