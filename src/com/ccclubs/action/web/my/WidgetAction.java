package com.ccclubs.action.web.my;

import java.util.List;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.service.admin.ICsGiftService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class WidgetAction {
	ICsUserPackService csUserPackService;
	ICsGiftService csGiftService;
	Long memberId;

	/**
	 * 用户套餐购买情况
	 * 
	 * @return
	 */
	public String pack() {
		// 未传入memberId 或者 memberId 为 null
		CsUserPack csUserPack = null;
		if (memberId != null) {
			// TODO：会员拥有多个套餐，需要从 PackageService 里获取 Page<CsUserPack>
			csUserPack = csUserPackService.getCsUserPack($.add(CsUserPack.F.csupMember, memberId).add(CsUserPack.F.csupStatus, 1).add("definex", CsUserPack.C.csupPack + " in (1,2)"));
			List<CsGift> giftLsit;
			if (csUserPack != null) {
				giftLsit = csGiftService.getCsGiftList($.add(CsGift.F.csgMember, memberId).add(CsGift.F.csgUserPack, csUserPack.getCsupId()).add(CsGift.F.csgStatus, 1), -1);
				$.SetRequest("giftLsit", giftLsit);
			}
		}
		$.SetRequest("csUserPack", csUserPack);
		return "pack";
	}

	/**
	 * 用户春节套餐购买情况
	 * 
	 * @return
	 */
	public String springPack() {
		// 未传入memberId 或者 memberId 为 null
		CsUserPack csUserPack = null;
		if (memberId != null) {
			// TODO：会员拥有多个套餐，需要从 PackageService 里获取 Page<CsUserPack>
			// 自定义
			String ids = "";
			ICsPackService csPackService = $.getBean("csPackService");
			List<CsPack> listCsPack = csPackService.getCsPackList($.add(CsPack.F.cspStatus, 1).add("definex", CsPack.C.cspFlag + " like '%" + SYSTEM.NEW_YEAR_PACK_2013_ + "%'"), -1);
			if (listCsPack.size() > 0) {
				for (CsPack csPack : listCsPack) {
					ids = ids + csPack.getCspId() + ",";
				}
				ids = ids.substring(0, ids.length() - 1);
			} else {
				ids = "0";
			}
			ICsUserPackService csUserPackService = $.getBean("csUserPackService");
			csUserPack = csUserPackService.getCsUserPack($.add(CsUserPack.F.csupMember, memberId).add(CsUserPack.F.csupStatus, 1).add("definex", CsUserPack.C.csupPack + " in (" + ids + ")"));

			List<CsGift> giftLsit;
			if (csUserPack != null) {
				giftLsit = csGiftService.getCsGiftList($.add(CsGift.F.csgMember, memberId).add(CsGift.F.csgUserPack, csUserPack.getCsupId()).add(CsGift.F.csgStatus, 1), -1);
				$.SetRequest("giftLsit", giftLsit);
			}
		}
		$.SetRequest("csUserPack", csUserPack);
		return "springPack";
	}

	public String points() {
		return "points";
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public ICsUserPackService getCsUserPackService() {
		return csUserPackService;
	}

	public void setCsUserPackService(ICsUserPackService csUserPackService) {
		this.csUserPackService = csUserPackService;
	}

	public ICsGiftService getCsGiftService() {
		return csGiftService;
	}

	public void setCsGiftService(ICsGiftService csGiftService) {
		this.csGiftService = csGiftService;
	}
}
