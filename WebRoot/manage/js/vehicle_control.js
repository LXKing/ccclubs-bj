/*
*车辆上线弹层
*/
function veh_onlie(){
	Toast_input.show();
	$("#toast_inpit").val("");
	$(".toast_p").html("请输入上线密码！");
	$("#toast_inpit").attr("type","password");
	$("#toast_inpit").attr("placeholder","请输入上线密码");
}
/*
*车辆下线弹层
*/
function veh_offlie(){
	Toast_input.show();
	$("#toast_inpit").val("");
	$("#toast_inpit").attr("type","password");
	$(".toast_p").html("请输入下线密码！");
	$("#toast_inpit").attr("placeholder","请输入下线密码");
}
/*
*还原弹出层
*/
function layerload(){
	$("#toast_inpit").attr("type","text");
	$(".toast_p").html("请输入车牌号！");
	$("#toast_inpit").attr("placeholder","请输入车牌号");
	
}

/*
*上下线ajax
*/
function car_status(pwd,usertle){
   $.ajax({
	url: "/manage/AjaxAction_carstatus.html",
	data: {cscOnPsd: pwd , ids: localStorage.getItem("cscId"),usertle:usertle},
	cache: false,
	async: false,
       type: "post",
       dataType:"json",
       success: function(data) {
       		if(data!=""&&data!=null&&data!=undefined){
       			Toast.show(data.status);
			}else{
				Toast.show("系统错误请重新输入");
			}
       },
       cache: false,
       timeout: 5000,
       error: function() {
       	  Toast_input.show();
          Toast.show("系统错误请重新输入");
       }
   });
}
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
	       		localStorage.setItem("cscHost",data.car.cscHost);
	       		loacal=localStorage.getItem("cscId");
				$(".vehicle_title p b").html(data.cscarmodel.cscmName+data.car.cscCarNo);
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
function enter_password(i){
	switch(i)
		{
		case "车辆上线":
		  veh_onlie();//车辆上线方法
		  break;
		case "车辆下线":
		  veh_offlie()//车辆下线方法
		  break;
		case "车辆调度":
		   window.location.href="CarAction_area.html?cschost="+localStorage.getItem("cscHost");
		  break;
		case "查看订单":
		   window.location.href="CarAction_vieworders.html?carid="+loacal+"";
		  break;
		case "车辆信息":
		   window.location.href="CarAction_carInfo.html?carid="+loacal+"";
		  break;
		default:
		  Toast.show("信息有误，请重试！");
		}
}