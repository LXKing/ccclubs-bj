package com.ccclubs.service.common.script;

import java.util.List;

import com.ccclubs.exception.ErrorCode;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.service.common.impl.OrderInfo;

/**
 * 2016春节套餐使用限制
 * @author 
 *
 */
public class Package2016GiftLimit {
	
	/**
	 * 判断一下是否“春节套餐包”
	 * @param orderinfo
	 * @return
	 */
	static boolean is2016NewYearPacket(OrderInfo orderinfo){
		if(orderinfo!=null){
			List<CsOrderDetail> orderDetailList = orderinfo.getDetails();
			for(CsOrderDetail detail : orderDetailList){
				CsGoods goods = detail.get$csodGoods();
				if(goods.getCsgName().equals("春节套餐")){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 获取订单可使用的现金券金额
	 * @param orderinfo
	 * @return
	 */
	public static Double getCanUsePayCoupon(OrderInfo orderinfo, Double payCoupon){
		if(is2016NewYearPacket(orderinfo)){
			return 0d;
		}else{
			return payCoupon;
		}
	}
	
	/**
	 * 判断一下（续订）用户余额是否足够（扣除会员现金券后，再比较余额是否足够）
	 */
	public static void doCheckReOrderPayment(CsOrder csOrder, OrderInfo orderinfo, Double memberMoney) throws MessageException{
		if(!is2016NewYearPacket(orderinfo))return;
		
		CsMember member = CsMember.get(csOrder.getCsoPayMember());
		Double coupon = member.getCsmCoupon()  == null ? 0 : member.getCsmCoupon();
		if (LoginHelper.getLogin() == null
				&& ((memberMoney-coupon) + csOrder.getCsoPayNeed() + csOrder.getCsoPredict() + csOrder.getCsoMarginNeed()) < (orderinfo.getPrice() + orderinfo.getFeeee()
						+ orderinfo.getMargin() - orderinfo.getUsecoin()))
		
			throw new MessageException(ErrorCode.ORDER_MONEY_LESS, "会员可用余额不足");
	}
	
	/**
	 * 判断一下（下单）用户余额是否足够（扣除会员现金券后，再比较余额是否足够）
	 */
	public static void doCheckOrderPayment(CsOrder csOrder, OrderInfo orderinfo, Double memberMoney) throws MessageException{
		if(!is2016NewYearPacket(orderinfo))return;
		
		CsMember member = CsMember.get(csOrder.getCsoPayMember());
		Double coupon = member.getCsmCoupon()  == null ? 0 : member.getCsmCoupon();
		if ((memberMoney-coupon) < (csOrder.getCsoMarginNeed() + csOrder.getCsoPayNeed() + csOrder.getCsoPredict() - csOrder.getCsoPayCoin()))
			throw new MessageException(ErrorCode.ORDER_MONEY_LESS, "会员可用余额不足");
	}
}
