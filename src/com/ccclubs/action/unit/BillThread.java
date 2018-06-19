package com.ccclubs.action.unit;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsRecord;
import com.ccclubs.model.CsUnitAirport;
import com.ccclubs.model.CsUnitBill;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsUnitAirportService;
import com.ccclubs.service.admin.ICsUnitBillService;
import com.ccclubs.service.admin.ICsUnitFeeService;
import com.ccclubs.service.admin.ICsUnitInfoService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.lazy3q.web.helper.$;


public class BillThread extends Thread{
	
	ICsMemberService csMemberService;
	ICsUnitInfoService csUnitInfoService;
	ICsUnitBillService csUnitBillService;
	ICsUnitAirportService csUnitAirportService;
	ICsOrderService csOrderService;
	ICsUnitOrderService csUnitOrderService;
	ICsUnitFeeService csUnitFeeService;
	

	@Override
	public void run() {
		try{
			//根据企业的创建日期开始到当前时间,每月生成一个帐单
			List<CsUnitInfo> units = csUnitInfoService.getCsUnitInfoList(new HashMap(), -1);
			for(CsUnitInfo csUnitInfo:units){
				Date date = csUnitInfo.getCsuiAddTime();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				while(calendar.getTime().before(new Date())){
					settleUnitBill(csUnitInfo,calendar.getTime());
					calendar.add(Calendar.MONTH, 1);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();	
		}
		
		while(true){
			try{
				$.trace("企业结算逻辑开始执行....");
				executeBill();
			}catch(Exception ex){
				ex.printStackTrace();	
			}
			
			try {
				Thread.sleep(SYSTEM.MINUTE);//一分钟处理一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	//一个小时处理一次
	private static int lastExecuteBill = -1;
	private void executeBill() {
		if(lastExecuteBill==new Date().getHours())
			return;
		lastExecuteBill = new Date().getHours();
		List<CsUnitInfo> units = csUnitInfoService.getCsUnitInfoList(new HashMap(), -1);
		for(CsUnitInfo csUnitInfo:units){
			settleUnitBill(csUnitInfo,new Date());
		}
	}


	/**
	 * 根据企业结算订单
	 * @param csUnitInfo 企业信息
	 * @param month 帐单月分
	 */
	private void settleUnitBill(CsUnitInfo csUnitInfo, Date month) {
		//如果帐单月分与当前时间在同一月，则不结算
		if($.equals($.date(month, "yyyyMM"),$.date(new Date(), "yyyyMM")))
			return;
		//如果要结算的月份为上月并且当前日期小于10号，则放弃结算
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		Date premoth = calendar.getTime();
		if($.equals($.date(month, "yyyyMM"),$.date(premoth, "yyyyMM")))
			return;
		
		//账单月份描述根据结算日判断
		String billMonth = $.date(month, "yyyy-MM")+"对帐单";
		CsUnitBill existUnitBill = csUnitBillService.getCsUnitBill(
			$.add(CsUnitBill.F.csubMonth, billMonth)
			.add(CsUnitBill.F.csubUnit, csUnitInfo.getCsuiId())
		);
		if(existUnitBill!=null)
			return;
		
		//根据酒店信息查询是否需要生成帐单
		calendar.setTime(month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, -1);
		Date settleStartDate = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date settleEndDate = calendar.getTime();
		
		
		CsUnitBill csUnitBill = new CsUnitBill();
		csUnitBill.setCsubHost(csUnitInfo.getCsuiHost());
		csUnitBill.setCsubAddTime(new Date());
		csUnitBill.setCsubUpdateTime(new Date());
		csUnitBill.setCsubUnit(csUnitInfo.getCsuiId());
		csUnitBill.setCsubMonth(billMonth);
		csUnitBill.setCsubStatus((short)0);
		csUnitBill.setCsubEditor(0l);
		
		
		com.ccclubs.model.CsUnitAirport.M airportWhere = CsUnitAirport.where().csuaStatus((short)1).csuaUnitInfo(csUnitInfo.getCsuiId()).csuaTimeEnd(settleEndDate);
		com.ccclubs.model.CsUnitOrder.M orderWhere = CsUnitOrder.where().csuoSettleState((short)1).csuoSettleTimeEnd(settleEndDate).csuoUnitInfo(csUnitInfo.getCsuiId()).definex("csuo_order>0");
		com.ccclubs.model.CsUnitOrder.M specialWhere = CsUnitOrder.where().csuoSettleState((short)1).csuoSettleTimeEnd(settleEndDate).csuoUnitInfo(csUnitInfo.getCsuiId()).definex("csuo_special>0");
		com.ccclubs.model.CsRecord.M inComeWhere = CsRecord.where().add("definex", "(csr_member in ("+csUnitInfo.getCsuiMember()+") and EXISTS (SELECT csrs_id FROM cs_record_subject WHERE csr_record_subject = csrs_id AND csrs_is_oper_in=1)) ");
		com.ccclubs.model.CsRecord.M payOutWhere = CsRecord.where().add("definex", "(csr_member in ("+csUnitInfo.getCsuiMember()+") and EXISTS (SELECT csrs_id FROM cs_record_subject WHERE csr_record_subject = csrs_id AND csrs_is_oper_out=1)) ");
		
		//获取机场接送次数
		csUnitBill.setCsubAirS(airportWhere.count().intValue());
		csUnitBill.setCsubOrderS(orderWhere.count().intValue());
		csUnitBill.setCsubSpecialS(specialWhere.count().intValue());		
		
		//获取订单结算费用
		csUnitBill.setCsubFeeOrder((Double)orderWhere.eval("sum(csuo_settlement)"));
		csUnitBill.setCsubFeeSpecial((Double)specialWhere.eval("sum(csuo_settlement)"));
		
		
		
		//获取支出 收入金额
		csUnitBill.setCsubIncome((Double)inComeWhere.eval("sum(csr_amount)")==null ? 0.0 : ((Double)inComeWhere.eval("sum(csr_amount)")*-1));
		csUnitBill.setCsubPayout(payOutWhere.eval("sum(csr_amount)")==null ? 0.0 : ((Double)payOutWhere.eval("sum(csr_amount)"))*-1);
		
		//保存帐单
		csUnitBill = csUnitBill.save(); 
		
		//同步帐单ID
		orderWhere.set().csuoSettlement(csUnitBill.getCsubId()).update();
		specialWhere.set().csuoSettlement(csUnitBill.getCsubId()).update();
		airportWhere.set().csuaStatement(csUnitBill.getCsubId()).update();
	}


	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}


	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}



	public ICsUnitInfoService getCsUnitInfoService() {
		return csUnitInfoService;
	}



	public void setCsUnitInfoService(ICsUnitInfoService csUnitInfoService) {
		this.csUnitInfoService = csUnitInfoService;
	}


	public ICsUnitBillService getCsUnitBillService() {
		return csUnitBillService;
	}


	public void setCsUnitBillService(ICsUnitBillService csUnitBillService) {
		this.csUnitBillService = csUnitBillService;
	}

	public ICsUnitAirportService getCsUnitAirportService() {
		return csUnitAirportService;
	}

	public void setCsUnitAirportService(
			ICsUnitAirportService csUnitAirportService) {
		this.csUnitAirportService = csUnitAirportService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}


	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}


	public ICsUnitFeeService getCsUnitFeeService() {
		return csUnitFeeService;
	}


	public void setCsUnitFeeService(ICsUnitFeeService csUnitFeeService) {
		this.csUnitFeeService = csUnitFeeService;
	}


	public ICsUnitOrderService getCsUnitOrderService() {
		return csUnitOrderService;
	}


	public void setCsUnitOrderService(ICsUnitOrderService csUnitOrderService) {
		this.csUnitOrderService = csUnitOrderService;
	}
	
}
