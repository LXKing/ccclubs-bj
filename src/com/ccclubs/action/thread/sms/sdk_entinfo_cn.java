package com.ccclubs.action.thread.sms;

import com.lazy3q.web.helper.$;

public class sdk_entinfo_cn extends BaseSMS{

	@Override
	public String query() {
		return "当前短信通道余量查询功能未实现";
	}

	@Override
	public String send(String mobile, String content) {
		String SN = "SDK-BBX-010-23300";
		String password = "194D7D-3";
		String ret = $.post("http://sdk.entinfo.cn:8061/mdsmssend.ashx", 
				$.add("sn", SN)
				.add("pwd", $.md5(SN+password).toUpperCase())
				.add("mobile", mobile)
				.add("Content", content),
				"UTF-8"
			);
		return ret;	
	}
	
	public static void main(String...strings){
		$.trace(new sdk_entinfo_cn().send("13575717846", "短信测试a【绿狗租车】"));
	}

}
