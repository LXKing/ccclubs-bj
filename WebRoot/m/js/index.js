var o_from = $('#o_from');
var _interval;
var cityname = '*';
var _latitude, _longitude;

$(document).ready(function() {
	Cache.save('area_id', "");
	Cache.save('dot_id', "");
	$('#ccc-start-time').date({
		title: "取车时间",
		curdate: true
	}, function(datestr, datevalue) {
		setStartTimeUI(datestr, datevalue);
		OrderInfoOperate.setStartTime(datevalue);
	});

	$('#ccc-finish-time').date({
		title: "还车时间",
		curdate: true,
		count: 30
	}, function(datestr, datevalue) {
		setFinishTimeUI(datestr, datevalue);
		OrderInfoOperate.setFinishTime(datevalue);
	});

	document.querySelector('#ccc-city').onclick = function() {
		$('#ccc-modal-box').html('');
		$.get('ajax_city.html?t=' + new Date().getTime(), function(data) {
			ModalBox.show();
			$('#ccc-modal-box').html(data);
			var city_value = Cache.get('host_value').data;
			getSelectModel(city_value);
		});
	};

	document.querySelector('#ccc-model').onclick = function() {
		$('#ccc-modal-box').html('');
		var host = Cache.get('host_value').data;
		$.get('ajax_model.html?host=' + host, function(data) {
			ModalBox.show();
			$('#ccc-modal-box').html(data);
			var model_value = Cache.get('model_value').data;
			getSelectModel(model_value);
		});
	};

	document.querySelector('#index-reserve-btn').onclick = function() {
		if (OrderInfoOperate.getLatitude() === null || OrderInfoOperate.getLongitude() === null) {
			Toast.show('还未获取到位置信息，请手动输入您的位置。');
			return;
		}else{
			var params = "?latitude="+Cache.get('latitude').data+"&longitude="+Cache.get('longitude').data;
			var startTime = new Date(Cache.get('start_time').data).toStringL('F');
			var finishTime = new Date(Cache.get('finish_time').data).toStringL('F');
			startTime=Cache.get('start_time').data;
			finishTime=Cache.get('finish_time').data;
			
			if( (isNull(startTime)!='空') && (isNull(finishTime)!='空') ){
				params = params + "&startTime=" + startTime + "&finishTime=" + finishTime;
			}
			params = params + "&host=" + Cache.get('host_value').data + "&carModel=" + Cache.get('model_value').data +"&csoArea="+Cache.get('area_id').data+"&csoOutlets="+Cache.get('dot_id').data;
			
			location.href = 'reserve.html' + params;
		}
	};
	get_position_loading();
	init_orderinfo();
	nativeApiGeo();
});

//选中车型
var getSelectModel = function(model_value){
	var model_list = $('.table-modal-view li');
	
	model_list.each(function(){
		var model_id = $(this).attr("id");
		var tmp_id = "table-modal-view-cell-" + model_value;
		if(model_id==tmp_id){
			$("#"+model_id).removeClass("table-modal-view-cell");
			$("#"+model_id).addClass("table-modal-view-cell active");
		}else{
			$("#"+model_id).removeClass("table-modal-view-cell active");
			$("#"+model_id).addClass("table-modal-view-cell");
		}
	});
}

var getSelectCity = function(city_value){
	var city_list = $('.table-modal-view li');
	city_list.each(function(){
		var model_id = $(this).attr("id");
		var tmp_id = "table-city-view-cell-" + city_value;
		if(model_id!=tmp_id){
			$(this).find("a").removeClass("active");
		}
	});
}

var init_orderinfo = function() {
	//初始化城市
	if (OrderInfoOperate.getCityValue() === null || OrderInfoOperate.getCityName() === null) {
		$.ajax({
			type: 'get',
    		url: 'ajax_getmHost.html',
    		data:{},
    		dataType: 'json',
    		success: function(data){
    			OrderInfoOperate.setCity(data.shName, data.shId);
    			cityname = data.shName;
    		},
    		error: function(xhr, type){
    			Toast.show('通讯失败，请检查网络');
                return;
    		}
		});
	} else {
		$.ajax({
			type: 'get',
    		url: 'ajax_getmHost.html',
    		data:{},
    		dataType: 'json',
    		success: function(data){
    			$('#ccc-city .badge').html(data.shName);
    			OrderInfoOperate.setCity(data.shName, data.shId);
    			cityname = data.shName;
    		},
    		error: function(xhr, type){
    			$('#ccc-city .badge').html(OrderInfoOperate.getCityName());
    			cityname = OrderInfoOperate.getCityName();
                return;
    		}
		});
	}
	//初始化车型
	if (OrderInfoOperate.getModelValue() === null || OrderInfoOperate.getModelName() === null) {
		OrderInfoOperate.setModel('不 限', -1);
	} else {
		$('#ccc-model .badge').html(OrderInfoOperate.getModelName());
	}
	//初始化取，还车时间
	Cache.remove('start_time');
	Cache.remove('finish_time');
	if (OrderInfoOperate.getStartTime() === null || OrderInfoOperate.getFinishTime() === null) {
		//设置取还，车时间
		init_ordertime();
	} else {
		// 判断缓存时间，如果时间间隔超过20分钟，重新设置取还车时间
		var startTime = Cache.get('start_time');
		var finishTime = Cache.get('finish_time');
		if (dateDiffD('M', new Date(startTime.cacheTime), new Date()) > 20) {
			init_ordertime();
		} else {
			setStartTimeUI(new Date(startTime.data).toStringL(), new Date(startTime.data).toStringL('F'));
			OrderInfoOperate.setStartTime(new Date(startTime.data).toStringL('F'));

			setFinishTimeUI(new Date(finishTime.data).toStringL(), new Date(finishTime.data).toStringL('F'));
			OrderInfoOperate.setFinishTime(new Date(finishTime.data).toStringL('F'));
		}
	}
}

// 初始化取、还车时间
var init_ordertime = function() {
	var _date = new Date();
	var hour = _date.getHours();
	var min = _date.getMinutes();
	if (min < 20 && min >= 0) {
		_date.setMinutes(20);
		setStartTimeUI(_date.toStringL(), _date.toStringL('F'));
		OrderInfoOperate.setStartTime(_date.toStringL('F'));
		var finish = _date.addHours(2);
		setFinishTimeUI(_date.toStringL(), _date.toStringL('F'));
		OrderInfoOperate.setFinishTime(_date.toStringL('F'));
	} else {
		if (min < 40 && min >= 20) {
			_date.setMinutes(40);
			setStartTimeUI(_date.toStringL(), _date.toStringL('F'));
			OrderInfoOperate.setStartTime(_date.toStringL('F'));
			var finish = _date.addHours(2);
			setFinishTimeUI(_date.toStringL(), _date.toStringL('F'));
			OrderInfoOperate.setFinishTime(_date.toStringL('F'));
		} else {
			_date.setMinutes(0);
			_date.addHours(1);
			setStartTimeUI(_date.toStringL(), _date.toStringL('F'));
			OrderInfoOperate.setStartTime(_date.toStringL('F'));
			var finish = _date.addHours(2);
			setFinishTimeUI(_date.toStringL(), _date.toStringL('F'));
			OrderInfoOperate.setFinishTime(_date.toStringL('F'));
		}
	}
}

var setStartTimeUI = function(str, value) {
	$('#ccc-start-time').html(str);
	$('#ccc-start-time').data('value', value);
}

var setFinishTimeUI = function(str, value) {
	$('#ccc-finish-time').html(str);
	$('#ccc-finish-time').data('value', value);
}

var nativeApiGeo = function() {
	console.log('use native.geolocation...');
	if (OrderInfoOperate.getLatitude() === null || OrderInfoOperate.getLongitude() === null) {
		console.log('*** js定位不可用，本地浏览器定位败 ***');
		navigatorGeo();
	} else {
		// 判断缓存时间，如果时间间隔超过20分钟，重新设置取还车时间
		var latitude = Cache.get('latitude');
		var longitude = Cache.get('longitude');
		if (dateDiffD('M', new Date(latitude.cacheTime), new Date()) > 20) {
			console.log('*** js定位不可用，本地浏览器定位败 ***');
			navigatorGeo();
		}else{
			console.log('*** 使用本地storerage定位 ***');
			init_position(OrderInfoOperate.getLatitude(), OrderInfoOperate.getLongitude());
		}
	}
}

var navigatorGeo = function() {
	console.log('use navigator.geolocation...');
	navigator.geolocation.getCurrentPosition(function(e) {
		_latitude = e.coords.latitude;
		_longitude = e.coords.longitude;
		init_position(_latitude, _longitude);
		console.log('*** 浏览器定位 ***' + _latitude + ' , ' + _longitude);
	}, function(error) {
		console.log('*** 浏览器定位失败 ***');
		init_positon_err();
	});
}

var init_position = function(_latitude, _longitude) {
	console.log('***获取当前经纬度成功！经纬度为：' + _latitude + ';' + _longitude);
	Cache.save('latitude', _latitude);
	Cache.save('longitude', _longitude);
	console.log('*** location name search ***');
	$.getJSON('http://restapi.amap.com/v3/geocode/regeo?location=' + _longitude + ',' + _latitude + 
			'&extensions=all&output=json&key=0f190aa16f94b25924efa17c42abaa13&radius=100&coordsys=gps&callback=?', function(data) {
		if (data.status == 1) {
			console.log('JSON.stringify(data) : ' + JSON.stringify(data));
			var first_road = typeof data.regeocode.roadinters[0] == 'undefined' ? '' : data.regeocode.roadinters[0].first_name;
			var roadinters_dis = typeof data.regeocode.roadinters[0] == 'undefined' ? 1000 : data.regeocode.roadinters[0].distance;
			var second_road = typeof data.regeocode.roadinters[0] == 'undefined' ? '' : data.regeocode.roadinters[0].second_name;
			var name = typeof data.regeocode.addressComponent.neighborhood.name == 'undefined' ? '' : data.regeocode.addressComponent.neighborhood.name;
			var addr_cnt = name;
			console.log('first_road;roadinters_dis;second_road;addr_cnt : ' + first_road + ';' + roadinters_dis + ';' + second_road + ';' + addr_cnt);
			if (roadinters_dis <= 100.0) {
				if (addr_cnt.length <= 0) {
					if (typeof data.regeocode.pois[0] != 'undefined') {
						addr_cnt = typeof data.regeocode.pois[0].name == 'undefined' ? '' : data.regeocode.pois[0].name;
						console.log('*** roadinters_dis <= 100.0 -> data.regeocode.pois[0].name : ' + data.regeocode.pois[0].name);
					}
				}
				addr_cnt = first_road + second_road + '口' + addr_cnt;
				if (addr_cnt.length > 16) addr_cnt = first_road + second_road + '口';
			}
			if (roadinters_dis > 100.0 && roadinters_dis <= 200.0) {
				if (addr_cnt.length <= 0) {
					if (typeof data.regeocode.pois[0] != 'undefined') {
						addr_cnt = typeof data.regeocode.pois[0].name == 'undefined' ? '' : data.regeocode.pois[0].name;
						console.log('*** roadinters_dis > 100.0 && roadinters_dis <= 200.0 -> data.regeocode.pois[0].name : ' + data.regeocode.pois[0].name);
					}
				}
				addr_cnt = first_road + addr_cnt;
				if (second_road.length > 0) addr_cnt = addr_cnt + '(近' + second_road + ')';
			}
			if (roadinters_dis > 200.0) {
				var address = '';
				if (addr_cnt.length <= 0) {
					if (typeof data.regeocode.pois[0] != 'undefined') {
						addr_cnt = typeof data.regeocode.pois[0].name == 'undefined' ? '' : data.regeocode.pois[0].name;
						address = typeof data.regeocode.pois[0].address == 'undefined' ? '' : data.regeocode.pois[0].address;
						console.log('*** roadinters_dis > 200.0 -> data.regeocode.pois[0].name : ' + data.regeocode.pois[0].name);
					}
				}
				var street = typeof data.regeocode.addressComponent.streetNumber.street == 'undefined' ? '' : data.regeocode.addressComponent.streetNumber.street;
				var number = typeof data.regeocode.addressComponent.streetNumber.number == 'undefined' ? '' : data.regeocode.addressComponent.streetNumber.number + '';
				console.log('*** street;number : ' + street + ';' + number);
				if (street.length > 0) {
					number.length > 0 ? addr_cnt = street + number + '号' + addr_cnt : addr_cnt = street + addr_cnt;
				} else {
					street = address;
					addr_cnt = street + addr_cnt;
				}
				if (addr_cnt.length <= 0) {
					var district = typeof data.regeocode.addressComponent.district == 'undefined' ? '' : data.regeocode.addressComponent.district;
					var township = typeof data.regeocode.addressComponent.township == 'undefined' ? '' : data.regeocode.addressComponent.township;
					addr_cnt = district + township;
				}
			}
			console.log('*** current location info : ' + addr_cnt);
			clearInterval(_interval);
			o_from.removeAttr('disabled');
			o_from.val(addr_cnt);
			Cache.save('location',addr_cnt);
			br = true;
			o_from.attr('placeholder', '你在哪里');
		} else {
			init_positon_err();
		}
	});
}

var init_positon_err = function() {
	clearInterval(_interval);
	o_from.removeAttr('disabled');
	o_from.attr('placeholder', '无法获取位置，请搜索');
	console.log('***navigator获取用户当前坐标失败！***');
}

var get_position_loading = function() {
	console.log('正在执行定位中操作...');
	o_from.attr('placeholder', '正在获取当前位置');
	var b = 0,
		a = "";
	_interval = setInterval(function() {
		if (50 !== b) {
			var c = (b) % 4;
			switch (c) {
				case 0:
					a = "";
					break;
				case 1:
					a = ".";
					break;
				case 2:
					a = "..";
					break;
				case 3:
					a = "...";
					break;
				default:
					a = "...";
					break;
			}
		} else {
			clearInterval(_interval);
			o_from.removeAttr('disabled');
			o_from.attr('placeholder', '无法获取位置，请搜索');
			return;
		}
		o_from.attr('placeholder', '正在获取当前位置' + a);
		console.log('placeholder', '正在获取当前位置' + a);
		b++;
	}, 300);
	o_from.attr('disabled', 'disabled');
}
function input_onblur(){
	var o=$("#o_from").val();
	$("#o_from").val()=o;
}

//切换选车方式
$("#takeswith").click(function(){
	$("#takedot").show();
	$("#takegbs").hide();
	selectarea();
})
$("#swith_dot").click(function(){
	$("#takedot").hide();
	$("#takegbs").show();
})
//首页选择网点确定事件
var area_id="";
var dot_id="";
function mineDot(){
	//如果用户没有滑动区域时执行
	if(area_val==""||area_val==null){
		Cache.save('area_id', first_area_id);
		Cache.save('area_val', first_area_name);
	}else{
		Cache.save('area_id', area_id);
		Cache.save('area_val', area_val);
	}
	//如果用户没有滑动网点时执行
	if(dot_val=='' || dot_val==null){
		Cache.save('dot_id', "");
		Cache.save('dot_val', "所有网点");
	}else{
		Cache.save('dot_id', dot_id);
		Cache.save('dot_val', dot_val);
	}
	
	$("#areadot").val(Cache.get('area_val').data+"-"+Cache.get('dot_val').data);
	$("#ccc-area-box").hide();
}
//点击选择地区
function selectarea(){
	$("#ccc-area-box").show();
	$("#ccc-area-box").html("");
	$.get('area-dot.html', function(data) {
		$('#ccc-area-box').html(data);
	});
}