<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/carres.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/carres.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarRes.cscrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscrId,cscrHost,cscrOutlets,cscrCar,cscrCarmodel,cscrCarNo,cscrColor,cscrLocation,cscrSource,cscrStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	visibles:"cscrId,cscrHost,cscrOutlets,cscrCar,cscrCarmodel,cscrCarNo,cscrColor,cscrLocation,cscrSource,cscrStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscrId,cscrHost,cscrOutlets,cscrCar,cscrCarmodel,cscrCarNo,cscrColor,cscrLocation,cscrSource,cscrStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	visibles:"cscrId,cscrHost,cscrOutlets,cscrCar,cscrCarmodel,cscrCarNo,cscrColor,cscrLocation,cscrSource,cscrStatus",
	</s:else>
	
	
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"专车信息编辑":CTRL.title}</title>
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
	$("#carresForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscrId==true">
		${lz:set("haveEditable",true)}
		,"csCarRes.cscrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrHost==true">
		${lz:set("haveEditable",true)}
		,"csCarRes.cscrHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrOutlets==true">
		${lz:set("haveEditable",true)}
		,"csCarRes.cscrOutlets":function(el){
			if(jQuery.trim(el.value)==0)
				return "请选择网点";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrCarmodel==true">
		${lz:set("haveEditable",true)}
		,"csCarRes.cscrCarmodel":function(el){
			if(jQuery.trim(el.value)=="")
				return "外部车型不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrCarNo==true">
		${lz:set("haveEditable",true)}
		,"csCarRes.cscrCarNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "车牌名称不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscrColor==true">
		${lz:set("haveEditable",true)}
		,"csCarRes.cscrColor":function(el){
			if(jQuery.trim(el.value)=="")
				return "车牌颜色不能为空";
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
	<form class="form editform" ref="csCarRes" id="carresForm" name="carresForm" action="${empty CTRL.action?"carres_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写专车人员相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCarResToken}" name="csCarResToken" id="csCarResToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarRes.cscrId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscrId和after$cscrId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrId==true">
	${before$cscrId}
	<dl class="cscrId ${CTRL.e.cscrId?"hidden":""}" major  ref="cscrId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscrId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCarRes$cscrId!=null">${csCarRes$cscrId}</s:if><s:else>
			<input type="hidden" value="${csCarRes.cscrId}" name="csCarRes.cscrId" id="cscrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrId">${csCarRes.cscrId}</textarea>
		 		<span>
		 	
			 ${csCarRes.cscrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscrHost和after$cscrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrHost==true">
	${before$cscrHost}
	<dl class="cscrHost " major  ref="cscrHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscrHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCarRes$cscrHost!=null">${csCarRes$cscrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscrHost" name="csCarRes.cscrHost">
		 				<option selected value="${csCarRes.cscrHost}">
		 					${get:SrvHost(csCarRes.cscrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrHost">${csCarRes.cscrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCarRes.cscrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCarRes.cscrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrHost}
	</s:if>
	
	${lz:set("注释","*****************指定网点字段的输入框代码*****************")}
	${lz:set("注释","before$cscrOutlets和after$cscrOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrOutlets==true">
	${before$cscrOutlets}
	<dl class="cscrOutlets " major  ref="cscrOutlets" style="width:100%;">
		<dt>指定网点:</dt>
		<s:if test="#request.CTRL.e.cscrOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="list">
		
		<s:if test="#request.csCarRes$cscrOutlets!=null">${csCarRes$cscrOutlets}</s:if><s:else>
			<select class="combox narrow"  id="cscrOutlets" name="csCarRes.cscrOutlets" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={cscrHost}">
				<option value="${csCarRes.cscrOutlets}">
						${get:CsOutlets(csCarRes.cscrOutlets).csoName}
				</option>
			</select>		 			
			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#cscrOutlets').val(),{ctrl:{editable:false}})"></a>
								  		
	 	 
		 </s:else>
		 <b>*</b>
	 	 <em>请选择网点</em>
		</dd>
		
		</s:if>
		<s:else>
		
		${lz:set("注释","****指定网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrOutlets">${csCarRes.cscrOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csCarRes.cscrOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csCarRes.cscrOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrOutlets}
	</s:if>
	
	${lz:set("注释","*****************外部车辆框代码*****************")}
	${lz:set("注释","before$cscrCar和after$cscrCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrCar==true">
	${before$cscrCar}
	<dl class="cscrCar " major  ref="cscrCar" style="width: 98%">
		<dt>外部车辆:</dt>
		<s:if test="#request.CTRL.e.cscrCar==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarRes$cscrCar!=null">${csCarRes$cscrCar}</s:if><s:else>
				<input type="text" class="input narrow"  maxlength="32" readonly="readonly" name="csCarRes.cscrCar" id="cscrCar"  value="${csCarRes.cscrCar}"/>
				<button class='button' type='button' onclick="javacript:href('${basePath}admin/unit/carres_select.do',{})">选择车辆</button>
				
	 	</s:else>
	 	
	 	 <b></b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****头像非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscrCar">${csCarRes.cscrCar}</textarea>
		 		<span>
			 ${csCarRes.cscrCar$}
	 	 		</span>
				
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrCar}
	</s:if>
	
	${lz:set("注释","*****************外部车型的输入框代码*****************")}
	${lz:set("注释","before$cscrCarmodel和after$cscrCarmodel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrCarmodel==true">
	${before$cscrCarmodel}
	<dl class="cscrCarmodel " major  ref="cscrCarmodel" >
		<dt>外部车型:</dt>
		<s:if test="#request.CTRL.e.cscrCarmodel==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarRes$cscrCarmodel!=null">${csCarRes$cscrCarmodel}</s:if><s:else>
		 	<select class="combox"  action="${basePath}${proname}/object/carmodel_query.do?parent={param}&host={csoHost}&csoHost={csuiHost}" name="csCarRes.cscrCarmodel" id="cscrCarmodel">
	 				<option selected value="${csCarRes.cscrCarmodel}">
	 					${get:CsCarModel(cscrCarmodel).cscmName}
	 				</option>
	 		</select>
			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscrCarmodel').val())==''){return;};window.href('${basePath}${proname}/unit/specialmodel_details.do?id='+$('#cscrCarmodel').val(),{ctrl:{editable:false,visible:true}})"></a>
		 	
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择外部车型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****外部车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrCarmodel">${csCarRes.cscrCarmodel}</textarea>
		 		<span>
		 	
			 ${csCarRes.cscrCarmodel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrCarmodel}
	</s:if>
	
	${lz:set("注释","*****************车牌名称的输入框代码*****************")}
	${lz:set("注释","before$cscrCarNo和after$cscrCarNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrCarNo==true">
	${before$cscrCarNo}
	<dl class="cscrCarNo " major  ref="cscrCarNo" >
		<dt>车牌名称:</dt>
		<s:if test="#request.CTRL.e.cscrCarNo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCarRes$cscrCarNo!=null">${csCarRes$cscrCarNo}</s:if><s:else>
		 	<input type="text" class="input" maxlength="128" size="32" name="csCarRes.cscrCarNo" id="cscrCarNo"  value="${csCarRes.cscrCarNo}"/>		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车牌名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌名称非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrCarNo"><s:if test="#csCarRes.cscrCarNo==1">男</s:if><s:else>女</s:else></textarea>
		 		<span>
		 	
				${ csCarRes.cscrCarNo$}
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrCarNo}
	</s:if>
	
	${lz:set("注释","*****************车辆颜色字段的输入框代码*****************")}
	${lz:set("注释","before$cscrColor和after$cscrColor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrColor==true">
	${before$cscrColor}
	<dl class="cscrColor " major  ref="cscrColor" >
		<dt>车辆颜色:</dt>
		<s:if test="#request.CTRL.e.cscrColor==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCarRes$cscrColor!=null">${csCarRes$cscrColor}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCarRes.cscrColor" id="cscrColor"  value="${csCarRes.cscrColor}"/>		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆颜色</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆颜色字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrColor">${csCarRes.cscrColor}</textarea>
		 		<span>
		 	
			 ${csCarRes.cscrColor$}
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrColor}
	</s:if>
	
	
	${lz:set("注释","*****************停放位置的输入框代码*****************")}
	${lz:set("注释","before$cscrLocation和after$cscrLocation变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrLocation==true">
	${before$cscrLocation}
	<dl class="cscrLocation " major  ref="cscrLocation" >
		<dt>停放位置:</dt>
		<s:if test="#request.CTRL.e.cscrLocation==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCarRes$cscrLocation!=null">${csCarRes$cscrLocation}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCarRes.cscrLocation" id="cscrLocation"  value="${csCarRes.cscrLocation}"/>		
	 	 </s:else>
	 	 
	 	 <b></b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****停放位置非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrLocation">${csCarRes.cscrLocation}</textarea>
		 		<span>
		 	
			 ${csCarRes.cscrLocation$}
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrLocation}
	</s:if>	
	
	${lz:set("注释","*****************数据来源的输入框代码*****************")}
	${lz:set("注释","before$cscrSource和after$cscrSource变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrSource==true">
	${before$cscrSource}
	<dl class="cscrSource " major  ref="cscrSource" >
		<dt>数据来源:</dt>
		<s:if test="#request.CTRL.e.cscrSource==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCarRes$cscrSource!=null">${csCarRes$cscrSource}</s:if><s:else>
		 	<select id="cscrSource" name="csCarRes.cscrSource" >
		 			<option value="">请选择</option>
					<option value="2" ${csCarRes.cscrSource==2?"selected":""}>外部采集</option>
					<option value="1" ${csCarRes.cscrSource==1?"selected":""}>公务出行</option>
	 		</select>		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	<em>数据来源</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据来源非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrSource">${csCarRes.cscrSource}</textarea>
		 		<span>
		 		<s:if test="csCarRes.cscrSource==1">公务出行</s:if><s:else>外部采集</s:else>
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrSource}
	</s:if>
	
	${lz:set("注释","*****************数据状态的输入框代码*****************")}
	${lz:set("注释","before$cscrStatus和after$cscrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrStatus==true">
	${before$cscrStatus}
	<dl class="cscrStatus " major  ref="cscrStatus" >
		<dt>数据状态:</dt>
		<s:if test="#request.CTRL.e.cscrStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.cscrStatus$cscrStatus!=null">${csCarRes$cscrStatus}</s:if><s:else>
	 	 	<select id="cscrStatus" name="csCarRes.cscrStatus" >
	 	 			<option value="">请选择</option>
					<option value="1" ${csCarRes.cscrStatus==1?"selected":""}>正常</option>
					<option value="0" ${csCarRes.cscrStatus==0?"selected":""}>禁用</option>
	 		</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>数据状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实名认证非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrStatus"><s:if test="#csCarRes.cscrStatus==1">已认证</s:if><s:else>未认证</s:else></textarea>
		 		<span>
		 	
			<s:if test="csCarRes.cscrStatus==1">正常</s:if><s:else>禁用</s:else>
	 	  	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrStatus}
	</s:if>
	
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscrUpdateTime和after$cscrUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrUpdateTime==true">
	${before$cscrUpdateTime}
	<dl class="cscrUpdateTime " major  ref="cscrUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscrUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarRes$cscrUpdateTime!=null">${csCarRes$cscrUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarRes.cscrUpdateTime" id="cscrUpdateTime"  value="<s:date name="csCarRes.cscrUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>专车信息修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrUpdateTime">${csCarRes.cscrUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCarRes.cscrUpdateTime$}
	 	  
	 	 	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrUpdateTime}
	</s:if>
	
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscrAddTime和after$cscrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscrAddTime==true">
	${before$cscrAddTime}
	<dl class="cscrAddTime " major  ref="cscrAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscrAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarRes$cscrAddTime!=null">${csCarRes$cscrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarRes.cscrAddTime" id="cscrAddTime"  value="<s:date name="csCarRes.cscrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>司机人员的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscrAddTime">${csCarRes.cscrAddTime}</textarea>
		 		<span>
		 	
			 ${csCarRes.cscrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscrAddTime}
	</s:if>
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCarRes.cscrId}" name="csCarRes.cscrId" id="cscrId" />
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
	function test(){
		$.getInput({
			type:'combox',
			title:'选择车辆',
			action:'${basePath}${proname}/unit/carres_queryCar.do?value={param}&object=true',
			callback:function(id,text){
				$('#cscrCar').html("<div>"+id+"<input type='hidden' name='csCarRes.cscrCar' value='"+parseInt(id)+"'/><span onclick='$(this).parent().remove();'>x</span></div>");
				$.ajax({
					dataType:"json",
					url:"${basePath}${proname}/unit/carres_queryCar.do?value=id:"+id+"&object=true",
					success:function(data){
						$("#cscrCarNo").val(data[0].object.cscNumber);
					
						switch (data[0].object.cscColor) {
						//0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红
							case 0:
									$("#cscrColor").val("黑色");
								break;
							case 1:
									$("#cscrColor").val("白色");
								break;
							case 2:
									$("#cscrColor").val("红色");
								break;
							case 3:
									$("#cscrColor").val("绿色");
								break;
							case 4:
									$("#cscrColor").val("黄色");
								break;
							case 5:
									$("#cscrColor").val("橙色");
								break;
							case 6:
									$("#cscrColor").val("银色");
								break;
							case 7:
									$("#cscrColor").val("蓝色");
								break;
							case 8:
									$("#cscrColor").val("深蓝");
								break;
							case 9:
									$("#cscrColor").val("深灰");
								break;
							case 10:
									$("#cscrColor").val("深红");
								break;
						}
						$("#cscrCarmodel").val(data[0].object.cscModel);
						$("#cscrLocation").val(data[0].cscrLocation);
						$("#cscrCarmodel").parent().find("span").text(data[0].cscmName);
						$("#cscrSource").val(1);
					}
				});
			}
		});
	
		
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>