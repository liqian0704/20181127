package com.yeepay.g3.ymf.boss.utils;/**
 * Created by jiwei.lv on 16/8/23.
 */

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/23
 */
public class Format {

    private String  fieldName;
    private String  field;
    private  String  format;
    private String fieldType;

    public Format() {
    }

    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }


}
