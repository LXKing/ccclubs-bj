<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"企业机场编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

</head>  
<body ${(controller.mutual=="page"||(controller.mutual!="dialog" && false))?"class='pagetype'":""}>

<lz:set name="jsonString">
	{	
		action:"airport.save.do",	
		fields:{
			csuaUnitInfo:{editable:false,visible:false,defaultValue:${unit_login.csuiId}}
			,csuaStatus:{editable:false,visible:false,defaultValue:0}
		}
	}
</lz:set>
${lz:set("controller",lz:eval(jsonString))}

<script>

$(function(){	
	showTips("${tips.value}");
});


$(function(){
		 
	${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitAirport.csuaId))}
	${lz:set("haveEditable",false)}
	$("#airportForm").form({
		"":function(){}
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaId')==null)?canEditable:(lz:editable(controller,'csuaId')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaId":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaUnitInfo')==null)?canEditable:(lz:editable(controller,'csuaUnitInfo')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaUnitInfo":function(el){
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaType')==null)?canEditable:(lz:editable(controller,'csuaType')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaFlight')==null)?canEditable:(lz:editable(controller,'csuaFlight')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaFlight":function(el){
			if(jQuery.trim(el.value)=="")
				return "航班号不能为空";
			if(el.value.length>32)
				return "航班号最大长度为32个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaTime')==null)?canEditable:(lz:editable(controller,'csuaTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "时间不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaContact')==null)?canEditable:(lz:editable(controller,'csuaContact')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaContact":function(el){
			if(jQuery.trim(el.value)=="")
				return "联系人不能为空";
			if(el.value.length>32)
				return "联系人最大长度为32个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaMobile')==null)?canEditable:(lz:editable(controller,'csuaMobile')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaMobile":function(el){
			if(jQuery.trim(el.value)=="")
				return "联系电话不能为空";
			if(el.value.length>32)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaCount')==null)?canEditable:(lz:editable(controller,'csuaCount')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaCount":function(el){
			if(jQuery.trim(el.value)=="")
				return "人数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaUpdateTime')==null)?canEditable:(lz:editable(controller,'csuaUpdateTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaAddTime')==null)?canEditable:(lz:editable(controller,'csuaAddTime')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaRemark')==null)?canEditable:(lz:editable(controller,'csuaRemark')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaRemark":function(el){
			if(el.value.length>256)
				return "备注最大长度为256个字符";
		}
		</s:if>	
			 		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaStatus')==null)?canEditable:(lz:editable(controller,'csuaStatus')))}
		<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			,"csUnitAirport.csuaStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	});		
});
</script>

<form class="form editform" id="airportForm" name="airportForm" action="${empty controller.action?"airport.save.do":controller.action}" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<s:if test="#request.haveEditable==true">
		<div class="prompt">
			温馨提示：请仔细填写企业机场相关信息，<span class="extrude">"*" 为必填选项。</span>
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
		<input type="hidden" value="${ids}" name="ids" id="ids" />
		<input type="hidden" value="${lz:html(lz:json(controller))}" id="controller" name="controller" />
		
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitAirport.csuaId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaId')==null)?canEditable:(lz:editable(controller,'csuaId')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaId')==null)?canVisible:(lz:visible(controller,'csuaId')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && true)?"class='hidden'":""}  ref="csuaId" >
			<dt>编号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd>
				<input type="hidden" value="${csUnitAirport.csuaId}" name="csUnitAirport.csuaId" id="csuaId" />
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaId}" id="csuaId" />
			 		<span>
				${lz:zerofill(csUnitAirport.csuaId,6)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaUnitInfo')==null)?canEditable:(lz:editable(controller,'csuaUnitInfo')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaUnitInfo')==null)?canVisible:(lz:visible(controller,'csuaUnitInfo')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaUnitInfo" >
			<dt>所属[${lz:editable(controller,'csuaUnitInfo')}]企业:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}admin/unit/info.query.do?value={param}" type="text" id="csuaUnitInfo" name="csUnitAirport.csuaUnitInfo" text="${get:CsUnitInfo(csUnitAirport.csuaUnitInfo).csuiName}" value="${csUnitAirport.csuaUnitInfo}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuaUnitInfo').val())==''){return;};window.href('${basePath}admin/unit/info_details.do?id='+$('#csuaUnitInfo').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaUnitInfo}" id="csuaUnitInfo" />
			 		<span>
			 	 <a href="javascript:void(0);" onclick="if($.trim('${csUnitAirport.csuaUnitInfo}')==''){return;};window.href('${basePath}admin/unit/info.details.do?id=${csUnitAirport.csuaUnitInfo}',{controller:{editable:false,visible:true}})">
			 	 ${get:CsUnitInfo(csUnitAirport.csuaUnitInfo).csuiName}
			 	 </a>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaType')==null)?canEditable:(lz:editable(controller,'csuaType')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaType')==null)?canVisible:(lz:visible(controller,'csuaType')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaType" >
			<dt>类型:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csuaType" name="csUnitAirport.csuaType">
			 		<option value="">请选择</option>
					<option value="0" ${csUnitAirport.csuaType==0?"selected":""}>接机</option>
					<option value="1" ${csUnitAirport.csuaType==1?"selected":""}>送机</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业机场的类型</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaType}" id="csuaType" />
			 		<span>
				 ${csUnitAirport.csuaType==0?"接机":""}
				 ${csUnitAirport.csuaType==1?"送机":""}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaFlight')==null)?canEditable:(lz:editable(controller,'csuaFlight')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaFlight')==null)?canVisible:(lz:visible(controller,'csuaFlight')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaFlight" >
			<dt>航班号:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitAirport.csuaFlight" id="csuaFlight"  value="${csUnitAirport.csuaFlight}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入企业机场的航班号</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaFlight}" id="csuaFlight" />
			 		<span>
				 ${lz:html(csUnitAirport.csuaFlight)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaTime')==null)?canEditable:(lz:editable(controller,'csuaTime')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaTime')==null)?canVisible:(lz:visible(controller,'csuaTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaTime" >
			<dt>时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitAirport.csuaTime" id="csuaTime"  value="<s:date name="csUnitAirport.csuaTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业机场的时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaTime}" id="csuaTime" />
			 		<span>
			 	 <s:date name="csUnitAirport.csuaTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaContact')==null)?canEditable:(lz:editable(controller,'csuaContact')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaContact')==null)?canVisible:(lz:visible(controller,'csuaContact')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaContact" >
			<dt>联系人:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitAirport.csuaContact" id="csuaContact"  value="${csUnitAirport.csuaContact}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入企业机场的联系人</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaContact}" id="csuaContact" />
			 		<span>
				 ${lz:html(csUnitAirport.csuaContact)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaMobile')==null)?canEditable:(lz:editable(controller,'csuaMobile')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaMobile')==null)?canVisible:(lz:visible(controller,'csuaMobile')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaMobile" >
			<dt>联系电话:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitAirport.csuaMobile" id="csuaMobile"  value="${csUnitAirport.csuaMobile}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入企业机场的联系电话</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaMobile}" id="csuaMobile" />
			 		<span>
				 ${lz:html(csUnitAirport.csuaMobile)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaCount')==null)?canEditable:(lz:editable(controller,'csuaCount')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaCount')==null)?canVisible:(lz:visible(controller,'csuaCount')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaCount" >
			<dt>人数:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
					<input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input narrow"  maxlength="8" name="csUnitAirport.csuaCount" id="csuaCount"  value="${csUnitAirport.csuaCount}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入企业机场的人数</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaCount}" id="csuaCount" />
			 		<span>
			 	 ${csUnitAirport.csuaCount}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaUpdateTime')==null)?canEditable:(lz:editable(controller,'csuaUpdateTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(false && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaUpdateTime')==null)?canVisible:(lz:visible(controller,'csuaUpdateTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaUpdateTime" >
			<dt>修改时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitAirport.csuaUpdateTime" id="csuaUpdateTime"  value="<s:date name="csUnitAirport.csuaUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业机场的修改时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaUpdateTime}" id="csuaUpdateTime" />
			 		<span>
			 	 <s:date name="csUnitAirport.csuaUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(false && isAddType)||(false && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaAddTime')==null)?canEditable:(lz:editable(controller,'csuaAddTime')))}
		
		${lz:set("canVisible",(false && isAddType)||(false && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaAddTime')==null)?canVisible:(lz:visible(controller,'csuaAddTime')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaAddTime" >
			<dt>添加时间:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitAirport.csuaAddTime" id="csuaAddTime"  value="<s:date name="csUnitAirport.csuaAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业机场的添加时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaAddTime}" id="csuaAddTime" />
			 		<span>
			 	 <s:date name="csUnitAirport.csuaAddTime" format="yyyy-MM-dd HH:mm:ss"/>
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaRemark')==null)?canEditable:(lz:editable(controller,'csuaRemark')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaRemark')==null)?canVisible:(lz:visible(controller,'csuaRemark')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaRemark" style="width:98%;">
			<dt>备注:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<input type="text" class="input wide"  maxlength="256" name="csUnitAirport.csuaRemark" id="csuaRemark"  value="${csUnitAirport.csuaRemark}"/>
		 	 
		 	 
		 	 <em>请输入企业机场的备注</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input wide">
			 		<input type="hidden" value="${csUnitAirport.csuaRemark}" id="csuaRemark" />
			 		<span>
				 ${lz:html(csUnitAirport.csuaRemark)}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
				
		
		${lz:set("canEditable",(true && isAddType)||(true && !isAddType))}
		${lz:set("canEditable",(lz:editable(controller,'csuaStatus')==null)?canEditable:(lz:editable(controller,'csuaStatus')))}
		
		${lz:set("canVisible",(true && isAddType)||(true && !isAddType))}
		${lz:set("canVisible",(lz:visible(controller,'csuaStatus')==null)?canVisible:(lz:visible(controller,'csuaStatus')))}
		
		<s:if test="#request.canVisible==true">
		<dl ${(canEditable && false)?"class='hidden'":""}  ref="csuaStatus" >
			<dt>状态:</dt>
			<s:if test="#request.canEditable==true">
			${lz:set("haveEditable",true)}
			<dd>
			 	<select class="narrow" id="csuaStatus" name="csUnitAirport.csuaStatus">
			 		<option value="">请选择</option>
					<option value="0" ${csUnitAirport.csuaStatus==0?"selected":""}>未处理</option>
					<option value="1" ${csUnitAirport.csuaStatus==1?"selected":""}>已处理</option>
					<option value="2" ${csUnitAirport.csuaStatus==2?"selected":""}>审核失败</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业机场的状态</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitAirport.csuaStatus}" id="csuaStatus" />
			 		<span>
				 ${csUnitAirport.csuaStatus==0?"未处理":""}
				 ${csUnitAirport.csuaStatus==1?"已处理":""}
				 ${csUnitAirport.csuaStatus==2?"审核失败":""}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
	
		
		
		<div class="line"></div>
		<center>
			<input type="hidden" value="${csUnitAirportToken}" name="csUnitAirportToken" id="csUnitAirportToken" />
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUnitAirport.csuaId}" name="csUnitAirport.csuaId" id="csuaId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
				<s:if test="#request.controller.submits!=null">
					<s:iterator value="#request.controller.submits" id="submit" status="i">
			<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
					</s:iterator>
				</s:if>
				<s:else>
			<button class="button" type="submit" name="submiter" value="完成">&nbsp;&nbsp;完成&nbsp;&nbsp;</button>
			<button class="button" type="submit" name="submiter" value="保存">&nbsp;&nbsp;保存&nbsp;&nbsp;</button>
				</s:else>
			<button class="button" type="reset">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
			</s:if>
			<s:else>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;确定&nbsp;&nbsp;</button>
			</s:else>
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		</center>
		
		
	</div>	
</div>
<div class="foot"></div>
	
</form>

<script>
	function cancel(){
		if(${controller.mutual=="dialog"})
			$.closeModalDialog();
		else if(${controller.mutual=="page"})
			window.location="${lz:xeh(entrypoint)}";
		else if(true)
			$.closeModalDialog();
		else
			window.location="${lz:xeh(entrypoint)}";
	}
	
	function href(url,params){
		if(${controller.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${controller.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else
			$.submit(url,params,"_self");	
	}
</script>

</body>
</html>