package com.ccclubs.action.api.chargedot.aishi.bio;

import java.util.List;

/**
 * 数据管道
 * 
 * @author zhangjian
 *
 */
public interface DataChannel {
	
	/**
	 * 设置（数据接收）阻塞中断器
	 */
	public void addBlockTnterruptor(BlockTnterruptor interruptor);
	
	/**
	 * 初始化
	 * @param socketWrapper
	 */
	public void init(SocketWrapper socketWrapper) throws Exception;
	
	/**
	 * 写入数据
	 * @param message
	 */
	public void write(String message) throws Exception;
	
	/**
	 * 写入数据
	 * @param message
	 */
	public void writeAndFlush(String message) throws Exception;
	
	/**
	 * 销毁数据流
	 */
	public void destroy() throws Exception;
	
	/**
	 * 数据读取
	 */
	public List<Result> read() throws Exception;
	
	/**
	 * 数据读取
	 * @param await
	 */
	public List<Result> read(boolean await) throws Exception;
}
