package com.ccclubs.action.app.official.task;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.ccclubs.action.app.official.util.ApiBase;
import com.ccclubs.action.app.official.util.Reason;
import com.ccclubs.action.app.official.util.Env;
import com.ccclubs.action.app.official.util.EventBarrier;
import com.ccclubs.action.app.official.util.ThirdPartyApiHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.ccclubs.service.common.ICommonUnitService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.$;

/**
 * 专车订单扫描任务
 * 	1、把请求叫车失败的订单，发起重新叫车
 * 	2、严重超时仍未叫车成功的订单废弃
 *  3、重叫订单接单超时的订单，切换叫车渠道重新发起叫车
 * @author joel
 *
 */
public class OrdersScannerTask {
	
	public static AtomicBoolean init = new AtomicBoolean(false);				//是否已启动实例
	
	private ICsSpecialCarService csSpecialCarService;
	private ICommonUnitService commonUnitService;
	
	private boolean run;
	
	public void init(){
		
		if(init.get()){
			return;
		}
		
		if(! (Env.isTest() || Env.isOnline())){
			$.trace("非在线模式，放弃执行同步任务："+this.getClass().getName());
			return;
		}
		
		Timer timer = new Timer();
		long delay = 10000; 							//从接收到指令延迟十秒后开始执行
		LoadOrdersTimerTask task = new LoadOrdersTimerTask(this);
		timer.schedule(task, delay, 1000 * 60);			//一分钟同步一次
		
		init.set(true);
	}
	
	/**
	 * 执行
	 * 叫车执行流程：
	 * 			1、未叫车			-》叫车
	 * 			2、首次叫车失败		-》叫车
	 * 			3、叫车超时			-》改派渠道叫车    (在DidiOrderStateSyncTask或者ThirdParthCallbackAction中能收到超时的地方叫车)
	 * 			4、重新叫车失败		-》改派渠道叫车
	 */
	public synchronized void execute(){
		if(!isRun())setRun(true);
		try{
			//查出【审核通过】和【叫车成功】的订单
			List<CsSpecialCar> specialCarList = csSpecialCarService.getCsSpecialCarList(
						$.add("definex", "("+CsSpecialCar.C.csscStatus+" in (0,1,2))"), -1);
			if(!CollectionUtils.isEmpty(specialCarList)){
				for(CsSpecialCar specialCar : specialCarList){
					try{
						
						//把超出[叫车的开始时间]半个小时后仍然没有接单，主动取消订单(范围：0:待审核 1:已审核 2:已下单)
						if(new Date().getTime() - specialCar.getCsscStartTime().getTime()  > 1000 * 60 * 20){
							try{
								CsUnitPerson person = CsUnitPerson.get(specialCar.getCsscPerson());
								commonUnitService.executeCancelUnitOrder(person.getCsupInfo(), specialCar.getCsscUnitOrder(), "");
							}catch(Exception e){
								Logger.getRootLogger().error(e.getMessage(),e);
							}
							continue;
						}
						
						//【待审核】状态的订单
						if(specialCar.getCsscStatus().intValue() == 0){
							//订单创建时间已过去5分钟，过滤掉一创建立即发送短信的
							if(new Date().getTime() - specialCar.getCsscAddTime().getTime() > 1000 * 60 * 5){
								//距离订单开始时间小于半个小时的，仍未审核的，发送短信
								if(specialCar.getCsscStartTime().getTime() - new Date().getTime() < 1000 * 60 * 30){
									try{
										Reason event = Reason.距离订单开始时间小于半个小时的仍未审核的发送短信.set(specialCar.getCsscId());
										if(!EventBarrier.has(event)){
											EventBarrier.put(event, (specialCar.getCsscFinishTime().getTime()-new Date().getTime())/(1000)+30*1000);
											
											//给审核人发送短信
											CsUnitPerson person = CsUnitPerson.get(specialCar.getCsscPerson());
											CsUnitGroup group = CsUnitGroup.getCsUnitGroupById(person.getCsupGroup());
											CsUnitPerson auditPerson = CsUnitPerson.get(group.getCsugPerson());
											UtilHelper.sendTemplateSMS(person.getCsupHost(),
													"CALLCAR_AUDIT_NOTICE",
													auditPerson.get$csupMember().getCsmMobile(),
													"您有一笔公务叫车申请(订单号:{ORDER_ID})等待审批，请及时处理。"
														,SMSType.通知类短信, 
														$.add("ORDER_ID", specialCar.getCsscId()+""));
										}
									}catch(Exception e){e.printStackTrace();}
								}
							}
							continue;
						}
						
						//把【审核通过】待叫车的订单发起叫车
						if(StringUtils.isEmpty(specialCar.getCsscThirdNo()) && specialCar.getCsscStatus().intValue() == 1){
							//尚未叫车过
							if(specialCar.getCsscResponse() == null){
								ThirdPartyApiHelper.sendCallCar(specialCar, false);
							}else{
								//首次叫车失败，使用本渠道再次重叫
								if(specialCar.getCsscResponse()!=null && specialCar.getCsscResponse().equals(ApiBase.CALL_FAIL)){
									ThirdPartyApiHelper.sendCallCar(specialCar, false);
								}else{
									//否则切换渠道叫车
									ThirdPartyApiHelper.sendCallCar(specialCar, true);
								}
							}
						}else{
							//(超时)重叫失败的
							if(specialCar.getCsscResponse()!=null && specialCar.getCsscResponse().equals(ApiBase.RECALL_FAIL)){
								ThirdPartyApiHelper.sendCallCar(specialCar, true);
							}
						}
					}catch(Exception e){
						Logger.getRootLogger().error(e.getMessage(),e);
					}
				}
			}
		}catch(Exception e){
			Logger.getRootLogger().error(e.getMessage(),e);
		}finally{
			setRun(false);
		}
	}

	public ICsSpecialCarService getCsSpecialCarService() {
		return csSpecialCarService;
	}

	public void setCsSpecialCarService(ICsSpecialCarService csSpecialCarService) {
		this.csSpecialCarService = csSpecialCarService;
	}

	public ICommonUnitService getCommonUnitService() {
		return commonUnitService;
	}

	public void setCommonUnitService(ICommonUnitService commonUnitService) {
		this.commonUnitService = commonUnitService;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
	
}

/**
 * 更新定时任务
 * 
 * @author
 * 
 */
class LoadOrdersTimerTask extends TimerTask {
	
	OrdersScannerTask task;
	
	public LoadOrdersTimerTask(OrdersScannerTask task){
		this.task = task;
	}
	
	/**
	 * 此计时器任务要执行的操作。
	 */
	public void run() {
		try {
			if(!task.isRun()){
				task.execute();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

