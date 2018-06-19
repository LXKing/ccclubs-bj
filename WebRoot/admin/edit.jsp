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
	<!-- 感谢My97DatePicker出品的在线日历控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>

<body>
<script>

$(function(){
	//构造编辑页naContent为html编辑控件
	 KE.init({
		id : 'naContent',
		width : '600px',
		height : '300px' , 
		imageUploadJson : 'attached/upload.jsp?context=true'
	 });
	 KE.create("naContent");
	 
	 $(".form").form({
	 	"naTitle":function(el){
			if(el.value!="我爱你")
				return "字段只能输入我爱你,^_^";			
		},
		"naPriority":function(el){
			if(el.value!=1)
				return "优先级只能选择低,^_^";
		}		
	 });
	 
});



</script>

<div class="title">内容管理 - 添加/编辑内容</div>

<div class="line"></div>

<form class="form" id="articleForm" name="articleForm" action="article_save.do" method="post" enctype="multipart/form-data" sizcache="13" sizset="2">
<div class="head"></div>
<div class="body">
	<div class="content">
		<div class="prompt">
			温馨提示：添加编辑内容提示信息，<span class="extrude">"*" 为必填选项。</span>
		</div>
	
		<dl style="width:95%;">
			<dt>字段:</dt>
			<dd>
				<input type="text" class="input" size="40"  maxlength="20" name="naTitle" id="naTitle"  value=""/>
				<b>*</b>
				<em>请输入字段内容请输入字段内容请输入字段内容,并且遵守法律法规定</em>	
			</dd>
		</dl>
		
		<dl >
			<dt>所属分类:</dt>
			<dd>
				<select id="naSort" name="naSort" style="width:60%;">
					<option value="">请选择</option>					
				</select>
				<b>*</b>
				<em>请选择所属分类,并且遵守法律法规定,这儿可以输入一些提示信息等等等</em>
			</dd>
		</dl>
		<dl >
			<dt>优先级:</dt>
			<dd>
				<select id="naPriority" name="naPriority" style="width:60%;">
					<option value="">请选择</option>
					<option value="0" >无</option>
					<option value="1" >低</option>
					<option value="2" >中</option>
					<option value="3" >高</option>
				</select>
			</dd>
		</dl>
		
		<dl style="width:98%;">
			<dt>内容:</dt>
			<dd>
				<textarea id="naContent" name="naContent" style="width:450px;height:300px;visibility:hidden;"></textarea>
				<em>请输入内容，长度不能超过好多个字</em>
			</dd>			
		</dl>
		
		<dl style="width:98%;">
			<dt>图片:</dt>
			<dd>
				<input type="text" class="input" maxlength="128" size="32" name="naAdImage" id="naAdImage"  value=""/>
				<button type="button" class="button">设置图片</button>
				<button type="button" class="button">删除图片</button>
				<button type="button" class="button">
				查看图片</button>
			</dd>
		</dl>
		
		<dl style="width:98%;">
			<dt>图片列表:</dt>
			<dd>
				<select name="Images" id="Images" size="5" multiple="multiple"  style="width:500px;">
					<option value="">33663.jpg</option>
				</select>
				<br/>
				<button type="button" class="button">添加</button>
				<button type="button" class="button">删除</button>
				<button type="button" class="button">清空</button>
			</dd>
		</dl>
		
		<dl style="width:98%;">
			<dt>地址:</dt>
			<dd>
				<input type="text" class="input" size="48"  maxlength="256" name="naVideo" id="naVideo"  value=""/>
				<b>*</b>
			</dd>
		</dl>
		
	
		
		<dl >
			<dt>关键字:</dt>
			<dd>
				<input type="text" class="input" size="32"  maxlength="50" name="naKeyword" id="naKeyword"  value=""/>
			</dd>
		</dl>
		
		
		<dl >
			<dt>状态:</dt>
			<dd>
				<select id="naStatus" name="naStatus" style="width:55%;">
				 
					<option value="1" >有效</option>
					<option value="0" >待审核</option>
					<option value="2" >无效</option>
				</select>
			</dd>
		</dl>
		
		<dl style="width:98%;" >
			<dt>关键字:</dt>
			<dd>
				<input type="text" class="input" size="32"  maxlength="50" name="naKeyword" id="naKeyword"  value=""/>
			</dd>
		</dl>
		
		<dl >
			<dt>处理状态:</dt>
			<dd>
				<select id="naTousuStatus" name="naTousuStatus" style="width:50%;">
				 
					<option value="0" >未处理</option>
					<option value="1" >处理中</option>
					<option value="2" >已处理</option>
				 
				</select>
			</dd>
		</dl>
		
		<dl >
			<dt>内容状态:</dt>
			<dd>
				<select id="naStatus" name="naStatus" style="width:50%;">
				 
					<option value="1" >有效</option>
					<option value="0" >待审核</option>
					<option value="2" >无效</option>
				</select>
			</dd>
		</dl>
		
		<div class="line"></div>
		
		<center>
			<button class="button" type="submit">提交</button>		
			<button class="button" type="reset">重置</button>
			<button class="button" onclick="window.history.back();" type="button">取消</button>
		</center>
	</div>	
</div>
<div class="foot"></div>
</form>

</body>
</html>
