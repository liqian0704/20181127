package com.yeepay.g3.core.ymf.biz.data;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/8/30
 * @Time: 上午10:39
 */
public interface DataHandlerBiz {
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
     * 绑定默认二维码到默认网点
     */
    public int updateQrFtpUrl() ;

    /**
     * 批量更新商户logo地址
     */
    public int updateCustomerLogoFtpUrl();
}
