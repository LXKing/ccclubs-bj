package com.ccclubs.action.web.my;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.SimpleSerialExecutor;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.model.CsIntegralRecord;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRecord;
import com.ccclubs.service.admin.ICsFreeHourService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class PointAction {
	ICsMemberService csMemberService;
	ICsRecordService csRecordService;
	ICommonMoneyService commonMoneyService;
	ICsFreeHourService csFreeHourService;

	/**
	 * 个人中心 积分记录页面
	 * 
	 * @return
	 */
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		// 积分小时类型 type = 1：全部 ，2：收入，3：支出
		int pointType = $.getInteger("type", 1);
		$.SetRequest("pointType", pointType);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsIntegralRecord.F.csrMember, SystemHelper.getLoginId());

		if (pointType == 2) {
			params.put("definex", CsIntegralRecord.C.csrAmount + " > 0");
		}

		if (pointType == 3) {
			params.put("definex", CsIntegralRecord.C.csrAmount + " < 0");
		}

		Page page = CsIntegralRecord.getCsIntegralRecordPage($.getInteger("page", 0), Constant.LOADE_COUNT * 2, params);
		$.SetRequest("page", page);

		return "point";
	}

	/**
	 * 加载更多
	 * 
	 * @return
	 */
	public String morePoint() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsIntegralRecord.F.csrMember, SystemHelper.getLoginId());

		int pointType = $.getInteger("type", 1);
		if (pointType == 2) {
			params.put("definex", CsIntegralRecord.C.csrAmount + " > 0");
		}

		if (pointType == 3) {
			params.put("definex", CsIntegralRecord.C.csrAmount + " < 0");
		}

		Page page = CsIntegralRecord.getCsIntegralRecordPage($.getInteger("page", 0), Constant.LOADE_COUNT * 2, params);

		$.SetRequest("page", page);
		return "more";
	}

	/**
	 * 积分兑换
	 * 
	 * @return
	 */
	public String exchange() {
		if (!SystemHelper.isLogin())
			return $.SendHtml("101", "UTF-8");

		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);

		return "exchange";
	}

	/**
	 * 好友邀请
	 * @return
	 */
	public String invite() {
		if (!SystemHelper.isLogin())
			return $.SendHtml("101", "UTF-8");

		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);

		return "invite";
	}

	/**
	 * 积分兑换免费小时
	 * 
	 * @return
	 */
	public String ajaxExchange() {
		SimpleSerialExecutor executor = new SimpleSerialExecutor("网站积分兑换-"+SystemHelper.getLoginId());
		try {
			//使用时一定要写在try块的第一句，finally块中记得释放
			if(executor.entry()){
				if (!SystemHelper.isLogin())
					// 未登录
					return $.SendHtml("-1", "UTF-8");
				long user = $.getLong("user", 0l);
				final int amount = $.getInteger("exAmount", 0);
				final CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
	
				if (user != SystemHelper.getLoginId()) {
					// 提交会员与当前登录会员不相符
					return $.SendHtml("101", "UTF-8");
				}
				int maxHour = SystemHelper.toInt(member.getCsmIntegral() / 500);
				if (amount == 0 || amount > maxHour) {
					// 积分不足，提醒去赚积分
					return $.SendHtml("102", "UTF-8");
				}
				// 进行积分兑换
				String result = csRecordService.executeTransaction(new Function() {
					@Override
					public <T> T execute(Object... arg0) {
						String remark = CommonMessage.MSG0300.replace("{amount}", String.valueOf(amount * 500)).replace("{freehour}", String.valueOf(amount));
						// 兑换成功 ，第1步，加相应的免费小时
						CsFreeHour csFreeHour = new CsFreeHour();
						csFreeHour.setNotNull(SystemHelper.getSrvHost().getShId(),member.getCsmId(), amount * 1.00, amount * 1.00, (short) 1, SystemHelper.getValidUntil((short) 1), (long) -1, new Date(), new Date(), (short) 1);
						csFreeHour.setCsfhRemark(remark);
						csFreeHourService.saveCsFreeHour(csFreeHour);
						// 兑换成功 ，第2步，扣除积分
						commonMoneyService.updateIntegral(member.getCsmId(), amount * -500.00, IntegralType.积分兑换, remark, null);
						return (T) "100";
					}
				});
				// 兑换成功，更新当前 session 信息
				CsMember newMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
				SystemHelper.setLogin(newMember);
				return $.SendHtml(result, "UTF-8");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// 系统错误,请稍候再试。
			return $.SendHtml("9999", "UTF-8");
		}finally{
			executor.release();
		}
		return null;
	}

	public ICsRecordService getCsRecordService() {
		return csRecordService;
	}

	public void setCsRecordService(ICsRecordService csRecordService) {
		this.csRecordService = csRecordService;
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

	public ICsFreeHourService getCsFreeHourService() {
		return csFreeHourService;
	}

	public void setCsFreeHourService(ICsFreeHourService csFreeHourService) {
		this.csFreeHourService = csFreeHourService;
	}

}
