/*
*上报公里数
*/
Toast_input.show();
reported();
function reported(){
	var cscId=localStorage.getItem("cardnumber");
	if(cscId==''||cscId==null||cscId==undefined){
		Toast_input.show();
	}else{
		$(".toast_p").html("请输入牌号为："+localStorage.getItem("cardnumber")+"的公里数！");
		$("#toast_carno").val(localStorage.getItem("cardnumber"));
		Toast_input.show();
	}
	
}
/*
*上报公里数操作相关按钮
*/
var btn_cancel=document.getElementById("btn_cancel");
btn_cancel.addEventListener("touchend", function(){
	javascript:history.go(-1);
});
var btn_deter=document.getElementById("btn_deter");
btn_deter.addEventListener("touchend", function(){
	if($("#toast_inpit").val()==''||$("#toast_inpit").val()==null||$("#toast_inpit").val()==undefined){
		Toast.show("数据不能为空，请输入里程数！");
	}else{
		reportedAjax($("#toast_carno").val(),$("#toast_inpit").val());
		$("#toast_inpit").val("");
		$("#toast_carno").val("");
		reported();
	}
});
/*
*上报公里数ajax
*/
function reportedAjax(carNo,currKm){
   $.ajax({
	url: "/manage/AjaxAction_maintain.html",
	data: {carNo: carNo,currKm:currKm, playcarname :localStorage.getItem("login_id")},
	cache: false,
	async: false,
       type: "post",
       dataType:"json",
       success: function(data) {
       		if(data.success==true){
       			Toast.show(data.message);
       			setTimeout("javascript:history.go(-1);",3000);
       		}else{
       			 Toast.show(data.message);
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