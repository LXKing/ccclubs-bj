
var minDate = "";
var ccToday = new Date();
var txtStartDate = $("#" + fromDateCtrlID).val();
var txtEndDate = $("#" + toDateCtrlID).val();
if (ccMinDate) minDate = ccMinDate;
else minDate = $CAL.formatStrDate(today);


//出发时间实例    
var oDepCal = new Calendar({
    id: "#" + fromDateCtrlID,
    //触发显示日历元素ID
    isPopup: !0,
    //弹出式日历
    isPrevBtn: !0,
    //显示上月按钮
    isNextBtn: !0,
    //显示下月按钮
    isCloseBtn: !0,
    //显示关闭按钮
    isHoliday: !0,
    //节假日特殊显示
    isHolidayTips: !0,
    //显示节假日1~3天/后1~3天信息
    isDateInfo: !0,
    //显示日期信息
    isMessage: !0,
    //有日历提示信息
    isCalStart: !0,
    //标记为开始时间
    dateInfoClass: "date-info-start",
    //开始时间icon样式
    range: {
        mindate: minDate,
        maxdate: new Date(ccToday.getFullYear(), ccToday.getMonth(), ccToday.getDate() + 6)
    },
    //限制范围（当天——2020-12-31）
    count: 1,
    //日历个数
    monthStep: 1,
    //切换上下月日历步长
    onSelectDate: fnSelectDate,
    //选择日期回调方法
    onClose: fnClose //关闭日历回调方法 
});
//返程时间实例
var oBackCal = new Calendar({
    id: "#" + toDateCtrlID,
    //触发显示日历元素ID
    isPopup: !0,
    //弹出式日历
    isPrevBtn: !0,
    //显示上月按钮
    isNextBtn: !0,
    //显示下月按钮
    isCloseBtn: !0,
    //显示关闭按钮
    isHoliday: !0,
    //节假日特殊显示
    isHolidayTips: !0,
    //显示节假日1~3天/后1~3天信息
    isDateInfo: !0,
    //显示日期信息
    isMessage: !0,
    //有日历提示信息
    isCalEnd: !0,
    //标记为结束时间
    dateInfoClass: "date-info-end",
    //结束时间icon样式
    range: {
        mindate: txtStartDate,
        maxdate: "2020-12-31"
    },
    //限制范围（当天——2020-12-31）
    count: 1,
    //日历个数
    monthStep: 1,
    //切换上下月日历步长
    onSelectDate: fnSelectDate,
    //选择日期回调方法
    onClose: fnClose //关闭日历回调方法 
});
//为返程实例添加className, 主要为了样式需要
oBackCal.container.className = "cal-end";
//开启返程实例的鼠标移动范围
oBackCal.showRange();

//开启键盘事件
oDepCal.CalStart = oBackCal.CalStart = oDepCal;
oDepCal.CalEnd = oBackCal.CalEnd = oBackCal;
oDepCal.keyup();
oBackCal.keyup();

//选择日期回调函数
function fnSelectDate(obj) {
    switch (this.triggerNode.id) {
        case fromDateCtrlID:
            oBackCal.startDate = this.startDate = obj["data-date"];
            oBackCal.range.mindate = this.startDate;
            oBackCal.render(this.startDate);
            this.render(this.startDate);
            oBackCal.focus();
            break;
        case toDateCtrlID:
            oDepCal.endDate = this.endDate = obj["data-date"];
            oDepCal.render(this.startDate);
            this.render(this.endDate);
            break;
    }
}

//关闭日历回调函数
function fnClose() {
    this.hideMessage();
}
//默认出发时间和返程时间
oDepCal.startDate = oBackCal.startDate = txtStartDate;
oDepCal.endDate = oBackCal.endDate = txtEndDate;
oDepCal.setDateInfo(txtStartDate);
oBackCal.setDateInfo(txtEndDate);
