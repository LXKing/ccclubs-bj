package com.ccclubs.action.api.scripts;

import com.lazy3q.web.helper.$;

public class BaseScript {
	
	static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取授权用户ID
	 * @return
	 */
	public static Long getOauth(){
		Long oauth = $.getSession("oauth");
		if(oauth != null){
			return oauth;
		}
		return null;
	}
	
	
}
