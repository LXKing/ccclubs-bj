package com.ccclubs.action.app.official.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lazy3q.web.helper.$;

/**
 * 
 * 极光推送账户配置
 * 
 * @author joel
 *
 */
public class JpushConfig {
	
	private String appKey;
	
	private String  appSecret;
	
	/**
	 * 用户端
	 */
	public final static JpushConfig OFC_APP = new JpushConfig($.config("jpush.appKey"), $.config("jpush.masterSecret"));
	
	/**
	 * 司机端
	 */
	public final static JpushConfig OFC_DRIVER_APP = new JpushConfig($.config("jpush.driver.appkey"), $.config("jpush.driver.appsecret"));
	
	public JpushConfig(String appKey, String appSecret){
		this.appKey = appKey;
		this.appSecret = appSecret;
		
		if(StringUtils.isEmpty(appKey) || StringUtils.isEmpty(appSecret)){
			Logger.getRootLogger().error("极光推送配置异常!");
			
			if(Env.isTest()){
				/**
				 * 北京出行测试的
				 */
				this.appKey = "88dd78af73dcb9dd27e028f6";
				this.appSecret = "872f0e9c262b7ed155cdb1f7";
				return;
			}else{
				/**
				 * 北京出行正式的
				 */
				this.appKey = "3aa095f7180ea39c6d8f49c4";
				this.appSecret = "d1d3dfea7b64de009f471c38";
				return;
			}
		}
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}
	
}
