package com.ccclubs.helper;

import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.entity.Invoice;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsState;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvLimited;
import com.ccclubs.model.SrvProject;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.model.SrvQuery;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ISrvHostService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.lang.ScriptJava;
import com.lazy3q.lang.api;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.opensymphony.xwork2.ActionContext;

public class SystemHelper {
	/**
	 * 动态属性分类
	 * 
	 * @author uiu
	 */
	public enum Propertype {		
		model_class("系统数据模型类"),
		log_type("日志操作类型"),
		rcgcard_method("充值卡分类"), ;
		private String	name;
		private Propertype(String name) {
			this.name = name;
		}
	}
		

	/**
	 * 根据属性标识获取属性列表
	 * 
	 * @param propertype
	 * @return
	 */
	public static List<SrvProperty> getPropertys(Propertype type) {
		return getPropertys(type.name, type.name());
	}

	/**
	 * 根据标识获取属性
	 * 
	 * @param flag
	 * @return
	 */
	public static SrvProperty getProperty(String flag) {
		ISrvPropertyService srvPropertyService = Lazy.GetSpringBean("srvPropertyService");
		return srvPropertyService.getSrvProperty($.add(SrvProperty.F.spFlag, flag));
	}

	/**
	 * 根据名称、标识获取系统属性列表
	 * 
	 * @param name
	 * @param flag
	 * @return
	 */
	public static List<SrvProperty> getPropertys(String name, String flag) {
		ISrvPropertyService srvPropertyService = Lazy.GetSpringBean("srvPropertyService");
		SrvProperty srvProperty = srvPropertyService.getSrvProperty($.add(SrvProperty.F.spFlag, flag));
		if (srvProperty == null) {// 如果不存在则创建一个
			new SrvProperty().spParent(0l).spFlag(flag).spName(name).spStatus((short) 1).save();
			srvProperty = srvPropertyService.getSrvProperty($.add(SrvProperty.F.spFlag, flag));
		}
		return srvPropertyService.getSrvPropertyList($.add(SrvProperty.F.spParent, srvProperty.getSpId()), -1);
	}
	
	
	/**
	 * 获取当前应用名称
	 * @return
	 */
	public static String getAplication(){
		String name = ActionContext.getContext().getActionInvocation().getAction().getClass().getName();
		name = name.replace("com.ccclubs.action.", "");
		name = name.substring(0, name.indexOf("."));
		return name;
	}
	
	
	/**
	 * 获取域列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SrvHost> getHosts(){
		LzMap params = $.add(SrvHost.F.shStatus, 1);
		if(!$.empty(LoginHelper.getLogin().getSuHost()))
			params.put("definex", "sh_id in ("+LoginHelper.getLogin().getSuHost()+")");
		params.put("asc", "sh_id");
		return SrvHost.list(params, -1);
	}
	
	/**
	 * 获取车辆状态
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static CsState getCsState(Map params) {
		List<CsState> list = CsState.list(params,-1);
		if(list.size()==1)
			return list.get(0);
		for(CsState csState:list){
			if(csState.getCssNumber().startsWith("EV"))
				return csState;
		}
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 获取查询保存
	 * @param path
	 * @return
	 */
	public static List<SrvQuery> getQuerys(String path) {
		List<SrvQuery> querys = SrvQuery.list(
			$.add(SrvQuery.F.sqPath, path)
			.add(SrvQuery.F.sqStatus, 1)
			.add("definex", " (sq_editor="+LoginHelper.getLoginId()+" or sq_editor=0)")
		, -1);
		return querys;
	}
	
	
	
	
	/**
	 * sql查询并缓存
	 * @param source
	 * @param sql
	 * @return
	 */
	public static List query(String source,String sql)
	{		
		String md5 = $.md5(sql);
		List list = SYSTEM.REDIS.getObject("SQL", md5);
		if(list!=null)return list;
		list = $.getDao(source).executeQuery(sql);
		SYSTEM.REDIS.setObject("SQL", md5, list, (int) ((SYSTEM.HOUR/1000)+$.rand((int) (SYSTEM.HOUR/1000))));
		return list;
	}
	public static List query$(String source,String sql, Object value)
	{
		String md5 = $.md5(sql+value);
		List list = SYSTEM.REDIS.getObject("SQL", md5);
		if(list!=null)return list;
		list = $.getDao(source).executeQuery(sql,value);
		SYSTEM.REDIS.setObject("SQL", md5, list, (int) ((SYSTEM.HOUR/1000)+$.rand((int) (SYSTEM.HOUR/1000))));
		return list;
	}
	public static List query$$(String source,String sql, Object value1, Object value2)
	{
		String md5 = $.md5(sql+value1+value2);
		List list = SYSTEM.REDIS.getObject("SQL", md5);
		if(list!=null)return list;
		list = $.getDao(source).executeQuery(sql,value1,value2);
		SYSTEM.REDIS.setObject("SQL", md5, list, (int) ((SYSTEM.HOUR/1000)+$.rand((int) (SYSTEM.HOUR/1000))));
		return list;
	}
	public static List query$$$(String source,String sql, Object value1, Object value2,Object value3)
	{
		String md5 = $.md5(sql+value1+value2+value3);
		List list = SYSTEM.REDIS.getObject("SQL", md5);
		if(list!=null)return list;
		list = $.getDao(source).executeQuery(sql,value1,value2,value3);
		SYSTEM.REDIS.setObject("SQL", md5, list, (int) ((SYSTEM.HOUR/1000)+$.rand((int) (SYSTEM.HOUR/1000))));
		return list;
	}
	
	
	
	
	
	
	/**
	 * 杀死锁死的sql
	 * @param bForce
	 * @return
	 */
	public static String killLocks(boolean bForce){
		return killLocks(5,true);
	}
	
	
	/**
	 * 杀死锁死的sql
	 * @param bSuper 
	 */
	public static String killLocks(Integer iTime,boolean bForce){
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> list = dao.executeQuery("show processlist");
		List<String> kills = new ArrayList();
		List<Map> infos = new ArrayList();
		int iCount = 0;
		for(Map map:list){
			Integer time = $.parseInt(map.get("Time"));
			String info = (String) map.get("Info");
			String commend = (String) map.get("Command");
			if( time>iTime && $.equals(commend, "Query") && !$.empty(info) && info.indexOf("kill")==-1 && info.indexOf("processlist")==-1){
				kills.add("kill "+map.get("Id"));
				$.trace("kill:"+info);
				infos.add(map);
				iCount++;
			}
		}
		
		if(!infos.isEmpty()){
			LoggerHelper.writeLog(null, "update", "系统加速", LoginHelper.getLoginId(), infos);
		}
		
		if(!kills.isEmpty()){
			$.trace($.json(kills));
			if(!bForce && iCount<10)
				return "系统:自我感觉良好，可能您网速慢，等30秒再试";
			for(String strKillSql:kills)
				dao.executeUpdate(strKillSql);
			return "系统:加速成功，感谢您对我的关心";
		}else{
			return "系统:无需加速，是您的网络或者电脑不给力哦";
		}	
	}
	
	public static void main(String...strings){
		System.out.println(killLocks(false));
	}
	
	
	
	private static Integer exps[]={64,128,256,512,1024,2048,4096,8192,16384,32768,65536};
	public static void getSrvLimit(String strPath){
		$.setRequest("canView",$.getBoolean("canView",false));
		$.setRequest("canAdd",$.getBoolean("canAdd",false));
		$.setRequest("canEdit",$.getBoolean("canEdit",false));
		$.setRequest("canDel",$.getBoolean("canDel",false));
		$.setRequest("canQuery",$.getBoolean("canQuery",true));
		$.setRequest("canExport",$.getBoolean("canExport",false));
		$.setRequest("canStats",$.getBoolean("canStats",false));
		$.setRequest("canExp",null);
		SrvUser srvUser = LoginHelper.getLogin();
		if(srvUser==null || $.empty(strPath))
			return;
		if($.getSession("super_user")!=null){
			$.setRequest("canView",$.getBoolean("canView",true));
			$.setRequest("canAdd",$.getBoolean("canAdd",true));
			$.setRequest("canEdit",$.getBoolean("canEdit",true));
			$.setRequest("canDel",$.getBoolean("canDel",true));
			$.setRequest("canQuery",$.getBoolean("canQuery",true));
			$.setRequest("canExport",$.getBoolean("canExport",true));
			$.setRequest("canStats",$.getBoolean("canStats",true));
			Boolean canExp[]=new Boolean[13];
			for(int i=0;i<13;i++){
				canExp[i]=(true);
			}
			$.setRequest("canExp",canExp);
			return;
		}
		SrvLimited srvLimited = $.getRequest("request."+strPath);
		if($.getRequest(strPath)==null){
			com.ccclubs.service.admin.ISrvLimitedService srvLimitedService = $.GetSpringBean("srvLimitedService");
			srvLimited = srvLimitedService.getUserLimited(srvUser,strPath);
			$.setRequest(strPath, true);
			$.setRequest("request."+strPath, srvLimited);
		}
		if(srvLimited==null)
			return;
		Integer limitsId=srvLimited.getSlLimit();
		if(limitsId!=null){			
			$.setRequest("canView", ((limitsId & 1)==1) && $.getBoolean("canView",true));
			$.setRequest("canQuery",$.getBoolean("canQuery",true));
			$.setRequest("canAdd", ((limitsId & 2)==2) && $.getBoolean("canAdd",true));
			$.setRequest("canEdit", ((limitsId & 4)==4) && $.getBoolean("canEdit",true));
			$.setRequest("canDel", ((limitsId & 8)==8) && $.getBoolean("canDel",true));
			$.setRequest("canExport", ((limitsId & 16)==16) && $.getBoolean("canExport",true));
			$.setRequest("canStats", ((limitsId & 32)==32) && $.getBoolean("canStats",true));
			Boolean canExp[]=new Boolean[exps.length];
			for(int i=0;i<exps.length;i++){
				canExp[i]=(limitsId & exps[i])==exps[i];
			}
			$.setRequest("canExp",canExp);
		}
	}
	/**
	 * 根据路径获取图标
	 * @param strPath
	 * @return
	 */
	public static String getSrvIcon(String strPath){
		com.ccclubs.service.admin.ISrvProjectService srvProjectService = $.GetSpringBean("srvProjectService");
		List<SrvProject> projects = srvProjectService.getSrvProjectList($.add("spUrl", strPath), -1);
		return projects.isEmpty()?"":projects.get(0).getSpLogo();
	}
	
	
	
	/**
	 * 获取车辆图片
	 * @param object
	 * @return
	 */
	public static String getCarThumb(Object object){
		if(object==null)return null;
		if(object instanceof CsCar){
			if(!$.empty(((CsCar)object).getCscPreview()))
				return ((CsCar)object).getCscPreview();
			else
				return ((CsCar)object).get$cscModel().getCscmThumb();
		}
		if(object instanceof CsCarModel)
			return ((CsCarModel)object).getCscmThumb();
		return null;
	}
	
	
	
	/**
	 * 获取车辆图片
	 * @param object
	 * @return
	 */
	public static String getCarPreview(Object object){
		if(object==null)return null;
		if(object instanceof CsCar){
			if(!$.empty(((CsCar)object).getCscPreview()))
				return ((CsCar)object).getCscPreview();
			else
				return ((CsCar)object).get$cscModel().getCscmImage();
		}
		if(object instanceof CsCarModel)
			return ((CsCarModel)object).getCscmImage();
		return null;
	}
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	public static void executeActionScript(Class cls,String event, Object object,Object old) throws Exception {
		ActionHelper.executeActionScript(cls, event, object, old);
	}
	
	
	/**
	 * 创建脚本类
	 * @param className
	 * @param javaCode
	 * @param paramInfos
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public @api static ScriptJava createApiScript(String className, String javaCode,List<Map> paramInfos) throws Exception{
		String paramsCode = "";
		//把参数定义放到代码的前面，看起来好像跟真的(函数)似的
		for(int i=0;i<paramInfos.size();i++){
			Map param=paramInfos.get(i);
			paramsCode+="			"+param.get("type")+" "+param.get("name")+"=("+param.get("type")+")args["+i+"];\r\n";
		}
		paramsCode+="Long oauth = (Long)args["+(paramInfos.size())+"];\r\n";
		ScriptJava script = ScriptJava.create(className, paramsCode+javaCode , new String[]{"com.ccclubs.model.*","com.lazy3q.web.util.*"});
		return script;
	}
	
	/**
	 * 创建脚本类
	 * @param className
	 * @param javaCode
	 * @param paramInfos
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public @api static String executeApiScript(String className,String javaCode,String formatCode,List<Map> paramInfos,Object[] objects ,Long oauth) throws Exception{
		//创建脚本类
		String paramsCode = "";
		//把参数定义放到代码的前面，看起来好像跟真的(函数)似的
		Object[] convertObjects = new Object[paramInfos.size()+1];
		for(int i=0;i<paramInfos.size();i++){
			Map param=paramInfos.get(i);
			String type = (String) param.get("type");
			paramsCode+="			"+type+" "+param.get("name")+"=("+type+")args["+i+"];\r\n";
			//if(objects[i]!=null && objects[i].getClass().getSimpleName().equals(type)){
			if(objects[i]!=null){
				Map<String,String> convert=new HashMap();
				convert.put(param.get("name").toString(), objects[i].toString());
				convertObjects[i] = $.getObject(convert, param.get("name").toString(), Class.forName((Pattern.matches("Date|List|Map", type)?"java.util.":"java.lang.")+type));
			}
		}
		//把授权用户ID放入参数队列
		convertObjects[paramInfos.size()] = oauth;
		
		paramsCode+="Long oauth = (Long)args["+(paramInfos.size())+"];\r\n";
		
		ScriptJava script = ScriptJava.create(className, paramsCode+javaCode , new String[]{"com.ccclubs.model.*","com.lazy3q.web.util.*"});
		//执行脚本
		Object objReturn = script.execute(convertObjects);
		if(objReturn!=null && objReturn instanceof String)
			return "{\"error\":\""+objReturn+"\"}";
		//获取脚本执行的局部变量
		Map<String, Object> variables = script.getVariables();
		//执行Velocity模板
		Velocity.init();
		VelocityContext context = new VelocityContext();
		for(String key:variables.keySet())
			context.put(key,variables.get(key));
		context.put("lz",$.class);
		StringWriter json = new StringWriter();
		Velocity.evaluate(context, json, "ApiJsonFormat"+className, formatCode);
		
		return json.toString();
	}
	
	/**
	 * 检验参数签名
	 * @param secret
	 * @param sign 客方传过来的加密串，格式为secret+(参数名+参数值...)+secret的大写md5值
	 * @param paramNames
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Boolean verifySign(String secret,String sign,String... paramNames){
		//按参数名asscic码排序
		List<String> names=new ArrayList();
		for(String paramName:paramNames)
			names.add(paramName);
		java.util.Collections.sort(names);
		String strSign = secret;
		for(String name:names)
			strSign+=name+$.getString(name,"");
		strSign+=secret;
		String md5 = $.md5(strSign).toUpperCase();
		return $.equals(md5,sign);
	}
	
	
	public @api static void clearKv(String key) {		
		com.lazy3q.sql.cache.KVCacheByFile.clear(key);
	}

	/**
	 * 根据月数获取有效期结束时间
	 * 
	 * @param month
	 * @return
	 */
	public static Date getValidUntil(Short month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
	
	/**
	 * 获取红包序列号
	 * @param csCoin
	 * @return
	 */
	public static String getCoinSerial(CsCoin csCoin) {
		if($.empty(csCoin.getCscFlag()))
			return null;
		return $.md5(csCoin.getCscMember()+csCoin.getCscEnd$()+csCoin.getCscCount()+"-"+Math.abs(csCoin.getCscFlag().hashCode()));
	}
	

	/**
	 * 邮箱合法性判断
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String emailPattern = "^[0-9a-zA-Z][a-zA-Z0-9\\._-]{1,}@[a-zA-Z0-9-]{1,}[a-zA-Z0-9]\\.[a-z0-9\\.]{1,}[a-zA-Z]$";
		boolean result = Pattern.matches(emailPattern, email);
		return result;
	}

	/** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
	
	/**
	 * 手机验证
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (mobile == null)
			return false;
		String mobileRegex = "^(1[3|4|5|7|8|9][0-9])\\d{8}$";
		return Pattern.matches(mobileRegex, mobile);
	}
	
	/**
	 * 是否是身份证号
	 * @param idCard
	 * @return
	 */
	public static Boolean isIdCardNo(String idCard) {
        if(StringUtils.isEmpty(idCard))
            return false;
        String reg = "(^\\d{15}$)|(^\\d{17}(\\d|X|x)$)";
        return Pattern.matches(reg, idCard);
    }

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.length() <= 0)
			return true;
		return false;
	}

	/**
	 * 用户名验证
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isUserName(String username) {
		if (username == null)
			return false;
		return Pattern.matches("^[a-zA-Z0-9\u4e00-\u9fa5]{4,16}$", username);
	}

	/**
	 * 用户密码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isPassword(String username) {
		if (username == null)
			return false;
		return Pattern.matches("^[a-zA-Z0-9]{6,16}$", username);
	}

	/**
	 * 按照定单格式，格式化日期 yyyy-MM-dd HH:mm
	 * 
	 * @param number
	 */
	public static String formatDate(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdFormat.format(date);
	}

	/**
	 * 日期，按照指定格式，格式化
	 * 
	 * @param date
	 * @param fomate
	 * @return
	 */
	public static String formatDate(Date date, String fomate) {
		SimpleDateFormat sdFormat = new SimpleDateFormat(fomate);
		return sdFormat.format(date);
	}
	
	
	/**
	 * 根据两gps坐标获取距离
	 * @param n1
	 * @param e1
	 * @param n2
	 * @param e2
	 * @return 千米
	 */
	public static double getDistanceFromGPSXtoY(double lat_a, double lng_a,double lat_b, double lng_b){
		double pk = (double) (180 / Math.PI);//3.14169
		double a1 = lat_a / pk;
		double a2 = lng_a / pk;
		double b1 = lat_b / pk;
		double b2 = lng_b / pk;
		double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
		double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
		double t3 = Math.sin(a1) * Math.sin(b1);
		double tt = Math.acos(t1 + t2 + t3);
		return 6366 * tt;
	}
	 

	/**
	 * 取整数
	 * 
	 * @param number
	 */
	public static int toInt(double number) {
		int result = 0;
		try {
			if (number <= 0) {
				result = (int) Math.ceil(number);
			} else {
				result = (int) Math.floor(number);
			}
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	/**
	 * 小数点后两位，含小数点
	 * 
	 * @param number
	 */
	public static String getDecimal(double number) {
		String result = LZ.digit(number, 2) + "";
		result = result.substring(result.indexOf('.'), result.length());
		return result;
	}

	/**
	 * 手机号变成星号
	 * 
	 * @param mobile
	 *            15224021377
	 * @return 152****1377
	 */
	public static String mobileConvert2Star(String mobile) {
		return mobile.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1****$3");
	}

	/**
	 * 驾驶证号变成星号
	 * 
	 * @param licnum
	 * @return
	 */
	public static String licnumConvert2Star(String licnum) {
		if (licnum != null && licnum.length() > 0) {
			licnum = licnum.toUpperCase();
			if (licnum.length() == 15) {
				return licnum.replaceAll("(\\d{3})(\\d{8})(\\d{4})", "$1********$3");
			} else {
				return licnum.replaceAll("(\\d{3})(\\d{11})([0-9X]{4})", "$1***********$3");
			}
		} else {
			return licnum;
		}
	}

	/**
	 * 邮箱号变成星号
	 * 
	 * @param mobile
	 *            513387369@qq.com
	 * @return ***7369@qq.com
	 */
	public static String emailConvert2Star(String email) {
		// return email.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1****$3");
		if (email.indexOf("@") > -1) {
			String[] array = email.split("@");
			if (array[0].length() > 4) {
				return "***" + array[0].substring(array[0].length() - 4) + "@" + array[1];
			} else {
				return email;
			}
		}
		return email;
	}

	/**
	 * 根据驾驶证号码，获取生日
	 * 
	 * @param licnum
	 * @return
	 */
	public static Date getBirthday(String licnum) {
		Date birthday = null;
		try {
			switch (licnum.length()) {
				case 15:
					birthday = DateHelper.getDate(Integer.parseInt("19" + licnum.substring(6, 8)), Integer.parseInt(licnum.substring(8, 10)), Integer.parseInt(licnum.substring(10, 12)), 0, 0, 0);
					break;
				case 18:
					birthday = DateHelper.getDate(Integer.parseInt(licnum.substring(6, 10)), Integer.parseInt(licnum.substring(10, 12)), Integer.parseInt(licnum.substring(12, 14)), 0, 0, 0);
					break;
				default:
					birthday = null;
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return birthday;
	}

	/**
	 * 判断会员是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		return Lazy.getSession(SYSTEM.ccclubs_web_login) != null && Lazy.getSession(SYSTEM.ccclubs_web_login_id) != null && (getLogin().getCsmId().equals(getLoginId()));
	}

	/**
	 * 获取会员ID
	 * 
	 * @return
	 */
	public static Long getLoginId() {
		return Lazy.getSession(SYSTEM.ccclubs_web_login_id);
	}

	/**
	 * 获取会员实例
	 * 
	 * @return
	 */
	public static CsMember getLogin() {
		return Lazy.getSession(SYSTEM.ccclubs_web_login);
	}

	/**
	 * 更新当前登录会员
	 * 
	 * @return
	 */
	public static void setLogin(CsMember member) {
		$.setSession(SYSTEM.ccclubs_web_login, member);
		$.setSession(SYSTEM.ccclubs_web_login_id, member.getCsmId());
	}

	/**
	 * 判断当前是否有定单信息
	 * 
	 * @return
	 */
	public static boolean isBaseOrderInfo() {
		return Lazy.getSession(Constant.BASE_ORDER_INFO) != null && (getBaseOrderInf().getCsoStart() != null) && (getBaseOrderInf().getCsoFinish() != null);
	}

	/**
	 * 获取当前定单基本信息实例
	 * 
	 * @return
	 */
	public static BaseOrderInfo getBaseOrderInf() {
		return Lazy.getSession(Constant.BASE_ORDER_INFO);
	}

	/**
	 * 更新当前定单基本信息实例
	 * 
	 * @return
	 */
	public static void setBaseOrderInfo(BaseOrderInfo baseOrderInfo) {
		$.setSession(Constant.BASE_ORDER_INFO, baseOrderInfo);
	}

	
	/**
	 * 获取会员可开票信息
	 * @param memberid
	 * @return
	 */
	public static Invoice getInvoiceInfo(Long memberid) {
		ICommonMoneyService commonMoneyService = Lazy.GetSpringBean("commonMoneyService");
		return commonMoneyService.getInvoiceInfo(memberid);
	}
	
	/** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */  
	public static String getClientIpAddr(){
		HttpServletRequest request = ServletActionContext.getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
	}
	
	/**
	 * 根据IP地址，获得城市
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static SrvHost getSrvHostByClientIp(){
		SrvHost srvHost = null; 
		try{
			ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
			HttpServletRequest request = ServletActionContext.getRequest();
			String ipString = getClientIpAddr();
			String strIpContent = $.http(SYSTEM.IP_FETCH_URL + ipString);
			// String strIpContent = "{\"city\":\"\u676d\u5dde\"}";
			Map mapIpContent = $.eval(strIpContent);
			if (mapIpContent.get("city") != null) {
				
				// Unicode 转化为中文
				String cityNameUnicode = mapIpContent.get("city").toString();
				String cityName = loadConvert(cityNameUnicode.toCharArray(), 0, cityNameUnicode.length(), new char[0]);
				// 查找对应的地域信息
				srvHost = srvHostService.getSrvHost($.add(SrvHost.F.shName, cityName).add(SrvHost.F.shState, 1));
				if (srvHost != null && srvHost.getShId() > 0) {
					setSrvHost(srvHost);
					return srvHost;
				}
				
			}
			// 默认的host信息
			srvHost = getDefaultSrvHost();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return srvHost;
	}
	
	/**
	 * 获得当前的地域信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static SrvHost getSrvHost() {
		ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
		SrvHost srvHost = null; 
		try {
			// 检查session，是否存在 host 信息
			srvHost = $.getSession(SYSTEM.ccclubs_web_host);
			if (srvHost != null && srvHost.getShId() > 0) {
				setSrvHost(srvHost);
				return srvHost;
			}
			// 检查cookie，是否存在 host 信息
			String hostId = $.getCookieValue("hostId");
			if (hostId != null && Long.parseLong(hostId) > 0) {
				srvHost = srvHostService.getSrvHost($.add(SrvHost.F.shId, Long.parseLong(hostId)));
				if (srvHost != null && srvHost.getShId() > 0) {
					setSrvHost(srvHost);
					return srvHost;
				}
			}
			// 根据IP地址，获得城市
			// TODO:获取ip信息超时处理。
			HttpServletRequest request = ServletActionContext.getRequest();
			String ipString = getClientIpAddr();
			String strIpContent = $.http(SYSTEM.IP_FETCH_URL + ipString);
			// String strIpContent = "{\"city\":\"\u676d\u5dde\"}";
			Map mapIpContent = $.eval(strIpContent);
			if (mapIpContent.get("city") != null) {
				// Unicode 转化为中文
				String cityNameUnicode = mapIpContent.get("city").toString();
				String cityName = loadConvert(cityNameUnicode.toCharArray(), 0, cityNameUnicode.length(), new char[0]);
				// 查找对应的地域信息
				srvHost = srvHostService.getSrvHost($.add(SrvHost.F.shName, cityName));
				if (srvHost != null && srvHost.getShId() > 0) {
					setSrvHost(srvHost);
					return srvHost;
				}
			}
			// 默认的host信息
			srvHost = getDefaultSrvHost();
			if (srvHost != null && srvHost.getShId() > 0) {
				setSrvHost(srvHost);
				return srvHost;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 默认的host信息
			srvHost = getDefaultSrvHost();
			if (srvHost != null && srvHost.getShId() > 0) {
				setSrvHost(srvHost);
				return srvHost;
			}
		}

		setSrvHost(srvHost);
		return srvHost;
	}

	/**
	 * 获得系统默认配置的地域
	 * 
	 * @return
	 */
	public static SrvHost getDefaultSrvHost() {		
		ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
		return srvHostService.getSrvHost($.add(SrvHost.F.shId, Long.parseLong($.config("web.host"))));
	}

	/**
	 * 更新地域信息
	 * 
	 * @param srvHost
	 */
	public static void setSrvHost(SrvHost srvHost) {
		if (srvHost != null) {
			// 写session
			$.setSession(SYSTEM.ccclubs_web_host, srvHost);
			// 写cookie
			$.setCookie("/", "hostId", srvHost.getShId().toString());
			// 修改地域信息
			if (SystemHelper.getBaseOrderInf() != null) {
				BaseOrderInfo baseOrderInfo = SystemHelper.getBaseOrderInf();
				baseOrderInfo.setCsoHost(srvHost.getShId());
				SystemHelper.setBaseOrderInfo(baseOrderInfo);
			}
		}
	}

	/*
	 * Converts encoded &#92;uxxxx to unicode chars and changes special saved
	 * chars to their original forms
	 */
	public static String loadConvert(char[] in, int off, int len, char[] convtBuf) {
		if (convtBuf.length < len) {
			int newLen = len * 2;
			if (newLen < 0) {
				newLen = Integer.MAX_VALUE;
			}
			convtBuf = new char[newLen];
		}
		char aChar;
		char[] out = convtBuf;
		int outLen = 0;
		int end = off + len;

		while (off < end) {
			aChar = in[off++];
			if (aChar == '\\') {
				aChar = in[off++];
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = in[off++];
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = (char) aChar;
			}
		}
		return new String(out, 0, outLen);
	}

	/**
	 * 自动生成用户名
	 * 
	 * @param length
	 *            长度
	 * @return
	 */
	public static String randomUsername(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		Object initLock = new Object();
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz-_" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_").toCharArray();
				}
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * action层转跳到登录页
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String gotoLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String context = request.getContextPath();// 上下文路径
			String strMethod = request.getMethod().toUpperCase();// 请求方式
			String strSrc = request.getHeader("Referer");// 源地址
			// 目标地址
			String strDest = context + request.getServletPath();// 目标地址
			String strQueryString = request.getQueryString();// 目标地址参数

			if (strDest != null && !strDest.equals("") && strQueryString != null && !strQueryString.trim().equals("")) {
				strDest += "?" + strQueryString;
			}

			String strDirect = null;// 登录后转跳到的地址

			if (strMethod.equals("GET")) {
				strDirect = strDest;
				if (strDirect == null)
					strDirect = strSrc;
			} else if (strMethod.equals("POST")) {
				strDirect = strSrc;
				if (strDirect == null)
					strDirect = strDest;
			}
			if (strDirect == null) {
				response.sendRedirect($.GetRequest("basePath") + "login.html");
			} else {
				response.sendRedirect($.GetRequest("basePath") + "login.html?from=" + strDirect);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * action层转跳到指定页面，不带"/"斜线
	 * 
	 * @return
	 */
	public static String gotoUrl(String targetUrl) {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.sendRedirect($.GetRequest("basePath") + targetUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置默认的取、还车时间
	 */
	public static BaseOrderInfo setDefaultOrderTime(BaseOrderInfo baseOrderInfo) {
		// 当前时间小时在[9-15]的，预设为预定接下来的2个小时； 当前时间小时在（15，19）的，预设为当天17:00 到第二天的9:00
		// 当前时间小时在19点以后的，预设为当前小时-1，到第二天的9:00
		if (baseOrderInfo == null)
			return baseOrderInfo;
		Calendar calendar = Calendar.getInstance();

		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);

		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		if (hour >= 9 && hour <= 15) {
			if (min < 20 && min >= 0) {
				calendar.add(Calendar.MINUTE, 20);
				baseOrderInfo.setCsoStart(calendar.getTime());
				calendar.add(Calendar.HOUR_OF_DAY, 2);
				baseOrderInfo.setCsoFinish(calendar.getTime());
			} else {
				if (min < 40 && min >= 20) {
					calendar.add(Calendar.MINUTE, 40);
					baseOrderInfo.setCsoStart(calendar.getTime());
					calendar.add(Calendar.HOUR_OF_DAY, 2);
					baseOrderInfo.setCsoFinish(calendar.getTime());
				} else {
					calendar.set(Calendar.MINUTE, 0);
					calendar.add(Calendar.HOUR_OF_DAY, 1);
					baseOrderInfo.setCsoStart(calendar.getTime());
					calendar.add(Calendar.HOUR_OF_DAY, 2);
					baseOrderInfo.setCsoFinish(calendar.getTime());
				}
			}
		} else if (hour > 15 && hour <= 18) {
			calendar.set(Calendar.HOUR_OF_DAY, 17);
			baseOrderInfo.setCsoStart(calendar.getTime());
			calendar.add(Calendar.HOUR, 16);
			baseOrderInfo.setCsoFinish(calendar.getTime());
		} else if (hour > 18) {
			// calendar.add(Calendar.HOUR_OF_DAY, -1);
			baseOrderInfo.setCsoStart(calendar.getTime());

			calendar.add(Calendar.HOUR_OF_DAY, 1);
			calendar.add(Calendar.DATE, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 9);
			baseOrderInfo.setCsoFinish(calendar.getTime());
		} else {
			baseOrderInfo.setCsoStart(calendar.getTime());

			calendar.set(Calendar.HOUR_OF_DAY, 9);
			baseOrderInfo.setCsoFinish(calendar.getTime());
		}
		return baseOrderInfo;
	}

	/**
	 * 根据年，月，日，时，分，秒获得一个日期类型 tips：月份是从0开始
	 */
	public static Date getDate(int y, int m, int d, int hour, int min, int second) {
		Calendar cStart = Calendar.getInstance();
		cStart.set(y, m, d, hour, min, second);
		return cStart.getTime();
	}

	/**
	 * 根据时间获取时间描述
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeDesc(Date date) {
		if (date == null)
			return null;

		long second = (new Date().getTime() - date.getTime()) / 1000;

		if (second < 60)
			return "几秒之前";
		if (second < 60 * 60)
			return "" + second / 60 + "分钟之前";

		if (second < 60 * 60 * 24)
			return "" + second / 60 / 60 + "小时之前";

		if (second < 60 * 60 * 24 * 30)
			return "" + second / 60 / 60 / 24 + "天之前";

		if (second < 60 * 60 * 24 * 365)
			return "" + second / 60 / 60 / 24 / 30 + "个月之前";

		return "" + second / 60 / 60 / 24 / 365 + "年之前";
	}

	/**
	 * 根据网点添加时间，时间获取时间描述 30天以内添加的则是新网点
	 * 
	 * @param date
	 * @return
	 */
	public static Boolean isNewOutlets(Date addDate) {
		Boolean result = false;
		if (addDate == null)
			return result;

		long miniute = (new Date().getTime() - addDate.getTime()) / 1000 / 60;
		// 30天
		return miniute < 30 * 24 * 60;
	}

	/**
	 * 根据当前时间获取 凌晨好，上午好，中午好，下午好，傍晚好，晚上好
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeDisplay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 6)
			return "凌晨好";
		if (hour < 12)
			return "上午好";
		if (hour < 14)
			return "中午好";
		if (hour < 17)
			return "下午好";
		if (hour < 19)
			return "傍晚好";
		if (hour < 22)
			return "晚上好";
		// if (hour < 24)
		return "夜深了";
	}

	/**
	 * 根据开始时间，结束时间 获取时间间隔描述 如：1天1小时20分钟
	 * 
	 * @param start
	 * @param finish
	 * @return
	 */
	public static String getTimeDesc(Date start, Date finish) {
		if (start == null)
			return null;
		if (start.after(finish)) {
			Date temp = start;
			start = finish;
			finish = temp;
		}

		long min = (finish.getTime() - start.getTime()) / 1000 / 60;
		if (min < 60)
			return "" + min + "分钟";
		if (min < 60 * 24) {
			if (min % 60 == 0) {
				return "" + min / 60 + "小时";
			} else {
				return "" + min / 60 + "小时" + min % 60 + "分钟";
			}
		}
		// 整天
		if (min % (60 * 24) == 0) {
			return "" + min / (60 * 24) + "天";
		}
		// 有小时，没分钟
		if (min / 60 > 0 && min % 60 == 0) {
			return "" + min / (60 * 24) + "天" + min % (60 * 24) / 60 + "小时";
		}
		// 有小时，有分钟
		if (min / 60 > 0 && min % 60 != 0) {
			return "" + min / (60 * 24) + "天" + min % (60 * 24) / 60 + "小时" + min % 60 + "分钟";
		}
		// 没小时，有分钟
		return "" + min / (60 * 24) + "天" + min % 60 + "分钟";
	}

	/**
	 * 根据开始时间，结束时间，获取定单时间轴描述 left:25%;width:25%;
	 * 
	 * @param start
	 * @param finish
	 * @return
	 */
	public static String getTimebar(Date start, Date finish, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		// 时间轴起点
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, amount);
		// 时间轴终点
		Date endDate = calendar.getTime();

		Date s_startTime = start;
		Date e_endTime = finish;
		// 如果定单开始时间 < startDate ，则定单开始时间为 startDate
		if (s_startTime.before(startDate)) {
			s_startTime = startDate;
		}
		// 如果定单结束时间 > endDate ，则定单开始时间为 endDate
		if (e_endTime.after(endDate)) {
			e_endTime = endDate;
		}
		// 分钟数，就是width，长度
		long width = (e_endTime.getTime() - s_startTime.getTime()) / 1000 / 60;

		long left = (s_startTime.getTime() - startDate.getTime()) / 1000 / 60;
		if (left < 0 || width < 0)
			return "display: none;";
		return CommonMessage.MSG0301.replace("{left_value}", LZ.digit(left * 100 / 24.0 / 60.0 / amount, 2)).replace("{widh_value}", LZ.digit(width * 100 / 24.0 / 60.0 / amount, 2));
	}

	/**
	 * 指定某一天
	 * 
	 * @param start
	 * @param finish
	 * @param nowDate
	 * @return
	 */
	public static String getTimebar(Date start, Date finish, String nowDate) {
		try {
			Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(nowDate);
			// 时间轴起点
			final Date startDate = currentDate;
			// 时间轴终点
			final Date endDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);

			Date s_startTime = start;
			Date e_endTime = finish;
			// 如果定单开始时间 < startDate ，则定单开始时间为 startDate
			if (s_startTime.before(startDate)) {
				s_startTime = startDate;
			}
			// 如果定单结束时间 > endDate ，则定单开始时间为 endDate
			if (e_endTime.after(endDate)) {
				e_endTime = endDate;
			}
			// 分钟数，就是width，长度
			long width = (e_endTime.getTime() - s_startTime.getTime()) / 1000 / 60;

			long left = (s_startTime.getTime() - startDate.getTime()) / 1000 / 60;
			if (left < 0 || width < 0)
				return "display: none;";
			return CommonMessage.MSG0301.replace("{left_value}", LZ.digit(left * 100 / 24.0 / 60.0, 2)).replace("{widh_value}", LZ.digit(width * 100 / 24.0 / 60.0, 2));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "display: none;";
		}
	}
	
	public static String getMTimebar(String start, String finish, String nowDate) {
		try {
			Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(nowDate+":00");
			final Date startDate = new DateUtil().StringtoDate(start+":00", null);
			final Date endDate = new DateUtil().StringtoDate(finish+":00", null);

			// 分钟数，就是width，长度
			long width = (endDate.getTime() - startDate.getTime()) / 1000 / 60;
			long left = (startDate.getTime() -  currentDate.getTime()) / 1000 / 60;
			if (left < 0 || width < 0){
				return "display: none;";
			}
			return CommonMessage.MSG0301.replace("{left_value}", LZ.digit(left * 100 / 24.0 / 60.0, 2)).replace("{widh_value}", LZ.digit(width * 100 / 24.0 / 60.0, 2));
		} catch (Exception e) {
			e.printStackTrace();
			return "display: none;";
		}
	}

	/**
	 * 根据限行起始时间，获得前台展示，限行时用到
	 * 
	 * @param restriction
	 * @param num
	 * @return
	 */
	public static String getLimitTimebar(Restriction restriction, int index, int num) {
		try {
			String tempString = "left:{0}%;width:{1}px;";
			Calendar cal = Calendar.getInstance();
			cal.setTime(restriction.getStart());
			int startHour = cal.get(Calendar.HOUR_OF_DAY);

			long min = (restriction.getFinish().getTime() - restriction.getStart().getTime()) / 1000 / 60;
			return tempString.replace("{0}", $.digit((startHour + index * 24) * 100 / num / 24, 2)).replace("{1}", String.valueOf(toInt(min * 512 / num / 24 / 60)));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 判断给定日期是否符合 定单时间规范
	 * 
	 * @param date
	 * @return
	 */
	public static Boolean isCorrectOrderDate(Date date) {
		if (date == null)
			return false;
		long min = date.getTime() / 1000 / 60;
		return min % 20 == 0;
	}

	/**
	 * 转换车辆颜色值
	 * 
	 * @param value
	 * @return
	 */
	public static String convertColor(short value) {
		if (value == (short) 0) {
			return "黑色";
		}
		if (value == (short) 1) {
			return "白色";
		}
		if (value == (short) 2) {
			return "红色";
		}
		if (value == (short) 3) {
			return "绿色";
		}
		if (value == (short) 4) {
			return "黄色";
		}
		if (value == (short) 5) {
			return "橙色";
		}
		if (value == (short) 6) {
			return "银色";
		}
		if (value == (short) 7) {
			return "蓝色";
		}
		if (value == (short) 8) {
			return "深蓝";
		}
		if (value == (short) 9) {
			return "深灰";
		}
		if (value == (short) 10) {
			return "深红";
		}
		return null;
	}

	/**
	 * 转换充值对应的银行
	 * 
	 * @param value
	 * @return
	 */
	public static String convertBank(String value) {
		if (value.toUpperCase().equals("ICBC-DEBIT")) {
			return "中国工商银行";
		}
		if (value.toUpperCase().equals("CCB")) {
			return "中国建设银行";
		}
		if (value.toUpperCase().equals("BOCB2C")) {
			return "中国银行";
		}
		if (value.toUpperCase().equals("CMB")) {
			return "中国招商银行";
		}
		if (value.toUpperCase().equals("COMM")) {
			return "中国交通银行";
		}
		if (value.toUpperCase().equals("ABC")) {
			return "中国农业银行";
		}
		if (value.toUpperCase().equals("CITIC")) {
			return "中信银行";
		}
		if (value.toUpperCase().equals("CIB")) {
			return "兴业银行";
		}
		if (value.toUpperCase().equals("SPDB")) {
			return "上海浦东发展银行";
		}
		if (value.toUpperCase().equals("CMBC")) {
			return "中国民生银行";
		}
		if (value.toUpperCase().equals("CEBBANK")) {
			return "中国光大银行";
		}
		if (value.toUpperCase().equals("NBBANK")) {
			return "宁波银行";
		}
		if (value.toUpperCase().equals("HZCBB2C")) {
			return "杭州银行";
		}
		if (value.toUpperCase().equals("GDB")) {
			return "广发银行";
		}
		if (value.toUpperCase().equals("PSBC-DEBIT")) {
			return "中国邮政储蓄";
		}
		return "银行卡";
	}

	
	/**
	 * 价格转换器
	 * @author uiu
	 */
	public static class Prices extends HashMap{
		
		private String onHour;
		
		private String offHour;
		
		private String nightHours;
		
		@Override
		public Object get(Object key) {
			String[] array = (""+key).split("\\|");
			for(String k:array){
				Object v = super.get(k);
				if(v!=null)
					return v;
			}
			return null;
		}

		public String getOnHour() {
			return onHour;
		}

		public void setOnHour(String onHour) {
			this.put("onHour", onHour);
			this.onHour = onHour;
		}

		public String getOffHour() {
			return offHour;
		}

		public void setOffHour(String offHour) {
			this.put("offHour", offHour);
			this.offHour = offHour;
		}

		public String getNightHours() {
			return nightHours;
		}

		public void setNightHours(String nightHours) {
			this.put("nightHours", nightHours);
			this.nightHours = nightHours;
		}
		
	}
	/**
	 * 根据城市获取车型/网点价格
	 * @param host
	 * @param model
	 * @param outlets 可为空
	 * @return
	 * 
	 * 	${lz:set("prices",(cc:prices(host,model,outles)))}
	 *  ${prices["租金.工作日白天小时|租金.白天小时|租金.每小时"]}
	 * 
	 */
	public static Prices getPrices(Long host,Long model,Long outlets){
		Prices modelPrice = $.getRequest("Prices_"+host+"_"+model);
		if(outlets!=null && outlets.longValue()==0)
			outlets=null;
		if(modelPrice!=null)
			return modelPrice;
		else
			modelPrice = new Prices();
		
		CsFeeTypeSet csFeeTypeSet = CsFeeTypeSet.where().csftsHost(host).csftsModel(model).get();
		if(csFeeTypeSet==null){
			$.trace("未配置["+SrvHost.getKeyValue(host)+"]["+CsCarModel.getKeyValue(model)+"]价格");
			return modelPrice; 
		}
		List<CsPrice> list = CsPrice.where().cspUserType(csFeeTypeSet.getCsftsDefault()).cspModel(model).cspOutets($.or(outlets,csFeeTypeSet.getCsftsOutlets())).list(-1);
		if(list.isEmpty())
			list = CsPrice.where().cspUserType(csFeeTypeSet.getCsftsDefault()).cspModel(model).cspOutets(csFeeTypeSet.getCsftsOutlets()).list(-1);
		for(CsPrice csPrice:list){
			CsGoods goods = csPrice.get$cspGoods();
			Long goodType=goods.getCsgUserType();
 			Long priceType=csPrice.getCspUserType();
 			//计费策略判断
 			if(goodType.intValue()!=priceType.intValue()) {
 				continue;
 			}
			if(csPrice.getCspPrice().doubleValue()<0.02) 
				modelPrice.put(goods.getCsgProduct$()+"."+goods.getCsgRule$(), 0);
			else
				modelPrice.put(goods.getCsgProduct$()+"."+goods.getCsgRule$(), csPrice.getCspPrice());
		}
		
		CsUserType userType = csFeeTypeSet.get$csftsDefault();
		
		SrvHost srvHost = SrvHost.get(host);
		
		if(userType!=null){
			String onHour = StringUtils.leftPad(srvHost.getShOnHour$(), 2, "0");
			String offHour = StringUtils.leftPad(srvHost.getShOffHour$(), 2, "0");
			modelPrice.setOnHour((srvHost.getShOnHour$() == null || srvHost.getShOnHour$().trim().equals("")) ? "09" : onHour);
			modelPrice.setOffHour((srvHost.getShOffHour$() == null || srvHost.getShOffHour$().trim().equals("")) ? "17" : offHour);
			modelPrice.setNightHours((srvHost.getShOffHour() == null || srvHost.getShOnHour() == null) ?  "16" : ""+ (24 - srvHost.getShOffHour() + srvHost.getShOnHour()));
		}
		
		return modelPrice;
	}
	
	
	/**
	 * 以表单方式提交
	 * 
	 * @param ALIPAY_GATEWAY_NEW
	 *            url
	 * @param sParaTemp
	 *            变量集合
	 * @param strMethod
	 *            get or post
	 * @param strButtonName
	 *            按钮文本
	 * @return
	 */
	public static String buildRequest(String ALIPAY_GATEWAY_NEW, Map<String, String> sParaTemp, String strMethod, String strButtonName) {
		// 待请求参数数组
		Map<String, String> sPara = sParaTemp;
		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<form id=\"ccclubssubmit\" name=\"ccclubssubmit\" action=\"" + ALIPAY_GATEWAY_NEW + "\" method=\"" + strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}

		// submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['ccclubssubmit'].submit();</script>");

		return sbHtml.toString();
	}

	public static String testHost(Long hostid) {
		String strHosts = LoginHelper.getLogin().getSuHost();
		if(hostid==null)
			return strHosts==null?null:strHosts.replace(" ", "");
		if($.empty(strHosts))
			return hostid.toString();
		for(String sId:strHosts.split(",")){
			if($.trim(sId).equals(hostid.toString()))
				return hostid.toString();
		}
		return strHosts; 
	}
	
	public static Long inHost(Long hostid) {
		String strHosts = LoginHelper.getLogin().getSuHost();
		if(hostid==null || $.empty(strHosts))
			return hostid;
		for(String sId:strHosts.split(",")){
			if($.trim(sId).equals(hostid.toString()))
				return hostid;
		}
		return null;
	}
	
	public static SrvHost getAdminHost(){
		Long session_host_id = $.getSession("session_like_host");
		if(session_host_id!=null)
			return SrvHost.get(session_host_id);
		String strHosts = LoginHelper.getLogin().getSuHost();
		if(!$.empty(strHosts)){
			Long hostid = Long.parseLong(strHosts.split(",")[0]);
			return SrvHost.get(hostid);
		}
		return SrvHost.get(1l);
	}

	/**
	 * 获取城市运营车型列表
	 * @param shId
	 * @return
	 */
	public static String getHostModelIds(Long host) {
		List<CsCarModel> models = getHostModels(host);
		String ids = "";
		for(CsCarModel csCarModel:models){
			if(!$.empty(ids))ids+=",";
			ids+=csCarModel.getCscmId();
		}
		return ids;
	}
	public static List<CsCarModel> getHostModels(Long host) {
		List<CsFeeTypeSet> set = CsFeeTypeSet.where().csftsStatus(1).csftsHost(host).list(-1);
		List<CsCarModel> models = new ArrayList();
		for(CsFeeTypeSet csFeeTypeSet:set)
			models.add(csFeeTypeSet.get$csftsModel());
		return models;
	}
	/**
	 * 获取某城市支持的计费类型列表
	 * @param host
	 * @return
	 */
	public static List<CsUserType> getHostFeeTypes(Long host) {
		List<CsFeeTypeSet> set = CsFeeTypeSet.where().csftsStatus(1).csftsHost(host).list(-1);
		Map<Long,CsUserType> mapping = new HashMap();
		for(CsFeeTypeSet csFeeTypeSet:set){
			mapping.put(csFeeTypeSet.getCsftsDefault(), csFeeTypeSet.get$csftsDefault());
			List<CsUserType> types = csFeeTypeSet.get$csftsFeeType();
			for(CsUserType type:types)
				mapping.put(type.getCsutId(), type);
		}
		List<CsUserType> types = new ArrayList();
		for(Map.Entry<Long, CsUserType> entry:mapping.entrySet()){
			types.add(entry.getValue());
		}
		return types;
	}

	
	
}
