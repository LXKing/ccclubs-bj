package com.ccclubs.action.manage;

import java.util.List;

import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.service.admin.ICsAreaService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class CarAction {

	/*
	 * 所选车辆的最后五个订单
	 * 
	 * @return
	 */
	public String vieworders(){
		try {
			String carid=$.getString("carid");
			List<CsOrder> orders= CsOrder.getCsOrderList($.add(CsOrder.F.csoCar, carid), 5);
			$.SetRequest("orders", orders);
			return "viewordersover";
		} catch (Exception e) {
			e.printStackTrace();
			$.SetTips("错误信息请重试！");
			return "ordersError";
		}
	}
	/*
	 * 查询所在车辆的相关信息
	 * 
	 * @return
	 */
	public String carInfo(){
		try {
			int carid=Integer.valueOf($.getString("carid")).intValue();
			CsCar csCar=CsCar.getCsCarById((long) carid);
			CsCarModel csCarModel=CsCarModel.get(csCar.getCscModel());
			CsOutlets csOutlets=CsOutlets.get(csCar.getCscOutlets());
			$.SetRequest("csOutlets", csOutlets.getCsoName());
			$.SetRequest("csCarModel", csCarModel.getCscmName());
			$.SetRequest("carInfo", csCar);
			return "carInfover";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			$.SetTips("错误信息请重试！");
			return "carInfoErr";
		}
	}
	/**
	 * 地区加载
	 * @ return
	 */
	public String area(){
		List<CsArea> areas = CsArea.where().csaHost($.getLong("cschost")).list(-1);
		$.SetRequest("areas", areas);
		return "areaover";
	}
}
