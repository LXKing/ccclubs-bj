var ApiModelNames={CsLongOrderFee:'CsLongOrderFee',CsLongOrderDetail:'CsLongOrderDetail',CsLongOrder:'CsLongOrder',CsSpecialModel:'CsSpecialModel',CsSpecialCar:'CsSpecialCar',CsUnitMt:'CsUnitMt',CsUnitStatement:'CsUnitStatement',CsPlace:'CsPlace',CsUnitPerson:'CsUnitPerson',CsUnitGroup:'CsUnitGroup',TbPassCheck:'TbPassCheck',TbEmployee:'TbEmployee',TbDepartment:'TbDepartment',CsCashRecord:'CsCashRecord',SrvHelp:'SrvHelp',CsShopOrderItem:'CsShopOrderItem',CsShopOrder:'CsShopOrder',CsCartItem:'CsCartItem',CsGrowType:'CsGrowType',SrvProject:'SrvProject',TbSaleContract:'TbSaleContract',TbSaleQuote:'TbSaleQuote',TbSaleCustom:'TbSaleCustom',TbCustomGroup:'TbCustomGroup',TbSaleBattle:'TbSaleBattle',CsStrategy:'CsStrategy',SrvLock:'SrvLock',SrvTransaction:'SrvTransaction',CsOrderTransaction:'CsOrderTransaction',CsOrderLock:'CsOrderLock',TbAbAffirm:'TbAbAffirm',CsUnitBillDetail:'CsUnitBillDetail',CsUnitBill:'CsUnitBill',CsUnitAirport:'CsUnitAirport',CsUnitFee:'CsUnitFee',CsUnitOrder:'CsUnitOrder',CsUnitUser:'CsUnitUser',CsUnitInfo:'CsUnitInfo',CsContent:'CsContent',CsElecHistory:'CsElecHistory',CsHistoryState:'CsHistoryState',CsState:'CsState',CsAlarm:'CsAlarm',CsRemote:'CsRemote',CsScheduling:'CsScheduling',CsMaintain:'CsMaintain',CsRepairAdr:'CsRepairAdr',CsRepair:'CsRepair',CsOrderLog:'CsOrderLog',CsOrderDetail:'CsOrderDetail',CsOrder:'CsOrder',CsViolat:'CsViolat',CsTrouble:'CsTrouble',CsUpdate:'CsUpdate',CsCarOn:'CsCarOn',CsRechargeCard:'CsRechargeCard',CsEvCard:'CsEvCard',CsPowerPile:'CsPowerPile',CsOutlets:'CsOutlets',CsCar:'CsCar',CsCarModel:'CsCarModel',CsItem:'CsItem',CsPackInfo:'CsPackInfo',CsPack:'CsPack',CsPrice:'CsPrice',CsGoods:'CsGoods',CsProduct:'CsProduct',CsRule:'CsRule',CsCreditBill:'CsCreditBill',CsAlipayLog:'CsAlipayLog',CsAlipayRefund:'CsAlipayRefund',CsAlipayRecord:'CsAlipayRecord',CsInvoice:'CsInvoice',CsRefund:'CsRefund',CsOperRecord:'CsOperRecord',CsRecord:'CsRecord',CsCreditRecord:'CsCreditRecord',CsCreditCard:'CsCreditCard',CsGrowRecord:'CsGrowRecord',CsIntegralRecord:'CsIntegralRecord',CsUseRecord:'CsUseRecord',CsCoin:'CsCoin',CsFreeHour:'CsFreeHour',CsUserPack:'CsUserPack',CsGift:'CsGift',CsUserType:'CsUserType',CsMemberMemo:'CsMemberMemo',CsMessage:'CsMessage',CsAddress:'CsAddress',CsMemberGroup:'CsMemberGroup',CsMemberShip:'CsMemberShip',CsMemberInfo:'CsMemberInfo',CsMember:'CsMember',CsQuestAnswer:'CsQuestAnswer',CsQuestTemplate:'CsQuestTemplate',CsChatMessage:'CsChatMessage',CsChatTalker:'CsChatTalker',CsCallIn:'CsCallIn',CsServe:'CsServe',CsCare:'CsCare',CsRevisit:'CsRevisit',CsComplain:'CsComplain',CsVisitIp:'CsVisitIp',CsVisitDetail:'CsVisitDetail',CsChannelIndex:'CsChannelIndex',CsChannelLog:'CsChannelLog',CsChannel:'CsChannel',CsPropagate:'CsPropagate',CsLuckyResult:'CsLuckyResult',CsLuckyItem:'CsLuckyItem',CsLucky:'CsLucky',CsPromotion:'CsPromotion',CsActivityIn:'CsActivityIn',CsActivity:'CsActivity',CsChjSign:'CsChjSign',CsShareIndex:'CsShareIndex',CsPresent:'CsPresent',CsMarketIndex:'CsMarketIndex',CsMarketLog:'CsMarketLog',CsMarketPlan:'CsMarketPlan',CsSaleOpport:'CsSaleOpport',CsSaleSurvey:'CsSaleSurvey',CsSaleEvent:'CsSaleEvent',CsCarPhoto:'CsCarPhoto',CsUpgradeLog:'CsUpgradeLog',CsBug:'CsBug',CsError:'CsError',CsEventHeed:'CsEventHeed',CsEvent:'CsEvent',CsEventType:'CsEventType',CsTodo:'CsTodo',CsNotice:'CsNotice',CsLog:'CsLog',CsEmail:'CsEmail',CsSmsMt:'CsSmsMt',SrvOauth:'SrvOauth',SrvApiLog:'SrvApiLog',CsWxMo:'CsWxMo',CsWxMt:'CsWxMt',CsWxContent:'CsWxContent',CsWxMenu:'CsWxMenu',CsWxOrder:'CsWxOrder',CsGasStation:'CsGasStation',CsLimitTime:'CsLimitTime',CsArea:'CsArea',CsTask:'CsTask',CsConfig:'CsConfig',CsArgument:'CsArgument',CsFeeTypeSet:'CsFeeTypeSet',CsIntegralRule:'CsIntegralRule',CsSmsTemplate:'CsSmsTemplate',CsHoliday:'CsHoliday',SrvApiClient:'SrvApiClient',SrvApi:'SrvApi',SrvActionScript:'SrvActionScript',SrvQuery:'SrvQuery',SrvProperty:'SrvProperty',CsProvid:'CsProvid',CsRecordType:'CsRecordType',CsRecordSubject:'CsRecordSubject',CsOper:'CsOper',TbSolve:'TbSolve',TbProblem:'TbProblem',CsOutsideQr:'CsOutsideQr',SrvModel:'SrvModel',SrvWorkflow:'SrvWorkflow',SrvHost:'SrvHost',SrvUserExp:'SrvUserExp',CsArtifRecord:'CsArtifRecord',SrvUser:'SrvUser'};
var ApiModelPackage="com.ccclubs.model.";
var ApiMethods="get,list,page,Get,count,getKeyValue".split(",");
for(var o in ApiModelNames){
	window[o]={name:o};
	for(var p in ApiMethods){
		var m=ApiMethods[p];
		window[o][m]=$.api(ApiModelPackage+o,m);
	}
}

$(function(){
	if(window["uploadUrl"]){
		var regx = /\/([^\.^\/]*)\.do/g;	
		var results = regx.exec(window.location.href);
		window["uploadUrl"]+=(window["uploadUrl"].indexOf("?")>-1?"&":"?")+"app="+results[1];
	}
	if($("#head-panel").size()>0 || $("#desktop #shortcuts").size()>0){
		setDefaultIcon();
	}
});

/********************************首页***********************************/
//输入远程控制
function onRemoteScript(){
	var scriptInput = $("<textarea class='input' id='script' style='width:400px;height:200px;' name='script'/>");
	scriptInput.dialog({
		title:"输入远程脚本"
		,onOk:function(){
			$.getObject("/admin/message_script.do",{script:scriptInput.val()},function(data){
				showTips(data.message);
			});
		}
	});	
}

function showMessage(content){
	var message = top.indexframe.header.$("#message");
	if(!message.data("init")){
		message.data("init",true);
		message.mouseleave(function(){
			message.data("mouseenter",false);
			message.fadeOut(1000);
		});
		message.mouseenter(function(){
			message.data("mouseenter",true);
			message.stop();
			message.fadeTo("fast",1);
		});
	}
	
	message.html(content);
	message.stop();
	message.fadeTo("fast",1);
	
	if(!message.data("mouseenter")){
		setTimeout(function(){
			message.fadeOut(1000);
		},10000);
	}
}
function doModal(url){
	top.$.showModalDialog({url:url},{},window);
}

/********************************头部***********************************/
function addIconLink(name,icon,func,link){
	if($("#head-panel").size()>0){
		var a=$("<a class='icon' href='"+(link?link:"javascript:void(0);")+"'></a>");
		a.attr("name",name);
		a.html("<img src=\""+icon+"\" /> <span>"+name+"</span>");
		a.insertBefore($("#panel .option .icon:last"));
		a.click(func);
	}else if($("#desktop #shortcuts").size()>0){
		var shortcut = $("<div class='item'><a class='link' href='javascript:void(0);'><img class='icon'/><span class='name'></span><span class='mask'></span></a></div>");
		shortcut.find(".icon").attr("src",icon);
		shortcut.find(".name").html(name);
		shortcut.find(".link").click(func);
		shortcut.find(".link").attr("href",(link?link:"javascript:void(0);"));
		shortcut.prependTo("#desktop #shortcuts");
	}
}
function setDefaultIcon(){
	
	//添加地图监控图标
	addIconLink("地图监控","/admin/images/icons/GPS.png",function(){
		parent.link('/admin/control/map.do');
	});
	
	//添加订单计算器图标
	addIconLink("订单计算器","/admin/images/icons/Calculator full.png",function(){
		top.$.showModalDialog({url:'/admin/service/order_calculator.do'},{},window);
	});
	
	//添加订单图标
	addIconLink("订单管理","/admin/images/icons/200882564216312778012.png",function(){		
		parent.link('/admin/service/order.do'); 
	});
		
	//添加会员管理
	addIconLink("会员管理","/admin/images/icons/20088256427828778017.png",function(){
		parent.link('/admin/user/member.do');
	});
}
/**
* 风格主题选择
**/
function themes()
{		
	//(同步方式)ajax取风格主题选择页面的html代码
	//什么叫同步方式？是指当ajax数据取回来后，代码才执行下一步
	var html = $.ajax({
	  	url: "themes.jsp?type=list", //网址
	  	cache: false,//是否缓存数据
	  	async: false//是否异步
	}).responseText;
	//把取回来的html显示到一个对话框上面
	var dialog=$(html).dialog({
			title:"风格主题选择",//对话框标题
			modal: true	,//是否模式对话框
			onOk:function() {
				parent.window.location='style.do?type=select&theme='+$("#themes_select").val()+'&css='+$("#styles_select").val();
				dialog.close();
			},
			onCancel: function() {
				dialog.close();
			}	
	});
}
