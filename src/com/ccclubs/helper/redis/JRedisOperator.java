package com.ccclubs.helper.redis;

import redis.clients.jedis.Jedis;

/**
 * 操作
 * 
 * @author joel
 *
 * @param <T>
 */
public interface JRedisOperator<T> {
	
	/**
	 * 成功
	 */
	public static final Result<Boolean> SUCCESS = new Result<Boolean>(true);
	
	/**
	 * 失败
	 */
	public static final Result<Boolean> FAILED = new Result<Boolean>(false);
	
	/**
	 * @param jedis
	 * @return
	 */
	public Result<T> process(Jedis jedis);
	
}
