package com.ccclubs.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * 
 * http访问助手类
 * 
 * @author Joel
 *
 */
public abstract class APICallHelper {
	
	final static String CHARSET = "utf-8";
	
	final static String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
	
	static final Logger log = Logger.getRootLogger();
	
	public static String doDelete(String url, Map<String, String> params){
		return doDelete(url, params, false);
	}
	
	/**
	 * 发起delete请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doDelete(String url, Map<String, String> params, boolean ssl){
		CloseableHttpClient httpClient = null; 
    	try{
	        httpClient = ssl ? createSSLClientDefault() : HttpClients.createDefault();
			MyHttpDelete delete = new MyHttpDelete(url); 
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); 
			if(params!=null){
				for(String key : params.keySet()){
					nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
				}
			}

			delete.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(delete);
			String result = parseResponse(response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally{
			if(httpClient!=null)try{httpClient.close();}catch (Exception e) {e.printStackTrace();}
		}
		return null;
	}
    
	public static String doGet(String url){
		return doGet(url, null, false);
	}
	
	/**
     * 发起get请求
     * @param url
     * @return
     */
    public static String doGet(String url, Map<String, String> headers, boolean ssl) {  
    	CloseableHttpClient httpClient = null; 
    	String body = null;  
    	try{
	        httpClient = ssl ? createSSLClientDefault() : HttpClients.createDefault();
	        HttpGet get = new HttpGet(url);
	        if(headers!=null){
	        	  for(String key : headers.keySet()){
	        		  get.setHeader(key, headers.get(key));
	        	  }
	        }
	        body = invoke(httpClient, get);
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error(e);
    	}finally{
    		closeHttpClient(httpClient);
    	}
        return body;  
    }  
	
    /**
     * 默认x-www-form-urlencoded
     * @param url
     * @param params
     * @return
     */
	public static String doPost(String url, Map<String, String> params) {  
		return doPost(url, params, null, null, null, false);
	}
    
    /**
     * 默认x-www-form-urlencoded
     * @param url
     * @param params
     * @param headers
     * @return
     */
	public static String doPost(String url, Map<String, String> params, Map<String, String> headers) {  
		return doPost(url, params, null, headers, null, false);
	}
	
	/**
	 * 默认x-www-form-urlencoded
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPostSSL(String url, Map<String, String> params) {  
		return doPost(url, params, null, null, null, true);
	}
	
	/**
	 * 默认x-www-form-urlencoded
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPostSSL(String url, Map<String, String> params, Map<String, String> headers) {  
		return doPost(url, params, null, headers, null, true);
	}
	
	/**
	 * 发起post请求
	 * @param url
	 * @param params
	 * @return
	 */
    public static String doPost(String url, Map<String, String> params, String contentType, Map<String, String> headers, String charset, boolean ssl) {
        CloseableHttpClient  httpClient = null;
        String body = null;
        try{
        	httpClient = ssl ? createSSLClientDefault() : HttpClients.createDefault();
	        HttpPost post = createPostForm(url, params, charset, contentType);  
	        if(headers!=null){
	        	  for(String key : headers.keySet()){
	        		  post.setHeader(key, headers.get(key));
	        	  }
	        }
	        body = invoke(httpClient, post);  
        }catch(Exception e){
        	e.printStackTrace();
        	log.error(e);
        }finally{
        	closeHttpClient(httpClient);
        }
        return body;  
    }  
    
    private static HttpPost createPostForm(String url, Map<String, String> params, String charset, String contentType){  
        
        HttpPost httpost = new HttpPost(url);  
        List<org.apache.http.NameValuePair> nvps = new ArrayList <org.apache.http.NameValuePair>();  
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
            nvps.add(new BasicNameValuePair(key, params.get(key)));  
        }  
        
        try {  
        	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, charset == null ? HTTP.UTF_8 : charset);
        	entity.setContentType(contentType == null ? CONTENT_TYPE : contentType);
            httpost.setEntity(entity);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
          
        return httpost;  
    }  
    
    public static String doPostJSON(String url, String json, Map<String, String> headers){
    	return doPostJSON(url, json, headers, false);
    }
    
    /**
     * post发送json格式的参数
     * @param url
     * @param json
     * @param headers
     * @param ssl 是否SSL信任
     * @return
     */
    public static String doPostJSON(String url, String json, Map<String, String> headers, boolean ssl) {
    	CloseableHttpClient httpClient = null; 
    	try{
	        httpClient = ssl ? createSSLClientDefault() : HttpClients.createDefault();
	        HttpPost httpPost = new HttpPost(url);
	        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
	        httpPost.addHeader("Accept", "application/json");
	        
	        if(headers!=null){
				  for(String key : headers.keySet()){
					  httpPost.addHeader(key, headers.get(key));
				  }
	        }
	        
	        StringEntity se = new StringEntity(json);
	        se.setContentType("application/json");
	        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	        httpPost.setEntity(se);
	        httpClient.execute(httpPost);
	        
	        String body = invoke(httpClient, httpPost);
	        return body;
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error(e);
    	}finally{
    		if(httpClient!=null){
    			try{ httpClient.close();}catch(Exception e){e.printStackTrace();}
    		}
    	}
    	return null;
    }
    
    private static void closeHttpClient(CloseableHttpClient  httpClient){
    	if(httpClient!=null)try{httpClient.close();}catch (Exception e) {e.printStackTrace();}
    }
          
    private static String invoke(CloseableHttpClient httpclient,  
            HttpUriRequest httpost) {  
        HttpResponse response = sendRequest(httpclient, httpost);  
        String body = parseResponse(response);  
        return body;  
    }  
  
    private static String parseResponse(HttpResponse response) {  
        HttpEntity entity = response.getEntity();  
        String body = null;  
        try {  
            body = EntityUtils.toString(entity);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return body;  
    }  
  
    private static HttpResponse sendRequest(CloseableHttpClient httpclient,  HttpUriRequest httpost) {  
        HttpResponse response = null;  
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return response;  
    }  
  
    /**
	  * 创建SSL信任请求对象
	  * @return
	  */
	 public static CloseableHttpClient createSSLClientDefault(){
		 try {
		       	SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
	                  //信任所有
	                  public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	                      return true;
	                  }
             }).build();
             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
         } catch (KeyManagementException e) {
	            e.printStackTrace();
         } catch (NoSuchAlgorithmException e) {
       	  	e.printStackTrace();
         } catch (KeyStoreException e) {
             e.printStackTrace();
         }
         return  HttpClients.createDefault();
	 }
	
	public static <T> T fromJson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return t;
    }
	
	public static Map<String, Object> fromJson(String jsonString){ 
		try{
			Gson gson = new Gson();
			return gson.fromJson(jsonString,new TypeToken<Map<String, Object>>(){}.getType());  
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
 
        Map<String, Object> sortMap = new TreeMap<String, Object>(
                new MapKeyComparator());
 
        sortMap.putAll(map);
 
        return sortMap;
    }
    
    public static String getSortedParamsUrl(Map<String, Object> map){
    	map = sortMapByKey(map);
    	StringBuffer sbf = new StringBuffer();
    	int i = 0;
    	for(String key : map.keySet()){
    		sbf.append((i == 0 ? "" : "&")+key+"="+map.get(key));
    		i++;
    	}
    	return sbf.toString();
    }
    
    /**
     * HttpClient helper
     * @author He Daoyuan
     * @version	2016-04-01
     */
    public static String getResultAsString(HttpClient client, HttpPost method, List<NameValuePair> paramList) {
        try {
        	method.setEntity(new UrlEncodedFormEntity(paramList, CHARSET));
            HttpResponse resp = client.execute(method);
            return EntityUtils.toString(resp.getEntity());
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        } finally {
        	method.releaseConnection();
        }

        return null;
    }
    
}

class MyHttpDelete extends HttpEntityEnclosingRequestBase {
	public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public MyHttpDelete(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public MyHttpDelete(final URI uri) {
        super();
        setURI(uri);
    }

    public MyHttpDelete() {
        super();
    }
}

class MapKeyComparator implements Comparator<String>{
	 
    @Override
    public int compare(String str1, String str2) {
         
        return str1.compareTo(str2);
    }
}