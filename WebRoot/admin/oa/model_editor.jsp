<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>

${lz:set("isDetail",(empty edittype))}
<s:if test="#request.isDetail==false">
<style>
.table tbody td{height:28px;line-height:28px;}
.table thead td a{text-decoration:underline;}
.table tbody td a{text-decoration:underline;}
#smFields{}
.plate{margin:10px auto;}
</style>
<script type="text/javascript">
<!--
window.$on("readyStart",function(){
	$(".editform .body").css("width","auto").css("padding-top","10px");
	$("body").height($(top.window).height()-100);
	var plate = $("<div class='plate'></div>").insertBefore($("#smFunctions").parents("dl")).css("clear","both");
	$(".editform .narrow").each(function(){
		$(this).css("width","100px");
		$(this).parents("dd").prev().css("width","60px");
		$(this).parents("dd").css("margin-left","60px");
		$(this).parents("dl").css("width","215");
		$(this).parents("dl").appendTo(plate);
	});
	$("#smFunctions").parents("dl").appendTo(plate);
	$("#smFunctions").parent().width(750);
	$("<div class='clear'></div>").appendTo(plate);
	$("#smName").parents("dl").css("margin-left","50px");
	$(".prompt").css("width","100%").css("margin","10px auto").remove();
	$(".editform .body .line").css("width","100%").css("margin","10px auto");
	var smFields = $("#smFields");
	var data = smFields.val();
	var smFieldsDL = $("#smFields").parents("dl");
	smFields.insertBefore(smFieldsDL).hide();
	var div = $("<div id='smFields'></div>").replaceAll(smFieldsDL).css("width","100%").css("clear","both");
	${lz:set("models",get:propertys("系统数据模型类","model_class"))}
	var models=[{text:"无",value:""}];
	<s:iterator value="#request.models" id="model">
	models[models.length]={
		value:"${lz:replace(model.spFlag,"[^\\.]+\\.","")}",text:"${model.spName}"
	}
	</s:iterator>
	var columns = [
		["名称",80,"text","caption",null,"名称{index}"],
		["英文",80,"text","name",null,"name_{index}"],
		["类型",70,"select","input",[{"value":"number","text":"数字","depict":"","size":8},{"value":"money","text":"小数","depict":"","size":8},{"value":"text","text":"短文本","depict":"","size":32},{"value":"textarea","text":"长文本","depict":"","size":200},{"value":"html","text":"HTML","depict":"","size":65535},{"value":"datetime","text":"日期时间","depict":"","size":19},{"value":"date","text":"日期","depict":"","size":19},{"value":"mobile","text":"手机号码","depict":"","size":32},{"value":"url","text":"网址","depict":"","size":128},{"value":"email","text":"邮箱地址","depict":"","size":128},{"value":"password","text":"密码","depict":"","size":32},{"value":"select","text":"下拉框","depict":"","size":4},{"value":"relate","text":"关联对象","depict":"","size":8},{"value":"checkbox","text":"多选框","depict":"","size":256},{"value":"radio","text":"单选框","depict":"","size":4},{"value":"image","text":"图片","depict":"","size":128},{"value":"imagelist","text":"图片列表","depict":"","size":1024},{"value":"file","text":"文件","depict":"","size":128},{"value":"filelist","text":"文件列表","depict":"","size":1024}]],
		["关联",80,"select","relate",models],
		["添加时",70,"select","add",[{"value":"editable","text":"可编辑","depict":""},{"value":"readonly","text":"只读","depict":""},{"value":"none","text":"隐藏","depict":""}]],
		["编辑时",70,"select","edit",[{"value":"editable","text":"可编辑","depict":""},{"value":"readonly","text":"只读","depict":""},{"value":"none","text":"隐藏","depict":""}]],
		["查询",40,"checkbox","query",[{"value":"true"}],"true"],
		["列表",40,"checkbox","list",[{"value":"true"}],"true"],
		["标识",40,"checkbox","flag",[{"value":"true"}],""],
		["唯一",40,"checkbox","unique",[{"value":"true"}],""],
		["非空",40,"checkbox","not_null",[{"value":"true"}],"true"],
		["说明",100,"text","depict",null,""],
		["默认值",50,"text","value",null],
		["列表项",100,"grid","options",[{"text":"项名称","value":"text"},{"text":"值","value":"value"}]],
		["旧名称",80,"hidden","old",null]
	];
	// 创建项目树形列表
	var grid=div.grid({
		column:columns,
		change:function(input){
			var name = input.attr("name");
			if(name.indexOf(".flag")>-1 && input.is(":checked")){
				$("input[name='"+name+"']").not(input).attr("checked",false);
			}else if(name.indexOf(".name")>-1){
				input.val(input.val().replace(/[^_a-zA-Z0-9]*/g,""));
			}else if(name.indexOf(".input")>-1){
				var data = input.find("option:selected").data("data");
				if(data && data.size)
					input.parents("tr").find("input[name*='.length']").val(data.size);
				if(input.val()=="money")
					input.parents("tr").find("input[name*='.unit']").val("元");
			}
		}
	});
	if(${srvModel.smId==null}){
		grid.add({data:["xx名称","name","text",null,"editable","editable","true","true","true","false","true","","",null]});
		grid.add({data:["备注信息","remark","textarea",null,"editable","editable","true","true","false","false","false","","",null]});
		grid.add({data:["添加时间","add_time","datetime",null,"none","none","true","true","false","false","true","","new Date()",null]});
		grid.add({data:["修改时间","update_time","datetime",null,"none","none","true","true","false","false","true","","new Date()",null]});
		grid.add({data:["状态","status","select",null,"editable","editable","true","true","false","false","true","","",[{"text":"有效","value":"1"},{"text":"无效","value":"0"}]]});
	}else{
		var datas = ${lz:or(srvModel.smFields,"[]")};
		for(var o in datas){
			var array = [];
			for(var p in columns)
				array[array.length]=datas[o][columns[p][3]];
			grid.add({data:array});
		}
	}
	$(".editform").submit(function(){
		smFields.val(grid.getValue());
		//验证是否添加了字段
		if($.trim(smFields.val())=="[]"){
			showTips("未配置字段信息");		
			return false;
		}
		//验证名称是否为空，是否重复
		var datas = eval("("+smFields.val()+")");
		var repeates={
			captions:{},names:{}
		};
		for(var i in datas){
			var data = datas[i];
			//验证名称是否为空，是否重复
			if(repeates.captions[data.caption]){
				grid.find("tbody tr:eq("+i+") [name='caption']").say("名称不能重复");
				return false;
			}else{
				repeates.captions[data.caption]=true;
			}
			if(repeates.names[data.name]){
				grid.find("tbody tr:eq("+i+") [name='name']").say("英文名称不能重复");
				return false;
			}else{
				repeates.names[data.name]=true;
			}
			if(!/^[a-zA-Z0-9_]+$/.test(data.name)){
				grid.find("tbody tr:eq("+i+") [name='name']").say("英文名称只能是英文字母或数字或下划线");
				return false;
			}
			if(data.caption==""){
				grid.find("tbody tr:eq("+i+") [name='caption']").say("名称不能为空");
				return false;
			}
			//验证关联对象是否为空
			if(data.input=="relate" && data.relate==""){
				grid.find("tbody tr:eq("+i+") [name='relate']").say("输入类型为关联对象时，必须选择一个关联对象");
				return false;
			}
			//验证添加是隐藏或只读并且非空的是否有默认值
			if(data.add!="editable" && data.not_null=="true" && data.value==""){
				grid.find("tbody tr:eq("+i+") [name='value']").say("添加不是可编辑的非空字段必须填个默认值");
				return false;
			}
			//验证下拉框的是否有列表项
			if((data.input=="select" || data.input=="checkbox" || data.input=="radio") && (typeof(data.options)=="undefined" || data.options.length==0)){
				grid.find("tbody tr:eq("+i+") [name='options']").say("下拉框或多选框或单选框必须配置列表项");
				return false;
			}
		}
	});
});
//-->
</script>
</s:if>
