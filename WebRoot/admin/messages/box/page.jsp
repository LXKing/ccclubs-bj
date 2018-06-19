<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>

	<div style="clear:both"></div>
	<ul class="pager">
	
	<s:if test="#request.page.total>0">
			<li>
				<a href="javascript:void(0);" onclick="$(this).parents('.list').load('${basePath}${servletPath}?page=0&${lz:querys("UTF-8","page")}')">首页</a>
			</li>
		<s:if test="#request.page.prev==true">
			<li>
				<a href="javascript:void(0);" onclick="$(this).parents('.list').load('${basePath}${servletPath}?page=${page.index-1}&${lz:querys("UTF-8","page")}')">上一页</a>
			</li>
		</s:if>
		<s:if test="#request.page.next==true">
			<li>
				<a href="javascript:void(0);" onclick="$(this).parents('.list').load('${basePath}${servletPath}?page=${page.index+1}&${lz:querys("UTF-8","page")}')">下一页</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="$(this).parents('.list').load('${basePath}${servletPath}?page=${page.total-1}&${lz:querys("UTF-8","page")}')">${page.total}</a>
			</li>
		</s:if>	
	</s:if>
		<li class="text">共${page.count+0}条记录</li>
				
	</ul>	
	
	<div style="clear:both;"></div>	
	
	
		