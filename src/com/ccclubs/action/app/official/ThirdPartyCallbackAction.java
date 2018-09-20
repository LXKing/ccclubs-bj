package com.ccclubs.action.app.official;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jfree.util.Log;
import com.ccclubs.action.app.official.util.ApiBase;
import com.ccclubs.action.app.official.util.DidiApi;
import com.ccclubs.action.app.official.util.JpushClientHelper;
import com.ccclubs.action.app.official.util.JpushConfig;
import com.ccclubs.action.app.official.util.ThirdPartyApiHelper;
import com.ccclubs.action.app.official.util.YidaoApi;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRecord;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ICsItemService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.ccclubs.service.common.ICommonUnitService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.$;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

public class ThirdPartyCallbackAction {
    
    
	
	ICsSpecialCarService csSpecialCarService;
	
	ICommonUnitService commonUnitService;
	ICsMemberService csMemberService;
	ICsItemService csItemService;
	
	
	Logger thirdPartyCallbackAppLogger = Logger.getLogger("rentApp");

	/**
	 * 活动盒子用户抽奖和中奖回调
	 * **/
	public String winningActivity() {
	    HttpServletRequest request = ServletActionContext.getRequest();
	    String results=null;
	    InputStream is=null;
	    try {
            is = request.getInputStream();
            String requestString=IOUtils.toString(is);
            System.out.println(requestString);
            results = JSONObject.fromObject(requestString).getString("result");
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
	    //todo http请求头部加上一个 Signature 字段的处理
	    //Map map=$.getJson("result");
	    
	    thirdPartyCallbackAppLogger.info("result is : "+results);
	    //处理数据
	        //分离event与data
	  //处理data数据，反序列化对象
	    if (null==results||"".equals(results)) {
	        return $.SendHtml("SUCCESS", SYSTEM.UTF8);
	        
        }
	    JSONObject jsonObject=JSONObject.fromObject(results);
	    String event=jsonObject.getString("event");
	    if ("ATTEND".equals(event)||"INVITE".equals(event)) {//中将的event事件为ATTEND
            
            //处理是否中奖
            int winning=jsonObject.getJSONObject("data").getInt("winning");
            if (1!=winning) {
                //用户未中奖
                return $.SendHtml("SUCCESS", SYSTEM.UTF8);
            }
          //处理用户
            //匹配本地用户
            String user_type=jsonObject.getJSONObject("data").getJSONObject("customer").getString("user_type");
            if (!"app".equals(user_type)) {
              //用户app类型不匹配
                return $.SendHtml("SUCCESS", SYSTEM.UTF8);
            }
	       Long identity=jsonObject.getJSONObject("data").getJSONObject("customer").getLong("identity");//获取用户唯一标志,获得的是用户主键id
	       //JSONArray prizeJsonArray=jsonObject.getJSONObject("data").getJSONArray("prize");//获取奖品列表
	       CsMember csMember=csMemberService.getCsMemberById(identity);
	               //CsMember.Get($.add(CsMember.F.csmId, identity));//todo有查询bug
	       
	       if (null!=csMember) {
	           
	               JSONObject prizeJson=jsonObject.getJSONObject("data").getJSONObject("prize");
	               String activityName=jsonObject.getJSONObject("data").getJSONObject("activity").getString("name");
	               String prizeName=prizeJson.getString("name");//获取奖品名称
	               //用奖品名称匹配红包
	               
	             //处理奖品 循环
	               //匹配本地奖品
	               CsItem csItem=csItemService.getCsItem($.add(CsItem.F.csiType, 3).add(CsItem.F.csiTitle, prizeName));
	                       //todo有查询bug
	               if (null!=csItem) {
	                   //添加红包
	                   CsCoin csCoin=new CsCoin();
	                   csCoin.setCscAddTime(new Date(System.currentTimeMillis()));
	                   
	                   if (null!=activityName) {
	                       if (activityName.contains("签到")) {
	                           csCoin.setCscEnd(new Date(System.currentTimeMillis()+1000*60*60*24*7));//签到7天后到期
	                           csCoin.setCscValidity((short)0);
	                       }else if (activityName.contains("邀请")) {
	                           csCoin.setCscEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));//邀请30天后到期
	                           csCoin.setCscValidity((short)1);
	                       }
	                   }else {
	                       csCoin.setCscEnd(new Date(System.currentTimeMillis()+1000*60*60*24*7));//默认7天后到期
                           csCoin.setCscValidity((short)0);
	                   }
	                   
	                   /*if ("ATTEND".equals(event)) {
	                       csCoin.setCscEnd(new Date(System.currentTimeMillis()+1000*60*60*24*7));//签到7天后到期
	                       csCoin.setCscValidity((short)0);
	                   }else {
	                       csCoin.setCscEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));//邀请30天后到期
	                       csCoin.setCscValidity((short)1);
	                   }
	                   */
	                   csCoin.setCscMember(csMember.getCsmId());
	                   csCoin.setCscHost(csMember.getCsmHost());
	                   csCoin.setCscCount(csItem.getCsiPrice());
	                   csCoin.setCscEditor(0l);//设置系统用户为充值用户
	                   csCoin.setCscFlag("后台添加");
	                   
	                   csCoin.setCscRemark("用户通过活动盒子获得"+prizeName);
	                   csCoin.setCscSerial(SystemHelper.getCoinSerial(csCoin));
	                   csCoin.setCscUpdateTime(new Date(System.currentTimeMillis()));
	                   
	                   
	                   //todo 添加红包来源
	                   //csCoin.setCscCoinSource();
	                   csCoin.setCscRemain(csItem.getCsiPrice());
	                   csCoin.setCscStatus((short)1);
	                   
	                 //下发奖品
	                   //添加下发记录，添加用户奖品
	                   csCoin.save();//todo 未测试
	                   //CsCoin csCoin=CsCoin.Get($.add(CsCoin.F., value));
	               }else {
	                //没找对对应的商品
	                   return $.SendHtml("SUCCESS", SYSTEM.UTF8);
	               }
	             
	           
	       }
	       else {
	           //未找到中奖用户
	           return $.SendHtml("SUCCESS", SYSTEM.UTF8);
	       }
	       
        }else {
            //不是中奖事件回调直接返回
            return $.SendHtml("SUCCESS", SYSTEM.UTF8);
        }
	    
	    return $.SendHtml("SUCCESS", SYSTEM.UTF8);
    }
	
	
	public static String unicode2String(String unicode){
        if(null==unicode||"".equals(unicode))return null;
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while((i=unicode.indexOf("\\u", pos)) != -1){
            sb.append(unicode.substring(pos, i));
            if(i+5 < unicode.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
            }
        }

        return sb.toString();
    }
	
	/**
	 * 易到订单支付成功回调
	 * @return
	 */
	public String orderCallback(){
		try {
			int type = $.getInteger("type", 1);
			if(type == 1){
				yidaoyongche();
			}else if(type == 2){
				didiyongche();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 易到用车回调
	 */
	void yidaoyongche(){
		
		int nullVal = 9999;
		String order_id = $.getString("yongche_order_id");
		int order_status = $.getInteger("yongche_order_status", nullVal);
		if(order_id==null || order_status==nullVal){
			return;
		}
		
		CsSpecialCar specialCar = csSpecialCarService.getCsSpecialCar($.add(CsSpecialCar.F.csscThird, ApiBase.THIRD_YIDAO).add(CsSpecialCar.F.csscThirdNo, order_id));
		if(specialCar == null){
			Logger.getRootLogger().error("ThirdPartyCallbackAction.orderCallback->yidaoyongche订单号["+order_id+"]不正确");
			return;
		}
		if(specialCar.getCsscStatus().intValue()==5){
			return;
		}
		
		
		CsUnitPerson person = CsUnitPerson.getCsUnitPersonById(specialCar.getCsscPerson());
		if(person == null) {
			Logger.getRootLogger().error("ThirdPartyCallbackAction.orderCallback->yidaoyongche用户["+specialCar.getCsscPerson()+"]不存在！");
			return;
		}
		
		StringBuffer respVal = new StringBuffer();
		respVal.append("order_id="+order_id);
		respVal.append("&order_status="+order_status);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(order_status == 7){ //服务结束
			try{
				Integer abnormal = $.getInteger("abnormal", null);
				respVal.append("&abnormal="+abnormal);
				Integer reson_id = $.getInteger("reson_id", null);
				respVal.append("&reson_id="+reson_id);
				Integer adjust_flag = $.getInteger("adjust_flag", null);
				respVal.append("&adjust_flag="+adjust_flag);
				Integer withdraw_flag = $.getInteger("withdraw_flag", null);
				respVal.append("&withdraw_flag="+withdraw_flag);
				if(abnormal!=null && abnormal == 0){ 		 //没有异常，即正常结束
					//获取订单费用信息				
					
					specialCar.setCsscStatus((short)4);
					specialCar.setCsscResponse(respVal.toString());
					specialCar.setCsscUpdateTime(new Date());
					specialCar.update();
					
					YidaoApi.synOrderSettle(order_id); //结算并发送短信
				}else{
					Logger.getRootLogger().error("ThirdPartyCallbackAction.orderCallback->yidaoyongche订单号["+order_id+"]异常结束，参数："+respVal.toString());
					JpushClientHelper.sendAlertWithTag(person.getCsupMember().toString(), "叫车提醒", "您发起的从"+specialCar.getCsscStartAddr()+"到"+specialCar.getCsscFinishAddr()+"的"+ApiBase.CALL_CAR+"订单已被异常结束！", JpushConfig.OFC_APP);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			if(order_status == 4){		//司机确认
				specialCar.setCsscStatus((short)3);
				JpushClientHelper.sendAlertWithTag(person.getCsupMember().toString(), "叫车提醒", "您发起的从"+specialCar.getCsscStartAddr()+"到"+specialCar.getCsscFinishAddr()+"的"+ApiBase.CALL_CAR+"订单已被司机接单！", JpushConfig.OFC_APP);
			}else if(order_status == 5){//司机到达
				specialCar.setCsscStatus((short)3);
				Logger.getRootLogger().error("ThirdPartyCallbackAction.orderCallback->yidaoyongche订单号["+order_id+"]司机已到达");
			}else if(order_status == 6){ //服务开始
				specialCar.setCsscStatus((short)3);
				Logger.getRootLogger().error("ThirdPartyCallbackAction.orderCallback->yidaoyongche订单号["+order_id+"]服务已开始");
				specialCar.setCsscStartTime(new Date());	//更改订单（实际）开始时间
			}else if(order_status == 8){ //服务取消
				
				//				63001 无车可派  
				//				63002 有车无人接单(无有效司机接单)  
				//				63003 有司机接单，用户未选车，系统自动取消  
				//				63004 有司机接单，用户未选车，客户端自动取消  
				//				100 合作方取消 
				String reason_id = request.getParameter("reason_id");
				
				//渠道改派
				if(reason_id!=null && (reason_id.equals("63001") || reason_id.equals("63002"))){
					try{
						if(specialCar.getCsscThird().intValue()==ApiBase.THIRD_YIDAO){
							ThirdPartyApiHelper.sendCallCar(specialCar, true);
						}
						return;
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{
					specialCar.setCsscStatus((short)5);
					
					//不是合作方取消的，发送短信提醒
					if(!(reason_id!=null && reason_id.equals("100"))){
						JpushClientHelper.sendAlertWithTag(person.getCsupMember().toString(), "叫车提醒", "您发起的从"+specialCar.getCsscStartAddr()+"到"+specialCar.getCsscFinishAddr()+"的"+ApiBase.CALL_CAR+"订单已取消！", JpushConfig.OFC_APP);
						UtilHelper.sendTemplateSMS(specialCar.getCsscHost(),"CANCEL_SPECIALORDER", specialCar.getCsscMobile(), "您预订的"+ApiBase.CALL_CAR+"服务已取消，期待下次为您服务。", SMSType.通知类短信 , new HashMap<String, String>());
					}
				}
			}
			
			if((order_status == 4 || order_status == 5 || order_status == 6) && specialCar.getCsscDriverName() == null){
				try{
					Map<String,Object> map = YidaoApi.getDriverInfo(specialCar.getCsscThirdNo());
					if(map!=null){
						Object code = map.get("code");
						if(Double.valueOf(code.toString()).intValue()==200){
							Map result = (Map)map.get("result");
							String name = result.get("name").toString();						//司机名称
							String cellphone = result.get("cellphone").toString();				//司机电话
							String brand = result.get("brand").toString();						//车型名称
							String vehicle_number = result.get("vehicle_number").toString();	//车牌号
							String photo_url = result.get("photo_url").toString();				//头像地址
							specialCar.setCsscCarNumber(vehicle_number);
							specialCar.setCsscDriverName(name);
							specialCar.setCsscDriverCarInfo(brand);
							specialCar.setCsscDriverPhone(cellphone);
							specialCar.setCsscDriverImage(photo_url);
							
							//同时更新企业订单里的车牌号码
							new CsUnitOrder(specialCar.getCsscUnitOrder()).csuoNumber(vehicle_number).update();
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
			try{
				if(order_status == 4){		//司机确认
					UtilHelper.sendTemplateSMS(person.getCsupHost(),
							"CALLCAR_RECEIVE",
							specialCar.getCsscMobile(),
							"尊敬的{USER_NAME}！您已成功预订叫车服务，司机{DRIVER_NAME}为您服务，电话{DRIVER_PHONE}，该订单由北京公务出行支付，请不要支付给司机车费。"
								,SMSType.通知类短信, 
								$.add("USER_NAME", specialCar.getCsscName())
								 .add("CAR_ID", specialCar.getCsscCarNumber())
								 .add("DRIVER_NAME", specialCar.getCsscDriverName())
								 .add("DRIVER_PHONE", specialCar.getCsscDriverPhone()));
				}else if(order_status == 5){//司机到达
					UtilHelper.sendTemplateSMS(person.getCsupHost(),
							"CALLCAR_ARRIVE",
							specialCar.getCsscMobile(),
							"您预订的专车{CAR_ID}已到达约定地点，司机{DRIVER_NAME}{DRIVER_PHONE}， 感谢选择北京公务出行。"
								,SMSType.通知类短信, 
								$.add("CAR_ID", specialCar.getCsscCarNumber())
								 .add("DRIVER_NAME", specialCar.getCsscDriverName())
								 .add("DRIVER_PHONE", specialCar.getCsscDriverPhone()));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//订单状态变更实时推送==============START==================
			try{
				Map<String, Object> msgmap = new HashMap<String, Object>();
				msgmap.put("specialId", specialCar.getCsscId());
				msgmap.put("status", specialCar.getCsscStatus().intValue());
				Map<String, String> extras = new HashMap<String, String>();
				extras.put("specialId", specialCar.getCsscId()+"");
				JpushClientHelper.sendMessageWithTag(person.getCsupMember().toString(), $.json(msgmap), extras, JpushConfig.OFC_APP);
			}catch(Exception e){
				Log.error(e);
			}
			//订单状态变更实时推送==============ENDING==================
			
			specialCar.setCsscResponse(respVal.toString());
			specialCar.setCsscUpdateTime(new Date());
			specialCar.update();
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 200);
		ret.put("msg", "OK");
		$.SendHtml($.json(ret), "utf-8");
	}
	
	/**
	 * 滴滴用车回调
	 */
	void didiyongche(){
		String client_id = $.getString("client_id");			//申请应用时分配的AppKey(同授权认证)
		String client_secret = $.getString("client_secret");	//申请应用时分配的AppSecret(同授权认证)
		String order_id = $.getString("order_id");				//订单id
		Float normal_distance = $.getFloat("normal_distance");	//单次用车，本次用车的行驶距离；套路用车-超出套餐的行驶距离
		Float total_price = $.getFloat("total_price");			//总金额
		Integer pay_time = $.getInteger("pay_time");			//支付时间
		Integer status = $.getInteger("status");				//订单状态（700-已支付）
		String callback_info = $.getString("callback_info");	//需要透传的内容(默认为空，如下单前传了该字段，会原样传递相应内容)
		Integer timestamp = $.getInteger("timestamp");			//当前时间戳
		String sign = $.getString("sign");						//签名
		
		if(order_id==null || client_id== null){
			return;
		}
		
		//client_id=bjszfgwyc&
		//client_secret=4091b83e9bc684ebd1e685e5b7dfe0f0&
		//order_id=5702176133099311294&
		//normal_distance=5.5&
		//total_price=33.8&
		//pay_time=1441940799&
		//status=700&
		//callback_info=&
		//timestamp=1441940816&
		//sign=57ee769b9a733f9f398d461a18e95195
		
		CsSpecialCar specialCar = csSpecialCarService.getCsSpecialCar($.add(CsSpecialCar.F.csscThird, ApiBase.THIRD_DIDI).add(CsSpecialCar.F.csscThirdNo, order_id));
		if(specialCar == null){
			Logger.getRootLogger().error("ThirdPartyCallbackAction.orderCallback->didiyongche订单号["+order_id+"]不正确");
			return;
		}
		
		if(specialCar.getCsscStatus().intValue()==4){
			Log.error("已成功处理过 ================================= >>");
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("errno", 0);
			ret.put("errmsg", "success");
			$.SendHtml($.json(ret), "utf-8");
			return;
		}
		
		CsUnitPerson person = CsUnitPerson.getCsUnitPersonById(specialCar.getCsscPerson());
		if(person == null) {
			Logger.getRootLogger().error("ThirdPartyCallbackAction.orderCallback->didiyongche用户["+specialCar.getCsscPerson()+"]不存在！");
			return;
		}
		
		StringBuffer respVal = new StringBuffer();
		respVal.append("client_id="+client_id);
		respVal.append("&client_secret="+client_secret);
		respVal.append("&order_id="+order_id);
		respVal.append("&normal_distance="+normal_distance);
		respVal.append("&total_price="+total_price);
		respVal.append("&pay_time="+pay_time);
		respVal.append("&status="+status);
		respVal.append("&callback_info="+callback_info);
		respVal.append("&timestamp="+timestamp);
		respVal.append("&sign="+sign);
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(pay_time);
		
		//specialCar.setCsscFinishTime(c.getTime());
		specialCar.csscKilo(Double.valueOf(normal_distance).intValue());
		specialCar.csscThirdFee(Double.valueOf(total_price));
		specialCar.csscThirdFeeData(respVal.toString());
		specialCar.setCsscUpdateTime(new Date());
		specialCar.setCsscResponse(status+"");
		specialCar.update();

		if(status == 700){
			Map<String, Object> map = DidiApi.getOrderDetail(specialCar.getCsscMobile(), specialCar.getCsscThirdNo());
			if(map!=null){
				Object code = map.get("errno");
				Object msg = map.get("errmsg");
				Map data = (Map)map.get("data");
				if(Double.valueOf(code.toString()).intValue()==0 && msg.equals("SUCCESS")){
					Map<String, Object> order = (Map)data.get("order");
					Object finish_time = order.get("finish_time");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try{
						Date finishTime = sdf.parse(finish_time.toString());
						Long time = finishTime.getTime() - specialCar.getCsscStartTime().getTime();
						Long val = time/1000/60;
						specialCar.setCsscFinishTime(finishTime);
						specialCar.setCsscDuration(val);
						specialCar.setCsscRealDur(val);
					}catch(Exception e){
						Logger.getRootLogger().error(e.getMessage(),e);
						e.printStackTrace();
					}
				}
			}
			specialCar.update();
			
			try{
					commonUnitService.executeComplete(specialCar.getCsscUnitOrder());
					
					UtilHelper.sendTemplateSMS(specialCar.getCsscHost(),
							"CALLCAR_OVER",
							specialCar.getCsscMobile(),
							"本次服务完成-行驶{BILLING_TIME},{BILLIN_MILEAGE}公里，费用将由北京公务出行统一支付，若您对本次乘车有任何异议，请拨打客服电话:4006181221。"
								,SMSType.通知类短信, 
								$.add("BILLING_TIME", specialCar.getCsscRealDur()+"分钟")
								 .add("BILLIN_MILEAGE", specialCar.getCsscKilo()+""));
			}catch(Exception e){
				Log.error(e);
				e.printStackTrace();
			}
			
		}
		
		CsSpecialCar spe = new CsSpecialCar(specialCar.getCsscId());
		spe.csscStatus((short)4);
		spe.update();
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("errno", 0);
		ret.put("errmsg", "success");
		$.SendHtml($.json(ret), "utf-8");
		
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


    public ICsMemberService getCsMemberService() {
        return csMemberService;
    }


    public void setCsMemberService(ICsMemberService csMemberService) {
        this.csMemberService = csMemberService;
    }


    public ICsItemService getCsItemService() {
        return csItemService;
    }


    public void setCsItemService(ICsItemService csItemService) {
        this.csItemService = csItemService;
    }
	
	
	
}
