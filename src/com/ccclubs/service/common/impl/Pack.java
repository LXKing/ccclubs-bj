package com.ccclubs.service.common.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.APICallHelper;
import com.ccclubs.model.SrvHost;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

public class Pack {
	
	private String rule;
	private Date start;
	private Date finish;
	private Date startTimeIn;
	private String name;
	private Integer cycle = 1;
	
	//===========================================================
	private Date _start;
	private Date _finish;
	private Long minutes;
	private Double rentPrice;
	private Double goodsPrice;
	
	private boolean ruleSuccess = false;	//规则状态
	
	public Pack(Date start, Date finish, Date startTimeIn, String rule, Integer cycle){
		this.start = start;//规则开始时间
		this.finish = finish;//规则结束时间
		this.startTimeIn=startTimeIn;//夜租订单开始时间
		this.minutes = (this.finish.getTime() - this.start.getTime()) / SYSTEM.MINUTE;
		
		this.rule = rule;
		this.cycle = cycle;
	}
	
	public static boolean hasPack(String prof){
		if(StringUtils.isNotEmpty(prof) && prof.indexOf("params")!=-1){
			return true;
		}
		return false;
	}
	
	public static Pack getPack(DateUtil dateUtil, Date ordbegin, Date ordend, String paramsJSON, SrvHost srvHost){
		if(StringUtils.isEmpty(paramsJSON)) return null;
		Map<String, Object> mapParam = (Map)APICallHelper.fromJson(paramsJSON);
		if(mapParam == null || (Map)mapParam.get("params") == null) return null;
		Map<String, Object> params = (Map)mapParam.get("params");
		
		String rule = mapParam.get("rule") == null ? "" : mapParam.get("rule").toString();
		Integer cycle = mapParam.get("cycle") == null ? 1 : Integer.valueOf(mapParam.get("cycle").toString());
		
		//==============================计算时间==============================================
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(ordbegin);
		Calendar finishCalendar = Calendar.getInstance();
		finishCalendar.setTime(ordbegin);
		Calendar startTimeInCalender=Calendar.getInstance();
		startTimeInCalender.setTime(ordbegin);
		
		//1、日期+时间限定的
		Calendar cal = Calendar.getInstance();
		cal.setTime(ordbegin);
		
		//日期时间限定
		if(params.get("startDateTime")!=null && params.get("finishDateTime")!=null){
			startCalendar.setTime(dateUtil.StringtoDate(params.get("startDateTime").toString(), null));
			finishCalendar.setTime(dateUtil.StringtoDate(params.get("finishDateTime").toString(), null));
		}else{
			//2、自由组合
			if(params.get("startWeekDay")!=null){
				startCalendar.set(Calendar.DAY_OF_WEEK, Integer.valueOf(params.get("startWeekDay").toString()) + 1);
			}
			
			if(params.get("startTime")!=null){ //17:00	2:00
				startCalendar.setTime(dateUtil.StringtoDate(dateUtil.dateToString(ordbegin, "yyyy-MM-dd")+" "+params.get("startTime").toString(), null));
				
				if(cal.get(Calendar.HOUR_OF_DAY)+(cal.get(Calendar.MINUTE)*0.01) < $.or(srvHost.getShOnHour(), 9d)){
					startCalendar.setTime(DateUtils.add(startCalendar.getTime(), Calendar.DAY_OF_MONTH, -1));
				}
			}
			
			
			if (params.get("startTimeIn")!=null) {
                startTimeInCalender.setTime(DateUtils.add(startCalendar.getTime(), Calendar.MINUTE, Integer.valueOf(params.get("startTimeIn").toString())));
            }
			
		
			if(params.get("timeLength")!=null){
				finishCalendar.setTime(DateUtils.add(startCalendar.getTime(), Calendar.MINUTE, Integer.valueOf(params.get("timeLength").toString())));
				
				//获取定位到[最近的]比当前时间晚结束时间
				if(DateUtils.add(finishCalendar.getTime(), Calendar.DAY_OF_MONTH, -7).after(ordbegin)){
					startCalendar.setTime(DateUtils.add(startCalendar.getTime(), Calendar.DAY_OF_MONTH, -7));
					finishCalendar.setTime(DateUtils.add(finishCalendar.getTime(), Calendar.DAY_OF_MONTH, -7));
				}
			}
		}
		
		Pack pack = new Pack(startCalendar.getTime(), finishCalendar.getTime(),startTimeInCalender.getTime(), rule, cycle);
		return pack;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * 订单金额满X元
	 * @return
	 */
	public boolean ordAmount(){
		Long realMinites = (getFinish().getTime() - getStart().getTime()) / SYSTEM.MINUTE;
		boolean bool = false;
		if(rentPrice!=null && realMinites!=null && goodsPrice!=null){
			if(rentPrice * realMinites >= goodsPrice) bool = true;
		}
		return bool;
	}
	
	/**
	 * 获取套餐包数量
	 * @return
	 */
	public int getCount(){
		if(!this.ruleSuccess)return 0;
		Long realMinites = (getFinish().getTime() - getStart().getTime()) / SYSTEM.MINUTE;
		
		int count = 1;
		if(getCycle()>1){
			Double count_ = Math.floor((this._finish.getTime() - this._start.getTime()) / realMinites);
			if(count_ >0){
				return count;
			}
		}
		return count;
	}
	
	/**
	 * 下单时间在范围内
	 * */
	public boolean timeStartIn() {
	    boolean flag=false;
	    if(start!=null&& _start!=null&& startTimeIn!=null){
	        if (_start.getTime()>=start.getTime()&&_start.getTime()<startTimeIn.getTime()) {
	            flag = true;
            }
	    }
	    return flag;
	}
	
	/**
	 * 在套餐时间内
	 * @return
	 */
	public boolean timeIn(){
		boolean boolflag = false;
		if(start != null && finish!=null && _start!=null && _finish!=null){
			if(_start.getTime() <= start.getTime() && _finish.getTime() > start.getTime()){
				 if(_finish.getTime() < finish.getTime()){
					 finish = _finish;
				 }
				 boolflag = true;
			}
			
			if(_start.getTime() >= start.getTime() && _start.getTime() < finish.getTime()){
				 start = _start;
				 boolflag = true;
			}
		}
		
		this.reach = (finish.getTime() - start.getTime()) / SYSTEM.MINUTE >= this.minutes;
		
		return  boolflag;
	}
	
	boolean reach = false;
	
	public boolean isFit(){
		return reach;
	}

	public void setParam(Date _start, Date _finish, Double rentPrice, Double goodsPrice){
		this._start = _start;
		this._finish = _finish;
		this.rentPrice = rentPrice;
		this.goodsPrice = goodsPrice;
	}

	public Date getStart() {
		if(_start.getTime() >= start.getTime() && _start.getTime() <= finish.getTime()){
			start = _start;
		}
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getFinish() {
		if(start != null && finish!=null && _start!=null && finish!=null){
			if(_start.getTime() <= start.getTime() && _finish.getTime() > start.getTime()){
				 if(_finish.getTime() < finish.getTime()){
					 finish = _finish;
				 }
			}
		}
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}
	
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
	
	public Integer getCycle() {
		return cycle == null ? 1 : cycle;
	}
}
