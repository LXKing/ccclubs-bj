<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/specialcar.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canEdit",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/specialcar.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSpecialCar.csscId))}
<lz:DefaultCtrl>{
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csscDriverPhone,csscFinishTime",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csscDriverPhone,csscFinishTime",
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"专车订单指派":CTRL.title}</title>
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



<!--LAZY3Q_JSP_BODY-->
<script>
$(function(){
	var dl=$("<dl><dt></dt><dd></dd></dl>").insertAfter("dl[ref=csscDriverPhone]");
	var button = $("<button class='button' type='button'>选择司机</button>").appendTo(dl.find("dd"));
	button.click(function(){
		if($("#csscFinishTime").val().trim()=="")
			showTips("请选择预计订单结束时间");
		else
			href("${basePath}admin/unit/drivercar_select.do?csdc_driver_query=true&csscId=${csSpecialCar.csscId}&csscFinishTime="+$("#csscFinishTime").val()+"&appoint=${appoint}",{});
		
	});
});
</script>
<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#smsmtForm").form({
		"csSmsMt.cssmStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
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
	<form class="form editform" ref="CsSmsMt" id="smsmtForm" name="smsmtForm" action="${empty CTRL.action?"specialcar_doAppoint.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写专车订单指派相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" name="csscId" id="csscId" value="${csSpecialCar.csscId}"/>
				<input type="hidden" name="cscdId" id="csscDriver" />
				<input type="hidden" name="appoint" id="appoint" value="${appoint }"/>
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSpecialCar.csscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
				${lz:set("注释","*****************派单时间字段的输入框代码*****************")}
				${lz:set("注释","before$csscFinishTime和after$csscFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
				<s:if test="#request.CTRL.v.csscFinishTime==true">
				${before$csscDriverPhone}
				<dl class="csscFinishTime " major  ref="csscFinishTime" style="width:98%;">
					<dt>订单预估结束时间:</dt>
					<s:if test="#request.CTRL.e.csscFinishTime==true">
					${lz:set("haveEditable",true)}
					<dd input="text">
					<s:if test="#request.csSmsMt$csscFinishTime!=null">${csSmsMt$csscFinishTime}</s:if><s:else>
				 	 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csscFinishTime" id="csscFinishTime"  value="<s:date name="csscFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
				 	 	
				 	 </s:else>
				 	 
				 	 <b>*</b>
				 	 <em>请输入订单预估结束时间</em>
					</dd>
					</s:if>
					<s:else>
					${lz:set("注释","****司机电话字段非编辑模式或只读时的显示****")}
					<dd>
					 	<div class="state-input narrow">
					 		<textarea class="" style="display:none;" id="csscFinishTime">${csSmsMt.csscFinishTime}</textarea>
					 		<span>
					 	
						 ${csSmsMt.csscFinishTime$}
				 	  
				 	 		&nbsp;	
				 	 		</span>
				 	 	</div>
					</dd>
					</s:else>
				</dl>
				${after$csscFinishTime}
				</s:if>
	
				${lz:set("注释","*****************司机电话字段的输入框代码*****************")}
				${lz:set("注释","before$cssmMobile和after$cssmMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
				<s:if test="#request.CTRL.v.csscDriverPhone==true">
				${before$csscDriverPhone}
				<dl class="csscDriverPhone " major  ref="csscDriverPhone" >
					<dt>司机电话:</dt>
					<s:if test="#request.CTRL.e.csscDriverPhone==true">
					${lz:set("haveEditable",true)}
					<dd input="text">
					<s:if test="#request.csSmsMt$csscDriverPhone!=null">${csSmsMt$csscDriverPhone}</s:if><s:else>
					 	<input type="text" class="input narrow"  maxlength="32" readonly="readonly" id="csscDriverPhone"  value="${csSmsMt.csscDriverPhone}"/>
				 	 </s:else>
				 	 
				 	 <b>*</b>
				 	 <em>请输入专车订单指派的司机电话</em>
					</dd>
					</s:if>
					<s:else>
					${lz:set("注释","****司机电话字段非编辑模式或只读时的显示****")}
					<dd>
					 	<div class="state-input narrow">
					 		<textarea class="" style="display:none;" id="cssmMobile">${csSmsMt.csscDriverPhone}</textarea>
					 		<span>
					 	
						 ${csSmsMt.csscDriverPhone$}
				 	  
				 	 		&nbsp;	
				 	 		</span>
				 	 	</div>
					</dd>
					</s:else>
				</dl>
				${after$csscDriverPhone}
				</s:if>
	
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSpecialCar.csscId}" name="csSpecialCar.csscId" id="cssmId" />
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