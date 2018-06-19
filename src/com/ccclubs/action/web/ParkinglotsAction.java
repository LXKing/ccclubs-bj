package com.ccclubs.action.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.api.scripts.CarScript;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.SystemHelper.Prices;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPowerPile;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsState;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPowerPileService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.common.ICommonOrderService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ParkinglotsAction {
	ICsGoodsService		csGoodsService;
	ICsUserTypeService	csUserTypeService;
	ICsCarService		csCarService;
	ICsPriceService		csPriceService;
	ICsOutletsService	csOutletsService;
	ICsPowerPileService	csPowerPileService;

	public String execute() {
		$.setRequest("point", SystemHelper.getSrvHost().getShLocation());
		$.setRequest("currentHost", SystemHelper.getSrvHost().getShId());
		return "parkinglots";
	}

	/**
	 * 获取电桩JSON数据
	 * 
	 * @return
	 */
	public String getPowerPile() {
		Map<String, Object> params = new HashMap();
		params.put(CsPowerPile.F.csppHost, SystemHelper.getSrvHost().getShId());
		List<CsPowerPile> powerPileList = csPowerPileService.getCsPowerPileList(params, 1000);

		// 预设电桩
		List<HashMap<String, String>> prePowerPileMapList = new ArrayList<HashMap<String, String>>();

		// 正常电桩
		List<HashMap<String, String>> powerPileMapList = new ArrayList<HashMap<String, String>>();
		for (CsPowerPile pile : powerPileList) {

			HashMap<String, String> powerPileMap = new HashMap<String, String>();
			CsOutlets outlets = pile.get$csppOutlets();
			if (outlets != null) {
				powerPileMap.put("Name", pile.get$csppOutlets().getCsoName());
				powerPileMap.put("Address", pile.get$csppOutlets().getCsoAddress());
			} else {
				powerPileMap.put("Name", "");
				powerPileMap.put("Address", "");
			}
			if (StringUtils.isEmpty(pile.getCsppLocation())) {
				continue;
			}

			String[] location = pile.getCsppLocation().split(",");
			powerPileMap.put("Longitude", location[1]);
			powerPileMap.put("Latitude", location[0]);

			if (pile.getCsppStatus().intValue() == 1) { // 正常状态
				powerPileMapList.add(powerPileMap);
			} else if (pile.getCsppStatus().intValue() == 2) { // 预设状态
				prePowerPileMapList.add(powerPileMap);
			}
		}
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		map.put("powerPile", powerPileMapList);
		map.put("prePowerPile", prePowerPileMapList);
		return Lazy.SendAjax(map, SYSTEM.UTF8);
	}

	/**
	 * 依据网点，获取 可租 车辆列表
	 * 
	 * @return
	 */
	// TODO：先选择有车网点，再搜索一个没有网点的地方，下面显示会乱掉
	public String getVehicles() {
		List<VehicleJsonFormat> vehicleJsonList = new ArrayList<VehicleJsonFormat>();
		try {
			//ICommonOrderService commonOrderService = $.getBean("commonOrderService");

			//CsUserType csUserType = SrvHost.get(SystemHelper.getSrvHost().getShId()).get$shFeeType();

			// 获取该网点下所有 可租 车辆
			Map<String, Object> paramsVehicle = new HashMap<String, Object>();
			paramsVehicle.put(CsCar.F.cscOutlets, $.getInteger(CsCar.F.cscOutlets));
			paramsVehicle.put(CsCar.F.cscStatus, 1);
			paramsVehicle.put("asc", CsCar.C.cscModel);
			List<CsCar> listCar = csCarService.getCsCarList(paramsVehicle, -1);
			CsOutlets csOutlets = csOutletsService.getCsOutletsById($.getLong(CsCar.F.cscOutlets));
			$.SetRequest("csOutlets", csOutlets);
			if (listCar.size() == 0) {
				$.SetRequest("records", vehicleJsonList);
			} else {
				for (CsCar csCar : listCar) {
					VehicleJsonFormat vehicleJsonFormat = new VehicleJsonFormat();
					vehicleJsonFormat.setCscId(csCar.getCscId());
					vehicleJsonFormat.setCscIsAd(csCar.getCscIsAd() ? 1 : 0);
					vehicleJsonFormat.setCscNumber(csCar.getCscNumber().replaceAll("^(zj)|(ZJ)", "浙"));
					vehicleJsonFormat.setCscmId(csCar.get$cscModel().getCscmId());
					vehicleJsonFormat.setCscmName(csCar.get$cscModel().getCscmName());
					// TODO:判断登录状态，获取登录用户的UserType类型
					Prices price = SystemHelper.getPrices(csCar.getCscHost(), csCar.getCscModel(), csCar.getCscOutlets());
					Object dayPrice = price.get("租金.工作日一天|租金.每天");
					Object HourPrice = price.get("租金.工作日白天小时|租金.白天小时|租金.每小时");
					vehicleJsonFormat.setPricesDay($.parseDouble(dayPrice));
					vehicleJsonFormat.setPricesHour($.parseDouble(HourPrice));
					vehicleJsonList.add(vehicleJsonFormat);
				}
				$.SetRequest("records", vehicleJsonList);
			}
		} catch (Exception e) {
			$.SetRequest("records", vehicleJsonList);
		}
		return "getVehicles";
	}

	/**
	 * 用途：网点地图弹窗预定，后台再次预定
	 * 
	 * 依据网点，车辆 显示预定弹出层
	 * 
	 * @return
	 */
	public String reserve() {
		try {
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (!SystemHelper.isBaseOrderInfo()) {
				baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
			} else {
				baseOrderInfo = SystemHelper.getBaseOrderInf();
			}
			$.SetRequest("baseOrderInfo", baseOrderInfo);

			long cspCar = $.getInteger("cspCar");
			CsCar csCar = csCarService.getCsCarById(cspCar);
			// 车辆
			$.SetRequest("csCar", csCar);

			CsOutlets csOutlets = csCar.get$cscOutlets();
			// 网点
			$.SetRequest("csOutlets", csOutlets);
			// 如果当前用车方式为 异地还车，筛选出能还入的网点
			if (csOutlets.getCsoCanA()) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId());
				params.put(CsOutlets.F.csoStatus, "1");
				params.put(CsOutlets.F.csoCanOrder, "1");
				// params.put(CsOutlets.F.csoCanB, "1");
				// 可用车位数大于0
				params.put("definex", "(" + CsOutlets.C.csoId + " = " + csOutlets.getCsoId() + " OR (" + CsOutlets.C.csoPositionS + " - " + CsOutlets.C.csoCarS + " > 0 AND " + CsOutlets.C.csoCanB + " = 1))");
				List<CsOutlets> outlets = csOutletsService.getCsOutletsList(params, -1);
				// 还车网点列表
				$.SetRequest("outlets", outlets);
			} else {
				// 还车网点列表
				List<CsOutlets> outlets = new ArrayList<CsOutlets>();
				outlets.add(csOutlets);
				$.SetRequest("outlets", outlets);
			}

			Integer isOnLimitTime = $.getInteger("isOnLimitTime");
			$.setRequest("isOnLimitTime", isOnLimitTime);

			// 电动车去查询一下里程
			if (csCar.get$cscModel().getCscmType() == 1) {
				com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
				CsState csState = SystemHelper.getCsState($.add("cssNumber", csCar.getCscNumber()));
				$.SetRequest("csState", csState);
			}

			// 会员计费类型
//			long csmType = SrvHost.get(SystemHelper.getSrvHost().getShId()).getShFeeType();
			// 根据地区+车型获取策略配置列表
//			CsFeeTypeSet csFeeTypeSet = CsFeeTypeSet.Get($.add(CsFeeTypeSet.F.csftsHost, csCar.getCscHost()).add(CsFeeTypeSet.F.csftsModel, csCar.getCscModel()));
			// 检查会员的计费类型是否是当前地区所接受的计费类型
//			if (csFeeTypeSet != null && csFeeTypeSet.getCsftsFeeType().indexOf("#" + csmType + "#") == -1)
//				csmType = csFeeTypeSet.getCsftsDefault();

//			ICommonOrderService commonOrderService = $.getBean("commonOrderService");
//			$.SetRequest("dayprice", csCar.getCscHourPrice());
			/*
			 * CsGoods csGoods = commonOrderService.getGoodsByFlag(SYSTEM.RENT,
			 * RuleName.工作日白天小时, csmType); Map<String, Object> params = new
			 * HashMap<String, Object>(); // 设置网点
			 * params.put(CsPrice.F.cspOutets, csCar.getCscOutlets()); if
			 * (SystemHelper.isLogin()) csmType =
			 * SystemHelper.getLogin().get$csmType().getCsutId();
			 * params.put(CsPrice.F.cspUserType, csmType);
			 * params.put(CsPrice.F.cspModel, csCar.get$cscModel().getCscmId());
			 * params.put(CsPrice.F.cspGoods, csGoods.getCsgId()); CsPrice
			 * csPrice = csPriceService.getCsPrice(params);
			 * $.SetRequest("dayprice", csPrice.getCspPrice());
			 * 
			 * CsGoods csFuelGoods =
			 * commonOrderService.getGoodsByFlag(SYSTEM.MILEAGE, RuleName.每公里,
			 * csmType); Map<String, Object> fuelParams = new HashMap<String,
			 * Object>(); // 设置网点 fuelParams.put(CsPrice.F.cspOutets,
			 * csCar.getCscOutlets()); fuelParams.put(CsPrice.F.cspUserType,
			 * csmType); fuelParams.put(CsPrice.F.cspModel,
			 * csCar.get$cscModel().getCscmId());
			 * fuelParams.put(CsPrice.F.cspGoods, csFuelGoods.getCsgId());
			 * CsPrice csFuelPrice = csPriceService.getCsPrice(fuelParams);
			 * $.SetRequest("price", csFuelPrice.getCspPrice());
			 */

//			CsGoods csElecGoods = commonOrderService.getGoodsByFlag(csCar.get$cscModel().getCscmType().shortValue() == 0 ? SYSTEM.MILEAGE : SYSTEM.KILOM, RuleName.每公里, csmType);
//
//			if (csElecGoods != null) {
//				Map<String, Object> elecParams = new HashMap<String, Object>();
//				// 设置网点
//				elecParams.put(CsPrice.F.cspOutets, csCar.getCscOutlets());
//				elecParams.put(CsPrice.F.cspUserType, csmType);
//				elecParams.put(CsPrice.F.cspModel, csCar.getCscModel());
//				elecParams.put(CsPrice.F.cspGoods, csElecGoods.getCsgId());
//				CsPrice csElecPrice = csPriceService.getCsPrice(elecParams);
//
//				if (csElecPrice == null) {
//					CsFeeTypeSet feeTypeSet = CsFeeTypeSet.Get($.add(CsFeeTypeSet.F.csftsModel, csCar.getCscModel()).add(CsFeeTypeSet.F.csftsHost, CsOutlets.get(csCar.getCscOutlets()).getCsoHost()));
//					if (feeTypeSet != null && feeTypeSet.getCsftsOutlets() != null) {
//						elecParams.put(CsPrice.F.cspOutets, feeTypeSet.getCsftsOutlets());
//						elecParams.put(CsPrice.F.cspUserType, feeTypeSet.getCsftsDefault());
//					}
//					csElecPrice = csPriceService.getCsPrice(elecParams);
//				}
//				$.SetRequest("csElecPrice", csElecPrice == null ? 0 : csElecPrice.getCspPrice());
//			} else {
//				$.SetRequest("csElecPrice", 0);
//			}
			return "reserve";
		} catch (Exception e) {
			// 白天价格未配置
			// 油里程价格未配置

			e.printStackTrace();

			return $.SendHtml("false", "UTF-8");
		}
	}

	public ICsGoodsService getCsGoodsService() {
		return csGoodsService;
	}

	public void setCsGoodsService(ICsGoodsService csGoodsService) {
		this.csGoodsService = csGoodsService;
	}

	public ICsUserTypeService getCsUserTypeService() {
		return csUserTypeService;
	}

	public void setCsUserTypeService(ICsUserTypeService csUserTypeService) {
		this.csUserTypeService = csUserTypeService;
	}

	public ICsCarService getCsCarService() {
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}

	public ICsPriceService getCsPriceService() {
		return csPriceService;
	}

	public void setCsPriceService(ICsPriceService csPriceService) {
		this.csPriceService = csPriceService;
	}

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}

	public ICsPowerPileService getCsPowerPileService() {
		return csPowerPileService;
	}

	public void setCsPowerPileService(ICsPowerPileService csPowerPileService) {
		this.csPowerPileService = csPowerPileService;
	}

}
