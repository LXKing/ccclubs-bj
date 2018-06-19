package com.ccclubs.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lazy3q.web.helper.$;

public class ManageFilter implements Filter {
	
	private static Map<String,Boolean> releasePaths=new HashMap();
	static{
		releasePaths.put("/manage/login_login.html", true);
		releasePaths.put("/manage/login.html", true);
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest HRequest = (HttpServletRequest) request;
		HttpServletResponse HResponse = (HttpServletResponse) response;
		HttpSession session = HRequest.getSession();
		
		//判断是否手机登录,或者是否要求手机登录
		//String basePath = request.getScheme()+"://"+request.getServerName()+"/";
		
		String strUrl=HRequest.getServletPath().toString();
		if(!strUrl.startsWith("/manage")){
			filterchain.doFilter(request, response);
			return;
		}
		if(session.getAttribute("ccclubs_login_id")==null&& releasePaths.get(strUrl)==null){		
			session.setAttribute("ccclubs_tips", "对不起，您未登录或已过登录有效期，请先登录");
			String strContextPath=HRequest.getContextPath();
			String strRedirect="/"+strContextPath+"/manage/login.html";
			strRedirect=strRedirect.replaceAll("[/]+", "/");
			HResponse.sendRedirect(strRedirect);		
			return;
		}		
		filterchain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
