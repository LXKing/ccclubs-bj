package com.ccclubs.util.mq.entity;

import com.lazy3q.web.helper.$;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 车辆订单当前消费金额
 * 
 * @author qsxiaogang
 * 
 */
public class OrderAmount extends CStruct {

	/**
	 * 车牌号
	 */
	public @size(8)
	String			mCarNum;
	/**
	 * 消息ID,对应的数据记录ID
	 */
	public long		mId;
	/**
	 * Fuc Code 标识位 0x10
	 */
	public byte		mFucCode			= 0x10;
	/**
	 * 输出地址 0x9000
	 */
	public short	mOutputAddress		= (short) 0x9018;
	/**
	 * 输出数量 0x0014
	 */
	public short	mQuantityOfOutputs	= (short) 0x0002;
	/**
	 * 消费金额，整数部分
	 */
	public short	mAmount;
	/**
	 * 消费金额，小数部分
	 */
	public short	mAmountDecimal;

	public OrderAmount() {

	}
	
	public OrderAmount(String carNum, Long id, double amount) {
		this.mCarNum = carNum;
		this.mId = id;
		String amountString = $.digit(amount, 2);
		String[] array = amountString.split("\\.");
		this.mAmount = Short.parseShort(array[0]);
		this.mAmountDecimal = Short.parseShort(array[1]);
	}
}
