package com.ccclubs.action.admin.csm;

import java.util.List;

import com.lazy3q.web.helper.$;

public class CsmqAction {
	
	public String execute(){
		return "index";
	}
	
	
	/**
	 * 发送消息
	 * @return
	 */
	public String send_message(){
		try{
			
			
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			return $.SendAjax($.add("success", false), $.UTF8);
		}
		return null;
	}
	
	
	/**
	 * 获取消息
	 * @return
	 */
	public String get_message(){
		try{
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			return $.SendAjax($.add("success", false), $.UTF8);
		}
		return null;
	}
	
	
	/**
	 * 获取在线用户
	 * @return
	 */
	public String get_members(){
		try{
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			return $.SendAjax($.add("success", false), $.UTF8);
		}
		return null;
	}

}
