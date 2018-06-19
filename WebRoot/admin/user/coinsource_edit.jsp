<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/coin.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/coinsource.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCoinSource.cscsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscsId,cscsName,cscsFlag,cscsRemark,cscsStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscsId,cscsName,cscsFlag,cscsRemark,cscsStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscsId,cscsName,cscsFlag,cscsRemark,cscsStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscsId,cscsName,cscsFlag,cscsRemark,cscsStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"红包编辑":CTRL.title}</title>
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
<script>
$(function(){
	$("#cscMember").attr("needparam","false");
});
</script>
<s:if test="#request.CTRL.title=='批量充红包'">
	<lz:set name="jsonString">
		{
			title:"批量充红包",
			action:"coin_source_batch.do",
			editable:false,
			visible:false,
			fields:{
				cscsId:{visible:false}
				,cscCount:{editable:true,visible:true}
				,cscValidity:{editable:true,visible:true}
				,cscsRemark:{editable:true,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
	<lz:set name="fileHtml">
		<dl>
			<dt>2003格式excel:</dt>
			<dd>
			 	<input type="file" name="batch" id="batch" /></br>
		 	 	<b>*</b>
		 		<em>第一列为会员ID,第二列为手机号</em>
			</dd>
		</dl>
	</lz:set>
	<script>
	$(function(){
		$("${lz:js(fileHtml)}").insertBefore("dl[ref=cscCount]");
		$(".form").attr("enctype","multipart/form-data");
	});
	</script>
</s:if>
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
	$("#coinSourceForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscsId==true">
		${lz:set("haveEditable",true)}
		,"csCoinSource.cscsId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsName==true">
		${lz:set("haveEditable",true)}
		,"csCoinSource.cscsName":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "来源名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsFlag==true">
		${lz:set("haveEditable",true)}
		,"csCoinSource.cscsFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "程序标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCoinSource.cscsUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCoinSource.cscsAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsRemark==true">
		${lz:set("haveEditable",true)}
		,"csCoinSource.cscsRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsStatus==true">
		${lz:set("haveEditable",true)}
		,"csCoinSource.cscsStatus":function(el){
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
	<form class="form editform" ref="csCoinSource" id="coinSourceForm" name="coinSourceForm" action="${empty CTRL.action?"coinsource_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写红包相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCoinToken}" name="csCoinSourceToken" id="csCoinSourceToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCoinSource.cscsId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscId和after$cscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsId==true">
	${before$cscsId}
	<dl class="cscsId ${CTRL.e.cscsId?"hidden":""}" major  ref="cscsId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscsId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCoinSource$cscsId!=null">${csCoinSource$cscsId}</s:if><s:else>
			<input type="hidden" value="${csCoinSource.cscsId}" name="csCoinSource.cscsId" id="cscsId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsId">${csCoinSource.cscsId}</textarea>
		 		<span>
		 	
			 ${csCoinSource.cscsId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsId}
	</s:if>
	
	${lz:set("注释","*****************来源名称字段的输入框代码*****************")}
	${lz:set("注释","before$cscsName和after$cscsName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsName==true">
	${before$cscsName}
	<dl class="cscsName " minor  ref="cscsName" >
		<dt>来源名称:</dt>
		<s:if test="#request.CTRL.e.cscsName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCoinSource$cscsName!=null">${csCoinSource$cscsName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCoinSource.cscsName" id="cscsName"  value="${csCoinSource.cscsName}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入来源名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsName">${csCoinSource.cscsName}</textarea>
		 		<span>
		 	
			 ${csCoinSource.cscsName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsName}
	</s:if>
	
	
	${lz:set("注释","*****************来源名称字段的输入框代码*****************")}
	${lz:set("注释","before$cscsFlag和after$cscsFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsFlag==true">
	${before$cscsFlag}
	<dl class="cscsFlag " minor  ref="cscsFlag" >
		<dt>标识:</dt>
		<s:if test="#request.CTRL.e.cscsFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCoinSource$cscsFlag!=null">${csCoinSource$cscsFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCoinSource.cscsFlag" id="cscsFlag"  value="${csCoinSource.cscsFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsFlag">${csCoinSource.cscsFlag}</textarea>
		 		<span>
		 	
			 ${csCoinSource.cscsFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsFlag}
	</s:if>
	
	
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsUpdateTime和after$cscsUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsUpdateTime==true">
	${before$cscsUpdateTime}
	<dl class="cscsUpdateTime " major  ref="cscsUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscsUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCoinSource$cscsUpdateTime!=null">${csCoinSource$cscsUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCoinSource.cscsUpdateTime" id="cscsUpdateTime"  value="<s:date name="csCoinSource.cscsUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsUpdateTime">${csCoinSource.cscsUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCoinSource.cscsUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsAddTime和after$cscsAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsAddTime==true">
	${before$cscsAddTime}
	<dl class="cscsAddTime " major  ref="cscsAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscsAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCoinSource$cscsAddTime!=null">${csCoinSource$cscsAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCoinSource.cscsAddTime" id="cscsAddTime"  value="<s:date name="csCoinSource.cscsAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择红包的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsAddTime">${csCoinSource.cscsAddTime}</textarea>
		 		<span>
		 	
			 ${csCoinSource.cscsAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscsRemark和after$cscsRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsRemark==true">
	${before$cscsRemark}
	<dl class="cscsRemark " minor  ref="cscsRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.cscsRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCoinSource$cscsRemark!=null">${csCoinSource$cscsRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscsRemark" name="csCoinSource.cscsRemark" rows="5">${csCoinSource.cscsRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入红包的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCoinSource.cscsRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscsStatus和after$cscsStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsStatus==true">
	${before$cscsStatus}
	<dl class="cscsStatus " major  ref="cscsStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscsStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCoinSource$cscsStatus!=null">${csCoinSource$cscsStatus}</s:if><s:else>
		 	<select class="narrow" id="cscsStatus" name="csCoinSource.cscsStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csCoinSource.cscsStatus==1?"selected":""}>正常</option>
				<option value="0" ${csCoinSource.cscsStatus==2?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择红包的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsStatus">${csCoinSource.cscsStatus}</textarea>
		 		<span>
		 	
			 ${csCoinSource.cscsStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCoinSource.cscsId}" name="csCoinSource.cscsId" id="cscsId" />
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