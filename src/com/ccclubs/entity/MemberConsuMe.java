package com.ccclubs.entity;

import java.util.Map;

import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsUnitPerson;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.hidden;
import com.lazy3q.lang.type;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

/**
 * 会员消费统计
 * 
 * @author ccclubs
 * 
 */
public  @caption("会员消费记录") class   MemberConsuMe{
	private @caption("会员") @hidden  @RelateClass(CsMember.class) CsMember csMember;

	private @caption("员工") @hidden @RelateClass(CsUnitPerson.class) CsUnitPerson csUnitPerson;

	private @caption("编号") Long memberId;
	
	private @caption("姓名")String memberName;
	
	private @caption("单位名称")String infoName;
	
	private @caption("部门")String groupName;
	
	private @caption("余额") Double money;
	
	private @caption("现金券") Double coupon;
	
	private @caption("订单总数") Integer orderCount;//订单总数
	
	private @caption("订单总时长") Double orderTime;//订单总时长
	
	private @caption("充值总金额") Double rechargeCount;//充值总金额
	
	private @caption("消费总金额") Double consumeCount;//消费总金额
	
	private @caption("订单总消费") Double orderConsume;
	
	private @caption("账户余额用户充值总计") Double balanceRechargeCount;//账户余额用户充值总计
	
	private @caption("账户余额后台充值总计") Double backgroundCount;//账户余额后台充值总计
	
	private @caption("账户余额用户消费总计") Double balanceConsuMeCount;//账户余额用户消费总计

	private @caption("账户余额后台扣款总计") Double backConsuMeCount;//账户余额后台扣款总计
	
	private @caption("现金券用户购买统计") Double couponCount;//现金券用户购买统计
	
	private @caption("现金券后台充值统计") Double backRechargeCoupon;//后台充值统计
	
	private @caption("现金券用户消费统计") Double couponConsuMeCount;//现金券用户消费统计
	
	private @caption("现金券后台扣款统计") Double backConsuMeCouponCount;//现金券后台扣款统计
	
	private @caption("红包") Double coinCount;//红包
	
	private @caption("红包赠送总计") Integer giveCoinCount;//红包赠送总计
	
	private @caption("红包消费总计") Double coinConsuMeCount;//红包消费总计

	public MemberConsuMe() {
		// TODO Auto-generated constructor stub
	}
	
	
	public MemberConsuMe(CsMember csMember, CsUnitPerson csUnitPerson) {
		super();
		this.csMember = csMember;
		this.csUnitPerson = csUnitPerson;
		this.orderCount = 0;
		this.orderTime = 0d;
		this.rechargeCount = 0d;
		this.consumeCount = 0d;
		this.balanceRechargeCount = 0d;
		this.backgroundCount = 0d;
		this.balanceConsuMeCount = 0d;
		this.backConsuMeCount = 0d;
		this.couponCount = 0d;
		this.backRechargeCoupon = 0d;
		this.couponConsuMeCount = 0d;
		this.backConsuMeCouponCount = 0d;
		this.coinCount = 0d;
		this.giveCoinCount = 0;
		this.coinConsuMeCount = 0d;
		this.memberId = csMember.getCsmId();
		this.memberName = csMember.getCsmName();
		this.infoName =  csUnitPerson != null && csUnitPerson.get$csupInfo() == null ? "" : csUnitPerson.get$csupInfo().getCsuiName();
		this.groupName = csUnitPerson != null && csUnitPerson.get$csupGroup() == null ? "" : csUnitPerson.get$csupGroup().getCsugName();
		this.money = csMember.getCsmMoney();
		this.coupon = csMember.getCsmCoupon();
		
	}


	public CsMember getCsMember() {
		return csMember;
	}

	public void setCsMember(CsMember csMember) {
		this.csMember = csMember;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Double getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Double orderTime) {
		this.orderTime = orderTime;
	}

	public Double getRechargeCount() {
		return rechargeCount;
	}

	public void setRechargeCount(Double rechargeCount) {
		this.rechargeCount = rechargeCount;
	}

	public Double getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(Double consumeCount) {
		this.consumeCount = consumeCount;
	}

	public Double getBalanceRechargeCount() {
		return balanceRechargeCount;
	}

	public void setBalanceRechargeCount(Double balanceRechargeCount) {
		this.balanceRechargeCount = balanceRechargeCount;
	}

	public Double getBackgroundCount() {
		return backgroundCount;
	}

	public void setBackgroundCount(Double backgroundCount) {
		this.backgroundCount = backgroundCount;
	}

	public Double getBalanceConsuMeCount() {
		return balanceConsuMeCount;
	}

	public void setBalanceConsuMeCount(Double balanceConsuMeCount) {
		this.balanceConsuMeCount = balanceConsuMeCount;
	}

	public Double getBackConsuMeCount() {
		return backConsuMeCount;
	}

	public void setBackConsuMeCount(Double backConsuMeCount) {
		this.backConsuMeCount = backConsuMeCount;
	}

	public Double getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Double couponCount) {
		this.couponCount = couponCount;
	}

	public Double getBackRechargeCoupon() {
		return backRechargeCoupon;
	}

	public void setBackRechargeCoupon(Double backRechargeCoupon) {
		this.backRechargeCoupon = backRechargeCoupon;
	}

	public Double getCouponConsuMeCount() {
		return couponConsuMeCount;
	}

	public void setCouponConsuMeCount(Double couponConsuMeCount) {
		this.couponConsuMeCount = couponConsuMeCount;
	}

	public Double getBackConsuMeCouponCount() {
		return backConsuMeCouponCount;
	}

	public void setBackConsuMeCouponCount(Double backConsuMeCouponCount) {
		this.backConsuMeCouponCount = backConsuMeCouponCount;
	}

	public Double getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(Double coinCount) {
		this.coinCount = coinCount;
	}

	public Integer getGiveCoinCount() {
		return giveCoinCount;
	}

	public void setGiveCoinCount(Integer giveCoinCount) {
		this.giveCoinCount = giveCoinCount;
	}

	public Double getCoinConsuMeCount() {
		return coinConsuMeCount;
	}

	public void setCoinConsuMeCount(Double coinConsuMeCount) {
		this.coinConsuMeCount = coinConsuMeCount;
	}

	public CsUnitPerson getCsUnitPerson() {
		return csUnitPerson;
	}

	public void setCsUnitPerson(CsUnitPerson csUnitPerson) {
		this.csUnitPerson = csUnitPerson;
	}


	public Long getMemberId() {
		return memberId;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getInfoName() {
		return infoName;
	}


	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public Double getMoney() {
		return money;
	}


	public void setMoney(Double money) {
		this.money = money;
	}


	public Double getCoupon() {
		return coupon;
	}


	public void setCoupon(Double coupon) {
		this.coupon = coupon;
	}


	public Double getOrderConsume() {
		return orderConsume;
	}


	public void setOrderConsume(Double orderConsume) {
		this.orderConsume = orderConsume;
	}
	
	public static class F {
		public final static @type(Long.class) String memberId="memberId";
		
		public final static @type(String.class) String memberName="memberName";
		
		public final static @type(String.class) String infoName="infoName";
		
		public final static @type(String.class) String groupName="groupName";
		
		public final static @type(Double.class) String money="money";
		
		public final static @type(Double.class) String coupon="coupon";
		
		public final static @type(Integer.class) String orderCount="orderCount";//订单总数
		
		public final static @type(Double.class) String orderTime="orderTime";//订单总时长
		
		public final static @type(Double.class) String rechargeCount="rechargeCount";//充值总金额
		
		public final static @type(Double.class) String consumeCount="consumeCount";//消费总金额
		
		public final static @type(Double.class) String orderConsume="orderConsume";
		
		public final static @type(Double.class) String balanceRechargeCount="balanceRechargeCount";//账户余额用户充值总计
		
		public final static @type(Double.class) String backgroundCount="backgroundCount";//账户余额后台充值总计
		
		public final static @type(Double.class) String balanceConsuMeCount="balanceConsuMeCount";//账户余额用户消费总计

		public final static @type(Double.class) String backConsuMeCount="backConsuMeCount";//账户余额后台扣款总计
		
		public final static @type(Double.class) String couponCount="couponCount";//现金券用户购买统计
		
		public final static @type(Double.class) String backRechargeCoupon="backRechargeCoupon";//后台充值统计
		
		public final static @type(Double.class) String couponConsuMeCount="couponConsuMeCount";//现金券用户消费统计
		
		public final static @type(Double.class) String backConsuMeCouponCount="backConsuMeCouponCount";//现金券后台扣款统计
		
		public final static @type(Double.class) String coinCount="coinCount";//红包
		
		public final static @type(Integer.class) String giveCoinCount="giveCoinCount";//红包赠送总计
		
		public final static @type(Double.class) String coinConsuMeCount="coinConsuMeCount";//红包消费总计
	}
	
	public static @api Page<MemberConsuMe> page(int page,int size,Map params){
		return getMemberConsuMePage(page, size , params);
	}
	
	public static @api Page<MemberConsuMe> getMemberConsuMePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsAnalysisService csAnalysisService = $.GetSpringBean("csAnalysisService");
		return csAnalysisService.getMemberConsuMePage(page, size , params);
	}
	
}
