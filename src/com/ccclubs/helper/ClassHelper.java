package com.ccclubs.helper;

import java.io.Serializable;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;


import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;

public class ClassHelper {
	
	static ClassPool classpool = ClassPool.getDefault();
	static{
		ClassClassPath classPath = new ClassClassPath(ClassHelper.class);
		classpool.insertClassPath(classPath);
	}
	/**
	 * 根据接口解析接口信息
	 * @param service
	 * @return
	 * @throws NotFoundException 
	 */
	public static MethodParamInfo[] getMethodParamInfos(String className,String methodName) throws Exception {
		classpool.appendSystemPath();
		CtClass serviceClass = classpool.get(className);
		CtMethod[] methods = serviceClass.getMethods();
		CtMethod ctMethod = null;
		for(CtMethod method:methods){
			if(method.getName().equals(methodName))
				ctMethod = method;
		}
		if(ctMethod.getAnnotation(api.class)==null)
			throw new Exception("api_name参数错误");
		MethodInfo ctMethodInfo = ctMethod.getMethodInfo();
		CodeAttribute codeAttribute = ctMethodInfo.getCodeAttribute();
		if(codeAttribute==null)
			throw new Exception("api_name参数错误");
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		CtClass[] paramTypes = ctMethod.getParameterTypes();
		MethodParamInfo[] methodParamInfos= new MethodParamInfo[paramTypes.length];
		int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
		for(int j=0;j<paramTypes.length;j++){
			MethodParamInfo methodParam = methodParamInfos[j] = new MethodParamInfo();
			methodParam.typeName = paramTypes[j].getName();
			methodParam.type = Class.forName(methodParam.typeName);
			methodParam.typeName = methodParam.type.getSimpleName();
			methodParam.name = attr.variableName(pos+j);
			try{methodParam.caption = ((caption)(paramTypes[j].getAnnotation(caption.class))).value();}catch(Exception ex){}
			methodParam.array = paramTypes[j].isArray();
		}
		return methodParamInfos;
	}

	
	
	public @caption("类型信息") static class MethodParamInfo implements Serializable{
		private static final long serialVersionUID = 1L;
		public @caption("类型") Class type;
		public @caption("类型名称") String typeName;
		public @caption("字段名称") String name;
		public @caption("字段描述") String caption;
		public @caption("是否数组") Boolean array;
		public @caption("复杂类型字段") MethodParamInfo[] fields;
	}
	
}
