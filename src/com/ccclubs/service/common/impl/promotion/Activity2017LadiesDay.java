package com.ccclubs.service.common.impl.promotion;

import java.util.Date;
import java.util.List;

import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOrderLog;
import com.ccclubs.service.common.impl.CommonDisposeService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

/**
 * 2017植树节妇女节优惠活动
 * @author Administrator
 *
 */
public class Activity2017LadiesDay extends ActivityPromotion{
	
	DateUtil dateUtil 	= new DateUtil();
	Date activityStart 	= dateUtil.StringtoDate("2017-03-05 23:59:59", null);
	Date activityFinish = dateUtil.StringtoDate("2017-03-13 00:00:00", null);
	Double rebateValue	=	0.8;				//八折
	
	public Activity2017LadiesDay(OrderInfo orderInfo, Long orderid, List<CsOrderDetail> rentDetails, CsOrder oldOrder, Date start, Date finish, boolean isSettle){
		super(orderInfo, orderid, rentDetails, oldOrder, start, finish, isSettle);
	}
	
	@Override
	public String getName() {
		return "植树节八折优惠";
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public boolean hasPromotion() {
		
		if(CommonDisposeService.isOrderCluster(oldOrder)){
			return false;
		}
		
		if (!(this.start.after(activityStart) && this.start.before(activityFinish))){
			return false;
		}
		
		return true;
	}

	@Override
	public boolean repalRebate() {
		return true;
	}

	@Override
	public void execute() {
		if(this.hasPromotion()){
			
			orderInfo.setPromotionText(this.getName());
			
			//与已有折扣互斥
			for(CsOrderDetail orderDetail : orderInfo.getDetails()){
				if(orderDetail.getCsodRebate()!=null && orderDetail.getCsodRebate()>0 && orderDetail.getCsodRebate()<1){
					return;
				}
			}
			
			for(CsOrderDetail orderDetail : orderInfo.getDetails()){
				orderDetail.setCsodRemark(orderDetail.getCsodRemark()+">全局优惠("+getName()+")打折："+rebateValue);
				//叠加折扣
				orderDetail.setCsodRebate($(orderDetail.getCsodRebate() * rebateValue));		
				orderDetail.setCsodTotalReal($(orderDetail.getCsodPriceReal()*orderDetail.getCsodCountReal()*orderDetail.getCsodRebate()));//实际金额
			}
			Double tmpVal = orderInfo.getPrice();
			orderInfo.setPrice($(orderInfo.getPrice() * rebateValue));
			orderInfo.setDiscountAmount(tmpVal - orderInfo.getPrice());
			
			if(isSettle){
				new CsOrderLog(// 添加订单日志
						this.getName()// 操作项目 [非空]
						, orderid// 所属订单 [非空]
						, $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
						, LoginHelper.getLoginId() != null ? null : oldOrder.getCsoUseMember(), (short) 1, oldOrder.getClass().getName()+"@"+oldOrder.getCsoId(), "原订单需支付"+oldOrder.getCsoPayNeed()+"元, 实际本次租金总费用"+this.getRentTotal()+"元,优惠了"+this.getDiscountAmount()+"元!"// 备注信息
								, new Date()// 添加时间 [非空]
						).save();
			}
			
		}
	}
}
