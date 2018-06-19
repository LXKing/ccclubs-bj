package com.ccclubs.action.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.app.official.model.JsonFormat;
import com.ccclubs.action.app.official.util.SessionMgr;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.APICallHelper;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsElecHistory;
import com.ccclubs.model.SrvApi;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.model.SrvApiLog;
import com.lazy3q.web.helper.$;

public class GatewayAction {
	
	final static String CHARSET = "utf-8";
	
	final static String APPHOST = $.config("app.domain");
	
	final DefaultJRedisClient<String, CsElecHistory> jr = new DefaultJRedisClient<String, CsElecHistory>(DBIndex.APP_API);

	private HttpServletRequest request = ServletActionContext.getRequest();
	
	private String returnError(String code, String message) {
		Logger.getRootLogger().error(code+" : "+message);
		$.SendHtml($.json(JsonFormat.error(code, message)), CHARSET);
		return null;
	}

	private String returnError(Exception ex) {
		if (ex instanceof MessageException) {
			MessageException e = (MessageException) ex;
			return returnError(e.getCode() + "", e.getMessage());
		}
		if (ex instanceof com.ccclubs.exception.ErrorException) {
			com.ccclubs.exception.ErrorException e = (com.ccclubs.exception.ErrorException) ex;
			return returnError("0", e.getMessage());
		}
		
		ex.printStackTrace();
		Logger.getRootLogger().error(ex.getMessage(), ex);
		return returnError("9999", SYSTEM.ERROR_TIPS);
	}
	
	/**
	 * 是否已授权
	 * @return
	 */
	private String isOauth(){
		String access_token = $.getString("access_token");
		if(access_token!=null){
			String client_id = jr.get("api-client-oauth-"+access_token);
			return client_id;
		}
		return "";
	}
	
	public String execute(){
		try {
			String clientId = isOauth();
			
			SrvApiClient client = SrvApiClient.getSrvApiClient($.add(SrvApiClient.F.sacClient, clientId).add(SrvApiClient.F.sacStatus, 1));
			if(StringUtils.isEmpty(clientId)){
				return returnError("1000", "登录授权无效");
			}
			String methodName = $.getString("method_name","");
			if($.empty(methodName)){
				return returnError("1001", "接口不存在");
			}
			SrvApi api = SrvApi.getSrvApi($.add(SrvApi.F.saName, methodName));
			if(api == null){
				return returnError("1001", "接口不存在");
			}
			String url = APPHOST+api.getSaNamespace$();
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				if(name.equals("method_name")){
					continue;
				}
				if(name.equals("access_token")){
					continue;
				}
				String[] values = (String[]) requestParams.get(name);
				for (String value : values) {
					params.put(name, value);
				}
				
			}
			String json = APICallHelper.doPost(url, params);
			if($.empty(url)){
				Logger.getRootLogger().error(clientId+" 客户端调用 ："+methodName + " 方法不存在 ");
			}else{
				SrvApiLog apiLog = new SrvApiLog();
				apiLog.setSalClient(client.getSacId());
				apiLog.setSalParams(params.toString());
				apiLog.setSalReturn(json);
				apiLog.setSalApi(api.getSaId$());
				apiLog.setSalInvokeTime(new Date());
				apiLog.salStatus((short)1);
				apiLog.save();
			}
			JSONObject jsonObject = JSONObject.fromObject(json);
			return $.SendHtml(json, CHARSET);
		} catch (Exception e) {
			// TODO: handle exception
			return returnError(e);
		}
	}
	
	
	
	final static String REGISTER_SMS_CODE = "registercode";//注册账号的校验码
	final static String RESET_SMS_CODE = "resetcode";//重置密码的校验码
	final static String FORGET_SMS_CODE = "forgetcode"; // 忘记密码的校验码
	
	public String codeValue(){
		Integer type = $.getInteger("type",2);
		String mobile = $.getString("mobile");
		Logger.getRootLogger().info("get Code Mobile : "+ mobile +" Type : "+type);
		String code = "";
		switch (type) {
		case 2:
			code = SessionMgr.get(mobile, REGISTER_SMS_CODE);
			break;
		case 0:
			code = SessionMgr.get(mobile, FORGET_SMS_CODE);
			break;
		case 1:
			code = SessionMgr.get(mobile, RESET_SMS_CODE);
			break;	
		}
		return $.SendHtml("{\"code\":\""+code+"\"}", CHARSET);
	}
	
}
