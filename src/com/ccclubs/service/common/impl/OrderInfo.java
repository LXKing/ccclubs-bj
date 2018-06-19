package com.ccclubs.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.exception.ErrorCode;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsUseRecord;

public class OrderInfo {
	
	/**
	 * 备注：
	 * price：是实际支付费用
	 * discountAmount：活动减免的费用
	 * payNeed=price+discountAmount
	 */
	
	Double price=0d;//总费用
	Double margin=0d;//保证金
	Double feeee=0d;//(预计)油电里程费
	List<CsOrderDetail> details;
	List<CsUseRecord> gifts=new ArrayList();
	Double usecoin=0d;//使用的红包
	Double cancoin=0d;//可用的红包
	Double freehour=0d;//使用的免费小时	
	Double onehours=0d;//零散小时数
	Boolean longorder = false;//是否长单
	Long feetype = null;
	Boolean conflict = false;//订单是否与其它订单冲突
	double discountAmount = 0d;	//减钱
	String promotionText = "";
	
	Long time = 0l;//订单总时长（毫秒）
	
	/**
	 * 电里程费
	 */
	Double kilom = 0d;
	
	/**
	 * 油里程费
	 */
	Double mileage= 0d;

	/**
	 * 租金费用
	 */
	Double rent= 0d;

	/**
	 * 免责费用  
	 */
	Double insure= 0d;

	/**
	 * 超时费用  
	 */
	Double timeout= 0d;

	/**
	 * 陪驾费用
	 */
	Double driver= 0d;
	
	public Map<String,Double> prices=new HashMap();//费用明细
	
	public List<String> errors=new ArrayList();//错误信息
	
	public void addError(String strError){
		this.errors.add(strError);
	}
	
	public Double getPayNeed(){
		return this.price + this.discountAmount;
	}
	
	public List<CsOrderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<CsOrderDetail> details) {
		this.details = details;
	}
	public List<CsUseRecord> getGifts() {
		return gifts;
	}
	public void setGifts(List<CsUseRecord> gifts) {
		this.gifts = gifts;
	}
	public Double getFreehour() {
		return freehour;
	}
	public void setFreehour(Double freehour) {
		this.freehour = freehour;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMargin() {
		return margin;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	public Double getFeeee() {
		return feeee;
	}
	public void setFeeee(Double feeee) {
		this.feeee = feeee;
	}
	public Double getOnehours() {
		return onehours;
	}
	public void setOnehours(Double onehours) {
		this.onehours = onehours;
	}
	/**
	 * 电里程费
	 */
	public Double getKilom() {
		return kilom;
	}
	/**
	 * 电里程费
	 */
	public void setKilom(Double kilom) {
		this.kilom = kilom;
	}
	/**
	 * 油里程费
	 */
	public Double getMileage() {
		return mileage;
	}
	/**
	 * 油里程费
	 */
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	/**
	 * 租金费用
	 */
	public Double getRent() {
		return rent;
	}
	/**
	 * 租金费用
	 */
	public void setRent(Double rent) {
		this.rent = rent;
	}
	/**
	 * 保险费用
	 */
	public Double getInsure() {
		return insure;
	}
	/**
	 * 保险费用
	 */
	public void setInsure(Double insure) {
		this.insure = insure;
	}
	/**
	 * 超时费用
	 */
	public Double getTimeout() {
		return timeout;
	}
	/**
	 * 超时费用
	 */
	public void setTimeout(Double timeout) {
		this.timeout = timeout;
	}
	/**
	 * 陪驾费用
	 */
	public Double getDriver() {
		return driver;
	}
	/**
	 * 陪驾费用
	 */
	public void setDriver(Double driver) {
		this.driver = driver;
	}
	public Boolean getLongorder() {
		return longorder;
	}
	public void setLongorder(Boolean longorder) {
		this.longorder = longorder;
	}
	public Long getFeetype() {
		return feetype;
	}
	public void setFeetype(Long feetype) {
		this.feetype = feetype;
	}
	public Boolean getConflict() {
		return conflict;
	}
	public void setConflict(Boolean conflict) {
		this.conflict = conflict;
	}
	public Double getUsecoin() {
		return usecoin;
	}
	public void setUsecoin(Double usecoin) {
		this.usecoin = usecoin;
	}
	public Double getCancoin() {
		return cancoin;
	}
	public void setCancoin(Double cancoin) {
		this.cancoin = cancoin;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getPromotionText() {
		return promotionText;
	}

	public void setPromotionText(String promotionText) {
		this.promotionText = promotionText;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
}
