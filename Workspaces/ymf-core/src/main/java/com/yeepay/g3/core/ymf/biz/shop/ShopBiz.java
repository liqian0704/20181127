package com.yeepay.g3.core.ymf.biz.shop;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.vo.shop.BatchShopRequest;
import com.yeepay.g3.core.ymf.vo.shop.BatchShopResponse;

import java.util.List;

/**
 * @Description: 网点
 * @Author: xiaobin.liu
 * @Date: 17/6/29
 * @Time: 上午10:57
 */
public interface ShopBiz {
    /**
     * 批量保存excel导入数据
     * @return
     */
    List<BatchShopResponse> batchAddShop(List<BatchShopRequest> list,String createUser);

    List<QRCode> batchBuildQrcode(int qrNo, Customer customer);
}
