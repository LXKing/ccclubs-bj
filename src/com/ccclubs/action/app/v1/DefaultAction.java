package com.ccclubs.action.app.v1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.SystemHelper.Prices;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsRemote;
import com.ccclubs.model.CsRule;
import com.ccclubs.model.CsState;
import com.ccclubs.model.CsUpdate;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsFeeTypeSetService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.admin.ICsRemoteService;
import com.ccclubs.service.admin.ICsRuleService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.admin.ICsUpdateService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.admin.impl.CsFeeTypeSetService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.lazy3q.web.helper.LZ;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Page;

public class DefaultAction {

	ISrvPropertyService		srvPropertyService;
	ICsMemberService		csMemberService;
	ICsOutletsService		csOutletsService;
	ICsOrderService			csOrderService;
	ICsCarService			csCarService;
	ICsGoodsService			csGoodsService;
	ICsPriceService			csPriceService;
	ICsProductService		csProductService;
	ICsUserTypeService		csUserTypeService;
	ICommonMoneyService		commonMoneyService;
	ICommonDisposeService	commonDisposeService;
	ICommonOrderService		commonOrderService;
	ICsUpdateService		csUpdateService;

	/**
	 * 初始化连接
	 * 
	 * @return
	 */
	public String init() {

		try {
			JsonFormat jf = new JsonFormat();
			jf.data.add("addrs", new ArrayList()).add("keyword", SYSTEM.APP_KEYWORD);

			CsUpdate csUpdate = csUpdateService.getCsUpdate($.add("csuType", 0));
			if (csUpdate == null)
				new ErrorException("APP_NOFOUND_ERROR", "未上传最新的APP程序", "请在后台上传最新的APP程序");
			else
				jf.data.add("version", csUpdate.getCsuVersion()).add("update", csUpdate.getCsuFile());

			jf.data.put("outlets", getOutlets($.getLong("uid")));
			jf.data.put("scenes", new ArrayList());

			// 把当前城市的位置放进去
			CsMember member = csMemberService.getCsMemberById($.getLong("uid"));
			SrvHost srvHost = $.or(member == null ? null : (SrvHost.get(member.getCsmHost())), SrvHost.Get($.add(SrvHost.F.shFlag, "杭州")));
			jf.data.put("location", srvHost.getShLocation());

			return LZ.SendAjax(jf, SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	/**
	 * 获取网点列表
	 * 
	 * @param uid
	 * @return
	 */
	private Map getOutlets(Long uid) {
		CsMember member = csMemberService.getCsMemberById(uid);
		Long host = (member == null ? null : member.getCsmHost());
		if (host == null)
			host = SrvHost.Get($.add(SrvHost.F.shFlag, "杭州")).getShId();

		List<CsArea> areas = CsArea.list($.add(CsArea.F.csaHost, host), -1);
		Map outlets = new HashMap();
		for (CsArea csArea : areas) {
			List<CsOutlets> list = csOutletsService.getCsOutletsList(LZ.add("csoArea", csArea.getCsaId()).add("csoCanReg", true).add("csoCanOrder", true).add("csoStatus", 1), -1);
			List formatList = new ArrayList();
			for (CsOutlets csOutlets : list) {
				formatList.add(LZ.add("id", csOutlets.getCsoId()).add("name", csOutlets.getCsoName()).add("address", csOutlets.getCsoAddress() + csOutlets.getCsoSpace() + ($.empty(csOutlets.getCsoDetails()) ? "" : ("(" + csOutlets.getCsoDetails() + ")"))).add("xpos", csOutlets.getCsoLongitude())
						.add("ypos", csOutlets.getCsoLatitude()).add("image", csOutlets.getCsoImage()).add("time", csOutlets.getCsoOnlineTime() == null ? 0 : csOutlets.getCsoOnlineTime().getTime()).add("rented", 0)// TODO
						// 未实现已租车辆数
						.add("unrent", csCarService.getCsCarCount($.add("cscOutlets", csOutlets.getCsoId()).add("cscStatus", 1)))// TODO
						// 未实现未租车辆数
						);
			}
			outlets.put(csArea.getCsaName(), formatList);
		}
		return outlets;
	}

	public String login() {
		try {
			String username = LZ.getString("username");
			String password = LZ.getString("password");
			if ($.empty(username))
				return LZ.SendAjax(JsonFormat.message(false, "用户名不能为空"), SYSTEM.UTF8);
			if ($.empty(password))
				return LZ.SendAjax(JsonFormat.message(false, "密码不能为空"), SYSTEM.UTF8);

			CsMember user = null;

			if (SystemHelper.isEmail(username))// 邮箱方式登录
				user = csMemberService.getCsMember($.Map("csmEmail", username));
			else if (SystemHelper.isMobile(username))// 手机方式登录
				user = csMemberService.getCsMember($.Map("csmMobile", username));
			else
				// 帐号方式登录
				user = csMemberService.getCsMember($.Map("csmUsername", username));

			if (user == null)
				return LZ.SendAjax(JsonFormat.message(false, "对不起，帐号不存在！"), SYSTEM.UTF8);
			if (user.getCsmStatus() != 1)
				return LZ.SendAjax(JsonFormat.message(false, "对不起，帐号已经失效！"), SYSTEM.UTF8);
			if (!$.md5(password).equals(user.getCsmPassword().toUpperCase()))
				return LZ.SendAjax(JsonFormat.message(false, "对不起，密码输入错误！"), SYSTEM.UTF8);

			HttpServletRequest request = ServletActionContext.getRequest();
			String csmLastIp = user.getCsmLastIp();
			java.util.Date csmLastTime = user.getCsmLastTime();
			csMemberService.updateCsMember$NotNull(new CsMember().csmLastIp(request.getRemoteAddr()).csmLastTime(new java.util.Date()).csmId(user.getCsmId()));
			user.setCsmLastIp(csmLastIp);
			user.setCsmLastTime(csmLastTime);
			$.setSession(SYSTEM.ccclubs_web_login, user);
			$.setSession(SYSTEM.ccclubs_web_login_id, user.getCsmId());

			JsonFormat jf = JsonFormat.message(true, "登录成功");

			jf.data.put("userid", user.getCsmId());
			jf.data.put("outlets", getOutlets(user.getCsmId()));

			// 把当前城市场的位置放进去
			CsMember member = csMemberService.getCsMemberById(user.getCsmId());
			SrvHost srvHost = $.or(SrvHost.get(member.getCsmHost()), SrvHost.Get($.add(SrvHost.F.shFlag, "杭州")));
			jf.data.put("location", srvHost.getShLocation());

			return LZ.SendAjax(jf, SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	public String logout() {
		try {
			$.setSession("ccclubs_web_login", null);
			$.setSession("ccclubs_web_login_id", null);
			return LZ.SendAjax(JsonFormat.message(true, "退出登录成功！"), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	/**
	 * 获取我的订单
	 * 
	 * @return
	 */
	public String orders() {
		try {
			Long uid = $.getLong("uid");
			if (uid == null)
				return LZ.SendAjax(JsonFormat.global(4, "参数传入错误"), SYSTEM.UTF8);

			JsonFormat jf = new JsonFormat();

			Page page = csOrderService.getCsOrderPage($.getInteger("index", 0), 8, $.add(CsOrder.F.csoPayMember, uid));
			List list = new ArrayList();
			for (CsOrder csOrder : (List<CsOrder>) (page.getResult())) {
				CsCar csCar = csOrder.get$csoCar();
				CsCarModel csModel = csOrder.get$csoModel();
				list.add($.add("id", csOrder.getCsoId()).add("time", csOrder.getCsoAddTime().getTime()).add("start", csOrder.getCsoStartTime().getTime()).add("finish", csOrder.getCsoFinishTime().getTime()).add("branchId", csOrder.getCsoOutlets()).add("modelName",
						csModel == null ? "" : csModel.getCscmName()).add("carId", csOrder.getCsoCar()).add("carNumber", csCar == null ? "  " : csCar.getCscNumber()).add("orderPrice", csOrder.getCsoPayNeed()).add("orderStatus", csOrder.getCsoStatus()).add("AuthorizeCode", csOrder.getCsoCode()));
			}

			jf.data.add("list", list).add("page", $.add("index", page.getIndex()).add("total", page.getTotal()));

			return LZ.SendAjax(jf, SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	/**
	 * 获取我的帐户信息
	 * 
	 * @return
	 */
	public String account() {
		try {
			Long uid = $.getLong("uid");
			if (uid == null)
				return LZ.SendAjax(JsonFormat.global(4, "参数传入错误"), SYSTEM.UTF8);

			CsMember member = csMemberService.getCsMemberById(uid);
			CsEvCard csmEvcard = member.get$csmEvcard();

			JsonFormat jf = new JsonFormat();

			jf.data.add("id", member.getCsmId()).add("username", member.getCsmUsername()).add("evcard", csmEvcard == null ? "" : csmEvcard.getCsecNumber()).add("margin", member.getCsmMoney()).add("cash", member.getCsmCoupon()).add(
					"freeHour", commonMoneyService.getCanUseFreeHour(uid, new java.util.Date()).intValue()).add("status", member.getCsmStatus() + 1).add("share", member.getCsmIntegral().intValue());

			return LZ.SendAjax(jf, SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	/**
	 * 根据网点取车辆列表
	 * 
	 * @return
	 */
	public String cars() {
		try {
			Long id = $.getLong("id");
			if (id == null)
				return LZ.SendAjax(JsonFormat.global(4, "参数传入错误"), SYSTEM.UTF8);

			JsonFormat jf = new JsonFormat();
			List list = new ArrayList();
			// CsUserType csUserType =
			// csUserTypeService.getCsUserType($.add("csutFlag",
			// "USER_TYPE_PERSONAL"));
			// CsGoods dayGoods = commonOrderService.getGoodsByFlag(SYSTEM.RENT,
			// RuleName.工作日一天, csUserType.getCsutId());
			// if (dayGoods == null)
			// throw new Exception("app获取车辆列表时出错 - 无法找到租金工作日一天的商品");
			// CsGoods hourGoods =
			// commonOrderService.getGoodsByFlag(SYSTEM.RENT, RuleName.工作日白天小时,
			// csUserType.getCsutId());
			// if (hourGoods == null)
			// throw new Exception("app获取车辆列表时出错 - 无法找到租金工作日白天小时的商品");
			// if (dayGoods == null)
			// throw new Exception("app获取车辆列表时出错 -
			// 无法找到普通会员类型[USER_TYPE_PERSONAL]");
			List<CsProduct> insureProducts = csProductService.getCsProductList($.add("definex", "csp_flag='INSURE' or csp_flag='SECURE'"), -1);
			if (insureProducts == null)
				throw new Exception("app获取车辆列表时出错 - 保险类型的产品[INSURE]");
			ICsStateService csStateService = $.getBean("csStateService");
			List<CsCar> cars = csCarService.getCsCarList($.add("cscOutlets", id).add("cscStatus", 1), -1);
			for (CsCar csCar : cars) {
				// CsPrice dayPrice =
				// csPriceService.getCsPrice($.add("cspGoods",
				// dayGoods.getCsgId()).add("cspUserType",
				// csUserType.getCsutId()).add("cspOutets",
				// csCar.getCscOutlets()).add("cspModel", csCar.getCscModel()));
				// CsPrice hourPrice =
				// csPriceService.getCsPrice($.add("cspGoods",
				// hourGoods.getCsgId()).add("cspUserType",
				// csUserType.getCsutId()).add("cspOutets",
				// csCar.getCscOutlets()).add("cspModel", csCar.getCscModel()));

				List<Map> times = new ArrayList();
				List<Restriction> restrictions = commonOrderService.getRestrictions(csCar.getCscId(), 3);
				for (Restriction restriction : restrictions)
					times.add($.add("start", restriction.getStart().getTime()).add("end", restriction.getFinish().getTime()).add("status", 2));
				List<CsOrder> orders = csOrderService.getCsOrderList($.add("csoCar", csCar.getCscId()).add("definex", "(cso_status!=3 and cso_status!=7)"), -1);
				for (CsOrder csOrder : orders)
					times.add($.add("start", csOrder.getCsoStartTime().getTime()).add("end", (csOrder.getCsoRetTime() != null ? csOrder.getCsoRetTime() : csOrder.getCsoFinishTime()).getTime()).add("status", 1));
				List<Map> avoids = new ArrayList();
				for (CsProduct csProduct : insureProducts)
					avoids.add($.add("value", csProduct.getCspId()).add("text", csProduct.getCspName()));
				avoids.add($.add("value", 0).add("text", "不购买免责产品"));
				// 获得默认计费策略，然后取价格
				ICsFeeTypeSetService csFeeTypeSetService = $.getBean("csFeeTypeSetService");
				CsFeeTypeSet csFeeTypeSet = csFeeTypeSetService.getCsFeeTypeSet($.add(CsFeeTypeSet.F.csftsHost, csCar.getCscHost()).add(CsFeeTypeSet.F.csftsModel, csCar.getCscModel()));
				if (csFeeTypeSet != null) {
					Prices prices = SystemHelper.getPrices(csCar.getCscHost(), csCar.getCscModel(), csCar.getCscOutlets());
					LzMap map = $.add("id", csCar.getCscId())
								.add("model", csCar.get$cscModel().getCscmName())
								.add("image", csCar.get$cscModel().getCscmImage())
								.add("dayPrice", prices.get("租金.工作日一天|租金.每天"))
								.add("hourPrice",prices.get("租金.工作日白天小时|租金.白天小时|租金.每小时"))
								.add("times", times).add("avoids", avoids);
						
					if (csCar.get$cscModel().getCscmType() == 0) {
						map.add("number", csCar.getCscCarNo() + (csCar.getCscIsAd() ? "(宣传车)" : ""));
					} else {
						// 如果是电动车，获得当前续航里程
						CsState csState = SystemHelper.getCsState($.add(CsState.F.cssNumber, csCar.getCscNumber()));
						if (csState != null && csState.getCssEndurance() != null) {
							map.add("number", csCar.getCscCarNo() + (csCar.getCscIsAd() ? "(宣传车)" : "") + " 续航" + csState.getCssEndurance() + "公里");
						} else {
							map.add("number", csCar.getCscCarNo() + (csCar.getCscIsAd() ? "(宣传车)" : ""));
						}
					}
					list.add(map);
				}
			}

			jf.data.add("list", list);

			System.out.println($.json(list));

			return LZ.SendAjax(jf, SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	public Double $(Double value) {
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 订单确认
	 * 
	 * @return
	 */
	public String confirm() {
		try {
			Long carid = $.getLong("id");
			Long uid = $.getLong("uid");
			String keyword = $.getString("keyword");
			Long start = $.getLong("start");
			Long finish = $.getLong("end");
			Long avoid = $.getLong("avoid");

			if (!$.equals(keyword, $.md5(carid + "" + uid + "" + SYSTEM.APP_KEYWORD))) {
				// new ErrorException("APP_ERROR", "安全验证未通过", keyword + "<>" +
				// $.md5(carid + "" + uid + "" + SYSTEM.APP_KEYWORD));
				// return LZ.SendAjax(JsonFormat.message(false, "安全验证未通过"),
				// SYSTEM.UTF8);
			}

			Double canUseMoney = commonMoneyService.getUsableAmount(uid);

			Boolean bExist = commonOrderService.isExistOrderByTime(carid, new Date(start), new Date(finish), null);
			if (bExist == true)
				return LZ.SendAjax(JsonFormat.message(false, "您所选的预订时间已经被其它订单占用，请重新选择时间"), SYSTEM.UTF8);
			// 拆分订单
			OrderInfo orderinfo = commonOrderService.splitOrderDetails(uid, carid, new Date(start), new Date(finish), null, null, null, null, null, avoid, null, null);
			// 如果会员保证金足够支付订单所需金额
			if (canUseMoney < (orderinfo.getMargin() + orderinfo.getFeeee() + orderinfo.getPrice()))
				return LZ.SendAjax(JsonFormat.message(false, "您的余额不足，不能下单"), SYSTEM.UTF8);

			return LZ.SendAjax(JsonFormat.message(true, "你的订单当前最低费用为" + orderinfo.getPrice() + "元，确定要提交订单吗？"), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}

	}

	/**
	 * 订单提交
	 * 
	 * @return
	 */
	public String submit() {
		try {
			Long carid = $.getLong("id");
			Long uid = $.getLong("uid");
			String keyword = $.getString("keyword");
			Date start = $.getLong("start") == null ? null : new Date($.getLong("start"));
			Date finish = $.getLong("end") == null ? null : new Date($.getLong("end"));
			Long avoid = $.getLong("avoid");

			if (!$.equals(keyword, $.md5(carid + "" + uid + "" + SYSTEM.APP_KEYWORD))) {
				// new ErrorException("APP_ERROR", "安全验证未通过", keyword + "<>" +
				// $.md5(carid + "" + uid + "" + SYSTEM.APP_KEYWORD));
				// return LZ.SendAjax(JsonFormat.message(false, "安全验证未通过"),
				// SYSTEM.UTF8);
			}

			if (start == null)
				return LZ.SendAjax(JsonFormat.message(false, "开始时间不能为空"), SYSTEM.UTF8);
			if (finish == null)
				return LZ.SendAjax(JsonFormat.message(false, "结束时间不能为空"), SYSTEM.UTF8);
			if ((start.getTime() - new Date().getTime()) < (-2 * SYSTEM.HOUR))
				return LZ.SendAjax(JsonFormat.message(false, "开始时间不能早于当前时间两小时"), SYSTEM.UTF8);
			if (start.after(finish))
				return LZ.SendAjax(JsonFormat.message(false, "开始时间不能结束时间晚"), SYSTEM.UTF8);
			if ((finish.getTime() - start.getTime()) < (SYSTEM.HOUR))
				return LZ.SendAjax(JsonFormat.message(false, "租车时间最低1个小时起租"), SYSTEM.UTF8);
			// 电动车型的开始预定时间判断
			CsCar car = csCarService.getCsCarById(carid);
			if (car.get$cscModel().getCscmType().intValue() == 1) {
				CsMember member = csMemberService.getCsMemberById(uid);
				if (member.getCsmHost() == 2l) {
					// 当前车型只提供一小时以内的车辆预定
					if (start.getTime() - Calendar.getInstance().getTime().getTime() > SYSTEM.HOUR) {
						return LZ.SendAjax(JsonFormat.message(false, "亲，电动汽车最多支持提前1小时预订"), SYSTEM.UTF8);
					}
				} else {
					// 当前车型只提供一小时以内的车辆预定
					if (start.getTime() - Calendar.getInstance().getTime().getTime() > 2 * SYSTEM.HOUR) {
						return LZ.SendAjax(JsonFormat.message(false, "亲，电动汽车最多支持提前2小时预订"), SYSTEM.UTF8);
					}
				}
			}
			if ((start.getTime() - new Date().getTime()) > (7 * SYSTEM.DAY))
				return LZ.SendAjax(JsonFormat.message(false, "系统只提供7天以内的预订"), SYSTEM.UTF8);

			commonDisposeService.executeSaveOrder(uid, uid, carid, start, finish, null, avoid, null, null, null, 0l,From.APP,null);

			return LZ.SendAjax(JsonFormat.message(true, "您的订单提交成功，祝你驾驶愉快"), SYSTEM.UTF8);
		} catch (com.ccclubs.exception.MessageException oe) {
			return LZ.SendAjax(JsonFormat.message(false, oe.getMessage()), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	/**
	 * 订单续订
	 * 
	 * @return
	 */
	public String reorder() {
		try {
			Long orderid = $.getLong("id");
			Long uid = $.getLong("uid");
			String keyword = $.getString("keyword");
			Long end = $.getLong("end");

			if (!$.equals(keyword, $.md5(orderid + "" + uid + "" + SYSTEM.APP_KEYWORD))) {
				new ErrorException("APP_ERROR", "安全验证未通过", keyword + "<>" + $.md5(orderid + "" + uid + "" + SYSTEM.APP_KEYWORD));
				// return LZ.SendAjax(JsonFormat.message(false, "安全验证未通过"),
				// SYSTEM.UTF8);
			}

			CsOrder oldOrder = CsOrder.get(orderid);
			commonDisposeService.executeReOrder(orderid, new Date(end), oldOrder.getCsoFreehour(),From.APP,null);

			return LZ.SendAjax(JsonFormat.message(true, "您的订单续订成功，续订后的结束时间是" + $.date(new Date(end), "yyyy-MM-dd HH:mm:ss")), SYSTEM.UTF8);
		} catch (com.ccclubs.exception.MessageException oe) {
			return LZ.SendAjax(JsonFormat.message(false, oe.getMessage()), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	/**
	 * 远程控制
	 * 
	 * @return
	 */
	public String remote() {
		try {
			Short type = $.getShort("type", (short) 0);// 0:远程开门 1:远程关门 2:允许启动
			// 3:断油电 4:初始化 5:同步时钟
			Long oid = $.getLong("oid");
			Long uid = $.getLong("uid");

			CsMember member = csMemberService.getCsMemberById(uid);
			if (member == null)
				return LZ.SendAjax(JsonFormat.message(false, "对不起，您没有登录，不能执行该操作"), SYSTEM.UTF8);

			CsOrder csOrder = csOrderService.getCsOrderById(oid);
			if (csOrder.getCsoUseMember().longValue() != member.getCsmId().longValue())
				return LZ.SendAjax(JsonFormat.message(false, "对不起，您没有权限执行该操作"), SYSTEM.UTF8);

			CsCar csCar = csCarService.getCsCarById(csOrder.getCsoCar());
			if (csCar == null)
				return LZ.SendAjax(JsonFormat.message(false, "执行远程操作失败(104)"), SYSTEM.UTF8);

			if (csOrder.getCsoStatus().shortValue() > 1) {
				return LZ.SendAjax(JsonFormat.message(false, "只有已预定或已执行的订单才能执行该操作"), SYSTEM.UTF8);
			}

			if ((csOrder.getCsoStartTime().getTime() - new Date().getTime()) > (1000 * 60 * 10)) {
				return LZ.SendAjax(JsonFormat.message(false, "订单只能在订单开始时间前10分钟到订单结束时间之内执行远程操作"), SYSTEM.UTF8);
			}

			ICsRemoteService csRemoteService = $.getBean("csRemoteService");

			// 取近的一次远程操作
			CsRemote lastRemote = csRemoteService.getCsRemote($.add(CsRemote.F.csrType, type).add(CsRemote.F.csrCar, csCar.getCscId()).add("desc", CsRemote.C.csrAddTime));
			if (lastRemote != null && (new Date().getTime() - lastRemote.getCsrAddTime().getTime()) < (SYSTEM.MINUTE / 6)) {
				return LZ.SendAjax(JsonFormat.message(false, "您好，请不要频繁执行远程操作，两次远程操作之间时间间隔不能小于一分钟"), SYSTEM.UTF8);
			}

			CsRemote csRemote = new CsRemote();
			csRemote.setCsrNumber(csCar.getCscNumber());
			csRemote.setCsrCar(csCar.getCscId());
			csRemote.setCsrEditor(0l);
			csRemote.setCsrAddTime(new Date());
			csRemote.setCsrState((short) 2);
			csRemote.setCsrStatus((short) 0);
			csRemote.setCsrType(type);
			csRemote.setCsrHost(csCar.getCscHost());
			csRemote.setCsrRemark(member.getCsmName() + "执行APP远程操作");

			csRemote = csRemoteService.saveCsRemote(csRemote);

			com.ccclubs.service.common.impl.RemoteService.dealRemoteCommend(csRemote);

			Thread.sleep(1000l);

			return LZ.SendAjax(JsonFormat.message(true, "执行远程开门操作成功！"), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(JsonFormat.global(4, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICommonDisposeService getCommonDisposeService() {
		return commonDisposeService;
	}

	public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
		this.commonDisposeService = commonDisposeService;
	}

	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}

	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICsCarService getCsCarService() {
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}

	public ICsGoodsService getCsGoodsService() {
		return csGoodsService;
	}

	public void setCsGoodsService(ICsGoodsService csGoodsService) {
		this.csGoodsService = csGoodsService;
	}

	public ICsPriceService getCsPriceService() {
		return csPriceService;
	}

	public void setCsPriceService(ICsPriceService csPriceService) {
		this.csPriceService = csPriceService;
	}

	public ICsUserTypeService getCsUserTypeService() {
		return csUserTypeService;
	}

	public void setCsUserTypeService(ICsUserTypeService csUserTypeService) {
		this.csUserTypeService = csUserTypeService;
	}

	public ICsProductService getCsProductService() {
		return csProductService;
	}

	public void setCsProductService(ICsProductService csProductService) {
		this.csProductService = csProductService;
	}

	public ICsUpdateService getCsUpdateService() {
		return csUpdateService;
	}

	public void setCsUpdateService(ICsUpdateService csUpdateService) {
		this.csUpdateService = csUpdateService;
	}

}
