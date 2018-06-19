package com.ccclubs.action.web.my;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.WidgetHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsRefund;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsRefundService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.lazy3q.web.helper.$;

public class RefundAction {
	ICsMemberService	csMemberService;
	ICsRefundService	csRefundService;
	ICommonMoneyService	commonMoneyService;
	ICsOrderService		csOrderService;

	/**
	 * 退款页面
	 * 
	 * @return
	 */
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		Date date = WidgetHelper.getWorkDay(9);
  		$.SetRequest("date", date);
		CsRefund csRefund = csRefundService.getCsRefund($.add(CsRefund.F.csrMember, SystemHelper.getLoginId()).add("definex", "csr_status<2"));
		$.SetRequest("csRefund", csRefund);
		return "refund";
	}

	/**
	 * 获取退款细节
	 * 
	 * @return
	 */
	public String details() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		CsRefund csRefund = csRefundService.getCsRefund($.add(CsRefund.F.csrMember, SystemHelper.getLoginId()).add("definex", "csr_status<2"));
		$.SetRequest("csRefund", csRefund);
		if (csRefund == null)
			return "details";
		//获取会员可用保证金
		Double money = csRefund.get$csrMember().getCsmMoney();
		ICsAlipayRecordService csAlipayRecordService = $.getBean("csAlipayRecordService");
		// $.SetRequest("money", money);
		List<CsAlipayRecord> records = csAlipayRecordService.getCsAlipayRecordList($.add("csarMember", csRefund.getCsrMember())// 会员ID
				.add("csarStatus", 1)// 成功的充值
				.add("definex", "csar_refund< csar_amount and ((csar_id > 7830 and (csar_pay_method = 'directPay' or csar_pay_method = 'bankPay')) or (csar_pay_method = 'wap01' and csar_id > 20588))")
				// 可退额大于0
				//可退额大于0 (2013年4月30号之后的充值) ，2014年7月1日以后通过手机支付宝充值的
				.add("desc", " csar_add_time desc , csar_amount-csar_refund ")// 按可退余额多少倒序,充值时间倒序
				, -1);

		Double total = 0d;
		for (CsAlipayRecord csAlipayRecord : records)
			total += $(csAlipayRecord.getCsarAmount() - csAlipayRecord.getCsarRefund());
		if (total < money) {
			// return $.SendHtml("该退款无法以支付宝退款的方式退款，请联系技术员", "UTF-8");
			$.SetRequest("nodetails", true);
		} else {
			final Double finalMoney = money;
			final List<CsAlipayRecord> finalRecords = records;
			final List<CsAlipayRecord> refunds = new ArrayList<CsAlipayRecord>();// 有退款的记录

			Double refundMoney = finalMoney;
			for (CsAlipayRecord csAlipayRecord : finalRecords) {
				if (refundMoney <= 0)
					break;
				Double amount = $(Math.min(refundMoney, csAlipayRecord.getCsarAmount() - csAlipayRecord.getCsarRefund()));
				if (amount <= 0)
					continue;
				refundMoney -= amount;
				// 设置
				csAlipayRecord.setCsarRefund(amount);
				refunds.add(csAlipayRecord);
			}
			$.SetRequest("nodetails", false);
			$.SetRequest("refundsList", refunds);
		}
		return "details";
	}

	public Double $(Double value) {
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提交退款
	 * 
	 * @return
	 */
	public String submit() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");
			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			if (member.getCsmMoney() <= 0) {
				return $.SendHtml("102", "UTF-8");
			}
			Long memberId = $.getLong("member", 0l);
			if (!memberId.equals(member.getCsmId())) {
				return $.SendHtml("103", "UTF-8");
			}
			if (commonMoneyService.haveUnFinishOrder(SystemHelper.getLoginId())) {
				return $.SendHtml("104", "UTF-8");
			}
			if (commonMoneyService.isRefunding(SystemHelper.getLoginId())) {
				return $.SendHtml("105", "UTF-8");
			}
			List<CsOrder> csOrderList = csOrderService.getCsOrderList($.add(CsOrder.F.csoPayMember, SystemHelper.getLoginId()).add(CsOrder.F.csoStatus, 4).add("desc", CsOrder.C.csoRetTime), -1);
			// 没有定单
			if (csOrderList.size() == 0) {
				// 没有用车记录，3个工作日退款
				noRented(member);
				return $.SendHtml("100", "UTF-8");
			} else {
				// 取最后一个订单，如果最后一个订单还车时间是在 2014-01-30 17：00 之前还车的
				// ----------------------------------------- 铁定不是春节订单
				if (csOrderList.get(0).getCsoFinishTime().before(SystemHelper.getDate(2014, 0, 30, 17, 0, 0))) {
					if (csOrderList.get(0).getCsoRetTime() != null) {
						Date now = new Date();
						if (now.after(WidgetHelper.getWorkDay(7, csOrderList.get(0).getCsoRetTime()))) {
							// 7个工作日没用车了，相当于没有车记录，3个工作日退款
							noRented(member);
							return $.SendHtml("100", "UTF-8");
						} else {
							CsRefund newCsRefund = new CsRefund();
							newCsRefund.setNotNull(member.getCsmHost(),SystemHelper.getLoginId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(9, csOrderList.get(0).getCsoRetTime()), WidgetHelper.getWorkDay(9, csOrderList.get(
									0).getCsoRetTime()), (short) 0);
							newCsRefund.csrMobile(member.getCsmMobile());
							csRefundService.saveCsRefund(newCsRefund);
							return $.SendHtml("100", "UTF-8");
						}
					} else {
						// 没有车记录，3个工作日退款
						noRented(member);
						return $.SendHtml("100", "UTF-8");
					}
				} else {
					// 遍历订单记录 查看是否有春节订单
					Date start = SystemHelper.getDate(2014, 0, 30, 18, 0, 0);
					Date finish = SystemHelper.getDate(2014, 1, 7, 8, 0, 0);
					CsOrder springOrder = null;
					for (CsOrder csOrder : csOrderList) {
						// 遍历订单记录 查看是否有春节订单
						if (csOrder.getCsoFlag() != null && csOrder.getCsoFlag() == 1) {
							springOrder = csOrder;
							break;
						}
						if (csOrder.getCsoStartTime().before(start) && csOrder.getCsoFinishTime().after(finish)) {
							springOrder = csOrder;
							break;
						} else {
							continue;
						}
					}
					// 没有春节订单
					if (springOrder == null) {
						if (csOrderList.get(0).getCsoRetTime() != null) {
							Date now = new Date();
							if (now.after(WidgetHelper.getWorkDay(7, csOrderList.get(0).getCsoRetTime()))) {
								// 7个工作日没用车了，相当于没有车记录，3个工作日退款
								noRented(member);
								return $.SendHtml("100", "UTF-8");
							} else {
								CsRefund newCsRefund = new CsRefund();
								newCsRefund.setNotNull(member.getCsmHost(),SystemHelper.getLoginId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(9, csOrderList.get(0).getCsoRetTime()), WidgetHelper.getWorkDay(9, csOrderList
										.get(0).getCsoRetTime()), (short) 0);
								newCsRefund.csrMobile(member.getCsmMobile());
								csRefundService.saveCsRefund(newCsRefund);
								return $.SendHtml("100", "UTF-8");
							}
						} else {
							// 没有车记录，3个工作日退款
							noRented(member);
							return $.SendHtml("100", "UTF-8");
						}
					} else {
						if (csOrderList.get(0).getCsoRetTime() != null) {
							Date now = new Date(springOrder.getCsoFinishTime().getTime() + SYSTEM.DAY * 35);
							Date refundDate = WidgetHelper.getWorkDay(9, csOrderList.get(0).getCsoRetTime());
							Date refundLastDate = WidgetHelper.getWorkDay(4);
							if (refundDate.after(now)) {
								// 7个工作日没用车了，相当于没有车记录，3个工作日退款
								if (refundLastDate.after(refundDate)) {
									noRented(member);
									return $.SendHtml("100", "UTF-8");
								} else {
									CsRefund newCsRefund = new CsRefund();
									newCsRefund.setNotNull(member.getCsmHost(),member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), refundDate, refundDate, (short) 0);
									newCsRefund.csrMobile(member.getCsmMobile());
									csRefundService.saveCsRefund(newCsRefund);
									return $.SendHtml("100", "UTF-8");
								}
							} else {
								if (refundLastDate.after(refundDate)) {
									noRented(member);
									return $.SendHtml("100", "UTF-8");
								}else {
									CsRefund newCsRefund = new CsRefund();
									newCsRefund.setNotNull(member.getCsmHost(),member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), refundDate, refundDate, (short) 0);
									newCsRefund.csrMobile(member.getCsmMobile());
									csRefundService.saveCsRefund(newCsRefund);
									return $.SendHtml("100", "UTF-8");
								}
							}
						} else {
							// 比较春节订单与当前订单还车日期先后
							if (WidgetHelper.getWorkDay(4).after(new Date(springOrder.getCsoFinishTime().getTime() + SYSTEM.DAY * 35))) {
								noRented(member);
								return $.SendHtml("100", "UTF-8");
							} else {
								CsRefund newCsRefund = new CsRefund();
								newCsRefund.setNotNull(member.getCsmHost(),SystemHelper.getLoginId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), new Date(springOrder.getCsoFinishTime().getTime() + SYSTEM.DAY * 35), new Date(springOrder
										.getCsoFinishTime().getTime()
										+ SYSTEM.DAY * 35), (short) 0);
								newCsRefund.csrMobile(member.getCsmMobile());
								csRefundService.saveCsRefund(newCsRefund);
								return $.SendHtml("100", "UTF-8");
							}
						}
					}
					// return $.SendHtml("100", "UTF-8");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("9999", "UTF-8");
		}
	}

	private void noRented(CsMember member) {
		CsRefund newCsRefund = new CsRefund();
		newCsRefund.setNotNull(member.getCsmHost(),SystemHelper.getLoginId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(4), WidgetHelper.getWorkDay(4), (short) 0);
		newCsRefund.csrMobile(member.getCsmMobile());
		csRefundService.saveCsRefund(newCsRefund);
	}

	/**
	 * 取消退款
	 * 
	 * @return
	 */
	public String cancel() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");
			if (!$.getLong("member", 0l).equals(SystemHelper.getLoginId()))
				return $.SendHtml("102", "UTF-8");
			if (!commonMoneyService.isRefunding(SystemHelper.getLoginId())) {
				return $.SendHtml("103", "UTF-8");
			}
			final CsRefund csRefund = csRefundService.getCsRefund($.add("csrMember", SystemHelper.getLoginId()).add("definex", "csr_status<2"));
			if (csRefund == null)
				return $.SendHtml("103", "UTF-8");
			csRefundService.updateCsRefund$NotNull(new CsRefund().csrId(csRefund.getCsrId()).csrStatus((short) 4));

			return $.SendHtml("100", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("9999", "UTF-8");
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsRefundService getCsRefundService() {
		return csRefundService;
	}

	public void setCsRefundService(ICsRefundService csRefundService) {
		this.csRefundService = csRefundService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}
}
