package com.ccclubs.action.official;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsUnitBill;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.service.admin.ICsUnitBillService;
import com.ccclubs.service.admin.ICsUnitGroupService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

public class FinancialAction {

	public ICsUnitBillService csUnitBillService;
	public ICsUnitGroupService csUnitGroupService;
	public ICsUnitOrderService csUnitOrderService;
	public CsUnitBill csUnitBill;

	public String execute() {
		try {
			$.SetRequest("controller", $.getJson("controller"));
			//查询条件传递到转跳
			$.SetRequest("entrypoint", $.hex($.getUrlPath() + "?" + $.querys("UTF-8")));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsUnitBill.F.csubUnit, UnitLoginHelper.getLogin().getCsuuUnitInfo());
			params.put(CsUnitBill.F.csubMonth, $.getString(CsUnitBill.F.csubMonth));
			params.put(CsUnitBill.F.csubGroup, $.getLong(CsUnitBill.F.csubGroup));
			params.put("definex", "(" + CsUnitBill.C.csubGroup + ">0)");
			params.put("desc", "csub_id");

			/*List<CsUnitOrder> total_List = new ArrayList<CsUnitOrder>();//系统所有订单
			List<CsUnitOrder> csUnitOrder_List = new ArrayList<CsUnitOrder>();//系统所有公务车订单
			List<CsUnitOrder> csSpecialCar_List = new ArrayList<CsUnitOrder>();//所有专车订单
			List totalfee_special = new ArrayList();
			List totalfee_unit = new ArrayList();*/
			@SuppressWarnings("rawtypes")
			Page page = csUnitBillService.getCsUnitBillPage($.getInteger("page", 0), 12, params);
			//Iterator csUnitOrder;			
			/*for(Iterator its=total_bill.iterator();its.hasNext();){
				csUnitBill = (CsUnitBill)its.next();
				total_List = csUnitBill.getDetails();
				double fee_special = 0l;
				double fee_unit = 0l;
				for(Iterator it=total_List.iterator();it.hasNext();){
					CsUnitOrder csUnitOrder = (CsUnitOrder)it.next();
					//如果
					if(csUnitOrder.getCsuoSpecial() != null && csUnitOrder.getCsuoOrder() == null){
						csSpecialCar_List.add(csUnitOrder);
						fee_special += csUnitOrder.getCsuoPayReal();
					}else if(csUnitOrder.getCsuoSpecial() == null && csUnitOrder.getCsuoOrder() != null){
						csUnitOrder_List.add(csUnitOrder);
						fee_unit += csUnitOrder.getCsuoPayReal();
					}
				}
				totalfee_special.add(fee_special);
			}*/
			/*$.setRequest("totalfee_special", totalfee_special);
			$.setRequest("totalfee_unit", totalfee_unit);
			$.setRequest("total_unit", csUnitOrder_List.size());
			$.setRequest("total_special", csSpecialCar_List.size());*/
			$.SetRequest("page", page);

			//获取所有的部门信息
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("csugInfo", UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
			param.put("definex", "(csug_status>=0)");
			List<CsUnitGroup> csUnitGroups = csUnitGroupService.getCsUnitGroupList(param, -1);
			$.setRequest("csUnitGroups", csUnitGroups);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(), e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return "index";
	}

	public String details() {
		try {
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitBill);//进入之前的预处理
			$.SetRequest("controller", controller);
			Map<String, Object> params1 = ActionContext.getContext().getParameters();
			Map<String, Object> params = new HashMap<String, Object>();
			Long id = $.getLong(params1, "id");
			csUnitBill = csUnitBillService.getCsUnitBillById(id);
			params.put(CsUnitOrder.F.csuoUnitGroup, csUnitBill.getCsubGroup());
			params.put(CsUnitOrder.F.csuoStartTimeStart, csUnitBill.getCsubMonth() + "-01 00:00:00");
			params.put(CsUnitOrder.F.csuoStartTimeEnd, csUnitBill.getCsubMonth() + "-31 24:59:59");
			params.put("join", " join cs_order co on co.cso_id =cs_unit_order.csuo_order ");
			params.put("definex", " co.cso_status = 4 ");
			@SuppressWarnings("rawtypes")
			Page page = csUnitOrderService.getCsUnitOrderPage($.getInteger("page", 0), 12, params);
			$.SetRequest("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(), e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return "details";
	}

	public ICsUnitBillService getCsUnitBillService() {
		return csUnitBillService;
	}

	public void setCsUnitBillService(ICsUnitBillService csUnitBillService) {
		this.csUnitBillService = csUnitBillService;
	}

	public CsUnitBill getCsUnitBill() {
		return csUnitBill;
	}

	public void setCsUnitBill(CsUnitBill csUnitBill) {
		this.csUnitBill = csUnitBill;
	}

	public ICsUnitGroupService getCsUnitGroupService() {
		return csUnitGroupService;
	}

	public void setCsUnitGroupService(ICsUnitGroupService csUnitGroupService) {
		this.csUnitGroupService = csUnitGroupService;
	}

	public ICsUnitOrderService getCsUnitOrderService() {
		return csUnitOrderService;
	}

	public void setCsUnitOrderService(ICsUnitOrderService csUnitOrderService) {
		this.csUnitOrderService = csUnitOrderService;
	}

}
