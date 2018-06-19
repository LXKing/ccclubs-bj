package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 修改车辆当前订单信息
 * 
 * @author qsxiaogang
 * 
 */
public class OrderModify extends CStruct {

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
	 * Fuc Code 标识位 0x53
	 */
	public byte		mFucCode			= 0x53;
	/**
	 * 订单开始时间
	 */
	public int		mStartTime;
	/**
	 * 订单结束时间
	 */
	public int		mEndTime;

	/**
	 * 
	 */
	public OrderModify() {
		// TODO Auto-generated constructor stub
	}
}
