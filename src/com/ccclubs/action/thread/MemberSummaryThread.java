package com.ccclubs.action.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberSummary;
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMemberSummaryService;
import com.ccclubs.service.admin.ICsOrderService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Page;

/**
 * 行程统计线程
 * 
 * @author just
 * 
 */
public class MemberSummaryThread {

	private final int PAGE_ZIE = 500;
	private Timer timer;
	private TravelDate travelDate;
	private CsMemberSummary csMemberSummary;
	
	ICsOrderService csOrderService;
	ICsMemberService csMemberService;
	ICsMemberSummaryService csMemberSummaryService;
	
	public void init() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 2); // 凌晨1点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime(); // 第一次执行定时任务的时间
		// 如果第一次执行定时任务的时间 小于当前的时间
		// 此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}
		timer = new Timer();
		travelDate = new TravelDate();
		timer.schedule(travelDate, date,1000*60*60*24);
	}

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	public class TravelDate extends TimerTask {
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			LzMap order_definex_map = $.add("join", "LEFT JOIN cs_unit_order u on cso_id = u.csuo_order ").add(CsOrder.F.csoStatus, 4).add("definex", "(  u.csuo_id = '' or u.csuo_id is null)");
			LzMap unit_definex_map = $.add("join",  "JOIN cs_unit_order u on cso_id = u.csuo_order").add(CsOrder.F.csoStatus, 4).add("definex", "(csuo_settle_state = 1)");
			//获取订单总数
			long count = csMemberService.getCsMemberCount($.add("definex", " ( EXISTS (select cso_use_member from cs_order where cso_use_member = csm_id and cso_status=4) )"));
			//获取分页总数
			
			int page_count = (int) ((count+PAGE_ZIE-1)/PAGE_ZIE);
			for (int i = 0; i < page_count; i++) {
				Page<CsMember> page = csMemberService.getCsMemberPage(i, PAGE_ZIE, $.add("definex", " ( EXISTS (select cso_use_member from cs_order where cso_use_member = csm_id and cso_status=4) )"));
				
				for (CsMember csMember : page.getResult()) {
					
					boolean exists = false;//不存在
					csMemberSummary = csMemberSummaryService.getCsMemberSummary($.add(CsMemberSummary.F.csmsMember, csMember.getCsmId()));
					if(csMemberSummary == null){
						csMemberSummary = new CsMemberSummary();
						exists = true;
					}
					
					try {
						long order_total = csOrderService.getCsOrderCount( order_definex_map.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						long unit_total = csOrderService.getCsOrderCount( unit_definex_map.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						Double order_time = csOrderService.getCsOrderEval("SUM(cso_duration) ",order_definex_map.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						Double unit_time = csOrderService.getCsOrderEval("SUM(cso_duration)",unit_definex_map.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						Double order_mileage = csOrderService.getCsOrderEval("SUM(cso_fuel+cso_electric) ", order_definex_map.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						Double unit_mileage = csOrderService.getCsOrderEval("SUM(cso_fuel+cso_electric) ", unit_definex_map.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						Double order_amount = csOrderService.getCsOrderEval("SUM(cso_pay_real+cso_pay_coin)",  order_definex_map.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						Double unit_amount = csOrderService.getCsOrderEval("SUM(cso_pay_real)",  unit_definex_map.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						csMemberSummary.setCsmsMember(csMember.getCsmId());
						csMemberSummary.setCsmsOrdTotal((String.valueOf(order_total)==null) ? 0 : Integer.parseInt(String.valueOf(order_total)));
						csMemberSummary.setCsmsUnitordTotal((String.valueOf(unit_total)==null) ? 0 : Integer.parseInt(String.valueOf(unit_total)));
						csMemberSummary.setCsmsOrdTimelong(order_time == null ? 0 :((Double)(order_time*60)).intValue());
						csMemberSummary.setCsmsUnitordTimelong(unit_time == null ? 0 :((Double)( unit_time*60)).intValue());
						csMemberSummary.setCsmsOrdMileages(order_mileage == null ? 0 : order_mileage);
						csMemberSummary.setCsmsUnitordMileages(unit_mileage == null ? 0 : unit_mileage );
						csMemberSummary.setCsmsOrdAmount(order_amount == null ? 0 : order_amount);
						csMemberSummary.setCsmsUnitordAmount(unit_amount == null ? 0 : unit_amount);
						if(exists){
							csMemberSummary.setCsmsAddtime(new Date());
							csMemberSummaryService.saveCsMemberSummary(csMemberSummary);
						}else{
							csMemberSummary.setCsmsUpdatetime(new Date());
							csMemberSummaryService.updateCsMemberSummary(csMemberSummary);
						}
						
					} catch (Exception e) {
						// TODO: handle exception
						Logger.getRootLogger().error("用ID ： "+csMember.getCsmId$()+"统计失败", e);
					}
					
				}
				
			}
		}
	}

	

	
	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsMemberSummaryService getCsMemberSummaryService() {
		return csMemberSummaryService;
	}

	public void setCsMemberSummaryService(
			ICsMemberSummaryService csMemberSummaryService) {
		this.csMemberSummaryService = csMemberSummaryService;
	}
	

}
