package com.ccclubs.action.api.chargedot.CMDT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ccclubs.helper.APICallHelper;

/**
 * 
 * 中移德电电桩数据开放接口
 * 
 * 先调用授权 -》 使用授权码调用接口
 * 
 * @author Joel
 *
 */
public class CMDTChargingPileApi{

	final static String SERVER_PATH = "https://dev03.virtueit.net/"; 
	//final static String SERVER_PATH = Env.isOnline()? "http://open.teld.cn/" : "http://api.wyqcd.cn:8004/";
	
	static String 	authKey 		     = "";											//授权码
	static String   refreshToken 	 	 = ""; 
	static String   scope 	 			 = ""; 
	static Date 	lastOauthTime 		 = new Date();         							//最后授权时间
	static int 		expireTimeLen        = 0;                  							//过期时间（单位：秒）
	
	/**
	 * 获取请求授权KEY
	 */
	public static void getAuthKey(){
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("grant_type", "password");
		msg.put("username", "CARDAN/chefenxiang");
		msg.put("password", "2i6BxFLg0SDyin14EXb0");
		msg.put("scope", "chargingBill");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Basic NU84TTFtMmVhRE5pb0F3azJhbEhvNWxRNkdnYTp6d3d3enNmRG9RYWZBa1NBaW1sNUh1Zlg1eHdh");
		String result = APICallHelper.doPostSSL(SERVER_PATH + "oauth2/token", msg, headers);
		System.out.println(result);
		if(result!=null && result.indexOf("access_token")!=-1){
			JSONObject jsonObj = JSONObject.fromObject(result);
			refreshToken = jsonObj.getString("refresh_token");
			authKey = jsonObj.getString("access_token");
			scope = jsonObj.getString("scope");
			expireTimeLen = jsonObj.getInt("expires_in");
			lastOauthTime = new Date();
		}
	}
	
	public static Map<String, String> getAuthHeader(){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer "+authKey);
		return headers;
	}
	
	/**
	 * 检测授权是否已过期，如果过期，则重新获取
	 */
	public static void before(){
		if(expireTimeLen <=0 || (new Date().getTime() - lastOauthTime.getTime())/1000 <=expireTimeLen-10){
			getAuthKey();
		}
	}
	
	/**
	 * 检测结果中的异常情况
	 */
	public static void after(CMDTResp resp){
		System.out.println(resp);
		if(resp !=null){
			if(!resp.isSuccess()){
				Logger.getRootLogger().error(resp.getFailureMsg());
			}
		}
	}
	
	/**
	 * 获取充电订单列表
	 * @return
	 */
	public static CMDTResp getChargingBills(){
		before();
		
		String result = APICallHelper.doGet(SERVER_PATH+"services-ext/chargingBill/retrieve", getAuthHeader(), true);
		System.out.println(result);
		CMDTResp resp = APICallHelper.fromJson(result, CMDTResp.class);
		
		after(resp);
		return resp;
	}
	
	public static void main(String[] args) throws Exception {
		getChargingBills();
	}
}
