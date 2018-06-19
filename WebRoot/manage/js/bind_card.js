/*
*绑定会员卡验证人员是否存在
*/
function selectuser(m){
	$.ajax({
	url: "/manage/AjaxAction_selectuser.html",
	data: {usertle: m},
	cache: false,
	async: false,
       type: "post",
       dataType:"json",
       success: function(data) {
       		if(data!=""&&data!=null&&data!=undefined){
       			if(data=="1"){
       				Toast_input.show();
       				Toast.show("会员已经绑定过不能重复操作");
       			}else if(data=="2"){
       				Toast_input.show();
       				Toast.show("会员信息错误请重新输入");
       			}else{
	       			localStorage.setItem("csmName",data.csmName);
	       			localStorage.setItem("user_number",data.csmId);
	       			$("#card_name").val(data.csmName);
	       			$("#user_number").val(data.csmId);
       			}
			}else{
				Toast.show("会员信息错误请重新输入");
			}
       },
       cache: false,
       timeout: 5000,
       error: function() {
       	  Toast_input.show();
          Toast.show("会员信息错误请重新输入");
       }
   });
}
