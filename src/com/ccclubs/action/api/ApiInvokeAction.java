package com.ccclubs.action.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.rmi.CORBA.Tie;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.ccclubs.action.weixin.LoginAction;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.SrvApi;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.model.SrvApiLog;
import com.ccclubs.model.SrvOauth;
import com.lazy3q.lang.ScriptJava;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.FremarkerHelper;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class ApiInvokeAction {
	
	public List<Map> parseParam(String method, String params) throws UnsupportedEncodingException{
		List<Map> paramArray = new ArrayList();
		if(method.equals("com.ccclubs.alipay.callback")){
			Object paramJson = $.eval(params);
			if(paramJson instanceof Map){
				Map newParam = new HashMap();
				Map param = (Map) paramJson;
				for(Object k : param.keySet()){
					Object val = param.get(k);
					String newVal = URLDecoder.decode(val.toString(), "UTF-8");
					newParam.put(k, newVal);
				}
				paramArray.add((Map) newParam);
			}else{//客户端传过来的参数[{},{}...]
				paramArray = (List<Map>) paramJson;
			}
		}else{
			Object paramJson = $.eval(URLDecoder.decode(params, "UTF-8"));
			if(paramJson instanceof Map){
				paramArray.add((Map) paramJson);
			}else{//客户端传过来的参数[{},{}...]
				paramArray = (List<Map>) paramJson;
			}
		}
		
		return paramArray;
	}
	
	public String execute(){
		String method = "";
		String params = "";
		String timestamp = "";
		String appid = "";
		try{
			method = $.getString("method");//调用接口
			params = $.getString("params");//调用参数
			timestamp = $.getString("timestamp");//时间戳
			appid = $.getString("appid");//appid
			String token = $.getString("token");//用户标识
			String sign = $.getString("sign");//参数签名
			
			$.setSession("token", token);
			$.setSession("appid", appid);
						
			if($.empty(method)||$.empty(timestamp)||$.empty(appid)||$.empty(params)||$.empty(sign))
				return $.SendAjax($.add("error","invalid_param").add("message", "参数不能为空"), $.UTF8);
			
			Date timeStamp = $.date(timestamp, "yyyy-MM-dd HH:mm:ss");
			if(Math.abs(new Date().getTime()-timeStamp.getTime())>1000*60*10)
				return $.SendAjax($.add("error","invalid_timestamp").add("message", "无效的调用时间"), $.UTF8);
			
			SrvApiClient apiClient = SrvApiClient.m().sacClient(appid).sacStatus((short)1).get();
			if(apiClient==null)
				return $.SendAjax($.add("error","invalid_client").add("message", "无效的客户端"), $.UTF8);
			
			$.setLocal("client", apiClient);
			$.setSession("token", token);
			
			//检验签名
			if(!SystemHelper.verifySign(apiClient.getSacSecret(), sign, "appid","method","params","token","timestamp"))
				return $.SendAjax($.add("error","invalid_sign").add("message", "参数签名验证失败"), $.UTF8);
			
			List<Map> paramArray = parseParam(method, params);
			String[] methods = method.split(",");
			if(paramArray.size()!=methods.length)
				return $.SendAjax($.add("error","invalid_method_param").add("message", "参数组数和接口组数不对应"), $.UTF8);
			
			
			Long oauth = null;//授权用户ID
			if(!$.empty(token)){
				SrvOauth srvOauth = SrvOauth.m().soApiClient(apiClient.getSacId()).soToken(token).get();
				if(srvOauth!=null){
					oauth = srvOauth.getSoUserId();
					$.setSession("oauth", oauth);
				}
			}
			
			
			String strReturnJson = "";
			String apiIds = "";
			//因为同时可以调用多个接口，所以根据接口名分别调用不同的接口，最后拼成一个数组型的JSON还给客户端
			for(int i=0;i<methods.length;i++){
				String strMethod=methods[i];
				Map clientParamMap = paramArray.get(i);//接口名对应传过来的参数组
				SrvApi srvApi = SrvApi.Get($.add(SrvApi.F.saNamespace, strMethod).add(SrvApi.F.saStatus, 1));
				if(srvApi==null)
					return $.SendAjax($.add("error","invalid_method").add("message", "调用接口名无效 - "+strMethod), $.UTF8);
				apiIds+=(i>0?",":"")+srvApi.getSaId();
				List<Map> paramInfos = $.eval(srvApi.getSaParams());//参数结构描述
				List<Object> objects = new ArrayList();//转换过后的参数
				if(paramInfos==null)paramInfos = new ArrayList<Map>();
				for(Map info:paramInfos){
					String type = (String) info.get("type");//参数类型
					String name = (String) info.get("name");//参数名称
					String notnull = (String) info.get("notnull");//是否不能为空
					String defaultValue = (String) info.get("defaultValue");//默认值
					Object value = clientParamMap.get(name);//参数值
					if(value==null && $.equals(notnull, "true"))
						return $.SendAjax($.add("error","invalid_invalid_param").add("message", "接口"+strMethod+"参数"+name+"不能为空"), $.UTF8);
					else if(value==null){//传来空值
						value = $.empty(defaultValue)?null:defaultValue;
					}else{
						if(Pattern.matches("Long|Short|Byte|Double|Float|Integer", type)  && !(value instanceof Number))
							return $.SendAjax($.add("error","invalid_invalid_param").add("message", "接口"+strMethod+"参数"+name+"必须是数字型"), $.UTF8);
						if(Pattern.matches("Boolean", type)  && !(value instanceof Boolean))
							return $.SendAjax($.add("error","invalid_invalid_param").add("message", "接口"+strMethod+"参数"+name+"必须是布尔型"), $.UTF8);
						if(Pattern.matches("String|Date", type)  && !(value instanceof String))
							return $.SendAjax($.add("error","invalid_invalid_param").add("message", "接口"+strMethod+"参数"+name+"必须是字符串类型"), $.UTF8);
						if(Pattern.matches("List", type)  && !(value instanceof List))
							return $.SendAjax($.add("error","invalid_invalid_param").add("message", "接口"+strMethod+"参数"+name+"必须是List类型"), $.UTF8);
						if(Pattern.matches("Map", type)  && !(value instanceof List))
							return $.SendAjax($.add("error","invalid_invalid_param").add("message", "接口"+strMethod+"参数"+name+"必须是Map类型"), $.UTF8);
					}
					objects.add(value==null?null:value);
				}
				
				if(oauth==null && srvApi.getSaOauth().shortValue()==1)
					return $.SendAjax($.add("error","invalid_invalid_oauth").add("message", "接口"+strMethod+"需要用户授权"), $.UTF8);
				
				String json = SystemHelper.executeApiScript(
					"ApiScript"+srvApi.getSaId(), 
					srvApi.getSaJavaCode(), 
					srvApi.getSaReturnFormat(),
					paramInfos, objects.toArray(),
					oauth
				);

				strReturnJson+=(i>0?",":"")+json;				
			}
			if(methods.length>1)
				strReturnJson = "["+strReturnJson+"]";
			
			new SrvApiLog(
				apiClient.getSacId()//调用应用 [非空]
			 	,apiIds//调用API [非空]
			 	,$.json($.getServletRequest().getParameterMap())//调用参数 [非空]
			 	,""//返回数据 [非空]
			 	,new Date()//添加时间 [非空]
			 	,(short)1//状态 [非空]
			).save();
			
			return $.SendAjax(strReturnJson, $.UTF8);
		}catch(Exception ex){
			ex.printStackTrace();
			String classPath;
			PrintStream ps = null;
			FileOutputStream fos = null;
			try {
				String classPath2 = ApiInvokeAction.class.getResource("/").toURI().getPath() + "apiLogs/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_apiInvokeErr.txt";
				classPath = ApiInvokeAction.class.getResource("/").toURI().getPath() + "apiLogs/" + DateHelper.formatDate(new Date(), "yyyyMMddHHmmssSSS")+"-"+appid+ ".txt";
				File fl = new File(classPath);
				fos = new FileOutputStream(fl);
				ps = new PrintStream(fos);
				ex.printStackTrace(ps);
				WeixinHelper.writeAppendToFile(classPath2, method+"#"+params+"#"+timestamp+"#"+appid+"#"+ex.getMessage()+ex.toString(), "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(fos!=null)
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				if(ps!=null)ps.close();
			}
			return $.SendAjax($.add("error","system_error_exception").add("message", "系统繁忙"), $.UTF8);
		}
	}

}
