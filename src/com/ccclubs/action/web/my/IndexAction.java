package com.ccclubs.action.web.my;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsIntegralRecord;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.lazy3q.web.helper.$;

public class IndexAction {
	ICsMemberService csMemberService;
	ICommonMoneyService commonMoneyService;
	ICsRecordService csRecordService;
	ICsOrderService csOrderService;
	/**
	 * 个人中心 index页面
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		// 会员可用余额
		$.SetRequest("money", commonMoneyService.getUsableAmount(member.getCsmId()));
		
		
		List<SrvHost> hosts=SrvHost.getSrvHostList($.add(SrvHost.F.shState, 1), -1);
		List list = new ArrayList();
		Double allcoin=(double) 0;
		for(SrvHost srvHost:hosts){
			Map map = new HashMap();
			Double coin=commonMoneyService.getCoin(srvHost.getShId(), SystemHelper.getLoginId(), Calendar.getInstance().getTime());
			map.put("cityName", srvHost.getShName());
			map.put("cityId", srvHost.getShId());
			map.put("coin", coin);			
			list.add(map);
			allcoin=allcoin+coin;
		}
		$.setRequest("allcoins", allcoin);
		$.setRequest("coins", list);
		
		$.SetRequest("sum", commonMoneyService.getCanUseFreeHour(member.getCsmId(), new Date()));
		List<CsOrder> orderlist = csOrderService.getCsOrderList($.add(CsOrder.F.csoPayMember, SystemHelper.getLoginId()).add("definex",
				"(" + CsOrder.C.csoStatus + " = 0 || " + CsOrder.C.csoStatus + " = 1 )"), -1);
		$.SetRequest("orderlist", orderlist);
		// 资料是否全部完善
		Boolean infoSuccess = true;
		infoSuccess = isBasicInfo(member.getCsmId());
		if (!infoSuccess) {
			$.SetRequest("infoSuccess", infoSuccess);
			return "index";
		}
		infoSuccess = isContactInfo(member.getCsmId());
		if (!infoSuccess) {
			$.SetRequest("infoSuccess", infoSuccess);
			return "index";
		}
		infoSuccess = isHeadInfo(member.getCsmId());
		if (!infoSuccess) {
			$.SetRequest("infoSuccess", infoSuccess);
			return "index";
		}
		$.SetRequest("infoSuccess", infoSuccess);
		return "index"; 
	}

	/**
	 * 是否发放 完善紧急联系人信息 积分奖励，true：发放过，false：没发放过
	 * 
	 * @return
	 */
	private Boolean isContactInfo(Long memberid) {
		return CsIntegralRecord.Get(
			$.add(CsIntegralRecord.F.csrMember,memberid)
			.add(CsIntegralRecord.F.csrType, IntegralType.完善紧急联系人信息.name())
		)!=null;
	}

	/**
	 * 是否发放 完善过个人基本信息 积分奖励，true：发放过，false：没发放过
	 * 
	 * @return
	 */
	private Boolean isBasicInfo(Long memberid) {
		return CsIntegralRecord.Get(
				$.add(CsIntegralRecord.F.csrMember,memberid)
				.add(CsIntegralRecord.F.csrType, IntegralType.完善个人基本信息.name())
			)!=null;
	}

	/**
	 * 是否发放过上传头像信息
	 * 
	 * @param memberid
	 * @return
	 */
	private Boolean isHeadInfo(Long memberid) {
		return CsIntegralRecord.Get(
				$.add(CsIntegralRecord.F.csrMember,memberid)
				.add(CsIntegralRecord.F.csrType, IntegralType.上传头像.name())
			)!=null;
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

	public ICsRecordService getCsRecordService() {
		return csRecordService;
	}

	public void setCsRecordService(ICsRecordService csRecordService) {
		this.csRecordService = csRecordService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}
}
