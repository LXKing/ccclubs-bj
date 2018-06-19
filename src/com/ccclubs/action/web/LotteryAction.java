package com.ccclubs.action.web;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsLuckyResult;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsLuckyService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.ILuckyService;
import com.ccclubs.model.CsLucky;
import com.ccclubs.service.common.ILuckyService.LuckyType;
import com.lazy3q.web.helper.$;

public class LotteryAction {
	ICsMemberService csMemberService;
	ILuckyService luckyService;

	/**
	 * web抽奖页面
	 * 
	 * @return
	 */
	public String execute() {
		if (SystemHelper.isLogin()) {
			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			$.SetRequest("member", member);
		}
		//获取抽奖活动
		CsLucky csLucky = luckyService.getCsLucky(LuckyType.积分抽奖大转盘);
		$.SetRequest("lottery", csLucky);
		return "lottery";
	}

	/**
	 * 执行抽奖动作
	 * 
	 * @return 返回抽奖结果
	 */
	public String go() {
		if (!SystemHelper.isLogin()) {
			return $.SendHtml("201", "UTF-8");
		}
		Long memberId = $.getLong("member", 0l);
		CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		if (!memberId.equals(csMember.getCsmId()))
			// 本人购买
			return $.SendHtml("252", "UTF-8");
		try {
			CsLuckyResult csLuckyResult = luckyService.executeLucky(LuckyType.积分抽奖大转盘, csMember.getCsmId(), (short) 0);
			// 中奖结果
			return $.SendHtml("253", "UTF-8");
		} catch (com.ccclubs.exception.MessageException oe) {
			// $.SetTips(oe.getMessage());
			return $.SendHtml(oe.getCode() + "", SYSTEM.UTF8);
		} catch (Exception e) {
			return $.SendHtml("9999", SYSTEM.UTF8);
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ILuckyService getLuckyService() {
		return luckyService;
	}

	public void setLuckyService(ILuckyService luckyService) {
		this.luckyService = luckyService;
	}
}
