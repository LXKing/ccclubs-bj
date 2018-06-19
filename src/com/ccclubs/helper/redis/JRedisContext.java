package com.ccclubs.helper.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JRedisContext {
	
	private JedisPool pool;
	
	private boolean initialized = false;
	
	public Jedis getJedis(){
		return null;
	}

	public JedisPool getPool() {
		return pool;
	}

	public JRedisContext setPool(JedisPool pool) {
		this.pool = pool;
		return this;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
}
