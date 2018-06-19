package com.ccclubs.action.web.my;

import java.util.Calendar;
import java.util.Date;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRechargeCard;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsRechargeCardService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

public class ExchangeAction {
	ICsMemberService		csMemberService;
	ICsRechargeCardService	csRechargeCardService;
	ICommonMoneyService		commonMoneyService;

	/**
	 * 个人中心 兑换现金券页面
	 * 
	 * @return
	 */
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		if ($.getSession("exchange_count") == null) {
			// 初始加载设置为0
			$.setSession("exchange_count", 0);
		}
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		
		Double hz_sum = commonMoneyService.getCoin(Long.valueOf(1), SystemHelper.getLoginId(), Calendar.getInstance().getTime());
		Double bj_sum = commonMoneyService.getCoin(Long.valueOf(2), SystemHelper.getLoginId(), Calendar.getInstance().getTime());
		
		// 可用红包
		$.setRequest("hz_sum", hz_sum);
		$.setRequest("bj_sum", bj_sum);

//		CsRechargeCard caCsRechargeCard = new CsRechargeCard();
//		caCsRechargeCard.setCsrcAddTime(new Date());
//		caCsRechargeCard.setCsrcDenomination(100d);
//		caCsRechargeCard.setCsrcNumber("XIAOGANG" + $.rand(999));
//		caCsRechargeCard.setCsrcPassword("XIAOGANG");
//		caCsRechargeCard.setCsrcType((short) 0);
//		caCsRechargeCard.setCsrcStatus((short) 2);
//		csRechargeCardService.saveCsRechargeCard(caCsRechargeCard);
		return "exchange";
	}

	/**
	 * 兑换现金券
	 * 
	 * @return
	 */
	public String submit() {
		try {
			if (!SystemHelper.isLogin())
				// 未登录
				return $.SendHtml("-1", "UTF-8");
			if ($.getSession("exchange_count") == null) {
				// 需要从网站访问
				return $.SendHtml("101", "UTF-8");
			}
			// 输入错误次数
			int errorCount = Integer.parseInt($.getSession("exchange_count").toString());
			if (errorCount >= 3) {
				// 错误次数太多，过会儿再来吧。
				return $.SendHtml("102", "UTF-8");
			}

			long user = $.getLong("user", 0l);
			final String csrcNumber = $.getString("csrcNumber", "");
			final String csrcPassword = $.getString("csrcPassword", "");

			if (user != SystemHelper.getLoginId()) {
				// 提交会员与当前登录会员不相符
				return $.SendHtml("103", "UTF-8");
			}
			CsRechargeCard csRechargeCard = csRechargeCardService.getCsRechargeCard($.add(CsRechargeCard.F.csrcNumber, csrcNumber.toUpperCase()).add(CsRechargeCard.F.csrcPassword, csrcPassword.toUpperCase()));

			/*if (csRechargeCard == null) {
				// 卡号或密码错误
				errorCount = errorCount + 1;
				$.setSession("exchange_count", errorCount);
				return $.SendHtml("104", "UTF-8");
			}
			
			// 如果卡的所属人与当前登录会员账号不一致，充值失败 
			if (csRechargeCard.getCsrcMember()!=null) {
				if(csRechargeCard.getCsrcMember().longValue()!=SystemHelper.getLoginId().longValue()){
					return $.SendHtml("该卡的所属人不是您，不能完成充值！", "UTF-8");
				}
			}
			
			// 出库的才能兑换
			if (csRechargeCard.getCsrcStatus() != 2) {
				return $.SendHtml("105", "UTF-8");
			}*/
			
			// 开始兑换
			int result = commonMoneyService.executeRechangeCard(csrcNumber, csrcPassword, user, "恭喜您，兑换充值卡成功");
			if(result == 104){
				// 卡号或密码错误
				errorCount = errorCount + 1;
				$.setSession("exchange_count", errorCount);
				return $.SendHtml("104", "UTF-8");
			}else if(result==106){
				// 如果卡的所属人与当前登录会员账号不一致，充值失败 
				return $.SendHtml("该卡的所属人不是您，不能完成充值！", "UTF-8");
			}else if(result==203){
				return $.SendHtml("105", "UTF-8");
			}else if (result == 200) {
				CsMember member = csMemberService.getCsMemberById(user);
				$.setSession(SYSTEM.ccclubs_web_login, member);
				$.setSession(SYSTEM.ccclubs_web_login_id, member.getCsmId());
				
				Calendar endCalendar = Calendar.getInstance();
				endCalendar.add(Calendar.MONTH, csRechargeCard.getCsrcValidity());
				
				DateUtil du = new DateUtil();
				//String endTimeStr = du.dateToString(csRechargeCard.getCsrcAddTime(), "yyyy-MM-dd");
				//endTimeStr += "至";
				//endTimeStr +=
				String endTimeStr = du.dateToString(endCalendar.getTime(), "yyyy-MM-dd");
				
				if(csRechargeCard.getCsrcValidity() == null || csRechargeCard.getCsrcValidity() == 0 && csRechargeCard.getCsrcValidityUtil()!=null){
					endTimeStr = du.dateToString(csRechargeCard.getCsrcValidityUtil(), "yyyy-MM-dd");
				}
				
				//return $.SendHtml("恭喜您已成功兑换充值卡" + csRechargeCard.getCsrcDenomination() + "元，您当前帐户充值卡余额为" + member.getCsmCoupon() + "元！", "UTF-8");
				String hostName = SrvHost.get(csRechargeCard.getCsrcHost()).getShName();
				return $.SendHtml("恭喜您！已成功兑换【"+hostName+"】红包" + csRechargeCard.getCsrcDenomination() + "元，有效期至："+endTimeStr, "UTF-8");
			} else {
				return $.SendHtml(result + "", "UTF-8");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// 系统错误,请稍候再试。
			return $.SendHtml("9999", "UTF-8");
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsRechargeCardService getCsRechargeCardService() {
		return csRechargeCardService;
	}

	public void setCsRechargeCardService(ICsRechargeCardService csRechargeCardService) {
		this.csRechargeCardService = csRechargeCardService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}
}
