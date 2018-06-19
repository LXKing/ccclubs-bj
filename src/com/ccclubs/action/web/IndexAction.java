package com.ccclubs.action.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsUpdate;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsFeeTypeSetService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsUpdateService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.web.IWebDefaultService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class IndexAction {

	IWebDefaultService	webDefaultService;
	ISrvPropertyService	srvPropertyService;
	ICsCarModelService	csCarModelService;

	/**
	 * 首页 index页面
	 * 
	 * @return
	 */
	public String execute() {
		// 设置可选区域
		ICsAreaService csAreaService = Lazy.GetSpringBean("csAreaService");
		List<CsArea> areas = csAreaService.getCsAreaList($.add(CsArea.F.csaHost, SystemHelper.getSrvHost().getShId()).add("desc", CsArea.C.csaLevel), -1);
		Lazy.SetRequest("areas", areas);

		ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		List<CsCarModel> carModels = SystemHelper.getHostModels(SystemHelper.getSrvHost().getShId());//csCarModelService.getCsCarModelList($.add("definex", CsCarModel.C.cscmHosts + " like '%#" + SystemHelper.getSrvHost().getShId() + "#%'").add(CsCarModel.F.cscmStatus, 1).add("desc", CsCarModel.C.cscmType), -1);
		
		
		//获取车型价格
		ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		List<CsFeeTypeSet> feeTypeSetList = csFeeTypeSetService.getCsFeeTypeSetList($.add(CsFeeTypeSet.F.csftsHost, SystemHelper.getSrvHost().getShId()).add(CsFeeTypeSet.F.csftsStatus, 1), -1);
		Map<Long, CsFeeTypeSet> modelPriceValues = new HashMap<Long, CsFeeTypeSet>();
		if(feeTypeSetList!=null){
			for(CsFeeTypeSet feeTypeSet : feeTypeSetList){
				modelPriceValues.put(feeTypeSet.getCsftsModel(), feeTypeSet);
			}
		}
		$.SetRequest("carModels", carModels);
		
		ICsMemberService csMemberService = $.getBean("csMemberService");
		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);
		
		//app下载
		ICsUpdateService csUpdateService=$.getBean("csUpdateService");
		CsUpdate csUpdateAndroid = csUpdateService.getCsUpdate($.add("csuType", 0));
		$.SetRequest("csUpdateAndroid", csUpdateAndroid);
		CsUpdate csUpdateIos= csUpdateService.getCsUpdate($.add("csuType", 1));
		$.SetRequest("csUpdateIos", csUpdateIos);

		// TODO: 系统通知，消息在首页上展示
		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
		if (!SystemHelper.isBaseOrderInfo()) {
			baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
			if (SystemHelper.isLogin()) {
				// 默认网点不为空
				CsOutlets csOutlets = SystemHelper.getLogin().get$csmOutlets();
				if (csOutlets != null) {
					baseOrderInfo.setCsoArea(csOutlets.getCsoArea());
					baseOrderInfo.setCsoAreaName(csOutlets.get$csoArea().getCsaName());
					baseOrderInfo.setCsoOutlets(csOutlets.getCsoId());
					baseOrderInfo.setCsoOutletsName(csOutlets.getCsoName());
				}
			}
			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
		} else {
			baseOrderInfo = SystemHelper.getBaseOrderInf();
			if (baseOrderInfo.getCsoIsSpring()) {
				baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
				baseOrderInfo.setCsoIsSpring(false);
			}
		}

		$.SetRequest("baseOrderInfo", baseOrderInfo);

		// ICsUpdateService csUpdateService=$.getBean("csUpdateService");
		// CsUpdate csUpdate = csUpdateService.getCsUpdate($.add("csuType", 0));
		// $.SetRequest("csUpdate", csUpdate);

		return "index";
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

}
