package com.ccclubs.action.app.v1;

import com.lazy3q.web.helper.WebHelper.LzMap;

public class JsonFormat {

	public int error=0;
	public String tips="";
	public String attach="";
	public int login=0;
	public LzMap data=new LzMap();
	
	
	public static JsonFormat global(int error, String errorTips) {
		JsonFormat jf = new JsonFormat();
		jf.error = error;
		jf.tips = errorTips;
		return jf;
	}
	
	
	public static JsonFormat message(Boolean bSuccess, String tips) {
		JsonFormat jf = new JsonFormat();
		jf.data.add("success", bSuccess).add("tips", tips);
		return jf;
	}
	
	
}
