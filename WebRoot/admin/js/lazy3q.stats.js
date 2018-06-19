/*设置默认的js*/
$(function(){
	/***********************处理统计视图***************************/
	if(window["stats"]){
		if($(".shortcuts").next().width()<$(window).width()-$(".shortcuts").width()-20)
			$(".shortcuts").next().width($(window).width()-$(".shortcuts").width()-20);
		$("body").css("padding-bottom","50px");
		if(stats.groupCount == 1 || stats.groupCount == 0){
			$("<button ref='table' class='state-header'>数据列表</button>").appendTo(".viewtypes");
		}else if(stats.groupCount == 2){
			$("<button ref='table' xyz='0,1' class='state-header'>XY数据列表</button>").appendTo(".viewtypes");
			$("<button ref='table' xyz='1,0' class='state-header'>YX数据列表</button>").appendTo(".viewtypes");
		}else if(stats.groupCount == 3){
			$("<button ref='table' xyz='0,1,2' class='state-header'>XYZ数据列表</button>").appendTo(".viewtypes");
			$("<button ref='table' xyz='2,1,0' class='state-header'>ZYX数据列表</button>").appendTo(".viewtypes");
			$("<button ref='table' xyz='0,2,1' class='state-header'>XZY数据列表</button>").appendTo(".viewtypes");
			$("<button ref='table' xyz='1,0,2' class='state-header'>YXZ数据列表</button>").appendTo(".viewtypes");
			$("<button ref='table' xyz='1,2,0' class='state-header'>YZX数据列表</button>").appendTo(".viewtypes");
			$("<button ref='table' xyz='2,0,1' class='state-header'>ZXY数据列表</button>").appendTo(".viewtypes");
		}
		if(stats.groupCount == 1 && stats.sumCount == 1 ){
			$("<button ref='pie' class='state-header'>饼 状 图</button>").appendTo(".viewtypes");
		}
		if(stats.haveTimeType && stats.sumCount == 1 && stats.groupCount != 3){
			$("<button ref='line' class='state-header'>曲 线 图</button>").appendTo(".viewtypes");
			$("<button ref='bar' class='state-header'>柱 状 图</button>").appendTo(".viewtypes");
		}
		$(".viewtypes button[ref]").click(function(){
			$(".viewtypes button[ref]").removeClass("state-focus").addClass("state-header");
			$(this).removeClass("state-header").addClass("state-focus");
			createStatsPage($(this).attr("ref"));
		}).width(100).css("margin","10px");
		var iframeid = "stats_"+$.UUID();
		var iframe = $("<iframe name='"+iframeid+"'/>").appendTo("body").hide();
		//进入时默认以列表方式
		if(stats.viewType)
			$(".viewtypes button[ref='"+stats.viewType+"']").click();
		else
			$(".viewtypes button[ref]:eq(0)").click();
	}
	$(".title .export").click(function(){
		var viewType = $(".viewtypes .state-focus[ref]").attr("ref");
		if(viewType=="table"){
			$.submit($(this).attr("href"),{
				html:$("#stats").clone().find("table").attr("border","1").parent().html()
			},iframeid);
		}else{
			showTips("当前为图表模式，请使用屏幕截图工具进行截图保存图片");
		}
		return false;
	});
	//统计表单提交时
	$(".statsForm").submit(function(){
		if($(this).find(".groupBy:checked").size()>3){
			Alert("统计分组勾选不能超过两个");
			return false;
		}
		/*if($(this).find(".groupBy:checked").size()==0){
			$.tips("请选择统计分组");
			return false;
		}*/
		if($(this).find(".sumBy:checked").size()==0){
			$.tips("请选择统计类型");
			return false;
		}
		if($(this).find(".sumBy:checked").size()>1 && $(this).find(".groupBy:checked").size()==3){
			Alert("统计分组维度为三维(勾选了三个统计分组)时，统计类型不能超过一个");
			return false;
		}
	});
});

//获取三维统计数据的变换矩阵,兼容二维矩阵
function getStatsMatrix(orders){
	var groupnames = stats.groupBy;//分组名称
	//把时间类型的分组默认放到Y轴
	var timeTypes = {};
	for(var o in stats.timeTypes)
		timeTypes[stats.timeTypes[o]]=true;
	var timeGroupIndex = -1;
	for(var i=0;i<groupnames.length;i++){
		if(timeTypes[groupnames[i]]==true)
			timeGroupIndex=i;
	}
	if(timeGroupIndex!=-1){
		groupnames.splice(1,0,groupnames.splice(timeGroupIndex,1));
	}
	
	var flages=[];//名称标识
	var indexs=[];//顺序标识
	for(var o in stats.datas){
		var data = stats.datas[o];
		for(var p in orders){
			var xyz = orders[p];
			var flag = data[groupnames[xyz]];
			if(typeof(indexs[p])=="undefined")
				indexs[p]=0;
			if(typeof(flages[p])=="undefined")
				flages[p]={};
			if(typeof(flages[p][flag])=="undefined")
				flages[p][flag]=indexs[p]++;
		}
	}
	var values = [];
	for(var o in stats.sumBy)values[o]=0;
	var mapping=NewArray(indexs,values);
	//填充数据
	for(var o in stats.datas){
		var data = stats.datas[o];
		var mdata = [];
		for(var p in stats.sumBy){
			var sumName = stats.sumBy[p];
			if(sumName.indexOf(":")==-1){
				mdata[p] = data[sumName];
			}else{
				var arr = sumName.split(":");
				var exp = arr[1];
				var regx = /\{([^\}]*)\}/g;
				while((results=regx.exec(arr[1]))!=null){
					var key = results[1];
					exp=exp.replace("{"+key+"}",data[key]);
				}
				mdata[p] = eval("("+exp+")");
			}
		}
		var strEval = "mapping";
		for(var p in orders){
			var flag = data[groupnames[orders[p]]];
			strEval+="[flages["+p+"]['"+flag+"']]";
		}
		strEval+="=mdata;";
		eval(""+strEval+"");
	}
	
	window.matrix={data:mapping};
	var xyzTypes = ["x","y","z"];
	for(var p in orders){
		var xyz = orders[p];
		var xyzType = xyzTypes[p];
		var values = [];
		for(var o in flages[p])
			values[values.length]=o;
		window.matrix[xyzType] = {
			count:indexs[p],
			name:groupnames[xyz],
			caption:stats.structs[groupnames[xyz]],
			timeType:timeTypes[groupnames[indexs[xyz]]]==true,
			values:values
		}
	}
	return matrix;
}
//声明多维数组，dims格式为[2,3,4]其中的数字为维度数
function NewArray(dims,value){
	if(dims.length==0)
		return value;
	var cdims=[];
	for(var i=1;i<dims.length;i++)
		cdims[cdims.length]=dims[i];
	var arr = [];
	for(var i=0;i<dims[0];i++){
		arr[i]=NewArray(cdims,value);
	}
	return arr;
}

//获取动态表达式值
function el(exp,data,keys){
	var names={};
	if(keys){
		for(var k = 0;k<keys.length;k++)
			names[keys[k].split(":")[0]]=k;
	}
	var regx = /\{([^\}]*)\}/g;
	while((results=regx.exec(exp))!=null){
		exp=exp.replace("{"+results[1]+"}",keys?data[names[results[1]]]:data[results[1]]);
	}
	return eval("("+exp+")");
}

function f2(number){
	if((""+number).indexOf(".")==-1)
		return number;
	else
		return number.toFixed(2);
}


//处理统计页面数据
function createStatsPage(viewType){	
	var container = $("#stats").empty();
	if(viewType=="table"){
		var table = $("<table style='table-layout:auto;' class='table' sort='true' width='100%' border='0' cellspacing='1' cellpadding='0'></table>").appendTo(container);
		var thead = $("<thead class='state-header'><tr></tr></thead>").appendTo(table);
		var tbody = $("<tbody></tbody>").appendTo(table);
		if(stats.groupCount==2){//二维统计
			var matrix = getStatsMatrix(($(".viewtypes .state-focus").attr("xyz")||"0,1").split(","));
			$("<td rowspan='"+(stats.sumBy.length>1?2:1)+"'></td>").html(
				stats.sumBy.length>1?
				"("+matrix.x.caption+"→)</br>("+matrix.y.caption+"↓)":
				"("+matrix.y.caption+"↓)&nbsp;&nbsp;&nbsp;("+matrix.x.caption+"→)"
			).appendTo(thead.find("tr"));
			for(var o in matrix.x.values){
				$("<td ref='"+matrix.x.name+"' "+(stats.sumBy.length>1?"notsort='true'":"")+" colspan='"+stats.sumBy.length+"'></td>").html((matrix.x.values[o]||"-")).appendTo(thead.find("tr"));
			}
			$("<td colspan='"+stats.sumBy.length+"'>水平合计</td>").appendTo(thead.find("tr"));
			if(stats.sumBy.length>1){
				var tr = $("<tr></tr>").appendTo(thead);
				for(var i=0;i<matrix.x.count;i++){
					for(var k = 0;k<stats.sumBy.length;k++){
						$("<td group='"+(matrix.x.values[i]||"-")+"' ref='"+stats.sumBy[k]+"' index='"+(i*stats.sumBy.length+k+1)+"'></td>").html(stats.structs[stats.sumBy[k].split(":")[0]]).appendTo(tr);
					}
				}
				for(var k = 0;k<stats.sumBy.length;k++){
					$("<td index='"+(matrix.x.count*stats.sumBy.length+k+1)+"'></td>").html(stats.structs[stats.sumBy[k].split(":")[0]]+"合计").appendTo(tr);
				}
			}
			var sumnames={};
			for(var k = 0;k<stats.sumBy.length;k++)
				sumnames[stats.sumBy[k].split(":")[0]]=k;
			var totals=[];
			for(var y=0;y<matrix.y.count;y++){
				var tr = $("<tr></tr>").appendTo(tbody);
				$("<td></td>").appendTo(tr).html((matrix.y.values[y]||"-")).css("font-weight","bold");
				var xTotal = [0,0,0,0,0,0,0,0,0,0];
				for(var x=0;x<matrix.x.count;x++){
					for(var k = 0;k<stats.sumBy.length;k++){
						var value = (matrix.data[x] && matrix.data[x][y] && matrix.data[x][y][k])?matrix.data[x][y][k]:0;
						$("<td class='price price"+k+"'>0</td>").appendTo(tr).html(value);
						xTotal[k]+=Number(value||"0");
						var xIndex = x*stats.sumBy.length+k;
						if(!totals[xIndex])
							totals[xIndex] = 0;					
						totals[xIndex]+=Number(value||"0");
					}
				}
				for(var k = 0;k<stats.sumBy.length;k++){
					if(stats.sumBy[k].indexOf(":")==-1){
						$("<td class='total total"+k+"'>"+f2(xTotal[k])+"</td>").appendTo(tr);
					}else{
						$("<td class='total total"+k+"'>"+el(stats.sumBy[k].split(":")[1],xTotal,stats.sumBy)+"</td>").appendTo(tr);
					}
				}
			}
			//垂直合计 
			{
				var tr = $("<tr notsort></tr>").appendTo(tbody);
				var data = stats.datas[p];
				$("<td></td>").appendTo(tr).html("垂直合计");
				var xTotal = [0,0,0,0,0,0,0,0,0,0];
				for(var i=0;i<matrix.x.count;i++){
					for(var k = 0;k<stats.sumBy.length;k++){
						var sumName = stats.sumBy[k];
						var sTotal = 0;
						if(sumName.indexOf(":")==-1){
							sTotal=totals[i*stats.sumBy.length+k];
						}else{
							var arr = sumName.split(":");
							var exp = arr[1];
							var regx = /\{([^\}]*)\}/g;
							while((results=regx.exec(arr[1]))!=null){
								var key = results[1];
								exp=exp.replace("{"+key+"}",totals[i*stats.sumBy.length+sumnames[key]]);
							}
							sTotal = eval("("+exp+")");
						}
						$("<td class='total total"+k+"'>"+f2(sTotal)+"</td>").appendTo(tr);
						xTotal[k]+=totals[i*stats.sumBy.length+k];
					}
				}
				for(var k = 0;k<stats.sumBy.length;k++){
					var sumName = stats.sumBy[k];
					var sTotal = 0;
					if(sumName.indexOf(":")==-1){
						sTotal=xTotal[k];
					}else{
						var arr = sumName.split(":");
						var exp = arr[1];
						var regx = /\{([^\}]*)\}/g;
						while((results=regx.exec(arr[1]))!=null){
							var key = results[1];
							exp=exp.replace("{"+key+"}",xTotal[sumnames[key]]);
						}
						sTotal = eval("("+exp+")");
					}
					$("<td title='总计' class='total total"+k+"'>总计:"+f2(sTotal)+"</td>").appendTo(tr);
				}
			}
		}else if(stats.groupCount==3){//三维统计
			var matrix = getStatsMatrix(($(".viewtypes .state-focus").attr("xyz")||"0,1").split(","));
			$("<td rowspan='2' style='line-height:22px;'></td>").html(
				"("+matrix.x.caption+"→)</br>("+matrix.z.caption+"↳)</br>("+matrix.y.caption+"↓)"
			).appendTo(thead.find("tr"));
			for(var o in matrix.x.values){
				$("<td ref='"+matrix.x.name+"' notsort='true' colspan='"+matrix.z.count+"'></td>").html((matrix.x.values[o]||"-")).appendTo(thead.find("tr"));
			}
			$("<td colspan='"+(matrix.z.count+1)+"'>水平合计</td>").appendTo(thead.find("tr"));
			var tr = $("<tr></tr>").appendTo(thead);
			for(var i=0;i<matrix.x.count;i++){
				for(var k = 0;k<matrix.z.count;k++){
					$("<td index='"+(i*matrix.z.count+k+1)+"'></td>").html(matrix.z.values[k]).appendTo(tr);
				}
			}
			for(var z = 0;z<matrix.z.count;z++){
				$("<td index='"+(matrix.x.count*matrix.z.count+z+1)+"'>"+matrix.z.values[z]+"</td>").appendTo(tr);
			}
			$("<td index='"+(matrix.x.count*matrix.z.count+matrix.z.count+1)+"'>总计</td>").appendTo(tr);
			var totals=[];
			for(var y=0;y<matrix.y.count;y++){
				var tr = $("<tr></tr>").appendTo(tbody);
				$("<td></td>").appendTo(tr).html((matrix.y.values[y]||"-")).css("font-weight","bold");
				var xTotal = [0,0,0,0,0,0,0,0,0,0];
				for(var x=0;x<matrix.x.count;x++){
					for(var z = 0;z<matrix.z.count;z++){
						var value = (matrix.data[x] && matrix.data[x][y] && matrix.data[x][y][z])?matrix.data[x][y][z][0]:0;
						$("<td class='price price"+z+"'>0</td>").appendTo(tr).html(value);
						xTotal[z]+=Number(value||"0");
						var xIndex = x*matrix.z.count+z;
						if(!totals[xIndex])
							totals[xIndex] = 0;					
						totals[xIndex]+=Number(value||"0");
					}
				}
				var xTotalTotal=0;
				for(var z = 0;z<matrix.z.count;z++){
					$("<td class='total total"+z+"'>"+f2(xTotal[z])+"</td>").appendTo(tr);
					xTotalTotal+=xTotal[z];
				}
				$("<td class='total'>"+f2(xTotalTotal)+"</td>").appendTo(tr);
			}
			//垂直合计 
			{
				var tr = $("<tr notsort></tr>").appendTo(tbody);
				var data = stats.datas[p];
				$("<td></td>").appendTo(tr).html("垂直合计");
				var xTotal = [0,0,0,0,0,0,0,0,0,0];
				for(var i=0;i<matrix.x.count;i++){
					for(var z = 0;z<matrix.z.count;z++){
						$("<td class='total total"+z+"'>"+f2(totals[i*matrix.z.count+z])+"</td>").appendTo(tr);
						xTotal[z]+=totals[i*matrix.z.count+z];
					}
				}
				var xTotalTotal=0;
				for(var z = 0;z<matrix.z.count;z++){
					$("<td title='总计' class='total total"+z+"'>总计:"+f2(xTotal[z])+"</td>").appendTo(tr);
					xTotalTotal+=xTotal[z];
				}
				$("<td class='total'>全部:"+f2(xTotalTotal)+"</td>").appendTo(tr);
			}
		}else{//一维统计直接扫描数据列表
			//表头处理
			if(stats.groupCount==0)
				$("<td width='160'></td>").html("统计内容").appendTo(thead.find("tr"));
			else
				$("<td width='160'></td>").html(stats.structs[stats.groupBy[0]]+"\\统计内容").appendTo(thead.find("tr"));
			for(var o in stats.sumBy){
				$("<td></td>").html(stats.structs[stats.sumBy[o].split(":")[0]]).appendTo(thead.find("tr"));
			}
			var totals=[];
			for(var p in stats.datas){
				var tr = $("<tr></tr>").appendTo(tbody);
				var data = stats.datas[p];
				if(stats.groupCount==0)
					$("<td></td>").appendTo(tr).html("统计结果");
				else
					$("<td></td>").appendTo(tr).html(data[stats.groupBy[0]]||"无");
				for(var o in stats.sumBy){
					$("<td class='price'></td>").appendTo(tr).html(data[stats.sumBy[o].split(":")[0]]||"0");
					totals[o]=(Number(totals[o]||"0"))+Number(data[stats.sumBy[o].split(":")[0]]||"0");
				}
			}
			//合计 
			{
				var tr = $("<tr notsort></tr>").appendTo(tbody);
				var data = stats.datas[p];
				$("<td></td>").appendTo(tr).html("合计");
				for(var o in stats.sumBy){
					$("<td class='price total'></td>").appendTo(tr).html(f2(Number(totals[o]||"0")));
				}
			}
		}
		window.$fire("table"); 
		table.tabler();
	}else if(viewType=="bar"){
		if(stats.groupCount == 1 && stats.sumCount == 1 ){
			var xml = "<chart xAxisName='"+stats.structs[stats.sumBy[0].split(":")[0]]+"' yAxisName='"+stats.structs[stats.groupBy[0]]+"' showValues='0' decimals='0' formatNumberScale='0' bgColor='f2f2f2'>";
			for(var p in stats.datas){
				var data = stats.datas[p];
		    	xml+="<set label='"+data[stats.groupBy[0]]+"' value='"+data[stats.sumBy[0].split(":")[0]]+"' />";
		    }
			xml+="</chart>";
			var chart = new FusionCharts(contextPath+"js/FusionCharts/Charts/Column3D.swf?mm="+Math.random(),"newly", $("#stats").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML(xml);		   
		   	chart.render("stats");
	   	}else if(stats.groupCount == 2 ){//二维统计
			var matrix = getStatsMatrix(($(".viewtypes .state-focus").attr("xyz")||"0,1").split(","));
			var xline = matrix.x.timeType?matrix.x:matrix.y;
			var yline = matrix.x.timeType?matrix.y:matrix.x;
			var xml = "<chart caption='"+stats.structs[stats.sumBy[0].split(":")[0]]+"统计' shownames='1' showvalues='0' decimals='0' formatNumberScale='0' bgColor='f2f2f2'>";
			//时间名称轴
			xml+="<categories>";
			for(var p in xline.values){
		    	xml+="<category label='"+xline.values[p]+"' />";
		    }
		    xml+="</categories>";
		    //数据轴
		    for(var i=0;i<yline.count;i++){
			    xml+="<dataset seriesName='"+yline.values[i]+"'>";
				for(var j=0;j<xline.count;j++){
			    	xml+="<set name='"+xline.values[j]+"' value='"+matrix.data[i][j]+"' />";
			    }
			    xml+="</dataset>";
		    }
			xml+="</chart>";
			var chart = new FusionCharts(contextPath+"js/FusionCharts/Charts/MSColumn3D.swf?mm="+Math.random(),"newly", $("#stats").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML(xml);		   
		   	chart.render("stats");
		}
		window.$fire("bar");   
	}else if(viewType=="pie"){
		var xml = "<chart palette='4' decimals='0' enableSmartLabels='1' enableRotation='0' bgColor='f2f2f2' bgAlpha='40,100' bgRatio='0,100' bgAngle='360' showBorder='0' startingAngle='70'>";
		for(var p in stats.datas){
			var data = stats.datas[p];
	    	xml+="<set label='"+data[stats.groupBy[0]]+"' value='"+data[stats.sumBy[0].split(":")[0]]+"' />";
	    }
		xml+="</chart>";
		var chart = new FusionCharts(contextPath+"js/FusionCharts/Charts/Pie3D.swf?mm="+Math.random(),"newly", $("#stats").width(), "400", "0", "0");
	   	chart.setTransparent(true);
	   	chart.setDataXML(xml);		   
	   	chart.render("stats");	 
	   	window.$fire("pie");   
	}else if(viewType=="line"){
		if(stats.groupCount == 1 && stats.sumCount == 1 ){
			var xml = "<chart lineThickness='1' canvasBorderThickness='2' canvasBorderColor='#000000' showValues='0' formatNumberScale='0' anchorRadius='2' divLineAlpha='80' divLineColor='efefef' borderColor='efefef' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='2' numvdivlines='5' chartRightMargin='35' bgColor='f2f2f2' bgAngle='270' bgAlpha='100,100'>";
			//时间名称轴
			xml+="<categories>";
			for(var p in stats.datas){
				var data = stats.datas[p];
		    	xml+="<category label='"+data[stats.groupBy[0]]+"' />";
		    }
		    xml+="</categories>";
		    //数据轴
		    xml+="<dataset seriesName='"+stats.structs[stats.groupBy[0]]+"' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
			for(var p in stats.datas){
				var data = stats.datas[p];
		    	xml+="<set name='"+data[stats.groupBy[0]]+"' value='"+data[stats.sumBy[0].split(":")[0]]+"' />";
		    }
		    xml+="</dataset>";
			xml+="</chart>";
			var chart = new FusionCharts(contextPath+"js/FusionCharts/Charts/MSLine.swf?mm="+Math.random(),"newly", $("#stats").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML(xml);		   
		   	chart.render("stats");
		}else if(stats.groupCount == 2 ){//二维统计
			var matrix = getStatsMatrix(($(".viewtypes .state-focus").attr("xyz")||"0,1").split(","));
			var xline = matrix.x.timeType?matrix.x:matrix.y;
			var yline = matrix.x.timeType?matrix.y:matrix.x;
			var xml = "<chart lineThickness='1' canvasBorderThickness='2' canvasBorderColor='#000000' showValues='0' formatNumberScale='0' anchorRadius='2' divLineAlpha='80' divLineColor='efefef' borderColor='efefef' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='2' numvdivlines='5' chartRightMargin='35' bgColor='f2f2f2' bgAngle='270' bgAlpha='100,100'>";
			//时间名称轴
			xml+="<categories>";
			for(var p in xline.values){
		    	xml+="<category label='"+xline.values[p]+"' />";
		    }
		    xml+="</categories>";
		    //数据轴
		    for(var i=0;i<yline.count;i++){
			    xml+="<dataset seriesName='"+yline.values[i]+"'>";
				for(var j=0;j<xline.count;j++){
			    	xml+="<set name='"+xline.values[j]+"' value='"+matrix.data[i][j]+"' />";
			    }
			    xml+="</dataset>";
		    }
			xml+="</chart>";
			var chart = new FusionCharts(contextPath+"js/FusionCharts/Charts/MSLine.swf?mm="+Math.random(),"newly", $("#stats").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML(xml);		   
		   	chart.render("stats");
		}  
		window.$fire("line");  
	}
	
}