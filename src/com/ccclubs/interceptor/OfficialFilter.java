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

public class OfficialFilter implements Filter {
	
	private static Map<String,Boolean> releasePaths=new HashMap();
	static{
		releasePaths.put("/official/login.do", true);
		releasePaths.put("/official/login.login.do", true);
		releasePaths.put("/official/login.logout.do", true);
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
		
		if(!strUrl.startsWith("/official")){
			filterchain.doFilter(request, response);
			return;
		}
		if(session.getAttribute("unit_login")==null && releasePaths.get(strUrl)==null){		
			session.setAttribute("ccclubs_tips", "对不起，您未登录或已过登录有效期，请先登录");
			String strContextPath=HRequest.getContextPath();
			String strRedirect="/"+strContextPath+"/official/login.do";
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
