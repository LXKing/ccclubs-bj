package com.ccclubs.action.unit;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsGiftService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.lazy3q.web.helper.$;

public class WelcomeAction {
	
	ICsUnitOrderService csUnitOrderService;
	
	public String execute(){
		try{			
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//查询条件传递到转跳
			Calendar calendar=Calendar.getInstance();
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 0);
			Long orderCount = csUnitOrderService.getCsUnitOrderCount(
				$.add(CsUnitOrder.F.csuoUnitInfo, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId())
				.add(CsUnitOrder.F.csuoStartTime+"Start", calendar.getTime()));
			$.SetRequest("orderCount", orderCount);
			
			//取所有已下单的订单
			List<CsUnitOrder> orders = csUnitOrderService.getCsUnitOrderList(
				$.add(CsUnitOrder.F.csuoUnitInfo, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId())
				.add(CsUnitOrder.F.csuoState, 2)
					, -1);
			$.SetRequest("orders", orders);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		return "index";
	}


	public ICsUnitOrderService getCsUnitOrderService() {
		return csUnitOrderService;
	}


	public void setCsUnitOrderService(ICsUnitOrderService csUnitOrderService) {
		this.csUnitOrderService = csUnitOrderService;
	}
}
