package com.ccclubs.action.api.chargedot.icharge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.app.official.util.Env;
import com.ccclubs.helper.APICallHelper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * 
 * 爱充电桩数据开放接口
 * 
 * 先调用授权 -》 使用授权码调用接口
 * 
 * @author Joel
 *
 */
public class IChargeApi {
	
	final static String SERVER_PATH 	= Env.isOnline()? "http://115.236.3.66:88/html/comapi.do" : "http://115.236.3.66:88/html/comapi.do";
	
	public final static String org		= "1003";										//第三方用户标识
	public final static String key		= "B03F5E107359E2B609B96727EFAE8111";			//验证码
	
	
	/**
	 * 用户初始化
	 */
	public static IChargeResp initUser(String phone){
		IChargeReq req = new IChargeReq();
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("method", "initUser");
		msg.put("org", req.getOrg());
		msg.put("time", req.getTimestamp());
		msg.put("token", req.getToken());
		msg.put("userPhone", phone);
		String result = APICallHelper.doPost(SERVER_PATH, msg);
		if(result!=null){
			IChargeResp resp = APICallHelper.fromJson(result, IChargeResp.class);
			return resp;
		}
		return null;
	}
	
	/**
	 * 电站、电桩基本信息
	 */
	public static IChargeResp getEpList(){
		IChargeReq req = new IChargeReq();
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("method", "getEpList");
		msg.put("org", req.getOrg());
		msg.put("time", req.getTimestamp());
		msg.put("token", req.getToken());
		msg.put("cityCode", "");
		msg.put("reqTime", "");				//格式：2000-01-01 00:00:00
		String result = APICallHelper.doPost(SERVER_PATH, msg);
		
		if(result!=null && result.equals("user invalid")){
			Logger.getRootLogger().error(result);
			return null;
		}
		
		if(result!=null){
			 Gson gson = new Gson();
			 IChargeResp<ArrayList<Elec>> resp  = gson.fromJson(result, new TypeToken<IChargeResp<ArrayList<Elec>>>(){}.getType());
			 return resp;
		}
		return null;
	}
	
	public static void main(String[] args) {
		IChargeResp resp = null; 
//		resp = initUser("15656666666");
//		System.out.println(resp.getMsg());
		
		resp = getEpList();
		System.out.println(resp.getMsg());
	}
	
	
}
