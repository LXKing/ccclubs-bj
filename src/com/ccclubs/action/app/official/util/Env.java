package com.ccclubs.action.app.official.util;

import org.apache.commons.lang.StringUtils;

import com.lazy3q.web.helper.$;

/**
 * 
 * 运行环境
 * 
 * @author joel
 *
 */
public class Env {
	
	private final String env;
	
	private static Env instance;
	
	
	/**
	 * dev本地环境 
	 */
	final static String local = "local";
	
	/**
	 * 测试环境
	 */
	final static String test = "test";
	
	/**
	 * 线上环境
	 */
	final static String online = "online";
	
	public static Env getInstance(){
		if(instance == null) instance = new Env();
		return instance;
	}
	
	public Env(){
		String environment = $.config("environment");environment = StringUtils.isEmpty(environment)?"local":environment;
		env = environment;
	}
	
	public static String getEnv(){
		return getInstance().env;
	}
	
	public static boolean isLocal(){
		return getEnv().equals(local);
	}
	
	public static boolean isTest(){
		return getEnv().equals(test);
	}
	
	public static boolean isOnline(){
		return getEnv().equals(online);
	}
}
