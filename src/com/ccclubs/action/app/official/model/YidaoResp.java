package com.ccclubs.action.app.official.model;

import java.util.Map;

/**
 * 易到专车
 * 
 * @author joel 
 *
 */
public class YidaoResp {
	
	private Integer code;
	
	private String msg;
	
	private Map<String, Object> result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
}
