package com.ccclubs.action.m.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ccclubs.model.CsOrder;
import com.ccclubs.util.DateUtil;

import com.lazy3q.web.helper.$;

public class MOrderUtil {
	DateUtil du = new DateUtil();
	
	
	/**
	 * 根据订单列表获取某天的时间交叉信息
	 * @param orderlist
	 * @param dateCondition
	 * @return
	 */
	public List<OrderTime> usableTime(List<CsOrder> orderlist, String dateCondition){
		List<OrderTime> list = new ArrayList<OrderTime>();
		if(orderlist.size()!=0){
			boolean s = isInDates(du.StringtoDate(dateCondition+" 00:00:00", null), orderlist.get(0).getCsoStartTime(), orderlist.get(0).getCsoFinishTime());
			if(!s){
				OrderTime newo = new OrderTime();
				newo.setStart("00:00");
				newo.setFinish(du.dateToString(orderlist.get(0).getCsoStartTime(), "HH:mm"));
				list.add(newo);
			}
			
			Date beginOfToday = $.date(dateCondition+" 00:00:00", "yyyy-MM-dd HH:mm:ss");//一天的开始
			Date endOfToday = $.date(dateCondition+" 23:59:59", "yyyy-MM-dd HH:mm:ss");//一天的结束
			
			Date temp1 = null;
			Date temp2 = null;
			String temp_s = "s";
			for(int i=0;i<orderlist.size();i++){
				CsOrder co = orderlist.get(i);
				
				//下面两行判断这个订单的时间与当天有没有交叉
				if(co.getCsoStartTime().after(endOfToday))continue;
				if(co.getCsoFinishTime().before(beginOfToday))continue;
				
				if(i==(orderlist.size()-1)){
					if(temp_s.equals("f")){
						temp2 = co.getCsoStartTime();
						OrderTime newo = new OrderTime();
						newo.setStart(du.dateToString(temp1, "HH:mm"));
						newo.setFinish(du.dateToString(temp2, "HH:mm"));
						list.add(newo);
					}
					boolean se = isInDates(du.StringtoDate(dateCondition+" 23:59:59", null), co.getCsoStartTime(), co.getCsoFinishTime());
					if(!se){
						OrderTime newo = new OrderTime();
						newo.setStart(du.dateToString(co.getCsoFinishTime(), "HH:mm"));
						newo.setFinish("23:59");
						list.add(newo);
					}
				}else{
					if(temp_s.equals("s")){
						temp1 = co.getCsoFinishTime();
						temp_s = "f";
					}else if(temp_s.equals("f")){
						temp2 = co.getCsoStartTime();
						OrderTime newo = new OrderTime();
						newo.setStart(du.dateToString(temp1, "HH:mm"));
						newo.setFinish(du.dateToString(temp2, "HH:mm"));
						list.add(newo);
						temp_s = "s";
					}
				}
			}
		}
		return list;
	}
	
	public boolean isInDates(Date strDate, Date strDateBegin, Date strDateEnd) {
		boolean s = false;
		if(strDate.before(strDateEnd) && strDate.after(strDateBegin)){
			s = true;
		}
		return s;
	}
	
	public String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
			w = 0;
		}

		return weekDays[w];
	}
}