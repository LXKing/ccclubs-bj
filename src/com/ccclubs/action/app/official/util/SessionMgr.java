package com.ccclubs.action.app.official.util;

import java.util.HashMap;
import java.util.Map;

import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.helper.redis.tag.APP_OFFICIAL;

/**
 * 基于授权协议的session管理
 * 结构：
 *         key：tag + 授权token
 *         value: {
 *                   key1:xxx,
 *                   key2:xxx
 *                }
 * 
 * @author joel
 *
 */
public class SessionMgr {
	
	public static final Long SESSION_LIFE_CYCLE = 60L * 60 * 24 * 7;	//7天过期

	static final DefaultJRedisClient<String, Object> jr = new DefaultJRedisClient<String, Object>(DBIndex.APP_OFFICIAL);
	
	public static void set(String key, String obj, long expireTime){
		jr.set(key, obj, expireTime);
	}
	
	public static void del(String key){
		jr.del(key);
	}
	
	public static String get(String key){
		return jr.get(key);
	}
	
	/**
	 * 获取session存储
	 * @param sessionToken  即一级key
	 * @return
	 */
	public static Map<String, Object> getSessionStore(String sessionToken){
		Map<String, Object> val = (Map<String, Object>) jr.getValue(APP_OFFICIAL.PL_APP_MEMBER_SESSION + sessionToken);
		return val;
	}
	
	/**
	 * 获取session
	 * @param sessionToken
	 * @param key
	 * @return
	 */
	public static <T> T get(String sessionToken, String key){
		Map<String, Object> val = getSessionStore(sessionToken);
		if(val !=null){
			Object v =  val.get(key);
			if(v !=null)return (T)v;
		}
		return null;
	}
	
	/**
	 * 放入session
	 * @param sessionToken
	 * @param key
	 * @param obj
	 */
	public static void put(String sessionToken, String key, Object obj){
		Map<String, Object> val = getSessionStore(sessionToken);
		if(val == null) val = new HashMap<String, Object>();
		val.put(key, obj);
		jr.setValue(APP_OFFICIAL.PL_APP_MEMBER_SESSION + sessionToken, val, SESSION_LIFE_CYCLE); 
	}
	
	/**
	 * 移除session
	 * @param sessionToken
	 * @param key
	 * @param obj
	 */
	public static void remove(String sessionToken, String key){
		Map<String, Object> val = getSessionStore(sessionToken);
		if(val != null) {
			Object obj = val.get(key);
			if(obj!=null){
				val.remove(key);
				jr.setValue(APP_OFFICIAL.PL_APP_MEMBER_SESSION + sessionToken, val, SESSION_LIFE_CYCLE); //7天过期
			}
		}
	}
	
	public static void clearSession(String sessionToken){
		jr.setValue(APP_OFFICIAL.PL_APP_MEMBER_SESSION + sessionToken, null);
	}
	
}
