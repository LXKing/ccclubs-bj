$(document).ready(function () {
	$(document).on("click", "#purNumPlus", function () {
		var num = parseInt($("#purNum").val());
		if (num == 99) {
			return;
		}
		$("#purNum").val(num + 1);
		setSum();
	});
	$(document).on("click", "#purNumMinus", function () {
		var num = parseInt($("#purNum").val());
		if (num == 1) {
			return;
		}
		$("#purNum").val(num - 1);
		setSum();
	});
	$(document).on({keydown:function (event) {
		if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || (event.keyCode >= 37 && event.keyCode <= 40) || event.keyCode == 8 || event.keyCode == 46)) {
			return false;
		}
	}, keyup:function () {
		var val = $("#purNum").val().replace(/[^\d]/g, "");
		if (val == "" || val == 0) {
			val = 1;
		}
		this.value = parseInt(val);
		setSum();
	}}, "#purNum");
	function setSum() {
		var price = parseInt($("#purPr").val());
		var num = parseInt($("#purNum").val().replace(/[^\d]/g, ""));
		$("#purSum").text(parseInt(price * num));
	}
	
	$(document).on("click", "#register-btn", function () {
		if($('#csiId').val()==''){
			alert('请选择您需要购买的现金券金额。');
		}else{
			var csiId = $('#couponid').val();
			var purNum = $('#purNum').val();
			location.href = 'account_buyCouponsubmit.html?t='+new Date().getTime()+'&csiId='+csiId+'&purNum='+purNum;
		}
	});
});

