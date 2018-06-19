package com.ccclubs.action.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.action.web.PricesJsonFormat;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class PriceAction {
	private ISrvPropertyService	srvPropertyService;
	private ICsUserTypeService	csUserTypeService;
	private ICsOutletsService	csOutletsService;
	private ICsPriceService		csPriceService;
	private ICsGoodsService		csGoodsService;
	private ICsCarModelService	csCarModelService;

	/**
	 * 价格
	 * 
	 * @return
	 */
	public String execute() {
		String price_carModel = $.getString("price_carModel");
		String time = $.getString("price_time");
		
		// 设置可选区域
		ICsAreaService csAreaService = Lazy.GetSpringBean("csAreaService");
		List<CsArea> areas = csAreaService.getCsAreaList($.add(CsArea.F.csaHost, SystemHelper.getSrvHost().getShId()).add("desc", CsArea.C.csaLevel), -1);
		Lazy.SetRequest("areas", areas);

		ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		List<CsCarModel> carModels = SystemHelper.getHostModels(SystemHelper.getSrvHost().getShId());//csCarModelService.getCsCarModelList($.add("definex", CsCarModel.C.cscmHosts + " like '%#" + SystemHelper.getSrvHost().getShId() + "#%'").add(CsCarModel.F.cscmStatus, 1).add("desc", CsCarModel.C.cscmType), -1);
		$.SetRequest("carModels", carModels);
		if(price_carModel==null||price_carModel.equals("")||price_carModel.equals("0")){
			$.SetRequest("csoModel", carModels.get(0).getCscmId());
			$.SetRequest("csoModelName", carModels.get(0).getCscmName$());
			price_carModel = carModels.get(0).getCscmId$();
		}else{
			$.SetRequest("csoModel", price_carModel);
			CsCarModel ccm = csCarModelService.getCsCarModelById(Long.parseLong(price_carModel));
			$.SetRequest("csoModelName", ccm.getCscmName$());
		}

		CsOutlets csOutlets = csOutletsService.getCsOutlets($.add(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId()).add(CsOutlets.F.csoCanOrder, 1).add(CsOutlets.F.csoStatus, 1).add("asc", CsOutlets.C.csoId));
		$.SetRequest("area", csOutlets.getCsoArea());
		$.SetRequest("csOutlets", csOutlets.getCsoId());
		if(time==null||time.equals("")){
			$.SetRequest("time", 1);
		}else{
			$.SetRequest("time", time);
		}
		
		List<CsPrice> listPrice = new ArrayList<CsPrice>();
		String price_outlets = $.getString("price_outlets");
		//初始化价格列表
		if(price_outlets==null||price_outlets.equals("")||price_outlets.equals("-1")){
			price_outlets = csOutlets.getCsoId$();
		}
		Long feeType = 3L;//默认为默认计费类型
		if(csOutlets.getCsoHost()==2){
			feeType = 7L;
		}
		listPrice = csPriceService.getCsPriceList($.add(CsPrice.F.cspUserType, feeType).add(CsPrice.F.cspOutets, price_outlets).add(CsPrice.F.cspModel, price_carModel).add("desc", CsPrice.C.cspGoods), -1);
		
		// 获取个人会员类型的的实体
		$.SetRequest(CsPrice.F.cspUserType,CsFeeTypeSet.Get(
				$.add(CsFeeTypeSet.F.csftsHost, csOutlets.getCsoHost())
				.add(CsFeeTypeSet.F.csftsModel, price_carModel)
			).getCsftsDefault());
		$.SetRequest("listPrice", listPrice);
		$.SetRequest("hosts", csOutlets.getCsoHost());
		return "price";
	}

	public String getPrice() {
		try {
			/*
			Map<String, String> paramsGoods = new HashMap<String, String>();
			String cspGoods = $.getString("cspGoods");

			paramsGoods.put("definex", CsGoods.C.csgName + " like '%" + cspGoods + "%'");
			List<CsGoods> listGoods = csGoodsService.getCsGoodsList(paramsGoods, -1);
			// TODO：价格列表没有
			String ids = "";
			for (CsGoods csGood : listGoods) {
				ids = ids + csGood.getCsgId() + ",";
			}
			if (ids.length() > 1) {
				ids = ids.substring(0, ids.length() - 1);
			}*/

			List<PricesJsonFormat> pricesJsonList = new ArrayList<PricesJsonFormat>();
			PricesJsonFormat pricesJsonFormat = new PricesJsonFormat();
			Map<String, Object> params = new HashMap<String, Object>();
			// 设置网点
			params.put(CsPrice.F.cspOutets, $.getInteger("cspOutets"));
			// 设置会员类型
			params.put(CsPrice.F.cspUserType, CsFeeTypeSet.Get(
					$.add(CsFeeTypeSet.F.csftsHost, CsOutlets.get($.getLong("cspOutets")).getCsoHost())
					.add(CsFeeTypeSet.F.csftsModel, $.getInteger("cspModel"))
				).getCsftsDefault());
			// 车型
			params.put(CsPrice.F.cspModel, $.getInteger("cspModel"));
			params.put("desc", CsPrice.C.cspGoods);
			// 自定义的条件查询
			params.put("definex", CsPrice.C.cspPrice + " > 0");//  and " + CsPrice.C.cspGoods + " in (" + ids + ")
			pricesJsonFormat.prices = csPriceService.getCsPriceList(params, -1);
			pricesJsonList.add(pricesJsonFormat);

			return $.SendAjax(pricesJsonList, SYSTEM.UTF8);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return $.SendAjax("error", SYSTEM.UTF8);
		}
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

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}

	public ICsPriceService getCsPriceService() {
		return csPriceService;
	}

	public void setCsPriceService(ICsPriceService csPriceService) {
		this.csPriceService = csPriceService;
	}

	public ICsGoodsService getCsGoodsService() {
		return csGoodsService;
	}

	public void setCsGoodsService(ICsGoodsService csGoodsService) {
		this.csGoodsService = csGoodsService;
	}

	public ICsCarModelService getCsCarModelService() {
		return csCarModelService;
	}

	public void setCsCarModelService(ICsCarModelService csCarModelService) {
		this.csCarModelService = csCarModelService;
	}

}
