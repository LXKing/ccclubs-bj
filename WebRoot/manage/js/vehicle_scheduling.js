/**
 * 手指触摸按钮效果变化事件
 */
var btn=document.getElementById("btn");
btn.addEventListener("touchend", function(){
	schecdul($(".select-right").val())
});
$(".vehicle_title p b").html(localStorage.getItem("cardnumber"));
/*
*车辆调度
*/
function schecdul(outlets){
	$.ajax({
	url: "/manage/AjaxAction_dispatch.html",
	data: {outlets: outlets,cscId:localStorage.getItem("cscId")},
	cache: false,
	async: false,
       type: "post",
       dataType:"json",
       success: function(data) {
   			Toast.show(data.status);
       },
       cache: false,
       timeout: 5000,
       error: function() {
          Toast.show("会员信息错误请重新输入");
       }
   });
}
/*
*网点加载
*/
$(".select-left").change(function(){
	loadarea($(this).val());
})
function loadarea(i){
	$.ajax({
	url: "/manage/AjaxAction_outles.html",
	data: {area_id: i},
	cache: false,
	async: false,
       type: "post",
       dataType:"json",
       success: function(data) {
       		var src="";
       		for(var i=0;data.length>i;i++){
       			src+="<option value="+data[i].csoId+">"+data[i].csoName+"</option>";
       		}
       		$(".select-right").html(src);
       },
       cache: false,
       timeout: 5000,
       error: function() {
       	  Toast_input.show();
          Toast.show("会员信息错误请重新输入");
       }
   });
}