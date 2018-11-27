package com.yeepay.g3.ymf.boss.support;

import com.yeepay.g3.facade.ymf.enumtype.CustomerLevel;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: 枚举标签
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/11.
 */
public class EnumTag extends TagSupport {

    private static final Logger log = LoggerFactory.getLogger(EnumTag.class);
    private static final long serialVersionUID = 5306041918998051642L;

    private String type; // 简单类名
    private String name; // 枚举名
    private String display; // 显示的属性名 如果要自定义的话

    private static final Map<String, Class<?>> enumMap = new HashMap<String, Class<?>>();

    static {
        enumMap.put("status", Status.class);
        enumMap.put("customerLevel", CustomerLevel.class);
        enumMap.put("operateType", OperateType.class);
        enumMap.put("optType", OperateType.class);
    }

    @SuppressWarnings("unchecked")
    public int doEndTag(){
        try {

            JspWriter out = pageContext.getOut();
            if (StringUtils.isBlank(name)) {
                out.write("");
                return EVAL_PAGE;//处理标签后，继续处理JSP后面的内容
            }
            if (StringUtils.isBlank(type)) {
                out.write("");
                return EVAL_PAGE;
            }
            // 写对应的枚举值
            Class<Enum> enumClazz = (Class<Enum>) enumMap.get(type);
            if (null == enumClazz) {
                enumClazz = (Class<Enum>) Class.forName(type);
            }
            if (null != enumClazz) {
                Enum e = Enum.valueOf(enumClazz, name);
                Field field;
                if (StringUtils.isBlank(display)) {
                    field = enumClazz.getDeclaredField("displayName");
                } else {
                    field = enumClazz.getDeclaredField(display);
                }
                if (null != field) {
                    field.setAccessible(true);
                    String value = (String) field.get(e);
                    out.write(value);
                }
            } else {
                out.write("NULL");
            }
        } catch (Exception e) {
            log.error("EnumTag异常:" + e.getMessage()); // simple message
        }
        return EVAL_PAGE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
