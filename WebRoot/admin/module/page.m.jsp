<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>

	<s:if test="#request.page.total>0">
	<p>
		<center data-corners="false" data-role="controlgroup" data-type="horizontal" class="page"  data-mini="true">
		    <a data-role="button" href="?page=0&${lz:querys("UTF-8","page")}">首页</a>
		    <a data-role="button" href="?page=${page.index-1}&${lz:querys("UTF-8","page")}">上一页</a>
		    <lable data-role="button">${page.index+1}</lable>
		    <a data-role="button" href="?page=${page.index+1}&${lz:querys("UTF-8","page")}">下一页</a>
		    <a data-role="button" href="?page=${page.total-1}&${lz:querys("UTF-8","page")}">未页</a>
		</center>
	</p>
	</s:if>