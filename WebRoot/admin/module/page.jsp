<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>

	<div style="clear:both"></div>
	<ul class="pager">
	
	<s:if test="#request.page.total>0">
			<li>
				<a href="?page=0&${lz:querys("UTF-8","page")}">首页</a>
			</li>
		<s:if test="#request.page.prev==true">
			<li>
				<a href="?page=${page.index-1}&${lz:querys("UTF-8","page")}">上一页</a>
			</li>
		</s:if>
	
	<!-- 取前后3页 -->
		<s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="(#request.page.index-3)<0?0:(#request.page.index-3)"/>
			 <s:param name="last" value="(#request.page.index+3)>(#request.page.total-1)?(#request.page.total-1):(#request.page.index+3)"/>
			 <s:iterator>
				  <li ${counter.current==(page.index+1)?"class='focus'":""}>
					<a href="?page=${counter.current-1}&${lz:querys("UTF-8","page")}">${counter.current}</a>
				  </li>
			 </s:iterator>
	 	</s:bean>
	 	
		<s:if test="#request.page.next==true">
			<li>
				<a href="?page=${page.index+1}&${lz:querys("UTF-8","page")}">下一页</a>
			</li>
			<li>
				<a href="?page=${page.total-1}&${lz:querys("UTF-8","page")}">${page.total}</a>
			</li>
		</s:if>
	
	</s:if>
	
		<% request.setAttribute("now",new java.util.Date());%>
		<li title="处理耗时${now.time-responseStart.time}毫秒" class="text">共${page.count}条记录</li>
		
		
		<li class="text">
			<input id="page" name="page" value="${page.index+1}" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" style="width:32px; height:16px; text-align:center;"/>
			页
			<input id="size" name="size" value="${page.size}" maxlength="3" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" style="width:32px; height:16px; text-align:center;"/>
			条
		</li>
		<li onclick="window.location='?page='+($('#page').val()-1)+'&size='+($('#size').val()-1)+'&${lz:queryss("UTF-8","page","size")}';">转到</li>
		<li class="text">
			&nbsp;&nbsp;&nbsp;&nbsp;
		</li>				
	</ul>	
	
	<div style="clear:both;"></div>	