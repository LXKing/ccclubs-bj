package com.ccclubs.helper.redis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JRedisHolder{
	
	private Map<String, JRedisContext> pools = new HashMap<String, JRedisContext>();
	
	static JRedisHolder instance;
	
	public JRedisHolder(){
		
	}
	
	public static synchronized JRedisHolder getInstance(){
		if(instance == null)instance = new JRedisHolder();
		return instance;
	}
	
	public JRedisContext getContext(String host, Integer port, JedisPoolConfig config){
		
		if(host == null || port == null)return null;
		
		if(pools.get(host+port) == null){
			if(config == null){
	         	config = new JedisPoolConfig();
	            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
	            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	            config.setMaxActive(500);
	            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
	            config.setMaxIdle(5);
	            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
	            config.setMaxWait(1000 * 100);
	            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	            config.setTestOnBorrow(false);
	        }
			JedisPool pool = new JedisPool(config, host, port);
			JRedisContext context = new JRedisContext();
			context.setPool(pool);
			pools.put(host+port, context);
			return context;
		}else{
			return pools.get(host+port);
		}
		
	}
	
}
