package com.ccclubs.action.web.my;

import java.util.List;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsPackInfoService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.IPackageService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

public class DiscountAction {
	ICsMemberService csMemberService;
	ICsPackService csPackService;
	ICsRecordService csRecordService;
	ICommonMoneyService commonMoneyService;
	IPackageService packageService;
	ICsUserPackService csUserPackService;
	ICsPackInfoService csPackInfoService;

	/**
	 * 超值优惠
	 * 
	 * @return
	 */
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);

		List<CsPack> packList = csPackService.getCsPackList($.add(CsPack.F.cspStatus, 1).add("definex", CsPack.C.cspId + " in (1)").add("asc", CsPack.C.cspPrice), -1);
		$.SetRequest("packList", packList);
		return "discount";
	}

	public String buypack() {
		if (!SystemHelper.isLogin())
			// 未登录
			return $.SendHtml("101", "UTF-8");
		Long pack = $.getLong("pack", 0l);
		Long memberId = $.getLong("member", 0l);
		final CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		if (!memberId.equals(member.getCsmId()))
			// 本人购买
			return $.SendHtml("102", "UTF-8");
		try {
			packageService.executeBuyPackage(member.getCsmId(), pack, CommonMessage.MSG0308);
			return $.SendHtml("100", "UTF-8");
		} catch (com.ccclubs.exception.MessageException oe) {
			// $.SetTips(oe.getMessage());
			if (oe.getMessage().equals("当前套餐不能购买"))
				return $.SendHtml("103", "UTF-8");
			if (oe.getMessage().equals("您已经购买了该套餐"))
				return $.SendHtml("104", "UTF-8");
			return $.SendHtml(oe.getMessage(), SYSTEM.UTF8);
		} catch (Exception e) {
			return $.SendHtml("9999", SYSTEM.UTF8);
		}

	}

	/**
	 * 购买现金券
	 * 
	 * @return
	 */
	public String buycoupon() {
		if (!SystemHelper.isLogin())
			// 未登录
			return $.SendHtml("101", "UTF-8");
		// 101：对应300元/294；102：对应1500元/1440；103：对应3000元/2760；
		int type = $.getInteger("type", 0);
		int num = $.getInteger("num", 1);
		Long memberId = $.getLong("member", 0l);
		final CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		if (!memberId.equals(member.getCsmId()))
			// 本人购买
			return $.SendHtml("102", "UTF-8");
		String result;
		switch (type) {
		// 101：对应300元/294
		case 101:
			result = submitBuyCoupon(member, 294 * num, 300 * num);
			break;
		// 102：对应1500元/1440
		case 102:
			result = submitBuyCoupon(member, 1440 * num, 1500 * num);
			break;
		// 103：对应3000元/2760
		case 103:
			result = submitBuyCoupon(member, 2760 * num, 3000 * num);
			break;
		case 104:
			result = submitBuyCoupon(member, 475 * num, 500 * num);
			break;
		default:
			result = "104";
			break;
		}
		return $.SendHtml(result, "UTF-8");
	}

	/**
	 * 购买现金券
	 * 
	 * @param member
	 * @param money
	 * @param coupon
	 * @return
	 */
	private String submitBuyCoupon(final CsMember member, final double money, final double coupon) {
		if (member.getCsmMoney() < money)
			return "103";
		try {
			// 购买现金券
			String result = csRecordService.executeTransaction(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
					String remark = CommonMessage.MSG0307.replace("{money}", String.valueOf(LZ.digit(money, 2))).replace("{coupon}", String.valueOf(LZ.digit(coupon, 2)));
					// 加现金券
					commonMoneyService.updateMoney(member.getCsmId(), MoneyType.Coupon, coupon, RecordType.现金券充值, remark, null, null,null);
					// 减充值帐户
					commonMoneyService.updateMoney(member.getCsmId(), MoneyType.Money, money * -1.00, RecordType.购买现金券, remark, null, null,null);
					return (T) "100";
				}
			});
			// 兑换成功
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			// 系统错误,请稍候再试。
			return "9999";
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsPackService getCsPackService() {
		return csPackService;
	}

	public void setCsPackService(ICsPackService csPackService) {
		this.csPackService = csPackService;
	}

	public ICsRecordService getCsRecordService() {
		return csRecordService;
	}

	public void setCsRecordService(ICsRecordService csRecordService) {
		this.csRecordService = csRecordService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public IPackageService getPackageService() {
		return packageService;
	}

	public void setPackageService(IPackageService packageService) {
		this.packageService = packageService;
	}

	public ICsUserPackService getCsUserPackService() {
		return csUserPackService;
	}

	public void setCsUserPackService(ICsUserPackService csUserPackService) {
		this.csUserPackService = csUserPackService;
	}

	public ICsPackInfoService getCsPackInfoService() {
		return csPackInfoService;
	}

	public void setCsPackInfoService(ICsPackInfoService csPackInfoService) {
		this.csPackInfoService = csPackInfoService;
	}
}
