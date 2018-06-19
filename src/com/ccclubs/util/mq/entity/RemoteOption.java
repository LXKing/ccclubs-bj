package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

public class RemoteOption extends CStruct{

	/**
	 * 车牌号
	 */
	public @size(8) String number;
	/**
	 * 消息ID,对应的数据记录ID
	 */
	public long id;
	/**
	 * [远程开门,远程关门,远程允许启动,远程禁止启动(断油电)] 0x05
	 * [远程初始化] 0x06
	 */
	public byte function;
	/**
	 * [远程开门，远程关门] 0x1000 
	 * [允许启动,禁止启动] 0x1004 
	 * [远程初始化] 0x0006 
	 */
	public short address;
	/**
	 * [远程开门] 0xFF00
	 * [远程关门] 0x0000
	 * [允许启动] 0xFF00
	 * [禁止启动] 0x0000
	 * [远程初始化] 0x0000
	 */
	public short value;
	
}
