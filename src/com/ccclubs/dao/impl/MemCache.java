package com.ccclubs.dao.impl;

import com.lazy3q.sql.redis.RedisSupport;


public class MemCache {
	
	private static final String redisFlag = "ccclubs.redis.0";	
	
	public static Integer defaultSeconds(){
		return 7200;
	}
	
	public static String getClassName(Class modelClass){
		return modelClass.getName().replaceAll("[^\\.]*\\.", "");
	};
	
	/**
	 * 对象缓存
	 * @param string
	 * @param spId
	 * @param keyValue
	 */
	public static Object setObject(Class modelClass, Object key, Object value) {
		RedisSupport.getRedis(redisFlag).setObject(getClassName(modelClass),""+key,value);
		return value;
	}
	public static Object setObject(Class modelClass, Object key, Object value,int seconds) {
		RedisSupport.getRedis(redisFlag).setObject(getClassName(modelClass),""+key,value,seconds);
		return value;
	}
	public static <T> T getObject(Class modelClass, Object key) {		
		return RedisSupport.getRedis(redisFlag).getObject(getClassName(modelClass),""+key);
	}
	public static Long deleteObject(Class modelClass, Object key) {
		return RedisSupport.getRedis(redisFlag).deleteObject(getClassName(modelClass),""+key);
	}
	public static Long clearObject(Class modelClass) {
		return RedisSupport.getRedis(redisFlag).clearObject(getClassName(modelClass));
	}
	
	
	/**
	 * 键值缓存
	 * @param string
	 * @param spId
	 * @param keyValue
	 */
	public static String setValue(Class modelClass, Object key, String value) {
		return RedisSupport.getRedis(redisFlag).setValue(getClassName(modelClass),""+key,value);
	}
	public static String setValue(Class modelClass, Object key, String value,int seconds) {
		return RedisSupport.getRedis(redisFlag).setValue(getClassName(modelClass),""+key,value,seconds);
	}
	public static String getValue(Class modelClass, Object key) {		
		return RedisSupport.getRedis(redisFlag).getValue(getClassName(modelClass),""+key);
	}
	public static Long deleteValue(Class modelClass, Object key) {		
		return RedisSupport.getRedis(redisFlag).deleteValue(getClassName(modelClass),""+key);
	}
	public static Long clearValue(Class modelClass) {
		return RedisSupport.getRedis(redisFlag).deleteValue(getClassName(modelClass));
	}
	public static String kv(Class modelClass, Object key, String value) {
		return RedisSupport.getRedis(redisFlag).setValue(getClassName(modelClass),""+key,value);
	}
	public static String kv(Class modelClass, Object key) {		
		return RedisSupport.getRedis(redisFlag).getValue(getClassName(modelClass),""+key);
	}
	
	
}