<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="wf" uri="/workflow-tags" %>


<style>
<!--
#synergy-type{padding:30px;white-space:nowrap; }
#condition{ height:200px;}
-->
</style>


<script type="text/javascript">
<!--
function setActionConfig(id,type){
	var paints=flash.getPaints();
	var arrow = paints[id];
	if(!arrow.start||!arrow.start.paint){
		$.dialog({title:"警告",html:"当前箭头没有起点"});
		return;
	}
	var source = paints[arrow.start.paint];
	if(source.type=="Condition"){		
		var arrays = getInputArrowsByPaintId(source.id);
		if(arrays.length!=1){
			$.dialog({title:"警告",html:"指向上级条件节点的箭头数必需为一个"});
			return;
		}
		var process = paints[arrays[0].start.paint];
		
		if(!process || !configs[process.id] || !configs[process.id].model){
			$.dialog({title:"警告",html:"当前箭头没有配置表单源"});
			return;
		}
		var model = getModel(configs[process.id].model);		
		var actionCondition=$(template($("#conditionTemplate").html(),{model:model,id:id}));	
		actionCondition.dialog({
			title:"流转条件",
			modal:true,
			onOk:function(){
				if(!configs[id])
					configs[id]={};
				configs[id].relation=actionCondition.find("select[name='relation']").val();
				var trs = actionCondition.find("tbody tr");
				configs[id].conditions=[];
				$.each(trs,function(i,o){
					var len = configs[id].conditions.length;
					configs[id].conditions[len]={};
					configs[id].conditions[len].property = $(o).find("select[name='property']").val();
					configs[id].conditions[len].operator = $(o).find("select[name='operator']").val();
					configs[id].conditions[len].value = $(o).find("input[name='value']").val();
				});	
			}
		});	
		actionCondition.find("#add").click(function(){
			var conditionItem=$(template($("#conditionItemTemplate").html(),{model:model}));	
			conditionItem.appendTo("#condition tbody");
		});			
	}else{
		var actionSynergy=$(template($("#synergyTemplate").html(),{id:id,type:type}));	
		actionSynergy.dialog({
			title:"协同机制",
			modal:true,
			onOk:function(){
				configs[id].synergy=actionSynergy.find("input[name='synergy']:checked").val();
			}
		});		
	}
	
	
};
//-->
</script>




<script id="synergyTemplate" type="template">
	<!-- 协同机制 -->
	<div id="synergy-type" class="cnt">
		<input #[configs[id].synergy!="1" ? "checked='checked'" : ""]# type="radio" name="synergy" value="0"/>任意其中一人处理，当前流程可向后流转。
		<br/><br/>
		<input #[configs[id].synergy=="1" ? "checked='checked'" : ""]# type="radio" name="synergy" value="1"/>所有涉及人员全部处理，当前流程可向后流转。
	</div>
</script>


<script id="conditionTemplate" type="template">
	<!-- 流转条件 -->
	<div id="condition" class="cnt">
		<div style="padding:10px 0px;">
			条件关系:
			<select name="relation">
				<option #[configs[id].relation=="or" ? "selected" : ""]# value="or">任一满足(||)</option>
				<option #[configs[id].relation=="and" ? "selected" : ""]# value="and">同时满足(&&)</option>				
			</select>
		</div>
		<table id="condition_table" class="table" border="0" cellspacing="1" cellpadding="0">			
			<thead class="state-header">
			  <tr>
				 <td width="120">
					字段名称
				 </td>
				 <td width="120">
					运算符
				 </td>
				 <td width="180">
					值
				 </td>
				 <td width="80">
					<a id="add" href='javascript:void(0);'>添加条件</a>
				 </td>
			  </tr>
			 </thead>
			 <tbody>
#[for condition:configs[id].conditions]#				
	<tr>
		<td>
			<select name="property">
				<option value="">请选择</option>
			#[for property:model.propertys]#
				<option #[condition.property==property.name?"selected":""]# value="#[property.name]#">#[property.caption]#</option>
			#[for]#
			</select>
		</td>
		<td>
			<select name="operator">
				<option value="">请选择</option>
				<option #[condition.operator=="="?"selected":""]# value="=">等于</option>
				<option #[condition.operator==">"?"selected":""]# value=">">大于</option>
				<option #[condition.operator==">="?"selected":""]# value=">=">大于等于</option>
				<option #[condition.operator=="<"?"selected":""]# value="<">小于</option>
				<option #[condition.operator=="<"?"selected":""]# value="<">小于等于</option>
				<option #[condition.operator=="!="?"selected":""]# value="!=">不等于</option>
				<option #[condition.operator=="@"?"selected":""]# value="@">包含</option>
			</select>
		</td>
		<td>
			<input type="text" name="value" class="input" value="#[condition.value]#"/>
		</td>
		<td>
			<a onclick="$(this).parents('tr:first').remove();" href='javascript:void(0);'>删除条件</a>
		</td>
	</tr>
#[for]#
			 </tbody>			 
		  </table>
	</div>
</script>


<!-- 条件项列表模板 -->
<script id="conditionItemTemplate" type="template">
	<tr>
		<td>
			<select name="property">
				<option value="">请选择</option>
			#[for property:model.propertys]#
				<option value="#[property.name]#">#[property.caption]#</option>
			#[for]#
			</select>
		</td>
		<td>
			<select name="operator">
				<option value="">请选择</option>
				<option value="=">等于</option>
				<option value=">">大于</option>
				<option value=">=">大于等于</option>
				<option value="<">小于</option>
				<option value="<">小于等于</option>
				<option value="!=">不等于</option>
				<option value="@">包含</option>
			</select>
		</td>
		<td>
			<input name="value" type="text" class="input"/>
		</td>
		<td>
			<a onclick="$(this).parents('tr:first').remove();" href='javascript:void(0);'>删除条件</a>
		</td>
	</tr>
</script>

