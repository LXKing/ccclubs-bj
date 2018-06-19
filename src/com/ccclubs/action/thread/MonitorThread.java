package com.ccclubs.action.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.helper.EventHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.WeixinHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsConfig;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class MonitorThread extends Thread {

	@Override
	public void run() {
		while(true){
			try{
				monitorGateway();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","监控通信程序时出错",ex);
			}
			try{
				monitor();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","监控车辆电量时出错",ex);
			}
			
			try {
				Thread.sleep(SYSTEM.MINUTE*10);//十分钟检查一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	private void monitor() {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<SrvHost> hosts = SrvHost.list($.add(SrvHost.F.shStatus, 1), -1);
		for(SrvHost srvHost:hosts){
			CsConfig config = SYSTEM.getHostConfig(srvHost.getShId());
			Integer limitPower = SYSTEM.getHostConfig(srvHost.getShId()).getCscLimitPower();//车辆电量阀值     当车辆电量超过此值时发短信给维护人员  
			Double limitDistance = SYSTEM.getArgumentValue("首次用车超出报警距离", "first_use_distance_set", 50.0d);
			List<Map> list = dao.executeQuery("select css_id from cs_state where css_host=?",srvHost.getShId());
			for(Map state:list){ 
				CsState csState = CsState.get((Long)state.get("css_id"));
				this.monitorPower(csState, srvHost, config, $.or(limitPower,110));
				this.monitorFirstOrder(csState,limitDistance);
			}	
		}
	}
	
	
	static Map<String,Date> sends = new HashMap();
	
	/**
	 * 监控车辆电量
	 */
	private void monitorPower(CsState csState,SrvHost srvHost,CsConfig config,Integer limitPower) {
		//查看车辆是不是在运营状态
		CsCar csCar = CsCar.getCsCar($.add("cscNumber", csState.getCssNumber()));
		if(csCar==null)return;
		if(Integer.parseInt(csState.getCssPower())>(limitPower*(csCar.getCscDeviceVesion().shortValue()==0?1:10)))
			return;
		//必须是可租用状态
		if(csCar.getCscStatus().shortValue()!=1)return;
		//Date sended = sends.get(csState.getCssNumber());
		//同一车辆8个小时只发送一条
		//if(sended!=null && (new Date().getTime()-sended.getTime())<(8*SYSTEM.HOUR))
			//return;
		sends.put(csState.getCssNumber(), new Date());
		EventHelper.postEvent(srvHost.getShId(),
			EventHelper.EventType.车辆低电量提醒,
			"低电量提醒，车牌号{number}，电量{power}，订单{order}，验证码{code}"
			.replace("{number}", csCar.getCscNumber())
			.replace("{power}", ""+csState.getCssPower())
			.replace("{order}", ""+csState.getCssOrder())
			.replace("{code}", $.zerofill($.rand(9999),4)), 
			csState.getCssId(),CsState.class
		);
	}
	
	
	
	
	
	/**
	 * 监控首次用车订单使用行为
	 * @param limitDistance 
	 */
	private void monitorFirstOrder(CsState csState, Double limitDistance){
		final CsOrder csOrder = csState.get$cssOrder();
		//非首次用车不监控
		if(csOrder==null || csOrder.getCsoUseIndex()>2)
			return;
		//车辆是否掉线
		String sNumber  = csState.getCssNumber();
		CsCar csCar = CsCar.where().cscNumber(sNumber).get();
		if(csCar==null)return;
		long minute = (new Date().getTime()-csState.getCssAddTime().getTime())/SYSTEM.MINUTE;
		if(minute>10){
			EventHelper.postEvent(csCar.getCscHost(), 
				EventHelper.EventType.首次用车掉线提醒,
				"会员["+csOrder.getCsoUseMember$()+"]首次用车["+csCar.getCscNumber()+"]订单号["+csOrder.getCsoId()+"]掉线超过"+minute+"分钟,请及时跟踪", 
				csOrder.getCsoId(),CsOrder.class
			);
			new Thread(){
				public void run(){
					new CsOrder(csOrder.getCsoId()).csoAlarm((short)3).update();
				}
			}.start();
		}
		//订单是否超时
		if(csOrder.getCsoFinishTime().getTime()<new Date().getTime()){
			EventHelper.postEvent(csCar.getCscHost(), 
				EventHelper.EventType.首次用车还车超时,
				"会员["+csOrder.getCsoUseMember$()+"]首次用车["+csCar.getCscNumber()+"]订单号["+csOrder.getCsoId()+"]还车超时,请及时跟踪", 
				csOrder.getCsoId(),CsOrder.class
			);
			new Thread(){
				public void run(){
					new CsOrder(csOrder.getCsoId()).csoAlarm((short)2).update();
				}
			}.start();
		}
		//是否超出距离
		CsOutlets csOutlets = csCar.get$cscOutlets();
		double distance = SystemHelper.getDistanceFromGPSXtoY(
				csOutlets.getCsoLatitude(),csOutlets.getCsoLongitude(),
				Double.valueOf(csState.getCssLatitude()), Double.valueOf(csState.getCssLongitude()));
		
		if(distance>limitDistance){
			EventHelper.postEvent(csCar.getCscHost(), 
				EventHelper.EventType.首次用车超距报警,
				"会员["+csOrder.getCsoUseMember$()+"]首次用车["+csCar.getCscNumber()+"]订单号["+csOrder.getCsoId()+"]超出设定("+limitDistance+")距离"+(limitDistance-distance)+"公里,请及时跟踪", 
				csOrder.getCsoId(),CsOrder.class
			);
			new Thread(){
				public void run(){
					new CsOrder(csOrder.getCsoId()).csoAlarm((short)1).update();
				}
			}.start();
		}
	}
	
	
	


	/**
	 * 监控通信程序
	 */
	private void monitorGateway() {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		Date last = (Date) dao.executeQuery("select * from cs_gateway_state limit 1").get(0).get("csgs_last_time");
		if((new Date().getTime()-last.getTime()) > (SYSTEM.MINUTE*10)){
			String arg = SYSTEM.getArgumentValue("通信程序监控人手机号码", "MONITOR_GATEWAY_MOBILE", "13634198360");
			String[] mobiles = arg.split(",");
			for(String mobile:mobiles)
				UtilHelper.sendSms(null,mobile, "通信程序断线超过10分钟，请及时处理",SMSType.提示类短信);
		}		
	}
	
}
