package com.ccclubs.action.thread.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

/**
 * 安徽通道
 * @author uiu
 */
public class sdk_zyer_cn extends BaseSMS {

	@Override
	public String query() {
		return "当前短信通道余量查询功能未实现";
	}

	@Override
	public String send(String mobile, String content) {
		String ret = post("http://sdk.zyer.cn/SmsService/SmsService.asmx/SendEx", 
				Lazy.add("LoginName", "yian454")
				.add("Password", digest("lya868").toLowerCase())
				.add("SmsKind", "808")
				.add("SendSim", mobile)
				.add("ExpSmsId", "")
				.add("MsgContext", content),
				"UTF-8"
			);
		return ret;	
	}

	public static String post(String action,Map<String,String> params,String encode){
		StringBuffer response = new StringBuffer();
		PostMethod method = new PostMethod(action); 		
		try{
			HttpClient client=new HttpClient();
			Set<String> keyset = params.keySet();			
			NameValuePair[] parametersBody = new NameValuePair[keyset.size()];
			int i=0;
			for(String key:keyset){
				String value =(String) params.get(key);
				parametersBody[i++]=new NameValuePair(key, value);
			}
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,encode);
			//method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));		
			method.setRequestBody(parametersBody);
			method.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			client.executeMethod(method);
			 //下面是读取网站的返回网页，例如上传成功之类的 
            //if (method.getStatusCode() == HttpStatus.SC_OK)
			{  
                //读取为 InputStream，在网页内容数据量大时候推荐使用  
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),encode));  
                String line=null;
                while ((line = reader.readLine()) != null){  
                     response.append(line);  
                }  
                reader.close();            	
            } 
		}catch(Exception ex){
			ex.printStackTrace();
			return $.json("提交短信时出错");
		}catch(Error er){
			return $.json("提交短信时出错");
		}finally{
			method.releaseConnection();
		}
		return response.toString();
	}	
	public final static String digest(String plainText) {

	   String md5Str = null;
	   try {

	    StringBuffer buf = new StringBuffer();

	    MessageDigest md = MessageDigest.getInstance("MD5");
	   
	    md.update(plainText.getBytes());

	    byte b[] = md.digest();
	    int i;

	    for (int offset = 0; offset < b.length; offset++) {

	     i = b[offset];

	     if (i < 0) {
	      i += 256;
	     }

	     if (i < 16) {
	      buf.append("0");
	     }

	     buf.append(Integer.toHexString(i));

	    }

	    md5Str = buf.toString().substring(8,24);

	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return md5Str;
	}
}
