package com.ccclubs.action.thread.sms;

public abstract class BaseSMS {

	//短信余额查询
	public abstract String send(String mobile,String content);
	
	//余量查询
	public abstract String query();
	
}
