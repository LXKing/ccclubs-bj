<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="wf" uri="/workflow-tags" %>


<style id="workfow-css">
<!--
#designate,#forms,#data,#roles{float:left;overflow:scroll;}
#designate{width:33%;}
#roles{width:67%;}
#forms{width:25%;}
#design{overflow:hidden;}
.visible,.editable{cursor:pointer;}
-->
</style>

<script type="text/javascript">
<!--
var project=${lz:json(wf:project())};
var models=${lz:json(wf:models())};
${lz:set("roles",wf:roles())}
var roles=${lz:json(roles)};

function getModel(name){
	for(var o in models){
		if(models[o].name==name){
			return models[o];
		}
	}
}


function setProcessConfig(id,type){
	try{
		var formatModelName = workflowForm.find("#swModel").val().replace(/(^|\s+)\w/g,function(s){
          	return s.toLowerCase();
       	});
       	
       	var modelName = workflowForm.find("#swModel").val();
       	
       	var currentModel=getModel(modelName);
       	var fields = (configs[id].fields?configs[id].fields:{});
       	       	       	
		var html = template($("#processTemplate").html(),{id:id,type:type,model:currentModel,fields:fields});
		var processConfig=$(html);	
		processConfig.find(".btns li[ref]").click(function(){
			$(this).siblings("li[ref]").attr("class","state-default");
			$(this).attr("class","state-focus");
			$(this).parents(".tab").find(".cnt").hide();
			$($(this).attr("ref")).show();
		});
		//把角色列表构造成树形
		processConfig.find("#role_table").treegrid({expand:true});	
		processConfig.find("input[name='designate']").click(function(){
			var value = $(this).val();
			if(value==1 || value==2){
				$("#role_table,#synergy_table").show();
			}else{
				$("#role_table,#synergy_table").hide();
			}
		});
		processConfig.css("width",$(window).width()-150);
		processConfig.find("#designate,#roles,#synergy,.panel,#forms,#design,#data,#list").css("height",$(window).height()-200);
		processConfig.dialog({
			title:"流程配置",
			modal:true,
			onOk:function(){
				if(!configs[id])
					configs[id]={};
				//当前流程的角色人员类型			
				configs[id].designate="{"+processConfig.find("input[name='designate']:checked").val()+"}";
				var strRoles="";
				//当前流程的角色
				configs[id].roles=(function(){
					var sValues = "";
					$.each(processConfig.find("input[name='role']:checked"),function(i,o){
						sValues+=" ["+$(o).val()+"] ";
						strRoles+=(strRoles==""?"":",")+$.trim($(o).next("a").text());
					});
					return sValues;
				})();
												
				//角色人员的描述，反馈到flash
				var strDefine = (function(){
					var sValues = ["任何角色","系统指定","任务分派","直接领导","直接下级"][Number(configs[id].designate.replace(/\{|\}/g,""))];
					var designate = Number(configs[id].designate.replace(/\{|\}/g,""));
					if(designate==1 || designate==2){
						sValues="";
						if(strRoles!="")
							sValues+=strRoles;
						if(sValues=="")
							sValues+="未选定";
					}				
					return "("+sValues+")";
				})();
				
				if(strDefine.define=="(未选定)"){
					alert("请选择角色");
					return false;
				}else
					flash.setPaintDefine(id,strDefine);
					
				//当前流程action
				configs[id].action=processConfig.find("#modelName option:selected").attr("action");
				
								
				//当前流程的对象的字段信息
				configs[id].fields={};				
	         	var model = currentModel;
	         	if(model)
		         	for(var o in model.propertys){
		         		var property=model.propertys[o];
		         		var tr = $("#model_table tbody tr[ref='"+property.name+"']");
		         		var frame = $(window.frameHandle.contentWindow.document);
						var formItem = frame.find("[name='"+formatModelName+"."+property.name+"']");
						if(formItem.size()==0)continue;
						if(property.input=="html")
							window.frameHandle.contentWindow.KE.sync(property.name);
		         		configs[id].fields[property.name]={
		         			visible:$.trim(tr.find(".visible").text())=="是",
		         			editable:$.trim(tr.find(".editable").text())=="是",
		         			defaultValue:getFormFieldValue(formItem.get(0))
		         		};
		         	}
				processConfig.closeDialog();
			}
		});
		processConfig.find("#role_table .icon").click();
		if(processConfig.find("input[name='designate']:checked").size()==0)
			processConfig.find("input[name='designate']:first").click();
		
		
		
		
		//当点击表单设计中的可显示
		$("#model_table tbody .visible").click(function(){
			var bTrue = $.trim($(this).text())=="是" ? false:true;				
			var fieldName = $(this).parent().attr("ref");
			var frame = $(window.frameHandle.contentWindow.document);
			var formItem = frame.find("[name='"+formatModelName+"."+fieldName+"']");
			var dl = formItem.parents("dl");
			if($(this).parent().attr("input")=="html")
				window.frameHandle.contentWindow.KE.sync(fieldName);
			if(type=="Start" && !bTrue && $(this).parent().attr("not_null")=="true" && $.trim(checkNull(formItem.get(0)))!=""){
				alert("该字段不能为空，请先在右边表单中配置一个默认值");
				return;
			}
			$(this).text(bTrue?"是":"否");
			if(bTrue)
				dl.show();					
			else
				dl.hide();
		});
		//当点击表单设计中的可编辑
		$("#model_table tbody .editable").click(function(){
			var bTrue = $.trim($(this).text())=="是" ? false:true;
			var fieldName = $(this).parent().attr("ref");
			var frame = $(window.frameHandle.contentWindow.document);
			var formItem = frame.find("[name='"+formatModelName+"."+fieldName+"']");
			var dl = formItem.parents("dl");
			if($(this).parent().attr("input")=="html")
				window.frameHandle.contentWindow.KE.sync(fieldName);
			if(type=="Start" && !bTrue && $(this).parent().attr("not_null")=="true" && $.trim(checkNull(formItem.get(0)))!=""){
				alert("该字段不能为空，请先在右边表单中配置一个默认值");
				return;
			}
			$(this).text(bTrue?"是":"否");
			if(bTrue){
				dl.find(".state-input").remove();
				dl.find("dd").show();		
			}else{
				var stateInput = $("<div class='state-input' style='margin-left:105px;height:"+(dl.find("dd").height()/2)+"px;line-height:25px;padding-left:5px;'>内容只读</div>").appendTo(dl);
				dl.find("dd").hide();
			}
		});	
		
		var submitData = {submits:[]};
		var sSubmits="",arrows=getOutputArrowsByPaintId(id);
		for(var o in arrows){
			submitData.submits[submitData.submits.length]={name:arrows[o].caption,value:arrows[o].caption}			
		}
		for(var o in fields){
			if($.trim(fields[o].defaultValue)!=""){
				submitData[formatModelName+"."+o]=fields[o].defaultValue;
			}
		}
		$.submit(
			"${basePath}"+project.namespace+"/"+currentModel.namespace+"/"+currentModel.action+"_edit.do?mm="+Math.random(),
			{ctrl:submitData},
			"iframe"
		);		
		
	}catch(ex){
		alert(ex.message);
	}
};



//获取form表单字段的值
function getFormFieldValue(oItem){
	var parenter=$(oItem).parents("dd");
	if(/^text|hidden|textarea|file|password|select-one$/.test(oItem.type))
		return $(oItem).val();
	if(/^checkbox|radio$/.test(oItem.type)){
		var checkeds = parenter.find("input:checked");
		var sValue = "";
		$.each(checkeds,function(i,o){
			sValue += (sValue==""?",":"")+$(o).val();
		});
		return sValue;
	}
	if(oItem.type=="select-multiple"){
		var options = $(oItem).find("option");
		var sValue = "";
		$.each(options,function(i,o){
			sValue += (sValue==""?",":"")+$(o).val();
		});
		return sValue;
	}
	
}


function checkNull(oItem){
	var parenter=$(oItem).parents("dd");	
	if((/^text|hidden|textarea|file|password|select-one$/.test(oItem.type) && $.trim(oItem.value)=="")||(/^checkbox|radio$/.test(oItem.type) && parenter.find("input:checked").length==0)){
		var strLabel=parenter.prev("dt").html();
		if(!strLabel)
			strLabel="";							
		if(/^text|textarea|password$/.test(oItem.type))
			return ("请输入"+strLabel.replace(/[\:|：]/g,""));
		else if(/^file|hidden$/.test(oItem.type))
			return (strLabel.replace(/[\:|：]/g,"")+"不能为空");
		else if(/^select-one|checkbox|radio$/.test(oItem.type))
			return ("请选择"+strLabel.replace(/[\:|：]/g,""));
	}
}

window.frameHandle = null;
function onFrameLoad(_frame){
	window.frameHandle = _frame;
	var frame = $(window.frameHandle.contentWindow.document);	
	frame.ready(function(){
		var buttons = frame.find("form center button");
		buttons.attr("onclick","").click(function(){
			alert($(this).text());
			return false;
		});
		var modelName = workflowForm.find("#swModel").val().replace(/(^|\s+)\w/g,function(s){
          	return s.toLowerCase();
       	});
		var trs = $("#model_table tbody tr");
		$.each(trs,function(i,o){
			var fieldName = $(o).attr("ref");
			var formItem = frame.find("[name='"+modelName+"."+fieldName+"']");
			var dl = formItem.parents("dl");
			if($.trim($(o).find(".visible").text())=="否")
				dl.hide();
			if($.trim($(o).find(".editable").text())=="否"){
				var stateInput = $("<div class='state-input' style='margin-left:105px;height:"+((dl.find("dd").height()||80)/2)+"px;line-height:25px;padding-left:5px;'>内容只读</div>").appendTo(dl);
				dl.find("dd").hide();
			}
		});		
	});
}

//-->
</script>



<script id="processTemplate" type="template">
<!-- 流程配置表单 -->
<div id="processConfig" class="tab">
	<ul class="btns">			
		<li ref="#forms-design" class="state-focus">表单设计</li>
		<li ref="#designate-role-user-synergy" class="state-default">角色用户</li>	
		<div class="clear"></div>
	</ul>
	<div class="panel">
			
		<div id="forms-design" class="cnt">
			<!-- 表单列表 -->
			<div id="forms" class="blank">
				
				<table id="model_table" style="table-layout:auto;" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
					<thead class="state-header">
					  <tr>
						 <td>
						 	字段名称
						 </td>
						 <td>
						 	显示
						 </td>
						 <td>
						 	编辑
						 </td>
					  </tr>
					 </thead>
					 <tbody>

#[for property:model.propertys]#
	#[if property.input!="none" && property.input!="hidden"]#
<tr not_null="#[property.not_null]#" input="#[property.input]#" ref="#[property.name]#">
	<td>
		#[property.caption]##[property.not_null==true?"*":""]#
	</td>
	<td class="visible" align="center">
		#[fields[property.name] && fields[property.name].visible==false?"否":"是"]#
	</td>
	<td class="editable" align="center">
		#[fields[property.name] && fields[property.name].editable==false?"否":"是"]#
	</td>
</tr>
	#[if]#
#[for]#

					 </tbody>			 
				  </table>
			</div>
			<div id="design">
				<iframe id="iframe" onload="onFrameLoad(this)" name="iframe" src="#" frameborder="0" height="100%" width="100%"/>
			</div>
		</div>

		
		<div id="designate-role-user-synergy" class="cnt hidden">			
			<!-- 指定方式 -->
			<div id="designate" class="blank">
				<table id="designate_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
				<thead class="state-header">
				  <tr>
					 <td width="200">
					 	指定方式
					 </td>
				  </tr>
				 </thead>	
				 <tbody>
					#[if type=="Start"]#
					  <tr>
						<td>
							<input #[configs[id].designate=="{0}" ? "checked='checked'" : ""]# name="designate" type="radio" value="0"/>
							&nbsp;
							<a href="javascript:void(0);">任何角色</a>
						</td>
					  </tr>
					#[if]#
					  <tr>
						<td>
							<input #[configs[id].designate=="{1}" ? "checked='checked'" : ""]# name="designate" type="radio" value="1"/>
							&nbsp;
							<a href="javascript:void(0);">系统指定</a>
						</td>
					  </tr>
					#[if type=="Process"]#
					  <tr>
						<td>
							<input #[configs[id].designate=="{2}" ? "checked='checked'" : ""]# name="designate" type="radio" value="2"/>
							&nbsp;
							<a href="javascript:void(0);">任务分派</a>
						</td>
					  </tr>
					  <tr>
						<td>
							<input #[configs[id].designate=="{3}" ? "checked='checked'" : ""]# name="designate" type="radio" value="3"/>
							&nbsp;
							<a href="javascript:void(0);">直接领导</a>
						</td>
					  </tr>
					  <tr>
						<td>
							<input #[configs[id].designate=="{4}" ? "checked='checked'" : ""]# name="designate" type="radio" value="4"/>
							&nbsp;
							<a href="javascript:void(0);">直接下级</a>
						</td>
					  </tr>					  
					#[if]#					  
					  <tr>
						<td height="100%"></td>
					  </tr>
				 </tbody>			 
			  </table>
			</div>
			<!-- 角色列表 -->
			<div id="roles" class="blank">
			<table id="role_table" style="table-layout:auto;" class="table #[configs[id].designate=="{1}"||configs[id].designate=="{2}" ? "" : "hidden"]#" width="100%" border="0" cellspacing="1" cellpadding="0">
				<thead class="state-header">
				  <tr>
					 <td width="45%">
					 	角色列表
					 </td>
				  </tr>
				 </thead>	
				 <tbody>
					  #[for role:roles]#
					  <tr id="#[role.srId]#"  tree="#[role.srParent]#">
						<td class=" node">
							<input #[configs[id].roles && configs[id].roles.indexOf("["+role.srId+"]")>-1 ? "checked='checked'" : ""]# name="role" type="checkbox" value="#[role.srId]#"/>
							&nbsp;
							<a roleid="#[role.srId]#" href="javascript:void(0);">
								#[role.srName]#
							</a>
						</td>						
					  </tr>
				 	  #[for]#
				 </tbody>			 
			  </table>
			</div>					
		</div>	


	</div>
</div>
</script>






