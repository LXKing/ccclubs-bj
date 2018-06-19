package com.ccclubs.action.app.official.util;

import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.helper.redis.tag.APP_OFFICIAL;

/**
 * 
 * 事件栅栏
 * 
 * 例如：在某件事情某个状态内做同步，发送短信提醒会员，只提醒一次
 * 
 * @author joel
 *
 */
public class EventBarrier {
	
	static final DefaultJRedisClient<String, Object> jr = new DefaultJRedisClient<String, Object>(DBIndex.APP_OFFICIAL);
	
	//====================================================================================================
	
	public static boolean has(Reason event){
		try{
			String key = Md5Util.MD5(event.get());
			return jr.get(APP_OFFICIAL.PL_APP_EVENT + key)!=null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static String put(Reason event, long expireTime){
		try{
			String key = Md5Util.MD5(event.get());
			jr.set(APP_OFFICIAL.PL_APP_EVENT  + key, event.get(), expireTime);
			return key;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void remove(String key){
		try{
			jr.del(key);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

