package com.ccclubs.action.app.official.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.app.official.model.ValueEntry;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsSpecialModel;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public abstract class ApiBase {

	final static String CHARSET = "utf-8";
	
	final static String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
	
	final static String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public abstract Map<String, Object> createOrder(CsSpecialCar specialCar, Type type);
	
	public final static int THIRD_DIDI = 1;			//滴滴专车	
	
	public final static int THIRD_YIDAO = 2;		//易到专车
	
	public final static String CALL_FAIL = "100001";						//首次叫车失败					//失败状态
	
	public final static String RECALL_FAIL = "100002";						//重新叫车失败					//失败状态
	
	public final static String ONCALLING   = "110000";						//正在叫车中...
	
	public final static String RENT_CAR = "分时租赁";
	
	public final static String CALL_CAR = "特急专送";
	/**
	 * @deprecated
	 */
	public final static String TIMEOUT_2_ONRECALL1  = "100003";				//首次接单超时-》重新叫车中
	
	/**
	 * @deprecated
	 */
	public final static String RECALLTIMEOUT_2_ONCHANGERECALL1  = "100004";	//重新叫车接单超时-》切换渠道叫车中
	
	//public static AtomicReference<String> TOKEN = new AtomicReference<String>(); 
	
	public static volatile boolean isTest = false;
	
	public static volatile boolean useYidao = false;
	
	public static volatile boolean useState = false;
	
	public enum Type{
		/**
		 * 实时单
		 */
		REAL_TIME,
		/**
		 * 预约单
		 */
		ORDER  
	}	
	/**
	 * 判断当前的环境，是否测试环境
	 * @return
	 */
	static boolean isTestServer(){
//		return !$.empty($.config("isAppTest"));
		//return isTest;
		return !Env.isOnline();
	}
	
	/**
	 * 发送JSON请求
	 * @param path
	 * @param jsonParam
	 * @return
	 */
	public static String sendJson(String path, String jsonParam){
		  try {
	            //创建连接
	            URL url = new URL(path);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setRequestMethod("POST");
	            connection.setUseCaches(false);
	            connection.setInstanceFollowRedirects(true);
	            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
	            connection.connect();

	            //POST请求
	            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	            out.writeBytes(jsonParam);
	            out.flush();
	            out.close();

	            //读取响应
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String lines;
	            StringBuffer sb = new StringBuffer("");
	            while ((lines = reader.readLine()) != null) {
	                lines = new String(lines.getBytes(), CHARSET);
	                sb.append(lines);
	            }
	            reader.close();
	            connection.disconnect();
	            return sb.toString();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  
		  return null;
	}
	
	static String parseParam2(Map<String, Object> param, boolean doEncode){
		StringBuffer sbf = new StringBuffer();
		if(param!=null){
			int i = 0;
			for(String key : param.keySet()){
				try {
					Object obj = param.get(key);
					Object value = null;
					if(obj!=null){
						if(obj instanceof ValueEntry){
							ValueEntry valEntry = (ValueEntry)param.get(key);
							value = valEntry.getVal();
							if(doEncode && StringUtils.isNotEmpty(valEntry.getEncode())){
								value = URLEncoder.encode((String)value , valEntry.getEncode());
							}else{
								value = valEntry.getVal();
							}
						}else{
							value = obj;
						}
					}
					sbf.append(i == 0 ? "" : "&").append(key).append("=").append(value);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				i ++;
			}
		}
		return sbf.toString();
	}
	
	@Deprecated
	static String parseParam(Map<String, Object> param, boolean encoder){
		StringBuffer sbf = new StringBuffer();
		if(param!=null){
			int i = 0;
			for(String key : param.keySet()){
				try {
					Object val = param.get(key);
					if(val!=null){
						if(encoder){
							if(val instanceof String){
								val = URLEncoder.encode((String)val , "UTF-8");
							}
						}
					}
					sbf.append(i == 0 ? "" : "&").append(key).append("=").append(val);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				i ++;
			}
		}
		return sbf.toString();
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
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(urlParam);
            out.flush();
            out.close();

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
	
	/**
	 * 生成签名
	 * @param param
	 * @return
	 */
	public static String genSignKey(Map<String, Object> param){
		param = sortMapByKey(param);
		String urlParam = parseParam2(param, false);
		param.put("sign", Md5Util.MD5(urlParam).toLowerCase());
		param.remove("sign_key");
		urlParam = parseParam2(param, true);
		return urlParam;
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
    
    /**
     * 车型互转
     * [1：舒适性，2：商务型]
     * csCarModel表缺少一个字段，这里只能手动转了
     * @param carmodel
     * @return
     */
    public static Long changeCarModel(CsSpecialModel carmodel){
    	if(carmodel.getCssmThird().intValue() == THIRD_DIDI){
    		if(carmodel.getCssmThirdCode().equals("100")){			//滴滴舒适型代码
    			return 1L;								  			//易到舒适型代码
    		}else if(carmodel.getCssmThirdCode().equals("400")){	//滴滴商务型代码
    			return 2L;								  			//易到商务型代码
    		}
    	}else if(carmodel.getCssmThird().intValue() == THIRD_YIDAO){
    		if(carmodel.getCssmThirdCode().equals("2")){			//易到舒适型代码
    			return 1L;								  			//滴滴舒适型代码
    		}else if(carmodel.getCssmThirdCode().equals("5")){		//易到商务型代码
    			return 2L;								  			//滴滴商务型代码
    		}
    	}
    	return null;
    }
    
    
    public static Map<String, Object> splitLatLng(CsSpecialCar csSpecialCar){
    	Map<String, Object> param = new HashMap<String, Object>();
    		if(csSpecialCar.getCsscStartPoint() != null && csSpecialCar.getCsscFinishPoint() != null){
    			if(csSpecialCar.getCsscStartPoint() != "" && csSpecialCar.getCsscFinishPoint() != ""){
    				String[] start_Point = csSpecialCar.getCsscStartPoint().split(";");
    				String[] finish_Point = csSpecialCar.getCsscFinishPoint().split(";");
    				param.put("start_longitude", start_Point[1]);
    				param.put("start_latitude", start_Point[0]);
    				param.put("end_longitude", finish_Point[1]);
    				param.put("end_latitude", finish_Point[0]);
    			}    			   			
    		}
    	return param;
    }
    
}


class MapKeyComparator implements Comparator<String>{
	 
    @Override
    public int compare(String str1, String str2) {
         
        return str1.compareTo(str2);
    }
}
