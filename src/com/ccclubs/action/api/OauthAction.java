package com.ccclubs.action.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.RSAUtil;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberGroup;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvOauth;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ISrvHostService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class OauthAction {
	
	String direct;//转跳地址
	String appid;//appid
	String ticket;//三方凭证
	String sign;//检验串
	String timestamp;//时间戳
	String username;
	String password;
	
	public String execute(){
		try{
			//操作类型，1登录，2注册
			String type = $.getRequest("opType");
			String token = $.getSession("token");
			if(type!=null && type.equals("2") && token!=null){
				$.setRequest("type", type);
				$.setRequest("success", true);
				$.setRequest("token", token);
				return "index";
			}
			
			if($.empty(appid)){
				$.setRequest("error","appid参数错误");
				return "index";
			}
			/*if($.empty(direct)&&$.empty(ticket)){
				$.setRequest("error","未传入direct(转跳地址)参数或ticket(三方凭证)参数");
				return "index";
			}*/
			if($.empty(timestamp)){
				$.setRequest("error","未传入timestamp参数错误");
				return "index";
			}
			if($.empty(sign)){
				$.setRequest("error","sign参数错误");
				return "index";
			}
			Date timeStamp = $.date(timestamp, "yyyy-MM-dd HH:mm:ss");
			if(Math.abs(new Date().getTime()-timeStamp.getTime())>1000*60*10){
				$.setRequest("error","无效的调用时间");
				return "index";
			}
			SrvApiClient srvApiClient = SrvApiClient.m().sacClient(appid).sacStatus((short)1).get();
			if(srvApiClient==null){
				$.setRequest("error","无效的应用");
				return "index";
			}
			if(!SystemHelper.verifySign(srvApiClient.getSacSecret(), sign, "appid","direct","ticket","timestamp")){
				$.setRequest("error","参数签名验证失败");
				return "index";
			}
			$.setRequest("srvApiClient", srvApiClient);
			
		}catch(Exception ex){
			ex.printStackTrace();
			$.setRequest("error","系统繁忙，请稍候再试");
		}
		return "index";
	}
	
	
	public String submit(){
		try{
			if($.empty(appid)){
				$.setRequest("error","appid参数错误");
				return "index";
			}
			/*if($.empty(direct)&&$.empty(ticket)){
				$.setRequest("error","未传入direct(转跳地址)参数或ticket(三方凭证)参数");
				return "index";
			}*/
			if($.empty(timestamp)){
				$.setRequest("error","未传入timestamp参数错误");
				return "index";
			}
			if($.empty(sign)){
				$.setRequest("error","sign参数错误");
				return "index";
			}
			if($.empty(username)){
				$.setRequest("message","用户名输入不能为空");
				return "index";
			}
			if($.empty(password)){
				$.setRequest("message","密码输入不能为空");
				return "index";
			}
			Date timeStamp = $.date(timestamp, "yyyy-MM-dd HH:mm:ss");
			if(Math.abs(new Date().getTime()-timeStamp.getTime())>1000*60*10){
				$.setRequest("error","无效的调用时间");
				return "index";
			}
			SrvApiClient srvApiClient = SrvApiClient.m().sacClient(appid).sacStatus((short)1).get();
			if(srvApiClient==null){
				$.setRequest("error","无效的应用");
				return "index";
			}
			if(!SystemHelper.verifySign(srvApiClient.getSacSecret(), sign, "appid","direct","ticket","timestamp")){
				$.setRequest("error","参数签名验证失败");
				return "index";
			}
			$.setRequest("srvApiClient", srvApiClient);
			
			Long uid = null;
			
			/*********************************登录验证***********************************/
			CsMember user = null;
			if (SystemHelper.isEmail(username))// 邮箱方式登录
				user = CsMember.getCsMember($.Map("csmEmail", username));
			else if (SystemHelper.isMobile(username))// 手机方式登录
				user = CsMember.getCsMember($.Map("csmMobile", username));
			else
				user = CsMember.getCsMember($.Map("csmUsername", username));
			if (user == null) {
				$.setRequest("message","用户名或密码输入错误");
				return "index";
			}
			if (user.getCsmStatus() != 1) {
				$.setRequest("message","该帐户已经失效，请联系车纷享");
				return "index";
			}
			if (!$.md5(password).equals(user.getCsmPassword().toUpperCase())) {
				Integer count=(Integer) $.getSession("login_repeat");
				$.setSession("login_repeat",count==null?1:count+1);
				if(count!=null&&count>5){
					$.setRequest("error","由于您连续5次登录错误，系统已拒绝您再次登录");
					return "index";
				}
				$.setRequest("message","你输入的密码不正确，请重新输入。");
				return "index";
			}
			uid = user.getCsmId();
			/*********************************登录验证***********************************/
			
			SrvOauth existOauth = SrvOauth.m().soApiClient(srvApiClient.getSacId()).soUserId(uid).get();
			if(existOauth==null){
				 existOauth = new SrvOauth(
				 	$.uuid()//Token [非空]
				 	,uid//帐号ID [非空]
				 	,srvApiClient.getSacId()//API客户端 [非空]
				 	,ticket//三方标识
				 	,new Date()//登录时间 [非空]
				 ).save();
			}else{
				existOauth.soUpdateTime(new Date()).update();
			}
			
			//操作类型，1登录，2注册
			$.setRequest("type", 1);
			
			$.setRequest("token", existOauth.getSoToken());
			$.setRequest("success", true);
			
			return "index";
		}catch(Exception ex){
			ex.printStackTrace();
			$.setRequest("error","系统繁忙，请稍候再试");
		}
		return "index";
	}
	
	/**
	 * 登录成功转跳
	 * @return
	 */
	public String success(){		
		return "success";
	}
	
	/**
	 * 用户注册页面
	 * @return
	 */
	public String register(){
		try{
			if($.empty(appid)){
				$.setRequest("error","appid参数错误");
				return "register";
			}
//			if($.empty(direct)&&$.empty(ticket)){
//				$.setRequest("error","未传入direct(转跳地址)参数或ticket(三方凭证)参数");
//				return "register";
//			}
			if($.empty(timestamp)){
				$.setRequest("error","未传入timestamp参数错误");
				return "register";
			}
			if($.empty(sign)){
				$.setRequest("error","sign参数错误");
				return "register";
			}
			Date timeStamp = $.date(timestamp, "yyyy-MM-dd HH:mm:ss");
			if (Math.abs(new Date().getTime() - timeStamp.getTime()) > 1000 * 60 * 10) {
				$.setRequest("error", "无效的调用时间");
				return "register";
			}
			SrvApiClient srvApiClient = SrvApiClient.m().sacClient(appid).sacStatus((short)1).get();
			if(srvApiClient==null){
				$.setRequest("error","无效的应用");
				return "register";
			}
			if(!SystemHelper.verifySign(srvApiClient.getSacSecret(), sign, "appid","direct","ticket","timestamp")){
				$.setRequest("error","参数签名验证失败");
				return "register";
			}
			$.setRequest("srvApiClient", srvApiClient);
			
			ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
			Map<String,Object> params = new HashMap<String, Object>();
			params.put(SrvHost.F.shStatus, 1);
			params.put("asc", SrvHost.C.shId);
			params.put("definex", "sh_state=1");
			List<SrvHost> hostList = srvHostService.getSrvHostList(params, -1);
			$.setRequest("hostList", hostList);
			
			// 为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
			$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime() - SYSTEM.MINUTE));
		}catch(Exception ex){
			ex.printStackTrace();
			$.setRequest("error","系统繁忙，请稍候再试");
		}
		return "register";
	}
	
	/**
	 * 提交注册基本信息 ，手机号，密码，验证码，校验码
	 * 
	 * @return
	 */
	public String save() {
		try {
			String csmMobile = $.getString("txtMobile", "");
			String csmPassword = $.getString("txtPassWord", "");
			String rePwd = $.getString("txtRePassWord", "");
			String strValidMobCode = $.getString("txtCode", "");
			final String txtHost = $.getString("txtHost", "1");
			
			final ICsMemberService csMemberService =Lazy.GetSpringBean("csMemberService");
			if ($.empty(csmMobile)) {
				// $.SetTips("未输入手机号码，不能注册");
				return $.SendHtml("101", "UTF-8");
			}
			if ($.empty(csmPassword)) {
				// $.SetTips("未输入密码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
//			if ($.empty(strValidCode)) {
//				// $.SetTips("未输入验证码，不能注册");
//				return $.SendHtml("103", "UTF-8");
//			}
			 if ($.empty(strValidMobCode)) {
				// $.SetTips("未输入短信校验码，不能注册");
				return $.SendHtml("104", "UTF-8");
			}

//			if (!SystemHelper.isMobile(csmMobile)) {
//				// $.SetTips("手机号码格式错误");
//				return $.SendHtml("105", "UTF-8");
//			}
			if (csmPassword.length() < 6) {
				// $.SetTips("密码设置有误，请输入6到20位有效密码");
				return $.SendHtml("106", "UTF-8");
			}
			if (!$.equals(csmPassword, rePwd)) {
				// $.SetTips("两次密码输入不一致");
				return $.SendHtml("107", "UTF-8");
			}
			if (csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile)) != null) {
				// $.SetTips("手机号码已被注册");
				return $.SendHtml("108", "UTF-8");
			}
//			if (!$.equals(strValidCode.toLowerCase(), ((String) $.getSession(Constant.VERIFY_CODE)).toLowerCase())) {
//				// $.SetTips("注册码输入错误");
//				return $.SendHtml("109", "UTF-8");
//			}
			 if (!$.equals(strValidMobCode, (String) $.getSession(SYSTEM.REGIST_VAILD_CODE))) {
				// $.SetTips("短信校验码输入错误");
				return $.SendHtml("110", "UTF-8");
			}
			// ---------------------------------------------------------通过验证，提交注册
			final String finalPassword = csmPassword;
			final String finalMobile = csmMobile;
			CsMember csMember = csMemberService.executeTransaction(new Function() {
				
				@Override
				public CsMember execute(Object... arg0) {
					String username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
					try{
						// 用户名去重
						while (csMemberService.getCsMember($.add(CsMember.F.csmUsername, username)) != null) {
							username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
						}
						HttpServletRequest request = ServletActionContext.getRequest();
	
						CsMember csMember = new CsMember();
						csMember.setNotNull(SystemHelper.getSrvHost().getShId(),username, $.md5(finalPassword),0d, 0d, 0d, 0, 0, 1d, new Date(), (short) 1);
						csMember.setCsmMobile(finalMobile);
						csMember.setCsmHost(Long.valueOf(txtHost));
						csMember.setCsmIsVip((short) 0);
						csMember.csmVDrive((short) 0).csmVEmail((short) 0).csmVMobile((short) 1).csmVReal((short) 0);
						csMember.csmLoginS(0);
						csMember.csmFrom((short) 5);
	
						csMember.csmRegistIp(request.getRemoteAddr());
						csMember.save();
						
						CsMember user = CsMember.getCsMember($.Map("csmMobile", finalMobile));
						if(user!=null){
							CsMemberInfo csMemberInfo = new CsMemberInfo();
							csMemberInfo.setCsmiMemberId(user.getCsmId());
							csMemberInfo.setNotNull(user.getCsmHost(),user.getCsmId(), new Date(), new Date(), (short) 1);
							csMemberInfo.save();
						}
						
						// $.SetTips("注册成功，请登录");
						// 清楚验证码，短信校验码session
						$.setSession(Constant.VERIFY_CODE, null);
						$.setSession(SYSTEM.REGIST_VAILD_CODE, null);
						return user;
					}catch(Exception e){
						e.printStackTrace();
					}
						
					return null;
				}
			});
			
			SrvApiClient srvApiClient = SrvApiClient.m().sacClient(appid).sacStatus((short)1).get();
			SrvOauth existOauth = SrvOauth.m().soApiClient(srvApiClient.getSacId()).soUserId(csMember.getCsmId()).get();
			if(existOauth==null){
				 existOauth = new SrvOauth(
				 	$.uuid()//Token [非空]
				 	,csMember.getCsmId()//帐号ID [非空]
				 	,srvApiClient.getSacId()//API客户端 [非空]
				 	,ticket//三方标识
				 	,new Date()//登录时间 [非空]
				 ).save();
			}else{
				existOauth.soUpdateTime(new Date()).update();
			}
			
			$.setSession("token", existOauth.getSoToken());
			
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}
	
	/**
	 * 判断会员是否已经存在
	 * 
	 * @return false：格式不正确，验证未通过 true：格式正确，可以使用 used：已存在
	 * 
	 */
	public String exist() {
		try {
			String strEmail = $.getString("txtEmail", "");
			String strUsername = $.getString("txtUserName", "");
			String strMobile = $.getString("txtMobile", "");
			Map<String, Object> params = new HashMap<String, Object>();
			if (!$.empty(strEmail) && SystemHelper.isEmail(strEmail))
				params.put(CsMember.F.csmEmail, strEmail);
			else if (!$.empty(strUsername) && SystemHelper.isUserName(strUsername))
				params.put(CsMember.F.csmUsername, strUsername);
			else if (!$.empty(strMobile) && SystemHelper.isMobile(strMobile))
				params.put(CsMember.F.csmMobile, strMobile);
			else
				return $.SendHtml("false", "UTF-8");
			
			ICsMemberService csMemberService =Lazy.GetSpringBean("csMemberService");
			CsMember existMember = csMemberService.getCsMember(params);
			if (existMember != null)
				return $.SendHtml("used", "UTF-8");
			else
				return $.SendHtml("true", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("false", "UTF-8");
		}
	}

	
	/**
	 * 发送短信
	 * 
	 * @return
	 */
	public String sms() {
		try {
			String mobile = $.getString("mobile", "");
			int type = $.getInteger("type", 0);
			if ($.empty(mobile))
				// 手机号码不能为空
				return $.SendHtml("101", "UTF-8");
			if (!SystemHelper.isMobile(mobile))
				// 手机号码格式错误
				return $.SendHtml("102", "UTF-8");
			// 验证短信发送频率
			Date last = $.getSession(SYSTEM.REGIST_VAILD_LAST);
			if (last == null) {
				// 不允许使用该接口发送短信
				return $.SendHtml("false", "UTF-8");
			}
			if ((new Date().getTime() - last.getTime()) < SYSTEM.MINUTE)
				// 两次注册短信验证码发送时间间隔不能小于一分钟
				return $.SendHtml("103", "UTF-8");
			String strCode = $.zerofill($.rand(9999), 4);
			// System.out.printf("短信校验码：" + strCode);
			Boolean bOk = false;
			if (type == 0) {
				bOk = UtilHelper.sendTemplateSMS($.getLong("host"), "REGIST_CODE", mobile, CommonMessage.MSG0200, SMSType.代码类短信, $.add("code", strCode));
			} else {
				bOk = UtilHelper.sendTemplateSMS(CsMember.where().csmMobile(mobile).get().getCsmHost(), "FORGOT_PWD", mobile, CommonMessage.MSG0201, SMSType.提示类短信, $.add("code", strCode));
			}
			// Boolean bOk = true;
			if (bOk == true) {
				$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date());
				$.setSession(SYSTEM.REGIST_VAILD_CODE, strCode);
				return $.SendHtml("success", "UTF-8");
			} else
				// 验证码发送失败，请联系管理员
				return $.SendHtml("false", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// 系统繁忙，请稍候再试
			return $.SendHtml("false", "UTF-8");
		}
	}
	
	public String code(){
		System.out.println(""+$.getSession("ddid"));
		
		$.setSession("ddid", 99910);
		if($.empty($.config("release.online"))){
			$.SendHtml($.getSession(SYSTEM.REGIST_VAILD_CODE)+"", "UTF-8");
		}
		return null;
	}

	public String getDirect() {
		return direct;
	}


	public void setDirect(String direct) {
		this.direct = direct;
	}


	public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	 
	

}
