package com.ccclubs.action.app.official.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.app.official.util.EventBarrier;
import com.ccclubs.action.app.official.util.JpushClientHelper;
import com.ccclubs.action.app.official.util.JpushConfig;
import com.ccclubs.action.app.official.util.Reason;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

public class OrderAlarmTask extends Thread {

	ICsSpecialCarService csSpecialCarService;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		DateUtil dateUtil = new DateUtil();
		
		while(true){
			Date date = new Date();
			String definex = "TIMESTAMPDIFF(HOUR, '"+dateUtil.dateToString(date, null)+"',cssc_start_time)<2 and TIMESTAMPDIFF(HOUR,'"+dateUtil.dateToString(date, null)+"',cssc_start_time)>0 ";
			
			List<CsSpecialCar> csSpecialCarList = csSpecialCarService.getCsSpecialCarList($.add("definex", definex).add(
					CsSpecialCar.F.csscStatus, "3"), -1);
			for (CsSpecialCar csSpecialCar : csSpecialCarList) {
				Reason event = Reason.距离订单开始时间小于2小时的给司机推送消息.set(csSpecialCar.getCsscId());
				if(!EventBarrier.has(event)){
					EventBarrier.put(event, (csSpecialCar.getCsscStartTime().getTime()-date.getTime())/(1000));
					try{
						Map<String, String> extra = new HashMap<String, String>();
						extra.put("data", $.json($.add("specialId", csSpecialCar.getCsscId())));
						JpushClientHelper.sendAlertWithTag(csSpecialCar.get$csscDrivercar().get$csdcDriver().getCsdMobile(),"订单提醒", csSpecialCar.getCsscStartTime$()+"的订单2小时之内开始", extra, JpushConfig.OFC_DRIVER_APP);
					}catch(Exception e){
						Logger.getRootLogger().error(e.getMessage(),e);
					}
				}
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	public ICsSpecialCarService getCsSpecialCarService() {
		return csSpecialCarService;
	}

	public void setCsSpecialCarService(ICsSpecialCarService csSpecialCarService) {
		this.csSpecialCarService = csSpecialCarService;
	}

}
