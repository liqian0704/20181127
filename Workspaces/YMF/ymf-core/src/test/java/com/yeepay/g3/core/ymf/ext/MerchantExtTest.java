package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/3/15
 * @Time: 下午3:42
 */
public class MerchantExtTest extends AnnotationContextAwareTest{
    @Autowired
    private MerchantExt merchantExt;

    @Test
    public void testMerchantRel() {
        try {
            //子商户zsh    10040040183   10040040231
            //直销商户zxsh
            String s = merchantExt.queryParentMerchentNo("10040040231");
            System.out.println("ParentNO:" + s);
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }
}
