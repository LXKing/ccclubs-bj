<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/control/alarm.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/control/alarm.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlarm.csaId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csaId,csaHost,csaNumber,csaAddTime,csaInfo,csaOrder",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaId,csaHost,csaNumber,csaAddTime,csaInfo,csaOrder",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csaId,csaHost,csaNumber,csaAddTime,csaInfo,csaOrder",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaId,csaHost,csaNumber,csaAddTime,csaInfo,csaOrder",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆警报编辑":CTRL.title}</title>
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
	$("#alarmForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csaId==true">
		${lz:set("haveEditable",true)}
		,"csAlarm.csaId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaHost==true">
		${lz:set("haveEditable",true)}
		,"csAlarm.csaHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaNumber==true">
		${lz:set("haveEditable",true)}
		,"csAlarm.csaNumber":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaAddTime==true">
		${lz:set("haveEditable",true)}
		,"csAlarm.csaAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "接收时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaInfo==true">
		${lz:set("haveEditable",true)}
		,"csAlarm.csaInfo":function(el){
			if(jQuery.trim(el.value)=="")
				return "报警信息不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "报警信息最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaOrder==true">
		${lz:set("haveEditable",true)}
		,"csAlarm.csaOrder":function(el){
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
	<form class="form editform" ref="CsAlarm" id="alarmForm" name="alarmForm" action="${empty CTRL.action?"alarm_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆警报相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csAlarmToken}" name="csAlarmToken" id="csAlarmToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlarm.csaId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csaId和after$csaId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaId==true">
	${before$csaId}
	<dl class="csaId ${CTRL.e.csaId?"hidden":""}" major  ref="csaId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csaId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csAlarm$csaId!=null">${csAlarm$csaId}</s:if><s:else>
			<input type="hidden" value="${csAlarm.csaId}" name="csAlarm.csaId" id="csaId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaId">${csAlarm.csaId}</textarea>
		 		<span>
		 	
			 ${csAlarm.csaId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csaHost和after$csaHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaHost==true">
	${before$csaHost}
	<dl class="csaHost " major  ref="csaHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csaHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csAlarm$csaHost!=null">${csAlarm$csaHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csaHost" name="csAlarm.csaHost">
		 				<option selected value="${csAlarm.csaHost}">
		 					${get:SrvHost(csAlarm.csaHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csaHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaHost">${csAlarm.csaHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csAlarm.csaHost}',{ctrl:{editable:false,visible:true}})">
			 ${csAlarm.csaHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaHost}
	</s:if>
	
	${lz:set("注释","*****************车牌号字段的输入框代码*****************")}
	${lz:set("注释","before$csaNumber和after$csaNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaNumber==true">
	${before$csaNumber}
	<dl class="csaNumber " major  ref="csaNumber" >
		<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csaNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlarm$csaNumber!=null">${csAlarm$csaNumber}</s:if><s:else>
	 				<input title="请输入查询" class="combox" action="${basePath}${proname}/object/car_numbers.do?value={param}" size="16" type="text" id="csaNumber" name="csAlarm.csaNumber" text="${csAlarm.csaNumber}" value="${csAlarm.csaNumber}" />	 		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaNumber">${csAlarm.csaNumber}</textarea>
		 		<span>
		 	
			 ${csAlarm.csaNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaNumber}
	</s:if>
	
	${lz:set("注释","*****************接收时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaAddTime和after$csaAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaAddTime==true">
	${before$csaAddTime}
	<dl class="csaAddTime " major  ref="csaAddTime" >
		<dt>接收时间:</dt>
		<s:if test="#request.CTRL.e.csaAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csAlarm$csaAddTime!=null">${csAlarm$csaAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csAlarm.csaAddTime" id="csaAddTime"  value="<s:date name="csAlarm.csaAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆警报的接收时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaAddTime">${csAlarm.csaAddTime}</textarea>
		 		<span>
		 	
			 ${csAlarm.csaAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaAddTime}
	</s:if>
	
	${lz:set("注释","*****************报警信息字段的输入框代码*****************")}
	${lz:set("注释","before$csaInfo和after$csaInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaInfo==true">
	${before$csaInfo}
	<dl class="csaInfo " major  ref="csaInfo" >
		<dt>报警信息:</dt>
		<s:if test="#request.CTRL.e.csaInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlarm$csaInfo!=null">${csAlarm$csaInfo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAlarm.csaInfo" id="csaInfo"  value="${csAlarm.csaInfo}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆警报的报警信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****报警信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaInfo">${csAlarm.csaInfo}</textarea>
		 		<span>
		 	
			 ${csAlarm.csaInfo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaInfo}
	</s:if>
	
	${lz:set("注释","*****************当前订单字段的输入框代码*****************")}
	${lz:set("注释","before$csaOrder和after$csaOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaOrder==true">
	${before$csaOrder}
	<dl class="csaOrder " major  ref="csaOrder" >
		<dt>当前订单:</dt>
		<s:if test="#request.CTRL.e.csaOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlarm$csaOrder!=null">${csAlarm$csaOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csaHost}" type="text" id="csaOrder" name="csAlarm.csaOrder" text="${get:CsOrder(csAlarm.csaOrder).csoId}" value="${csAlarm.csaOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csaOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****当前订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaOrder">${csAlarm.csaOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csAlarm.csaOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csAlarm.csaOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaOrder}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csAlarm.csaId}" name="csAlarm.csaId" id="csaId" />
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