package com.ccclubs.action.api.chargedot.aishi.bio;

import java.util.List;
import java.util.Queue;

import com.ccclubs.action.api.chargedot.aishi.bio.BlockTnterruptor.Type;


/**
 * 
 * 数据接收
 * 
 * @author zhangjian
 *  
 *
 */
public interface SocketReceiveHandler {
	
	/**
	 * 设置命令
	 * @param command
	 */
	public SocketReceiveHandler setCommands(Queue<Command> commands);
	
	/**
	 * 消息读取
	 * @param message
	 */
	public List<Result> read(SocketWrapper socket, String message, Type type) throws Exception;
	
}
