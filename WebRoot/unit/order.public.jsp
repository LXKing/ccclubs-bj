<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


<script type="text/javascript">
function empty(inputs){
	var bEmpty = false;
	$.each(inputs,function(i,o){
		if($.trim($(o).val())==""){
			bEmpty=true;
		}
	});
	return bEmpty;
}
function getFormParams(){
	var oForm=$("#orderForm").get(0);
	var params = {};
	for(var i=0;i<oForm.length;i++ ){
	   var oItem = oForm[i];
	   if(!oItem)
			continue;
		params[$(oItem).attr("name")]=$(oItem).val();		
	}
	return params;
}
</script>
${lz:set("uuid",lz:uuid())}