package com.ccclubs.action.weixin;

import com.lazy3q.web.helper.WebHelper.LzMap;

public class Message {

	
	Boolean success=false;
	String message;	
	
	
	/**
	 * @param success
	 * @param message
	 */
	public Message(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
	
}
