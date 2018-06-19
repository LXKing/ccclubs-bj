package com.ccclubs.action.app.official.util;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.model.CsDriver;

public class DriverOauthUtils {

	
	static final String  driverid = "pldri_uid";
	
	public static boolean validLogin(String sessionToken){
		return getSession(sessionToken)!=null;
	}
	
	/**
	 * 登录校验
	 * @param sessionToken
	 * @return
	 */
	public static Long getSession(String sessionToken){
		String driverID = SessionMgr.get(sessionToken, driverid);
		if(StringUtils.isNotEmpty(driverID)){
			return Long.valueOf(driverID);
		}
		return null;
	}
	
	/**
	 * 获取权限用户
	 * @param sessionToken
	 * @return
	 */
	public static CsDriver getOauth(String sessionToken){
		Long driverId = getSession(sessionToken);
		if(driverId!=null){
			CsDriver driver = CsDriver.get(driverId);
			return driver;
		}
		return null;
	}
	
	
	/**
	 * 保存用户token
	 * @param driverID
	 * @param sessionToken
	 */
	public static void saveToken(String driverID, String sessionToken){
		SessionMgr.put(sessionToken, driverid, driverID);
	}
	
	/**
	 * 删除用户token
	 * @param driverID
	 * @param sessionToken
	 */
	public static void removeToken(String sessionToken){
		SessionMgr.remove(sessionToken, driverid);
	}
	

}
