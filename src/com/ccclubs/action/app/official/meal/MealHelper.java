package com.ccclubs.action.app.official.meal;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.APICallHelper;
import com.ccclubs.model.CsOrder;
import com.ccclubs.util.DateUtil;

/**
 * 
 * 套餐助手类
 * 
 * @author joel
 *
 */
public class MealHelper {
	
	/**
	 * 当前时间与规则对齐
	 * @return
	 */
	public static Date alignStartDate(Date startTime, MealExpress me){
		String rule = me.getRule();
		Calendar current = Calendar.getInstance();
		
		
		Calendar cal = Calendar.getInstance();
		if(startTime == null)startTime = new Date();
		Date propStartTime = startTime;
		
		if(current.getTime().after(startTime)){
			startTime = current.getTime();
		}
		cal.setTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		if(!rule.equals("*")){
			cal.set(Calendar.DAY_OF_WEEK, Integer.valueOf(rule)+1);
		}
		
		Calendar cal_ = Calendar.getInstance();
		cal_.setTime(propStartTime);
		cal_.set(Calendar.HOUR_OF_DAY, 0);
		cal_.set(Calendar.MINUTE, 0);
		cal_.set(Calendar.SECOND, 0);
		
		//如果小于当前时间，则需要加上间隔
		if(cal.get(Calendar.DAY_OF_MONTH) < current.get(Calendar.DAY_OF_MONTH)){
			while(cal.getTime().before(cal_.getTime())){
				cal.add(Calendar.HOUR_OF_DAY, me.getSep());
			}
		}
		
		return cal.getTime();
	}
	
	/**
	 * 解析表达式
	 * @param express
	 * @return
	 */
	public static MealExpress parseExpress(String express){
		MealExpress me = APICallHelper.fromJson(express, MealExpress.class);
		if(me == null)me = new MealExpress();
		if(me.getRule() ==null)me.setRule("*");
		if(me.getTime() ==null)me.setTime("");
		if(me.getTag() == null)me.setTag("");
		return me;
	}

	/**
	 * 计算套餐订单
	 * 位数奇数是空闲，偶数是订单，从1开始
	 * 例如： {time:"60#120#50#360", rule:"1", tag:"1"}  时间线为【空闲 60分钟，订单2个小时，空闲50分钟，订单6个小时】   规则为开始时间是【星期一】
	 *       rule:* 任意时间
	 * @param text
	 */
	public static List<CsOrder> calcMealOrders(String express, Date startTime){
		
		List<CsOrder> orderList = new ArrayList<CsOrder>();
		MealExpress me  = parseExpress(express);
		startTime = alignStartDate(startTime, me);
		
		if(me != null){
			if(!me.getRule().equals("*")){
				int rule = Integer.valueOf(me.getRule());
				int day = getDay(startTime);
				if(day != rule){
					throw new MessageException("订单开始时间不符合套餐规则", 200);
				}
			}
			
			String[] timelines = me.getTime().split("#");
			Date lastBeginDate = startTime;
			System.out.println("=============================套餐时间========================================");
			System.out.println("开始时间\t"+new DateUtil().dateToString(startTime, "yyyy-MM-dd HH:mm:ss"));
			for(int i=1; i<timelines.length+1; i++){
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(lastBeginDate);
				cal.add(Calendar.MINUTE, Integer.valueOf(timelines[i-1]));
				if(cal.getTime().before(Calendar.getInstance().getTime())){
					cal.add(Calendar.HOUR_OF_DAY, me.getSep());
				}
				
				boolean inOrder = i%2 == 0;  //偶数位是订单
				if(inOrder){
					CsOrder order = new CsOrder();
					order.setCsoStartTime(lastBeginDate);
					order.setCsoFinishTime(cal.getTime());
					System.out.println(new DateUtil().dateToString(lastBeginDate, "yyyy-MM-dd HH:mm:ss")+"\t"+new DateUtil().dateToString(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
					orderList.add(order);
				}
				
				lastBeginDate = cal.getTime();
			}
		}
		return orderList;
	}
	
	/**
	 *  1:"星期一", 2:"星期二", 3:"星期三", 4:"星期四", 5:"星期五", 6:"星期六", 0:"星期日"
	 * @param datestr
	 * @return
	 */
	public static int getDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK) -1;
		return day;
	}
	
	public static void main(String[] args) {
		
//		Date date = new DateUtil().StringtoDate("2016-04-11", "yyyy-MM-dd");
//		List<CsOrder> orderList = calcMealOrders("{time:\"540#480#960#480#960#480#960#480#960#480\", rule:\"1\"}", date);
//		for(CsOrder o : orderList){
//			String start = new DateUtil().dateToString(o.getCsoStartTime(), "yyyy-MM-dd HH:mm:ss");
//			String finish = new DateUtil().dateToString(o.getCsoFinishTime(), "yyyy-MM-dd HH:mm:ss");
//			System.out.println(start+"\t"+finish);
//		}
		
//		Calendar current = Calendar.getInstance();
//		current.set(Calendar.DAY_OF_WEEK, 5+1);
//		Date date = current.getTime();
//		System.out.println(new DateUtil().dateToString(date, "yyyy-MM-dd HH:mm:ss"));
		
//		System.out.println(new DateUtil().dateToString(alignStartDate("1"), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(new DateUtil().dateToString(alignStartDate("2"), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(new DateUtil().dateToString(alignStartDate("3"), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(new DateUtil().dateToString(alignStartDate("4"), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(new DateUtil().dateToString(alignStartDate("5"), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(new DateUtil().dateToString(alignStartDate("6"), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(new DateUtil().dateToString(alignStartDate("0"), "yyyy-MM-dd HH:mm:ss"));
		
//		getDay("2016-04-11");
//		getDay("2016-04-12");
//		getDay("2016-04-13");
//		getDay("2016-04-14");
//		getDay("2016-04-15");
//		getDay("2016-04-16");
//		getDay("2016-04-17");
	}
}
