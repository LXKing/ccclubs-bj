package com.ccclubs.action.app.official.model;

import com.lazy3q.web.helper.WebHelper.LzMap;

public class JsonFormat {
	
	/**
	 * 处理是否成功
	 */
	private boolean success;
	
	/**
	 * 错误代码
	 */
	private String code;
	
	/**
	 * 错误提示
	 */
	private String text;
	
	/**
	 * 数据
	 */
	private LzMap data = new LzMap();
	
	public static JsonFormat success(){
		return new JsonFormat().setSuccess(true);
	}
	
	public static JsonFormat error(String code, String text){
		JsonFormat jf = new JsonFormat();
		jf.setSuccess(false);
		jf.setCode(code);
		jf.setText(text);
		return jf;
	}
	
	public JsonFormat addValue(String key, Object value){
		this.data.add(key, value);
		return this;
	}

	public boolean isSuccess() {
		
		return success;
	}

	public JsonFormat setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getCode() {
		return code;
	}

	public JsonFormat setCode(String code) {
		this.code = code;
		return  this;
	}

	public String getText() {
		return text;
	}

	public JsonFormat setText(String text) {
		this.text = text;
		return this;
	}

	public LzMap getData() {
		return data;
	}

	public JsonFormat setData(LzMap data) {
		this.data = data;
		return this;
	}
	
}
