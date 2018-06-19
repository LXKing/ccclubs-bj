<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/api.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/api.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvApi.saId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"saId,saName,saNamespace,saType,saOpen,saDescript,saParams,saOauth,saReturnStruct,saJavaCode,saReturnFormat,saError",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"saId,saName,saNamespace,saType,saOpen,saDescript,saParams,saOauth,saReturnStruct,saJavaCode,saReturnFormat,saError",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"saId,saName,saNamespace,saType,saOpen,saDescript,saParams,saOauth,saReturnStruct,saJavaCode,saReturnFormat,saError",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"saId,saName,saNamespace,saType,saOpen,saDescript,saParams,saOauth,saReturnStruct,saJavaCode,saReturnFormat,saError",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"API接口编辑":CTRL.title}</title>
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
${lz:set("models",get:propertys("系统数据模型类","model_class"))}
var systemModels={<s:iterator value="#request.models" id="model" status="i">
	${i.count>1 ? ",":""}"${model.spName}":"${model.spFlag}"
</s:iterator>};
var excludes={"SrvUser":true};//如果需要排除的关联字段
var oauthclasses={"CsMember":true}//如果需要授权的关联字段
//如果一级属性的文本字段配置了编辑表格格式化信息，则自动渲染为表格
$(function(){
	{
		var div = $("<div></div>").insertBefore($("#saParams").hide()).width(620).css("float","left");
		window.saParamsGrid=div.grid({
			column:[
				["变量名",80,"text","name",null],
				["描述",160,"text","title"],
				["类型",70,"select","type",[
					{"value":"String","text":"字符串","depict":""},
					{"value":"Boolean","text":"布尔型","depict":""},
					{"value":"Long","text":"长整型","depict":""},
					{"value":"Integer","text":"整型","depict":""},
					{"value":"Short","text":"短整型","depict":""},
					{"value":"Byte","text":"字节","depict":""},
					{"value":"Double","text":"双精度","depict":""},
					{"value":"Float","text":"浮点型","depict":""},
					{"value":"Date","text":"日期型","depict":""},
					{"value":"Map","text":"键值对","depict":""},
					{"value":"List","text":"列表型","depict":""}
				]],
				["列表值",70,"grid","define",[{"text":"项","value":"text"},{"text":"值","value":"value"}]],
				["是否必须",100,"select","notnull",[{"text":"必须","value":"true"},{"text":"非必须","value":"false"}]],
				["默认值",50,"text","defaultValue",null]
			],
			change:function(input){
				$("#saParams").val(saParamsGrid.getValue());
			}
		});
		if(!${empty srvApi.saParams})
			saParamsGrid.setValue(${lz:or(srvApi.saParams,"[]")});
	}
	{
		var div = $("<div></div>").insertBefore($("#saReturnStruct").hide()).width(620).css("float","left");
		window.saReturnStructGrid=div.grid({
			column:[
				["变量名",80,"text","name",null],
				["描述",160,"text","title"],
				["类型",70,"select","type",[
					{"value":"String","text":"字符串","depict":""},
					{"value":"Boolean","text":"布尔型","depict":""},
					{"value":"Number","text":"数字型","depict":""},
					{"value":"Decimal","text":"小数型","depict":""},
					{"value":"Map","text":"键值对(子结构)","depict":""},
					{"value":"List","text":"列表型(子结构)","depict":""}
				]],
				["子结构",70,"grid","define",null],
				["字段名",70,"hidden","field",null]
			],
			change:function(input){
				$("#saReturnStruct").val(saReturnStructGrid.getValue());
			}
		});
		if(!${empty srvApi.saReturnStruct})
			saReturnStructGrid.setValue(${lz:or(srvApi.saReturnStruct,"[]")});
	}
	{
		var div = $("<div></div>").insertBefore($("#saError").hide()).width(620).css("float","left");
		window.saErrorGrid=div.grid({
			column:[
				["错误代码",80,"text","code",null,function(){
					return $("#saNamespace").val().replace(/\./g,"_")+"_error_{index}";
				}],
				["描述",80,"text","title",null,"对应的错误描述"]
			],
			change:function(input){
				$("#saError").val(saErrorGrid.getValue());
			}
		});
		if(!${empty srvApi.saError})
			saErrorGrid.setValue(${lz:or(srvApi.saError,"[]")});
	}
	$("#saJavaCode").height(200);
	$("#saReturnFormat").height(200);
	$("<a href='javascript:void(0);' style='color:blue;margin:0px 10px;'>测试代码语法</a>").appendTo("dl[ref=saJavaCode] dd").click(function(){
		var javaCode = $("#saJavaCode").val();
		var params = eval("("+($.trim($("#saParams").val())==""?"[]":$("#saParams").val())+")");
		var script = $.api("com.ccclubs.helper.SystemHelper","createApiScript")("TestJavaScript",javaCode,params);
		if(script.exception)
			top.Alert("<textarea style='width:900px;height:"+($(window).height()-100)+"px;'>"+script.exception+"\r\n"+script.message+"</textarea>");
		else
			top.Alert("测试OK,你好厉害,纯手写代码测试通过!");
	});
	
	var typemapping = {"String":"String","Boolean":"Boolean","Long":"Number","Integer":"Number","Short":"Number","Byte":"Number","Double":"Decimal","Float":"Decimal","Date":"Number","Map":"Map","List":"List"};
	
	
	$("<a href='javascript:void(0);' style='color:blue;margin:0px 10px;'>测试输出结果</a>").appendTo("dl[ref=saReturnFormat] dd").click(function(){
		var javaCode = $("#saJavaCode").val();
		var jsonFormat = $("#saReturnFormat").val();
		var params = eval("("+($.trim($("#saParams").val())==""?"[]":$("#saParams").val())+")");
		
		var table = top.$("<table id=\"paramInfos\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"10\">");
		if($("#saOauth").val()=="1"){//需要用户授权
			var tr = $("<tr></tr>").appendTo(table);
			var td1 = $("<td align='right'></td>").html("Long oauth:").appendTo(tr);
			var td2 = $("<td></td>").html("授权用户ID").appendTo(tr);
			$("<span style='color:red;'>*</span>").prependTo(td2);
			var input = input = $("<input onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'')\" name=\"oauth\" type=\"text\"/>").prependTo(td2);
			input.attr("notnull","true");
			input.attr("title","授权用户ID");
			input.attr("oauth","true");
		}
		
		for(var o in params){
			var tr = $("<tr></tr>").appendTo(table);
			var td1 = $("<td align='right'></td>").html(params[o].type+" "+params[o].name+":").appendTo(tr);
			var td2 = $("<td></td>").html(params[o].title.substring(0,8)).appendTo(tr);
			if(params[o].notnull=="true")
				$("<span style='color:red;'>*</span>").prependTo(td2);
			var input = null;
			if(params[o].type=="Boolean"){
				input = $("<select name=\""+params[o].name+"\"><option value=''>请选择</option><option value='true'>true</option><option value='false'>false</option></select>").prependTo(td2);
			}else if(params[o].type=="Date"){
				input = $("<input onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" name=\""+params[o].name+"\" type=\"text\"/>").prependTo(td2);
			}else if(params[o].type=="Double"||params[o].type=="Float"){
				input = $("<input onchange=\"onkeyup()\" onkeyup=\"var reg=/^-?(([1-9]\d{0,9})|0)?(\\.)?(\\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''\"  name=\""+params[o].name+"\" type=\"text\"/>").prependTo(td2);
			}else{
				input = $("<input name=\""+params[o].name+"\" type=\"text\"/>").prependTo(td2);
			}
			if(params[o].notnull=="true")
				input.attr("notnull","true");
			input.attr("title",params[o].title);
			input.attr("params","true");
			input.attr("javaType",params[o].type);
		}
		table.dialog({
			title:"请输入必需要的参数",
			modal:true,
			onOk:function(){
				var notnull = false;
				table.find("[notnull]").each(function(){
					if($(this).val()==""){
						top.$.tips(("参数"+$(this).attr("title")+"不能为空"));
						notnull=true;
					}
				});
				if(notnull)return false;
				
				var paramsObjects = [];
				table.find("[params]").each(function(){
					var javaType = $(this).attr("javaType");
					if($(this).val()==""){
						paramsObjects[paramsObjects.length]="";
						return true;
					}
					if(javaType=="String"||javaType=="Date")
						paramsObjects[paramsObjects.length]=$(this).val();
					else if(javaType=="Long"||javaType=="Integer"||javaType=="Double"||javaType=="Float"||javaType=="Short"||javaType=="Byte")
						paramsObjects[paramsObjects.length]=Number($(this).val());
					else if(javaType=="Boolean")
						paramsObjects[paramsObjects.length]=($(this).val()=="true");
				});
				
				var oauth = null;
				if($("#saOauth").val()=="1"){//需要用户授权
					oauth = table.find("[oauth]").val();
				};
				
				var output = $.api("com.ccclubs.helper.SystemHelper","executeApiScript")("TestJavaScript",javaCode,jsonFormat,params,paramsObjects,oauth);
				if(output.exception)
					top.Alert("<textarea style='width:900px;height:"+($(window).height()-100)+"px;'>"+output.exception+"\r\n"+output.message+"</textarea>");
				else
					top.Alert("<textarea style='width:900px;height:"+($(window).height()-100)+"px;'>"+output+"</textarea>");
			}
		});
	});
	
	
	
	
	
	<s:if test="srvApi.saId==null">
	$("<dl style='width:98%;'><dt>全自动化：</dt><dd><button style='height:32px;width:610px;' type='button' class='button'>根据模块自动生成全部配置</button></dd></dl>").insertBefore("dl[ref=saName]").find("button").click(function(){
		$.getInput({
		type:"select",
		items:systemModels,
		selected:window.selectedModel,
		callback:function(value){
			var fieldmap = {};
			var className = value;
			var modelName = null;
			for(var o in systemModels){
				if(systemModels[o]==className){
					modelName = o;
					break;
				}
			}
				
			$.getObject(window.contextPath+"export.do",{depth:2,struct:true,className:value},function(classFields){
				var strHtml = "<div>"
				strHtml+="请选择查询类型：<select id='dataType'><option value='0'>分页</option><option value='1'>单条</option><option value='2'>列表</option></select>";
				strHtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				strHtml+="<table id=\"classTree\" width=\"320\" class=\"table\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\">";
				strHtml+="<thead class='state-header'><tr>";
				strHtml+="<td width='160'>字段名称</td><td flag='query'><a href='javascript:void(0);'>是否查询</a></td><td flag='order'><a href='javascript:void(0);'>是否排序</a></td><td flag='output'><a href='javascript:void(0);'>是否输出</a></td>";
				strHtml+="</tr></thead>";
				strHtml+="<tbody>";
				var oauths = [];
				for(var o in classFields){
					var item = classFields[o];
					//如果一级字段存在需授权的字段，那么就需要授权
					var isOauth = false;
					if(!item.parent && item.relate && oauthclasses[item.relate]){
						oauths[oauths.length]=item.name;
						isOauth = true;
					}
					if(item.relate && excludes[item.relate])
						continue;
					strHtml+="<tr id=\""+item.name+"\" tree=\""+(item.parent||"0")+"\">";
					strHtml+="<td class=\"node\">";
					strHtml+=item.caption;
					strHtml+="</td>";
					strHtml+="<td>";
					if(!item.parent && !isOauth)
						strHtml+="<input class=\"keys\" query='true' range='"+(item.range||"")+"' note='"+(item.note||"")+"' classtype='"+item.type+"' name=\"keys\" "+(fieldmap[item.name] ? "checked='checked'" : "")+" type=\"checkbox\" value=\""+item.name+"\" text=\""+item.caption+"\"/>";
					else
						strHtml+="&nbsp;";
					strHtml+="</td>";
					strHtml+="<td>";
					if(!item.parent && !isOauth)
						strHtml+="<input class=\"keys\" order='true' note='"+(item.note||"")+"' classtype='"+item.type+"' name=\"keys\" "+(fieldmap[item.name] ? "checked='checked'" : "")+" type=\"checkbox\" value=\""+item.name+"\" text=\""+item.caption+"\"/>";
					else
						strHtml+="&nbsp;";
					strHtml+="</td>";
					strHtml+="<td>";
					strHtml+="<input class=\"keys\" output='true' note='"+(item.note||"")+"' classtype='"+item.type+"' name=\"keys\" "+(fieldmap[item.name] ? "checked='checked'" : "")+" type=\"checkbox\" value=\""+item.name+"\" text=\""+item.caption+"\"/>";
					strHtml+="</td>";
					strHtml+="</tr>";
				}
				strHtml+="</tbody>";
				strHtml+="</table>";
				strHtml+="</div>";
				var divTable = top.$(strHtml);
				divTable.find("thead td").click(function(){
					divTable.find("tbody tr .keys["+$(this).attr("flag")+"]:visible").click();
				}).css("text-decoration","underline");
				var dialog = divTable.dialog({
					title:"请选择相关字段配置",
					modal:true,
					onOk:function(){
					
						var dataType = dialog.find("#dataType").val();
						
						if(dataType=="0"){
							saParamsGrid.setValue([
								{"name":"pageNo","title":"第几页","type":"Integer","define":null,"notnull":"false","defaultValue":"1"},
								{"name":"pageSize","title":"每页几条","type":"Integer","define":null,"notnull":"false","defaultValue":"20"}
							]);
						}
						if(dataType=="2"){
							saParamsGrid.setValue([
								{"name":"count","title":"获取几条","type":"Integer","define":null,"notnull":"false","defaultValue":"20"}
							]);
						}
						
						var children = {};
						dialog.find(".keys").each(function(){
							if($(this).is(":checked") && $(this).attr('output')){
								var fullname = $(this).val();
								children[fullname]=$(this);
								if(fullname.indexOf(".")>-1){
									var pName = fullname.substring(0,fullname.lastIndexOf("."));
									if(children[pName]){
										children[pName].attr("children",true);
									}else{
										dialog.find(".keys[value='"+pName+"']").attr("children",true);
									}
								}
							}
						});
						
						
						
						var mapping = {};
						var parammappping={};
						var querys={};
						var orders={};
						var outputs=[];
						dialog.find(".keys").each(function(){
							if($(this).is(":checked")||$(this).attr("children")){
								var fullname = $(this).val();
								var fieldname = fullname.substring(fullname.lastIndexOf(".")+1)
								var simpname = fieldname.replace(/^[^A-Z]+/g,"");
								var forMat = simpname.charAt(0).toLowerCase()+simpname.substring(1);
								var ForMat = simpname.charAt(0).toUpperCase()+simpname.substring(1);
								var FORMAT = "_"+forMat;//simpname.toUpperCase();
								var classtype = $(this).attr("classtype");
								var stype = classtype.substring(classtype.lastIndexOf(".")+1);
							
								if($(this).attr('query')){
									if($(this).attr('range')==""){
										querys[$(this).val()]={
											name:forMat,
											title:$(this).attr("text"),
											type:stype,
											define:null,
											notnull:"false",
											defaultValue:""
										};
										parammappping[fieldname]=forMat;
									}else{
										querys[$(this).val()+(stype=="Date"?"Start":"Min")]={
											name:forMat+(stype=="Date"?"Start":"Min"),
											title:$(this).attr("text")+">=?",
											type:stype,
											define:null,
											notnull:"false",
											defaultValue:""
										};
										parammappping[fieldname+(stype=="Date"?"Start":"Min")]=forMat+(stype=="Date"?"Start":"Min");
										querys[$(this).val()+(stype=="Date"?"End":"Max")]={
											name:forMat+(stype=="Date"?"End":"Max"),
											title:$(this).attr("text")+"<=?",
											type:stype,
											define:null,
											notnull:"false",
											defaultValue:""
										};
										parammappping[fieldname+(stype=="Date"?"End":"Max")]=forMat+(stype=="Date"?"End":"Max");
									}
								}
								if($(this).attr('order')){
									orders[$(this).val()]={
										name:$(this).attr("text"),
										title:forMat
									};
								}
								if($(this).attr('output')){
									var struct = {
										name:forMat,
										title:$(this).attr("text")+" "+$(this).attr("note"),
										type:typemapping[stype],
										define:[],
										field:fieldname
									};
									var struct$ = {
										name:FORMAT,
										title:$(this).attr("text")+"格式化",
										type:"String",
										define:[],
										field:"fmt."+fieldname
									};
									var $struct = mapping["$"+fullname] = {
										name:ForMat,
										title:$(this).attr("text")+"详情"+" "+$(this).attr("note"),
										type:"Map",
										define:[],
										field:"obj."+fieldname
									};
									if(fullname.indexOf(".")==-1){
										if($(this).is(":checked")){
											outputs[outputs.length]=struct;
										}
										if($(this).attr("children"))
											outputs[outputs.length]=$struct;
									}else{
										var parentStruct = mapping["$"+fullname.substring(0,fullname.lastIndexOf("."))];
										if($(this).is(":checked")){
											parentStruct.define[parentStruct.define.length]=struct;
										}
										if($(this).attr("children"))
											parentStruct.define[parentStruct.define.length]=$struct;
									}
								}
							}
						});
						//参数
						saParamsGrid.setValue(querys);
						
						//返回结构
						if(dataType=="0"){//如果是分页，则把返回结构转成分页
							var structs=[];
							structs[structs.length] = {name:"index",title:"当前页",type:"Number",define:[],field:null};
							structs[structs.length] = {name:"count",title:"总记录数",type:"Number",define:[],field:null};
							structs[structs.length] = {name:"total",title:"总页数",type:"Number",define:[],field:null};
							structs[structs.length] = {name:"size",title:"每页记录数",type:"Number",define:[],field:null};
							structs[structs.length] = {name:"next",title:"是否有下一页",type:"Boolean",define:[],field:null};
							structs[structs.length] = {name:"prev",title:"是否有上一页",type:"Boolean",define:[],field:null};
							structs[structs.length] = {name:"result",title:"结果集",type:"List",define:outputs,field:"result"};
							saReturnStructGrid.setValue(structs);
							outputs = structs;
						}else{
							saReturnStructGrid.setValue(outputs);
						}
						
						
						//排序
						var strOrders="";
						for(var o in orders){
							strOrders+=orders[o].name+":"+orders[o].title+",";
						}
						saParamsGrid.setValue([
							{"name":"desc","title":"倒序字段 - "+strOrders,"type":"String","define":null,"notnull":"false","defaultValue":""},
							{"name":"asc","title":"升序字段 - "+strOrders,"type":"String","define":null,"notnull":"false","defaultValue":""}
						]);
						
						
						//对象简称
						var actionName = className.substring(className.lastIndexOf(".")+1).replace(/^[A-Z][^A-Z]+/g,"").toLowerCase();
						var namespace = className.split(".")[0]+"."+className.split(".")[1]+"."+actionName;
						
						//授权
						$("#saOauth").val(oauths.length>0?"1":"0");
						//api名称
						if(dataType=="0"){//分页
							$("#saName").val("获取"+modelName+"分页");
						}else if(dataType=="1"){//单条
							$("#saName").val("获取单个"+modelName+"");
						}else{//列表
							$("#saName").val("获取"+modelName+"列表");
						}
						//名字空间
						$("#saNamespace").val(namespace+"."+["page","get","list"][Number(dataType)]);
						//是否开放
						$("#saOpen").val("1");
						
						
						var classSampleName = className.substring(className.lastIndexOf(".")+1);
						var variable = null;
						//java脚本代码
						var javaCode = "";
						javaCode+=classSampleName+".M map="+classSampleName+".m()";
						for(var o in parammappping){
							javaCode+="."+o+"("+parammappping[o]+")";
						}
						for(var o in oauths)
							javaCode+="."+oauths[o]+"(oauth)";
						javaCode+=";\r\n";
						if(dataType=="0"){//分页
							javaCode+="Page<"+classSampleName+"> page=map.page($.or(pageNo,1),$.or(pageSize,20));\r\n";
							variable="page";
						}else if(dataType=="1"){//单条
							javaCode+=""+classSampleName+" "+actionName+"=map.get();\r\n";
							variable = actionName;
						}else{//列表
							javaCode+="List<"+classSampleName+"> list=map.list(count);\r\n";
							variable = "list";
						}
						$("#saJavaCode").val(javaCode);
						
						
						//格式化模板代码
						var structs = outputs;
						
						if(!window.getFormat){
							//type:数据类型   0表示列表   1表示对象
							window.getFormat = function(type,variableName,struct,step){
								//空白符
								var blankcode = "";
								for(var i=0;i<step;i++)
									blankcode+="	";
									
								var code="";
								if(type=="0"){
									code+="[";
									code+="#foreach($item"+step+" in $"+variableName+")#if($"+"{velocityCount}>1),#end";
									variableName="item"+step;
								}
									
								
								code+="{\r\n";
								var bMap = false;
								for(var o in struct){
									code+=bMap?"":("	"+blankcode);
									code+=(o>0?",":"");
									if(struct[o].type=="Map"){
										bMap = true;
										code+="\""+struct[o].name+"\":"+getFormat("1",variableName+"."+(struct[o].field||struct[o].name),struct[o].define,step+1);
									}else if(struct[o].type=="List"){
										bMap = true;
										code+="\""+struct[o].name+"\":"+getFormat("0",variableName+"."+(struct[o].field||struct[o].name),struct[o].define,step+1);
									}else{
										bMap = false;
										code+="\""+struct[o].name+"\":$"+"{lz.json($"+variableName+"."+(struct[o].field||struct[o].name)+")}";
										code+="\r\n";
									}
								}
								code+=blankcode+"}";
								
								if(type=="0"){
									code+="#end";
									code+="]\r\n";
								}								
								
								return code;
							}
						}
						formatCode = getFormat(dataType==2?0:1,variable,structs,0);						
						$("#saReturnFormat").val(formatCode);
					}
				});
				dialog.find("#classTree").treegrid({expand:true});
				dialog.find("#classTree").parent().parent().width(dialog.find("#classTree").width()+25);
				dialog.find("#classTree").parent().parent().height($(window).height()/3*2);
				dialog.data("onSize")();
			});	
		}});
	});
	</s:if>
	
	
	
});
</script>
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
	$("#apiForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.saId==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saName==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saName":function(el){
			if(jQuery.trim(el.value)=="")
				return "API名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "API名称最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("api_query.do",{exists:true,saName:el.value});
				if(exists && exists.length>0){
					if(${srvApi.saId==null} || exists[0].value!=$("#saId").val())
						return "API名称已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saNamespace==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saNamespace":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称空间不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称空间最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("api_query.do",{exists:true,saNamespace:el.value});
				if(exists && exists.length>0){
					if(${srvApi.saId==null} || exists[0].value!=$("#saId").val())
						return "名称空间已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saType==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择接口分类";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saOpen==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saOpen":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择是否开放";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saDescript==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saDescript":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "接口说明最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saParams==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saParams":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "参数结构最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saOauth==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saOauth":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择需要用户授权";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saReturnStruct==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saReturnStruct":function(el){
			if(jQuery.trim(el.value)=="")
				return "返回结构设计不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "返回结构设计最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saJavaCode==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saJavaCode":function(el){
			if(jQuery.trim(el.value)=="")
				return "脚本代码不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "脚本代码最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saReturnFormat==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saReturnFormat":function(el){
			if(jQuery.trim(el.value)=="")
				return "返回格式化不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "返回格式化最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saError==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saError":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "错误代码最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saEditor==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saAddTime==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.saStatus==true">
		${lz:set("haveEditable",true)}
		,"srvApi.saStatus":function(el){
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
	<form class="form editform" ref="SrvApi" id="apiForm" name="apiForm" action="${empty CTRL.action?"api_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写API接口相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvApiToken}" name="srvApiToken" id="srvApiToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvApi.saId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$saId和after$saId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saId==true">
	${before$saId}
	<dl class="saId ${CTRL.e.saId?"hidden":""}" major  ref="saId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.saId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvApi$saId!=null">${srvApi$saId}</s:if><s:else>
			<input type="hidden" value="${srvApi.saId}" name="srvApi.saId" id="saId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saId">${srvApi.saId}</textarea>
		 		<span>
		 	
			 ${srvApi.saId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saId}
	</s:if>
	
	${lz:set("注释","*****************API名称字段的输入框代码*****************")}
	${lz:set("注释","before$saName和after$saName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saName==true">
	${before$saName}
	<dl class="saName " major  ref="saName" >
		<dt>API名称:</dt>
		<s:if test="#request.CTRL.e.saName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvApi$saName!=null">${srvApi$saName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvApi.saName" id="saName"  value="${srvApi.saName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>中文描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****API名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saName">${srvApi.saName}</textarea>
		 		<span>
		 	
			 ${srvApi.saName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saName}
	</s:if>
	
	${lz:set("注释","*****************名称空间字段的输入框代码*****************")}
	${lz:set("注释","before$saNamespace和after$saNamespace变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saNamespace==true">
	${before$saNamespace}
	<dl class="saNamespace " major  ref="saNamespace" >
		<dt>名称空间:</dt>
		<s:if test="#request.CTRL.e.saNamespace==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvApi$saNamespace!=null">${srvApi$saNamespace}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvApi.saNamespace" id="saNamespace"  value="${srvApi.saNamespace}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>格式如:com.xxxx.xxx</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称空间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saNamespace">${srvApi.saNamespace}</textarea>
		 		<span>
		 	
			 ${srvApi.saNamespace$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saNamespace}
	</s:if>
	
	${lz:set("注释","*****************接口分类字段的输入框代码*****************")}
	${lz:set("注释","before$saType和after$saType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saType==true">
	${before$saType}
	<dl class="saType " major  ref="saType" >
		<dt>接口分类:</dt>
		<s:if test="#request.CTRL.e.saType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvApi$saType!=null">${srvApi$saType}</s:if><s:else>
		 	<select class="narrow" id="saType" name="srvApi.saType">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("API接口分类","api_type"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${srvApi.saType==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#saType').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#saType').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("api_type").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#saType');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API接口的接口分类</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接口分类字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saType">${srvApi.saType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${srvApi.saType}',{ctrl:{editable:false,visible:true}})">
			 ${srvApi.saType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saType}
	</s:if>
	
	${lz:set("注释","*****************是否开放字段的输入框代码*****************")}
	${lz:set("注释","before$saOpen和after$saOpen变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saOpen==true">
	${before$saOpen}
	<dl class="saOpen " major  ref="saOpen" >
		<dt>是否开放:</dt>
		<s:if test="#request.CTRL.e.saOpen==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvApi$saOpen!=null">${srvApi$saOpen}</s:if><s:else>
		 	<select class="narrow" id="saOpen" name="srvApi.saOpen">
		 		<option value="">请选择</option>
				<option value="1" ${srvApi.saOpen==1?"selected":""}>开放</option>
				<option value="0" ${srvApi.saOpen==0?"selected":""}>非开放</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API接口的是否开放</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****是否开放字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saOpen">${srvApi.saOpen}</textarea>
		 		<span>
		 	
			 ${srvApi.saOpen$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saOpen}
	</s:if>
	
	${lz:set("注释","*****************接口说明字段的输入框代码*****************")}
	${lz:set("注释","before$saDescript和after$saDescript变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saDescript==true">
	${before$saDescript}
	<dl class="saDescript " minor  ref="saDescript" style="width:98%;">
		<dt>接口说明:</dt>
		<s:if test="#request.CTRL.e.saDescript==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvApi$saDescript!=null">${srvApi$saDescript}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="srvApi.saDescript" id="saDescript"  value="${srvApi.saDescript}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入API接口的接口说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接口说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="saDescript">${srvApi.saDescript}</textarea>
		 		<span>
		 	
			 ${srvApi.saDescript$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saDescript}
	</s:if>
	
	${lz:set("注释","*****************参数结构字段的输入框代码*****************")}
	${lz:set("注释","before$saParams和after$saParams变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saParams==true">
	${before$saParams}
	<dl class="saParams " minor  ref="saParams" style="width:98%;">
		<dt>参数结构:</dt>
		<s:if test="#request.CTRL.e.saParams==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvApi$saParams!=null">${srvApi$saParams}</s:if><s:else>
		 	<textarea class="input wide"  id="saParams" name="srvApi.saParams" rows="5">${srvApi.saParams}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入API接口的参数结构</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****参数结构字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvApi.saParams$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saParams}
	</s:if>
	
	${lz:set("注释","*****************需要用户授权字段的输入框代码*****************")}
	${lz:set("注释","before$saOauth和after$saOauth变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saOauth==true">
	${before$saOauth}
	<dl class="saOauth " major  ref="saOauth" >
		<dt>需要用户授权:</dt>
		<s:if test="#request.CTRL.e.saOauth==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvApi$saOauth!=null">${srvApi$saOauth}</s:if><s:else>
		 	<select class="narrow" id="saOauth" name="srvApi.saOauth">
		 		<option value="">请选择</option>
				<option value="0" ${srvApi.saOauth==0?"selected":""}>无需授权</option>
				<option value="1" ${srvApi.saOauth==1?"selected":""}>需要授权</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API接口的需要用户授权</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****需要用户授权字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saOauth">${srvApi.saOauth}</textarea>
		 		<span>
		 	
			 ${srvApi.saOauth$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saOauth}
	</s:if>
	
	${lz:set("注释","*****************返回结构设计字段的输入框代码*****************")}
	${lz:set("注释","before$saReturnStruct和after$saReturnStruct变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saReturnStruct==true">
	${before$saReturnStruct}
	<dl class="saReturnStruct " major  ref="saReturnStruct" style="width:98%;">
		<dt>返回结构设计:</dt>
		<s:if test="#request.CTRL.e.saReturnStruct==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvApi$saReturnStruct!=null">${srvApi$saReturnStruct}</s:if><s:else>
		 	<textarea class="input wide"  id="saReturnStruct" name="srvApi.saReturnStruct" rows="5">${srvApi.saReturnStruct}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入API接口的返回结构设计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****返回结构设计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvApi.saReturnStruct$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saReturnStruct}
	</s:if>
	
	${lz:set("注释","*****************脚本代码字段的输入框代码*****************")}
	${lz:set("注释","before$saJavaCode和after$saJavaCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saJavaCode==true">
	${before$saJavaCode}
	<dl class="saJavaCode " major  ref="saJavaCode" style="width:98%;">
		<dt>脚本代码:</dt>
		<s:if test="#request.CTRL.e.saJavaCode==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvApi$saJavaCode!=null">${srvApi$saJavaCode}</s:if><s:else>
		 	<textarea class="input wide"  id="saJavaCode" name="srvApi.saJavaCode" rows="5">${srvApi.saJavaCode}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****脚本代码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvApi.saJavaCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saJavaCode}
	</s:if>
	
	${lz:set("注释","*****************返回格式化字段的输入框代码*****************")}
	${lz:set("注释","before$saReturnFormat和after$saReturnFormat变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saReturnFormat==true">
	${before$saReturnFormat}
	<dl class="saReturnFormat " major  ref="saReturnFormat" style="width:98%;">
		<dt>返回格式化:</dt>
		<s:if test="#request.CTRL.e.saReturnFormat==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvApi$saReturnFormat!=null">${srvApi$saReturnFormat}</s:if><s:else>
		 	<textarea class="input wide"  id="saReturnFormat" name="srvApi.saReturnFormat" rows="5">${srvApi.saReturnFormat}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****返回格式化字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvApi.saReturnFormat$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saReturnFormat}
	</s:if>
	
	${lz:set("注释","*****************错误代码字段的输入框代码*****************")}
	${lz:set("注释","before$saError和after$saError变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saError==true">
	${before$saError}
	<dl class="saError " minor  ref="saError" style="width:98%;">
		<dt>错误代码:</dt>
		<s:if test="#request.CTRL.e.saError==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvApi$saError!=null">${srvApi$saError}</s:if><s:else>
		 	<textarea class="input wide"  id="saError" name="srvApi.saError" rows="5">${srvApi.saError}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入API接口的错误代码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****错误代码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvApi.saError$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saError}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$saEditor和after$saEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saEditor==true">
	${before$saEditor}
	<dl class="saEditor " major  ref="saEditor" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.saEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.srvApi$saEditor!=null">${srvApi$saEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="saEditor" name="srvApi.saEditor">
		 				<option selected value="${srvApi.saEditor}">
		 					${get:SrvUser(srvApi.saEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#saEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#saEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saEditor">${srvApi.saEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${srvApi.saEditor}',{ctrl:{editable:false,visible:true}})">
			 ${srvApi.saEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$saUpdateTime和after$saUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saUpdateTime==true">
	${before$saUpdateTime}
	<dl class="saUpdateTime " major  ref="saUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.saUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvApi$saUpdateTime!=null">${srvApi$saUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvApi.saUpdateTime" id="saUpdateTime"  value="<s:date name="srvApi.saUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API接口的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saUpdateTime">${srvApi.saUpdateTime}</textarea>
		 		<span>
		 	
			 ${srvApi.saUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$saAddTime和after$saAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saAddTime==true">
	${before$saAddTime}
	<dl class="saAddTime " major  ref="saAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.saAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvApi$saAddTime!=null">${srvApi$saAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvApi.saAddTime" id="saAddTime"  value="<s:date name="srvApi.saAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API接口的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saAddTime">${srvApi.saAddTime}</textarea>
		 		<span>
		 	
			 ${srvApi.saAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$saStatus和after$saStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.saStatus==true">
	${before$saStatus}
	<dl class="saStatus " major  ref="saStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.saStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvApi$saStatus!=null">${srvApi$saStatus}</s:if><s:else>
		 	<select class="narrow" id="saStatus" name="srvApi.saStatus">
		 		<option value="">请选择</option>
				<option value="0" ${srvApi.saStatus==0?"selected":""}>测试中</option>
				<option value="1" ${srvApi.saStatus==1?"selected":""}>正常</option>
				<option value="2" ${srvApi.saStatus==2?"selected":""}>禁用</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API接口的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="saStatus">${srvApi.saStatus}</textarea>
		 		<span>
		 	
			 ${srvApi.saStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$saStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvApi.saId}" name="srvApi.saId" id="saId" />
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