package com.ccclubs.action.admin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.FileHelper;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.xml.Item;
import com.lazy3q.xml.Xml;

import com.opensymphony.xwork2.ActionContext;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.PluginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsUpgradeLog;
import com.ccclubs.model.SrvHelp;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvLimited;
import com.ccclubs.model.SrvProject;
import com.ccclubs.model.SrvUser;
import com.ccclubs.plugin.BasePlugin;
import com.ccclubs.service.admin.ISrvLimitedService;
import com.ccclubs.service.admin.ISrvProjectService;
import com.ccclubs.service.admin.ISrvUserService;

/**
 * LAZY3Q_LOCK_FLAG
 * @author uiu
 */
public class IndexAction
{
	ISrvUserService srvUserService;
	ISrvLimitedService srvLimitedService;
	ISrvProjectService srvProjectService;
	
	
	public String execute()
	{
		if($.getSession("EXTFIX")!=null)
			left();//获取当前用户可用的权限菜单
		if($.getLong("host")!=null){
			$.setSession("session_like_host",SystemHelper.inHost($.getLong("host")));
		}
		$.SetRequest("structs",true);		
		return "index";
	}
	
	public String frame()
	{
		return "frame";
	}
	
	public String window()
	{
		return "window";
	}
	
	public String desktop()
	{
		try{
			left();
			header();
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "desktop";
	}
	
	public String background(){
		try{
			String image = $.getString("image");
			if($.empty(image)||image.length()<6)
				image="";
			Long loginId = (Long) $.getSession("ccclubs_login_id");
			Xml xml=new Xml("GBK",new Item("shortcuts"));
			if(loginId.longValue()==0)//如果是超级用户，则保存到classpath下的快捷方式xml文件
			{	
				String classPath=IndexAction.class.getResource("/").toURI().getPath();
				String xmlString=null;
				try{
					xmlString=FileHelper.readFile(classPath+"shortcuts.super.xml");
				}catch(java.io.FileNotFoundException ex){					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if(xmlString!=null && !xmlString.equals(""))
					xml.Parse(xmlString);
				xml.getRoot().setAttribute("background", image);
				FileHelper.writeFile(classPath+"shortcuts.super.xml", xml.toString(),"GBK");
				SrvUser srvUser = (SrvUser) $.getSession("ccclubs_login");
				srvUser.setSuShortcut(xml.toString());
			}else{
				SrvUser srvUser = srvUserService.getSrvUserById(loginId);			
				String strXml = srvUser.getSuShortcut();			
				if(strXml!=null && !strXml.equals(""))
					xml.Parse(strXml);			
				xml.getRoot().setAttribute("background", image);
				srvUser.setSuShortcut(xml.toString());		
				srvUserService.updateSrvUser(srvUser);
				$.setSession("ccclubs_login", srvUser);
				$.setSession("ccclubs_login_id", srvUser.getSuId());
			}
			return $.SendAjax($.add("success",true), "UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("");
			return $.SendAjax($.add("message", "系统错误,请稍候再试"), "UTF-8");
		}
	}
	
	public String left()
	{
		try{			
			//获取用户的权限
			//获取用户支持的项目列表	
			if((Long)$.getSession("ccclubs_login_id")==0)
				$.SetRequest("projects", srvProjectService.getSrvProjectList());			
			else
			{
				List<SrvLimited> list=srvLimitedService.getUserLimits((SrvUser) $.getSession("ccclubs_login"),true);//获取用户权限
				Map<Long,Integer> limits = new HashMap();
				String strIds="";
				for(SrvLimited o:list){
					limits.put(o.getSlProjectId(), o.getSlLimit());					
					if(!strIds.equals(""))
						strIds+=",";
					strIds+=o.getSlProjectId();
				}
				$.SetRequest("limits", limits);
				$.SetRequest("projects", srvProjectService.getSrvProjectListByIds(strIds));
			}
			
			
			List<SrvProject> projects = $.getRequest("projects");
			java.util.Collections.sort(projects, new java.util.Comparator<SrvProject>(){
				public int compare(SrvProject o1,SrvProject o2){
					if(o1.getSpFamily().split(",").length<o2.getSpFamily().split(",").length)
						return 0;
					if(o1.getSpFamily().split(",").length==o2.getSpFamily().split(",").length && $.or(o1.getSpLevel(),0)>$.or(o2.getSpLevel(),0))
						return 0;
					return 1;
				}
			});
			
			
			List<BasePlugin> plugins = PluginHelper.getPlugins();
			$.SetRequest("plugins",plugins);
			for(BasePlugin plugin:plugins){
				$.SetRequest(plugin.getName()+"_links", plugin.getLinks());								
			}		
			
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		
		return "left";
	}
	
	/**
	 * 欢迎页LAZY3Q_LOCK_FLAG
	 * @return
	 */
	public String welcome(){
		Long defaultHostId = SYSTEM.getDefaultHost();
		if(defaultHostId!=null && CacheThread.getCacheThread(SYSTEM.getDefaultHost())!=null){
			$.SetRequest("statistics", CacheThread.getCacheThread(SYSTEM.getDefaultHost()).getStatistics());
			$.SetRequest("operations", CacheThread.getCacheThread(SYSTEM.getDefaultHost()).getOperations());
		}
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
		try{
			Long loginId = (Long) $.getSession("ccclubs_login_id");
			Xml xml=new Xml("GBK",new Item("shortcuts"));
			
			if(loginId.longValue()==0)//如果是超级用户，则读取classpath下的快捷方式xml文件
			{
				String classPath=IndexAction.class.getResource("/").toURI().getPath();
				String xmlString=null;
				try{
					xmlString=FileHelper.readFile(classPath+"shortcuts.super.xml");
				}catch(java.io.FileNotFoundException ex){
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if(xmlString!=null && !xmlString.equals(""))
					xml.Parse(xmlString);
			}
			else
			{
				SrvUser srvUser = srvUserService.getSrvUserById(loginId);			
				String strXml = srvUser.getSuShortcut();			
				if(strXml!=null && !strXml.equals(""))
					xml.Parse(strXml);
			}
			
			List shortcuts = new ArrayList();
			List<Item> items = xml.getRoot().getItemList();
			for(Item item:items)
			{
				Map map=new HashMap();
				map.put("icon",item.getAttribute("icon"));
				map.put("link",item.getAttribute("link"));
				map.put("name",item.getAttribute("name"));
				map.put("uuid",item.getAttribute("uuid"));
				map.put("target",item.getAttribute("target"));				
				shortcuts.add(map);
			}
			
			$.SetRequest("shortcuts",shortcuts);	
			$.SetRequest("background",xml.getRoot().getAttribute("background"));//读取背景图
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "header";
	}
	
	public String example(){
	
		return "example";
	}
	
	public String edit(){
	
		return "edit";
	}
	
	public String details(){
	
		return "details";
	}	
	
		
	/**
	 * 设置风格
	 * @return
	 */
	public String style()
	{
		try{		
			String style = $.getString("css");
			$.setCookie("/", "style", style);
			$.setSession("style", style);
			
			Long loginId = (Long) $.getSession("ccclubs_login_id");
			Xml xml=new Xml("GBK",new Item("shortcuts"));
			
			if(loginId.longValue()==0)//如果是超级用户，则保存到classpath下的快捷方式xml文件
			{	
				String classPath=IndexAction.class.getResource("/").toURI().getPath();
				String xmlString=null;
				try{
					xmlString=FileHelper.readFile(classPath+"shortcuts.super.xml");
				}catch(java.io.FileNotFoundException ex){					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if(xmlString!=null && !xmlString.equals(""))
					xml.Parse(xmlString);
				xml.getRoot().setAttribute("style", style);
				FileHelper.writeFile(classPath+"shortcuts.super.xml", xml.toString(),"GBK");
				SrvUser srvUser = (SrvUser) $.getSession("ccclubs_login");
				srvUser.setSuShortcut(xml.toString());
			}
			else
			{
				SrvUser srvUser = srvUserService.getSrvUserById(loginId);			
				String strXml = srvUser.getSuShortcut();			
				if(strXml!=null && !strXml.equals(""))
					xml.Parse(strXml);			
				xml.getRoot().setAttribute("style", style);
				srvUser.setSuShortcut(xml.toString());			
				srvUserService.updateSrvUser(srvUser);
				$.setSession("ccclubs_login", srvUser);
				$.setSession("ccclubs_login_id", srvUser.getSuId());
			}
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "style";
	}
	
	
	public String shortcut(){
		left();//获取当前用户可用的权限菜单
		return "shortcut";
	}
	
	public String mind(){
		$.setRequest("project", SrvProject.get($.getLong("id")));
		return "mind";
	}
	
	/**
	 * 保存快捷方式
	 * @return
	 */
	public String saveIcon(){		
		try{			
			Map<String,Object> params = ActionContext.getContext().getParameters();
			String icon=$.getString(params, "icon");
			String link=$.getString(params, "link");
			String name=$.getString(params, "name");
			String uuid=$.getString(params, "uuid");
			String target=$.getString(params, "target");
			
			if(icon==null || icon.equals(""))
			{
				$.SetTips("未设置链接图标!");
				return "header-entry";
			}
			if(link==null || link.equals(""))
			{
				$.SetTips("未设置链接地址!");
				return "header-entry";
			}
			if(name==null || name.equals(""))
			{
				$.SetTips("未设置链接名称!");
				return "header-entry";
			}
			
			Long loginId = (Long) $.getSession("ccclubs_login_id");
			Xml xml=new Xml("GBK",new Item("shortcuts"));
			
			Item item=new Item("item");
			item.setAttribute("icon",icon);
			item.setAttribute("link",link);
			item.setAttribute("name",name);
			item.setAttribute("uuid",uuid);	
			item.setAttribute("target",target);	
			
			if(loginId.longValue()==0)//如果是超级用户，则保存到classpath下的快捷方式xml文件
			{	
				String classPath=IndexAction.class.getResource("/").toURI().getPath();
				String xmlString=null;
				try{
					xmlString=FileHelper.readFile(classPath+"shortcuts.super.xml");
				}catch(java.io.FileNotFoundException ex){					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if(xmlString!=null && !xmlString.equals(""))
					xml.Parse(xmlString);
				xml.getRoot().addChild(item);
				FileHelper.writeFile(classPath+"shortcuts.super.xml", xml.toString(),"GBK");
				SrvUser srvUser = (SrvUser) $.getSession("ccclubs_login");
				srvUser.setSuShortcut(xml.toString());
			}
			else
			{
				SrvUser srvUser = srvUserService.getSrvUserById(loginId);			
				String strXml = srvUser.getSuShortcut();			
				if(strXml!=null && !strXml.equals(""))
					xml.Parse(strXml);			
				xml.getRoot().addChild(item);			
				srvUser.setSuShortcut(xml.toString());			
				srvUserService.updateSrvUser(srvUser);
				$.setSession("ccclubs_login", srvUser);
				$.setSession("ccclubs_login_id", srvUser.getSuId());
			}
			
			$.SetTips("添加快捷方式成功!");
			
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		
		return "header-entry";
	}
	
	/**
	 * 删除快捷方式
	 * @return
	 */
	public String delIcon(){
		try{			
			Map<String,Object> params = ActionContext.getContext().getParameters();
			String uuid=$.getString(params, "uuid");
			
			Long loginId = (Long) $.getSession("ccclubs_login_id");
			
			if(loginId.longValue()==0)//如果是超级用户，则保存到classpath下的快捷方式xml文件
			{
				Xml xml=new Xml("GBK");
				String classPath=IndexAction.class.getResource("/").toURI().getPath();
				String xmlString=null;
				try{
					xmlString=FileHelper.readFile(classPath+"shortcuts.super.xml");
				}catch(java.io.FileNotFoundException ex){					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if(xmlString!=null && !xmlString.equals(""))
					xml.Parse(xmlString);
				Item root = xml.getRoot();
				List<Item> items = root.getItemList();
				for(Item item:items){
					String strUuid = item.getAttribute("uuid");
					if(strUuid.equals(uuid)){
						items.remove(item);
						break;
					}
				}
				FileHelper.writeFile(classPath+"shortcuts.super.xml", xml.toString(),"GBK");
				SrvUser srvUser = (SrvUser) $.getSession("ccclubs_login");
				srvUser.setSuShortcut(xml.toString());
			}
			else
			{
				SrvUser srvUser = srvUserService.getSrvUserById(loginId);			
				String strXml = srvUser.getSuShortcut();
				Xml xml=new Xml("GBK");
				if(strXml!=null && !strXml.equals(""))
					xml.Parse(strXml);			
				Item root = xml.getRoot();
				List<Item> items = root.getItemList();
				for(Item item:items){
					String strUuid = item.getAttribute("uuid");
					if(strUuid.equals(uuid)){
						items.remove(item);
						break;
					}
				}
				srvUser.setSuShortcut(xml.toString());
				srvUserService.updateSrvUser(srvUser);
				$.setSession("ccclubs_login", srvUser);
				$.setSession("ccclubs_login_id", srvUser.getSuId());
			}			
			
			$.SetTips("删除快捷方式成功!");			
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "header-entry";
	}
	
	/**
	 * 类解析
	 * @param calssName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List parseClass(String calssName,List list,String parentKey,int depth,int maxDepth) throws Exception{
		if(depth>maxDepth)
			return list;
		Class<?> cls = Class.forName(calssName);
		Map<String,Boolean> fmaping = new HashMap();
		try{
			Class FClass = Class.forName(calssName+"$F");
			if(FClass!=null){
				Field[] Ffields = FClass.getFields();
				for(Field field:Ffields){
					fmaping.put(field.getName(), true);
				}
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		Field[] fields = cls.getDeclaredFields();
		Map<String,String> names = new HashMap();
		for(Field field:fields){			
			caption caption = field.getAnnotation(caption.class);	
			note note = field.getAnnotation(note.class);
			RelateClass relateClass = field.getAnnotation(RelateClass.class);
			if(caption!=null && field.getAnnotation(hidden.class)==null){
				Map map = new HashMap();
				if($.empty(parentKey))
					map.put("name",field.getName());
				else{
					map.put("name",parentKey+"."+field.getName());
					map.put("parent",parentKey.replace("[]", ""));
				}
				map.put("depth", depth);
				map.put("caption", caption.value());	
				map.put("type", field.getType().getName());
				if(note!=null && note.value().indexOf("'")==-1 && note.value().indexOf("lz:")==-1 && note.value().indexOf("{")==-1)
					map.put("note", note.value());
				if(fmaping.get(field.getName()+"Start")!=null || fmaping.get(field.getName()+"Min")!=null)
					map.put("range","true");
				if(relateClass!=null)
					map.put("relate",relateClass.value().getSimpleName());
				list.add(map);
				if(relateClass!=null){ 
					//$.trace(caption.value(),relateClass.value().getName(),field.getType(),field.getType()==List.class);
					parseClass(relateClass.value().getName(),list,$.str(map.get("name"))+(field.getType()==List.class?"[]":""),depth+1,maxDepth);
				}		
			}
		}
		return list;		
	}
	/**
	 * 根据类名获取结构信息
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static List parseClass(String className,int maxDepth) throws Exception{
		return parseClass(className,new ArrayList(),"",0,maxDepth);
	}	
	/**
	 * 数据导出工具
	 * @return
	 */
	public String export(){
		try{
			String className = $.getString("className");
			List fields = parseClass(className,$.getInteger("depth",1));
			Boolean struct = $.getBoolean("struct",false);
			if(struct)
				return $.SendAjax($.json(fields), $.UTF8);
			$.SetRequest("fields", fields);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍候再试");
		}
		return "export";
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
				if(method.getAnnotation(api.class)!=null)
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
				if(paramType.isArray()){
					List array = $.eval($.getString(key));
					params[i]=array.toArray();
				}else if(typeName.equals("Byte"))
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
				else if(typeName.equals("List"))
					params[i]=$.eval($.getString(key));
			}
			Object value = method.invoke(null, params);
			return $.SendAjax($.json(value), $.UTF8);
		}catch(Exception ex){			
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			String strException = ex.getClass().getName();
			for(int i=0;i<Math.min(ex.getStackTrace().length, 8);i++)
				strException+="\r\n"+ex.getStackTrace()[i];
			Throwable caused = ex.getCause();
			return $.SendAjax($.add("exception",strException).add("message", caused==null?ex.getMessage():caused.getMessage()), $.UTF8);
		}
	}
	
	/**
	 * 系统帮助
	 * @return
	 */
	public String help$index(){
		return "index";
	}
	public String help$list(){
		try{
			List<SrvHelp> list = SrvHelp.where().shStatus((short)1).list(-1);
			$.setRequest("list", list);
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendHtml("<center>系统繁忙，请稍候再试</center>", $.UTF8);
		}
		return "list";
	}
	public String help$view(){
		try{
			Long id = $.getLong("id");
			SrvHelp srvHelp = SrvHelp.get(id);
			$.setRequest("srvHelp", srvHelp);
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendHtml("<center>系统繁忙，请稍候再试</center>", $.UTF8);
		}
		return "view";
	}
	
	
	/**
	 * 系统搜索
	 * @return
	 */
	public String search(){
		try{
			String keyword = $.trim($.getString("keyword"));
			if($.empty(keyword))
				return "search";
			List members = new ArrayList();
			if(Pattern.matches("[\\u4e00-\\u9fa5]{2,}", keyword)){//如果输入的是汉字搜索会员
				$.setRequest("members", members = CsMember.list($.add(CsMember.F.csmHost, LoginHelper.getLogin().getSuHost()).add(CsMember.F.csmName, "%"+keyword+"%"), 5));
			}else if(Pattern.matches("[0-9]+", keyword)){//如果输入的是手机号码搜索会员
				$.setRequest("members", members = CsMember.list($.add(CsMember.F.csmHost, LoginHelper.getLogin().getSuHost()).add(CsMember.F.csmMobile, "%"+keyword+"%"), 5));
			}else if(Pattern.matches("[0-9a-zA-Z]{5,}", keyword)){//如果输入的是车牌号码，搜索车辆
				$.setRequest("cars", CsCar.list($.add(CsCar.F.cscHost, LoginHelper.getLogin().getSuHost()).add(CsCar.F.cscNumber, "%"+keyword+"%"), 5));
			}
			if(Pattern.matches("[0-9]+", keyword)){//如果输入的是纯数字，搜索订单
				members.addAll(CsMember.list($.add(CsMember.F.csmHost, LoginHelper.getLogin().getSuHost()).add(CsMember.F.csmId, keyword), 5));
				$.setRequest("members", members);
				$.setRequest("orders", CsOrder.list($.add(CsOrder.F.csoHost, LoginHelper.getLogin().getSuHost()).add(CsOrder.F.csoId, keyword), 5));
			}
			$.setRequest("getMemberLastOrders", new Function(){
				public <T> T execute(Object... arg0) {
					return (T) CsOrder.list($.add("csoUseMember", arg0[0]),2);
				}
			});
			$.setRequest("getCarLastOrders", new Function(){
				public <T> T execute(Object... arg0) {
					return (T) CsOrder.list($.add("csoCar", arg0[0]),2);
				}
			});
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendHtml("<center>系统繁忙，请稍候再试</center>", $.UTF8);
		}
		return "search";
	}
	
	public String upgrade_log(){
		try{
			Page<CsUpgradeLog> page = CsUpgradeLog.where().csulStatus((short)1).add("desc", "csul_on_time").page($.getInteger("page",0), 60);
			$.setRequest("page", page);
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
		}
		return "upgrade.log";
	}
	
	public ISrvUserService getSrvUserService()
	{
		return srvUserService;
	}

	public void setSrvUserService(ISrvUserService srvUserService)
	{
		this.srvUserService = srvUserService;
	}

	public ISrvLimitedService getSrvLimitedService()
	{
		return srvLimitedService;
	}

	public void setSrvLimitedService(ISrvLimitedService srvLimitedService)
	{
		this.srvLimitedService = srvLimitedService;
	}

	public ISrvProjectService getSrvProjectService()
	{
		return srvProjectService;
	}

	public void setSrvProjectService(ISrvProjectService srvProjectService)
	{
		this.srvProjectService = srvProjectService;
	}
}