$.fn.extend({
	params:function(kvs){
		if(kvs){
			for(var o in kvs){
				if($(this).find("[name="+o+"]").size()==0)//如果没有该字段，则添加一个
					$("<input type='hidden' name='"+o+"'/>").appendTo($(this));
				$(this).find("[name="+o+"]").val(kvs[o]);
			}
		}else{
			var form=$(this).get(0);
			var kvs = {};
			for(var i=0;i<form.length;i++ ){
			   var item = form[i];
			   if(!item)
					continue;
				kvs[$(item).attr("name")]=$(item).val();
			}
			return kvs;
		}
	}
});
$.extend({
	submit:function(url,data,target){
		var form = $("#tempPostForm");
		if(form.size()==0)
			form=$("<form id='tempPostForm' name='tempPostForm'></form>").appendTo("body").hide();
		form.attr("action",url).attr("method","post").attr("target",target?target:"_self");
		form.empty();
		for(var o in data){
			var value = data[o];
			if(typeof(value)=="object")
				value = JSON.stringify(value);
			$("<input type='hidden'/>").val(value).attr("name",o).appendTo(form);
		}
		form.submit();
	},
	getObject:function(url,params,callback){
		var jsonString=$.ajax({
		   type: "POST",
		   dataType:(url.indexOf("=?")>-1)?"jsonp":"json",
		   url:url,
		   data:params,
		   cache:false,
		   async:callback?true:false,
		   success:callback
		}).responseText;
		if(!callback)
			return eval("("+jsonString+")");
	},	
	/**
	* 显示一个time的提示框
	**/
	tips:function(content,time){
		if(!document.body){
			return alert(content);
		}
		var div=$("<div class='tips'><div class='content'></div></div>");
		div.find(".content").html(content);
		div.appendTo("body");
		div.css("left",(($(window).width())/2-(parseInt(div.width())/2))+"px");	
		div.css("top",(($(window).height())/2-(parseInt(div.height())/2)+$(document).scrollTop())+"px");
		div.show();
		setTimeout(function(){
			if(!div.mousein)
			{
				div.find(".content").fadeOut(1000,function(){
					div.remove();
				});	
			}	
			div.mouseout(function(){
				div.stop();
				div.find(".content").fadeOut(1000,function(){
					div.remove();
				});
			});
		},time?time:1000);
		div.mouseout(function(){
			div.mousein=false;
		});
		div.mouseover(function(){
			div.mousein=true;
			div.stop();
			div.fadeTo("fast",1);
		});		
		div.css("z-index",$.zindex());
	},
	/**
	获取当前日期时间星期
	格式
	YYYY/yyyy/YY/yy 表示年份   
	MM/M 月份   
	W/w 星期   
	dd/DD/d/D 日期   
	hh/HH/h/H 时间   
	mm/m 分钟   
	ss/SS/s/S 秒
	**/
    formatDate: function(strFormat,date) {
		var dDate=date?date:new Date();
		var str = strFormat;    
		var Week = ['日','一','二','三','四','五','六'];   
	   
		str=str.replace(/yyyy|YYYY/,dDate.getFullYear());    
		str=str.replace(/yy|YY/,(dDate.getYear() % 100)>9?(dDate.getYear() % 100).toString():'0' + (dDate.getYear() % 100));    
	   
		str=str.replace(/MM/,dDate.getMonth()>8?(dDate.getMonth()+1):'0' + (dDate.getMonth()+1));    
		str=str.replace(/M/g,dDate.getMonth()+1);    
	   
		str=str.replace(/w|W/g,Week[dDate.getDay()]);    
	   
		str=str.replace(/dd|DD/,dDate.getDate()>9?dDate.getDate().toString():'0' + dDate.getDate());    
		str=str.replace(/d|D/g,dDate.getDate());    
	   
		str=str.replace(/hh|HH/,dDate.getHours()>9?dDate.getHours().toString():'0' + dDate.getHours());    
		str=str.replace(/h|H/g,dDate.getHours());    
		str=str.replace(/mm/,dDate.getMinutes()>9?dDate.getMinutes().toString():'0' + dDate.getMinutes());    
		str=str.replace(/m/g,dDate.getMinutes());    
	   
		str=str.replace(/ss|SS/,dDate.getSeconds()>9?dDate.getSeconds().toString():'0' + dDate.getSeconds());    
		str=str.replace(/s|S/g,dDate.getSeconds());    
	   
		return str;
	},
	dateFormat:function(string,format){//将字符串转换成日期时间，有默认格式
		if(format == null) format = 'yyyy-MM-dd hh:mm:ss';
		var compare = {'y+' : 'y','M+' : 'M','d+' : 'd','H+' : 'H','m+' : 'm','s+' : 's'};
		var result = {'y' : '','M' : '','d' : '','H' : '00','m' : '00','s' : '00'};
		var tmp = format;
		for (var k in compare) {		
			if (new RegExp('(' + k + ')').test(format))
				 result[compare[k]] = string.substring(tmp.indexOf(RegExp.$1), tmp.indexOf(RegExp.$1) +RegExp.$1.length);
		}
		return new Date(result['y'], result['M'] - 1, result['d'], result['H'], result['m'], result['s']);
	},
	date:function(date,format){
		if(typeof(date)=="string")
			return $.dateFormat(date,format);
		else
			return $.formatDate(format,date);
	},
	sizeFormat:function(number){
		if(!number || number<0)
			number=0;
		number=number/1024/1024;		
		number=""+number;
		var reg=/^(([1-9]\d{0,9})|0)(\.)?(\d{1,2})?/;
		return (number.match(reg)?number.match(reg)[0]:'')+"M";
	},
	cookie:function(name, value, options) {
		if (typeof value != 'undefined') { // name and value given, set cookie
			options = options || {};
			if (value === null) {
				value = '';
				options.expires = -1;
			}
			var expires = '';
			if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
				var date;
				if (typeof options.expires == 'number') {
					date = new Date();
					date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
				} else {
					date = options.expires;
				}
				expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
			}
			var path = options.path ? '; path=' + options.path : '';
			var domain = options.domain ? '; domain=' + options.domain : '';
			var secure = options.secure ? '; secure' : '';
			document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
		} else { // only name given, get cookie
			var cookieValue = null;
			if (document.cookie && document.cookie != '') {
				var cookies = document.cookie.split(';');
				for (var i = 0; i < cookies.length; i++) {
					var cookie = jQuery.trim(cookies[i]);
					// Does this cookie string begin with the name we want?
					if (cookie.substring(0, name.length + 1) == (name + '=')) {
						cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
						break;
					}
				}
			}
			return cookieValue;
		}
	}
	,UUID:function(){
		var S4=function () {
       		 return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
	    };
	    return (S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4());
	}
});
