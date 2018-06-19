$(function(){
	$('#google_play').click(function(){
		showConfirm();
		return false;
	});
	
	$('#ccc-bottom-wx').click(function(){
		showBottmConfirm();
		return false;
	});
	$('#ccc-bottom-zfb').click(function(){
		showBottmConfirm();
		return false;
	});
	$('#ccc-bottom-qq').click(function(){
		showBottmConfirm();
		return false;
	});
	
	$(".bg_1").click(function(){
		window.location.href = "mianya.html";
	});
	$(".bg_2").click(function(){
		window.location.href = "steppedoil.html";
	});
	$(".bg_3").click(function(){
		window.location.href = "#";
	});
	$(".bg_4").click(function(){
		window.location.href = "activity.html";
	});
	$(".bg_5").click(function(){
		window.location.href = "activity_coin.html";
	});
	$(".bg_6").click(function(){
		window.open("/activity/newyear2015/activity.html?spm=100");
	});
	$(".bg_7").click(function(){
		window.location.href ="send88_coin.html";
	});
	$(".bg_8").click(function(){
		window.open("http://item.taobao.com/item.htm?spm=686.1000925.0.0.CYifEW&id=43834540313");
	});
	$(".bg5_2").click(function(){
		window.location.href ="send88_coin_host5.html";
	});
	$(".bg11_2").click(function(){
		window.location.href ="send88_coin_host11.html";
	});
	
	// 提交表单的下拉框
	$(".form-item .lst[ref]").each(function(){
		var pThis = $(this);
		var pInput = $("#"+($(this).attr("ref")));
		var pHidden = $("input[name='"+($(this).attr("ref"))+"']");
		pInput.click(function(){
			pThis.show();
			return false;
		});
		$(document).click(function(){
			pThis.hide();
		});
		pThis.click(function(event){
			pInput.val($(event.target).text());
			pHidden.val($(event.target).attr("value"));
			if(pInput.selector=="#area"){setArea();}
		});
	});
	
	/* 日期时间 */
	var timeCur = new Date();
	$('.form-item .date').datepicker({
		minDate: new Date(timeCur.getFullYear(), timeCur.getMonth(), timeCur.getDate(), 0, 0),
		dateFormat:"yy-mm-dd"
	}).attr('readonly','readonly');
	$('.form-item .time').timepicker({
		dateFormat:"hh:mm"
	}).attr('readonly','readonly');
	
	// 照片墙鼠标效果
	$("#photos table img").mouseenter(function(){
		if($(this).attr('data')!=-1){
			var overlay = $(this).siblings(".overlay");
			if(overlay.size()==0)
				overlay = $("<div class='overlay'><span>"+$(this).attr("tips")+"</span></div>").insertAfter($(this));
			overlay.fadeIn(200);
		}
	});
	$("#photos table td").mouseleave(function(){
		if($(this).attr('data')!=-1){
			$(this).children(".overlay").fadeOut(300);
		}
	});
	
	// 全屏轮播
	$("#carmodels .scroll").parent().each(function(){
		var pThis = $(this);
		var table = pThis.find("table");
		var eThis = pThis.get(0);
		var lastTime = new Date();
		pThis.data("index",0);			
		// 把第一个克隆到最后一个，以便循环
		// var first = table.find("td:eq(0)");
		// first.clone().appendTo(first.parent());
		if(table.find("td").size()>2)
			table.find("td").clone().appendTo(table.find("tr"));
		var itemWidth = (pThis.width()/4-20);
		table.width(table.find("td").size()*itemWidth);
		table.css("position","relative");
		var execute = function(index){
			if(table.is(":animated"))return;
			lastTime = new Date();
			if(index<0){
				table.find("td:last").prependTo(table.find("tr"));
				table.css("left",-(itemWidth)+"px");
				index = 0;
			}else if(index>=table.find("td").size()){
				index = 0;
			}
			table.animate({
				left:-index*itemWidth
			},500,"swing",function(){
				if(index==1){
					index=0; 
					table.find("td:first").appendTo(table.find("tr"));
					table.css("left","0px");
				}
				pThis.data("index",index);
			});	
		};
		pThis.find(".toLeft").click(function(){
			execute(pThis.data("index")-1);	
			return false;	
		});
		pThis.find(".toRight").click(function(){
			execute(pThis.data("index")+1);	
			return false;
		});
        pThis.autoSlide=function(){
        	if((new Date().getTime()-lastTime.getTime())<4000)
        		return;
			pThis.find(".toRight").click();
		}
		setInterval(pThis.autoSlide,5000);
	});
	setArea();
});
	
function setArea(){
	var value = $("input[name='area']").val();
	var container = $(".form-item .lst[ref=outlets]");
	
	container.html("");
	
	var index = -1;
	var display = "";
	$.getJSON("/ajax_outletsByArea.html",{area:value,t:new Date().getTime()},function(data) {
		// 遍历JSON中的每个entry
		$.each(data, function(entryIndex, entry) {
			var info = '<a value="'+entry.csoId+'" href="javascript:;">'+entry.csoName+'</a>';
			$(info).appendTo(container);
			if($("#outlets_base").val()== entry.csoId){
				index = entry.csoId;
				display = entry.csoName;
			}
		});
	});
	var pInput = $("#outlets");
	var pHidden = $("input[name='outlets']");
	if(index!=-1&&$("#outlets_base").val()== index){
		pInput.val(display);
		pHidden.val(index);
	}else if($("#outlets_base").val()!=-1){
		pInput.val($("#outlets_name_base").val());
		pHidden.val($("#outlets_base").val());
	}else{
		pInput.val("请选择网点");
		pHidden.val("");
	}
}
		
function reserve(){
	var start = $.trim($('#startDate').val())+" "+$.trim($('#startTime').val());
	var finish = $.trim($('#finishDate').val())+" "+$.trim($('#finishTime').val());
	if (dateDiff('M', start, finish) <= 0) {
		showTips("还车时间应该比取车时间晚","error",3);
		return;
	}
	if (dateDiff('H', start, finish) < 1) {
		showTips("最低1个小时起租","error",3);
		return;
	}
	var csoArea=$("input[name='area']").val();
	
	var csoOutlets=$("input[name='outlets']").val();
	var ajax = {
		url: "/reserve_checkBasicOrder.html?t="+new Date().getTime(),
		type: "POST",
		data: {
			csoStart: start+":00",
			csoFinish: finish+":00",
			csoArea: csoArea,
			// csoModel:$('#csoModel').val(),
			csoOutlets:csoOutlets
		},
		async: true,
		success: function(data) {
			switch (data) {
				case "100":
					location.href = '/reserve.html?src=index';
					break;
				case "101":
					showTips("请设置取车时间","error",3);
					break;
				case "102":
					showTips("请设置还车时间","error",3);
					break;
				case "103":
					showTips("取车时间不能早于当前时间两小时","error",4);
					break;
				case "104":
					showTips("取车时间不能晚于还车时间","error",3);
					break;
				case "105":
					showTips("最低1个小时起租","error",3);
					break;
				case "106":
					showTips("取、还车时间应该是20分钟的倍数","error",4);
					break;
				case "107":
					showTips("网站提只提供7天以内的车辆预订","error",3);
					break;
				case "108":
					showTips("网站提只提供最长15天的订单","error",3);
					break;
				default:
					var msg = "未知的返回。";
					return;
				}
			}
		}
		$.ajax(ajax);
	}

function showBottmConfirm(title,text,trueLabel,returnLabel,callback) {
	var gConfirm = $('#ccc-global-bottom-confirm');
	showLayer();
	gConfirm.addClass('ccc-show');
	gConfirm.find('h3').text(title);
	gConfirm.find('#ccc-confirm-tip').html(text);
	gConfirm.find('#ccc-true').text(trueLabel);
	gConfirm.find('#ccc-return').text(returnLabel);
	gConfirm.find('.ccc-cfm-close').one('click',function(){
		closeBottmConfirm();
		if (callback!==undefined && $(this).hasClass('ccc-cfm-true')) {
			callback();
		}
	});
	$(document).one('click','#ccc-overclose',closeConfirm);
}
function closeBottmConfirm() {
	$('#ccc-global-bottom-confirm .ccc-cfm-close').off('click');
	closeLayer();
	$('#ccc-global-bottom-confirm').removeClass('ccc-show');
}

/** banner **/
(function(){
    if(!Function.prototype.bind){
        Function.prototype.bind = function(obj){
            var owner = this,args = Array.prototype.slice.call(arguments),callobj = Array.prototype.shift.call(args);
            return function(e){e=e||top.window.event||window.event;owner.apply(callobj,args.concat([e]));};
        };
    }
})();
var glume = function(id){
    this.ctn = document.getElementById(id);
    this.adLis = null;
    this.btns = null;
    this.animStep = 0.1;// 动画速度0.1～0.9
    this.switchSpeed = 5;// 自动播放间隔(s)
    this.defOpacity = 1;
    this.tmpOpacity = 1;
    this.crtIndex = 0;
    this.crtLi = null;
    this.adLength = 0;
    this.timerAnim = null;
    this.timerSwitch = null;
    this.init();
};
glume.prototype = {
    fnAnim:function(toIndex){
        if(this.timerAnim){window.clearTimeout(this.timerAnim);}
        if(this.tmpOpacity <= 0){
            this.crtLi.style.opacity = this.tmpOpacity = this.defOpacity;
            this.crtLi.style.filter = 'Alpha(Opacity=' + this.defOpacity*100 + ')';
            this.crtLi.style.zIndex = 0;
            this.crtIndex = toIndex;
            return;
        }
        this.crtLi.style.opacity = this.tmpOpacity = this.tmpOpacity - this.animStep;
        this.crtLi.style.filter = 'Alpha(Opacity=' + this.tmpOpacity*100 + ')';
        this.timerAnim = window.setTimeout(this.fnAnim.bind(this,toIndex),50);
    },
    fnNextIndex:function(){
        return (this.crtIndex >= this.adLength-1)?0:this.crtIndex+1;
    },
    fnSwitch:function(toIndex){
        if(this.crtIndex==toIndex){return;}
        this.crtLi = this.adLis[this.crtIndex];
        for(var i=0;i<this.adLength;i++){
            this.adLis[i].style.zIndex = 0;
        }
        this.crtLi.style.zIndex = 2;
        this.adLis[toIndex].style.zIndex = 1;
        for(var i=0;i<this.adLength;i++){
            this.btns[i].className = '';
        }
        this.btns[toIndex].className = 'on'
        this.fnAnim(toIndex);
    },
    fnAutoPlay:function(){
        this.fnSwitch(this.fnNextIndex());
    },
    fnPlay:function(){
        this.timerSwitch = window.setInterval(this.fnAutoPlay.bind(this),this.switchSpeed*1000);
    },
    fnStopPlay:function(){
        window.clearTimeout(this.timerSwitch);
    },
    init:function(){
        this.adLis = this.ctn.getElementsByTagName('li');
        //this.btns = this.ctn.getElementsByName('ul')[0].getElementsByTagName('li');
        this.btns = $(this.ctn).find('.Nubbt')[0].getElementsByTagName('span');
        this.adLength = this.adLis.length;
        for(var i=0,l=this.btns.length;i<l;i++){
            with({i:i}){
                this.btns[i].index = i;
                this.btns[i].onclick = this.fnSwitch.bind(this,i);
                this.btns[i].onmouseover = this.fnSwitch.bind(this,i);
            }
        }
        this.adLis[this.crtIndex].style.zIndex = 2;
        this.fnPlay();
        this.ctn.onmouseover = this.fnStopPlay.bind(this);
        this.ctn.onmouseout = this.fnPlay.bind(this);
    }
};
var player1 = new glume('glume');