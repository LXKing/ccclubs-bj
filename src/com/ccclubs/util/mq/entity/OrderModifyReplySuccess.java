package com.ccclubs.util.mq.entity;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 订单修改信息确认回复
 * <p>
 * 包括车牌号，订单号等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class OrderModifyReplySuccess extends CStruct {

	/**
	 * 车牌号
	 */
	public @size(8)
	String		mCarNum;
	/**
	 * 订单号
	 */
	public long	mOrderId;
	/**
	 * Fuc Code 标识位 0x53
	 */
	public byte	mFucCode	= 0x53;
	/**
	 * 订单结束时间
	 */
	public int		mEndTime;
	
	public OrderModifyReplySuccess() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public OrderModifyReplySuccess(String carNum, Long orderId,int endTime) {
		// TODO Auto-generated constructor stub
		this.mCarNum = carNum;
		this.mOrderId = orderId;
		this.mEndTime = endTime;
	}
}
