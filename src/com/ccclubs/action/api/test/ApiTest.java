package com.ccclubs.action.api.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class ApiTest {
	
	static{
		//设置当地时间为北京时间
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
	}
	
	public static void main(String...strings) throws Exception{
		String url = "http://api.ccclubs.com/invoke.do";//api地址
		String appid="4lt3y02g0j";//appid
		String secret="jldabv20p7lzm8wu";//密钥
		
		String method = "com.ccclubs.carmodel.page";//调用的接口
		String timestamp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//时间戳
		String token="";//如果接口需要授权时，这个就不能为空
		String params = "{}";//应用参数，没有就写个大括号
		
		
		//下面这些是生成签名的
		Map<String,String> map = new HashMap();
		map.put("method", method);
		map.put("timestamp", timestamp);
		map.put("token", token);
		map.put("params", params);
		map.put("appid", appid);
		String sign = getSign(secret,map);
		
		
		//把所有参数拼成地址参数,注意参数值URLEncoder一下
		String strQueryParams = "?";
		for(String paramName:map.keySet()){
			strQueryParams+=paramName+"="+URLEncoder.encode(map.get(paramName))+"&";
		}
		strQueryParams+="sign="+sign;
		
		//请求并返回
		String jsonString = http(url+strQueryParams,"UTF-8");
		
		//得到的一个json串
		System.out.println(jsonString);
	}
	
	
	
	
	public static String http(String sUrl,String encode)throws Exception{	
		String strContent = "";
		HttpURLConnection urlConnection = null;
		URL url = new URL(sUrl);
		urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setConnectTimeout(10000);
		urlConnection.setReadTimeout(10000);
		// urlConnection.setDoOutput(true);
		if (urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() < 300) {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),encode));
			String strLine = in.readLine();
			// 得到的结论是文件如果是带bom的utf-8格式的，即使用UTF-8的流读入，也得自己去掉bom才可以
			if (strLine != null && !strLine.equals("")) {
				if (strLine.charAt(0) == (char) 65279)// BOM
					strLine = strLine.substring(1);
			}
			while (strLine != null) {
				strContent += strLine + "\r\n";
				strLine = in.readLine();
			}
			in.close();
		}
		urlConnection.disconnect();
		return strContent;
	}
	
	
	/**
	 * 检验参数签名
	 * @param secret
	 * @param sign 客方传过来的加密串，格式为secret+(参数名+参数值...)+secret的大写md5值
	 * @param paramNames
	 * @return
	 */
	public static String getSign(String secret,Map<String,String> params){
		//按参数名asscic码排序
		List<String> names=new ArrayList();
		for(String paramName:params.keySet())
			names.add(paramName);
		java.util.Collections.sort(names);//按字串ASCII排序
		String strSign = secret;//头部加密钥
		for(String name:names)
			strSign+=name+params.get(name);
		strSign+=secret;//尾部加密钥
		return MD5(strSign).toUpperCase();
	}
	
	/**
	 * md5
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
