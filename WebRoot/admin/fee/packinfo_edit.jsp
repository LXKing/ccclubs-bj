<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/fee/packinfo.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/fee/packinfo.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csPackInfo.cspiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cspiId,cspiName,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue,cspiScript",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspiId,cspiName,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue,cspiScript",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cspiId,cspiName,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue,cspiScript",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspiId,cspiName,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue,cspiScript,cspiAddTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"套餐内容编辑":CTRL.title}</title>
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
	$("#packinfoForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cspiId==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiName==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiName":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiPack==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiPack":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiGoods==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiGoods":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiOutlets==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiModel==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiType==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiValue==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiValue":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "值输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiScript==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiScript":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "函数脚本最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspiAddTime==true">
		${lz:set("haveEditable",true)}
		,"csPackInfo.cspiAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
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
	<form class="form editform" ref="CsPackInfo" id="packinfoForm" name="packinfoForm" action="${empty CTRL.action?"packinfo_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写套餐内容相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csPackInfoToken}" name="csPackInfoToken" id="csPackInfoToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csPackInfo.cspiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cspiId和after$cspiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiId==true">
	${before$cspiId}
	<dl class="cspiId ${CTRL.e.cspiId?"hidden":""}" major  ref="cspiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cspiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csPackInfo$cspiId!=null">${csPackInfo$cspiId}</s:if><s:else>
			<input type="hidden" value="${csPackInfo.cspiId}" name="csPackInfo.cspiId" id="cspiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspiId">${csPackInfo.cspiId}</textarea>
		 		<span>
		 	
			 ${csPackInfo.cspiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiId}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$cspiName和after$cspiName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiName==true">
	${before$cspiName}
	<dl class="cspiName " major  ref="cspiName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.cspiName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPackInfo$cspiName!=null">${csPackInfo$cspiName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPackInfo.cspiName" id="cspiName"  value="${csPackInfo.cspiName}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入套餐内容的名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspiName">${csPackInfo.cspiName}</textarea>
		 		<span>
		 	
			 ${csPackInfo.cspiName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiName}
	</s:if>
	
	${lz:set("注释","*****************所属套餐字段的输入框代码*****************")}
	${lz:set("注释","before$cspiPack和after$cspiPack变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiPack==true">
	${before$cspiPack}
	<dl class="cspiPack " major  ref="cspiPack" >
		<dt>所属套餐:</dt>
		<s:if test="#request.CTRL.e.cspiPack==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csPackInfo$cspiPack!=null">${csPackInfo$cspiPack}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/fee/pack_query.do?size=-1" id="cspiPack" name="csPackInfo.cspiPack">
		 				<option selected value="${csPackInfo.cspiPack}">
		 					${get:CsPack(csPackInfo.cspiPack).cspName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspiPack').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?key='+$('#cspiPack').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属套餐字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspiPack">${csPackInfo.cspiPack}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/pack_details.do?key=${csPackInfo.cspiPack}',{ctrl:{editable:false,visible:true}})">
			 ${csPackInfo.cspiPack$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiPack}
	</s:if>
	
	${lz:set("注释","*****************所属商品字段的输入框代码*****************")}
	${lz:set("注释","before$cspiGoods和after$cspiGoods变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiGoods==true">
	${before$cspiGoods}
	<dl class="cspiGoods " major  ref="cspiGoods" >
		<dt>所属商品:</dt>
		<s:if test="#request.CTRL.e.cspiGoods==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csPackInfo$cspiGoods!=null">${csPackInfo$cspiGoods}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/fee/goods_query.do?size=-1" id="cspiGoods" name="csPackInfo.cspiGoods">
		 				<option selected value="${csPackInfo.cspiGoods}">
		 					${get:CsGoods(csPackInfo.cspiGoods).csgName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspiGoods').val())==''){return;};window.href('${basePath}${proname}/fee/goods_details.do?key='+$('#cspiGoods').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属商品字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspiGoods">${csPackInfo.cspiGoods}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/goods_details.do?key=${csPackInfo.cspiGoods}',{ctrl:{editable:false,visible:true}})">
			 ${csPackInfo.cspiGoods$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiGoods}
	</s:if>
	
	${lz:set("注释","*****************所属网点字段的输入框代码*****************")}
	${lz:set("注释","before$cspiOutlets和after$cspiOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiOutlets==true">
	${before$cspiOutlets}
	<dl class="cspiOutlets " major  ref="cspiOutlets" >
		<dt>所属网点:</dt>
		<s:if test="#request.CTRL.e.cspiOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csPackInfo$cspiOutlets!=null">${csPackInfo$cspiOutlets}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_query.do?size=-1" id="cspiOutlets" name="csPackInfo.cspiOutlets">
		 				<option selected value="${csPackInfo.cspiOutlets}">
		 					${get:CsOutlets(csPackInfo.cspiOutlets).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspiOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#cspiOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>适用到的网点，不选表示适用于所有网点</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspiOutlets">${csPackInfo.cspiOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csPackInfo.cspiOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csPackInfo.cspiOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiOutlets}
	</s:if>
	
	${lz:set("注释","*****************所属车型字段的输入框代码*****************")}
	${lz:set("注释","before$cspiModel和after$cspiModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiModel==true">
	${before$cspiModel}
	<dl class="cspiModel " major  ref="cspiModel" style="width:98%;">
		<dt>所属车型:</dt>
		<s:if test="#request.CTRL.e.cspiModel==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csPackInfo$cspiModel!=null">${csPackInfo$cspiModel}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csPackInfo.cspiModel" id="cspiModel"/>
 					${lz:set("items", lz:query("ccclubs_system","select * from cs_car_model where 1=1 ") )}
					<s:iterator value="#request.items" id="item" status="i">
			<label><input type="checkbox" ${lz:idin(csPackInfo.cspiModel,item.cscm_id)?"checked='checked'":""} value="#${item.cscm_id}#" name="csPackInfo.cspiModel" id="cspiModel"/>
					${item.cscm_name}&nbsp;&nbsp;&nbsp;</label>
					</s:iterator>
					${lz:set("items",null)}
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>适用到的车型，不选表示适用于所有车型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cspiModel">${csPackInfo.cspiModel}</textarea>
		 		<span>
		 	
			 ${csPackInfo.cspiModel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiModel}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$cspiType和after$cspiType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiType==true">
	${before$cspiType}
	<dl class="cspiType " major  ref="cspiType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.cspiType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPackInfo$cspiType!=null">${csPackInfo$cspiType}</s:if><s:else>
		 	<select class="narrow" id="cspiType" name="csPackInfo.cspiType">
		 		<option value="">请选择</option>
				<option value="0" ${csPackInfo.cspiType==0?"selected":""}>数量</option>
				<option value="1" ${csPackInfo.cspiType==1?"selected":""}>价格</option>
				<option value="2" ${csPackInfo.cspiType==2?"selected":""}>折扣</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>当类型为折扣时，值为0到1之间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspiType">${csPackInfo.cspiType}</textarea>
		 		<span>
		 	
			 ${csPackInfo.cspiType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiType}
	</s:if>
	
	${lz:set("注释","*****************值字段的输入框代码*****************")}
	${lz:set("注释","before$cspiValue和after$cspiValue变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiValue==true">
	${before$cspiValue}
	<dl class="cspiValue " major  ref="cspiValue" >
		<dt>值:</dt>
		<s:if test="#request.CTRL.e.cspiValue==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csPackInfo$cspiValue!=null">${csPackInfo$cspiValue}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPackInfo.cspiValue" id="cspiValue"  value="${csPackInfo.cspiValue}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>数量或价格或折扣，由类型决定</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspiValue">${csPackInfo.cspiValue}</textarea>
		 		<span>
		 	
			 ${csPackInfo.cspiValue$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiValue}
	</s:if>
	
	${lz:set("注释","*****************函数脚本字段的输入框代码*****************")}
	${lz:set("注释","before$cspiScript和after$cspiScript变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiScript==true">
	${before$cspiScript}
	<dl class="cspiScript " minor  ref="cspiScript" style="width:98%;">
		<dt>函数脚本:</dt>
		<s:if test="#request.CTRL.e.cspiScript==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPackInfo$cspiScript!=null">${csPackInfo$cspiScript}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csPackInfo.cspiScript" id="cspiScript"  value="${csPackInfo.cspiScript}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>优惠项脚本，二次干预优惠项，返回false表示不能使用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****函数脚本字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cspiScript">${csPackInfo.cspiScript}</textarea>
		 		<span>
		 	
			 ${csPackInfo.cspiScript$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiScript}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cspiAddTime和after$cspiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspiAddTime==true">
	${before$cspiAddTime}
	<dl class="cspiAddTime " major  ref="cspiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cspiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csPackInfo$cspiAddTime!=null">${csPackInfo$cspiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPackInfo.cspiAddTime" id="cspiAddTime"  value="<s:date name="csPackInfo.cspiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择套餐内容的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspiAddTime">${csPackInfo.cspiAddTime}</textarea>
		 		<span>
		 	
			 ${csPackInfo.cspiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspiAddTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csPackInfo.cspiId}" name="csPackInfo.cspiId" id="cspiId" />
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