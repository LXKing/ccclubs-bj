package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 还车信息
 * <p>
 * 包括车牌号，订单号，RFID，经、纬度，里程，油量等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class FurtherCar extends CStruct {

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
	 * Fuc Code 标识位 0x10
	 */
	public byte		mFucCode	= 0x42;
	/**
	 * 经度，整数部分
	 */
	public short	mLongitude;
	/**
	 * 经度，小数部分
	 */
	public int		mLongitudeDecimal;
	/**
	 * 纬度，整数部分
	 */
	public short	mLatitude;
	/**
	 * 纬度，整数部分
	 */
	public int		mLatitudeDecimal;
	/**
	 * 里程
	 */
	public int		mMiles;
	/**
	 * 还车时间
	 */
	public int		mFurtherTime;

	/**
	 * 
	 */
	public FurtherCar() {
		// TODO Auto-generated constructor stub
	}
}
