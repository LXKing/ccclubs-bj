package com.ccclubs.helper.redis;

/**
 * DB 索引
 * 
 * @author joel
 * 
 */
public enum DBIndex {
	
	/**
	 * 默认
	 */
	DEFAULT(0),
	
	/**
	 * 微网站OPENID账户信息
	 */
	APP_WEIXIN(1),
	
	/**
	 * 
	 */
	APP_API(2),
	
	/**
	 * 公务车应用
	 */
	APP_OFFICIAL(3),
	
	APP_SITE(4);
	
	int index;
	
	DBIndex(int index){
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
