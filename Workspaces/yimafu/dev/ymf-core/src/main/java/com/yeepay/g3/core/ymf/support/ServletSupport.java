package com.yeepay.g3.core.ymf.support;

import com.yeepay.g3.core.ymf.utils.reflect.ReflectionUtils;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderDownloadArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderQueryArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderDownloadArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundRequestDTO;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Title: servlet支持类, 将servlet请求的map参数转换成接口用的dto类
 * Description:
 * Copyright: Copyright (c)2016
 * Company: YeePay
 *
 * @author chen.liu on 16/8/24.
 */
public class ServletSupport {

    private static final Logger log = LoggerFactory.getLogger(ServletSupport.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 构造订单请求参数
     * @param paramMap
     * @return
     */
    public static OrderArgs buildOrderArgs(Map<String, Object> paramMap) {
        return reflect(paramMap, new OrderArgs());
    }

    /**
     * 构造订单请求参数
     * @param paramMap
     * @return
     */
    public static OrderQueryArgs buildQueryOrderArgs(Map<String, Object> paramMap) {
        return reflect(paramMap, new OrderQueryArgs());
    }

    /**
     * 构造退款请求参数
     * @param paramMap
     * @return
     */
    public static RefundOrderArgs buildRefundArgs(Map<String, Object> paramMap) {
        return reflect(paramMap, new RefundOrderArgs());
    }

    /**
     * 构建退款请求
     * @param paramMap
     * @return
     */
    public static RefundRequestDTO buildRefundRequest(Map<String, Object> paramMap) {
        return reflect(paramMap, new RefundRequestDTO());
    }

    /**
     * 构造订单请求参数
     * @param paramMap
     * @return
     */
    public static OrderDownloadArgs buildOrderDownloadArgs(Map<String, Object> paramMap) {
        return reflect(paramMap, new OrderDownloadArgs());
    }

    /**
     * 构造退款请求参数
     * @param paramMap
     * @return
     */
    public static RefundOrderDownloadArgs buildRefundDownloadArgs(Map<String, Object> paramMap) {
        return reflect(paramMap, new RefundOrderDownloadArgs());
    }


    /**
     * 使用反射构建请求参数
     * servlet请求使用, 网络开销比反射开销要大的多, 可以使用
     * @param paramMap 参数集
     * @param t t
     * @param <T> 泛型
     * @return oo
     */
    private static <T> T reflect(Map<String, Object> paramMap, T t) {
        // 构建请求参数
        for (Iterator<Map.Entry<String, Object>> it = paramMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            try {
                Field field = ReflectionUtils.getField(t.getClass(), key);
                if (null != field && null != value) {
                    field.setAccessible(true);
                    if (field.getType() == BigDecimal.class) { // 特殊处理
                        field.set(t, new BigDecimal(value.toString()));
                    } else if (field.getType() == boolean.class) {
                        field.set(t, Boolean.valueOf(value.toString()));
                    } else if (field.getType() == Date.class) {
                        field.set(t, df.parse(value.toString()));
                    } else if (field.getType() == Integer.class) {
                        field.set(t, Integer.valueOf(value.toString()));
                    } else {
                        field.set(t, value);
                    }
                }
            } catch (Exception e) {
                log.error("反射字段失败, 类名:" + t.getClass().getSimpleName() + ", 字段名称:" + key);
            }
        }
        return t;
    }
}
