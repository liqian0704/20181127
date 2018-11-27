package com.yeepay.g3.core.ymf.junit;

import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import org.junit.Test;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/2.
 */
public class AESPwdTest {

    @Test
    public void encrypt() {
        String key = "I am a fool, OK?";
        String pan = AES.encryptToBase64("h8B3iVwp2", key);
        System.out.println(pan);
    }

    @Test
    public void decrypt() {
        String key = "I am a fool, OK?";
        String pwd = "mWekChrhA6w7FqG/gKE2Qg==";
        String value = AES.decryptFromBase64(pwd, key);
        System.out.println(value);
    }

    @Test
    public void testConfigure() {
        ConfigurationUtils.init();
        String value = ConfigureSetting.getValue("YMF_OpenIdUrl", "123");
        System.out.println(value);
    }
}
