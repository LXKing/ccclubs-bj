package com.ccclubs.action.api.chargedot.icharge;

import com.ccclubs.action.app.official.util.Md5Util;

public class IChargeReq {
	
	public final static String org		= "1003";										//第三方用户标识
	
	public final static String key		= "611225E8745B8EFF88571093CA86826C";			//验证码
	
	private Long timestamp = 0L;														//时间戳（妙级）
	
	public IChargeReq(){
		this.timestamp = System.currentTimeMillis() / 1000;		
	}
	
	public String getToken(){
		String tokenstr = org + key + timestamp.toString().substring(5);
		return new Md5Util().MD5(tokenstr).toLowerCase();
	}

	public static String getOrg() {
		return org;
	}

	public static String getKey() {
		return key;
	}

	public String getTimestamp() {
		return timestamp.toString();
	}
	
}
