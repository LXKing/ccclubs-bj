package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 取车信息确认回复
 * <p>
 * 包括车牌号，订单号等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class TakeCarReply extends CStruct {

	/**
	 * 车牌号
	 */
	public @size(8)
	String			mCarNum;
	/**
	 * 订单号
	 */
	public long		mOrderId;
	/**
	 * Fuc Code 标识位 0x45
	 */
	public byte		mFucCode			= 0x45;
	/**
	 * 输出地址
	 */
	public short	mOutputAddress		= (short) 0x9000;
	/**
	 * 输出数量
	 */
	public short	mQuantityOfOutputs	= (short) 0x000C;

	/**
	 * 
	 */
	public TakeCarReply(String carNum, Long orderId) {
		// TODO Auto-generated constructor stub
		this.mCarNum = carNum;
		this.mOrderId = orderId;
	}
}
