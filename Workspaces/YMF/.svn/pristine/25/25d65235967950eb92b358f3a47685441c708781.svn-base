package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/1/17
 * @Time: 下午5:03
 */
public class G2ServiceExtTest extends AnnotationContextAwareTest {

    @Autowired
    G2ServiceExt g2ServiceExt ;

    @Test
    public void testQueryHmacKey() {
        String hmacKey = g2ServiceExt.queryCustomerHmacKey("10040007800");
        System.out.println("返回数据：" + hmacKey);
    }

}
