<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/fee/goods.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/fee/goods.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csGoods.csgId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csgId,csgName,csgProduct,csgUserType,csgRule,csgProfile,csgStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgId,csgName,csgProduct,csgUserType,csgRule,csgProfile,csgStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csgId,csgName,csgProduct,csgUserType,csgRule,csgProfile,csgStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgId,csgName,csgProduct,csgUserType,csgRule,csgProfile,csgUpdateTime,csgAddTime,csgStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"商品编辑":CTRL.title}</title>
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
	$("#goodsForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csgId==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgName==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgProduct==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgProduct":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgUserType==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgUserType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择计费类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgRule==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgRule":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgProfile==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgProfile":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "商品描述最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgAddTime==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgStatus==true">
		${lz:set("haveEditable",true)}
		,"csGoods.csgStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	},function(){
		${lz:set("注释","****表单提交自定义判断，阻止提交返回提示字符串即可****")}
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
	
	$("#csgRule").on("change",function(){
		selRule($(this).val());
	})
});

function selRule(value){
	if(value == 0 || value==undefined) $("#rule-param-div").html("");
	$.ajax({
			url:"rule_query.do",
			cache:false,
			dataType:"json",
			type:"post",
			data:{
				"value":"id:"+value,
				"object":true
			},
			success:function(data){
				if(data && data.length>0){
					//try{
						if(data[0].object.csrProfile!=""){
							var json = eval("("+data[0].object.csrProfile+")");
							var params=[], xhtml="", tmparr={}; 
							if(json && json.length>0){
								var pattern = new RegExp("\\{(.| )+?\\}","igm");
								for(var i=0; i<json.length; i++){
									var parr = json[i].text.match(pattern);
									var txt = json[i].text;
									if(parr.length>0){
										for(var j=0;j<parr.length;j++){
											var htl ="<input type='text' parent='"+json[i].name+"' name='"+parr[j].replace("{","").replace("}","")+"' style=\"width:50px\" xtype='rulep' />";
											txt = txt.replace(parr[j], htl);
										}
									}
									xhtml += (i>0?"<br/>并且":"")+ txt;
								}
								appendHTML(xhtml);
							}
						}else{
							$("#rule-param-div").html("");
						}
					//}catch(e){alert("规则参数表达式设置异常!");}
				}else{
					$("#rule-param-div").html("");
				}
			}
	});
}

function appendHTML(htl){
	var xhtml = "<dl major style='width:98%;'><dt>规则参数：</dt><dd>";
		xhtml += htl;
		xhtml += "</dd></dl>";
	$("#rule-param-div").html(xhtml);	
}

function dosub(){
	var params = {};
	$("#rule-param-div").find("input[xtype='rulep']").each(function(i){
		if(!params[$(this).attr("parent")])
			params[$(this).attr("parent")]={};
		params[$(this).attr("parent")][$(this).attr("name")] = $(this).val();
	});
	var a = JSON.stringify(params);
	$("#ruleparams").val(a);
	return true;
}

</script>

<s:if test="false"></s:if>
<s:elseif test="false"></s:elseif>
<s:else>
	<form class="form editform" ref="CsGoods" id="goodsForm" name="goodsForm" action="${empty CTRL.action?"goods_save.do":CTRL.action}" method="post" onsubmit="dosub();">
		<input type="hidden" name="ruleparams" id="ruleparams"/>
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写商品相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csGoodsToken}" name="csGoodsToken" id="csGoodsToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csGoods.csgId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csgId和after$csgId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgId==true">
	${before$csgId}
	<dl class="csgId ${CTRL.e.csgId?"hidden":""}" major  ref="csgId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csgId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csGoods$csgId!=null">${csGoods$csgId}</s:if><s:else>
			<input type="hidden" value="${csGoods.csgId}" name="csGoods.csgId" id="csgId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgId">${csGoods.csgId}</textarea>
		 		<span>
		 	
			 ${csGoods.csgId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgId}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$csgName和after$csgName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgName==true">
	${before$csgName}
	<dl class="csgName " major  ref="csgName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.csgName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGoods$csgName!=null">${csGoods$csgName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csGoods.csgName" id="csgName"  value="${csGoods.csgName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入商品的名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgName">${csGoods.csgName}</textarea>
		 		<span>
		 	
			 ${csGoods.csgName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgName}
	</s:if>
	
	${lz:set("注释","*****************所属产品字段的输入框代码*****************")}
	${lz:set("注释","before$csgProduct和after$csgProduct变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgProduct==true">
	${before$csgProduct}
	<dl class="csgProduct " major  ref="csgProduct" >
		<dt>所属产品:</dt>
		<s:if test="#request.CTRL.e.csgProduct==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGoods$csgProduct!=null">${csGoods$csgProduct}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/fee/product_query.do?size=-1" id="csgProduct" name="csGoods.csgProduct">
		 				<option selected value="${csGoods.csgProduct}">
		 					${get:CsProduct(csGoods.csgProduct).cspName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgProduct').val())==''){return;};window.href('${basePath}${proname}/fee/product_details.do?key='+$('#csgProduct').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属产品字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgProduct">${csGoods.csgProduct}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/product_details.do?key=${csGoods.csgProduct}',{ctrl:{editable:false,visible:true}})">
			 ${csGoods.csgProduct$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgProduct}
	</s:if>
	
	${lz:set("注释","*****************计费类型字段的输入框代码*****************")}
	${lz:set("注释","before$csgUserType和after$csgUserType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgUserType==true">
	${before$csgUserType}
	<dl class="csgUserType " major  ref="csgUserType" >
		<dt>计费类型:</dt>
		<s:if test="#request.CTRL.e.csgUserType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGoods$csgUserType!=null">${csGoods$csgUserType}</s:if><s:else>
		 	<select class="narrow" id="csgUserType" name="csGoods.csgUserType">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query("ccclubs_system","select * from cs_user_type where 1=1 ") )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.csutId}" ${csGoods.csgUserType==item.csutId?"selected":""}>${item.csutName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgUserType').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?key='+$('#csgUserType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品的计费类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****计费类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgUserType">${csGoods.csgUserType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/type_details.do?key=${csGoods.csgUserType}',{ctrl:{editable:false,visible:true}})">
			 ${csGoods.csgUserType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgUserType}
	</s:if>
	
	${lz:set("注释","*****************单位规则字段的输入框代码*****************")}
	${lz:set("注释","before$csgRule和after$csgRule变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgRule==true">
	${before$csgRule}
	<dl class="csgRule " major  ref="csgRule" >
		<dt>单位规则:</dt>
		<s:if test="#request.CTRL.e.csgRule==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGoods$csgRule!=null">${csGoods$csgRule}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/fee/rule_query.do?size=-1" id="csgRule" name="csGoods.csgRule" onchange="selRule(this);">
		 				<option selected value="${csGoods.csgRule}">
		 					${get:CsRule(csGoods.csgRule).csrName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgRule').val())==''){return;};window.href('${basePath}${proname}/fee/rule_details.do?key='+$('#csgRule').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****单位规则字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgRule">${csGoods.csgRule}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/rule_details.do?key=${csGoods.csgRule}',{ctrl:{editable:false,visible:true}})">
			 ${csGoods.csgRule$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgRule}
	</s:if>
	
	<div id="rule-param-div"></div>
	
	${lz:set("注释","*****************商品描述字段的输入框代码*****************")}
	${lz:set("注释","before$csgProfile和after$csgProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgProfile==true">
	${before$csgProfile}
	<dl class="csgProfile " minor  ref="csgProfile" style="width:98%;">
		<dt>商品描述:</dt>
		<s:if test="#request.CTRL.e.csgProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csGoods$csgProfile!=null">${csGoods$csgProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="csgProfile" name="csGoods.csgProfile" rows="5">${csGoods.csgProfile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入商品的商品描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csGoods.csgProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgProfile}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csgUpdateTime和after$csgUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgUpdateTime==true">
	${before$csgUpdateTime}
	<dl class="csgUpdateTime " major  ref="csgUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csgUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csGoods$csgUpdateTime!=null">${csGoods$csgUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGoods.csgUpdateTime" id="csgUpdateTime"  value="<s:date name="csGoods.csgUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgUpdateTime">${csGoods.csgUpdateTime}</textarea>
		 		<span>
		 	
			 ${csGoods.csgUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csgAddTime和after$csgAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgAddTime==true">
	${before$csgAddTime}
	<dl class="csgAddTime " major  ref="csgAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csgAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csGoods$csgAddTime!=null">${csGoods$csgAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGoods.csgAddTime" id="csgAddTime"  value="<s:date name="csGoods.csgAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgAddTime">${csGoods.csgAddTime}</textarea>
		 		<span>
		 	
			 ${csGoods.csgAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csgStatus和after$csgStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgStatus==true">
	${before$csgStatus}
	<dl class="csgStatus " major  ref="csgStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csgStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGoods$csgStatus!=null">${csGoods$csgStatus}</s:if><s:else>
		 	<select class="narrow" id="csgStatus" name="csGoods.csgStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csGoods.csgStatus==1?"selected":""}>正常</option>
				<option value="0" ${csGoods.csgStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择商品的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgStatus">${csGoods.csgStatus}</textarea>
		 		<span>
		 	
			 ${csGoods.csgStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csGoods.csgId}" name="csGoods.csgId" id="csgId" />
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