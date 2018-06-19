<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

<style>
	#compute{clear:both;}
</style>

<script type="text/javascript">
$(function(){
	$(".form dl[ref=csoId]").css("width","98%").find(".state-input").removeClass("narrow").addClass("wide");
});
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
function $$(money){
	return Number(money).toFixed(2);
}
</script>
<div class="hidden">
	<dl ref="canMoney" >
		<dt>会员可用余额:</dt>
		<dd>
		 	<div class="state-input narrow">
		 		<input type="hidden" value="" id="canMoney" />
		 	 	<span></span>
	 	 		&nbsp;	
	 	 	</div>
		</dd>		
	</dl>
	<dl ref="canCoupon" >
		<dt>可用现金券:</dt>
		<dd>
		 	<div class="state-input narrow">
		 		<input type="hidden" value="" id="canCoupon" />
		 	 	<span></span>
	 	 		&nbsp;	
	 	 	</div>
		</dd>		
	</dl>
	<dl ref="canFreehour">
		<dt>可用免费小时:</dt>
		<dd>
		 	<div class="state-input narrow">
		 		<input type="hidden" value="" id="canFreehour" />
		 	 	<span></span>
	 	 		&nbsp;	
	 	 	</div>
		</dd>		
	</dl>
	<dl ref="canCoin">
		<dt>可用红包:</dt>
		<dd>
		 	<div class="state-input narrow">
		 		<input type="hidden" value="" id="canCoin" />
		 	 	<span></span>
	 	 		&nbsp;	
	 	 	</div>
		</dd>		
	</dl>
	<dl ref="defineMargin" >
		<dt>长租保证金:</dt>
		<dd>
		 	<input type="text" class="input" value="" name="defineMargin" id="defineMargin" />	
		</dd>		
	</dl>
	<input type="hidden" class="input" value="" name="conflict" id="conflict" />
</div>

${lz:set("uuid",lz:uuid())}