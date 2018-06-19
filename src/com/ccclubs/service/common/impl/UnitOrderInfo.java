package com.ccclubs.service.common.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.config.SYSTEM;


public class UnitOrderInfo {
	
	/**
	 * (理论)需要支付价格
	 */
	Double payNeed = 0d;   		
	
	/**
	 * 实际支付价格
	 */
	Double payReal = 0d;	 		
	
	/**
	 * 里程费
	 */
	Double mileageFee = 0d;		
	
	/**
	 * 时长费
	 */
	Double timeLongFee = 0d;   	
	
	/**
	 * 订单时长（毫秒）
	 */
	Long timeMill = 0l;	  
	
	/**
	 * 订单时长（分钟）
	 */
	Long timeMinute = 0l; 		
	
	/**
	 * 订单公里数
	 */
	Double mileage = 0d;	 
	
	/**
	 * 是否有优惠
	 */
	String promotion = "";
	
	
	public UnitOrderInfo(){
		
	}
	
	public UnitOrderInfo(Date startTime, Date finishTime){
		Long durationMillisecond = finishTime.getTime() - startTime.getTime();
		this.setTimeMill(durationMillisecond);
		this.setTimeMinute(durationMillisecond / SYSTEM.MINUTE.longValue());
	}
	
	public Double getPayNeed() {
		return payNeed;
	}

	public void setPayNeed(Double payNeed) {
		this.payNeed = payNeed;
	}

	public Double getPayReal() {
		return payReal;
	}

	public void setPayReal(Double payReal) {
		this.payReal = payReal;
	}

	public Double getMileageFee() {
		return mileageFee;
	}
	public void setMileageFee(Double mileageFee) {
		this.mileageFee = mileageFee;
	}
	public Long getTimeMill() {
		return timeMill;
	}
	public void setTimeMill(Long timeMill) {
		this.timeMill = timeMill;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Double getTimeLongFee() {
		return timeLongFee;
	}

	public void setTimeLongFee(Double timeLongFee) {
		this.timeLongFee = timeLongFee;
	}

	public Long getTimeMinute() {
		return timeMinute;
	}

	public void setTimeMinute(Long timeMinute) {
		this.timeMinute = timeMinute;
	}
	
	public boolean hasPromotion(){
		return StringUtils.isNotEmpty(promotion);
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

}
