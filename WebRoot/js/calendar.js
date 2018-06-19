var _dateMap = {};

/**
* 静态方法集
* @name _angtian
* @type object
* @private
*/
var _angtian = {
    /**
    * 格式化数字，不足两位前面补0
    * @param {number} num 要格式化的数字
    * @name _angtian#formatNum
    * @function
    * @return string
    */
    formatNum: function(num) {
        return num.toString().replace(/^(\d)$/, "0$1")
    },
    /**
    * 将日期对象/日期字会串格式化为指定日期字符串
    * @param {object|string} vArg	可为日期对象或日期字符串，日期对象格式new Date(yyyy, mm, dd)，日期字符串格式yyyy-mm|n-dd|d
    * @name _angtian#formatStrDate
    * @function
    * @return string yyyy-mm-dd
    */
    formatStrDate: function(vArg) {
        switch (typeof vArg) {
            case "string":
                vArg = vArg.split(/-|\//g);
                return vArg[0] + "-" + this.formatNum(vArg[1]) + "-" + this.formatNum(vArg[2]);
                break;
            case "object":
                return vArg.getFullYear() + "-" + this.formatNum(vArg.getMonth() + 1) + "-" + this.formatNum(vArg.getDate());
                break;
        }
    },
    /**
    * 将日期字符串格式化为数字
    * @param {string} sDate	日期字符串，格式yyyy-mm|m-dd|d
    * @name _angtian#formatIntDate
    * @function
    * @return number yyyymmdd
    */
    formatIntDate: function(sDate) {
        return this.formatStrDate(sDate).replace(/-|\//g, "")
    },
    /**
    * 获取指定日期（yyyy-mm-dd）的前1~3天/后1~3天数据
    * @param {string} dateName 日期名字
    * @param {string} sDate	日期字符串（yyyy-mm-dd）
    * @name _angtian#getThreeDays
    * @function
    * @return object
    */
    getThreeDays: function(sDate, dateName) {
        var oTmp = {},
			aDate = sDate.split(/-|\//g),
			sDate, i;
        /*		for(i = 0; i < 7; i++) {			
        sDate = this.formatStrDate(new Date(aDate[0], aDate[1] - 1, aDate[2] - 0 + (i - 3)));
        oTmp[sDate] = dateName + (i != 3 ? (i < 3 ? "\u524d" : "\u540e") + Math.abs(i - 3) + "\u5929" : "");
        i > 2 && (_dateMap[sDate] = dateName + (i != 3 ? (i < 3 ? "\u524d" : "\u540e") + Math.abs(i - 3) + "\u5929" : ""))
        }*/
        return oTmp
    },
    /**
    * 向对象中追加不存在的属性
    * @param {object} obj		目标对象
    * @param {object} oData	要添加的对象
    * @name _angtian#addObject
    * @function
    * @return object
    */
    addObject: function(obj, oData) {
        for (var p in oData) obj[p] || (obj[p] = oData[p])
    },
    /**
    * 获取目标元素针对于文档的位置
    * @param {element} element 目标元素
    * @name Calendar#getPos
    * @function
    * @return object {left: 元素左端距文档左侧位置, top: 元素左端距文档左侧位置, right: 元素右端距文档左侧位置, bottom: 元素底端距文档上方位置}
    */
    getPos: function(element) {
        var iScrollTop = document.documentElement.scrollTop || document.body.scrollTop, //documentElement.scrollTop要获取当前页面的滚动条纵坐标位置
			iScrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft,
			iPos = element.getBoundingClientRect(); //获取坐标的 等同于OFFset
        /*document.getElementById('t2').innerHTML=document.body.scrollTop*/
        return { top: iPos.top + iScrollTop, left: iPos.left + iScrollLeft, right: iPos.right + iScrollLeft, bottom: iPos.bottom + iScrollTop }
    },
    /**
    * 元素选择器
    * @param {string} sArg		#id/.clssName/tagName
    * @param {object} context	可选，上下文
    * @name _angtian#$
    * @function
    * @return element 为id时返回元素
    * @return elements 为className|tagName时返回元素集
    */
    $: function(sArg, context) {
        switch (sArg.charAt(0)) {
            case "#":
                return document.getElementById(sArg.substring(1));
                break;
            case ".":
                var reg = new RegExp("(^|\\s)" + sArg.substring(1) + "(\\s|$)"),
					arr = [],
					aEl = _angtian.$("*", context),
					i;
                for (i = 0; i < aEl.length; i++) reg.test(aEl[i].className) && arr.push(aEl[i]);
                return arr;
                break;
            default:
                return (context || document).getElementsByTagName(sArg);
                break;
        }
    },
    /**
    * 查找元素位置
    * @param {string} vArg 元素或样式名，当vArg为样式名时，返回第一个匹配元素在元素集中的位置
    * @param {object} arr 	元素集
    * @name _angtian#indexOf
    * @function
    * @return number	元素在元素集中的位置，如果没有找到返回-1
    */
    indexOf: function(vArg, arr) {
        for (var i = arr.length; i--; ) {
            if (typeof vArg === "string" ? this.hasClass(arr[i].children[0] || arr[i], vArg) : vArg == arr[i]) return i
        }
        return -1;
    },
    /**
    * 判断目标元素是否包含指定的className
    * @param {object} element		目标元素
    * @param {string} className	要检测的className
    * @name _angtian#hasClass
    * @function
    * @return Boolean
    */
    hasClass: function(element, className) {
        return new RegExp("(^|\\s)" + className + "(\\s|$)").test(element.className)
    },
    /**
    * 给目标元素添加className
    * @param {object} element		目标元素
    * @param {string} className	要添加的className
    * @name Calendar#addClass
    * @function
    */
    addClass: function(element, className) {
        var arr = element.className.split(/\s+/);
        this.hasClass(element, className) || arr.push(className);
        element.className = arr.join(" ").replace(/(^\s*)|(\s*$)/, "")
    },
    /**
    * 删除目标元素className
    * @param {object} element		目标元素
    * @param {string} className	要删除的className
    * @name _angtian#removeClass
    * @function
    */
    removeClass: function(element, className) {
        element.className = element.className.replace(new RegExp("(^|\\s)" + className + "(\\s|$)", "g"), "").split(/\s+/).join(" ")
    },
    /**
    * 给目标元素绑定事件
    * @param {object} element		目标元素
    * @param {string} type 		事件类型
    * @param {function} handler	处理函数
    * @name _angtian#on
    * @function
    */
    on: function(element, type, handler) {
        if (element !== null) {
            element.addEventListener ? element.addEventListener(type, handler, false) : element.attachEvent("on" + type, handler)
        }
    },
    /**
    * 阻止事件冒泡和默认事件
    * @param {event} e
    * @name _angtian#halt
    * @function
    */
    halt: function(e) {
        e = e || event;
        e.preventDefault ? e.preventDefault() : e.returnValue = !1;
        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0
    },
    /**
    * 判断元素A元素是否包含B元素
    * @param {element} A
    * @param {element} B
    * @name _angtian#contains
    * @function
    * @return Boolean
    */
    contains: function(A, B) {
        if (A.contains) {
            return A.contains(B)
        }
        else if (A.compareDocumentPosition) {
            return !!(A.compareDocumentPosition(B) & 16)
        }
    }
};

/**
* 外部调用方式
*/
var $CAL = _angtian;
/**
* 日历模板
* @name template
* @type Array
*/
var template = [
	"<div class=\"cal-container\">",
		"<dl>",
			"<dt class=\"date\"></dt>",
			"<dt><strong style=\"color:#0077b3\">日</strong></dt>",
			"<dt>一</dt>",
			"<dt>二</dt>",
			"<dt>三</dt>",
			"<dt>四</dt>",
			"<dt>五</dt>",
			"<dt><strong style=\"color:#0077b3\">六</strong></dt>",
			"<dd></dd>",
		"</dl>",
	"</div>"
];
/**
* 节假日名字
* @name _dateName
* @type Object
* @private
*/
var _dateName = {
    "today": "今天",
    "yuandan": "元旦",
    "chuxi": "除夕",
    "chunjie": "春节",
    "yuanxiao": "元宵",
    "qingming": "清明",
    "wuyi": "五一",
    "duanwu": "端午",
    "zhongqiu": "中秋",
    "guoqing": "国庆"
};
/**
* 2012——2020年节假日数据
* @name _holidays
* @type Object
* @private
*/
var _holidays = {
    today: [_angtian.formatStrDate(today)],
    yuandan: ["2012-01-01", "2013-01-01", "2014-01-01", "2015-01-01", "2016-01-01", "2017-01-01", "2018-01-01", "2019-01-01", "2020-01-01"],
    chuxi: ["2012-01-22", "2013-02-09", "2014-01-30", "2015-02-18", "2016-02-07", "2017-01-27", "2018-02-15", "2019-02-04", "2020-01-24"],
    chunjie: ["2012-01-23", "2013-02-10", "2014-01-31", "2015-02-19", "2016-02-08", "2017-01-28", "2018-02-16", "2019-02-05", "2020-01-25"],
    yuanxiao: ["2012-02-06", "2013-02-24", "2014-2-14", "2015-03-05", "2016-02-22", "2017-02-11", "2018-03-02", "2019-02-19", "2020-02-8"],
    qingming: ["2012-04-04", "2013-04-04", "2014-04-05", "2015-04-05", "2016-04-04", "2017-04-04", "2018-04-05", "2019-04-05", "2020-04-04"],
    wuyi: ["2012-05-01", "2013-05-01", "2014-05-01", "2015-05-01", "2016-05-01", "2017-05-01", "2018-05-01", "2019-05-01", "2020-05-01"],
    duanwu: ["2012-06-23", "2013-06-12", "2014-06-02", "2015-06-20", "2016-06-09", "2017-05-30", "2018-06-18", "2019-06-07", "2020-06-25"],
    zhongqiu: ["2012-09-30", "2013-09-19", "2014-09-08", "2015-09-27", "2016-09-15", "2017-10-04", "2018-09-24", "2019-09-13", "2020-10-01"],
    guoqing: ["2012-10-01", "2013-10-01", "2014-10-01", "2015-10-01", "2016-10-01", "2017-10-01", "2018-10-01", "2019-10-01", "2020-10-01"]
};
//生成节假日提示信息地图
(function() {
    var p, i;
    for (p in _holidays) {
        if (p == "today") continue;
        for (i = 0; i < _holidays[p].length; i++) {
            _angtian.addObject(_dateMap, _angtian.getThreeDays(_holidays[p][i], _dateName[p]));
        }
    }
})();
/**
* 日历构造函数
* @constructor
* @param {object} config 配置参数
*/
function Calendar() {
    this._init.apply(this, arguments)
}
Calendar.prototype = {
    constructor: Calendar,
    /**
    * 匹配日期字符串yyyy-mm-dd或yyyy/mm/dd
    * @name Calendar.reg
    * @type Object
    */
    reg: /-|\//g,
    /**
    * 匹配日期字符串2012-01-01——2020-12-31 格式yyyy-mm|m-dd|d
    * @name Calendar.rDate
    * @type Object
    */
    rDate: /^20(1[2-9]|20)-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\d|3[0-1])$/,
    /**
    * 日历初始化
    * @name Calendar#_init
    * @param {object} config 日历参数配置
    * @function
    * @private
    */
    _init: function(config) {
        config = config || {};
        /**
        * 日历容器ID（如果值为空, 则生成不重复ID）
        * @name Calendar.id
        * @type String
        */
        this.id = config.isPopup ? "C_" + (+new Date()) : config.id.replace(/^#/, "") || "C_" + (+new Date());
        /**
        * 日历容器
        * @name Calendar.container
        * @type Object
        */
        this.container = _angtian.$("#" + this.id) || document.createElement("div");
        /**
        * 是否开启select选择年月，默认值false
        * @name Calendar.isSelect
        * @type Boolean
        */
        this.isSelect = config.isSelect;
        /**
        * 是否显示上月按钮，默认值false
        * @name Calendar.isPrevBtn
        * @type Boolean
        */
        this.isPrevBtn = config.isPrevBtn;
        /**
        * 是否显示上月按钮，默认值false
        * @name Calendar.isNextBtn
        * @type Boolean
        */
        this.isNextBtn = config.isNextBtn;
        /**
        * 是否显示关闭按钮，默认值false
        * @name Calendar.isCloseBtn
        * @type Boolean
        */
        this.isCloseBtn = config.isCloseBtn;
        /**
        * 是否特殊显示节假日，默认值false
        * @name Calendar.isHoliday
        * @type Boolean
        */
        this.isHoliday = config.isHoliday;
        /**
        * 是否提示节假日前1~3天/后1~3天，默认值false
        * @name Calendar.isHolidayTips
        * @type Boolean
        */
        this.isHolidayTips = config.isHolidayTips;
        /**
        * 是否弹出显示日历，默认值false
        * @name Calendar.isPopup
        * @type Boolean
        */
        this.isPopup = config.isPopup;
        /**
        * 是否设置文本框为只读，默认值false
        * @name Calendar.isReadonly
        * @type Boolean
        */
        this.isReadonly = config.isReadonly;
        /**
        * 是否显示日期信息，默认值false
        * @name Calendar.isDateInfo
        * @type Boolean
        */
        this.isDateInfo = config.isDateInfo;
        /**
        * 日期信息className，默认值"date-info"
        * @name Calendar.dateInfoClass
        * @type String
        */
        this.dateInfoClass = config.dateInfoClass || "date-info";
        /**
        * 日历是否包含提示信息，默认值false
        * @name Calendar.isMessage
        * @type Boolean
        */
        this.isMessage = config.isMessage;
        /**
        * 日历提示信息内容，默认值为空
        * @name Calendar.sMessage
        * @type String
        */
        this.sMessage = config.sMessage || "";
        /**
        * 存放出发日期日历实例
        * @name Calendar.CalStart
        * @type Object
        */
        this.CalStart = config.CalStart || null;
        /**
        * 标记日历实例是否为出发日历
        * @name Calendar.isCalStart
        * @type Boolean
        */
        this.isCalStart = config.isCalStart;
        /**
        * 存放返程日期日历实例
        * @name Calendar.CalEnd
        * @type Object
        */
        this.CalEnd = config.CalEnd || null;
        /**
        * 标记日历实例是否为返程日历
        * @name Calendar.isCalEnd
        * @type Boolean
        */
        this.isCalEnd = config.isCalEnd;
        /**
        * 日历个数,默认1个日历
        * @name Calendar.count
        * @type Number
        */
        this.count = config.count || 1;
        /**
        * 上下月切换步长，默认与日历个数一致，整区域切换
        * @name Calendar.monthStep
        * @type Number
        */
        this.monthStep = config.monthStep || this.count;
        /**
        * 显示位置微调，在日历原有的位置进行偏移微调(left值, top值)，满足个性化需求
        * @name Calendar.revise
        * @type Object
        */
        this.revise = { left: 0, top: 0 };
        /**
        * 触发显示日历的元素
        * @name Calendar.triggerNode
        * @type Object
        */
        this.triggerNode = _angtian.$("#" + config.id.replace(/^#/, ""));
        /**
        * 设置日历渲染日期，格式new Date(yyyy, mm, dd)
        * @name Calendar.date
        * @type Object
        */
        this.date = config.date || new Date;
        /**
        * 日历年份，只读
        * @name Calendar.year
        * @type Number
        */
        this.year = this.date.getFullYear();
        /**
        * 日历月份，只读
        * @name Calendar.month
        * @type Number
        */
        this.month = _angtian.formatNum(this.date.getMonth() + 1);
        /**
        * 设置出发日期，格式(yyyy-mm-dd)
        * @name Calendar.startDate
        * @type String
        */
        this.startDate = config.startDate;
        /**
        * 设置返程日期，格式(yyyy-mm-dd)
        * @name Calendar.endDate
        * @type String
        */
        this.endDate = config.endDate;
        /**
        * 设置默认选择的日期，增加高亮显示，格式(yyyy, mm, dd)
        * @name Calendar.selectDate
        * @type String
        */
        this.selectDate = config.selectDate && _angtian.formatStrDate(config.selectDate);
        /**
        * 指定日历可选范围 mindate:最小日期, maxdate:最大日期。格式new Date(yyyy, mm, dd)
        * @name Calendar.range
        * @type Object
        */
        this.range = config.range || { mindate: null, maxdate: null };
        /**
        * 日历创建完毕回调方法
        * @name Calendar#onComplete
        * @function
        */
        this.onComplete = config.onComplete || function() { };
        /**
        * 日历关闭回调方法
        * @name Calendar#onClose
        * @function
        */
        this.onClose = config.onClose || function() { };
        /**
        * 选择日期回调方法
        * @name Calendar#onSelectDate
        * @function
        */
        this.onSelectDate = config.onSelectDate || function() { };
        //创建日历
        this._create();
        //初次渲染
        this.render();
        //添加事件
        this._addEvent();
    },
    /**
    * 创建日历结构
    * @name Calendar#_create
    * @function
    * @private
    */
    _create: function() {
        var aTmp = [],
			i = 0,
			oIframe = null,
			oDiv = document.createElement("div"),
			oMsg = document.createElement("div");
        //显示上月按钮
        //aTmp.push("<div class=\"line_calendar\"></div>");
        this.isPrevBtn && aTmp.push("<span class=\"cal-prev\">prev</span>");
        //显示下月按钮
        this.isNextBtn && aTmp.push("<span class=\"cal-next\">next</span>");
        //显示关闭按钮
        //this.isCloseBtn && aTmp.push("<span class=\"cal-close\">close</span>");


        //生成日历结构
        for (i = this.count; i--; ) aTmp = aTmp.concat(template);

        //配置日历容器
        oDiv.className = "calendar";
        oDiv.innerHTML = aTmp.join("");
        if (!this.isPrevBtn && !this.isNextBtn && !this.isCloseBtn) oDiv.style.paddingLeft = oDiv.style.paddingRight = "5px";
        this.container.id = this.id;
        this.container.appendChild(oDiv);

        //插入信息容器
        if (this.isMessage) {
            this.oMsg = oMsg;
            oMsg.className = "cal-msg";
            oMsg.innerHTML = this.sMessage;
            oMsg.style.display = this.sMessage ? "block" : "none";
            this.container.insertBefore(oMsg, oDiv);
        }

        //修复ie6无法遮盖select的bug
        !!window.ActiveXObject && !window.XMLHttpRequest && (oIframe = document.createElement("iframe"), this.container.appendChild(oIframe));

        //如果页面中没有日历容器则创建一个
        document.getElementById(this.id) || document.body.appendChild(this.container);
        //如果是ie6, 设置容器内的iframe的宽高
        if (oIframe) {
            var style = oIframe.style;
            style.position = "absolute";
            style.top = style.left = "-1px";
            style.filter = "alpha(opacity=0)";
            style.zIndex = 9999;
            style.border = 0;
            style.width = this.container.offsetWidth + "px";
            style.height = this.container.offsetHeight + "px"
        }

        //如果是弹出式日历则先将其隐藏, 并设置绝对定位
        this.isPopup && (this.hide().container.style.position = "absolute");
        this.isPopup && (this.hide().container.style.zIndex = 9999);
        //触发元素为输入框时的相关设置
        if (this.triggerNode.tagName.toUpperCase() === "INPUT") {
            //如果设置了只读, 修改触发元素的readonly
            this.isReadonly && (this.triggerNode.readOnly = true);
            //如果设置了显示日期信息, 则创建日期信息标签
            if (this.isDateInfo) {
                this.triggerNodeParent = document.createElement("div");
                this.oDateInfo = document.createElement("span");
                this.oDateInfo.className = this.dateInfoClass;
                this.triggerNode.style.position = "absolute";
                this.triggerNodeParent.style.position = "relative";
                this.triggerNodeParent.style.display = "inline-block";
                //this.triggerNodeParent.style.styleFloat  = "left";
                this.triggerNodeParent.style.width = this.triggerNode.offsetWidth + "px";
                this.triggerNodeParent.style.height = this.triggerNode.offsetHeight + "px";
                this.triggerNode.parentNode.insertBefore(this.triggerNodeParent, this.triggerNode);
                this.triggerNodeParent.appendChild(this.triggerNode);
                this.triggerNodeParent.appendChild(this.oDateInfo)
            }
            //如果输入框有值
            this.triggerNode.value != "" && this.isHolidayTips && this.setDateInfo();
        }

        //日历创建完毕回调方法
        this.onComplete()
    },
    /**
    * 画日历
    * @name Calendar#_draw
    * @function
    * @private		 
    */
    _draw: function(oCal, date) {
        var that = this,
			oDt = oCal.getElementsByTagName("dt")[0],
			oDd = oCal.getElementsByTagName("dd")[0],
			oTmpY = document.createDocumentFragment(),
			oTmpM = document.createDocumentFragment(),
			oOption, oFrag, oDiv, i;
        //select年月
        if (this.isSelect) {
            oDiv = document.createElement("div");
            this.selectYear = document.createElement("select");
            this.selectMonth = document.createElement("select");

            oFrag = document.createDocumentFragment();
            for (i = (new Date).getFullYear(); i >= 1900; i--) {
                oOption = document.createElement("option");
                oOption.value = oOption.innerHTML = i;
                oOption.selected = this.year == i;
                oTmpY.appendChild(oOption)
            }
            for (i = 1; i <= 12; i++) {
                oOption = document.createElement("option");
                oOption.value = oOption.innerHTML = i;
                oOption.selected = this.month == i;
                oTmpM.appendChild(oOption)
            }

            this.selectYear.appendChild(oTmpY);
            this.selectMonth.appendChild(oTmpM);

            oDiv.appendChild(this.selectYear);
            oDiv.appendChild(this.selectMonth);
            oDiv.appendChild(document.createTextNode("\u6708"));
            oDiv.insertBefore(document.createTextNode("\u5e74"), this.selectMonth);

            oDt.innerHTML = "";
            oDt.appendChild(oDiv);

            this.selectYear.onchange = this.selectMonth.onchange = function() {
                that.render(new Date(that.selectYear.value, that.selectMonth.value - 1))
            }
        }
        //文字年月
        else {
            oDt.innerHTML = date.year + "年" + date.month + "\月";
        }
        //日期
        oDd.innerHTML = "";
        oDd.appendChild(this._createDays(date.year, date.month))
    },
    /**
    * 生成日期
    * @name Calendar#_createDays
    * @function
    * @private		 
    */
    _createDays: function(year, month) {
        var iDays = new Date(year, month, 0).getDate(),
			iFirstDay = new Date(year, month - 1, 1).getDay(),
			arr = [],
			result = [],
			oFarg = document.createDocumentFragment(),
			i, len, sValue, oA, iCur;

        for (i = iFirstDay; i--; ) arr.push(0);
        for (i = 1; i <= iDays; i++) arr.push(i);

        while (arr.length) {
            for (i = 0, len = arr.length; i < len; i++) {
                if (arr.length) {
                    oA = document.createElement("a");
                    sValue = arr.shift();
                    if (!sValue) {
                        oA.className = "disabled";
                        oA.innerHTML = "&nbsp;"
                    }
                    else {
                        oA.href = "javascript:;";
                        oA.innerHTML = sValue;
                        oA["data-date"] = _angtian.formatStrDate(year + "-" + month + "-" + sValue);

                        iCur = _angtian.formatIntDate(oA["data-date"]);

                        //指定范围
                        if (this.range.mindate) {
                            iCur < (_angtian.formatIntDate(this.range.mindate)) && (oA.className = "disabled");
                        }
                        if (this.range.maxdate) {
                            iCur > (_angtian.formatIntDate(this.range.maxdate)) && (oA.className = "disabled");
                        }

                        //节假日处理
                        if (this.isHoliday) {
                            for (var className in _dateName) {
                                if (oA.className == "disabled") continue;
                                this._isHoliday(oA, className)
                            }
                        }

                        //指定了日期则增加选中状态
                        this.selectDate == oA["data-date"] && ((oA.children[0] || oA).className = "selected");

                        //开始日期
                        this.startDate == oA["data-date"] && ((oA.children[0] || oA).className = "start-date");

                        //开始日期
                        this.endDate == oA["data-date"] && ((oA.children[0] || oA).className = "end-date");

                        //标记选择范围
                        if (this.startDate && this.endDate) {
                            iCur > this.startDate.replace(this.reg, "") && iCur < this.endDate.replace(this.reg, "") && ((oA.children[0] || oA).className = "select-range")
                        }
                    }
                    oFarg.appendChild(oA)
                }
            }
        }
        return oFarg
    },
    /**
    * 计算是否为节假日
    * @name Calendar#_isHoliday
    * @function
    * @private		 
    */
    _isHoliday: function(obj, className) {
        if (new RegExp(obj["data-date"]).test(_holidays[className].join())) {
            obj.className = className;
            obj.innerHTML = "<span>" + obj.innerHTML.replace(/<[^>]+>/, "") + "</span>"
        }
    },
    /**
    * 设置弹出式日历位置
    * @name Calendar#_setPos
    * @function
    * @private		 
    */
    _setPos: function() {
        var t, l, maxT, ie = /msie\s(\d+\.\d+)/i.test(navigator.userAgent) ? RegExp.$1 : undefined;
        t = _angtian.getPos(this.triggerNode).bottom + this.revise.top - (ie < 8 ? 2 : 0);
        l = _angtian.getPos(this.triggerNode).left + this.revise.left - (ie < 8 ? 2 : 0);
        maxT = t - 211 - this.triggerNode.offsetHeight - this.revise.top * 2;
        if (t > document.documentElement.clientHeight + (document.documentElement.scrollTop || document.body.scrollTop) - 211) t = maxT < 0 ? t : maxT;
        this.container.style.top = t + "px";
        this.container.style.left = l + "px";
        this.container.style.zIndex = 9999
    },
    /**
    * 添加事件
    * @name Calendar#_addEvent
    * @function
    * @private		 
    */
    _addEvent: function() {
        var that = this,
			obj = this.container;
        //为日历控件添加CLICK事件监听
        _angtian.on(obj, "click", function(e) {
            that.isHide = !0;
            that.closeTimer && clearTimeout(that.closeTimer);
            e = e || event;
            var oTarget = e.target || e.srcElement;
            switch (oTarget.className) {
                case "cal-close":
                    that.hide();
                    break;
                case "cal-prev":
                    that.prevMonth();
                    break;
                case "cal-next":
                    that.nextMonth();
                    break;
            }
            oTarget.parentNode.tagName.toUpperCase() === "A" && (oTarget = oTarget.parentNode);
            if (oTarget.tagName.toUpperCase() === "A" && oTarget.className != "disabled") {
                that.onSelectDate(oTarget);
                if (that.triggerNode.tagName.toUpperCase() === "INPUT") {
                    that.triggerNode.value = oTarget["data-date"];
                    if (that.isDateInfo) that.setDateInfo(oTarget["data-date"]);
                    that.hide()
                }
            }
            that.isPopup && _angtian.contains(obj, oTarget) && _angtian.halt(e)
        });
        //日历为弹出显示时添加事件
        if (this.isPopup) {
            _angtian.on(this.triggerNode, "focus", function(e) {
                that.isHide = !1;
                that.closeTimer && clearTimeout(that.closeTimer);
                e = e || event;
                var oTarget = e.target || e.srcElement,
					oIframe = _angtian.$("iframe", that.container)[0];
                that._setPos();
                that.show();
                oTarget.select && oTarget.select();
                oIframe && (oIframe.style.width = that.container.offsetWidth + "px", oIframe.style.height = that.container.offsetHeight + "px");
            });

            _angtian.on(this.triggerNode, "blur", function() {
                that.closeTimer = setTimeout(function() {
                    that.hide();
                }, 150)
            });

            _angtian.on(document, "click", function(e) {
                that.isHide && that.hide()
            });

            _angtian.on(window, "resize", function() {
                that._setPos()
            })
        }
        //给日期信息容器添加事件
        if (this.oDateInfo) {
            _angtian.on(this.oDateInfo, "click", function(e) {
                that.focus();
                _angtian.halt(e || event)
            })
        }
    },
    /**
    * 渲染日历
    * @param {object|string} date 指定日期渲染日期，参数可为日期对象new Date(yyyy, mm, dd)或字符串yyyy-mm-dd
    * @name Calendar#render
    * @function
    */
    render: function(date) {
        var date = date || this.date,
			aCal = _angtian.$(".cal-container", this.container),
			year, month, i;
        date = typeof date === "string" ? new Date(date.split(this.reg)[0], _angtian.formatNum(date.split(this.reg)[1] - 1)) : date;
        year = date.getFullYear();
        month = date.getMonth() + 1;
        this.year = year;
        this.month = month;
        for (i = 0, len = aCal.length; i < len; i++) {
            year += (month + (i ? 1 : 0)) > 12 ? 1 : 0;
            month = (month + (i ? 1 : 0)) % 12 || 12;
            this._draw(aCal[i], { year: year, month: month })
        }
    },
    /**
    * 渲染下月日历
    * @name Calendar#nextMonth
    * @function
    */
    nextMonth: function() {
        this.render(new Date(this.year, this.month + (this.monthStep - 1), 1))
    },
    /**
    * 渲染上月日历
    * @name Calendar#prevMonth
    * @function
    */
    prevMonth: function() {
        this.render(new Date(this.year, this.month - (this.monthStep + 1), 1))
    },
    /**
    * 显示日历
    * @name Calendar#show
    * @function
    */
    show: function() {
        this.container.style.display = "block";
        return this
    },
    /**
    * 隐藏日历
    * @name Calendar#hide
    * @function
    */
    hide: function() {
        this.container.style.display = "none";
        this.onClose();
        return this
    },
    /**
    * 设置显示日期信息
    * @param {string} str 日期信息内容
    * @name Calendar#setDateInfo
    * @function
    */
    setDateInfo: function(sDate) {
        sDate = sDate || this.triggerNode.value;
        //this.oDateInfo.innerHTML = this.rDate.test(sDate) ? (this.triggerNode.value = sDate, this.render(sDate), this.getDateInfo(sDate)[this.isHoliday ? "holiday" : "week"]) : ""
        this.oDateInfo.innerHTML = this.rDate.test(sDate) ? (this.render(sDate), this.getDateInfo(sDate)[this.isHoliday ? "holiday" : "week"]) : ""
    },
    /**
    * 获取日期信息
    * @param {string} sDate 日期字符串yyyy-mm-dd或yyyy/mm/dd
    * @name Calendar#getDateInfo
    * @function
    */
    getDateInfo: function(sDate) {
        var that = this,
			aDate = sDate.split(this.reg),
			oDate = new Date(aDate[0], aDate[1] - 1, aDate[2]),
			aDateName = ["\u4eca\u5929", "\u660e\u5929", "\u540e\u5929"],
			sWeekName = "周" + ["日", "一", "二", "三", "四", "五", "六"][oDate.getDay()],
			iDiff, p;
        return {
            week: sWeekName,
            holiday: function() {
                //节假日
                for (p in _holidays) if (new RegExp(sDate).test(_holidays[p])) return _dateName[p];
                //今天/明天/后天
                iDiff = _angtian.formatIntDate(sDate) - _angtian.formatIntDate(new Date);
                if (iDiff >= 0 && iDiff <= 2) return aDateName[iDiff];
                //节假日前1-3天/后1-3天日期数
                return that.isHolidayTips ? _dateMap[sDate] || sWeekName : sWeekName
            } ()
        }
    },
    /**
    * 显示鼠标移入的日期距开始日期的时间段
    * @name Calendar#showRange
    * @function
    */
    showRange: function() {
        var that = this;
        _angtian.on(this.container, "mouseover", function(e) {
            e = e || event;
            var oTarget = e.target || e.srcElement,
				aEl = _angtian.$("a", that.container),
				iBegin = _angtian.indexOf("start-date", aEl),
				clearClass = function() {
				    for (i = aEl.length; i--; ) _angtian.removeClass(aEl[i].children[0] || aEl[i], "hover")
				},
				i;
            if (!that.startDate) return;
            oTarget.parentNode.tagName.toUpperCase() === "A" && (oTarget = oTarget.parentNode);
            if (oTarget.tagName.toUpperCase() === "A") {
                var iNow = _angtian.indexOf(oTarget, aEl);
                clearClass();
                for (i = iBegin + 1; i < iNow; i++) _angtian.hasClass(aEl[i].children[0] || aEl[i], "end-date") || _angtian.addClass(aEl[i].children[0] || aEl[i], "hover")
            }
            else {
                clearClass()
            }
        })
    },
    /**
    * 弹出式日历时，让触发元素获取焦点
    * @name Calendar#focus
    * @function
    */
    focus: function() {
        this.triggerNode.focus()
    },
    /**
    * 触发元素为INPUT时的keyup事件
    * @name Calendar#keyup
    * @function
    */
    keyup: function() {
        var that = this,
			Cal_S = that.CalStart,
			Cal_E = that.CalEnd;
        _angtian.on(that.triggerNode, "keyup", function(e) {
            e = e || event;
            var oTarget = e.target || e.srcElement,
				sValue = oTarget.value;
            if (that.rDate.test(sValue)) {
                sValue = _angtian.formatStrDate(sValue);
                if (sValue != (that.isCal_start ? that.startDate : that.endDate)) {
                    if (that.isCalStart) {
                        Cal_S.startDate = Cal_E.startDate = sValue;
                        Cal_S.render(sValue);
                        that.setDateInfo(that.triggerNode.value);
                        Cal_E.render(Cal_E.endDate || sValue);
                    }
                    else if (that.isCalEnd) {
                        Cal_S.endDate = Cal_E.endDate = sValue;
                        Cal_E.render(sValue);
                        that.setDateInfo(that.triggerNode.value);
                        Cal_S.render();
                    }
                }
            }
            else {
                if (that.isCalStart) {
                    Cal_S.startDate = Cal_E.startDate = "";
                    Cal_S.render(new Date);
                    Cal_E.render(new Date);
                }
                else if (that.isCalEnd) {
                    Cal_S.endDate = Cal_E.endDate = "";
                    Cal_S.render(new Date);
                    Cal_E.render(new Date);
                }
                that.setDateInfo("")
            }
        });
    },
    /**
    * 显示日历提示信息
    * @param {string} message 日历提示信息内容
    * @name Calendar#showMessage
    * @function
    */
    showMessage: function(message) {
        if (this.oMsg) {
            this.oMsg.innerHTML = message;
            this.oMsg.style.display = "block";
            this.focus()
        }
    },
    /**
    * 隐藏日历提示信息
    * @name Calendar#hideMessage
    * @function
    */
    hideMessage: function() {
        this.oMsg && (this.oMsg.style.display = "none")
    }
};