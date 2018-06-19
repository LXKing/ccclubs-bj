<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/fee/item.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/fee/item.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csItem.csiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csiId,csiTitle,csiType,csiSort,csiPrice,csiRebate,csiFlag,csiDataString,csiDataNumber,csiDataDecimal,csiThumb,csiImages,csiDepict,csiSale,csiLevel,csiRemark,csiStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csiId,csiTitle,csiType,csiSort,csiPrice,csiRebate,csiFlag,csiDataString,csiDataNumber,csiDataDecimal,csiThumb,csiImages,csiDepict,csiSale,csiLevel,csiRemark,csiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csiId,csiTitle,csiType,csiSort,csiPrice,csiRebate,csiFlag,csiDataString,csiDataNumber,csiDataDecimal,csiThumb,csiImages,csiDepict,csiSale,csiLevel,csiRemark,csiStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csiId,csiTitle,csiType,csiSort,csiPrice,csiRebate,csiFlag,csiDataString,csiDataNumber,csiDataDecimal,csiThumb,csiImages,csiDepict,csiSale,csiLevel,csiRemark,csiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"商品信息编辑":CTRL.title}</title>
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
		if($("#csiDepict").size()>0){
			 //构造编辑页csItem.csiDepict为html编辑控件
			 KE.init({
				id : 'csiDepict',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=item_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csiDepict");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#itemForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csiId==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiTitle==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "商品名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "商品名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiType==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择商品类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiSort==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiSort":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择商品分类";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiPrice==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "商品价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiRebate==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "折扣输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiFlag==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiDataString==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiDataString":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "附加字串最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiDataNumber==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiDataNumber":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiDataDecimal==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiDataDecimal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "附加小数输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiThumb==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiThumb":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiImages==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiDepict==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiDepict":function(el){
			if($("#csiDepict").get(0))
			 	KE.sync("csiDepict");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "描述最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiSale==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiSale":function(el){
			if(jQuery.trim(el.value)=="")
				return "已售数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiLevel==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiLevel":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiRemark==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiAddTime==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiStatus==true">
		${lz:set("haveEditable",true)}
		,"csItem.csiStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
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
	<form class="form editform" ref="CsItem" id="itemForm" name="itemForm" action="${empty CTRL.action?"item_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写商品信息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csItemToken}" name="csItemToken" id="csItemToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csItem.csiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csiId和after$csiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiId==true">
	${before$csiId}
	<dl class="csiId ${CTRL.e.csiId?"hidden":""}" major  ref="csiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csItem$csiId!=null">${csItem$csiId}</s:if><s:else>
			<input type="hidden" value="${csItem.csiId}" name="csItem.csiId" id="csiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiId">${csItem.csiId}</textarea>
		 		<span>
		 	
			 ${csItem.csiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiId}
	</s:if>
	
	${lz:set("注释","*****************商品名称字段的输入框代码*****************")}
	${lz:set("注释","before$csiTitle和after$csiTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiTitle==true">
	${before$csiTitle}
	<dl class="csiTitle " major  ref="csiTitle" >
		<dt>商品名称:</dt>
		<s:if test="#request.CTRL.e.csiTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csItem$csiTitle!=null">${csItem$csiTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csItem.csiTitle" id="csiTitle"  value="${csItem.csiTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入商品信息的商品名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiTitle">${csItem.csiTitle}</textarea>
		 		<span>
		 	
			 ${csItem.csiTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiTitle}
	</s:if>
	
	${lz:set("注释","*****************商品类型字段的输入框代码*****************")}
	${lz:set("注释","before$csiType和after$csiType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiType==true">
	${before$csiType}
	<dl class="csiType " major  ref="csiType" >
		<dt>商品类型:</dt>
		<s:if test="#request.CTRL.e.csiType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csItem$csiType!=null">${csItem$csiType}</s:if><s:else>
		 	<select class="narrow" id="csiType" name="csItem.csiType">
		 		<option value="">请选择</option>
				<option value="0" ${csItem.csiType==0?"selected":""}>实物</option>
				<option value="1" ${csItem.csiType==1?"selected":""}>现金券</option>
				<option value="2" ${csItem.csiType==2?"selected":""}>免费小时</option>
				<option value="3" ${csItem.csiType==3?"selected":""}>红包</option>
				<option value="4" ${csItem.csiType==4?"selected":""}>套餐</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品信息的商品类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiType">${csItem.csiType}</textarea>
		 		<span>
		 	
			 ${csItem.csiType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiType}
	</s:if>
	
	${lz:set("注释","*****************商品分类字段的输入框代码*****************")}
	${lz:set("注释","before$csiSort和after$csiSort变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiSort==true">
	${before$csiSort}
	<dl class="csiSort " major  ref="csiSort" >
		<dt>商品分类:</dt>
		<s:if test="#request.CTRL.e.csiSort==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csItem$csiSort!=null">${csItem$csiSort}</s:if><s:else>
		 	<select class="narrow" id="csiSort" name="csItem.csiSort">
		 		<option value="">请选择</option>
				    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'ITEM_TYPE') )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csItem.csiSort==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csiSort').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csiSort').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品信息的商品分类</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品分类字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiSort">${csItem.csiSort}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csItem.csiSort}',{ctrl:{editable:false,visible:true}})">
			 ${csItem.csiSort$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiSort}
	</s:if>
	
	${lz:set("注释","*****************商品价格字段的输入框代码*****************")}
	${lz:set("注释","before$csiPrice和after$csiPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiPrice==true">
	${before$csiPrice}
	<dl class="csiPrice " major  ref="csiPrice" >
		<dt>商品价格:</dt>
		<s:if test="#request.CTRL.e.csiPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csItem$csiPrice!=null">${csItem$csiPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csItem.csiPrice" id="csiPrice"  value="${csItem.csiPrice}"/>
	 	 </s:else>
	 	 元
	 	 <b>*</b>
	 	 <em>请输入商品信息的商品价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiPrice">${csItem.csiPrice}</textarea>
		 		<span>
		 	
			 ${csItem.csiPrice$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiPrice}
	</s:if>
	
	${lz:set("注释","*****************折扣字段的输入框代码*****************")}
	${lz:set("注释","before$csiRebate和after$csiRebate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiRebate==true">
	${before$csiRebate}
	<dl class="csiRebate " major  ref="csiRebate" >
		<dt>折　　扣:</dt>
		<s:if test="#request.CTRL.e.csiRebate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csItem$csiRebate!=null">${csItem$csiRebate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csItem.csiRebate" id="csiRebate"  value="${csItem.csiRebate}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>0到1之间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****折扣字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiRebate">${csItem.csiRebate}</textarea>
		 		<span>
		 	
			 ${csItem.csiRebate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiRebate}
	</s:if>
	
	${lz:set("注释","*****************标识字段的输入框代码*****************")}
	${lz:set("注释","before$csiFlag和after$csiFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiFlag==true">
	${before$csiFlag}
	<dl class="csiFlag " minor  ref="csiFlag" >
		<dt>标　　识:</dt>
		<s:if test="#request.CTRL.e.csiFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csItem$csiFlag!=null">${csItem$csiFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csItem.csiFlag" id="csiFlag"  value="${csItem.csiFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商品信息的标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiFlag">${csItem.csiFlag}</textarea>
		 		<span>
		 	
			 ${csItem.csiFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiFlag}
	</s:if>
	
	${lz:set("注释","*****************附加字串字段的输入框代码*****************")}
	${lz:set("注释","before$csiDataString和after$csiDataString变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiDataString==true">
	${before$csiDataString}
	<dl class="csiDataString " minor  ref="csiDataString" >
		<dt>附加字串:</dt>
		<s:if test="#request.CTRL.e.csiDataString==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csItem$csiDataString!=null">${csItem$csiDataString}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csItem.csiDataString" id="csiDataString"  value="${csItem.csiDataString}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商品信息的附加字串</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****附加字串字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiDataString">${csItem.csiDataString}</textarea>
		 		<span>
		 	
			 ${csItem.csiDataString$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiDataString}
	</s:if>
	
	${lz:set("注释","*****************附加数字字段的输入框代码*****************")}
	${lz:set("注释","before$csiDataNumber和after$csiDataNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiDataNumber==true">
	${before$csiDataNumber}
	<dl class="csiDataNumber " minor  ref="csiDataNumber" >
		<dt>附加数字:</dt>
		<s:if test="#request.CTRL.e.csiDataNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csItem$csiDataNumber!=null">${csItem$csiDataNumber}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csItem.csiDataNumber" id="csiDataNumber"  value="${csItem.csiDataNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商品信息的附加数字</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****附加数字字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiDataNumber">${csItem.csiDataNumber}</textarea>
		 		<span>
		 	
			 ${csItem.csiDataNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiDataNumber}
	</s:if>
	
	${lz:set("注释","*****************附加小数字段的输入框代码*****************")}
	${lz:set("注释","before$csiDataDecimal和after$csiDataDecimal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiDataDecimal==true">
	${before$csiDataDecimal}
	<dl class="csiDataDecimal " minor  ref="csiDataDecimal" >
		<dt>附加小数:</dt>
		<s:if test="#request.CTRL.e.csiDataDecimal==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csItem$csiDataDecimal!=null">${csItem$csiDataDecimal}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csItem.csiDataDecimal" id="csiDataDecimal"  value="${csItem.csiDataDecimal}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商品信息的附加小数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****附加小数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiDataDecimal">${csItem.csiDataDecimal}</textarea>
		 		<span>
		 	
			 ${csItem.csiDataDecimal$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiDataDecimal}
	</s:if>
	
	${lz:set("注释","*****************缩略图字段的输入框代码*****************")}
	${lz:set("注释","before$csiThumb和after$csiThumb变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiThumb==true">
	${before$csiThumb}
	<dl class="csiThumb " minor  ref="csiThumb" style="width:98%;">
		<dt>缩&nbsp;&nbsp;略&nbsp;&nbsp;图:</dt>
		<s:if test="#request.CTRL.e.csiThumb==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csItem$csiThumb!=null">${csItem$csiThumb}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csItem.csiThumb" id="csiThumb"  value="${csItem.csiThumb}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csiThumb').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csiThumb').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csiThumb').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传商品信息的缩略图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缩略图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csiThumb">${csItem.csiThumb}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csItem.csiThumb))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csItem.csiThumb}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiThumb}
	</s:if>
	
	${lz:set("注释","*****************图片列表字段的输入框代码*****************")}
	${lz:set("注释","before$csiImages和after$csiImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiImages==true">
	${before$csiImages}
	<dl class="csiImages " minor  ref="csiImages" style="width:98%;">
		<dt>图片列表:</dt>
		<s:if test="#request.CTRL.e.csiImages==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csItem$csiImages!=null">${csItem$csiImages}</s:if><s:else>
		    <div id="csiImages" class="wide input imagelist">
			<s:generator val="csItem.csiImages" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csItem.csiImages" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#csiImages').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csItem.csiImages&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传商品信息的图片列表</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****图片列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csiImages">${csItem.csiImages}</textarea>
		 		<span>
		 	 <s:generator val="csItem.csiImages" separator=",">
		 	 	<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="<s:property/>"/>
					</s:if>
				</s:iterator>
			 </s:generator>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiImages}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$csiDepict和after$csiDepict变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiDepict==true">
	${before$csiDepict}
	<dl class="csiDepict " minor  ref="csiDepict" style="width:98%;">
		<dt>描　　述:</dt>
		<s:if test="#request.CTRL.e.csiDepict==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csItem$csiDepict!=null">${csItem$csiDepict}</s:if><s:else>
		    <textarea class="input wide"  id="csiDepict" name="csItem.csiDepict" style="height:300px;visibility:hidden;">${csItem.csiDepict}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商品信息的描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csItem.csiDepict$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiDepict}
	</s:if>
	
	${lz:set("注释","*****************已售数字段的输入框代码*****************")}
	${lz:set("注释","before$csiSale和after$csiSale变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiSale==true">
	${before$csiSale}
	<dl class="csiSale " major  ref="csiSale" >
		<dt>已&nbsp;&nbsp;售&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.csiSale==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csItem$csiSale!=null">${csItem$csiSale}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csItem.csiSale" id="csiSale"  value="${csItem.csiSale}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入商品信息的已售数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已售数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiSale">${csItem.csiSale}</textarea>
		 		<span>
		 	
			 ${csItem.csiSale$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiSale}
	</s:if>
	
	${lz:set("注释","*****************优先级字段的输入框代码*****************")}
	${lz:set("注释","before$csiLevel和after$csiLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiLevel==true">
	${before$csiLevel}
	<dl class="csiLevel " major  ref="csiLevel" >
		<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级:</dt>
		<s:if test="#request.CTRL.e.csiLevel==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csItem$csiLevel!=null">${csItem$csiLevel}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csItem.csiLevel" id="csiLevel"  value="${csItem.csiLevel}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>大于0的整数，越大优先级越高</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优先级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiLevel">${csItem.csiLevel}</textarea>
		 		<span>
		 	
			 ${csItem.csiLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiLevel}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csiRemark和after$csiRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiRemark==true">
	${before$csiRemark}
	<dl class="csiRemark " minor  ref="csiRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csiRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csItem$csiRemark!=null">${csItem$csiRemark}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csItem.csiRemark" id="csiRemark"  value="${csItem.csiRemark}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商品信息的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csiRemark">${csItem.csiRemark}</textarea>
		 		<span>
		 	
			 ${csItem.csiRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiRemark}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csiUpdateTime和after$csiUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiUpdateTime==true">
	${before$csiUpdateTime}
	<dl class="csiUpdateTime " major  ref="csiUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csiUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csItem$csiUpdateTime!=null">${csItem$csiUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csItem.csiUpdateTime" id="csiUpdateTime"  value="<s:date name="csItem.csiUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品信息的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiUpdateTime">${csItem.csiUpdateTime}</textarea>
		 		<span>
		 	
			 ${csItem.csiUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csiAddTime和after$csiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiAddTime==true">
	${before$csiAddTime}
	<dl class="csiAddTime " major  ref="csiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csItem$csiAddTime!=null">${csItem$csiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csItem.csiAddTime" id="csiAddTime"  value="<s:date name="csItem.csiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品信息的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiAddTime">${csItem.csiAddTime}</textarea>
		 		<span>
		 	
			 ${csItem.csiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csiStatus和after$csiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiStatus==true">
	${before$csiStatus}
	<dl class="csiStatus " major  ref="csiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csItem$csiStatus!=null">${csItem$csiStatus}</s:if><s:else>
		 	<select class="narrow" id="csiStatus" name="csItem.csiStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csItem.csiStatus==1?"selected":""}>正常</option>
				<option value="2" ${csItem.csiStatus==2?"selected":""}>下架</option>
				<option value="0" ${csItem.csiStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品信息的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiStatus">${csItem.csiStatus}</textarea>
		 		<span>
		 	
			 ${csItem.csiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csItem.csiId}" name="csItem.csiId" id="csiId" />
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