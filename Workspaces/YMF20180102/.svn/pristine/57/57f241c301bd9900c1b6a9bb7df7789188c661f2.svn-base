package com.yeepay.g3.core.ymf.utils;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置项
 *
 * 统一配置组件提供管理后台，可以进行参数的增、删、查、改操作
 * 增参数的界面上需要填写以下属性：
 * 属性名称                   描述
 * 命名空间                   对于同一个参数，可以分别在不同的命名空间下配置，获取的时候可以指定取哪个命名空间下的值
 *                            例如可以配置一个QA的命名空间，以实现在QA环境和生产环境设置不同的参数值
 * 配置类型                   即配置信息的分组，例如所有的子系统参数分为一组，产品应用参数分为一组，远程调用的接口地址分为一组
 * 配置键                     即配置信息的key，分组+key作为唯一约束
 * 值类型                     可以是基本类型，list，map
 * 值数据类型                  如果类型是list或者map，在这里定义里面的value数据类型
 * 配置值                      参数值
 * 是否可修改                  该参数是否允许修改
 * 是否可删除                  该参数是否允许删除
 * 描述                       附加描述
 * 所属系统                    该参数属于哪个业务系统，也是附加描述字段
 *
 */
public class ConfigureSetting {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ConfigureSetting.class);


    /**
     * 获取应用服务器IP 对应的数据中心ID列表
     * <p/>
     * sequence序列（每个服务器 对应的数据中心起始值 每个机器1个数据中心）
     *
     * @return
     */
    public static Map<String, String> getSequenceSourceIp2Datacenter() {
        return getValue("skb.sequennce.sourceIp2DataCenter", null);
    }


    /**
     * 获取config_type_sys下的key对应的value
     * 有内存缓存
     * @param configKey 统一配置key
     * @param defaultValue 默认值
     * @param <T> 泛型
     * @return value
     */
    @SuppressWarnings("unchecked")
    public static <T> T getValue(String configKey, T defaultValue) {
        try {
            ConfigParam<?> configParam = ConfigurationUtils
                    .getSysConfigParam(configKey);
            T t = (T) configParam.getValue();
            if (null == t) {
                return defaultValue;
            }
            return t;
        } catch (Exception e) {
            LOGGER.error("获取统一配置失败, configKey:" + configKey, e);
            return defaultValue;
        }
    }

    /**
     * 获取config_type_sys下的key对应的value
     * 有内存缓存
     * @param configKey 统一配置key
     * @param clazz 反省类
     * @param <T> 泛型
     * @return value
     */
    public static <T> T getValue(String configKey, Class<T> clazz) {
        try {
            ConfigParam<?> configParam = ConfigurationUtils
                    .getSysConfigParam(configKey);
            return (T) configParam.getValue();
        } catch (Exception e) {
            LOGGER.error("获取统一配置失败, configKey:" + configKey, e);
            return null;
        }
    }

    /**
     * 补单时间
     * @return
     */
    public static int getTimerConfigOrder() {
        Map<String, Long> map = getTimerConfig();
        Long l = map.get(Constants.YMF_TIMER_CONFIG_ORDER);
        if (null == l) {
            return -10;
        } else {
            return -l.intValue();
        }
    }

    /**
     * public static final String YMF_TIMER_CONFIG_ORDER_FROM = "ORDER_FROM";
     public static final String YMF_TIMER_CONFIG_ORDER_TO = "ORDER_TO";
     */

    /**
     * 补单时间
     * @return
     */
    public static int getTimerConfigOrderFrom() {
        Map<String, Long> map = getTimerConfig();
        Long l = map.get(Constants.YMF_TIMER_CONFIG_ORDER_FROM);
        if (null == l) {
            return -130;
        } else {
            return -l.intValue();
        }
    }

    /**
     * 补单时间
     * @return
     */
    public static int getTimerConfigOrderTo() {
        Map<String, Long> map = getTimerConfig();
        Long l = map.get(Constants.YMF_TIMER_CONFIG_ORDER_TO);
        if (null == l) {
            return -10;
        } else {
            return -l.intValue();
        }
    }

    /**
     * 补退款单时间
     * @return
     */
    public static int getTimerConfigRefundFrom() {
        Map<String, Long> map = getTimerConfig();
        Long l = map.get(Constants.YMF_TIMER_CONFIG_REFUND_FROM);
        if (null == l) {
            return -15;
        } else {
            return -l.intValue();
        }
    }

    /**
     * 补退款单时间
     * @return
     */
    public static int getTimerConfigRefundTo() {
        Map<String, Long> map = getTimerConfig();
        Long l = map.get(Constants.YMF_TIMER_CONFIG_REFUND_TO);
        if (null == l) {
            return -3;
        } else {
            return -l.intValue();
        }
    }

    /**
     * 订单超时配置
     * @return
     */
    public static int getTimerConfigExpire() {
        Map<String, Long> map = getTimerConfig();
        Long l = map.get(Constants.YMF_TIMER_CONFIG_EXPIRE_BEFORE);
        if (null == l) {
            return -15;
        } else {
            return -l.intValue();
        }
    }

    private static Map<String, Long> getTimerConfig() {
        return getValue(Constants.YMF_TIMER_CONFIG, new HashMap<String, Long>());
    }

    @Deprecated
    public static int getRedisExpireTime() {
        return (Integer) ConfigurationUtils.getAppConfigParam("").getValue();
    }


}
