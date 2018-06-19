<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆照片管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

	<script type="text/javascript" src="${basePath}/admin/js/lazy3q.template.js"></script>

</head> 

<style>
#navigator{width:200px;float:left;height:100%;overflow:scroll;overflow-x:hidden;}
#datelist{width:200px;float:left;height:100%;overflow:scroll;overflow-x:hidden;}
body,html{overflow:hidden;}
.table tbody td{cursor:pointer;}
.table tbody td a{color:#000000;}
.table tbody td .uploaded{color:red;}
.table tbody .focus .select{background:#0000ff;color:#ffffff;}
.table tbody .select td{background:#0000ff;color:#ffffff;}
.table tbody .select td a{color:#ffffff;}
.table tbody .clicked td{background:#0000ff;color:#ffffff;}
.table tbody .clicked td a{color:#ffffff;}
#photolist{height:100%;overflow:scroll;overflow-x:hidden;padding-top:60px;}
#photolist .pic{display:block;width:100px;height:100px;margin:10px;float:left;background:#ffffff;border:1px solid #dddddd;}
#photolist .pic img{width:80px;height:80px;margin:10px;border:0px;}
#mapControl{position:absolute; top:10px; left:420px; z-index:10;margin:5px;}
#mapControl div{ height:100%; line-height:32px; float:left; padding:0px 10px;font-weight:bold;}
#mapControl input{margin:2px;width:100px;}
#mapControl button{background:url(/admin/images/icons/search.png) center no-repeat; border:0px;width:28px;28px;margin:5px 0px;cursor:pointer;}
</style>

<body scroll="no" style="overflow:hidden;">

<div id="navigator">
	<table width="100%" id="outlets-cars" class="table" border="0" cellspacing="1" cellpadding="0">
		<tbody>
			<s:iterator value="#request.outlets" id="outlet">
				<tr id="o${outlet.csoId}" expand="true" tree="0">
					<td class="node">
						<span class="icon tree-less" style="margin-left:0px;"></span>
						${outlet.csoName}
					<br /></td>
				</tr>
				<s:iterator value="#request.cars" id="car">
					<s:if test="#outlet.csoId==#car.cscOutlets">
					<tr class="carer" id="${car.cscNumber}" tree="o${car.cscOutlets}">
						<td class="node" cscpCar="${car.cscId}">
							<span class="icon" style="visibility:hidden;"></span>
							<a class="link" href="javascript:void(0);">${car.cscNumber}</a>
							<a flag="${car.cscId}" class="upload" href="javascript:void(0);">上传</a>
						</td>
					</tr>
					</s:if>
				</s:iterator>
			</s:iterator>
		</tbody>
	</table>
</div>
<div id="datelist">
	<table width="100%" class="table" border="0" cellspacing="1" cellpadding="0">
		<tbody>
			<s:iterator value="#request.dates" id="date">
			<tr>
				<td cscpDate="${date}">
					${date}
					<a flag="${date}" class="upload" href="javascript:void(0);">上传</a>
                </td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
</div>
<div id="photolist">

</div>


<div id='mapControl' class='state-header'>
	<div>车辆搜索:</div>
	<input class='input' type='text' id=''/>
	<button type='button'></button>
</div>

<script>

$(function(){
	top.showTips("${tips.value}");
	
	var input = $("#mapControl").find("input");
	input.keyup(function(){		
		search();	
		var strIn=$(this).val().toUpperCase();
		if($.trim(strIn)=="")
			return;
		search(strIn);		
	});
	
});


function search(strIn){
	if(!strIn){
		$("#navigator tr[tree]").find(".link").css("color","").css("font-weight","");
	}else{
		var trs = $("#navigator tr[tree]:contains('"+strIn+"')");
		trs.find(".link").css("color","red").css("font-weight","bold");
		var top = $("#navigator").scrollTop()+$(trs.get(0)).offset().top-100;
		if(trs.size()>0)
			$("#navigator").scrollTop(top);
	}
}



function href(url,params){
	top.$.showModalDialog({url:url},params,window);
}


$(function(){
	setTimeout(function(){
		$("#outlets-cars").treegrid({});
	},1000);	
	//当点击车辆或日期时，加载相关的图片列表
	$("[cscpCar],[cscpDate]").click(function(){
		var bSelected = $(this).hasClass("select");
		$(this).parents("table").find("td").removeClass("select");
		if(!bSelected)
			$(this).addClass("select");
		loadPhotos($("#navigator .select").attr("cscpCar"),$("#datelist .select").attr("cscpDate"));
	});
	//当点击车辆时，读取当前车辆的图片上传情况
	$("[cscpCar]").click(function(){
		$.getObject("${basePath}admin/systematic/carphoto_query.do",{
			cscpCar:$(this).attr("cscpCar"),
			object:true
		},function(datas){
			$("#datelist .upload").removeClass("uploaded").html("未上传");
			for(var o in datas){
				var sDate = $.formatDate("yyyy-MM-dd",new Date(datas[o].object.cscpDate));
				$("#datelist .upload:[flag="+sDate+"]").addClass("uploaded").html("[已上传]");
			}
		});
	});
	//当点击日期时，读取当前日期的图片上传情况
	$("[cscpDate]").click(function(){
		var date = $(this).attr("cscpDate");
		$.getObject("${basePath}admin/systematic/carphoto_query.do",{
			cscpDate:date,
			object:true
		},function(datas){
			$("#navigator .upload").removeClass("uploaded").html("未上传");
			for(var o in datas){
				$("#navigator .upload:[flag="+datas[o].object.cscpCar+"]").addClass("uploaded").html("[已上传]");
			}
		});
	});
	//当点击上传时
	$(".upload").click(function(){
		var params = {
			cscpCar:$(this).parent().attr("cscpCar") || $("#navigator .select").attr("cscpCar"),
			cscpDate:$(this).parent().attr("cscpDate") || $("#datelist .select").attr("cscpDate")
		};
		var id;
		if(params.cscpCar && params.cscpDate){
			var existPhotos = $.getObject("${basePath}admin/systematic/carphoto_query.do",params);
			if(existPhotos && existPhotos.length==1)
				id = existPhotos[0].value;
		}
		addPhoto(id,params);
		return false;
	});
});

//加载数据
function loadPhotos(car,date){
	$("#photolist").empty();
	$.getObject("${basePath}admin/systematic/carphoto_query.do",{
		size:-1,
		object:true,
		cscpCar:car,
		cscpDate:date
	},function(photos){
		var strHtml = "";
		for(var o in photos){
			var sImages = photos[o].object.cscpImages;
			if($.trim(sImages)=="")
				continue;
			var array = sImages.split(",");
			for(var p in array)
				strHtml+=("<a href='javascript:DetailsCarphoto("+photos[o].object.cscpId+")' class='pic'><img src='"+array[p]+".pic'/></a>");
		}
		$("#photolist").html(strHtml);
	});
}

	/**
	* 上传车辆图片
	**/
	function addPhoto(id,params){
		var url = "${basePath}admin/systematic/carphoto_edit.do";
		var params = {entrypoint:"${entrypoint}",id:(id?id:""),
			ctrl:{
				title:'上传车辆图片',
				fields:{
					cscpDate:{
						defaultValue:params.cscpDate?($.dateFormat(params.cscpDate,"yyyy-MM-dd").getTime()):null
					},
					cscpCar:{
						defaultValue:params.cscpCar
					}
				}
			}
		};
		href(url,params);
	}

	/**
	* 显示车辆照片详情
	**/
	function DetailsCarphoto(id)
	{
		var url = "${basePath}admin/systematic/carphoto_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆照片详情",editable:false,visible:true}};
		href(url,params);
	}

</script>


</body>
</html>
