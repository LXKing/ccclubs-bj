<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%
/**
 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
 */ 
 
 String sApp=request.getParameter("app");
 if(sApp==null || sApp.trim().equals(""))
 	sApp="";
			
String upload_forder=request.getRealPath("/")+"/attached/";
upload_forder=upload_forder.replaceAll("\\\\", "/");
if(!upload_forder.endsWith("/"))
	upload_forder+="/";
	
String port="";
if(request.getServerPort()!=80)
	port=":"+request.getServerPort();
String upload_domain = request.getScheme()+"://"+request.getServerName()+port+request.getContextPath()+"/attached/";

response.setContentType("text/html; charset=UTF-8"); 

//创建文件夹
SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
String ymd = sdf.format(new Date());
upload_forder += sApp + ymd;
upload_domain += sApp + ymd;
File dirFile = new File(upload_forder);
if (!dirFile.exists()) {
	dirFile.mkdirs();
}

FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);
upload.setHeaderEncoding("UTF-8");
List items = upload.parseRequest(request);
Iterator itr = items.iterator();
while (itr.hasNext()) {
	FileItem item = (FileItem) itr.next();
	String fileName = item.getName();
	long fileSize = item.getSize();
	if (!item.isFormField()) {
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();		
		SimpleDateFormat df = new SimpleDateFormat("HHmmssS"+new Random().nextInt(1000));
		String newFileName = df.format(new Date()) + "." + fileExt;
		String pfilename=request.getParameter("filename");
		if(pfilename!=null && !pfilename.trim().equals(""))
			newFileName=pfilename;
		try{
			File uploadedFile = new File(upload_forder, newFileName);
			item.write(uploadedFile);
		}catch(Exception e){
			out.println(getError("上传文件失败。"));
			return;
		}
		out.println("{\"error\":0,\"url\":\""+upload_domain + newFileName+"\"}");
		break;
	}
}
%>
<%!
private String getError(String message) {
	return "{\"error\":1,\"message\":\""+message+"\"}";
}
%>