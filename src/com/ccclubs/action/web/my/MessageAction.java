package com.ccclubs.action.web.my;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.Constant;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMessage;
import com.ccclubs.model.CsRecord;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMessageService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class MessageAction {
	ICsMessageService csMessageService;
	ICsMemberService csMemberService;
	/**
	 * 个人中心 消息中心页面
	 * 
	 * @return
	 */
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);

		
		if(true)
			throw new RuntimeException("消息机制系统架构已被重构 by 刘代进，请重新实现");
//		Page page = csMessageService.getCsMessagePage($.getInteger("page", 0), Constant.LOADE_COUNT *2, $.add(CsMessage.F.csmReceiver, SystemHelper.getLoginId()));
//		$.SetRequest("page", page);
//		// 未读信息
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put(CsMessage.F.csmReceiver, SystemHelper.getLoginId());
//		params.put(CsMessage.F.csmRcvStatus, 0);
//		List<CsMessage> unRead = csMessageService.getCsMessageList(params, -1);
//		$.SetRequest("unRead", unRead.size());

		return "message";
	}

	/**
	 * 加载更多
	 * 
	 * @return
	 */
	public String moreMsg() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		if(true)
			throw new RuntimeException("消息机制系统架构已被重构 by 刘代进，请重新实现");
//		Page page = csMessageService.getCsMessagePage($.getInteger("page", 0), Constant.LOADE_COUNT*2, $.add(CsMessage.F.csmReceiver, SystemHelper.getLoginId()));
//		$.SetRequest("page", page);
		
		return "more";
	}

	public ICsMessageService getCsMessageService() {
		return csMessageService;
	}

	public void setCsMessageService(ICsMessageService csMessageService) {
		this.csMessageService = csMessageService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
}
