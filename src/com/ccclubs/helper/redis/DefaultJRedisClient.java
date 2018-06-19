package com.ccclubs.helper.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.lazy3q.web.helper.$;

/**
 * redis管理
 * 
 * @author joel
 *
 */
public class DefaultJRedisClient<K, V> implements JRedisClient<K, V>{
	
	private 	JedisPoolConfig		config 		= new JedisPoolConfig();
	private 	String 				host 		= "127.0.0.1";
	private 	Integer 			port 		= 6379;
	private 	String 				password 	= "";
	
	private 	JRedisContext 		context		= null;
	private 	int 				dbindex		= 0;
	
	public DefaultJRedisClient(){
		this(DBIndex.DEFAULT);
	}
	
	public DefaultJRedisClient(DBIndex index){
		this.dbindex = index.getIndex();
		
	    if(!$.empty($.config("release.online"))){
	    	this.host = $.config("redis.serveraddr");
		    this.port = Integer.valueOf($.config("redis.port"));
		    this.password = $.config("redis.requirepass");
    	}else{
    		//默认连接测试服务器
    		this.host = "123.57.46.221";
    		this.port = 6188;
    		this.password = "test";
    	}
	    
		doInit();
	}
	
	/**
	 * 
	 * @param host	服务主机
	 * @param port	服务端口
	 * @param config	配置
	 * @param doInit	是否初始化
	 */
	public DefaultJRedisClient(String host, Integer port, String password, JedisPoolConfig config, DBIndex index){
		this.host = host;
		this.port = port;
		this.password = password;
		this.config = config;
		this.dbindex = index.getIndex();
		doInit();
	}
	
	/**
	 * 初始化
	 */
	public void doInit(){
		if (context == null) {
			context = JRedisHolder.getInstance().getContext(host, port, config);
			if(!context.isInitialized()){
				if(tryConn()){
					context.setInitialized(true);
				}
			}
        }
	}
	
	/**
	 * 执行操作
	 * 
	 * @param operator
	 */
	public <T> T execute(JRedisOperator<T> operator){
		if(operator == null) {
			return null;
		}
		
		JedisPool pool = context.getPool();
		if(pool == null){
			return null;
		}
		
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			if(jedis == null){
				return null;
			}
			
			//if(!context.isInitialized()){
				jedis.auth(getPassword());
			//}
			
			jedis.select(dbindex);
			
			Result<T> r = operator.process(jedis);
			return (r == null ? null : r.getValue());
		}catch(Exception e){
			e.printStackTrace();
			pool.returnBrokenResource(jedis);
			throw new UnavailableException(e.getMessage());
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
	}
	
	/**
	 * 尝试连接
	 * @return
	 */
	public boolean tryConn(){
		
		//测试连接
       Boolean bool = execute(new JRedisOperator<Boolean>() {
			
			@Override
			public Result<Boolean> process(Jedis jedis) {
				
				try{
					jedis.ping();
					jedis.auth(password);
					jedis.select(dbindex);
				}catch(Exception e){
					e.printStackTrace();
					return FAILED;
				}
				return SUCCESS;
			}
			
	   });
      
       return bool;
	}
	
	public String usedb(final DBIndex index){
		String r = execute(new JRedisOperator<String>() {
			
			@Override
			public Result<String> process(Jedis jedis) {
				setDbindex(index.getIndex());
				String value = jedis.select(index.getIndex());
				return new Result<String>(value);
			}
			
		});
		return r;
	}
	
	@Override
	public Long incr(final String key) {
		Long r = execute(new JRedisOperator<Long>() {
			
			@Override
			public Result<Long> process(Jedis jedis) {
				Long value = jedis.incr(key);
				return new Result<Long>(value);
			}
			
		});
		return r;
	}
	
	public boolean setnx(final String key){
		Long r = execute(new JRedisOperator<Long>() {
			
			@Override
			public Result<Long> process(Jedis jedis) {
				Long value = jedis.setnx(key, "1");
				return new Result<Long>(value);
			}
			
		});
		return r!=null && r == 1;
	}
	
	public boolean expire(final String key, final int seconds){
		Long r = execute(new JRedisOperator<Long>() {
			
			@Override
			public Result<Long> process(Jedis jedis) {
				Long value = jedis.expire(key, seconds);
				return new Result<Long>(value);
			}
			
		});
		return r!=null && r == 1;
	}

	@Override
	public Long decr(final String key) {
		Long r = execute(new JRedisOperator<Long>() {
			
			@Override
			public Result<Long> process(Jedis jedis) {
				Long value = jedis.decr(key);
				return new Result<Long>(value);
			}
			
		});
		return r;
	}

	@Override
	public boolean set(final String key, final String value) {
		boolean r = execute(new JRedisOperator<Boolean>() {
			
			@Override
			public Result<Boolean> process(Jedis jedis) {
				String r = jedis.set(key, value);
				return (r!=null && r.equals("OK")) ? SUCCESS : FAILED;
			}
			
		});
		
		return r;
	}

	@Override
	public String get(final String key) {
		String r = execute(new JRedisOperator<String>() {
			
			@Override
			public Result<String> process(Jedis jedis) {
				String r = jedis.get(key);
				return new Result<String>(r);
			}
			
		});
		
		return r;
	}
	
	@Override
	public boolean setValue(K key, V value) {
		final byte[] key_binary = ObjectToByte(key);
		final byte[] value_binary = ObjectToByte(value);
		boolean r = execute(new JRedisOperator<Boolean>() {
			
			@Override
			public Result<Boolean> process(Jedis jedis) {
				String r = jedis.set(key_binary, value_binary);
				return (r!=null && r.equals("OK")) ? SUCCESS : FAILED;
			}
			
		});
		
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V getValue(K key) {
		final byte[] key_binary = ObjectToByte(key);
		byte[] r = execute(new JRedisOperator<byte[]>() {
			@Override
			public Result<byte[]> process(Jedis jedis) {
				byte[] r = jedis.get(key_binary);
				return new Result<byte[]>(r);
			}
			
		});
		if(r!=null){
			return (V)ByteToObject(r);
		}
		return null;
	}

	@Override
	public Set<String> keys(final String keys) {
		Set<String> r = execute(new JRedisOperator<Set<String>>() {
			
			@Override
			public Result<Set<String>> process(Jedis jedis) {
				Set<String> r = jedis.keys(keys);
				return new Result<Set<String>>(r);
			}
			
		});
		
		return r;
	}

	@Override
	public boolean set(final String key, final String value, final Long expireSeconds) {
		boolean r = execute(new JRedisOperator<Boolean>() {
			
			@Override
			public Result<Boolean> process(Jedis jedis) {
				String r = jedis.set(key, value);
				if(expireSeconds!=null){
					jedis.expire(key, expireSeconds.intValue());
				}
				System.out.println(r);
				return SUCCESS;
			}
			
		});
		
		return r;
	}

	@Override
	public boolean setValue(K key, V value,final Long expireSeconds) {
		final byte[] key_binary = ObjectToByte(key);
		final byte[] value_binary = ObjectToByte(value);
		boolean r = execute(new JRedisOperator<Boolean>() {
			
			@Override
			public Result<Boolean> process(Jedis jedis) {
				String r = jedis.set(key_binary, value_binary);
				if(expireSeconds!=null){
					jedis.expire(key_binary, expireSeconds.intValue());
				}
				return (r!=null && r.equals("OK")) ? SUCCESS : FAILED;
			}
			
		});
		
		return r;
	}

	@Override
	public Long del(final String key) {
		Long r = execute(new JRedisOperator<Long>() {
			
			@Override
			public Result<Long> process(Jedis jedis) {
				Long val = jedis.del(key);
				return new Result<Long>(val);
			}
		});
		
		return r;
	}
	
	@Override
	public boolean isExists(final String key) {
		boolean r = execute(new JRedisOperator<Boolean>() {
			
			@Override
			public Result<Boolean> process(Jedis jedis) {
				boolean r = jedis.exists(key);
				return r ? SUCCESS : FAILED;
			}
			
		});		
		return r;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<V> like(K keys) {
		final byte[] key_binary = ObjectToByte(keys);
		List<V> r = execute(new JRedisOperator<List<V>>() {
			
			@Override
			public Result<List<V>> process(Jedis jedis) {
				List<V> rlist = new ArrayList<V>();
				Set<byte[]> r = jedis.keys(key_binary);
				if(r!=null){
					for(byte[] d : r){
						V data = (V)ByteToObject(d);
						rlist.add(data);
					}
				}
				return new Result<List<V>>(rlist);
			}
			
		});		
		
		return r;
	}
	
	public static Object ByteToObject(byte[] bytes) {  
		 Object obj = null;  
		 try {  
		     // bytearray to object  
		     ByteArrayInputStream bi = new ByteArrayInputStream(bytes);  
		     ObjectInputStream oi = new ObjectInputStream(bi);  
		   
		     obj = oi.readObject();  
		     bi.close();  
		     oi.close();  
		 } catch (Exception e) {  
		     System.out.println("translation" + e.getMessage());  
		     e.printStackTrace();  
		     throw new RuntimeException(e);
		 }  
         return obj;  
    }  
	
    public static byte[] ObjectToByte(java.lang.Object obj) {  
        byte[] bytes = null;  
        try {  
            // object to bytearray  
            ByteArrayOutputStream bo = new ByteArrayOutputStream();  
            ObjectOutputStream oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);  
      
            bytes = bo.toByteArray();  
            bo.close();  
            oo.close();  
        } catch (Exception e) {  
            System.out.println("translation" + e.getMessage());  
            e.printStackTrace();  
            throw new RuntimeException(e);
        }  
        return bytes;  
    }  

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public JedisPoolConfig getConfig() {
		return config;
	}

	public void setConfig(JedisPoolConfig config) {
		this.config = config;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDbindex() {
		return dbindex;
	}

	public void setDbindex(int dbindex) {
		this.dbindex = dbindex;
	}
	
}
