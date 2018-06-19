/*
*扫一扫ajax提交
*/
function patrolcheck(carnum,userId){
   $.ajax({
	url: "/manage/mgAjaxAction_patrolcheck.html",
	data: {csoqAdder: userId ,csoqCar: carnum ,csoqUserPosStart: latitude.toString() ,csoqUsePosEnd:longitude.toString(),csoqType :1},
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
          Toast.show("提交出错请重试");
       }
   });
}
