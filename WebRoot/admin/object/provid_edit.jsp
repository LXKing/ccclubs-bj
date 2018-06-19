<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/provid.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/provid.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csProvid.cspId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cspId,cspHost,cspName,cspPerson,cspConcat,cspFlag,cspRemark,cspStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspHost,cspName,cspPerson,cspConcat,cspFlag,cspRemark,cspStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cspId,cspHost,cspName,cspPerson,cspConcat,cspFlag,cspRemark,cspStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspHost,cspName,cspPerson,cspConcat,cspFlag,cspRemark,cspUpdateTime,cspAddTime,cspStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"供应商编辑":CTRL.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
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
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#providForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspHost==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspName==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspPerson==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspPerson":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "负责人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspConcat==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspConcat":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系方式最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspFlag==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "程序标识最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("provid_query.do",{exists:true,cspFlag:el.value});
				if(exists && exists.length>0){
					if(${csProvid.cspId==null} || exists[0].value!=$("#cspId").val())
						return "程序标识已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspRemark==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspAddTime==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspStatus==true">
		${lz:set("haveEditable",true)}
		,"csProvid.cspStatus":function(el){
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

<form class="form editform" ref="CsProvid" id="providForm" name="providForm" action="${empty CTRL.action?"provid_save.do":CTRL.action}" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<s:if test="#request.haveEditable==true">
		<div class="prompt">
			温馨提示：请仔细填写供应商相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
		<input type="hidden" value="${csProvidToken}" name="csProvidToken" id="csProvidToken" />
					
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csProvid.cspId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
		
		${lz:set("注释","*****************编号字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspId==true">
		<dl class="${CTRL.e.cspId?"hidden":""}" major  ref="cspId" >
			<dt>编　　号:</dt>
			<s:if test="#request.CTRL.e.cspId==true">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd input="hidden">
			<s:if test="#request.csProvid$cspId!=null">${csProvid$cspId}</s:if><s:else>
				<input type="hidden" value="${csProvid.cspId}" name="csProvid.cspId" id="cspId" />
		 	 </s:else>
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspId">${csProvid.cspId}</textarea>
			 		<span>
			 	
				 ${csProvid.cspId$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************城市字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspHost==true">
		<dl class="" major  ref="cspHost" >
			<dt>城　　市:</dt>
			<s:if test="#request.CTRL.e.cspHost==true">
			${lz:set("haveEditable",true)}
			<dd input="combox">
			<s:if test="#request.csProvid$cspHost!=null">${csProvid$cspHost}</s:if><s:else>
			 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cspHost" name="csProvid.cspHost">
			 				<option selected value="${csProvid.cspHost}">
			 					${get:SrvHost(csProvid.cspHost).shName}
			 				</option>
			 			</select>
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cspHost').val(),{ctrl:{editable:false}})"></a>
		 	 </s:else>
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspHost">${csProvid.cspHost}</textarea>
			 		<span>
			 	
				 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csProvid.cspHost}',{ctrl:{editable:false,visible:true}})">
				 ${csProvid.cspHost$}</a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************名称字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspName==true">
		<dl class="" major  ref="cspName" >
			<dt>名　　称:</dt>
			<s:if test="#request.CTRL.e.cspName==true">
			${lz:set("haveEditable",true)}
			<dd input="text">
			<s:if test="#request.csProvid$cspName!=null">${csProvid$cspName}</s:if><s:else>
			 	<input type="text" class="input narrow"  maxlength="32" name="csProvid.cspName" id="cspName"  value="${csProvid.cspName}"/>
		 	 </s:else>
		 	 <b>*</b>
		 	 <em>请输入供应商的名称</em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspName">${csProvid.cspName}</textarea>
			 		<span>
			 	
				 ${csProvid.cspName$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************负责人字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspPerson==true">
		<dl class="" major  ref="cspPerson" >
			<dt>负&nbsp;&nbsp;责&nbsp;&nbsp;人:</dt>
			<s:if test="#request.CTRL.e.cspPerson==true">
			${lz:set("haveEditable",true)}
			<dd input="text">
			<s:if test="#request.csProvid$cspPerson!=null">${csProvid$cspPerson}</s:if><s:else>
			 	<input type="text" class="input narrow"  maxlength="32" name="csProvid.cspPerson" id="cspPerson"  value="${csProvid.cspPerson}"/>
		 	 </s:else>
		 	 
		 	 <em>请输入供应商的负责人</em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****负责人字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspPerson">${csProvid.cspPerson}</textarea>
			 		<span>
			 	
				 ${csProvid.cspPerson$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************联系方式字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspConcat==true">
		<dl class="" major  ref="cspConcat" >
			<dt>联系方式:</dt>
			<s:if test="#request.CTRL.e.cspConcat==true">
			${lz:set("haveEditable",true)}
			<dd input="text">
			<s:if test="#request.csProvid$cspConcat!=null">${csProvid$cspConcat}</s:if><s:else>
			 	<input type="text" class="input narrow"  maxlength="32" name="csProvid.cspConcat" id="cspConcat"  value="${csProvid.cspConcat}"/>
		 	 </s:else>
		 	 
		 	 <em>请输入供应商的联系方式</em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****联系方式字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspConcat">${csProvid.cspConcat}</textarea>
			 		<span>
			 	
				 ${csProvid.cspConcat$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************程序标识字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspFlag==true">
		<dl class="" major  ref="cspFlag" >
			<dt>程序标识:</dt>
			<s:if test="#request.CTRL.e.cspFlag==true">
			${lz:set("haveEditable",true)}
			<dd input="text">
			<s:if test="#request.csProvid$cspFlag!=null">${csProvid$cspFlag}</s:if><s:else>
			 	<input type="text" class="input narrow"  maxlength="32" name="csProvid.cspFlag" id="cspFlag"  value="${csProvid.cspFlag}"/>
		 	 </s:else>
		 	 
		 	 <em>请输入供应商的程序标识</em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****程序标识字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspFlag">${csProvid.cspFlag}</textarea>
			 		<span>
			 	
				 ${csProvid.cspFlag$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspRemark==true">
		<dl class="" minor  ref="cspRemark" style="width:98%;">
			<dt>备注信息:</dt>
			<s:if test="#request.CTRL.e.cspRemark==true">
			${lz:set("haveEditable",true)}
			<dd input="textarea">
			<s:if test="#request.csProvid$cspRemark!=null">${csProvid$cspRemark}</s:if><s:else>
			 	<textarea class="input wide"  id="cspRemark" name="csProvid.cspRemark" rows="5">${csProvid.cspRemark}</textarea>
		 	 </s:else>
		 	 
		 	 <em>请输入供应商的备注信息</em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input wide">
			 		<span>
			 	
				 ${csProvid.cspRemark$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspUpdateTime==true">
		<dl class="" major  ref="cspUpdateTime" >
			<dt>修改时间:</dt>
			<s:if test="#request.CTRL.e.cspUpdateTime==true">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			<s:if test="#request.csProvid$cspUpdateTime!=null">${csProvid$cspUpdateTime}</s:if><s:else>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csProvid.cspUpdateTime" id="cspUpdateTime"  value="<s:date name="csProvid.cspUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 </s:else>
		 	 <b>*</b>
		 	 <em>请选择供应商的修改时间</em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspUpdateTime">${csProvid.cspUpdateTime}</textarea>
			 		<span>
			 	
				 ${csProvid.cspUpdateTime$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspAddTime==true">
		<dl class="" major  ref="cspAddTime" >
			<dt>添加时间:</dt>
			<s:if test="#request.CTRL.e.cspAddTime==true">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			<s:if test="#request.csProvid$cspAddTime!=null">${csProvid$cspAddTime}</s:if><s:else>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csProvid.cspAddTime" id="cspAddTime"  value="<s:date name="csProvid.cspAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 </s:else>
		 	 <b>*</b>
		 	 <em>请选择供应商的添加时间</em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspAddTime">${csProvid.cspAddTime}</textarea>
			 		<span>
			 	
				 ${csProvid.cspAddTime$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		${lz:set("注释","*****************状态字段的输入框代码*****************")}
		<s:if test="#request.CTRL.v.cspStatus==true">
		<dl class="" major  ref="cspStatus" >
			<dt>状　　态:</dt>
			<s:if test="#request.CTRL.e.cspStatus==true">
			${lz:set("haveEditable",true)}
			<dd input="select">
			<s:if test="#request.csProvid$cspStatus!=null">${csProvid$cspStatus}</s:if><s:else>
			 	<select class="narrow" id="cspStatus" name="csProvid.cspStatus">
			 		<option value="">请选择</option>
					<option value="1" ${csProvid.cspStatus==1?"selected":""}>正常</option>
					<option value="0" ${csProvid.cspStatus==0?"selected":""}>无效</option>
			 	</select>
		 	 </s:else>
		 	 <b>*</b>
		 	 <em>请选择供应商的状态</em>
			</dd>
			</s:if>
			<s:else>
			${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
			<dd>
			 	<div class="state-input narrow">
			 		<textarea class="" style="display:none;" id="cspStatus">${csProvid.cspStatus}</textarea>
			 		<span>
			 	
				 ${csProvid.cspStatus$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
	
		
		
		<div class="line"></div>
		<center class="buttons">			
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csProvid.cspId}" name="csProvid.cspId" id="cspId" />
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