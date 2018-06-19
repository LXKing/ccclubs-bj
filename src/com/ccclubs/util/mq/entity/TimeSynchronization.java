package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 远程时钟同步
 * <p>
 * 包括车牌号，操作号等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class TimeSynchronization extends CStruct {

	/**
	 * 车牌号
	 */
	public @size(8)
	String			mCarNum;
	/**
	 * 消息ID
	 */
	public long		mId;
	/**
	 * Fuc Code 标识位 0x10
	 */
	public byte		mFucCode			= 0x10;
	/**
	 * 输出地址
	 */
	public short	mOutputAddress		= (short) 0xC000;
	/**
	 * 输出数量
	 */
	public short	mQuantityOfOutputs	= (short) 0x0002;
	/**
	 * 车辆当前时间
	 */
	public int		mTime;

	/**
	 * 
	 */
	public TimeSynchronization() {
		// TODO Auto-generated constructor stub
	}

	public TimeSynchronization(String carNum, long id, int time) {
		this.mCarNum = carNum;
		this.mId = id;
		this.mTime = time;
		this.mFucCode = 0x10;
	}
}
