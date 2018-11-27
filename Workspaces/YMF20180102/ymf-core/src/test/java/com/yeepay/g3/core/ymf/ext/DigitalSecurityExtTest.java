package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/2/23
 * @Time: 下午2:09
 */
public class DigitalSecurityExtTest extends SoaContextAwareTest {

    @Test
    public void testSign() {
        DigitalSecurityExt oprYopOrderExt = getBean(DigitalSecurityExt.class);
        try {
            //签名方式
            Map<String, String> params = new HashMap<String, String>();
            params.put("merchantNo", "10040007800");
            params.put("token", "AFD5C282FFB7D3AE9A7390105838CF0850C97D1A957BF1BF9D01F4C9F8F9C683");
            long seconds = 1487659807329l/1000;
            System.out.println(("当前时间：" + System.currentTimeMillis()/1000));
            params.put("timestamp", System.currentTimeMillis()/1000+""); //1487659807329
            params.put("codeType", "WX");
            params.put("code", "130635563031244670");
            params.put("storeCode", "12344");
            params.put("deviceSn", "USER_ID");
//			params.put("bizType", "G2_NET");
            StringBuilder str = new StringBuilder();
            str.append(params.get("merchantNo")).append(params.get("token")).append(params.get("timestamp"))
                    .append(params.get("codeType")).append(params.get("code"))
                    .append(params.get("storeCode")).append(params.get("deviceSn"));
            if(StringUtils.isNotEmpty(params.get("bizType"))){
                str.append(params.get("bizType"));
            }

            String sign = oprYopOrderExt.sign("123456789334823423423947329857029358huwrehwerhewhrewkkjg");
            System.out.println("返回签名:" + sign.length());
            String sign1 = oprYopOrderExt.verify("123456789", sign);

        } catch (YmfTrxException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testLength() {
        String str = "Ki0atn4Dn8uhzHpaMHgfQXEjVI7frQoNrVnWnXsRveN1tmKeD4ZCa+pioCRYyCiqoTFdkjBVTsgFBwJFdQ" +
                "MHPQ6mdq/yl9IZJlYT+j2zasAl3Mt7SXz8SJXCj29vlbYfSDA7IKm1AcE8LTP7l+4CIerWytg3lacD1efcv9vvoeA=";
        System.out.println("长度:" + str.length());
    }
}
