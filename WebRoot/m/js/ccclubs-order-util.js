var OrderInfoOperate = (function() {
	/**
	 * 设置纬度
	 * @param {Object} value
	 */
	var _get_latitude = function getLatitude(value) {
		var data = Cache.get('latitude');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	var _get_longitude = function getLongitude(value) {
		var data = Cache.get('longitude');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	var _get_location = function getLocation(value) {
		var data = Cache.get('location');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	/**
	 * 设置当前选择城市
	 * @param {Object} 城市名字
	 * @param {Object} value
	 */
	var _set_city = function setCity(name, value) {
		$('#ccc-city .badge').html(name);
		cityname = name;
		Cache.save('host_value', value);
		Cache.save('host_name', name);
		ModalBox.hide();
	}

	var _get_city_value = function getCityValue() {
		var data = Cache.get('host_value');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	var _get_city_name = function getCityName() {
		var data = Cache.get('host_name');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	var _set_model = function setModel(name, value) {
		$('#ccc-model .badge').html(name);
		Cache.save('model_value', value);
		Cache.save('model_name', name);
		ModalBox.hide();
	}

	var _get_model_name = function getModel() {
		var data = Cache.get('model_name');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	var _get_model_value = function getModel() {
		var data = Cache.get('model_value');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	var _get_start_time = function getStartTime() {
		var data = Cache.get('start_time');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	var _set_start_time = function setStartTime(value) {
		Cache.save('start_time', value);
	}

	var _get_finish_time = function getFinishTime() {
		var data = Cache.get('finish_time');
		if (data === null || data === undefined) {
			return null;
		}
		return data.data;
	}

	var _set_finish_time = function setFinishTime(value) {
		Cache.save('finish_time', value);
	}

	return {
		getLatitude: _get_latitude,
		getLongitude: _get_longitude,
		setCity: _set_city,
		getCityName: _get_city_name,
		getCityValue: _get_city_value,
		setModel: _set_model,
		getModelName: _get_model_name,
		getModelValue: _get_model_value,
		getStartTime: _get_start_time,
		setStartTime: _set_start_time,
		getFinishTime: _get_finish_time,
		setFinishTime: _set_finish_time
	};
})();

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
