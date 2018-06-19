package com.ccclubs.action.app.official.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.WeixinHelper.WxTemplateMsgType;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsState;
import com.ccclubs.model.CsWxMt;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.util.mq.ICarStatus;
import com.ccclubs.util.mq.entity.CarStatusII;
import com.ccclubs.util.mq.entity.CarStatusIV;
import com.lazy3q.web.helper.$;

/**
 * 车辆状态消费者
 */
public class CarStatusConsumer extends Thread{
	final ICsCarService csCarService = $.GetSpringBean("csCarService");

	final ICsStateService csStateService = $.GetSpringBean("csStateService");

	final ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
	
	final static List<Event> events = new ArrayList<Event>();
	
	public static AtomicBoolean init = new AtomicBoolean(false);				//是否已启动实例
	
	final static Logger log = Logger.getRootLogger();
	
    BlockingQueue<ICarStatus> carStatusQueue;
    
    public Map<String, CsCar> carsmap = new ConcurrentHashMap<String, CsCar>();
    
    public CarStatusConsumer(BlockingQueue<ICarStatus> carStatusQueue){
    	this.carStatusQueue = carStatusQueue;
    }

	@Override
	public void run() {
		try {
			
			if(init.get()){
				return;
			}
			init.set(true);
			
			List<CsCar> carList = csCarService.getCsCarList($.Map(), Integer.MAX_VALUE);
			if(carList!=null){
				for(CsCar car : carList){
					carsmap.put(car.getCscNumber(), car);
				}
			}
			
				while(true){
					try{
						ICarStatus carStatusNode = carStatusQueue.take();
						if(carStatusNode instanceof CarStatusII){					//第二版汽油车
							CarStatusII carStatus= (CarStatusII)carStatusNode;
							CsCar car = carsmap.get(carStatus.getCarNum());
							if(car == null){
								car = csCarService.getCsCar($.add(CsCar.F.cscNumber, carStatus.getCarNum()));
								if(car !=null)carsmap.put(car.getCscNumber(), car);
							}
							if(car == null){
								log.error("未知的车牌号："+carStatus.getCarNum());
								continue;
							}
							
						}else if(carStatusNode instanceof CarStatusIV){				//北汽鹏龙版电动车
							CarStatusIV carStatus= (CarStatusIV)carStatusNode;
							
							CsCar car = carsmap.get(carStatus.getCarNum());
							if(car == null){
								car = csCarService.getCsCar($.add(CsCar.F.cscNumber, carStatus.getCarNum()));
								if(car !=null)carsmap.put(car.getCscNumber(), car);
							}
							if(car == null){
								log.error("未知的车牌号："+carStatus.getCarNum());
								continue;
							}
							
							CsState state = csStateService.getCsState($.add(CsState.F.cssNumber, car.getCscNumber()));
							if(state == null){
								state = new CsState();
							}
							
							if(carStatus.mSOC < 30){
								if(StringUtils.isEmpty(state.getCssEvBattery()) || (Integer.valueOf(state.getCssEvBattery()) > 30)){
									if(state.getCssEvBattery()!=null && Integer.valueOf(state.getCssEvBattery())!=carStatus.mSOC){
										CsOrder order = csOrderService.getCsOrder($.add("definex", " cso_status in (0,1) and cso_car = '"+car.getCscId()+"'"));
										if(order !=null){
											//给使用中的用户发短信提醒
											Long member = order.getCsoPayMember();
											UtilHelper.sendSms(car.getCscHost(), CsMember.get(member).getCsmMobile(), "尊敬的用户，您正在使用的车辆"+car.getCscCarNo()+"电量过低，请及时充电！", SMSType.通知类短信 );
										}else{
											//给管理员推送消息提醒
											Long noticeMember = Long.valueOf($.config("noticeMember"));
											CsMember member = CsMember.get(noticeMember);
											//发送微信消息
											
											new CsWxMt(
													car.getCscHost()//城市 [非空]
												 	,(short)1//平台类型 [非空]
												 	,member.getCsmWeixinFlag()//三方标识
												 	,null//关联会员
												 	,WxTemplateMsgType.一般通知.name()//分类标识
												 	,(short)3//内容类型 [非空]
												 	,null//关联对象
												 	,"车辆:"+car.getCscCarNo()+"电量过低，请及时处理。"//消息内容
												 	,null//关联上行
												 	,null//发送时间
												 	,0//发送状态
												 	,0//成功状态
												 	,new Date()//添加时间 [非空]
												 	,(short)0//数据状态 [非空]
												 ).save();	
										}
									}
								}
							}
						}
					}catch(Exception e){
						log.error(e);
					}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
}
