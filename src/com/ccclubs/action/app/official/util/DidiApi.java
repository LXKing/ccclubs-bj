package com.ccclubs.action.app.official.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.app.official.model.DidiResp;
import com.ccclubs.action.app.official.model.ValueEntry;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.helper.redis.tag.APP_OFFICIAL;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsSpecialModel;
import com.lazy3q.web.helper.$;

/**
 * 滴滴专车api
 * @author joel
 * 
 * 滴滴专车发起叫车请求分3步
 * 1、认证授权，获取access_token (根据api说明，授权码超时时间为3600秒)
 * 2、请求获取订单id
 * 3、发起叫车订单
 */
public class DidiApi extends ApiBase{
	
	static final DefaultJRedisClient<String, Object> jr = new DefaultJRedisClient<String, Object>(DBIndex.APP_OFFICIAL);
	
	final static class Param{
		final static String client_id = isTestServer() ? "bqplgs" : "bjszfgwyc";
		final static String client_secret = isTestServer() ? "7cfa96f588dc941442e8531c7a465c26" : "4091b83e9bc684ebd1e685e5b7dfe0f0";
		final static String caller_mobile = isTestServer() ? "13020000101" : "13811279031";		//叫车用户的电话号码,这里是统一的，如果要正常用会员的号码，把这里名字改下，看下报错的地方改下引用就好了
		final static String sign_key = isTestServer() ? "5eaeF95B574684380be6" : "fa991B1586fcB406fba3";
		final static int 	city 	 = Env.isTest() ? 5 : 1;
		final static String require_level = "100";// 预定车型，如果app没有传递车型信息，默认为车型为100.100舒适型，400商务型, 200豪华型
	}
	
	static CsSpecialModel specialModel = null;

	
	final static class URL{
		
		/**
		 * 叫车请求
		 */
		final static String ORDER = getServerUrl()+"order/Create/request";
		
		/**
		 * 授权认证
		 */
		final static String AUTH = getServerUrl()+"Auth/authorize";
		
		/**
		 * 获取叫车请求id
		 */
		final static String REQ_ORDER_ID = getServerUrl()+"order/Create/orderId";
		
		/**
		 * 获取订单详情
		 */
		final static String ORDER_DETAIL = getServerUrl()+"order/Detail/getOrderDetail";
		
		/**
		 * 获取订单预估价格
		 */
		final static String ORDER_ESTIMATE = getServerUrl()+"common/Estimate/price";
		
	}

	public static String getServerUrl(){
		if(isTestServer()){
			return "http://api.es.xiaojukeji.com/v1/";
		}else{
			return "http://api.es.xiaojukeji.com/v1/";
//			return "https://es.api.xiaojukeji.com/v1/";
		}
	}
	
	static final Long expireTime = 60 * 30L;  //30分钟 
	
	public static String reqAccessToken(String memberMobile){
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("client_id", Param.client_id);
			param.put("client_secret", Param.client_secret);
			param.put("grant_type", "client_credentials");
			param.put("phone", memberMobile);
			param.put("timestamp", (new Date()).getTime());
			param.put("sign_key", Param.sign_key);
			
			String respstr = sendHttpPost("GET", URL.AUTH, genSignKey(param), null, CONTENT_TYPE, CHARSET);
			Map<String, Object> resp = fromJson(respstr);
			return resp.get("access_token")+"";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取滴滴专车token
	 * @return
	 */
	public static String getAndSetAccessToken(String memberMobile){
		memberMobile = Param.caller_mobile;
		String token = null;
		
		//因为这里的请求授权的phone始终是一个人（caller_mobile），所以不用放入redis咯，但是自己维护token还要维护超时，何况以后说不定还要变也不一定，还是放吧，呵呵呵
		try{
			token = jr.get(APP_OFFICIAL.DIDI_ACCESS_TOKEN+memberMobile);
			if(token == null || token.trim().equals("")){
				token = reqAccessToken(memberMobile);
				cacheToken(memberMobile, token);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(token == null){
				token = reqAccessToken(memberMobile);
				cacheToken(memberMobile, token);
			}
		}
		
		return token;
	}
	
	public static void cacheToken(String memberMobile, String token){
		memberMobile = Param.caller_mobile;  
		//因为这里的请求授权的phone始终是一个人（caller_mobile），所以不用放入redis咯，但是自己维护token还要维护超时，何况以后说不定还要变也不一定，还是放吧，呵呵呵
		try{
			if(StringUtils.isNotEmpty(token)){
				jr.set(APP_OFFICIAL.DIDI_ACCESS_TOKEN+memberMobile, token, expireTime);
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	public String genOrderId(String access_token){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("client_id", Param.client_id);
		param.put("access_token", access_token);
		param.put("timestamp", (new Date()).getTime());
		param.put("sign_key", Param.sign_key);
		String respstr = sendHttpPost("GET", URL.REQ_ORDER_ID, genSignKey(param), null, CONTENT_TYPE, CHARSET);
		Map<String, Object> resp = fromJson(respstr);
		resp = resultFilter(resp);
		Map<String, Object> data = (Map)resp.get("data");
		if(data!=null){
			return data.get("order_id")+"";
		}
		return null;
	}
	
	public static Map<String, Object> resultFilter(Map<String, Object> value){
		
		return value;
	}
	
	public static Map<String, Object> getOrderDetail(String memberMobile, String orderId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("client_id", Param.client_id);
		param.put("access_token", getAndSetAccessToken(memberMobile));
		param.put("timestamp", (new Date()).getTime());
		param.put("sign_key", Param.sign_key);
		param.put("order_id", orderId);
		String respstr = sendHttpPost("GET", URL.ORDER_DETAIL+"?"+genSignKey(param), "", null, CONTENT_TYPE, CHARSET);
		Map<String, Object> resp = fromJson(respstr);
		resp = resultFilter(resp);
		return resp;
	}
	
	/**
	 * 获取订单预估价格
	 * @param memberMobile
	 * @param flat 起点经度
	 * @param flng 起点纬度
	 * @param tlat 终点经度
	 * @param tlng 终点纬度
	 * @param CarModle 车型
	 * @return
	 */
	public static Map<String, Object> getEstimateOrderPrice(CsSpecialCar csSpecialCar){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("client_id", Param.client_id);
		param.put("access_token", getAndSetAccessToken(csSpecialCar.getCsscMobile()));
		param.put("timestamp", (new Date()).getTime());
		param.put("sign_key", Param.sign_key);
		Map<String, Object> points = ApiBase.splitLatLng(csSpecialCar);
		param.put("city", Param.city);//出发城市id（城市车型接口返回）1是北京, 5是杭州
		param.put("rule", "201");//计价模型分类，201(普通)；202(套餐);301(快车)
		param.put("flat", points.get("start_longitude"));//出发地经度
		param.put("flng", points.get("start_latitude"));//出发地经度
		param.put("tlat", points.get("end_longitude"));//目的地经度
		param.put("tlng", points.get("end_latitude"));//目的地纬度
		param.put("require_level", carModelMapping(csSpecialCar));//车型  getCarSpecialModel(csSpecialCar).getCssmThirdCode()
		//param.put("flat", flat);
		String respstr = sendHttpPost("GET", URL.ORDER_ESTIMATE+"?"+genSignKey(param), "", null, CONTENT_TYPE, CHARSET);
		Map<String, Object> resp = fromJson(respstr);
		resp = resultFilter(resp);
		return resp;
	}
	
	/**
	 * 取消订单
	 * @param memberMobile
	 * @param orderId
	 * @param force	是否强制取消(true 或 false)默认false
	 * @return
	 */
	public Map<String, Object> cancelOrder(String memberMobile, String orderId, boolean force){
		String access_token = getAndSetAccessToken(memberMobile);
		Map<String, Object> param = getBaseParam(access_token);
		param.put("order_id", orderId);
		param.put("force", force);
		String respstr = sendHttpPost("POST", getServerUrl()+"order/Cancel", genSignKey(param), null, CONTENT_TYPE, CHARSET);
		Map<String, Object> resp = fromJson(respstr);
		resp = resultFilter(resp);
		return resp;
	}
	
	public static CsSpecialModel getCarSpecialModel(CsSpecialCar csSpecialCar){
		if(specialModel !=null)return specialModel;
		CsSpecialModel specialModel = CsSpecialModel.getCsSpecialModel($.add(CsSpecialModel.F.cssmThird, THIRD_DIDI).add(CsSpecialModel.F.cssmThirdCode, carModelMapping(csSpecialCar)));
		if(specialModel !=null){
			return specialModel;
		}
		return null;
	}
	
	public String getCarTypeId(){
		if(specialModel!=null){
			return specialModel.getCssmThirdCode();
		}else{
			return Param.require_level;
		}
	}
	
	
	/**
	 * 发起重新叫单
	 */
	public Map<String, Object> reOrder(String oldOrderId){
		String access_token = getAndSetAccessToken(Param.caller_mobile);
		Map<String, Object> param = getBaseParam(access_token);
		CsSpecialCar specialCar = CsSpecialCar.get(Long.parseLong(oldOrderId));
		param.put("order_id", oldOrderId);
		param.put("require_level", carModelMapping(specialCar));
		String respstr = sendHttpPost("POST", getServerUrl()+"order/Create/reRequest", genSignKey(param), null, CONTENT_TYPE, CHARSET);
		Map<String, Object> resp = fromJson(respstr);
		resp = resultFilter(resp);
		return resp;
	}
	
	public Map<String, Object> createOrder(CsSpecialCar specialCar, int city, Type type) {
		String access_token = getAndSetAccessToken(Param.caller_mobile);
		String orderId = genOrderId(access_token);
		
		Map<String, Object> param = getBaseParam(access_token);
		
		param.put("order_id", orderId);
		param.put("rule", 201);															//计价模型分类，201(普通)；202(套餐)；301(快车)
		param.put("type", type == Type.REAL_TIME ? 0 : 1);								//叫车车型，0(实时)；1(预约)
		param.put("passenger_phone", specialCar.getCsscMobile());						//乘客手机号，不填表示给自己叫车
		param.put("city", city);														//1是北京, 5是杭州 ，需要调用滴滴的城市车型接口获取
		
		String[] startPoint = specialCar.getCsscStartPoint().split(";");
		
		param.put("flat", Float.valueOf(startPoint[0]));								//出发地纬度
		param.put("flng", Float.valueOf(startPoint[1]));								//出发地经度
		param.put("start_name", new ValueEntry(specialCar.getCsscStartAddr(),CHARSET));			//出发地名称(最多50个字)
		param.put("start_address", new ValueEntry(specialCar.getCsscStartAddr(),CHARSET));		//出发地详细地址(最多100个字)
		
		
		String[] finishPoint = specialCar.getCsscFinishPoint().split(";");
		param.put("tlat", Float.valueOf(finishPoint[0]));								//目的地纬度
		param.put("tlng", Float.valueOf(finishPoint[1]));								//目的地经度
		param.put("end_name", new ValueEntry(specialCar.getCsscFinishAddr(),CHARSET));			//目的地名称(最多50个字)
		param.put("end_address", new ValueEntry(specialCar.getCsscFinishAddr(),CHARSET));		//目的地详细地址(最多100个字)
		
		param.put("clat", startPoint[0]);												//当前位置纬度
		param.put("clng", startPoint[1]);												//当前位置经度
		
		String startTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		startTime = sdf.format(specialCar.getCsscStartTime());
		
		param.put("departure_time", startTime);											//出发时间，不传表示现在用车
		param.put("require_level", carModelMapping(specialCar));						//所需车型
		param.put("app_time", sdf.format(new Date()));									//客户端时间
		param.put("sms_policy", 3);														//发送短信策略(0,为叫车人和乘车人都发送，1，乘车人发送叫车人不发，2乘车人不发叫车人发，3乘车人和叫车人都不发)
		
		String respstr = sendHttpPost("POST", URL.ORDER, genSignKey(param), null, CONTENT_TYPE, CHARSET);
		Map<String, Object> resp = fromJson(respstr);
		resp = resultFilter(resp);
		return resp;
	}

	@Override
	public Map<String, Object> createOrder(CsSpecialCar specialCar, Type type) {
		return createOrder(specialCar, Param.city, type);
	}
	
	public Map<String, Object> getBaseParam(String access_token){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("client_id", Param.client_id);
		param.put("access_token", access_token);
		param.put("timestamp", (new Date()).getTime());
		param.put("sign_key", Param.sign_key);
		return param;
	}
	
	public static void main(String[] args) {
		DidiApi api = new DidiApi();
		Map o = api.getOrderDetail("13020000101", "5627157375469769604");
	}
	
	public static void testCreateOrder(){
		DidiApi api = new DidiApi();
		
		String phone = "13020000101";
		String access_token = api.getAndSetAccessToken(phone);
		String orderId = api.genOrderId(access_token);
		Map<String, Object> param = api.getBaseParam(access_token);
		param.put("order_id", orderId);
		param.put("rule", 201);
		param.put("type", 0);
		param.put("city", 1);
		param.put("flat", Float.valueOf("30.188709"));
		param.put("flng", Float.valueOf("120.18995"));
//		param.put("start_name", "alibaba");
		param.put("start_name", new ValueEntry("阿里巴巴", "utf-8"));
		param.put("tlat", Float.valueOf("30.2647235818544"));
		param.put("tlng", Float.valueOf("120.126846581697"));
		param.put("end_name", "test2");
		param.put("end_address", "test3");
		param.put("departure_time", "2015-08-30 16:00:00");
		param.put("require_level", "400");
		param.put("app_time", "2015-08-30 16:00:00");
		param.put("sms_policy", 3);
		try {
			String respstr;
			respstr = sendHttpPost("POST", "https://es.api.xiaojukeji.com/v1/order/Create/request", genSignKey(param), null, CONTENT_TYPE, CHARSET);
			DidiResp resp = fromJson(respstr, DidiResp.class);
			System.out.println(respstr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 车型转换函数，默认为100
	 * @param specialCar
	 * @return
	 */
	public static String carModelMapping(CsSpecialCar specialCar){
		if(specialCar != null){
			if(specialCar.getCsscCarModel() == 1)
				return "100";							//100舒适型
			else if(specialCar.getCsscCarModel() == 2)
				return "400";							//400商务型 
			else if(specialCar.getCsscCarModel() == 3)
				return "200";							//200豪华型
			else 
				return "100";
		}else{
			return "100";
		}
	}
	
	
	
}

