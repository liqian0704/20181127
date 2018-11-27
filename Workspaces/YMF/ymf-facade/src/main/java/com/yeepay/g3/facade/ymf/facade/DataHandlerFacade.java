package com.yeepay.g3.facade.ymf.facade;

/**
 * @Description: 数据处理器
 * @Author: xiaobin.liu
 * @Date: 17/8/30
 * @Time: 上午10:32
 */
public interface DataHandlerFacade {

    /**
     * 处理历史数据中二维码QrContent为空数据
     */
    int handleQrCodeContent();

    /**
     * 生成默认网点
     */
    int handleDefaultShop();

    /**
     * 绑定默认二维码到默认网点
     */
    int bindQrCodeToShop();

    /**
     * 批量更新二维码ftp地址
     */
    int updateQrFtpUrl();

    /**
     * 批量更新商户logo地址
     */
    int updateCustomerLogoFtpUrl();
}
