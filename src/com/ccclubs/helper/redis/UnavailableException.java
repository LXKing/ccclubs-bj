package com.ccclubs.helper.redis;

public class UnavailableException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1654204272442070054L;
	
	public UnavailableException(String message){
		super(message);
	}

}
