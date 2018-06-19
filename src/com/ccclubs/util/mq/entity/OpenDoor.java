package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

public class OpenDoor extends CStruct {

	public @size(8) String number;
	public long id;//消息ID
	public byte fc=0x05;//function
	public byte fc33=0x00;//function
	public short oa=0x1000;//4096;//0x1000;//Output Address; 0x1000:开关门  0x1004:禁止允许启动
	public short ov=(short) 0xff00;//(short) 0xff00;//-256;//Output Value;
	
	/**
	 * 
	 */
	public OpenDoor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param number
	 * @param id
	 */
	public OpenDoor(String number, Long id) {
		super();
		this.number = number;
		this.id = id;
	}
	
}
