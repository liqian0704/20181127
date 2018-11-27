package com.yeepay.g3.core.ymf.facade.impl;

import com.yeepay.g3.core.ymf.biz.data.DataHandlerBiz;
import com.yeepay.g3.facade.ymf.facade.DataHandlerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 数据处理
 * @Author: xiaobin.liu
 * @Date: 17/8/30
 * @Time: 上午10:33
 */
@Service
public class DataHandlerFacadeImpl implements DataHandlerFacade {

    @Autowired
    private DataHandlerBiz dataHandlerBiz;
    /**
     * 处理历史数据中二维码QrContent为空数据
     * @return 返回查询到可处理数据的条数。最大1000
     */
    @Override
    public int handleQrCodeContent() {
        return dataHandlerBiz.handleQrCodeContent();
    }

    /**
     * 生成默认网点
     */
    @Override
    public int handleDefaultShop() {
        return dataHandlerBiz.handleDefaultShop();
    }

    /**
     * 绑定默认二维码到默认网点
     */
    @Override
    public int bindQrCodeToShop() {
        return dataHandlerBiz.bindQrCodeToShop();
    }


    @Override
    public int updateQrFtpUrl() {
        return dataHandlerBiz.updateQrFtpUrl();
    }

    /**
     * 批量更新商户logo地址
     */
    @Override
    public int updateCustomerLogoFtpUrl() {
        return dataHandlerBiz.updateCustomerLogoFtpUrl();
    }
}
