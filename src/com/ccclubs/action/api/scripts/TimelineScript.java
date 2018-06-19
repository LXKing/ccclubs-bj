package com.ccclubs.action.api.scripts;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.ccclubs.model.CsCar;
import com.lazy3q.web.util.Page;

public class TimelineScript extends BaseScript{
	
	/**
	 * 获取车辆列表的订单/限行时间线
	 * @param page
	 * @param start 开始时间
	 * @param finish 结束时间
	 */
	public static void readTimeLine(Page<CsCar> page, String start, String finish){
		
		List<CsCar> result = page.getResult();
		if(result == null)return;
		
		//如果开始时间为空，开始时间为今天00:00:00
		SimpleDateFormat formatter = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		if(start == null){
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
			calendar.set(java.util.Calendar.MINUTE, 0);
			calendar.set(java.util.Calendar.SECOND, 0);
			start = formatter.format(calendar.getTime());
		}
		
		//如果结束时间为空，结束时间为今天23:59:59
		if(finish == null){
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			calendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
			calendar.set(java.util.Calendar.MINUTE, 59);
			calendar.set(java.util.Calendar.SECOND, 59);
			finish = formatter.format(calendar.getTime());
		}
		
		String carIds = "";
		for(int i = 0; i<result.size(); i++){
			CsCar car = result.get(i);
			carIds += (i==0 ? "" : ",")+ car.getCscId();
		}
		
		Map<String, Object> map = OrderScript.getCarStatusTimeLine(carIds, start, finish);
		if(map!=null){
			for(CsCar car : result){
				Map value = (Map)map.get(car.getCscId().toString());
				if(value !=null){
					car.setValues("orders", value.get("orderTimeLine"));
					car.setValues("restrs", value.get("limitTimeLine"));
				}
			}
		}
	}
	
}
