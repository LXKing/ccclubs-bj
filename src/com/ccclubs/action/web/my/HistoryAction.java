package com.ccclubs.action.web.my;

import java.util.HashMap;
import java.util.Map;
import com.ccclubs.config.Constant;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRecord;
import com.ccclubs.model.CsRecordType;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsRecordService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class HistoryAction {
	ICsMemberService	csMemberService;
	ICsRecordService	csRecordService;

	// TODO：没有记录时的显示
	/**
	 * 个人中心 消费记录页面
	 * 
	 * @return
	 */
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		// 帐户类型 type = 0：充值帐户 ，1：现金券帐户
		int type = $.getInteger("type", 0);
		if (type > 1)
			type = 0;
		$.SetRequest("type", type);
		Page page = CsRecord.where().csrMoneyType((short) type).csrMember(SystemHelper.getLoginId()).page($.getInteger("page", 0), Constant.LOADE_COUNT * 2);

		$.SetRequest("page", page);
		return "history";
	}

	/**
	 * 加载更多
	 * 
	 * @return
	 */
	public String moreHistory() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		
		// 帐户类型 type = 0：充值帐户 ，1：现金券帐户
		int type = $.getInteger("type", 0);

		if (type > 1)
			type = 0;
		$.SetRequest("type", type);

		Page page = CsRecord.where().csrMoneyType((short) type).csrMember(SystemHelper.getLoginId()).page($.getInteger("page", 0), Constant.LOADE_COUNT * 2);
		$.SetRequest("page", page);
		return "more";
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

}
