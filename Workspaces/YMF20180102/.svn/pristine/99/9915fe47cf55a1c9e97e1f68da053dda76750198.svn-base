package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.merchant_platform.dto.MerchantRespDTO;
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

    @Test
    public void testMerchant() {
        try {
            //子商户zsh    10040040183   10040040231
            //直销商户zxsh
            //,响应参数：{"createTime":"2017-01-19 18:23:57","subBizcatName":"火车票/船票/车票等长途交通票务","bizcatName":"商旅",
            // "status":"ACTIVE","signName":"丽丽了","signType":"INDIVIDUAL","customerNo":"KH1701190625058252",
            // "bizcatCode":"032","type":"CUSTOMER","subBizcatCode":"032001","city":"北京","auditStatus":"PASSED",
            // "retCode":"0000","source":"LK","address":"呼家楼街道东大桥路16号楼","retMsg":"操作成功","name":"丽丽了",
            // "province":"北京","merchantNo":"10040040231","shortName":"丽丽了","activateStatus":"ACTIVATED"}
            MerchantRespDTO merchantRespDTO = merchantExt.queryMerchant("10040041870");
            System.out.println("结果:" + merchantRespDTO);
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArea() {
        try {
            merchantExt.queryAllArea();
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }
}
