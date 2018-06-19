package com.ccclubs.action.api.chargedot.aishi.bio;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

/**
 * 
 * socket包装类
 * 
 * @author zhangjian
 *
 */
public interface SocketWrapper {
	
	/**
	 * 超时时间
	 * @param time
	 * @return
	 */
	public SocketWrapper setTimeout(int time);
	
	/**
	 * 设置数据管道，请设置线程安全实例
	 * @param channel
	 */
	public SocketWrapper setDataChannel(DataChannel channel);
	
	/**
	 * 设置读取，请设置线程安全实例
	 * @param readHandler
	 */
	public SocketWrapper setReadHandler(SocketReceiveHandler readHandler);
	
	/**
	 * 获取读取
	 * @return
	 */
	public SocketReceiveHandler getReadHandler();
	
	/**
	 * 设置主机
	 * @param addr
	 */
	public SocketWrapper setAddr(InetSocketAddress addr);
	
	/**
	 * 获取socket
	 * @return
	 */
	public Socket getSocket() throws Exception;
	
	/**
	 * 发送
	 * @param message
	 * @return
	 */
	public SocketWrapper send(Command cmd) throws Exception;
	
	/**
	 * 返回当前正在执行的命令
	 * @return
	 */
	public Command getCommand();
	
	/**
	 * 接收
	 * @return
	 */
	public List<Result> receive() throws Exception;
	
	/**
	 * 接收
	 * @return
	 */
	public List<Result> receive(boolean await) throws Exception;
	
	/**
	 * 初始化
	 */
	public void init() throws Exception;
	
	/**
	 * 关闭
	 */
	public void close() throws Exception;
	
}
