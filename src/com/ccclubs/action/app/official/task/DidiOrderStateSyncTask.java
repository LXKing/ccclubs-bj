package com.ccclubs.action.app.official.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.util.CollectionUtils;

import com.ccclubs.action.app.official.model.TaskNode;
import com.ccclubs.action.app.official.util.ApiBase;
import com.ccclubs.action.app.official.util.DidiApi;
import com.ccclubs.action.app.official.util.Env;
import com.ccclubs.action.app.official.util.JpushClientHelper;
import com.ccclubs.action.app.official.util.JpushConfig;
import com.ccclubs.action.app.official.util.ThirdPartyApiHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.$;

/**
 * 滴滴专车获取订单状态任务
 * 
 * 请配置使用单例模式启动
 * 
 * 这里如果后续订单数据量大，需要重新设定方案，例如spring reactor
 * 
 * @author joel
 *
 */

public class DidiOrderStateSyncTask{
	
	public static AtomicBoolean init = new AtomicBoolean(false);				//是否已启动实例
	
	static Map<String, TaskNode> records = new ConcurrentHashMap<String, TaskNode>();
	
	ICsSpecialCarService csSpecialCarService;

	public void init(){
		
		if(init.get()){
			return;
		}

		if(! (Env.isTest() || Env.isOnline())){
			$.trace("非在线模式，放弃执行同步任务："+this.getClass().getName());
			return;
		}
		
		Timer timer = new Timer();
		long delay = 10000; 						//从接收到指令延迟十秒后开始执行
		LoadRecordTimerTask task = new LoadRecordTimerTask(this);
		timer.schedule(task, delay, 5000);			//5秒钟刷新一下本地记录
		
		Timer timer_2 = new Timer();
		SyncTimerTask task_2 = new SyncTimerTask(this);
		timer_2.schedule(task_2, delay, 1000 * 10);	//10秒钟
		
		init.set(true);
	}
	
	public void loadRecord(){
		List<CsSpecialCar> specialCarList = csSpecialCarService.getCsSpecialCarList($.add(CsSpecialCar.F.csscThird, ApiBase.THIRD_DIDI).
				add("definex", "("+CsSpecialCar.C.csscStatus+" in (2, 3))"), -1);
		if(!CollectionUtils.isEmpty(specialCarList)){
			List<String> idList = new ArrayList<String>();
			for(CsSpecialCar specialCar : specialCarList){
				idList.add(specialCar.getCsscId().toString());
				TaskNode record = records.get(specialCar.getCsscId().toString());
				if(record!=null){
					//如果正在执行中的任务，就不要去更新record了
					if(!record.isRunning()){
						record.setSpecialCar(specialCar);
					}
				}else{
					records.put(specialCar.getCsscId().toString(), new TaskNode(specialCar));
				}
			}
			
			for(String sepcialId : records.keySet()){
				if(!idList.contains(sepcialId)){
					records.remove(sepcialId);
				}
			}
		}
		
	}
	
	DidiApi api = new DidiApi();
	
	/**
	 * 执行同步
	 */
	public synchronized void sync(){
		
		try{
			if(!CollectionUtils.isEmpty(records)){
				for(String specialCarId : records.keySet()){
					int status = 2;  //已下单 
					TaskNode node = records.get(specialCarId);
					CsSpecialCar specialCar = CsSpecialCar.get(node.getSpecialCar().getCsscId());
					node.setSpecialCar(specialCar);
					
					if(specialCar.getCsscThird().intValue() != ApiBase.THIRD_DIDI){
						records.remove(specialCar.getCsscId().toString());continue;
					}
					if(!(specialCar.getCsscStatus().intValue() == 2 || specialCar.getCsscStatus().intValue() == 3)){
						records.remove(specialCar.getCsscId().toString());continue;
					}
					
					if(node.canRequest()){
						try{
							node.setRunning(true);
							Map<String, Object> map = api.getOrderDetail(specialCar.getCsscMobile(), specialCar.getCsscThirdNo());
							if(map!=null){
								Object code = map.get("errno");
								Object msg = map.get("errmsg");
								Map data = (Map)map.get("data");
								if(Double.valueOf(code.toString()).intValue()==0 && msg.equals("SUCCESS")){
									Map<String, Object> order = (Map)data.get("order"); 
									Integer req_stu = Double.valueOf(order.get("status").toString()).intValue();
									String driver_name = order.get("driver_name").toString();			//司机姓名
									String driver_phone = order.get("driver_phone").toString();			//司机电话
									String driver_car_type = order.get("driver_car_type").toString();	//车型信息
									String driver_card = order.get("driver_card").toString();			//车牌号码
									String finish_time = order.get("finish_time").toString();			//订单结束时间
									//	300 	等待应答
									//	311 	订单超时
									//	400 	等待接驾
									//	410 	司机已到达
									//	500 	行程中
									//	600 	行程结束
									//	610 	行程异常结束
									//	700 	已支付
									
									if(req_stu == 300){
										status = 2;
									}else if(req_stu == 311){
										status = 6;
									}else if(req_stu == 400){
										status = 3;
									}else if(req_stu == 410){
										status = 3;
									}else if(req_stu == 500){
										status = 3;
									}else if(req_stu == 600){
										status = specialCar.getCsscStatus();
									}else if(req_stu == 610){
										status = 5;
									}else if(req_stu == 700){
										status = specialCar.getCsscStatus();
									}
									
									//该字段的状态发生了改变，再去更新数据库
									CsSpecialCar specialCar_New = new CsSpecialCar(specialCar.getCsscId());
									if(specialCar.getCsscResponse()==null || !specialCar.getCsscResponse().equals(req_stu.toString())){
										
										//订单状态变更实时推送==============START==================
										try{
											Map<String, Object> msgmap = new HashMap<String, Object>();
											msgmap.put("specialId", specialCar.getCsscId());
											msgmap.put("status", status);
											CsUnitPerson person = CsUnitPerson.getCsUnitPersonById(specialCar.getCsscPerson());
											JpushClientHelper.sendMessageWithTag(person.getCsupMember().toString(), $.json(msgmap), null, JpushConfig.OFC_APP);
										}catch(Exception e){
											Log.error(e);
										}
										//订单状态变更实时推送==============ENDING==================
										
										specialCar_New.setCsscResponse(req_stu+"");
										specialCar_New.setCsscStatus((short)status); 
										specialCar_New.setCsscCarNumber(driver_card);
										specialCar_New.setCsscDriverName(driver_name);
										specialCar_New.setCsscDriverCarInfo(driver_car_type);
										specialCar_New.setCsscDriverPhone(driver_phone);
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										if(StringUtils.isNotEmpty(finish_time)){
											try{
												specialCar_New.setCsscFinishTime(sdf.parse(finish_time));
											}catch(Exception e){}
										}
										specialCar_New.setCsscUpdateTime(new Date());
										
										//同时更新企业订单里的车牌号码
										new CsUnitOrder(specialCar.getCsscUnitOrder()).csuoNumber(driver_card).update();
										
										//订单超时的,发起重新叫单
										if(req_stu == 311){
											try{
												ThirdPartyApiHelper.sendCallCar(specialCar, true);
												records.remove(String.valueOf(specialCar.getCsscId()));
												continue;
											}catch(Exception e){
												e.printStackTrace();
											}
											
										//司机已到达，状态从“等待接驾”变成“行程中”的时候，更新订单（实际）开始时间
										}else if(req_stu == 410 || (specialCar.getCsscResponse()!=null && !specialCar.getCsscResponse().equals("410") && req_stu == 500)){	 
											specialCar_New.setCsscStartTime(new Date());
											specialCar_New.setCsscUpdateTime(new Date());
											specialCar_New.update();
										}else{
											//加乐观锁 防止数据脏写
											//不支持，暂时没办法加咯~~~~(>_<)~~~~
											//if(specialCar.getCsscResponse()!=null){
												
											//}
											specialCar_New.update();
										}
										
										//发送短信 ==================================================================
									 	CsUnitPerson person = specialCar.get$csscPerson();
									 	if(req_stu.intValue() == 400){	//派单成功/司机接单
											UtilHelper.sendTemplateSMS(person.getCsupHost(),
																	"CALLCAR_RECEIVE",
																	specialCar.getCsscMobile(),
																	"尊敬的{USER_NAME}！您已成功预订叫车服务，司机{DRIVER_NAME}为您服务，电话{DRIVER_PHONE}，该订单由北京公务出行支付，请不要支付给司机车费。"
																		,SMSType.通知类短信, 
																		$.add("USER_NAME", specialCar.getCsscName())
																		 .add("DRIVER_NAME", driver_name)
																		 .add("CAR_ID", driver_card)
																		 .add("DRIVER_PHONE", driver_phone));
									 	}else if(req_stu.intValue() == 410){ //司机已到达
									 		UtilHelper.sendTemplateSMS(person.getCsupHost(),
													"CALLCAR_ARRIVE",
													specialCar.getCsscMobile(),
													"您预订的专车{CAR_ID}已到达约定地点，司机{DRIVER_NAME}{DRIVER_PHONE}， 感谢选择北京公务出行。"
														,SMSType.通知类短信, 
														$.add("CAR_ID", driver_card)
														 .add("DRIVER_NAME", driver_name)
														 .add("DRIVER_PHONE", driver_phone));
									 	}
									}
								}else{
									Logger.getRootLogger().error("公务叫车订单specialId["+specialCar.getCsscId()+"]获取订单详情失败！code="+code+",msg="+msg);
								}
							}else{
								Logger.getRootLogger().error("公务叫车订单specialId["+specialCar.getCsscId()+"]获取订单详情失败！");
							}
						}catch(Exception e){
							e.printStackTrace();
						}finally{
							node.setRunning(false);
							node.setLastTime(new Date());
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	/**
	 * "null"字符处理
	 * @param data
	 * @return
	 */
	public String ignoreNull(String data){
		if(data !=null && data.equals("null")){
			return null;
		}
		return data;
	}

	public ICsSpecialCarService getCsSpecialCarService() {
		return csSpecialCarService;
	}

	public void setCsSpecialCarService(ICsSpecialCarService csSpecialCarService) {
		this.csSpecialCarService = csSpecialCarService;
	}
	
}

/**
 * 更新定时任务
 * 
 * @author
 * 
 */
class LoadRecordTimerTask extends TimerTask {
	
	DidiOrderStateSyncTask syncTask;
	
	public LoadRecordTimerTask(DidiOrderStateSyncTask syncTask){
		this.syncTask = syncTask;
	}
	
	/**
	 * 此计时器任务要执行的操作。
	 */
	public void run() {
		try {
			syncTask.loadRecord();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

/**
 * 更新定时任务
 * 
 * @author
 * 
 */
class SyncTimerTask extends TimerTask {
	
	DidiOrderStateSyncTask syncTask;
	
	public SyncTimerTask(DidiOrderStateSyncTask syncTask){
		this.syncTask = syncTask;
	}
	
	/**
	 * 此计时器任务要执行的操作。
	 */
	public void run() {
		try {
			syncTask.sync();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
