package com.ccclubs.action.thread.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.ccclubs.action.app.official.util.Md5Util;
import com.ccclubs.helper.APICallHelper;
import com.lazy3q.web.helper.$;

@SuppressWarnings("deprecation")
public class JinLouNoteV3 extends BaseSMS {
	private static String name = "bjcxqc";
	private static String pwd = "d41d8cd98f00b204e9800998ecf8427e";
//	private static String pwd = "2f33f665ec817c6c97adb450da7b2c73";
//	private static String sendNoteUrl = "http://183.203.28.226:9000/HttpSmsMt";
	private static String sendNoteUrl = "http://60.205.14.180:9000/HttpSmsMt";
	
	@Override
	public String query() {
		
		return null;
	}
	
	@Override
	public String send(String mobile, String content) {
		HttpClient client = new DefaultHttpClient();
		HttpPost method = new HttpPost(sendNoteUrl);
		
		String mttime = $.date(new Date(), "yyyyMMddhhmmss");
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("name", name));
		paramList.add(new BasicNameValuePair("pwd", Md5Util.MD5(pwd + mttime, 32)));
		paramList.add(new BasicNameValuePair("subid", ""));	// 选填
		paramList.add(new BasicNameValuePair("phone", mobile));
		paramList.add(new BasicNameValuePair("content", content));
		paramList.add(new BasicNameValuePair("mttime", mttime));
		paramList.add(new BasicNameValuePair("rpttype", "1"));
		
		return APICallHelper.getResultAsString(client, method, paramList);
	}
	
	public static boolean isSucceedOrFail(String str) {
		if(str == null)return false;
		JSONObject json = JSONObject.fromObject(str);
		return json.getString("ReqCode")!=null && json.getString("ReqCode").equals("00");
	}
	
	public static void main(String[] args) {
		String s = new JinLouNoteV3().send("15868164303", "测试短信");
		System.out.println(s);
	}

}
