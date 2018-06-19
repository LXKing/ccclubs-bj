package com.ccclubs.action.app.v2;

import com.lazy3q.web.helper.WebHelper.LzMap;

public class AppJsonFrame {
	
	
	public enum ERROR{
		NO_ERROR,
		NO_LOGIN,
		SYSTEM_MESSAGE,
		NEED_UPDATE,
		SYSTEM_ERROR,
		VALID_ERROR
	}
	

	public int error=0;
	public String tips="";
	public String attach="";
	public int login=0;
	public LzMap data=new LzMap();
	
	
	public LzMap addData(String key,Object value){
		return this.data.add(key, value);
	}
	
	public static AppJsonFrame global(ERROR error, String errorTips) {
		return global(error.ordinal(),errorTips);
	}
	
	
	public static AppJsonFrame global(int error, String errorTips) {
		AppJsonFrame jf = new AppJsonFrame();
		jf.error = error;
		jf.tips = errorTips;
		return jf;
	}
	
	
	public static AppJsonFrame message(Boolean bSuccess, String tips) {
		AppJsonFrame jf = new AppJsonFrame();
		jf.data.add("success", bSuccess).add("tips", tips);
		return jf;
	}
	
	
}
