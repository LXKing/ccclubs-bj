package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 发送给远程车辆的订单信息
 * <p>
 * 包括车牌号，订单号，RFID，经、纬度，里程，油量等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class OrderDownStream extends CStruct {

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
	 * 输出地址 0x9000
	 */
	public short	mOutputAddress		= (short) 0x9000;
	/**
	 * 输出数量 0x0014
	 */
	public short	mQuantityOfOutputs	= (short) 0x0014;
	/**
	 * 订单开始时间
	 */
	public int		mStartTime;
	/**
	 * 订单结束时间
	 */
	public int		mEndTime;
	/**
	 * RFID号
	 */
	public @size(8)
	String			mRfid;
	/**
	 * 授权码
	 */
	public int		mCode;

	/**
	 * 
	 */
	public OrderDownStream() {
		// TODO Auto-generated constructor stub
	}

	public OrderDownStream(String number, Long id, int startTime, int endTime, Long order, String rfid, int code) {
		// TODO Auto-generated constructor stub
		super();
		this.mCarNum = number;
		this.mId = id;
		this.mStartTime = startTime;
		this.mEndTime = endTime;
		this.mOrderId = order;
		this.mRfid = rfid;
		this.mCode = code;
	}
}
