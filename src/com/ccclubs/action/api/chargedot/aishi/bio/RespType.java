package com.ccclubs.action.api.chargedot.aishi.bio;

/**
 * 结果类型
 * 
 * 
 * @author zhangjian
 *
 */
public enum RespType {
	/**
	 * 连接
	 */
	CONN,			
	
	/**
	 * 登陆
	 */
	LOGIN,			
	
	/**
	 * 绑定设备
	 */
	JOIN,			
	
	/**
	 * 查询充电
	 */
	QUERYELE,		
	
	/**
	 * 查询状态
	 */
	QUERYSTU,		
	
	/**
	 * 断开连接
	 */
	KICK,			
	
	/**
	 * 未知
	 */
	UNKNOW,
	
	/**
	 * 预约充电
	 */
	TIMING,
	
	/**
	 * 预约取消
	 */
	CANCELTIMING,
	
	/**
	 * 立即充电
	 */
	CHARGEUP,
	
	/**
	 * 暂停充电
	 */
	CHARGEPAUSE,
	/**
	 * 停止充电
	 */
	CHARGESTOP,
	
	/**
	 * 开关电子锁
	 */
	ELOCK
	
	
}
