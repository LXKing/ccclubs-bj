package com.ccclubs.action.api.chargedot.teld;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.app.official.util.Env;
import com.ccclubs.helper.APICallHelper;
import com.ccclubs.util.DESUtil;

/**
 * 
 * 特来电电桩数据开放接口
 * 
 * 先调用授权 -》 使用授权码调用接口
 * 
 * @author Joel
 *
 */
public class TeldApi {
	
//	final static String SERVER_PATH = "http://open.teld.cn/"; 
	final static String SERVER_PATH = Env.isOnline()? "http://open.teld.cn/" : "http://api.wyqcd.cn:8004/";
	
	public final static String MerchCode = "teldwbe81kte3u53s7zh";						//商户代码
	public final static String Secret    = "7gHr3rJibQ";								//秘钥
	public final static String Key       = "Ro8XExqlpzOKjwFaDu17Ffcgsy6909Zl";			//生成sign的key
	public final static String DesKey    = "prpjqpdD";									//DES加密Key
	public final static String DesIv     = "0FY9NzXxkeFIQ33y3Q3tVl8OSjIyGnQU";			//DES加密Iv
	
	final static String maxPageSize 	 = "500";
	final static String hostName     	 = "北京";
	
	static String 	authKey 		     = "";											//授权码
	static Date 	lastOauthTime 		 = new Date();         							//最后授权时间
	static int 		expireTimeLen        = 0;                  							//过期时间（单位：秒）
	
	/**
	 * 是否加密
	 */
	final static boolean doEncrypt 		 = true;
	
	/**
	 * 获取请求授权KEY
	 */
	public static void getAuthKey(){
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("grant_type", "client_credentials");
		msg.put("client_id", MerchCode);
		msg.put("client_secret", Secret);
		String result = APICallHelper.doPost(SERVER_PATH + "OAuth/Token", msg);
		if(result!=null && result.indexOf("access_token")!=-1){
			JSONObject jsonObj = JSONObject.fromObject(result);
			authKey = jsonObj.getString("access_token");
			expireTimeLen = jsonObj.getInt("expires_in");
			lastOauthTime = new Date();
		}
	}
	
	public static Map<String, String> getAuthHeader(){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer "+authKey);
		return headers;
	}
	
	/**
	 * 检测授权是否已过期，如果过期，则重新获取
	 */
	public static void before(){
		if(expireTimeLen <=0 || (new Date().getTime() - lastOauthTime.getTime())/1000 <=expireTimeLen-10){
			getAuthKey();
		}
	}
	
	/**
	 * 检测结果中的异常情况
	 */
	public static void after(TeldApiResp resp){
		if(resp !=null){
			if(!resp.success()){
				//如果是“未授权的调用”，失效授权
				if(resp.getReturnCode() == 1002){
					expireTimeLen = 0;
				}
			}else{
				if(doEncrypt){
					Object res = resp.getResultValue();
					if(res!=null && StringUtils.isNotEmpty(res.toString())){
						try {
							String desParam = DESUtil.decrypt(res.toString(), TeldApi.DesKey, TeldApi.DesIv);
							Object a = APICallHelper.fromJson(desParam, (new HashMap<String, Object>()).getClass());
							resp.setResultValue(a);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}else{
			resp = TeldApiResp.FAIL;
		}
	}
	
	public static TeldApiResp queryStas(){
		return queryStas(null, null);
	}
	
	
	/**
	 * 获取电站列表
	 * @return
	 */
	public static TeldApiResp queryStas(String province, String city){
		before();
		
		TeldApiReq req = new TeldApiReq();
		
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("province", province == null ? hostName : province);
		msg.put("city", city == null ? "" : city);
		msg.put("region", "");
		msg.put("type", "");
		msg.put("opState", "");
		msg.put("pageNo", "1");
		msg.put("pageSize", maxPageSize);
		
		req.setRequestMsg(msg);
		String result = APICallHelper.doPostJSON(SERVER_PATH+"api/Sta/PostSta", req.getReqData(doEncrypt), getAuthHeader());
		TeldApiResp resp = APICallHelper.fromJson(result, TeldApiResp.class);
		
		after(resp);
		resp = resp == null ? TeldApiResp.FAIL : resp; 
		return resp;
	}
	
	/**
	 * 获取电站详情
	 * @return
	 */
	public static TeldApiResp getStaDetail(String staCode){
		before();
		
		TeldApiReq req = new TeldApiReq();
		if(staCode == null || staCode.equals(""))return TeldApiResp.FAIL;
		
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("staCode", staCode);
		
		req.setRequestMsg(msg);
		String result = APICallHelper.doPostJSON(SERVER_PATH+"api/Sta/PostStaDetail", req.getReqData(doEncrypt), getAuthHeader());
		TeldApiResp resp = APICallHelper.fromJson(result, TeldApiResp.class);
		
		after(resp);
		resp = resp == null ? TeldApiResp.FAIL : resp;
		return resp;
	}
	
	/**
	 * 获取电桩详情
	 * @return
	 */
	public static TeldApiResp getPileDetail(String pileCode){
		before();
		
		TeldApiReq req = new TeldApiReq();
		if(pileCode == null || pileCode.equals(""))return TeldApiResp.FAIL;
		
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("pileCode", pileCode);
		
		req.setRequestMsg(msg);
		String result = APICallHelper.doPostJSON(SERVER_PATH+"api/Sta/PostPileDetail", req.getReqData(doEncrypt), getAuthHeader());
		TeldApiResp resp = APICallHelper.fromJson(result, TeldApiResp.class);
		
		after(resp);
		resp = resp == null ? TeldApiResp.FAIL : resp;
		return resp;
	}
	
	/**
	 * 获取充电订单
	 * @return
	 */
	public static TeldApiResp queryChargeRecord(TeldChargeRecordQuery query){
		before();
		
		TeldApiReq req = new TeldApiReq();
		
		Map<String, Object> msg = new HashMap<String, Object>();
		if(query.getBeginTime()!=null)
			msg.put("beginTime", query.getBeginTime());
		if(query.getEndTime()!=null)
			msg.put("endTime", query.getEndTime());
		if(query.getStaCode()!=null)
			msg.put("staCode", query.getStaCode());
		if(query.getPileCode()!=null)
			msg.put("pileCode", query.getPileCode());
		if(query.getChargeCode()!=null)
			msg.put("chargeCode", query.getChargeCode());
		if(query.getOpenChargeCode()!=null)
			msg.put("openChargeCode", query.getOpenChargeCode());
		if(query.getCarLicense()!=null)
			msg.put("carLicense", query.getCarLicense());
		if(query.getCardCode()!=null)
			msg.put("cardCode", query.getCardCode());
		if(query.getPageNo()!=null)
			msg.put("pageNo", query.getPageNo());
		if(query.getPageSize()!=null)
			msg.put("pageSize", query.getPageSize());
    	
		req.setRequestMsg(msg);
		String result = APICallHelper.doPostJSON(SERVER_PATH+"api/Charge/PostChargeRecordSta", req.getReqData(doEncrypt), getAuthHeader());
		TeldApiResp resp = APICallHelper.fromJson(result, TeldApiResp.class);
		
		after(resp);
		resp = resp == null ? TeldApiResp.FAIL : resp;
		return resp;
	}
	
	/**
	 * 获取充电订单明细
	 * @param chargeCode 充电订单编号
	 * @return
	 */
	public static TeldApiResp queryChargeRecord(String chargeCode){
		before();
		TeldApiReq req = new TeldApiReq();
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("chargeCode", chargeCode);
		
		req.setRequestMsg(msg);
		String result = APICallHelper.doPostJSON(SERVER_PATH+"api/Charge/PostChargeDetailRecord", req.getReqData(doEncrypt), getAuthHeader());
		TeldApiResp resp = APICallHelper.fromJson(result, TeldApiResp.class);
		
		after(resp);
		resp = resp == null ? TeldApiResp.FAIL : resp;
		return resp;
	}
	
	public static void main(String[] args) {
		TeldApiResp resp;
		resp = queryStas();
		if(resp.success()){
			Map<String, Object> re = resp.getResultValue();
			List<?> l = (List<?>)re.get("staList");
			System.out.println(l);
		}
		
		resp = getStaDetail("1101150019");
		if(resp.success()){
			Map<String, Object> re = resp.getResultValue();
			List<?> l = (List<?>)re.get("pileList");
			System.out.println(l);
		}
		
		resp = getPileDetail("1101150019206");
		if(resp.success()){ 
			Map<String, Object> re = (Map)resp.getResultValue();
			System.out.println(re);
		}
//		
	}
}
