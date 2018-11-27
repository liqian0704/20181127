package com.yeepay.g3.core.laike.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.LbsService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.core.laike.utils.RemoteFacadeFactory;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ExternalReturnCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Description: 百度LBS服务实现层
 * Author: wei.li
 * Date: 17/8/28
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class LbsServiceImpl extends AbstractService implements LbsService {

    @Override
    public HashMap<String, String> queryGeologyLocation(String lat, String lng) {
        try {
            Map<String, String> params = Maps.newHashMap();
            params.put("location", lat + "," + lng);
            params.put("output", "json");
            params.put("pois", "0");
            params.put("ak", ConstantUtil.LBS_KEY);
            String response = RemoteFacadeFactory.getHttpPostService(ExternalSystem.BAIDU_LBS, params, null, null);
            JSONObject jsonObject = JSON.parseObject(response);
            HashMap<String, String> map = Maps.newHashMap();
            if (ExternalReturnCode.LBS_SUCCESS_CODE.equals(jsonObject.get("status"))) {
                JSONObject result = (JSONObject) ((JSONObject) jsonObject.get("result")).get("addressComponent");
                map.put("merProvinceName", result.get("province").toString());
                map.put("merCityName", result.get("city").toString());
                map.put("merDistrictName", result.get("district").toString());
                map.put("merAddress", ((JSONObject) jsonObject.get("result")).get("formatted_address").toString());
                if (map.containsValue(null)) {
                    throw new LaikeSysException(ErrorCode.LBS_INFO_NOT_EXIST);
                }
            } else {
                throw new LaikeSysException(ErrorCode.LBS_REQ_EXCEPTION);
            }
            return map;
        } catch (LaikeSysException e) {
            throw e;
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.LBS_UNKNOW_EXCEPTION);
        }
    }
}
