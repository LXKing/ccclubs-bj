package com.ccclubs.action.app.official.util;

import java.util.Random;

import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;

/**
 * 
 * 分布式锁
 * 
 * @author joel
 *
 */
public class EventLock {
	
	/** 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒 */  
    public static final long MILLI_NANO_CONVERSION 				= 1000 * 1000L;  
    
    public static final Random RANDOM 							= new Random();  
	
	static final DefaultJRedisClient<String, Object> jr 		= new DefaultJRedisClient<String, Object>(DBIndex.APP_OFFICIAL);
	
	private boolean locked									 	= false;
	
	/**
	 * 
	 * @param event
	 * @param timeout 单位：毫秒
	 * @return
	 */
	public boolean tryLock(String reason, long timeout){
		long nano = System.nanoTime();  
	    timeout *= MILLI_NANO_CONVERSION;  
		try{
			String key = Md5Util.MD5(reason);
			while((System.nanoTime() - nano) < timeout){
				if(jr.setnx(key)){
					jr.expire(key, 60 * 5);
					this.locked = true;
					return this.locked;
				}
				// 短暂休眠，避免出现活锁  
                Thread.sleep(3, RANDOM.nextInt(500));  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param event
	 */
    public void unlock(String reason) {  
        try {  
        	String key = Md5Util.MD5(reason);
        	if(this.locked){
        		jr.del(key);  
        	}
        } catch(Exception e){
			e.printStackTrace();
		}
    }  
}

