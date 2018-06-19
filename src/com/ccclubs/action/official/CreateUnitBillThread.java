package com.ccclubs.action.official;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsUnitBill;
import com.ccclubs.model.CsUnitInfo;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 企业对账
 * @author byz
 *
 */
public class CreateUnitBillThread {

	private Date date;
	private Lazy3qDaoSupport dao;
	private String sql = "SELECT u.csuo_host, u.csuo_unit_info,u.csuo_unit_group,count(u.csuo_id) as csub_order_s, SUM(u.csuo_pay_real) as csuo_pay_real ,SUM(csuo_mileage) as csuo_mileage, SUM(o.cso_duration) as cso_duration  from cs_unit_order u LEFT JOIN cs_order o on u.csuo_order = o.cso_id where o.cso_status = 4 and o.cso_start_time BETWEEN '%s' and '%s' and u.csuo_unit_info = %s GROUP BY u.csuo_unit_group ";
	private String month = null;
	private CsUnitBill csUnitBill = null;

	public void run() {
		// TODO Auto-generated method stub
		date = new Date();
		month = $.date(date, "yyyy-MM");
		dao = $.getDao("ccclubs_system");
		List<CsUnitInfo> unitInfoList = CsUnitInfo.getCsUnitInfoList(new HashMap<String, Object>(), -1);
		for (CsUnitInfo csUnitInfo : unitInfoList) {
			createBill(csUnitInfo);
		}
	}

	public void createBill(CsUnitInfo csUnitInfo) {
		List<Map> mapList = dao.executeQuery(String.format(sql, $.date(getMonthMinDay(date), "yyyy-MM-dd"),
				$.date(getMonthMaxDay(date), "yyyy-MM-dd"), csUnitInfo.getCsuiId()));
		for (Map map : mapList) {
			Long unit_host = (Long) map.get("csuo_host");
			Long unit_id = (Long) map.get("csuo_unit_info");
			Long unit_group = (Long) map.get("csuo_unit_group");
			Long unit_order_s = (Long) map.get("csub_order_s");
			Double csuo_pay_real = (Double) map.get("csuo_pay_real");
			Double csuo_mileage = (Double) map.get("csuo_mileage");
			Double cso_duration = (Double) map.get("cso_duration");

			csUnitBill = CsUnitBill.Get($.add(CsUnitBill.F.csubUnit, unit_id).add(CsUnitBill.F.csubGroup, unit_group)
					.add(CsUnitBill.F.csubMonth, month));
			if (csUnitBill == null) {
				csUnitBill = new CsUnitBill();
				csUnitBill.setCsubAddTime(new Date());
				csUnitBill.setCsubUpdateTime(new Date());
				csUnitBill.setCsubAirS(0);
				csUnitBill.setCsubEditor(LoginHelper.getLoginId() == null ? 1l : LoginHelper.getLoginId());
			} else {
				csUnitBill.setCsubUpdateTime(new Date());
			}
			csUnitBill.setCsubMonth(month);
			csUnitBill.setCsubHost(unit_host);
			csUnitBill.setCsubOrderS(unit_order_s.intValue());
			csUnitBill.setCsubUnit(unit_id);
			csUnitBill.setCsubGroup(unit_group);
			csUnitBill.setCsubRealPay(csuo_pay_real.doubleValue());
			csUnitBill.setCsubMileage(csuo_mileage.doubleValue());
			csUnitBill.setCsubDuration(cso_duration.doubleValue());
			csUnitBill.setCsubStatus((short) 0);
			csUnitBill.setCsubIncome(0d);
			if ($.empty(csUnitBill.getCsubId$())) {
				csUnitBill.save();
			} else {
				csUnitBill.update();
			}
		}
	}

	public Date getMonthMinDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public Date getMonthMaxDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
}
