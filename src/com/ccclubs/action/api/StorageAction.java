package com.ccclubs.action.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ccclubs.action.api.model.Result;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsElecHistory;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsState;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPowerPileService;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * @category 数据交换
 * 
 * @author zhangjian
 * 
 */
public class StorageAction {

	private final static String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final static String CHARSET = "utf-8"; 
	private static Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
	
	ICsCarService		csCarService = $.GetSpringBean("csCarService");
	ICsOutletsService	csOutletsService = $.GetSpringBean("csOutletsService");
	ICsPowerPileService	csPowerPileService = $.GetSpringBean("csPowerPileService");
	ICsOrderService     csOrderService    = $.GetSpringBean("csOrderService");
	ICsMemberService     csMemberService    = $.GetSpringBean("csMemberService");
	final DefaultJRedisClient<String, CsElecHistory> jr = new DefaultJRedisClient<String, CsElecHistory>(DBIndex.APP_API);
	
	/**
	 * 是否已授权
	 * @return
	 */
	public boolean isOauth(){
		String access_token = $.getString("access_token");
		if(access_token!=null){
			String client_id = jr.get("api-client-oauth-"+access_token);
			if(StringUtils.isNotEmpty(client_id)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据车辆ID查该车辆正在进行中的订单用户的EV卡号
	 */
	public String getEvCardNumber(){
		Result result = new Result();
		try{
			if(!isOauth()){
				return $.SendHtml($.json(result.setResult(false).setCode(1000).setMessage("授权凭证无效")), CHARSET); 
			}
			
			String carNumber = $.getString("carNumber");
			
			if(StringUtils.isEmpty(carNumber)){
				return $.SendHtml($.json(result.setResult(false).setCode(1001).setMessage("参数无效")), CHARSET); 
			}
			
			String cardNumber = "0";
			CsState state = CsState.getCsState($.add(CsState.F.cssNumber, carNumber));
			if(state!=null){
				Long orderId = state.getCssOrder();
				if(orderId!=null){
					CsOrder order = CsOrder.get(orderId);
					if(order!=null){
						CsEvCard card = CsEvCard.getCsEvCard($.add(CsEvCard.F.csecRfid, order.getCsoEvRfid()));
						if(card!=null){
							cardNumber = card.getCsecNumber();
						}
					}
				}
			}
			
			return $.SendHtml($.json(result.setResult(true).setCode(0).setMessage("调用成功").setData($.add("cardNumber", cardNumber))), CHARSET); 
		}catch(Exception e){
			Logger.getRootLogger().error(e.getMessage(),e);
			e.printStackTrace();
			return $.SendHtml($.json(result.setResult(false).setCode(9999).setMessage("系统异常")), CHARSET);
		}
	}
	
	/**
	 * 根据卡号获取会员信用值
	 * @return
	 */
	public String getMemberCredit(){
		Result result = new Result();
		try{
			if(!isOauth()){
				return $.SendHtml($.json(result.setResult(false).setCode(1000).setMessage("授权凭证无效")), CHARSET); 
			}
			
			String cardNumber = $.getString("cardNumber");
			
			if(StringUtils.isEmpty(cardNumber)){
				return $.SendHtml($.json(result.setResult(false).setCode(1001).setMessage("参数无效")), CHARSET); 
			}
			
			return $.SendHtml($.json(result.setResult(true).setCode(0).setMessage("调用成功").setData($.add("cardNumber", cardNumber).add("creditValue", "200"))), CHARSET); 
		}catch(Exception e){
			Logger.getRootLogger().error(e.getMessage(),e);
			e.printStackTrace();
			return $.SendHtml($.json(result.setResult(false).setCode(9999).setMessage("系统异常")), CHARSET);
		}
	}
	
	/**
	 * 获取车辆状态数据
	 * @return
	 */
	public String getAllCarsStates(){
		Result result = new Result();
		try{
			if(!isOauth()){
				return $.SendHtml($.json(result.setResult(false).setCode(1000).setMessage("授权凭证无效")), CHARSET); 
			}
			
			String SQL = "SELECT"
						+" c.csc_number carNumber,"
						+"c.csc_vin carVin,"
						+"cm.cscm_name carModel,"
						+"st.css_add_time addTime,"
						+"c.csc_status status,"
						+"st.css_speed speed,"
						+"st.css_motor motor,"
						+"st.css_fuel_mileage fuelMileage,"
						+"st.css_electric_mileage elecMileage,"
					  +"st.css_mileage mileage,"
					  +"st.css_oil oil,"
					  +"st.css_power power,"
					  +"st.css_ev_battery evBattery,"
					  +"st.css_longitude longitude,"
					  +"st.css_latitude latitude,"
					  +"null directAngle,"
					  +"null altitude"
					+" FROM"
					+"	cs_car c"
					+" LEFT JOIN cs_car_model cm ON c.csc_model = cm.cscm_id"
					+" left JOIN cs_state st ON c.csc_id = st.css_car";
			
			List<Map> dataList = dao.executeQuery(SQL);
			return $.SendHtml($.json(result.setResult(true).setCode(0).setMessage("调用成功").setData(dataList)), CHARSET); 
		}catch(Exception e){
			Logger.getRootLogger().error(e.getMessage(),e);
			e.printStackTrace();
			return $.SendHtml($.json(result.setResult(false).setCode(9999).setMessage("系统异常")), CHARSET);
		}
	}
	
	/**
	 * 获取所有车辆车牌号
	 * @return
	 */
	public String getAllCarsNumber(){
		Result result = new Result();
		try{
			if(!isOauth()){
				return $.SendHtml($.json(result.setResult(false).setCode(1000).setMessage("授权凭证无效")), CHARSET); 
			}
			List<CsCar> carList = csCarService.getCsCarList($.Map(), 9999);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			if(carList!=null){
				for(CsCar car : carList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("carNumber", car.getCscNumber());
					map.put("carNo", car.getCscCarNo());
					dataList.add(map);
				}
			}
			return $.SendHtml($.json(result.setResult(true).setCode(0).setMessage("调用成功").setData(dataList)), CHARSET); 
		}catch(Exception e){
			Logger.getRootLogger().error(e.getMessage(),e);
			e.printStackTrace();
			return $.SendHtml($.json(result.setResult(false).setCode(9999).setMessage("系统异常")), CHARSET);
		}
	}

}
