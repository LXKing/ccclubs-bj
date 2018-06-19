package com.ccclubs.helper;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsLog;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ICsLogService;
import com.lazy3q.lang.Feflect;
import com.lazy3q.lang.caption;
import com.lazy3q.web.helper.$;

/**
 * 自助日志助手
 * @author uiu
 */
public class LoggerHelper{
	static Map<String,Long> modelmapping = new HashMap();
	static Map<String,Long> typemapping = new HashMap();
	public static Long getModelId(Class modelClass){
		String className = modelClass.getName();
		Long modelid = modelmapping.get(className);
		if(modelid!=null)
			return modelid;
		SrvProperty modelRootProperty = SrvProperty.Get($.add(SrvProperty.F.spFlag, "model_class"));
		if(modelRootProperty==null){
			modelRootProperty = new SrvProperty(
			 	"日志操作模块"//名称 [非空]
			 	,0l//类型 [非空]
			 	,"log_model"//标识
			 	,null,null,null
			 	,(short)1//状态 [非空]
			 ).save();
		}
		SrvProperty modelProperty = SrvProperty.Get($.add(SrvProperty.F.spParent,modelRootProperty.getSpId()).add(SrvProperty.F.spFlag,className));
		if(modelProperty==null){
			caption caption = (caption) modelClass.getAnnotation(caption.class);
			modelProperty = new SrvProperty(
					caption == null ? modelClass.getSimpleName() : caption.value()//名称 [非空]
				 	,modelRootProperty.getSpId()//类型 [非空]
				 	,className//标识
				 	,null,null,null
				 	,(short)1//状态 [非空]
				 ).save();
		}
		modelmapping.put(modelProperty.getSpFlag(), modelProperty.getSpId());
		return modelProperty.getSpId();
	}
	public static Long getTypeId(String optionType){
		Long typeid = typemapping.get(optionType);
		if(typeid!=null)
			return typeid;
		SrvProperty typeRootProperty = SrvProperty.Get($.add(SrvProperty.F.spFlag, "log_type"));
		if(typeRootProperty==null){
			typeRootProperty = new SrvProperty(
			 	"日志操作类型"//名称 [非空]
			 	,0l//类型 [非空]
			 	,"log_type"//标识
			 	,null,null,null
			 	,(short)1//状态 [非空]
			 ).save();
		}
		SrvProperty typeProperty = SrvProperty.Get($.add(SrvProperty.F.spParent,typeRootProperty.getSpId()).add(SrvProperty.F.spFlag,optionType));
		if(typeProperty==null){
			typeProperty = new SrvProperty(
					optionType//名称 [非空]
				 	,typeRootProperty.getSpId()//类型 [非空]
				 	,optionType//标识
				 	,null,null,null
				 	,(short)1//状态 [非空]
				 ).save();
		}
		typemapping.put(typeProperty.getSpFlag(), typeProperty.getSpId());
		return typeProperty.getSpId();
	}
	
	

	/**
	 * 存储日志
	 * @param what 做了些什么
	 * @param userId 后台用户ID
	 * @param object 关联的对象
	 */
	public static void writeLog(Class modelClass,String optionType,String what, Long userId, Object relate,Long dataid) {
		final CsLog log = new CsLog(
				LoginHelper.getLogin()==null?null:SYSTEM.getDefaultHost()
				,what//操作名称 [非空]
			 	,modelClass==null?null:getModelId(modelClass)//操作模块
			 	,getTypeId(optionType)//操作类型
			 	,userId==null?0l:userId//操作人 [非空]
			 	,modelClass==null?null:modelClass.getSimpleName()//模块类名
			 	,dataid//数据ID
			 	,$.json(relate)//数据状态
			 	,new Date()//添加时间 [非空]
			 );
		new Thread(){
			public void run(){
				log.save();
			}
		}.start();		
	}
	public static void writeLog(Class modelClass,String optionType,String what, Long userId, Object relate) {		
		LoggerHelper.writeLog(modelClass, optionType, what, userId, relate, null);
	}
	
	/**
	 * 获取对象修改描述
	 * @param oldObject
	 * @param newObject
	 * @return
	 */
	public static String getUpdateDescription(Object oldObject,Object newObject,Boolean bAll) {
		String strDescription = "";
		Field[] fields = newObject.getClass().getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			if(field.getAnnotation(caption.class)==null)
				continue;
			String fieldName = field.getName();
			try{
				String oldValue = oldObject==null?"":Feflect.getFieldValue(oldObject, fieldName);
				String newValue = Feflect.getFieldValue(newObject, fieldName);
				if(!$.equals(oldValue, newValue) && (bAll || !$.empty(newValue))){
					String caption = Feflect.getFieldCaption(newObject.getClass(), fieldName);
					if(caption.indexOf("修改时间")>-1)
						continue;
					if(!$.empty(strDescription))
						strDescription+=",";
					if(oldObject!=null)
						strDescription+="["+caption+"]由("+oldValue+")变更为("+newValue+")";
					else
						strDescription+="["+caption+"]变更为("+newValue+")";
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return $.empty(strDescription)?"什么也没改":strDescription;
	}
}