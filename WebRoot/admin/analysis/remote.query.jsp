<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
			 
			 
			<dl group="${relateObject==null?"suMobile":relateObject}" id="form-dl-suMobile">
				<dt>外勤人员：</dt>
				<dd>
					<input type="text" class="input"  maxlength="12" size="16" name="suMobile" id="suMobile"  value="${srvUser.suMobile}"/>
				</dd>
			</dl>
			 	
			<dl group="${relateObject==null?"addTime":relateObject}" id="form-dl-addTime">
				<dt>添加时间：</dt>
				<dd>
		 			<input type="datetime" class="input" ref="addTime" name="addTime" id="addTime" start="${lz:date(addTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(addTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${addTime}">
				</dd>
			</dl>
			 	
		
			 	
			 
			
			
			 	
			 
			
			
			