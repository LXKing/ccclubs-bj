package com.ccclubs.action.api.http;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ccclubs.action.app.v1.JsonFormat;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.ClassHelper;
import com.ccclubs.helper.ClassHelper.MethodParamInfo;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsRemote;
import com.ccclubs.service.admin.ICsRemoteService;
import com.lazy3q.lang.api;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

public class JsonAction {
	
	public String execute(){
		try{
			String client_id = $.getString("client_id");
			String client_secret = $.getString("client_secret");
			if($.empty(client_id)||$.empty(client_secret))
				throw new Exception("用户密码参数未传入");
			SrvApiClient srvApiClient = SrvApiClient.Get($.add(SrvApiClient.F.sacClient, client_id).add(SrvApiClient.F.sacSecret, client_secret));
			if(srvApiClient==null)
				throw new Exception("用户密码输入错误");
			String apiName = $.getString("api_name");
			String apiId = apiName.substring(0,apiName.lastIndexOf("."));
			String methodName = apiName.substring(apiName.lastIndexOf(".")+1);
			Object bean = $.getBean(apiId);
			MethodParamInfo[] methodParamInfos = ClassHelper.getMethodParamInfos(bean.getClass().getName(), methodName);
			Object[] params=new Object[methodParamInfos.length];
			Class<?>[] parameterTypes = new Class<?>[methodParamInfos.length];
			for(int i=0;i<methodParamInfos.length;i++){
				MethodParamInfo methodParamInfo = methodParamInfos[i];
				parameterTypes[i]=methodParamInfo.type;
				String typeName = methodParamInfo.typeName;
				String paramName = methodParamInfo.name;
				String str = $.getString(paramName);
				if(typeName.equals("Byte"))
					params[i]=$.getByte(paramName);
				else if(typeName.equals("Short"))
					params[i]=$.getShort(paramName);
				else if(typeName.equals("Integer"))
					params[i]=$.getInteger(paramName);
				else if(typeName.equals("Long"))
					params[i]=$.getLong(paramName);
				else if(typeName.equals("Double"))
					params[i]=$.getDouble(paramName);
				else if(typeName.equals("Float"))
					params[i]=$.getFloat(paramName);
				else if(typeName.equals("Boolean"))
					params[i]=$.getBoolean(paramName);
				else if(typeName.equals("Date"))
					params[i]=$.getDate(paramName);
				else if(typeName.equals("String"))
					params[i]=$.getString(paramName);
				else if(typeName.equals("Map"))
					params[i]=$.getJson(paramName,new HashMap());				
			}
			Method method = bean.getClass().getMethod(methodName, parameterTypes);
			Object value = method.invoke(null, params);
			return $.SendAjax(value, $.UTF8);
		}catch(Exception ex){			
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendAjax($.add("exception", ex).add("message", ex.getMessage()), $.UTF8);
		}
	}
	/**
	 * 远程控制开门
	 * @param number
	 * @param type
	 * @return
	 */
	public @api static Short control(String number,Short type){
		try{
			if(type.shortValue()!=0 && type.shortValue()!=1)
				return -11;
			CsCar csCar = CsCar.Get($.add("definex", "csc_number like '%"+number.replace("'", "''")+"%'"));
			if(csCar==null)
				return -12;
			//取近的一次远程操作
			CsRemote lastRemote = CsRemote.getCsRemote(
					$.add(CsRemote.F.csrType, type)
					.add(CsRemote.F.csrCar, csCar.getCscId())
					.add("desc", CsRemote.C.csrAddTime)
				);	
			CsRemote csRemote = new CsRemote();
			csRemote.setCsrNumber(csCar.getCscNumber());
			csRemote.setCsrCar(csCar.getCscId());
			csRemote.setCsrEditor(0l);
			csRemote.setCsrAddTime(new Date());
			csRemote.setCsrState((short)2);
			csRemote.setCsrStatus((short)0);			
			csRemote.setCsrType(type);
			csRemote.setCsrHost(csCar.getCscHost());
			csRemote.setCsrRemark("API客户端["+$.getString("client_id")+"]执行远程操作");
			csRemote=csRemote.save();
			
			com.ccclubs.service.common.impl.RemoteService.dealRemoteCommend(csRemote);			
			
			return 1;
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return -13;
		}
	}
	
	
}
