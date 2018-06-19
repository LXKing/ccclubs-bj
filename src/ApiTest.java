import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ccclubs.action.app.official.model.ValueEntry;
import com.ccclubs.action.app.official.util.Md5Util;
import com.ccclubs.helper.APICallHelper;
import com.lazy3q.web.helper.$;


public class ApiTest {
	
	final static String  CHARSET ="utf-8";
	final static String CONTENT_TYPE = "application/x-www-form-urlencoded";
	final static String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	static String server = "http://123.57.46.221:7009/invoke.do";
	static String token = "060075689813436d9201add28c9ff126";		//
	static String appid = "test";
	static String appsecret = "test";
	
	public static void main(String[] args) {
		
		try{
		
			Map<String, String> mpam = new HashMap<String, String>();
			mpam.put("method", "com.ccclubs.member.saveContact");
			mpam.put("timestamp", formmat(new Date()));
			mpam.put("appid", appid);
			mpam.put("token", token);
			
			Map<String, Object> params = new LinkedHashMap<String, Object>();
//			params.put("postAddress", URLEncoder.encode("谁谁谁", CHARSET));
			params.put("postAddress", "谁谁谁");
			params.put("company", "");
			params.put("people", "");
			params.put("peoplePhoneNumber", "");
			params.put("relation", "");
			
			mpam.put("params", $.json(params));
			
			String sign = genSignKey(mpam, "appid","method","params","token","timestamp");
			System.out.println("签名："+sign);
			mpam.put("sign", sign);
			//mpam.put("params", URLEncoder.encode($.json(mpam.get("params")),CHARSET));
			//mpam.put("timestamp", URLEncoder.encode(mpam.get("timestamp").toString(),CHARSET));
			
			String url = server+"?"+parseParam2(mpam);
			System.out.println(url);
			
//			String resp = sendHttpPost("GET", url, parseParam2(mpam), null, CONTENT_TYPE, CHARSET);
			//String resp = APICallHelper.doGet(url);
			String resp = APICallHelper.doPost(server, mpam);
			//String resp = $.http(server+"?"+parseParam2(mpam));
			System.out.println(resp);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String formmat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		return sdf.format(date);
	}
	
	/**
	 * 生成签名
	 * @param param
	 * @return
	 */
	public static String genSignKey(Map<String, String> param, String... paramNames){
		//按参数名asscic码排序
		List<String> names=new ArrayList();
		for(String paramName:paramNames)
			names.add(paramName);
		java.util.Collections.sort(names);

		String strSign = appsecret;
		for(String name:names){
			strSign += name+ (param.get(name) == null ? "" : param.get(name)); 
		}
		strSign += appsecret;
		
		try {
			strSign = new String(strSign.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(strSign);
		String md5 = $.md5(strSign).toUpperCase();
		//String md5 = new Md5Util().MD5(strSign).toUpperCase();
		
		return md5;
	}
	
	/**
	 * 发送Url请求
	 * @param path
	 * @param urlParam
	 * @return
	 */
	public static String sendHttpPost(String methodType, String path, String urlParam, Map<String, String> headers, String contentType, String encode){
		try {
			//创建连接
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(methodType);	
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", contentType);
            
            connection.connect();

            //POST请求
//            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//            out.writeBytes(urlParam);
//            out.flush();
//            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
               // lines = new String(lines.getBytes(), encode);
                sb.append(lines);
            }
            
            reader.close();
            connection.disconnect();
            return sb.toString();
            
		} catch (Exception e) {
			e.printStackTrace();
		} 
		  
		return null;
	}
	
	static String parseParam2(Map<String, String> param){
		StringBuffer sbf = new StringBuffer();
		if(param!=null){
			int i = 0;
			for(String key : param.keySet()){
				try {
					Object obj = param.get(key);
					sbf.append(i == 0 ? "" : "&").append(key).append("=").append(obj);
					//sbf.append(key).append(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
				i ++;
			}
		}
		return sbf.toString();
	}
	
}
