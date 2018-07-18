<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/csUnderlineMember.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/csUnderlineMember.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnderlineMember.cumId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cumId,cumHost,cumArea,cumUser,cumMobile,cumAddTime,cumUpdateTime,cumStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cumId,cumHost,cumArea,cumUser,cumMobile,cumAddTime,cumUpdateTime,cumStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cumId,cumHost,cumArea,cumUser,cumMobile,cumAddTime,cumUpdateTime,cumStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cumId,cumHost,cumArea,cumUser,cumMobile,cumAddTime,cumUpdateTime,cumStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"线下人员管理":CTRL.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:Or(style,lz:Or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
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
	$("#csUnderlineMemberForm").form({
		"":function(){}
			<s:if test="#request.CTRL.e.cumId==true">
			${lz:set("haveEditable",true)}
			,"csUnderlineMember.cumId":function(el){
			}
			</s:if>	
			<s:if test="#request.CTRL.e.cumHost==true">
			${lz:set("haveEditable",true)}
			,"csUnderlineMember.cumHost":function(el){
			}
			</s:if>	
			
			<s:if test="#request.CTRL.e.cumArea==true">
			${lz:set("haveEditable",true)}
			,"csUnderlineMember.cumArea":function(el){
			}
			</s:if>	
			
			<s:if test="#request.CTRL.e.cumUser==true">
			${lz:set("haveEditable",true)}
			,"csUnderlineMember.cumUser":function(el){
				if(el.value.length>32 && el.value.indexOf("[*]")==-1)
					return "真实姓名最大长度为32个字符";
			}
			</s:if>	
			
			
			<s:if test="#request.CTRL.e.cumMobile==true">
			${lz:set("haveEditable",true)}
			,"csUnderlineMember.cumMobile":function(el){
				if(el.value.length>32 && el.value.indexOf("[*]")==-1)
					return "联系电话最大长度为32个字符";
			}
			</s:if>	
			<s:if test="#request.CTRL.e.cumAddTime==true">
			${lz:set("haveEditable",true)}
			,"csUnderlineMember.cumAddTime":function(el){
				if(jQuery.trim(el.value)=="")
					return "添加时间不能为空";
			}
			</s:if>	
			
			<s:if test="#request.CTRL.e.cumUpdateTime==true">
			${lz:set("haveEditable",true)}
			,"csUnderlineMember.cumUpdateTime":function(el){
				if(el.value.length>32 && el.value.indexOf("[*]")==-1)
					return "最后登录时间最大长度为32个字符";
			}
			</s:if>
			
			<s:if test="#request.CTRL.e.cumStatus==true">
			${lz:set("haveEditable",true)}
			,"csUnderlineMember.cumStatus":function(el){
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
	<form class="form editform" ref="csUnderlineMember" id="csUnderlineMemberForm" name="csUnderlineMemberForm" action="${empty CTRL.action?"csUnderlineMember_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写线下人员管理相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnderlineMemberToken}" name="csUnderlineMemberToken" id="csUnderlineMemberToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnderlineMember.cumId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
				
				
				
				
				




	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csuuId和after$csuuId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cumId==true">
	${before$csuuId}
	<dl class="cumId ${CTRL.e.cumId?"hidden":""}" major  ref="cumId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cumId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnderlineMember$cumId!=null">${csUnderlineMember$cumId}</s:if><s:else>
			<input type="hidden" value="${csUnderlineMember.cumId}" name="csUnderlineMember.cumId" id="cumId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cumId">${csUnderlineMember.cumId}</textarea>
		 		<span>
		 	
			 ${csUnderlineMember.cumId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cumHost和after$cumHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cumHost==true">
	${before$cumHost}
	<dl class="cumHost " major  ref="cumHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cumHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnderlineMember$cumHost!=null">${csUnderlineMember$cumHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cumHost" name="csUnderlineMember.cumHost">
		 				<option selected value="${csUnderlineMember.cumHost}">
		 					${get:SrvHost(csUnderlineMember.cumHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cumHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cumHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cumHost">${csUnderlineMember.cumHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnderlineMember.cumHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnderlineMember.cumHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cumHost}
	</s:if>
	
	
	
	
	
	
	
		
	${lz:set("注释","*****************所在区域字段的输入框代码*****************")}
	${lz:set("注释","before$cumArea和after$cumArea变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cumArea==true">
	${before$cumArea}
	<dl class="cumArea " major  ref="cumArea" >
		<dt>所在区域:</dt>
		<s:if test="#request.CTRL.e.cumArea==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOutlets$cumArea!=null">${csUnderlineMember$cumArea}</s:if><s:else>
 			<select class="combox narrow" action="${basePath}${proname}/configurator/area_query.do?size=-1&csaHost={cumHost}" id="cumArea" name="csUnderlineMember.cumArea">
 				<option selected value="${csUnderlineMember.cumArea}">
 					${get:CsArea(csOutlets.cumArea).csaName}
 				</option>
 			</select>
 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cumArea').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?key='+$('#cumArea').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择区域</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所在区域字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cumArea">${csUnderlineMember.cumArea}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/area_details.do?key=${csUnderlineMember.cumArea}',{ctrl:{editable:false,visible:true}})">
			 ${csUnderlineMember.cumArea$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cumArea}
	</s:if>
	
	
	
	
	
	
	
	
	
	
	${lz:set("注释","*****************真实姓名字段的输入框代码*****************")}
	${lz:set("注释","before$cumUser和after$cumUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cumUser==true">
	${before$cumUser}
	<dl class="cumUser " major  ref="cumUser" >
		<dt>对接人:</dt>
		<s:if test="#request.CTRL.e.cumUser==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnderlineMember$cumUser!=null">${csUnderlineMember$cumUser}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnderlineMember.cumUser" id="cumUser"  value="${csUnderlineMember.cumUser}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入线下管理人员的真实姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****真实姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cumUser">${csUnderlineMember.cumUser}</textarea>
		 		<span>
		 	
			 ${csUnderlineMember.cumUser$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cumUser}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$cumMobile和after$cumMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cumMobile==true">
	${before$cumMobile}
	<dl class="cumMobile " major  ref="cumMobile" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.cumMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnderlineMember$cumMobile!=null">${csUnderlineMember$cumMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnderlineMember.cumMobile" id="cumMobile"  value="${csUnderlineMember.cumMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入线下管理人员的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cumMobile">${csUnderlineMember.cumMobile}</textarea>
		 		<span>
		 	
			 ${csUnderlineMember.cumMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cumMobile}
	</s:if>
	
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cumAddTime和after$cumAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cumAddTime==true">
	${before$cumAddTime}
	<dl class="cumAddTime " major  ref="cumAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cumAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnderlineMember$cumAddTime!=null">${csUnderlineMember$cumAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnderlineMember.cumAddTime" id="cumAddTime"  value="<s:date name="csUnderlineMember.cumAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择线下管理人员的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cumAddTime">${csUnderlineMember.cumAddTime}</textarea>
		 		<span>
		 	
			 ${csUnderlineMember.cumAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cumAddTime}
	</s:if>
	

	
	
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cumUpdateTime和after$cumUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cumUpdateTime==true">
	${before$cumUpdateTime}
	<dl class="cumUpdateTime " major  ref="cumUpdateTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cumUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnderlineMember$cumUpdateTime!=null">${csUnderlineMember$cumUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnderlineMember.cumUpdateTime" id="cumUpdateTime"  value="<s:date name="csUnderlineMember.cumUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择线下管理人员的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cumUpdateTime">${csUnderlineMember.cumUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUnderlineMember.cumUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cumUpdateTime}
	</s:if>
	
	
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cumStatus和after$cumStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cumStatus==true">
	${before$cumStatus}
	<dl class="cumStatus " major  ref="cumStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cumStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnderlineMember$cumStatus!=null">${csUnderlineMember$cumStatus}</s:if><s:else>
		 	<select class="narrow" id="cumStatus" name="csUnderlineMember.cumStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csUnderlineMember.cumStatus==1?"selected":""}>正常</option>
				<option value="-1" ${csUnderlineMember.cumStatus==-1?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择线下管理人员的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cumStatus">${csUnderlineMember.cumStatus}</textarea>
		 		<span>
		 	
			 ${csUnderlineMember.cumStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cumStatus}
	</s:if>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnderlineMember.cumId}" name="csUnderlineMember.cumId" id="cumId" />
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


