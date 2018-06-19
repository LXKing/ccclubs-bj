package com.ccclubs.helper;

import java.util.ArrayList;
import java.util.List;

import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsDriver;
import com.ccclubs.model.CsElecHistory;
import com.ccclubs.model.CsState;
import com.ccclubs.service.admin.ICsStateService;
import com.lazy3q.web.helper.$;

/**
 * 业务缓存
 * 
 * @author joel
 *
 */
public class CacheStoreHelper {

	//======================================车辆实时状态缓存=========================================
	static final DefaultJRedisClient<String, CsState> cache_csstate = new DefaultJRedisClient<String, CsState>(DBIndex.APP_API);
	static final String REDIS_KEY__CS_STATE = "csstate-";
	
	//======================================电桩历史状态缓存=========================================
	static final DefaultJRedisClient<String, CsElecHistory> cache_elechistory = new DefaultJRedisClient<String, CsElecHistory>(DBIndex.APP_API);
	static final String REDIS_KEY__CS_ELECHISTORY = "cshty-";
	
	//======================================用户登录授权缓存=========================================
	static final DefaultJRedisClient<String, CsElecHistory> cache_openid = new DefaultJRedisClient<String, CsElecHistory>(DBIndex.APP_API);
	static final String REDIS_KEY__CS_OPENID = "openid-";
	//======================================订单推送订单人员=========================================
	static final DefaultJRedisClient<String, List<CsDriver>> cache_driver = new DefaultJRedisClient<String, List<CsDriver>>(DBIndex.APP_API);
	static final String REDIS_KEY_CS_DRIVER = "driver-";
	/**
	 * 设置车辆实时状态
	 * @param carNumber
	 * @param state
	 */
	public static void setCarState(String carNumber, CsState state){
		try{
			cache_csstate.setValue(REDIS_KEY__CS_STATE+carNumber, state, 5L);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取车辆实时状态
	 * @param carNumber
	 * @return
	 */
	public static CsState getCarState(String carNumber){
		try{
			CsState csstate = cache_csstate.getValue(REDIS_KEY__CS_STATE+carNumber);
			if(csstate==null){
				ICsStateService csstateService = $.GetSpringBean("csStateService");
				CsState new_csstate = csstateService.getCsState($.add(CsState.F.cssNumber, carNumber));
				cache_csstate.setValue(REDIS_KEY__CS_STATE+carNumber, new_csstate);
				return new_csstate;
			}
			return csstate;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 设置充电桩历史状态
	 * @param powerpileId
	 * @param history
	 */
	public static void setElecHistory(String powerpileId, CsElecHistory history){
		try{
			cache_elechistory.setValue(REDIS_KEY__CS_ELECHISTORY+powerpileId, history);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取充电桩历史状态
	 * @param powerpileId
	 * @return
	 */
	public static CsElecHistory getElecHistory(String powerpileId){
		try{
			return cache_elechistory.getValue(REDIS_KEY__CS_ELECHISTORY+powerpileId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 设置登录认证ID
	 * @param openId
	 * @param memberId
	 */
	public static void setLoginOpenId(String openId, String memberId){
		try{
			cache_openid.set(openId, memberId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 取得登录认证ID 
	 * @param openId
	 * @return
	 */
	public static String getLoginOpenId(String openId){
		try{
			return cache_openid.get(openId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 保存订单推送对象
	 * @param csscId 订单ID
	 * @param driverList
	 */
	public static void setDriverList(String csscId,List<CsDriver> driverList){
		try{
			cache_driver.setValue(REDIS_KEY_CS_DRIVER+csscId, driverList,60L * 5);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取订单推送司机
	 * @param powerpileId
	 * @return
	 */
	public static List<CsDriver> getDriverList(String csscId){
		try{
			return cache_driver.getValue(REDIS_KEY_CS_DRIVER+csscId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static List<CsState> getAllCsstate(){
		return null;
	}
	
	public static List<CsState> getAllElecHistory(){
		return null;
	}
	
	
}
