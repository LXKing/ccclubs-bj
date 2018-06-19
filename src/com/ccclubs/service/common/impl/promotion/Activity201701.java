package com.ccclubs.service.common.impl.promotion;

import java.util.Date;
import java.util.List;

import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOrderLog;
import com.ccclubs.service.common.impl.OrderInfo;
import com.lazy3q.web.helper.$;

/**
 * 2017春节活动
 * 
 * * 2017春节满减活动，规则如下：
 * 只计算租金部分（不含超时费、不含提前还车罚金），优先于会员/车辆折扣（享受了此活动，不享受会员/车辆折扣）
 * 
 * @author Administrator
 *
 */
public class Activity201701 extends ActivityPromotion{
	
	@Override
	public boolean hasPromotion() {
		return false;
	}
	
	@Override
	public void init() {
		Double discountAmount = 0d;
		/*
		if(!CommonDisposeService.isOrderCluster(oldOrder)){
			DateUtil dateUtil = new DateUtil();
			Date activityStart = dateUtil.StringtoDate("2017-01-21 23:59:59", null);
			Date activityFinish = dateUtil.StringtoDate("2017-02-10 00:00:00", null);
			if(new Date().before(activityFinish) && start.after(activityStart) && activityStart.before(realFinish)){
				Double total = new OrderDetails(rentDetails).getTotal();
				promotion.setRentTotal(total);
				if(total>=999){
					discountAmount = 500d;
				}else if(total>=799){
					discountAmount = 400d;
				}else if(total>=599){
					discountAmount = 300d;
				}else if(total>=399){
					discountAmount = 200d;
				}else if(total>=199){
					discountAmount = 100d;
				}
			}
		}*/
		this.setName("春节满减优惠活动");
		this.setDiscountAmount(discountAmount);
	}

	@Override
	public void execute() {
		if(this.hasPromotion()){
			new CsOrderLog(// 添加订单日志
					this.getName()// 操作项目 [非空]
					, orderid// 所属订单 [非空]
					, $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
					, LoginHelper.getLoginId() != null ? null : oldOrder.getCsoUseMember(), (short) 1, oldOrder.getClass().getName()+"@"+oldOrder.getCsoId(), "原订单需支付"+oldOrder.getCsoPayNeed()+"元, 实际本次租金总费用"+this.getRentTotal()+"元,优惠了"+this.getDiscountAmount()+"元!"// 备注信息
					, new Date()// 添加时间 [非空]
			).save();
			
			orderInfo.setPromotionText(this.getName());
			orderInfo.setDiscountAmount(this.getDiscountAmount());
			
			Double remainVal = getDiscountAmount(), tempVal = 0d;
			for(CsOrderDetail orderDetail : rentDetails){
				tempVal =  Math.min(orderDetail.getCsodTotalReal(), remainVal);
				orderDetail.setCsodTotalReal(orderDetail.getCsodTotalReal() - tempVal);
				orderDetail.setCsodRemark(orderDetail.getCsodRemark()+">全局优惠(春节优惠)减钱：" + tempVal);
				remainVal -= tempVal;
				if(remainVal <= 0d){
					break;
				}
			}
		}
	}
}
