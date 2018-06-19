package com.ccclubs.action.web.my;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ccclubs.config.Constant;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class OrderAction {

	ICsOrderService csOrderService;
	ICsMemberService csMemberService;

	/**
	 * 个人中心 我的定单页面
	 * 
	 * @return
	 */
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);

		List<CsOrder> orderlist = csOrderService.getCsOrderList($.add(CsOrder.F.csoPayMember, SystemHelper.getLoginId()).add("definex",
				"(" + CsOrder.C.csoStatus + " = 0 || " + CsOrder.C.csoStatus + " = 1 )"), -1);
		$.SetRequest("orderlist", orderlist);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsOrder.F.csoPayMember, SystemHelper.getLoginId());
		params.put("definex", CsOrder.C.csoStatus + " <> 0 and " + CsOrder.C.csoStatus + " <> 1 ");

		Page page = csOrderService.getCsOrderPage($.getInteger("page", 0), Constant.LOADE_COUNT, params);
		$.SetRequest("page", page);
		return "order";
	}

	/**
	 * 加载更多
	 * 
	 * @return
	 */
	public String moreOrder() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		Page page = csOrderService.getCsOrderPage($.getInteger("page", 0), Constant.LOADE_COUNT, $.add(CsOrder.F.csoPayMember, SystemHelper.getLoginId()).add("definex",
				CsOrder.C.csoStatus + " <> 0 and " + CsOrder.C.csoStatus + " <> 1 "));
		$.SetRequest("page", page);
		return "more";
	}

	/**
	 * 续订定单，需要提供定单id
	 * 
	 * @return
	 */
	public String renew() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		long order = $.getLong("order", 0l);
		CsOrder csOrder = csOrderService.getCsOrderById(order);
		// 不存在定单
		if (csOrder == null)
			return $.SendHtml("false", "UTF-8");
		// 支付会员对不上
		if (!csOrder.getCsoPayMember().equals(SystemHelper.getLoginId()))
			return $.SendHtml("false", "UTF-8");
		$.SetRequest("csOrder", csOrder);
		//根据车辆ID 和订单的预定结束时间查询离该记录最近的一条后续订单
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsOrder.F.csoCar, csOrder.getCsoCar());
		params.put("asc", CsOrder.C.csoStartTime);
		params.put("definex", CsOrder.C.csoStartTime+" > '" +SystemHelper.formatDate(csOrder.getCsoEndTime())+"' and "+CsOrder.C.csoStatus +" in (0,1)");
		CsOrder csLastOrder = csOrderService.getCsOrder(params);
		$.SetRequest("csLastOrder", csLastOrder);
		// 默认续订1小时，输出新的续订时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(csOrder.getCsoFinishTime());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		$.SetRequest("newFinish", cal.getTime());
		return "renew";
	}
	
	/**
	 * 订单详情，需要提供定单id
	 * 
	 * @return
	 */
	public String details() {
		//TODO：错误中间页面，如果订单号不存在，跳转到错误页面，显示错误信息
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		long order = $.getLong("order", 0l);
		CsOrder csOrder = csOrderService.getCsOrderById(order);
		// 不存在定单
		if (csOrder == null)
			return $.SendHtml("false", "UTF-8");
		// 支付会员对不上
		if (!csOrder.getCsoPayMember().equals(SystemHelper.getLoginId()))
			return $.SendHtml("false", "UTF-8");
		$.SetRequest("csOrder", csOrder);
		return "details";
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

}
