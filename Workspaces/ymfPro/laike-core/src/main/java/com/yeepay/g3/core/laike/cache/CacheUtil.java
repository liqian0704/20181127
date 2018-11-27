package com.yeepay.g3.core.laike.cache;

import com.google.gson.Gson;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.cache.annotations.CacheKey;
import com.yeepay.g3.core.laike.cache.annotations.UnCacheable;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 缓存工具
 * Author: jiawen.huang
 * Date: 2017/8/3
 * Time: 18:19
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class CacheUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtil.class);

	/**
	 * query对拦截参数处理
	 *
	 * @param systemName
	 * @param args
	 * @return
	 */
	public static CachePretreatment getCachePretreatment(String systemName, Object[] args) {
		CachePretreatment cachePretreatment = new CachePretreatment();
		if (!(Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CACHE_SWITCH)) {
			cachePretreatment.setCacheable(false);
		} else {
			StringBuilder keyBuilder = new StringBuilder().append(systemName);
			for (Object arg : args) {
				if (arg instanceof MappedStatement) {
					List<ResultMap> resultMaps = ((MappedStatement) arg).getResultMaps();
					String methodPath = ((MappedStatement) arg).getId();
					Class targetClz = resultMaps.get(0).getType();
					if (!CacheUtil.checkCacheable(targetClz) || !CacheUtil.checkCacheable(methodPath)) {
						cachePretreatment.setCacheable(false);
						return cachePretreatment;
					}
					cachePretreatment.setTargetClz(targetClz);
					keyBuilder.append("-").append(methodPath);
				}
				if ((null != arg) && !(arg instanceof MappedStatement) && !(arg instanceof RowBounds)) {
					if (arg instanceof HashMap) {
						HashMap<String, Object> map = (HashMap) arg;
						for (String k : map.keySet()) {
							if (!StringUtils.startsWith(k, "param")) {
								keyBuilder.append("-").append(((HashMap) arg).get(k).toString());
							}
						}
					} else {
						keyBuilder.append("-").append(arg.toString());
					}
				}
			}
			cachePretreatment.setIndexKey(keyBuilder.toString());
		}
		return cachePretreatment;
	}

	/**
	 * 根据key查询缓存，转成clz类型
	 *
	 * @param clz
	 * @param cacheKey
	 * @return
	 */
	public static Object find(final Class clz, final String cacheKey) {
		String serializeResult = BaseCache.find(cacheKey);
		return (StringUtils.isBlank(serializeResult) || null == clz) ?
				null : new Gson().fromJson(serializeResult, clz);
	}


	/**
	 * 直接缓存对象
	 *
	 * @param entity
	 * @return persistentKey
	 */
	public static String refresh(String systemName, Object entity) {
		if (null != entity) {
			CacheKey cacheKey = entity.getClass().getAnnotation(CacheKey.class);
			if (null != cacheKey) {
				Gson gson = new Gson();
				final String serializeEntity = gson.toJson(entity);
				Map<String, Object> map = gson.fromJson(serializeEntity, Map.class);
				Object id = map.get(StringUtils.lowerCase(cacheKey.key()));
				final String persistentKey = CacheUtil.createPersistentKey(systemName, entity.getClass(), id);
				BaseCache.save(persistentKey, serializeEntity);
				return persistentKey;
			}
		}
		return null;
	}

	/**
	 * 缓存persistentKey-obj和indexKey-persistentKey
	 *
	 * @param entity
	 */
	public static void refresh(String systemName, final String cacheKey, Object entity) {
		final String persistentKey = refresh(systemName, entity);
		if (StringUtils.isNotBlank(persistentKey)) {
			BaseCache.save(cacheKey, persistentKey);
		}
	}

	/**
	 * 生成对象缓存的主键
	 *
	 * @param clz
	 * @param idValue
	 * @return
	 */
	public static String createPersistentKey(String systemName, Class clz, Object idValue) {
		if (null != clz) {
			CacheKey cacheKey = (CacheKey) clz.getAnnotation(CacheKey.class);
			if (null != cacheKey && null != idValue) {
				return new StringBuilder().append(systemName).append("_").append(cacheKey.value())
						.append("_").append(cacheKey.key()).append("_").append(idValue.toString()).toString();
			}
		}
		return null;
	}

	/**
	 * 匹配前缀的所有缓存
	 *
	 * @return
	 */
	public static void cleanAll(String perfixString) {
		BaseCache.delAll(perfixString);
	}

	/**
	 * 表是否缓存
	 *
	 * @param clz
	 * @return true 缓存；false 不缓存
	 */
	public static boolean checkCacheable(Class clz) {
		if (null != clz) {
			CacheKey cacheKey = (CacheKey) clz.getAnnotation(CacheKey.class);
			return null == cacheKey ? false : true;
		}
		return false;
	}

	/**
	 * 方法是否可以缓存
	 *
	 * @param methodPath
	 * @return true 缓存；false 不缓存
	 */
	public static boolean checkCacheable(String methodPath) {
		String invokeMethodStr = methodPath.substring(methodPath.lastIndexOf(".") + 1, methodPath.length());
		String invokeClzStr = methodPath.substring(0, methodPath.lastIndexOf("."));
		try {
			Class clz = Class.forName(invokeClzStr);
			for (Method m : clz.getMethods()) {
				if (m.getName().equals(invokeMethodStr)) {
					UnCacheable unCacheable = m.getAnnotation(UnCacheable.class);
					return null == unCacheable ? true : false;
				}
			}
			return false;
		} catch (ClassNotFoundException e) {
			LOGGER.warn("[laike_sys] - [ 缓存异常 ] - [ CacheUtil.checkCacheable ] ", e);
		}
		return false;
	}
}
