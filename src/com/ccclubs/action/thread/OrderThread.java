package com.ccclubs.action.thread;

import java.util.Date;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.helper.EventHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsServAlarm;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class OrderThread extends Thread {
	
	ICommonDisposeService commonDisposeService;	
	ICsOrderService csOrderService;

	@Override
	public void run() {	
		
		while(true){
			
			int count = 0;
			
			//报警提醒 用户还车
			try {
				count += scanCarAlsoIn();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				new ErrorException("THREAD_ERROR","扫描已还车订单时出错",e);
			} 
			
			//扫描需要结算的订单
			try{
				count += scanNeedSettleOrders();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","线程处理订单时出错",ex);
			}
			
			
			
			//扫描25分钟需要还车的订单，并发送短信
			try{
				count += scanNeedRetOrders();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","线程处理订单时出错",ex);
			}			
			
			//扫描订单簇，当前时间已经超过订单开始时间并且订单状态仍是已预订（0）
			try {
				//count += scanClusterOrders();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				new ErrorException("THREAD_ERROR","线程处理订单时出错",e);
			}
			
			try {
				if(count==0)
					Thread.sleep(5000);
				else
					Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 扫描已还车订单
	 * @return
	 */
	final long ALARM_TYPE_ALSO_IN = 4;
	final String content = "%s网点%s车辆已还车，请及时检查！";
	private int scanCarAlsoIn() {
		// TODO Auto-generated method stub
		CsOrder csOrder=csOrderService.getCsOrder(Lazy.add("csoStatus", 2));
		if(csOrder!=null){
			CsServAlarm alarm_also_in = CsServAlarm.Get($.add(CsServAlarm.F.cssaStatus, 1)
					.add(CsServAlarm.F.cssaRelate, "CsCar@"+csOrder.getCsoCar())
					.add(CsServAlarm.F.cssaType, ALARM_TYPE_ALSO_IN));
			if(alarm_also_in == null){
				new CsServAlarm(csOrder.getCsoHost(), csOrder.getCsoOutletsRet(), ALARM_TYPE_ALSO_IN, "用户还车", String.format(content, csOrder.getCsoOutletsRet$(),csOrder.get$csoCar().getCscCarNo$()), "CsCar@"+csOrder.getCsoCar(), null, (short)1, null, new Date(), new Date()).save();
			}
		}
		return 0;
	}
	
	DateUtil dateUtil = new DateUtil();
	//扫描订单簇，当前时间已经超过订单开始时间并且订单状态仍是已预订（0）直接结算
	private int scanClusterOrders() {
		// TODO Auto-generated method stub
		Date date = new Date();
		final CsOrder csOrder=csOrderService.getCsOrder(
				Lazy.add("csoStatus", 0)
				.add("csoFlag", 3)
				.add("definex", " ('"+dateUtil.dateToString(date, null)+"'>cso_finish_time) ")
			);
		if(csOrder != null){
			commonDisposeService.executeSettleOrder(csOrder.getCsoId(), date, null, null, From.APP, null);
			return 1;
		}else
			return 0;
	}

	/**
	 * 扫描25分钟需要还车并且未发送催车短信的订单，并发送短信
	 * @return
	 */
	private int scanNeedRetOrders() {

		Date latter25 = new Date(new Date().getTime()+(SYSTEM.MINUTE*25));//25分钟后的时间
		
		final CsOrder csOrder=csOrderService.getCsOrder(
				Lazy.add("csoStatus", 1)
				.add("csoFinishTimeEnd", latter25)
				.add("definex", "cso_mask&1=0")
			);
		
		if(csOrder!=null){
			try{
				UtilHelper.sendTemplateSMS(csOrder.getCsoHost(),"RET_REMIND_ORDER", 
					(csOrder.get$csoUseMember()).getCsmMobile(), 
					"亲爱的车纷享会员，您当前车辆{number}应该在{time}还车，请及时还车或进行续定，以免产生延时费用。[车纷享]",
					SMSType.通知类短信,
					$.add("time", $.date(csOrder.getCsoFinishTime(), "MM月dd日HH时mm分"))
					.add("order", csOrder.getCsoId().toString())
					.add("number", csOrder.getCsoCarNumber().toString())
				);								
			}catch(Exception ex){
				ex.printStackTrace();
				Logger.getRootLogger().error(ex.getMessage(),ex);
			}finally{
				csOrderService.updateCsOrderByConfirm(
						$.add("definex", "cso_mask=cso_mask|1"),
						$.add("csoId", csOrder.getCsoId()).add("confirm", 1)
					);
			}
			return 1;
		}else
			return 0;
	}

	/**
	 * 扫描需要处理的订单 (状态为已还车2)
	 */
	private int scanNeedSettleOrders() {		
		final CsOrder csOrder=csOrderService.getCsOrder(Lazy.add("csoStatus", 2));
		if(csOrder!=null){
			try{
				//如果是汽油车，并且油里程小于1
				if(csOrder.getCsoFuel().doubleValue()<1 && csOrder.getCsoElectric().doubleValue()<1){					
					csOrderService.updateCsOrder$NotNull(
						new CsOrder()
						.csoId(csOrder.getCsoId())
						.csoStatus((short)5)
						.csoRemark("该订单没有里程数，请检查系统或设备状态，如有问题请及时联系技术部")
					);
					return 1;
				}
				
				//订单时长（小时）
				double hours = Math.abs(csOrder.getCsoStartTime().getTime()-csOrder.getCsoFinishTime().getTime())/(double)($.TIME.HOUR.time);
				double speed = ($.or(csOrder.getCsoElectric(),0d)+$.or(csOrder.getCsoFuel().doubleValue(), 0d))/hours;
				$.trace("##############################",speed,"##############################");
				if(speed > 70d){
					csOrderService.updateCsOrder$NotNull(
						new CsOrder()
						.csoId(csOrder.getCsoId())
						.csoStatus((short)5)
						.csoRemark("平均时速超70，可能里程出错或结算时间出错，如有问题请及时联系技术部")
					);
					return 1;
				}
				
				if(csOrder.getCsoRetTime()==null){
					csOrderService.updateCsOrder$NotNull(
						new CsOrder()
						.csoId(csOrder.getCsoId())
						.csoStatus((short)5)
						.csoRemark("该订单没有还车时间，请检查系统或设备状态，如有问题请及时联系技术部")
					);
					return 1;
				}
				if(csOrder.getCsoTakeTime()==null){
					csOrder.setCsoTakeTime(csOrder.getCsoStartTime());
				}
				if(Math.abs(csOrder.getCsoTakeTime().getTime()-csOrder.getCsoStartTime().getTime())>SYSTEM.DAY){
					csOrderService.updateCsOrder$NotNull(
						new CsOrder()
						.csoId(csOrder.getCsoId())
						.csoStatus((short)5)
						.csoRemark("订单的取车时间与开始时间误差超过一天，请检查系统或设备状态，如有问题请及时联系技术部")
					);
					return 1;
				}
				
				if(Math.abs(csOrder.getCsoRetTime().getTime()-csOrder.getCsoFinishTime().getTime())>SYSTEM.DAY){
					csOrderService.updateCsOrder$NotNull(
						new CsOrder()
						.csoId(csOrder.getCsoId())
						.csoStatus((short)5)
						.csoRemark("订单的还车时间与结束时间误差超过一天，请检查系统或设备状态，如有问题请及时联系技术部")
					);
					return 1;
				}
				
				if(csOrder.getCsoLongOrder()!=null && csOrder.getCsoLongOrder()==true){
					csOrderService.updateCsOrder$NotNull(
							new CsOrder()
							.csoId(csOrder.getCsoId())
							.csoStatus((short)5)
							.csoRemark("长单需要人工结算")
						);					
				}else{
					commonDisposeService.executeSettleOrder(csOrder.getCsoId(),null,null,null,From.后台,null);
				}
				
				//是否异地还车订单
				Boolean isAB = csOrder.getCsoOutlets().longValue()!=csOrder.getCsoOutletsRet().longValue();
				
				EventHelper.postEvent(
						csOrder.getCsoHost(),
						EventHelper.EventType.valueOf("会员"+(isAB?"异地":"")+"还车提醒"),
						"会员("+csOrder.getCsoUseMember$()+")"+(isAB?"异地":"")+"还车提醒",
						csOrder.getCsoId(),
						CsOrder.class
					);
				
			}catch(Exception ex){
				ex.printStackTrace();
				Logger.getRootLogger().error(ex.getMessage(),ex);
				csOrderService.updateCsOrder$NotNull(
					new CsOrder()
					.csoId(csOrder.getCsoId())
					.csoStatus((short)5)
					.csoRemark("系统自动处理订单时出错 - "+ex.getMessage())
				);
			}
			return 1;
		}else
			return 0;
	}
	
	
	
	

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICommonDisposeService getCommonDisposeService() {
		return commonDisposeService;
	}

	public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
		this.commonDisposeService = commonDisposeService;
	}

}
