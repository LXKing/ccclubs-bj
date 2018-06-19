var loacal=localStorage.getItem("cscId");
if(loacal==''||loacal==null||loacal==undefined){
	Toast_input.show();//首次加载请输入会员信息
}
$(".License_bg p").html(localStorage.getItem("cardModel"));
$(".License_bg span").html(localStorage.getItem("cardnumber"));
/*
*按键效果 
*/
$(".open-door").on("touchstart", function(){
	$(this).addClass("door_touch");
});
$(".open-door").on("touchend", function(){
	$(this).removeClass("door_touch");
	var operation=$(this).children("p").html();
	var cscId=localStorage.getItem("cscId");
	if(operation==''||operation==null||operation==undefined){
		Toast.show("触摸信息有错误请重新触摸！");
	}else if(cscId==''||cscId==null||cscId==undefined){
		Toast.show("车辆信息有误请重新输入！");
	}else{
		operate_Ajax(cscId,operation);
	}
});
/*
*结束按钮
*/
$(".end").click(function(){
	localStorage.setItem("cscId","");
	localStorage.setItem("cardnumber","");
	localStorage.setItem("cardModel","");
	$(".License_bg p").html("");
	$(".License_bg span").html("");
	location.href=document.referrer;
})
/*
*刷新按钮动画效果
*/
$(".refresh img").on("touchstart", function(){
	$(this).addClass("loading");
	setTimeout("endflash()", 3000);
})
function endflash(){
	$(".refresh img").removeClass("loading");
	location.reload();
}
/*
*切换车辆相关信息
*/
$(".License_bg").on("touchstart", function(){
	Toast_input.show();
})
/*
*切换车辆相关信息
*/
var btn_cancel=document.getElementById("btn_cancel");
btn_cancel.addEventListener("touchend", function(){
	javascript:history.go(-1);
});
var btn_deter=document.getElementById("btn_deter");
btn_deter.addEventListener("touchend", function(){
	Toast_input.hide();
	var card= $("#toast_inpit").val();
	if(card!=""&&card!=null&&card!=undefined){
		selectcard(card);
		$("#toast_inpit").val("");
	}else{
		Toast_input.show();
		Toast.show("请输入车牌号！");
		$("#toast_inpit").val("");
	}
});
/*
*车牌选择ajax
*/
function selectcard(card){
   $.ajax({
	url: "/manage/AjaxAction_selectcard.html",
	data: {cardnumber: card},
	cache: false,
	async: false,
       type: "post",
       dataType:"json",
       success: function(data) {
       		if(data!=""&&data!=null&&data!=undefined){
	       		localStorage.setItem("cardModel",data.cscarmodel.cscmName);
	       		localStorage.setItem("cardnumber",data.car.cscCarNo);
	       		localStorage.setItem("cscId",data.car.cscId);
	       		$(".License_bg p").html(data.cscarmodel.cscmName);
				$(".License_bg span").html(data.car.cscCarNo);
			}else{
			 	Toast_input.show();
				Toast.show("系统错误请重新输入");
			}
       },
       cache: false,
       timeout: 5000,
       error: function() {
       	  Toast_input.show();
          Toast.show("车牌错误请重新输入");
       }
   });
}
/*
*开关门ajax
*/
function operate_Ajax(cscId,operation){
	$.ajax({
	url: "/manage/AjaxAction_orderoperate.html",
	data: {cscId: cscId,operation: operation},
	cache: false,
	async: false,
       type: "post",
       dataType:"json",
       success: function(data) {
       		Toast.show(data);
       },
       cache: false,
       timeout: 5000,
       error: function() {
           alert("联系管理员！");
       }
   });
}
$(".open-door").css("height",$(".bac-layer").height()/2)