package com.yeepay.g3.ymf.boss.utils;/**
 * Created by jiwei.lv on 16/9/7.
 */

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.support.bean.DictionaryHolder;
import com.yeepay.g3.utils.soa.context.ApplicationContextHelper;

import java.util.List;

/**
 * @author jiwei.lv
 * @create 2016-09-16/9/7
 */
public class DictUtils {
    private String type;
    private String code;
    private DictionaryHolder fetchHolder() {
        return ApplicationContextHelper.getApplicationContext().getBean(DictionaryHolder.class);
    }
    public  String getDictValue(){
        String value=fetchHolder().getDictValue(type+code);

        return value;
    }

    public DictUtils(String type, String code) {
        this.type = type;
        this.code = code;
    }
}
