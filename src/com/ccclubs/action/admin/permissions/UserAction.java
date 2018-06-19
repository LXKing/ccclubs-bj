package com.ccclubs.action.admin.permissions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.lazy3q.lang.type;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.$;

import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.opensymphony.xwork2.ActionContext;
import com.ccclubs.service.admin.ISrvGroupService;
import com.ccclubs.service.admin.ISrvUserExpService;
import com.ccclubs.service.admin.ISrvUserService;

@SuppressWarnings({"unchecked","unused"})
public class UserAction
{
	ISrvUserService srvUserService ;
	ISrvGroupService srvGroupService;
	ISrvUserExpService srvUserExpService;
	
	SrvUser srvUser;
	SrvUserExp srvUserExp;
	String common_password=ConfigHelper.GetString("common_password");//默认的注册密码
	
	/**
	 * 用户管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			$.SetRequest("users", srvUserService.getSrvUserOnwerTree((SrvUser) $.getSession("ccclubs_login")));
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "index";
	}
	
	/**
	 * 用户添加/编辑用户
	 * @return
	 */
	public String edit()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params, "id");
			Long parent=$.getLong(params, "parent");			
			if(id!=null)
			{
				srvUser=srvUserService.getSrvUserById(id);
				if(srvUser != null)
					srvUserExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueUser, srvUser.getSuId$()));
				$.SetRequest("parent", srvUser.getSuGroupId());
			}
			else if(parent!=null)
			{
				$.SetRequest("parent",parent);
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "edit";
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	public String save()
	{
		try{
			SrvUser user = srvUserService.getSrvUserByUsername(srvUser.getSuUsername());
			if(user!=null)
			{
				$.SetTips("用户名已经存在，不能添加用户");
				return "common";
			}
			srvUser.setSuFamily("");
			srvUser.setSuLastTime(new java.util.Date());
			srvUser.setSuLastIp("");
			srvUser.setSuPassword(common_password);
			if(srvUser.getSuHost()==null)
				srvUser.setSuHost(LoginHelper.getLogin().getSuHost());
			srvUser.setSuParentId((Long) $.getSession("ccclubs_login_id"));
			srvUser.setSuAddTime(new Date());
			
			srvUserExpService.executeTransaction(new Function() {
				
				@Override
				public <T> T execute(Object... arg0) {
					// TODO Auto-generated method stub
					srvUser = srvUserService.saveSrvUser(srvUser);		
					if(srvUserExp.getSueOutlets() != null){
						srvUserExp.setSueUser(srvUser.getSuId());
						srvUserExp.setSueIsQrOp(true);
						srvUserExp.setSueChatNick(srvUser.getSuUsername());
						srvUserExpService.saveSrvUserExp(srvUserExp);
					}
					return null;
				}
			});
			//记录操作日志			
			LoggerHelper.writeLog(SrvUser.class,"add","添加了用户",(Long)$.getSession("ccclubs_login_id"),srvUser);
				
			$.SetTips("保存用户成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	public String update()
	{
		try{
			SrvUser oldSrvUser = srvUserService.getSrvUserById(srvUser.getSuId());			
			if(oldSrvUser.getSuParentId().longValue()!=(Long) $.getSession("ccclubs_login_id"))
			{
				$.SetTips("您没有权限修改该用户");
				return "common";
			}
			srvUser.setSuUsername(oldSrvUser.getSuUsername());
			srvUser.setSuAddTime(oldSrvUser.getSuAddTime());
			srvUser.setSuFamily(oldSrvUser.getSuFamily());
			srvUser.setSuLastIp(oldSrvUser.getSuLastIp());
			srvUser.setSuLastTime(oldSrvUser.getSuLastTime());
			if(srvUser.getSuHost()==null)
				srvUser.setSuHost(oldSrvUser.getSuHost());
			srvUser.setSuParentId(oldSrvUser.getSuParentId());
			srvUser.setSuPassword(oldSrvUser.getSuPassword());
			srvUser.setSuShortcut(oldSrvUser.getSuShortcut());
			srvUserService.updateSrvUser(srvUser);
			
			if(srvUserExp!=null){
				SrvUserExp exp = SrvUserExp.Get($.add(SrvUserExp.F.sueUser, srvUser.getSuId()));
				if(exp!=null){
					exp.setSueOutlets(srvUserExp.getSueOutlets());
					exp.update();
				}else{
					srvUserExp.setSueUser(srvUser.getSuId());
					srvUserExp.setSueIsQrOp(true);
					srvUserExp.setSueChatNick(srvUser.getSuUsername());
					srvUserExpService.saveSrvUserExp(srvUserExp);
				}
			}
			//记录操作日志			
			LoggerHelper.writeLog(SrvUser.class,"update","修改了用户",(Long)$.getSession("ccclubs_login_id"),srvUser);
			
			$.SetTips("修改用户成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String del()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			SrvUser oldSrvUser = srvUserService.getSrvUserById(id);
			if(oldSrvUser.getSuParentId().longValue()!=(Long) $.getSession("ccclubs_login_id"))
			{
				$.SetTips("您没有权限删除该用户");
				return "common";
			}
			srvUserService.deleteSrvUserById(id);
			SrvUserExp exp = SrvUserExp.Get($.add(SrvUserExp.F.sueUser, id));
			if(exp!=null){
				exp.delete();
			}
			
			//记录操作日志			
			LoggerHelper.writeLog(SrvUser.class,"del","删除了用户",(Long)$.getSession("ccclubs_login_id"),oldSrvUser);
			
			$.SetTips("删除用户成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	public String reset()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params, "id");
			srvUser=srvUserService.getSrvUserById(id);
			srvUser.setSuPassword(common_password);
			srvUserService.updateSrvUser(srvUser);
			$.SetTips("重置密码用户成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
		
	/**
	 * 显示用户详情
	 * @return
	 */
	public String details()
	{
		try{	
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				srvUser=srvUserService.getSrvUserById(id);
			if(key!=null)
				srvUser=srvUserService.getSrvUserById(key);
			if(srvUser != null)
				srvUserExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueUser, srvUser.getSuId$()));
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "details";
	}
	
	/**
	 * 验证用户是否存在
	 * @return
	 */
	public String exist()
	{
		try{	
			Map params = ActionContext.getContext().getParameters();
			String strUsername=$.getString(params, "username");			
			srvUser=srvUserService.getSrvUserByUsername(strUsername);
			if(srvUser!=null)
				return $.SendAjax("{exist:true}", null);
			else
				return $.SendAjax("{exist:false}", null);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
			return $.SendAjax("{error:true}", null);
		}
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String password()
	{	
		return "password";
	}
	public String update_password()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			String strOld=$.getString(params, "old");
			String strNew=$.getString(params, "now");
			srvUser=srvUserService.getSrvUserById((Long) $.getSession("ccclubs_login_id"));
			if(!strOld.equals(srvUser.getSuPassword()) && !$.md5(strOld).equals(srvUser.getSuPassword()))
			{
				$.SetTips("旧密码输入错误");
				return "common";
			}
			if(!strNew.matches("((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$")){
				$.SetTips("密码格式错误，必须是8到16位字符，不能为纯数字或纯字母");
				return "common";
			}
			srvUser.setSuPassword($.md5(strNew).substring(0, 32));
			srvUserService.updatePassword(srvUser);
			$.SetTips("密码修改成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
	
	
	
	/**
	 * 修改资料
	 * @return
	 */
	public String info(){
		try{
			if(srvUser==null){
				srvUser=srvUserService.getSrvUserById((Long) $.getSession("ccclubs_login_id"));
				return "info";
			}else{
				SrvUser updateSrvUser = srvUserService.getSrvUserById((Long) $.getSession("ccclubs_login_id"));
				updateSrvUser.setSuMobile(srvUser.getSuMobile());
				updateSrvUser.setSuEmail(srvUser.getSuEmail());
				srvUserService.updateSrvUser(updateSrvUser);
				$.SetTips("资料修改成功");
				return "info";
			}
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
			
		}
		return "info";
	}
	
	
	
	/**
	 * 根据真实姓名或ID查询用户
	 * @return
	 */
	public String query(){
		try{
 			Map<String,Object> params=new HashMap();		
			//把request传过来的(String)参数转换成java对象			
			Field[] fields = SrvUser.F.class.getFields();
			for(Field field:fields){
				type type = field.getAnnotation(type.class);
				params.put(field.getName(), $.getObject(field.getName(), type.value()));
			}
			
			//添加域标识
			if($.getBoolean("exists")==null)
				params.put(SrvUser.F.suHost,LoginHelper.getLogin().getSuHost());
			
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","su_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("suId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or su_real_name like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			
			Boolean bObject=$.getBoolean("object",false);
			
			List<SrvUser> list = srvUserService.getSrvUserList(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(SrvUser srvUser:list){
				Map map = new HashMap();
				map.put("value", srvUser.getSuId().toString());
				String strText = "";
				strText+=srvUser.getSuRealName()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",srvUser);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}

	
	
	
	
	/**
	 * 动态查询所有组和人员
	 * @return
	 */
	public String tree(){
		try{
			return $.SendAjax(getUserTree(), "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	public List<Map> getUserTree(){
		SrvUser login = LoginHelper.getLogin();
		SrvUser rootUser = login.getSuId().longValue()==0 ? login : SrvUser.get(Long.parseLong(login.getSuFamily().split(",")[2].replaceAll("[^\\d]", "")));
		Boolean bGroup=$.getBoolean("group",false);
		List<Map> roots = $.getCache(rootUser.getSuId().toString()+"."+bGroup);
		if(roots!=null && !roots.isEmpty())
			return roots;
		else
			roots = new ArrayList();
		List<SrvUser> users = srvUserService.getSrvUserOnwerTree(rootUser);
		if(login.getSuId().longValue()!=0)users.add(rootUser);
		List<SrvGroup> groups = srvGroupService.getSrvUserTreeChild(rootUser);
		Map<String,Map> mapping = new HashMap();
		for(SrvUser srvUser:users)
			mapping.put("u"+srvUser.getSuId(), Node(0,""+srvUser.getSuId(),srvUser.getKeyValue(),"g"+srvUser.getSuGroupId(),bGroup));
		for(SrvGroup srvGroup:groups)
			mapping.put("g"+srvGroup.getSgId(), Node(1,"-"+srvGroup.getSgId(),srvGroup.getSgName(),"u"+srvGroup.getSgParentId(),bGroup));
		for(Map node:mapping.values()){
			if(mapping.get(node.get("parent"))==null)
				roots.add(node);
			else{
				Map parentMap = mapping.get(node.get("parent"));
				List childs = (List) parentMap.get("childs");
				childs.add(node);
			}
		}
		$.setCache(rootUser.getSuId().toString(), roots, $.TIME.HOUR.time);
		return roots;
	}
	private Map Node(int type, String value, String text, String parent,Boolean bGroup) {
		Map map=new HashMap();
		map.put("type", type);
		map.put("value", value);
		map.put("text", text);
		map.put("parent", parent);
		if(type==1 && !bGroup)
			map.put("invalid", true);
		map.put("childs", new ArrayList());
		return map;
	}
	
	
	
	public ISrvUserService getSrvUserService()
	{
		return srvUserService;
	}

	public void setSrvUserService(ISrvUserService srvUserService)
	{
		this.srvUserService = srvUserService;
	}

	public SrvUser getSrvUser()
	{
		return srvUser;
	}

	public void setSrvUser(SrvUser srvUser)
	{
		this.srvUser = srvUser;
	}

	public ISrvGroupService getSrvGroupService()
	{
		return srvGroupService;
	}

	public void setSrvGroupService(ISrvGroupService srvGroupService)
	{
		this.srvGroupService = srvGroupService;
	}

	public SrvUserExp getSrvUserExp() {
		return srvUserExp;
	}

	public void setSrvUserExp(SrvUserExp srvUserExp) {
		this.srvUserExp = srvUserExp;
	}

	public ISrvUserExpService getSrvUserExpService() {
		return srvUserExpService;
	}

	public void setSrvUserExpService(ISrvUserExpService srvUserExpService) {
		this.srvUserExpService = srvUserExpService;
	}
	
	
	
	
}