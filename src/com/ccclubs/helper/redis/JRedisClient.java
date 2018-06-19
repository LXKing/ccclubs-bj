package com.ccclubs.helper.redis;

import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * 
 * @author joel
 *
 */
public interface JRedisClient<K, V>{
	
	/**
	 * 递增
	 * @param key
	 * @return
	 */
	public Long incr(String key);
	
	/**
	 * 递减
	 * @param key
	 * @return
	 */
	public Long decr(String key);
	
	/**
	 * 存入值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value);
	
	/**
	 * 存入值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setValue(K key, V value);
	
	/**
	 * 存入值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value, Long expireSeconds);
	
	/**
	 * 存入值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setValue(K key, V value, Long expireSeconds);

	/**
	 * 取值
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	public V getValue(K key);
	
	/**
	 * 删除
	 * @param key
	 * @return
	 */
	public Long del(String key);
	
	/**
	 * 匹配key
	 * @param keys
	 * @return
	 */
	public Set<String> keys(final String keys);	
	
	
	/**
	 * 是否存在key
	 * @param key
	 * @return
	 */
	public boolean isExists(final String key);
	
	/**
	 * 匹配
	 * @param key
	 * @return
	 */
	public List<V> like(K key);
	
	/**
	 * set if not exists
	 * @param key
	 * @return
	 */
	public boolean setnx(final String key);
	
	/**
	 * set expire with key
	 * @param key
	 * @param seconds
	 * @return
	 */
	public boolean expire(final String key, final int seconds);
	

}
