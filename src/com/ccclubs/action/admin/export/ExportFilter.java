package com.ccclubs.action.admin.export;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazy3q.web.helper.$;
public class ExportFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest _request, ServletResponse _response,
			FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) _request;
        HttpServletResponse response = (HttpServletResponse) _response;
        String canExport = request.getParameter("export");
        if(!$.empty(canExport) && canExport.equals("true")){
        	request.getRequestDispatcher(".."+File.separator+"export"+File.separator+"export.do").forward(request, response);
        }else{
        	chain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
