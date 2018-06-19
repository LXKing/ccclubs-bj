package com.ccclubs.helper;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.SrvUser;
import com.lazy3q.web.helper.$;

public class LoginHelper {
	
	public static SrvUser getLogin(){
		return (SrvUser) $.getSession("ccclubs_login");		
	}
	
	public static Long getLoginId(){
		return (Long) $.getSession("ccclubs_login_id");		
	}
	
	/*
	public static Long getHostId(){
		SrvUser login = LoginHelper.getLogin();
		if(login==null)return null;
		return login.getSuHost();
	}*/
}
