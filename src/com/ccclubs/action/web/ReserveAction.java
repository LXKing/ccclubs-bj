package com.ccclubs.action.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.util.Page;

public class ReserveAction {
	ICsOutletsService	csOutletsService;
	ICsCarService		csCarService;
	ICsOrderService		csOrderService;
	ISrvPropertyService	srvPropertyService;
	ICommonOrderService	commonOrderService;
	// ---------------车辆id
	Long				car;
	// ---------------最近 amount 天的车辆使用情况
	int					amount;

	/**
	 * 自驾预订页面
	 * 
	 * @return
	 */
	public String execute() {

		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
		if (!SystemHelper.isBaseOrderInfo()) {
			baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
			if (SystemHelper.isLogin()) {
				// 默认网点不为空
				CsOutlets csOutlets = SystemHelper.getLogin().get$csmOutlets();
				if (csOutlets != null && SystemHelper.getLogin().getCsmHost().equals(SystemHelper.getSrvHost().getShId())) {
					baseOrderInfo.setCsoArea(csOutlets.getCsoArea());
					baseOrderInfo.setCsoAreaName(csOutlets.get$csoArea().getCsaName());
					baseOrderInfo.setCsoOutlets(csOutlets.getCsoId());
					baseOrderInfo.setCsoOutletsName(csOutlets.getCsoName());
					baseOrderInfo.setCsoLatitude(csOutlets.getCsoLatitude());
					baseOrderInfo.setCsoLongitude(csOutlets.getCsoLongitude());
				}
				// else {
				// // 默认取车网点，这里默认选择 西湖区，银马公寓
				// baseOrderInfo.setCsoArea(5l);
				// baseOrderInfo.setCsoOutlets(1065l);
				// }
			}
			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
		} else {
			baseOrderInfo = SystemHelper.getBaseOrderInf();
			if (baseOrderInfo.getCsoIsSpring()) {
				baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
				baseOrderInfo.setCsoIsSpring(false);
			}
			// baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
		}

		Long csoHost = $.getLong("csoHost", Long.parseLong($.config("web.host")));
		if (csoHost.equals(baseOrderInfo.getCsoHost())) {
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
			CsOutlets csOutlets = csOutletsService.getCsOutletsById(csoOutlets);
			$.SetRequest("csOutlets", csOutlets);
			// 设置车型
			Long csoModel = $.getLong("csoModel", 0l);
			if (!csoModel.equals(0l))
				baseOrderInfo.setCsoModel(csoModel);
		}

		// 设置用车方式
		int csoType = $.getInteger("csoType", -1);
		// 如果选择了异地还车，但是该地区不支持，则修改为原取原还
		if (csoType != -1 && baseOrderInfo.getCsoHost() == Integer.parseInt($.config("web.host"))) {
			csoType = -1;
		}
		baseOrderInfo.setCsoType(csoType);
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
		$.SetRequest("baseOrderInfo", baseOrderInfo);
		// TODO：如果没有结果，返回的是？
		Map<String, Object> params = getCarParams(baseOrderInfo);
		Page page = csCarService.getCsCarPage($.getInteger("page", 0), 8, params);
		$.SetRequest("page", page);

		// 设置可选区域
		// SrvProperty root =
		// srvPropertyService.getSrvProperty(Lazy.add(SrvProperty.F.spFlag,
		// Constant.AREA_ROOT_TYPE_VALUE));
		// List<SrvProperty> areas =
		// srvPropertyService.getSrvPropertyList(Lazy.add(SrvProperty.F.spParent,
		// root.getSpId()), -1);
		// Lazy.SetRequest("areas", areas);
		ICsAreaService csAreaService = Lazy.GetSpringBean("csAreaService");
		List<CsArea> areas = csAreaService.getCsAreaList($.add(CsArea.F.csaHost, SystemHelper.getSrvHost().getShId()).add("desc", CsArea.C.csaLevel), -1);
		Lazy.SetRequest("areas", areas);

		ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		List<CsCarModel> carModels = SystemHelper.getHostModels(SystemHelper.getSrvHost().getShId());//csCarModelService.getCsCarModelList($.add("definex", CsCarModel.C.cscmHosts + " like '%#" + SystemHelper.getSrvHost().getShId() + "#%'").add(CsCarModel.F.cscmStatus, 1).add("desc", CsCarModel.C.cscmType), -1);
		$.SetRequest("carModels", carModels);

		// 加载车辆时间轴数据
		loadCarTimebar(page.getResult(), csoAmount);

		return "reserve";
	}

	/**
	 * 一次性加载车辆时间轴信息
	 */
	void loadCarTimebar(List<CsCar> carList, int amount) {
		try {
			if (CollectionUtils.isEmpty(carList))
				return;

			// 默认最近2天的租车情况
			if (amount == 0)
				amount = 2;

			Map<String /* carId */, HashMap<String, Object>/* car timebar data */> timeBarDataMap = new HashMap<String, HashMap<String /* key */, Object>>();
			String carIdsStr = "";
			for (int j = 0; j < carList.size(); j++) {
				carIdsStr += (j == 0 ? "" : ",") + carList.get(j).getCscId();

				int restriction = -1;
				HashMap<String, Object> carTimebarData = new HashMap<String, Object>();
				List<Restriction> restrictions = new ArrayList<Restriction>();
				List<Date> dateList = new ArrayList<Date>();

				for (int i = 0; i < amount; i++) {
					Calendar tempCalendar = Calendar.getInstance();
					tempCalendar.set(Calendar.HOUR_OF_DAY, 0);
					tempCalendar.set(Calendar.SECOND, 0);
					tempCalendar.set(Calendar.MINUTE, 0);
					tempCalendar.add(Calendar.DATE, i);
					Date tempDate = tempCalendar.getTime();

					// 当天0点到23点59分
					tempCalendar.add(Calendar.HOUR_OF_DAY, 23);
					tempCalendar.add(Calendar.MINUTE, 59);
					Date tempDateEnd = tempCalendar.getTime();
					dateList.add(tempDate);

					// 对应的限行时间表
					List<Restriction> restrictionsTemp = commonOrderService.getRestrictions(carList.get(j).getCscId(), tempDate, tempDateEnd);
					if (restrictionsTemp.size() > 0) {
						restriction = i;
						restrictions.addAll(restrictionsTemp);
					}
				}

				carTimebarData.put("restriction", restriction);
				carTimebarData.put("restrictions", restrictions);
				carTimebarData.put("dateList", dateList);

				carTimebarData.put("limitInToday", 0);
				// 判断该车辆是否“今日限行”
				for (Restriction r : restrictions) {
					if (r.getStart().before(new Date())) {
						carTimebarData.put("limitInToday", 1);
					}
				}

				timeBarDataMap.put(carList.get(j).getCscId().toString(), carTimebarData);
			}

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			Date start = calendar.getTime();

			calendar.add(Calendar.DATE, amount);
			Date end = calendar.getTime();

//			List<CsOrder> orders = csOrderService.getCsOrderList($.add("definex", CsOrder.C.csoCar + " in (" + carIdsStr + ") and " + CsOrder.C.csoStatus + " not in (3,7) and cso_start_time < '" + SystemHelper.formatDate(end, "yyyy-MM-dd") + "' and if(ISNULL(cso_ret_time), cso_finish_time > '"
//					+ SystemHelper.formatDate(start, "yyyy-MM-dd") + "' , cso_ret_time >'" + SystemHelper.formatDate(start, "yyyy-MM-dd") + "')"), -1);
//			if (orders != null)
//				for (CsOrder order : orders) {
//					HashMap<String, Object> carTimebarData = timeBarDataMap.get(order.getCsoCar().toString());
//					List<CsOrder> orderList = (List) carTimebarData.get("orderlist");
//					if (orderList == null)
//						orderList = new ArrayList<CsOrder>();
//					orderList.add(order);
//					carTimebarData.put("orderlist", orderList);
//				}

			$.setRequest("timebarmap", timeBarDataMap);
			$.setRequest("amount", amount);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
		}
	}

	/**
	 * 车辆时间轴信息
	 * 
	 * @return
	 */
	public String timebar() {
		try {
			if (car != null) {
				$.setRequest("csCar", CsCar.get(car));
				
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MINUTE, 0);
				Date start = calendar.getTime();
				// 默认最近2天的租车情况
				if (amount == 0)
					amount = 2;
				calendar.add(Calendar.DATE, amount);
				Date end = calendar.getTime();
//				List<CsOrder> orders = csOrderService.getCsOrderList($.add(CsOrder.F.csoCar, car).add(
//						"definex",
//						CsOrder.C.csoStatus + " not in (3,7) and cso_start_time < '" + SystemHelper.formatDate(end, "yyyy-MM-dd") + "' and if(ISNULL(cso_ret_time), cso_finish_time > '" + SystemHelper.formatDate(start, "yyyy-MM-dd") + "' , cso_ret_time >'"
//								+ SystemHelper.formatDate(start, "yyyy-MM-dd") + "')"), -1);
				// $.SetRequest("image", TimeLineHelper.getTimeLineImage(date,
				// count, orders, commonOrderService.getRestrictions(id, 10)));
//				$.SetRequest("orders", orders);
				List<Date> dateList = new ArrayList<Date>();
				int restriction = -1;
				List<Restriction> restrictions = new ArrayList();
				for (int i = 0; i < amount; i++) {
					Calendar tempCalendar = Calendar.getInstance();
					tempCalendar.set(Calendar.HOUR_OF_DAY, 0);
					tempCalendar.set(Calendar.SECOND, 0);
					tempCalendar.set(Calendar.MINUTE, 0);
					tempCalendar.add(Calendar.DATE, i);
					Date tempDate = tempCalendar.getTime();
					// 当天0点到23点59分
					tempCalendar.add(Calendar.HOUR_OF_DAY, 23);
					tempCalendar.add(Calendar.MINUTE, 59);
					Date tempDateEnd = tempCalendar.getTime();
					dateList.add(tempDate);
					// 对应的限行时间表
					// if (commonOrderService.isExistRestrictionByTime(car,
					// tempDate, tempDateEnd)) {
					List<Restriction> restrictionsTemp = commonOrderService.getRestrictions(car, tempDate, tempDateEnd);
					if (restrictionsTemp.size() > 0) {
						restriction = i;
						restrictions.addAll(restrictionsTemp);
					}
					// }
				}
				$.SetRequest("dateList", dateList);
				$.SetRequest("amount", amount);
				$.SetRequest("restriction", restriction);
				$.SetRequest("restrictions", restrictions);
			}
			return "timebar";
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("{\"error\": \"car must be not null !\"}", "UTF-8");
		}
	}

	/**
	 * 获取指定网点的地图上的信息
	 * 
	 * @return
	 */
	public String viewmap() {
		Long csoOutlets = $.getLong("csoOutlets", 0l);
		// 设置所选网点
		CsOutlets csOutlets = csOutletsService.getCsOutletsById(csoOutlets);
		$.SetRequest("csOutlets", csOutlets);
		return "viewmap";
	}

	/**
	 * 首页订单基本数据收集，或预订页面选择时间作为条件
	 * 
	 * @return
	 */
	public String checkBasicOrder() {
		try {
			// 开始时间，结束时间
			Date csoStart = $.getDate("csoStart", null);
			Date csoFinish = $.getDate("csoFinish", null);
			// 区域，网点，车型
			Long csoArea = $.getLong("csoArea", -1l);
			Long csoModel = $.getLong("csoModel", -1l);
			Long csoOutlets = $.getLong("csoOutlets", -1l);
			// 节假日处理
			Boolean isSpring = $.getBoolean("isSpring", false);
			if (csoStart == null)
				// 开始时间没有
				return $.SendHtml("101", SYSTEM.UTF8);
			if (csoFinish == null)
				// 结束时间没有
				return $.SendHtml("102", SYSTEM.UTF8);
			if ((csoStart.getTime() - new Date().getTime()) < (-2 * SYSTEM.HOUR))
				// 开始时间不能早于当前时间两小时
				return $.SendHtml("103", SYSTEM.UTF8);
			if (csoStart.after(csoFinish))
				// 开始时间不能大于结束时间
				return $.SendHtml("104", SYSTEM.UTF8);
			if ((csoFinish.getTime() - csoStart.getTime()) < (SYSTEM.HOUR))
				// 租车时间最低1个小时起租
				return $.SendHtml("105", SYSTEM.UTF8);
			if (!SystemHelper.isCorrectOrderDate(csoStart) || !SystemHelper.isCorrectOrderDate(csoFinish))
				// 开始时间、结束时间，不符合定单时间要求，应该是20分钟的整数倍
				return $.SendHtml("106", SYSTEM.UTF8);

			// 节假日租车时间特殊化未处理!!
			if (!isSpring) {
				if ((csoStart.getTime() - new Date().getTime()) > (Constant.ORDER_DAYS_EARLY * SYSTEM.DAY))
					// 系统只提供7天以内的预订
					return $.SendHtml("107", SYSTEM.UTF8);
			}

			if ((csoFinish.getTime() - csoStart.getTime()) > (Constant.ORDER_DAYS_DUR * SYSTEM.DAY))
				// 系统只提供30天长度的预订
				return $.SendHtml("108", SYSTEM.UTF8);

			// 设置定单基本信息
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			baseOrderInfo.setCsoArea(csoArea);
			ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
			CsArea csoAreaModel = csAreaService.getCsAreaById(csoArea);
			if (csoAreaModel != null) {
				baseOrderInfo.setCsoAreaName(csoAreaModel.getCsaName());
			}
			baseOrderInfo.setCsoFinish(csoFinish);
			baseOrderInfo.setCsoModel(csoModel);
			baseOrderInfo.setCsoOutlets(csoOutlets);
			if (!csoOutlets.equals(-1l)) {
				baseOrderInfo.setCsoOutletsName(csOutletsService.getCsOutletsById(csoOutlets).getCsoName());
			}
			baseOrderInfo.setCsoStart(csoStart);
			baseOrderInfo.setCsoIsSpring(isSpring);

			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
			// 设置定单基本信息 Session 成功，跳转到 预订页面
			return $.SendHtml("100", SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("9999", SYSTEM.UTF8);
		}
	}

	/**
	 * 预定页面，基本数据收集
	 * 
	 * @return
	 */
	public String checkOrder() {
		try {
			// 开始时间，结束时间
			Date csoStart = $.getDate("csoStart", null);
			Date csoFinish = $.getDate("csoFinish", null);
			// 区域，网点，车型
			Long csoCar = $.getLong("csoCar", -1l);
			// 还车网点
			Long csoOutletsRet = $.getLong("csoOutletsRet", -1l);
			if (csoStart == null)
				// 开始时间没有
				return $.SendHtml("101", SYSTEM.UTF8);
			if (csoFinish == null)
				// 结束时间没有
				return $.SendHtml("102", SYSTEM.UTF8);
			if ((csoStart.getTime() - new Date().getTime()) < (-2 * SYSTEM.HOUR))
				// 开始时间不能早于当前时间两小时
				return $.SendHtml("103", SYSTEM.UTF8);
			if (csoStart.after(csoFinish))
				// 开始时间不能大于结束时间
				return $.SendHtml("104", SYSTEM.UTF8);
			if ((csoFinish.getTime() - csoStart.getTime()) < (SYSTEM.HOUR))
				// 租车时间最低1个小时起租
				return $.SendHtml("105", SYSTEM.UTF8);
			if (!SystemHelper.isCorrectOrderDate(csoStart) || !SystemHelper.isCorrectOrderDate(csoFinish))
				// 开始时间、结束时间，不符合定单时间要求，应该是20分钟的整数倍
				return $.SendHtml("106", SYSTEM.UTF8);

			if ((csoFinish.getTime() - csoStart.getTime()) > (Constant.ORDER_DAYS_DUR * SYSTEM.DAY))
				// 系统只提供30天长度的预订
				return $.SendHtml("108", SYSTEM.UTF8);
			CsCar car = csCarService.getCsCarById(csoCar);
			if (car == null)
				// 需要选择一辆车
				return $.SendHtml("109", SYSTEM.UTF8);
			if (car.getCscStatus() == (short) 0)
				// 需要选择一辆车
				return $.SendHtml("110", SYSTEM.UTF8);
			// 检查该车辆，该时间段已经被预订
			if (commonOrderService.isExistOrderByTime(csoCar, csoStart, csoFinish, null))
				return $.SendHtml("111", SYSTEM.UTF8);

			CsOutlets csOutletsRet = csOutletsService.getCsOutletsById(csoOutletsRet);
			if (csOutletsRet == null) {
				// 需要选择一个还车网点
				return $.SendHtml("114", SYSTEM.UTF8);
			}

			// 电动车型的开始预定时间判断
			if (car.get$cscModel().getCscmType().intValue() == 1) {
				// 当前车型只提供一小时以内的车辆预定
				// if(SrvHost.isHangZhou(SystemHelper.getSrvHost().getShId())){
				if (csoStart.getTime() - Calendar.getInstance().getTime().getTime() > SYSTEM.HOUR * 2)
					return $.SendHtml("112", SYSTEM.UTF8);
				// }else{
				// if(csoStart.getTime() -
				// Calendar.getInstance().getTime().getTime() > SYSTEM.HOUR * 1)
				// return $.SendHtml("112", SYSTEM.UTF8);
				// }
			}
			// 设置定单基本信息
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (SystemHelper.isBaseOrderInfo())
				baseOrderInfo = SystemHelper.getBaseOrderInf();

			if (!baseOrderInfo.getCsoIsSpring()) {
				if ((csoStart.getTime() - new Date().getTime()) > (Constant.ORDER_DAYS_EARLY * SYSTEM.DAY))
					// 系统只提供7天以内的预订
					return $.SendHtml("107", SYSTEM.UTF8);
			} else {
				// 2015年使用春节套餐租车最短要租满10天
				if ((csoFinish.getTime() - csoStart.getTime()) < (10 * SYSTEM.DAY))
					return $.SendHtml("113", SYSTEM.UTF8);
			}
			// SystemHelper.formatDate(csoFinish);
			// SystemHelper.formatDate(csoStart);
			baseOrderInfo.setCsoFinish(csoFinish);
			baseOrderInfo.setCsoStart(csoStart);
			baseOrderInfo.setCsoCar(csoCar);
			baseOrderInfo.setCsoOutletsRet(csOutletsRet.getCsoId());
			baseOrderInfo.setCsoOutletsNameRet(csOutletsRet.getCsoName());
			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
			// 设置定单基本信息 Session 成功，跳转到 预订页面
			return $.SendHtml("100", SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("9999", SYSTEM.UTF8);
		}
	}

	/**
	 * 根据 baseOrderInfo 生成条件参数列表
	 * 
	 * @param baseOrderInfo
	 * @return
	 */
	private Map<String, Object> getCarParams(BaseOrderInfo baseOrderInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 设置地域信息
		params.put(CsCar.F.cscHost, SystemHelper.getSrvHost().getShId());
		params.put(CsCar.F.cscStatus, 1);
		if (baseOrderInfo == null)
			return params;
		// 限定用车方式，异地还车
		if (baseOrderInfo.getCsoType() != -1) {
			// 限定网点
			if (!baseOrderInfo.getCsoOutlets().equals(-1l)) {
				CsOutlets csOutlets = csOutletsService.getCsOutletsById(baseOrderInfo.getCsoOutlets());
				if (!csOutlets.getCsoCanA()) {
					// 纠正用车方式为 原借原还
					baseOrderInfo.setCsoType(-1);
					// 指定网点
					params.put(CsCar.F.cscOutlets, baseOrderInfo.getCsoOutlets());
					// 限定时间
					if (baseOrderInfo.getCsoIsDate()) {
						// 限定用车方式
						params.put("definex", " csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '" + SystemHelper.formatDate(baseOrderInfo.getCsoStart())
								+ "' group by cso_car )");

					}
				} else {
					// 指定网点
					params.put(CsCar.F.cscOutlets, baseOrderInfo.getCsoOutlets());
					// 限定时间
					if (baseOrderInfo.getCsoIsDate()) {
						// 限定用车方式
						params.put("definex", " csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '" + SystemHelper.formatDate(baseOrderInfo.getCsoStart())
								+ "' group by cso_car )");

					}
				}
			} else {
				// 指定区域，不指定网点
				if (!baseOrderInfo.getCsoArea().equals(-1l)) {
					// 自定义
					String ids = "";
					List<CsOutlets> listOutlets = csOutletsService.getCsOutletsList($.add(CsOutlets.F.csoArea, baseOrderInfo.getCsoArea()).add(CsOutlets.F.csoCanA, true), -1);
					if (listOutlets.size() > 0) {
						for (CsOutlets car : listOutlets) {
							ids = ids + car.getCsoId() + ",";
						}
						ids = ids.substring(0, ids.length() - 1);
						if (baseOrderInfo.getCsoIsDate()) {
							params.put("definex", CsCar.C.cscOutlets + " in ( " + ids + " )" + " and csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
									+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
						} else {
							params.put("definex", CsCar.C.cscOutlets + " in ( " + ids + " )");
						}
					} else {
						if (baseOrderInfo.getCsoIsDate()) {
							params.put("definex", CsCar.C.cscOutlets + " in ( 0 )  " + " and  csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
									+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
						} else {
							params.put("definex", CsCar.C.cscOutlets + " in ( 0 )  ");
						}
					}
				} else {
					// 指定网点
					// params.put(CsCar.F.cscOutlets,
					// baseOrderInfo.getCsoOutlets());

					// 自定义
					String ids = "";
					List<CsOutlets> listOutlets = csOutletsService.getCsOutletsList($.add(CsOutlets.F.csoCanA, true), -1);
					if (listOutlets.size() > 0) {
						for (CsOutlets car : listOutlets) {
							ids = ids + car.getCsoId() + ",";
						}
						ids = ids.substring(0, ids.length() - 1);
						if (baseOrderInfo.getCsoIsDate()) {
							params.put("definex", CsCar.C.cscOutlets + " in ( " + ids + " )" + " and csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
									+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
						} else {
							params.put("definex", CsCar.C.cscOutlets + " in ( " + ids + " )");
						}
					} else {
						if (baseOrderInfo.getCsoIsDate()) {
							params.put("definex", CsCar.C.cscOutlets + " in ( 0 )  " + " and  csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
									+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
						} else {
							params.put("definex", CsCar.C.cscOutlets + " in ( 0 )  ");
						}
					}
				}
			}
		} else {
			// 限定网点
			if (!baseOrderInfo.getCsoOutlets().equals(-1l)) {
				// 指定网点
				params.put(CsCar.F.cscOutlets, baseOrderInfo.getCsoOutlets());
				// 限定时间
				if (baseOrderInfo.getCsoIsDate()) {
					// 限定用车方式
					params.put("definex", " csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '" + SystemHelper.formatDate(baseOrderInfo.getCsoStart())
							+ "' group by cso_car )");

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
							params.put("definex", CsCar.C.cscOutlets + " in ( " + ids + " )" + " and csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
									+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
						} else {
							params.put("definex", CsCar.C.cscOutlets + " in ( " + ids + " )");
						}
					} else {
						if (baseOrderInfo.getCsoIsDate()) {
							params.put("definex", CsCar.C.cscOutlets + " in ( 0 )  " + " and  csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
									+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
						} else {
							params.put("definex", CsCar.C.cscOutlets + " in ( 0 )  ");
						}
					}
				} else {
					// 指定网点
					// params.put(CsCar.F.cscOutlets,
					// baseOrderInfo.getCsoOutlets());
					// 限定时间
					if (baseOrderInfo.getCsoIsDate()) {
						params.put("definex", " csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '" + SystemHelper.formatDate(baseOrderInfo.getCsoStart())
								+ "' group by cso_car )");
					}
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
			// CsOutlets csOutlets = csOutletsService.getCsOutletsById(1065l);
			SrvHost srvHost = SystemHelper.getSrvHost();
			String[] point = srvHost.getShLocation().split(",");
			baseOrderInfo.setCsoLatitude(Double.valueOf(point[0]));
			baseOrderInfo.setCsoLongitude(Double.valueOf(point[1]));
		}
		params.put("asc", "(pow(" + CsCar.C.cscLongitude + " -  " + baseOrderInfo.getCsoLongitude() + ",2) + pow(" + CsCar.C.cscLatitude + " - " + baseOrderInfo.getCsoLatitude() + ",2))");

		return params;
	}

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}

	public ICsCarService getCsCarService() {
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}

	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
	}

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}

	public Long getCar() {
		return car;
	}

	public void setCar(Long car) {
		this.car = car;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
