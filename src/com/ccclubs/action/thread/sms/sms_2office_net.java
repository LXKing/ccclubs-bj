package com.ccclubs.action.thread.sms;

import com.ccclubs.config.SYSTEM;
import com.lazy3q.web.helper.$;

public class sms_2office_net extends BaseSMS {

	@Override
	public String query() {
		return "当前短信通道余额查询功能未实现";
	}

	@Override
	public String send(String mobile, String content) {	
		
		Boolean isVCode = false;
		if(content.indexOf("验证码")>-1)
			isVCode = true;
		if(content.indexOf("授权码")>-1)
			isVCode = true;
		if(content.indexOf("检验码")>-1)
			isVCode = true;
		return $.post("http://sms.2office.net:8080/WebService/sms3.aspx", 
				$.add("account", SYSTEM.getArgumentValue("第二办公室短信接口帐号", "2office_account", "2521110"))
				.add("password", $.md5(SYSTEM.getArgumentValue("第二办公室短信接口密码", "2office_password", "88190338")+"70a92bbbd7dd84510bccf1e915523f96").toLowerCase())
				.add("mobile", mobile)
				.add("content", content)
				.add("channel", isVCode?"252111001":"252111005")
				, "UTF-8");
	}
	
	public static void main(String...strings){
		System.out.println(new sms_2office_net().send("13575717846", "您的订单时间11月15日17点00分到11月16日09点00分，取车点三新大厦，车牌号ZJA1NN25，授权码251129[车纷享]"));
	}
}
