package com.yeepay.g3.core.ymf.ext;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/7/10
 * @Time: 下午3:21
 */
public class AreaExtTest extends AnnotationContextAwareTest {
    @Autowired
    private AreaExt areaExt;

    @Test
    public void testQueryAreaInfo() {
        Map<String, String> map = areaExt.queryAllProvince();
        System.out.println("省：" + map);
        Map<String, String> map1 = areaExt.queryAreaInfo("340000");
        System.out.println("市或区：" + map1);

    }

    @Test
    public void testQueryArea() {
        String code = areaExt.queryCodeByProinceName("北京");

        System.out.println("省：" + code);
        Map<String, String> map1 = areaExt.queryAreaInfo("340000");
        System.out.println("市或区：" + map1);
        Map<String, String> cityNameMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println("市或区：" + entry.getKey() + ",值" + entry.getValue());
            cityNameMap.put(entry.getValue(), entry.getValue());
        }
    }
}
