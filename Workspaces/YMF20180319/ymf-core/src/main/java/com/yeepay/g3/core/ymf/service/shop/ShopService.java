package com.yeepay.g3.core.ymf.service.shop;

import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.common.ShopStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 网点
 * @Author: xiaobin.liu
 * @Date: 17/6/21
 * @Time: 下午2:18
 */
public interface ShopService {

    /**
     * 保存新记录
     * @param shop
     * @return
     */
    int save(Shop shop);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    int save(OperateEntity<Shop> shop);

    /**
     * 保存或更新
     * @param shop
     * @return
     */
    int saveOrUpdate(Shop shop);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    int saveOrUpdate(OperateEntity<Shop> shop);

    /**
     * 更新(全量)
     * @param shop
     * @return
     */
    int update(Shop shop);

    /**
     * 更新（选择）
     * @param shop
     * @return
     */
    int updateSelective(Shop shop);

    /**
     * 根据Id查询Shop
     */
    Shop queryShopById(Long id);

    /**
     * 根据商户编号查询所有网点
     *
     * @param customerNumber 商户编号
     * @param status         必填
     * @return 网点信息
     */
    List<Shop> queryShopByCustomerNumber(String customerNumber, ShopStatus status);


    /**
     * 根据网点编号查询网点
     * @param shopNumber
     * @param status        不填为忽略
     * @return
     */
    Shop queryShopByShopNumber(String shopNumber, ShopStatus status);


    /**
     * 批量保存shop和二维码
     * @param shop
     * @param qrCodeList
     */
    void batchSaveShopAndQrcode(Shop shop, List<QRCode> qrCodeList);
}
