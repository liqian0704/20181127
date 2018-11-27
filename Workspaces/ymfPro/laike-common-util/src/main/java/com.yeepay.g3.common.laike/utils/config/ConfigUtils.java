/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.common.laike.utils.config;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigParamGroup;
import com.yeepay.g3.utils.config.ConfigurationUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: 统一配置工具类二次封装</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 14-4-18 18:12
 */
public class ConfigUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtils.class);

	public static final String CONFIG_TYPE_TEXT_RESOURCES = "config_type_text_resources";

	public static String CONFIG_REMOTE_SERVICE = "config_remoteservice";

	/**
	 * 获取配置参数
	 *
	 * @param configType 参数的分类
	 * @param configKey  参数key(含默认值)
	 * @return 参数值
	 */
	public static Object getConfigParam(String configType, ConfigEnum configKey) {
		ConfigParam configParam = ConfigurationUtils.getConfigParam(configType, configKey.getConfigKey());
		if (null != configParam && null != configParam.getValue()) {
			return configParam.getValue();
		} else {
			LOGGER.debug("configType:{}, configKey:{}, defaultValue:{}",
					configType, configKey.getConfigKey(), configKey.getDefaultValue());
			return configKey.getDefaultValue();
		}
	}

	/**
	 * 获取产品层配置参数
	 *
	 * @param configKey 参数key(含默认值)
	 * @return 参数值
	 */
	public static Object getAppConfigParam(ConfigEnum configKey) {
		return getConfigParam(ConfigurationUtils.CONFIG_TYPE_APP, configKey);
	}

	/**
	 * 获取子系统配置参数
	 *
	 * @param configKey 参数key(含默认值)
	 * @return 参数值
	 */
	public static Object getSysConfigParam(ConfigEnum configKey) {
		return getConfigParam(ConfigurationUtils.CONFIG_TYPE_SYS, configKey);
	}

	/**
	 * 获取远程调用地址
	 *
	 * @param configKey
	 * @return
	 */
	public static Object getRemoteService(ConfigEnum configKey) {
		return getConfigParam(CONFIG_REMOTE_SERVICE, configKey);
	}

	/**
	 * 获取数据字典统一配置 Map
	 *
	 * @param configKey 配置键
	 * @return 参数值
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getResourcesConfigMap(ConfigEnum configKey) {
		return getResourcesConfigMap(configKey.getConfigKey(), configKey.getDefaultValue());
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getResourcesConfigMap(String configKey, Object defaultValue) {
		ConfigParamGroup configParamGroup = ConfigurationUtils.getConfigParamGroup(CONFIG_TYPE_TEXT_RESOURCES);
		ConfigParam configParam = configParamGroup.getConfig(configKey);
		if (null != configParam && null != configParam.getValue()) {
			Object value = configParam.getValue();
			if (value instanceof Map) {
				return (Map<String, String>) value;
			} else if (value instanceof String && ((String) value).indexOf('@') == 0) {
				String key = (String) value;
				return getResourcesConfigMap(key.substring(1, key.length()), defaultValue);
			}
		}
		return (Map<String, String>) defaultValue;
	}

	/**
	 * 获取数据字典统一配置 List
	 *
	 * @param configKey 配置键
	 * @return 参数值
	 */
	public static List<String> getResourcesConfigList(ConfigEnum configKey) {
		return getResourcesConfigList(configKey.getConfigKey(), configKey.getDefaultValue());
	}

	@SuppressWarnings("unchecked")
	private static List<String> getResourcesConfigList(String configKey, Object defaultValue) {
		ConfigParamGroup configParamGroup = ConfigurationUtils.getConfigParamGroup(CONFIG_TYPE_TEXT_RESOURCES);
		ConfigParam configParam = configParamGroup.getConfig(configKey);
		if (null != configParam && null != configParam.getValue()) {
			Object value = configParam.getValue();
			if (value instanceof List) {
				return (List<String>) value;
			} else if (value instanceof String && ((String) value).indexOf('@') == 0) {
				String key = (String) value;
				return getResourcesConfigList(key.substring(1, key.length()), defaultValue);
			}
		}
		return (List<String>) defaultValue;
	}

}