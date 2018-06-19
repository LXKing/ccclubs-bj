Date.prototype.addDays = Date.prototype.addDays || function(days) {
	this.setDate(this.getDate() + days);
	return this;
}
Date.prototype.addHours = Date.prototype.addHours || function(hours) {
	this.setHours(this.getHours() + hours);
	return this;
}
Date.prototype.addMins = Date.prototype.addMins || function(mins) {
	this.setMinutes(this.getMinutes() + mins);
	return this;
}
Date.prototype.toStringL = Date.prototype.toStringL || function(format) {
	var m_monthDefault = this.getMonth() + 1;
	var m_date = this.getDate();
	var m_hour = this.getHours();
	var m_min = this.getMinutes();
	var short_fomart = m_monthDefault + '月' + m_date + '日' + ' ' + (m_hour < 10 ? ('0' + m_hour) : m_hour) + ':' + (m_min < 10 ? ('0' + m_min) : m_min);
	var full_fomart = '' + this.getFullYear() + '-' + (m_monthDefault < 10 ? ('0' + m_monthDefault) : m_monthDefault) + '-' + (m_date < 10 ? ('0' + m_date) : m_date) + ' ' + (m_hour < 10 ? ('0' + m_hour) : m_hour) + ':' + (m_min < 10 ? ('0' + m_min) : m_min);

	if (format === 'F')
		return full_fomart
	return short_fomart;
}

/**
 * 计算时间间隔
 * @param {Object} interval
 * @param {Object} date1
 * @param {Object} date2
 */
function dateDiff(interval, date1, date2) {
	var objInterval = {
		"D": 1000 * 60 * 60 * 24,
		"H": 1000 * 60 * 60,
		"M": 1000 * 60,
		"S": 1000,
		"T": 1
	};
	interval = interval.toUpperCase();
	var dt1 = new Date(Date.parse(date1.replace(/-/g, "/")));
	var dt2 = new Date(Date.parse(date2.replace(/-/g, "/")));
	try {
		return Math.round((dt2.getTime() - dt1.getTime()) / eval("objInterval." + interval));
	} catch (e) {
		return e.message;
	}
}

/**
 * 计算时间间隔
 * @param {Object} interval
 * @param {Object} date1
 * @param {Object} date2
 */
function dateDiffD(interval, date1, date2) {
	var objInterval = {
		"D": 1000 * 60 * 60 * 24,
		"H": 1000 * 60 * 60,
		"M": 1000 * 60,
		"S": 1000,
		"T": 1
	};
	interval = interval.toUpperCase();
	//var dt1 = new Date(Date.parse(date1.replace(/-/g, "/")));
	//var dt2 = new Date(Date.parse(date2.replace(/-/g, "/")));
	try {
		return Math.round((dt2.getTime() - dt1.getTime()) / eval("objInterval." + interval));
	} catch (e) {
		return e.message;
	}
}