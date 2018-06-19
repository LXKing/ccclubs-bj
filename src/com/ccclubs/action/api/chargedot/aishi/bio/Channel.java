package com.ccclubs.action.api.chargedot.aishi.bio;

/**
 * 
 * 数据管道
 * 
 * @author zhangjian
 *
 */
public interface Channel {
	
	/**
	 * 写入
	 * @param obj
	 */
	public void write(Object obj);
	
	/**
	 * 写入并刷入缓冲
	 * @param obj
	 */
	public void writeAndFlush(Object obj);
	
}
