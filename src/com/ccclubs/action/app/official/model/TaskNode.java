package com.ccclubs.action.app.official.model;

import java.util.Calendar;
import java.util.Date;

import com.ccclubs.action.app.official.util.ApiBase;
import com.ccclubs.model.CsSpecialCar;

/**
 * 订单详情任务节点
 * 
 * @author joel
 *
 */
public class TaskNode {
	
	/**
	 * 最后更新时间
	 */
	private Date lastTime;
	
	private CsSpecialCar specialCar;
	
	private boolean running;
	
	public TaskNode(){
		
	}
	
	public TaskNode(CsSpecialCar specialCar){
		this.specialCar = specialCar;
	}
	
	/**
	 * 是否可以请求了
	 * @return
	 */
	public boolean canRequest(){
		if(!(specialCar.getCsscStatus().intValue() == 2 || specialCar.getCsscStatus().intValue() == 3)){
			return false;
		}
		
		//第一次调用
		if(lastTime == null){
			return true;
		}
		
		/**
		 * 参考滴滴订单详情接口请求频率限制
		 * 司机计费前（status<500）访问限制在 10秒/次 
         * 司机计费后（status>=500）访问限制在 300秒/次
		 */
		if(specialCar.getCsscResponse()!=null){
			try{
				int status = Integer.valueOf(specialCar.getCsscResponse());
				long currentTime = Calendar.getInstance().getTime().getTime();
				if(status <500 ){
					if(currentTime - getLastTime().getTime() < 1000 * 10){
						return false;
					}
				}else if(status>=500){
					if(currentTime - getLastTime().getTime() < 1000 * 300){
						return false;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return true;
	}
	

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public CsSpecialCar getSpecialCar() {
		return specialCar;
	}

	public void setSpecialCar(CsSpecialCar specialCar) {
		this.specialCar = specialCar;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
}
