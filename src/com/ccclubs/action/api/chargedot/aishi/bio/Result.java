package com.ccclubs.action.api.chargedot.aishi.bio;


public class Result extends Command{
	
	private boolean success;
	
	/**
	 * 返回结果
	 */
	private String result;
	
	/**
	 * 返回结果描述
	 */
	private String text;
	
	/**
	 * 返回代码
	 */
	private Integer code;
	
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
