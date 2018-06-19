package com.ccclubs.service.common.impl;

import java.util.Date;
import java.util.List;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarOn;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsScheduling;
import com.lazy3q.web.helper.$;

/**
 * 车辆调度服务
 * @author uiu
 *
 */
public class SchedulingService {
	
	public enum DispatchType{
		车辆投放,定单调度,干预调度
	}
	
	public static void scheduling(final Long car,final Long fromOutlets,final Long toOutlets,final Date outTime,final Date inTime,final DispatchType dispatchType,final Long order, final String remark){

		new Thread(){
			public void run(){
				CsCar csCar = CsCar.get(car);
				CsOrder csOrder = order==null?null:CsOrder.get(order);
				//调出记录 
				new CsScheduling(
					csCar.getCscHost()//城市 [非空]
				 	,fromOutlets//网点 [非空]
				 	,csCar.getCscId()//车辆 [非空]
				 	,outTime//时间 [非空]
				 	,(short)dispatchType.ordinal()//类型 [非空]
				 	,(short)1//方向 [非空]
				 	,remark//说明 [非空]
				 	,order//订单
				 	,csOrder==null?("SrvUser@"+LoginHelper.getLoginId()):("CsMember@"+csOrder.getCsoUseMember())//操作人
				 	,order==null?(short)1:0//状态 [非空]
				).save();
				
				//调入记录
				new CsScheduling(
					csCar.getCscHost()//城市 [非空]
				 	,toOutlets//网点 [非空]
				 	,csCar.getCscId()//车辆 [非空]
				 	,inTime//时间 [非空]
				 	,(short)dispatchType.ordinal()//类型 [非空]
				 	,(short)0//方向 [非空]
				 	,remark//说明 [非空]
				 	,order//订单
				 	,csOrder==null?("SrvUser@"+LoginHelper.getLoginId()):("CsMember@"+csOrder.getCsoUseMember())//操作人
				 	,order==null?(short)1:0//状态 [非空]
				).save();
				
				//更新原网点车辆数
				com.ccclubs.service.common.impl.SchedulingService.synOutletsCarCount(fromOutlets);
				//更新目标网点车辆数
				com.ccclubs.service.common.impl.SchedulingService.synOutletsCarCount(toOutlets);	
			}
		}.start();
			
	}
	
	
	/**
	 * 根据订单撤销车辆调度
	 * @param order
	 */
	public static void cancelScheduling(final Long order){
		new Thread(){
			public void run(){
				for(CsScheduling csScheduling:CsScheduling.where().cssOrder(order).list(-1)){
					csScheduling.delete();
					//更新原网点车辆数
					com.ccclubs.service.common.impl.SchedulingService.synOutletsCarCount(csScheduling.getCssOutlets());
				}
			}
		}.start();
	}
	
	
	/**
	 * 根据订单撤销车辆调度
	 * @param order
	 */
	public static void finishScheduling(final Long order){
		new Thread(){
			public void run(){
				for(CsScheduling csScheduling:CsScheduling.where().cssOrder(order).list(-1)){
					new CsScheduling(csScheduling.getCssId()).cssStatus((short)1).update();
					com.ccclubs.service.common.impl.SchedulingService.synOutletsCarCount(csScheduling.getCssOutlets());
				}
			}
		}.start();
	}
	
	/**
	 * 同步网点车辆数
	 * @param outlets
	 */
	public static void synOutletsCarCount(final Long outlets){
		new Thread(){
			public void run(){
				int count = CsCar.where().cscOutlets(outlets).count().shortValue()//当前车辆所在的网点
				+CsOrder.where().csoOutlets(outlets).add("csoOutletsNot", "not").csoOutletsRet(outlets).definex("(cso_status<3 or cso_status=5)").count().shortValue();//加将要还到此网点的订单数
				new CsOutlets(outlets).csoCarS((short)count).update();
			}
		}.start();
	}
	
	/**
	 * 检查车辆、取还车网点，在某个时间段的取还车网点是否合法
	 * @param car_id
	 * @param outlets_get_id
	 * @param outlets_ret_id
	 * @param start
	 * @param finish
	 * @return
	 */
	public static String checkGetRetOutlets(Long car_id, Long outlets_get_id,Long outlets_ret_id, Date start, Date finish) {
		CsCar csCar = CsCar.get(car_id);
		CsOutlets csOutlets = CsOutlets.get(outlets_ret_id);
		
		/*
		//取比开始时间是的最近一条调入记录
		CsScheduling inScheduling = CsScheduling.where().cssCar(car_id).cssDir((short)0).css.cssTimeEnd(start).add("desc", "css_time").get();		
		if(inScheduling!=null && inScheduling.getCssStatus().shortValue()==0 && inScheduling.getCssOutlets().longValue()!=outlets_get_id.longValue())
			return "车辆"+csCar.getKeyValue()+"在"+$.date(start, "yyyy-MM-dd HH:mm")+"不在"+CsOutlets.getKeyValue(outlets_get_id)+"网点";
		
		//取比结束时间后的调出记录
		CsScheduling inScheduling = CsScheduling.where().cssCar(car_id).cssDir((short)0).cssTimeEnd(start).add("desc", "css_time").get();		
		if(inScheduling!=null && inScheduling.getCssStatus().shortValue()==0 && inScheduling.getCssOutlets().longValue()!=outlets_get_id.longValue())
			return "车辆"+csCar.getKeyValue()+"在"+$.date(start, "yyyy-MM-dd HH:mm")+"不在"+CsOutlets.getKeyValue(outlets_get_id)+"网点";
		
		
		//查询起始时间内该车有没有调动记录
		List<CsScheduling> schedulings = CsScheduling.where().cssCar(car_id).cssTimeStart(start).cssTimeEnd(finish).list(50);
		for(CsScheduling scheduling:schedulings){
			if(scheduling.getCssStatus()>0)
				continue;
			CsOrder order = scheduling.get$cssOrder();
			if(order!=null && !$.idin("4,3,7", order.getCsoStatus()))
				return "车辆"+csCar.getKeyValue()+"在"+$.date(start, "yyyy-MM-dd HH:mm")+"到"+$.date(finish, "yyyy-MM-dd HH:mm")+"有订单或调度";
		}
		*/
		
		//如果取车网点和还车网点一样，则直接返回OK
		if(outlets_get_id.longValue()==outlets_ret_id.longValue())
			return null;
		
		//判断原网点是否支持借出
		if(!CsOutlets.get(outlets_get_id).getCsoCanA())return "网点"+CsOutlets.getKeyValue(outlets_get_id)+"不支持车辆借出";
		//判断目标网点是否支持还入
		if(!CsOutlets.get(outlets_ret_id).getCsoCanB())return "网点"+CsOutlets.getKeyValue(outlets_ret_id)+"不支持车辆借出";
		
		
		//检查目标网点在目标时间内的车位数是否够用
		Long inCount = CsScheduling.where().cssOutlets(outlets_ret_id).cssDir((short)0).count();//调入数
		Long outCount = CsScheduling.where().cssOutlets(outlets_ret_id).cssDir((short)1).count();//调出数		
		if((csOutlets.getCsoPositionS()-(inCount-outCount))<=0)
			return "目标网点"+csOutlets.getKeyValue()+"在"+$.date(finish, "yyyy-MM-dd HH:mm")+"无可用车位";
		
		return null;
	}
	
	
	
	
	
	
	
	
	/**
	 * 车辆上下线操作
	 * @param carId
	 * @param s
	 * @param string
	 * @param password
	 * @return
	 */
	public static String onlineCar(Long id, Short status, String remark,String password) {
		
		CsCar oldCsCar = CsCar.get(Long.valueOf(id));
		if(oldCsCar.getCscStatus().shortValue()==status.shortValue()){
			return (oldCsCar.getCscNumber()+"状态无更改");
		}
		CsCar updateCsCar = new CsCar(id);
		updateCsCar.setCscStatus(status);
		
		if(status.shortValue()==2){//下线
			updateCsCar.setCscOnPsd(password);
		}else{//上线
			//如果密码不对，则不更新
			if(($.empty(password) && $.empty(oldCsCar.getCscOnPsd())) || $.equals(password,oldCsCar.getCscOnPsd())){
				updateCsCar.setCscOnPsd("");//清除密码
			}else{
				return (oldCsCar.getCscNumber()+"上线失败：密码错误");
			}
		}
		
		updateCsCar.update();
		
		if(status.shortValue()==2){//下线
			CsCarOn csCarOn = CsCarOn.Get(
				$.add(CsCarOn.F.cscoCar, updateCsCar.getCscId())
				.add(CsCarOn.F.cscoStatus, 0)
				.add("desc", "csco_on_time")
			);
			if(csCarOn==null){
				csCarOn = new CsCarOn(
					oldCsCar.getCscHost()//域 [非空]
				 	,oldCsCar.getCscId()//所属车辆 [非空]
				 	,null//上线操作人
				 	,null//下线操作人
				 	,null//上线时间 [非空]
				 	,null//下线时间
				 	,0l//运营时长
				 	,null//下线备注
				 	,(short)0//状态 [非空]
				 ,null).save();
			}
			csCarOn.setCscoOffTime(new Date());
			csCarOn.setCscoOffUser(LoginHelper.getLoginId());
			if(csCarOn.getCscoOnTime()!=null)
				csCarOn.setCscoLong((csCarOn.getCscoOffTime().getTime()-csCarOn.getCscoOnTime().getTime())/(SYSTEM.MINUTE));
			csCarOn.setCscoStatus((short)1);
			csCarOn.setCscoRemark(remark);
			csCarOn.update();
			
			return (oldCsCar.getCscNumber()+"下线成功");
		}else{
			new CsCarOn(
				oldCsCar.getCscHost()//域 [非空]
			 	,oldCsCar.getCscId()//所属车辆 [非空]
			 	,LoginHelper.getLoginId()//上线操作人
			 	,null//下线操作人
			 	,new Date()//上线时间 [非空]
			 	,null//下线时间
			 	,0l//运营时长
			 	,null//下线备注
			 	,(short)0//状态 [非空]
			 ,remark).save();
			 return (oldCsCar.getCscNumber()+"上线成功");
		}
	}
	
	/**
	 * 车辆上下线操作
	 * @param carId
	 * @param s
	 * @param string
	 * @param password
	 * @param property 下线原因
	 * @param loginId 上下线操作人员
	 * @return
	 */
	public static String onlineCar(Long id, Short status, String remark,String password,Long property,Long loginId) {
		
		CsCar oldCsCar = CsCar.get(Long.valueOf(id));
		if(oldCsCar.getCscStatus().shortValue()==status.shortValue()){
			return (oldCsCar.getCscNumber()+"状态无更改");
		}
		CsCar updateCsCar = new CsCar(id);
		updateCsCar.setCscStatus(status);
		
		if(status.shortValue()==2){//下线
			updateCsCar.setCscOnPsd(password);
		}else{//上线
			//如果密码不对，则不更新
			if(($.empty(password) && $.empty(oldCsCar.getCscOnPsd())) || $.equals(password,oldCsCar.getCscOnPsd())){
				updateCsCar.setCscOnPsd("");//清除密码
			}else{
				return (oldCsCar.getCscNumber()+"上线失败：密码错误");
			}
		}
		
		updateCsCar.update();
		
		if(status.shortValue()==2){//下线
			CsCarOn csCarOn = CsCarOn.Get(
				$.add(CsCarOn.F.cscoCar, updateCsCar.getCscId())
				.add(CsCarOn.F.cscoStatus, 0)
				.add("desc", "csco_on_time")
			);
			if(csCarOn==null){
				csCarOn = new CsCarOn(
					oldCsCar.getCscHost()//域 [非空]
				 	,oldCsCar.getCscId()//所属车辆 [非空]
				 	,null//上线操作人
				 	,null//下线操作人
				 	,null//上线时间 [非空]
				 	,null//下线时间
				 	,0l//运营时长
				 	,null//下线备注
				 	,(short)0//状态 [非空]
				 ,null).save();
			}
			csCarOn.setCscoOffTime(new Date());
			csCarOn.setCscoOffUser(loginId);
			if(csCarOn.getCscoOnTime()!=null)
				csCarOn.setCscoLong((csCarOn.getCscoOffTime().getTime()-csCarOn.getCscoOnTime().getTime())/(SYSTEM.MINUTE));
			csCarOn.setCscoStatus((short)1);
			csCarOn.setCscoRemark(remark);
			csCarOn.setCscoProperty(property);
			csCarOn.update();
			
			return (oldCsCar.getCscNumber()+"下线成功");
		}else{
			CsCarOn carOn = new CsCarOn(
				oldCsCar.getCscHost()//域 [非空]
			 	,oldCsCar.getCscId()//所属车辆 [非空]
			 	,loginId//上线操作人
			 	,null//下线操作人
			 	,new Date()//上线时间 [非空]
			 	,null//下线时间
			 	,0l//运营时长
			 	,null//下线备注
			 	,(short)0//状态 [非空]
			 ,"");
			
			carOn.cscoOnRemark(remark).save();
			 return (oldCsCar.getCscNumber()+"上线成功");
		}
	}
	
	
	
	
	/**
	 * 获取网点某个时间点的可用车位数
	 * @param outlets
	 * @param time
	 * @return
	 */
	public static int getOutletsParkLotCount(Long outlets,Date time){
		
		return 0;
	}
	
	/**
	 * 获取一些网点某个时间点可用车位数
	 * @param ids
	 * @param time
	 * @return
	 */
	public static List getOutletsParkLotCount(Long[] ids,Date time){
		
		return null;
	}
	
	/**
	 * 获取所有网点某个时间点可用车位数
	 * @param outlets
	 * @param time
	 * @return
	 */
	public static List getAllOutletsParkLotCount(Date time){
		
		return null;
	}
	
	
	
	/**
	 * 获取车辆几天内的时间轴
	 * @param car
	 * @return
	 */
	public static List getCarTimeLine(Long car,int dayCount){
		
		return null;
	}
	/**
	 * 获取车辆某个时间段的时间轴
	 * @param car
	 * @param start
	 * @param finish
	 * @return
	 */
	public static List getCarTimeLine(Long car,Date start,Date finish){
		
		return null;
	}


	





	

}
