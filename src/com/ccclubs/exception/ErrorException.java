package com.ccclubs.exception;

import java.util.Date;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsError;
import com.ccclubs.model.CsNotice;
import com.ccclubs.service.admin.ICsNoticeService;
import com.lazy3q.web.helper.$;

/**
 * 错误异常
 * @author uiu
 * 如：new ErrorException(ErrorException.ErrorType.运维错误,"车牌号码格式错误","车牌号码格式错误,理论上车牌号中应该存在一个数字");
 * 如果致命错误，建议throw
 */
public class ErrorException extends RuntimeException {		
	public ErrorException(final String flag,final String title,final String descript){
		super(descript);
		sendError(flag,title,descript);
	}
	
	
	public ErrorException(final String flag,final String title,final Exception exception){
		super(exception.toString());
		String  descript=exception.toString();
		StackTraceElement[] stackTraces = exception.getStackTrace();
		for(StackTraceElement ste:stackTraces)
			descript+=ste.toString()+"\r\n";
		sendError(flag,title,descript);
	}
	
	/**
	 * 发送错误信息
	 * @param flag
	 * @param title
	 * @param descript
	 */
	private void sendError(final String flag,final String title,final String descript){
		try{
			new Thread(){
				public void run(){
					new CsError(
						title//错误标题 [非空]
					 	,descript//错误内容
					 	,flag//标识
					 	,new Date()//修改时间 [非空]
					 	,new Date()//添加时间 [非空]
					 	,(short)0//状态 [非空]
					 ).save();
				}			
			}.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private ErrorException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	private ErrorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	private ErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	private ErrorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
