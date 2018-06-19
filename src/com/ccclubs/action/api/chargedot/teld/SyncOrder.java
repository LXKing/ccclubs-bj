package com.ccclubs.action.api.chargedot.teld;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ccclubs.model.CsChargeBill;
import com.ccclubs.service.admin.ICsChargeBillService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

/**
 * 同步订单
 */
public class SyncOrder implements Job {
	
	final ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
	final static String provider = "teld";
	final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	Map<String, CsChargeBill> orders = new ConcurrentHashMap<String, CsChargeBill>();
	
	final static Logger log = Logger.getRootLogger();
	int execute_call_count = 0;
	int getMoreInfo_call_count = 0;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TeldApiResp resp;
		try{
			TeldChargeRecordQuery query = new TeldChargeRecordQuery();
			Calendar start = Calendar.getInstance();
			start.set(java.util.Calendar.HOUR_OF_DAY, 0);
			start.set(java.util.Calendar.MINUTE, 0);
			start.set(java.util.Calendar.SECOND, 0);
			
			Calendar end = Calendar.getInstance();
			end.set(java.util.Calendar.HOUR_OF_DAY, 23);
			end.set(java.util.Calendar.MINUTE, 59);
			end.set(java.util.Calendar.SECOND, 59);
			
			query.setBeginTime(new DateUtil().dateToString(start.getTime(), DATE_FORMAT));
			query.setEndTime(new DateUtil().dateToString(end.getTime(), DATE_FORMAT));
			//query.setBeginTime("2015-11-13 00:00:00");
			//query.setEndTime("2015-11-13 23:59:59");
			resp = TeldApi.queryChargeRecord(query);
			if(resp.success()){
				Map<String, Object> re = resp.getResultValue();
				List<?> l = (List<?>)re.get("chargeBills");
				for(int i=0; i<l.size(); i++){
					try{
						DateUtil dateUtil = new DateUtil();
						Map map = (Map)l.get(i);
						CsChargeBill order = csChargeBillService.getCsChargeBill($.add(CsChargeBill.F.cscbSerialnumber, map.get("chargeCode").toString())
								.add(CsChargeBill.F.cscbProvider, provider));
						if(order != null)continue;
						
						order = new CsChargeBill();
						order.setCscbSerialnumber(map.get("chargeCode").toString());
						
						order.setCscbPileCode(map.get("pileCode").toString());
						order.setCscbCarno(map.get("carLicense").toString());
						order.setCscbCustName(map.get("custName").toString());
						order.setCscbCustCardno(map.get("custCardCode").toString());
						order.setCscbStartTime(dateUtil.StringtoDate(map.get("startTime").toString(), DATE_FORMAT));
						order.setCscbFinishTime(dateUtil.StringtoDate(map.get("endTime").toString(), DATE_FORMAT));
						order.setCscbTotalPower(Double.valueOf(map.get("totalPower").toString()));
						order.setCscbTotalElecfee(Double.valueOf(map.get("electricMoney").toString()));
						order.setCscbStatus(map.get("status").toString());
						order.setCscbProvider(provider);
						
						order.setCscbAddTime(new Date());
						order.setCscbUpdateTime(new Date());
						Map<String, Object> otherData = new HashMap<String, Object>();
						otherData.put("openChargeCode", map.get("openChargeCode").toString());
						otherData.put("serviceMoney", map.get("serviceMoney").toString());
						otherData.put("endReason", map.get("endReason").toString());
						order.setCscbData($.json(otherData));
						
						order.save();
						
						orders.put(order.getCscbSerialnumber(), order);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				getMoreInfo();
			}else{
				if(execute_call_count >= 1000){
					log.error("execute_call_count reach threshold!");
					return;
				}
				
				execute_call_count++;
				execute(arg0);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void getMoreInfo() {
		if(orders.isEmpty())return;
		TeldApiResp resp;
		try{
			for(Map.Entry<String, CsChargeBill> entry : orders.entrySet()){
				try{
					CsChargeBill order = csChargeBillService.getCsChargeBill($.add(CsChargeBill.F.cscbSerialnumber, entry.getValue().getCscbSerialnumber())
							.add(CsChargeBill.F.cscbProvider, provider));
					if(order == null)entry.getValue().save();
					
					resp = TeldApi.queryChargeRecord(entry.getValue().getCscbSerialnumber());
					if(resp.success()){
						Map<String, Object> re = resp.getResultValue();
						
						Map<String, Object> data = $.getJson(order.getCscbData());
						if(data == null)data = new HashMap<String, Object>();
						data.put("costInfo", re.get("chargeInfo"));
						data.put("costInfo", re.get("costInfo"));
						
						CsChargeBill orderUpt = new CsChargeBill(); 
						orderUpt.setCscbId(order.getCscbId());
						orderUpt.setCscbUpdateTime(new Date());
						orderUpt.setCscbData($.json(data));
						orderUpt.update();
						
						orders.remove(entry.getKey());
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(getMoreInfo_call_count >= 1000){
				log.error("getMoreInfo_call_count reach threshold!");
				return;
			}
			
			getMoreInfo_call_count++;
			
			getMoreInfo();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
