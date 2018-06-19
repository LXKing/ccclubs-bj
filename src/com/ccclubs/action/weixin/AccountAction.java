package com.ccclubs.action.weixin;

import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.ccclubs.action.weixin.alipay.util.AlipayNotify;
import com.ccclubs.action.weixin.hzbank.NetSignServer;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.WidgetHelper;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsCreditBill;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberGroup;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsRechargeCard;
import com.ccclubs.model.CsRefund;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.CsViolat;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsCoinService;
import com.ccclubs.service.admin.ICsCreditBillService;
import com.ccclubs.service.admin.ICsCreditCardService;
import com.ccclubs.service.admin.ICsEvCardService;
import com.ccclubs.service.admin.ICsItemService;
import com.ccclubs.service.admin.ICsMemberGroupService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsRechargeCardService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.admin.ICsRefundService;
import com.ccclubs.service.admin.ICsViolatService;
import com.ccclubs.service.admin.ISrvHostService;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.service.common.impl.OrderInfo;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.util.Page;

public class AccountAction {
	private ICsMemberService		csMemberService;
	private ICommonMoneyService		commonMoneyService;
	private ICsOrderService			csOrderService;
	private ICsMemberInfoService	csMemberInfoService;
	private ICommonDisposeService	commonDisposeService;
	private ICsRefundService		csRefundService;
	private ICsMemberGroupService	csMemberGroupService;
	private ICsAlipayRecordService	csAlipayRecordService;
	private ICsItemService			csItemService;
	private ICsRechargeCardService	csRechargeCardService;
	private ICsCreditCardService	cs_Credit_CardService;
	
	final DefaultJRedisClient<String, Object> jr = new DefaultJRedisClient<String, Object>(DBIndex.APP_WEIXIN);

	/**
	 * 账户
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String execute() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		// 强制刷新当前登录信息
		WeixinHelper.refreshLoginSession();
		csMember = WeixinHelper.getCsMember();

		CsEvCard csmEvcard = csMember.get$csmEvcard();

		$.SetRequest("csMember", csMember);
		$.SetRequest("csmEvcard", csmEvcard);

		$.SetRequest("freeHour", commonMoneyService.getCanUseFreeHour(csMember.getCsmId(), new java.util.Date()));

		String limit = "[?]";
		if (csMember.getCsmName() != null && !csMember.getCsmName().equals("")) {
			// 芝麻信用额度
			ICsCreditCardService creditCardService = $.GetSpringBean("csCreditCardService");
			CsCreditCard creditCard = creditCardService.getCsCreditCard($.add("csccMember", csMember.getCsmId()));
			boolean isToken = WeixinHelper.isZhimaAuthToken(csMember);// 是否授权过
			if (isToken) {
				String token = WeixinHelper.getAlipayZhimaToken(csMember);
				boolean result = WeixinHelper.getAlipayZhimaScore(csMember, token);
				if (result) {
					// 免押金余额
					ICommonMoneyService moneyService = $.GetSpringBean("commonMoneyService");
					Double fund = moneyService.getUsableCredit(csMember.getCsmId());
					limit = new DecimalFormat("#####0.00").format(fund);
				} else {
					limit = "0";
				}
			}
		}
		$.SetRequest("limit", limit);

		// 红包
		$.SetRequest("coin", commonMoneyService.getCoin(null, csMember.getCsmId(), new java.util.Date()));
		return "account";
	}

	/**
	 * 自助绑卡
	 * 
	 * @return
	 */
	public String binding() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		return "binding";
	}

	public String bindingSubmit() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		String evCardNum = $.getString("cardNum");
		ICsEvCardService csEvCardService = $.GetSpringBean("csEvCardService");
		CsEvCard cec = csEvCardService.getCsEvCard($.add(CsEvCard.F.csecNumber, evCardNum));
		if (cec == null) {
			return $.SendHtml("199", "UTF-8");
		} else {
			ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
			CsMember csm = csMemberService.getCsMember($.add(CsMember.F.csmEvcard, cec.getCsecId$()));
			if (csm != null) {
				return $.SendHtml("299", "UTF-8");
			} else {
				csMember.setCsmEvcard(cec.getCsecId());
				csMemberService.updateCsMember(csMember);
				return $.SendHtml("111", "UTF-8");
			}
		}
	}

	/**
	 * 免押金额
	 * 
	 * @return
	 */
	public String credit() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		String openid = csMember.getCsmAlipayFlag();
		if ($.empty(openid)) {
			$.SetRequest("error", 1);
			$.SetRequest("msg", "该账户未绑定支付宝，请先绑定！");
		} else if (!csMember.getCsmVDrive().toString().equals("1")) {
			$.SetRequest("error", 1);
			$.SetRequest("msg", "&nbsp;&nbsp;&nbsp;&nbsp;你尚未进行驾驶身份认证，暂不能享受免押金服务。<br>&nbsp;&nbsp;&nbsp;&nbsp;通过驾驶身份认证后，系统会根据您的支付宝和芝麻信用状况，车纷享将授予您特定免押金额度。<br><br><div style=\"text-align:center;\"><a href=\"account_setname.html?from=" + $.getString("from")
					+ "\" style=\"width:100%;text-align:center;\">立即完成驾驶身份认证</a></div>");
		} else {
			String token = WeixinHelper.getAlipayZhimaToken(csMember);
			boolean result = WeixinHelper.getAlipayZhimaScore(csMember, token);
			if (!result) {
				$.SetRequest("error", 1);
				$.SetRequest("state", $.getString("state"));
				$.SetRequest("msg", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前登录的车纷享账户与支付宝芝麻信用授权失败，请重新尝试。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如有任何疑问，请致电客服咨询。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客服电话：0571-88190338");
			} else if (result) {
				$.SetRequest("state", $.getString("state"));
				$.SetRequest("result", "2000");
			} else {
				$.SetRequest("error", 0);
				$.SetRequest("state", $.getString("state"));
				$.SetRequest("result", "0");
			}
			ICommonMoneyService moneyService = $.GetSpringBean("commonMoneyService");
			Double fund = moneyService.getUsableCredit(csMember.getCsmId());
			// 免押金余额
			ICsCreditCardService cardService = $.GetSpringBean("csCreditCardService");
			CsCreditCard card = cardService.getCsCreditCard($.add("csccMember", csMember.getCsmId()));
			if (card != null) {
				Double returning = card.getCsccLimit() - fund;
				$.SetRequest("returning", new DecimalFormat("#####0.00").format(returning));
			} else {
				$.SetRequest("returning", "0.00");
			}
			$.SetRequest("fund", new DecimalFormat("#####0.00").format(fund));
		}

		return "credit";
	}

	/**
	 * 是否已经授权过
	 * 
	 * @return
	 */
	public String isZhimaAuthToken() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			// openid不存在，跳转到403页面
			return $.SendHtml("101", "UTF-8");
		}
		boolean res = WeixinHelper.isZhimaAuthToken(csMember);
		if (res) {
			return $.SendHtml("111", "UTF-8");
		} else {
			return $.SendHtml("222", "UTF-8");
		}
	}

	/**
	 * 获取芝麻授权url
	 * 
	 * @link WeixinHelper.getOpenId()
	 * @return
	 */
	public String zhimaAuth() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			// openid不存在，跳转到403页面
			return $.SendHtml("101", "UTF-8");
		}

		String url = WeixinHelper.getWindowAlipayZhimaAuthUrl(csMember, "first&redirect=account_credit&from=" + WeixinHelper.getOpenId());
		return $.SendHtml(url, "UTF-8");
	}

	/**
	 * 我的违章
	 * 
	 * @return
	 */
	public String weizhang() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		ICsViolatService voilatService = $.GetSpringBean("csViolatService");
		List<CsViolat> list = voilatService.getCsViolatList($.add(CsViolat.F.csvMember, csMember.getCsmId()), null);
		$.SetRequest("weizhanglist", list);
		return "weizhang";
	}

	/**
	 * 红包
	 * 
	 * @return
	 */
	public String coin() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		$.SetRequest("coin_all", commonMoneyService.getCoin(null, csMember.getCsmId(), new java.util.Date()));
		$.SetRequest("coin_hz", commonMoneyService.getCoin(1l, csMember.getCsmId(), new java.util.Date()));
		$.SetRequest("coin_bj", commonMoneyService.getCoin(2l, csMember.getCsmId(), new java.util.Date()));

		return "coin";
	}

	/**
	 * 红包兑换页面
	 * 
	 * @return
	 */
	public String paycoin() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		return "paycoin";
	}

	/**
	 * 兑换红包
	 * 
	 * @return
	 */
	public String rechargeCoin() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// openid不存在，跳转到403页面
				return $.SendHtml("101", "UTF-8");
			}

			final String csrcNumber = $.getString("cardNum");
			final String csrcPassword = $.getString("checkcode");

			// CsRechargeCard csRechargeCard =
			// csRechargeCardService.getCsRechargeCard($.add(CsRechargeCard.F.csrcNumber,
			// csrcNumber.toUpperCase()).add(CsRechargeCard.F.csrcPassword,
			// csrcPassword.toUpperCase()));
			// 开始兑换
			int result = commonMoneyService.executeRechangeCard(csrcNumber, csrcPassword, csMember.getCsmId(), "恭喜您，兑换现金券成功");
			return $.SendHtml(result+"", "UTF-8");
			/*if (result == 104) {
				return $.SendHtml("104", "UTF-8");
			} else if (result == 106) {
				// 如果卡的所属人与当前登录会员账号不一致，充值失败
				// return $.SendHtml("该卡的所属人不是您，不能完成充值！", "UTF-8");
				return $.SendHtml("201", "UTF-8");
			} else if (result == 203) {
				return $.SendHtml("105", "UTF-8");
			} else if (result == 204) {
				return $.SendHtml("105", "UTF-8");
			} else if (result == 205) {
				return $.SendHtml("106", "UTF-8");
			} else if (result == 200) {
				// Calendar endCalendar = Calendar.getInstance();
				// endCalendar.add(Calendar.MONTH,
				// ICommonMoneyService.validity);
				// DateUtil du = new DateUtil();
				// String endTimeStr =
				// du.dateToString(csRechargeCard.getCsrcAddTime(),
				// "yyyy-MM-dd");
				// endTimeStr += "-";
				// endTimeStr += du.dateToString(endCalendar.getTime(),
				// "yyyy-MM-dd");
				return $.SendHtml("111", "UTF-8");
				// return $.SendHtml("恭喜您！已成功兑换红包" +
				// csRechargeCard.getCsrcDenomination() + "元，有效期限：" +
				// endTimeStr, "UTF-8");
			} else {
				return $.SendHtml("299", "UTF-8");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 红包详情
	 * 
	 * @return
	 */
	public String coinInfo() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		ICsCoinService coinService = $.GetSpringBean("csCoinService");
		String host = "";
		if($.getString("host")==null){
			SrvHost srvhost = SystemHelper.getSrvHostByClientIp();
			host = srvhost.getShId().toString();
		}else{
			host = $.getString("host");
		}
		List<CsCoin> list = coinService.getCsCoinList($.add(CsCoin.F.cscMember, csMember.getCsmId()).add(CsCoin.F.cscHost, host).add(CsCoin.F.cscStatus, "1"), null);
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, 7);
		int renum = 0;
		for (CsCoin cc : list) {
			if (cc.getCscEnd().before(ca.getTime())) {
				renum += cc.getCscCount();
			}
		}

		$.SetRequest("renum", renum);
		$.SetRequest("coinlist", list);
		$.SetRequest("city", host.equals("1") ? "杭州" : "北京");
		return "coinInfo";
	}

	/**
	 * 免押金服务说明
	 * 
	 * @return
	 */
	public String creditInfo() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		$.SetRequest("from", $.getString("from"));
		ICsCreditCardService creditCardService = $.GetSpringBean("csCreditCardService");
		CsCreditCard creditCard = creditCardService.getCsCreditCard($.add("csccMember", csMember.getCsmId()));
		Double limit = 0d;
		if (creditCard != null) {
			if (creditCard.getCsccLimit() != 0) {
				limit = creditCard.getCsccLimit();
			}
		}
		$.SetRequest("limit", limit);
		return "creditInfo";
	}

	/**
	 * 远程开关门页面
	 * 
	 * @return
	 */
	public String remote() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		// 订单前10分钟才可以取车，return "orderdetail"
		CsOrder csOrder = csOrderService.getCsOrderById(Long.parseLong($.getString("order")));
		// 不存在定单
		if (csOrder == null)
			return $.SendHtml("false", "UTF-8");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(csOrder.getCsoStartTime());
		calendar.add(Calendar.MINUTE, -10);
		long temp = calendar.getTimeInMillis() - new Date().getTime();
		ICsCreditBillService creditBillService = $.GetSpringBean("csCreditBillService");
		CsCreditBill bill = creditBillService.getCsCreditBill($.add("cscbOrder", csOrder.getCsoId()));
		if (bill != null) {
			String payStatus = bill.getCscbStatus().toString();
			$.SetRequest("payStatus", payStatus);
			$.SetRequest("mustPay", bill.getCscbValue());
		} else {
			$.SetRequest("payStatus", "1");
		}
		$.SetRequest("csOrder", csOrder);

		// 电动车才可远程开关门-杭州
		CsCar car = csOrder.get$csoCar();
		$.SetRequest("carNum", car.getCscCarNo());// car.getCscNumber$().substring(3)
		if (car.get$cscModel().getCscmType$().equals("电动车") && car.getCscHost() == 1) {// 电动车
			if (temp > 0) {// 判断订单开始时间
				// 支付会员对不上
				if (!csOrder.getCsoUseMember().equals(csMember.getCsmId())) {
					return $.SendHtml("false", "UTF-8");
				}
				$.SetRequest("msg", "您的订单尚未开始，在取车时间前10分钟开始就能操控小纷咯，享受开关门的乐趣。");
				return "remote";
			} else if (!(csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1")))) {// System.currentTimeMillis()>csOrder.getCsoFinishTime().getTime()
				// 订单状态不为0和1
				return "orderdetail";
			} else {
				return "remote";
			}
		} else {// 汽油车
			return "orderdetail";
		}
	}

	public String remoteCon() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// openid不存在，跳转到403页面
				return $.SendHtml("101", "UTF-8");
			}

			CsOrder csOrder = csOrderService.getCsOrderById(Long.parseLong($.getString("orderId")));
			// 不存在定单
			if (csOrder == null) {
				return $.SendHtml("101", "UTF-8");
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(csOrder.getCsoStartTime());
			calendar.add(Calendar.MINUTE, -10);
			long temp = calendar.getTimeInMillis() - new Date().getTime();
			if (temp > 0) {// 判断订单开始时间
				return $.SendHtml("444", "UTF-8");
			}

			String controllType = $.getString("type");
			if (controllType.equals("1")) {
				if (csOrder.getCsoStatus().equals(new Short("0"))) {// 已预订状态-远程取车
					WeixinHelper.remoteController(csOrder.getCsoCar(), "6", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else if (csOrder.getCsoStatus().equals(new Short("1"))) {// 使用中状态-远程开门
					WeixinHelper.remoteController(csOrder.getCsoCar(), "0", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else {
					return $.SendHtml("9999", "UTF-8");
				}
			} else if (controllType.equals("2")) {// 关门
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "1", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else {
					return $.SendHtml("9999", "UTF-8");
				}
			} else if (controllType.equals("3")) {// 鸣笛
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "17", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else {
					return $.SendHtml("9999", "UTF-8");
				}
			} else if (controllType.equals("4")) {// 远程还车
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "7", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else {
					return $.SendHtml("9999", "UTF-8");
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("101", "UTF-8");
		}
	}

	/**
	 * 账户金额
	 * 
	 * @return
	 */
	public String amount() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		// 强制刷新当前登录信息
		WeixinHelper.refreshLoginSession();
		csMember = WeixinHelper.getCsMember();

		$.SetRequest("csMember", csMember);

		return "amount";
	}

	/**
	 * 余额充值
	 * 
	 * @return
	 */
	public String recharge() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		$.SetRequest("csMember", csMember);

		return "recharge";
	}

	/**
	 * 现金券金额
	 * 
	 * @return
	 */
	public String coupon() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		// 强制刷新当前登录信息
		WeixinHelper.refreshLoginSession();
		csMember = WeixinHelper.getCsMember();

		$.SetRequest("csMember", csMember);

		return "coupon";
	}

	/**
	 * 购买现金券
	 * 
	 * @return
	 */
	public String buy() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		// 获取宝贝列表 ALIPAY_WALLET
		// 7月底到9月份的充值送活动
		// List<CsItem> list =
		// csItemService.getCsItemList($.add(CsItem.F.csiFlag,
		// "ALIPAY_WALLET").add(CsItem.F.csiStatus, 1).add("asc",
		// CsItem.C.csiPrice), -1);
		List<CsItem> list = csItemService.getCsItemList($.add(CsItem.F.csiFlag, "COMMON_COUPON").add(CsItem.F.csiStatus, 1).add("asc", CsItem.C.csiPrice), -1);
		$.SetRequest("list", list);
		if (list.size() > 0) {
			$.SetRequest("csItem", list.get(0));
		}
		$.SetRequest("csMember", csMember);

		return "buy";
	}

	/**
	 * 兑换现金券
	 * 
	 * @return
	 */
	public String exchange() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		if ($.getSession("exchange_count") == null) {
			// 初始加载设置为0
			$.setSession("exchange_count", 0);
		}
		// 获取宝贝列表 ALIPAY_WALLET
		List<CsItem> list = csItemService.getCsItemList($.add(CsItem.F.csiFlag, "ALIPAY_WALLET").add(CsItem.F.csiStatus, 1).add("asc", CsItem.C.csiPrice), -1);
		$.SetRequest("list", list);
		if (list.size() > 0) {
			$.SetRequest("csItem", list.get(0));
		}
		$.SetRequest("csMember", csMember);

		return "exchange";
	}

	/**
	 * 兑换现金券 - 提交兑换
	 * 
	 * @return
	 */
	public String exchangeSubmit() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();

			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// openid不存在，跳转到403页面
				return $.SendHtml("101", "UTF-8");
			}
			// 输入错误次数
			int errorCount = Integer.parseInt($.getSession("exchange_count").toString());
			if (errorCount >= 3) {
				// 错误次数太多，过会儿再来吧。
				return $.SendHtml("102", "UTF-8");
			}

			final String csrcNumber = $.getString("csrcNumber", "");
			final String csrcPassword = $.getString("csrcPassword", "");

			CsRechargeCard csRechargeCard = csRechargeCardService.getCsRechargeCard($.add(CsRechargeCard.F.csrcNumber, csrcNumber.toUpperCase()).add(CsRechargeCard.F.csrcPassword, csrcPassword.toUpperCase()));
			// 开始兑换
			int result = commonMoneyService.executeRechangeCard(csrcNumber, csrcPassword, csMember.getCsmId(), "恭喜您，兑换现金券成功");
			if (result == 104) {
				// 卡号或密码错误
				errorCount = errorCount + 1;
				$.setSession("exchange_count", errorCount);
				return $.SendHtml("104", "UTF-8");
			} else if (result == 105) {
				// 如果卡的所属人与当前登录会员账号不一致，充值失败
				return $.SendHtml("该卡的所属人不是您，不能完成充值！", "UTF-8");
			} else if (result == 203) {
				return $.SendHtml("105", "UTF-8");
			} else if (result == 200) {
				return $.SendHtml("恭喜您已成功兑换现金券" + csRechargeCard.getCsrcDenomination() + "元，您当前帐户现金券余额为" + csMember.getCsmCoupon() + "元！", "UTF-8");
			} else {
				return $.SendHtml(result + "", "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 退款页面
	 * 
	 * @return
	 */
	public String refund() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		$.SetRequest("csMember", csMember);
		CsRefund csRefund = csRefundService.getCsRefund($.add(CsRefund.F.csrMember, csMember.getCsmId()).add("definex", "csr_status<2"));
		$.SetRequest("csRefund", csRefund);
		return "refund";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String pw() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		$.SetRequest("csMember", csMember);

		return "pw";
	}

	/**
	 * 微信注册页面
	 * 
	 * @return
	 */
	public String register() {
		if (!WeixinHelper.isCorrectOpenId()) {
			return WeixinHelper.gotoLogin();
		}
		if (WeixinHelper.getCsMember() != null) {
			return "success";
		}
		String flag = $.getString("flag", "");
		if (!$.empty(flag)) {
			$.setSession("cooperation", flag.toUpperCase());
		}
		// 为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
		$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime() - SYSTEM.MINUTE));

		ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SrvHost.F.shState, "1");
		List<SrvHost> hostList = srvHostService.getSrvHostList(params, Integer.MAX_VALUE);
		$.setRequest("hostList", hostList);
		SrvHost host = SystemHelper.getSrvHostByClientIp();
		if (host != null) {
			$.setRequest("selHostId", host.getShId());
		} else {
			// 杭州
			$.setRequest("selHostId", 1);
		}

		return "register";
	}

	/**
	 * 保存手机账号信息
	 * 
	 * @return
	 */
	public String saveAccount() {
		try {
			String csmMobile = $.getString("txtMobile", "");
			String strValidMobCode = $.getString("txtCode", "");
			final String csmHost = $.getString("txtCity", "");
			// String strValidMobCode = $.getString("txtMobCode", "");
			if (!WeixinHelper.isCorrectOpenId()) {
				// $.SetTips("请在微信里注册");
				return $.SendHtml("109", "UTF-8");
			}
			final String from = WeixinHelper.getOpenId();
			if ($.empty(csmMobile)) {
				// $.SetTips("未输入手机号码，不能注册");
				return $.SendHtml("101", "UTF-8");
			}
			if ($.empty(strValidMobCode)) {
				// $.SetTips("未输入短信校验码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}

			if (!SystemHelper.isMobile(csmMobile)) {
				// $.SetTips("手机号码格式错误");
				return $.SendHtml("103", "UTF-8");
			}

			if (csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile)) != null) {
				// $.SetTips("手机号码已被注册");
				return $.SendHtml("104", "UTF-8");
			}
			if (!$.equals(strValidMobCode, (String) $.getSession(SYSTEM.REGIST_VAILD_CODE))) {
				// $.SetTips("短信校验码输入错误");
				return $.SendHtml("105", "UTF-8");
			}
			// ---------------------------------------------------------通过验证，提交注册
			final String finalPassword = csmMobile.substring(5);
			final String finalMobile = csmMobile;
			csMemberService.executeTransaction(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
					String username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
					// 用户名去重
					while (csMemberService.getCsMember($.add(CsMember.F.csmUsername, username)) != null) {
						username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
					}
					HttpServletRequest request = ServletActionContext.getRequest();

					CsMember csMember = new CsMember().setNotNull(Long.parseLong(csmHost), username, $.md5(finalPassword),  0d, 0d, 0d, 0, 0, 1d, new Date(), (short) 1);
					csMember.csmMobile(finalMobile);
					csMember.csmIsVip((short) 0);
					short src = WeixinHelper.switchRegPlatform(from);
					csMember.csmFrom(src);
					switch (src) {
						case 4:
							csMember.csmAlipayFlag(from);
							break;
						case 2:
							csMember.csmWeixinFlag(from);
							break;
					}
					csMember.csmVDrive((short) 0).csmVEmail((short) 0).csmVMobile((short) 1).csmVReal((short) 0);
					csMember.csmLoginS(0);

					csMember.csmRegistIp(request.getRemoteAddr());

					//合作接入页面，需要设置组别 cooperation，如：FM93等
					String flag = $.getSession("cooperation", "");
					if (!$.empty(flag)) {
						CsMemberGroup csMemberGroup = csMemberGroupService.getCsMemberGroup($.add(CsMemberGroup.F.csmgFlag, flag.toUpperCase()));
						if (csMemberGroup != null) {
							csMember.csmGroup(csMemberGroup.getCsmgId());
						}
						$.setSession("cooperation", null);
					}

					csMember = csMemberService.saveCsMember(csMember);

					// 设置登录 session
					$.setSession(SYSTEM.ccclubs_web_login, csMember);
					$.setSession(SYSTEM.ccclubs_web_login_id, csMember.getCsmId());
					// 设置 cache
					try {
						jr.set(from, csMember.getCsmId().toString());
					} catch (Exception e) {
						e.printStackTrace();
						// 记录一些东西
						try {
							String path = AccountAction.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_wx.txt";
							WeixinHelper.writeAppendToFile(path, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + e.getMessage(), "UTF-8");
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// 再尝试一遍
						jr.set(from, csMember.getCsmId().toString());
					}
					// -----------------------------------通过支付宝钱包注册，赠送100元现金券
					// if (from.length() == 16) {
					// ICommonMoneyService commonMoneyService =
					// $.getBean("commonMoneyService");
					// commonMoneyService.updateMoney(csMember.getCsmId(),
					// MoneyType.Coupon, 100.0, RecordType.支付宝钱包注册送现金券,
					// "恭喜您通过支付宝钱包注册成功，送您100元现金券。", null, null);
					// }

					// $.SetTips("注册成功，请登录");
					// 清除验证码，短信校验码session
					$.setSession(SYSTEM.REGIST_VAILD_CODE, null);

					return null;
				}
			});
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 新用户注册 设置密码页面
	 * 
	 * @return
	 */
	public String setpwd() {

		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		return "setpwd";

	}

	/**
	 * 注册 - 提交密码修改
	 * 
	 * @return
	 */
	public String setpassword() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();

			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// openid不存在，跳转到403页面
				return $.SendHtml("101", "UTF-8");
			}
			String csmPassword = $.getString("txtPassWord", "");
			String rePwd = $.getString("txtRePassWord", "");
			if ($.empty(csmPassword)) {
				// $.SetTips("未输入密码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if (csmPassword.length() < 6) {
				// $.SetTips("密码设置有误，请输入6到20位有效密码");
				return $.SendHtml("103", "UTF-8");
			}
			if (!$.equals(csmPassword, rePwd)) {
				// $.SetTips("两次密码输入不一致");
				return $.SendHtml("104", "UTF-8");
			}

			csMemberService.updateCsMember$NotNull(new CsMember().csmPassword($.md5(csmPassword)).csmId(csMember.getCsmId()));
			return $.SendHtml("100", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 注册 - 提交密码修改
	 * 
	 * @return
	 */
	public String modifypassword() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();

			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// openid不存在，跳转到403页面
				return $.SendHtml("101", "UTF-8");
			}
			String password = $.getString("accName", "");
			String csmPassword = $.getString("txtPassWord", "");
			String rePwd = $.getString("txtRePassWord", "");
			if ($.empty(csmPassword)) {
				// $.SetTips("未输入密码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if (csmPassword.length() < 6) {
				// $.SetTips("密码设置有误，请输入6到20位有效密码");
				return $.SendHtml("103", "UTF-8");
			}
			if (!$.equals(csmPassword, rePwd)) {
				// $.SetTips("两次密码输入不一致");
				return $.SendHtml("104", "UTF-8");
			}

			if (!$.md5(password).equals(csMember.getCsmPassword().toUpperCase())) {
				// 你输入的密码不正确，请重新输入。
				return $.SendHtml("105", "UTF-8");
			}

			csMemberService.updateCsMember$NotNull(new CsMember().csmPassword($.md5(csmPassword)).csmId(csMember.getCsmId()));
			return $.SendHtml("100", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 找回密码页面
	 * 
	 * @return
	 */
	public String forgot() {
		if (!WeixinHelper.isCorrectOpenId()) {
			return WeixinHelper.gotoLogin();
		}
		// 为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
		$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime() - SYSTEM.MINUTE));
		return "forgot";
	}

	/**
	 * 找回密码 - 核对手机信息，及校验码
	 * 
	 * @return
	 */
	public String forgotCheck() {
		try {
			String csmMobile = $.getString("txtMobile", "");
			String strValidMobCode = $.getString("txtCode", "");
			// String strValidMobCode = $.getString("txtMobCode", "");
			if (!WeixinHelper.isCorrectOpenId()) {
				// $.SetTips("请在微信里注册");
				return $.SendHtml("109", "UTF-8");
			}
			// final String from = $.getString("from");
			if ($.empty(csmMobile)) {
				// $.SetTips("未输入手机号码，不能注册");
				return $.SendHtml("101", "UTF-8");
			}
			if ($.empty(strValidMobCode)) {
				// $.SetTips("未输入短信校验码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}

			if (!SystemHelper.isMobile(csmMobile)) {
				// $.SetTips("手机号码格式错误");
				return $.SendHtml("103", "UTF-8");
			}

			if (!$.equals(strValidMobCode, (String) $.getSession(SYSTEM.REGIST_VAILD_CODE))) {
				// $.SetTips("短信校验码输入错误");
				return $.SendHtml("105", "UTF-8");
			}
			CsMember existMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile));
			if (existMember == null) {
				// $.SetTips("该手机号尚未注册");
				return $.SendHtml("104", "UTF-8");
			}
			// ----------------------------------------------------------验证通过，进入到修改密码页面
			$.setSession(Constant.VERIFY_MOBILE, csmMobile);
			return $.SendHtml("100", "UTF-8");
		} catch (Exception e) {
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 忘记密码 - 重置密码页面
	 * 
	 * @return
	 */
	public String forgotset() {
		// CsMember csMember = WeixinHelper.getCsMember();
		//
		// if (csMember == null) {
		// return WeixinHelper.gotoLogin();
		// }
		// 验证当前手机号
		if ($.getSession(Constant.VERIFY_MOBILE) == null) {
			return "forgot";
		}
		return "forgotset";
	}

	public String forgotsave() {
		try {
			String csmPassword = $.getString("txtPassWord", "");
			String rePwd = $.getString("txtRePassWord", "");
			if ($.empty(csmPassword)) {
				// $.SetTips("未输入密码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if (csmPassword.length() < 6) {
				// $.SetTips("密码设置有误，请输入6到20位有效密码");
				return $.SendHtml("103", "UTF-8");
			}
			if (!$.equals(csmPassword, rePwd)) {
				// $.SetTips("两次密码输入不一致");
				return $.SendHtml("104", "UTF-8");
			}

			// 当前重置密码用户
			CsMember csMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, $.getSession(Constant.VERIFY_MOBILE)));
			if (csMember == null)
				// $.SetTips("手机号不存在，不能修改密码");
				return $.SendHtml("111", "UTF-8");
			// ----------------------------------------------------------验证通过，重置密码
			csMemberService.updateCsMember$NotNull(new CsMember().csmPassword($.md5(csmPassword)).csmId(csMember.getCsmId()));
			// $.SetTips("重置密码成功，进入到成功页面");
			// 清空当前手机号session
			$.setSession(Constant.VERIFY_MOBILE, null);
			return $.SendHtml("100", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 设置驾驶证信息
	 * 
	 * @return
	 */
	public String setname() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		// 更新会员的扩展信息
		CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, csMember.getCsmId()));
		/*
		 * String cert = $.getSession(SYSTEM.ccclubs_alipay_cert); String addr =
		 * $.getSession(SYSTEM.ccclubs_alipay_addr); String name =
		 * $.getSession(SYSTEM.ccclubs_alipay_name);
		 */
		// 
		if (csMemberInfo == null || csMember.getCsmVReal() == null || csMember.getCsmVDrive() == null || csMember.getCsmVReal() != 1 || csMember.getCsmVDrive() != 1) {
			$.SetRequest("isPerfect", false);
		} else {
			$.SetRequest("isPerfect", true);
		}
		$.SetRequest("csMember", csMember);
		$.SetRequest("csMemberInfo", csMemberInfo);

		/*
		 * $.SetRequest("cert", cert); $.SetRequest("addr", addr);
		 * $.SetRequest("name", name);
		 */

		return "setname";
	}

	/**
	 * 提交真实姓名，驾驶证照片等
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String saveDriver() {
		try {
			final String txtName = $.getString("txtName", "");
			final String txtLicnum = $.getString("txtLicnum", "");
			final String from = $.getString("from", "");
			final String txtLicPic = $.getString("txtLicPic", "");
			final String txtVerPic = $.getString("txtVerPic", "");
			final String txtaddress = $.getString("txtaddress", "");
			// String strValidMobCode = $.getString("txtMobCode", "");
			final CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// openid不存在，跳转到403页面
				return $.SendHtml("101", "UTF-8");
			}

			if ($.empty(txtName)) {
				// $.SetTips("未输入姓名，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if ($.empty(txtLicnum)) {
				// $.SetTips("未输入驾驶证号，不能注册");
				return $.SendHtml("103", "UTF-8");
			}
			if ($.empty(txtLicPic)) {
				// $.SetTips("未输入驾驶证号，不能注册");
				return $.SendHtml("104", "UTF-8");
			}
			if ($.empty(txtVerPic)) {
				// $.SetTips("未输入驾驶证号，不能注册");
				return $.SendHtml("105", "UTF-8");
			}
			// ---------------------------------------------------------通过验证，提交注册

			csMemberService.executeTransaction(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
					// 更新会员的扩展信息
					// 更新会员的扩展信息
					CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, csMember.getCsmId()));
					if (csMemberInfo == null) {
						csMemberInfo = new CsMemberInfo();
						csMemberInfo.setNotNull(SystemHelper.getSrvHost().getShId(),csMember.getCsmId(), new Date(), new Date(), (short) 1);
						csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
					}
					CsMemberInfo oldMemberInfo = csMemberInfoService.getCsMemberInfoById(csMemberInfo.getCsmiId());
					// 设置扩展信息：关联的会员帐号，驾照照片路径，驾照号码，真实姓名，更新时间
					csMemberInfo.csmiMemberId(csMember.getCsmId()).csmiDriverImage(txtLicPic).csmiDriverNum(txtLicnum).csmiName(txtName).csmiCertifyImage(txtVerPic).csmiUpdateTime(new Date());
					Date birthday = SystemHelper.getBirthday(txtLicnum);
					if (birthday != null) {
						csMemberInfo.csmiBirthday(birthday);
					}
					csMemberInfo.csmiAddress(txtaddress);
					csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);
					// CsMember csMember = new
					// CsMember().csmId(SystemHelper.getLoginId()).csmInfo(csMemberInfo.getCsmiId()).csmUpdateTime(new
					// Date());
					// 比较修改的内容，决定会员的审核状态,如果任意其中一个被修改过，则更新其对应的状态
					if (!$.equals(txtName, oldMemberInfo.getCsmiName()))
						csMember.setCsmVReal((short) 2);
					if (!$.equals(txtLicPic, oldMemberInfo.getCsmiDriverImage()) || !$.equals(txtLicnum, oldMemberInfo.getCsmiDriverNum()))
						csMember.setCsmVDrive((short) 2);

					// 真实姓名同步到会员表中
					csMember.setCsmName(csMemberInfo.getCsmiName());
					csMember.setCsmInfo(csMemberInfo.getCsmiId());
					csMember.setCsmUpdateTime(new Date());
					csMemberService.updateCsMember$NotNull(csMember);
					// 注册成功
					return null;
				}
			});
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 订单列表信息
	 * 
	 * @return
	 */
	public String order() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		@SuppressWarnings("unchecked")
		Page<CsOrder> page = csOrderService.getCsOrderPage($.getInteger("index", 0), 5, $.add("csoPayMember", csMember.getCsmId()));
		List<CsOrder> list = page.getResult();
		// 付款状态
		for (CsOrder csOrder : list) {
			ICsCreditBillService creditBillService = $.GetSpringBean("csCreditBillService");
			CsCreditBill bill = creditBillService.getCsCreditBill($.add("cscbOrder", csOrder.getCsoId()));
			if (bill != null) {
				csOrder.setValues("csoCreditCardStatus", bill.getCscbStatus().toString());
			} else {
				// 若账单为空，则表示该订单通过余额直接扣款结算
				csOrder.setValues("csoCreditCardStatus", "1");
			}
		}
		page.setResult(list);

		$.SetRequest("page", page);
		return "order";
	}

	/**
	 * 订单列表信息
	 * 
	 * @return
	 */
	public String orderdetail() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		long order = $.getLong("order", 0l);
		CsOrder csOrder = csOrderService.getCsOrderById(order);
		// 不存在定单
		if (csOrder == null)
			return $.SendHtml("false", "UTF-8");
		// 支付会员对不上
		if (!csOrder.getCsoPayMember().equals(csMember.getCsmId()))
			return $.SendHtml("false", "UTF-8");

		ICsCreditBillService creditBillService = $.GetSpringBean("csCreditBillService");
		CsCreditBill bill = creditBillService.getCsCreditBill($.add("cscbOrder", csOrder.getCsoId()));
		if (bill != null) {
			String payStatus = bill.getCscbStatus().toString();
			$.SetRequest("payStatus", payStatus);
			$.SetRequest("mustPay", bill.getCscbValue());
		} else {
			$.SetRequest("payStatus", "1");
		}
		$.SetRequest("csOrder", csOrder);
		return "orderdetail";
	}

	/**
	 * 信用卡账单
	 * 
	 * @return
	 */
	public String orderCredit() {
		// String credit_card = $.getString("credit_card");
		String csoId = $.getString("csoId");
		ICsCreditBillService ccb = $.GetSpringBean("csCreditBillService");
		CsCreditBill creditBill = ccb.getCsCreditBill($.add("cscbOrder", csoId));
		if (creditBill != null) {
			if (creditBill.getCscbStatus().toString().equals("1")) {
				return $.SendHtml("0", SYSTEM.UTF8);
			} else if (creditBill.getCscbStatus().toString().equals("0")) {
				return $.SendHtml(creditBill.getCscbValue().toString(), SYSTEM.UTF8);
			}
		} else {
			return $.SendHtml("-1", SYSTEM.UTF8);
		}
		return null;
	}

	/**
	 * 消费明细
	 * 
	 * @return
	 */
	public String consumerdetails() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		long order = $.getLong("order", 0l);
		CsOrder csOrder = csOrderService.getCsOrderById(order);
		// 不存在定单
		if (csOrder == null)
			return $.SendHtml("false", "UTF-8");
		// 支付会员对不上
		if (!csOrder.getCsoPayMember().equals(csMember.getCsmId()))
			return $.SendHtml("false", "UTF-8");
		$.SetRequest("csOrder", csOrder);
		return "consumerdetails";
	}

	/**
	 * 订单列表信息
	 * 
	 * @return
	 */
	public String ordercontinue() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		long order = $.getLong("order", 0l);
		CsOrder csOrder = csOrderService.getCsOrderById(order);
		// 不存在定单
		if (csOrder == null)
			return $.SendHtml("false", "UTF-8");
		// 支付会员对不上
		if (!csOrder.getCsoPayMember().equals(csMember.getCsmId()))
			return $.SendHtml("false", "UTF-8");
		$.SetRequest("csOrder", csOrder);

		// 最近 7天
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(csOrder.getCsoFinishTime());
		dates.add(csOrder.getCsoFinishTime());
		for (int i = 0; i < 6; i++) {
			calendar.add(Calendar.DATE, 1);
			dates.add(calendar.getTime());
		}
		$.SetRequest("dates", dates);

		// 默认续订1小时，输出新的续订时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(csOrder.getCsoFinishTime());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		$.SetRequest("newFinish", cal.getTime());
		return "ordercontinue";
	}

	/**
	 * 提交续订
	 * 
	 * @return
	 */
	public String renewSubmit() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return $.SendHtml("101", "UTF-8");
			}

			Long order = $.getLong("order", 0l);
			Date newFinishDate = $.getDate("newFinishDate");
			Double freehour = $.getDouble("freehour", 0d);

			CsOrder csOrder = csOrderService.getCsOrderById(order);
			// 不存在定单
			if (csOrder == null)
				return $.SendHtml("false", "UTF-8");
			// 支付会员对不上
			if (!csOrder.getCsoPayMember().equals(csMember.getCsmId()))
				return $.SendHtml("false", "UTF-8");
			if (newFinishDate == null)
				// 新的还车时间不能为空
				return $.SendHtml("102", "UTF-8");
			/*ICsOrderLogService orderlogService = $.GetSpringBean("csOrderLogService");
			Long renewcount = orderlogService.getCsOrderLogCount($.add(CsOrderLog.F.csolOrder, csOrder.getCsoId()).add("definex", "csol_title LIKE '%续订%'"));
			if (renewcount > 3l) {
				return $.SendHtml("104", "UTF-8");
			}
			Date oldFinishDate = csOrder.getCsoFinishTime();
			if ((newFinishDate.getTime() - oldFinishDate.getTime()) > (3 * SYSTEM.HOUR)) {
				// 续订时间不能超过3小时
				return $.SendHtml("103", "UTF-8");
			}*/
			freehour = csOrder.getCsoFreehour();
			CsOrder reOrder = commonDisposeService.executeReOrder(order, newFinishDate, freehour,WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);

			if (csOrder.getCsoCreditCard$() != null) { // 判断信用额度是否够下单
				ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
				if ((reOrder.getCsoPayNeed() - csOrder.getCsoPayNeed()) > commonMoneyService.getUsableMoneyAndCouponAndCredit(csMember.getCsmId())) {
					return $.SendHtml("444", SYSTEM.UTF8);
				}
				Long creditId = csOrder.getCsoCreditCard();
				ICsCreditCardService creditcardService = $.GetSpringBean("csCreditCardService");
				CsCreditCard creditcard = creditcardService.getCsCreditCardById(creditId);
				if (creditcard.getCsccStatus$().equals("2")) {
					return $.SendHtml("444", SYSTEM.UTF8);
				}
			}

			return $.SendHtml("100", SYSTEM.UTF8);
		} catch (com.ccclubs.exception.MessageException oe) {
			return $.SendHtml(oe.getMessage(), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("9999", SYSTEM.UTF8);
		}
	}

	/**
	 * 取消订单确认信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String confirm() {
		try {
			Long orderid = $.getLong("orderid");
			CsOrder csOrder = csOrderService.getCsOrderById(orderid);

			Date now = new Date();
			// 订单时长
			Double longer = new Double(csOrder.getCsoFinishTime().getTime() - csOrder.getCsoStartTime().getTime()) / new Double(1000 * 60 * 60);
			// 取消时间
			Double hour = new Double(csOrder.getCsoStartTime().getTime() - now.getTime()) / new Double(1000 * 60 * 60);
			Double scale = 1.0d;
			String sDescript = "";
			if (longer > 24) {
				if (hour > 8) {
					scale = 0.1d;
					sDescript = "大于24小时订单，取消时间大于8小时";
				} else {
					scale = 1.0d;
					sDescript = "大于24小时订单，取消时间小于8小时";
				}
			} else {
				if (hour > 1) {
					scale = 0.1d;
					sDescript = "小于24小时订单，取消时间大于1小时";
				} else {
					scale = 1.0d;
					sDescript = "小于24小时订单，取消时间小于1小时";
				}
			}

			ICommonOrderService commonOrderService = $.getBean("commonOrderService");
			// 拆分订单
			OrderInfo orderinfo = commonOrderService.splitOrderDetails(csOrder.getCsoPayMember(), csOrder.getCsoCar(), csOrder.getCsoStartTime(), csOrder.getCsoFinishTime(), csOrder.getCsoRetTime(), csOrder.getCsoFreehour(), csOrder.getCsoElectric(), csOrder.getCsoFuel(), csOrder.getCsoFeeType(),
					csOrder.getCsoInsureType(), csOrder.getCsoLongPrice(), csOrder.getCsoId());
			// 设置订单需付额
			csOrder.setCsoPayNeed(orderinfo.getPrice());
			csOrder.setCsoPayReal(orderinfo.getPrice() * scale);

			double payReal = orderinfo.getPrice() * scale;
			return $.SendHtml("取消本订单将扣除" + $.digit(payReal, 2) + "元，确定取消订单？", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips(SYSTEM.ERROR_TIPS);
			return $.SendHtml("false", "UTF-8");
		}
	}

	/**
	 * 取消订单
	 * 
	 * @return
	 */
	public String ordercancel() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return $.SendHtml("101", "UTF-8");
			}
			Long orderid = $.getLong("orderid");
			CsOrder order = csOrderService.getCsOrderById(orderid);
			// 验证当前订单是不是当前用户的
			if (order == null || !order.getCsoPayMember().equals(csMember.getCsmId())) {
				return $.SendHtml("102", "UTF-8");
			}
			if (order.getCsoStatus() != 0)
				return $.SendHtml("103", "UTF-8");
			commonDisposeService.executeCancelOrder(orderid, "会员自主取消订单",WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);
			// $.SetTips("订单取消成功!");
			return $.SendHtml("100", SYSTEM.UTF8);
		} catch (com.ccclubs.exception.MessageException oe) {
			// $.SetTips(oe.getMessage());
			return $.SendHtml(oe.getMessage(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips(SYSTEM.ERROR_TIPS);
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 更多订单信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String ordermore() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		Page page = csOrderService.getCsOrderPage($.getInteger("page", 0), 5, $.add("csoPayMember", csMember.getCsmId()));

		$.SetRequest("page", page);
		return "ordermore";
	}

	public String coinsof88() {

		return "coinsof88";
	}

	/**
	 * 提交退款
	 * 
	 * @return
	 */
	public String refundsubmit() {
		try {
			CsMember member = WeixinHelper.getCsMember();
			if (member == null || $.empty(WeixinHelper.getOpenId())) {
				return $.SendHtml("101", "UTF-8");
			}
			// CsMember member =
			// csMemberService.getCsMemberById(SystemHelper.getLoginId());
			if (member.getCsmMoney() <= 0) {
				return $.SendHtml("102", "UTF-8");
			}
			// Long memberId = $.getLong("member", 0l);
			// if (!memberId.equals(member.getCsmId())) {
			// return $.SendHtml("103", "UTF-8");
			// }
			if (commonMoneyService.haveUnFinishOrder(member.getCsmId())) {
				return $.SendHtml("104", "UTF-8");
			}
			if (commonMoneyService.isRefunding(member.getCsmId())) {
				return $.SendHtml("105", "UTF-8");
			}
			List<CsOrder> csOrderList = csOrderService.getCsOrderList($.add(CsOrder.F.csoPayMember, member.getCsmId()).add(CsOrder.F.csoStatus, 4).add("desc", CsOrder.C.csoRetTime), -1);
			// 没有定单
			if (csOrderList.size() == 0) {
				// 没有用车记录，3个工作日退款
				noRented(member);
				return $.SendHtml("100", "UTF-8");
			} else {
				// 取最后一个订单，如果最后一个订单还车时间是在 2015-02-15 17：00 之前还车的
				// ----------------------------------------- 铁定不是春节订单
				if (csOrderList.get(0).getCsoFinishTime().before(SystemHelper.getDate(2015, 1, 15, 17, 0, 0))) {
					if (csOrderList.get(0).getCsoRetTime() != null) {
						Date now = new Date();
						if (now.after(WidgetHelper.getWorkDay(7, csOrderList.get(0).getCsoRetTime()))) {
							// 7个工作日没用车了，相当于没有车记录，3个工作日退款
							noRented(member);
							return $.SendHtml("100", "UTF-8");
						} else {
							CsRefund newCsRefund = new CsRefund();
							newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(11, csOrderList.get(0).getCsoRetTime()), WidgetHelper.getWorkDay(11,
									csOrderList.get(0).getCsoRetTime()), (short) 0);
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
					Date start = SystemHelper.getDate(2015, 1, 17, 17, 0, 0);
					Date finish = SystemHelper.getDate(2015, 1, 25, 9, 0, 0);
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
								newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(11, csOrderList.get(0).getCsoRetTime()), WidgetHelper.getWorkDay(11,
										csOrderList.get(0).getCsoRetTime()), (short) 0);
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
							Date refundDate = WidgetHelper.getWorkDay(11, csOrderList.get(0).getCsoRetTime());
							Date refundLastDate = WidgetHelper.getWorkDay(4);
							if (refundDate.after(now)) {
								// 7个工作日没用车了，相当于没有车记录，3个工作日退款
								if (refundLastDate.after(refundDate)) {
									noRented(member);
									return $.SendHtml("100", "UTF-8");
								} else {
									CsRefund newCsRefund = new CsRefund();
									newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), refundDate, refundDate, (short) 0);
									newCsRefund.csrMobile(member.getCsmMobile());
									csRefundService.saveCsRefund(newCsRefund);
									return $.SendHtml("100", "UTF-8");
								}
							} else {
								if (refundLastDate.after(refundDate)) {
									noRented(member);
									return $.SendHtml("100", "UTF-8");
								} else {
									CsRefund newCsRefund = new CsRefund();
									newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), refundDate, refundDate, (short) 0);
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
								newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), new Date(springOrder.getCsoFinishTime().getTime() + SYSTEM.DAY * 35), new Date(springOrder
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
		newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(4), WidgetHelper.getWorkDay(4), (short) 0);
		newCsRefund.csrMobile(member.getCsmMobile());
		csRefundService.saveCsRefund(newCsRefund);
	}

	/**
	 * 取消退款
	 * 
	 * @return
	 */
	public String refundcancel() {
		try {
			CsMember member = WeixinHelper.getCsMember();
			if (member == null || $.empty(WeixinHelper.getOpenId())) {
				return $.SendHtml("101", "UTF-8");
			}

			if (!commonMoneyService.isRefunding(member.getCsmId())) {
				return $.SendHtml("103", "UTF-8");
			}
			final CsRefund csRefund = csRefundService.getCsRefund($.add("csrMember", member.getCsmId()).add("definex", "csr_status<2"));
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

	/**
	 * 发送短信
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String sms() {
		try {
			String mobile = $.getString("mobile", "");
			int type = $.getInteger("type", 0);
			if ($.empty(mobile))
				// 手机号码不能为空
				return $.SendHtml("101", "UTF-8");
			if (!SystemHelper.isMobile(mobile))
				// 手机号码格式错误
				return $.SendHtml("101", "UTF-8");
			// 验证短信发送频率
			Date last = $.getSession(SYSTEM.REGIST_VAILD_LAST);
			if (last == null) {
				Logger.getRootLogger().warn("调用/ajax_sms.html发送短信之前必须在session中添加一个标识为SYSTEM.REGIST_VAILD_LAST的时间");
				// 不允许使用该接口发送短信
				return $.SendHtml("false", "UTF-8");
			}
			if ((new Date().getTime() - last.getTime()) < SYSTEM.MINUTE)
				// 两次注册短信验证码发送时间间隔不能小于一分钟
				return $.SendHtml("102", "UTF-8");
			String strCode = $.zerofill($.rand(9999), 4);
			// System.out.printf("短信校验码：" + strCode);
			Boolean bOk = false;
			if (type == 0) {
				bOk = UtilHelper.sendTemplateSMS($.getLong("host"), "REGIST_CODE", mobile, CommonMessage.MSG0200, SMSType.代码类短信, $.add("code", strCode));
			} else {
				bOk = UtilHelper.sendTemplateSMS($.getLong("host"), "FORGOT_PWD", mobile, CommonMessage.MSG0201, SMSType.提示类短信, $.add("code", strCode));
			}
			// Boolean bOk = true;
			if (bOk == true) {
				$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date());
				$.setSession(SYSTEM.REGIST_VAILD_CODE, strCode);
				return $.SendHtml("success", "UTF-8");
			} else
				// 验证码发送失败，请联系管理员
				return $.SendHtml("false", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// 系统繁忙，请稍候再试
			return $.SendHtml("false", "UTF-8");
		}
	}

	/**
	 * 判断会员是否已经存在
	 * 
	 * @return false：格式不正确，验证未通过 true：格式正确，可以使用 used：已存在
	 * 
	 */
	public String exist() {
		try {
			String strEmail = $.getString("txtEmail", "");
			String strUsername = $.getString("txtUserName", "");
			String strMobile = $.getString("txtMobile", "");
			Map<String, Object> params = new HashMap<String, Object>();
			if (!$.empty(strEmail) && SystemHelper.isEmail(strEmail))
				params.put(CsMember.F.csmEmail, strEmail);
			else if (!$.empty(strUsername) && SystemHelper.isUserName(strUsername))
				params.put(CsMember.F.csmUsername, strUsername);
			else if (!$.empty(strMobile) && SystemHelper.isMobile(strMobile))
				params.put(CsMember.F.csmMobile, strMobile);
			else
				return $.SendHtml("false", "UTF-8");

			CsMember existMember = csMemberService.getCsMember(params);
			if (existMember != null)
				return $.SendHtml("used", "UTF-8");
			else
				return $.SendHtml("true", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("false", "UTF-8");
		}
	}

	/**
	 * 依据区域，获得该区域下的所有网点，传入参数为 int 类型 area
	 * 
	 * @return 网点 UTF-8 编码 Json 数据
	 */
	public String outletsByAreaCanorder() {
		Map<String, Object> params = new HashMap<String, Object>();
		int area = $.getInteger("area", -1);
		params.put(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId());
		params.put(CsOutlets.F.csoArea, area);
		params.put(CsOutlets.F.csoCanOrder, 1);
		// 按照能预订的网点排前面
		params.put("desc", CsOutlets.C.csoPriority + " desc, " + CsOutlets.C.csoAddTime);
		ICsOutletsService csOutletsService = $.getBean("csOutletsService");
		List<CsOutlets> outlets = csOutletsService.getCsOutletsList(params, -1);
		return Lazy.SendAjax(outlets, SYSTEM.UTF8);
	}

	/**
	 * 提交充值
	 * 
	 * @return
	 * @throws Exception
	 */
	public String alipay() {
		CsMember csMember = WeixinHelper.getCsMember();

		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		Double amount = $.getDouble("alimoney");
		if (!$.getBoolean("type", true)) {
			String cerNo = "";
			if (csMember.get$csmInfo() != null && !$.empty(csMember.get$csmInfo().getCsmiDriverNum())) {
				cerNo = csMember.get$csmInfo().getCsmiDriverNum();
			}
			// ----------------------杭州银行支付------------------------------
			RechargeHelper.payFromHzbank(csMember, amount, cerNo);
			// ----------------------杭州银行支付------------------------------
		} else {
			// ----------------------支付宝支付------------------------------
			RechargeHelper.payFromAlipay(csMember, amount, 0l, 0);
			// ----------------------支付宝支付------------------------------
		}
		return null;
	}

	/**
	 * 提交购买现金券
	 * 
	 * @return
	 */
	public String buyCoupon() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();

			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
			Long csiId = $.getLong("csiId", 0l);
			CsItem csItem = csItemService.getCsItemById(csiId);
			if (csItem == null) {
				return $.SendHtml("false", "UTF-8");
			}
			int purNum = $.getInteger("purNum", 0);
			if (purNum == 0) {
				return $.SendHtml("false", "UTF-8");
			}
			// ----------------------------去充值，计算需要充值金额
			Double amount = csMember.getCsmMoney() - csItem.getCsiPrice() * csItem.getCsiRebate() * purNum;

			// amount >=0 直接使用余额购买现金券
			if (amount >= 0) {
				submitBuyCoupon(csMember, csItem.getCsiPrice() * csItem.getCsiRebate() * purNum, csItem.getCsiPrice() * purNum);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.sendRedirect($.GetRequest("basePath") + "/weixin/account_coupon.html?from=" + $.getString("from"));
				return null;
			} else {
				// ----------------------支付宝支付------------------------------
				RechargeHelper.payFromAlipay(csMember, amount * -1, csiId, purNum);
				return null;
			}
			// ----------------------支付宝支付------------------------------
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 支付宝充值通知-服务器异步通知页面
	 */
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public String notice() throws URISyntaxException {
		String classPath = AccountAction.class.getResource("/").toURI().getPath() + "rechargeTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_alipay.txt";
		try {
			HttpServletRequest request = $.getServletRequest();
			String notifyMsg = request.getParameter("notifyMsg");
			if ($.empty(notifyMsg)) {
				// 获取支付宝POST过来反馈信息
				Map<String, String> params = new HashMap<String, String>();
				Map requestParams = request.getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
					}
					// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
					// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
					// "gbk");
					params.put(name, valueStr);
				}

				Logger.getLogger("alipay").debug($.getQueryString("UTF-8"));

				// 解密（如果是RSA签名需要解密，如果是MD5签名则下面一行清注释掉）
				// Map<String,String> decrypt_params =
				// AlipayNotify.decrypt(params);
				// XML解析notify_data数据
				Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));

				// 商户订单号
				String out_trade_no = doc_notify_data.selectSingleNode("//notify/out_trade_no").getText();

				// 支付宝交易号
				String trade_no = doc_notify_data.selectSingleNode("//notify/trade_no").getText();

				// 交易状态
				String trade_status = doc_notify_data.selectSingleNode("//notify/trade_status").getText();

				// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

				// 支付人支付宝帐号
				String buyer_email = doc_notify_data.selectSingleNode("//notify/buyer_email").getText();

				// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
				if (AlipayNotify.verifyNotify(params)) {// 验证成功
					// ////////////////////////////////////////////////////////////////////////////////////////
					// 请在这里加上商户的业务逻辑程序代码

					// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						updatePaySuccess(out_trade_no, trade_no, buyer_email);
						// 注意：
						// 该种交易状态只在两种情况下出现
						// 1、开通了普通即时到账，买家付款成功后。
						// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
					}
					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + out_trade_no + " - " + trade_no + " - notice -success", "UTF-8");
					return $.SendHtml("success", SYSTEM.UTF8); // 请不要修改或删除
					// ////////////////////////////////////////////////////////////////////////////////////////
				} else {// 验证失败
					// WeixinHelper.writeAppendToFile(classPath,
					// DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " +
					// out_trade_no + " - " + trade_no + " - notice -fail0",
					// "UTF-8");
					return $.SendHtml("fail", SYSTEM.UTF8);
				}
			} else {
				$.trace(notifyMsg);
				// ---------------------------------杭州银行返回
				int lastIndex = notifyMsg.lastIndexOf("|");

				String signMsg = notifyMsg.substring(lastIndex + 1, notifyMsg.length()); // 获取签名信息

				String srcMsg = notifyMsg.substring(0, lastIndex + 1);

				int veriyCode = -1;

				NetSignServer nss = new NetSignServer();
				nss.NSDetachedVerify(signMsg.getBytes("GBK"), srcMsg.getBytes("GBK"));
				veriyCode = nss.getLastErrnum();
				if (veriyCode < 0) { // 验签出错
					$.trace("商户端验证签名失败：return code:" + veriyCode);
					return null;
				}
				java.util.StringTokenizer stName = new java.util.StringTokenizer(srcMsg, "|"); // 拆解通知结果到Vector

				java.util.Vector vc = new java.util.Vector();

				int i = 0;

				while (stName.hasMoreTokens()) {
					String value = (String) stName.nextElement();

					if (value.equals(""))
						value = "&nbsp;";

					vc.add(i++, value);
				}
				// 付款成功
				if (vc.get(7).equals("1")) {
					updatePaySuccess(vc.get(3).toString(), vc.get(10).toString(), "");
				}
				return $.SendHtml("success", SYSTEM.UTF8);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log
			// String classPath =
			// AccountAction.class.getResource("/").toURI().getPath() +
			// "alipayTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") +
			// "_wxMessage.txt";
			Logger.getRootLogger().error(e.getMessage(),e);
			WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - notice -fail2", "UTF-8");
			return $.SendHtml("fail", SYSTEM.UTF8);
		}
	}

	/**
	 * 支付宝充值同步返回
	 */
	@SuppressWarnings( { "rawtypes", "unused" })
	public String callback() {
		try {
			HttpServletRequest request = $.getServletRequest();
			String orderSTT = request.getParameter("orderSTT");
			if ($.empty(orderSTT)) {
				// 获取支付宝POST过来反馈信息
				Map<String, String> params = new HashMap<String, String>();
				Map requestParams = request.getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
					}
					// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
					// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
					// "gbk");
					params.put(name, valueStr);
				}

				Logger.getLogger("alipay").info($.getQueryString("UTF-8"));

				// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
				// 商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 支付宝交易号

				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 交易状态
				String result = new String(request.getParameter("result").getBytes("ISO-8859-1"), "UTF-8");

				// 支付人支付宝帐号
				String buyer_email = "";

				// log
				String classPath = AccountAction.class.getResource("/").toURI().getPath() + "rechargeTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_alipay.txt";
				WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + out_trade_no + " - " + trade_no + " - notice", "UTF-8");

				// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

				// 计算得出通知验证结果
				boolean verify_result = AlipayNotify.verifyReturn(params);

				if (verify_result) {// 验证成功
					// ////////////////////////////////////////////////////////////////////////////////////////
					// 请在这里加上商户的业务逻辑程序代码

					// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					if (result.equals("success")) {
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						/** **************本地业务处理******************* */
						// updatePaySuccess(out_trade_no, trade_no,
						// buyer_email);
						/** **************本地业务处理******************* */
						// 该页面可做页面美工编辑
						$.SetRequest("success", true);
						CsAlipayRecord csAlipayRecord = csAlipayRecordService.getCsAlipayRecord($.add("csarTradeNo", out_trade_no));
						$.SetRequest("from", csAlipayRecord.getCsarBlank());
					}
					// $.SetRequest("amount", total_fee);
					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					// ////////////////////////////////////////////////////////////////////////////////////////
				} else {
					// 该页面可做页面美工编辑
					$.SetRequest("success", false);// out.println("验证失败");
				}
			} else {
				// merID|orderDate|orderTime|orderId|currencyType|amount|remark|orderSTT|tranDate|tranTime|pyFlownNo
				// ---------------------------------杭州银行返回
				// $.trace("orderSTT=" + orderSTT);
				String hzbankOpenId = $.getString("openId");
				Logger.getLogger("hzbank").info($.getQueryString("UTF-8"));
				if (orderSTT.equals("1")) {
					$.SetRequest("success", true);
					// CsAlipayRecord csAlipayRecord =
					// csAlipayRecordService.getCsAlipayRecord($.add("csarTradeNo",
					// out_trade_no));
					$.SetRequest("from", hzbankOpenId);
				} else {
					$.SetRequest("success", false);
					// CsAlipayRecord csAlipayRecord =
					// csAlipayRecordService.getCsAlipayRecord($.add("csarTradeNo",
					// out_trade_no));
					$.SetRequest("from", hzbankOpenId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetRequest("success", false);
		}
		return "callback";
	}

	/**
	 * 更新支付结果成功
	 */
	public void updatePaySuccess(final String out_trade_no, final String ali_trade_no, final String buyer_email) {
		csAlipayRecordService.executeTransaction(new Function() {
			@Override
			public <T> T execute(Object... arg0) {
				Logger.getRootLogger().info("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]");
				CsAlipayRecord csAlipayRecord = csAlipayRecordService.getCsAlipayRecord($.add("csarTradeNo", out_trade_no));
				$.SetRequest("csAlipayRecord", csAlipayRecord);
				if (csAlipayRecord == null) {
					Logger.getRootLogger().info("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]错误 - 无法找到相关的充值记录 ");
					return null;
				} else if (csAlipayRecord.getCsarStatus().shortValue() == 0) {
					Logger.getRootLogger().info("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "] - 充值成功");
					// 修改充值记录为已成功
					csAlipayRecordService.updateCsAlipayRecord$NotNull(new CsAlipayRecord().csarAlipayNo(ali_trade_no)// 支付宝交易号
							.csarStatus((short) 1)// 状态为成功
							.csarBuyerAccount(buyer_email)// 支付宝帐号
							.csarId(csAlipayRecord.getCsarId())// 原充值记录ID
							.csarUpdateTime(new Date())// 更新时间为当前时间
							);
					// 修改会员余额帐户
					String remark = "";
					if (csAlipayRecord.getCsarPayMethod().equals("wap01")) {
						remark = CommonMessage.MSG0309.replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));
					} else if (csAlipayRecord.getCsarPayMethod().equals("HZBANK")) {
						remark = CommonMessage.MSG0313.replace("{bank}", SystemHelper.convertBank("HZCBB2C")).replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));
					} else {
						remark = CommonMessage.MSG0313.replace("{bank}", SystemHelper.convertBank(csAlipayRecord.getCsarBlank())).replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));
					}
					commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Money, csAlipayRecord.getCsarAmount(), RecordType.支付宝充值, remark, null, csAlipayRecord.getCsarId(),CsAlipayRecord.class);
					// 是否有购买现金券
					if (csAlipayRecord.getCsarBuyItem() != null && csAlipayRecord.getCsarBuyItem() != 0l && csAlipayRecord.getCsarBuyCount() != null && csAlipayRecord.getCsarBuyCount() != 0) {
						CsItem csItem = csAlipayRecord.get$csarBuyItem();
						if (csItem != null) {
							if (csItem.getCsiDataString().equals("MONTH")) {
								// 每月仅一次
								// 判断本月是否已经购买,购买成功的记录
								Calendar calendarStart = Calendar.getInstance();
								calendarStart.set(calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DATE), 0, 0, 0);
								calendarStart.set(Calendar.DAY_OF_MONTH, 1);

								Calendar calendarEnd = Calendar.getInstance();
								calendarEnd.set(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH), calendarEnd.get(Calendar.DATE), 23, 59, 59);
								calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMaximum(Calendar.DAY_OF_MONTH));

								List<CsAlipayRecord> newCsAlipayRecord = csAlipayRecordService.getCsAlipayRecordList($.add(CsAlipayRecord.F.csarBuyItem, csAlipayRecord.getCsarBuyItem()).add(CsAlipayRecord.F.csarMember, csAlipayRecord.getCsarMember()).add(CsAlipayRecord.F.csarStatus, 1).add(
										CsAlipayRecord.F.csarAddTime + "Start", calendarStart.getTime()).add(CsAlipayRecord.F.csarAddTime + "End", calendarEnd.getTime()), -1);
								if (newCsAlipayRecord.size() > 1) {
									// 扣保证金，加现金券
									commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Money, csAlipayRecord.getCsarBuyCount() * csItem.getCsiPrice() * csItem.getCsiRebate() * -1, RecordType.购买现金券, "欢迎使用支付宝钱包购买现金券。", null, csAlipayRecord.getCsarId(),CsAlipayRecord.class);
									commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Coupon, csAlipayRecord.getCsarBuyCount() * csItem.getCsiPrice(), RecordType.现金券充值, "欢迎使用支付宝钱包购买现金券。", null, csAlipayRecord.getCsarId(),CsAlipayRecord.class);
								} else {
									// 扣保证金，加现金券
									commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Money, csAlipayRecord.getCsarBuyCount() * csItem.getCsiPrice() * csItem.getCsiRebate() * -1, RecordType.购买现金券, "欢迎使用支付宝钱包购买现金券。", null, csAlipayRecord.getCsarId(),CsAlipayRecord.class);
									commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Coupon, csAlipayRecord.getCsarBuyCount() * csItem.getCsiPrice() + csItem.getCsiDataNumber() * csAlipayRecord.getCsarBuyCount(), RecordType.现金券充值, "欢迎使用支付宝钱包购买现金券。", null, csAlipayRecord
											.getCsarId(),CsAlipayRecord.class);
								}
							} else {
								// 扣保证金，加现金券
								commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Money, csAlipayRecord.getCsarBuyCount() * csItem.getCsiPrice() * csItem.getCsiRebate() * -1, RecordType.购买现金券, "欢迎使用支付宝钱包购买现金券。", null, csAlipayRecord.getCsarId(),CsAlipayRecord.class);
								commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Coupon, csAlipayRecord.getCsarBuyCount() * csItem.getCsiPrice() + csItem.getCsiDataNumber() * csAlipayRecord.getCsarBuyCount(), RecordType.现金券充值, "欢迎使用支付宝钱包购买现金券。", null, csAlipayRecord
										.getCsarId(),CsAlipayRecord.class);
							}
							// 更新购买次数
							csItemService.updateCsItem$NotNull(new CsItem().csiId(csItem.getCsiId()).csiSale(csItem.getCsiSale() + csAlipayRecord.getCsarBuyCount()));
						}
					}
					return null;
				} else if (!$.empty(buyer_email)) {
					Logger.getRootLogger().info("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "] - 更新支付宝帐号");
					// 修改支付宝帐号
					csAlipayRecordService.updateCsAlipayRecord$NotNull(new CsAlipayRecord().csarAlipayNo(ali_trade_no)// 支付宝交易号
							.csarBuyerAccount(buyer_email)// 支付宝帐号
							.csarId(csAlipayRecord.getCsarId())// 原充值记录ID
							.csarUpdateTime(new Date())// 更新时间为当前时间
							);
					return null;
				} else {// 放弃处理
					Logger.getRootLogger().info("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]放弃处理 - " + $.json(csAlipayRecord));
					return null;
				}
			}
		});
	}

	/**
	 * 购买现金券
	 * 
	 * @param member
	 * @param money
	 *            扣的保证金数额
	 * @param coupon
	 *            增加的现金券数额
	 * @return
	 */
	private String submitBuyCoupon(final CsMember member, final double money, final double coupon) {
		try {
			ICsRecordService csRecordService = $.getBean("csRecordService");
			// 购买现金券
			String result = csRecordService.executeTransaction(new Function() {
				@SuppressWarnings("unchecked")
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
			return "false";
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
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

	public ICsMemberInfoService getCsMemberInfoService() {
		return csMemberInfoService;
	}

	public void setCsMemberInfoService(ICsMemberInfoService csMemberInfoService) {
		this.csMemberInfoService = csMemberInfoService;
	}

	public ICommonDisposeService getCommonDisposeService() {
		return commonDisposeService;
	}

	public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
		this.commonDisposeService = commonDisposeService;
	}

	public ICsRefundService getCsRefundService() {
		return csRefundService;
	}

	public void setCsRefundService(ICsRefundService csRefundService) {
		this.csRefundService = csRefundService;
	}

	public ICsMemberGroupService getCsMemberGroupService() {
		return csMemberGroupService;
	}

	public void setCsMemberGroupService(ICsMemberGroupService csMemberGroupService) {
		this.csMemberGroupService = csMemberGroupService;
	}

	public ICsAlipayRecordService getCsAlipayRecordService() {
		return csAlipayRecordService;
	}

	public void setCsAlipayRecordService(ICsAlipayRecordService csAlipayRecordService) {
		this.csAlipayRecordService = csAlipayRecordService;
	}

	public ICsItemService getCsItemService() {
		return csItemService;
	}

	public void setCsItemService(ICsItemService csItemService) {
		this.csItemService = csItemService;
	}

	public ICsRechargeCardService getCsRechargeCardService() {
		return csRechargeCardService;
	}

	public void setCsRechargeCardService(ICsRechargeCardService csRechargeCardService) {
		this.csRechargeCardService = csRechargeCardService;
	}

	public ICsCreditCardService getCs_Credit_CardService() {
		return cs_Credit_CardService;
	}

	public void setCs_Credit_CardService(ICsCreditCardService cs_Credit_CardService) {
		this.cs_Credit_CardService = cs_Credit_CardService;
	}
}
