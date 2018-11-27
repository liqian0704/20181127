package com.yeepay.g3.ymf.boss.utils;/**
 * Created by jiwei.lv on 16/8/23.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/23
 */
public class AmountUtil {
    private static final Log logger = LogFactory.getLog(AmountUtil.class);

    public static String  formatAmount(Object object,String pattern){
        try{
            if(pattern == null||("").equals(pattern)){
                return String.valueOf(object);
            }
            if(object == null||("").equals(object)){
                return  null;
            }else{
                DecimalFormat df=(DecimalFormat) NumberFormat.getInstance();
                df.applyPattern(pattern);
                return  df.format(object);
            }
        }catch(Exception e){
            logger.error(object+"转化失败", e);;
        }
        return  null;
    }
}
