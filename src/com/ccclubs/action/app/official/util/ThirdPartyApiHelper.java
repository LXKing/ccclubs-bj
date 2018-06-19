package com.ccclubs.action.app.official.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ccclubs.action.app.official.util.ApiBase.Type;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsSpecialModel;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.$;

/**
 * 公务专车第三方接入接口
 * 
 * @author joel
 *
 */
public class ThirdPartyApiHelper{
	
	static final Logger log = Logger.getLogger("plapp");
	
	/**
	 * @param specialCar
	 * @param change  是否切换渠道
	 * @return
	 */
	public static void sendCallCar(CsSpecialCar specialCar, boolean change){
		if(specialCar.getCsscStatus().intValue()>3)return;
		if(specialCar.getCsscResponse()!=null && specialCar.getCsscResponse().equals(ApiBase.ONCALLING))return;
		
//		if(ApiBase.useYidao){
//			if(change){
//				CsSpecialCar specialCar_change = new CsSpecialCar(specialCar.getCsscId());
//				CsSpecialModel carmodel = CsSpecialModel.get(specialCar.getCsscCarModel());
//				specialCar_change.setCsscCarModel(ApiBase.changeCarModel(carmodel));
//				if(specialCar.getCsscThird() == ApiBase.THIRD_DIDI){
//					specialCar_change.setCsscThird(Long.valueOf(ApiBase.THIRD_YIDAO));
//					
//				}else if(specialCar.getCsscThird() == ApiBase.THIRD_YIDAO){
//					specialCar_change.setCsscThird(Long.valueOf(ApiBase.THIRD_DIDI));
//				}else{
//					specialCar_change.setCsscThird(Long.valueOf(ApiBase.THIRD_DIDI));
//				}
//				
//				specialCar_change.setCsscUpdateTime(new Date());
//				specialCar_change.update();
//			}
//		}
		
		String channel = specialCar.getCsscThird() == ApiBase.THIRD_DIDI ? "滴滴" : "易到";
		log.info("订单：["+specialCar.getCsscId()+"]发起叫车，当前渠道：["+channel+"]，切换渠道：["+(change?"是":"否")+"]，当前三方订单号：["+specialCar.getCsscThirdNo()+"]");
		
		if(specialCar.getCsscStartTime().getTime() - new Date().getTime() > 1000 * 60 * 30){
			createOrder(specialCar.getCsscMobile(), specialCar.getCsscId(), Type.ORDER);
		}else if(specialCar.getCsscStartTime().getTime() - new Date().getTime() <= 1000 * 60 * 15){
			createOrder(specialCar.getCsscMobile(), specialCar.getCsscId(), Type.REAL_TIME);
		}
	}
	
	/**
	 * 创建订单
	 */
	public static Map<String, Object> createOrder(String memberMobile, Long specialCarId, Type type){
		if(specialCarId == null)return null;
		CsSpecialCar specialCar = CsSpecialCar.get(specialCarId);
		if(specialCar==null)return null;
		if(specialCar.getCsscStatus().intValue()>3)return null;
		if(specialCar.getCsscResponse()!=null && specialCar.getCsscResponse().equals(ApiBase.ONCALLING))return null;
		
		Map<String, Object> callOrder = null;
		if(specialCar.getCsscThird() == ApiBase.THIRD_DIDI){			//1:滴滴专车 
			DidiApi didiApi = new DidiApi();
			callOrder = didiApi.createOrder(specialCar, type); 
			Object code = callOrder.get("errno");
			Object msg = callOrder.get("errmsg");
			Map data = (Map)callOrder.get("data");
			if(Double.valueOf(code.toString()).intValue()==0 && msg.equals("SUCCESS")){
				Map diorder = (Map)data.get("order");
				specialCar.setCsscThirdNo((new BigDecimal(diorder.get("id").toString())).toPlainString());
				CsSpecialModel model = DidiApi.getCarSpecialModel(specialCar);
				if(model!=null){
					specialCar.setCsscCarModel(model.getCssmId());
				}
				specialCar.setCsscResponse(ApiBase.ONCALLING);
				specialCar.setCsscStatus((short)2);
				specialCar.update();
				return callOrder;
			}else{
				Logger.getRootLogger().error("公务叫车滴滴订单specialId:["+specialCar.getCsscId()+"]失败！"+ "错误信息："+code+"-"+msg);
			}
		}else if(specialCar.getCsscThird() == ApiBase.THIRD_YIDAO){		//2:易到专车
			YidaoApi yidaoApi = new YidaoApi();
			callOrder =  yidaoApi.createOrder(specialCar, type);
			Object code = callOrder.get("code");
			
			if(Double.valueOf(code.toString()).intValue()==200){
				Map result = (Map)callOrder.get("result");
				Object callOrderId = result.get("order_id");
				CsSpecialModel model = YidaoApi.getCarSpecialModel(specialCar);
				if(model!=null){
					specialCar.setCsscCarModel(model.getCssmId());
				}
				specialCar.setCsscResponse(ApiBase.ONCALLING);
				specialCar.setCsscThirdNo((new BigDecimal(callOrderId.toString())).toPlainString());
				specialCar.setCsscStatus((short)2);
				specialCar.update();
				return callOrder;
			}else{
				Logger.getRootLogger().error("公务叫车易到订单specialId:["+specialCar.getCsscId()+"]失败！"+ "错误信息："+code);
			}
		}
		
		//叫车失败
		if(specialCar.getCsscStatus().intValue()!=2){
			
			//首次叫车失败-》重复叫车失败
			if(StringUtils.isEmpty(specialCar.getCsscResponse())){
				specialCar.setCsscResponse(ApiBase.CALL_FAIL);
			}else{
				specialCar.setCsscResponse(ApiBase.RECALL_FAIL);
			}
			
			specialCar.setCsscUpdateTime(new Date());
			specialCar.update();
		}else{
			//超时的
			specialCar.setCsscResponse(ApiBase.RECALL_FAIL);
			specialCar.setCsscUpdateTime(new Date());
			specialCar.update();
		}
		
		return callOrder;
	}

	/**
	 * 主动取消订单
	 * @param specialCarId
	 */
	public static boolean cancelOrder(Long specialCarId) {
		
		boolean flag = false;
		if(specialCarId == null)return false;
		CsSpecialCar specialCar = CsSpecialCar.get(specialCarId);
		
		//尚未叫单成功
		if(StringUtils.isEmpty(specialCar.getCsscThirdNo())){
			specialCar.setCsscStatus((short)5);
			specialCar.setCsscUpdateTime(new Date());
			specialCar.update();
			flag = true;
		}else{
			if(specialCar.getCsscThird() == ApiBase.THIRD_DIDI){
				DidiApi didiApi = new DidiApi();
				Map<String, Object> result = didiApi.cancelOrder(specialCar.getCsscMobile(), specialCar.getCsscThirdNo(), true);
				Object code = result.get("errno");
				Object msg = result.get("errmsg");
				if(Double.valueOf(code.toString()).intValue()==0 && msg.equals("SUCCESS")){
					Map data = (Map)result.get("data");
					if(data!=null){
						Object cost = data.get("cost");
						if(cost!=null){
							specialCar.setCsscThirdFee(Double.valueOf(cost.toString()));
						}
					}
					specialCar.setCsscStatus((short)5);
					specialCar.setCsscUpdateTime(new Date());
					specialCar.update();
					flag = true;
				}
			}else if(specialCar.getCsscThird() == ApiBase.THIRD_YIDAO){	//2:易到专车
				YidaoApi yidaoApi = new YidaoApi();
				Map<String, Object> result = yidaoApi.cancelOrder(specialCar.getCsscThirdNo(), "58", "");	//58 	其它
				if(result!=null){
					Object code = result.get("code");
					if(Double.valueOf(code.toString()).intValue()==200){
						specialCar.setCsscStatus((short)5);
						specialCar.update();
						flag = true;
					}
				}
			}
		}
		
		if(flag && specialCar.getCsscStatus().intValue() == 5){
			CsUnitPerson person = CsUnitPerson.get(specialCar.getCsscPerson());
			try{
				Map<String, String> extra = new HashMap<String, String>();
				extra.put("flag", "order-cancel");
				extra.put("data", $.json($.add("unitOrderId", specialCar.getCsscUnitOrder())
										  .add("specialId", specialCar.getCsscId())));
				JpushClientHelper.sendAlertWithTag(person.getCsupMember().toString(), "叫车提醒", "您发起的从"+specialCar.getCsscStartAddr()+"到"+specialCar.getCsscFinishAddr()+"的公务叫车订单已取消！", extra, JpushConfig.OFC_APP);
				UtilHelper.sendTemplateSMS(specialCar.getCsscHost(),"CANCEL_SPECIALORDER", specialCar.getCsscMobile(), "您预订的特急专送服务已取消，期待下次为您服务。", SMSType.通知类短信 , new HashMap<String, String>());
			}catch(Exception e){}
		}
		return flag;
		
	}
	
}
