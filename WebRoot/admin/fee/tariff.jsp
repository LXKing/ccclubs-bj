<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${basePath}admin/js/jquery-ui-1.10.2.custom.min.js"></script>	
<script type="text/javascript">
<!--
$(function(){
	$(".query button:contains('保存')").remove();
	$(".Not").remove();//把”取反条件“去掉
	$("#cspUserType").change(resetGoods);//当修改计费类型时，读取当前计费类型的商品列表
	resetGoods();
	$("#queryForm").sortable();
	$("#queryForm").submit(function(){
		var inputs = $("#cspGoods,#cspOutets,#cspModel,#cspUserType");
		var paramCount = 0;
		var names = "";
		$.each(inputs,function(i,o){
			if($(o).val()!="")paramCount++;
			names+=(names==""?"":",")+$(o).attr("id");
		});
		$("#queryForm").params({orders:names});
		if($("#cspUserType").val()==""){
			$("#cspUserType").say("亲，请选择一个计费类型");
			return false;
		}
		if($("#cspModel").val()==""){
			$("#cspModel").say("亲，请选择一个车型");
			return false;
		}
		if(paramCount==1||paramCount==2)
	    	$("#queryForm").params({size:65535});
	    else
	    	$("#queryForm").params({size:12});	    	
	});
	var array="${orders}".split(",");
	for(var o in array){
		var name = array[o];
		var dl = $("#"+name).parents("dl");
		dl.insertBefore("#queryForm .line");
	}
});
function resetGoods(){
	var userType = $("#cspUserType").val();
	if($.trim(userType)!=""){
		var oldGoods = $("#cspGoods").val();
		$("#cspGoods option").remove();
		$("<option value=''>全部</option>").appendTo("#cspGoods");
		var data = $.getObject("${basePath}admin/fee/goods_query.do",{
			csgUserType:userType,size:-1,object:true
		});
		for(var o in data){
			var goods = data[o].object;
			$("<option value='"+goods.csgId+"'>"+goods.csgName+"</option>").appendTo("#cspGoods");				
		}
		$("#cspGoods").val(oldGoods);
	}
}
//-->
</script>
<style>
.pricetable{background:#dddddd;table-layout:fixed;clear:both;border-spacing:1px;}
.pricetable td{ height:32px; line-height:32px;color:#000000;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
.pricetable thead td{font-weight:bold; border-bottom:1px solid #FFFFFF;text-align:center;padding:0px 5px;border:0px;}
.pricetable thead td a{white-space: nowrap; text-decoration:none; color:#000000;}
.pricetable tbody td{padding:0px;text-indent:0px;border:0px;background-color:#FFFFFF;text-align:center;border:0px;}
.pricetable tbody td .input{border:0px;margin:0px;}
.pricetable tbody .rowspan{line-height:14px;}
.pricetable .state-header{text-decoration:none;cursor:pointer;};
.rowspan{text-decoration:underline;cursor:pointer;}
#queryForm dl{cursor:pointer;}
.pricetable tbody .focus{background:blue;color:#ffffff;}
#form-dl-cspGoods{display:none;}
#form-dl-cspOutets{display:none;}
.highlight{margin:10px;font-size:12px;padding:10px;}
.highlight span{font-family:impact;font-size:14px;color:red;}
</style>
${lz:set("querySize",size)}
<s:if test="#request.querySize==65535">

<lz:set name="after$form">
<div class="highlight">
	价格为<span>0</span>表示未配置价格，会导致下单出错。价格为<span>0.01</span>表示该商品不计价。
	&nbsp;&nbsp;
	当前默认的网点是${csFeeTypeSet.csftsOutlets$}，当一般网点未配置价格时，将以默认网点的价格为准。
</div>
</lz:set>


<lz:set name="tempHtml">
<table id="price_table" class="pricetable table" border="0" cellspacing="1" cellpadding="0">
	<thead class="state-header">
	  <tr>
		 
	  </tr>
	 </thead>	
	 <tbody>
	 	
	 </tbody>			 
</table>
<input onchange="onkeyup()" onkeyup="var reg=/^(([1-9]\d{0,9})|0)(\.)?(\d{1,2})?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="hidden" style="width:100%;height:100%;outline:none;text-align:center;" id="priceditor"  value=""/>
</lz:set>
${lz:set("all",true)}
<script>
$(function(){	
	$("#price_table").parent().html("${lz:js(tempHtml)}");
	$(".toolbar").remove();
	$('<button class="button" onclick="submitPrice()" type="button">保存配置</button>').appendTo("#queryForm .option table td");
	$('<button class="button" onclick="importPrice()" type="button">价格导入</button>').prependTo("#queryForm .option table td");
	window["priceditor"]=$("#priceditor").remove();
});
function clearForm(){
	$.each($("#queryForm select"),function(i,o){
		$(o).val("");
	})
}
//价格导入
function importPrice(){
	var table=$("<table border='0' cellspacing='5' cellpadding='5'></table>");
	for(var o in selects){
		var tr=$("<tr><td>"+selects[o].text+":</td></tr>").appendTo(table);
		var td=$("<td></td>").appendTo(tr);
		var select=$("<select><option value=''>--请选择"+selects[o].text+"--</option></select>").attr("text",selects[o].text).attr("name",o).appendTo(td);
		for(var p in selects[o].items){
			var item=selects[o].items[p];
			var option = $("<option></option>").attr("value",item.value).text(item.text);
			option.appendTo(select);
		}
	}
	table.dialog({
		title:"价格导入操作",
		modal:true,
		static:true,
		onOk:function(){
			var bEmpty = false;
			var params = {};
			table.find("select").each(function(){
				if($(this).val()=="")
					bEmpty=true;
				else
					params[$(this).attr("name")]=$(this).val();
			});
			if(bEmpty){
				alert("导入源项不能为空");
				return false;
			}
			var idstemplate = "${empty cspGoods?"cspGoods":cspGoods},${empty cspOutets?"cspOutets":cspOutets},${empty cspModel?"cspModel":cspModel},${empty cspUserType?"cspUserType":cspUserType}";
			$.getObject("${basePath}admin/fee/price_copy.do",params,function(data){
				for(var o in data){
					var ids = idstemplate;
					ids=ids.replace("cspGoods",data[o].cspGoods);
					ids=ids.replace("cspOutets",data[o].cspOutets);
					ids=ids.replace("cspModel",data[o].cspModel);
					ids=ids.replace("cspUserType",data[o].cspUserType);	
					$("td[ids='"+ids+"']").text(data[o].cspPrice);
					$("td[ids='"+ids+"']").css("color","blue").attr("value",data[o].cspPrice);
				}
			});
		}
	});
}

//获取点击的TD相关的行列
function getSelTds(clicker){
	var tds=null;
	if($(clicker).hasClass("xy"))//全部
		tds = $("#price_table tbody td[ids]");
	else if($(clicker).parents("thead.state-header").size()>0){//列
		var lastindex =$(clicker).siblings().size() - $(clicker).index();
		$.each($("#price_table tbody tr"),function(i,o){
			var childs = $(o).children("td");
			var td = childs.get(childs.size()-lastindex-1);						
			if(tds==null){
				tds = $(td);						
			}else{
				tds=tds.add($(td));						
			}
		});
	}else if($(clicker).hasClass("rowspan")){//多行
		var rowspan=Number($(clicker).attr("rowspan"));
		var index = $(clicker).parent().index();
		for(var i=index;i<index+rowspan;i++){
			var temp = $("#price_table tbody tr:eq("+i+")").children("td[ids]");
			if(tds==null){
				tds = temp;						
			}else{
				tds=tds.add(temp);						
			}
		}
	}else
		tds = $(clicker).nextAll("td");//行
	return tds;
}

var clipboard = {};

function copyPrice(clicker){
	var tds = getSelTds(clicker);
	var array = [];
	tds.each(function(){
		array[array.length]=$(this).text();
	});
	clipboard[clicker.attr("xyz")]=array;
	$.toast("数据已经复制到内存");
}
function pastePrice(clicker){
	if(!clipboard[clicker.attr("xyz")]){
		showTips("粘贴板中不存在相同类型的数据");
		return;
	}
	var array = clipboard[clicker.attr("xyz")];
	var tds = getSelTds(clicker);
	tds.each(function(i){
		$(this).text(array[i]);
		$(this).css("color","blue").attr("value",array[i]);
	});
}

function setPrice(clicker){
	var tds = getSelTds(clicker);	
	tds.addClass("focus");	
	<lz:set name="dialogHtml">
	<table width="408" border="0" cellspacing="5" cellpadding="5">
	  <tr>
	    <td width="89" align="right">批量修改：</td>
	    <td width="284"><input style='width:200px;' id='getContentInput' type='text' class='input'/></td>
	  </tr>
	  <tr>
	  	<td align="right">&nbsp;</td>
	    <td align="left">格式如：N+8、N*2....之类的，或者直接输入数字。<br/>用于批量修改所选价格</td>
	  </tr>
	</table>
	</lz:set>

	$.dialog({
		modal:true,
		static:true,
		title:"价格操作",
		html:"${lz:js(dialogHtml)}",
		onOk:function(){
			if($.trim($("#getContentInput").val())=="")
				return $.tips("请输入内容");
			var expression = $.trim($("#getContentInput").val());
			$.each(tds,function(i,o){
				var value = $(o).text();
				var sEval = expression.replace(/[n|N]/g,value);
				var result = null;
				eval("result="+sEval);
				$(o).text($_$$(result));
				$(o).css("color","blue").attr("value",$_$$(result));								
			});
			tds.removeClass("focus");
		},
		onCancel:function(){
			tds.removeClass("focus");
		}
	});
}
function $_$$(v){
	return Number(v).toFixed(2);
}
//保存价格
function submitPrice(){
	var strPriceInfo = "";
	$.each($("#price_table tbody td[ids]"),function(i,o){
		if($(o).attr("value")){
			strPriceInfo+=(strPriceInfo==""?"":";")+$(o).attr("ids")+":"+$.trim($(o).text());
		}
	});
	$("#prices").val(strPriceInfo);
	/********************遮罩层****************/
	var overlay=$("<div class='overlay'></div>");		
	overlay.css("height",$(document).height()+20);
	overlay.css("width",$(document).width()+20);
	overlay.fadeTo("fast",0.8);
	overlay.css("z-index",$.zindex());
	overlay.appendTo("body");
	var iframe=$("<iframe border='0' width='100%' height='100%'></iframe>");
	iframe.appendTo(overlay);
	iframe.fadeTo("fast",0);
	/*******************遮罩层*****************/
	//$("#priceForm").submit();
	$.ajax({
		url:"price_saveall.do",
		data:{
			prices:strPriceInfo
		},
		dataType:"json",
		type:"post",
		success:function(data){
			overlay.remove();
			$.tips(data.message);
		},
		error:function(){
			overlay.remove();
			$.tips("保存价格出错，请稍候再试");			
		}	
	});
}

function onMenuOpen(clicker){
	var tds = getSelTds(clicker);	
	tds.addClass("focus");
}
function onMenuClose(clicker){
	var tds = getSelTds(clicker);	
	tds.removeClass("focus");
}


var datas=[];
<s:iterator value="#request.page.result" id="item" status="i">
datas["${item.cspGoods},${item.cspOutets},${item.cspModel},${item.cspUserType}"]="${item.cspPrice}";
</s:iterator>
${lz:set("page",null)}

var defaults=[];
<s:iterator value="#request.defaults" id="item" status="i">
defaults["${item.cspGoods},${item.cspOutets},${item.cspModel},${item.cspUserType}"]="${item.cspPrice}";
</s:iterator>


var selects=[];
$(function(){
	var array="${orders}".split(",");
	var ranks={};//表格信息
	var rankname="";;
	for(var o in array){
		var name = array[o];
		var items=[];
		$.each($("#"+name+" option[value!='']"),function(i,o){
			if(name=="cspOutets" && $(o).attr("value")=="${defaultOutlets}"){
				items.splice(0,0,{text:$(o).text()+" (默认)",value:$(o).attr("value")});
			}else{
				items[items.length]={text:$(o).text(),value:$(o).attr("value")};
			}
		});
		var text = $("#"+name).parents("dl").find("dt").text().replace(":","");
		var info={name:name,text:text,items:items};
		if($("#"+name).val()!=""){
			window[name]=$("#"+name).val();
			selects[name]=info;
		}else{			
			if(!ranks["x"])//x轴
				ranks.x=info;
			else if(!ranks["y"])//y轴
				ranks.y=info;
			else if(!ranks["z"])//z轴
				ranks.z=info;
		}
	}
	//z轴
	if(ranks.z)
		$("<td></td>").html(ranks.z.text).attr("width",150).appendTo("#price_table thead tr");
	$("<td></td>").addClass("xy").html(ranks.y.text+"\\"+ranks.x.text).click(function(){
		setPrice(this);
	}).attr("width",150).appendTo("#price_table thead tr");
	for(var o in ranks.x.items){
		$("<td class='x' xyz='x'></td>").attr("width",Math.max(ranks.x.items[o].text.length*13,66)).attr("name",ranks.x.name).click(function(){
			setPrice(this);
		}).menu({
			"价格复制":function(clicker){
				copyPrice(clicker);
			},
			"价格粘贴":function(clicker){
				pastePrice(clicker);
			}
		},{onOpen:onMenuOpen,onClose:onMenuClose}).html(ranks.x.items[o].text).appendTo("#price_table thead tr");
	}
	if($("#price_table").width()<$(window).width()){
		$("#price_table").attr("width","100%");
		$("#price_table thead .x").removeAttr("width");
	}
	//z轴
	for(var k in ranks.z?ranks.z.items:[1]){
		if(ranks.z)
			window[ranks.z.name]=ranks.z.items[k].value;
		//行
		for(var o in ranks.y.items){
			window[ranks.y.name]=ranks.y.items[o].value;		
			var tr = $("<tr></tr>").appendTo("#price_table tbody");//新建行
			if(ranks.z && o==0){
				$("<td class='rowspan z' xyz='z'></td>").attr("name",ranks.z.name).click(function(){
					setPrice(this);
				}).menu({
					"价格复制":function(clicker){
						copyPrice(clicker);
					},
					"价格粘贴":function(clicker){
						pastePrice(clicker);
					}
				},{onOpen:onMenuOpen,onClose:onMenuClose}).html(ranks.z.items[k].text).appendTo(tr).attr("rowspan",ranks.y.items.length);//.replace(/(.)/g,"$1<br/>")
			}
			$("<td class='state-header y' xyz='y'></td>").attr("name",ranks.y.name).click(function(){
				setPrice(this);
			}).menu({
				"价格复制":function(clicker){
					copyPrice(clicker);
				},
				"价格粘贴":function(clicker){
					pastePrice(clicker);
				}
			},{onOpen:onMenuOpen,onClose:onMenuClose}).html(ranks.y.items[o].text).appendTo(tr);//第一列
			//填充数据
			for(var p in ranks.x.items){
				window[ranks.x.name]=ranks.x.items[p].value;		
				var ids = cspGoods+","+cspOutets+","+cspModel+","+cspUserType;
				var td = $("<td></td>").attr("ids",ids).appendTo(tr);
				if(datas[ids]){
					td.html(datas[ids]);
				}else if(defaults[cspGoods+",${defaultOutlets},"+cspModel+","+cspUserType]){
					td.html(defaults[cspGoods+",${defaultOutlets},"+cspModel+","+cspUserType]);
					td.css("color","red");
				}else
					td.html("0");
			}
		}
	}
	$("#price_table tbody td[ids]").click(function(){
		if($(this).find("input").size())
			return; 
		var text=$.trim($(this).html());
		$(this).width($(this).width());
		priceditor.width($(this).width());
		$(this).empty().append(priceditor);
		var pThis = $(this);
		priceditor.val(text).show().select().blur(function(){
			pThis.text($(this).val());
			pThis.attr("value",$(this).val());
			pThis.css("color","blue");
		});
	});	
});
</script>
</s:if>
