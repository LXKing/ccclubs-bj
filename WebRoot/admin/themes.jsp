<%@ page language="java" import="java.util.*,java.util.regex.Pattern,java.io.*,javax.servlet.http.Cookie;" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%
	String requestType=(String)request.getParameter("type");
%>
<%if(requestType.equals("list")){%>
	<div>
	风格：<select id="styles_select" name="styles_select" style="width:200px;margin:10px;">
	<%
	 String strrootpath=application.getRealPath("/");//获取WEB根所在路径
	 File fDirectory=new File(strrootpath+"/admin/css");
	 String [] folders=fDirectory.list();
	//读目录
	if(folders!=null)
	for(String folder:folders)
	{
		 if(new File(strrootpath+"/admin/css",folder).isDirectory() && new File(strrootpath+"/admin/css/"+folder+"/lazy3q.ui.css").exists())
		 {
		 	File fFiles=new File(strrootpath+"/admin/css/"+folder);
			String [] files=fFiles.list();
			if(files==null)continue;
			for(String filename:files)
			{				
				if(Pattern.compile("lazy3q.*.\\.css").matcher(filename).find())
					request.setAttribute("stylePath",folder+"/"+filename);
			}
			if(request.getAttribute("stylePath")==null)continue;
		 %>
		 <option value="${stylePath}" ${lz:or(style,lz:or(cookie.style.value,lz:config("style")))==stylePath?"selected":""}><%=folder%></option>
		 <%
		 }  	
	}
	%>
	</select>
	<!-- <br>
	主题：<select id="themes_select" name="themes_select" style="width:200px;margin:10px;">
		<option value="frame" ${lz:or(theme,lz:or(cookie.theme.value,lz:config("theme")))=="frame"?"selected":""}>管理系统模式</option>
		<option value="window" ${lz:or(theme,lz:or(cookie.theme.value,lz:config("theme")))=="window"?"selected":""}>桌面窗口模式</option>
	</select> -->
	</div>
<%}%>
<%
if(requestType.equals("select")){
	Cookie cookie = new Cookie("style",request.getParameter("css"));
	cookie.setMaxAge(60*60*24*30);//默认cookie保存1个月
	response.addCookie(cookie);	
	response.sendRedirect("index.do");
}%>