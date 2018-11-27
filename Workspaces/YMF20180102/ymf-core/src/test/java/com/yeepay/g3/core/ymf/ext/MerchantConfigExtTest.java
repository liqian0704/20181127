package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 测试
 * @Author: xiaobin.liu
 * @Date: 17/3/28
 * @Time: 下午3:28
 */
public class MerchantConfigExtTest extends AnnotationContextAwareTest {
    @Autowired
    private MerchantConfigExt merchantConfigExt ;

    @Test
    public void testQuery() {
        try {
            merchantConfigExt.queryPayConfig("10040007800");
        } catch (YmfException e) {
            e.printStackTrace();
        }

    }
}
