package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 取车信息
 * <p>
 * 包括车牌号，订单号，RFID，取车时间等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class TakeCar extends CStruct {

	/**
	 * 车牌号
	 */
	public @size(8)
	String			mCarNum;								
	/**
	 *  订单号
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
	 * 实际取车时间
	 */
	public int		mTakeTime;								
	/**
	 * RFID号
	 */
	public @size(8)
	String			mRfid;									

	/**
	 * 
	 */
	public TakeCar() {
		// TODO Auto-generated constructor stub
	}
}
