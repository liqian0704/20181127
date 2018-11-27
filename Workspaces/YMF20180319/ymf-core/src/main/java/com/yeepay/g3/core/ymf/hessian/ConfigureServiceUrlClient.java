package com.yeepay.g3.core.ymf.hessian;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.utils.common.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
public class ConfigureServiceUrlClient implements FactoryBean<String> {

    private String key;

    @Override
    public String getObject() throws Exception {
        if (StringUtils.isNotBlank(key)) {
            return ConfigureSetting.getValue(key, String.class);
        } else {
            return ConfigureSetting.getValue(Constants.YMF_COD_HESSIAN_URL, String.class);
        }
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
