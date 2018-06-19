$(document).ready(function() {
	
});

function submitAddress() {
	var txtAddress = $.trim($("#txtAddress").val());
	var txtContact = $.trim($("#txtContact").val());
	var txtPhone = $.trim($("#txtPhone").val());
	
    if(txtAddress===""){
		var msg = "请输入寄送地址";
		Toast.show(msg);
		return false;
	}
    if(txtContact===""){
    	var msg = "请输入紧急联系人";
    	Toast.show(msg);
    	return false;
    }
    if(txtPhone===""){
    	var msg = "请输入紧急联系人电话";
    	Toast.show(msg);
    	return false;
    }
	
	btn.attr("onclick", "return false");
	btn.addClass('disabled');
    btn.text("正在提交。。。");

    // 需要禁用按钮 
    /*var btn = $('#save-btn');
	$.ajax({
		url: '/m/register_saveAddress.html',
		type: "POST",
		data: {
				txtAddress:txtAddress
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
					location.href = '/m/home/index.html';
					break;
				case "101":
					location.href = '/m/403.html';
					break;
				case "103":
					var msg = "请输入寄送地址";
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
				btn.text("提交");
				btn.removeClass('disabled');
			    btn.attr("onclick", "submit()");
			}
		});*/
}