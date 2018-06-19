var mapObj, geolocation;
$(function() {
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		document.getElementById("o_from").onpropertychange = target_from;
	} else {
		document.getElementById("o_from").oninput = target_from;
	}
	$('#o_from').focus(function() {
		$("#o_to_lst").hide();
		$('#bar_to, #bar_pmob').show();
		$('.icon-from-to').hide();
		if ($('#o_from').val().length > 0) {
			$('#from_remove').show();
		}
	});
	$('#o_to').focus(function() {
		$("#o_from_lst").hide();
		$('.icon-from-to').hide();
		if ($('#o_to').val().length > 0) {
			$('#to_remove').show();
		}
	});
	$('#pmob').focus(function() {
		$('.icon-from-to').hide();
		if ($('#pmob').val().length > 0) {
			$('#phone_remove').show();
		}
	});
	$('#from_remove').click(function() {
		$('#o_from').val('').focus();
	});
	$('#to_remove').click(function() {
		$('#o_to').val('').focus();
	});
	$('#phone_remove').click(function() {
		$('#pmob').val('').focus();
	});
});

function hide_lst(o) {
	if ($('#' + o).val().length > 0) {
		return;
	}
	$('#' + o + '_lst').hide()
}
var target = '',
	id_start = 0;

function target_from() {
	if ($('#o_from').val().length > 0) {
		$('#from_remove').show();
	} else {
		$('#from_remove').hide();
	}
	if ($('#o_from').val().length <= 0) {
		$('#bar_to, #bar_pmob').show();
	}
	target = 'o_from';
	id_start = 0;
	autoSearch()
};

function target_to() {
	if ($('#o_to').val().length > 0) {
		$('#to_remove').show();
	} else {
		$('#to_remove').hide();
	}
	if ($('#o_to').val().length <= 0) {
		$('#bar_from, #bar_pmob').show();
	}
	target = 'o_to';
	id_start = 20;
	autoSearch()
};

function target_pmob() {
	if ($('#pmob').val().length > 0) {
		$('#phone_remove').show();
	} else {
		$('#phone_remove').hide();
	}
	storage.set('pmob_status', 0);
};

function autoSearch() {
	if (!mapObj) {
		mapObj = new AMap.Map("iCenter");
	}
	_latitude = undefined;
	_longitude = undefined;
	console.log('***文字改变，当前经纬度重置为：' + _latitude + ';' + _longitude);
	var keywords = document.getElementById(target).value;
	var auto;
	mapObj.plugin(["AMap.PlaceSearch"], function() {
		var autoOptions = {
			city: cityname
		};
		auto = new AMap.PlaceSearch(autoOptions);
		if (keywords.length > 0) {
			AMap.event.addListener(auto, "complete", autocomplete_CallBack);
			auto.search(keywords);
		} else {
			document.getElementById(target + "_lst").style.display = "none";
		}
	});
}

function autocomplete_CallBack(data) {
	if ($('#' + target).val().length <= 0) {
		$('#bar_to, #bar_pmob').show();
		document.getElementById(target + "_lst").style.display = "none";
		return;
	}
	var d = data.poiList.pois;
	console.log('length:' + d.length);
	if (d.length > 0) {
		var lngX = d[0].location.getLng();
		var latY = d[0].location.getLat();
		console.log(lngX + ';' + latY);
		var resultStr = "";
		var tipArr = [];
		tipArr = data.poiList.pois;
		if (typeof(tipArr) != 'undefined' && tipArr.length > 0) {
			for (var i = 0; i < tipArr.length; i++) {
				resultStr += "<div class='addr_lst' data-lat=" + tipArr[i].location.getLat() + " data-lon=" + tipArr[i].location.getLng() + " id='divid" + (id_start + i + 1) + "' onclick='selectResult(" + (id_start + i) + ")' onmouseout='onmouseout_MarkerStyle(" + (id_start + i + 1) + ",this)' style=\"font-size: 14px; line-height:25px;cursor:pointer;padding:8px 5px 8px 5px; overflow:hidden; height:36px;\">" + tipArr[i].name + "<span style='color:#C1C1C1;'>&nbsp;&nbsp;" + tipArr[i].address + "</span></div>";
			}
			//resultStr = resultStr + "<div class='tip_up addr_lst' id='id_" + target + "' style='font-size: 14px;line-height:25px; cursor:pointer;padding:8px 5px 8px 5px; height:23px;background:#eee' >收起</div>";
			if (target == 'o_from') {
				$('#bar_to').hide();
				$('#bar_pmob').hide();
			} else {
				$('#bar_pmob').hide();
			}
		} else {
			resultStr = "";
			$('#bar_to, #bar_pmob').show();
		}
		//常用地址
		var address = Cache.get("address");
		var arrayaddress;
		if(address!=""&&address!=null){
			var array=address.split(";");
			for(var i=0;i<array.length;i++){
				var array_temp = array[i].split(",");
				var common_address="<div class='addr_lst' data-lat='"+array_temp[0]+"' data-lon='"+array_temp[1]+"' id='divid2' onclick='selectResult(1)' " +
						"onmouseout='onmouseout_MarkerStyle(2,this)' style='font-size: 14px; line-height:25px;cursor:pointer;padding:8px 5px 8px 5px; " +
						"overflow:hidden; height:36px;'>"+array_temp[2]+"<span style='color:#C1C1C1;'>&nbsp;&nbsp;"+array_temp[2]+"</span>" +
						"<p style='float:right;color:#666666'>常用地址</p></div>";
				resultStr=common_address+resultStr;
			}
		}
		
		document.getElementById(target + "_lst").innerHTML = resultStr;
		document.getElementById(target + "_lst").style.display = "block";
	}
}

function openMarkerTipById1(pointid, thiss) {
	thiss.style.background = '#CAE1FF';
}

function onmouseout_MarkerStyle(pointid, thiss) {
	thiss.style.background = "";
}

function selectResult(index) {
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		document.getElementById(target).onpropertychange = null;
		document.getElementById(target).onfocus = focus_callback;
	}
	var text = document.getElementById("divid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g, "");
	document.getElementById(target).value = text.replace('amp;', '');
	document.getElementById(target + "_lst").style.display = "none";
	$('#bar_to, #bar_pmob').show();
	if (target == 'o_from') {
		_latitude = $('#divid' + (index + 1)).attr('data-lat');
		_longitude = $('#divid' + (index + 1)).attr('data-lon');
		console.log('***当前经纬度重置为：' + _latitude + ';' + _longitude);
		Cache.save('latitude', _latitude);
		Cache.save('longitude', _longitude);
		Cache.save('location',text.replace('amp;', ''));
		//order.init();
		//order.randomDriNum();
	}
}

function focus_callback() {
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		document.getElementById(target).onpropertychange = autoSearch;
	}
}

function placeSearch_CallBack(data) {
		var poiArr = data.poiList.pois;
		clearInterval(_interval);
		o_from.removeAttr('disabled');
		if (poiArr) {
			o_from.val(poiArr[0].name);
			br = true;
			o_from.attr('placeholder', '你在哪里');
		} else {
			o_from.attr('placeholder', '无法获取位置，请搜索');
			console.log('***获取位置错误！！！');
		}
	}