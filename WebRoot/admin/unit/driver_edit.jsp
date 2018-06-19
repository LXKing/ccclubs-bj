<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/driver.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/driver.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csDriver.csdId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csdId,csdHost,csdOutlets,csdName,csdSex,csdMobile,csdPhoto,csdCertifyNum,csdDriverNum,csdVReal,csdStatus,csdPassword",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csdId,csdHost,csdOutlets,csdName,csdSex,csdMobile,csdPhoto,csdCertifyNum,csdDriverNum,csdVReal,csdStatus,csdPassword",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csdId,csdHost,csdOutlets,csdName,csdSex,csdMobile,csdPassword,csdPhoto,csdCertifyNum,csdDriverNum,csdVReal,csdStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csdId,csdHost,csdOutlets,csdName,csdSex,csdMobile,csdPassword,csdPhoto,csdCertifyNum,csdDriverNum,csdVReal,csdStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"司机人员编辑":CTRL.title}</title>
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
	$("#driverForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csdId==true">
		${lz:set("haveEditable",true)}
		,"csDriver.csdId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csdHost==true">
		${lz:set("haveEditable",true)}
		,"csDriver.csdHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csdOutlets==true">
		${lz:set("haveEditable",true)}
		,"csDriver.csdOutlets":function(el){
				
				if(jQuery("input[name='csDriver.csdOutlets']").length<2)
					return "请选择网点";
				else{
				    if(jQuery("input[name='csDriver.csdOutlets']")[0].value=="0")
						jQuery("input[name='csDriver.csdOutlets']")[0].remove();
				}
				
			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csdName==true">
		${lz:set("haveEditable",true)}
		,"csDriver.csdName":function(el){
			if(jQuery.trim(el.value)=="")
				return "姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csdSex==true">
		${lz:set("haveEditable",true)}
		,"csDriver.csdSex":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csdMobile==true">
		${lz:set("haveEditable",true)}
		,"csDriver.csdMobile":function(el){
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
	<form class="form editform" ref="csDriver" id="driverForm" name="driverForm" action="${empty CTRL.action?"driver_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写司机人员相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csDriverToken}" name="csDriverToken" id="csDriverToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csDriver.csdId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csdId和after$csdId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdId==true">
	${before$csdId}
	<dl class="csdId ${CTRL.e.csdId?"hidden":""}" major  ref="csdId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csdId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csDriver$csdId!=null">${csDriver$csdId}</s:if><s:else>
			<input type="hidden" value="${csDriver.csdId}" name="csDriver.csdId" id="csdId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdId">${csDriver.csdId}</textarea>
		 		<span>
		 	
			 ${csDriver.csdId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csdHost和after$csdHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdHost==true">
	${before$csdHost}
	<dl class="csdHost " major  ref="csdHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csdHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csDriver$csdHost!=null">${csDriver$csdHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csdHost" name="csDriver.csdHost">
		 				<option selected value="${csDriver.csdHost}">
		 					${get:SrvHost(csDriver.csdHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csdHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csdHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdHost">${csDriver.csdHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csDriver.csdHost}',{ctrl:{editable:false,visible:true}})">
			 ${csDriver.csdHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdHost}
	</s:if>
	
	${lz:set("注释","*****************指定网点字段的输入框代码*****************")}
	${lz:set("注释","before$csdOutlets和after$csdOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdOutlets==true">
	${before$csdOutlets}
	<dl class="csdOutlets " major  ref="csdOutlets" style="width:100%;">
		<dt>指定网点:</dt>
		<s:if test="#request.CTRL.e.csdOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="list">
		
		<s:if test="#request.csDriver$csdOutlets!=null">${csDriver$csdOutlets}</s:if><s:else>
			<div name="csDriver.csdOutlets" id="csdOutlets" class="wide input listinput">
				<input type="hidden" name="csDriver.csdOutlets" value="0"/>
				<s:generator val="csDriver.csdOutlets" separator=",">
					<s:iterator  id="item">
					<s:if test="item!=0">
						<div>
							${get:CsOutlets(item).csoName}							
						<input type="hidden" name="csDriver.csdOutlets" value="${item}"/><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
					</s:iterator>
				</s:generator>
			</div>
			<button type="button" style="float:left;" onclick="$.getInput({type:'combox',title:'选择网点',action:$(this).attr('action'),callback:function(id,text){$('#csdOutlets').append('<div>'+text+'<input type=&quot;hidden&quot; name=&quot;csDriver.csdOutlets&quot; value=&quot;'+parseInt(id)+'&quot;/><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}});" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csoHost}&csoHost={csdHost}" class="button">添加</button>
		 </s:else>
		 <b>*</b>
	 	 <em>请选择网点</em>
		</dd>
		
		</s:if>
		<s:else>
		
		${lz:set("注释","****指定网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdOutlets">${csDriver.csdOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csDriver.csdOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csDriver.csdOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdOutlets}
	</s:if>
	
	${lz:set("注释","*****************头像的输入框代码*****************")}
	${lz:set("注释","before$csdPhoto和after$csdPhoto变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdPhoto==true">
	${before$csdPhoto}
	<dl class="csdPhoto " major  ref="csdPhoto" style="width: 98%">
		<dt>司机照片:</dt>
		<s:if test="#request.CTRL.e.csdPhoto==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csDriver$csdPhoto!=null">${csDriver$csdPhoto}</s:if><s:else>
	 	 	<input type="text" class="input" maxlength="128" size="32" name="csDriver.csdPhoto" id="csdPhoto"  value="${csDriver.csdPhoto}"/>
				<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csdPhoto').val(url)}})" class="button">设置图片</button>
				<button type="button" onclick="$('#csdPhoto').val('')" class="button">删除图片</button>
				<button type="button" onclick="$.thumb({url:$('#csdPhoto').val()})" class="button">查看图片</button>
	 	 
	 	 </s:else>
	 	 
	 	 
	 	 
	 	 <b></b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****头像非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csdPhoto">${csDriver.csdPhoto}</textarea>
		 		<span>
			 ${csDriver.csdPhoto$}
	 	 		</span>
				
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdPhoto}
	</s:if>
	
	${lz:set("注释","*****************姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csdName和after$csdName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdName==true">
	${before$csdName}
	<dl class="csdName " major  ref="csdName" >
		<dt>姓　　名:</dt>
		<s:if test="#request.CTRL.e.csdName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csDriver$csdName!=null">${csDriver$csdName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csDriver.csdName" id="csdName"  value="${csDriver.csdName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入司机人员的姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdName">${csDriver.csdName}</textarea>
		 		<span>
		 	
			 ${csDriver.csdName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdName}
	</s:if>
	
	${lz:set("注释","*****************性别的输入框代码*****************")}
	${lz:set("注释","before$csdSex和after$csdSex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdSex==true">
	${before$csdSex}
	<dl class="csdSex " major  ref="csdSex" >
		<dt>性　　别:</dt>
		<s:if test="#request.CTRL.e.csdSex==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csDriver$csdSex!=null">${csDriver$csdSex}</s:if><s:else>
		 	<select id="csdSex" name="csDriver.csdSex" >
		 			<option value="">请选择</option>
					<option value="1"  ${csDriver.csdSex==1?"selected":""}>男</option>
					<option value="0"  ${csDriver.csdSex==0?"selected":""}>女</option>
	 		</select>			
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择性别</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****性别非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdSex"><s:if test="#csDriver.csdSex==1">男</s:if><s:else>女</s:else></textarea>
		 		<span>
		 	
			<s:if test="csDriver.csdSex==1">男</s:if><s:else>女</s:else>
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdSex}
	</s:if>
	
	${lz:set("注释","*****************手机字段的输入框代码*****************")}
	${lz:set("注释","before$csdMobile和after$csdMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdMobile==true">
	${before$csdMobile}
	<dl class="csdMobile " major  ref="csdMobile" >
		<dt>手机号码:</dt>
		<s:if test="#request.CTRL.e.csdMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csDriver$csdMobile!=null">${csDriver$csdMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csDriver.csdMobile" id="csdMobile"  value="${csDriver.csdMobile}"/>		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入司机人员手机号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****手机号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdMobile">${csDriver.csdMobile}</textarea>
		 		<span>
		 	
			 ${csDriver.csdMobile$}
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdMobile}
	</s:if>
	
	
	${lz:set("注释","*****************身份证的输入框代码*****************")}
	${lz:set("注释","before$csdCertifyNum和after$csdCertifyNum变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdCertifyNum==true">
	${before$csdCertifyNum}
	<dl class="csdCertifyNum " major  ref="csdCertifyNum" >
		<dt>身份证号码:</dt>
		<s:if test="#request.CTRL.e.csdCertifyNum==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csDriver$csdCertifyNum!=null">${csDriver$csdCertifyNum}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csDriver.csdCertifyNum" id="csdCertifyNum"  value="${csDriver.csdCertifyNum}"/>		
	 	 </s:else>
	 	 
	 	 <b></b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****身份证号码非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdCertifyNum">${csDriver.csdCertifyNum}</textarea>
		 		<span>
		 	
			 ${csDriver.csdCertifyNum$}
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdCertifyNum}
	</s:if>	
	
	${lz:set("注释","*****************驾驶证的输入框代码*****************")}
	${lz:set("注释","before$csdDriverNum和after$csdDriverNum变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdDriverNum==true">
	${before$csdDriverNum}
	<dl class="csdDriverNum " major  ref="csdDriverNum" >
		<dt>驾驶证号码:</dt>
		<s:if test="#request.CTRL.e.csdDriverNum==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csDriver$csdDriverNum!=null">${csDriver$csdDriverNum}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csDriver.csdDriverNum" id="csdDriverNum"  value="${csDriver.csdDriverNum}"/>		
	 	 </s:else>
	 	 
	 	 <b></b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****驾驶证号码非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdDriverNum">${csDriver.csdDriverNum}</textarea>
		 		<span>
		 	
			 ${csDriver.csdDriverNum$}
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdDriverNum}
	</s:if>
	
	${lz:set("注释","*****************实名认证的输入框代码*****************")}
	${lz:set("注释","before$csdVReal和after$csdVReal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdVReal==true">
	${before$csdVReal}
	<dl class="csdVReal " major  ref="csdVReal" >
		<dt>认证状态:</dt>
		<s:if test="#request.CTRL.e.csdVReal==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csdVReal$csdVReal!=null">${csDriver$csdVReal}</s:if><s:else>
	 	 	<select id="csdVReal" name="csDriver.csdVReal" >
	 	 			<option value="">请选择</option>
					<option value="1" ${csDriver.csdVReal==1?"selected":""}>已认证</option>
					<option value="0" ${csDriver.csdVReal==0?"selected":""}>未认证</option>
	 		</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>实名认证状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实名认证非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdVReal"><s:if test="#csDriver.csdVReal==1">已认证</s:if><s:else>未认证</s:else></textarea>
		 		<span>
		 	
			<s:if test="csDriver.csdVReal==1">已认证</s:if><s:else>未认证</s:else>
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdVReal}
	</s:if>
	
	${lz:set("注释","****************数据状态的输入框代码*****************")}
	${lz:set("注释","before$csdStatus和after$csdStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdStatus==true">
	${before$csdStatus}
	<dl class="csdStatus " major  ref="csdStatus" >
		<dt>数据状态:</dt>
		<s:if test="#request.CTRL.e.csdStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csdStatus$csdStatus!=null">${csDriver$csdStatus}</s:if><s:else>
	 	 	<select id="csdStatus" name="csDriver.csdStatus" >
	 	 			<option value="">请选择</option>
					<option value="1" ${csDriver.csdStatus==1?"selected":""}>正常</option>
					<option value="0" ${csDriver.csdStatus==0?"selected":""}>禁用</option>
	 		</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>数据状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据状态非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdStatus"><s:if test="#csDriver.csdStatus==1">正常</s:if><s:else>禁用</s:else></textarea>
		 		<span>
		 	
			<s:if test="csDriver.csdStatus==1">正常</s:if><s:else>禁用</s:else>
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdStatus}
	</s:if>
	
	${lz:set("注释","*****************密码字段的输入框代码*****************")}
	${lz:set("注释","before$csdPassword和after$csdPassword变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdPassword==true">
	${before$csdPassword}
	<dl class="csdPassword " major  ref="csdPassword" >
		<dt>密　　码:</dt>
		<s:if test="#request.CTRL.e.csdPassword==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csDriver$csdPassword!=null">${csDriver$csdPassword}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csDriver.csdPassword" id="csdPassword"  value=""/>
	 	 </s:else>
	 	 
	 	 <b></b>
	 	 <em></em>
		</dd> 
		</s:if>
		<s:else>
		${lz:set("注释","****密码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdPassword">${csDriver.csdPassword}</textarea>
		 		
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdPassword}
	</s:if>
	
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csdUpdateTime和after$csdUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdUpdateTime==true">
	${before$csdUpdateTime}
	<dl class="csdUpdateTime " major  ref="csdUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csdUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csDriver$csdUpdateTime!=null">${csDriver$csdUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csDriver.csdUpdateTime" id="csdUpdateTime"  value="<s:date name="csDriver.csdUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>司机人员的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdUpdateTime">${csDriver.csdUpdateTime}</textarea>
		 		<span>
		 	
			 ${csDriver.csdUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdUpdateTime}
	</s:if>
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csdAddTime和after$csdAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csdAddTime==true">
	${before$csdAddTime}
	<dl class="csdAddTime " major  ref="csdAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csdAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csDriver$csdAddTime!=null">${csDriver$csdAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csDriver.csdAddTime" id="csdAddTime"  value="<s:date name="csDriver.csdAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>司机人员的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csdAddTime">${csDriver.csdAddTime}</textarea>
		 		<span>
		 	
			 ${csDriver.csdAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csdAddTime}
	</s:if>
	
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csDriver.csdId}" name="csDriver.csdId" id="csdId" />
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