package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 订单附加信息确认回复
 * <p>
 * 包括车牌号，订单号等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class OrderDetailReply extends CStruct {

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
	 * Fuc Code 标识位 0x51
	 */
	public byte		mFucCode			= 0x52;
	/**
	 * 订单号
	 */
	public long		mOrderId;
	/**
	 * 输出地址
	 */
	public short	mOutputAddress		= (short) 0x9020;
	/**
	 * 输出数量
	 */
	public short	mQuantityOfOutputs	= (short) 0x000A;

	public OrderDetailReply() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public OrderDetailReply(String carNum, Long orderId) {
		// TODO Auto-generated constructor stub
		this.mCarNum = carNum;
		this.mOrderId = orderId;
	}
}
