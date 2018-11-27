package com.yeepay.g3.core.ymf.ext.impl;

import com.yeepay.g3.core.ymf.ext.MerchantConfigExt;
import com.yeepay.g3.facade.foundation.dto.MerchantPayConfigDto;
import com.yeepay.g3.facade.foundation.exception.MerchantConfigException;
import com.yeepay.g3.facade.foundation.facade.MerchantConfigFacade;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 配置中心接口
 * @Author: xiaobin.liu
 * @Date: 17/3/28
 * @Time: 上午11:25
 */
@Service
public class MerchantConfigExtImpl implements MerchantConfigExt {
    private static final Logger logger = LoggerFactory.getLogger(MerchantConfigExtImpl.class);

    /**
     * 商户支付配置信息设置
     */
    @Override
    public void addPayConfig(String merchantNo) throws YmfException {
        MerchantConfigFacade service = RemoteServiceFactory.getService(MerchantConfigFacade.class);
        try {
            MerchantPayConfigDto req = new MerchantPayConfigDto();
            req.setMerchantNo(merchantNo);
            req.setMccCode("");
            req.setMarketingCode("");//营销产品码
            req.setFeeType("");
            Map<String, List<MerchantPayConfigDto.PayProductDto>> payProductMap
                    = new HashMap<String, List<MerchantPayConfigDto.PayProductDto>>();
            List<MerchantPayConfigDto.PayProductDto> dtos = new ArrayList<MerchantPayConfigDto.PayProductDto>();
            MerchantPayConfigDto.PayProductDto payProductDto = new MerchantPayConfigDto.PayProductDto();
            payProductDto.setPayProductName("MSCANPAY");
            List<String> payways = new ArrayList<String>();
            payways.add("WECHAT_SCAN");
            payways.add("ALIPAY_SCAN");
            payProductDto.setPayWays(payways);
            dtos.add(payProductDto);
            payProductMap.put("API", dtos);
            req.setPayProductMap(payProductMap);
            logger.info("开始调用 配置中心 [addPayConfig] 接口，请求参数：{}",req);
            service.addPayConfig(req);
            logger.info("调用 配置中心 [addPayConfig] 接口成功");
        } catch (MerchantConfigException e) {
            logger.error("调用 配置中心 [addPayConfig] 接口失败",e);
            throw new YmfException(e);
        }
    }

    @Override
    public void queryPayConfig(String merchantNo) throws YmfException {
        MerchantConfigFacade service = RemoteServiceFactory.getService(MerchantConfigFacade.class);
        try {
            logger.info("开始调用 配置中心 [queryPayConfig] 接口，请求参数：{}",merchantNo);
            MerchantPayConfigDto merchantPayConfigDto = service.queryPayConfig(merchantNo);
            logger.info("调用 配置中心 [queryPayConfig] 接口成功,响应参数：{}", JSONUtils.toJsonString(merchantPayConfigDto));
        } catch (MerchantConfigException e) {
            logger.error("调用 配置中心 [addPayConfig] 接口失败",e);
            throw new YmfException(e);
        }
    }

    @Override
    public void updatePayConfig(String merchantNo) throws YmfException {
        MerchantConfigFacade service = RemoteServiceFactory.getService(MerchantConfigFacade.class);
        try {
            MerchantPayConfigDto req = new MerchantPayConfigDto();
            req.setMerchantNo(merchantNo);
            logger.info("开始调用 配置中心 [updatePayConfig] 接口，请求参数：{}",req);
            service.updatePayConfig(req);
            logger.info("调用 配置中心 [updatePayConfig] 接口成功");
        } catch (MerchantConfigException e) {
            logger.error("调用 配置中心 [updatePayConfig] 接口失败",e);
            throw new YmfException(e);
        }
    }
}
