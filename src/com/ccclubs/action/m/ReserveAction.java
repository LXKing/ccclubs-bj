package com.ccclubs.action.m;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.helper.CacheStoreHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsState;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsCreditCardService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvHostService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.util.Page;

public class ReserveAction {
	private ISrvPropertyService		srvPropertyService;
	private ICsOutletsService		csOutletsService;
	private ICsCarService			csCarService;
	private ICsMemberService		csMemberService;
	private ICsGoodsService			csGoodsService;
	private ICsPriceService			csPriceService;
	private ICsUserTypeService		csUserTypeService;
	private ICommonOrderService		commonOrderService;
	private ICommonDisposeService	commonDisposeService;
	private ICsOrderService			csOrderService;
	private ICommonMoneyService		commonMoneyService;
	private ICsProductService		csProductService;
	private ICsCreditCardService	csCreditCardService;
	// ---------------车辆id
	Long							carid;
	// ---------------最近 amount 天的车辆使用情况
	String							date;
	String							startTime;
	String							finishTime;
	DateUtil dateutil = new DateUtil();
	
	@SuppressWarnings("unchecked")
	public String execute() {
		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
		if (!SystemHelper.isBaseOrderInfo()) {
			String startTime = $.getString("startTime") + ":00";
			String finishTime = $.getString("finishTime") + ":00";
			if(startTime != null && !startTime.equals("") ){
				baseOrderInfo.setCsoStart(dateutil.StringtoDate(startTime, null));
				baseOrderInfo.setCsoFinish(dateutil.StringtoDate(finishTime, null));
			}else{
				baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
			}
			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
		}else{
			baseOrderInfo = SystemHelper.getBaseOrderInf();
			if (baseOrderInfo.getCsoIsSpring()) {
				baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
				baseOrderInfo.setCsoIsSpring(false);
			}
		}
		// 取车网点(不做限制)
		baseOrderInfo.setCsoOutlets(-1l);
		// 设置车型
		Long csoModel = $.getLong("carModel", 0l);
		if (!csoModel.equals(0l)){
			baseOrderInfo.setCsoModel(csoModel);
		}
		// 设置是否日期参与筛选条件 false
		baseOrderInfo.setCsoIsDate(false);

		String host = $.getString("host", "1");
		ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
		SrvHost srvHost = srvHostService.getSrvHost($.add(SrvHost.F.shId, host));
		SystemHelper.setSrvHost(srvHost);
		baseOrderInfo.setCsoHost(Long.parseLong(host));
		
		String latitude = $.getString("latitude");
		String longitude = $.getString("longitude");
		baseOrderInfo.setCsoLongitude(Double.parseDouble(longitude));
		baseOrderInfo.setCsoLatitude(Double.parseDouble(latitude));
		
		// 取车区域
		Long csoArea = $.getLong("csoArea", 0l);
		if (!csoArea.equals(0l)) {
			baseOrderInfo.setCsoArea(csoArea);
			ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
			CsArea csoAreaModel = csAreaService.getCsAreaById(csoArea);
			if (csoAreaModel != null) {
				baseOrderInfo.setCsoAreaName(csoAreaModel.getCsaName());
				$.SetRequest("csAreaName", csoAreaModel.getCsaName());
			}
		}
		// 设置所选区域
		$.SetRequest("csoArea", csoArea);

		// 取车网点
		Long csoOutlets = $.getLong("csoOutlets", 0l);
		if (!csoOutlets.equals(0l))
			baseOrderInfo.setCsoOutlets(csoOutlets);
		// 设置所选网点
		CsOutlets csOutlets = csOutletsService.getCsOutletsById(csoOutlets);
		$.SetRequest("csOutlets", csOutlets);

		Map<String, Object> params = getCarParams(baseOrderInfo);
		Page<CsCar> page = csCarService.getCsCarPage($.getInteger("page", 0), 5, params);
		List<CsCar> list = page.getResult();
		List<CsCar> list_x = new ArrayList<CsCar>();
		for(CsCar cscar : list){
			Long carid = cscar.getCscId();
			String today = dateutil.dateToString(new Date(), "yyyy-MM-dd");
			String start = today + " 00:00:00";
			String finish = today + " 23:59:59";
			//限行
			boolean xianxing = commonOrderService.isExistRestrictionByTime(carid, dateutil.StringtoDate(start, null), dateutil.StringtoDate(finish, null));
			if(xianxing){
				cscar.setValues("xianxing", "1");
			}
			
			//油量(汽油车)
			if(cscar.get$cscModel().getCscmType$().equals("汽油车")){
				CsState csstate = CacheStoreHelper.getCarState(cscar.getCscNumber());
				if(csstate!=null){
					//smart-除以365为油量百分比
					if(cscar.get$cscModel().getCscmId$().equals("1003")){
						double oil_left = Double.parseDouble(csstate.getCssOil())/365*100;
						cscar.setValues("oil_left", (int)oil_left);
					}else{
						cscar.setValues("oil_left", Integer.parseInt(csstate.getCssOil()));
					}
				}else{
					cscar.setValues("oil_left", 0);
				}
			}
			
			
			/**************2015年10月15日，刘代进改：在循环中查询影响性能，暂时去掉，请稍候改成在页面上点击时动态判断！！！****************/
			//选择时间是否占用
			if (commonOrderService.isExistOrderByTime(cscar.getCscId(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null)){
				cscar.setValues("occupy", "occupy");
			}else{
				cscar.setValues("occupy", "no");
			}
			
			list_x.add(cscar);
		}
		page.setResult(list_x);
		
		$.SetRequest("page", page);
		$.SetRequest("baseOrderInfo", baseOrderInfo);
		$.SetRequest("startTimeIndex", $.getString("startTime"));
		$.SetRequest("finishTimeIndex", $.getString("finishTime"));
		$.SetRequest("startTime", dateutil.dateToString(baseOrderInfo.getCsoStart(),null));
		$.SetRequest("finishTime", dateutil.dateToString(baseOrderInfo.getCsoFinish(),null));
		$.SetRequest("csoModel", csoModel);
		$.SetRequest("host", host);
		return "reserve";
	}
	
	/**
	 * 根据 baseOrderInfo 生成条件参数列表
	 * 
	 * @param baseOrderInfo
	 * @return
	 */
	@SuppressWarnings("unused")
	private Map<String, Object> getCarParams(BaseOrderInfo baseOrderInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 设置地域信息
		params.put(CsCar.F.cscHost, baseOrderInfo.getCsoHost());
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
			SrvHost srvHost = SrvHost.get(baseOrderInfo.getCsoHost());
			String[] point = srvHost.getShLocation().split(",");
			baseOrderInfo.setCsoLatitude(Double.valueOf(point[0]));
			baseOrderInfo.setCsoLongitude(Double.valueOf(point[1]));
		}
		params.put("asc", "(pow(" + CsCar.C.cscLongitude + " -  " + baseOrderInfo.getCsoLongitude() + ",2) + pow(" + CsCar.C.cscLatitude + " - " + baseOrderInfo.getCsoLatitude() + ",2))");

		return params;
	}
	
	public String timeline(){
		try {
			if (carid != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MINUTE, 0);
				Date start = calendar.getTime();
				String dateCondition = SystemHelper.formatDate(start, "yyyy-MM-dd");
				if (!$.empty(date)){
					dateCondition = date;
				}
//				List<CsOrder> orders = csOrderService.getCsOrderList($.add(CsOrder.F.csoCar, carid).add("definex", CsOrder.C.csoStatus + " not in (3,7) and cso_start_time < '" +
//						dateCondition + " 23:59' and if(ISNULL(cso_ret_time), cso_finish_time  > '" + dateCondition + "' , cso_ret_time >'" + dateCondition + "')"), -1);
//				$.SetRequest("orders", orders);
				int restriction = -1;
				
				Date tempDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateCondition);
				Date tempDateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateCondition + " 23:59");
				List<Restriction> restrictions = commonOrderService.getRestrictions(carid, tempDate, tempDateEnd);
				if (restrictions.size() > 0) {
					restriction = 1;
				}
				$.SetRequest("dateCondition", dateCondition);
				$.SetRequest("restriction", restriction);
				$.SetRequest("startTime", startTime);
				$.SetRequest("finishTime", finishTime);
			}
			return "timeline";
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("{\"error\": \"car must be not null !\"}", "UTF-8");
		}
	}

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
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

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
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

	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}

	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
	}

	public ICommonDisposeService getCommonDisposeService() {
		return commonDisposeService;
	}

	public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
		this.commonDisposeService = commonDisposeService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICsProductService getCsProductService() {
		return csProductService;
	}

	public void setCsProductService(ICsProductService csProductService) {
		this.csProductService = csProductService;
	}

	public ICsCreditCardService getCsCreditCardService() {
		return csCreditCardService;
	}

	public void setCsCreditCardService(ICsCreditCardService csCreditCardService) {
		this.csCreditCardService = csCreditCardService;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getCarid() {
		return carid;
	}

	public void setCarid(Long carid) {
		this.carid = carid;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
}
