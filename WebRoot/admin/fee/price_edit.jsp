<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/fee/price.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/fee/price.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csPrice.cspId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cspUserType",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspUserType",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cspId,cspUserType,cspPrice",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspUserType,cspGoods,cspOutets,cspModel,cspPrice",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"价格编辑":CTRL.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.hashcode = "${window.hashcode}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
	</script>
		
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>

</head>  
<body>

${lz:set("注释","****非法权限时的友情提醒****")}
<s:if test="#request.canView!=true && #request.canAdd!=true && #request.canEdit!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>${lz:config("nolimit.message")}</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>



<!------------------------LAZY3Q_JSP_BODY------------------------>
<!------------------------LAZY3Q_JSP_BODY------------------------>



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#priceForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		,"csPrice.cspId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspUserType==true">
		${lz:set("haveEditable",true)}
		,"csPrice.cspUserType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择计费类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspGoods==true">
		${lz:set("haveEditable",true)}
		,"csPrice.cspGoods":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择商品";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspOutets==true">
		${lz:set("haveEditable",true)}
		,"csPrice.cspOutets":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择网点";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspModel==true">
		${lz:set("haveEditable",true)}
		,"csPrice.cspModel":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择车型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspPrice==true">
		${lz:set("haveEditable",true)}
		,"csPrice.cspPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "价格输入格式错误";
		}
		</s:if>	
	},function(){
		${lz:set("注释","****表单提交自定义判断，阻止提交返回提示字符串即可****")}
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<s:if test="false"></s:if>
<s:elseif test="false"></s:elseif>
<s:else>
	<form class="form editform" ref="CsPrice" id="priceForm" name="priceForm" action="${empty CTRL.action?"price_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写价格相关信息，<span class="extrude">"*" 为必填选项。</span>			
					<input type="checkbox" ${empty edittype?"checked='checked'":""} id="all"/>显示全部可编辑项
				</div>
				</s:if>
				${lz:set("empty",lz:vacant(message))}
				<s:if test="#request.empty!=true">
				<br/>
				<center>
					<font color="red"><h4>${message}</h4></font>
				</center>
				<br/>
				</s:if>
				<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
				<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
				<input type="hidden" value="${complete}" name="complete" id="complete" />
				<input type="hidden" value="${ids}" name="ids" id="ids" />		
				<input type="hidden" value="${edittype}" name="edittype" id="edittype" />
				<input type="hidden" value="${method}" name="method" id="method" />
				<input type="hidden" value="${lz:html(ctrl)}" id="ctrl" name="ctrl" />
				<input type="hidden" value="${csPriceToken}" name="csPriceToken" id="csPriceToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csPrice.cspId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cspId和after$cspId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspId==true">
	${before$cspId}
	<dl class="cspId ${CTRL.e.cspId?"hidden":""}" major  ref="cspId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csPrice$cspId!=null">${csPrice$cspId}</s:if><s:else>
			<input type="hidden" value="${csPrice.cspId}" name="csPrice.cspId" id="cspId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspId">${csPrice.cspId}</textarea>
		 		<span>
		 	
			 ${csPrice.cspId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspId}
	</s:if>
	
	${lz:set("注释","*****************计费类型字段的输入框代码*****************")}
	${lz:set("注释","before$cspUserType和after$cspUserType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspUserType==true">
	${before$cspUserType}
	<dl class="cspUserType " major  ref="cspUserType" >
		<dt>计费类型:</dt>
		<s:if test="#request.CTRL.e.cspUserType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPrice$cspUserType!=null">${csPrice$cspUserType}</s:if><s:else>
		 	<select class="narrow" id="cspUserType" name="csPrice.cspUserType">
		 		<option value="">请选择</option>
 					${lz:set("items",get:hfts(lz:or(csoHost,get:host())))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.csutId}" ${csPrice.cspUserType==item.csutId?"selected":""}>${item.csutName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspUserType').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?key='+$('#cspUserType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择价格的计费类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****计费类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspUserType">${csPrice.cspUserType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/type_details.do?key=${csPrice.cspUserType}',{ctrl:{editable:false,visible:true}})">
			 ${csPrice.cspUserType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspUserType}
	</s:if>
	
	${lz:set("注释","*****************商品字段的输入框代码*****************")}
	${lz:set("注释","before$cspGoods和after$cspGoods变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspGoods==true">
	${before$cspGoods}
	<dl class="cspGoods " major  ref="cspGoods" >
		<dt>商　　品:</dt>
		<s:if test="#request.CTRL.e.cspGoods==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPrice$cspGoods!=null">${csPrice$cspGoods}</s:if><s:else>
		 	<select class="narrow" id="cspGoods" name="csPrice.cspGoods">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query("ccclubs_system","select * from cs_goods where 1=1 ") )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.csgId}" ${csPrice.cspGoods==item.csgId?"selected":""}>${item.csgName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspGoods').val())==''){return;};window.href('${basePath}${proname}/fee/goods_details.do?key='+$('#cspGoods').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择价格的商品</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspGoods">${csPrice.cspGoods}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/goods_details.do?key=${csPrice.cspGoods}',{ctrl:{editable:false,visible:true}})">
			 ${csPrice.cspGoods$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspGoods}
	</s:if>
	
	${lz:set("注释","*****************网点字段的输入框代码*****************")}
	${lz:set("注释","before$cspOutets和after$cspOutets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspOutets==true">
	${before$cspOutets}
	<dl class="cspOutets " major  ref="cspOutets" >
		<dt>网　　点:</dt>
		<s:if test="#request.CTRL.e.cspOutets==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPrice$cspOutets!=null">${csPrice$cspOutets}</s:if><s:else>
		 	<select class="narrow" id="cspOutets" name="csPrice.cspOutets">
		 		<option value="">请选择</option>
				    ${lz:set("items", lz:query$("ccclubs_system","select * from cs_outlets where cso_host=?" ,lz:or(csoHost,get:host())) )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.csoId}" ${csPrice.cspOutets==item.csoId?"selected":""}>${item.csoName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspOutets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#cspOutets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择价格的网点</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspOutets">${csPrice.cspOutets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csPrice.cspOutets}',{ctrl:{editable:false,visible:true}})">
			 ${csPrice.cspOutets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspOutets}
	</s:if>
	
	${lz:set("注释","*****************车型字段的输入框代码*****************")}
	${lz:set("注释","before$cspModel和after$cspModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspModel==true">
	${before$cspModel}
	<dl class="cspModel " major  ref="cspModel" >
		<dt>车　　型:</dt>
		<s:if test="#request.CTRL.e.cspModel==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPrice$cspModel!=null">${csPrice$cspModel}</s:if><s:else>
		 	<select class="narrow" id="cspModel" name="csPrice.cspModel">
		 		<option value="">请选择</option>
 					${lz:set("items",get:hms(lz:or(csoHost,get:host())))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.cscmId}" ${csPrice.cspModel==item.cscmId?"selected":""}>${item.cscmName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspModel').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#cspModel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择价格的车型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspModel">${csPrice.cspModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${csPrice.cspModel}',{ctrl:{editable:false,visible:true}})">
			 ${csPrice.cspModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspModel}
	</s:if>
	
	${lz:set("注释","*****************价格字段的输入框代码*****************")}
	${lz:set("注释","before$cspPrice和after$cspPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspPrice==true">
	${before$cspPrice}
	<dl class="cspPrice " major  ref="cspPrice" >
		<dt>价　　格:</dt>
		<s:if test="#request.CTRL.e.cspPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csPrice$cspPrice!=null">${csPrice$cspPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPrice.cspPrice" id="cspPrice"  value="${csPrice.cspPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入价格的价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspPrice">${csPrice.cspPrice}</textarea>
		 		<span>
		 	
			 ${csPrice.cspPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspPrice}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csPrice.cspId}" name="csPrice.cspId" id="cspId" />
					</s:if>			
					<s:if test="#request.haveEditable==true">
						${lz:set("submitsCount",lz:size(CTRL.submits))}
						<s:if test="#request.submitsCount>0">
							<s:iterator value="#request.CTRL.submits" id="submit" status="i">
					<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
							</s:iterator>
						</s:if>
						<s:else>
					<s:if test="#request.complete!=null && #request.complete!=''">
						${lz:set("注释","****如果调用时传入了complete完成句柄，那么只能提交到本页面，由action控制调用该句柄****")}
						<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
							保存&nbsp;&nbsp;</button>
					</s:if>
					<s:else>
						<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
						<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
							保存&nbsp;&nbsp;</button>
					</s:else>
						</s:else>
					<button class="button" type="reset" value="重置">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
						重置&nbsp;&nbsp;</button>
					</s:if>
					<s:else>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						确定&nbsp;&nbsp;</button>
					</s:else>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:else>

<script>
	function cancel(){
		$.closeModalDialog();
	}
	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>