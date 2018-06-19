package com.ccclubs.action.app.official.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.ccclubs.action.app.official.model.ValueEntry;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsSpecialModel;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.service.common.impl.CommonUnitService;
import com.lazy3q.web.helper.$;

public class YidaoApi extends ApiBase{
	
	final static class URL{
		final static String ORDER = getServerUrl()+"order";
		//预估价格请求URL -author 庄田
		final static String ORDER_ESTIMATED = getServerUrl()+"cost/estimated";
	}
	
	final static class Param{
		final static String access_token = Env.isOnline() ? "7PNVkaajjW6Qk3dtGjZsKBj0mwxdVYTrK3wf4W6q" : "0xyioeusAoRsyVuw8SbndlUTumgUKvdYRDPegL8j";
	}

	public static String getServerUrl(){
		if(Env.isOnline()){
			return "https://yop.yongche.com/v2/";
		}else{
			return "http://sandbox.open.yongche.org/v2/";
		}
	}
	
	/**
	 * 创建订单
	 * 
	 * @param specialCar
	 */
	public Map<String, Object> createOrder(CsSpecialCar specialCar, Type type){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("city", "bj");
		param.put("type", "1"); 
		if(type == Type.REAL_TIME){
			param.put("is_asap", 1);
		}else{
			param.put("is_asap", 0);
		}
		param.put("car_type_id", carModelMapping(specialCar.getCsscCarModel()));
		param.put("start_position", new ValueEntry(specialCar.getCsscStartAddr(), CHARSET));
		param.put("start_address", new ValueEntry(specialCar.getCsscStartAddr(), CHARSET));
		String[] startPoint = specialCar.getCsscStartPoint().split(";");
		param.put("expect_start_latitude", startPoint[0]);
		param.put("expect_start_longitude", startPoint[1]);
		
		String startTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		startTime = sdf.format(specialCar.getCsscStartTime());
		
		param.put("time", startTime);
		param.put("rent_time", specialCar.getCsscDuration());
		param.put("end_position", new ValueEntry(specialCar.getCsscFinishAddr(), CHARSET));
		param.put("end_address", new ValueEntry(specialCar.getCsscFinishAddr(), CHARSET));
		String[] finishPoint = specialCar.getCsscFinishPoint().split(";");
		param.put("expect_end_latitude", finishPoint[0]);
		param.put("expect_end_longitude", finishPoint[1]);
		
		param.put("passenger_name", new ValueEntry(specialCar.getCsscName(), CHARSET));
		param.put("passenger_phone", specialCar.getCsscMobile());
		param.put("ms_type", "false");
		param.put("app_trade_no", (Env.isOnline()?"":"test-")+specialCar.getCsscId());
		param.put("access_token", Param.access_token == null ? "0xyioeusAoRsyVuw8SbndlUTumgUKvdYRDPegL8j" : Param.access_token);
		String urlParam = parseParam2(param, true);
		String resp = sendHttpPost("POST",  URL.ORDER , urlParam, null, CONTENT_TYPE, CHARSET);
		return fromJson(resp);
	}

	public static CsSpecialModel getCarSpecialModel(CsSpecialCar csSpecialCar){
		CsSpecialModel specialModel = CsSpecialModel.getCsSpecialModel($.add(CsSpecialModel.F.cssmThird, THIRD_YIDAO).add(CsSpecialModel.F.cssmThirdCode, carModelMapping(csSpecialCar.getCsscCarModel())));
		if(specialModel !=null){
			return specialModel;
		}
		return null;
	}
	
	/**
	 * 获取订单预估价格 
	 * @param csSpecialCar
	 * @return
	 */
	public static Map<String, Object> getOrderEstimatedPrice(CsSpecialCar csSpecialCar){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("city", cityMapping(csSpecialCar.getCsscHost()));//城市
		param.put("type", "1");//产品类型
		param.put("car_type_id", carModelMapping(csSpecialCar.getCsscCarModel()));//车型  getCarSpecialModel(csSpecialCar).getCssmThirdCode()
		Map<String, Object> points = ApiBase.splitLatLng(csSpecialCar);
		param.put("expect_start_longitude", points.get("start_longitude"));//出发地点经度
		param.put("expect_start_latitude", points.get("start_latitude"));//出发地点纬度
		param.put("expect_end_longitude", points.get("end_longitude"));//目的地点经度
		param.put("expect_end_latitude", points.get("end_latitude"));//目的地点纬度
		
		String startTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		startTime = sdf.format(csSpecialCar.getCsscStartTime());
		param.put("time", startTime);//开始时间
		param.put("map_type", 3);//地图类型 1-百度 2-火星 3-谷歌 默认 1-百度
		param.put("access_token", Param.access_token == null ? "0xyioeusAoRsyVuw8SbndlUTumgUKvdYRDPegL8j" : Param.access_token);
		String urlParam = parseParam2(param, true);
		String resp = sendHttpPost("POST",  URL.ORDER_ESTIMATED , urlParam, null, CONTENT_TYPE, CHARSET);
		return fromJson(resp);
	}
	
	/**
	 * 取消订单
	 * @param memberMobile
	 * @param orderId
	 * @param force	是否强制取消(true 或 false)默认false
	 * @return
	 */
	public Map<String, Object> cancelOrder(String orderId, String reasonId, String otherReason){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order_id", orderId);
		param.put("reason_id", reasonId);
		param.put("other_reason", otherReason);
		param.put("access_token", Param.access_token == null ? "0xyioeusAoRsyVuw8SbndlUTumgUKvdYRDPegL8j" : Param.access_token);
		String urlParam = parseParam2(param, true);
		
		DefaultHttpClient httpClient = new DefaultHttpClient(); 

		MyHttpDelete delete = new MyHttpDelete(URL.ORDER+"/"+orderId); 

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); 
		nameValuePairs.add(new BasicNameValuePair("order_id", "reason_id"));
		nameValuePairs.add(new BasicNameValuePair("reason_id", reasonId));
		nameValuePairs.add(new BasicNameValuePair("other_reason", otherReason));
		nameValuePairs.add(new BasicNameValuePair("access_token", Param.access_token == null ? "0xyioeusAoRsyVuw8SbndlUTumgUKvdYRDPegL8j" : Param.access_token));

		StringBuilder sb = new StringBuilder();      
		try {
			delete.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(delete);
			HttpEntity entity = response.getEntity();    
	        if (entity != null) {    
	            InputStream instreams = entity.getContent();    
	            BufferedReader reader = new BufferedReader(new InputStreamReader(instreams));    
	            
	            String line = null;      
	            try {      
	                while ((line = reader.readLine()) != null) {  
	                    sb.append(line + "\n");      
	                }      
	            } catch (IOException e) {      
	                e.printStackTrace();      
	            } finally {      
	                try {      
	                	instreams.close();      
	                } catch (IOException e) {      
	                   e.printStackTrace();      
	                }      
	            }      
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
//		String respstr = sendHttpPost("DELETE", URL.ORDER+"/"+orderId+"?"+urlParam,null, null, CONTENT_TYPE, CHARSET);
		Map<String, Object> resp = fromJson(sb.toString());
		resp = resultFilter(resp);
		return resp;
	}
	
	/**
	 * 同步订单结算信息
	 * @param order_id
	 * @return
	 */
	public static void synOrderSettle(String orderId){
		if(StringUtils.isEmpty(orderId))return;
		CsSpecialCar specialCar = CsSpecialCar.where().csscThird(Long.valueOf(ApiBase.THIRD_YIDAO)).csscThirdNo(orderId).get();
		if(specialCar==null)return;
		CsSpecialCar updateCsSpecialCar = new CsSpecialCar(specialCar.getCsscId());
		String jsonString = "";
		try {
			jsonString = $.http(URL.ORDER+"/calculate/"+orderId+"?access_token="+Param.access_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map data = $.eval(jsonString);
		if(data!=null && $.equals("200", data.get("code").toString())){
			Map result = (Map) data.get("result");
			if(result.get("time_length")!=null){//订单正常结束
				updateCsSpecialCar.setCsscStartTime($.date(result.get("actual_board_time").toString(),"yyyy-MM-dd HH:mm:ss"));
				updateCsSpecialCar.csscFinishTime($.date(result.get("actual_off_time").toString(),"yyyy-MM-dd HH:mm:ss"));
				updateCsSpecialCar.csscDuration(((Double)(Double.valueOf(result.get("time_length").toString()) * 60)).longValue());
				updateCsSpecialCar.csscRealDur(((updateCsSpecialCar.getCsscFinishTime().getTime()-updateCsSpecialCar.getCsscStartTime().getTime())/$.TIME.MINUTE.time));
				updateCsSpecialCar.csscKilo(Double.valueOf(result.get("kilo_length").toString()).intValue());
				updateCsSpecialCar.csscThirdFee(Double.valueOf(result.get("order_amount").toString()));
				updateCsSpecialCar.csscThirdFeeData(jsonString);
				updateCsSpecialCar.csscStatus((short)4);
			}else if(result.get("cancel_order_amount")!=null){//订单取消
				updateCsSpecialCar.csscDuration(0l);
				updateCsSpecialCar.csscRealDur(0l);
				updateCsSpecialCar.csscKilo(0);
				updateCsSpecialCar.csscThirdFee((Double) result.get("cancel_order_amount"));
				updateCsSpecialCar.csscThirdFeeData(jsonString);
				updateCsSpecialCar.csscStatus((short)5);
			}
		}else{
			updateCsSpecialCar.setCsscAssess("synOrderSettle failure" + jsonString);
		}
		updateCsSpecialCar.update();
		
		try{
			if(specialCar.getCsscStatus().intValue()!=4){
				UtilHelper.sendTemplateSMS(specialCar.getCsscHost(),
						"CALLCAR_OVER",
						specialCar.getCsscMobile(),
						"本次服务完成-行驶{BILLING_TIME},{BILLIN_MILEAGE}公里，费用将由北京公务出行统一支付，若您对本次乘车有任何异议，请拨打客服电话:4006181221。"
							,SMSType.通知类短信, 
							$.add("BILLING_TIME", updateCsSpecialCar.getCsscRealDur()+"分钟")
							 .add("BILLIN_MILEAGE", specialCar.getCsscKilo()+""));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//结束系统的企业订单
		CommonUnitService.getBean().executeComplete(specialCar.getCsscUnitOrder());
	}
	
	/**
	 * 获取司机的信息
	 * @return
	 */
	public static Map<String, Object> getDriverInfo(String orderId){
		if(StringUtils.isEmpty(orderId))return null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order_id", orderId);
		param.put("access_token", Param.access_token == null ? "0xyioeusAoRsyVuw8SbndlUTumgUKvdYRDPegL8j" : Param.access_token);
		String urlParam = parseParam2(param, false);
		String resp = null;
		try {
			resp = $.http(getServerUrl()+"driver/info?"+urlParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fromJson(resp);
	}
	
	/**
	 * 获取当前订单的状态位置
	 * @return
	 */
	public static Map<String, Object> getOrderLocale(String orderId){
		if(StringUtils.isEmpty(orderId))return null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("order_id", orderId);
		param.put("access_token", Param.access_token == null ? "0xyioeusAoRsyVuw8SbndlUTumgUKvdYRDPegL8j" : Param.access_token);
		param.put("map_type", 3);			//1：百度，2：火星 3-谷歌 默认值：1       我们app使用的是高德地图，接近谷歌标准
		String urlParam = parseParam2(param, false);
		String resp = null;
		try {
			resp = $.http(getServerUrl()+"driver/location?"+urlParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fromJson(resp);
	}
	
	public static Map<String, Object> resultFilter(Map<String, Object> value){
		
		return value;
	}
	
	public static void main(String[] args) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("city", "hz");
		param.put("type", "1");
		param.put("car_type_id", "28");
		param.put("start_position", new ValueEntry("阿里巴巴", CHARSET));
		param.put("start_address", new ValueEntry("阿里巴巴北门", CHARSET));
		param.put("expect_start_latitude", "30.188709");
		param.put("expect_start_longitude", "120.18995");
		param.put("time", "2015-08-27 18:30:00");
		param.put("rent_time", "2");
		param.put("end_position", new ValueEntry("浙大玉泉校区", CHARSET));
		param.put("end_address", new ValueEntry("浙大玉泉校区", CHARSET));
		param.put("expect_end_latitude", "30.2647235818544");
		param.put("expect_end_longitude", "120.126846581697");
		param.put("passenger_name", new ValueEntry("张三", CHARSET));
		param.put("passenger_phone", "15868164303");
		param.put("ms_type", "false");
		param.put("app_trade_no", "test201508241724");
		param.put("access_token", "0xyioeusAoRsyVuw8SbndlUTumgUKvdYRDPegL8j");
		String urlParam = parseParam2(param, true);
		String resp = sendHttpPost("POST", URL.ORDER, urlParam, null, CONTENT_TYPE, CHARSET);
		
		try {
			System.out.println(new String(resp.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 车型转换函数，默认为100
	 * @param type @see app端的选项值：[1：舒适性，2：商务型]
	 * @return
	 */
	public static String carModelMapping(Long type){
		if(type == null)return "2";
		if(type == 1)
			return "2";								//舒适型
		else if(type == 2)
			return "5";								//商务型 
		else 
			return "2";
	}
	
	/**
	 * 城市转换函数，默认为bj
	 */
	public static String cityMapping(Long host){
		if(host == null) return "bj";
		if(host == 1)
			return "bj";
		else
			return "bj";
	}
	
}
