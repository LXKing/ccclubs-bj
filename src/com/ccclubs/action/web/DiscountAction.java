package com.ccclubs.action.web;

import java.util.List;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsPack;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsPackService;
import com.lazy3q.web.helper.$;

public class DiscountAction {
	ICsMemberService csMemberService;
	ICsPackService csPackService;

	/**
	 * 超值优惠
	 * 
	 * @return
	 */
	public String execute() {
		if (SystemHelper.isLogin()) {
			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			$.SetRequest("member", member);
		}
		List<CsPack> packList = csPackService.getCsPackList($.add(CsPack.F.cspStatus, 1).add("definex", CsPack.C.cspId+" in (1)").add("asc", CsPack.C.cspPrice), -1);
		$.SetRequest("packList", packList);
		return "discount";
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsPackService getCsPackService() {
		return csPackService;
	}

	public void setCsPackService(ICsPackService csPackService) {
		this.csPackService = csPackService;
	}
}
