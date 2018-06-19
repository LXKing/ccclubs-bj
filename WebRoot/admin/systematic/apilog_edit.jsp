<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/apilog.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/apilog.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvApiLog.salId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"salId,salClient,salApi,salParams,salReturn,salStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"salId,salClient,salApi,salParams,salReturn,salStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"salId,salClient,salApi,salParams,salReturn,salStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"salId,salClient,salApi,salParams,salReturn,salStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"API日志编辑":CTRL.title}</title>
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
	$("#apilogForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.salId==true">
		${lz:set("haveEditable",true)}
		,"srvApiLog.salId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.salClient==true">
		${lz:set("haveEditable",true)}
		,"srvApiLog.salClient":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.salApi==true">
		${lz:set("haveEditable",true)}
		,"srvApiLog.salApi":function(el){
			if($("#salApi option").length==0)
				return "调用API至少添加一项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.salParams==true">
		${lz:set("haveEditable",true)}
		,"srvApiLog.salParams":function(el){
			if(jQuery.trim(el.value)=="")
				return "调用参数不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "调用参数最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.salReturn==true">
		${lz:set("haveEditable",true)}
		,"srvApiLog.salReturn":function(el){
			if(jQuery.trim(el.value)=="")
				return "返回数据不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "返回数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.salInvokeTime==true">
		${lz:set("haveEditable",true)}
		,"srvApiLog.salInvokeTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.salStatus==true">
		${lz:set("haveEditable",true)}
		,"srvApiLog.salStatus":function(el){
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
	<form class="form editform" ref="SrvApiLog" id="apilogForm" name="apilogForm" action="${empty CTRL.action?"apilog_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写API日志相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvApiLogToken}" name="srvApiLogToken" id="srvApiLogToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvApiLog.salId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$salId和after$salId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.salId==true">
	${before$salId}
	<dl class="salId ${CTRL.e.salId?"hidden":""}" major  ref="salId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.salId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvApiLog$salId!=null">${srvApiLog$salId}</s:if><s:else>
			<input type="hidden" value="${srvApiLog.salId}" name="srvApiLog.salId" id="salId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="salId">${srvApiLog.salId}</textarea>
		 		<span>
		 	
			 ${srvApiLog.salId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$salId}
	</s:if>
	
	${lz:set("注释","*****************调用应用字段的输入框代码*****************")}
	${lz:set("注释","before$salClient和after$salClient变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.salClient==true">
	${before$salClient}
	<dl class="salClient " major  ref="salClient" >
		<dt>调用应用:</dt>
		<s:if test="#request.CTRL.e.salClient==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.srvApiLog$salClient!=null">${srvApiLog$salClient}</s:if><s:else>
		 			<input title="请输入API客户端客户端ID进行查询" class="combox narrow" action="${basePath}${proname}/configurator/apiclient_query.do?value={param}" type="text" id="salClient" name="srvApiLog.salClient" text="${get:SrvApiClient(srvApiLog.salClient).sacClient}" value="${srvApiLog.salClient}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#salClient').val())==''){return;};window.href('${basePath}${proname}/configurator/apiclient_details.do?key='+$('#salClient').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调用应用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="salClient">${srvApiLog.salClient}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/apiclient_details.do?key=${srvApiLog.salClient}',{ctrl:{editable:false,visible:true}})">
			 ${srvApiLog.salClient$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$salClient}
	</s:if>
	
	${lz:set("注释","*****************调用API字段的输入框代码*****************")}
	${lz:set("注释","before$salApi和after$salApi变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.salApi==true">
	${before$salApi}
	<dl class="salApi " major  ref="salApi" style="width:98%;">
		<dt>调用API:</dt>
		<s:if test="#request.CTRL.e.salApi==true">
		${lz:set("haveEditable",true)}
		<dd input="list">
		<s:if test="#request.srvApiLog$salApi!=null">${srvApiLog$salApi}</s:if><s:else>
		 	
			    <div name="srvApiLog.salApi" id="salApi" class="wide input listinput">
					<input type="hidden" name="srvApiLog.salApi" value="0"/>
				<s:generator val="srvApiLog.salApi" separator=",">
					<s:iterator  id="item">
					<s:if test="item!=0">
						<div>
							${get:SrvApi(item).saName}							
						<input type="hidden" name="srvApiLog.salApi" value="${item}"/><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
					</s:iterator>
				</s:generator>
				</div>
				<button type="button" style="float:left;" onclick="$.getInput({type:'combox',title:'API接口API名称',action:$(this).attr('action'),callback:function(id,text){$('#salApi').append('<div>'+text+'<input type=&quot;hidden&quot; name=&quot;srvApiLog.salApi&quot; value=&quot;'+id+'&quot;/><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}});" action="select * from srv_api where 1=1?value={param}" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调用API字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="salApi">${srvApiLog.salApi}</textarea>
		 		<span>
		 	
			 ${srvApiLog.salApi$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$salApi}
	</s:if>
	
	${lz:set("注释","*****************调用参数字段的输入框代码*****************")}
	${lz:set("注释","before$salParams和after$salParams变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.salParams==true">
	${before$salParams}
	<dl class="salParams " major  ref="salParams" style="width:98%;">
		<dt>调用参数:</dt>
		<s:if test="#request.CTRL.e.salParams==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvApiLog$salParams!=null">${srvApiLog$salParams}</s:if><s:else>
		 	<textarea class="input wide"  id="salParams" name="srvApiLog.salParams" rows="5">${srvApiLog.salParams}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入API日志的调用参数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调用参数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvApiLog.salParams$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$salParams}
	</s:if>
	
	${lz:set("注释","*****************返回数据字段的输入框代码*****************")}
	${lz:set("注释","before$salReturn和after$salReturn变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.salReturn==true">
	${before$salReturn}
	<dl class="salReturn " major  ref="salReturn" style="width:98%;">
		<dt>返回数据:</dt>
		<s:if test="#request.CTRL.e.salReturn==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvApiLog$salReturn!=null">${srvApiLog$salReturn}</s:if><s:else>
		 	<textarea class="input wide"  id="salReturn" name="srvApiLog.salReturn" rows="5">${srvApiLog.salReturn}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入API日志的返回数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****返回数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvApiLog.salReturn$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$salReturn}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$salInvokeTime和after$salInvokeTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.salInvokeTime==true">
	${before$salInvokeTime}
	<dl class="salInvokeTime " major  ref="salInvokeTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.salInvokeTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvApiLog$salInvokeTime!=null">${srvApiLog$salInvokeTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvApiLog.salInvokeTime" id="salInvokeTime"  value="<s:date name="srvApiLog.salInvokeTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API日志的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="salInvokeTime">${srvApiLog.salInvokeTime}</textarea>
		 		<span>
		 	
			 ${srvApiLog.salInvokeTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$salInvokeTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$salStatus和after$salStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.salStatus==true">
	${before$salStatus}
	<dl class="salStatus " major  ref="salStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.salStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvApiLog$salStatus!=null">${srvApiLog$salStatus}</s:if><s:else>
		 	<select class="narrow" id="salStatus" name="srvApiLog.salStatus">
		 		<option value="">请选择</option>
				<option value="1" ${srvApiLog.salStatus==1?"selected":""}>默认</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API日志的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="salStatus">${srvApiLog.salStatus}</textarea>
		 		<span>
		 	
			 ${srvApiLog.salStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$salStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvApiLog.salId}" name="srvApiLog.salId" id="salId" />
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