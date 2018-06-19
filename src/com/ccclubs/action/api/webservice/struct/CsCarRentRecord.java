package com.ccclubs.action.api.webservice.struct;

import com.ccclubs.util.struct.Ignore;
import com.ccclubs.util.struct.OrderNumber;
import com.ccclubs.util.struct.Struct;

/**
 * 车辆租赁状态数据
 * @author zhangjian
 */

public class CsCarRentRecord extends Struct{
	
	@Ignore(true)
	private String carvin;		//车辆vin码
	
	@OrderNumber(1)
	private byte[] collectDate;	//采集时间
	
	@OrderNumber(2)
	private byte cssRented;		//租赁状态
	
	@OrderNumber(3)
	private byte[] startTime;	//出租开始时间
	
	@OrderNumber(4)
	private byte[] endTime;		//出租截止时间
	
	@OrderNumber(5)
	private byte useType;		//使用性质
	
	@OrderNumber(6)
	public byte[] remain;	//预留

	public byte[] getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(byte[] collectDate) {
		this.collectDate = collectDate;
	}

	public byte[] getStartTime() {
		return startTime;
	}

	public void setStartTime(byte[] startTime) {
		this.startTime = startTime;
	}

	public byte[] getEndTime() {
		return endTime;
	}

	public void setEndTime(byte[] endTime) {
		this.endTime = endTime;
	}
	
	public byte[] getRemain() {
		return remain;
	}

	public void setRemain(byte[] remain) {
		this.remain = remain;
	}

	public byte getCssRented() {
		return cssRented;
	}

	public void setCssRented(byte cssRented) {
		this.cssRented = cssRented;
	}

	public byte getUseType() {
		return useType;
	}

	public void setUseType(byte useType) {
		this.useType = useType;
	}

	public String getCarvin() {
		return carvin;
	}

	public void setCarvin(String carvin) {
		this.carvin = carvin;
	}
	
}
