package com.ccclubs.action.api.scripts;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ccclubs.action.api.model.Result;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.admin.ICsOrderService;
import com.lazy3q.web.helper.$;

/**
 * 远程控制操作指令
 * 
 * @author 
 *
 */
public class RemoteCommandScript extends BaseScript{
	
	public static Result validCar(long carId){
		Result rs = new Result();
		if(carId != 1810){
			rs.setResult(false);
			rs.setCode(1810);
		}
		rs.setResult(true);
		return rs;
	}
		
	/**
	 * 远程操作
	 * 
	 * @param carId
	 * @param conType 1取车/开门、2关门、3鸣笛、4远程还车
	 * @return
	 */
	public static Result remoteOperate(String orderIdStr, String conType){
		Result rs = new Result();
		
		try{
			
			Long orderId = Long.valueOf(orderIdStr);
			
			rs = validRemote(orderId, conType);
			if(!rs.getResult()){
				return rs;
			}
			
			CsOrder csOrder = (CsOrder)rs.getData();
			if (conType.equals("1")) { //取车/开门
				if (csOrder.getCsoStatus().equals(new Short("0"))) {	// 已预订状态-远程取车
					WeixinHelper.remoteController(csOrder.getCsoCar(), "6", getOauth());
				} else if (csOrder.getCsoStatus().equals(new Short("1"))) {// 使用中状态-远程开门
					WeixinHelper.remoteController(csOrder.getCsoCar(), "0", getOauth());
				} else {
					//非法操作
					return new Result(false, 106);
				}
			} else if (conType.equals("2")) {// 关门
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "1", getOauth());
				} else {
					//非法操作
					return new Result(false, 106);
				}
			} else if (conType.equals("3")) {// 鸣笛
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "17", getOauth());
				} else {
					//非法操作
					return new Result(false, 106);
				}
			} else if (conType.equals("4")) {// 远程还车
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "7", getOauth());
				} else {
					//非法操作
					return new Result(false, 106);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
		
		rs.setResult(true);
		return rs;
	}
	
	/**
	 * 校验远程操作
	 * @return
	 */
	public static Result validRemote(Long orderId, String controllType) {
		Result rs = new Result();
		ICsOrderService	csOrderService = $.GetSpringBean("csOrderService");
		
		//用户没有登录
		if (getOauth() == null)
			return new Result(false, 100);
		
		if(orderId == null){
			return new Result(false, 101);
		}

		CsOrder csOrder = csOrderService.getCsOrderById(orderId);
		// 不存在定单或订单无效
		if (csOrder == null)
			return new Result(false, 101);
		
		// 支付会员对不上
		if (!csOrder.getCsoUseMember().equals(getOauth())) {
			return new Result(false, 102);
		}
		
		//判断订单开始时间：订单前10分钟才可以取车
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(csOrder.getCsoStartTime());
		calendar.add(Calendar.MINUTE, -10);
		long temp = calendar.getTimeInMillis() - new Date().getTime();
		if (temp > 0) {
			//订单尚未开始
			return new Result(false, 103);
		}

//		CsCar car = csOrder.get$csoCar();
//		rs = validCar(car.getCscId());
//		if(!rs.getResult()){
//			return rs;
//		}
		
		// 仅允许北京+电动车可操作
		//if (car.get$cscModel().getCscmType$().equals("电动车") && car.getCscHost() == 2) {
			if (!(csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1")))) {// System.currentTimeMillis()>csOrder.getCsoFinishTime().getTime()
				// 订单状态不为0和1
				return new Result(false, 104);
			} 
		//} else{
			//该车型不支持该操作
		//	return new Result(false, 105);
		//}
		rs.setData(csOrder).setResult(true);
		return rs;
	}
	
}
