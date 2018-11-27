package com.yeepay.g3.core.ymf.dao.shop;

import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.enumtype.common.ShopStatus;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/6/20
 * @Time: 下午2:25
 */
public class ShopDaoTest extends AnnotationContextAwareTest {

//    private ShopDao shopDao;
    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void test() {
        Shop shop = new Shop();
        shop.setShopNumber("1234567890");
        shop.setShopName("测试网点插入");
        shop.setLinkMan("张三");
        shop.setLinkPhone("123456789");
        shop.setStatus(ShopStatus.ACTIVE);
        shopMapper.insert(shop);
        System.out.println("插入成功" + JSONUtils.toJsonString(shop));

    }

    @Test
    public void testSelect() {
        Shop shop = shopMapper.selectByPrimaryKey(1l);
        System.out.println("查询成功" + JSONUtils.toJsonString(shop));
    }
}
