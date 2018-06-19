package  com.ccclubs.action.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionServlet;
import org.apache.struts2.ServletActionContext;

import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.FileHelper;
import com.lazy3q.xml.Item;
import com.lazy3q.xml.Xml;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvGroup;
import com.opensymphony.xwork2.ActionContext;
import com.ccclubs.service.admin.ISrvUserService;
import com.ccclubs.service.admin.ISrvGroupService;

public class LoginAction{
	
	ISrvUserService srvUserService;
	ISrvGroupService srvGroupService;
	String _superUser=ConfigHelper.GetString("super_username");
	String _superPassword=ConfigHelper.GetString("super_password");
	
	static Map conn_map=new HashMap();
	
	/********************登录**********************/
	/**
	 * 登陆默认入口
	 */
	public String execute(){
		return "index";
	}
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		try{
			$.setSession("super_user", null);
			Map params = ActionContext.getContext().getParameters();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			String strRequestSyscode=$.getString(params,"syscode");
		    String strSessionSyscode = (String) session.getAttribute("syscode");
			session.removeAttribute("syscode");
			if (strRequestSyscode == null || !strRequestSyscode.equals(strSessionSyscode)){
				$.SetTips("对不起，验证码输入错误！");
				return "failure";
			}
			String strUsername=$.getString(params, "username");
			String strPassword=$.getString(params, "password");		
			if(strUsername==null || strUsername.trim().equals("")){
				$.SetTips("对不起，帐号输入为空！");
				return "failure";
			}
			if(strPassword==null || strPassword.trim().equals("")){
				$.SetTips("对不起，密码输入为空！");
				return "failure";
			}

			
			SrvUser user = null;
			//验证是否超级用户
			if(strUsername.equals(_superUser) && strPassword.equals(_superPassword))
			{
				user=new SrvUser();//创建超级用户
				user.setSuUsername(_superUser);
				user.setSuRealName("超级用户");
				user.setSuFamily("#0#");
				user.setSuId((long)0);
				$.setSession("ccclubs_login", user);
				$.setSession("ccclubs_login_id", user.getSuId());
				$.setSession("super_user", true);
			}else{			
				user=srvUserService.getSrvUserByUsername(strUsername);
				if(user==null)
				{
					$.SetTips("对不起，帐号不存在！");
					return "failure";
				}
				if(user.getSuStatus()!=1)
				{
					$.SetTips("对不起，帐号已经失效！");
					return "failure";
				}
				
				if(!strPassword.equals(user.getSuPassword()) && !$.md5(strPassword).equals(user.getSuPassword()))
				{
					$.SetTips("对不起，密码输入错误！");
					return "failure";
				}
				
				SrvUser parentUser=user;
				SrvGroup parentGroup=null;
				while(parentUser!=null)
				{
					parentGroup=srvGroupService.getSrvGroupById(parentUser.getSuGroupId());
					System.out.println(parentUser.getSuStatus().intValue()+"<>"+ parentGroup.getSgStatus().intValue());
					if(parentUser.getSuStatus().intValue()!=1 || parentGroup.getSgStatus().intValue()!=1)
					{
						$.SetTips("对不起，当前用户不可用！");
						return "failure";
					}
					parentUser=srvUserService.getSrvUserById(parentGroup.getSgParentId());				
				}
				
				String suLastIp=user.getSuLastIp();
				java.util.Date  suLastTime=user.getSuLastTime();
				user.setSuLastTime(new java.util.Date());
				user.setSuLastIp(request.getRemoteAddr());
				srvUserService.updateLastIp(user);
				user.setSuLastIp(suLastIp);
				user.setSuLastTime(suLastTime);
				$.setSession("ccclubs_login", user);
				$.setSession("ccclubs_login_id", user.getSuId());

				//记录操作日志			
				LoggerHelper.writeLog(SrvUser.class,"LOGIN","登入系统",(Long)$.getSession("ccclubs_login_id"),"登录系统");
			}
			
			$.setCookie("/", "UID", user.getSuId()+"-"+$.md5(SYSTEM.PRIVATE_KEY+user.getSuId()));
			
			Xml xml=new Xml("GBK",new Item("shortcuts"));
			if(user.getSuId().longValue()==0){	
				String classPath=IndexAction.class.getResource("/").toURI().getPath();
				String xmlString=null;
				try{
					xmlString=FileHelper.readFile(classPath+"shortcuts.super.xml");
				}catch(java.io.FileNotFoundException ex){					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if(xmlString!=null && !xmlString.equals("")){
					xml.Parse(xmlString);
					$.setSession("style", xml.getRoot().getAttribute("style"));
					$.setSession("theme", xml.getRoot().getAttribute("theme"));
					$.setCookie("/", "theme", xml.getRoot().getAttribute("theme"));
					$.setCookie("/", "style", xml.getRoot().getAttribute("style"));
				}
			}else{		
				String strXml = user.getSuShortcut();
				if(strXml!=null && !strXml.equals("")){
					xml.Parse(strXml);			
					$.setSession("style", xml.getRoot().getAttribute("style"));
					$.setSession("theme", xml.getRoot().getAttribute("theme"));
					$.setCookie("/", "theme", xml.getRoot().getAttribute("theme"));
					$.setCookie("/", "style", xml.getRoot().getAttribute("style"));
				}
			}
			
			
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
			return "failure";
		}
		return "success";
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	public String logout (){
		try{
			$.setSession("ccclubs_login", null);
			$.setSession("ccclubs_login_id",null);
			$.setSession("session_like_host", null);
			$.setCookie("/", "UID", null);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误["+this.getClass().getName().hashCode()+"],请稍候再试");
			return "failure";
		}
		return "failure";
	}	
	
	public ISrvUserService getSrvUserService()
	{
		return srvUserService;
	}
	public void setSrvUserService(ISrvUserService srvUserService)
	{
		this.srvUserService = srvUserService;
	}
	
	public ISrvGroupService getSrvGroupService()
	{
		return srvGroupService;
	}
	public void setSrvGroupService(ISrvGroupService srvGroupService)
	{
		this.srvGroupService = srvGroupService;
	}

}