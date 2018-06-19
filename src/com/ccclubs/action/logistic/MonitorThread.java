package com.ccclubs.action.logistic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsServAlarm;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvHost;
import com.jada.jdbc.Rs2bean;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class MonitorThread extends Thread {

	@Override
	public void run() {
		$.trace("####");
		dao = $.getDao("ccclubs_system");
		hosts = SrvHost.list($.add(SrvHost.F.shStatus, 1), -1);
		while(true){
			try{
				monitor();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","监控服务程序时出错",ex);
			}
			try {
				Thread.sleep(SYSTEM.MINUTE * 5);	//5分钟检查一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	Lazy3qDaoSupport dao;
	List<SrvHost> hosts ;
	Rs2bean rs = new Rs2bean();
	final Map<String, CsState> carstatemap = new HashMap<String, CsState>();
	
	final static int ALARM_TYPE__LOST 				= 1;	//掉线
	final static int ALARM_TYPE__LOWPOWER 			= 2;	//小电瓶亏电
	final static int ALARM_TYPE__LOWEVBATTERY		= 3;	//低电量提醒
	
	private void monitor() {
		
		for(SrvHost srvHost : hosts){
			
			List<CsState> stateList = CsState.getCsStateList($.add(CsState.F.cssHost, srvHost.getShId()), Integer.MAX_VALUE);
			for(CsState state : stateList){ 
				try{
					CsCar car 	= CsCar.getCsCar($.add(CsCar.F.cscNumber, state.getCssNumber()));
					if(car == null){
						Logger.getRootLogger().error("车牌号为【"+state.getCssNumber()+"】的状态数据没有车辆！");
						continue;
					}
					
					Long carId 	= car.getCscId(); 
					CsServAlarm alarm = CsServAlarm.Get($.add(CsServAlarm.F.cssaStatus, 1)
							.add(CsServAlarm.F.cssaRelate, "CsCar@"+carId)
							.add(CsServAlarm.F.cssaType, ALARM_TYPE__LOST));
					
					CsServAlarm alarm_lowpower = CsServAlarm.Get($.add(CsServAlarm.F.cssaStatus, 1)
							.add(CsServAlarm.F.cssaRelate, "CsCar@"+carId)
							.add(CsServAlarm.F.cssaType, ALARM_TYPE__LOWPOWER));
					
					CsServAlarm alarm_lowevbattery = CsServAlarm.Get($.add(CsServAlarm.F.cssaStatus, 1)
							.add(CsServAlarm.F.cssaRelate, "CsCar@"+carId)
							.add(CsServAlarm.F.cssaType, ALARM_TYPE__LOWEVBATTERY));
					
					// =========================######################车辆掉线##################========================
					if(alarm == null){
						if (new Date().getTime() - state.getCssAddTime().getTime() >= 1000 * 60 * 15) {		// 15分钟未更新状态数据，则认为已掉线
							alarm = new CsServAlarm(srvHost.getShId(), car.getCscOutlets(), Long.valueOf(ALARM_TYPE__LOST+""), "车辆掉线", 
									"{outelets}网点{carNo}车机掉线，请及时检查！".replace("{outelets}", car.getCscOutlets$()).replace("{carNo}", car.getCscCarNo$()),
									"CsCar@"+carId, null, (short)1, null, new Date(), new Date()).save();
						}
					}else{
						if(new Date().getTime() - state.getCssAddTime().getTime() < 1000 * 60 * 10){		//上线
							alarm.cssaUpdateTime(new Date()).cssaStatus((short)0).update();
						}
					}
					
					// ========================######################小电瓶亏电##################========================
					if(alarm_lowpower == null){
						if(Integer.valueOf($.or(state.getCssPower(), "0")) < 1000){
							alarm_lowpower = new CsServAlarm(srvHost.getShId(), car.getCscOutlets(), Long.valueOf(ALARM_TYPE__LOWPOWER+""), "小电瓶亏电", 
									"{outelets}网点{carNo}电量小电瓶可能正在漏电，请及时检查！".replace("{outelets}", car.getCscOutlets$()).replace("{carNo}", car.getCscCarNo$()),
									"CsCar@"+carId, null, (short)1, $.json($.add("power", state.getCssPower())), new Date(), new Date()).save();
						}
					}else{
						if(Integer.valueOf($.or(state.getCssPower(), "0")) >= 1000){
							alarm_lowpower.cssaUpdateTime(new Date()).cssaStatus((short)0).update();
						}
					}
					
					// ========================######################低电量##################========================
					if(alarm_lowevbattery == null){
						if(Integer.valueOf($.or(state.getCssEvBattery(), "0")) < 20){
							alarm_lowevbattery = new CsServAlarm(srvHost.getShId(), car.getCscOutlets(), Long.valueOf(ALARM_TYPE__LOWEVBATTERY+""), "低电量", 
									"{outelets}网点{carNo}电量过低，请及时安排充电！".replace("{outelets}", car.getCscOutlets$()).replace("{carNo}", car.getCscCarNo$()),
									"CsCar@"+carId, null, (short)1, $.json($.add("evbattery", state.getCssPower())), new Date(), new Date()).save();
						}
					}else{
						if(Integer.valueOf($.or(state.getCssEvBattery(), "0")) >= 20){
							alarm_lowevbattery.cssaUpdateTime(new Date()).cssaStatus((short)0).update();
						}
					}
					
					carstatemap.put(state.getCssNumber(), state);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
		}
	}
}
