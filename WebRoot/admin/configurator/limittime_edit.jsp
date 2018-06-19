<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/limittime.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/limittime.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLimitTime.csltId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csltId,csltHost,csltWeekDay,csltNum,csltStart,csltEnd",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csltId,csltHost,csltWeekDay,csltNum,csltStart,csltEnd",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csltId,csltHost,csltWeekDay,csltNum,csltStart,csltEnd",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csltId,csltHost,csltWeekDay,csltNum,csltStart,csltEnd",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"交通限行编辑":CTRL.title}</title>
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
${lz:set("message","请注意，如果是批量设置时间后，需要随便选一个限行保存一下才生效 *_*")}
<script>
$(function(){
	$("#csltStart").attr("onclick","").click(function(){
		WdatePicker({
			el:"csltStart",
			dateFmt:'HH:mm'
		});
	});	
	$("#csltEnd").attr("onclick","").click(function(){
		WdatePicker({
			el:"csltEnd",
			dateFmt:'HH:mm'
		});
	});	
})
</script>
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
	$("#limittimeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csltId==true">
		${lz:set("haveEditable",true)}
		,"csLimitTime.csltId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csltHost==true">
		${lz:set("haveEditable",true)}
		,"csLimitTime.csltHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csltWeekDay==true">
		${lz:set("haveEditable",true)}
		,"csLimitTime.csltWeekDay":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择星期几？";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csltNum==true">
		${lz:set("haveEditable",true)}
		,"csLimitTime.csltNum":function(el){
			if(jQuery.trim(el.value)=="")
				return "限行尾号不能为空";
			if(el.value.length>16 && el.value.indexOf("[*]")==-1)
				return "限行尾号最大长度为16个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csltStart==true">
		${lz:set("haveEditable",true)}
		,"csLimitTime.csltStart":function(el){
			if(jQuery.trim(el.value)=="")
				return "开始时间不能为空";
			if(el.value.length>16 && el.value.indexOf("[*]")==-1)
				return "开始时间最大长度为16个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csltEnd==true">
		${lz:set("haveEditable",true)}
		,"csLimitTime.csltEnd":function(el){
			if(jQuery.trim(el.value)=="")
				return "结束时间不能为空";
			if(el.value.length>16 && el.value.indexOf("[*]")==-1)
				return "结束时间最大长度为16个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csltUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csLimitTime.csltUpdateTime":function(el){
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
	<form class="form editform" ref="CsLimitTime" id="limittimeForm" name="limittimeForm" action="${empty CTRL.action?"limittime_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写交通限行相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csLimitTimeToken}" name="csLimitTimeToken" id="csLimitTimeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csLimitTime.csltId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csltId和after$csltId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csltId==true">
	${before$csltId}
	<dl class="csltId ${CTRL.e.csltId?"hidden":""}" major  ref="csltId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csltId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csLimitTime$csltId!=null">${csLimitTime$csltId}</s:if><s:else>
			<input type="hidden" value="${csLimitTime.csltId}" name="csLimitTime.csltId" id="csltId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csltId">${csLimitTime.csltId}</textarea>
		 		<span>
		 	
			 ${csLimitTime.csltId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csltId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csltHost和after$csltHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csltHost==true">
	${before$csltHost}
	<dl class="csltHost " major  ref="csltHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csltHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLimitTime$csltHost!=null">${csLimitTime$csltHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csltHost" name="csLimitTime.csltHost">
		 				<option selected value="${csLimitTime.csltHost}">
		 					${get:SrvHost(csLimitTime.csltHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csltHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csltHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csltHost">${csLimitTime.csltHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csLimitTime.csltHost}',{ctrl:{editable:false,visible:true}})">
			 ${csLimitTime.csltHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csltHost}
	</s:if>
	
	${lz:set("注释","*****************星期几？字段的输入框代码*****************")}
	${lz:set("注释","before$csltWeekDay和after$csltWeekDay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csltWeekDay==true">
	${before$csltWeekDay}
	<dl class="csltWeekDay " major  ref="csltWeekDay" >
		<dt>星期几？:</dt>
		<s:if test="#request.CTRL.e.csltWeekDay==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLimitTime$csltWeekDay!=null">${csLimitTime$csltWeekDay}</s:if><s:else>
		 	<select class="narrow" id="csltWeekDay" name="csLimitTime.csltWeekDay">
		 		<option value="">请选择</option>
				<option value="1" ${csLimitTime.csltWeekDay==1?"selected":""}>星期天</option>
				<option value="2" ${csLimitTime.csltWeekDay==2?"selected":""}>星期一</option>
				<option value="3" ${csLimitTime.csltWeekDay==3?"selected":""}>星期二</option>
				<option value="4" ${csLimitTime.csltWeekDay==4?"selected":""}>星期三</option>
				<option value="5" ${csLimitTime.csltWeekDay==5?"selected":""}>星期四</option>
				<option value="6" ${csLimitTime.csltWeekDay==6?"selected":""}>星期五</option>
				<option value="7" ${csLimitTime.csltWeekDay==7?"selected":""}>星期六</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择交通限行的星期几？</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****星期几？字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csltWeekDay">${csLimitTime.csltWeekDay}</textarea>
		 		<span>
		 	
			 ${csLimitTime.csltWeekDay$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csltWeekDay}
	</s:if>
	
	${lz:set("注释","*****************限行尾号字段的输入框代码*****************")}
	${lz:set("注释","before$csltNum和after$csltNum变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csltNum==true">
	${before$csltNum}
	<dl class="csltNum " major  ref="csltNum" >
		<dt>限行尾号:</dt>
		<s:if test="#request.CTRL.e.csltNum==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLimitTime$csltNum!=null">${csLimitTime$csltNum}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="16" name="csLimitTime.csltNum" id="csltNum"  value="${csLimitTime.csltNum}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>限行的车牌尾号，123表未以1或2或3的数字尾号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****限行尾号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csltNum">${csLimitTime.csltNum}</textarea>
		 		<span>
		 	
			 ${csLimitTime.csltNum$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csltNum}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csltStart和after$csltStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csltStart==true">
	${before$csltStart}
	<dl class="csltStart " major  ref="csltStart" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.csltStart==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLimitTime$csltStart!=null">${csLimitTime$csltStart}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="16" name="csLimitTime.csltStart" id="csltStart"  value="${csLimitTime.csltStart}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入交通限行的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csltStart">${csLimitTime.csltStart}</textarea>
		 		<span>
		 	
			 ${csLimitTime.csltStart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csltStart}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csltEnd和after$csltEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csltEnd==true">
	${before$csltEnd}
	<dl class="csltEnd " major  ref="csltEnd" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csltEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLimitTime$csltEnd!=null">${csLimitTime$csltEnd}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="16" name="csLimitTime.csltEnd" id="csltEnd"  value="${csLimitTime.csltEnd}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入交通限行的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csltEnd">${csLimitTime.csltEnd}</textarea>
		 		<span>
		 	
			 ${csLimitTime.csltEnd$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csltEnd}
	</s:if>
	
	${lz:set("注释","*****************更新时间字段的输入框代码*****************")}
	${lz:set("注释","before$csltUpdateTime和after$csltUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csltUpdateTime==true">
	${before$csltUpdateTime}
	<dl class="csltUpdateTime " minor  ref="csltUpdateTime" >
		<dt>更新时间:</dt>
		<s:if test="#request.CTRL.e.csltUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLimitTime$csltUpdateTime!=null">${csLimitTime$csltUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLimitTime.csltUpdateTime" id="csltUpdateTime"  value="<s:date name="csLimitTime.csltUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择交通限行的更新时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****更新时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csltUpdateTime">${csLimitTime.csltUpdateTime}</textarea>
		 		<span>
		 	
			 ${csLimitTime.csltUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csltUpdateTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csLimitTime.csltId}" name="csLimitTime.csltId" id="csltId" />
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