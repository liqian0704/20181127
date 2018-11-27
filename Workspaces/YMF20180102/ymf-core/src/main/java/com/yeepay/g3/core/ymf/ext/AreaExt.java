package com.yeepay.g3.core.ymf.ext;

import java.util.Map;

/**
 * @Description: 省市区查询
 * @Author: xiaobin.liu
 * @Date: 17/7/10
 * @Time: 上午11:45
 */
public interface AreaExt {
    /**
     * 查询下级所有地区
     * @param areaCode  地区编码
     * @return    key:code   value:name
     */
    Map<String,String> queryAreaInfo(String areaCode);

    /**
     * 查询所有省信息
     * @return  key:code   value:name
     */
    Map<String,String> queryAllProvince();

    /**
     * 根据省名称查询省编码
     * @param provinceName 省名称
     * @return
     */
    String queryCodeByProinceName(String provinceName);

    /**
     * 根据市区
     * @param provinceCode 省编码
     * @param cityName     市名称
     * @return
     */
    String queryCodeByCityName(String provinceCode, String cityName);
}
