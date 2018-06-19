package com.ccclubs.action.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.config.Constant;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.service.web.IWebDefaultService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class PricesAction {

	IWebDefaultService	webDefaultService;
	ISrvPropertyService	srvPropertyService;
	ICsUserTypeService	csUserTypeService;
	ICsCarModelService	csCarModelService;
	ICsOutletsService	csOutletsService;
	ICommonOrderService	commonOrderService;
	ICsProductService	csProductService;
	ICsGoodsService		csGoodsService;

	public String execute() {
		// 设置区域
		ICsAreaService csAreaService = Lazy.GetSpringBean("csAreaService");
		List<CsArea> areas = csAreaService.getCsAreaList($.add(CsArea.F.csaHost, SystemHelper.getSrvHost().getShId()).add("desc", CsArea.C.csaLevel), -1);
		Lazy.SetRequest("areas", areas);

		// 列出车型列表
		ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		List<CsCarModel> carModels = SystemHelper.getHostModels(SystemHelper.getSrvHost().getShId());//csCarModelService.getCsCarModelList($.add("definex", CsCarModel.C.cscmHosts + " like '%#" + SystemHelper.getSrvHost().getShId() + "#%'").add(CsCarModel.F.cscmStatus, 1).add("desc", CsCarModel.C.cscmType), -1);
		$.SetRequest("carModels", carModels);

		// 获取个人会员类型的的实体
		//CsUserType userType = SrvHost.get(SystemHelper.getSrvHost().getShId()).get$shFeeType();

		// CsOutlets csOutlets =
		// csOutletsService.getCsOutletsById(Constant.PRICES_OUTETS_VALUE);
		CsOutlets csOutlets = csOutletsService.getCsOutlets($.add(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId()).add(CsOutlets.F.csoCanOrder, 1).add(CsOutlets.F.csoStatus, 1).add("asc", CsOutlets.C.csoId));
		Lazy.SetRequest(CsPrice.F.cspOutets, csOutlets.getCsoId());
		Lazy.SetRequest("area", csOutlets.getCsoArea());
//		Lazy.SetRequest(CsPrice.F.cspUserType, userType.getCsutId());
//		String onHour = StringUtils.leftPad(userType.getCsutOnHour$(), 2, "0");
//		String offHour = StringUtils.leftPad(userType.getCsutOffHour$(), 2, "0");
//		Lazy.setRequest("onHour", (userType.getCsutOnHour$() == null || userType.getCsutOnHour$().trim().equals("")) ? 9 : onHour);
//		Lazy.setRequest("offHour", (userType.getCsutOffHour$() == null || userType.getCsutOffHour$().trim().equals("")) ? 17 : offHour);
//		int nightHours = (userType.getCsutOffHour() == null || userType.getCsutOnHour() == null) ?  16 :  (24 - userType.getCsutOffHour() + userType.getCsutOnHour());
//		Lazy.setRequest("nightHours", nightHours);
		// 设置默认选择 工作日
		//Lazy.SetRequest(CsPrice.F.cspGoods, Constant.DAYS);
		return "prices";
	}
	
	/**
	 * 根据计费类型路由价格模板
	 * @return
	 */
	public String routePriceTemplate(String csutFlag){
		
		//分时计费策略
		if(csutFlag.equals(CsUserType.USER_TYPE_TIME_SHARE)){
			return "prices_time";
		}else{
			return "prices";
		}
		
	}

	public String details() {
		// 根据 车辆 id 获得价格信息
		Long cscar = $.getLong("car", 0l);
		ICsPriceService csPriceService = $.getBean("csPriceService");
		ICsCarService csCarService = $.getBean("csCarService");
		CsCar car = csCarService.getCsCarById(cscar);
		CsCarModel carModel = car.get$cscModel();
		SrvHost srvHost = car.get$cscHost();
		CsUserType csUserType = CsFeeTypeSet.Get(
			$.add(CsFeeTypeSet.F.csftsHost, car.getCscHost())
			.add(CsFeeTypeSet.F.csftsModel, car.getCscModel())
		).get$csftsDefault();
		if(csUserType != null){
			int nightHours = (int) ((srvHost.getShOffHour() == null || srvHost.getShOnHour() == null) ?  16 :  (24 - srvHost.getShOffHour() + srvHost.getShOnHour()));
			Lazy.setRequest("nightHours", nightHours);
		}else{
			Lazy.setRequest("nightHours", 16);
		}
		
		List<CsPrice> listPrice = new ArrayList<CsPrice>();
		if (car == null) {
			$.SetRequest("listPrice", listPrice);
			return "details";
		}
		if (carModel == null) {
			$.SetRequest("listPrice", listPrice);
			return "details";
		}
		CsOutlets csOutlets = car.get$cscOutlets();
		if (csOutlets == null) {
			$.SetRequest("listPrice", listPrice);
			return "details";
		}
		
		
		//listPrice = csPriceService.getCsPriceList($.add(CsPrice.F.cspUserType, csUserType.getCsutId()).add(CsPrice.F.cspOutets, csOutlets.getCsoId()).add(CsPrice.F.cspModel, carModel.getCscmId()).add("desc", CsPrice.C.cspGoods), -1);
		//$.SetRequest("listPrice", listPrice);
		$.SetRequest("car", car);
		$.SetRequest("carModel", carModel);
		$.SetRequest("csOutlets", csOutlets);
		
		return "details";

		//return routePriceDetailTemplate(csUserType.getCsutFlag());
	}
	
	/**
	 * 根据计费类型路由价格详情模板
	 * @return
	 */
	public String routePriceDetailTemplate(String csutFlag){
		
		//分时计费策略
		if(csutFlag.equals(CsUserType.USER_TYPE_TIME_SHARE)){
			return "details_time";
		}else{
			return "details";
		}
		
	}

	/**
	 * 显示收集取还车时间，责任免除，行驶里程
	 * 
	 * @return
	 */
	public String step2() {
		try {
			long cspModel = $.getLong("cspModel");
			long cspOutets = $.getLong("cspOutets");
			CsCarModel csCarModel = csCarModelService.getCsCarModelById(cspModel);
			CsOutlets csOutlets = csOutletsService.getCsOutletsById(cspOutets);

			// 获取保险的产品

			List<CsProduct> insureTypes = csProductService.getCsProductList($.add("definex", CsProduct.C.cspFlag + " = '" + Constant.SECURE + "' || " + CsProduct.C.cspFlag + " = '" + Constant.INSURE + "'"), -1);

			$.SetRequest("insureTypes", insureTypes);
			// 获取保险的产品
			// CsProduct insureProduct =
			// csProductService.getCsProduct($.add(CsProduct.F.cspFlag,
			// Constant.INSURE));
			// List<CsGoods> insureTypes =
			// csGoodsService.getCsGoodsList($.add(CsGoods.F.csgProduct,
			// insureProduct.getCspId()).add("asc", CsGoods.C.csgId), -1);
			// $.SetRequest("insureTypes", insureTypes);

			$.SetRequest("csCarModel", csCarModel);
			$.SetRequest("csOutlets", csOutlets);
		} catch (Exception e) {
			return $.SendHtml("false", "UTF-8");
		}
		return "step2";
	}

	/**
	 * 根据 取还车时间，责任免除，行驶里程，网点，车型 计算所需租金，里程费，责任免除费用
	 * 
	 * @return
	 */
	public String simulate() {
		try {
			// 车型
			Long cspModel = $.getLong("cspModel");
			CsCarModel csCarModel = csCarModelService.getCsCarModelById(cspModel);
			$.SetRequest("csCarModel", csCarModel);
			// 网点
			Long cspOutets = $.getLong("cspOutets");
			CsOutlets csOutlets = csOutletsService.getCsOutletsById(cspOutets);
			$.SetRequest("csOutlets", csOutlets);
			// 会员计费类型
			Long csmType = CsFeeTypeSet.Get(
					$.add(CsFeeTypeSet.F.csftsHost, csOutlets.getCsoHost())
					.add(CsFeeTypeSet.F.csftsModel, cspModel)
				).getCsftsDefault();
			// 里程数
			double fuel = $.getDouble("fuel", 0d);
			$.SetRequest("fuel", fuel);

			Date start = $.getDate("start");
			Date finish = $.getDate("finish");
			// 取还车时间
			$.SetRequest("start", start);
			$.SetRequest("finish", finish);
			// 预计里程费
			$.SetRequest("dur", SystemHelper.getTimeDesc(start, finish));
			// 保险类型
			Long insureType = $.getLong("insureType", 0l);
			CsProduct csProduct = csProductService.getCsProductById(insureType);
			$.SetRequest("insureProduct", csProduct);
			$.SetRequest("insureType", insureType);
			/** **********商品列表************ */
			OrderInfo orderInfo;
			// orderinfo = commonOrderService.splitOrderDetails(csmType,
			// cspModel, cspOutets, start, finish, fuel, insureType);
			if (csCarModel.getCscmType() == (short) 0) {
				orderInfo = commonOrderService.splitOrderDetails(csmType, cspModel, cspOutets, start, finish, null, fuel, insureType);
			} else {
				orderInfo = commonOrderService.splitOrderDetails(csmType, cspModel, cspOutets, start, finish, fuel, 0, insureType);
			}
			/** ***********计费信息************** */
			// 应付金额
			$.SetRequest("price", orderInfo.getRent());
			// 预计里程费
			$.SetRequest("predict", orderInfo.getFeeee());
			// 保险费用
			$.SetRequest("insure", orderInfo.getInsure());
			// 保险费用
			$.SetRequest("total", orderInfo.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("false", "UTF-8");
		}
		return "simulate";
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

	public ICsUserTypeService getCsUserTypeService() {
		return csUserTypeService;
	}

	public void setCsUserTypeService(ICsUserTypeService csUserTypeService) {
		this.csUserTypeService = csUserTypeService;
	}

	public ICsCarModelService getCsCarModelService() {
		return csCarModelService;
	}

	public void setCsCarModelService(ICsCarModelService csCarModelService) {
		this.csCarModelService = csCarModelService;
	}

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}

	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}

	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
	}

	public ICsProductService getCsProductService() {
		return csProductService;
	}

	public void setCsProductService(ICsProductService csProductService) {
		this.csProductService = csProductService;
	}

	public ICsGoodsService getCsGoodsService() {
		return csGoodsService;
	}

	public void setCsGoodsService(ICsGoodsService csGoodsService) {
		this.csGoodsService = csGoodsService;
	}

}
