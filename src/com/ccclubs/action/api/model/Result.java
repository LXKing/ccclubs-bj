package com.ccclubs.action.api.model;

/**
 * 
 * @author zhangjian
 * 
 * 通用调用结果
 *
 */
public class Result {
	
	public Result(){
		
	}
	
	public Result(Integer code){
		this.code = code;
	}
	
	public Result(Boolean result, Integer code){
		this.code = code;
	}
	
	public static Result newSuccess(){
		return new Result().setResult(Boolean.TRUE);
	}
	
	/**
	 * 返回结果
	 */
	private Boolean result = Boolean.FALSE;
	
	/**
	 * 返回消息
	 */
	private String message;
	
	/**
	 * 状态码
	 */
	private Integer code;
	
	/**
	 * 数据
	 */
	private Object data;

	public Boolean getResult() {
		return result;
	}

	public Result setResult(Boolean result) {
		this.result = result;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public Result setCode(Integer code) {
		this.code = code;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Result setData(Object data) {
		this.data = data;
		return this;
	}
	
	
}
