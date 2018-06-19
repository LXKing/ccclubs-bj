$(document).ready(function() {
	//
});

function submitDriver() {
	var txtLicnum = $.trim($("#txtLicnum").val());
	var txtLicPic = $.trim($("#txtLicPic").val());
	
    if(txtLicnum===""){
		var msg = "请输入驾照号码";
		Toast.show(msg);
		return false;
	}

	if(typeof(txtLicPic) == "undefined" || txtLicPic==="undefined" ||txtLicPic==="" || txtLicPic.length<10){
		var msg = "请上传证件照片";
		Toast.show(msg);
		return false;
	}	
   
    // 需要禁用按钮     
    btn.attr("onclick", "return false");
	btn.addClass('disabled');
    btn.text("正在提交。。。");
   
    // 需要禁用按钮 
    /*var btn = $('#save-btn');
	$.ajax({
		url: '/m/register_saveDriver.html',
		type: "POST",
		data: {
				txtLicnum: txtLicnum,
				txtLicPic:txtLicPic
		},
		async: false,
		beforeSend: function(XMLHttpRequest){
			btn.attr("onclick", "return false");
			btn.addClass('disabled');
		    btn.text("正在提交。。。");
		},
		success: function(data) {
			switch (data) {
				case "100":
					location.href = '/m/register_address.html';
					break;
				case "101":
					location.href = '/m/403.html';
					break;
				case "103":
					var msg = "请输入证件号码";
					Toast.show(msg);
					break;
				case "104":
					var msg = "请上传证件照片";
					Toast.show(msg);
					break;
				case "9999":
					var msg = "系统错误,请稍候再试";
					Toast.show(msg);
					break;
				default:
					var msg = "未知的返回";
					Toast.show(msg);
					return;
				}
			},
			complete: function(XMLHttpRequest, textStatus){
				btn.text("下一步");
				btn.removeClass('disabled');
			    btn.attr("onclick", "submit()");
			}
		});*/
}