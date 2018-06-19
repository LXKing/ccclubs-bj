package com.ccclubs.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.SrvActionScript;
import com.lazy3q.util.Function;
import com.lazy3q.util.IbatisMap;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.DateExp;
import com.lazy3q.lang.*;



public class ActionHelper {
	
	
	/**
	 * 处理页面的视图数据，比如显示哪些列，自定义显示列等
	 */
	public static void doPageViewFields(Class modelClass){
		String className = modelClass.getSimpleName();//类名		
		Boolean all = $.setRequest("all", $.getBoolean("all")) ;//页面是否选择了显示全部字段
		String strFields = $.or($.getString("fields"), $.getCookieValue(""+className+"Fields"));
		if($.getBoolean("all")==null && $.getString("fields")==null)//如果all和fields都为空，表示进入列表页
			all = $.setRequest("all", $.getBoolean("all",$.parseBool($.getCookieValue(""+className+"All")))) ;//页面是否选择了显示全部字段
		if($.getRequest("ctrl")==null && $.getString("Q")==null && $.getString("T")==null)
			$.setCookie($.getServletRequest().getServletPath().replaceAll("/[^\\/]+$", ""), ""+className+"All", ""+(all==null?"":all));
		$.setRequest("fields", strFields);
		if(all==null && !$.empty(strFields)){//如果选择了自定义显示字段（各显示字段以,号分隔）
			if($.getBoolean("setFields",false)==true){//如果是用户设置了自定义显示字段，则放入cookie
				$.setCookie($.getServletRequest().getServletPath().replaceAll("/[^\\/]+$", ""), ""+className+"Fields", strFields);
				$.putQueryStringNotAt("setFields");
			}
			String[] fields = strFields.split(",");//把传过来的自定义显示字段拆下来				
			Map<String,Integer> defines = new HashMap();//转成Map类型
			List<String> childes = null;
			Map<String,Boolean> children = new HashMap();
			for(String key:fields){
				if(key.indexOf(".")==-1){//当前模型的直接字段
					defines.put(key, 0);//默认为无子节点
				}else{//关联模型的间接字段
					if(childes==null)
						childes = new java.util.ArrayList();
					String[] array = key.split("\\.");
					if(defines.get(array[0].replace("[]", ""))!=null){
						defines.put(array[0].replace("[]", ""),1+$.or(defines.get(array[0].replace("[]", "")),0));//修改子节点数
						childes.add(key);
						children.put(key.replace("[]", ""),true);
					}
				}
			}
			$.setRequest("fields", strFields);
			$.setRequest("defines", defines);//又丢到request中
			$.setRequest("childes", childes);//又丢到request中	
			$.setRequest("children", children);	
		}
	}
	
	
	/**
	 * 根据对象转为数据库需要的set条件
	 * @param csMemberInfo
	 * @return
	 */
	public static Map getSetValuesFromModel(Object object) {
		Map values=new HashMap();
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field field:fields){
			column column = field.getAnnotation(column.class);
			if(column==null)continue;
			try {
				field.setAccessible(true);
				Object value = field.get(object);
				if(value!=null)
					values.put(field.getName(),value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return values;
	}
	
	
	/**
	 * 通过类结构反射获取数据数据库查询Map数据<br/> 
	 * prefix 前缀
	 * ClassName:实体java类的类名<br/>
	 * classField:实体java类的字段名<br/>
	 * tableColum:数据库的字段名<br/>
	 * {<br/>
	 * 		"[classField]"：value,					//多个键值对<br/>
	 * 		"[classField]Not":"not",				//该字段查询条件取反<br/>
	 * 		"desc"/"asc":[tableColum],			//降序/升序的条件<br/>
	 * 		"definex":sql,						//自定义查询条件<br/>
	 * 		"[ClassName]":{						//连接查询的类名<br/>
	 * 			....							//其值为Map，结构同上一级<br/>
	 * 			"on":[tableColum]					//可不指定，但如果原对象有两个字段绑定到当前对象，指定当前表关联到原对象哪个字段,
	 * 		}<br/>
	 * }<br/>
	 */
	public static Map getQueryFormParams(Class modelClass){
		return getQueryFormParams(modelClass,"");
	}
	@SuppressWarnings("unchecked")
	public static Map getQueryFormParams(Class modelClass,String prefix){
		IbatisMap objects=new IbatisMap();
		{
			//把request传过来的(String)参数转换成java对象			
			Field[] fields = Class(modelClass,"F").getFields();
			for(Field field:fields){
				type type = field.getAnnotation(type.class);
				like like = field.getAnnotation(like.class);
				if(type==null)continue;
				try {
					Object value = $.getObject(prefix+field.getName(),type.value());
					if(value!=null)
						objects.put(field.getName(),value);
					if(type.value()==String.class && $.empty((String)value)){
						objects.remove(field.getName());
					}else if(!$.empty(prefix) && like!=null){
						String sValue = (String) value;
						if(!$.empty(sValue))
							objects.put(field.getName(),"%"+sValue+"%");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					String strYesNot = $.getString(prefix+field.getName()+"YesNot");
					if($.equals("not", strYesNot)){
						objects.put(prefix+field.getName()+"Not", "not");
					}else if($.equals("yes", strYesNot)){
						objects.put(prefix+field.getName()+"Not", "");
					}
					if(!$.empty(strYesNot))
						objects.put(prefix+field.getName()+"YesNot", strYesNot);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//获取日期表达式值
			for(Field field:fields){
				type type = field.getAnnotation(type.class);
				if(type==null || type.value()!=Date.class)continue;
				if(field.getName().endsWith("Start")||field.getName().endsWith("End"))continue;
				String value = $.getString(prefix+field.getName()+"Exp");
				if($.empty(value))continue;
				DateExp dateExp = $.DateExp(value.toString());
				objects.put(field.getName()+"Start",dateExp.start);
				objects.put(field.getName()+"End",dateExp.end);
			}
		}
		
		if($.empty(prefix)){
			//取连接查询的对象名称
			Field[] fields = modelClass.getDeclaredFields();
			for(Field field:fields){
				relate relate = field.getAnnotation(relate.class);
				column column = field.getAnnotation(column.class);
				if(relate==null || column==null)continue;//如果未关联对象	
				if($.getBoolean(field.getName()+"$on",false)==true){
					Field mappingField = null;
					try{
						mappingField = modelClass.getDeclaredField(relate.value());//否则根据关联对象取该类映射字段
					}catch(Exception ex){}
					if(mappingField==null)continue;
					Class<?> relatClass = mappingField.getType();			
					Map relateObject = getQueryFormParams(relatClass,field.getName()+"$");
					if(relateObject.isEmpty())continue;
					relateObject.put("on", column.value());
					if(relateObject!=null){
						//默认以对象模型作为子Map键
						objects.put(relatClass.getSimpleName(),relateObject);
						//放入键值重定义触发字段，用调用到ForMap(字段名称)时，把Map键指向当前子Map
						objects.put($.ForMat(field.getName()),relatClass.getSimpleName());
						//当前子Map放入别名
						relateObject.put("suffix", "_"+column.value());
						//放入当前子Map
						objects.put($.ForMat(field.getName()),relateObject);
					}
				}
			}
		}
		
		return objects;
	}
	
	/**
	 * 重置请求参数,主要是把百分号去掉一下
	 * @param params
	 */
	public static void resetQueryFormParams(Map params){
		for(Object key:params.keySet()){
			Object value = params.get(key);
			if(value==null)continue;
			if(value instanceof String){
				params.put(key, ((String)value).replace("%", ""));
			}else if(value instanceof Map){
				resetQueryFormParams((Map) value);
			}
		}
	}
	
	/**
	 * 获取内部类
	 * @param modelClass
	 * @param internalClassName
	 * @return
	 */
	private static Class Class(Class modelClass,String internalClassName){
		Class[] classes = modelClass.getDeclaredClasses();
		for(Class cls:classes)
			if(cls.getSimpleName().equals(internalClassName))
				return cls;
		return null;
	}
	
	
	public static Map<String,SrvActionScript> action_scripts = new HashMap();//代码脚本主键缓存
	public static void loadActionActions(){
		action_scripts.clear();
		List<SrvActionScript> actions = SrvActionScript.m().ssStatus((short)1).list(-1);
		for(SrvActionScript srvActionScript:actions){
			action_scripts.put(srvActionScript.getSsModelClass()+"-"+srvActionScript.getSsEvent(), srvActionScript);
		}
	}
	static{
		loadActionActions();
	}
	/**
	 * 执行Action脚本
	 * @param ssCode
	 * @param object
	 * @param old
	 * @throws Exception 
	 */
	public static void executeActionScript(Class cls,String event, Object object,Object old) throws Exception {
		String key = cls.getName()+"-"+event;
		SrvActionScript srvActionScript = action_scripts.get(key);
		if(srvActionScript!=null){
			String paramsCode = "";
			//把新旧对象参数定义放到代码的前面，看起来好像跟真的(函数)似的
			String className = object.getClass().getSimpleName();
			paramsCode+=className+" object=("+className+")args[0];\r\n";
			paramsCode+=className+" old=("+className+")args[1];\r\n";
			ScriptJava script = ScriptJava.create(className+"ActionScript", paramsCode+srvActionScript.getSsCode() , new String[]{"com.ccclubs.model.*"});
			//执行脚本
			script.execute(object,old);
		}
	}
	
	
}
