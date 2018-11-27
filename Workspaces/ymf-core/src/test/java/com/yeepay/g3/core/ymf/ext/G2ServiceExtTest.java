package com.yeepay.g3.core.ymf.ext;

import com.yeepay.app.httpinvoke.online.dto.CustomerBasicInfoDTO;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.junit.Test;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/1/17
 * @Time: 下午5:03
 */
public class G2ServiceExtTest extends ApplicationContextAwareTest {

//    @Autowired
//    G2ServiceExt g2ServiceExt ;

    @Test
    public void testQueryHmacKey() {

//        String hmacKey = g2ServiceExt.queryCustomerHmacKey("10040007800");
//        System.out.println("返回数据：" + hmacKey);
        G2ServiceExt g2ServiceExt = getBean(G2ServiceExt.class);

        CustomerBasicInfoDTO customerBasicInfoDTO = g2ServiceExt.queryCustomerBasicInfo("10040007800");
        System.out.println("返回数据：" + JSONUtils.toJsonString(customerBasicInfoDTO));
    }

}
