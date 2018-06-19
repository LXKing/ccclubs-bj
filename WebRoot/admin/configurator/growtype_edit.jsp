<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/growtype.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/growtype.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csGrowType.csgtId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csgtId,csgtName,csgtIsOrder,csgtValue,csgtProfile,csgtStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgtId,csgtName,csgtIsOrder,csgtValue,csgtProfile,csgtStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csgtId,csgtIsArtif,csgtIsOrder,csgtValue,csgtProfile,csgtStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgtId,csgtName,csgtIsArtif,csgtIsOrder,csgtValue,csgtProfile,csgtStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"成长分类型编辑":CTRL.title}</title>
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
	$("#growtypeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csgtId==true">
		${lz:set("haveEditable",true)}
		,"csGrowType.csgtId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgtName==true">
		${lz:set("haveEditable",true)}
		,"csGrowType.csgtName":function(el){
			if(jQuery.trim(el.value)=="")
				return "类型名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "类型名称最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("growtype_query.do",{exists:true,csgtName:el.value});
				if(exists && exists.length>0){
					if(${csGrowType.csgtId==null} || exists[0].value!=$("#csgtId").val())
						return "类型名称已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgtIsArtif==true">
		${lz:set("haveEditable",true)}
		,"csGrowType.csgtIsArtif":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择人工操作项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgtIsOrder==true">
		${lz:set("haveEditable",true)}
		,"csGrowType.csgtIsOrder":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择与订单相关";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgtValue==true">
		${lz:set("haveEditable",true)}
		,"csGrowType.csgtValue":function(el){
			if(jQuery.trim(el.value)=="")
				return "变化值不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgtProfile==true">
		${lz:set("haveEditable",true)}
		,"csGrowType.csgtProfile":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "类型说明最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgtStatus==true">
		${lz:set("haveEditable",true)}
		,"csGrowType.csgtStatus":function(el){
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
	<form class="form editform" ref="CsGrowType" id="growtypeForm" name="growtypeForm" action="${empty CTRL.action?"growtype_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写成长分类型相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csGrowTypeToken}" name="csGrowTypeToken" id="csGrowTypeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csGrowType.csgtId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csgtId和after$csgtId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgtId==true">
	${before$csgtId}
	<dl class="csgtId ${CTRL.e.csgtId?"hidden":""}" major  ref="csgtId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csgtId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csGrowType$csgtId!=null">${csGrowType$csgtId}</s:if><s:else>
			<input type="hidden" value="${csGrowType.csgtId}" name="csGrowType.csgtId" id="csgtId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgtId">${csGrowType.csgtId}</textarea>
		 		<span>
		 	
			 ${csGrowType.csgtId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgtId}
	</s:if>
	
	${lz:set("注释","*****************类型名称字段的输入框代码*****************")}
	${lz:set("注释","before$csgtName和after$csgtName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgtName==true">
	${before$csgtName}
	<dl class="csgtName " major  ref="csgtName" >
		<dt>类型名称:</dt>
		<s:if test="#request.CTRL.e.csgtName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGrowType$csgtName!=null">${csGrowType$csgtName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csGrowType.csgtName" id="csgtName"  value="${csGrowType.csgtName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入成长分类型的类型名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgtName">${csGrowType.csgtName}</textarea>
		 		<span>
		 	
			 ${csGrowType.csgtName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgtName}
	</s:if>
	
	${lz:set("注释","*****************人工操作项字段的输入框代码*****************")}
	${lz:set("注释","before$csgtIsArtif和after$csgtIsArtif变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgtIsArtif==true">
	${before$csgtIsArtif}
	<dl class="csgtIsArtif " major  ref="csgtIsArtif" >
		<dt>人工操作项:</dt>
		<s:if test="#request.CTRL.e.csgtIsArtif==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGrowType$csgtIsArtif!=null">${csGrowType$csgtIsArtif}</s:if><s:else>
		 	<select class="narrow" id="csgtIsArtif" name="csGrowType.csgtIsArtif">
		 		<option value="">请选择</option>
				<option value="true" ${csGrowType.csgtIsArtif==true?"selected":""}>是</option>
				<option value="false" ${csGrowType.csgtIsArtif==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择成长分类型的人工操作项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人工操作项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgtIsArtif">${csGrowType.csgtIsArtif}</textarea>
		 		<span>
		 	
			 ${csGrowType.csgtIsArtif$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgtIsArtif}
	</s:if>
	
	${lz:set("注释","*****************与订单相关字段的输入框代码*****************")}
	${lz:set("注释","before$csgtIsOrder和after$csgtIsOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgtIsOrder==true">
	${before$csgtIsOrder}
	<dl class="csgtIsOrder " major  ref="csgtIsOrder" >
		<dt>与订单相关:</dt>
		<s:if test="#request.CTRL.e.csgtIsOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGrowType$csgtIsOrder!=null">${csGrowType$csgtIsOrder}</s:if><s:else>
		 	<select class="narrow" id="csgtIsOrder" name="csGrowType.csgtIsOrder">
		 		<option value="">请选择</option>
				<option value="true" ${csGrowType.csgtIsOrder==true?"selected":""}>是</option>
				<option value="false" ${csGrowType.csgtIsOrder==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择成长分类型的与订单相关</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****与订单相关字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgtIsOrder">${csGrowType.csgtIsOrder}</textarea>
		 		<span>
		 	
			 ${csGrowType.csgtIsOrder$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgtIsOrder}
	</s:if>
	
	${lz:set("注释","*****************变化值字段的输入框代码*****************")}
	${lz:set("注释","before$csgtValue和after$csgtValue变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgtValue==true">
	${before$csgtValue}
	<dl class="csgtValue " major  ref="csgtValue" >
		<dt>变&nbsp;&nbsp;化&nbsp;&nbsp;值:</dt>
		<s:if test="#request.CTRL.e.csgtValue==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csGrowType$csgtValue!=null">${csGrowType$csgtValue}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGrowType.csgtValue" id="csgtValue"  value="${csGrowType.csgtValue}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入成长分类型的变化值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****变化值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgtValue">${csGrowType.csgtValue}</textarea>
		 		<span>
		 	
			 ${csGrowType.csgtValue$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgtValue}
	</s:if>
	
	${lz:set("注释","*****************类型说明字段的输入框代码*****************")}
	${lz:set("注释","before$csgtProfile和after$csgtProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgtProfile==true">
	${before$csgtProfile}
	<dl class="csgtProfile " major  ref="csgtProfile" style="width:98%;">
		<dt>类型说明:</dt>
		<s:if test="#request.CTRL.e.csgtProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csGrowType$csgtProfile!=null">${csGrowType$csgtProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="csgtProfile" name="csGrowType.csgtProfile" rows="5">${csGrowType.csgtProfile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入成长分类型的类型说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csGrowType.csgtProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgtProfile}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csgtStatus和after$csgtStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgtStatus==true">
	${before$csgtStatus}
	<dl class="csgtStatus " major  ref="csgtStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csgtStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGrowType$csgtStatus!=null">${csGrowType$csgtStatus}</s:if><s:else>
		 	<select class="narrow" id="csgtStatus" name="csGrowType.csgtStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csGrowType.csgtStatus==1?"selected":""}>正常</option>
				<option value="0" ${csGrowType.csgtStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择成长分类型的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgtStatus">${csGrowType.csgtStatus}</textarea>
		 		<span>
		 	
			 ${csGrowType.csgtStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgtStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csGrowType.csgtId}" name="csGrowType.csgtId" id="csgtId" />
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