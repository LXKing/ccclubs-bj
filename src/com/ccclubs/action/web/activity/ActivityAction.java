package com.ccclubs.action.web.activity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.action.web.activity.newyear2015.ActivityBase;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsChjSign;
import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsChjSignService;
import com.ccclubs.service.admin.ICsGiftService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.service.web.IWebDefaultService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.util.Page;

public class ActivityAction {

	IWebDefaultService	webDefaultService;
	ISrvPropertyService	srvPropertyService;
	ICsCarModelService	csCarModelService;
	// ---------------车辆id
	Long				car;

	/**
	 * 首页 index页面
	 * 
	 * @return
	 */
	public String execute() {

		return "index";
	}

	public String sign() {
		// 设置可选区域
		SrvProperty root = srvPropertyService.getSrvProperty(Lazy.add(SrvProperty.F.spFlag, Constant.AREA_ROOT_TYPE_VALUE));
		List<SrvProperty> areas = srvPropertyService.getSrvPropertyList(Lazy.add(SrvProperty.F.spParent, root.getSpId()), -1);
		Lazy.SetRequest("areas", areas);
		ICsMemberService csMemberService = $.getBean("csMemberService");
		ICsChjSignService csChjSignService = $.getBean("csChjSignService");
		boolean isSign = false;
		if (SystemHelper.isLogin()) {
			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			$.SetRequest("member", member);
			CsChjSign csChjSign = csChjSignService.getCsChjSign($.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId()));
			if (csChjSign != null) {
				isSign = true;
				$.SetRequest("csChjSign", csChjSign);
			}
		}
		$.SetRequest("isSign", isSign);
		return "sign";
	}

	public String signSave() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");

			Calendar calendar = Calendar.getInstance();
			// 2014年1月5日10点
			calendar.set(2014, 0, 5, 10, 0, 0);
			if ((new Date().getTime() - calendar.getTime().getTime()) > 0)
				// 已经截止了
				return $.SendHtml("109", SYSTEM.UTF8);

			// 开始时间，结束时间
			Date cscsTakeTime = $.getDate("cscsTakeTime", null);
			Date cscsRetTime = $.getDate("cscsRetTime", null);
			Long cscsModel = $.getLong("cscsModel", -1l);
			Long cscsOutlets = $.getLong("cscsOutlets", -1l);
			String cscsTarget = $.getString("cscsTarget", "");
			if (cscsTakeTime == null)
				// 开始时间没有
				return $.SendHtml("102", SYSTEM.UTF8);
			if (cscsRetTime == null)
				// 结束时间没有
				return $.SendHtml("103", SYSTEM.UTF8);
			if (cscsModel.equals(-1l)) {
				// 没有选择车型
				return $.SendHtml("104", SYSTEM.UTF8);
			}
			if (cscsOutlets.equals(-1l)) {
				// 没有选择网点
				return $.SendHtml("105", SYSTEM.UTF8);
			}
			if (cscsTarget.equals("")) {
				// 没有选择目的地
				return $.SendHtml("106", SYSTEM.UTF8);
			}
			ICsMemberService csMemberService = $.getBean("csMemberService");
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			if (csMember.getCsmName() == null || csMember.getCsmName() == "") {
				// 需要完善资料
				return $.SendHtml("107", SYSTEM.UTF8);
			}

			ICsChjSignService csChjSignService = $.getBean("csChjSignService");
			CsChjSign csChjSign = csChjSignService.getCsChjSign($.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId()));
			if (csChjSign == null) {
				$.SetRequest("csChjSign", csChjSign);
				csChjSign = new CsChjSign();
				csChjSign.setCscsMember(csMember.getCsmId());
				csChjSign.setCscsMobile(csMember.getCsmMobile());
				csChjSign.setCscsModel(cscsModel);
				csChjSign.setCscsName(csMember.getCsmName());
				csChjSign.setCscsOutlets(cscsOutlets);
				csChjSign.setCscsRetTime(cscsRetTime);
				csChjSign.setCscsStatus((short) 0);
				csChjSign.setCscsTakeTime(cscsTakeTime);
				csChjSign.setCscsTarget(cscsTarget);
				csChjSign.setCscsUpdateTime(new Date());
				csChjSign.setCscsAddTime(new Date());
				csChjSignService.saveCsChjSign(csChjSign);

				return $.SendHtml("100", "UTF-8");
			} else {
				// 已经提交过报名了
				return $.SendHtml("108", SYSTEM.UTF8);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 春节自驾预订页面
	 * 
	 * @return
	 */
	public String reserve() {
		// 判断是否登录，
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		// 判断是否有春节套餐：
		ICsMemberService csMemberService = $.getBean("csMemberService");
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());

		// 自定义
		String ids = "";
		ICsPackService csPackService = $.getBean("csPackService");
		
		List<CsPack> listCsPack = csPackService.getCsPackList($.add(CsPack.F.cspStatus, 1).add("definex", CsPack.C.cspFlag + " like '%" + ActivityBase.NEW_YEAR_PACK_2015_ + "%'"), -1);
		if (listCsPack.size() > 0) {
			for (CsPack csPack : listCsPack) {
				ids = ids + csPack.getCspId() + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
		} else {
			ids = "0";
		}

		ICsUserPackService csUserPackService = $.getBean("csUserPackService");
		CsUserPack existUserPack = csUserPackService.getCsUserPack($.add(CsUserPack.F.csupMember, member.getCsmId()).add("definex", CsUserPack.C.csupPack + " in (" + ids + ")"));
		// 春节套餐3：别克，春节套餐4：MG3，春节套餐5：Smart
		// -------------------------------------------------------
		if (existUserPack == null) {
			return "nopack";
		}
		// session失效后，跳转到春节预订页面
		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
		if (!SystemHelper.isBaseOrderInfo()) {
			// 设置为春节租车时间段，2014-01-30 17:00 ，2014-02-07 09:00
			baseOrderInfo.setCsoStart(DateHelper.getDate(2015, 2, 17, 17, 0, 0));
			baseOrderInfo.setCsoFinish(DateHelper.getDate(2015, 2, 25, 9, 0, 0));
			// ------------------------------------------------------------------
			// 默认网点不为空，此处已登录～
			CsOutlets csOutlets = SystemHelper.getLogin().get$csmOutlets();
			if (csOutlets != null) {
				baseOrderInfo.setCsoLatitude(csOutlets.getCsoLatitude());
				baseOrderInfo.setCsoLongitude(csOutlets.getCsoLongitude());
			} else {
				// 获取春节报名网点
				ICsChjSignService csChjSignService = $.getBean("csChjSignService");
				CsChjSign csChjSign = csChjSignService.getCsChjSign($.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId()));
				if (csChjSign != null) {
					csOutlets = csChjSign.get$cscsOutlets();
					baseOrderInfo.setCsoLatitude(csOutlets.getCsoLatitude());
					baseOrderInfo.setCsoLongitude(csOutlets.getCsoLongitude());
				}
			}
			baseOrderInfo.setCsoArea(-1l);
			baseOrderInfo.setCsoOutlets(-1l);
			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
		} else {
			baseOrderInfo = SystemHelper.getBaseOrderInf();
			if (!baseOrderInfo.getCsoIsSpring()) {
				baseOrderInfo.setCsoStart(DateHelper.getDate(2015, 2, 17, 17, 0, 0));
				baseOrderInfo.setCsoFinish(DateHelper.getDate(2015, 2, 25, 9, 0, 0));
				baseOrderInfo.setCsoIsSpring(true);
				// 设置定单基本信息 Session
				SystemHelper.setBaseOrderInfo(baseOrderInfo);
			}

			// baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
		}
		// 获取页面条件
		// 取车区域
		Long csoArea = $.getLong("csoArea", 0l);
		if (!csoArea.equals(0l)) {
			baseOrderInfo.setCsoArea(csoArea);
			ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
			CsArea csoAreaModel = csAreaService.getCsAreaById(csoArea);
			if (csoAreaModel != null) {
				baseOrderInfo.setCsoAreaName(csoAreaModel.getCsaName());
			}
		}

		// 取车网点
		Long csoOutlets = $.getLong("csoOutlets", 0l);
		if (!csoOutlets.equals(0l))
			baseOrderInfo.setCsoOutlets(csoOutlets);
		// 设置所选网点
		ICsOutletsService csOutletsService = $.getBean("csOutletsService");
		CsOutlets csOutlets = csOutletsService.getCsOutletsById(csoOutlets);
		$.SetRequest("csOutlets", csOutlets);
		// 设置车型，依据春节套餐，设置选定车型
		// Long csoModel = $.getLong("csoModel", 0l);
		// if (!csoModel.equals(0l))
		// baseOrderInfo.setCsoModel(csoModel);
		// 遍历会员优惠项
		ICsGiftService csGiftService = $.getBean("csGiftService");
		List<CsGift> gifts = csGiftService.getCsGiftList($.add("csgUntilTimeStart", new Date())
				.add(CsGift.F.csgUserPack, existUserPack.getCsupId())
				.add("csgMember", member.getCsmId()), -1);
		for (CsGift csGift : gifts) {
			if (csGift.get$csgModel() != null) {
				List<CsCarModel> mod = csGift.get$csgModel(); //.getCscmId();
				if(mod!=null && mod.size()>0){
					baseOrderInfo.setCsoModel(mod.get(0).getCscmId()); 
				}
//				throw new RuntimeException("优惠项的车型改成了字符型,程序需要修改");
			}
		}

		// 设置车型 -------------------------------------------
		Long csoModel = $.getLong("csoModel", 0l);
		if (!csoModel.equals(0l))
			baseOrderInfo.setCsoModel(csoModel);

		// 设置宣传车
		baseOrderInfo.setCsoIsAdCar($.getBoolean("csoIsAdCar", false));
		// 设置是否日期参与筛选条件
		Boolean csoIsDate = $.getBoolean("csoIsDate", false);
		baseOrderInfo.setCsoIsDate(csoIsDate);
		// 设置查看多长时间内的车辆租赁情况
		int csoAmount = $.getInteger("csoAmount", 2);
		if (csoAmount != 2 && csoAmount != 3 && csoAmount != 7) {
			csoAmount = 2;
		}
		baseOrderInfo.setCsoAmount(csoAmount);
		// 设置为春节租车
		baseOrderInfo.setCsoIsSpring(true);
		$.SetRequest("baseOrderInfo", baseOrderInfo);
		// TODO：如果没有结果，返回的是？
		Map<String, Object> params = getCarParams(baseOrderInfo);
		ICsCarService csCarService = $.getBean("csCarService");
		Page page = csCarService.getCsCarPage($.getInteger("page", 0), 8, params);
		$.SetRequest("page", page);

		// 设置可选区域
		SrvProperty root = srvPropertyService.getSrvProperty(Lazy.add(SrvProperty.F.spFlag, Constant.AREA_ROOT_TYPE_VALUE));
		List<SrvProperty> areas = srvPropertyService.getSrvPropertyList(Lazy.add(SrvProperty.F.spParent, root.getSpId()), -1);
		Lazy.SetRequest("areas", areas);
		return "reserve";
	}

	/**
	 * 春节预订结算页面
	 * 
	 * @return
	 */
	public String checkout() {
		// 判断是否登录，
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();
		// 判断是否有春节套餐：
		ICsMemberService csMemberService = $.getBean("csMemberService");
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		// 自定义
		String ids = "";
		ICsPackService csPackService = $.getBean("csPackService");
		List<CsPack> listCsPack = csPackService.getCsPackList($.add(CsPack.F.cspStatus, 1).add("definex", CsPack.C.cspFlag + " like '%" + ActivityBase.NEW_YEAR_PACK_2015_ + "%'"), -1);
		if (listCsPack.size() > 0) {
			for (CsPack csPack : listCsPack) {
				ids = ids + csPack.getCspId() + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
		} else {
			ids = "0";
		}
		ICsUserPackService csUserPackService = $.getBean("csUserPackService");
		CsUserPack existUserPack = csUserPackService.getCsUserPack($.add(CsUserPack.F.csupMember, member.getCsmId()).add("definex", CsUserPack.C.csupPack + " in (" + ids + ")"));
		// -------------------------------------------------------
		if (existUserPack == null) {
			// TODO：跳转到套餐订购页面
			return "nopack";
		}

		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
		// Session 里没有基本订单信息
		if (!SystemHelper.isBaseOrderInfo() || SystemHelper.getBaseOrderInf().getCsoCar().equals(0l)) {
			return "failure";
		}
		baseOrderInfo = SystemHelper.getBaseOrderInf();
		$.SetRequest("baseOrderInfo", baseOrderInfo);

		ICsCarService csCarService = $.getBean("csCarService");
		CsCar csoCar = csCarService.getCsCarById(baseOrderInfo.getCsoCar());
		$.SetRequest("csoCar", csoCar);
		ICommonOrderService commonOrderService = $.getBean("commonOrderService");
		$.SetRequest("isRestriction", commonOrderService.isExistRestrictionByTime(baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish()));

		return "checkout";
	}

	/**
	 * 预定时，时间产品拆分
	 * 
	 * @return
	 */
	public String checkoutDetail() {
		try {
			// 获取保险的产品
			ICsProductService csProductService = $.getBean("csProductService");
			ICsGoodsService csGoodsService = $.getBean("csGoodsService");
			ICommonOrderService commonOrderService = $.getBean("commonOrderService");
			ICommonMoneyService commonMoneyService = $.getBean("commonMoneyService");
			ICsMemberService csMemberService = $.getBean("csMemberService");
			ICsCarService csCarService = $.getBean("csCarService");

			//CsProduct insureProduct = csProductService.getCsProduct($.add(CsProduct.F.cspFlag, Constant.INSURE));
			//List<CsGoods> insureTypes = csGoodsService.getCsGoodsList($.add(CsGoods.F.csgProduct, insureProduct.getCspId()).add("asc", CsGoods.C.csgId), -1);
			List<CsProduct> insureTypes = csProductService.getCsProductList($.add("definex", CsProduct.C.cspFlag + " = '" + Constant.SECURE + "' || " + CsProduct.C.cspFlag + " = '" + Constant.INSURE + "'"), -1);
			$.SetRequest("insureTypes", insureTypes);
			// 保险类型，默认按小时购买
			Long insureType = $.getLong("insureType", insureTypes.get(0).getCspId());
			$.SetRequest("insureType", insureType);

			// 判断是否登录，
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", SYSTEM.UTF8);
			// 判断是否有春节套餐：
			CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());

			// 自定义
			String ids = "";
			ICsPackService csPackService = $.getBean("csPackService");
			List<CsPack> listCsPack = csPackService.getCsPackList($.add(CsPack.F.cspStatus, 1).add("definex", CsPack.C.cspFlag + " like '%" + ActivityBase.NEW_YEAR_PACK_2015_ + "%'"), -1);
			if (listCsPack.size() > 0) {
				for (CsPack csPack : listCsPack) {
					ids = ids + csPack.getCspId() + ",";
				}
				ids = ids.substring(0, ids.length() - 1);
			} else {
				ids = "0";
			}
			ICsUserPackService csUserPackService = $.getBean("csUserPackService");
			CsUserPack existUserPack = csUserPackService.getCsUserPack($.add(CsUserPack.F.csupMember, member.getCsmId()).add("definex", CsUserPack.C.csupPack + " in (" + ids + ")"));
			// -------------------------------------------------------
			if (existUserPack == null) {
				return $.SendHtml("102", SYSTEM.UTF8);
			}

			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			// Session 里没有基本订单信息
			if (!SystemHelper.isBaseOrderInfo() || SystemHelper.getBaseOrderInf().getCsoCar().equals(0l)) {
				return $.SendHtml("103", SYSTEM.UTF8);
			} else {
				baseOrderInfo = SystemHelper.getBaseOrderInf();
			}
			Double freehour = $.getDouble("freehour", 0d);
			OrderInfo orderInfo;
			// 获取小时数
			int allhours = (int) ((baseOrderInfo.getCsoFinish().getTime() - baseOrderInfo.getCsoStart().getTime()) / SYSTEM.HOUR);

			// --------------------------------------------------------------------------------------

			// 传入预计里程数，计算预计里程费用，计算最低帐户余额
			orderInfo = commonOrderService.splitOrderDetails(SystemHelper.getLoginId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, null, commonOrderService.getPredictKms(allhours) * 1.0, null, insureType, null, null);
			Double memberhours = commonMoneyService.getCanUseFreeHour(SystemHelper.getLoginId(), new Date());
			// 最多可用免费小时
			$.SetRequest("maxhours", Math.min(memberhours, orderInfo.getOnehours()));
			// 帐户可用免费小时
			$.SetRequest("memberhours", memberhours);
			// CsMember member =
			// csMemberService.getCsMemberById(SystemHelper.getLoginId());
			$.SetRequest("member", member);
			// 会员可用余额
			$.SetRequest("money", commonMoneyService.getUsableAmount(member.getCsmId()));

			freehour = orderInfo.getFreehour();
			// 设置免费小时，保险产品类型
			baseOrderInfo.setCsoFreeHours(freehour);
			baseOrderInfo.setCsoInsure(insureType);
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
			// 获取是否使用免费小时标识
			Boolean isfreehour = $.getBoolean("isfreehour", false);

			$.SetRequest("isfreehour", isfreehour);
			$.SetRequest("freehour", freehour);
			$.SetRequest("orderInfo", orderInfo);
			$.SetRequest("baseOrderInfo", baseOrderInfo);
			CsCar csoCar = csCarService.getCsCarById(baseOrderInfo.getCsoCar());
			$.SetRequest("csoCar", csoCar);
		} catch (Exception ex) {
			ex.printStackTrace();
			// TODO：打印错误信息，网点车辆价格没有找到，请尝试选择其他区域网点
			return $.SendHtml("false", SYSTEM.UTF8);
		}
		return "checkoutDetail";
	}

	/*
	 * 查看指定车辆春节是否租出
	 */
	public String isRent() {
		try {
			if (car != null) {
				// 检查该车辆，该时间段已经被预订
				ICommonOrderService commonOrderService = $.getBean("commonOrderService");
				// 设置为春节租车时间段，2014-01-30 17:00 ，2014-02-07 09:00
				Boolean isRent = false;
				if (commonOrderService.isExistOrderByTime(car, SystemHelper.getDate(2015, 1, 17, 17, 0, 0), SystemHelper.getDate(2015, 1, 25, 9, 0, 0), null)) {
					isRent = true;
				}
				$.SetRequest("isRent", isRent);
			}
			return "isRent";
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("", "UTF-8");
		}
	}

	/**
	 * 根据 baseOrderInfo 生成条件参数列表
	 * 
	 * @param baseOrderInfo
	 * @return
	 */
	private Map<String, Object> getCarParams(BaseOrderInfo baseOrderInfo) {
		// 设置所选网点
		ICsOutletsService csOutletsService = $.getBean("csOutletsService");
		Map<String, Object> params = new HashMap<String, Object>();

		// -------------------------------------春节预订必须包含的时间段
		//
		if (baseOrderInfo == null)
			return params;
		// 限定网点
		if (!baseOrderInfo.getCsoOutlets().equals(-1l)) {
			// 指定网点
			params.put(CsCar.F.cscOutlets, baseOrderInfo.getCsoOutlets());
			// 限定时间
			if (baseOrderInfo.getCsoIsDate()) {
				params.put("definex", CsCar.C.cscStatus + " =1 and " + " csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
						+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
			} else {
				params.put("definex", CsCar.C.cscStatus + " =1 ");
			}
		} else {
			// 指定区域，不指定网点
			if (!baseOrderInfo.getCsoArea().equals(-1l)) {
				// 自定义
				String ids = "";
				List<CsOutlets> listOutlets = csOutletsService.getCsOutletsList($.add(CsOutlets.F.csoArea, baseOrderInfo.getCsoArea()), -1);
				if (listOutlets.size() > 0) {
					for (CsOutlets car : listOutlets) {
						ids = ids + car.getCsoId() + ",";
					}
					ids = ids.substring(0, ids.length() - 1);
					if (baseOrderInfo.getCsoIsDate()) {
						params.put("definex", CsCar.C.cscStatus + " =1 and " + CsCar.C.cscOutlets + " in ( " + ids + " )" + " and csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish())
								+ "' and cso_finish_time >  '" + SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
					} else {
						params.put("definex", CsCar.C.cscStatus + " =1 and " + CsCar.C.cscOutlets + " in ( " + ids + " )");
					}
				} else {
					if (baseOrderInfo.getCsoIsDate()) {
						params.put("definex", CsCar.C.cscStatus + " =1 and " + CsCar.C.cscOutlets + " in ( 0 )  " + " and  csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish())
								+ "' and cso_finish_time >  '" + SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
					} else {
						params.put("definex", CsCar.C.cscStatus + " =1 and " + CsCar.C.cscOutlets + " in ( 0 )  ");
					}
				}
			} else {
				// 限定时间
				if (baseOrderInfo.getCsoIsDate()) {
					params.put("definex", CsCar.C.cscStatus + " =1 and " + " csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
							+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
				} else {
					params.put("definex", CsCar.C.cscStatus + " =1 ");
				}
			}
		}
		// 限定车型
		if (!baseOrderInfo.getCsoModel().equals(-1l)) {
			params.put(CsCar.F.cscModel, baseOrderInfo.getCsoModel());
		}
		// 限定广告车
		if (baseOrderInfo.getCsoIsAdCar()) {
			params.put(CsCar.F.cscIsAd, 1);
		}
		// 限定排序
		if (baseOrderInfo.getCsoLatitude().equals(0d) || baseOrderInfo.getCsoLongitude().equals(0d)) {
//			CsOutlets csOutlets = csOutletsService.getCsOutletsById(1065l);
			SrvHost srvHost = SystemHelper.getSrvHost();
			String[] point = srvHost.getShLocation().split(",");
			baseOrderInfo.setCsoLatitude(Double.valueOf(point[0]));
			baseOrderInfo.setCsoLongitude(Double.valueOf(point[1]));
		}
		params.put("asc", "(pow(" + CsCar.C.cscLongitude + " -  " + baseOrderInfo.getCsoLongitude() + ",2) + pow(" + CsCar.C.cscLatitude + " - " + baseOrderInfo.getCsoLatitude() + ",2))");

		return params;
	}

	public IWebDefaultService getWebDefaultService() {
		return webDefaultService;
	}

	public void setWebDefaultService(IWebDefaultService webDefaultService) {
		this.webDefaultService = webDefaultService;
	}

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}

	public ICsCarModelService getCsCarModelService() {
		return csCarModelService;
	}

	public void setCsCarModelService(ICsCarModelService csCarModelService) {
		this.csCarModelService = csCarModelService;
	}

	public Long getCar() {
		return car;
	}

	public void setCar(Long car) {
		this.car = car;
	}

}
