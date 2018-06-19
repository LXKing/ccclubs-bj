<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<s:bean id="lz" name="com.lazy3q.web.helper.$" />
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />
		<title>车辆历史轨迹</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta name="author" content="飞啊飘啊">
		<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
		<script charset="utf-8" src="http://webapi.amap.com/maps?v=1.3&key=0a7423560970ad2922ad3d7506d2d6b2"></script>
		<script src="${basePath}admin/js/evnetmap.js?v=5" type="text/javascript"></script>
		<script src="${basePath}admin/js/coordtransform.js?v=4" type="text/javascript"></script>
		<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
		<style type="text/css">
body {
 color: #272E7E; font: 12px/ 18px Arial, sans-serif, "宋体"; overflow: hidden; margin: 0; padding: 0;
}

h4 {
 font-style: normal;
}

ul,ol {
 list-style: none; padding: 0; margin: 0;
}

li {
 list-style: none outside none; line-height: 24px; overflow: visible; vertical-align: bottom;
}

#left {
 width: 226px;
}

dl.vehAll {
 margin: 0;
}

dl.vehAll dt {
 background-color: #F8F8F8; padding: 5px 0px 0px 10px; position: relative; text-align: left; border-bottom: 1px solid #7899C4;
}

dl.vehAll dt span {
 cursor: pointer; display: inline-block; border-top: 1px solid #7899C4; border-left: 1px solid #7899C4; border-right: 1px solid #7899C4; background-color: #8BA9D1; margin-right: 5px; height: 20px; padding: 3px 10px 0px 10px; vertical-align: middle; color: #FFFFFF; font-weight: bold;
}

.group_canvas {
 overflow-y: auto; padding: 5px 0;
}

.dTreeNode {
 text-align: left; padding-left: 10px;
}

.dTreeNode h4 {
 line-height: 26px; height: 26px;
}

.dTreeNode h4 a {
 font-size: 12px; display: block; background-image: url("/skin/images/bg_mycenter.png"); background-repeat: no-repeat; padding-left: 15px; background-position: -250px -374px;
}

.dTreeList {
 padding: 0 10px;
}

.dTreeList_title {
 display: block; cursor: pointer; background: url("/admin/images/state/u_online.gif") 0 center no-repeat; height: 24px; line-height: 24px; text-align: left; padding-left: 20px;
}

.dTreeList_tool {
 display: block; text-align: right;
}

.itemon {
 background-color: #EBF9FF; border: 1px solid #A4D4F5; color: #5461D9; cursor: pointer; font-weight: bold; padding-bottom: 3px; padding-top: 3px;
}

.itemonover {
 background-color: #EBF9FF; border: 1px solid #A4D4F5; color: #5461D9; cursor: pointer; font-weight: bold; padding-bottom: 3px; padding-top: 3px;
}

.dTreeList a {
 color: #3E3E3E; text-decoration: none; font-size: 12px;
}

.dTreeList a:hover {
 color: #FF6600; text-decoration: underline;
}

.dTreeList_tool {
 text-align: left; padding-right: 0; color: #5D5D5D; padding-left: 5px; padding-right: 2px;
}

.dTreeList_tool p {
 line-height: 22px; border-bottom: 1px dashed #CCCCCC; font-size: 12px; margin: 0;
}

.dTreeList_tool p a {
 color: #5461D9; font-weight: bold;
}

.rTreeList {
 padding: 0 10px;
}

.rTreeList_title {
 display: block; cursor: pointer; background: url("/admin/images/state/u_online.gif") 0 center no-repeat; height: 24px; line-height: 24px; text-align: left; padding-left: 20px; margin: 0;
}

.rTreeList_span {
 float: right;
}

.rTreeList_tool {
 display: block; text-align: right;
}

.rTreeList a {
 color: #3E3E3E; text-decoration: none; font-size: 12px;
}

.rTreeList a:hover {
 color: #FF6600; text-decoration: underline;
}

.rTreeList_tool {
 text-align: left; padding-right: 0; color: #5D5D5D; padding-left: 5px; padding-right: 2px;
}

.rTreeList_tool p {
 line-height: 22px; border-bottom: 1px dashed #CCCCCC; font-size: 12px; margin: 0;
}

.rTreeList_tool p a {
 color: #5461D9; font-weight: bold;
}

#mapControl {
 margin: 5px 5px 5px 5px; background-color: #F8F8F8;
}

#mapControl td {
 text-align: center; padding: 5px;
}

#mapControl .slider {
 height: 30px; position: relative; overflow: hidden;
}

#mapControl .slider .opacity {
 z-index: 1; position: absolute; height: 100%; width: 100%; background: #000000; filter: alpha(opacity = 30); opacity: 0.3;
}

#mapControl .slider .background {
 height: 100%;
}

#mapControl .slider .background .strip {
 height: 10px; background: #0080e2; margin: 10px;
}

#mapControl .slider .block {
 z-index: 3; position: absolute; left: 10px; top: 5px; height: 20px; width: 10px; background: #ffffff;
}

#mapControl .button {
 background: #EBF9FF; color: #5461D9; border: 0px; font-size: 14px; padding: 5px; font-weight: bold; cursor: pointer;
}

#mapControl #time {
 background: #EBF9FF; color: #5461D9; border: 0px; font-size: 14px; border: 0px; height: 30px; line-height: 30px; padding: 0px 10px; margin: 0px 10px; cursor: pointer;
}
</style>
		${lz:set("defaultHost",get:SrvHost(get:host()))}
		<script type="text/javascript">
			var ccclubsMap;
			var timer;
			//轨迹
			var historys = [
			<s:iterator value="#request.historys" id="item" status="i">
				${i.count>1?",":""}[${item.cshsLatitude},${item.cshsLongitude},${item.cshsAddTime.time},${item.cshsOil+0},${item.cshsSpeed+0},${item.cshsPower+0},${item.cshsMileage+0}]
			</s:iterator>
			];
	        $(document).ready(function() {
	            restFrame();
	            ccclubsMap= new EVnetMap("allmap", 12, MapType.Gaode, ${lz:or(defaultHost.shLocation,"30.276611,120.161665")});
				ccclubsMap.displayPloy(historys); 
				var marker = new AMap.Marker({
				    map:ccclubsMap.map,
				    cursor:"pointer",
				    icon:new AMap.Icon({image:"/admin/images/state/marker.png?v=1", size:new AMap.Size(22, 34), imageOffset:new AMap.Pixel(0, 0)}),
				    title:"${csCar.cscNumber}"
				});
				
				if(ccclubsMap.markers_obj.length > 0){
					ccclubsMap.map.panTo(ccclubsMap.markers_obj[0].getPosition());
					ccclubsMap.map.setZoom(14);
					marker.setPosition(ccclubsMap.markers_obj[0].getPosition());
					marker.show();
				}
			//条件选择，高速设置，怠速设置，
			//分析结果，高速行驶里程，怠速时长，停车时长，加油次数
			<lz:set name="rightControl">		
				<div class="group_canvas" style="position: absolute;left: 10px;top: 50px;width: 226px; width: 226px;background-color:#F8F8F8; margin-top:10px;">
						<div class="rTreeList">
								<p class="rTreeList_title ">高速设置：</span>
								<select id="selHighSpeed" class="rTreeList_span" style="width:100px">
											<option value="90">车速大于90</option>
											<option value="80">车速大于80</option>
											<option value="70">车速大于70</option>
											<option value="60">车速大于60</option>
											<option value="30">车速大于30</option>
								</select>
								</p>
								<p class="rTreeList_title ">怠速设置：</span>
								<select id="selLowSpeed" class="rTreeList_span" style="width:100px">
											<option value="5">车速小于5</option>
											<option value="10">车速小于10</option>
								</select>
								</p>
								<hr>
								<ul>
										<li class="">
											<p class="rTreeList_title ">高速行驶里程：<span class="rTreeList_span"><em id="highSpeed" >0</em>公里</span></p>
										</li>
								</ul>
						</div>
						<div class="rTreeList">
								<ul>
										<li class="" >
											<p class="rTreeList_title ">怠速时长：<span class="rTreeList_span"><em id="lowSpeed" >1小时20分钟</em></span></p>
										</li>
								</ul>
						</div>
						<div class="rTreeList">
								<ul>
										<li  class="">
											<p class="rTreeList_title ">停车时长：<span class="rTreeList_span" ><em id="stopSpeed" >1小时20分钟</em></span></p>
										</li>
								</ul>
						</div>
						<div id="refuel" class="rTreeList">
								<ul>
										<li class="">
											<div class="rTreeList_tool">
												<p style="text-align: right;height:32px;">
													<a href="###" id ="calculate">计算轨迹里程</a>
														<a href="###" id ="analysis">重新分析</a>
													</p>
											</div>
										</li>
								</ul>
							</div>				
					</div>
			</lz:set>
			//地图自定义控件
			rightControl=$("${lz:js(rightControl)}");

			$("#allmap").append(rightControl);

			rightControl.find("#highSpeed").html(ccclubsMap.highSpeedMiles);
			rightControl.find("#lowSpeed").html(convertTimeDur(ccclubsMap.idleSpeedTime));
			rightControl.find("#stopSpeed").html(convertTimeDur(ccclubsMap.stopTime));
			rightControl.find("#analysis").click(function(){
				var high_objselect=$('#selHighSpeed').get(0);
			　 var high_indexselect=high_objselect.selectedIndex;
			　 var high_valselect=high_objselect.options[high_indexselect].value;
			
				var idle_objselect=$('#selLowSpeed').get(0);
			　 var idle_indexselect=idle_objselect.selectedIndex;
			　 var idle_valselect=idle_objselect.options[idle_indexselect].value;
				
				clearInterval(timer);
				
				 ccclubsMap.analysis(historys,high_valselect,idle_valselect); 
			     rightControl.find("#highSpeed").html(ccclubsMap.highSpeedMiles);
				 rightControl.find("#lowSpeed").html(convertTimeDur(ccclubsMap.idleSpeedTime));
				 rightControl.find("#stopSpeed").html(convertTimeDur(ccclubsMap.stopTime));
				 
				 timer =  setInterval(play,300);//定时器
				 return false;
			});
			rightControl.find("#calculate").click(function(){
				var m=0;
				var lastPostion=null;			
				for(var o in ccclubsMap.markers_obj){
					var position=ccclubsMap.markers_obj[o].position;
					if(lastPostion){
						var d=getDistanceFromGPSXtoY(lastPostion.lat,lastPostion.lng,position.lat,position.lng);
						if(!isNaN(d))m+=d;
					}
					lastPostion = position;
				}
				alert("轨迹里程为"+Math.floor(m/1000)+"公里");
				return false;
			});
			/**
			 * 根据两gps坐标获取距离
			 * @param n1
			 * @param e1
			 * @param n2
			 * @param e2
			 * @return 米
			 */
			function getDistanceFromGPSXtoY(lat_a, lng_a,lat_b, lng_b){
				var pk = (180 / Math.PI);
				var a1 = lat_a / pk;
				var a2 = lng_a / pk;
				var b1 = lat_b / pk;
				var b2 = lng_b / pk;
				var t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
				var t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
				var t3 = Math.sin(a1) * Math.sin(b1);
				var tt = Math.acos(t1 + t2 + t3);
				return 6366000 * tt;
			}
			
			
			//end
	           <lz:set name="controlHtml">		
				<table id="mapControl" style="position: absolute; top: 10px; left: 320px;" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td ><button class="button" type='button' id='optionBtn'>已暂停</button></td>
				    <td width="400" valign="top">
						<div class="slider">
							<div class="block"></div>
							<div class="opacity"></div>
							<div class="background"><div class="strip"></div></div>
						</div>
					</td>
					<td>
						<div id="time"></div>
					</td>
				  </tr>
				</table>
			</lz:set>
			
			//地图自定义控件
			var mapControl=$("${lz:js(controlHtml)}");
			$("#allmap").append(mapControl);
			
			mapControl.find(".block").mousedown(function(event){
				$(this).data("mousedown",true);
			}).mouseup(function(event){
				$(this).data("mousedown",false);
			})
			mapControl.find(".slider").mouseleave(function(event){
				mapControl.find(".block").data("mousedown",false);
			});
			mapControl.find(".slider").mousemove(function(event){
				var block = mapControl.find(".block");
				var strip = mapControl.find(".strip");
				if(!block.data("mousedown"))return;
				var left = event.pageX - strip.offset().left;
				if(left<0 || left>strip.width()-block.width())return;
				block.offset({
					left:event.pageX
				});
				Event.fire("drag",left/(strip.width()-block.width()));
			});
			mapControl.find(".slider").click(function(event){
				var block = mapControl.find(".block");
				var strip = mapControl.find(".strip");
				var left = event.pageX - strip.offset().left;
				if(left<0 || left>strip.width()-block.width())return;
				block.offset({
					left:event.pageX
				});
				Event.fire("drag",left/(strip.width()-block.width()));
			});
			mapControl.find("#optionBtn").click(function(){
				pause = ($(this).text()=="播放中");
				Event.fire("pause");
			});
	            
	        var index = 0;//当前序列
			var pause = false;//是否暂停
			
			//播放轨迹
			function play(){
				if(pause)return;
				index++;
				if(index>=ccclubsMap.markers_obj.length){
					index = 0;
					pause=true;
					Event.fire("pause");
					alert("轨迹已经播放完毕");
					return;
				}
				marker.setPosition(ccclubsMap.markers_obj[index].getPosition());
				Event.fire("play",index/ccclubsMap.markers_obj.length);	
			}
			if(${orderid} !=0){
				if(historys.length==0)
					alert("当前车辆订单没有历史轨迹数据");
				else
					timer =  setInterval(play,300);//定时器
			}
			Event.on("drag",function(scan){
				index = Math.floor(ccclubsMap.markers_obj.length*scan);	
			});
			
			Event.on("pause",function(){
				mapControl.find("#optionBtn").html(pause?"已暂停":"播放中");
			});
			$(function(){//禁止选择
				$(document).bind("selectstart",function(){return false;});
			});
			Event.on("play",function(scan){
				var block = mapControl.find(".block");
				var strip = mapControl.find(".strip");
				var background = mapControl.find(".background");
				background.css("top","0px");
				var left = (strip.width()-block.width())*scan;
				if(block.data("mousedown"))return;
				block.offset({
					left:strip.offset().left+left
				});
				$("#optionBtn").html("播放中");
				$("#time").html($.formatDate("yyyy-MM-dd HH:mm:ss",new Date(ccclubsMap.markers_info_obj[index])));
			});
	            
	            $("li:not(.itemonover)").hover(function() {
	                $(this).addClass("itemonover");
	            },function() {
	              $(this).removeClass("itemonover");
	          	});
	        })

	        function restFrame() {
				var w = document.documentElement.clientWidth;
				var h = document.documentElement.clientHeight;
				var leftMenuWidth = 226; 
				//左边菜单区域的整体高度 
				$("#allmap").css("height", h); 
				$("#allmap").css("width", w - leftMenuWidth);
			 } 
			 
	        function showOrderdetail(id) {
	            //将当前车辆选中，其他车辆选中状态取消
	            var item = "#" + id;
	            $(".dTreeList li").removeClass("itemon");
	            $(".dTreeList li").children(".dTreeList_tool").css("display", "none");
	            $(item).addClass("itemon");
	            $(item).children(".dTreeList_tool").css("display", "block");
	        };
    </script>
    <script>
	//事件监听
		var Event =new (function(){
			var THIS = this;
			this.listenmap={};
			this.on=function(type,func){
				var listens = THIS.listenmap[type];
				if(!listens)
					listens = THIS.listenmap[type] = [];
				if(func){
					listens[listens.length]=func;
				}else{
					for(var o in listens)
						listens[o]();
				}
			}
			this.fire=function(type,param){
				var listens = THIS.listenmap[type];
				if(!listens)
					listens = THIS.listenmap[type] = [];
				for(var o in listens)
					listens[o](param);
			}
		})();
	</script>
	
	</head>
	<body onresize="restFrame();">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<td valign="top" align="center" id="left">
						<dl class="vehAll">
							<dt>
								<span> [ <font color="#FFFF00">${lz:replace(csCar.cscNumber,"^(zjA)|(ZJA)","浙A·")}</font> ] 最近一个月订单</span>
							</dt>
						</dl>
						<div class="group_canvas">
							<s:if test="#request.orders.size==0">
								<ul>
									<li>
										没有找到订单记录
									</li>
								</ul>
							</s:if>
							<s:else>
								<s:iterator value="#request.orders" id="item" status="i">
									<div id="node${item.csoId}" class="dTreeList" title="[ 下单时间：${lz:date(item.csoAddTime,'yyyy-MM-dd HH:mm')} ] 支付帐户：${item.$csoPayMember.csmName}">
										<ul>
											<s:if test="#item.csoId == #request.orderid">
													<li id="${item.csoId}" class="itemon" onclick="javascript:showOrderdetail('${item.csoId}');">
														<span class="dTreeList_title ">${item.csoId} -- ${item.$csoUseMember.csmName}</span>
															<div class="dTreeList_tool">
												</s:if>
												<s:else>
													<li id="${item.csoId}" onclick="javascript:showOrderdetail('${item.csoId}');">
													<span class="dTreeList_title ">${item.csoId} -- ${item.$csoUseMember.csmName}</span>
													<div class="dTreeList_tool" style="display:none;">
												</s:else>
													<p>
														定单状态：${item.csoStatus == 0?'已预订':''}${item.csoStatus == 1?'正在执行':''}${item.csoStatus == 2?'已还车':''}${item.csoStatus == 3?'已取消':''}${item.csoStatus == 4?'已完成':''}${item.csoStatus == 5?'待处理':''}${item.csoStatus == 6?'已退款':''}${item.csoStatus == 7?'已撤销':''}
													</p>
													<p>
														开始时间：${lz:date(item.csoStartTime,'yyyy-MM-dd HH:mm')}
													</p>
													<p>
														结束时间：${lz:date(item.csoFinishTime,'yyyy-MM-dd HH:mm')}
													</p>
													<p>
														驾驶手机：${item.$csoUseMember.csmMobile}
													</p>
													<p>
														取车时间：${lz:date(item.csoTakeTime,'yyyy-MM-dd HH:mm')}
													</p>
													<p>
														还车时间：${lz:date(item.csoRetTime,'yyyy-MM-dd HH:mm')}
													</p>
													<p>
														备注：${item.csoRemark}
													</p>
													<p style="text-align: right; padding-right: 5px;">
														<a href="/admin/control/map_history.do?number=${csCar.cscNumber}&order=${item.csoId}" target="_self">轨迹回放</a>
													</p>
												</div>
											</li>
										</ul>
									</div>
								</s:iterator> 
							</s:else>
					</td>
					<td valign="top" align="center">
						<div id="allmap">
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</body>	
</html>