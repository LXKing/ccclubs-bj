package com.ccclubs.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ISrvUserService;
import com.lazy3q.web.helper.$;
import com.lazy3q.xwork.WebContext;

public class LoginFilter implements Filter {
	
	private String loginPath=null;
	private Map<String,Boolean> releasePaths=new HashMap();
	private String mobileHost=null;//手机访问域名
	
	public void init(FilterConfig arg0) throws ServletException {
		loginPath=arg0.getInitParameter("loginPath");
		mobileHost=arg0.getInitParameter("mobileHost");//手机访问域名
		String releasePath=arg0.getInitParameter("releasePath");
		if(releasePath!=null){
			String[] array=releasePath.split(",");
			for(String s:array)
				releasePaths.put(s, true);
		}
		if(this.loginPath!=null)
			releasePaths.put(loginPath, true);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest HRequest = (HttpServletRequest) request;
		HttpServletResponse HResponse = (HttpServletResponse) response;
		HttpSession session = HRequest.getSession();
		
		
		
		//判断是否手机登录,或者是否要求手机登录
		String basePath = request.getScheme()+"://"+request.getServerName()+"/";
		if(basePath!=null && mobileHost!=null && basePath.startsWith(mobileHost))
			session.setAttribute("EXTFIX", "m.");
		
		String strUrl=HRequest.getServletPath().toString();
		if(!strUrl.startsWith("/admin")){
			filterChain.doFilter(request, response);
			return;
		}
		
		request.setAttribute("proname","admin");
		if(session.getAttribute("ccclubs_login")==null){
			try{
				Cookie[] cookies = HRequest.getCookies();
				String strCookieUID = null;
				if(cookies!=null){
					for(Cookie cookie:cookies){
						if(cookie.getName().equals("UID"))
							strCookieUID = cookie.getValue();
					}
				}
				String UID = $.or(request.getParameter("UID"),strCookieUID);
				if(!$.empty(UID) && UID.split("\\-").length==2 && $.equals(UID.split("\\-")[1], $.md5(SYSTEM.PRIVATE_KEY+UID.split("\\-")[0]))){
					ISrvUserService srvUserService = $.getBean("srvUserService");
					SrvUser user=srvUserService.getSrvUserById(Long.parseLong(UID.split("\\-")[0]));
					if(user!=null){
						session.setAttribute("ccclubs_login", user);
						session.setAttribute("ccclubs_login_id", user.getSuId());
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		if(session.getAttribute("ccclubs_login")==null && releasePaths.get(strUrl)==null){		
			session.setAttribute("ccclubs_tips", "对不起，您未登录或已过登录有效期，请先登录");
			String strContextPath=HRequest.getContextPath();
			if(loginPath==null)
				throw new ServletException("can't find login path,please see config set at web.xml");
			String strRedirect="/"+strContextPath+"/"+loginPath;
			strRedirect=strRedirect.replaceAll("[/]+", "/");
			HResponse.sendRedirect(strRedirect);		
			return;
		}
		else if(session.getAttribute("super_user")!=null)
		{
			request.setAttribute("canView",true);
			request.setAttribute("canAdd",true);
			request.setAttribute("canEdit",true);
			request.setAttribute("canDel",true);
			request.setAttribute("canExport",true);
			request.setAttribute("canStats",true);
			List<Boolean> canExp=new ArrayList();
			for(int i=0;i<13;i++){
				canExp.add(true);
			}
			request.setAttribute("canExp",canExp);
		}
		
		request.setAttribute("canExport", true);
		request.setAttribute("responseStart", new java.util.Date());
		
		filterChain.doFilter(request, response);
	}
	
	
	
	public void destroy() {
		
	}
	
}