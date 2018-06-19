/* 
 * 日期插件
 * 滑动选取日期
 * qsxiaogang
 * V1.1
 */
(function($) {
	$.fn.date = function(options, Ycallback, Ncallback) {
		//插件默认选项
		var that = $(this);
		var docType = $(this).is('input');
		var datetime = false;
		var nowdate = new Date();
		var indexY = 1,
			indexM = 1,
			indexD = 1;
		var yearScroll = null,
			monthScroll = null,
			dayScroll = null;
		$.fn.date.defaultOptions = {
				title: '请选择日期',
				count: 7,
				mindur: 20,
				beginyear: 2000, //日期--年--份开始
				endyear: 2020, //日期--年--份结束
				beginmonth: 0, //日期--月--份结束
				endmonth: 23, //日期--月--份结束
				beginday: 00, //日期--日--份结束
				endday: 40, //日期--日--份结束

				curdate: false, //打开日期是否定位到当前日期
				theme: "", //控件样式（1：日期，2：日期+时间）
				mode: null, //操作模式（滑动模式）
				event: "click", //打开日期插件默认方式为点击后后弹出日期 
				show: true
			};
			//用户选项覆盖插件默认选项   
		var opts = $.extend(true, {}, $.fn.date.defaultOptions, options);
		if (!opts.show) {
			that.unbind('click');
		} else {
			//绑定事件（默认事件为获取焦点）
			that.bind(opts.event, function() {
				createUL(); //动态生成控件显示的日期
				init_iScrll(); //初始化iscrll
				extendOptions(); //显示控件
				that.blur();
				refreshDate();
				bindButton();
			});
		}

		function refreshDate() {
			yearScroll.refresh();
			monthScroll.refresh();
			dayScroll.refresh();

			resetInitDate();
			yearScroll.scrollTo(0, indexY * 62 - 62, 100, true);
			monthScroll.scrollTo(0, indexM * 62 - 62, 100, true);
			dayScroll.scrollTo(0, indexD * 62 - 62, 100, true);
		}

		function resetInitDate() {
			if (!opts.curdate) {
				return false;
			} else {
				indexY = getYearIndex();
				indexM = getMonthIndex();
				indexD = getDateIndex();
			}
		}

		/*
		 * 初始化日期的位置
		 * */

		function getYearIndex() {
			var result = 1;
			var full_fomart = that.data('value').substr(0, 10);
			$('#yearwrapper ul li').each(function() {
				if ($(this).data('value') === full_fomart) {
					result = $('#yearwrapper ul li').index(this);
					console.log('***当前选择的日期位置为：' + ($('#yearwrapper ul li').index(this)));
				}
			});
			return result;
		}

		function getMonthIndex() {
			var result = 1;
			var hour = that.data('value').substr(11, 2);
			$('#monthwrapper ul li').each(function() {
				if ($(this).html() === hour) {
					console.log('***当前选择的小时位置为：' + ($('#monthwrapper ul li').index(this)));
					result = $('#monthwrapper ul li').index(this);
				}
			});
			return result;
		}

		function getDateIndex() {
			var result = 1;
			var min = that.data('value').substr(14, 2);
			$('#daywrapper ul li').each(function() {
				if ($(this).html() === min) {
					console.log('***当前选择的分钟位置为：' + ($('#daywrapper ul li').index(this)));
					result = $('#daywrapper ul li').index(this);
				}
			});
			return result;
		}


		function bindButton() {
			$("#dateconfirm").unbind('click').click(function() {
				var datestr = $("#yearwrapper ul li").eq(indexY).html() + " " +
					$("#monthwrapper ul li").eq(indexM).html() + ":" +
					$("#daywrapper ul li").eq(Math.round(indexD)).html();
				var datevalue = $("#yearwrapper ul li").eq(indexY).data('value') + " " +
					$("#monthwrapper ul li").eq(indexM).html() + ":" +
					$("#daywrapper ul li").eq(Math.round(indexD)).html();

				if (Ycallback === undefined) {
					if (docType) {
						that.val(datestr);
					} else {
						that.html(datestr);
					}
					that.data('value', datevalue);
				} else {
					Ycallback(datestr,datevalue);
				}
				ModalBox.hide();
			});
			$("#datecancle").click(function() {
				ModalBox.hide();
				if (Ncallback === undefined) {
					return false;
				} else {
					Ncallback(false);
				}
			});
		}

		function extendOptions() {
				ModalBox.show();
			}
			//日期滑动

		function init_iScrll() {
			yearScroll = new iScroll("yearwrapper", {
				snap: "li",
				vScrollbar: false,
				onScrollEnd: function() {
					indexY = (this.y / 62) * (-1) + 1;
					$("#daywrapper ul").html(createDAY_UL());
					dayScroll.refresh();
				}
			});
			monthScroll = new iScroll("monthwrapper", {
				snap: "li",
				vScrollbar: false,
				onScrollEnd: function() {
					indexM = (this.y / 62) * (-1) + 1;
					$("#daywrapper ul").html(createDAY_UL());
					dayScroll.refresh();
				}
			});
			dayScroll = new iScroll("daywrapper", {
				snap: "li",
				vScrollbar: false,
				onScrollEnd: function() {
					indexD = (this.y / 62) * (-1) + 1;
				}
			});
		}

		function checkdays(year, month) {
			var new_year = year; //取当前的年份        
			var new_month = month++; //取下一个月的第一天，方便计算（最后一天不固定）        
			if (month > 12) //如果当前大于12月，则年份转到下一年        
			{
				new_month -= 12; //月份减        
				new_year++; //年份增        
			}
			var new_date = new Date(new_year, new_month, 1); //取当年当月中的第一天        
			return (new Date(new_date.getTime() - 1000 * 60 * 60 * 24)).getDate(); //获取当月最后一天日期    
		}

		function createUL() {
			CreateDateUI();
			$("#yearwrapper ul").html(createYEAR_UL());
			$("#monthwrapper ul").html(createMONTH_UL());
			$("#daywrapper ul").html(createDAY_UL());
		}

		function CreateDateUI() {
			var str = '' +
				'<div class="panel" data-role="panel">' +
				'<div class="panel-header">' + opts.title + '</div>' +
				'<div class="panel-content">' +
				'<div id="datemark"></div>' +
				//'<div id="timemark"><a id="markhour"></a><a id="markminut"></a><a id="marksecond"></a></div>' +
				'<div id="datescroll">' +
				'<div id="yearwrapper">' +
				'<ul></ul>' +
				'</div>' +
				'<div id="monthwrapper">' +
				'<ul></ul>' +
				'</div>' +
				'<div id="daywrapper">' +
				'<ul></ul>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'<div class="panel-footer segmented-control ">' +
				'<a id="dateconfirm" class="platform-switch control-item font-positive ">确  定</a>' +
				'<a id="datecancle" class="platform-switch control-item font-gray">取  消</a>' +
				'</div>';

			$('#ccc-modal-box').html(str);
		}

		//创建 --日期-- 列表

		function createYEAR_UL() {
				var str = "<li>&nbsp;</li>";
				for (var i = 0; i <= opts.count - 1; i++) {
					var _date = new Date().addDays(i);
					var m_monthDefault = _date.getMonth() + 1;
					var m_date = _date.getDate();
					var short_fomart = m_monthDefault + '月' + m_date + '日';
					var full_fomart = '' + _date.getFullYear() + '-' + (m_monthDefault < 10 ? ('0' + m_monthDefault) : m_monthDefault) + '-' + (m_date < 10 ? ('0' + m_date) : m_date);
					str += '<li data-value = ' + full_fomart + '>' + short_fomart + '</li>';
				}
				return str + "<li>&nbsp;</li>";
			}
			//创建 --小时-- 列表

		function createMONTH_UL() {
				var str = "<li>&nbsp;</li>";
				for (var i = opts.beginmonth; i <= opts.endmonth; i++) {
					if (i < 10) {
						i = "0" + i;
					}
					str += '<li>' + i + '</li>';
				}
				return str + "<li>&nbsp;</li>";
			}
			//创建 --分钟-- 列表 ，20分钟为一个间隔

		function createDAY_UL() {
			$("#daywrapper ul").html("");
			var str = "<li>&nbsp;</li>";
			for (var i = opts.beginday; i <= opts.endday;) {
				if (i < 10) {
					str += '<li>' + "0" + i + '</li>';
				} else {
					str += '<li>' + i + '</li>';
				}
				i = i + opts.mindur;
			}
			return str + "<li>&nbsp;</li>";
		}
	};
})(Zepto);
function close_other(){
	ModalBox.hide();
	if (Ncallback === undefined) {
		return false;
	} else {
		Ncallback(false);
	}
}