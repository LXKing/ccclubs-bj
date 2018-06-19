package com.ccclubs.action.thread.sms;

import com.lazy3q.web.helper.Lazy;

public class www_smsadmin_cn extends BaseSMS {
	@Override
	public String query() {
		try {
			return Lazy.http("http://www.smsadmin.cn/smsmarketing/wwwroot/api/user_info/?uid=evnet001&pwd=883309188","GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return "短信余量查询出错";
		}
	}
	@Override
	public String send(String mobile, String content) {
		String ret = Lazy.post("http://www.smsadmin.cn/smsmarketing/wwwroot/api/post_send/", 
				Lazy.add("uid", "evnet001")
				.add("pwd", "883309188")
				.add("mobile", mobile)
				.add("msg", content),
				"gb2312"
			);
		return ret;
	}

}
