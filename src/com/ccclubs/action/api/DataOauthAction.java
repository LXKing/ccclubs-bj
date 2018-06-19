package com.ccclubs.action.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.api.model.Result;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsElecHistory;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.service.admin.ISrvApiClientService;
import com.ccclubs.util.UUIDGenerator;
import com.lazy3q.web.helper.$;

/**
 * 
 * 数据授权
 * 
 * 用于数据交换时，第三方商户调用接口的授权
 * 
 * @author Joel
 *
 */
public class DataOauthAction {
	
	final String CHARSET = "utf-8"; 
	
	final ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
	
	final DefaultJRedisClient<String, CsElecHistory> jr = new DefaultJRedisClient<String, CsElecHistory>(DBIndex.APP_API);
	
	public String execute(){
		
		Result result = new Result();
		
		try{
		
			String client_id 		= $.getString("client_id");
			String client_secret 	= $.getString("client_secret");
			
			if(StringUtils.isEmpty(client_id) || StringUtils.isEmpty(client_secret)){
				return $.SendHtml($.json(result.setResult(true).setCode(1000).setMessage("授权凭证无效")), CHARSET);
			}
			
			SrvApiClient client = srvApiClientService.getSrvApiClient($.add(SrvApiClient.F.sacClient, client_id)
					.add(SrvApiClient.F.sacSecret, client_secret).add(SrvApiClient.F.sacStatus, 1));
			if(client == null || client.getSacId() == null){
				return $.SendHtml($.json(result.setResult(true).setCode(1000).setMessage("授权凭证无效")), CHARSET);
			}
			
			String token = UUIDGenerator.getUUID();
			long expire_in = 60L * 60 * 2;
			jr.set("api-client-oauth-"+token, client_id, expire_in);  //2个小时过期
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("access_token", token);
			data.put("expires_in", expire_in * 60);
			return $.SendHtml($.json(result.setResult(true).setCode(0).setMessage("授权成功").setData(data)), CHARSET);
		}catch(Exception e){
			e.printStackTrace();
			return $.SendHtml($.json(result.setResult(false).setCode(9999).setMessage("系统异常")), CHARSET);
		}
		
	}
	
}
