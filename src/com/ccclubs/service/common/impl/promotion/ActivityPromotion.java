package com.ccclubs.service.common.impl.promotion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.service.common.impl.OrderInfo;

/**
 * 
 * 活动优惠
 * 
 * @author joel
 *
 */
public abstract class ActivityPromotion {
	
	private String name;
	private Double rentTotal;
	private Double discountAmount;
	
	public Double $(Double value){
		if(value==null || value.doubleValue()==0.01)return 0d;
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Double getRentTotal() {
		return rentTotal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRentTotal(Double rentTotal) {
		this.rentTotal = rentTotal;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public boolean hasPromotion(){
		return this.discountAmount != null && this.discountAmount>0d;
	}
	
	/**
	 * 是否排斥打折（会员折扣、车辆折扣、会员组织折扣）
	 * @return
	 */
	public boolean repalRebate(){
		return false;
	}
	
	OrderInfo orderInfo;
	Long orderid; 
	List<CsOrderDetail> rentDetails; 
	CsOrder oldOrder;
	Date start;
	Date finish;
	boolean isSettle;
	
	public ActivityPromotion(){}
	
	/**
	 * @param orderInfo	订单信息
	 * @param orderid	订单id（可能为空）
	 * @param rentDetails	租金details
	 * @param oldOrder	订单（可能为空）
	 * @param start	订单开始时间
	 * @param finish	订单结束时间
	 * @param isSettle	是否结算环节
	 */
	public ActivityPromotion(OrderInfo orderInfo, Long orderid, List<CsOrderDetail> rentDetails, CsOrder oldOrder, Date start, Date finish, boolean isSettle){
		this.orderid = orderid;
		this.orderInfo = orderInfo;
		this.rentDetails = rentDetails;
		this.oldOrder = oldOrder;
		this.start = start;
		this.finish = finish;
		this.isSettle = isSettle;
		
		this.init();
	}
	
	public abstract void init();
	
	public abstract void execute();

}
