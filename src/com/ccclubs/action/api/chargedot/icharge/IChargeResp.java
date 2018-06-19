package com.ccclubs.action.api.chargedot.icharge;


public class IChargeResp<T> {
	
	private int status;
	
	private String msg;
	
	private T data;
	
	public IChargeResp(){
		
	}
	
	public final static IChargeResp<Object> FAIL = new IChargeResp<Object>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean success(){
		return status == 100;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
