package com.yeepay.g3.core.ymf.ext.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.core.ymf.ext.AreaExt;
import com.yeepay.g3.facade.mer.dto.response.out.AreaDto;
import com.yeepay.g3.facade.mer.dto.response.out.MerAreaRespDto;
import com.yeepay.g3.facade.mer.facade.out.MerOutInvokeFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 省市区查询
 * @Author: xiaobin.liu
 * @Date: 17/7/10
 * @Time: 上午11:45
 */
@Service
public class AreaExtImpl implements AreaExt {

    private static final Logger logger = LoggerFactory.getLogger(AreaExtImpl.class);

    /**
     * 所有省份
     */
    public static final String province = "{\"110000\":\"北京\",\"120000\":\"天津\",\"130000\":\"河北\"," +
            "\"140000\":\"山西\",\"150000\":\"内蒙古\",\"210000\":\"辽宁\",\"220000\":\"吉林\",\"230000\":\"黑龙江\"," +
            "\"310000\":\"上海\",\"320000\":\"江苏\",\"330000\":\"浙江\",\"340000\":\"安徽\",\"350000\":\"福建\"," +
            "\"360000\":\"江西\",\"370000\":\"山东\",\"410000\":\"河南\",\"420000\":\"湖北\",\"430000\":\"湖南\"," +
            "\"440000\":\"广东\",\"450000\":\"广西\",\"460000\":\"海南\",\"500000\":\"重庆\",\"510000\":\"四川\"," +
            "\"520000\":\"贵州\",\"530000\":\"云南\",\"540000\":\"西藏\",\"610000\":\"陕西\",\"620000\":\"甘肃\"," +
            "\"630000\":\"青海\",\"640000\":\"宁夏\",\"650000\":\"新疆\"}";

    /**
     * 通过名字获取编码（变态需求）
     */
    public static final String provinceName = "{\"北京\":\"110000\",\"天津\":\"120000\",\"河北\":\"130000\"," +
            "\"山西\":\"140000\",\"内蒙古\":\"150000\",\"辽宁\":\"210000\",\"吉林\":\"220000\",\"黑龙江\":\"230000\"," +
            "\"上海\":\"310000\",\"江苏\":\"320000\",\"浙江\":\"330000\",\"安徽\":\"340000\",\"福建\":\"350000\"," +
            "\"江西\":\"360000\",\"山东\":\"370000\",\"河南\":\"410000\",\"湖北\":\"420000\",\"湖南\":\"430000\"," +
            "\"广东\":\"440000\",\"广西\":\"450000\",\"海南\":\"460000\",\"重庆\":\"500000\",\"四川\":\"510000\"," +
            "\"贵州\":\"520000\",\"云南\":\"530000\",\"西藏\":\"540000\",\"陕西\":\"610000\",\"甘肃\":\"620000\"," +
            "\"青海\":\"630000\",\"宁夏\":\"640000\",\"新疆\":\"650000\"}";


    /**
     * key：省编码   value：市map
     */
    private static Map<String, Map<String, String>> cacheCityNameMap = new HashMap<String, Map<String, String>>();

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            provinceMap = mapper.readValue(province, Map.class);
            provinceNameMap = mapper.readValue(provinceName, Map.class);
        } catch (IOException e) {
            logger.error("省数据转换异常。", e);
        }
    }

    /**
     * key:code   value:name
     */
    private static Map<String, String> provinceMap;

    /**
     * key:name   value:code
     */
    private static Map<String, String> provinceNameMap;

    /**
     * 查询下级所有地区
     *
     * @param areaCode 地区编码
     */
    @Override
    public Map<String, String> queryAreaInfo(String areaCode) {
        MerOutInvokeFacade service = RemoteServiceFactory.getService(MerOutInvokeFacade.class);
        logger.info("查询省市区 [queryAreaInfo] 请求参数：{}", areaCode);
        MerAreaRespDto merAreaRespDto = service.queryAreaInfo(areaCode);
        logger.info("查询省市区 [queryAreaInfo] 响应参数：{}", merAreaRespDto);
        String returnCode = merAreaRespDto.getReturnCode();
        if (!"REG00000".equals(returnCode)) {
            return null;
        }
        List<AreaDto> areaDtoList = merAreaRespDto.getAreaDtoList();

        if (areaDtoList != null && areaDtoList.size() > 0) {
            Map<String, String> map = new HashMap<String, String>();
            for (AreaDto areaDto : areaDtoList) {
                map.put(areaDto.getCode(), areaDto.getName());
            }
            return map;
        }
        return null;
    }

    /**
     * 查询所有省信息
     *
     * @return key:code   value:name
     */
    @Override
    public Map<String, String> queryAllProvince() {
        return provinceMap;
    }

    /**
     * 根据省名称查询省编码
     * @param provinceName 省名称
     * @return
     */
    @Override
    public String queryCodeByProinceName(String provinceName) {
        String code = provinceNameMap.get(provinceName);
        return code;
    }

    /**
     * 根据市区
     * @param provinceCode 省编码
     * @param cityName     市名称
     * @return
     */
    @Override
    public String queryCodeByCityName(String provinceCode, String cityName) {
        if (provinceCode == null) {
            return null;
        }
        //先从本地缓存中拿
        Map<String, String> cachedCityNameMap = cacheCityNameMap.get(provinceCode);
        if (cachedCityNameMap != null) {
            return cachedCityNameMap.get(cityName);
        }

        //从远程查询，并放入本地缓存
        Map<String, String> map = queryAreaInfo(provinceCode);
        if (map != null && map.size() > 0) {
            Map<String, String> cityNameMap = new HashMap<String, String>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                cityNameMap.put(entry.getValue(), entry.getKey());
            }
            cacheCityNameMap.put(provinceCode, cityNameMap);
            return cityNameMap.get(cityName);
        }
        return null;
    }


}
