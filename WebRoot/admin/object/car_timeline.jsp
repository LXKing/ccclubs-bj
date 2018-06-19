<%@ page contentType="text/html; charset=utf-8" language="java"
 import="java.sql.*,java.util.*,java.io.*,javax.naming.*,java.awt.image.*,javax.imageio.*,java.awt.*;" errorPage="" %>
<%
		response.setContentType("image/jpeg");
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		ServletOutputStream responseOutputStream =response.getOutputStream();
		ImageIO.write((BufferedImage)request.getAttribute("image"), "PNG", responseOutputStream);
		responseOutputStream.flush();
		responseOutputStream.close();
		out.clear();
		out = pageContext.pushBody();
%>
