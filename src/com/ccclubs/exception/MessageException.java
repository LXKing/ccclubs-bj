package com.ccclubs.exception;

public class MessageException extends RuntimeException {
	
	/**
	 * 错误代码
	 */
	public int code = 0;
	/**
	 * 扩展代码
	 */
	public int codeEx = 0;
	
	
	/*public MessageException(String message){
		super(message);
	}*/
	
	public MessageException(String message,int code){
		super(message);
		this.code = code;
	}
	
	public MessageException(ErrorCode errorCode){
		super(errorCode.message);
		this.code = errorCode.code;
	}
	

	public MessageException(ErrorCode errorCode, String defineMessage) {
		super(defineMessage);
		this.code = errorCode.code;
	}

	public MessageException(ErrorCode errorCode, int codeEx, String message) {
		super(message);
		this.code = errorCode.code;
		this.codeEx = codeEx;
	}

	/**
	 * 错误代码
	 * 100到200之间为系统异常
	 * 200到300之间为业务异常
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 错误代码
	 * 100到200之间为系统异常
	 * 200到300之间为业务异常
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
