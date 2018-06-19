package com.ccclubs.action.unit;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.model.CsUnitBill;
import com.ccclubs.service.admin.ICsUnitBillService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class BillAction {

	ICsUnitBillService csUnitBillService;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		try{
			Map params = new HashMap();
			params.put(CsUnitBill.F.csubUnit, UnitLoginHelper.getLogin().getCsuuUnitInfo());
			params.put(CsUnitBill.F.csubMonth, $.getString(CsUnitBill.F.csubMonth));
			params.put(CsUnitBill.F.csubStatus, $.getShort(CsUnitBill.F.csubStatus));		
			
			Page page = csUnitBillService.getCsUnitBillPage($.getInteger("page",0), 8, params);
			$.SetRequest("page", page);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	
	public ICsUnitBillService getCsUnitBillService() {
		return csUnitBillService;
	}
	public void setCsUnitBillService(ICsUnitBillService csUnitBillService) {
		this.csUnitBillService = csUnitBillService;
	}
	
}
