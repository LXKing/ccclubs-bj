package com.ccclubs.action.m.home;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
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

import com.ccclubs.action.m.WeixinHelper;
import com.ccclubs.action.m.util.ResponseEnvelope;
import com.ccclubs.action.weixin.RechargeHelper;
import com.ccclubs.action.weixin.alipay.util.AlipayNotify;
import com.ccclubs.action.weixin.hzbank.NetSignServer;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.RSAUtil;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.WidgetHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsComplain;
import com.ccclubs.model.CsConfig;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsRefund;
import com.ccclubs.model.CsViolat;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsComplainService;
import com.ccclubs.service.admin.ICsConfigService;
import com.ccclubs.service.admin.ICsCreditCardService;
import com.ccclubs.service.admin.ICsFreeHourService;
import com.ccclubs.service.admin.ICsItemService;
import com.ccclubs.service.admin.ICsMemberGroupService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsRechargeCardService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.admin.ICsRefundService;
import com.ccclubs.service.admin.ICsViolatService;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.util.DateUtil;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

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
	
	public String identity(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		ICsMemberInfoService memberinfoservie = $.GetSpringBean("csMemberInfoService");
		CsMemberInfo csMemberInfo = memberinfoservie.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, csMember.getCsmId()));
		String str = "";
		if(csMemberInfo!=null){
			if(csMemberInfo.getCsmiDriverNum().trim().length()==18){
				str = csMemberInfo.getCsmiDriverNum().substring(0,3) + "*******" + csMemberInfo.getCsmiDriverNum().substring(15, csMemberInfo.getCsmiDriverNum().length());
			}
		}else{
			str = "*******";
		}
		
		$.SetRequest("csMember", csMember);
		$.SetRequest("csMemberInfo", csMemberInfo);
		$.SetRequest("csMemberDriver", str);
		return "identiy";
	}
	
	public String violat(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		ICsViolatService voilatService = $.GetSpringBean("csViolatService");
		List<CsViolat> list = voilatService.getCsViolatList($.add(CsViolat.F.csvMember, csMember.getCsmId()), null);
		$.SetRequest("violatlist", list);
		return "violat";
	}
	
	public String violatdetail(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		ICsViolatService voilatService = $.GetSpringBean("csViolatService");
		CsViolat violat = voilatService.getCsViolatById($.getLong("violateid", 0l));
		
		Calendar ca = Calendar.getInstance();
		ca.setTime(violat.getCsvHappenTime());
		ca.add(Calendar.DAY_OF_MONTH, 15);
		Date dayafter = ca.getTime();
		
		$.SetRequest("violat", violat);
		$.SetRequest("dayafter", new DateUtil().dateToString(dayafter, "yyyy年MM月dd日 HH:mm"));
		return "violatdetail";
	}
	
	public String money(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		// 强制刷新当前登录信息
		WeixinHelper.refreshLoginSession();
		csMember = WeixinHelper.getCsMember();
		
		ICsConfigService configservie = $.GetSpringBean("csConfigService");
		CsConfig config = configservie.getCsConfig($.add(CsConfig.F.cscHost, csMember.getCsmHost()));
		
		//有无退款中的钱
		List<CsRefund> list = csRefundService.getCsRefundList($.add(CsRefund.F.csrMember, csMember.getCsmId()).add("definex", "(csr_status=0 or csr_status=1)"), -1);
		if(!list.isEmpty()){
			$.SetRequest("refund", 1);
		}else{
			$.SetRequest("refund", 0);
		}
		$.SetRequest("csMember", csMember);
		$.SetRequest("config", config);
		return "money";
	}
	
	public String recharge(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		$.SetRequest("csMember", csMember);
		return "recharge";
	}
	
	public String alipay() {
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
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
	
	public String coupon(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		// 强制刷新当前登录信息
		WeixinHelper.refreshLoginSession();
		csMember = WeixinHelper.getCsMember();
		
		ICsConfigService configservie = $.GetSpringBean("csConfigService");
		CsConfig config = configservie.getCsConfig($.add(CsConfig.F.cscHost, csMember.getCsmHost()));
		$.SetRequest("csMember", csMember);
		$.SetRequest("config", config);
		return "coupon";
	}
	
	public String buycoupon(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		List<CsItem> list = csItemService.getCsItemList($.add(CsItem.F.csiFlag, "COMMON_COUPON").add(CsItem.F.csiStatus, 1).add("asc", CsItem.C.csiPrice), -1);
		$.SetRequest("list", list);
		if (list.size() > 0) {
			$.SetRequest("csItem", list.get(0));
		}
		$.SetRequest("csMember", csMember);
		return "buycoupon";
	}
	
	public String buyCouponsubmit(){
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
			int purNum = Math.abs($.getInteger("purNum", 0));
			if (purNum == 0) {
				return $.SendHtml("false", "UTF-8");
			}
			
			// ----------------------------去充值，计算需要充值金额
			Double amount = csMember.getCsmMoney() - csItem.getCsiPrice() * csItem.getCsiRebate() * purNum;

			// amount >=0 直接使用余额购买现金券
			if (amount >= 0) {
				submitBuyCoupon(csMember, csItem.getCsiPrice() * csItem.getCsiRebate() * purNum, csItem.getCsiPrice() * purNum);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.sendRedirect($.GetRequest("basePath") + "/m/home/account_buycoupon.html");
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
	
	public String coin(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		$.SetRequest("coin_all", commonMoneyService.getCoin(null, csMember.getCsmId(), new java.util.Date()));
		$.SetRequest("coin_hz", commonMoneyService.getCoin(1l, csMember.getCsmId(), new java.util.Date()));
		$.SetRequest("coin_bj", commonMoneyService.getCoin(2l, csMember.getCsmId(), new java.util.Date()));
		$.SetRequest("coin_cz", commonMoneyService.getCoin(3l, csMember.getCsmId(), new java.util.Date()));
		$.SetRequest("coin_qd", commonMoneyService.getCoin(5l, csMember.getCsmId(), new java.util.Date()));
		$.SetRequest("coin_nb", commonMoneyService.getCoin(11l, csMember.getCsmId(), new java.util.Date()));

		return "coin";
	}
	
	public String coinarea(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		Long host = 0l;
		if($.getString("host")==null){
			SrvHost srvhost = SystemHelper.getSrvHostByClientIp();
			host = srvhost.getShId();
		}else{
			host = $.getLong("host");
		}
		List<CsCoin> list = commonMoneyService.getCanUseCoinList(host, csMember.getCsmId(), new java.util.Date());
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
		return "coinarea";
	}
	
	public String exchangecoin(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		return "exchangecoin";
	}
	
	public String exchangecoinsubmit(){
		try{
			CsMember csMember = WeixinHelper.getCsMember();
			if (!WeixinHelper.isCorrectOpenId()) {
				// 微信openid不合规范
				return WeixinHelper.gotoLogin();
			}
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
			
			final String csrcNumber = $.getString("cardNum");
			final String csrcPassword = $.getString("checkcode");
			// 开始兑换
			int result = commonMoneyService.executeRechangeCard(csrcNumber, csrcPassword, csMember.getCsmId(), "恭喜您，兑换现金券成功");
			return $.SendHtml(result+"", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("9999", "UTF-8");
		}
	}
	
	public String freehours(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		ICsFreeHourService freeservice = $.GetSpringBean("csFreeHourService");
		List<CsFreeHour> list= freeservice.getCsFreeHourList($.add(CsFreeHour.F.csfhMember, csMember.getCsmId()).add(CsFreeHour.F.csfhStatus, 1), -1);
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, 7);
		Double renum = 0d;
		for (CsFreeHour cc : list) {
			if (cc.getCsfhEnd().before(ca.getTime())) {
				renum += cc.getCsfhRemain();
			}
		}

		$.SetRequest("renum", renum);
		$.SetRequest("freelist", list);
		return "freehours";
	}
	
	public String integral(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		ICsConfigService configservie = $.GetSpringBean("csConfigService");
		CsConfig config = configservie.getCsConfig($.add(CsConfig.F.cscHost, csMember.getCsmHost()));
		$.SetRequest("csMember", csMember);
		$.SetRequest("config", config);
		return "integral";
	}
	
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
			$.SetRequest("msg", "&nbsp;&nbsp;&nbsp;&nbsp;你尚未进行驾驶身份认证，暂不能享受免押金服务。<br>&nbsp;&nbsp;&nbsp;&nbsp;通过驾驶身份认证后，系统会根据您的支付宝和芝麻信用状况，车纷享将授予您特定免押金额度。<br><br><div style=\"text-align:center;\"><a href=\"m/register_identity.html");
		} else {
			String token = WeixinHelper.getAlipayZhimaToken(csMember);
			boolean result = WeixinHelper.getAlipayZhimaScore(csMember, token);
			ICsCreditCardService cardservice = $.GetSpringBean("csCreditCardService");
			if(result){
				//是否在风险名单
				CsCreditCard riskcard = cardservice.getCsCreditCard($.add(CsCreditCard.F.csccMember, csMember.getCsmId()));
				WeixinHelper.getAlipayRisk(riskcard);
			}
			
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
			CsCreditCard card = cardservice.getCsCreditCard($.add("csccMember", csMember.getCsmId()));
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
	
	public String exchangefreehours(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		$.SetRequest("csMember", csMember);
		return "exchangefreehours";
	}
	
	public String exchangefreeSubmit(){
		try{
			final CsMember csMember = WeixinHelper.getCsMember();
			if (!WeixinHelper.isCorrectOpenId()) {
				// 微信openid不合规范
				return WeixinHelper.gotoLogin();
			}
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
		
			final int amount = $.getInteger("purSum", 0)/500;
			int maxHour = SystemHelper.toInt(csMember.getCsmIntegral() / 500);
			if (amount == 0 || amount > maxHour) {
				// 积分不足，提醒去赚积分
				return $.SendHtml("102", "UTF-8");
			}
			// 进行积分兑换
			ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
			String result = csRecordService.executeTransaction(new Function() {
				@SuppressWarnings("unchecked")
				@Override
				public <T> T execute(Object... arg0) {
					ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
					String remark = CommonMessage.MSG0300.replace("{amount}", String.valueOf(amount * 500)).replace("{freehour}", String.valueOf(amount))
							+ $.date(new Date(), "yyyy-MM-dd HH:mm:ss");
					// 兑换成功 ，第1步，加相应的免费小时
					CsFreeHour csFreeHour = new CsFreeHour();
					csFreeHour.setNotNull(csMember.getCsmHost(),csMember.getCsmId(), amount * 1.00, amount * 1.00, (short) 1, SystemHelper.getValidUntil((short) 1),
							(long) -1, new Date(), new Date(), (short) 1);
					csFreeHour.setCsfhRemark(remark);
					csFreeHourService.saveCsFreeHour(csFreeHour);
					// 兑换成功 ，第2步，扣除积分
					commonMoneyService.updateIntegralForWeixin(csMember.getCsmHost(), csMember.getCsmId(), amount * -500.00, IntegralType.积分兑换, remark, null);
					return (T) "100";
				}
			});
			// 兑换成功
			return $.SendHtml(result, "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// 系统错误,请稍候再试。
			return $.SendHtml("9999", "UTF-8");
		}
	}
	
	public String refundsubmit(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try{
			CsMember member = WeixinHelper.getCsMember();
			if (member == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
			if (member.getCsmMoney() <= 0) {
				re.setState(false);
				re.setMessage("亲，您没有可用余额可退啦～");
				return $.SendAjax(re, "utf-8");
			}
			if (commonMoneyService.haveUnFinishOrder(member.getCsmId())) {
				re.setState(false);
				re.setMessage("亲，您还有未完成的订单，不能申请退款哦～");
				return $.SendAjax(re, "utf-8");
			}
			if (commonMoneyService.isRefunding(member.getCsmId())) {
				re.setState(false);
				re.setMessage("亲，您已经提交过退款申请啦，不能申请退款哦～");
				return $.SendAjax(re, "utf-8");
			}
			List<CsOrder> csOrderList = csOrderService.getCsOrderList($.add(CsOrder.F.csoPayMember, member.getCsmId()).add(CsOrder.F.csoStatus, 4).add("desc", CsOrder.C.csoRetTime), -1);
			// 没有定单
			if (csOrderList.size() == 0) {
				// 没有用车记录，3个工作日退款
				noRented(member);
				re.setState(true);
				re.setMessage("提交退款申请成功");
				return $.SendAjax(re, "utf-8");
			} else {
				// 取最后一个订单，如果最后一个订单还车时间是在 2015-02-15 17：00 之前还车的
				// ----------------------------------------- 铁定不是春节订单
				if (csOrderList.get(0).getCsoFinishTime().before(SystemHelper.getDate(2015, 1, 15, 17, 0, 0))) {
					if (csOrderList.get(0).getCsoRetTime() != null) {
						Date now = new Date();
						if (now.after(WidgetHelper.getWorkDay(7, csOrderList.get(0).getCsoRetTime()))) {
							// 7个工作日没用车了，相当于没有车记录，3个工作日退款
							noRented(member);
							re.setState(true);
							re.setMessage("提交退款申请成功");
							return $.SendAjax(re, "utf-8");
						} else {
							CsRefund newCsRefund = new CsRefund();
							newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), 
									WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(9, csOrderList.get(0).getCsoRetTime()), WidgetHelper.getWorkDay(9,
									csOrderList.get(0).getCsoRetTime()), (short) 0);
							newCsRefund.csrMobile(member.getCsmMobile());
							csRefundService.saveCsRefund(newCsRefund);
							re.setState(true);
							re.setMessage("提交退款申请成功");
							return $.SendAjax(re, "utf-8");
						}
					} else {
						// 没有车记录，3个工作日退款
						noRented(member);
						re.setState(true);
						re.setMessage("提交退款申请成功");
						return $.SendAjax(re, "utf-8");
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
								re.setState(true);
								re.setMessage("提交退款申请成功");
								return $.SendAjax(re, "utf-8");
							} else {
								CsRefund newCsRefund = new CsRefund();
								newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), 
										new Date(), WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(9, csOrderList.get(0).getCsoRetTime()), WidgetHelper.getWorkDay(9,
										csOrderList.get(0).getCsoRetTime()), (short) 0);
								newCsRefund.csrMobile(member.getCsmMobile());
								csRefundService.saveCsRefund(newCsRefund);
								re.setState(true);
								re.setMessage("提交退款申请成功");
								return $.SendAjax(re, "utf-8");
							}
						} else {
							// 没有车记录，3个工作日退款
							noRented(member);
							re.setState(true);
							re.setMessage("提交退款申请成功");
							return $.SendAjax(re, "utf-8");
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
									re.setState(true);
									re.setMessage("提交退款申请成功");
									return $.SendAjax(re, "utf-8");
								} else {
									CsRefund newCsRefund = new CsRefund();
									newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), 
											new Date(), WidgetHelper.getWorkDay(1), refundDate, refundDate, (short) 0);
									newCsRefund.csrMobile(member.getCsmMobile());
									csRefundService.saveCsRefund(newCsRefund);
									re.setState(true);
									re.setMessage("提交退款申请成功");
									return $.SendAjax(re, "utf-8");
								}
							} else {
								if (refundLastDate.after(refundDate)) {
									noRented(member);
									re.setState(true);
									re.setMessage("提交退款申请成功");
									return $.SendAjax(re, "utf-8");
								} else {
									CsRefund newCsRefund = new CsRefund();
									newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), 
											new Date(), WidgetHelper.getWorkDay(1), refundDate, refundDate, (short) 0);
									newCsRefund.csrMobile(member.getCsmMobile());
									csRefundService.saveCsRefund(newCsRefund);
									re.setState(true);
									re.setMessage("提交退款申请成功");
									return $.SendAjax(re, "utf-8");
								}
							}
						} else {
							// 比较春节订单与当前订单还车日期先后
							if (WidgetHelper.getWorkDay(4).after(new Date(springOrder.getCsoFinishTime().getTime() + SYSTEM.DAY * 35))) {
								noRented(member);
								re.setState(true);
								re.setMessage("提交退款申请成功");
								return $.SendAjax(re, "utf-8");
							} else {
								CsRefund newCsRefund = new CsRefund();
								newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), 
										new Date(), WidgetHelper.getWorkDay(1), new Date(springOrder.getCsoFinishTime().getTime() + SYSTEM.DAY * 35), new Date(springOrder
										.getCsoFinishTime().getTime()
										+ SYSTEM.DAY * 35), (short) 0);
								newCsRefund.csrMobile(member.getCsmMobile());
								csRefundService.saveCsRefund(newCsRefund);
								re.setState(true);
								re.setMessage("提交退款申请成功");
								return $.SendAjax(re, "utf-8");
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
	}
	
	public String refundcancel(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try {
			CsMember member = WeixinHelper.getCsMember();
			if (member == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}

			if (!commonMoneyService.isRefunding(member.getCsmId())) {
				re.setState(false);
				re.setMessage("亲，您没有可以取消的退款申请哦～");
				return $.SendAjax(re, "utf-8");
			}
			final CsRefund csRefund = csRefundService.getCsRefund($.add("csrMember", member.getCsmId()).add("definex", "csr_status<2"));
			if (csRefund == null){
				re.setState(false);
				re.setMessage("亲，您没有可以取消的退款申请哦～");
				return $.SendAjax(re, "utf-8");
			}
			csRefundService.updateCsRefund$NotNull(new CsRefund().csrId(csRefund.getCsrId()).csrStatus((short) 4));
			re.setState(true);
			re.setMessage("提交退款申请成功");
			return $.SendAjax(re, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
	}
	private void noRented(CsMember member) {
		CsRefund newCsRefund = new CsRefund();
		newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1), WidgetHelper.getWorkDay(4), WidgetHelper.getWorkDay(4), (short) 0);
		newCsRefund.csrMobile(member.getCsmMobile());
		csRefundService.saveCsRefund(newCsRefund);
	}
	
	public String info(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		$.SetRequest("csMember", csMember);
		return "info";
	}
	
	public String address(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		$.SetRequest("csMember", csMember);
		return "address";
	}
	
	public String saveAddress(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try{
			String txtaddress = $.getString("txtaddress", "");
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
			ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
			CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, csMember.getCsmId()));
			csMemberInfo.csmiAddress(txtaddress);
			csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);
			re.setState(true);
			re.setMessage("更新地址成功");
			return $.SendAjax(re, "utf-8");
		}catch(Exception e){
			e.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
	}
	
	public String emergencycontacts(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		$.SetRequest("csMember", csMember);
		return "emergencycontacts";
	}
	
	public String saveEmergency(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try{
			String identityType = $.getString("identityType", "");
			String csmiPerson = $.getString("csmiPerson", "");
			String csmiContact = $.getString("csmiContact", "");
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
			ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
			CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, csMember.getCsmId()));
			csMemberInfo.csmiPerson(csmiPerson);
			csMemberInfo.csmiContact(csmiContact);
			csMemberInfo.csmiRelation(identityType);
			csMemberInfoService.updateCsMemberInfo(csMemberInfo);
			re.setState(true);
			re.setMessage("更新紧急联系人成功");
			return $.SendAjax(re, "utf-8");
		}catch(Exception e){
			e.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
	}
	
	public String pwd(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		$.SetRequest("csMember", csMember);
		return "pwd";
	}
	
	public String resetPwd() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
			String csmPassword = RSAUtil.decrypt($.getString("txtPassWord", ""));
			String rePwd = RSAUtil.decrypt($.getString("txtRePassWord", ""));
			if ($.empty(csmPassword)) {
				// $.SetTips("未输入密码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if (csmPassword.length() < 6) {
				// $.SetTips("密码设置有误，请输入6到20位有效密码");
				return $.SendHtml("106", "UTF-8");
			}
			if (!$.equals(csmPassword, rePwd)) {
				// $.SetTips("两次密码输入不一致");
				return $.SendHtml("107", "UTF-8");
			}
			// ----------------------------------------------------------验证通过，重置密码
			csMemberService.updateCsMember$NotNull(new CsMember().csmPassword($.md5(csmPassword)).csmId(csMember.getCsmId()));
			// $.SetTips("重置密码成功，进入到成功页面");
			// 清空当前手机号session
			$.setSession(Constant.VERIFY_MOBILE, null);

			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
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
				bOk = UtilHelper.sendTemplateSMS(CsMember.where().csmMobile(mobile).get().getCsmHost(), "FORGOT_PWD", mobile, CommonMessage.MSG0201, SMSType.提示类短信, $.add("code", strCode));
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
	
	public String feedback(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		ICsComplainService complainService = $.GetSpringBean("csComplainService");
		List<CsComplain> list = complainService.getCsComplainList($.add(CsComplain.F.cscMember, csMember.getCsmId()), -1);
		
		$.SetRequest("complain", list);
		$.SetRequest("csMember", csMember);
		return "feedback";
	}
	
	public String grade(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		$.SetRequest("grade", csMember.getCsmGrade());
		$.SetRequest("grade_left", 4-csMember.getCsmGrade());
		return "grade";
	}
	
	public String gradelist(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		$.SetRequest("glist", csMember.getCsmGrows());
		return "gradelist";
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
					if (trade_status.equals("TRADE_SUCCESS")) {
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
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public String callBackUrl() throws UnsupportedEncodingException{
		HttpServletRequest request = $.getServletRequest();
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号	
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String result = new String(request.getParameter("result").getBytes("ISO-8859-1"),"UTF-8");

		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verifyReturn(params);
		
		if(verify_result){//验证成功
			CsOrder csOrder = CsOrder.get(Long.parseLong(out_trade_no));
			return $.Redirect($.GetRequest("basePath") + "m/home/order_detail.html?orderid=" + Long.parseLong(out_trade_no));
		}else{
			CsOrder csOrder = CsOrder.get(Long.parseLong(out_trade_no));
			return $.Redirect($.GetRequest("basePath") + "m/home/order_detail.html?orderid=" + Long.parseLong(out_trade_no));
		}
	}
	
	public String back(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try{
			String type = $.getString("type", "");
			String tucao = $.getString("tucao", "");
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
			ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
			CsComplain csComplain = new CsComplain();
			csComplain.setNotNull(csMember.getCsmHost(), "", tucao, new Short(type), csMember.getCsmId(), new Date(),  new Date(),new Short("0"), new Short("1"));
			csComplainService.saveCsComplain(csComplain);
			re.setState(true);
			re.setMessage("提交成功");
			return $.SendAjax(re, "utf-8");
		}catch(Exception e){
			e.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
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
	public void setCsAlipayRecordService(
			ICsAlipayRecordService csAlipayRecordService) {
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
	public void setCsRechargeCardService(
			ICsRechargeCardService csRechargeCardService) {
		this.csRechargeCardService = csRechargeCardService;
	}
}
