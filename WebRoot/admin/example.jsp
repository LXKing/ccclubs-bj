<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

<body>

<script>

$(function(){
	$.tips("开发者示例页，前端风格请参照此页面html原代码，以保持系统风格统一",2000);	
})

</script>

<div class="title">内容管理 - 数据列表</div>

<div class="line"></div>

<div class="content">	
	<form class="query" method="post" action="#" id="queryForm">
		<dl>
			<dt>标题:</dt>
			<dd>
				<input type="text" value="" id="naTitle" name="naTitle" maxlength="32" size="16" class="input">
			</dd>
		</dl>
		<dl>
			<dt>关键字:</dt>
			<dd>
				<input type="text" value="" id="naKey" name="naKey" maxlength="32" size="16" class="input">
			</dd>
		</dl>
		<dl>
			<dt>所属分类:</dt>
			<dd>
			<select style="width:128px;" name="naSort" id="naSort">
				<option value="">全部</option>				
				<option value="1">选项</option>				
				<option value="2">选项</option>				
				<option value="3">选项</option>				
			</select>
			</dd>
		</dl>	 
		<dl>
			<dt>添加时间:</dt>
			<dd>
				<input type="text" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="naAddTimeStart" name="naAddTimeStart" maxlength="19" size="14" class="input">
				起</dd></dl><dl class="query"><dt>添加时间:</dt><dd>
				<input type="text" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="naAddTimeEnd" name="naAddTimeEnd" maxlength="19" size="14" class="input">
				止
			</dd>
		</dl>
		<dl>
			<dt>状态:</dt>
			<dd>
			<select style="width:128px;" name="naStatus" id="naStatus">
				<option value="">全部</option>
				<option value="1">有效</option>
				<option value="0">待审核</option>
				<option value="2">无效</option>
			</select>
			</dd>
		</dl>
		<div class="line"></div>
		<center>
			<button class="button" type="submit">查询</button>		
			<button class="button" onclick="clearForm()" type="button">重置</button>
		</center>
	</form>	
</div>

<div class="content">
	<div class="toolbar state-default">
		<button class="del" onclick="DelSel()" type="button">删除</button>
		<button class="detail" onclick="DetailsSel()" type="button">详情</button>
		<button class="edit" onclick="EditSel();" type="button">修改</button>
		<button class="add" onclick="window.location='edit.do';" type="button">添加</button>
		<button class="all" type="button" onclick="$('.check').attr('checked',true)">全选</button>
		<button class="invert" type="button" onclick="$('.check').click();">反选</button>
	</div>
	<table class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		    <td width="50" align="center">选择</td>
			<td width="50" align="center">编号</td>
			<td>用户名</td>
			<td>操作时间</td>
			<td width="280">操作日志</td>
			<td>IP地址</td>
			<td>邮箱</td>
		  </tr>
		</thead>
		<tbody>
		  <tr>
		    <td align="center">
		      <input class="check" type="checkbox" name="checkbox" value="0" />
		    </td>
			<td align="center">01</td>
			<td>admin</td>
			<td>2011-08-10 11:31</td>
			<td>删除系统用户</td>
			<td>127.0.0.1</td>
			<td>admin@qq.com</td>
		  </tr>
		  <tr>
		   <td align="center">
		      <input class="check" type="checkbox" name="checkbox" value="1" />
		    </td>
			<td align="center">02</td>
			<td>user</td>
			<td>2011-08-10 11:31</td>
			<td>修改密码</td>
			<td>127.0.0.1</td>
			<td>user@163.com</td>
		  </tr>
		  <tr>
		    <td align="center">
		      <input class="check" type="checkbox" name="checkbox" value="2" />
		    </td>
			<td align="center">03</td>
			<td>super</td>
			<td>2011-08-10 11:31</td>
			<td>添加了一个文章</td>
			<td>127.0.0.1</td>
			<td>super@ideacome.com</td>
		  </tr>
		  <tr>
		    <td align="center">
		      <input class="check" type="checkbox" name="checkbox" value="3" />
		    </td>
			<td align="center">04</td>
			<td>manager</td>
			<td>2011-08-10 11:31</td>
			<td>设置用户登录权限设置用户登录权限设置用户登录权限设置用户登录权限</td>
			<td>127.0.0.1</td>
			<td>manager@sina.com</td>
		  </tr>
		  <tr>
		    <td align="center">
		      <input class="check" type="checkbox" name="checkbox" value="4" />
		    </td>
			<td align="center">05</td>
			<td>Mr Liu </td>
			<td>2011-08-10 11:31</td>
			<td>修改文章</td>
			<td>127.0.0.1</td>
			<td>MrLiu@139.com</td>
		  </tr>	  
		</tbody>
	</table>
</div>

<ul class="pager">		  
	<li><a href="#">首页</a></li>
	<li class="focus">1</li>
	<li><a href="#">2</a></li>	
	<li><a href="#">3</a></li>
	<li><a href="#">4</a></li>
	<li><a href="#">下一页</a></li>		
	<li><a href="#">未页</a></li>	
	<li class="text">共17页/261条记录</li>
	<li class="text">
		<input class="input" style="width:32px; height:16px; text-align:center;" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" value="1" name="page" id="page">
	</li>
	<li onclick="window.location='?page='+($('#page').val()-1)+'&amp;mm=0.47693879647264603&amp;limitsid=15-531A40844027039503C3DA0B09C0F244';">转到</li>
</ul>


<br /><br /><br />

<script>
function DelSel(){
	var checker=$(".check:checked");
	if(checker.length==0){
		$.tips("您未选中任何要删除的记录，请至少选择一项.");
		return;
	}
	var dialog=$.dialog({
			html:"确定要删除所选记录吗",
			title:"批量删除记录",
			onOk:function(){
				var ids = "";
				checker.each(function(i){
					if(ids!="")
						ids+=",";
					ids+=$(this).val();
				});
				//window.location='del.do?ids='+ids;
				alert(ids);
			}
	});
}
function DetailsSel(){
	var checker=$(".check:checked");
	if(checker.length!=1){
		$.tips("请选择一个需要查看的记录（单选）");
		return;
	}
	$.paper({width:700,url:'details.do'});
}
function EditSel(){
	var checker=$(".check:checked");
	if(checker.length!=1){
		$.tips("请选择一个需要修改的记录（单选）");
		return;
	}
	window.location='edit.do';
}
</script>


<div id="grid-panel" class="content" style="height:300px;"></div>

<br />

<script>
	var addOption="";//添加地方名称的链接:当如果该用户有添加地方名称的权限

	addOption="[<a href='javascript:AddPlace()' style='text-decoration:underline'>添加地方名称</a>]";

	// 创建地方名称列表表头
	var placeGrid=$("#grid-panel").grid({
		column:[
		["编号",172],
		["名称",200],
		["所属",72],
			[addOption,200]
		],nodeAt:1
	});
	
	//填充地方名称列表数据
	var places=[];
	var option="";
	
	option="";	
	option+="[<a href=\"javascript:AddPlace('8')\">添加</a>]";	
	option+="[<a href=\"javascript:EditPlace('8')\">修改</a>]";	
	option+="[<a href=\"javascript:DetailsPlace('8')\">详情</a>]";
	places[8]=(places[0]||placeGrid).add(
		{data:["000008","江苏","",option],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('2')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('2')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('2')\">详情</a>]";
	places[2]=(places[0]||placeGrid).add(
		{data:[
								
					 "000002",				 	
					 "上海",				 	
					 "",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('1')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('1')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('1')\">详情</a>]";
	places[1]=(places[0]||placeGrid).add(
		{data:[
								
					 "000001",				 	
					 "浙江",				 	
					 "",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('10')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('10')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('10')\">详情</a>]";
	places[10]=(places[8]||placeGrid).add(
		{data:[
								
					 "000010",				 	
					 "南京",				 	
					 "江苏",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('9')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('9')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('9')\">详情</a>]";
	places[9]=(places[8]||placeGrid).add(
		{data:[
								
					 "000009",				 	
					 "苏州",				 	
					 "江苏",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('6')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('6')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('6')\">详情</a>]";
	places[6]=(places[8]||placeGrid).add(
		{data:[
								
					 "000006",				 	
					 "常熟",				 	
					 "江苏",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('7')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('7')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('7')\">详情</a>]";
	places[7]=(places[2]||placeGrid).add(
		{data:[
								
					 "000007",				 	
					 "上海市",				 	
					 "上海",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('18')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('18')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('18')\">详情</a>]";
	places[18]=(places[1]||placeGrid).add(
		{data:[
								
					 "000018",				 	
					 "丽水",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('17')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('17')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('17')\">详情</a>]";
	places[17]=(places[1]||placeGrid).add(
		{data:[
								
					 "000017",				 	
					 "台州",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('16')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('16')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('16')\">详情</a>]";
	places[16]=(places[1]||placeGrid).add(
		{data:[
								
					 "000016",				 	
					 "舟山",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('15')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('15')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('15')\">详情</a>]";
	places[15]=(places[1]||placeGrid).add(
		{data:[
								
					 "000015",				 	
					 "衢州",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('14')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('14')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('14')\">详情</a>]";
	places[14]=(places[1]||placeGrid).add(
		{data:[
								
					 "000014",				 	
					 "绍兴",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('13')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('13')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('13')\">详情</a>]";
	places[13]=(places[1]||placeGrid).add(
		{data:[
								
					 "000013",				 	
					 "湖州",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('12')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('12')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('12')\">详情</a>]";
	places[12]=(places[1]||placeGrid).add(
		{data:[
								
					 "000012",				 	
					 "嘉兴",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('11')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('11')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('11')\">详情</a>]";
	places[11]=(places[1]||placeGrid).add(
		{data:[
								
					 "000011",				 	
					 "宁波",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('5')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('5')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('5')\">详情</a>]";
	places[5]=(places[1]||placeGrid).add(
		{data:[
								
					 "000005",				 	
					 "金华",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('4')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('4')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('4')\">详情</a>]";
	places[4]=(places[1]||placeGrid).add(
		{data:[
								
					 "000004",				 	
					 "温州",				 	
					 "浙江",
				option
		],expand:true}
	)

	option="";
	
	option+="[<a href=\"javascript:AddPlace('3')\">添加</a>]";
	
	
	option+="[<a href=\"javascript:EditPlace('3')\">修改</a>]";
	
	
	option+="[<a href=\"javascript:DetailsPlace('3')\">详情</a>]";
	places[3]=(places[1]||placeGrid).add(
		{data:[
								
					 "000003",				 	
					 "杭州",				 	
					 "浙江",
				option
		],expand:true}
	)
</script>


</body>
</html>
