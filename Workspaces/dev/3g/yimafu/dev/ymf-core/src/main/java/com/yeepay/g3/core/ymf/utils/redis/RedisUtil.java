package com.yeepay.g3.core.ymf.utils.redis;

import java.util.Collections;
import java.util.List;

import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import redis.clients.jedis.Jedis;

import com.yeepay.utils.lock.utils.RedisCall;
import com.yeepay.utils.lock.utils.RedisClientUtils;
import com.yeepay.utils.lock.utils.SerializeUtil;

/**
 * 类的主要功能及作用: redis功能类
 * 注意事项:
 * @author baichuan.zheng
 * @since 2016年8月17日 下午4:08:20
 * 
 */
public class RedisUtil {
	

	/**
	 * 过期时间
	 */
	private static final int EXPIRE_TIME = ConfigureSetting.getRedisExpireTime();
	
	
	
	/**
	 * @description 保存 对象
	 * @time 2016年8月17日 上午11:54:33
	 * @author	baichuan.zheng
	 */
	public static  void set(final String key,final Object value){
		
	     RedisClientUtils.call(new RedisCall<Boolean>() {
	    	 	
	            @Override
	            public Boolean run(Jedis jedis) {
	                jedis.setex(key.getBytes(), EXPIRE_TIME, SerializeUtil.serialize(value));
	                return true;
	            }
	        });
		
	}
	
	/**
	 * @description 获取 对象
	 * @author	baichuan.zheng
	 * @since 2016年8月17日 下午1:49:49
	 */
	public static Object getObject(final String key) {
		return RedisClientUtils.call(new RedisCall<Object>() {
			@Override
			public Object run(Jedis jedis) {
				byte[] resultString = jedis.get(key.getBytes());
				return SerializeUtil.unserialize(resultString);
			}
		}

		);
	}
	
	
	/**
	 * @description 保存 字符串String
	 * @author	baichuan.zheng
	 * @since 2016年8月17日 下午1:50:38
	 */
	public static void set(final String key,final String value){
		System.out.println("@@@@@="+EXPIRE_TIME);
		System.out.println("@@@@@2="+ConfigureSetting.getRedisExpireTime());
	     RedisClientUtils.call(new RedisCall<Boolean>() {
	            @Override
	            public Boolean run(Jedis jedis) {
	                jedis.setex(key, EXPIRE_TIME, value);
	                return true;
	            }
	        });
		
	}
	
	
	/**
	 * @description 获取 字符串
	 * @author	baichuan.zheng
	 * @since 2016年8月17日 下午1:50:49
	 */
	public static String get(final String key) {
		return RedisClientUtils.call(new RedisCall<String>() {
			@Override
			public String run(Jedis jedis) {
				String resultString = jedis.get(key);
				return resultString;
			}
		}

		);
	}
	
	
	
	/**
	 * @description 保存  List<String>
	 * @author	baichuan.zheng
	 * @since 2016年8月17日 下午1:50:38
	 */
	public static void set(final String key,final List<String> list){
		
	     RedisClientUtils.call(new RedisCall<Boolean>() {
	            @Override
	            public Boolean run(Jedis jedis) {
	            	List<String> result = jedis.lrange(key, 0, -1);
	            	jedis.expire(key, EXPIRE_TIME);
	            	if(result != null){
	            		jedis.del(key);
	            	}
	            	for (String string : list) {
						jedis.lpush(key, string);
					}
	                return true;
	            }
	        });
		
	}
	
	
	/**
	 * @description 获取 List<String>
	 * @author	baichuan.zheng
	 * @since 2016年8月17日 下午1:56:49
	 */
	public static List<String> getList(final String key) {
		return RedisClientUtils.call(new RedisCall<List<String>>() {
			@Override
			public List<String> run(Jedis jedis) {
				List<String> result = jedis.lrange(key, 0, -1);
				if(result != null && result.size() != 0){
					Collections.reverse(result);
				}
				return result;
			}
		}

		);
	}
	
	
	/**
	 * @description 删除保存的信息
	 * @author	baichuan.zheng
	 * @since 2016年8月17日 下午2:26:03
	 */
	public static void delete(final String key){
		 RedisClientUtils.call(new RedisCall<Boolean>() {
	            @Override
	            public Boolean run(Jedis jedis) {
	            	
					jedis.del(key);
	                return true;
	            }
	        });
	}
	
	

	
	
	
	public static void main(String[] arg){
		
		
		
		set("aaa", "www");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		set("vvv", "qqq");
		System.out.println(get("aaa"));
		
		
//		User user = new User();
//		
//		user.setUserName("aaa");
//		user.setCreateTime(new Date());
//		user.setDisplayName("不知道");
//		
//		System.out.println(user.getClass().getPackage().getName());
		
		
//		set(user.getUserName(), user);
//		
//		User user2 = null;
//		
//		user2 = (User)getObject(user.getUserName());
//		
//		System.out.println(ToStringBuilder.reflectionToString(user2));
		
		
//		User user2  = new User();
//		user2.setUserName("bbb");
//		user2.setDisplayName("测试");
//		List<User> list = new ArrayList<User>();
//		list.add(user);
//		list.add(user2);
//		set("list", list);
//		List<User> list2 = new ArrayList<User>();
//		list2 = (List<User>)getObject(user.getUserName());
//		System.out.println(list2.get(0));
//		System.out.println(user2.getClass().getSimpleName());
		
		
		
//		List<String> stringList = new ArrayList<String>();
//		
//		stringList.add("aaa");
//		stringList.add("bbb");
//		stringList.add("ccc");
//		
////		for (String string : stringList) {
////			System.out.println("qqq"+string);
////		}
//		set("stringList", stringList);
//		
//		List<String> stringList2 = null;
//		
//		stringList2 = getList("stringList");
//		
//		if(stringList2 != null){
//			for (String string : stringList2) {
//				System.out.println(string);
//			}
//		}
		
		

		
	}


	
	
	
	
	
	
	
	
	
	
}
