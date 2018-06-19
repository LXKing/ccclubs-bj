$(document).ready(function() {
	document.querySelector('#ccc-identity').onclick = function() {
		ModalBox.show();
		getSelectType($("#j-identity-type").val());
	};
	
});

//选中证件类型
var getSelectType = function(value){
	var type_list = $('.table-modal-view li');
	
	type_list.each(function(){
		var model_id = $(this).attr("id");
		var tmp_id = "table-modal-view-cell-" + value;
		if(model_id===tmp_id){
			$("#"+model_id).addClass("active");
		}else{
			$("#"+model_id).removeClass("active");
		}
	});
}

function setIdentity(name,value){
	ModalBox.hide();
	$("#j-identity-type").val(value);
	$('#ccc-identity .badge').html(name);
}


function submitIdentity() {
	var txtName = $.trim($("#txtName").val());
	var txtCertifyNum = $.trim($("#txtCertifyNum").val());
	var txtVerPic = $.trim($("#txtVerPic").val());
	var type = $("#j-identity-type").val();
	
    if(txtName===""){
		var msg = "请输入姓名";
		Toast.show(msg);
		return false;
	}

	if(txtCertifyNum===""){
		var msg = "请输入证件号码";
		Toast.show(msg);
		return false;
	}

	if(typeof(txtVerPic) == "undefined" || txtVerPic==="undefined" ||txtVerPic==="" || txtVerPic.length<10){
		var msg = "请上传证件照片";
		Toast.show(msg);
		return false;
	}
	
    // 需要禁用按钮     
    btn.attr("onclick", "return false");
	btn.addClass('disabled');
    btn.text("正在提交。。。");
}