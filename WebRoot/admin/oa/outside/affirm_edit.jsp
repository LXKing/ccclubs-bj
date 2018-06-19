<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/outside/affirm.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/outside/affirm.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbAbAffirm.tbaaId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbaaId,tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbaaId,tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbaaId,tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm,tbaaConfirmTime,tbaaStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbaaId,tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm,tbaaAdder,tbaaEditor,tbaaConfirmTime,tbaaStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"异借确认编辑":CTRL.title}</title>
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

<s:if test="#request.CTRL.title=='确认'">
	<s:if test="tbAbAffirm.tbaaStatus>1">
		${lz:set("message","只有待确认或未确认的记录才能确认")}
		<lz:set name="jsonString">
			{
				title:"确认",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		<lz:set name="jsonString">
			{
				title:"确认",
				editable:false,
				action:"affirm_confirm.do",
				fields:{
					tbaaId:{visible:false}
					,tbaaRealRet:{editable:true}
					,tbaaRealTime:{editable:true}
					,tbaaConfirm:{visible:false}
					,tbaaAdder:{visible:false}
					,tbaaEditor:{visible:false}
					,tbaaConfirmTime:{visible:false}
					,tbaaRemark:{editable:true,visible:true}
					,tbaaStatus:{visible:false}
				},
				submits:[
					{name:"确&nbsp;&nbsp;&nbsp;&nbsp;认",value="确认"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
		
		<s:set name="before$tbaaRemark">
			<dl>
				<dt>是否上线:</dt>
				<dd>
					<select class="narrow" name="online" id="online">
						<option value="1">上线</option>
						<option value="2">不上线</option>
					</select>
					<b>*</b>
				</dd>
			</dl>
			<dl>
				<dt>调至网点:</dt>
				<dd>
					<select class="narrow" name="outlets" id="outlets">
						<option value="${tbAbAffirm.tbaaRet}">${tbAbAffirm.tbaaRet$}</option>
						<option value="${tbAbAffirm.tbaaOut}">${tbAbAffirm.tbaaOut$}</option>
					</select>
					<b>*</b>
				</dd>
			</dl>
		</s:set>
	</s:else>
</s:if>

<script>
$(function(){
	$("#tbaaRealTime").val("${tbAbAffirm.tbaaTime$}");
	$("#tbaaRealRet").setValue("${tbAbAffirm.tbaaRet}","${tbAbAffirm.tbaaRet$}");
});
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
	$("#affirmForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbaaId==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaHost==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaCar==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaOrder==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaOut==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaOut":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaRet==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaRet":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaRealRet==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaRealRet":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaTime==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaRealTime==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaRealTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaConfirm==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaConfirm":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaAdder==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaEditor==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaConfirmTime==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaConfirmTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaAddTime==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaRemark==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbaaStatus==true">
		${lz:set("haveEditable",true)}
		,"tbAbAffirm.tbaaStatus":function(el){
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
	<form class="form editform" ref="TbAbAffirm" id="affirmForm" name="affirmForm" action="${empty CTRL.action?"affirm_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写异借确认相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbAbAffirmToken}" name="tbAbAffirmToken" id="tbAbAffirmToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbAbAffirm.tbaaId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaId和after$tbaaId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaId==true">
	${before$tbaaId}
	<dl class="tbaaId ${CTRL.e.tbaaId?"hidden":""}" major  ref="tbaaId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbaaId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbAbAffirm$tbaaId!=null">${tbAbAffirm$tbaaId}</s:if><s:else>
			<input type="hidden" value="${tbAbAffirm.tbaaId}" name="tbAbAffirm.tbaaId" id="tbaaId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaId">${tbAbAffirm.tbaaId}</textarea>
		 		<span>
		 	
			 ${tbAbAffirm.tbaaId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaHost和after$tbaaHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaHost==true">
	${before$tbaaHost}
	<dl class="tbaaHost " major  ref="tbaaHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbaaHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbAbAffirm$tbaaHost!=null">${tbAbAffirm$tbaaHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbaaHost" name="tbAbAffirm.tbaaHost">
		 				<option selected value="${tbAbAffirm.tbaaHost}">
		 					${get:SrvHost(tbAbAffirm.tbaaHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbaaHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaHost">${tbAbAffirm.tbaaHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbAbAffirm.tbaaHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaHost}
	</s:if>
	
	${lz:set("注释","*****************所属车辆字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaCar和after$tbaaCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaCar==true">
	${before$tbaaCar}
	<dl class="tbaaCar " major  ref="tbaaCar" >
		<dt>所属车辆:</dt>
		<s:if test="#request.CTRL.e.tbaaCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbAbAffirm$tbaaCar!=null">${tbAbAffirm$tbaaCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={tbaaHost}" type="text" id="tbaaCar" name="tbAbAffirm.tbaaCar" text="${get:CsCar(tbAbAffirm.tbaaCar).cscNumber}" value="${tbAbAffirm.tbaaCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#tbaaCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaCar">${tbAbAffirm.tbaaCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${tbAbAffirm.tbaaCar}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaCar}
	</s:if>
	
	${lz:set("注释","*****************所属订单字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaOrder和after$tbaaOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaOrder==true">
	${before$tbaaOrder}
	<dl class="tbaaOrder " major  ref="tbaaOrder" >
		<dt>所属订单:</dt>
		<s:if test="#request.CTRL.e.tbaaOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbAbAffirm$tbaaOrder!=null">${tbAbAffirm$tbaaOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={tbaaHost}" type="text" id="tbaaOrder" name="tbAbAffirm.tbaaOrder" text="${get:CsOrder(tbAbAffirm.tbaaOrder).csoId}" value="${tbAbAffirm.tbaaOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#tbaaOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaOrder">${tbAbAffirm.tbaaOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${tbAbAffirm.tbaaOrder}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaOrder}
	</s:if>
	
	${lz:set("注释","*****************借出网点字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaOut和after$tbaaOut变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaOut==true">
	${before$tbaaOut}
	<dl class="tbaaOut " major  ref="tbaaOut" >
		<dt>借出网点:</dt>
		<s:if test="#request.CTRL.e.tbaaOut==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbAbAffirm$tbaaOut!=null">${tbAbAffirm$tbaaOut}</s:if><s:else>
		 			<input title="请输入网点名称进行查询" class="combox narrow" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={tbaaHost}" type="text" id="tbaaOut" name="tbAbAffirm.tbaaOut" text="${get:CsOutlets(tbAbAffirm.tbaaOut).csoName}" value="${tbAbAffirm.tbaaOut}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaOut').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#tbaaOut').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****借出网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaOut">${tbAbAffirm.tbaaOut}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${tbAbAffirm.tbaaOut}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaOut$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaOut}
	</s:if>
	
	${lz:set("注释","*****************预计还入网点字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaRet和after$tbaaRet变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaRet==true">
	${before$tbaaRet}
	<dl class="tbaaRet " major  ref="tbaaRet" >
		<dt>预计还入网点:</dt>
		<s:if test="#request.CTRL.e.tbaaRet==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbAbAffirm$tbaaRet!=null">${tbAbAffirm$tbaaRet}</s:if><s:else>
		 			<input title="请输入网点名称进行查询" class="combox narrow" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={tbaaHost}" type="text" id="tbaaRet" name="tbAbAffirm.tbaaRet" text="${get:CsOutlets(tbAbAffirm.tbaaRet).csoName}" value="${tbAbAffirm.tbaaRet}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaRet').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#tbaaRet').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预计还入网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaRet">${tbAbAffirm.tbaaRet}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${tbAbAffirm.tbaaRet}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaRet$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaRet}
	</s:if>
	
	${lz:set("注释","*****************实际还入网点字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaRealRet和after$tbaaRealRet变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaRealRet==true">
	${before$tbaaRealRet}
	<dl class="tbaaRealRet " major  ref="tbaaRealRet" >
		<dt>实际还入网点:</dt>
		<s:if test="#request.CTRL.e.tbaaRealRet==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbAbAffirm$tbaaRealRet!=null">${tbAbAffirm$tbaaRealRet}</s:if><s:else>
		 			<input title="请输入网点名称进行查询" class="combox narrow" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={tbaaHost}" type="text" id="tbaaRealRet" name="tbAbAffirm.tbaaRealRet" text="${get:CsOutlets(tbAbAffirm.tbaaRealRet).csoName}" value="${tbAbAffirm.tbaaRealRet}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaRealRet').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#tbaaRealRet').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际还入网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaRealRet">${tbAbAffirm.tbaaRealRet}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${tbAbAffirm.tbaaRealRet}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaRealRet$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaRealRet}
	</s:if>
	
	${lz:set("注释","*****************预计还入时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaTime和after$tbaaTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaTime==true">
	${before$tbaaTime}
	<dl class="tbaaTime " major  ref="tbaaTime" >
		<dt>预计还入时间:</dt>
		<s:if test="#request.CTRL.e.tbaaTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbAbAffirm$tbaaTime!=null">${tbAbAffirm$tbaaTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbAbAffirm.tbaaTime" id="tbaaTime"  value="<s:date name="tbAbAffirm.tbaaTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择异借确认的预计还入时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预计还入时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaTime">${tbAbAffirm.tbaaTime}</textarea>
		 		<span>
		 	
			 ${tbAbAffirm.tbaaTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaTime}
	</s:if>
	
	${lz:set("注释","*****************实际还入时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaRealTime和after$tbaaRealTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaRealTime==true">
	${before$tbaaRealTime}
	<dl class="tbaaRealTime " major  ref="tbaaRealTime" >
		<dt>实际还入时间:</dt>
		<s:if test="#request.CTRL.e.tbaaRealTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbAbAffirm$tbaaRealTime!=null">${tbAbAffirm$tbaaRealTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbAbAffirm.tbaaRealTime" id="tbaaRealTime"  value="<s:date name="tbAbAffirm.tbaaRealTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择异借确认的实际还入时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际还入时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaRealTime">${tbAbAffirm.tbaaRealTime}</textarea>
		 		<span>
		 	
			 ${tbAbAffirm.tbaaRealTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaRealTime}
	</s:if>
	
	${lz:set("注释","*****************确认人字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaConfirm和after$tbaaConfirm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaConfirm==true">
	${before$tbaaConfirm}
	<dl class="tbaaConfirm " major  ref="tbaaConfirm" >
		<dt>确&nbsp;&nbsp;认&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbaaConfirm==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbAbAffirm$tbaaConfirm!=null">${tbAbAffirm$tbaaConfirm}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbaaConfirm" name="tbAbAffirm.tbaaConfirm">
		 				<option selected value="${tbAbAffirm.tbaaConfirm}">
		 					${get:SrvUser(tbAbAffirm.tbaaConfirm).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaConfirm').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbaaConfirm').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****确认人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaConfirm">${tbAbAffirm.tbaaConfirm}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbAbAffirm.tbaaConfirm}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaConfirm$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaConfirm}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaAdder和after$tbaaAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaAdder==true">
	${before$tbaaAdder}
	<dl class="tbaaAdder " major  ref="tbaaAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbaaAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbAbAffirm$tbaaAdder!=null">${tbAbAffirm$tbaaAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbaaAdder" name="tbAbAffirm.tbaaAdder">
		 				<option selected value="${tbAbAffirm.tbaaAdder}">
		 					${get:SrvUser(tbAbAffirm.tbaaAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbaaAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaAdder">${tbAbAffirm.tbaaAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbAbAffirm.tbaaAdder}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaEditor和after$tbaaEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaEditor==true">
	${before$tbaaEditor}
	<dl class="tbaaEditor " major  ref="tbaaEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbaaEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbAbAffirm$tbaaEditor!=null">${tbAbAffirm$tbaaEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbaaEditor" name="tbAbAffirm.tbaaEditor">
		 				<option selected value="${tbAbAffirm.tbaaEditor}">
		 					${get:SrvUser(tbAbAffirm.tbaaEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbaaEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbaaEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaEditor">${tbAbAffirm.tbaaEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbAbAffirm.tbaaEditor}',{ctrl:{editable:false,visible:true}})">
			 ${tbAbAffirm.tbaaEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaEditor}
	</s:if>
	
	${lz:set("注释","*****************确认时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaConfirmTime和after$tbaaConfirmTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaConfirmTime==true">
	${before$tbaaConfirmTime}
	<dl class="tbaaConfirmTime " major  ref="tbaaConfirmTime" >
		<dt>确认时间:</dt>
		<s:if test="#request.CTRL.e.tbaaConfirmTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbAbAffirm$tbaaConfirmTime!=null">${tbAbAffirm$tbaaConfirmTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbAbAffirm.tbaaConfirmTime" id="tbaaConfirmTime"  value="<s:date name="tbAbAffirm.tbaaConfirmTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择异借确认的确认时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****确认时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaConfirmTime">${tbAbAffirm.tbaaConfirmTime}</textarea>
		 		<span>
		 	
			 ${tbAbAffirm.tbaaConfirmTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaConfirmTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaUpdateTime和after$tbaaUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaUpdateTime==true">
	${before$tbaaUpdateTime}
	<dl class="tbaaUpdateTime " major  ref="tbaaUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.tbaaUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbAbAffirm$tbaaUpdateTime!=null">${tbAbAffirm$tbaaUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbAbAffirm.tbaaUpdateTime" id="tbaaUpdateTime"  value="<s:date name="tbAbAffirm.tbaaUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择异借确认的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaUpdateTime">${tbAbAffirm.tbaaUpdateTime}</textarea>
		 		<span>
		 	
			 ${tbAbAffirm.tbaaUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaAddTime和after$tbaaAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaAddTime==true">
	${before$tbaaAddTime}
	<dl class="tbaaAddTime " major  ref="tbaaAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.tbaaAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbAbAffirm$tbaaAddTime!=null">${tbAbAffirm$tbaaAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbAbAffirm.tbaaAddTime" id="tbaaAddTime"  value="<s:date name="tbAbAffirm.tbaaAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择异借确认的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaAddTime">${tbAbAffirm.tbaaAddTime}</textarea>
		 		<span>
		 	
			 ${tbAbAffirm.tbaaAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaRemark和after$tbaaRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaRemark==true">
	${before$tbaaRemark}
	<dl class="tbaaRemark " minor  ref="tbaaRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.tbaaRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbAbAffirm$tbaaRemark!=null">${tbAbAffirm$tbaaRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="tbaaRemark" name="tbAbAffirm.tbaaRemark" rows="5">${tbAbAffirm.tbaaRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入异借确认的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbAbAffirm.tbaaRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbaaStatus和after$tbaaStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbaaStatus==true">
	${before$tbaaStatus}
	<dl class="tbaaStatus " major  ref="tbaaStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbaaStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbAbAffirm$tbaaStatus!=null">${tbAbAffirm$tbaaStatus}</s:if><s:else>
		 	<select class="narrow" id="tbaaStatus" name="tbAbAffirm.tbaaStatus">
		 		<option value="">请选择</option>
				<option value="0" ${tbAbAffirm.tbaaStatus==0?"selected":""}>待确认</option>
				<option value="1" ${tbAbAffirm.tbaaStatus==1?"selected":""}>未确认</option>
				<option value="2" ${tbAbAffirm.tbaaStatus==2?"selected":""}>已确认</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>未确认表示订单已完成未确认</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbaaStatus">${tbAbAffirm.tbaaStatus}</textarea>
		 		<span>
		 	
			 ${tbAbAffirm.tbaaStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbaaStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbAbAffirm.tbaaId}" name="tbAbAffirm.tbaaId" id="tbaaId" />
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