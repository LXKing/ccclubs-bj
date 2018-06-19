package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 远程车辆回复的订单信息
 * <p>
 * 包括车牌号，订单号等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class OrderUpStream extends CStruct {

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
	 * Fuc Code 标识位 0x44
	 */
	public byte		mFucCode			= 0x44;
	/**
	 * 订单号
	 */
	public long		mOrderId;
	/**
	 * 输出地址
	 */
	public short	mOutputAddress		= (short) 0x9000;
	/**
	 * 输出数量
	 */
	public short	mQuantityOfOutputs	= (short) 0x0014;

	/**
	 * 
	 */
	public OrderUpStream() {
		// TODO Auto-generated constructor stub
	}
}
