$.extend({
	getObject:function(url,params,callback){
		var jsonString=$.ajax({
		   type: "POST",
		   dataType:"json",
		   url:url,
		   data:params,
		   cache:false,
		   async:callback?true:false,
		   success:callback
		}).responseText;
		return eval("("+jsonString+")");
	}
	/**
	* 取最大的zIndex
	**/
	,zindex:function(){
		if(!window.zindex)
			window.zindex=0xaaaa;
		return window.zindex++;		
	},	
	/**
	* 弹出对话框
	**/
	dialog:function(params)
	{
		if(!params)
			return;
		var div=$("<div></div>");
		div.html(params.html||$.ajax({url: params.url,cache: false,async: false}).responseText);
		return div.dialog(params);
	}
});
$.fn.extend({
	closeDialog:function(){
		if($(this).data("close"))
			$(this).data("close")();
		else if($(this).parents(".dialog") && $(this).parents(".dialog").data("close"))
			$(this).parents(".dialog").data("close")();
	}
	,dialog:function(params){		
		if(!params)
			return;
		var dialog=$("<table class='shadow dialog' border='0' cellspacing='0' cellpadding='0'><tr>  <td class='tl'></td>  <td class='t'></td>  <td class='tr'></td></tr><tr>  <td class='l'></td>  <td class='c'><table class='state-content' border='0' cellspacing='0' cellpadding='0'><tr><td class='caption state-focus'><button class='button'></button></td></tr><tr><td class='body'><div class='content'></div></td></tr><tr><td class='option'><div class='line'></div></td></tr></table></td>  <td class='r'></td></tr><tr>  <td class='bl'></td>  <td class='b'></td>  <td class='br'></td></tr></table>");
		dialog.find(".caption").html(params.title||"提示");
		
		if(!$(this).is(":visible"))
			$(this).show();		
		
		$(this).appendTo(dialog.find(".content"));
		
		var closeFunc = function(){
			if(dialog.data("overlay")){
				dialog.data("overlay").hide();
				dialog.data("overlay").remove();			
			}
			dialog.remove();
		};
		dialog.close = closeFunc;
		dialog.data("close",closeFunc);
		
		var closeBtn=$("<span class='icon'>x</span>");
		closeBtn.prependTo(dialog.find(".caption"));
		closeBtn.click(function(){
			$(this).closeDialog();
		});
		
		if(!params.buttons)
		{
			dialog.data("onOk",params.onOk)
			var trueBtn=$("<button class='button' type='button'>&nbsp;确&nbsp;&nbsp;&nbsp;&nbsp;定&nbsp;</button>");
			trueBtn.appendTo(dialog.find(".option"));
			trueBtn.click(function(){
				var result=true;
				if(dialog.data("onOk"))
					result=dialog.data("onOk")();
				if(result!=false)
					dialog.closeDialog();
			});
			
			dialog.data("onCancel",params.onCancel)
			var cancelBtn=$("<button class='button' type='button'>&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;</button>");		
			cancelBtn.appendTo(dialog.find(".option"));		
			cancelBtn.click(function(){
				var result=true;
				if(dialog.data("onCancel"))
					result=dialog.data("onCancel")();
				if(result!=false)
					dialog.closeDialog();	
			});
		}
		else
		{
			for(var o in params.buttons)
			{
				var btn=$("<button class='button' type='button'>"+o+"</button>");
				btn.appendTo(dialog.find(".option"));
				btn.data("func",params.buttons[o]);
				btn.click(function(){
					$(this).data("func")();
				});
			}
			if(dialog.find(".option button").size()==0)
				dialog.find(".option .line").remove();
		}
		
		if(params.modal==true)
		{
			/*遮罩层*/
			var overlay=$("<div class='overlay'></div>");
			dialog.data("overlay",overlay);
			overlay.css("height",$("body").height()+"px");
			overlay.css("z-index",$.zindex());
			overlay.appendTo("body");
		}
		
		dialog.css("z-index",$.zindex());
		
		dialog.appendTo("body");

		if( ($(window).width()-dialog.width()-80) < 0)
			dialog.find(".content").css("width",$(window).width()-80);
		if(($(window).height()-dialog.height()-160) < 0)
			dialog.find(".content").css("height",$(window).height()-160);

		var left = $(window).width()/2-parseInt(dialog.width())/2;
		if(left<10)
			left=10;
		var top = $(window).height()/2-parseInt(dialog.height())/2;
		if(top<10)
			top=10;
		if(!params.static)
			top+=$(window).scrollTop();
		dialog.css("left",left+"px");	
		dialog.css("top",top+"px");
		dialog.css("z-index",$.zindex());		
		
				
		//设置遮罩层高度
		if(params.modal==true){
			overlay.css("height",$(document).height()+"px");
		}
			
		if(params.callback)
			params.callback();
		
		return dialog;
	}
});
window.Alert = window.alert;
window.alert=function(message){
	$("<div></div>").html(message).dialog({
		modal:true,
		title:"提示"
	});
}