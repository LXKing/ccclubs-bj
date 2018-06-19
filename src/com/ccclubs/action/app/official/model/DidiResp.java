package com.ccclubs.action.app.official.model;

import java.util.Map;

/**
 * 滴滴打车
 * 
 * @author joel 
 *
 */
public class DidiResp {
	
	private Integer errno;
	
	private String errmsg;
	
	private Map<String, Object> data;

	public Integer getErrno() {
		return errno;
	}

	public void setErrno(Integer errno) {
		this.errno = errno;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
