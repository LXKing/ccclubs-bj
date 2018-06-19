package com.ccclubs.action.official;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.service.admin.ICsUnitUserService;
import com.lazy3q.lang.api;
import com.lazy3q.web.helper.$;

public class OfficialIndexAction {
	
	public ICsUnitUserService csUnitUserService;
	
	public String execute(){
		//ICsUnitUserService csUnitUserService = $.getBean("csUnitUserService");
		CsUnitUser csUnitUser = csUnitUserService.getCsUnitUserById(UnitLoginHelper.getLogin().getCsuuId());
		UnitLoginHelper.getLogin().setCsuuLimit(csUnitUser.getCsuuLimit());
		return "index";
	}
	
	/**
	 * API ajax请求
	 * @return
	 */
	public String api(){
		try{
			String className = $.getString("className");
			String methodName = $.getString("methodName");			
			Class<?> cls = Class.forName(className);
			Map<String,Method> methods = new HashMap();
			for(Method method:cls.getDeclaredMethods()){
				methods.put(method.getName(), method);
			}
			//if(cls.getAnnotation(api.class)==null)
				//throw new Exception("类 "+className+" 未标识为@api");
			Method method = methods.get(methodName);
			if(method.getAnnotation(api.class)==null)
				throw new Exception("函数 "+methodName+" 未标识为@api");
			Class<?>[] paramTypes = method.getParameterTypes();
			Object[] params=new Object[paramTypes.length];
			for(int i=0;i<paramTypes.length;i++){
				String key = "$"+i;
				Class<?> paramType = paramTypes[i];
				String typeName = paramType.getSimpleName();
				String str = $.getString(key);
				if(typeName.equals("Byte"))
					params[i]=$.getByte(key);
				else if(typeName.equals("Short"))
					params[i]=$.getShort(key);
				else if(typeName.equals("Integer"))
					params[i]=$.getInteger(key);
				else if(typeName.equals("Long"))
					params[i]=$.getLong(key);
				else if(typeName.equals("Double"))
					params[i]=$.getDouble(key);
				else if(typeName.equals("Float"))
					params[i]=$.getFloat(key);
				else if(typeName.equals("Boolean"))
					params[i]=$.getBoolean(key);
				else if(typeName.equals("Date"))
					params[i]=$.getDate(key);
				else if(typeName.equals("String"))
					params[i]=$.getString(key);
				else if(typeName.equals("Map"))
					params[i]=$.getJson(key,new HashMap());				
			}
			Object value = method.invoke(null, params);
			return $.SendAjax(value, $.UTF8);
		}catch(Exception ex){			
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendAjax($.add("exception", ex).add("message", ex.getMessage()), $.UTF8);
		}
	}

	public String frame()
	{
		return "frame";
	}
	
	public String left()
	{		
		return "left";
	}
	
	public String welcome(){
		return "welcome";
	}
	
	public String separator()
	{
		return "separator";
	}
	
	public String switcher()
	{
		return "switcher";
	}	
	
	public String right()
	{
		return "right";
	}
		
	public String header()
	{		
		return "header";
	}
	
	/**
	 * 设置风格
	 * @return
	 */
	public String style(){
		try{		
			String style = $.getString("css");
			$.setCookie("/", "style", style);
			$.setSession("style", style);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return $.Redirect("index.do");
	}

	public ICsUnitUserService getCsUnitUserService() {
		return csUnitUserService;
	}

	public void setCsUnitUserService(ICsUnitUserService csUnitUserService) {
		this.csUnitUserService = csUnitUserService;
	}
	
}
