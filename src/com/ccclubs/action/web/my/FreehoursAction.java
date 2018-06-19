package com.ccclubs.action.web.my;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ccclubs.config.Constant;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.service.admin.ICsFreeHourService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsUseRecordService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class FreehoursAction {
	ICsMemberService csMemberService;
	ICsFreeHourService csFreeHourService;
	ICsUseRecordService csUseRecordService;
	ICommonMoneyService commonMoneyService;

	/**
	 * 个人中心 免费小时页面
	 * 
	 * @return
	 */
	public String execute() {
		try {
			if (!SystemHelper.isLogin())
				return SystemHelper.gotoLogin();

			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			$.SetRequest("member", member);

			$.SetRequest("sum", commonMoneyService.getCanUseFreeHour(member.getCsmId(), new Date()));
			// ------------------------------7天 即将过期的免费小时总和
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 7);
			Map<String, Object> paramsOverSum = new HashMap<String, Object>();
			paramsOverSum.put(CsFreeHour.F.csfhMember, SystemHelper.getLoginId());
			paramsOverSum.put("definex", CsFreeHour.C.csfhStatus + " <> 2 and " + CsFreeHour.C.csfhEnd + " < '" + SystemHelper.formatDate(calendar.getTime()) + "'");
			// TODO：针对 csfhRemain 字段求和
			List<CsFreeHour> recordsOverSum = csFreeHourService.getCsFreeHourList(paramsOverSum, -1);
			double overSum = 0;
			for (CsFreeHour hour : recordsOverSum) {
				overSum += hour.getCsfhRemain();
			}
			$.SetRequest("overSum", overSum);
			$.SetRequest("overDate", calendar.getTime());
			// 免费小时类型 type = 1：收入 ，2：已过期，3：支出
			int freehoursType = $.getInteger("type", 1);
			$.SetRequest("freehourType", freehoursType);

			if (freehoursType == 1 || freehoursType == 2) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(CsFreeHour.F.csfhMember, SystemHelper.getLoginId());
				if (freehoursType == 2)
					params.put(CsFreeHour.F.csfhStatus, freehoursType);

				Page page = csFreeHourService.getCsFreeHourPage($.getInteger("page", 0), Constant.LOADE_COUNT * 2, params);
				$.SetRequest("page", page);
			}

			if (freehoursType == 3) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(CsUseRecord.F.csurMember, SystemHelper.getLoginId());
				params.put("definex", " csur_free_hour is not null and csur_free_hour<>''");
				Page page = csUseRecordService.getCsUseRecordPage($.getInteger("page", 0), Constant.LOADE_COUNT * 2, params);
				$.SetRequest("page", page);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "freehours";
	}

	/**
	 * 加载更多
	 * 
	 * @return
	 */
	public String moreFreehours() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		// 免费小时类型 type = 1：收入 ，2：已过期，3：支出
		int freehoursType = $.getInteger("type", 1);
		$.SetRequest("freehourType", freehoursType);

		if (freehoursType == 1 || freehoursType == 2) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsFreeHour.F.csfhMember, SystemHelper.getLoginId());
			if (freehoursType == 2)
				params.put(CsFreeHour.F.csfhStatus, freehoursType);

			Page page = csFreeHourService.getCsFreeHourPage($.getInteger("page", 0), Constant.LOADE_COUNT * 2, params);
			$.SetRequest("page", page);
		}

		if (freehoursType == 3) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsUseRecord.F.csurMember, SystemHelper.getLoginId());
			Page page = csUseRecordService.getCsUseRecordPage($.getInteger("page", 0), Constant.LOADE_COUNT * 2, params);
			$.SetRequest("page", page);
		}
		return "more";
	}

	public String exchange() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);

		return "exchange";
	}

	// TODO：积分兑换免费小时

	public ICsFreeHourService getCsRecordService() {
		return csFreeHourService;
	}

	public void setCsFreeHourService(ICsFreeHourService csFreeHourService) {
		this.csFreeHourService = csFreeHourService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsUseRecordService getCsUseRecordService() {
		return csUseRecordService;
	}

	public void setCsUseRecordService(ICsUseRecordService csUseRecordService) {
		this.csUseRecordService = csUseRecordService;
	}

	public ICsFreeHourService getCsFreeHourService() {
		return csFreeHourService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

}
