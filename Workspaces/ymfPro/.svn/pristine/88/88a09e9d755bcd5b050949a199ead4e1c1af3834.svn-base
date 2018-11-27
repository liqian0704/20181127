package com.yeepay.g3.core.laike.cache;

import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.utils.lock.utils.RedisCall;
import com.yeepay.utils.lock.utils.RedisClientUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Set;

/**
 * Description: 缓存
 * Author: jiawen.huang
 * Date: 17/6/6
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class BaseCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseCache.class);

	/**
	 * 单纯滴根据key查询缓存
	 *
	 * @param cacheKey
	 * @return 序列化结果
	 */
	public static String find(final String cacheKey) {
		if (StringUtils.isBlank(cacheKey)) {
			return null;
		}
		try {
			return (String) RedisClientUtils.call(new RedisCall<Object>() {
				@Override
				public Object run(Jedis jedis) {
					Pipeline pl = jedis.pipelined();
					String result = jedis.get(cacheKey);
					pl.sync();
					return result;
				}
			});
		} catch (Throwable e) {
			LOGGER.warn("[laike_sys] - [ 缓存异常 ] - [ BaseCache.find ] ", e);
		}
		return null;
	}

	/**
	 * 单纯地插入键值对
	 *
	 * @param key   k
	 * @param value 序列化后的v
	 * @return
	 */
	public static boolean save(final String key, final String value) {
		try {
			return RedisClientUtils.call(new RedisCall<Boolean>() {
				@Override
				public Boolean run(Jedis jedis) {
					Pipeline pl = jedis.pipelined();
					pl.set(key, value);
					pl.expire(key, 3600);
					pl.sync();
					return true;
				}
			});
		} catch (Throwable e) {
			LOGGER.warn("[laike_sys] - [ 缓存异常 ] - [ BaseCache.save ] ", e);
		}
		return false;
	}

	/**
	 * 删除匹配的key
	 *
	 * @param keysPattern
	 * @return
	 */
	public static void del(final String keysPattern) {
		try {
			RedisClientUtils.call(new RedisCall<Boolean>() {
				@Override
				public Boolean run(Jedis jedis) {
					Pipeline pl = jedis.pipelined();
					Set<String> keys = jedis.keys(keysPattern);
					for (String key : keys) {
						pl.del(key);
					}
					pl.sync();
					return true;
				}
			});
		} catch (Throwable e) {
			LOGGER.warn("[laike_sys] - [ 缓存异常 ] - [ BaseCache.del ] ", e);
		}
	}

	/**
	 * 删除所有前缀匹配的key
	 *
	 * @param perfix
	 */
	public static void delAll(final String perfix) {
		del(perfix + "*");
	}
}