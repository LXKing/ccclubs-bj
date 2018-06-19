package com.ccclubs.action.manage;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ISrvGroupService;
import com.ccclubs.service.admin.ISrvUserService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;



public class LoginAction {
	ISrvUserService srvUserService;
	ISrvGroupService srvGroupService;
	String _superUser=ConfigHelper.GetString("super_username");
	String _superPassword=ConfigHelper.GetString("super_password");
	public String execute(){
		return "index";
	}
	/*
	 * 登录
	 */
	public String login(){
		try{
			String username=$.getString("username");
			String password=$.getString("userpwd");
			HttpServletRequest request = ServletActionContext.getRequest();
			if(username==null||username.equals("")){
				System.out.println("用户名为空");
			}else if(password==null||password.equals("")){
				System.out.println("密码为空");
			}else{
				SrvUser user = null;
				//验证是否超级用户
				if(username.equals(_superUser) && password.equals(_superPassword))
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
					user=srvUserService.getSrvUserByUsername(username);
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
					
					if(!password.equals(user.getSuPassword()) && !$.md5(password).equals(user.getSuPassword()))
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
					String login_id = String.valueOf(user.getSuId());
					$.setSession("ccclubs_login", user);
					$.setSession("ccclubs_login_id", user.getSuId());
					$.SetTips(login_id);
					//记录操作日志
					LoggerHelper.writeLog(SrvUser.class,"LOGIN","登入系统",(Long)$.getSession("ccclubs_login_id"),"登录系统");
					return "success";
				}
			}
			}catch(Exception ex){
				ex.printStackTrace();
				$.SetTips("系统错误,请稍候再试");
				return "failure";
			}
			return "failure";
	}
	public ISrvUserService getSrvUserService() {
		return srvUserService;
	}

	public void setSrvUserService(ISrvUserService srvUserService) {
		this.srvUserService = srvUserService;
	}

	public ISrvGroupService getSrvGroupService() {
		return srvGroupService;
	}

	public void setSrvGroupService(ISrvGroupService srvGroupService) {
		this.srvGroupService = srvGroupService;
	}

}
